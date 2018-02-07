package drabik.michal.dao;

import drabik.michal.entity.Subcategory;

import java.util.List;

public interface SubcategoryDAO {
    void addSubcategory(Subcategory subcategory);
    Subcategory getSubcategory(int id);
    List<Subcategory> getAllCategories();
    void deleteSubcategory(int id);
    void updateSubcategory(Subcategory subcategory);
}
