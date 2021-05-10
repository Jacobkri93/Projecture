package login.data;

import login.domain.*;

import java.io.FileNotFoundException;
import java.sql.*;

public class ProjectMapper {

    public Project create_project(User user, Project week_duration) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO project (user_id, week_duration) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user.getId());
            ps.setInt(2, week_duration.getWeek_duration());

            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setId(id);

        } catch (SQLException ex) {

        }
        return Project(user);
    }



}
