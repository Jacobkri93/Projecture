package login.domain;

import login.data.DataFacadeImpl;

public class LoginController {

    // facade to datasource layer
    private DataFacade facade = null;

    public LoginController(DataFacade facade) {
        this.facade = facade;
    }

    public User login(String email, String password) throws LoginSampleException {
        return facade.login(email, password);
    }

    public User createUser(String email, String password) throws LoginSampleException {
        // By default, new users are customers
        User user = new User(email, password);
        facade.createUser(user);
        return user;
    }
}
