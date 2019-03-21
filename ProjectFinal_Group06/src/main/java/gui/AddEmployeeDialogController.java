package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Customers;
import entity.CustomersException;
import entity.Employees;
import entity.EmployeesException;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import util.FunctionLibrary;

public class AddEmployeeDialogController {
    @FXML
    private JFXTextField nameField;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField phoneField;
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
            RadioButton genderRadio = (RadioButton) genderGroup.getSelectedToggle();
            String gender = genderRadio.getText();
            try {
                EmployeesController.addEmployee = new Employees(0,name,phone,email,gender);
                Stage stage = (Stage) addButton.getScene().getWindow();
                EmployeesController.isAdd = true;
                stage.close();
            } catch (EmployeesException e) {
                FunctionLibrary.showAlertError(e.getMessage());
            }
        });
    }
}
