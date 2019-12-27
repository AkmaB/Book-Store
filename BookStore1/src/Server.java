import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) throws IOException {
        //ServerSocket listening
        try {
            ServerSocket ss = new ServerSocket(2001);
            while(true){
                System.out.println("waiting for client...");
                Socket socket =  ss.accept();
                System.out.println("client connected: " + socket.getInetAddress().getHostAddress());
                ClientHandler ch = new ClientHandler(socket);
                ch.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
