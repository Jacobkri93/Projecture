package login.data;

import login.domain.Role;
import login.domain.Subtask;
import login.domain.SubtaskRole;

import java.sql.*;
import java.util.ArrayList;

public class SubtaskRoleMapper {

    public SubtaskRole getSubtaskRole(Role role) {
        SubtaskRole list = new SubtaskRole();
        ArrayList<Subtask> subtaskList = new ArrayList<>();

        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT subtask.* FROM subtaskrole join subtask ON subtask.subtask_id=subtaskrole.id WHERE subtaskrole.taskrole_id= ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, role.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String task_name = rs.getString("task_name");
                subtaskList.add(new Subtask(id, task_name));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        list.setSubtaskList(subtaskList);
        list.setTaskrole_id(role.getId());
        return list;
    }


    public Subtask addRoletoSubtask (SubtaskRole subtaskRole) {
        Subtask subtask = new Subtask();
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO subtaskrole (hours, subtask_id, taskrole_id) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, subtaskRole.getHours());
            ps.setInt(2, subtaskRole.getSubtask_id());
            ps.setInt(3, subtaskRole.getTaskrole_id());

            ps.executeUpdate();

        } catch (SQLException ex) {
        }
        return subtask;
    }


}
