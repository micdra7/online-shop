package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.cart.data.ProductData;
import drabik.michal.entity.Product;
import drabik.michal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;

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
}
