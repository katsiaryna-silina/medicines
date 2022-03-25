package by.epam.silina.medicines.util;

import by.epam.silina.medicines.exception.JAXBMedicinesException;

import java.io.File;

public interface MedicinesJAXB {
    void convert(File file) throws JAXBMedicinesException;
}
