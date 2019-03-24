package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Products;
import entity.ProductsException;
import entity.ProductsModel;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import util.FunctionLibrary;

//Declare some GUI fields and connect to database
public class AddProductDialogController {
    private ProductsModel pm;
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
        try {
            //Load the database
            pm = new ProductsModel();
            pm.loadProducts();
        } catch (ProductsException e) {
            e.printStackTrace();
        }
        setAddAction();
    }

    //Set actions to buttons
    private void setAddAction() {
        addButton.setOnMouseClicked(event -> {
            Products tem;
            try {
                String name = nameField.getText();
                Double price;
                Double size;
                try {
                    price = Double.parseDouble(priceField.getText());
                } catch (Exception e) {
                    throw new ProductsException("Price must not be empty");
                }
                String color = colorField.getText();
                try {
                    size = Double.parseDouble(sizeField.getText());
                } catch (Exception e) {
                    throw new ProductsException("Size must not be empty");
                }
                //Get value from GUI fields and create an object
                String description = descriptionField.getText();
                tem = new Products(pm.getLastedIndex() + 1, name, price, color, size, description);
            } catch (ProductsException e) {
                FunctionLibrary.showAlertError(e.getMessage());
                return;
            }
            try {
                //Pass value to parent
                ProductsController.addProduct = tem;
                Stage stage = (Stage) addButton.getScene().getWindow();
                ProductsController.isAdd = true;
                stage.close();
            } catch (NumberFormatException ignored) {
            }
        });
    }
}
