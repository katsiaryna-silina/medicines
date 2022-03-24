package by.epam.silina.medicines.model;

import static by.epam.silina.medicines.config.Constant.*;

public enum ValidationStatusEnum {
    SUCCESS_STATUS(0, OK_STATUS),
    ERROR_EMAIL_IS_EMPTY(1, EMAIL_IS_EMPTY),
    ERROR_EMAIL_IS_NOT_VALID(2, EMAIL_IS_NOT_VALID),
    ERROR_USERNAME_IS_EMPTY(3, USERNAME_IS_EMPTY),
    ERROR_USERNAME_IS_NOT_VALID(4, USERNAME_IS_NOT_VALID),
    ERROR_PASSWORD_IS_EMPTY(5, PASSWORD_IS_EMPTY),
    ERROR_PASSWORD_IS_NOT_VALID(6, PASSWORD_IS_NOT_VALID),
    ERROR_TELEPHONE_NUMBER_IS_EMPTY(7, TELEPHONE_NUMBER_IS_EMPTY),
    ERROR_TELEPHONE_NUMBER_IS_NOT_VALID(8, TELEPHONE_NUMBER_IS_NOT_VALID),
    ERROR_EMPLOYEE_POSITION_IS_EMPTY(9, EMPLOYEE_POSITION_IS_EMPTY),
    ERROR_LAST_LOGIN_IS_NULL(10, LAST_LOGIN_IS_NULL),
    ERROR_LAST_LOGIN_IS_NOT_VALID(11, LAST_LOGIN_IS_EMPTY);

    int statusNumber;
    String statusDescription;

    ValidationStatusEnum(int statusNumber, String statusDescription) {
        this.statusNumber = statusNumber;
        this.statusDescription = statusDescription;
    }

    public int getStatusNumber() {
        return statusNumber;
    }

    public String getStatusDescription() {
        return statusDescription;
    }
}
