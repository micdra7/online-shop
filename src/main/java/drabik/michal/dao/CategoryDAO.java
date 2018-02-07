package drabik.michal.dao;

import drabik.michal.entity.Category;

import java.util.List;

public interface CategoryDAO {
    void addCategory(Category category);
    Category getCategory(int id);
    List<Category> getAllCategories();
    void deleteCategory(int id);
    void updateCategory(Category category);
}
