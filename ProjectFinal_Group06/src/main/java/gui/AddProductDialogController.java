package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Products;
import entity.ProductsException;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import util.FunctionLibrary;

public class AddProductDialogController {
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField priceField;
    @FXML
    private JFXTextField colorField;
    @FXML
    private JFXTextField sizeField;
    @FXML
    private JFXTextField descriptionField;
    @FXML
    private JFXButton addButton;

    @FXML
    private void initialize() {
        setAddAction();
    }

    private void setAddAction() {
        addButton.setOnMouseClicked(event -> {
            String name = nameField.getText();
            String price = priceField.getText();
            String color = colorField.getText();
            String size = sizeField.getText();
            String description = descriptionField.getText();
            try {
                ProductsController.addProduct = new Products(0, name, Double.parseDouble(price), color, Double.parseDouble(size), description);
                Stage stage = (Stage) addButton.getScene().getWindow();
                ProductsController.isAdd = true;
                stage.close();
            } catch (ProductsException e) {
                FunctionLibrary.showAlertError(e.getMessage());
            }
        });
    }
}
