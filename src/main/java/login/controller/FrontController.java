package login.controller;

import login.data.DataFacadeImpl;
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

    //use case controller (GRASP Controller) - injects concrete facade instance into controller
    private LoginController loginController = new LoginController(new DataFacadeImpl());
    private ProjectController projectController = new ProjectController(new DataFacadeImpl());
    private SubtaskController subtaskController = new SubtaskController(new DataFacadeImpl());
    private RoleController roleController = new RoleController(new DataFacadeImpl());


    //Getmapping når vi skal have noget fra serveren. Betyder også html siderne.
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws LoginSampleException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        // delegate work + data to login controller
        User user = loginController.login(email, pwd);
        ArrayList<Project> list = projectController.getProject(user);
        setSessionInfo(request, user);


        return "home";
    }

    //postmapping når vi skal give noget til serveren.
    @PostMapping("/register")
    public String createUser(WebRequest request) throws LoginSampleException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        // If passwords match, work + data is delegated to login controller
        if (password1.equals(password2)) {
            User user = loginController.createUser(email, password1);
            setSessionInfo(request, user);

            //
            return "home";

        } else { // If passwords don't match, an exception is thrown
            throw new LoginSampleException("The two passwords did not match");
        }
    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("project_list", projectController.getProject(user),WebRequest.SCOPE_SESSION);
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("role", user, WebRequest.SCOPE_SESSION);
    }

    private void setSessionInfoForProject(WebRequest request, Project project, User user) {
        // Place project info on session
        request.setAttribute("name", project.getProject_name(), WebRequest.SCOPE_SESSION);
        request.setAttribute("week_duration", project.getWeek_duration(), WebRequest.SCOPE_SESSION);
        request.setAttribute("project", project, WebRequest.SCOPE_SESSION);
        request.setAttribute("project_id", project.getProjectId(), WebRequest.SCOPE_SESSION);

//        added roles
        request.setAttribute("roles", this.roleController.getRoles(), WebRequest.SCOPE_SESSION);
        //Før var det project - nu project.getProject_name()

    }


    //vi pegede på et object og forventede en string.
    @PostMapping(value = "/makeproject")
    public String createProject(WebRequest request) {
        String project_name = request.getParameter("name");
        int week_duration = Integer.valueOf(request.getParameter("week_duration"));
        //  Project project = projectController.createProject(project_name, user);
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        Project list = projectController.createProject(project_name, week_duration, user);
        setSessionInfoForProject(request, list, user);


        return "createProject";
    }


//    private void setSessionInfoForSubtask(WebRequest request, Subtask subtask, Project project) {
//
//        request.setAttribute("task_name", subtask.getTask_name(), WebRequest.SCOPE_SESSION);
//        request.setAttribute("hours", subtask.getHours(), WebRequest.SCOPE_SESSION);
//        request.setAttribute("cost", subtask.getCost(), WebRequest.SCOPE_SESSION);
//        request.setAttribute("employees", subtask.getEmployees(), WebRequest.SCOPE_SESSION);
//    }

    private void setSessionInfoForSubtask(WebRequest request, User user, ArrayList<Subtask> list, String project_name) {

        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("project", project_name, WebRequest.SCOPE_SESSION);
        request.setAttribute("subtasks", list, WebRequest.SCOPE_SESSION);


    }

    @PostMapping(value = "/makesubtask")
    public String createSubtask(WebRequest request) {
        String task_name = request.getParameter("task_name");
        Integer hours = Integer.valueOf(request.getParameter("hours"));
        //Double cost = Double.valueOf(request.getParameter("cost"));
        Double cost = 0.0;
        String employees = request.getParameter("employees");

        Integer project_id = (Integer) request.getAttribute("project_id", WebRequest.SCOPE_SESSION);
        Subtask subtask = this.subtaskController.getSubtask(task_name, project_id);
        if (subtask == null) {

            subtask = subtaskController.createSubtask(task_name, project_id);
//            Kommentar: Her skal vi oprette ny subtask role:
//            Ex.
//            new Subtaskrole(hours, subtask.getId(),employees)
        }
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        ArrayList<Subtask> list = subtaskController.getSubtaskList(project_id);
        Project project = (Project) request.getAttribute("project", WebRequest.SCOPE_SESSION);
        setSessionInfoForSubtask(request, user, list, project.getProject_name());

        return "createProject";
    }


    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "exceptionPage";
    }
}