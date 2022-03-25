package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.model.ValidationStatusEnum;
import by.epam.silina.medicines.model.medicines.*;
import by.epam.silina.medicines.util.MedicineValidationUtil;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import static by.epam.silina.medicines.config.Constant.*;

public class MedicineValidationUtilImpl implements MedicineValidationUtil {
    private static final MedicineValidationUtil instance = new MedicineValidationUtilImpl();

    private MedicineValidationUtilImpl() {
    }

    public static MedicineValidationUtil getInstance() {
        return instance;
    }

    @Override
    public ValidationStatusEnum validateId(UUID id) {
        if (id == null) {
            return ValidationStatusEnum.ERROR_ID_IS_NULL;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateId(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return validateId(uuid);
        } catch (IllegalArgumentException e) {
            return ValidationStatusEnum.ERROR_ID_IS_NOT_VALID;
        }
    }

    @Override
    public ValidationStatusEnum validateMedicineName(String medicineName) {
        if (StringUtils.isBlank(medicineName)) {
            return ValidationStatusEnum.ERROR_MEDICINE_NAME_IS_EMPTY;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateCompany(String company) {
        if (StringUtils.isBlank(company)) {
            return ValidationStatusEnum.ERROR_COMPANY_IS_EMPTY;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateGroup(String group) {
        if (StringUtils.isBlank(group)) {
            return ValidationStatusEnum.ERROR_GROUP_IS_EMPTY;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateVersions(List<Version> versions) {
        if (versions == null) {
            return ValidationStatusEnum.ERROR_VERSIONS_ARE_NULL;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateMedicineTypeEnum(MedicineTypeEnum medicineTypeEnum) {
        if (medicineTypeEnum == null) {
            return ValidationStatusEnum.ERROR_MEDICINE_TYPE_ENUM_IS_NULL;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }


    @Override
    public ValidationStatusEnum validateDosage(String dosage) {
        if (StringUtils.isBlank(dosage)) {
            return ValidationStatusEnum.ERROR_DOSAGE_IS_EMPTY;
        } else {
            if (Pattern.compile(REGEX_FOR_CHECKING_DOSAGE)
                    .matcher(dosage)
                    .matches()
                    && dosage.length() >= DOSAGE_MIN_LENGTH
                    && dosage.length() <= DOSAGE_MAX_LENGTH) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_DOSAGE_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateCertificate(Certificate certificate) {
        if (certificate == null) {
            return ValidationStatusEnum.ERROR_CERTIFICATE_IS_NULL;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateMedicinePackages(List<MedicinePackage> medicinePackages) {
        if (medicinePackages == null) {
            return ValidationStatusEnum.ERROR_MEDICINE_PACKAGES_ARE_NULL;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateCertificateName(String certificateName) {
        if (StringUtils.isBlank(certificateName)) {
            return ValidationStatusEnum.ERROR_CERTIFICATE_NAME_IS_EMPTY;
        } else {
            if (certificateName.length() >= MIN_TEXT_LENGTH
                    && certificateName.length() <= MAX_TEXT_LENGTH) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_CERTIFICATE_NAME_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateWhoIssued(String whoIssued) {
        if (StringUtils.isBlank(whoIssued)) {
            return ValidationStatusEnum.ERROR_WHO_ISSUED_IS_EMPTY;
        } else {
            if (whoIssued.length() >= MIN_TEXT_LENGTH
                    && whoIssued.length() <= MAX_TEXT_LENGTH) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_WHO_ISSUED_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateRegistrationNumber(String registrationNumber) {
        if (StringUtils.isBlank(registrationNumber)) {
            return ValidationStatusEnum.ERROR_REGISTRATION_NUMBER_IS_EMPTY;
        } else {
            if (registrationNumber.length() >= MIN_TEXT_LENGTH
                    && registrationNumber.length() <= MAX_TEXT_LENGTH) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_REGISTRATION_NUMBER_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateRegistrationDateFrom(LocalDate registrationDateFrom) {
        if (registrationDateFrom == null) {
            return ValidationStatusEnum.ERROR_REGISTRATION_DATE_FROM_IS_NULL;
        } else {
            if (LocalDate.now().isAfter(registrationDateFrom)) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_REGISTRATION_DATE_FROM_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateRegistrationDateFrom(String registrationDateFrom) {
        if (registrationDateFrom == null) {
            return ValidationStatusEnum.ERROR_REGISTRATION_DATE_FROM_IS_NULL;
        } else {
            try {
                LocalDate registrationDate = LocalDate.parse(registrationDateFrom);
                return validateRegistrationDateFrom(registrationDate);
            } catch (
                    DateTimeParseException e) {
                return ValidationStatusEnum.ERROR_REGISTRATION_DATE_FROM_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateRegistrationDateTo(LocalDate registrationDateTo) {
        if (registrationDateTo == null) {
            return ValidationStatusEnum.ERROR_REGISTRATION_DATE_TO_IS_NULL;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateRegistrationDateTo(String registrationDateTo) {
        if (registrationDateTo == null) {
            return ValidationStatusEnum.ERROR_REGISTRATION_DATE_TO_IS_NULL;
        } else {
            try {
                LocalDate registrationDate = LocalDate.parse(registrationDateTo);
                return validateRegistrationDateTo(registrationDate);
            } catch (
                    DateTimeParseException e) {
                return ValidationStatusEnum.ERROR_REGISTRATION_DATE_TO_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validatePackageTypeEnum(PackageTypeEnum packageTypeEnum) {
        if (packageTypeEnum == null) {
            return ValidationStatusEnum.ERROR_PACKAGE_TYPE_ENUM_IS_NULL;
        } else {
            return ValidationStatusEnum.SUCCESS_STATUS;
        }
    }

    @Override
    public ValidationStatusEnum validateMedicineNumberInPackage(Integer number) {
        if (number == null) {
            return ValidationStatusEnum.ERROR_MEDICINE_NUMBER_IN_PACKAGE_IS_NULL;
        } else {
            if (number > 0) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_MEDICINE_NUMBER_IN_PACKAGE_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateMedicineNumberInPackage(String number) {
        if (number == null) {
            return ValidationStatusEnum.ERROR_MEDICINE_NUMBER_IN_PACKAGE_IS_NULL;
        } else {
            try {
                Integer medicineNumber = Integer.valueOf(number);
                return validateMedicineNumberInPackage(medicineNumber);
            } catch (NumberFormatException e) {
                return ValidationStatusEnum.ERROR_MEDICINE_NUMBER_IN_PACKAGE_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validatePrice(BigDecimal price) {
        if (price == null) {
            return ValidationStatusEnum.ERROR_PRICE_IS_NULL;
        } else {
            int numberOfDecimalPlace = Math.max(0, price.scale());
            if (price.compareTo(BigDecimal.valueOf(MIN_PRICE)) > 0 && numberOfDecimalPlace <= NUMBER_OF_DECIMAL_PLACE) {
                return ValidationStatusEnum.SUCCESS_STATUS;
            } else {
                return ValidationStatusEnum.ERROR_PRICE_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validatePrice(String price) {
        if (price == null) {
            return ValidationStatusEnum.ERROR_PRICE_IS_NULL;
        } else {
            try {
                double medicinePrice = Double.parseDouble(price);
                BigDecimal bigDecimalPrice = BigDecimal.valueOf(medicinePrice);
                return validatePrice(bigDecimalPrice);
            } catch (NumberFormatException e) {
                return ValidationStatusEnum.ERROR_PRICE_IS_NOT_VALID;
            }
        }
    }

    @Override
    public ValidationStatusEnum validateIsSealed(String isSealed) {
        if ("true".equals(isSealed) || "false".equals(isSealed) || "".equals(isSealed)) {
            return ValidationStatusEnum.SUCCESS_STATUS;
        } else {
            return ValidationStatusEnum.ERROR_IS_SEALED_IS_NOT_BOOLEAN_TYPE;
        }
    }
}
