package drabik.michal.dao;

import drabik.michal.entity.Role;
import drabik.michal.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOimpl implements RoleDAO {
    @Autowired
    private SessionFactory factory;

    @Override
    public void addRole(Role role) {
        factory.getCurrentSession().save(role);
    }

    @Override
    public Role getRole(int id) {
        return factory.getCurrentSession().get(Role.class, id);
    }

    @Override
    public List<Role> getAllRoles() {
        Query<Role> query = factory.getCurrentSession().createQuery("from Role");
        return query.getResultList();
    }

    @Override
    public List<User> getUsersForRole(int roleId) {
        Query<User> query = factory.getCurrentSession().createQuery("from User u where u.roles.id=:id");
        query.setParameter("id", roleId);
        return query.getResultList();
    }

    @Override
    public void deleteRole(int id) {
        Session session = factory.getCurrentSession();
        Role role = session.get(Role.class, id);
        session.delete(role);
    }

    @Override
    public void updateRole(Role role) {
        factory.getCurrentSession().update(role);
    }
}
