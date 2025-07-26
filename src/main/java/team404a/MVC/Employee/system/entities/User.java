package team404a.MVC.Employee.system.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "username" , unique = true)
    @NotNull(message = "Required")
    @Size(min=5,max = 12,message = "Should be between 5-12")
    private String username;
    @NotNull(message = "Required")
    @Size(min=5,max = 12,message = "Should be between 5-12")
    @Column(name = "password")
    private String password;
    @NotNull(message = "Required")
    @Column(name = "email")
    private String  email;
    @Column(name="is_active")
    private boolean active;
    @OneToMany
    //name = "username"
    //Refers to the foreign key column in the child table (roles)
    //(The column that stores the reference)
    //
    //referencedColumnName = "username"
    //Points to the unique column in the parent table (users)
    //(The column being referenced)
    @JoinColumn(name = "username" , referencedColumnName = "username")
    private List<Role> roles;
    public User(){
        active=true;
    }

    public User(long id, String username, String password, String email , List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        active = true;
        this.roles=roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
