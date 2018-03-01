package drabik.michal.service;

import drabik.michal.entity.Product;
import drabik.michal.entity.Review;
import drabik.michal.entity.User;

import java.util.List;

public interface ReviewService {
    void addReview(Review review);
    Review getReview(long id);
    Review getReviewForUserAndProduct(long userId, long productId);
    List<Review> getAllReviews();
    User getUserForReview(long reviewId);
    Product getProductForReview(long reviewId);
    void deleteReview(long reviewId);
    void updateReview(Review review);
}
