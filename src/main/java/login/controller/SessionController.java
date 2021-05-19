package login.controller;

import login.data.ProjectMapper;
import login.data.RoleMapper;
import login.data.UserMapper;
import login.domain.Project;
import login.domain.Subtask;
import login.domain.User;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

public class SessionController {
    private ProjectMapper projectMapper;
    private RoleMapper roleMapper;

    public SessionController() {
        this.projectMapper = new ProjectMapper();
        this.roleMapper = new RoleMapper();
    }

    public void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("project_list", projectMapper.getProject(user),WebRequest.SCOPE_SESSION);
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("role", user, WebRequest.SCOPE_SESSION);
        //laver med crof
//        ArrayList<Project> projectsx = projectController.getProject(user);
//        request.setAttribute("projects", projectController.getProject(user), WebRequest.SCOPE_SESSION);
    }

    public void setSessionInfoForProject(WebRequest request, Project project) {
        // Place project info on session
        request.setAttribute("name", project.getProject_name(), WebRequest.SCOPE_SESSION);
        request.setAttribute("week_duration", project.getWeek_duration(), WebRequest.SCOPE_SESSION);
        request.setAttribute("project", project, WebRequest.SCOPE_SESSION);
        request.setAttribute("project_id", project.getProjectId(), WebRequest.SCOPE_SESSION);



//        request.setAttribute("roles", this.roleController.getRoles(), WebRequest.SCOPE_SESSION);
//        added roles

    }
    public void setSessionInfoForSubtask(WebRequest request, User user, ArrayList<Subtask> list, String project_name) {
        //Place subtask info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("project", project_name, WebRequest.SCOPE_SESSION);
        request.setAttribute("subtasks", list, WebRequest.SCOPE_SESSION);
        request.setAttribute("roles", roleMapper.getRoles(), WebRequest.SCOPE_SESSION);
//        request.setAttribute("roles", this.roleController.getRoles(), WebRequest.SCOPE_SESSION);
    }
}
