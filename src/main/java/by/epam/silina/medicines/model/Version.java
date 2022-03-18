package by.epam.silina.medicines.model;

import java.util.List;
import java.util.Objects;

public class Version {
    private MedicineTypeEnum medicineTypeEnum;
    private String dosage;
    private Certificate certificate;
    private List<MedicinePackage> medicinePackages;

    private Version() {
    }

    public static VersionBuilder builder() {
        return new VersionBuilder();
    }

    public MedicineTypeEnum getMedicineTypeEnum() {
        return medicineTypeEnum;
    }

    public void setMedicineTypeEnum(MedicineTypeEnum medicineTypeEnum) {
        this.medicineTypeEnum = medicineTypeEnum;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public List<MedicinePackage> getMedicinePackages() {
        return medicinePackages;
    }

    public void setMedicinePackages(List<MedicinePackage> medicinePackages) {
        this.medicinePackages = medicinePackages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return medicineTypeEnum == version.medicineTypeEnum && Objects.equals(dosage, version.dosage) && Objects.equals(certificate, version.certificate) && Objects.equals(medicinePackages, version.medicinePackages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineTypeEnum, dosage, certificate, medicinePackages);
    }

    @Override
    public String toString() {
        return "Version{" +
                "medicineTypeEnum=" + medicineTypeEnum +
                ", dosage='" + dosage + '\'' +
                ", certificate=" + certificate +
                ", medicinePackages=" + medicinePackages +
                '}';
    }

    public static final class VersionBuilder {
        private MedicineTypeEnum medicineTypeEnum;
        private String dosage;
        private Certificate certificate;
        private List<MedicinePackage> medicinePackages;

        private VersionBuilder() {
        }

        public VersionBuilder medicineTypeEnum(MedicineTypeEnum medicineTypeEnum) {
            this.medicineTypeEnum = medicineTypeEnum;
            return this;
        }

        public VersionBuilder dosage(String dosage) {
            this.dosage = dosage;
            return this;
        }

        public VersionBuilder certificate(Certificate certificate) {
            this.certificate = certificate;
            return this;
        }

        public VersionBuilder medicinePackages(List<MedicinePackage> medicinePackages) {
            this.medicinePackages = medicinePackages;
            return this;
        }

        public Version build() {
            Version version = new Version();
            version.setMedicineTypeEnum(medicineTypeEnum);
            version.setDosage(dosage);
            version.setCertificate(certificate);
            version.setMedicinePackages(medicinePackages);
            return version;
        }
    }
}
