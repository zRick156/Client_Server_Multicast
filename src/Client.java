import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    Socket socket=null;

    int porta = 3191;
    DataInputStream in;
    DataOutputStream out;
    BufferedReader tastiera;


    public void comunica() throws IOException {
        String messaggio = "";
        tastiera = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("[2] - Messaggio da inviare al server: ");

            messaggio = tastiera.readLine();
            System.out.println("[3] - invio: " + messaggio);
            out.writeBytes(messaggio + "\n");
            System.out.println("[4] - in attesa di una risposta");
            String ricevuta = in.readLine();
            System.out.println("[5] - risposta del server: " + ricevuta);
        } while (!messaggio.equalsIgnoreCase("fine"));

    }



    public Socket connetti(){
        try {
            System.out.println("[0] - provo a connettermi con il server ");
            Socket socket = new Socket(InetAddress.getLocalHost(), porta);

            System.out.println("[1] - Connessione effettuta");
            in=new DataInputStream(socket.getInputStream());
            out=new DataOutputStream(socket.getOutputStream());



        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }





    public static void main(String[] arg) throws IOException {
        Client client= new Client();
        client.connetti();
        client.comunica();
    }


}
