package websocket;

import javax.websocket.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Repository {

    private Connection con;
    private Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("C:/Users/Dan/IdeaProjects/WebSocketIntelliJAA/src/websocket/settings.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchData(Session session) {
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM temperaturelog ORDER BY ID DESC LIMIT 1")) {

            while (rs.next()) {
                float temperature = rs.getFloat("temperature");
                String sqlstring = "Fetched SQL: " + temperature + " °C";
                session.getBasicRemote().sendText(sqlstring);
            }
        }
        catch(SQLException | IOException e ){
            e.printStackTrace();
        }

    }

    public void insertData(float temperature) {

        String query = "INSERT INTO temperaturelog(temperature) VALUES (?)";

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query))
        {
            stmt.setFloat(1, temperature);
            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
