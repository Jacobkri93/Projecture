package login.data;

import login.domain.*;

import java.sql.*;
import java.util.ArrayList;

public class RoleMapper {

    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<Role>();

        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT * FROM role";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                roles.add(new Role(id, description, price));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;


    }
}
