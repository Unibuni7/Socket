package Server;

import javafx.animation.Timeline;
import sample.Controller;

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


public ClientConnection(Socket s) throws SocketException, IOException {
    this.s = s;

}
    Controller controller = new Controller();

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



                            // here we make an if-condition and tell the program that if the stream starts with
                        // NAME: then it will execute the statement.
                    } else if (stream.startsWith("Name: ")) {
                        // here we relpace "NAME: " with an empty one, and set it equal to name variable.
                        name = new String (stream.getBytes()).replace("Name: ","");
                        // and we set the name that the user choice and print it out.
                        out.println(name+ ": " + stream);

                    } else if (stream.startsWith("PUT")) {
                        // here we do basically the same thing as we did with "NAME:"
                        addToContainer = new String(stream.getBytes()).replace("PUT","");
                        // here the user can add string variable in the ArrayList
                        stringContainer.add(name+" added "+addToContainer);
                        // here we print what the user add with his/her name attached.
                        out.println(name+" added: "+addToContainer);
                        // here we make a for each loop and display the content of the arraylist.
                        for (String a: stringContainer){
                            System.out.println(a);
                        }

                    }else if (stream.startsWith("COUNT")) {
                        // here we make a for each loop and make the countValue increase by one each time we add a new string in the arraylist
                        for (String a : stringContainer) {
                            countValue = countValue + 1;
                        }
                        // here we let countValue be equal to itself minus index, which is zero at the beginning.
                        countValue = countValue - index;
                        System.out.println(countValue);
                        out.println(countValue + " Have been added");
                        // index is not equal to countValue
                        if (index != countValue) {
                            // we set i equal to index and make a for loop
                            for (int i = index; i <countValue; i++) {
                                // here we display an element from the arraylist based on the index number.
                                out.println(stringContainer.get(i));
                                System.out.println(stringContainer.get(i));
                            }
                        }
                        // here we set index equal to countValue, so next time we run count we only get the elements that weren't displayed last time.
                        index = countValue;




                    } else if (stream.startsWith("GET: ")) {
                        // number is a string here, and we do basically what we have done before.
                        number = new String(stream.getBytes()).replace("GET: ", "");
                        // we assign index to hold the value of number
                        index = Integer.valueOf(number);
                        // and print the element that is equivalent to the index number.
                        out.println(stringContainer.get(index));
                    }else{
                        // else print ERROR and the length of the received stream.
                            int length = stream.length();

                            out.println("Received (" + length + ") chars, message is / " + stream);
                        out.println("ERROR");
                        }
                    }
                    // we could easly have made it into a switch statement which is more practical in these situations.

            } finally {
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
