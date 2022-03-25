package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.JAXBMedicinesException;
import by.epam.silina.medicines.model.medicines.Medicine;
import by.epam.silina.medicines.model.medicines.Medicines;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static by.epam.silina.medicines.config.Constant.MEDICINES_XML_PATH;

class MedicinesJAXBImplTest {
    private final MedicinesJAXBImpl medicinesJAXBImpl = MedicinesJAXBImpl.getInstance();
    private final List<Medicine> expectedMedicines = new ArrayList<>();

    @BeforeEach
    void setup() {
        MedicineListCreator medicineListCreator = new MedicineListCreator();
        List<Medicine> medicineList = medicineListCreator.createMedicineList();
        expectedMedicines.addAll(medicineList);
    }

    @Test
    void convert() throws JAXBMedicinesException {
        Medicines actualMedicines = medicinesJAXBImpl.getMedicines();
        Assertions.assertNull(actualMedicines);

        File file = new File(MEDICINES_XML_PATH);
        medicinesJAXBImpl.convert(file);

        actualMedicines = medicinesJAXBImpl.getMedicines();
        Assertions.assertNotNull(actualMedicines);
        Assertions.assertEquals(6, actualMedicines.getMedicineList().size());


        List<Medicine> actualMedicineList = actualMedicines.getMedicineList();

        Assertions.assertEquals(expectedMedicines.get(0), actualMedicineList.get(0));
        Assertions.assertEquals(expectedMedicines.get(1), actualMedicineList.get(1));
        Assertions.assertEquals(expectedMedicines.get(2), actualMedicineList.get(2));
        Assertions.assertEquals(expectedMedicines.get(3), actualMedicineList.get(3));
        Assertions.assertEquals(expectedMedicines.get(4), actualMedicineList.get(4));
        Assertions.assertEquals(expectedMedicines.get(5), actualMedicineList.get(5));

        Assertions.assertEquals(expectedMedicines, actualMedicineList);
    }
}