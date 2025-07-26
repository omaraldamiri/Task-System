package team404a.MVC.Employee.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team404a.MVC.Employee.system.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
