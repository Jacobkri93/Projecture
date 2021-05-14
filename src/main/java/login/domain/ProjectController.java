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

    public Project getProject(User user) {
        return this.facade.getProject(user);
    }

    public Project addToProject(User user, Subtask subtask) {
        return this.facade.addToList(user,subtask);
    }
}
