package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;


public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Socket");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

    }

     private static Controller c = new Controller();


    public static void main(String[] args) {
        launch(args);


    }

   /* private static void runTimer() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                c.displayTextArea.appendText("Abdul");
            }




        };
        Timer timer = new Timer("timer");
        timer.schedule(timerTask,1000,1000);
    }*/
}



