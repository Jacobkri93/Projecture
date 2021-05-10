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
    public Project create_project(Project project) {
        projectMapper.create_project(project);
        return project;
    }
}
