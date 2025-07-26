package team404a.MVC.Employee.system.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import team404a.MVC.Employee.system.entities.Role;
import team404a.MVC.Employee.system.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User FindUserById(long id);
    List<User> findAllUsers();
    void saveUser(User user);
    User deleteUser(User user);
    List<Role> roles(String username);
    boolean usernameCheck(String username);
    User findUserByUsername(String username);

}
