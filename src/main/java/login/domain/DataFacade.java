package login.domain;

import java.util.ArrayList;

public interface DataFacade {

    public User login(String email, String password) throws LoginSampleException;
    public User createUser(User user) throws LoginSampleException;
    public Project createProject(Project project, User user);
    public Subtask createSubtask(Subtask subtask,Integer project_id);
    public Subtask getSubtask(String subtask_name);
    public ArrayList<Subtask> getSubtaskList(Integer project_id);
    public Project addToList(User user, Subtask subtask, Integer project_id);
    Project getProjectNew(Integer project_id);
    Project getProject(User user);

}