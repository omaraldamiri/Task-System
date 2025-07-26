package team404a.MVC.Employee.system.service;

import org.springframework.stereotype.Service;
import team404a.MVC.Employee.system.entities.Role;
import team404a.MVC.Employee.system.entities.User;
import team404a.MVC.Employee.system.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl  implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        roleRepository.save(role);
        return role;
    }

    @Override
    public void deleteRoles(User user) {
        roleRepository.deleteByUser(user);
    }

    @Override
    public void addRolesToUser(List<String> list,User user) {
        for(String e : list){
            if(e!=null && user!=null){
            roleRepository.save(new Role(e,user));
        }
        }
    }

}
