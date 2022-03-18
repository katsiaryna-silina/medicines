package by.epam.silina.medicines.model;

import static by.epam.silina.medicines.config.Constant.*;

public enum MedicineTypeEnum {
    EFFERVESCENT_TABLETS(EFFERVESCENT_TABLETS_TYPE),
    CAPSULES(CAPSULES_TYPE),
    TABLETS(TABLETS_TYPE);

    private final String name;

    MedicineTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
