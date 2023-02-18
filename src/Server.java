import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final int PORT=3191;

    private static int nclient = 0;

    private static ExecutorService pool=null;


    public static void main(String[] args) throws IOException {
        new Server();
        Server serverObj=new Server();
        serverObj.startServer();

    }

    public Server() {
        pool=Executors.newFixedThreadPool(22);

    }

    public void startServer() throws IOException{
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("[Server] Attivo");
        System.out.println("[Server] Attesa connessione");
        do {
            try {
                Socket client = server.accept();
                nclient++;
                ServerThread runnable = new ServerThread(client, nclient);
                pool.execute(runnable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
