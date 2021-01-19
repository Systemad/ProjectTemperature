package Testing;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.websocket.Session;
import java.util.concurrent.ThreadLocalRandom;

public class displayWebsiteData {

    @SuppressWarnings( "deprecation" )
    public displayWebsiteData(Session session) {
        while(true){
            try{
                String jsonString = "{\"device\":\"loc1\",\"temperature\":20,\"humidity\":50,\"tempAlert\":\"false\",\"humidAlert\": \"false\"}";
                JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
                //session.getBasicRemote().sendText(jsonObject.getAsString());
                session.getBasicRemote().sendText("50");
                Thread.sleep(2000);
            }catch(Exception e){
                System.out.println("displayWebsiteData: Something went wrong");
                e.printStackTrace();
            }
        }
    }
}