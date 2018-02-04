package drabik.michal.dao;

import drabik.michal.entity.Users;

import java.util.List;

public interface UsersDAO {
    void addUser(Users user);
    Users getUser(long id);
    List<Users> getAllUsers();
    void deleteUser(long id);
    void updateUser(Users user);
}
