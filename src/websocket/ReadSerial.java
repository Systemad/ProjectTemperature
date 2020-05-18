package websocket;
import java.util.Scanner;
//import com.fazecast.jSerialComm.*;
import com.fazecast.jSerialComm.SerialPort;

import javax.websocket.Session;


public class ReadSerial {

    private Session session;

    /*
    public ReadSerial(Session session){
        this.session = session;
    }
     */

    public ReadSerial(Session session){
        this.session = session;

        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("Select a port:");
        int i = 1; // Prints Ports
        for(SerialPort port : ports)
            System.out.println(i++ +  ": " + port.getSystemPortName());

        // new Scanner to choose ports
        Scanner s = new Scanner(System.in);
        int chosenPort = s.nextInt();

        // Set selected Serial Port and checks if its open
        SerialPort serialPort = ports[chosenPort - 1];

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

        TempServer tempServer = new TempServer();
        // As long as there in output in Serial Port int wont stop looping
        while(scanner.hasNextLine()){
            try{
                String line = scanner.nextLine();
                String test = "hello";

                tempServer.handleMessage(test, session);
                System.out.println(line);
                //message = scanner.nextLine();
            }catch(Exception e){
                System.out.println("something wrong");
                e.printStackTrace();
            }
        }

    }

}