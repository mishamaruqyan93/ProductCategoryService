package am.itspace.productcategoryservice.service;

import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.sequrity.CurrentUser;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(int id);
    Product save(Product product, CurrentUser currentUser);
    Product put(int id);
    void deleteProductById(int id, CurrentUser currentUser);
}
