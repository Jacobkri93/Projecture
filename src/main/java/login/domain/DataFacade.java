package login.domain;

import java.util.ArrayList;

public interface DataFacade {

    public User login(String email, String password) throws LoginSampleException;
    public User createUser(User user) throws LoginSampleException;
    public Project createProject(Project project, User user);
    public Subtask createSubtask(Subtask subtask,int project_id);
    public Subtask getSubtask(String task_name, int project_id);
    public ArrayList<Subtask> getSubtaskList(int project_id);
    public Project addToList(User user, Subtask subtask, int project_id);
    Project getProjectNew(Integer project_id);
    ArrayList<Project> getProjectList(User user);
    ArrayList<Role> getRoles();

}