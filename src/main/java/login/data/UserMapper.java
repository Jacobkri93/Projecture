package login.data;

import login.domain.LoginSampleException;
import login.domain.User;
import java.sql.*;

//Ansvarlig: Mads + Patrick
public class UserMapper {

    //Metoden bruges til at oprette en ny bruger og gemme i DB.
    public void createUser(User user) throws LoginSampleException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "INSERT INTO user (email, password) VALUES (?, ?)";
            //Man bruger PreparedStatement for at mindske eksekveringstid og et Preparedstatement indeholder et SQL statement der er precompileret
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            user.setId(id);
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }

    //Metoden skal bruges når en bruger vil logge ind -> Den spørger efter user_id fra user tabellen, hvor emailen og password == "Det brugeren skriver"
    public User login(String email, String password) throws LoginSampleException {
        try {
            Connection con = DBManager.getConnection();
            String SQL = "SELECT user_id FROM user   "
                    + "WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                User user = new User(email, password);
                user.setId(id);
                return user;
            } else {
                throw new LoginSampleException("Could not validate user - Please make sure you have a user registered");
            }
        } catch (SQLException ex) {
            throw new LoginSampleException(ex.getMessage());
        }
    }


    // Metode til test case.
    // En foreign key med cascade delete som betyder at hvis en værdi i parenttablen er slettet,
    // Så vil de samme værdier i tabellens child også blive slettet.
    // Dette kaldes en cascade delete i SQL server.
    public void deleteUser(User user) {
        try {
            int id = 0;
            Connection con = DBManager.getConnection();
            String SQL = "SELECT user_id FROM user WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("user_id");
            }

            if(id != 0) {
                String SQL1 = "DELETE FROM project WHERE user_id = ?";
                PreparedStatement ps1 = con.prepareStatement(SQL1, Statement.RETURN_GENERATED_KEYS);
                ps1.setInt(1, id);
                ps1.executeUpdate();


                String SQL2 = "DELETE FROM user WHERE email = ?";
                PreparedStatement ps2 = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
                ps2.setString(1, user.getEmail());
                ps2.executeUpdate();
            }

        } catch (SQLException ex) {
        }
    }
}
