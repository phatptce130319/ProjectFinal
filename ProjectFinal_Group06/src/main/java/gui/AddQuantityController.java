package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import util.FunctionLibrary;

public class AddQuantityController {
    //Declare GUI views
    //Ha Van Ngoan
    @FXML
    JFXTextField quantityField;
    @FXML
    JFXButton confirmButton;
    @FXML
    JFXButton cancelButton;
    @FXML
    private void initialize(){
        //Set click action and pass value to parent
            confirmButton.setOnMouseClicked(event -> {
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                } catch (Exception e) {
                    FunctionLibrary.showAlertError("Only integer numbers are accepted");
                    return;
                }
                AddOrderController.quantity = quantity;
                AddOrderController.isAdd = true;
                OrdersController.isBought = true;
                Stage stage = (Stage) confirmButton.getScene().getWindow();
                stage.close();
            });
            cancelButton.setOnMouseClicked(event -> {
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            });
    }
}
