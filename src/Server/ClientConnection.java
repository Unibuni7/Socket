package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientConnection implements Runnable {
private Socket s;

// Here we have our ClientConnection that will allow us to communicate with our server.

    //variables
    private static ArrayList<String> stringContainer = new ArrayList<>();
    private static String addToContainer;
    private static int countValue;
    private static String name = "Guest";
    private static int index = 0;
    private static String number;
    private static Writer writer;

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


                    } else if (stream.startsWith("Name: ")) {
                        name = new String (stream.getBytes()).replace("Name: ","");
                        out.println(name+ ": " + stream);

                    } else if (stream.startsWith("PUT")) {
                        addToContainer = new String(stream.getBytes()).replace("PUT","");
                        stringContainer.add(name+" added "+addToContainer);
                        out.println(name+" added: "+addToContainer);

                        for (String a: stringContainer){
                            System.out.println(a);

                        }
                    }else if (stream.startsWith("COUNT")) {
                        for (String a : stringContainer) {
                            countValue = countValue + 1;


                        }
                        countValue = countValue - index;
                        System.out.println(countValue);
                        out.println(countValue + " Have been added");
                        if (index != countValue) {
                            for (int i = index; i <countValue; i++) {
                                out.println(stringContainer.get(i));
                                System.out.println(stringContainer.get(i));


                            }



                        }
                        index = countValue;




                    } else if (stream.startsWith("GET: ")) {
                        number = new String(stream.getBytes()).replace("GET: ", "");
                        index = Integer.valueOf(number);
                        out.println(stringContainer.get(index));




                    }else{
                            //int length = stream.length();

                           // out.println("Received (" + length + ") chars, message is / " + stream);
                        out.println("ERROR");
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
