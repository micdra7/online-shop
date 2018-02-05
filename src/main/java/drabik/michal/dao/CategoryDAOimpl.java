package drabik.michal.dao;

import drabik.michal.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOimpl implements CategoryDAO {

    @Autowired
    SessionFactory factory;

    @Override
    public void addCategory(Category category) {
        factory.getCurrentSession().save(category);
    }

    @Override
    public Category getCategory(long id) {
        return factory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public List<Category> getAllCategories() {
        Query<Category> query = factory.getCurrentSession().createQuery("from Category");
        return query.getResultList();
    }

    @Override
    public void deleteCategory(long id) {
        Session session = factory.getCurrentSession();
        Category category = session.get(Category.class, id);
        session.delete(category);
    }

    @Override
    public void updateCategory(Category category) {
        factory.getCurrentSession().update(category);
    }
}
