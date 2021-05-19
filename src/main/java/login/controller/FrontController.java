package login.controller;

import login.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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

    @GetMapping(value = "/makesubtask/{project_name}")
    public String createSubtask(WebRequest request){
        //TODO sikrer korrekt reload af siden
        String project_name = request.getParameter("project_name");
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        sessionController.setSessionInfo(request, user);
        return "createProject";
    }

    @PostMapping(value = "/makesubtask")
    public String createSubtask(WebRequest request, RedirectAttributes redirectAttributes) {
        String task_name = request.getParameter("task_name");
        String developer_hours_string = request.getParameter("developer_hours");
        String senior_developer_hours_string = request.getParameter("senior_developer_hours");
        String designer_hours_string = request.getParameter("designer_hours");

        int developer_hours = developer_hours_string == null || developer_hours_string.length() < 1 ? 0 : Integer.parseInt(developer_hours_string);
        int senior_developer_hours = senior_developer_hours_string == null || senior_developer_hours_string.length() < 1 ? 0 : Integer.parseInt(senior_developer_hours_string);
        int designer_hours = designer_hours_string == null || designer_hours_string.length() < 1 ? 0 : Integer.parseInt(designer_hours_string);
        int project_id = (Integer) request.getAttribute("project_id", WebRequest.SCOPE_SESSION);

        Subtask subtask = this.subtaskController.getSubtask(task_name, project_id);

        if (subtask == null) {
            subtask = subtaskController.createSubtask(task_name, project_id);
            ArrayList<Role> roles =  roleController.getRoles();

            for(int roleIndex = 0; roleIndex < roles.size(); roleIndex++){
                Role curRole = roles.get(roleIndex);

                int curHours;
                switch (curRole.getDescription()) {
                    case "Developer":
                        curHours = developer_hours;
                        break;
                    case "Senior Developer":
                        curHours = senior_developer_hours;
                        break;
                    case "Designer":
                        curHours = designer_hours;
                        break;
                    default:
                        curHours = 0;
                        break;
                }
                subtaskRoleController.createSubtaskRole(subtask.getId(), curRole.getId(), curHours);
            }
        }
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        ArrayList<Subtask> list = subtaskController.getSubtaskList(project_id);
        Project project = (Project) request.getAttribute("project", WebRequest.SCOPE_SESSION);
        sessionController.setSessionInfoForSubtask(request, user, list, project.getProject_name());

        redirectAttributes.addAttribute("project_name", project.getProject_name());
        return "redirect:createSubtask";
    }

    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "exceptionPage";
    }
}

