package server.model.user;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

public class Admin extends BaseUser implements Serializable,Comparable {
    private AdminPrivilege privilege;

    public Admin(int userId, String userName, String password, LocalDate timeStamp, AdminPrivilege privilege) {
        super(userId, userName, password, timeStamp);
        this.privilege = privilege;
    }

    public AdminPrivilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(AdminPrivilege privilege) {
        this.privilege = privilege;
    }

    @Override
    public int compareTo(Object o) {
        Admin a = (Admin)o;
        return Integer.compare(getUserId(),a.getUserId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return privilege == admin.privilege;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), privilege);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Admin.class.getSimpleName() + "[", "]")
                .add("userId=" + getUserId())
                .add("userName=" + getUserName())
                .add("password=" + getPassword())
                .add("timeStamp=" + getTimeStamp())
                .add("privilege=" + privilege)
                .toString();
    }
}
