package by.epam.silina.medicines.util;

import by.epam.silina.medicines.model.ValidationStatusEnum;
import by.epam.silina.medicines.model.medicines.*;
import by.epam.silina.medicines.util.impl.MedicineValidationUtilImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static by.epam.silina.medicines.config.Constant.*;

public class MedicineHandler extends DefaultHandler {
    private static final Logger log = LoggerFactory.getLogger(MedicineHandler.class);
    private static final MedicineHandler instance = new MedicineHandler();
    private final MedicineValidationUtil medicineValidationUtil = MedicineValidationUtilImpl.getInstance();
    private Medicines medicines;
    private String medicineId;
    private Medicine medicine;
    private Version version;
    private Certificate certificate;
    private MedicinePackage medicinePackage;
    private List<ValidationStatusEnum> validationStatuses;
    private List<Version> versionList;
    private List<Medicine> medicineList;
    private List<MedicinePackage> medicinePackageList;

    private StringBuilder elementValueBuilder;

    private MedicineHandler() {
    }

    public static MedicineHandler getInstance() {
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
    public void startDocument() {
        medicines = new Medicines();
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case MDC_MEDICINES:
                medicineList = new ArrayList<>();
                break;
            case MDC_MEDICINE:
                validationStatuses = new ArrayList<>();
                medicine = Medicine.builder().build();
                int attrLength = attr.getLength();
                for (int i = 0; i < attrLength; i++) {
                    if (ID.equals(attr.getQName(i))) {
                        medicineId = attr.getValue(i);
                    }
                }
                break;
            case MDC_VERSIONS:
                versionList = new ArrayList<>();
                break;
            case MDC_VERSION:
                validationStatuses = new ArrayList<>();
                version = Version.builder().build();
                break;
            case MDC_PACKAGES:
                medicinePackageList = new ArrayList<>();
                break;
            case MDC_PACKAGE:
                validationStatuses = new ArrayList<>();
                medicinePackage = MedicinePackage.builder().build();
                break;
            case MDC_CERTIFICATE:
                validationStatuses = new ArrayList<>();
                certificate = Certificate.builder().build();
                break;
            case MDC_NAME:
            case MDC_COMPANY:
            case MDC_GROUP:
            case MDC_MEDICINE_TYPE:
            case MDC_PACKAGE_TYPE:
            case MDC_NUMBER:
            case MDC_PRICE:
            case MDC_DOSAGE:
            case MDC_CERTIFICATE_NAME:
            case MDC_WHO_ISSUED:
            case MDC_REGISTRATION_NUMBER:
            case MDC_REGISTRATION_DATE_FROM:
            case MDC_REGISTRATION_DATE_TO:
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

        switch (qName) {
            case MDC_MEDICINES:
                medicines.setMedicineList(medicineList);
                break;
            case MDC_MEDICINE:
                processMedicineEndElement();
                break;
            case MDC_VERSION:
                processVersionEndElement();
                break;
            case MDC_PACKAGE:
                List<ValidationStatusEnum> packageValidationErrors = validationStatuses.stream()
                        .filter(el -> el.getStatusNumber() != 0)
                        .collect(Collectors.toList());
                if (packageValidationErrors.isEmpty()) {
                    medicinePackageList.add(medicinePackage);
                } else {
                    log.error(CANNOT_CREATE_MEDICINE_PACKAGE);
                    packageValidationErrors.forEach(el -> log.error(el.getStatusDescription()));
                }
                break;
            case MDC_CERTIFICATE:
                List<ValidationStatusEnum> certificateValidationErrors = validationStatuses.stream()
                        .filter(el -> el.getStatusNumber() != 0)
                        .collect(Collectors.toList());
                if (certificateValidationErrors.isEmpty()) {
                    version.setCertificate(certificate);
                    break;
                }
                log.error(CANNOT_CREATE_CERTIFICATE);
                certificateValidationErrors.forEach(el -> log.error(el.getStatusDescription()));
                break;
            case MDC_NAME:
                validationStatuses.add(medicineValidationUtil.validateMedicineName(elementValue));
                medicine.setName(elementValue);
                break;
            case MDC_COMPANY:
                validationStatuses.add(medicineValidationUtil.validateCompany(elementValue));
                medicine.setCompany(elementValue);
                break;
            case MDC_GROUP:
                validationStatuses.add(medicineValidationUtil.validateGroup(elementValue));
                medicine.setGroup(elementValue);
                break;
            case MDC_VERSIONS:
                validationStatuses.add(medicineValidationUtil.validateVersions(versionList));
                medicine.setVersions(versionList);
                break;
            case MDC_MEDICINE_TYPE:
                MedicineTypeEnum medicineTypeEnum = MedicineTypeEnum.getMedicineTypeEnumByTypeName(elementValue);
                validationStatuses.add(medicineValidationUtil.validateMedicineTypeEnum(medicineTypeEnum));
                version.setMedicineTypeEnum(medicineTypeEnum);
                break;
            case MDC_PACKAGES:
                version.setMedicinePackages(medicinePackageList);
                break;
            case MDC_PACKAGE_TYPE:
                PackageTypeEnum packageTypeEnum = PackageTypeEnum.getPackageTypeEnumByTypeName(elementValue);
                validationStatuses.add(medicineValidationUtil.validatePackageTypeEnum(packageTypeEnum));
                medicinePackage.setPackageTypeEnum(packageTypeEnum);
                break;
            case MDC_NUMBER:
                validationStatuses.add(medicineValidationUtil.validateMedicineNumberInPackage(elementValue));
                medicinePackage.setNumber(Integer.valueOf(elementValue));
                break;
            case MDC_PRICE:
                ValidationStatusEnum validationStatusPrice = medicineValidationUtil.validatePrice(elementValue);
                validationStatuses.add(validationStatusPrice);
                if (validationStatusPrice.getStatusNumber() == 0) {
                    medicinePackage.setPrice(BigDecimal.valueOf(Double.parseDouble(elementValue)));
                }
                break;
            case MDC_DOSAGE:
                validationStatuses.add(medicineValidationUtil.validateDosage(elementValue));
                version.setDosage(elementValue);
                break;
            case MDC_CERTIFICATE_NAME:
                validationStatuses.add(medicineValidationUtil.validateCertificateName(elementValue));
                certificate.setName(elementValue);
                break;
            case MDC_WHO_ISSUED:
                validationStatuses.add(medicineValidationUtil.validateWhoIssued(elementValue));
                certificate.setWhoIssued(elementValue);
                break;
            case MDC_REGISTRATION_NUMBER:
                validationStatuses.add(medicineValidationUtil.validateRegistrationNumber(elementValue));
                certificate.setRegistrationNumber(elementValue);
                break;
            case MDC_REGISTRATION_DATE_FROM:
                ValidationStatusEnum validationStatusRegistrationDateFrom = medicineValidationUtil.validateRegistrationDateFrom(elementValue);
                validationStatuses.add(validationStatusRegistrationDateFrom);
                if (validationStatusRegistrationDateFrom.getStatusNumber() == 0) {
                    certificate.setRegistrationDateFrom(LocalDate.parse(elementValue));
                }
                break;
            case MDC_REGISTRATION_DATE_TO:
                ValidationStatusEnum validationStatusRegistrationDateTo = medicineValidationUtil.validateRegistrationDateTo(elementValue);
                validationStatuses.add(validationStatusRegistrationDateTo);
                if (validationStatusRegistrationDateTo.getStatusNumber() == 0) {
                    certificate.setRegistrationDateTo(LocalDate.parse(elementValue));
                }
                break;
            default:
                log.warn(UNKNOWN_ELEMENT, qName);
                break;
        }
    }

    private void processMedicineEndElement() {
        ValidationStatusEnum validationStatusEnum = medicineValidationUtil.validateId(medicineId);
        validationStatuses.add(validationStatusEnum);
        List<ValidationStatusEnum> validationErrors = validationStatuses.stream()
                .filter(el -> el.getStatusNumber() != 0)
                .collect(Collectors.toList());
        if (validationErrors.isEmpty()) {
            medicine.setId(UUID.fromString(medicineId));
            medicineList.add(medicine);
        } else {
            log.error(CANNOT_CREATE_MEDICINE);
            validationErrors.forEach(el -> log.error(el.getStatusDescription()));
        }
    }

    private void processVersionEndElement() {
        List<ValidationStatusEnum> versionValidationErrors = validationStatuses.stream()
                .filter(el -> el.getStatusNumber() != 0)
                .collect(Collectors.toList());
        if (versionValidationErrors.isEmpty()) {
            versionList.add(version);
        } else {
            log.error(CANNOT_CREATE_VERSION);
            versionValidationErrors.forEach(el -> log.error(el.getStatusDescription()));
        }
    }

    public Medicines getMedicines() {
        return medicines;
    }
}
