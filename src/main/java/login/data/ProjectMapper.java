package login.data;

import login.domain.Project;
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
            ps.setString(2, project.getWeek_duration());
            ps.setInt(3, user.getId());
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            ps.executeUpdate();
            int id = ids.getInt(1);
            project.setProject_id(id);


        } catch (SQLException ex) {

        }


    }
}