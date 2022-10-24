package am.itspace.productcategoryservice.endpoint;

import am.itspace.productcategoryservice.dto.CategoryPutRequestDto;
import am.itspace.productcategoryservice.dto.CategoryRequestDto;
import am.itspace.productcategoryservice.dto.CategoryResponseDto;
import am.itspace.productcategoryservice.mapper.CategoryMapper;
import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CategoryEndpoint {
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    public CategoryEndpoint(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping("/categories")
    public List<CategoryResponseDto> getAllCategories() {
        return categoryMapper.map(categoryService.getAllCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody CategoryRequestDto categoryRequestDto) {
        categoryService.save(categoryMapper.map(categoryRequestDto));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/categories")
    public ResponseEntity<CategoryResponseDto> update(@RequestBody CategoryPutRequestDto categoryPutRequestDto) {
        Category categoryFromDb = categoryService.save(categoryMapper.map(categoryPutRequestDto));
        return ResponseEntity.ok(categoryMapper.map(categoryFromDb));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
