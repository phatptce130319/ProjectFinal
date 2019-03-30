package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

//Phan Tan Phat
//My defined of some useful functions
public final class FunctionLibrary {
    private static final String WORD_SEPARATOR = " ";

    private FunctionLibrary() {
    }
    //Convert string to title case
    public static String convertToTitleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Arrays
                .stream(text.split(WORD_SEPARATOR))
                .map(word -> word.isEmpty()
                        ? word
                        : Character.toTitleCase(word.charAt(0)) + word
                        .substring(1)
                        .toLowerCase())
                .collect(Collectors.joining(WORD_SEPARATOR));
    }

    //Show alert dialog when error
    public static void showAlertError(String alertInfo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Error happened");
        alert.setContentText(alertInfo);
        alert.showAndWait();
    }

    public static void setUpNewWindows(String resource, String windowName, int width, int height) {
        Parent root = null;
        try {
            root = FXMLLoader.load(FunctionLibrary.class.getResource(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(windowName);
        stage.setScene(new Scene(Objects.requireNonNull(root), width, height));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.showAndWait();
    }
}
