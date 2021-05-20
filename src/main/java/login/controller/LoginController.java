package login.controller;

import login.data.ProjectMapper;
import login.data.UserMapper;
import login.domain.LoginSampleException;
import login.domain.Project;
import login.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
@Controller
public class LoginController {

    // UserMapper to datasource layer
    private UserMapper userMapper;
    private ProjectMapper projectMapper;
    private SessionController sessionController;

    public LoginController() {
        this.userMapper = new UserMapper();
        this.projectMapper = new ProjectMapper();
        this.sessionController = new SessionController();
    }

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws LoginSampleException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        // delegate work + data to login controller
        User user = userMapper.login(email, pwd);
        ArrayList<Project> list = projectMapper.getProject(user);
        sessionController.setSessionInfo(request, user);
        return "home";
    }

    @PostMapping("/register")
    public String createUser(WebRequest request) throws LoginSampleException {
        //Retrieve values from HTML form via WebRequest
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        // If passwords match, work + data is delegated to login controller
        if (password1.equals(password2)) {
            User user = new User(email, password1);
            userMapper.createUser(user);
            sessionController.setSessionInfo(request, user);
            return "home";

        } else { // If passwords don't match, an exception is thrown
            throw new LoginSampleException("The two passwords did not match");
        }
    }
    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "exceptionPage";
    }
}
