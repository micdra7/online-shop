package drabik.michal.service;

import drabik.michal.dao.OrderDAO;
import drabik.michal.entity.Order;
import drabik.michal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO dao;

    @Transactional
    @Override
    public void addOrder(Order order) {
        dao.addOrder(order);
    }

    @Transactional
    @Override
    public Order getOrder(long id) {
        return dao.getOrder(id);
    }

    @Transactional
    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }

    @Transactional
    @Override
    public List<Order> getOrdersForUser(User user) {
        return dao.getOrdersForUser(user);
    }

    @Transactional
    @Override
    public void deleteOrder(long id) {
        dao.deleteOrder(id);
    }

    @Transactional
    @Override
    public void updateOrder(Order order) {
        dao.updateOrder(order);
    }
}
