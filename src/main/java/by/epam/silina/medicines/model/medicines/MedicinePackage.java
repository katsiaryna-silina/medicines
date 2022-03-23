package by.epam.silina.medicines.model.medicines;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;

import static by.epam.silina.medicines.config.Constant.*;

@XmlType(name = PACKAGE)
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicinePackage {
    @XmlElement(name = PACKAGE_TYPE)
    private PackageTypeEnum packageTypeEnum;
    @XmlElement(name = NUMBER)
    private Integer number;
    @XmlElement(name = PRICE)
    private BigDecimal price;
    @XmlAttribute(name = IS_SEALED)
    private boolean isSealed;

    private MedicinePackage() {
    }

    public static MedicinePackageBuilder builder() {
        return new MedicinePackageBuilder();
    }

    public PackageTypeEnum getPackageTypeEnum() {
        return packageTypeEnum;
    }

    public void setPackageTypeEnum(PackageTypeEnum packageTypeEnum) {
        this.packageTypeEnum = packageTypeEnum;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isSealed() {
        return isSealed;
    }

    public void setSealed(boolean sealed) {
        isSealed = sealed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicinePackage that = (MedicinePackage) o;
        return isSealed == that.isSealed && packageTypeEnum == that.packageTypeEnum && Objects.equals(number, that.number) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageTypeEnum, number, price, isSealed);
    }

    @Override
    public String toString() {
        return "MedicinePackage{" +
                "packageTypeEnum=" + packageTypeEnum +
                ", number=" + number +
                ", price=" + price +
                ", isSealed=" + isSealed +
                '}';
    }


    public static final class MedicinePackageBuilder {
        private PackageTypeEnum packageTypeEnum;
        private Integer number;
        private BigDecimal price;
        private boolean isSealed;

        private MedicinePackageBuilder() {
        }

        public MedicinePackageBuilder packageTypeEnum(PackageTypeEnum packageTypeEnum) {
            this.packageTypeEnum = packageTypeEnum;
            return this;
        }

        public MedicinePackageBuilder number(Integer number) {
            this.number = number;
            return this;
        }

        public MedicinePackageBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public MedicinePackageBuilder isSealed(boolean isSealed) {
            this.isSealed = isSealed;
            return this;
        }

        public MedicinePackage build() {
            MedicinePackage medicinePackage = new MedicinePackage();
            medicinePackage.setPackageTypeEnum(packageTypeEnum);
            medicinePackage.setNumber(number);
            medicinePackage.setPrice(price);
            medicinePackage.isSealed = this.isSealed;
            return medicinePackage;
        }
    }
}
