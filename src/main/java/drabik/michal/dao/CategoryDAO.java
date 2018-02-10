package drabik.michal.dao;

import drabik.michal.entity.Category;
import drabik.michal.entity.Subcategory;

import java.util.List;

public interface CategoryDAO {
    void addCategory(Category category);
    Category getCategory(int id);
    List<Category> getAllCategories();
    List<Subcategory> getSubcategoriesForCategory(int categoryId);
    void deleteCategory(int id);
    void updateCategory(Category category);
}
