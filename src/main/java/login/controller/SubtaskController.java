package login.controller;

import login.data.SubtaskMapper;
import login.data.SubtaskRoleMapper;
import login.domain.SubTaskRoleViewModel;
import login.domain.Subtask;

import java.util.ArrayList;

public class SubtaskController {


    private SubtaskMapper subtaskMapper;
    private SubtaskRoleMapper subtaskRoleMapper;

    public SubtaskController() {
        this.subtaskMapper = new SubtaskMapper();
        this.subtaskRoleMapper = new SubtaskRoleMapper();
    }

    public Subtask createSubtask(String task_name, int project_id) {
        Subtask subtask = new Subtask(task_name);
        subtaskMapper.createSubtask(subtask,project_id);
        return subtask;
    }
    public Subtask getSubtask(String task_name, int project_id){
        return this.subtaskMapper.getSubtask(task_name, project_id);
    }

    public ArrayList<Subtask> getSubtaskList(int project_id){
        ArrayList<Subtask> subtasks = this.subtaskMapper.getSubtaskList(project_id);
        for (int subtasksIndex = 0; subtasksIndex < subtasks.size(); subtasksIndex++) {
            Subtask subtask = subtasks.get(subtasksIndex);
            int subtaskId = subtask.getId();
            ArrayList<SubTaskRoleViewModel> subtaskRoles = subtaskRoleMapper.getRolesFromSubtask(subtaskId);
            subtask.setSubtaskRoleList(subtaskRoles);
            subtasks.set(subtasksIndex,subtask);
        }

        return subtasks;
    }

    //Create Subtask Role
}