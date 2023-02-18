import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final int PORT=3191;

    private static int nclient = 0;
    private DataOutputStream out;
    private DataInputStream in;

    private ServerSocket server=null;
    private Socket client=null;
    private static ExecutorService pool=null;


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Server();
        Server serverObj=new Server();
        serverObj.startServer();

    }

    public Server() throws IOException, ClassNotFoundException {
        pool=Executors.newFixedThreadPool(22);

    }

    public void startServer() throws IOException{
        server = new ServerSocket(PORT);
        System.out.println("[Server] Attivo");
        System.out.println("[Server] Attesa connessione");
        while (true){
            client=server.accept();
            nclient++;
            ServerThread runnable=new ServerThread(client, nclient ,this);
            pool.execute(runnable);
        }
    }
}
