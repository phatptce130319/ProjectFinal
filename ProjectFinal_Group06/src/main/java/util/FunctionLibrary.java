package util;

import javafx.scene.control.Alert;

import java.util.Arrays;
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
}
