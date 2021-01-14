package Testing;
import javax.websocket.Session;
import java.util.concurrent.ThreadLocalRandom;

public class displayWebsiteData {

    public displayWebsiteData(Session session) {
        while(true){
            try{
                int temp = ThreadLocalRandom.current().nextInt(1, 29 + 1);
                int humid = ThreadLocalRandom.current().nextInt(1, 99 + 1);

                String data = temp + " " + humid;
                session.getBasicRemote().sendText(data);

                Thread.sleep(5000);
            }catch(Exception e){
                System.out.println("displayWebsiteData: Something went wrong");
                e.printStackTrace();
            }
        }
    }
}