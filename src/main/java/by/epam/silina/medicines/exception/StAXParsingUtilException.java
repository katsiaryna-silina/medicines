package by.epam.silina.medicines.exception;

public class StAXParsingUtilException extends ParserException {
    public StAXParsingUtilException(String message) {
        super(message);
    }

    public StAXParsingUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public StAXParsingUtilException(Throwable cause) {
        super(cause);
    }
}
