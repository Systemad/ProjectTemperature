package websocket;

import java.util.Scanner;


public class updateTemp {

    public updateTemp() throws InterruptedException{
        Repository2 r = new Repository2();
        int temp;

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("What temp ");
            temp = sc.nextInt();
            r.updateTemp(temp);
            //r.getChildByName(name).printOut();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        updateTemp d = new updateTemp();
    }
}