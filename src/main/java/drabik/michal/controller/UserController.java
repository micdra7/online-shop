package drabik.michal.controller;

import drabik.michal.entity.User;
import drabik.michal.service.UserService;
import drabik.michal.validation.UserDataError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/log-in-failure")
    public String logInFailure(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        UserDataError error = new UserDataError("","");

        if (service.getUser(username) == null) {
            error.setUsername(UserDataError.INCORRECT_USERNAME_OR_PASSWORD);
        }
        model.addAttribute("user", new User(username, ""));
        model.addAttribute("error", error);
        return "log-in";
    }

    @RequestMapping("/register-form")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("error", new UserDataError());
        return "register";
    }

    @RequestMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           Model model) {
        return processRegistration(username, password, model);
    }

    private String processRegistration(String username, String password, Model model) {
        UserDataError error = new UserDataError();
        if (service.getUser(username) != null) {
            error.setUsername(UserDataError.EXISTING_USERNAME);
        }
        if (password.length() < 8) {
            error.setPassword(UserDataError.SHORT_PASSWORD);
        }

        if (error.getUsername() != null || error.getPassword() != null) {
            model.addAttribute("user", new User(username, ""));
            model.addAttribute("error", error);
            return "register";
        } else {
            service.addUser(new User(username, passwordEncoder.encode(password)));
            model.addAttribute("user", new User(username, ""));
            return "log-in";
        }
    }

}
