package team404a.MVC.Employee.system.service;

import org.springframework.stereotype.Service;
import team404a.MVC.Employee.system.entities.Product;
import team404a.MVC.Employee.system.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product FindUserById(long id) {
        if(productRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Product Not Found");
        }
        return productRepository.findById(id).get();

    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product deleteUser(Product product) {
        productRepository.delete(product);
        return product;
    }
}
