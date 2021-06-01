package login.controller;

import login.data.RoleMapper;
import login.domain.Role;

import java.util.ArrayList;
//Ansvarlig: Jacob
public class RoleController {

    private RoleMapper roleMapper;

    //Constructor brugt til at instantiere objekter

    public RoleController() {
        this.roleMapper = new RoleMapper();
    }

    public ArrayList<Role> getRoles() {

        return roleMapper.getRoles();
    }
}
