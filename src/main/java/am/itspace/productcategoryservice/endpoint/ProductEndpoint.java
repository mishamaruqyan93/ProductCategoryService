package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.ProductRequestDto;
import am.itspace.productcategoryservice.dto.ProductResponseDto;
import am.itspace.productcategoryservice.mapper.ProductMapper;
import am.itspace.productcategoryservice.model.Product;
import am.itspace.productcategoryservice.sequrity.CurrentUser;
import am.itspace.productcategoryservice.service.impl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductEndpoint {

    private ProductMapper productMapper;
    private ProductServiceImpl productServiceImpl;

    public ProductEndpoint(ProductServiceImpl productServiceImpl, ProductMapper productMapper) {
        this.productServiceImpl = productServiceImpl;
        this.productMapper = productMapper;
    }

    @GetMapping()
    public List<ProductResponseDto> getAllProducts() {
        return productMapper.map(productServiceImpl.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") int id) {
        Optional<Product> productById = productServiceImpl.getProductById(id);
        return ResponseEntity.ok(productMapper.map(productById.get()));
    }

    //@RolesAllowed("Role.USER")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ProductRequestDto productRequestDto, @AuthenticationPrincipal CurrentUser currentUser) {
        productServiceImpl.save(productMapper.map(productRequestDto), currentUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable("id") int id) {
        Product putProduct = productServiceImpl.put(id);
        return ResponseEntity.ok(productMapper.map(putProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        productServiceImpl.deleteProductById(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}