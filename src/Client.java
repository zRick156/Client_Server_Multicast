import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    private DataInputStream in;
    private DataOutputStream out;


    public void comunica() throws IOException {
        String messaggio;
        Character    chrRisposta;

        chrRisposta = 'S';
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        do
        {
                if (chrRisposta.toString().equalsIgnoreCase("S"))
                {
                    System.out.println("[2.2] - Messaggio da inviare al server: ");
                    messaggio = tastiera.readLine();
                    System.out.println("[3] - invio: " + messaggio);
                    out.writeBytes(messaggio + "\n");
                    System.out.println("[4] - in attesa di una risposta");
                    String ricevuta = in.readLine();
                    System.out.println("[5] - risposta del server: " + ricevuta);
                } else {
                    messaggio = "fine";
                    out.writeBytes(messaggio + "\n");
                    break;
                }
            do {
                System.out.println("[2.1] - Continuare la connessione? S/N");
                chrRisposta = tastiera.readLine().toUpperCase().charAt(0);
            } while (chrRisposta != 'S' && !chrRisposta.equals('N'));
        } while (true);
    }



    public void connetti(){
        try {
            System.out.println("[0] - provo a connettermi con il server ");
            int porta = 3191;
            Socket socket = new Socket(InetAddress.getLocalHost(), porta);

            System.out.println("[1] - Connessione effettuta");
            in=new DataInputStream(socket.getInputStream());
            out=new DataOutputStream(socket.getOutputStream());



        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] arg) throws IOException {
        Client client= new Client();
        client.connetti();
        client.comunica();
    }


}
