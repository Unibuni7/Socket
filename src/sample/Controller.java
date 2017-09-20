package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;



public class Controller {




    @FXML
    private TextArea displayTextArea;

    @FXML
    private Button SendBtn;

    @FXML
    private TextArea inputTextArea;




    @FXML protected void Send(ActionEvent event) {
        try {
            Socket s = new Socket("127.0.0.1", 8001);

            displayTextArea.appendText("\n Connected");

            while(true){
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();

                Scanner in = new Scanner(input);

                PrintWriter out = new PrintWriter(output,true);

                displayTextArea.appendText("\n Welcome: " + in.nextLine());

                displayTextArea.appendText("\n Send");

                String message = inputTextArea.getText();
                out.println(message);

                displayTextArea.appendText("\n  From: " + in.nextLine());


                //s.close();


                System.out.println("closed");


            }
        } catch (IOException ex) {
            displayTextArea.appendText("\n Failed");
        }

    }


}
