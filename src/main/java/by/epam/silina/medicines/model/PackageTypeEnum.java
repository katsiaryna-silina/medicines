package by.epam.silina.medicines.model;

import static by.epam.silina.medicines.config.Constant.*;

public enum PackageTypeEnum {
    TUBE(TUBE_TYPE),
    BOTTLE(BOTTLE_TYPE),
    JAR(JAR_TYPE),
    PACK(PACK_TYPE);

    private final String name;

    PackageTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
