package login.data;

import login.domain.Role;
import login.domain.SubTaskRoleViewModel;
import login.domain.Subtask;
import login.domain.SubtaskRole;

import java.sql.*;
import java.util.ArrayList;

public class SubtaskRoleMapper {

    public ArrayList<SubTaskRoleViewModel> getRolesFromSubtask(int subtask_id) {
        ArrayList<SubTaskRoleViewModel> rolelist = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT ro.description, subro.hours, ro.price, (subro.hours * ro.price) as FinalPrice FROM subtaskrole subro join subtask sub ON subro.subtask_id = sub.subtask_id join role ro on ro.id = subro.taskrole_id where sub.subtask_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, subtask_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //TODO Tilpas til nyt select
                int hours = rs.getInt("hours");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                double finalPrice = rs.getDouble("FinalPrice");
                rolelist.add(new SubTaskRoleViewModel(description,hours,price,finalPrice));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rolelist;
    }

    public void createSubtaskRole(int subtask_id, int role_id, int hours) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO SubtaskRole (subtask_id, taskrole_id, hours) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, subtask_id);
            ps.setInt(2, role_id);
            ps.setInt(3, hours);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
