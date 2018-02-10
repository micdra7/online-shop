package drabik.michal.service;

import drabik.michal.entity.Role;
import drabik.michal.entity.User;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    Role getRole(int id);
    List<Role> getAllRoles();
    List<User> getUserForRole(int roleId);
    void deleteRole(int id);
    void updateRole(Role role);
}
