package drabik.michal.dao;

import drabik.michal.entity.Product;
import drabik.michal.entity.Review;
import drabik.michal.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDAOimpl implements ReviewDAO {
    @Autowired
    private SessionFactory factory;

    @Override
    public void addReview(Review review) {
        factory.getCurrentSession().save(review);
    }

    @Override
    public Review getReview(long id) {
        return factory.getCurrentSession().get(Review.class, id);
    }

    @Override
    public List<Review> getAllReviews() {
        Query<Review> query = factory.getCurrentSession().createQuery("from Review");
        return query.getResultList();
    }

    @Override
    public User getUserForReview(long reviewId) {
        return factory.getCurrentSession().get(Review.class, reviewId).getUser();
    }

    @Override
    public Product getProductForReview(long reviewId) {
        return factory.getCurrentSession().get(Review.class, reviewId).getProduct();
    }

    @Override
    public void deleteReview(long reviewId) {
        Session session = factory.getCurrentSession();
        Review review = session.get(Review.class, reviewId);
        session.delete(review);
    }

    @Override
    public void updateReview(Review review) {
        factory.getCurrentSession().update(review);
    }
}
