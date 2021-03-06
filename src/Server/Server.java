package Server;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    //variables
    private static ArrayList<String> stringContainer = new ArrayList<>();
    private static String addToContainer;
    private static int countValue;
    private static String name = "Guest";

    public static String getName() {
        return name;
    }

    public static void main(String[]args) {

        //initialize variables
        countValue = 0;

        //Try block to establish server
        try {
            ServerSocket ss = new ServerSocket(8001);
            System.out.println("Server.Server kører....");

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

                    if (stream.startsWith("Name: ")) {
                        name = new String (stream.getBytes()).replace("Name: ","");
                        out.println(name+ ": " + stream);

                    } else {
                        out.println(name + ": "+ stream);

                    }
                    if (stream.equals("luk ned")) {
                        done = true;
                    }else if (stream.startsWith("PUT")){
                        addToContainer = new String(stream.getBytes()).replace("PUT","");
                        stringContainer.add(name+" "+addToContainer);
                        out.println("added: "+addToContainer);
                        for (String a: stringContainer){
                            System.out.println(a);

                        }
                    }else if(stream.startsWith("COUNT")) {

                        for (String a: stringContainer) {
                            countValue = countValue+1;

                        }
                        System.out.println(countValue);
                        out.println(countValue+" Have been added");
                        countValue = 0;
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
