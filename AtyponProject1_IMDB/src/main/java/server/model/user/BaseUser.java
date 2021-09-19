package server.model.user;

import server.model.Identifiable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class BaseUser implements Comparable, Serializable, Identifiable {
    private int userId;
    private String userName;
    private String password;
    private LocalDate timeStamp;

    public BaseUser(int userId, String userName, String password, LocalDate timeStamp) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.timeStamp = timeStamp;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return getUserId();
    }

    public boolean authenticate(String password) {
        return getPassword().equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseUser)) return false;
        BaseUser baseUser = (BaseUser) o;
        return userId == baseUser.userId &&
                userName.equals(baseUser.userName) &&
                password.equals(baseUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password);
    }
}
