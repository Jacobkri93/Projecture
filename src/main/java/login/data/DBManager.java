package login.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static String user;
    private static String password;
    private static String url;
    private static Connection connection = null;


    /*Denne klasse og metode er primært til at stable forbindelse til databasen
    * Den opretter en forbindelse via application.properties hvor der peges på en JDBC.driver til MySQL serveren
    * Der hentes URL + bruger + password til at stable forbindelse
    * */
    public static Connection getConnection(){
        if (connection != null) return connection;
        //FileInputStream bruges til at læse infoen fra filen som ligger den path der beskrives i ()
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url,user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
