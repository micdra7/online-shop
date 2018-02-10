package drabik.michal.dao;

import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.User;

import java.util.Date;
import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);
    Order getOrder(long id);
    List<Order> getAllOrders();
    List<Order> getAllOrdersAfter(Date date);
    List<Order> getOrdersForUser(long userId);
    User getUserForOrder(long orderId);
    List<OrderDetails> getDetailsForOrder(long orderId);
    void deleteOrder(long id);
    void updateOrder(Order order);
}
