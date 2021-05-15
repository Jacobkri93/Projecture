package login.data;

import login.domain.*;

import java.util.ArrayList;

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

    public  Subtask getSubtask(String subtask_name) {
       return subtaskMapper.getSubtask(subtask_name);
    }


    public ArrayList<Subtask> getSubtaskList(Integer project_id) {
        return subtaskMapper.getSubtaskList(project_id);
    }


    public Project addToList(User user, Subtask subtask, Integer project_id) {
       return projectMapper.addSubtaskToProject(user, subtask,project_id);
    }


    public Project getProjectNew(Integer project_id) {
        return projectMapper.getProjectNew(project_id);
    }
    public Project getProject(User user){
        return  projectMapper.getProject(user);
    }


    @Override
    public Project createProject(Project project, User user) {
        projectMapper.createProject(project, user);
        return project;
    }

}


