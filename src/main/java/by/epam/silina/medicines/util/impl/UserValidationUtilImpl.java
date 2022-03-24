package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.model.ValidationStatusEnum;
import by.epam.silina.medicines.util.UserValidationUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import static by.epam.silina.medicines.config.Constant.*;

public class UserValidationUtilImpl implements UserValidationUtil {
    private static final UserValidationUtil instance = new UserValidationUtilImpl();

    private UserValidationUtilImpl() {
    }

    public static UserValidationUtil getInstance() {
        return instance;
    }

    @Override
    public ValidationStatusEnum validateEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return ValidationStatusEnum.ERROR_EMAIL_IS_EMPTY;
        } else {
            if (Pattern.compile(REGEX_FOR_CHECKING_EMAIL)
                    .matcher(email)
                    .matches()
                    && email.length() >= EMAIL_MIN_LENGTH
                    && email.length() <= EMAIL_MAX_LENGTH) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_EMAIL_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return ValidationStatusEnum.ERROR_USERNAME_IS_EMPTY;
        } else {
            if (username.length() >= USERNAME_MIN_LENGTH
                    && username.length() <= USERNAME_MAX_LENGTH) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_USERNAME_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validatePassword(String password) {
        if (StringUtils.isBlank(password)) {
            return ValidationStatusEnum.ERROR_PASSWORD_IS_EMPTY;
        } else {
            if (password.length() >= PASSWORD_MIN_LENGTH
                    && password.length() <= PASSWORD_MAX_LENGTH) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_PASSWORD_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateTelephoneNumber(String telephoneNumber) {
        if (StringUtils.isBlank(telephoneNumber)) {
            return ValidationStatusEnum.ERROR_TELEPHONE_NUMBER_IS_EMPTY;
        } else {
            if (Pattern.compile(REGEX_FOR_CHECKING_TELEPHONE_NUMBER)
                    .matcher(telephoneNumber)
                    .matches()
                    && telephoneNumber.length() >= TELEPHONE_NUMBER_MIN_LENGTH
                    && telephoneNumber.length() <= TELEPHONE_NUMBER_MAX_LENGTH) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_TELEPHONE_NUMBER_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateEmployeePosition(String position) {
        if (StringUtils.isBlank(position)) {
            return ValidationStatusEnum.ERROR_EMPLOYEE_POSITION_IS_EMPTY;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateLastLoginFromLocalDateTime(LocalDateTime lastLogin) {
        if (lastLogin == null) {
            return ValidationStatusEnum.ERROR_LAST_LOGIN_IS_NULL;
        } else {
            if (LocalDateTime.now().isAfter(lastLogin)) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_LAST_LOGIN_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateLastLoginFromString(String lastLogin) {
        if (lastLogin == null) {
            return ValidationStatusEnum.ERROR_LAST_LOGIN_IS_NULL;
        } else {
            try {
                LocalDateTime lastLoginDateAndTime = LocalDateTime.parse(lastLogin);
                return validateLastLoginFromLocalDateTime(lastLoginDateAndTime);
            } catch (DateTimeParseException e) {
                return ValidationStatusEnum.ERROR_LAST_LOGIN_IS_NOT_VALID;
            }
        }
    }
}
