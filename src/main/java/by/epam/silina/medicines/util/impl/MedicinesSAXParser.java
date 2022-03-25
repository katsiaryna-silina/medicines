package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.ParserException;
import by.epam.silina.medicines.exception.SAXParserException;
import by.epam.silina.medicines.util.MedicineHandler;
import by.epam.silina.medicines.util.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

import static by.epam.silina.medicines.config.Constant.FILE_IS_NULL;

public class MedicinesSAXParser implements Parser {
    private static final MedicinesSAXParser instance = new MedicinesSAXParser();
    private static final Logger log = LoggerFactory.getLogger(MedicinesSAXParser.class);
    private final MedicineHandler medicineHandler = MedicineHandler.getInstance();

    private MedicinesSAXParser() {
    }

    public static MedicinesSAXParser getInstance() {
        return instance;
    }

    @Override
    public void parse(File file) throws ParserException {
        if (file == null) {
            throw new SAXParserException(FILE_IS_NULL);
        }
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            saxParserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            saxParserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(file, medicineHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new SAXParserException(e);
        }
        medicineHandler.getMedicines().getMedicineList().forEach(el -> log.info("{}", el));
    }
}
