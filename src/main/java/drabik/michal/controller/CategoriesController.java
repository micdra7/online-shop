package drabik.michal.controller;

import drabik.michal.entity.Category;
import drabik.michal.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CategoriesController {

    @Autowired
    CategoryService service;

    @RequestMapping("/categories")
    public String categories(Model model) {
        listAllCategories(model);
        return "categories";
    }

    private void listAllCategories(Model model) {
        List<Category> categories = service.getAllCategories();
        model.addAttribute("categories", categories);
    }

}
