package Testing;
import javax.websocket.Session;
import java.util.concurrent.ThreadLocalRandom;

public class displayWebsiteData {

    public displayWebsiteData(Session session) {

        // Reads serial and sends it to websocket and SQL
        while(true){
            try{
                //String input = scanner.nextLine();

                int temp = ThreadLocalRandom.current().nextInt(1, 29 + 1);
                int humid = ThreadLocalRandom.current().nextInt(1, 99 + 1);

                // Sends temperature 2 digits + humidity 2 digits and split
                // ex temp = 25, humid 50 = 2550.
                // then split in middle and store in seperate variables
                //String temp = input.substring(0,input.length()/2);
                //String humid = input.substring((input.length()/2));

                //int parseTempInt = Integer.parseInt(temp);
                //int parseHumidInt = Integer.parseInt(humid);

                String realTimeTemp = "Temperature: " + temp + " °C";
                String realTimeHumid = "Humidity: " + humid + "%";

                String data = temp + " " + humid;
                session.getBasicRemote().sendText(data);

                // Send to websocket
                //session.getBasicRemote().sendText(realTimeTemp);
                //session.getBasicRemote().sendText(realTimeHumid);

                Thread.sleep(5000);
            }catch(Exception e){
                System.out.println("displayWebsiteData: Something went wrong");
                e.printStackTrace();
            }
        }
    }
}