package drabik.michal.service;

import drabik.michal.dao.SubcategoryDAO;
import drabik.michal.entity.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryDAO dao;

    @Transactional
    @Override
    public void addSubcategory(Subcategory subcategory) {
        dao.addSubcategory(subcategory);
    }

    @Transactional
    @Override
    public Subcategory getSubcategory(int id) {
        return dao.getSubcategory(id);
    }

    @Transactional
    @Override
    public List<Subcategory> getAllCategories() {
        return dao.getAllCategories();
    }

    @Transactional
    @Override
    public void deleteSubcategory(int id) {
        dao.deleteSubcategory(id);
    }

    @Transactional
    @Override
    public void updateSubcategory(Subcategory subcategory) {
        dao.updateSubcategory(subcategory);
    }
}
