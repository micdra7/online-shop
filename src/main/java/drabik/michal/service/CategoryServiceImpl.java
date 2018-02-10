package drabik.michal.service;

import drabik.michal.dao.CategoryDAO;
import drabik.michal.entity.Category;
import drabik.michal.entity.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO dao;

    @Transactional
    @Override
    public void addCategory(Category category) {
        dao.addCategory(category);
    }

    @Transactional
    @Override
    public Category getCategory(int id) {
        return dao.getCategory(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    @Override
    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }

    @Transactional
    @Override
    public List<Subcategory> getSubcategoriesForCategory(int categoryId) {
        return dao.getSubcategoriesForCategory(categoryId);
    }

    @Transactional
    @Override
    public void deleteCategory(int id) {
        dao.deleteCategory(id);
    }

    @Transactional
    @Override
    public void updateCategory(Category category) {
        dao.updateCategory(category);
    }
}
