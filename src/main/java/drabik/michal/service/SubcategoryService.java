package drabik.michal.service;

import drabik.michal.entity.Product;
import drabik.michal.entity.Subcategory;

import java.util.List;

public interface SubcategoryService {
    void addSubcategory(Subcategory subcategory);
    Subcategory getSubcategory(int id);
    List<Subcategory> getAllSubcategories();
    List<Product> getProductsForSubcategory(int subcategoryId);
    void deleteSubcategory(int id);
    void updateSubcategory(Subcategory subcategory);
}
