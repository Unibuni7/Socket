package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {




    public Socket s;
    public OutputStream output;
    public InputStream input;
    public PrintWriter out;




    @FXML
    public TextArea displayTextArea;

    @FXML
    private Button SendBtn;

    @FXML
    private TextArea inputTextArea;

    @FXML
    Button Connectbtn;

    @FXML
    Button putButton;

   /* protected void runTimer() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                 displayTextArea.appendText("Abdul");

            }




        };
        Timer timer = new Timer("timer");
        timer.schedule(timerTask,1000,1000);
    }*/
    Timeline fivesec = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
displayTextArea.appendText("Abdul");
            try {
                input = s.getInputStream();
                output = s.getOutputStream();
                Scanner in = new Scanner(input);
                out = new PrintWriter(output,true);
                out.println("COUNT");
                displayTextArea.appendText(in.nextLine() + " \n");



            } catch (IOException ex) {

            }}
    }));





    private void Timer() throws InterruptedException {

        //   Timer timer = new Timer();

        //  sample.Timer time = new sample.Timer();

        // timer.scheduleAtFixedRate(time,5000,5000); }


    }


    @FXML
    protected void Send(ActionEvent event) throws InterruptedException {
     //   Timer();
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
                fivesec.setCycleCount(Timeline.INDEFINITE);
           fivesec.play();



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