package drabik.michal.service;

import drabik.michal.dao.RoleDAO;
import drabik.michal.entity.Role;
import drabik.michal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO dao;

    @Transactional
    @Override
    public void addRole(Role role) {
        dao.addRole(role);
    }

    @Transactional
    @Override
    public Role getRole(int id) {
        return dao.getRole(id);
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return dao.getAllRoles();
    }

    @Transactional
    @Override
    public List<User> getUserForRole(int roleId) {
        return dao.getUsersForRole(roleId);
    }

    @Transactional
    @Override
    public void deleteRole(int id) {
        dao.deleteRole(id);
    }

    @Transactional
    @Override
    public void updateRole(Role role) {
        dao.updateRole(role);
    }
}
