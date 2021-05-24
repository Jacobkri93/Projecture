package login.controller;

import login.data.ProjectMapper;
import login.data.RoleMapper;
import login.data.SubtaskMapper;
import login.data.SubtaskRoleMapper;
import login.domain.*;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SessionController {
    private ProjectMapper projectMapper;
    private RoleMapper roleMapper;
    private SubtaskMapper subtaskMapper;
    private SubtaskRoleMapper subtaskRoleMapper;

    public SessionController() {
        this.projectMapper = new ProjectMapper();
        this.roleMapper = new RoleMapper();
        this.subtaskMapper = new SubtaskMapper();
        this.subtaskRoleMapper = new SubtaskRoleMapper();

    }

    public void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        ArrayList<Project> list = projectMapper.getProject(user);
        Collections.sort(list, (o1, o2) -> o1.getProject_name().compareTo(o2.getProject_name()));
        request.setAttribute("project_list", list,WebRequest.SCOPE_SESSION);
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
    public void setSessionInfoForSubtask(WebRequest request, User user, ArrayList<Subtask> list, Project project) {
        //Place subtask info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("project", project, WebRequest.SCOPE_SESSION);
        request.setAttribute("subtasks", list, WebRequest.SCOPE_SESSION);
        request.setAttribute("roles", roleMapper.getRoles(), WebRequest.SCOPE_SESSION);


//        request.setAttribute("roles", this.roleController.getRoles(), WebRequest.SCOPE_SESSION);
    }
    public void setSessionInfoFromHome(WebRequest request, int project_id){
        Project project = projectMapper.getProjectNew(project_id);
        request.setAttribute("name",project.getProject_name(),WebRequest.SCOPE_SESSION);
        request.setAttribute("week_duration", project.getWeek_duration(), WebRequest.SCOPE_SESSION);
        ArrayList<Subtask> subtask = subtaskMapper.getSubtaskList(project_id);
        for (int i = 0; i<subtask.size();i++){
            subtask.get(i).setSubtaskRoleList(subtaskRoleMapper.getRolesFromSubtask(subtask.get(i).getId()));
        }
        request.setAttribute("calculatedPrice",calculatePrice(subtask),WebRequest.SCOPE_SESSION);
        request.setAttribute("subtasks",subtask,WebRequest.SCOPE_SESSION);
    }

    // Vi har summeret price * hours fra vores project. Vi kommer ind med en subtask og vi looper over rolelisten fra en subtask.
    public double calculatePrice(ArrayList<Subtask> subtasks) {
        double sum = 0;
      for (Subtask x: subtasks) {
         ArrayList<SubTaskRoleViewModel> vm =  x.getSubtaskRoleList();

        for (SubTaskRoleViewModel y: vm) {
            sum += y.getPrice() * y.getHours();
         }
      }

        return sum;
    }
    public void clearSession(WebRequest request){
        request.removeAttribute("project",WebRequest.SCOPE_SESSION);
        request.removeAttribute("subtasks",WebRequest.SCOPE_SESSION);
    }


}
