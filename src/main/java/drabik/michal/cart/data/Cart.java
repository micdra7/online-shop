package drabik.michal.cart.data;

import drabik.michal.entity.Product;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Integer> quantity = new ArrayList<>();
    private double totalPrice = 0;

    public void addToCart(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            quantity.add(1);
            totalPrice += product.getPrice();
        }
    }

    public void removeFromCart(Product product) {
        if (products.contains(product)) {
            quantity.remove(products.indexOf(product));
            products.remove(product);
        }
    }

    public void updateProductQuantity(Product product, int q) {
        if (products.contains(product)) {
            if (q < 0) {
                q = 1;
            } else if (q == 0) {
                removeFromCart(product);
            }
            quantity.set(products.indexOf(product), q);
        }
    }

    public boolean contains(Product product) {
        return products.contains(product);
    }

    public int getQuantity(Product product) {
        return quantity.get(products.indexOf(product));
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
