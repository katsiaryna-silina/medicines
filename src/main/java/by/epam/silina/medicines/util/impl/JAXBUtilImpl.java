package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.FileUtilException;
import by.epam.silina.medicines.exception.ParserException;
import by.epam.silina.medicines.util.FileUtil;
import by.epam.silina.medicines.util.JAXBUtil;
import by.epam.silina.medicines.util.MedicinesJAXB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static by.epam.silina.medicines.config.Constant.*;

public class JAXBUtilImpl implements JAXBUtil {
    public static final MedicinesJAXB medicinesJAXB = MedicinesJAXBImpl.getInstance();
    private static final Logger log = LoggerFactory.getLogger(JAXBUtilImpl.class);
    private static final FileUtil fileUtil = FileUtilImpl.getInstance();
    private static final JAXBUtil instance = new JAXBUtilImpl();

    private JAXBUtilImpl() {
    }

    public static JAXBUtil getInstance() {
        return instance;
    }

    @Override
    public void convert(String fileName) {
        File file = new File(fileName);
        try {
            if (fileUtil.isFileExists(file) && fileUtil.isFileNotEmpty(file)) {
                if (MEDICINES_XML_PATH.equals(fileName)) {
                    parseMedicinesXML(file);
                } else {
                    log.error(CANNOT_PARSE_FILE_BY_THIS_PROGRAM, fileName);
                }
            }
        } catch (FileUtilException e) {
            log.error(CANNOT_PARSE_FILE, file.getName(), e);
        }
    }

    private void parseMedicinesXML(File file) {
        try {
            medicinesJAXB.convert(file);
        } catch (ParserException e) {
            log.error(CANNOT_PARSE_FILE, file.getName(), e);
        }
    }
}
