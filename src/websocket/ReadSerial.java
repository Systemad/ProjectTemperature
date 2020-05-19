package websocket;
import java.io.IOException;
import java.util.Scanner;
import com.fazecast.jSerialComm.*;
import javax.websocket.Session;

public class ReadSerial {

    public ReadSerial(Session session) throws IOException {
        // Fetches ports and prints them out
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("Select a port: ");
        int i = 1;
        for(SerialPort port : ports)
            System.out.println(i++ +  ": " + port.getSystemPortName());

        // New Scanner to choose ports
        //Scanner s = new Scanner(System.in);
        //int chosenPort = s.nextInt();

        // Set selected Serial Port and checks if its open
        SerialPort serialPort = ports[2 - 1];
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

        // As long as there it reads from Serial it wont stop looping
        while(scanner.hasNextLine()){
            try{
                String line = scanner.nextLine();
                line = "Temperature: " + line + " C";
                session.getBasicRemote().sendText(line);
                System.out.println(line);
            }catch(Exception e){
                System.out.println("something wrong");
                e.printStackTrace();
            }
        }
    }
}