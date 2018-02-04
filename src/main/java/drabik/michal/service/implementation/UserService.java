package drabik.michal.service.implementation;

import drabik.michal.dao.UsersDAO;
import drabik.michal.entity.Users;
import drabik.michal.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UsersService {

    @Autowired
    @Qualifier("usersDAOimpl")
    private UsersDAO dao;

    public UserService() {
        System.out.println("service created");
    }

    public void addUser(Users user) {
        dao.addUser(user);
    }

    public Users getUser(long id) {
        return dao.getUser(id);
    }

    public List<Users> getAllUsers() {
        return dao.getAllUsers();
    }

    public void deleteUser(long id) {
        dao.deleteUser(id);
    }

    public void updateUser(Users user) {
        dao.updateUser(user);
    }
}
