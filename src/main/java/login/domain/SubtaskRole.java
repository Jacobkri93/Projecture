package login.domain;

import java.util.ArrayList;

//Ansvarlig: Jacob
public class SubtaskRole {
    private int id;
    private double hours;
    private int subtask_id;
    private int role_id;

    private ArrayList<Subtask> subtaskList;

    public SubtaskRole(double hours, int subtask_id, int role_id) {
        this.hours = hours;
        this.subtask_id = subtask_id;
        this.role_id = role_id;
    }
    public SubtaskRole() {
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

    public int getrole_id() {
        return role_id;
    }

    public void setrole_id(int taskrole) {
        this.role_id = taskrole;
    }

    public void setSubtaskList(ArrayList<Subtask> subtaskList){
        this.subtaskList = subtaskList;
    }

    public ArrayList<Subtask> getSubtaskList() {
        return subtaskList;
    }
}
