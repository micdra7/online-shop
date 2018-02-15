package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.entity.Review;
import drabik.michal.service.ProductService;
import drabik.michal.service.ReviewService;
import drabik.michal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;

@Controller
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/review")
    public String review(@RequestParam("productId") Long productId,
                         Model model,
                         HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        model.addAttribute("product", productService.getProduct(productId));
        model.addAttribute("review", new Review());
        return "review";
    }

    @RequestMapping("/add-review")
    public String addReview(@RequestParam("productId") Long productId,
                            @Valid @ModelAttribute("review") Review review,
                            BindingResult result,
                            Model model,
                            HttpSession session) {
        Cart.createInstanceIfNotExisting(session);

        if (result.hasErrors()) {
            model.addAttribute("review", review);
            model.addAttribute("product", productService.getProduct(productId));
            return "review";
        }

        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User springUser;
        if (user instanceof User) {
            springUser = (User) user;
        } else {
            return "redirect:/log-in";
        }

        String content = review.getContent();
        content = content.replace("\r\n", "<br>");
        content = content.replace("\n", "<br>");

        review.setContent(content);
        review.setUser(userService.getUser(springUser.getUsername()));
        review.setProduct(productService.getProduct(productId));
        review.setDate(Calendar.getInstance().getTime());

        reviewService.addReview(review);

        return "redirect:/";
    }

}
