package drabik.michal.service;

import drabik.michal.dao.OrderDetailsDAO;
import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsDAO dao;

    @Transactional
    @Override
    public void addOrderDetails(OrderDetails details) {
        dao.addOrderDetails(details);
    }

    @Transactional
    @Override
    public OrderDetails getOrderDetails(long id) {
        return dao.getOrderDetails(id);
    }

    @Transactional
    @Override
    public List<OrderDetails> getAllOrderDetails() {
        return dao.getAllOrderDetails();
    }

    @Transactional
    @Override
    public Order getOrderForOrderDetails(long orderDetailsId) {
        return dao.getOrderForOrderDetails(orderDetailsId);
    }

    @Transactional
    @Override
    public Product getProductForOrderDetails(long orderDetailsId) {
        return dao.getProductForOrderDetails(orderDetailsId);
    }

    @Transactional
    @Override
    public void deleteOrderDetails(long id) {
        dao.deleteOrderDetails(id);
    }

    @Transactional
    @Override
    public void updateOrderDetails(OrderDetails details) {
        dao.updateOrderDetails(details);
    }
}
