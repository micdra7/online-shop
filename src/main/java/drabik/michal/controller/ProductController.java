package drabik.michal.controller;

import drabik.michal.entity.Product;
import drabik.michal.entity.Review;
import drabik.michal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("/product")
    public String product(@RequestParam("id") Long id, Model model) {
        listProductParameters(id, model);
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
}
