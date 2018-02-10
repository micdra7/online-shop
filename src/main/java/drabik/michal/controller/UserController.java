package drabik.michal.controller;

import drabik.michal.entity.Order;
import drabik.michal.entity.Role;
import drabik.michal.entity.User;
import drabik.michal.entity.UserDetails;
import drabik.michal.service.OrderService;
import drabik.michal.service.UserService;
import drabik.michal.validation.UserDataError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.LinkedList;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public String user(@RequestParam("username") String username,
                       @RequestParam("page") Integer page,
                       Model model) {
        displayUserPage(username, page, model);
        return "user";
    }

    @RequestMapping("/log-in")
    public String logIn(Model model) {
        model.addAttribute("user", new User());
        return "log-in";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        return logIn(model);
    }

    @RequestMapping("/register-form")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userDetails", new UserDetails());
        model.addAttribute("error", new UserDataError());
        return "register";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("user") User user,
                           @ModelAttribute("userDetails") UserDetails details,
                           Model model) {
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
            User toAdd = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()));
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(new Role("CUSTOMER"));
            toAdd.setEnabled(1);
            toAdd.setDetails(details);
            toAdd.setRoles(roles);
            userService.addUser(toAdd);
            model.addAttribute("user", new User(user.getUsername(), ""));
            return "redirect:/logout";
        }
    }

    private void displayUserPage(String username, Integer page, Model model) {
        User user = userService.getUser(username);

        if (page == 1) {
            model.addAttribute("userDetails", user.getDetails());
            model.addAttribute("orders", new LinkedList<Order>());
        } else if (page == 2) {
            model.addAttribute("userDetails", new UserDetails());
            model.addAttribute("orders", orderService.getOrdersForUser(user));
        } else {
            model.addAttribute("userDetails", new UserDetails());
            model.addAttribute("orders", new LinkedList<Order>());
        }
    }

}
