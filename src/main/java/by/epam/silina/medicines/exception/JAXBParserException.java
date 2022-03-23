package by.epam.silina.medicines.exception;

public class JAXBParserException extends ParserException {
    public JAXBParserException(String message) {
        super(message);
    }

    public JAXBParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public JAXBParserException(Throwable cause) {
        super(cause);
    }
}
