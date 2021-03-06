package Testing;

import javax.websocket.Session;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class fetchDatabase {
    public static void main(String[] args, Session session) throws FileNotFoundException, IOException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Properties p = new Properties();
        p.load(new FileInputStream("C:/Users/Dan/IdeaProjects/WebSocketIntelliJAA/src/websocket/settings.properties"));

        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

            Statement stmt = con.createStatement();
                                                    // Order by time
            ResultSet rs = stmt.executeQuery("SELECT * FROM data ORDER BY ID DESC LIMIT 1")) {

            while (rs.next()) {
                //int id = rs.getInt("id"); // BYT till tid
                float temperature = rs.getInt("temperature");
                int humidity = rs.getInt("humidity");

                session.getBasicRemote().sendText(temperature + " " + humidity);
            }
        }
        catch(SQLException e ){
            e.printStackTrace();
        }
    }
}
