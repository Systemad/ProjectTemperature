package websocket;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.fazecast.jSerialComm.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.websocket.Session;

public class ReadSerial {

    Repository repository = new Repository();

    @SuppressWarnings( "deprecation" )
    public ReadSerial(Session session) {

        // Prints ports
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("Select a port: ");
        int i = 1;
        for(SerialPort port : ports)
            System.out.println(i++ +  ": " + port.getSystemPortName());

        // Not working - selecting manual port for now
        // New Scanner to choose ports
        //Scanner s = new Scanner(System.in);
        //int chosenPort = s.nextInt();

        // Manually for now
        // Set selected Serial Port and checks if its open
        SerialPort serialPort = ports[1 - 1];
        if(serialPort.openPort())
            System.out.println("Port opened successfully.");
        else {
            System.out.println("Unable to open the port.");
            return;
        }

        // Sets correct baudrate in order to properly read serial output
        serialPort.setComPortParameters(115200, 8, 1, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        Scanner scanner = new Scanner(serialPort.getInputStream());

        // Reads serial and sends it to websocket and SQL
        while(scanner.hasNextLine()){
            try{
                // Input received [temp humid]
                String input = scanner.nextLine();
                JsonObject jsonObject = new JsonParser().parse(input).getAsJsonObject();
                session.getBasicRemote().sendText(input);
                repository.insertData(jsonObject);

                //Write JSON file
                try (FileWriter file = new FileWriter("C:\\Users\\Dan\\IdeaProjects\\WebSocketIntelliJ\\web\\tools.json")) {

                    file.write(jsonObject.toString());
                    file.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }catch(Exception e){
                System.out.println("ReadSerial: Something went wrong");
                e.printStackTrace();
            }
        }
    }
}