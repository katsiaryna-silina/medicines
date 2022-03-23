package by.epam.silina.medicines.model.users;

public final class EmployeeBuilder {
    private String position;
    private String username;
    private String password;
    private String email;

    EmployeeBuilder() {
    }

    public EmployeeBuilder position(String position) {
        this.position = position;
        return this;
    }

    public EmployeeBuilder username(String username) {
        this.username = username;
        return this;
    }

    public EmployeeBuilder password(String password) {
        this.password = password;
        return this;
    }

    public EmployeeBuilder email(String email) {
        this.email = email;
        return this;
    }

    public Employee build() {
        return new Employee(this.username, this.password, this.email, this.position);
    }
}
