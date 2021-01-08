package Testing;
import websocket.Repository;
import java.util.Scanner;

public class insertData {

    public insertData() throws InterruptedException{
        Repository r = new Repository();
        int temp;
        //int humidity;

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("What temp ");
            temp = sc.nextInt();
            //humidity = sc.nextInt();
            r.insertData(temp /*humidity*/);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        insertData d = new insertData();
    }
}