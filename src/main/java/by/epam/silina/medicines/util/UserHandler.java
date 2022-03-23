package by.epam.silina.medicines.util;

import by.epam.silina.medicines.datasource.InMemoryDataSource;
import by.epam.silina.medicines.model.users.Client;
import by.epam.silina.medicines.model.users.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static by.epam.silina.medicines.config.Constant.*;

public class UserHandler extends DefaultHandler {
    private static final UserHandler instance = new UserHandler();
    private InMemoryDataSource inMemoryDataSource;
    //todo lists?
    private List<Client> clientList;
    private List<Employee> employeeList;
    private StringBuilder elementValue;

    private UserHandler() {
    }

    public static UserHandler getInstance() {
        return instance;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() {
        inMemoryDataSource = InMemoryDataSource.getInstance();
    }

    @Override
    public void endDocument() {
        inMemoryDataSource.getClients().addAll(clientList);
        inMemoryDataSource.getEmployees().addAll(employeeList);
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case USERS_USERS:
                clientList = new ArrayList<>();
                employeeList = new ArrayList<>();
                break;
            case USERS_CLIENT:
                clientList.add(Client.builder().build());
                break;
            case USERS_EMPLOYEE:
                employeeList.add(Employee.builder().build());
                break;
            case CLIENT_USERNAME:
            case CLIENT_PASSWORD:
            case CLIENT_EMAIL:
            case EMPLOYEE_USERNAME:
            case EMPLOYEE_EMAIL:
            case EMPLOYEE_PASSWORD:
            case CLIENT_TELEPHONE_NUMBER:
            case EMPLOYEE_POSITION:
                elementValue = new StringBuilder();
                break;
            default:
                //todo
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        //todo is it ok startsWith() method ?
        if (qName.startsWith(START_STRING_CLIENT)) {
            switch (qName) {
                case CLIENT_USERNAME:
                    latestClient().setUsername(elementValue.toString());
                    break;
                case CLIENT_PASSWORD:
                    latestClient().setPassword(elementValue.toString());
                    break;
                case CLIENT_EMAIL:
                    latestClient().setEmail(elementValue.toString());
                    break;
                case CLIENT_TELEPHONE_NUMBER:
                    latestClient().setTelephoneNumber(elementValue.toString());
                    break;
                default:
                    //todo
                    break;
            }
        } else if (qName.startsWith(START_STRING_EMPLOYEE)) {
            switch (qName) {
                case EMPLOYEE_USERNAME:
                    latestEmployee().setUsername(elementValue.toString());
                    break;
                case EMPLOYEE_PASSWORD:
                    latestEmployee().setPassword(elementValue.toString());
                    break;
                case EMPLOYEE_EMAIL:
                    latestEmployee().setEmail(elementValue.toString());
                    break;
                case EMPLOYEE_POSITION:
                    latestEmployee().setPosition(elementValue.toString());
                    break;
                default:
                    //todo
                    break;
            }
        }
    }

    private Client latestClient() {
        int latestClientIndex = clientList.size() - 1;
        return clientList.get(latestClientIndex);
    }

    private Employee latestEmployee() {
        int latestEmployeeIndex = employeeList.size() - 1;
        return employeeList.get(latestEmployeeIndex);
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
