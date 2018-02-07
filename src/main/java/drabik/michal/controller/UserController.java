package drabik.michal.controller;

import drabik.michal.entity.User;
import drabik.michal.entity.UserDetails;
import drabik.michal.service.UserService;
import drabik.michal.validation.UserDataError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @RequestMapping("/log-in")
    public String logIn(Model model) {
        model.addAttribute("user", new User());
        return "log-in";
    }

    @RequestMapping("/authenticate")
    public String logInFailure(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        UserDataError error = new UserDataError("","");

        if (service.getUser(username) == null) {
            error.setUsername(UserDataError.INCORRECT_USERNAME_OR_PASSWORD);
        }

        if (error.getUsername() != null) {
            model.addAttribute("user", new User(username, ""));
            model.addAttribute("error", error);
            return "log-in";
        } else {
            return "home";
        }
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
        if (service.getUser(user.getUsername()) != null) {
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
            toAdd.setEnabled(1);
            toAdd.setDetails(details);
            service.addUser(toAdd);
            model.addAttribute("user", new User(user.getUsername(), ""));
            return "redirect:/logout";
        }
    }

}
