package login.controller;

import login.data.ProjectMapper;
import login.domain.Project;
import login.domain.Subtask;
import login.domain.User;

import java.util.ArrayList;

public class ProjectController {

    private ProjectMapper projectMapper;


    public ProjectController() {
        this.projectMapper = new ProjectMapper();
    }

    public Project createProject(String project_name, int week_duration, User user) {
        Project project = new Project(project_name, week_duration);
        projectMapper.createProject(project, user );
        return project;
    }

    public Project getProjectNew(Integer project_id) {
        return this.projectMapper.getProjectNew(project_id);
    }

    public ArrayList<Project> getProject(User user){
        return this.projectMapper.getProjectList(user);
    }

}
