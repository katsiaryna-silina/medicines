package by.epam.silina.medicines.exception;

public class SAXParserException extends ParserException {
    public SAXParserException(String message) {
        super(message);
    }

    public SAXParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public SAXParserException(Throwable cause) {
        super(cause);
    }
}
