package by.epam.silina.medicines.util;

import by.epam.silina.medicines.exception.FileUtilException;

import java.io.File;

public interface FileUtil {
    boolean isFileEmpty(File file) throws FileUtilException;
}
