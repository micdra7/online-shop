package drabik.michal.service;

import drabik.michal.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    User getUser(long id);
    List<User> getAllUsers();
    void deleteUser(long id);
    void updateUser(User user);
}
