package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Base64;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        primaryStage.setTitle("Encriptator 3K");
        primaryStage.setScene(new Scene(root, 640, 380));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
