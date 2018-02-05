package drabik.michal.service;

import drabik.michal.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    Category getCategory(long id);
    List<Category> getAllCategories();
    void deleteCategory(long id);
    void updateCategory(Category category);
}
