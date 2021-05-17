package login.domain;

import java.util.ArrayList;

public class SubtaskRoleController {

    private DataFacade facade = null;
    public SubtaskRoleController(DataFacade facade) {
        this.facade = facade;
    }

    public SubtaskRole getSubtaskRole(Role role){
        return facade.getSubtaskRole(role);
    }
}
