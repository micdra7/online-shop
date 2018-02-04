package drabik.michal.controller;

import drabik.michal.entity.Users;
import drabik.michal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @Autowired
    private UsersService service;

    @GetMapping("/")
    public String home() {
        Users user = new Users("michal", "drabik");
        service.addUser(user);
        return "home";
    }
}
