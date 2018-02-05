package drabik.michal.dao;

import drabik.michal.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOimpl implements OrderDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public void addOrder(Order order) {
        factory.getCurrentSession().save(order);
    }

    @Override
    public Order getOrder(long id) {
        return factory.getCurrentSession().get(Order.class, id);
    }

    @Override
    public List<Order> getAllOrders() {
        Query<Order> query = factory.getCurrentSession().createQuery("from Order as o order by o.date desc");
        return query.getResultList();
    }

    @Override
    public void deleteOrder(long id) {
        Session session = factory.getCurrentSession();
        Order order = session.get(Order.class, id);
        session.delete(order);
    }

    @Override
    public void updateOrder(Order order) {
        factory.getCurrentSession().update(order);
    }
}
