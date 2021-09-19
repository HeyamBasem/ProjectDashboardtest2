package server.model.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class User extends BaseUser implements Serializable, Comparable {
    private int licenceId;
    private UserPrivilege privilege;

    public User(int userId, String userName, String password, LocalDate timeStamp, int licenceId, UserPrivilege privilege) {
        super(userId, userName, password, timeStamp);
        this.licenceId = licenceId;
        this.privilege = privilege;
    }

    public UserPrivilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(UserPrivilege privilege) {
        this.privilege = privilege;
    }

    public int getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(int licenceId) {
        this.licenceId = licenceId;
    }

    @Override
    public int compareTo(Object o) {
        User u = (User) o;
        return Integer.compare(getUserId(), u.getUserId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return licenceId == user.licenceId &&
                privilege == user.privilege;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), licenceId, privilege);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + getUserId())
                .add("userName=" + getUserName())
                .add("password=" + getPassword())
                .add("timeStamp=" + getTimeStamp())
                .add("licenceId=" + licenceId)
                .add("privilege=" + privilege)
                .toString();
        //userId, userName, password, timeStamp
    }
}
