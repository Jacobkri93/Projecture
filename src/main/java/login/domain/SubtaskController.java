package login.domain;

import java.util.ArrayList;

public class SubtaskController {


    private DataFacade facade = null;

    public SubtaskController(DataFacade facade) {
        this.facade = facade;
    }

    public Subtask createSubtask(String task_name, int project_id) {
        Subtask subtask = new Subtask(task_name);
        facade.createSubtask(subtask,project_id);
        return subtask;
    }
    public Subtask getSubtask(String task_name, int project_id){
        return this.facade.getSubtask(task_name, project_id);
    }

    public ArrayList<Subtask> getSubtaskList(int project_id){
        return this.facade.getSubtaskList(project_id);
    }

    //Create Subtask Role

}
