package by.epam.silina.medicines.exception;

public class JAXBMedicinesException extends ParserException {
    public JAXBMedicinesException(String message) {
        super(message);
    }

    public JAXBMedicinesException(String message, Throwable cause) {
        super(message, cause);
    }

    public JAXBMedicinesException(Throwable cause) {
        super(cause);
    }
}
