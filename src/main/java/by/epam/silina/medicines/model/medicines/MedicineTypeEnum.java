package by.epam.silina.medicines.model.medicines;

import by.epam.silina.medicines.config.EnumIdentifier;
import jakarta.xml.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

import static by.epam.silina.medicines.config.Constant.*;

@XmlType
@XmlEnum
@XmlAccessorType(XmlAccessType.FIELD)
public enum MedicineTypeEnum implements EnumIdentifier {
    @XmlEnumValue(EFFERVESCENT_TABLETS_TYPE)
    EFFERVESCENT_TABLETS(EFFERVESCENT_TABLETS_TYPE),
    @XmlEnumValue(CAPSULES_TYPE)
    CAPSULES(CAPSULES_TYPE),
    @XmlEnumValue(TABLETS_TYPE)
    TABLETS(TABLETS_TYPE);

    private final String name;

    MedicineTypeEnum(String name) {
        this.name = name;
    }

    public static MedicineTypeEnum getMedicineTypeEnumByTypeName(String typeName) {
        Optional<MedicineTypeEnum> optionalMedicineTypeEnum = Arrays.stream(MedicineTypeEnum.values())
                .filter(el -> el.getName().equals(typeName))
                .findFirst();
        if (optionalMedicineTypeEnum.isPresent()) {
            return optionalMedicineTypeEnum.get();
        } else {
            //todo log and exception
            return null;
        }
    }

    public String getName() {
        return name;
    }
}
