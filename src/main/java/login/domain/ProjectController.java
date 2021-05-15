package login.domain;

public class ProjectController {

    private DataFacade facade = null;

    public ProjectController(DataFacade facade) {
        this.facade = facade;
    }


    public Project createProject(String project_name, int week_duration, User user) {
        Project project = new Project(project_name, week_duration);
        facade.createProject(project, user );
        return project;
    }

    public Project getProjectNew(Integer project_id) {
        return this.facade.getProjectNew(project_id);
    }
    public Project getProject(User user){
        return this.facade.getProject(user);
    }

    public Project addToProject(User user, Subtask subtask, Integer project_id) {
        return this.facade.addToList(user,subtask,project_id);
    }


}
