package login.data;

import login.domain.Subtask;
import login.domain.Project;

import java.sql.*;

public class SubtaskMapper {

    public void createSubtask(Subtask subtask, Project project) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO project (task_name,hours,cost,employees, project_id) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            // ps.setInt(1, project.getProject_id());
            ps.setString(1, subtask.getTask_name());
            ps.setInt(2, subtask.getHours());
            ps.setDouble(3, subtask.getCost());
            ps.setString(4, subtask.getEmployees());
            ps.setInt(5, project.getProject_id());
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            ps.executeUpdate();
            int id = ids.getInt(1);
            project.setProject_id(id);


        } catch (SQLException ex) {

        }

    }
}