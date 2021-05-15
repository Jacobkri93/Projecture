package login.data;

import login.domain.Subtask;
import login.domain.Project;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class SubtaskMapper {

    public void createSubtask(Subtask subtask, Integer project_id) {

        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO subtasks (task_name, hours, cost, employees, project_id) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            // ps.setInt(1, project.getProject_id());
            ps.setString(1, subtask.getTask_name());
            ps.setInt(2, subtask.getHours());
            ps.setDouble(3, subtask.getCost());
            ps.setString(4, subtask.getEmployees());
            ps.setInt(5,project_id.intValue());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            subtask.setId(id);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

public Subtask getSubtask (String task_name) {
    try {
        Connection con = DBManager.getConnection();
        String SQL = "SELECT subtask_id, task_name, hours, cost,employees FROM subtasks where task_name = ?;";
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setString(1, task_name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int hours = rs.getInt("hours");
            int id = rs.getInt("subtask_id");
            double cost = rs.getDouble("cost");
            String employees = rs.getString("employees");
            Subtask subtask = new Subtask(task_name, hours, cost, employees);
            subtask.setId(id);
            return subtask;
        }
    } catch (SQLException ex) {
    }
    return null;

}
public ArrayList<Subtask> getSubtaskList (Integer project_id){
        ArrayList<Subtask> subtasks = new ArrayList();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM subtasks WHERE project_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, project_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("subtask_id");
                String task_name = rs.getString("task_name");
                int hours = rs.getInt("hours");
                double cost = rs.getDouble("cost");
                String employees = rs.getString("employees");
                subtasks.add(new Subtask(id,task_name, hours, cost, employees));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return subtasks;
}
}