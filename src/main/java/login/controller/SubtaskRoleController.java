package login.controller;

import login.data.SubtaskRoleMapper;
import login.domain.Role;
import login.domain.Subtask;

import java.util.ArrayList;

public class SubtaskRoleController {

    private SubtaskRoleMapper subtaskRoleMapper;

    public SubtaskRoleController() {
        this.subtaskRoleMapper = new SubtaskRoleMapper();
    }

    public ArrayList<Role> getRolesFromSubtask(Subtask subtask){
        return this.subtaskRoleMapper.getRolesFromSubtask(subtask);
    }
}
