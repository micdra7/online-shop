package drabik.michal.dao;

import drabik.michal.entity.Role;
import drabik.michal.entity.User;

import java.util.List;

public interface RoleDAO {
    void addRole(Role role);
    Role getRole(int id);
    List<Role> getAllRoles();
    List<User> getUsersForRole(int roleId);
    void deleteRole(int id);
    void updateRole(Role role);
}
