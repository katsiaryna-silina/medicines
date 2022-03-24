package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.SAXParserException;
import by.epam.silina.medicines.util.Parser;
import by.epam.silina.medicines.util.UserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

import static by.epam.silina.medicines.config.Constant.FILE_IS_NULL;

public class UsersSAXParser implements Parser {
    private static final UsersSAXParser instance = new UsersSAXParser();
    private static final Logger log = LoggerFactory.getLogger(UsersSAXParser.class);
    private final UserHandler userHandler = UserHandler.getInstance();

    private UsersSAXParser() {
    }

    public static UsersSAXParser getInstance() {
        return instance;
    }

    @Override
    public void parse(File file) throws SAXParserException {
        if (file == null) {
            throw new SAXParserException(FILE_IS_NULL);
        }
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            saxParserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            saxParserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(file, userHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new SAXParserException(e);
        }

        userHandler.getClientList().forEach(el -> log.info("{}", el));
        userHandler.getEmployeeList().forEach(el -> log.info("{}", el));
    }
}
