package by.epam.silina.medicines.util;

import by.epam.silina.medicines.model.medicines.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.epam.silina.medicines.config.Constant.*;

public class MedicineHandler extends DefaultHandler {
    private static final MedicineHandler instance = new MedicineHandler();
    private Medicines medicines;
    private Version version;
    private Certificate certificate;
    private List<Version> versionList;
    private List<Medicine> medicineList;
    private List<MedicinePackage> medicinePackageList;

    private StringBuilder elementValue;

    private MedicineHandler() {
    }

    public static MedicineHandler getInstance() {
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
        medicines = new Medicines();
    }

    @Override
    public void endDocument() {
        medicines.setMedicineList(medicineList);
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case MDC_MEDICINES:
                medicineList = new ArrayList<>();
                break;
            case MDC_MEDICINE:
                Medicine medicine = Medicine.builder().build();
                int attrLength = attr.getLength();
                for (int i = 0; i < attrLength; i++) {
                    if (ID.equals(attr.getQName(i))) {
                        String stringId = attr.getValue(i);
                        medicine.setId(UUID.fromString(stringId));
                    }
                }
                medicineList.add(medicine);
                break;
            case MDC_VERSIONS:
                versionList = new ArrayList<>();
                break;
            case MDC_VERSION:
                version = Version.builder().build();
                versionList.add(version);
                break;
            case MDC_PACKAGES:
                medicinePackageList = new ArrayList<>();
                break;
            case MDC_PACKAGE:
                MedicinePackage medicinePackage = MedicinePackage.builder().build();
                medicinePackageList.add(medicinePackage);
                break;
            case MDC_CERTIFICATE:
                certificate = Certificate.builder().build();
                version.setCertificate(certificate);
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
        switch (qName) {
            case MDC_NAME:
                latestMedicine().setName(elementValue.toString());
                break;
            case MDC_COMPANY:
                latestMedicine().setCompany(elementValue.toString());
                break;
            case MDC_GROUP:
                latestMedicine().setGroup(elementValue.toString());
                break;
            case MDC_VERSIONS:
                latestMedicine().setVersions(versionList);
                break;
            case MDC_MEDICINE_TYPE:
                MedicineTypeEnum medicineTypeEnum = MedicineTypeEnum.getMedicineTypeEnumByTypeName(elementValue.toString());
                latestVersion().setMedicineTypeEnum(medicineTypeEnum);
                break;
            case MDC_PACKAGES:
                latestVersion().setMedicinePackages(medicinePackageList);
                break;
            case MDC_PACKAGE_TYPE:
                PackageTypeEnum packageTypeEnum = PackageTypeEnum.getPackageTypeEnumByTypeName(elementValue.toString());
                latestMedicinePackage().setPackageTypeEnum(packageTypeEnum);
                break;
            case MDC_NUMBER:
                latestMedicinePackage().setNumber(Integer.valueOf(elementValue.toString()));
                break;
            case MDC_PRICE:
                latestMedicinePackage().setPrice(BigDecimal.valueOf(Double.parseDouble(elementValue.toString())));
                break;
            case MDC_DOSAGE:
                latestVersion().setDosage(elementValue.toString());
                break;
            case MDC_CERTIFICATE_NAME:
                certificate.setName(elementValue.toString());
                break;
            case MDC_WHO_ISSUED:
                certificate.setWhoIssued(elementValue.toString());
                break;
            case MDC_REGISTRATION_NUMBER:
                certificate.setRegistrationNumber(elementValue.toString());
                break;
            case MDC_REGISTRATION_DATE_FROM:
                certificate.setRegistrationDateFrom(LocalDate.parse(elementValue.toString()));
                break;
            case MDC_REGISTRATION_DATE_TO:
                certificate.setRegistrationDateTo(LocalDate.parse(elementValue.toString()));
                break;
            default:
                //todo
                break;
        }
    }

    private Medicine latestMedicine() {
        int latestMedicineIndex = medicineList.size() - 1;
        return medicineList.get(latestMedicineIndex);
    }

    private Version latestVersion() {
        int latestVersionIndex = versionList.size() - 1;
        return versionList.get(latestVersionIndex);
    }

    private MedicinePackage latestMedicinePackage() {
        int latestMedicinePackageIndex = medicinePackageList.size() - 1;
        return medicinePackageList.get(latestMedicinePackageIndex);
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }
}
