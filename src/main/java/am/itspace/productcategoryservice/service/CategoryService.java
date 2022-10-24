package am.itspace.productcategoryservice.service;

import am.itspace.productcategoryservice.exception.CategoryNotFoundException;
import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        if (category == null) {
            throw new NullPointerException("Category was passed null");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(int id) {
        if (id <= 0) {
            throw new CategoryNotFoundException("Category does not exists with this id` " + id);
        }
        categoryRepository.deleteById(id);
    }
}
