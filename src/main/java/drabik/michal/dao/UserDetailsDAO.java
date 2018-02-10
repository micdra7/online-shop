package drabik.michal.dao;

import drabik.michal.entity.User;
import drabik.michal.entity.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
    void addUserDetails(UserDetails details);
    UserDetails getUserDetails(long id);
    List<UserDetails> getAllUserDetails();
    User getUserForUserDetails(long userDetailsId);
    void deleteUserDetails(long id);
    void updateUserDetails(UserDetails details);
}
