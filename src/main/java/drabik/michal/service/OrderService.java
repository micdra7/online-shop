package drabik.michal.service;

import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.User;

import java.util.Date;
import java.util.List;

public interface OrderService {
    void addOrder(Order order);
    Order getOrder(long id);
    List<Order> getAllOrders();
    List<Order> getOrdersForUser(long userId);
    List<Order> getAllOrdersAfter(Date date);
    User getUserForOrder(long orderId);
    List<OrderDetails> getDetailsForOrder(long orderId);
    void deleteOrder(long id);
    void updateOrder(Order order);
}
