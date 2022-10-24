package am.itspace.productcategoryservice.service;

import am.itspace.productcategoryservice.exception.ProductNotFoundException;
import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        if (id <= 0) {
            throw new ProductNotFoundException("Product does not exists this id - " + id);
        } else {
            Optional<Product> product = productRepository.findById(id);
            return product;
        }
    }

    public Product save(Product product) {
        if (product == null) {
            throw new NullPointerException("Product was passed null");
        }
        return productRepository.save(product);
    }

    public void deleteProductById(int id) {
        if (id <= 0) {
            throw new ProductNotFoundException("Product does not exists with this id `" + id);
        }
        productRepository.deleteById(id);
    }
}
