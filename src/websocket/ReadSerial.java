package websocket;
import java.io.IOException;
import java.util.Scanner;
import com.fazecast.jSerialComm.*;
import javax.websocket.Session;

public class ReadSerial {

    Repository2 r = new Repository2();

    public ReadSerial(Session session) throws IOException {
        // Prints ports
        SerialPort[] ports = SerialPort.getCommPorts();
        //System.out.println("Select a port: ");
        int i = 1;
        for(SerialPort port : ports)
            System.out.println(i++ +  ": " + port.getSystemPortName());

        // Not working - selecting manual port for now
        // New Scanner to choose ports
        //Scanner s = new Scanner(System.in);
        //int chosenPort = s.nextInt();

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

        // Reads serial and sends it to websocket
        while(scanner.hasNextLine()){
            try{
                String line = "Arduino (Real time): " + scanner.nextLine() + " °C";
                session.getBasicRemote().sendText(line);
                //session.getBasicRemote().sendText("Humidity 45%");

                String tempStr = scanner.nextLine();
                float f = Float.parseFloat(tempStr);
                r.updateTemp(f);
                r.fetchData(session);
                //System.out.println(line);
            }catch(Exception e){
                System.out.println("something wrong");
                e.printStackTrace();
            }
        }
    }
}