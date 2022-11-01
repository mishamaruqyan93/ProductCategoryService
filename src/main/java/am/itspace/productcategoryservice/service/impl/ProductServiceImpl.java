package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.exception.DeleteProductProcessingException;
import am.itspace.productcategoryservice.exception.ProductNotFoundException;
import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.repository.CategoryRepository;
import am.itspace.productcategoryservice.repository.ProductRepository;
import am.itspace.productcategoryservice.sequrity.CurrentUser;
import am.itspace.productcategoryservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        if (id <= 0) {
            throw new ProductNotFoundException("Product does not exists this id - " + id);
        } else {
            Optional<Product> product = productRepository.findById(id);
            return product;
        }
    }

    @Override
    public Product save(Product product, CurrentUser currentUser) {
        if (Objects.nonNull(currentUser)) {
            product.setUserId(currentUser.getUser().getId());
        }
        if (product == null) {
            throw new NullPointerException("Product was passed null");
        }
        return productRepository.save(product);
    }

    @Override
    public Product put(int id) {
        if (id <= 0) {
            throw new NullPointerException("Product id was passed null" + id);
        }
        Optional<Product> byId = productRepository.findById(id);
        Product product = byId.get();
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(int id, CurrentUser currentUser) {
        if (id <= 0) {
            throw new ProductNotFoundException("Product does not exists with this id `" + id);
        }
        Product product = productRepository.getReferenceById(id);
        if (product.getUserId() == currentUser.getUser().getId()) {
            productRepository.deleteById(id);
        } else {
            throw new DeleteProductProcessingException("You have not permission for this action");
        }
    }
}
