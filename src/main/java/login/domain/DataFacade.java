package login.domain;

public interface DataFacade {

    public User login(String email, String password) throws LoginSampleException;
    public User createUser(User user) throws LoginSampleException;
    public Project createProject(Project project, User user);
    public Subtask createSubtask(Subtask subtask);
    public Subtask getSubtask(String subtask_name, int hours, double cost, String employees);
    public Project addToList(User user, Subtask subtask);
    Project getProject(User user);
}