package Testing;
import com.google.gson.*;
import javax.websocket.Session;
import java.util.concurrent.ThreadLocalRandom;

public class displayWebsiteData {

    @SuppressWarnings( "deprecation" )
    public displayWebsiteData(Session session) {
        while(true){
            try{
                String jsonString = "{\"device\":\"loc1\",\"temperature\":20,\"humidity\":50,\"tempAlert\":\"false\",\"humidAlert\": \"false\"}";
                session.getBasicRemote().sendText(jsonString);
                Thread.sleep(2000);
            }catch(Exception e){
                System.out.println("displayWebsiteData: Something went wrong");
                e.printStackTrace();
            }
        }
    }
}