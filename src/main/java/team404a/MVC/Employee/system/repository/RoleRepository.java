package team404a.MVC.Employee.system.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team404a.MVC.Employee.system.entities.Role;
import team404a.MVC.Employee.system.entities.User;
import team404a.MVC.Employee.system.entities.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, UserRole> {
    @Transactional
    @Modifying
    @Query("DELETE from Role where user=?1")
    void deleteByUser(User user);



}
