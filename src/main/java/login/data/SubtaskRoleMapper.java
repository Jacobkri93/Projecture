package login.data;

import login.domain.Role;
import login.domain.Subtask;
import login.domain.SubtaskRole;

import java.sql.*;
import java.util.ArrayList;

public class SubtaskRoleMapper {

    public ArrayList<Role> getRolesFromSubtask(Subtask subtask) {
        ArrayList<Role> rolelist = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT subtask.* FROM subtaskrole join subtask ON subtask.subtask_id=subtaskrole.id WHERE subtaskrole.subtask_id= ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, subtask.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                rolelist.add(new Role(id,description, price));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rolelist;
    }


//    public Subtask addRoletoSubtask (SubtaskRole subtaskRole) {
//        Subtask subtask = new Subtask();
//        try {
//            Connection con = DBManager.getConnection();
//            String SQL = "INSERT INTO subtaskrole (hours, subtask_id, taskrole_id) VALUES (?,?,?)";
//            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            ps.setDouble(1, subtaskRole.getHours());
//            ps.setInt(2, subtaskRole.getSubtask_id());
//            ps.setInt(3, subtaskRole.getTaskrole_id());
//
//            ps.executeUpdate();
//
//        } catch (SQLException ex) {
//        }
//        return subtask;
//    }


}
