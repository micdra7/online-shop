package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.Product;
import drabik.michal.service.OrderDetailsService;
import drabik.michal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @RequestMapping("/")
    public String home(Model model, HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        listRecentPurchases(model);
        return "home";
    }

    private void listRecentPurchases(Model model) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }

        calendar.set(year, month, day);

        List<Order> orders = orderService.getAllOrdersAfter(calendar.getTime());
        List<Product> displayed = new LinkedList<>();

        for (int i = 0; i < 5 && i < orders.size(); i++) {
            List<OrderDetails> details = orderService.getDetailsForOrder(orders.get(i).getId());
            displayed.add(orderDetailsService.getProductForOrderDetails(details.get(0).getId()));
        }

        model.addAttribute("products", displayed);

    }

}
