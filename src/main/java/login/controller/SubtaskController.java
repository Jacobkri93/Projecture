package login.controller;

import login.data.SubtaskMapper;
import login.domain.Subtask;

import java.util.ArrayList;

public class SubtaskController {


    private SubtaskMapper subtaskMapper;

    public SubtaskController() {
        this.subtaskMapper = new SubtaskMapper();
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
        return this.subtaskMapper.getSubtaskList(project_id);
    }

    //Create Subtask Role
}