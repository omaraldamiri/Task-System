package team404a.MVC.Employee.system.entities;


import java.io.Serializable;
import java.util.Objects;

public class UserRole implements Serializable {

    private User user;
    private String roleName;

    public  UserRole(){}

    public UserRole(String username, User user) {
        this.user = user;
        this.roleName = roleName;
    }

    public User getUsername() {
        return user;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj==null || this.getClass() != obj.getClass()) return false;

        UserRole userRole=(UserRole)obj;
        return Objects.equals(user, userRole.user) &&
                Objects.equals(roleName, userRole.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user,roleName);
    }
}
