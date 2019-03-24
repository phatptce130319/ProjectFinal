package  gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//Author: Phan Tan Phat
public class Main extends Application {

    @Override
    //The entry to the application
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/mainScreen.fxml"));
        primaryStage.setTitle("Agricultural Products Manager");
        primaryStage.setScene(new Scene(root, 1080, 680));
        primaryStage.getIcons().add(new Image("/icon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
