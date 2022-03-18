package by.epam.silina.medicines.model;

import java.time.LocalDate;
import java.util.Objects;

public class Certificate {
    private String name;
    private String whoIssued;
    private String registrationNumber;
    private LocalDate registrationDateFrom;
    private LocalDate registrationDateTo;

    private Certificate() {
    }

    public static CertificateBuilder builder() {
        return new CertificateBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhoIssued() {
        return whoIssued;
    }

    public void setWhoIssued(String whoIssued) {
        this.whoIssued = whoIssued;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getRegistrationDateFrom() {
        return registrationDateFrom;
    }

    public void setRegistrationDateFrom(LocalDate registrationDateFrom) {
        this.registrationDateFrom = registrationDateFrom;
    }

    public LocalDate getRegistrationDateTo() {
        return registrationDateTo;
    }

    public void setRegistrationDateTo(LocalDate registrationDateTo) {
        this.registrationDateTo = registrationDateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate that = (Certificate) o;
        return Objects.equals(name, that.name) && Objects.equals(whoIssued, that.whoIssued) && Objects.equals(registrationNumber, that.registrationNumber) && Objects.equals(registrationDateFrom, that.registrationDateFrom) && Objects.equals(registrationDateTo, that.registrationDateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, whoIssued, registrationNumber, registrationDateFrom, registrationDateTo);
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "name='" + name + '\'' +
                ", whoIssued='" + whoIssued + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", registrationDateFrom=" + registrationDateFrom +
                ", registrationDateTo=" + registrationDateTo +
                '}';
    }

    public static final class CertificateBuilder {
        private String name;
        private String whoIssued;
        private String registrationNumber;
        private LocalDate registrationDateFrom;
        private LocalDate registrationDateTo;

        private CertificateBuilder() {
        }

        public static CertificateBuilder aCertificate() {
            return new CertificateBuilder();
        }

        public CertificateBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CertificateBuilder whoIssued(String whoIssued) {
            this.whoIssued = whoIssued;
            return this;
        }

        public CertificateBuilder registrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
            return this;
        }

        public CertificateBuilder registrationDateFrom(LocalDate registrationDateFrom) {
            this.registrationDateFrom = registrationDateFrom;
            return this;
        }

        public CertificateBuilder registrationDateTo(LocalDate registrationDateTo) {
            this.registrationDateTo = registrationDateTo;
            return this;
        }

        public Certificate build() {
            Certificate certificate = new Certificate();
            certificate.setName(name);
            certificate.setWhoIssued(whoIssued);
            certificate.setRegistrationNumber(registrationNumber);
            certificate.setRegistrationDateFrom(registrationDateFrom);
            certificate.setRegistrationDateTo(registrationDateTo);
            return certificate;
        }
    }
}
