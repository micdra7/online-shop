package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccessDeniedController {

    @RequestMapping("/403")
    public String accessDenied(HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        return "403";
    }

}
