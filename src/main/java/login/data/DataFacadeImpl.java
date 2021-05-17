package login.data;

import login.domain.*;

import java.util.ArrayList;

public class DataFacadeImpl implements DataFacade {
    private UserMapper userMapper = new UserMapper();
    private ProjectMapper projectMapper = new ProjectMapper();
    private SubtaskMapper subtaskMapper = new SubtaskMapper();
    private RoleMapper roleMapper = new RoleMapper();
    private SubtaskRoleMapper subtaskRoleMapper = new SubtaskRoleMapper();



    public User login(String email, String password) throws LoginSampleException {
        return userMapper.login(email, password);
    }

    public User createUser(User user) throws LoginSampleException {
        userMapper.createUser(user);
        return user;
    }

    public ArrayList<Subtask> getSubtaskList(Integer project_id) {
        return subtaskMapper.getSubtaskList(project_id);
    }

    public Project addToList(User user, Subtask subtask, Integer project_id) {
        return projectMapper.addSubtaskToProject(user, subtask, project_id);
    }


    public Project getProjectNew(Integer project_id) {
        return projectMapper.getProjectNew(project_id);
    }

    public ArrayList<Project> getProjectList(User user) {
        ArrayList<Project> projectList = projectMapper.getProject(user);
        subtaskMapper.setProjectSubtask(projectList);

        roleMapper.getRoles();
//       Her skal subtaskroles mappes til subtasks
//        Her skal roles mappes til subtaskroles
        return projectList;
    }

    @Override
    public ArrayList<Role> getRoles() {
        return this.roleMapper.getRoles();
    }


    public SubtaskRole getSubtaskRole(Role role) {
        return this.subtaskRoleMapper.getSubtaskRole(role);
    }


    @Override
    public Project createProject(Project project, User user) {
        projectMapper.createProject(project, user);
        return project;
    }


    public Subtask createSubtask(Subtask subtask, int project_id) {
        subtaskMapper.createSubtask(subtask, project_id);
        return subtask;
    }


    public Subtask getSubtask(String task_name, int project_id) {
        return subtaskMapper.getSubtask(task_name, project_id);
    }

    @Override
    public ArrayList<Subtask> getSubtaskList(int project_id) {
        return null;
    }

    @Override
    public Project addToList(User user, Subtask subtask, int project_id) {
        return null;
    }

}


