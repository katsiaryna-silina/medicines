package by.epam.silina.medicines.util;


import by.epam.silina.medicines.exception.ParserException;

import java.io.File;

public interface Parser {
    void parse(File file) throws ParserException;
}
