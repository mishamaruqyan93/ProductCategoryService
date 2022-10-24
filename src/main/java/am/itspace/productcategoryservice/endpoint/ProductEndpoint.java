package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.ProductPutRequestDto;
import am.itspace.productcategoryservice.dto.ProductRequestDto;
import am.itspace.productcategoryservice.dto.ProductResponseDto;
import am.itspace.productcategoryservice.mapper.ProductMapper;
import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductEndpoint {

    private ProductService productService;
    private ProductMapper productMapper;

    public ProductEndpoint(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        return productMapper.map(productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable int id) {
        Optional<Product> productById = productService.getProductById(id);
        return ResponseEntity.ok(productMapper.map(productById.get()));
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@RequestBody ProductRequestDto productRequestDto) {
        productService.save(productMapper.map(productRequestDto));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/products")
    public ResponseEntity<ProductResponseDto> update(@RequestBody ProductPutRequestDto productPutRequestDto) {
        Product productFromDb = productService.save(productMapper.map(productPutRequestDto));
        return ResponseEntity.ok(productMapper.map(productFromDb));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byCategory/{id}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategoryId(@PathVariable int id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}