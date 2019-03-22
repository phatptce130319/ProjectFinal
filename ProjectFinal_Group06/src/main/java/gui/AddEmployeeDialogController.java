package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Employees;
import entity.EmployeesException;
import entity.EmployeesModel;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import util.FunctionLibrary;

public class AddEmployeeDialogController {
    //Declare some GUI views and connection to other tables
    private EmployeesModel em;
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
        //initialize the views and load database
        try {
            em = new EmployeesModel();
            em.loadEmployees();
        } catch (EmployeesException e) {
            e.printStackTrace();
        }
        //Set group to radio buttons
        genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        setAddAction();

    }

    //Set action on add button
    private void setAddAction(){
        addButton.setOnMouseClicked(event -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            RadioButton genderRadio = (RadioButton) genderGroup.getSelectedToggle();
            String gender = genderRadio.getText();
            try {
                EmployeesController.addEmployee = new Employees(em.getLastedIndex() + 1, name, phone, email, gender);
                Stage stage = (Stage) addButton.getScene().getWindow();
                EmployeesController.isAdd = true;
                stage.close();
            } catch (EmployeesException e) {
                FunctionLibrary.showAlertError(e.getMessage());
            }
        });
    }
}
