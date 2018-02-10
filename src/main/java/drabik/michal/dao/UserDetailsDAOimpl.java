package drabik.michal.dao;

import drabik.michal.entity.User;
import drabik.michal.entity.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsDAOimpl implements UserDetailsDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public void addUserDetails(UserDetails details) {
        factory.getCurrentSession().save(details);
    }

    @Override
    public UserDetails getUserDetails(long id) {
        return factory.getCurrentSession().get(UserDetails.class, id);
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        Query<UserDetails> query = factory.getCurrentSession().createQuery("from UserDetails");
        return query.getResultList();
    }

    @Override
    public User getUserForUserDetails(long userDetailsId) {
        Query<User> query = factory.getCurrentSession().createQuery("from User u where u.id=:id");
        query.setParameter("id", userDetailsId);
        return query.getSingleResult();
    }

    @Override
    public void deleteUserDetails(long id) {
        Session session = factory.getCurrentSession();
        UserDetails details = session.get(UserDetails.class, id);
        session.delete(details);
    }

    @Override
    public void updateUserDetails(UserDetails details) {
        factory.getCurrentSession().delete(details);
    }
}
