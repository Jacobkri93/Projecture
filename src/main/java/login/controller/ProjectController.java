package login.controller;

import login.data.ProjectMapper;
import login.domain.Project;
import login.domain.User;


public class ProjectController {

    private ProjectMapper projectMapper;

    //Constructor brugt til at instantiere objekter
    public ProjectController() {
        this.projectMapper = new ProjectMapper();
    }

    //Opretter nyt projekt og returnere det med navn og duration attributerne.
    public Project createProject(String project_name, int week_duration, User user) {
        Project project = new Project(project_name, week_duration);
        projectMapper.createProject(project, user );
        return project;
    }

}
