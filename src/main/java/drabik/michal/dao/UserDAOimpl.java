package drabik.michal.dao;

import drabik.michal.entity.Order;
import drabik.michal.entity.Review;
import drabik.michal.entity.Role;
import drabik.michal.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOimpl implements UserDAO {

    @Autowired
    private SessionFactory factory;

    public UserDAOimpl() {
        System.out.println("dao impl created");
    }

    public void addUser(User user) {
        factory.getCurrentSession().save(user);
    }

    public User getUser(long id) {
        return factory.getCurrentSession().get(User.class, id);
    }

    @Override
    public User getUser(String username) {
        Session session = factory.getCurrentSession();
        Query<User> query = session.createQuery("from User u where u.username=:username");
        query.setParameter("username", username);
        User user = new User();
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    public List<User> getAllUsers() {
        Query<User> query = factory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<Role> getRolesForUser(long userId) {
        Query<Role> query = factory.getCurrentSession().createQuery("from Role r where r.users.id=:id");
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrdersForUser(long userId) {
        Query<Order> query = factory.getCurrentSession().createQuery("from Order o where o.user.id=:id");
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public List<Review> getReviewsForUser(long userId) {
        Query<Review> query = factory.getCurrentSession().createQuery("from Review r where r.id=:id");
        query.setParameter("id", userId);
        return query.getResultList();
    }

    public void deleteUser(long id) {
        Session session = factory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }

    public void updateUser(User user) {
        factory.getCurrentSession().update(user);
    }
}
