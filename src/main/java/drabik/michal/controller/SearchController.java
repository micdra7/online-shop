package drabik.michal.controller;

import drabik.michal.cart.data.Cart;
import drabik.michal.entity.Product;
import drabik.michal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/search")
    public String search(HttpSession session, Model model) {
        Cart.createInstanceIfNotExisting(session);
        model.addAttribute("searchData", new SearchData());
        session.setAttribute("callCounter", 0);
        return "search";
    }

    @RequestMapping("/search-results")
    public String searchResults(@ModelAttribute("searchData") SearchData searchData,
                                @RequestParam("page") Integer page,
                                Model model,
                                HttpSession session) {
        Cart.createInstanceIfNotExisting(session);
        session.setMaxInactiveInterval(0);
        if (session.getAttribute("callCounter").equals(0)) {
            session.setAttribute("callCounter", 1);
            session.setAttribute("searchData", searchData);
        }
        displaySearchResults(page, model, session);

        return "search_result";
    }

    private void displaySearchResults(Integer page, Model model, HttpSession session) {
        SearchData data = (SearchData)session.getAttribute("searchData");
        List<Product> products = productService.getAllProducts();
        List<Product> searched = new LinkedList<>();

        for (Product p : products) {
            String producer = p.getProducer().toLowerCase();
            String name = p.getName().toLowerCase();
            String fullName = p.getProducer() + " " + p.getName();
            String queryString = data.getQueryString().toLowerCase();

            if (producer.contains(queryString) || name.contains(queryString) || fullName.contains(queryString)) {
                searched.add(p);
            }
        }

        if (searched.size() > 25) {
            List<Product> displayed = new LinkedList<>();
            for (int i = (page-1)*25; i < searched.size() && i < (page*25); i++) {
                displayed.add(searched.get(i));
            }
            model.addAttribute("products", displayed);
            model.addAttribute("maxPages", Math.ceil(searched.size()/25));
        } else {
            model.addAttribute("products", searched);
            model.addAttribute("maxPages", 1);
        }

        model.addAttribute("page", 1);
        model.addAttribute("searchData", data);
    }

    private static class SearchData {
        private String queryString;

        public SearchData() {}

        public SearchData(String queryString) {
            this.queryString = queryString;
        }

        public String getQueryString() {
            return queryString;
        }

        public void setQueryString(String queryString) {
            this.queryString = queryString;
        }
    }
}
