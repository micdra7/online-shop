package drabik.michal.dao;

import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
        Query<Order> query = factory.getCurrentSession().createQuery("from Order o order by o.date desc");
        return query.getResultList();
    }

    @Override
    public List<Order> getAllOrdersAfter(Date date) {
        Query<Order> query = factory.getCurrentSession().createQuery("from Order o where o.date > :date");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrdersForUser(long userId) {
        Query<Order> query =factory.getCurrentSession().createQuery("from Order o where o.user.id=:id");
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public User getUserForOrder(long orderId) {
        return factory.getCurrentSession().get(Order.class, orderId).getUser();
    }

    @Override
    public List<OrderDetails> getDetailsForOrder(long orderId) {
        Query<OrderDetails> query =
                factory.getCurrentSession().createQuery("from OrderDetails od where od.order.id=:id");
        query.setParameter("id", orderId);
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
