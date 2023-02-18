import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class ServerThread implements Runnable{
    private final Socket socketClient;
    private final int id;
    private final DataInputStream in;
    private final DataOutputStream out;

    public ServerThread(Socket socketClient, int contatore) throws IOException
    {
        this.socketClient = socketClient;
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
                String risposta;
                assert letto != null;
                if(!letto.equalsIgnoreCase("fine")) {
                    char a;
                    StringBuilder rispostaBuilder = new StringBuilder();
                    for (int i = 0; i < letto.length(); ++i) {
                        a = letto.charAt(i);
                        rispostaBuilder.insert(0, a);
                    }
                    risposta = rispostaBuilder.toString();
                    risposta = risposta.toUpperCase();
                }else{
                    risposta = letto.toUpperCase();
                }
                System.out.println("[" + id + "][5] - rispondo con: " + risposta);
                out.writeBytes(risposta + "\n");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }while (!Objects.requireNonNull(letto).equalsIgnoreCase("fine"));
    }
}
