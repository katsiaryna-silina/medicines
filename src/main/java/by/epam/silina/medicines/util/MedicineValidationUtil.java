package by.epam.silina.medicines.util;

import by.epam.silina.medicines.model.ValidationStatusEnum;
import by.epam.silina.medicines.model.medicines.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicineValidationUtil {
    ValidationStatusEnum validateId(UUID id);

    ValidationStatusEnum validateId(String id);

    ValidationStatusEnum validateMedicineName(String medicineName);

    ValidationStatusEnum validateCompany(String company);

    ValidationStatusEnum validateGroup(String group);

    ValidationStatusEnum validateVersions(List<Version> versions);

    ValidationStatusEnum validateMedicineTypeEnum(MedicineTypeEnum medicineTypeEnum);

    ValidationStatusEnum validateDosage(String dosage);

    ValidationStatusEnum validateCertificate(Certificate certificate);

    ValidationStatusEnum validateMedicinePackages(List<MedicinePackage> medicinePackages);

    ValidationStatusEnum validateCertificateName(String certificateName);

    ValidationStatusEnum validateWhoIssued(String whoIssued);

    ValidationStatusEnum validateRegistrationNumber(String registrationNumber);

    ValidationStatusEnum validateRegistrationDateFrom(LocalDate registrationDateFrom);

    ValidationStatusEnum validateRegistrationDateFrom(String registrationDateFrom);

    ValidationStatusEnum validateRegistrationDateTo(LocalDate registrationDateTo);

    ValidationStatusEnum validateRegistrationDateTo(String registrationDateTo);

    ValidationStatusEnum validatePackageTypeEnum(PackageTypeEnum packageTypeEnum);

    ValidationStatusEnum validateMedicineNumberInPackage(Integer number);

    ValidationStatusEnum validateMedicineNumberInPackage(String number);

    ValidationStatusEnum validatePrice(BigDecimal price);

    ValidationStatusEnum validatePrice(String price);

    ValidationStatusEnum validateIsSealed(String isSealed);

}
