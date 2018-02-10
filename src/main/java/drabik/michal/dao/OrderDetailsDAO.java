package drabik.michal.dao;

import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.Product;

import java.util.List;

public interface OrderDetailsDAO {
    void addOrderDetails(OrderDetails details);
    OrderDetails getOrderDetails(long id);
    List<OrderDetails> getAllOrderDetails();
    Order getOrderForOrderDetails(long orderDetailsId);
    Product getProductForOrderDetails(long orderDetailsId);
    void deleteOrderDetails(long id);
    void updateOrderDetails(OrderDetails details);
}
