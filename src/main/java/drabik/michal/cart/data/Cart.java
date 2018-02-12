package drabik.michal.cart.data;

import drabik.michal.entity.Product;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<ProductData> products = Collections.synchronizedList(new ArrayList<ProductData>());
    private double totalPrice = 0;

    public static void createInstanceIfNotExisting(HttpSession session) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Cart());
        }
    }

    public void addToCart(ProductData product) {
        synchronized (products) {
            boolean contains = false;
            for (ProductData data : products) {
                if (data.getId().equals(product.getId())) {
                    contains = true;
                }
            }

            if (!contains) {
                product.setSelectedQuantity(1);
                products.add(product);
                updateTotalPrice();
            }
        }
    }

    public void removeFromCart(ProductData product) {
        int index = -1;
        synchronized (products) {
            for (ProductData data : products) {
                if (data.getId().equals(product.getId())) {
                    index = products.indexOf(data);
                }
            }
        }
        if (index != -1) {
            products.remove(index);
        }
        updateTotalPrice();
    }

    public void updateProductQuantity(long id, int q) {
        synchronized (products) {
            for (ProductData data : products) {
                if (data.getId().equals(id)) {
                    data.setSelectedQuantity(q);
                }
            }
        }
        updateTotalPrice();
    }

    public boolean contains(ProductData product) {
        synchronized (product) {
            for (Product p : products) {
                if (p.getName().equals(product.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateTotalPrice() {
        this.totalPrice = 0d;
        synchronized (products) {
            for (ProductData productData : products) {
                this.totalPrice += productData.getPrice()*productData.getSelectedQuantity();
            }
        }
    }

    public List<ProductData> getProducts() {
        return products;
    }

    public void setProducts(List<ProductData> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
