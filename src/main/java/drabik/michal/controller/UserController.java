package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.entity.*;
import drabik.michal.service.OrderService;
import drabik.michal.service.RoleService;
import drabik.michal.service.UserDetailsService;
import drabik.michal.service.UserService;
import drabik.michal.validation.UserDataError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/user")
    public String user(@RequestParam("username") String username,
                       @RequestParam("page") Integer page,
                       Model model,
                       HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        displayUserPage(username, page, model);
        return "user";
    }

    @RequestMapping("/user-update")
    public String userUpdate(@ModelAttribute("userDetails") UserDetails details,
                             Model model,
                             HttpSession session) {

        Cart.createInstanceIfNotExisting(session);

        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleService.getRole(1));
        details.getUser().setRoles(roles);
        userDetailsService.updateUserDetails(details);
        String username = details.getUser().getUsername();

        model.addAttribute("userDetails", details);
        model.addAttribute("orders", new LinkedList<Order>());
        return "redirect:/user?username=" + username + "&page=1";
    }

    @RequestMapping("/log-in")
    public String logIn(Model model, HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        model.addAttribute("user", new User());
        return "log-in";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        return logIn(model, session);
    }

    @RequestMapping("/register-form")
    public String registerForm(Model model, HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        model.addAttribute("user", new User());
        model.addAttribute("userDetails", new UserDetails());
        model.addAttribute("error", new UserDataError());
        return "register";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("user") User user,
                           @ModelAttribute("userDetails") UserDetails details,
                           Model model,
                           HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        return processRegistration(user, details, model);
    }

    private String processRegistration(User user, UserDetails details, Model model) {
        UserDataError error = new UserDataError();
        if (userService.getUser(user.getUsername()) != null) {
            error.setUsername(UserDataError.EXISTING_USERNAME);
        }
        if (user.getPassword().length() < 8) {
            error.setPassword(UserDataError.SHORT_PASSWORD);
        }

        if (error.getUsername() != null || error.getPassword() != null) {
            model.addAttribute("user", new User(user.getUsername(), ""));
            model.addAttribute("userDetails", details);
            model.addAttribute("error", error);
            return "register";
        } else {
            User toAdd = new User(user.getUsername(), BCrypt.hashpw(user.getPassword(),  BCrypt.gensalt()));
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(roleService.getRole(1));
            toAdd.setEnabled(1);
            toAdd.setDetails(details);
            details.setUser(toAdd);
            toAdd.setRoles(roles);
            userService.addUser(toAdd);
            model.addAttribute("user", new User(user.getUsername(), ""));
            return "redirect:/log-in";
        }
    }

    private void displayUserPage(String username, Integer page, Model model) {
        User user = userService.getUser(username);

        if (page == 1) {
            model.addAttribute("userDetails", userDetailsService.getUserDetailsForUser(user.getId()));
            model.addAttribute("orders", new LinkedList<Order>());
        } else if (page == 2) {
            List<Order> orders = orderService.getOrdersForUser(user.getId());
            LinkedHashMap<Order, Double> displayed = new LinkedHashMap<>();
            for (Order order : orders) {
                order.setDetails(orderService.getDetailsForOrder(order.getId()));
                double orderValue = 0d;
                for (OrderDetails details : order.getDetails()) {
                    orderValue += details.getValue();
                }
                displayed.put(order, orderValue);
            }

            model.addAttribute("userDetails", new UserDetails());
            model.addAttribute("orders", displayed);
        } else {
            model.addAttribute("userDetails", new UserDetails());
            model.addAttribute("orders", new LinkedList<Order>());
        }
    }

}
