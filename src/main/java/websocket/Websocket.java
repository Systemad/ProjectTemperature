package websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import Testing.displayWebsiteData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ServerEndpoint("/ws")
public class Websocket {

    Repository repository = new Repository();

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        //ReadSerial readSerial = new ReadSerial(session);
        //displayWebsiteData displayWebsiteData = new displayWebsiteData(session);
        ReadDatabase readDatabase = new ReadDatabase(session);
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("Session " + session.getId()+ " has ended");
    }

    @SuppressWarnings( "deprecation" )
    @OnMessage
    public void onMessage(Session session, String message){
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
            // Message is a JSON object, let the javascript parse it
            session.getBasicRemote().sendText(message);
            repository.insertData(jsonObject);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}