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


    public Subtask createSubtask(Subtask subtask, Integer project_id) {
        subtaskMapper.createSubtask(subtask, project_id);
        return subtask;
    }

    @Override
    public  Subtask getSubtask(String subtask_name, int hours, double cost, String employees, Integer project_id) {
       return subtaskMapper.getSubtask(subtask_name);
    }

    @Override
    public Project addToList(User user, Subtask subtask) {
       return projectMapper.addSubtaskToProject(user, subtask);
    }

    @Override
    public Project getProject(User user) {
        return projectMapper.getProject(user);
    }


    @Override
    public Project createProject(Project project, User user) {
        projectMapper.createProject(project, user);
        return project;
    }

    @Override
    public Subtask createSubtask(Subtask subtask) {
        return null;
    }


}


