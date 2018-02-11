package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.entity.Product;
import drabik.michal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session) {
        model.addAttribute("cart", session.getAttribute("cart"));
        return "cart";
    }

    @RequestMapping("/add-to-cart")
    public String addToCart(@RequestParam("id") Long productId,
                            Model model,
                            HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        Product product = productService.getProduct(productId);

        if (!cart.contains(product)) {
            cart.addToCart(product);
        }

        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart);

        return "redirect:/cart";
    }

    @RequestMapping("/update-cart")
    public String updateCart(@ModelAttribute("cart") Cart cart,
                             Model model,
                             HttpSession session) {
        Cart c = (Cart) session.getAttribute("cart");
        for (Product product : cart.getProducts()) {
            c.updateProductQuantity(product, cart.getQuantity(product));
        }

        session.setAttribute("cart", c);
        model.addAttribute("cart", c);

        return "redirect:/cart";
    }
}
