package login.controller;

import login.data.RoleMapper;
import login.domain.Role;

import java.util.ArrayList;

public class RoleController {

    private RoleMapper roleMapper;

    public RoleController() {
        this.roleMapper = new RoleMapper();
    }

    public ArrayList<Role> getRoles() {

        return roleMapper.getRoles();
    }
}
