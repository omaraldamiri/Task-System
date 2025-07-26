package team404a.MVC.Employee.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team404a.MVC.Employee.system.entities.Role;
import team404a.MVC.Employee.system.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<Role> findByUsername(String username);
    Optional<User> findByUsernameIgnoreCase(String username);
    //here we use the entity names
    @Query("SELECT u FROM User u where u.username = ?1")
    Optional<User> findUserByUsername(String username);



}
