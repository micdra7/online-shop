package drabik.michal.service;

import drabik.michal.dao.OrderDAO;
import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public List<Order> getAllOrdersAfter(Date date) {
        return dao.getAllOrdersAfter(date);
    }

    @Transactional
    @Override
    public List<Order> getOrdersForUser(long userId) {
        return dao.getOrdersForUser(userId);
    }

    @Transactional
    @Override
    public User getUserForOrder(long orderId) {
        return dao.getUserForOrder(orderId);
    }

    @Transactional
    @Override
    public List<OrderDetails> getDetailsForOrder(long orderId) {
        return dao.getDetailsForOrder(orderId);
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
