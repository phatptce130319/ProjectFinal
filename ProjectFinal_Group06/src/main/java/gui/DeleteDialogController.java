package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DeleteDialogController {
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private void initialize(){
        deleteButton.setOnMouseClicked(event -> {
            CustomersController.isDelete = true;
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
        });
        cancelButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
        });
    }
}
