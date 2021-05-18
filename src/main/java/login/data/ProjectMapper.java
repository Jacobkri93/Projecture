package login.data;

import login.domain.Project;
import login.domain.Subtask;
import login.domain.User;

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
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();

            int id = ids.getInt(1);
            project.setProject_id(id);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }


    public Project getProjectNew(Integer project_id) {
        Project project = new Project();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM project WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, project_id);

            ResultSet rs = ps.executeQuery();

            rs.next();
            int projectid = rs.getInt("project_id");
            String project_name = rs.getString("project_name");
            int week_duration = rs.getInt("week_duration");
            int user_id = rs.getInt("user_id");
            project.setProject_id(projectid);
            project.setProject_name(project_name);
            project.setWeek_duration(week_duration);
            project.setUser_id(user_id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return project;
    }



    public ArrayList<Project> getProject(User user) {
        ArrayList<Project> projectList = new ArrayList<Project>();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM project WHERE project.user_id=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, user.getId());



            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("project_id");
                String name = rs.getString("project_name");
                int week_duration = rs.getInt("week_duration");
                Project project = new Project(id, name, week_duration, user.getId());

                projectList.add(project);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return projectList;


    }

    public Project addSubtaskToProject(User user, Subtask subtask, Integer project_id) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO subtasks (task_name, project_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, subtask.getTask_name());

            ps.setInt(2, project_id);
            ps.executeUpdate();

        } catch (SQLException ex) {
        }
        return getProjectNew(project_id);
    }
}