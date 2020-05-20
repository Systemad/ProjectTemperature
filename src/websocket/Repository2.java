package websocket;

import javax.websocket.Session;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class Repository2 {

    private Connection con;
    private Properties p = new Properties();

    public Repository2() {
        try {
            p.load(new FileInputStream("C:/Users/Dan/IdeaProjects/WebSocketIntelliJAA/src/websocket/settings.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    public Child getChildByName(String name) {
        Child child = new Child();

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM child WHERE name='" + name + "'");) {

            while (rs.next()) {
                child = new Child(rs.getInt("id"), rs.getString("name"), rs.getBoolean("nice"), rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return child;
    }

     */
    public void fetchData(Session session) {
        try(Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

            Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("select id, temperature from temperaturelog")) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM temperaturelog ORDER BY ID DESC LIMIT 1")) {

            while (rs.next()) {
                //int id = rs.getInt("id");
                float temp = rs.getFloat("temperature");

                //String city = rs.getString("city");
                String hello = "SQL: " + temp + " °C";
                session.getBasicRemote().sendText(hello);
                //System.out.println(hello);
                //System.out.println("Id: " + id + " name " + temp); //+ "city" + city);
            }
        }
        catch(SQLException | IOException e ){
            e.printStackTrace();
        }
    }

    public void updateTemp(float temp) {

        String query1 = "INSERT INTO temperaturelog(temperature) VALUES (?)";

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query1))
        {
            stmt.setFloat(1, temp);
            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
