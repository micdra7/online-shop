package drabik.michal.service;

import drabik.michal.entity.User;
import drabik.michal.entity.UserDetails;

import java.util.List;

public interface UserDetailsService {
    void addUserDetails(UserDetails details);
    UserDetails getUserDetailsForUser(long id);
    List<UserDetails> getAllUserDetails();
    User getUserForUserDetails(long userDetailsId);
    void deleteUserDetails(long id);
    void updateUserDetails(UserDetails details);
}
