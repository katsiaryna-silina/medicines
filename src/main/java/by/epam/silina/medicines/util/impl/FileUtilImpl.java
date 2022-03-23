package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.FileUtilException;
import by.epam.silina.medicines.util.FileUtil;

import java.io.File;

import static by.epam.silina.medicines.config.Constant.FILE_IS_NULL;

public class FileUtilImpl implements FileUtil {
    private static final FileUtil instance = new FileUtilImpl();

    private FileUtilImpl() {
    }

    public static FileUtil getInstance() {
        return instance;
    }

    @Override
    public boolean isFileEmpty(File file) throws FileUtilException {
        if (file == null) {
            throw new FileUtilException(FILE_IS_NULL);
        } else {
            return file.length() == 0;
        }
    }
}
