package by.epam.silina.medicines.model.users;

import java.time.LocalDateTime;
import java.util.Objects;

public class Client extends User {
    private String telephoneNumber;

    private Client() {
    }

    private Client(String username, String password, String email, String telephoneNumber, LocalDateTime lastLoginDateAndTime) {
        super(username, password, email, lastLoginDateAndTime);
        this.telephoneNumber = telephoneNumber;
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(telephoneNumber, client.telephoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), telephoneNumber);
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", lastLoginDateAndTime=" + super.getLastLoginDateAndTime() +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    public static class ClientBuilder {
        private String telephoneNumber;
        private String username;
        private String password;
        private String email;
        private LocalDateTime lastLoginDateAndTime;

        private ClientBuilder() {
        }

        public ClientBuilder telephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public ClientBuilder username(String username) {
            this.username = username;
            return this;
        }

        public ClientBuilder password(String password) {
            this.password = password;
            return this;
        }

        public ClientBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder lastLoginDateAndTime(LocalDateTime lastLoginDateAndTime) {
            this.lastLoginDateAndTime = lastLoginDateAndTime;
            return this;
        }

        public Client build() {
            return new Client(this.telephoneNumber, this.username, this.password, this.email, this.lastLoginDateAndTime);
        }
    }
}
