package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.FileUtilException;
import by.epam.silina.medicines.exception.ParserException;
import by.epam.silina.medicines.util.FileUtil;
import by.epam.silina.medicines.util.Parser;
import by.epam.silina.medicines.util.ParsingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static by.epam.silina.medicines.config.Constant.*;

public class SAXParsingUtil implements ParsingUtil {
    private static final Logger log = LoggerFactory.getLogger(SAXParsingUtil.class);
    private static final FileUtil fileUtil = FileUtilImpl.getInstance();
    private static final Parser saxUsersParser = UsersSAXParser.getInstance();
    private static final Parser medicinesSAXParser = MedicinesSAXParser.getInstance();
    private static final ParsingUtil instance = new SAXParsingUtil();

    private SAXParsingUtil() {
    }

    public static ParsingUtil getInstance() {
        return instance;
    }

    public void parse(String fileName) {
        File file = new File(fileName);
        try {
            if (fileUtil.isFileExists(file) && fileUtil.isFileNotEmpty(file)) {
                switch (fileName) {
                    case MEDICINES_XML_PATH:
                        parseMedicinesXML(file);
                        break;
                    case USERS_XML_PATH:
                        parseUsersXML(file);
                        break;
                    default:
                        log.error(CANNOT_PARSE_FILE_BY_THIS_PROGRAM, fileName);
                        break;
                }
            }
        } catch (FileUtilException e) {
            log.error(CANNOT_PARSE_FILE, file.getName(), e);
        }
    }

    private void parseUsersXML(File file) {
        try {
            saxUsersParser.parse(file);
        } catch (ParserException e) {
            log.error(CANNOT_PARSE_FILE, file.getName(), e);
        }
    }

    private void parseMedicinesXML(File file) {
        try {
            medicinesSAXParser.parse(file);
        } catch (ParserException e) {
            log.error(CANNOT_PARSE_FILE, file.getName(), e);
        }
    }
}
