package login.domain;

public class SubtaskController {


    private DataFacade facade = null;

    public SubtaskController(DataFacade facade) {
        this.facade = facade;
    }

    public Subtask createSubtask(String task_name, int hours, double cost, String employees, Project project) {
        Subtask subtask = new Subtask(task_name, hours, cost, employees);
        facade.createSubtask(subtask);
        return subtask;
    }
    public Subtask getSubtask(String task_name, int hours, double cost, String employees){
        return this.facade.getSubtask(task_name,hours,cost,employees);
    }


    public Subtask createSubtask(Subtask subtask) {
        return subtask;
    }
}