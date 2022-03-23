package by.epam.silina.medicines.model.medicines;

import jakarta.xml.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static by.epam.silina.medicines.config.Constant.MEDICINE;
import static by.epam.silina.medicines.config.Constant.MEDICINES;

@XmlRootElement(name = MEDICINES)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = MEDICINES)
public class Medicines {
    @XmlElement(name = MEDICINE)
    private List<Medicine> medicineList;

    public Medicines() {
    }

    public Medicines(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    public boolean add(Medicine medicine) {
        return medicineList.add(medicine);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicines medicines1 = (Medicines) o;
        return Objects.equals(medicineList, medicines1.medicineList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineList);
    }

    @Override
    public String toString() {
        return "Medicines{" +
                "medicines=" + medicineList +
                '}';
    }
}
