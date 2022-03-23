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

import static by.epam.silina.medicines.config.Constant.USERS_XML_PATH;

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
        //todo newInstance? XXE attack?!
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(USERS_XML_PATH, userHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new SAXParserException(e);
        }

        userHandler.getClientList().forEach(el -> log.info("{}", el));
        userHandler.getEmployeeList().forEach(el -> log.info("{}", el));
    }
}
