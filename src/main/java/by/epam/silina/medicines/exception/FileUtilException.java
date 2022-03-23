package by.epam.silina.medicines.exception;

import java.io.IOException;

public class FileUtilException extends IOException {
    public FileUtilException(String message) {
        super(message);
    }

    public FileUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUtilException(Throwable cause) {
        super(cause);
    }
}
