package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextField;
import entity.Employees;
import entity.EmployeesException;
import entity.EmployeesModel;
import entity.Gender;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.textfield.TextFields;
import util.FunctionLibrary;

import java.util.ArrayList;
import java.util.List;

public class EmployeesController {
    private List<String> employeesName;
    static Employees addEmployee;
    static boolean isAdd = false;
    private static Employees selectedItem = null;
    static boolean isDelete = false;
    private ObservableList<Employees> employeesList;
    private EmployeesModel em;
    private JFXButton addButton;
    private JFXButton deleteButton;
    @FXML
    private GridPane mainFrame;
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<Employees> employeesTable;
    @FXML
    private TableColumn<Employees, Integer> idColumn;
    @FXML
    private TableColumn<Employees, String> nameColumn;
    @FXML
    private TableColumn<Employees, Gender> genderColumn;
    @FXML
    private TableColumn<Employees, String> emailColumn;
    @FXML
    private TableColumn<Employees, String> phoneColumn;
    @FXML
    private void initialize(){
        employeesTable.setEditable(true);
        try {
            em = new EmployeesModel();
            em.loadEmployees();
            employeesName = new ArrayList<>();
            for (Employees employees : EmployeesModel.sEmployeesList){
                employeesName.add(employees.getEmployeeName());
            }
            TextFields.bindAutoCompletion(searchField,employeesName);
        } catch (EmployeesException ignored) {
        }
        employeesList = FXCollections.observableList(EmployeesModel.sEmployeesList);
        mappingData();
        setEditTable();
        addButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/add_icon.png"))));
        addButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        addButton.setButtonType(JFXButton.ButtonType.RAISED);
        deleteButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/delete_icon.png"))));
        deleteButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        deleteButton.setButtonType(JFXButton.ButtonType.RAISED);
        deleteButton.setDisable(true);
        JFXButton menuButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/setting_icon.png"))));
        menuButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setRotate(90);
        nodesList.addAnimatedNode(menuButton);
        nodesList.addAnimatedNode(addButton);
        nodesList.addAnimatedNode(deleteButton);
        mainFrame.add(nodesList,1,2);
        GridPane.setValignment(nodesList, VPos.CENTER);
        GridPane.setHalignment(nodesList, HPos.RIGHT);
        GridPane.setMargin(nodesList,new Insets(0,15,0,0));
        setButtonClick();
        employeesTable.setRowFactory(tableView -> {
            TableRow<Employees> row = new TableRow<>();
            // If a row of our table is clicked...
            row.setOnMouseClicked(mouseEvent -> {
                selectedItem = row.getItem();
                deleteButton.setDisable(false);
            });
            return row;
        });
    }
    private void mappingData(){
        idColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject();
            } catch (EmployeesException e) {
                e.printStackTrace();
                return new SimpleIntegerProperty(-1).asObject();
            }
        });
        nameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getEmployeeName());
            } catch (EmployeesException e) {
                e.printStackTrace();
                return null;
            }
        });
        genderColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleObjectProperty<>(Gender.getByCode(cellData.getValue().getGender()));
            } catch (EmployeesException e) {
                e.printStackTrace();
                return null;
            }
        });

        emailColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getEmailAddress());
            } catch (EmployeesException e) {
                e.printStackTrace();
                return null;
            }
        });

        phoneColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getPhoneNumber());
            } catch (EmployeesException e) {
                e.printStackTrace();
                return null;
            }
        });


        FilteredList<Employees> filteredData = new FilteredList<>(employeesList, p -> true);


        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(employee -> {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            try {
                if (employee.getEmployeeName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (employee.getEmployeeId().toString().equals(lowerCaseFilter)) {
                    return true;
                }
            } catch (EmployeesException e) {
                e.printStackTrace();
            }
            return false; // Does not match.
        }));

        SortedList<Employees> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(employeesTable.comparatorProperty());

        employeesTable.setItems(sortedData);

    }
    private void setEditTable(){
        setEditableColumn();
    }

    private void setEditableColumn() {
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> setEditOnColumn(event,2));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> setEditOnColumn(event,4));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(event -> setEditOnColumn(event,5));
        ObservableList<Gender> genderList = FXCollections.observableArrayList(//
                Gender.values());
        genderColumn.setCellValueFactory(param -> {
            Employees employee = param.getValue();
            // F,M
            String genderCode = null;
            try {
                genderCode = employee.getGender();
            } catch (EmployeesException e) {
                e.printStackTrace();
            }
            Gender gender = Gender.getByCode(genderCode);
            return new SimpleObjectProperty<>(gender);
        });
        genderColumn.setCellFactory(ComboBoxTableCell.forTableColumn(genderList));
        genderColumn.setOnEditCommit((TableColumn.CellEditEvent<Employees, Gender> event) -> {
            TablePosition<Employees, Gender> pos = event.getTablePosition();
            Gender newGender = event.getNewValue();
            int row = pos.getRow();
            Employees employee = event.getTableView().getItems().get(row);
            try {
                employee.setGender(newGender.getCode());
                em.updateEmployee(employee.getEmployeeId(),employee.getEmployeeName(),employee.getPhoneNumber(),employee.getEmailAddress(),employee.getGender());
            } catch (EmployeesException e) {
                e.printStackTrace();
            }
        });
    }
    private void setEditOnColumn(TableColumn.CellEditEvent<Employees,String> event,int columnIndex) {
        TablePosition<Employees, String> pos = event.getTablePosition();
        String value = event.getNewValue();
        int row = pos.getRow();
        Employees employee = event.getTableView().getItems().get(row);
        try {
            switch (columnIndex){
                case 2:
                    employee.setEmployeeName(value);
                    break;
                case 4:
                    employee.setEmailAddress(value);
                    break;
                case 5:
                    employee.setPhoneNumber(value);
                    break;
            }
            em.updateEmployee(employee.getEmployeeId(),employee.getEmployeeName(),employee.getPhoneNumber(),employee.getEmailAddress(),employee.getGender());
        } catch (EmployeesException e) {
            e.printStackTrace();
        }
    }
    private void setButtonClick(){
        addButton.setOnMouseClicked(event -> {
            FunctionLibrary.setUpNewWindows("/add_employee_dialog.fxml","Add Employee Dialog");
            if (isAdd) {
                try {
                    em.addEmployee(addEmployee.getEmployeeName(),addEmployee.getPhoneNumber(),addEmployee.getEmailAddress(),addEmployee.getGender());
                    employeesList.add(addEmployee);
                    employeesName.add(addEmployee.getEmployeeName());
                    TextFields.bindAutoCompletion(searchField,employeesName);
                } catch (EmployeesException e) {
                    e.printStackTrace();
                }
            }
            isAdd = false;
        });
        deleteButton.setOnMouseClicked(event -> {
            FunctionLibrary.setUpNewWindows("/delete_dialog.fxml","Delete Employee Dialog");
            DeleteDialogController.type = DeleteDialogController.EMPLOYEES;
            if (isDelete) {
                employeesList.remove(selectedItem);
                try {
                    em.deleteEmployee(selectedItem.getEmployeeId());
                } catch (EmployeesException e) {
                    e.printStackTrace();
                }
            }
            isDelete = false;
        });
    }

}
