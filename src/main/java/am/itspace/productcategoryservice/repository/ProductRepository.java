package am.itspace.productcategoryservice.repository;

import am.itspace.productcategoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
