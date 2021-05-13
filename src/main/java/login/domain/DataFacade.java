package login.domain;

public interface DataFacade {

    public User login(String email, String password) throws LoginSampleException;
    public User createUser(User user) throws LoginSampleException;
    public Project createProject(Project project, User user);
    public Project getProject(int project_id, String project_name, int week_duration);
    public Subtask createSubtask(Subtask subtask, Project project);
    public Project getSubtask(String subtask_name, int hours, double cost, String employees);

}