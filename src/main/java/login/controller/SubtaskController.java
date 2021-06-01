package login.controller;

import login.data.SubtaskMapper;
import login.data.SubtaskRoleMapper;
import login.domain.SubTaskRoleViewModel;
import login.domain.Subtask;

import java.util.ArrayList;

//Ansvarlig: Jacob + Patrick
public class SubtaskController {


    private SubtaskMapper subtaskMapper;
    private SubtaskRoleMapper subtaskRoleMapper;

    //Constructor brugt til at instantiere objekter
    public SubtaskController() {
        this.subtaskMapper = new SubtaskMapper();
        this.subtaskRoleMapper = new SubtaskRoleMapper();
    }

    //Opretter ny subtask og returnere subtask objektet
    public Subtask createSubtask(String task_name, int project_id) {
        Subtask subtask = new Subtask(task_name);
        subtaskMapper.createSubtask(subtask,project_id);
        return subtask;
    }
    //Metode der bruges til at hente en subtask og dens værdier fra databasen
    public Subtask getSubtask(String task_name, int project_id){

        return this.subtaskMapper.getSubtask(task_name, project_id);
    }
    /*Henter en liste af subtask, og returnerer denne liste:
    Metoden opretter en Arrayliste af Subtask og kalder den subtasks -> Henter projekt id den tilhører, og løber alle subtask igennem der er på dette projekt
    for loopet bruges til at løbe størrelsen af Arraylisten igennem, og placere ressourcer (Timer pr. udvikler) på den specifikke subtask, med linje 44+45*/
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

}