package by.epam.silina.medicines.model.users;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class User {
    private String username;
    private String password;
    private String email;
    private LocalDateTime lastLoginDateAndTime;

    protected User() {
    }

    protected User(String username, String password, String email, LocalDateTime lastLoginDateAndTime) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLoginDateAndTime = lastLoginDateAndTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLastLoginDateAndTime() {
        return lastLoginDateAndTime;
    }

    public void setLastLoginDateAndTime(LocalDateTime lastLoginDateAndTime) {
        this.lastLoginDateAndTime = lastLoginDateAndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(lastLoginDateAndTime, user.lastLoginDateAndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, lastLoginDateAndTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastLoginDateAndTime=" + lastLoginDateAndTime +
                '}';
    }
}
