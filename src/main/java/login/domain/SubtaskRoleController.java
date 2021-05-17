package login.domain;

import java.util.ArrayList;

public class SubtaskRoleController {

    private DataFacade facade = null;
    public SubtaskRoleController(DataFacade facade) {
        this.facade = facade;
    }

    public ArrayList<SubtaskRole> getSubtaskRole(){
        return facade.getSubtaskRole();
    }
}
