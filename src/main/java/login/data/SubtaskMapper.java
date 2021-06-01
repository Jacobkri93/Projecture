package login.data;

import login.domain.Project;
import login.domain.SubTaskRoleViewModel;
import login.domain.Subtask;
import java.sql.*;
import java.util.ArrayList;


//Ansvarlig: Patrick + Mads
public class SubtaskMapper {
    SubtaskRoleMapper subtaskRoleMapper = new SubtaskRoleMapper();


    /*Opretter ny subtask og gemmer i DB
     * Connection: Kommer fra DBManager klassen, som skaber forbindelsen
     * String SQL: Et statement for hvilken udførsel den skal køres og gemmes pga. det er et INSERT INTO
     * Preparedstatement: Pre-compileret statement der bruges til at indsætte værdier, i dette tilfælde navn, duration og bruger ID.
     * executeUpdate bruges til at eksekvere SQL Statementet (INTERT, DELETE, UPDATE)
     * ResultSet er dataen fra den specifikke tabel (Dvs. Subtask table, har den resultset = task_name, project_id)
     * */

    public void createSubtask(Subtask subtask, Integer project_id) {

        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO subtask (task_name, project_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subtask.getTask_name());
            ps.setInt(2, project_id);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            subtask.setId(id);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //Metoden getSubtaskList bruges til at hente en liste over alle subtask ud fra et project ID.
    //(lidt ligsom getProject metoden fra ProjectMapper klassen)
    //Metoden returnere en Arrayliste der kaldes subtask
    //Bruges til setSessionInfoFromHome metoden i SessionController klassen
    public ArrayList<Subtask> getSubtaskList(int project_id) {
        ArrayList<Subtask> subtasks = new ArrayList();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM subtask WHERE project_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, project_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("subtask_id");
                String task_name = rs.getString("task_name");
                subtasks.add(new Subtask(id, task_name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subtasks;
    }


    //Metoden bruges til at hente data fra subtask tabellen hvor task_name og project_id =?.
    //Metoden returnere en subtask
    public Subtask getSubtask(String task_name, int project_id) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT subtask_id, task_name FROM subtask where task_name = ? AND project_id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, task_name);
            ps.setInt(2, project_id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("subtask_id");
                Subtask subtask = new Subtask(id, task_name, project_id);
                return subtask;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

//    public void setProjectSubtask(ArrayList<Project> projects) {
//        for (int projectIndex = 0; projectIndex < projects.size(); projectIndex++) {
//            Project project = projects.get(projectIndex);
//            int projectId = project.getProjectId();
//            ArrayList<Subtask> subtasks = getSubtaskList(projectId);
//
//            for (int subtasksIndex = 0; subtasksIndex < subtasks.size(); subtasksIndex++) {
//                Subtask subtask = subtasks.get(subtasksIndex);
//                int subtaskId = subtask.getId();
//                ArrayList<SubTaskRoleViewModel> subtaskRoles = subtaskRoleMapper.getRolesFromSubtask(subtaskId);
//                subtask.setSubtaskRoleList(subtaskRoles);
//                subtasks.set(subtasksIndex, subtask);
//            }
//
//            project.setSubtasklist(subtasks);
//            projects.set(projectIndex, project);
//        }
//    }
}
