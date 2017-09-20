import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientConnection implements Runnable {
private Socket s;

public ClientConnection(Socket s) throws SocketException, IOException {
    this.s = s;
}

    @Override
    public void run() {
        try {
            try {
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();
                Scanner in = new Scanner(input);

                PrintWriter out = new PrintWriter(output,true);

                out.println("Velkommen");

                boolean done = false;

                while (!done && in.hasNextLine()){
                    String stream = in.nextLine();
                    if (stream.equals("luk ned")){
                        done = true;
                    } else {
                        int length = stream.length();

                        out.println("Received (" + length + ") chars, message is / " + stream);
                    }
                }
            } finally {
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
