package drabik.michal.dao;

import drabik.michal.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOimpl implements ProductDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public void addProduct(Product product) {
        factory.getCurrentSession().save(product);
    }

    @Override
    public Product getProduct(long id) {
        return factory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public List<Product> getAllProducts() {
        Query<Product> query = factory.getCurrentSession().createQuery("from Product");
        return query.getResultList();
    }

    @Override
    public void deleteProduct(long id) {
        Session session = factory.getCurrentSession();
        Product product = session.get(Product.class, id);
        session.delete(product);
    }

    @Override
    public void updateProduct(Product product) {
        factory.getCurrentSession().update(product);
    }
}
