package by.epam.silina.medicines.datasource;

import by.epam.silina.medicines.model.medicines.Medicine;
import by.epam.silina.medicines.model.users.Client;
import by.epam.silina.medicines.model.users.Employee;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataSource {
    private static final InMemoryDataSource instance = new InMemoryDataSource();
    private final List<Client> clients = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<Medicine> medicines = new ArrayList<>();

    private InMemoryDataSource() {
    }

    public static InMemoryDataSource getInstance() {
        return instance;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }
}
