package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.cart.data.ProductData;
import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.Product;
import drabik.michal.service.OrderService;
import drabik.michal.service.ProductService;
import drabik.michal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        model.addAttribute("cart", session.getAttribute("cart"));
        return "cart";
    }

    @RequestMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") Long productId,
                            Model model,
                            HttpSession session) {
        Cart.createInstanceIfNotExisting(session);

        Cart cart = (Cart) session.getAttribute("cart");
        Product product = productService.getProduct(productId);
        ProductData productData = new ProductData(product);

        if (!cart.contains(productData)) {
            cart.addToCart(productData);
        }

        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart);

        return "redirect:/cart";
    }

    @RequestMapping("/update-cart")
    public String updateCart(@RequestParam("id") Long productId,
                             @RequestParam("selectedQuantity") Integer selectedQuantity,
                             Model model,
                             HttpSession session){

        Cart.createInstanceIfNotExisting(session);

        Cart c = (Cart) session.getAttribute("cart");
        for (ProductData product : c.getProducts()) {
            if (product.getId().equals(productId)) {
                if (product.getQuantity() < selectedQuantity) {
                    selectedQuantity = product.getQuantity();
                }
                product.setSelectedQuantity(selectedQuantity);
            }
            c.updateProductQuantity(productId, selectedQuantity);
        }
        c.updateTotalPrice();

        session.setAttribute("cart", c);
        model.addAttribute("cart", c);

        return "redirect:/cart";
    }

    @RequestMapping("/delete-from-cart")
    public String deleteFromCart(@RequestParam("id") Long productId,
                                 Model model,
                                 HttpSession session) {

        Cart.createInstanceIfNotExisting(session);

        Cart cart = (Cart) session.getAttribute("cart");
        Product product = productService.getProduct(productId);
        ProductData data = new ProductData(product);
        cart.removeFromCart(data);

        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart);

        return "redirect:/cart";
    }

    @RequestMapping("/pay")
    public String pay(Model model, HttpSession session) {
        Cart.createInstanceIfNotExisting(session);

        Cart cart = (Cart) session.getAttribute("cart");

        model.addAttribute("products", cart.getProducts());
        model.addAttribute("price", cart.getTotalPrice());

        return "pay";
    }

    @RequestMapping("/order-confirmation")
    public String orderConfirmation(Model model, HttpSession session) throws Exception {
        Cart.createInstanceIfNotExisting(session);

        Cart cart = (Cart)session.getAttribute("cart");
        Order order = new Order(Calendar.getInstance().getTime());
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User)SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();

        if (cart.getProducts().isEmpty()) {
            return "redirect:/cart";
        }

        if (user != null) {
            order.setUser(userService.getUser(user.getUsername()));
            order.setDetails(new ArrayList<>());

            for (ProductData data : cart.getProducts()) {
                double price = data.getPrice();
                int quantity = data.getSelectedQuantity();
                OrderDetails details = new OrderDetails(price, quantity, price*quantity);
                details.setOrder(order);
                Product product = productService.getProduct(data.getProducer(), data.getName());
                if (product.getQuantity() == 0) {
                    data.setSelectedQuantity(0);
                    cart.updateTotalPrice();
                    return "redirect:/cart";
                } else {
                    product.setQuantity(product.getQuantity() - data.getSelectedQuantity());
                    productService.updateProduct(product);
                    details.setProduct(product);
                    order.getDetails().add(details);
                }
            }
            orderService.addOrder(order);

            session.removeAttribute("cart");
            model.addAttribute("success", "Order has been successfully placed!");
        }
        return "redirect:/";
    }
}
