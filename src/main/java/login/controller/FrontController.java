package login.controller;

import login.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class FrontController {

    // use case controller (GRASP Controller) - injects concrete facade instance into controller
    private LoginController loginController = new LoginController();
    private ProjectController projectController = new ProjectController();
    private SubtaskController subtaskController = new SubtaskController();
    private RoleController roleController = new RoleController();
    private SubtaskRoleController subtaskRoleController = new SubtaskRoleController();
    private SessionController sessionController = new SessionController();

    //Getmapping når vi skal have noget fra serveren. Betyder også html siderne.
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @PostMapping(value = "/makeproject")
    public String createProject(WebRequest request) {
        String project_name = request.getParameter("name");
        int week_duration = Integer.valueOf(request.getParameter("week_duration"));
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Project list = projectController.createProject(project_name, week_duration, user);
        sessionController.setSessionInfoForProject(request, list);
        return "createProject";
    }


    @PostMapping(value = "/makesubtask")
    public String createSubtask(WebRequest request) {
        String task_name = request.getParameter("task_name");
        Integer project_id = (Integer) request.getAttribute("project_id", WebRequest.SCOPE_SESSION);
//        String description = request.getParameter("description");
        int hours = Integer.valueOf(request.getParameter("hours"));
        Subtask subtask = this.subtaskController.getSubtask(task_name, project_id);
//        SubtaskRole subtaskRole = this.subtaskRoleController.getRolesFromSubtask(SubtaskRole);
        if (subtask == null) {
            subtask = subtaskController.createSubtask(task_name, project_id);
//            Kommentar: Her skal vi oprette ny subtask role:
//            Ex.
//            new Subtaskrole(hours, subtask.getId(),employees)
        }
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        ArrayList<Subtask> list = subtaskController.getSubtaskList(project_id);
        Project project = (Project) request.getAttribute("project", WebRequest.SCOPE_SESSION);
        sessionController.setSessionInfoForSubtask(request, user, list, project.getProject_name());
        return "createProject";
    }

    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "exceptionPage";
    }
}