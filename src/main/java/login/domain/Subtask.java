package login.domain;

import java.util.ArrayList;


//Ansvarlig: Mads
public class Subtask {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String task_name;
    private int project_id;
    private ArrayList<SubTaskRoleViewModel> subtaskRoleList;


    public ArrayList<SubTaskRoleViewModel> getSubtaskRoleList() {
        return subtaskRoleList;
    }

    public void setSubtaskRoleList(ArrayList<SubTaskRoleViewModel> subtaskRoleList) {
        this.subtaskRoleList = subtaskRoleList;
    }



    public Subtask() {

    }
    public Subtask(int id, String task_name) {
        this.id = id;
        this.task_name = task_name;
    }

    public Subtask(int id, String task_name, int project_id) {
        this.id = id;
        this.task_name = task_name;
        this.project_id = project_id;
    }

    public Subtask(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + id +
                ", task_name='" + task_name + '\'' +
                '\'' +
                '}';
    }
}
