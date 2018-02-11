package drabik.michal.service;

import drabik.michal.dao.UserDetailsDAO;
import drabik.michal.entity.User;
import drabik.michal.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsDAO dao;

    @Transactional
    @Override
    public void addUserDetails(UserDetails details) {
        dao.addUserDetails(details);
    }

    @Transactional
    @Override
    public UserDetails getUserDetailsForUser(long id) {
        return dao.getUserDetailsForUser(id);
    }

    @Transactional
    @Override
    public List<UserDetails> getAllUserDetails() {
        return dao.getAllUserDetails();
    }

    @Transactional
    @Override
    public User getUserForUserDetails(long userDetailsId) {
        return dao.getUserForUserDetails(userDetailsId);
    }

    @Transactional
    @Override
    public void deleteUserDetails(long id) {
        dao.deleteUserDetails(id);
    }

    @Transactional
    @Override
    public void updateUserDetails(UserDetails details) {
        dao.updateUserDetails(details);
    }
}
