package by.epam.silina.medicines.exception;

public class DOMParserException extends ParserException {
    public DOMParserException(String message) {
        super(message);
    }

    public DOMParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DOMParserException(Throwable cause) {
        super(cause);
    }
}
