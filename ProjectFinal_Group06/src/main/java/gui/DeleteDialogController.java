package gui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DeleteDialogController {
    //Declare some GUI views, and some code to find out what to delete
    public static final String CUSTOMERS = "CUSTOMERS";
    public static final String PRODUCTS = "PRODUCTS";
    public static final String EMPLOYEES = "EMPLOYEES";
    public static final String ORDERS = "ORDERS";
    public static String type;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton cancelButton;

    @FXML
    private void initialize(){
        setButtonAction();
    }
    private void setButtonAction() {
        deleteButton.setOnMouseClicked(event -> {
            switch (type) {
                //Delete will alter depends on type of delete, save time for creating isolating frames
                case "CUSTOMERS":
                    CustomersController.isDelete = true;
                    break;
                case "PRODUCTS":
                    ProductsController.isDelete = true;
                    break;
                case "EMPLOYEES":
                    EmployeesController.isDelete = true;
                    break;
                case "ORDERS":
                    OrdersController.isDelete = true;
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
