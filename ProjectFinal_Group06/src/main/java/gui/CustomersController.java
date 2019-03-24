package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextField;
import entity.Customers;
import entity.CustomersException;
import entity.CustomersModel;
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

//Author: Phan Tan Phat
public class CustomersController {
    //Declare some GUI views with connection to database
    static Customers addCustomer;
    private List<String> customerName;
    static boolean isAdd = false;
    private static Customers selectedItem = null;
    static boolean isDelete = false;
    private ObservableList<Customers> customerList;
    private CustomersModel cm;
    private JFXButton addButton;
    private JFXButton deleteButton;
    @FXML
    private GridPane mainFrame;
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<Customers> customersTable;
    @FXML
    private TableColumn<Customers, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customers, String> customerNameColumn;
    @FXML
    private TableColumn<Customers, Gender> genderColumn;
    @FXML
    private TableColumn<Customers, String> emailColumn;
    @FXML
    private TableColumn<Customers, String> phoneColumn;
    @FXML
    private TableColumn<Customers, String> addressColumn;
    @FXML
    private TableColumn<Customers, String> townCityColumn;
    @FXML
    private TableColumn<Customers, String> stateProvinceColumn;
    @FXML
    private TableColumn<Customers, String> countryColumn;
    @FXML
    private void initialize(){
        //Initialize the GUI views
        customersTable.setEditable(true);
        try {
            //load the data in tables
            cm = new CustomersModel();
            cm.loadCustomers();
            customerName = new ArrayList<>();
            customerName = new ArrayList<>();
            for (Customers customers : CustomersModel.sCustomersList){
                customerName.add(customers.getCustomerName());
            }
            TextFields.bindAutoCompletion(searchField,customerName);
        } catch (CustomersException e) {
            FunctionLibrary.showAlertError(e.getMessage());
            return;
        }
        customerList = FXCollections.observableList(CustomersModel.sCustomersList);
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
        // Set button click action
        setButtonClick();
        //Alter the delete button state when select
        customersTable.setRowFactory(tableView -> {
            TableRow<Customers> row = new TableRow<>();
            // If a row of our table is clicked...
            row.setOnMouseClicked(mouseEvent -> {
                selectedItem = row.getItem();
                deleteButton.setDisable(false);
            });
            return row;
        });
    }

    //Mapping data from database to table
    private void mappingData(){
        customerIdColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleIntegerProperty(cellData.getValue().getCustomerId()).asObject();
            } catch (CustomersException e) {
                e.printStackTrace();
                return new SimpleIntegerProperty(-1).asObject();
            }
        });
        customerNameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getCustomerName());
            } catch (CustomersException e) {
                e.printStackTrace();
                return null;
            }
        });
        genderColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleObjectProperty<>(Gender.getByCode(cellData.getValue().getGender()));
            } catch (CustomersException e) {
                e.printStackTrace();
                return null;
            }
        });

        emailColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getEmailAddress());
            } catch (CustomersException e) {
                e.printStackTrace();
                return null;
            }
        });

        phoneColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getPhoneNumber());
            } catch (CustomersException e) {
                e.printStackTrace();
                return null;
            }
        });

        addressColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getAddressLine());
            } catch (CustomersException e) {
                e.printStackTrace();
                return null;
            }
        });

        townCityColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getTownCity());
            } catch (CustomersException e) {
                e.printStackTrace();
                return null;
            }
        });

        stateProvinceColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getStateCountyProvince());
            } catch (CustomersException e) {
                e.printStackTrace();
                return null;
            }
        });

        countryColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getCountry());
            } catch (CustomersException e) {
                e.printStackTrace();
                return null;
            }
        });
        FilteredList<Customers> filteredData = new FilteredList<>(customerList, p -> true);


        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(customer -> {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Get the filter string
            String lowerCaseFilter = newValue.toLowerCase();

            try {
                if (customer.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name or id
                } else if (customer.getCustomerId().toString().equals(lowerCaseFilter)) {
                    return true;
                }
            } catch (CustomersException e) {
                e.printStackTrace();
            }
            return false; // Does not match.
        }));

        SortedList<Customers> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(customersTable.comparatorProperty());

        customersTable.setItems(sortedData);

    }
    private void setEditTable(){
        setEditableColumn();
    }

    private void setEditableColumn() {
        //Set the column editable
        customerNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        customerNameColumn.setOnEditCommit(event -> setEditOnColumn(event,2));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> setEditOnColumn(event,4));
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(event -> setEditOnColumn(event,5));
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        addressColumn.setOnEditCommit(event -> setEditOnColumn(event,6));
        townCityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        townCityColumn.setOnEditCommit(event -> setEditOnColumn(event,7));
        stateProvinceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        stateProvinceColumn.setOnEditCommit(event -> setEditOnColumn(event,8));
        countryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        countryColumn.setOnEditCommit(event -> setEditOnColumn(event,9));
        ObservableList<Gender> genderList = FXCollections.observableArrayList(//
                Gender.values());
        genderColumn.setCellValueFactory(param -> {
            Customers customer = param.getValue();
            // Female , Male combo box
            String genderCode = null;
            try {
                genderCode = customer.getGender();
            } catch (CustomersException e) {
                e.printStackTrace();
            }
            Gender gender = Gender.getByCode(genderCode);
            return new SimpleObjectProperty<>(gender);
        });
        genderColumn.setCellFactory(ComboBoxTableCell.forTableColumn(genderList));
        genderColumn.setOnEditCommit((TableColumn.CellEditEvent<Customers, Gender> event) -> {
            TablePosition<Customers, Gender> pos = event.getTablePosition();
            Gender newGender = event.getNewValue();
            int row = pos.getRow();
            Customers customer = event.getTableView().getItems().get(row);
            try {
                customer.setGender(newGender.getCode());
                cm.updateCustomer(customer.getCustomerId(),customer.getCustomerName(),customer.getGender(),customer.getEmailAddress(),customer.getPhoneNumber(),customer.getAddressLine(),customer.getTownCity(),customer.getStateCountyProvince(),customer.getCountry());
            } catch (CustomersException e) {
                e.printStackTrace();
            }
        });
    }
    private void setEditOnColumn(TableColumn.CellEditEvent<Customers,String> event,int columnIndex) {
        TablePosition<Customers, String> pos = event.getTablePosition();
        String value = event.getNewValue();
        int row = pos.getRow();
        Customers customer = event.getTableView().getItems().get(row);
        try {
            switch (columnIndex){
                case 2:
                    //Set which field to change values
                    customer.setCustomerName(value);
                    break;
                case 4:
                    customer.setEmailAddress(value);
                    break;
                case 5:
                    customer.setPhoneNumber(value);
                    break;
                case 6:
                    customer.setAddressLine(value);
                    break;
                case 7:
                    customer.setTownCity(value);
                    break;
                case 8:
                    customer.setStateCountyProvince(value);
                    break;
                case 9:
                    customer.setCountry(value);
                    break;
            }
            cm.updateCustomer(customer.getCustomerId(),customer.getCustomerName(),customer.getGender(),customer.getEmailAddress(),customer.getPhoneNumber(),customer.getAddressLine(),customer.getTownCity(),customer.getStateCountyProvince(),customer.getCountry());
        } catch (CustomersException e) {
            FunctionLibrary.showAlertError(e.getMessage());
        }
    }
    private void setButtonClick(){
        //Set button click add and delete
        addButton.setOnMouseClicked(event -> {
            //launch the add dialog
            FunctionLibrary.setUpNewWindows("/add_dialog.fxml","Add Customer Dialog");
            if (isAdd) {
                try {
                    //Get the data back, add to database and map to GUI view
                    cm.addCustomer(addCustomer.getCustomerName(),addCustomer.getGender(),addCustomer.getEmailAddress(),addCustomer.getPhoneNumber(),addCustomer.getAddressLine(),addCustomer.getTownCity(),addCustomer.getStateCountyProvince(),addCustomer.getCountry());
                    customerList.add(addCustomer);
                    customerName.add(addCustomer.getCustomerName());
                    TextFields.bindAutoCompletion(searchField,customerName);

                } catch (CustomersException e) {
                    e.printStackTrace();
                }
            }
            isAdd = false;
        });
        deleteButton.setOnMouseClicked(event -> {
            //Launch the delete dialog
            FunctionLibrary.setUpNewWindows("/delete_dialog.fxml","Delete Customer Dialog");
            DeleteDialogController.type = DeleteDialogController.CUSTOMERS;
            if (isDelete) {
                //Remove an item from database and GUI views
                customerList.remove(selectedItem);
                try {
                    cm.deleteCustomer(selectedItem.getCustomerId());
                } catch (CustomersException e) {
                    e.printStackTrace();
                }
            }
            isDelete = false;
        });
    }

}
