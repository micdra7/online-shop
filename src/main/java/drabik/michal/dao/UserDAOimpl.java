package drabik.michal.dao;

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
    private SessionFactory sessionFactory;

    public UserDAOimpl() {
        System.out.println("dao impl created");
    }

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUser(long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    public List<User> getAllUsers() {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from users");
        return query.getResultList();
    }

    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }

    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
