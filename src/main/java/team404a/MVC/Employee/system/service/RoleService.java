package team404a.MVC.Employee.system.service;

import team404a.MVC.Employee.system.entities.Role;
import team404a.MVC.Employee.system.entities.User;

import java.util.List;

public interface RoleService {

    Role saveRole(Role role);
    void deleteRoles(User User);
    void addRolesToUser(List<String> list,User user);

}
