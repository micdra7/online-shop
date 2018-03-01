package drabik.michal.service;

import drabik.michal.dao.ReviewDAO;
import drabik.michal.entity.Product;
import drabik.michal.entity.Review;
import drabik.michal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDAO dao;

    @Transactional
    @Override
    public void addReview(Review review) {
        dao.addReview(review);
    }

    @Transactional
    @Override
    public Review getReview(long id) {
        return dao.getReview(id);
    }

    @Transactional
    @Override
    public Review getReviewForUserAndProduct(long userId, long productId) {
        return dao.getReviewForUserAndProduct(userId, productId);
    }

    @Transactional
    @Override
    public List<Review> getAllReviews() {
        return dao.getAllReviews();
    }

    @Transactional
    @Override
    public User getUserForReview(long reviewId) {
        return dao.getUserForReview(reviewId);
    }

    @Transactional
    @Override
    public Product getProductForReview(long reviewId) {
        return dao.getProductForReview(reviewId);
    }

    @Transactional
    @Override
    public void deleteReview(long reviewId) {
        dao.deleteReview(reviewId);
    }

    @Transactional
    @Override
    public void updateReview(Review review) {
        dao.updateReview(review);
    }
}
