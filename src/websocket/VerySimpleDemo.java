package websocket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class VerySimpleDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM temperaturelog ORDER BY ID DESC LIMIT 1")) {

            while (rs.next()) {
                //int id = rs.getInt("id"); // BYT till tid
                float temp = rs.getFloat("temperature");

                String hello = " Temperature: " + temp;
                System.out.println(hello);
                System.out.println(rs.getInt("id"));
            }
        }
        catch(SQLException e ){
            e.printStackTrace();
        }
    }
}
