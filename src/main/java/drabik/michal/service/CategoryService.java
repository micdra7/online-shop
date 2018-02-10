package drabik.michal.service;

import drabik.michal.entity.Category;
import drabik.michal.entity.Subcategory;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    Category getCategory(int id);
    List<Category> getAllCategories();
    List<Subcategory> getSubcategoriesForCategory(int categoryId);
    void deleteCategory(int id);
    void updateCategory(Category category);
}
