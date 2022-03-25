package by.epam.silina.medicines.util.impl;

import by.epam.silina.medicines.exception.JAXBMedicinesException;
import by.epam.silina.medicines.model.medicines.Medicines;
import by.epam.silina.medicines.util.MedicinesJAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MedicinesJAXBImpl implements MedicinesJAXB {
    private static final Logger log = LoggerFactory.getLogger(MedicinesJAXBImpl.class);
    private static final MedicinesJAXBImpl instance = new MedicinesJAXBImpl();
    private Medicines medicines;

    private MedicinesJAXBImpl() {
    }

    public static MedicinesJAXBImpl getInstance() {
        return instance;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    @Override
    public void convert(File file) throws JAXBMedicinesException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Medicines.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            medicines = (Medicines) unmarshaller.unmarshal(file);
            medicines.getMedicineList().forEach(el -> log.info("{}", el));
        } catch (JAXBException e) {
            throw new JAXBMedicinesException(e);
        }
    }
}
