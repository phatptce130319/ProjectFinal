package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DeleteDialogController {
    public static final String CUSTOMERS = "CUSTOMERS";
    public static final String PRODUCTS = "PRODUCTS";
    public static String type;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton cancelButton;

    @FXML
    private void initialize(){
        setButtonAction(type);
    }

    private void setButtonAction(String type) {
        deleteButton.setOnMouseClicked(event -> {
            switch (type) {
                case "CUSTOMERS":
                    CustomersController.isDelete = true;
                    break;
                case "PRODUCTS":
                    ProductsController.isDelete = true;
                    break;
            }

            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
        });
        cancelButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) deleteButton.getScene().getWindow();
            stage.close();
        });
    }
}
