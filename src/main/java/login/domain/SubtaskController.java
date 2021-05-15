package login.domain;

import java.util.ArrayList;

public class SubtaskController {


    private DataFacade facade = null;

    public SubtaskController(DataFacade facade) {
        this.facade = facade;
    }

    public Subtask createSubtask(String task_name, int hours, double cost, String employees, Integer project_id) {
        Subtask subtask = new Subtask(task_name, hours, cost, employees);
        facade.createSubtask(subtask,project_id);
        return subtask;
    }
    public Subtask getSubtask(String task_name){
        return this.facade.getSubtask(task_name);
    }

    public ArrayList<Subtask> getSubtaskList(Integer project_id){
        return this.facade.getSubtaskList(project_id);
    }

}
