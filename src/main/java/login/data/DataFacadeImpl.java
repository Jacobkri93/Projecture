package login.data;

import login.domain.DataFacade;
import login.domain.LoginSampleException;
import login.domain.Project;
import login.domain.User;

public class DataFacadeImpl implements DataFacade {
   private UserMapper userMapper = new UserMapper();
   private ProjectMapper projectMapper = new ProjectMapper();


    public User login(String email, String password) throws LoginSampleException {
        return userMapper.login(email, password);
    }

    public User createUser(User user) throws LoginSampleException {
        userMapper.createUser(user);
        return user;
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


