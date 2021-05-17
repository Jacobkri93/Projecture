package login.domain;

import java.util.ArrayList;

public interface DataFacade {

    User login(String email, String password) throws LoginSampleException;
    User createUser(User user) throws LoginSampleException;
    Project createProject(Project project, User user);
    Subtask createSubtask(Subtask subtask,int project_id);
    Subtask getSubtask(String task_name, int project_id);
    ArrayList<Subtask> getSubtaskList(int project_id);
    Project addToList(User user, Subtask subtask, int project_id);
    Project getProjectNew(Integer project_id);
    ArrayList<Project> getProjectList(User user);
    ArrayList<Role> getRoles();
    ArrayList<SubtaskRole> getSubtaskRole();

}