import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientConnection implements Runnable {
private Socket s;

    //variables
    private static ArrayList<String> stringContainer = new ArrayList<>();
    private static String addToContainer;
    private static int countValue;
    private static String Name = "Guest";

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

                    if (stream.startsWith("Name: ")) {
                        Name = new String (stream.getBytes()).replace("Name: ","");
                        out.println(Name+ ": " + stream);

                    } else {
                        out.println(Name + ": "+ stream);

                    }
                    if (stream.equals("luk ned")){
                        done = true;
                    } else if (stream.startsWith("PUT")) {
                        addToContainer = new String(stream.getBytes()).replace("PUT","");
                        stringContainer.add(addToContainer);
                        out.println("added: "+addToContainer);

                        for (String a: stringContainer){
                            System.out.println(a);

                        }
                    }else if (stream.startsWith("COUNT")) {
                        for (String a: stringContainer) {
                            countValue = countValue+1;

                        }
                        System.out.println(countValue);
                        out.println(countValue+" Have been added");
                        countValue = 0;

                    }else{
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
