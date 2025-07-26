package team404a.MVC.Employee.system.service;



import org.springframework.security.core.parameters.P;
import team404a.MVC.Employee.system.entities.Product;

import java.util.List;

public interface ProductService {

    Product FindUserById(long id);
    List<Product> findAllProducts();
    void saveProduct(Product product);
    Product deleteUser(Product product);

    
}
