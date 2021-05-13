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
    //måske ??

//    public Project get(User user) {
//        Wishlist list = new Wishlist();
//        ArrayList<Item> itemlist = new ArrayList<Item>();
//        try {
//            Connection con = DBManager.getConnection();
//            String SQL = "SELECT item.* FROM wishlist join item ON item.id=wishlist.item_id WHERE wishlist.user_id=?";
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setInt(1, user.getId());
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                String description = rs.getString("description");
//                String name = rs.getString("name");
//                int id = rs.getInt("id");
//                double price = rs.getDouble("price");
//                Item item = new Item(name, description, price);
//                item.setId(id);
//                itemlist.add(item);
//            }
//        } catch (SQLException | FileNotFoundException ex) {
//        }
//        list.setItemlist(itemlist);
//        list.setUser_id(user.getId());
//        return list;
//


}