package websocket;

import javax.websocket.*;
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

}