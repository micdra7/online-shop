package drabik.michal.service;

import drabik.michal.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    Category getCategory(int id);
    List<Category> getAllCategories();
    void deleteCategory(int id);
    void updateCategory(Category category);
}
