package by.epam.silina.medicines.model.medicines;

import by.epam.silina.medicines.config.EnumIdentifier;
import jakarta.xml.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

import static by.epam.silina.medicines.config.Constant.*;

@XmlType
@XmlEnum
@XmlAccessorType(XmlAccessType.FIELD)
public enum PackageTypeEnum implements EnumIdentifier {
    @XmlEnumValue(TUBE_TYPE)
    TUBE(TUBE_TYPE),
    @XmlEnumValue(BOTTLE_TYPE)
    BOTTLE(BOTTLE_TYPE),
    @XmlEnumValue(JAR_TYPE)
    JAR(JAR_TYPE),
    @XmlEnumValue(PACK_TYPE)
    PACK(PACK_TYPE);

    private static final Logger log = LoggerFactory.getLogger(PackageTypeEnum.class);
    private final String name;

    PackageTypeEnum(String name) {
        this.name = name;
    }

    public static PackageTypeEnum getPackageTypeEnumByTypeName(String typeName) {
        Optional<PackageTypeEnum> optionalPackageTypeEnum = Arrays.stream(PackageTypeEnum.values())
                .filter(el -> el.getName().equals(typeName))
                .findFirst();
        if (optionalPackageTypeEnum.isPresent()) {
            return optionalPackageTypeEnum.get();
        } else {
            log.warn(NO_SUCH_ENUM, typeName);
            return null;
        }
    }

    public String getName() {
        return name;
    }
}
