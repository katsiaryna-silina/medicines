package by.epam.silina.medicines.model;

import java.util.Objects;

public class Employee extends User {
    private String position;

    Employee(String username, String password, String email, String position) {
        super(username, password, email);
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
        Employee employee = (Employee) o;
        return Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
