package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.DOMParserException;
import by.epam.silina.medicines.model.medicines.*;
import by.epam.silina.medicines.util.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
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

import static by.epam.silina.medicines.config.Constant.*;

public class MedicinesDOMParser implements Parser {
    private static final Logger log = LoggerFactory.getLogger(MedicinesDOMParser.class);
    private static final MedicinesDOMParser instance = new MedicinesDOMParser();

    private MedicinesDOMParser() {
    }

    public static MedicinesDOMParser getInstance() {
        return instance;
    }

    public void parse(File file) throws DOMParserException {
        try {
            Document document = createDocument(file);
            normalizeXMLStructure(document);

            List<Medicine> medicines = new ArrayList<>();
            NodeList allMedicineNodes = document.getElementsByTagName(MDC_MEDICINE); //6 "medicine"
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
        for (int i = 0; i < versionNodes.getLength(); i++) {//1 or 2 or 3 - number of "version"
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
        return Medicine.builder()
                .id(UUID.fromString(medicineElement.getAttribute(ID)))
                .name(String.valueOf(medicineElement.getElementsByTagName(MDC_NAME).item(0).getTextContent()))
                .company(medicineElement.getElementsByTagName(MDC_COMPANY).item(0).getTextContent())
                .group(medicineElement.getElementsByTagName(MDC_GROUP).item(0).getTextContent())
                .versions(getVersions(medicineElement))
                .build();
    }

    private List<MedicinePackage> getMedicinePackages(Element versionElement) {
        List<MedicinePackage> medicinePackages = new ArrayList<>();
        NodeList packageNodes = versionElement.getElementsByTagName(MDC_PACKAGE);
        for (int i = 0; i < packageNodes.getLength(); i++) {//1 or 2  - number of "package"
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
        String packageTypeName = packageElement.getElementsByTagName(MDC_PACKAGE_TYPE).item(0).getTextContent();
        return MedicinePackage.builder()
                .packageTypeEnum(PackageTypeEnum.getPackageTypeEnumByTypeName(packageTypeName))
                .number(Integer.valueOf(getTextContentBy(packageElement, MDC_NUMBER)))
                .price(BigDecimal.valueOf(Double.parseDouble(getTextContentBy(packageElement, MDC_PRICE))))
                .isSealed(Boolean.parseBoolean(packageElement.getAttribute(IS_SEALED)))
                .build();
    }

    private Version getVersionFromElement(Element versionElement, List<MedicinePackage> medicinePackages) {
        String medicineTypeName = getTextContentBy(versionElement, MDC_MEDICINE_TYPE);
        return Version.builder()
                .medicineTypeEnum(MedicineTypeEnum.getMedicineTypeEnumByTypeName(medicineTypeName))
                .dosage(getTextContentBy(versionElement, MDC_DOSAGE))
                .certificate(getCertificateObjectFromElement(versionElement))
                .medicinePackages(medicinePackages)
                .build();
    }

    private Certificate getCertificateObjectFromElement(Element versionElement) {
        return Certificate.builder()
                .name(getTextContentBy(versionElement, MDC_CERTIFICATE_NAME))
                .whoIssued(getTextContentBy(versionElement, MDC_WHO_ISSUED))
                .registrationNumber(getTextContentBy(versionElement, MDC_REGISTRATION_NUMBER))
                .registrationDateFrom(LocalDate.parse(getTextContentBy(versionElement, MDC_REGISTRATION_DATE_FROM)))
                .registrationDateTo(LocalDate.parse(getTextContentBy(versionElement, MDC_REGISTRATION_DATE_TO)))
                .build();
    }

    private String getTextContentBy(Element element, String elementTagName) {
        return element.getElementsByTagName(elementTagName).item(0).getTextContent();
    }

    private Document createDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        // process XML securely, avoid attacks like XML External Entities (XXE)
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        return documentBuilder.parse(file);
    }

    private void normalizeXMLStructure(Document document) {
        document.getDocumentElement().normalize();
    }
}
