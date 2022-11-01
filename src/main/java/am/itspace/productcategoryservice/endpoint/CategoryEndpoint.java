package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.CategoryRequestDto;
import am.itspace.productcategoryservice.dto.CategoryResponseDto;
import am.itspace.productcategoryservice.mapper.CategoryMapper;
import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.service.impl.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryEndpoint {
    private CategoryServiceImpl categoryServiceImpl;
    private CategoryMapper categoryMapper;

    public CategoryEndpoint(CategoryServiceImpl categoryServiceImpl, CategoryMapper categoryMapper) {
        this.categoryServiceImpl = categoryServiceImpl;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public List<CategoryResponseDto> getAllCategories() {
        return categoryMapper.map(categoryServiceImpl.getAllCategories());
    }

//    @RolesAllowed("Role.ADMIN")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryServiceImpl.save(categoryMapper.map(categoryRequestDto));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable("id") int id) {
        Category putCategory = categoryServiceImpl.put(id);
        return ResponseEntity.ok(categoryMapper.map(putCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        categoryServiceImpl.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
