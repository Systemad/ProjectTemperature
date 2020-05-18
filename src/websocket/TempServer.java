package websocket;

import sun.plugin2.message.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/ws")
public class TempServer {
    private Session Session;

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        ReadSerial readSerial = new ReadSerial(Session);
        session.getBasicRemote().sendText("Showing Temperatures");
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
        for (Session peer : session.getOpenSessions())
            peer.getBasicRemote().sendText(message);
    }
}