package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Customers;
import entity.CustomersException;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import util.FunctionLibrary;

public class AddDialogController {
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField phoneField;
    @FXML
    private JFXTextField addressField;
    @FXML
    private JFXTextField townCityField;
    @FXML
    private JFXTextField stateCountyProvinceField;
    @FXML
    private JFXTextField countryField;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private JFXButton addButton;
    private ToggleGroup genderGroup;
    @FXML
    private void initialize(){
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        setAddAction();

    }
    private void setAddAction(){
        addButton.setOnMouseClicked(event -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String townCity = townCityField.getText();
            String stateCountyProvince = stateCountyProvinceField.getText();
            String country = countryField.getText();
            RadioButton genderRadio = (RadioButton) genderGroup.getSelectedToggle();
            String gender = genderRadio.getText();
            try {
                CustomersController.addCustomer = new Customers(0,name,gender,email,phone,address,townCity,stateCountyProvince,country);
                Stage stage = (Stage) addButton.getScene().getWindow();
                CustomersController.isAdd = true;
                stage.close();
            } catch (CustomersException e) {
                FunctionLibrary.showAlertError(e.getMessage());
            }
        });
    }
}
