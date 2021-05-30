package login.data;

import login.domain.SubTaskRoleViewModel;

import java.sql.*;
import java.util.ArrayList;

public class SubtaskRoleMapper {

    //Metoden getRolesFromSubtask henter dataen fra subtask, subtaskrole og role tabellerne og fletter dem sammen ud fra subtask id
    //Den tager timerne fra role tabellen, og ganger med role price som giver en ny double værdi kaldet FinalPrice.

    public ArrayList<SubTaskRoleViewModel> getRolesFromSubtask(int subtask_id) {
        ArrayList<SubTaskRoleViewModel> rolelist = new ArrayList<>();
        try {
            Connection con = DBManager.getConnection();
            //Statement: Vælg role.description, subtaskrole.hours, role.price, (subtaskrole.hours * role.price) Lav ny colonne og kald for Finalprice -> Fra subtaskrole subtask_id FLET/join sammen med subtask subtask_id ->
            String SQL = "SELECT ro.description, subro.hours, ro.price, (subro.hours * ro.price) as FinalPrice FROM subtaskrole subro join subtask sub ON subro.subtask_id = sub.subtask_id join role ro on ro.id = subro.role_id where sub.subtask_id = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, subtask_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int hours = rs.getInt("hours");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                double finalPrice = rs.getDouble("FinalPrice");
                rolelist.add(new SubTaskRoleViewModel(description,hours,price,finalPrice));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Skriver subtaskens role ressources ud til consollen og viser dem med priser udregnet (OBS man skal vælge et projekt i webappen før dette vises)
        System.out.println(rolelist);

        return rolelist;

    }

    //Denne metode bruges til at forbinde subtaskrole og subtask, med role tabellen, som beskriver timer og hvilken rolle der bruges ud fra subtask id.

    public void createSubtaskRole(int subtask_id, int role_id, int hours) {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO SubtaskRole (subtask_id, role_id, hours) VALUES (?,?,?)";
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
