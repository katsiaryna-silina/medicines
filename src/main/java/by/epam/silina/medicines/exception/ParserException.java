package by.epam.silina.medicines.exception;

import java.io.IOException;

public abstract class ParserException extends IOException {
    ParserException(String message) {
        super(message);
    }

    ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    ParserException(Throwable cause) {
        super(cause);
    }
}
