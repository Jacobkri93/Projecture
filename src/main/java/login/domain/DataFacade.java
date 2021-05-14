package login.domain;

public interface DataFacade {

    public User login(String email, String password) throws LoginSampleException;
    public User createUser(User user) throws LoginSampleException;
    public Project createProject(Project project, User user);
    public Subtask createSubtask(Subtask subtask);
    public Subtask createSubtask(Subtask subtask,Integer project_id);
    public Subtask getSubtask(String subtask_name, int hours, double cost, String employees,Integer project_id);
    public Project addToList(User user, Subtask subtask);
    Project getProject(User user);
}