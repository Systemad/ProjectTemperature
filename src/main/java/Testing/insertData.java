package Testing;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import websocket.Repository;
import java.util.concurrent.ThreadLocalRandom;

public class insertData {

    @SuppressWarnings( "deprecation" )
    public insertData() throws InterruptedException {
        Repository repository = new Repository();
        while (true) {
            String jsonString = "{\"device\":\"loc1\",\"temperature\":21,\"humidity\":20,\"tempAlert\":\"false\",\"humidAlert\": \"false\"}";
            JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
            repository.insertData(jsonObject);
            Thread.sleep(2000);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        insertData d = new insertData();
    }
}