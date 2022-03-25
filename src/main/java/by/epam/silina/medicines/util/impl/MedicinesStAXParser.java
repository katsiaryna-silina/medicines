package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.ParserException;
import by.epam.silina.medicines.exception.StAXParsingUtilException;
import by.epam.silina.medicines.model.medicines.*;
import by.epam.silina.medicines.util.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.epam.silina.medicines.config.Constant.*;

public class MedicinesStAXParser implements Parser {
    private static final Logger log = LoggerFactory.getLogger(MedicinesStAXParser.class);
    private static final MedicinesStAXParser instance = new MedicinesStAXParser();
    private List<Medicine> medicineList;
    private Medicine medicine;
    private List<Version> versionList;
    private Version version;
    private List<MedicinePackage> medicinePackages;
    private MedicinePackage medicinePackage;
    private Certificate certificate;

    private MedicinesStAXParser() {
    }

    public static MedicinesStAXParser getInstance() {
        return instance;
    }

    public List<Medicine> getMedicines() {
        return medicineList;
    }

    @Override
    public void parse(File file) throws ParserException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(MEDICINES_XML_PATH));
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                processStartElementOfEvent(reader, nextEvent);
                processEndElementOfEvent(nextEvent);
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new StAXParsingUtilException(e);
        }
        medicineList.forEach(el -> log.info("{}", el));
    }

    private void processStartElementOfEvent(XMLEventReader reader, XMLEvent nextEvent) throws XMLStreamException {
        if (nextEvent.isStartElement()) {
            StartElement startElement = nextEvent.asStartElement();
            switch (startElement.getName().getLocalPart()) {
                case MEDICINES:
                    medicineList = new ArrayList<>();
                    break;
                case MEDICINE:
                    medicine = Medicine.builder().build();
                    Attribute id = startElement.getAttributeByName(new QName(ID));
                    if (id != null) {
                        medicine.setId(UUID.fromString(id.getValue()));
                    }
                    break;
                case NAME:
                    nextEvent = reader.nextEvent();
                    medicine.setName(nextEvent.asCharacters().getData());
                    break;
                case COMPANY:
                    nextEvent = reader.nextEvent();
                    medicine.setCompany(nextEvent.asCharacters().getData());
                    break;
                case GROUP:
                    nextEvent = reader.nextEvent();
                    medicine.setGroup(nextEvent.asCharacters().getData());
                    break;
                case VERSIONS:
                    versionList = new ArrayList<>();
                    break;
                case VERSION:
                    version = Version.builder().build();
                    break;
                case MEDICINE_TYPE:
                    nextEvent = reader.nextEvent();
                    String medicineTypeName = nextEvent.asCharacters().getData();
                    version.setMedicineTypeEnum(MedicineTypeEnum.getMedicineTypeEnumByTypeName(medicineTypeName));
                    break;
                case PACKAGES:
                    medicinePackages = new ArrayList<>();
                    break;
                case PACKAGE:
                    medicinePackage = MedicinePackage.builder().build();
                    Attribute isSealed = startElement.getAttributeByName(new QName(IS_SEALED));
                    if (isSealed != null) {
                        medicinePackage.setSealed(Boolean.parseBoolean(isSealed.getValue()));
                    } else {
                        medicinePackage.setSealed(false);
                    }
                    break;
                case PACKAGE_TYPE:
                    nextEvent = reader.nextEvent();
                    String packageTypeName = nextEvent.asCharacters().getData();
                    medicinePackage.setPackageTypeEnum(PackageTypeEnum.getPackageTypeEnumByTypeName(packageTypeName));
                    break;
                case NUMBER:
                    nextEvent = reader.nextEvent();
                    medicinePackage.setNumber(Integer.valueOf(nextEvent.asCharacters().getData()));
                    break;
                case PRICE:
                    nextEvent = reader.nextEvent();
                    BigDecimal priceBigDecimal = BigDecimal.valueOf(Double.parseDouble(nextEvent.asCharacters().getData()));
                    medicinePackage.setPrice(priceBigDecimal.setScale(NUMBER_OF_DECIMAL_PLACE, RoundingMode.FLOOR));
                    break;
                case DOSAGE:
                    nextEvent = reader.nextEvent();
                    version.setDosage(nextEvent.asCharacters().getData());
                    break;
                case CERTIFICATE:
                    certificate = Certificate.builder().build();
                    break;
                case CERTIFICATE_NAME:
                    nextEvent = reader.nextEvent();
                    certificate.setName(nextEvent.asCharacters().getData());
                    break;
                case WHO_ISSUED:
                    nextEvent = reader.nextEvent();
                    certificate.setWhoIssued(nextEvent.asCharacters().getData());
                    break;
                case REGISTRATION_NUMBER:
                    nextEvent = reader.nextEvent();
                    certificate.setRegistrationNumber(nextEvent.asCharacters().getData());
                    break;
                case REGISTRATION_DATE_FROM:
                    nextEvent = reader.nextEvent();
                    certificate.setRegistrationDateFrom(LocalDate.parse(nextEvent.asCharacters().getData()));
                    break;
                case REGISTRATION_DATE_TO:
                    nextEvent = reader.nextEvent();
                    certificate.setRegistrationDateTo(LocalDate.parse(nextEvent.asCharacters().getData()));
                    break;
                default:
                    break;
            }
        }
    }

    private void processEndElementOfEvent(XMLEvent nextEvent) {
        if (nextEvent.isEndElement()) {
            EndElement endElement = nextEvent.asEndElement();
            switch (endElement.getName().getLocalPart()) {
                case MEDICINE:
                    medicineList.add(medicine);
                    break;
                case VERSIONS:
                    medicine.setVersions(versionList);
                    break;
                case VERSION:
                    versionList.add(version);
                    break;
                case PACKAGES:
                    version.setMedicinePackages(medicinePackages);
                    break;
                case PACKAGE:
                    medicinePackages.add(medicinePackage);
                    break;
                case CERTIFICATE:
                    version.setCertificate(certificate);
                    break;
                default:
                    break;
            }
        }
    }
}
