package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.exception.CategoryNotFoundException;
import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.repository.CategoryRepository;
import am.itspace.productcategoryservice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        if (Objects.isNull(category)) {
            throw new NullPointerException("Category was passed null");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category put(int id) {
        if (id <= 0) {
            throw new NullPointerException("Category id was passed null" + id);
        }
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.get();
        return save(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        if (id <= 0) {
            throw new CategoryNotFoundException("Category does not exists with this id` " + id);
        }
        categoryRepository.deleteById(id);
    }
}
