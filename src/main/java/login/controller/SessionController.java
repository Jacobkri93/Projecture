package login.controller;

import login.data.ProjectMapper;
import login.data.RoleMapper;
import login.data.SubtaskMapper;
import login.data.SubtaskRoleMapper;
import login.domain.Project;
import login.domain.SubTaskRoleViewModel;
import login.domain.Subtask;
import login.domain.User;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
//Ansvarlig: Mads + Peter
public class SessionController {
    private ProjectMapper projectMapper;
    private RoleMapper roleMapper;
    private SubtaskMapper subtaskMapper;
    private SubtaskRoleMapper subtaskRoleMapper;

    //Constructor brugt til at instantiere objekter
    public SessionController() {
        this.projectMapper = new ProjectMapper();
        this.roleMapper = new RoleMapper();
        this.subtaskMapper = new SubtaskMapper();
        this.subtaskRoleMapper = new SubtaskRoleMapper();

    }

    public void setSessionInfo(WebRequest request, User user) {
        // Placerer user info i en session
        ArrayList<Project> list = projectMapper.getProject(user);

        //Placerer listen af projekter i alfabetisk rækkefølge i browseren
        Collections.sort(list, Comparator.comparing(Project::getProject_name));

        //setAttribute bruges til at store information i session
        request.setAttribute("project_list", list,WebRequest.SCOPE_SESSION);
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("role", user, WebRequest.SCOPE_SESSION);
        //WebRequest.SCOPE_SESSION bruges til at indikere den lokale session, hvis sådan en er tilgængelig
    }

    public void setSessionInfoForProject(WebRequest request, Project project) {
        // Placerer project info i en session
        request.setAttribute("name", project.getProject_name(), WebRequest.SCOPE_SESSION);
        request.setAttribute("week_duration", project.getWeek_duration(), WebRequest.SCOPE_SESSION);
        request.setAttribute("project", project, WebRequest.SCOPE_SESSION);
        request.setAttribute("project_id", project.getProjectId(), WebRequest.SCOPE_SESSION);

    }
    public void setSessionInfoForSubtask(WebRequest request, User user, ArrayList<Subtask> list, Project project) {
        //Placerer subtask info i en session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
        request.setAttribute("project", project, WebRequest.SCOPE_SESSION);
        request.setAttribute("subtasks", list, WebRequest.SCOPE_SESSION);
        request.setAttribute("roles", roleMapper.getRoles(), WebRequest.SCOPE_SESSION);
    }

    //Denne metode bruges til interaktion på home.html siden, at man kan gå ind på brugerens projekter
    //Man henter projektets indhold og viser det i browseren
    public void setSessionInfoFromHome(WebRequest request, int project_id){
        Project project = projectMapper.getProjectNew(project_id);
        request.setAttribute("name",project.getProject_name(),WebRequest.SCOPE_SESSION);
        request.setAttribute("week_duration", project.getWeek_duration(), WebRequest.SCOPE_SESSION);

        //Her hentes der alle subtask fra projektet - og man looper størrelsen af Arraylisten igennem og henter rollerne der er på hver subtask
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
         //For hver role i Arraylisten ganger man pris med hours og får en sum
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
