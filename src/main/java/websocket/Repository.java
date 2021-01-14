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
            p.load(new FileInputStream("C:\\Users\\Dan\\IdeaProjects\\WebSocketIntelliJ\\src\\main\\java\\websocket\\settings.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: Refactor to obtain data from SQL, then display it on a table and visualise on the website
    public void fetchData(Session session) {
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

            Statement stmt = con.createStatement();
            //                                                                                  limit 10
            ResultSet rs = stmt.executeQuery("SELECT * FROM data ORDER BY ID DESC LIMIT 1")) {

            // make so it fetches last 10 entries from both humidity and temperature to a separate table
            while (rs.next()) {
                //                  rs.getInt
                float temperature = rs.getInt("temperature");
                int humidity = rs.getInt("humidity");

                session.getBasicRemote().sendText("placeholder");
                //session.getBasicRemote().sendText(humidityString);
            }
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }

    public void insertData(int temperature, int humidity, boolean tempAlert, boolean humidAlert) {

        //String query = "INSERT INTO data(temperature) VALUES (?)";
        String query = "INSERT INTO data(temp, humid, tempAlert, humidAlert) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query))
        {
            stmt.setInt(1, temperature);
            stmt.setInt(2, humidity);
            stmt.setBoolean(3, tempAlert);
            stmt.setBoolean(4, humidAlert);
            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
