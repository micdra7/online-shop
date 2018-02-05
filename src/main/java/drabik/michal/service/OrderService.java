package drabik.michal.service;

import drabik.michal.entity.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    Order getOrder(long id);
    List<Order> getAllOrders();
    void deleteOrder(long id);
    void updateOrder(Order order);
}
