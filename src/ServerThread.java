import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class ServerThread implements Runnable{
    Server  server = null;
    Socket socketClient;
    private int id;
    int porta = 6789; //porta server

    DataInputStream in;
    DataOutputStream out;

    public ServerThread(Socket socketClient, int contatore, Server server) throws IOException
    {
        this.socketClient = socketClient;
        this.server = server;
        this.id = contatore;
        System.out.println("[Server] connessione " + id + " stabilita con il client");
        out = new DataOutputStream(this.socketClient.getOutputStream());
        in = new DataInputStream(this.socketClient.getInputStream());
    }

    @Override
    public void run()
    {
        String letto = null;
        do {
            if(!socketClient.isConnected())
                letto = "fine";
            try {
                System.out.println("[" + id + "][3] - Aspetto un messaggio dal client...");
                if(socketClient.isConnected())
                letto = in.readLine();
                System.out.println("["+ id + "][4] - messaggio ricevuto : " + letto);
                String risposta="";
                if(!letto.equalsIgnoreCase("fine")) {
                    char a;
                    for (int i = 0; i < letto.length(); ++i) {
                        a = letto.charAt(i);
                        risposta = a + risposta;
                    }
                    risposta = risposta.toUpperCase();
                    System.out.println("[" + id + "][5] - rispondo con: " + risposta);
                    out.writeBytes(risposta + "\n");
                }else{
                    risposta = letto.toUpperCase();
                    System.out.println("[" + id + "][5] - rispondo con: " + risposta);
                    out.writeBytes(risposta + "\n");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (!letto.equalsIgnoreCase("fine"));
    }
}
