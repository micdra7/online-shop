package drabik.michal.service;

import drabik.michal.dao.UserDAO;
import drabik.michal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements drabik.michal.service.UserService {

    @Autowired
    private UserDAO dao;

    @Transactional
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Transactional
    public User getUser(long id) {
        return dao.getUser(id);
    }

    @Transactional
    public User getUser(String username) {
        return dao.getUser(username);
    }

    @Transactional
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Transactional
    public void deleteUser(long id) {
        dao.deleteUser(id);
    }

    @Transactional
    public void updateUser(User user) {
        dao.updateUser(user);
    }
}
