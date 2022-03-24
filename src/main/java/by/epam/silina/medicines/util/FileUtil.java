package by.epam.silina.medicines.util;

import by.epam.silina.medicines.exception.FileUtilException;

import java.io.File;

public interface FileUtil {
    boolean isFileExists(File file) throws FileUtilException;

    boolean isFileNotEmpty(File file) throws FileUtilException;
}
