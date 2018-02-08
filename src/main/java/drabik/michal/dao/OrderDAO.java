package drabik.michal.dao;

import drabik.michal.entity.Order;
import drabik.michal.entity.User;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    Order getOrder(long id);
    List<Order> getAllOrders();
    List<Order> getOrdersForUser(User user);
    void deleteOrder(long id);
    void updateOrder(Order order);
}
