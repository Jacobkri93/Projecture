package login.controller;

import login.data.SubtaskRoleMapper;
//Ansvarlig: Jacob
public class SubtaskRoleController {

    private SubtaskRoleMapper subtaskRoleMapper;

    public SubtaskRoleController() {
        this.subtaskRoleMapper = new SubtaskRoleMapper();
    }

    //Opretter en subtask role til subtaskrole tabellen i DB, og tildeler denne til en subtask
    public void createSubtaskRole(int subtask_id, int role_id, int hours) {
        this.subtaskRoleMapper.createSubtaskRole(subtask_id, role_id, hours);
    }
}
