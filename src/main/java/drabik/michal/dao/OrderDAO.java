package drabik.michal.dao;

import drabik.michal.entity.Order;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    Order getOrder(long id);
    List<Order> getAllOrders();
    void deleteOrder(long id);
    void updateOrder(Order order);
}
