package login.domain;

public class ProjectController {

    private DataFacade facade = null;

    public ProjectController(DataFacade facade) {
        this.facade = facade;
    }


    public Project createProject(String project_name, User user) {
        Project project = new Project(project_name);
        facade.createProject(project, user );
        return project;
    }
}
