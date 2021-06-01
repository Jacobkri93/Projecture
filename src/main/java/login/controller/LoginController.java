package login.controller;

import login.data.ProjectMapper;
import login.data.UserMapper;
import login.domain.LoginSampleException;
import login.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

//Ansvarlig: ALLE
@Controller
public class LoginController {

    // UserMapper to datasource layer
    private UserMapper userMapper;
    private ProjectMapper projectMapper;
    private SessionController sessionController;


    //Constructor brugt til at instansiere objekter
    public LoginController() {
        this.userMapper = new UserMapper();
        this.projectMapper = new ProjectMapper();
        this.sessionController = new SessionController();
    }

    //PostMapping bruges til at poste til HTTP - for login, tager "email" og "password" fra WebRequest og via login(linje: 26) metoden fra UserMapper klassen.

    @PostMapping("/login")
    public String loginUser(WebRequest request) throws LoginSampleException {
        //Henter værdier fra HTML form via WebRequest
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        //login metoden hentes fra UserMapper
        User user = userMapper.login(email, pwd);
        //setSessionInfo gemmer informationen der skal bruges til metoden
        sessionController.setSessionInfo(request, user);

        //Returnere HTML "home" fra ressources/templates
        return "home";
    }


    //Metode til at registere ny bruger, og gemme i databasen - som efterfølgende kan bruge loginUser metoden ovenover.
    @PostMapping("/register")
    public String createUser(WebRequest request) throws LoginSampleException {
        //Henter værdier fra HTML form via WebRequest
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        // If sætning til passwords match
        if (password1.equals(password2)) {
            User user = new User(email, password1);
            userMapper.createUser(user);
            sessionController.setSessionInfo(request, user);
            return "home";

        } else { // else sætning til passwords mismatch, en exception smides med teksten.
            throw new LoginSampleException("The two passwords did not match");
        }
    }

    //exception håndtering - returnere exception html siden med error tekst.
    @ExceptionHandler(Exception.class)
    public String anotherError(Model model, Exception exception) {
        model.addAttribute("message", exception.getMessage());
        return "exceptionPage";
    }
}
