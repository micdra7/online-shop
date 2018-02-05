package drabik.michal.controller;

import drabik.michal.entity.Order;
import drabik.michal.entity.Product;
import drabik.michal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private OrderService service;

    @RequestMapping("/")
    public String home(Model model) {
        listRecentPurchases(model);
        return "home";
    }

    private void listRecentPurchases(Model model) {
        List<Order> orders = service.getAllOrders();

        int orderCounter = 1;
        List<Product> recent = Collections.synchronizedList(new ArrayList<>());

        for (Order order : orders) {
            Calendar orderDate = Calendar.getInstance();
            orderDate.setTime(order.getDate());

            Calendar lastMonth = Calendar.getInstance();
            int day = lastMonth.get(Calendar.DAY_OF_MONTH);
            int month = lastMonth.get(Calendar.MONTH);
            int year = lastMonth.get(Calendar.YEAR);

            if (month == Calendar.JANUARY) {
                month = Calendar.DECEMBER;
                year = year - 1;
            } else {
                month--;
            }

            lastMonth.set(year, month, day);


            if (orderDate.after(lastMonth)) {
                recent.add(order.getDetails().get(0).getProduct());
                orderCounter++;
            }

            if (orderCounter == 5) {
                break;
            }
        }
        model.addAttribute("products", recent);
    }

}
