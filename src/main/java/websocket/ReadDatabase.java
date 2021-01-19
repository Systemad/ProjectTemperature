package websocket;
import javax.websocket.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ReadDatabase {
    Properties p = new Properties();
    public ReadDatabase(Session session) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        p.load(new FileInputStream("C:/Users/Dan/IdeaProjects/WebSocketIntelliJAA/src/websocket/settings.properties"));
        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
            Statement stmt = con.createStatement();
            // Order by time
            ResultSet rs = stmt.executeQuery("SELECT * FROM data ORDER BY ID DESC LIMIT 1")) {

            while (rs.next()) {
                float temperature = rs.getInt("temperature");
                int humidity = rs.getInt("humidity");
                session.getBasicRemote().sendText(temperature + " " + humidity);
            }
        }
        catch(SQLException | IOException e ){
            e.printStackTrace();
        }
    }
}