package login.domain;

public class SubtaskRole {
    private int id;
    private double hours;
    private int subtask_id;
    private int taskrole;

    public SubtaskRole(double hours, int subtask_id, int taskrole) {
        this.hours = hours;
        this.subtask_id = subtask_id;
        this.taskrole = taskrole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public int getSubtask_id() {
        return subtask_id;
    }

    public void setSubtask_id(int subtask_id) {
        this.subtask_id = subtask_id;
    }

    public int getTaskrole() {
        return taskrole;
    }

    public void setTaskrole(int taskrole) {
        this.taskrole = taskrole;
    }
}
