package drabik.michal.service;

import drabik.michal.entity.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    Product getProduct(long id);
    List<Product> getAllProducts();
    void deleteProduct(long id);
    void updateProduct(Product product);
}
