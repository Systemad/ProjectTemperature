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
            //                                                                                  limit 10
            ResultSet rs = stmt.executeQuery("SELECT * FROM temperaturelog ORDER BY ID DESC LIMIT 1")) {

            // make so it fetches last 10 entries from both humidity and temperature to a seperate table
            while (rs.next()) {
                //                  rs.getInt
                float temperature = rs.getFloat("temperature");
                int humidity = rs.getInt("humidity");

                String temperatureString = "Temperature" + temperature + " °C";
                String humidityString = "Humidity: " + humidity + " %";

                session.getBasicRemote().sendText(temperatureString);
                session.getBasicRemote().sendText(humidityString);
            }
        }
        catch(SQLException | IOException e ){
            e.printStackTrace();
        }
    }

    public void insertData(float temperature, int humidity) {

        String query = "INSERT INTO temperaturelog(temperature, humidity) VALUES (?)";

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query))
        {
            stmt.setFloat(1, temperature);
            stmt.setInt(2, humidity);
            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
