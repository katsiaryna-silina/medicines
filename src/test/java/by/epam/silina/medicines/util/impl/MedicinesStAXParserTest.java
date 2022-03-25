package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.ParserException;
import by.epam.silina.medicines.model.medicines.Medicine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static by.epam.silina.medicines.config.Constant.MEDICINES_XML_PATH;

class MedicinesStAXParserTest {
    private final MedicinesStAXParser medicinesStAXParser = MedicinesStAXParser.getInstance();
    private final List<Medicine> expectedMedicines = new ArrayList<>();

    @BeforeEach
    void setup() {
        MedicineListCreator medicineListCreator = new MedicineListCreator();
        List<Medicine> medicineList = medicineListCreator.createMedicineList();
        expectedMedicines.addAll(medicineList);
    }

    @Test
    void parse() throws ParserException {
        List<Medicine> actualMedicines = medicinesStAXParser.getMedicines();
        Assertions.assertNull(actualMedicines);

        File file = new File(MEDICINES_XML_PATH);
        medicinesStAXParser.parse(file);

        actualMedicines = medicinesStAXParser.getMedicines();
        Assertions.assertNotNull(actualMedicines);
        Assertions.assertEquals(6, actualMedicines.size());

        Assertions.assertEquals(expectedMedicines.get(0), actualMedicines.get(0));
        Assertions.assertEquals(expectedMedicines.get(1), actualMedicines.get(1));
        Assertions.assertEquals(expectedMedicines.get(2), actualMedicines.get(2));
        Assertions.assertEquals(expectedMedicines.get(3), actualMedicines.get(3));
        Assertions.assertEquals(expectedMedicines.get(4), actualMedicines.get(4));
        Assertions.assertEquals(expectedMedicines.get(5), actualMedicines.get(5));

        Assertions.assertEquals(expectedMedicines, actualMedicines);
    }
}