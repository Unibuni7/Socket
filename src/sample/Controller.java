package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.util.Duration;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;



public class Controller {

    // In this class we control our gui.



    // we start with initialising our variables
    private Socket s;
    private OutputStream output;
    private InputStream input;
    private PrintWriter out;
    private Scanner in;
    private int counter = 0;
    private int oldcount = 0;




// Here are all the elements from JAVAFX that we are going to use in the controller.
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



  // Here is our timer it works fine, in the beginning we wanted to use TimerTask but we found out the for JAVAFX we have to use Timeline.
    // Timeline works fine the problem we have is with the code for COUNT.
    // we call the Timeline method from the PUT button method.
    Timeline sevensec = new Timeline(new KeyFrame(Duration.seconds(7), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                input = s.getInputStream();
                output = s.getOutputStream();
                in = new Scanner(input);
                out = new PrintWriter(output, true);

            }catch (Exception e) {
                e.printStackTrace();
            }

            out.println("COUNT");
            displayTextArea.appendText(in.nextLine() + " \n");
            displayTextArea.appendText(in.nextLine() + "\n");
            displayTextArea.appendText(in.nextLine()+ "\n");


        }
         // we have problems with the code.



        }));




    @FXML
    protected void Send(ActionEvent event) throws InterruptedException {

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
    protected void Connect(ActionEvent event) throws InterruptedException {

        try {
           this.s = new Socket("127.0.0.1", 8001);

            displayTextArea.appendText("\n Connected");


              this.input =  this.s.getInputStream();
              this.output =  this.s.getOutputStream();

                Scanner in = new Scanner(this.input);

               this.out =  new PrintWriter(this.output, true);
                System.out.println("Virker");

                displayTextArea.appendText(" \n" + in.nextLine());








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
            in = new Scanner(this.input);
            this.out = new PrintWriter(this.output, true);

        }catch (Exception e) {
                e.printStackTrace();
        }

        this.out.println("COUNT");
        displayTextArea.appendText(in.nextLine() + " \n");
        displayTextArea.appendText(in.nextLine() + "\n");
        displayTextArea.appendText(in.nextLine()+ "\n");


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
            counter++;

            // here we start our timer
              sevensec.setCycleCount(Timeline.INDEFINITE);
           sevensec.play();

        }catch (Exception e) {
            System.out.println("Error: "+ e);
        }
    }

}