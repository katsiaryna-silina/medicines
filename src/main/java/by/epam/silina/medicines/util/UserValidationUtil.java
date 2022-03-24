package by.epam.silina.medicines.util;

import by.epam.silina.medicines.model.ValidationStatusEnum;

import java.time.LocalDateTime;

public interface UserValidationUtil {
    ValidationStatusEnum validateEmail(String email);

    ValidationStatusEnum validateUsername(String username);

    ValidationStatusEnum validatePassword(String password);

    ValidationStatusEnum validateTelephoneNumber(String telephoneNumber);

    ValidationStatusEnum validateEmployeePosition(String position);

    ValidationStatusEnum validateLastLoginFromLocalDateTime(LocalDateTime lastLogin);

    ValidationStatusEnum validateLastLoginFromString(String lastLogin);
}
