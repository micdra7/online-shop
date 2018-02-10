package drabik.michal.dao;

import drabik.michal.entity.Category;
import drabik.michal.entity.Product;
import drabik.michal.entity.Subcategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubcategoryDAOimpl implements SubcategoryDAO {

    @Autowired
    SessionFactory factory;

    @Override
    public void addSubcategory(Subcategory subcategory) {
        factory.getCurrentSession().save(subcategory);
    }

    @Override
    public Subcategory getSubcategory(int id) {
        return factory.getCurrentSession().get(Subcategory.class, id);
    }

    @Override
    public List<Subcategory> getAllSubcategories() {
        Query<Subcategory> query = factory.getCurrentSession().createQuery("from Subcategory");
        return query.getResultList();
    }

    @Override
    public List<Product> getProductsForSubcategory(int subcategoryId) {
        Query<Product> query = factory.getCurrentSession().createQuery("from Product p where p.subcategory.id=:id");
        query.setParameter("id", subcategoryId);
        return query.getResultList();
    }

    @Override
    public Category getCategoryForSubcategory(int subcategoryId) {
        Session session = factory.getCurrentSession();
        Subcategory subcategory = session.get(Subcategory.class, subcategoryId);
        return subcategory.getCategory();
    }

    @Override
    public void deleteSubcategory(int id) {
        Session session = factory.getCurrentSession();
        Subcategory subcategory = session.get(Subcategory.class, id);
        session.delete(subcategory);
    }

    @Override
    public void updateSubcategory(Subcategory subcategory) {
        factory.getCurrentSession().update(subcategory);
    }
}
