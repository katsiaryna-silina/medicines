package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.JAXBParserException;
import by.epam.silina.medicines.model.medicines.Medicines;
import by.epam.silina.medicines.util.Parser;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MedicinesJAXBParser implements Parser {
    private static final Logger log = LoggerFactory.getLogger(MedicinesJAXBParser.class);
    private static final MedicinesJAXBParser instance = new MedicinesJAXBParser();

    private MedicinesJAXBParser() {
    }

    public static MedicinesJAXBParser getInstance() {
        return instance;
    }


    @Override
    public void parse(File file) throws JAXBParserException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Medicines.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Medicines medicines = (Medicines) unmarshaller.unmarshal(file);
            medicines.getMedicineList().forEach(el -> log.info("{}", el));
        } catch (JAXBException e) {
            throw new JAXBParserException(e);
        }
    }
}
