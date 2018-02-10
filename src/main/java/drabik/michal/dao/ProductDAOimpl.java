package drabik.michal.dao;

import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.Product;
import drabik.michal.entity.Review;
import drabik.michal.entity.Subcategory;
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
    public List<OrderDetails> getOrderDetailsForProduct(long productId) {
        Query<OrderDetails> query =
                factory.getCurrentSession().createQuery("from OrderDetails od where od.product.id=:id");
        query.setParameter("id", productId);
        return query.getResultList();
    }

    @Override
    public Subcategory getSubcategoryForProduct(long productId) {
        return factory.getCurrentSession().get(Product.class, productId).getSubcategory();
    }

    @Override
    public List<Review> getReviewsForProduct(long productId) {
        Query<Review> query = factory.getCurrentSession().createQuery("from Review r where r.product.id=:id");
        query.setParameter("id", productId);
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
