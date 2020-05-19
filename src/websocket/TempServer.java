package websocket;

import javax.websocket.*;
import com.fazecast.jSerialComm.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/ws")
public class TempServer {

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        session.getBasicRemote().sendText("Showing Temperature log in (real time)");
        //System.out.println("open");
        ReadSerial readSerial = new ReadSerial(session);
    }

    /*
    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
        System.out.println("handle message");
        for (Session peer : session.getOpenSessions())
            peer.getBasicRemote().sendText(message);
    }

     */
}