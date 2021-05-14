package login.domain;

import java.util.ArrayList;

public class Project {
    private int project_id;
    private String project_name;
    private int week_duration;
    private ArrayList<Subtask> subtasklist;
    private int user_id;

    public Project(String project_name, int week_duration) {
        this.project_name = project_name;
        this.week_duration = week_duration;
    }

    public Project(int project_id, String project_name, int week_duration, ArrayList<Subtask> subtasklist, int user_id) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.week_duration = week_duration;
        this.subtasklist = subtasklist;
        this.user_id = user_id;
    }

    public Project(){
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getWeek_duration() {
        return week_duration;
    }

    public void setWeek_duration(int week_duration) {
        this.week_duration = week_duration;
    }

    public ArrayList<Subtask> getSubtasklist() {
        return subtasklist;
    }

    public void setSubtasklist(ArrayList<Subtask> subtasklist) {
        this.subtasklist = subtasklist;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
