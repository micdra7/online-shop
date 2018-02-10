package drabik.michal.dao;

import drabik.michal.entity.Order;
import drabik.michal.entity.Review;
import drabik.michal.entity.Role;
import drabik.michal.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUser(long id);
    User getUser(String username);
    List<User> getAllUsers();
    List<Role> getRolesForUser(long userId);
    List<Order> getOrdersForUser(long userId);
    List<Review> getReviewsForUser(long userId);
    void deleteUser(long id);
    void updateUser(User user);
}
