package login.domain;

public class Project {
    private int project_id;
    private String project_name;
    private String week_duration;


    public Project(String project_name, String week_duration) {
        this.project_name = project_name;
        this.week_duration = week_duration;
    }




    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getWeek_duration() {
        return week_duration;
    }

    public void setWeek_duration(String week_duration) {
        this.week_duration = week_duration;
    }



    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }




}
