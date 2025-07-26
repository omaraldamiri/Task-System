package team404a.MVC.Employee.system.entities;

import jakarta.persistence.*;

@Table(name="roles")
@Entity
@IdClass(UserRole.class)
public class Role {

    @Id
    private String roleName;
    @Id
    @ManyToOne
    @JoinColumn(name = "username" , referencedColumnName = "username")
    private User user;

    public Role(){}
    public Role(String roleName, User user) {
        this.user = user;
        this.roleName = roleName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUsername() {
        return user;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return roleName + " ";
    }
}




