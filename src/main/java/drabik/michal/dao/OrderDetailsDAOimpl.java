package drabik.michal.dao;

import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDetailsDAOimpl implements OrderDetailsDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public void addOrderDetails(OrderDetails details) {
        factory.getCurrentSession().save(details);
    }

    @Override
    public OrderDetails getOrderDetails(long id) {
        return factory.getCurrentSession().get(OrderDetails.class, id);
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        Query<OrderDetails> query = factory.getCurrentSession().createQuery("from OrderDetails");
        return query.getResultList();
    }

    @Override
    public Order getOrderForOrderDetails(long orderDetailsId) {
        return factory.getCurrentSession().get(OrderDetails.class, orderDetailsId).getOrder();
    }

    @Override
    public Product getProductForOrderDetails(long orderDetailsId) {
        return factory.getCurrentSession().get(OrderDetails.class, orderDetailsId).getProduct();
    }

    @Override
    public void deleteOrderDetails(long id) {
        Session session = factory.getCurrentSession();
        OrderDetails details = session.get(OrderDetails.class, id);
        session.delete(details);
    }

    @Override
    public void updateOrderDetails(OrderDetails details) {
        factory.getCurrentSession().update(details);
    }
}
