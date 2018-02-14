package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.entity.Product;
import drabik.michal.entity.Review;
import drabik.michal.service.ProductService;
import drabik.michal.service.ReviewService;
import drabik.michal.service.UserService;
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

        for (int i = 0; i < 3 && i < reviews.size(); i++) {
            listedReviews.add(reviews.get(i));
        }

        model.addAttribute("reviews", listedReviews);

    }

    private void checkUsersReviewsForProduct(Long productId, Model model) {

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User springUser;
        if (user instanceof User) {
            springUser = (User) user;
        } else {
            model.addAttribute("review", "");
            return;
        }

        drabik.michal.entity.User myUser = userService.getUser(springUser.getUsername());

        List<Review> reviews = userService.getReviewsForUser(myUser.getId());

        for (Review review : reviews) {
            if (review.getProduct().getId().equals(productId)) {
                model.addAttribute("review", "");
                return;
            }
        }
        model.addAttribute("review", null);
    }
}
