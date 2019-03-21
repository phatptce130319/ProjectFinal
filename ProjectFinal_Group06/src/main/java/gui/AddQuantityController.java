package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AddQuantityController {
    @FXML
    JFXTextField quantityField;
    @FXML
    JFXButton confirmButton;
    @FXML
    JFXButton cancelButton;
    @FXML
    private void initialize(){
            confirmButton.setOnMouseClicked(event -> {
                AddOrderController.quantity = Integer.parseInt(quantityField.getText());
                AddOrderController.isAdd = true;
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();
            });
            cancelButton.setOnMouseClicked(event -> {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            });
    }
}
