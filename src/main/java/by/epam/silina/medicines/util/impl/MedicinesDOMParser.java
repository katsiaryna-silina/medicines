package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.DOMParserException;
import by.epam.silina.medicines.model.ValidationStatusEnum;
import by.epam.silina.medicines.model.medicines.*;
import by.epam.silina.medicines.util.MedicineValidationUtil;
import by.epam.silina.medicines.util.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static by.epam.silina.medicines.config.Constant.*;

public class MedicinesDOMParser implements Parser {
    public static final MedicineValidationUtil medicineValidationUtil = MedicineValidationUtilImpl.getInstance();
    private static final Logger log = LoggerFactory.getLogger(MedicinesDOMParser.class);
    private static final MedicinesDOMParser instance = new MedicinesDOMParser();
    private List<ValidationStatusEnum> validationStatuses;

    private MedicinesDOMParser() {
    }

    public static MedicinesDOMParser getInstance() {
        return instance;
    }

    public void parse(File file) throws DOMParserException {
        if (file == null) {
            throw new DOMParserException(FILE_IS_NULL);
        }
        try {
            Document document = createDocument(file);
            normalizeXMLStructure(document);

            List<Medicine> medicines = new ArrayList<>();
            NodeList allMedicineNodes = document.getElementsByTagName(MDC_MEDICINE);
            for (int i = 0; i < allMedicineNodes.getLength(); i++) {
                Node medicineNode = allMedicineNodes.item(i);
                if (medicineNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element medicineElement = (Element) medicineNode;
                    Medicine medicine = createMedicine(medicineElement);
                    medicines.add(medicine);
                }
            }
            medicines.forEach(el -> log.info("{}", el));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new DOMParserException(e);
        }
    }

    private List<Version> getVersions(Element medicineElement) {
        List<Version> versions = new ArrayList<>();
        NodeList versionNodes = medicineElement.getElementsByTagName(MDC_VERSION);
        for (int i = 0; i < versionNodes.getLength(); i++) {
            Node versionNode = versionNodes.item(i);
            if (versionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element versionElement = (Element) versionNode;
                List<MedicinePackage> medicinePackages = getMedicinePackages(versionElement);
                Version version = getVersionFromElement(versionElement, medicinePackages);
                versions.add(version);
            }
        }
        return versions;
    }

    private Medicine createMedicine(Element medicineElement) {
        validationStatuses = new ArrayList<>();

        String id = medicineElement.getAttribute(ID);
        validationStatuses.add(medicineValidationUtil.validateId(id));
        String medicineName = medicineElement.getElementsByTagName(MDC_NAME).item(0).getTextContent();
        validationStatuses.add(medicineValidationUtil.validateMedicineName(medicineName));
        String company = medicineElement.getElementsByTagName(MDC_COMPANY).item(0).getTextContent();
        validationStatuses.add(medicineValidationUtil.validateCompany(company));
        String group = medicineElement.getElementsByTagName(MDC_GROUP).item(0).getTextContent();
        validationStatuses.add(medicineValidationUtil.validateGroup(group));
        List<Version> versions = getVersions(medicineElement);
        validationStatuses.add(medicineValidationUtil.validateVersions(versions));

        List<ValidationStatusEnum> validationErrors = validationStatuses.stream()
                .filter(el -> el.getStatusNumber() != 0)
                .collect(Collectors.toList());
        if (validationErrors.isEmpty()) {
            return Medicine.builder()
                    .id(UUID.fromString(id))
                    .name(medicineName)
                    .company(company)
                    .group(group)
                    .versions(versions)
                    .build();
        } else {
            log.error(CANNOT_CREATE_MEDICINE);
            validationErrors.forEach(el -> log.error(el.getStatusDescription()));
            return null;
        }
    }

    private List<MedicinePackage> getMedicinePackages(Element versionElement) {
        List<MedicinePackage> medicinePackages = new ArrayList<>();
        NodeList packageNodes = versionElement.getElementsByTagName(MDC_PACKAGE);
        for (int i = 0; i < packageNodes.getLength(); i++) {
            Node packageNode = packageNodes.item(i);
            if (packageNode.getNodeType() == Node.ELEMENT_NODE) {
                Element packageElement = (Element) packageNode;
                MedicinePackage medicinePackage = getMedicinePackage(packageElement);
                medicinePackages.add(medicinePackage);
            }
        }
        return medicinePackages;
    }

    private MedicinePackage getMedicinePackage(Element packageElement) {
        validationStatuses = new ArrayList<>();

        String packageTypeName = packageElement.getElementsByTagName(MDC_PACKAGE_TYPE).item(0).getTextContent();
        PackageTypeEnum packageTypeEnum = PackageTypeEnum.getPackageTypeEnumByTypeName(packageTypeName);
        validationStatuses.add(medicineValidationUtil.validatePackageTypeEnum(packageTypeEnum));
        String medicineNumber = getTextContentBy(packageElement, MDC_NUMBER);
        validationStatuses.add(medicineValidationUtil.validateMedicineNumberInPackage(medicineNumber));
        String price = getTextContentBy(packageElement, MDC_PRICE);
        validationStatuses.add(medicineValidationUtil.validatePrice(price));
        String isSealed = packageElement.getAttribute(IS_SEALED);
        validationStatuses.add(medicineValidationUtil.validateIsSealed(isSealed));

        List<ValidationStatusEnum> validationErrors = validationStatuses.stream()
                .filter(el -> el.getStatusNumber() != 0)
                .collect(Collectors.toList());
        if (validationErrors.isEmpty()) {
            return MedicinePackage.builder()
                    .packageTypeEnum(packageTypeEnum)
                    .number(Integer.valueOf(medicineNumber))
                    .price(BigDecimal.valueOf(Double.parseDouble(price)))
                    .isSealed(Boolean.parseBoolean(isSealed))
                    .build();
        } else {
            log.error(CANNOT_CREATE_MEDICINE_PACKAGE);
            validationErrors.forEach(el -> log.error(el.getStatusDescription()));
            return null;
        }
    }

    private Version getVersionFromElement(Element versionElement, List<MedicinePackage> medicinePackages) {
        validationStatuses = new ArrayList<>();

        String medicineTypeName = getTextContentBy(versionElement, MDC_MEDICINE_TYPE);
        MedicineTypeEnum medicineTypeEnum = MedicineTypeEnum.getMedicineTypeEnumByTypeName(medicineTypeName);
        validationStatuses.add(medicineValidationUtil.validateMedicineTypeEnum(medicineTypeEnum));
        String dosage = getTextContentBy(versionElement, MDC_DOSAGE);
        validationStatuses.add(medicineValidationUtil.validateDosage(dosage));
        Certificate certificate = getCertificateObjectFromElement(versionElement);
        validationStatuses.add(medicineValidationUtil.validateCertificate(certificate));
        validationStatuses.add(medicineValidationUtil.validateMedicinePackages(medicinePackages));

        List<ValidationStatusEnum> validationErrors = validationStatuses.stream()
                .filter(el -> el.getStatusNumber() != 0)
                .collect(Collectors.toList());
        if (validationErrors.isEmpty()) {
            return Version.builder()
                    .medicineTypeEnum(medicineTypeEnum)
                    .dosage(dosage)
                    .certificate(certificate)
                    .medicinePackages(medicinePackages)
                    .build();
        } else {
            log.error(CANNOT_CREATE_VERSION);
            validationErrors.forEach(el -> log.error(el.getStatusDescription()));
            return null;
        }
    }

    private Certificate getCertificateObjectFromElement(Element versionElement) {
        validationStatuses = new ArrayList<>();

        String certificateName = getTextContentBy(versionElement, MDC_CERTIFICATE_NAME);
        validationStatuses.add(medicineValidationUtil.validateCertificateName(certificateName));
        String whoIssued = getTextContentBy(versionElement, MDC_WHO_ISSUED);
        validationStatuses.add(medicineValidationUtil.validateWhoIssued(whoIssued));
        String registrationNumber = getTextContentBy(versionElement, MDC_REGISTRATION_NUMBER);
        validationStatuses.add(medicineValidationUtil.validateRegistrationNumber(registrationNumber));
        String registrationDateFrom = getTextContentBy(versionElement, MDC_REGISTRATION_DATE_FROM);
        validationStatuses.add(medicineValidationUtil.validateRegistrationDateFrom(registrationDateFrom));
        String registrationDateTo = getTextContentBy(versionElement, MDC_REGISTRATION_DATE_TO);
        validationStatuses.add(medicineValidationUtil.validateRegistrationDateTo(registrationDateTo));

        List<ValidationStatusEnum> validationErrors = validationStatuses.stream()
                .filter(el -> el.getStatusNumber() != 0)
                .collect(Collectors.toList());
        if (validationErrors.isEmpty()) {
            return Certificate.builder()
                    .name(certificateName)
                    .whoIssued(whoIssued)
                    .registrationNumber(registrationNumber)
                    .registrationDateFrom(LocalDate.parse(registrationDateFrom))
                    .registrationDateTo(LocalDate.parse(registrationDateTo))
                    .build();
        } else {
            log.error(CANNOT_CREATE_CERTIFICATE);
            validationErrors.forEach(el -> log.error(el.getStatusDescription()));
            return null;
        }
    }

    private String getTextContentBy(Element element, String elementTagName) {
        return element.getElementsByTagName(elementTagName).item(0).getTextContent();
    }

    private Document createDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

    private void normalizeXMLStructure(Document document) {
        document.getDocumentElement().normalize();
    }
}
