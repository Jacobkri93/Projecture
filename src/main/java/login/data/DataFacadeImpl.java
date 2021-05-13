package login.data;

import login.domain.*;

public class DataFacadeImpl implements DataFacade {
   private UserMapper userMapper = new UserMapper();
   private ProjectMapper projectMapper = new ProjectMapper();
   private SubtaskMapper subtaskMapper = new SubtaskMapper();


    public User login(String email, String password) throws LoginSampleException {
        return userMapper.login(email, password);
    }

    public User createUser(User user) throws LoginSampleException {
        userMapper.createUser(user);
        return user;
    }

    @Override
    public Subtask createSubtask(Subtask subtask, Project project) {
        subtaskMapper.createSubtask(subtask,project);
        return subtask;
    }

    @Override
    public Project getSubtask(String subtask_name, int hours, double cost, String employees) {
        return null;
    }



    @Override
    public Project createProject(Project project, User user) {
        projectMapper.createProject(project, user);
        return project;
    }

    @Override
    public Project getProject(int project_id, String project_name, int week_duration) {
        return null;
    }

}


