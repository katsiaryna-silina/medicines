package by.epam.silina.medicines.util;

import by.epam.silina.medicines.model.ValidationStatusEnum;
import by.epam.silina.medicines.model.users.Client;
import by.epam.silina.medicines.model.users.Employee;
import by.epam.silina.medicines.util.impl.UserValidationUtilImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static by.epam.silina.medicines.config.Constant.*;

public class UserHandler extends DefaultHandler {
    private static final Logger log = LoggerFactory.getLogger(UserHandler.class);
    private static final UserHandler instance = new UserHandler();
    private final UserValidationUtil userValidationUtil = UserValidationUtilImpl.getInstance();
    private List<Client> clientList;
    private List<Employee> employeeList;
    private List<ValidationStatusEnum> validationStatuses;
    private Client client;
    private Employee employee;
    private StringBuilder elementValueBuilder;

    private UserHandler() {
    }

    public static UserHandler getInstance() {
        return instance;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValueBuilder == null) {
            elementValueBuilder = new StringBuilder();
        } else {
            elementValueBuilder.append(ch, start, length);
        }
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case USERS_USERS:
                clientList = new ArrayList<>();
                employeeList = new ArrayList<>();
                break;
            case USERS_CLIENT:
                client = Client.builder().build();
                validationStatuses = new ArrayList<>();
                break;
            case USERS_EMPLOYEE:
                employee = Employee.builder().build();
                validationStatuses = new ArrayList<>();
                break;
            case CLIENT_USERNAME:
            case CLIENT_PASSWORD:
            case CLIENT_EMAIL:
            case CLIENT_LAST_LOGIN:
            case CLIENT_TELEPHONE_NUMBER:
            case EMPLOYEE_USERNAME:
            case EMPLOYEE_EMAIL:
            case EMPLOYEE_PASSWORD:
            case EMPLOYEE_LAST_LOGIN:
            case EMPLOYEE_POSITION:
                elementValueBuilder = new StringBuilder();
                break;
            default:
                log.warn(UNKNOWN_ELEMENT, qName);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String elementValue = elementValueBuilder.toString();

        if (USERS_EMPLOYEE.equals(qName)) {
            processEmployeeEndElement();
        } else if (USERS_CLIENT.equals(qName)) {
            processClientEndElement();
        } else if (qName.startsWith(START_STRING_CLIENT)) {
            processClientEndElements(qName, elementValue);
        } else if (qName.startsWith(START_STRING_EMPLOYEE)) {
            processEmployeeEndElements(qName, elementValue);
        }
    }

    private void processEmployeeEndElements(String qName, String elementValue) {
        switch (qName) {
            case EMPLOYEE_USERNAME:
                validationStatuses.add(userValidationUtil.validateUsername(elementValue));
                employee.setUsername(elementValue);
                break;
            case EMPLOYEE_PASSWORD:
                validationStatuses.add(userValidationUtil.validatePassword(elementValue));
                employee.setPassword(elementValue);
                break;
            case EMPLOYEE_EMAIL:
                validationStatuses.add(userValidationUtil.validateEmail(elementValue));
                employee.setEmail(elementValue);
                break;
            case EMPLOYEE_LAST_LOGIN:
                ValidationStatusEnum validationStatusLastLogin = userValidationUtil.validateLastLoginFromString(elementValue);
                validationStatuses.add(validationStatusLastLogin);
                if (validationStatusLastLogin.getStatusNumber() == 0) {
                    employee.setLastLoginDateAndTime(LocalDateTime.parse(elementValue));
                }
                break;
            case EMPLOYEE_POSITION:
                validationStatuses.add(userValidationUtil.validateEmployeePosition(elementValue));
                employee.setPosition(elementValue);
                break;
            default:
                log.warn(UNKNOWN_ELEMENT, qName);
                break;
        }
    }

    private void processClientEndElements(String qName, String elementValue) {
        switch (qName) {
            case CLIENT_USERNAME:
                validationStatuses.add(userValidationUtil.validateUsername(elementValue));
                client.setUsername(elementValue);
                break;
            case CLIENT_PASSWORD:
                validationStatuses.add(userValidationUtil.validatePassword(elementValue));
                client.setPassword(elementValue);
                break;
            case CLIENT_EMAIL:
                validationStatuses.add(userValidationUtil.validateEmail(elementValue));
                client.setEmail(elementValue);
                break;
            case CLIENT_LAST_LOGIN:
                ValidationStatusEnum validationStatusLastLogin = userValidationUtil.validateLastLoginFromString(elementValue);
                validationStatuses.add(validationStatusLastLogin);
                if (validationStatusLastLogin.getStatusNumber() == 0) {
                    client.setLastLoginDateAndTime(LocalDateTime.parse(elementValue));
                }
                break;
            case CLIENT_TELEPHONE_NUMBER:
                validationStatuses.add(userValidationUtil.validateTelephoneNumber(elementValue));
                client.setTelephoneNumber(elementValue);
                break;
            default:
                log.warn(UNKNOWN_ELEMENT, qName);
                break;
        }
    }

    private void processClientEndElement() {
        List<ValidationStatusEnum> validationErrors = validationStatuses.stream()
                .filter(el -> el.getStatusNumber() != 0)
                .collect(Collectors.toList());
        if (validationErrors.isEmpty()) {
            clientList.add(client);
        } else {
            log.error(CANNOT_ADD_INVALID_CLIENT, client);
            validationErrors.forEach(el -> log.error(el.getStatusDescription()));
        }
    }

    private void processEmployeeEndElement() {
        List<ValidationStatusEnum> validationErrors = validationStatuses.stream()
                .filter(el -> el.getStatusNumber() != 0)
                .collect(Collectors.toList());
        if (validationErrors.isEmpty()) {
            employeeList.add(employee);
        } else {
            log.error(CANNOT_ADD_INVALID_EMPLOYEE, employee);
            validationErrors.forEach(el -> log.error(el.getStatusDescription()));
        }
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
