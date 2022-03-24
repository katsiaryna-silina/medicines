package by.epam.silina.medicines.model.users;

import java.time.LocalDateTime;
import java.util.Objects;

public class Employee extends User {
    private String position;

    private Employee() {
    }

    Employee(String username, String password, String email, String position, LocalDateTime lastLoginDateAndTime) {
        super(username, password, email, lastLoginDateAndTime);
        this.position = position;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", lastLoginDateAndTime=" + super.getLastLoginDateAndTime() +
                ", position='" + position + '\'' +
                '}';
    }
}
