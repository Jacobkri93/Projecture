package login.data;

import login.domain.Project;
import login.domain.Subtask;

import java.sql.*;
import java.util.ArrayList;

public class SubtaskMapper {

    public void createSubtask(Subtask subtask, Integer project_id) {

        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO subtask (task_name, project_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subtask.getTask_name());
            ps.setInt(2,project_id.intValue());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            subtask.setId(id);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

//public Subtask getSubtask (int subtask_id) {
//    try {
//        Connection con = DBManager.getConnection();
//        String SQL = "SELECT subtask_id, task_name FROM subtasks where subtask_id = ?;";
//        PreparedStatement ps = con.prepareStatement(SQL);
//        ps.setInt(1, subtask_id);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//            String name = rs.getString("task_name");
//            Subtask subtask = new Subtask(subtask_id, name);
//            return subtask;
//        }
//    } catch (SQLException ex) {
//    }
//    return null;

//}
public ArrayList<Subtask> getSubtaskList (int project_id){
        ArrayList<Subtask> subtasks = new ArrayList();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM subtask WHERE project_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, project_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("subtask_id");
                String task_name = rs.getString("task_name");
                subtasks.add(new Subtask(id,task_name));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subtasks;
}

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
        }
        return null;
    }

    public void setProjectSubtask(ArrayList<Project> projects) {
        for (int projectIndex = 0; projectIndex < projects.size(); projectIndex++) {
            Project project = projects.get(projectIndex);
            int projectId = project.getProjectId();
            ArrayList<Subtask> subtasks = getSubtaskList(projectId);
            project.setSubtasklist(subtasks);
            projects.set(projectIndex, project);
        }
    }
}