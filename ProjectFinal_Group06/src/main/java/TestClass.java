package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Phan Tan Phat
//The entry of the program, with the launch of GUI
//If do not need the GUI, do not run this main method.
public class TestClass extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/test.fxml"));
        } catch (LoadException ex) {
            System.out.println("Failed to load data to view");
            return;
        }
        primaryStage.setTitle("English Learner");
        if (root != null) {
            primaryStage.setScene(new Scene(root, 1280, 1024));
        }
        primaryStage.show();
    }
}
