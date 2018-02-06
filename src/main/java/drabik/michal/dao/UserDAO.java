package drabik.michal.dao;

import drabik.michal.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUser(long id);
    User getUser(String username);
    List<User> getAllUsers();
    void deleteUser(long id);
    void updateUser(User user);
}
