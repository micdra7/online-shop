package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.entity.Order;
import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.Product;
import drabik.michal.entity.Review;
import drabik.michal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;


    @RequestMapping("/product")
    public String product(@RequestParam("id") Long id,
                          Model model,
                          HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        listProductParameters(id, model);
        checkUsersReviewsForProduct(id, model);
        return "product";
    }

    private void listProductParameters(Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("id", id);
        model.addAttribute("product", product);
        model.addAttribute("subcategory", product.getSubcategory());

        List<Review> reviews = productService.getReviewsForProduct(id);
        Collections.sort(reviews, (review, review1) -> {
            if (review.getDate().after(review1.getDate())) {
                return 1;
            } else if (review.getDate().before(review1.getDate())) {
                return -1;
            } else {
                return 0;
            }
        });

        List<Review> listedReviews = new LinkedList<>();
        double averageRating = 0d;
        int quantity = 0;
        for (int i = 0; i < 5 && i < reviews.size(); i++) {
            listedReviews.add(reviews.get(i));
            averageRating += reviews.get(i).getRating();
            quantity++;
        }
        averageRating /= quantity;
        averageRating = (double) Math.round(averageRating * 100.0) / 100.0;
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("reviews", listedReviews);

    }

    private void checkUsersReviewsForProduct(Long productId, Model model) {

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User springUser;
        if (user instanceof User) {
            springUser = (User) user;
        } else {
            model.addAttribute("review", "review existing");
            return;
        }

        drabik.michal.entity.User myUser = userService.getUser(springUser.getUsername());

        List<Order> orders = userService.getOrdersForUser(myUser.getId());
        List<Review> reviews = userService.getReviewsForUser(myUser.getId());
        for (Order order : orders) {
            List<OrderDetails> details = orderService.getDetailsForOrder(order.getId());

            for (OrderDetails detail : details) {
                for (Review review : reviews) {
                    if (detail.getProduct().getId().equals(productId)) {
                        model.addAttribute("review", "review existing");
                        return;
                    }
                }
            }
        }


        model.addAttribute("review", null);
    }

}
