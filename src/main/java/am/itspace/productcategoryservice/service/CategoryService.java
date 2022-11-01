package am.itspace.productcategoryservice.service;

import am.itspace.productcategoryservice.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category save(Category category);

    void deleteCategoryById(int id);

    Category put(int id);
}
