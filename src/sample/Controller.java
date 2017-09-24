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
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;



public class Controller {


    private Socket s;
    private OutputStream output;
    private InputStream input;
    private PrintWriter out;



    @FXML
    private TextArea displayTextArea;

    @FXML
    private Button SendBtn;

    @FXML
    private TextArea inputTextArea;

    @FXML
    Button Connectbtn;

    @FXML
    Button putButton;



    @FXML
    protected void Send(ActionEvent event) {
        try {
                String message = inputTextArea.getText();
                out.println(message);
                Scanner in = new Scanner(input);
                displayTextArea.appendText("\n " + in.nextLine());

            }catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    @FXML
    protected void Connect(ActionEvent event) {
        try {
           this.s = new Socket("127.0.0.1", 8001);

            displayTextArea.appendText("\n Connected");


              this.input =  this.s.getInputStream();
              this.output =  this.s.getOutputStream();

                Scanner in = new Scanner(this.input);

               this.out =  new PrintWriter(this.output, true);
                System.out.println("Virker");

                displayTextArea.appendText(" \n" + in.nextLine());



             // this.s.close();;



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML protected void Disconnect(ActionEvent event) {

        try {
            this.s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("closed");



    }


    @FXML protected void Count(ActionEvent event) {


       try {
           this.input = this.s.getInputStream();
           this.output = this.s.getOutputStream();
           Scanner in = new Scanner(this.input);
           this.out = new PrintWriter(this.output,true);
           this.out.println("COUNT");
           displayTextArea.appendText(in.nextLine() + " \n");

       } catch (IOException ex) {

       }


    }

    @FXML
    protected void Put(){
        try {
            String putter = "PUT "+inputTextArea.getText();
            this.input = this.s.getInputStream();
            this.output = this.s.getOutputStream();
            Scanner in = new Scanner(this.input);
            this.out = new PrintWriter(this.output, true);

            out.println(putter);

        }catch (Exception e) {
            System.out.println("Error: "+ e);
        }
    }

}