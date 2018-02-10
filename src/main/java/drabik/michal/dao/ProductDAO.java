package drabik.michal.dao;

import drabik.michal.entity.OrderDetails;
import drabik.michal.entity.Product;
import drabik.michal.entity.Review;
import drabik.michal.entity.Subcategory;

import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    Product getProduct(long id);
    List<Product> getAllProducts();
    List<OrderDetails> getOrderDetailsForProduct(long productId);
    Subcategory getSubcategoryForProduct(long productId);
    List<Review> getReviewsForProduct(long productId);
    void deleteProduct(long id);
    void updateProduct(Product product);
}
