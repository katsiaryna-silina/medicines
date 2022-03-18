package by.epam.silina.medicines.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Medicine {
    private UUID id;
    private String name;
    private String company;
    private String group;
    private List<Version> versions;

    private Medicine() {
    }

    public static Medicine.MedicineBuilder builder() {
        return new Medicine.MedicineBuilder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id) && Objects.equals(name, medicine.name) && Objects.equals(company, medicine.company) && Objects.equals(group, medicine.group) && Objects.equals(versions, medicine.versions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, company, group, versions);
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", group='" + group + '\'' +
                ", versions=" + versions +
                '}';
    }

    public static final class MedicineBuilder {
        private UUID id;
        private String name;
        private String company;
        private String group;
        private List<Version> versions;

        private MedicineBuilder() {
        }

        public MedicineBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public MedicineBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MedicineBuilder company(String company) {
            this.company = company;
            return this;
        }

        public MedicineBuilder group(String group) {
            this.group = group;
            return this;
        }

        public MedicineBuilder versions(List<Version> versions) {
            this.versions = versions;
            return this;
        }

        public Medicine build() {
            Medicine medicine = new Medicine();
            medicine.setId(id);
            medicine.setName(name);
            medicine.setCompany(company);
            medicine.setGroup(group);
            medicine.setVersions(versions);
            return medicine;
        }
    }
}
