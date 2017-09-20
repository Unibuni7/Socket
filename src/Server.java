import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[]args) {
        try {
            ServerSocket ss = new ServerSocket(8001);
            System.out.println("Server kører....");

            while(true) {
                Socket s = ss.accept();
                System.out.println("Klient forbundet.");

                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();

                Scanner in = new Scanner(input);

                PrintWriter out = new PrintWriter(output,true);

                out.println("Velkommen");

                boolean done = false;
                while (!done && in.hasNextLine()) {

                    String stream = in.nextLine();
                    if (stream.equals("luk ned")) {
                        done = true;
                    } else {
                        out.println(stream);
                    }
                }

                s.close();
                System.out.println("Forbindelsen til Klienten bliver lukket");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
