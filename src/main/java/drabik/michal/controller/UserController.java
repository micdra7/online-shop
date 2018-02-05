package drabik.michal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/log-in")
    public String logIn() {

        return "log-in";
    }

    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    @RequestMapping("/user")
    public String user() {

        return "user";
    }

}
