package login.data;

import login.domain.Project;
import login.domain.Subtask;
import login.domain.User;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class ProjectMapper {

    public void createProject(Project project, User user) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO project (project_name,week_duration, user_id) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            // ps.setInt(1, project.getProject_id());
            ps.setString(1, project.getProject_name());
            ps.setInt(2, project.getWeek_duration());
            ps.setInt(3, user.getId());
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            ps.executeUpdate();
            int id = ids.getInt(1);
            project.setProject_id(id);


        } catch (SQLException ex) {

        }


    }
    //måske

    public Project getProject(User user) {
        Project list = new Project();
        ArrayList<Subtask> subtasklist = new ArrayList<Subtask>();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT subtasks.* FROM project join subtasks ON subtasks.subtask_id=project.subtask_id WHERE project.user_id=?)";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, user.getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String task_name = rs.getString("task_name");
                int hours = rs.getInt("hours");
                double cost = rs.getDouble("cost");
                String employees = rs.getString("employess");
                int id = rs.getInt("id");
                Subtask subtask = new Subtask(task_name, hours, cost, employees);
                subtask.setId(id);
                subtasklist.add(subtask);
            }
        } catch (SQLException ex) {
        }
        list.setSubtasklist(subtasklist);
        list.setUser_id(user.getId());
        return list;


    }

    public Project addSubtaskToProject(User user, Subtask subtask) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO subtasks (task_name, hours, cost, employees) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subtask.getTask_name());
            ps.setInt(2, subtask.getHours());
            ps.setDouble(3,subtask.getHours());
            ps.setString(4, subtask.getEmployees());
            ps.executeUpdate();

        } catch (SQLException ex) {
        }
        return getProject(user);
    }
}