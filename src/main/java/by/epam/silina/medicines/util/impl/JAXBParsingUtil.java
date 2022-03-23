package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.FileUtilException;
import by.epam.silina.medicines.exception.ParserException;
import by.epam.silina.medicines.util.FileUtil;
import by.epam.silina.medicines.util.Parser;
import by.epam.silina.medicines.util.ParsingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static by.epam.silina.medicines.config.Constant.MEDICINE_XML_PATH;
import static by.epam.silina.medicines.config.Constant.USERS_XML_PATH;

public class JAXBParsingUtil implements ParsingUtil {
    public static final Parser medicinesJAXBParser = MedicinesJAXBParser.getInstance();
    private static final Logger log = LoggerFactory.getLogger(JAXBParsingUtil.class);
    private static final FileUtil fileUtil = FileUtilImpl.getInstance();
    private static final ParsingUtil instance = new JAXBParsingUtil();

    private JAXBParsingUtil() {
    }

    public static ParsingUtil getInstance() {
        return instance;
    }

    @Override
    public void parse(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileUtil.isFileEmpty(file)) {
                    log.error("Cannot parse file={}. It is empty.", fileName);
                } else {
                    switch (fileName) {
                        case MEDICINE_XML_PATH:
                            try {
                                medicinesJAXBParser.parse(file);
                            } catch (ParserException e) {
                                log.error("", e);
                            }
                            break;
                        case USERS_XML_PATH:
                            //todo
                            break;
                        default:
                            log.error("File={} cannot be parsed by this program.", fileName);
                            break;
                    }
                }
            } catch (FileUtilException e) {
                log.error("", e);
            }
        } else {
            log.error("Cannot parse file={}. It doesn't exist.", fileName);
        }
    }
}
