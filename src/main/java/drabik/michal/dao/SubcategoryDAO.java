package drabik.michal.dao;

import drabik.michal.entity.Category;
import drabik.michal.entity.Product;
import drabik.michal.entity.Subcategory;

import java.util.List;

public interface SubcategoryDAO {
    void addSubcategory(Subcategory subcategory);
    Subcategory getSubcategory(int id);
    List<Subcategory> getAllSubcategories();
    List<Product> getProductsForSubcategory(int subcategoryId);
    Category getCategoryForSubcategory(int subcategoryId);
    void deleteSubcategory(int id);
    void updateSubcategory(Subcategory subcategory);
}
