package drabik.michal.controller;

import drabik.michal.entity.Category;
import drabik.michal.entity.Product;
import drabik.michal.entity.Subcategory;
import drabik.michal.service.CategoryService;
import drabik.michal.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class CategoriesController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubcategoryService subcategoryService;

    @RequestMapping("/categories")
    public String categories(Model model) {
        listAllCategories(model);
        return "categories";
    }

    @RequestMapping("/subcategories")
    public String subcategories(@RequestParam Integer id,
                                @RequestParam Integer page,
                                Model model) {
        listProductsFromSubcategory(id, page, model);
        return "subcategories";
    }

    private void listAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        for (Category category : categories) {
            category.setSubcategories(categoryService.getSubcategoriesForCategory(category.getId()));
        }
        model.addAttribute("categories", categories);
    }

    private void listProductsFromSubcategory(Integer id, Integer page, Model model) {
        Subcategory subcategory = subcategoryService.getSubcategory(id);
        List<Product> products = subcategoryService.getProductsForSubcategory(id);

        if (products.size() > 25) {
            List<Product> displayed = new LinkedList<>();
            for (int i = (page-1)*25; i < page*25 && i < products.size(); i++) {
                displayed.add(products.get(i));
            }
            model.addAttribute("id", id);
            model.addAttribute("page", page);
            model.addAttribute("maxPages", Math.ceil(products.size()/25));
            model.addAttribute("products", displayed);
        } else {
            page = 1;
            model.addAttribute("id", id);
            model.addAttribute("page", page);
            model.addAttribute("maxPages", 1);
            model.addAttribute("products", products);
        }
    }
}
