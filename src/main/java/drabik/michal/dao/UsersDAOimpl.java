package drabik.michal.dao;

import drabik.michal.entity.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDAOimpl implements UsersDAO{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    public UsersDAOimpl() {
        System.out.println("dao impl created");
    }

    public void addUser(Users user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public Users getUser(long id) {
        return sessionFactory.getCurrentSession().get(Users.class, id);
    }

    public List<Users> getAllUsers() {
        Query<Users> query = sessionFactory.getCurrentSession().createQuery("from users");
        return query.getResultList();
    }

    public void deleteUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        Users user = session.get(Users.class, id);
        session.delete(user);
    }

    public void updateUser(Users user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
