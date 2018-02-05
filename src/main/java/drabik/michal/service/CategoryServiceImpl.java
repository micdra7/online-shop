package drabik.michal.service;

import drabik.michal.dao.CategoryDAO;
import drabik.michal.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryDAO dao;

    @Transactional
    @Override
    public void addCategory(Category category) {
        dao.addCategory(category);
    }

    @Transactional
    @Override
    public Category getCategory(long id) {
        return dao.getCategory(id);
    }

    @Transactional
    @Override
    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }

    @Transactional
    @Override
    public void deleteCategory(long id) {
        dao.deleteCategory(id);
    }

    @Transactional
    @Override
    public void updateCategory(Category category) {
        dao.updateCategory(category);
    }
}
