import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",2001);

            Shop shop = new Shop(socket);


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
