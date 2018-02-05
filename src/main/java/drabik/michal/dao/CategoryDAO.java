package drabik.michal.dao;

import drabik.michal.entity.Category;

import java.util.List;

public interface CategoryDAO {
    void addCategory(Category category);
    Category getCategory(long id);
    List<Category> getAllCategories();
    void deleteCategory(long id);
    void updateCategory(Category category);
}
