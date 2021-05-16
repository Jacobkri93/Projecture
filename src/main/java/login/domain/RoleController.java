package login.domain;

import java.util.ArrayList;

public class RoleController {


    private DataFacade facade = null;

    public RoleController(DataFacade facade) {
        this.facade = facade;
    }

    public ArrayList<Role> getRoles() {

        return facade.getRoles();
    }
}
