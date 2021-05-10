package login.domain;

public class Project {

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

//    public Project(int user_id, String project_name, int week_duration) {
//        this.user_id = user_id;
//        this.project_name = project_name;
//        this.week_duration = week_duration;
//    }

    private int user_id;
    private String project_name;
    private int week_duration;

}
