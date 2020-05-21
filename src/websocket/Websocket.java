package websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/ws")
public class Websocket {

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        ReadSerial readSerial = new ReadSerial(session);
    }
}