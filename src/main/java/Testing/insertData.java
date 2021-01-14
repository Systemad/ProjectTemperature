package Testing;
import websocket.Repository;
import java.util.concurrent.ThreadLocalRandom;

public class insertData {

    public insertData() throws InterruptedException{
        Repository repository = new Repository();
        int temp;
        int humid;

        while(true){
            temp = ThreadLocalRandom.current().nextInt(1, 29 + 1);
            humid = ThreadLocalRandom.current().nextInt(1, 99 + 1);
            boolean tempAlert;
            boolean humidAlert;

            String data = temp + " " + humid;

            // Split string into [temp, humid]
            String[] split = data.split("\\s+");

            // split[0] = temp, split[1] = humid
            int parseTempInt = Integer.parseInt(split[0]);
            int parseHumidInt = Integer.parseInt(split[1]);

            tempAlert = parseTempInt < 10 || parseTempInt > 25;

            humidAlert = parseHumidInt  < 25 || parseHumidInt > 70;

            System.out.println(parseTempInt);
            System.out.println(parseHumidInt);
            // Send to SQL
            repository.insertData(parseTempInt, parseHumidInt, tempAlert, humidAlert);
            Thread.sleep(2000);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        insertData d = new insertData();
    }
}