package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import util.FunctionLibrary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AddOrderController {
    //Declare some GUI views and connection to other database
    //Phan Tan Phat
    public static int quantity;
    static boolean isAdd = false;
    private static Products selectedItem = null;
    private ObservableList<Products> productsList;
    private ProductsModel pm;
    private OrderItemsModel oim;
    private OrdersModel om;
    private CustomersModel cm;
    private EmployeesModel em;
    @FXML
    private GridPane mainFrame;
    @FXML
    private JFXTextField customerPhone;
    @FXML
    private JFXTextField orderAddress;
    @FXML
    private JFXTextField creatorPhone;
    @FXML
    private JFXButton doneButton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<Products> productsTable;
    @FXML
    private TableColumn<Products, String> productsNameColumn;
    @FXML
    private TableColumn<Products, String> productsPriceColumn;
    @FXML
    private TableColumn<Products, String> productsSizeColumn;
    @FXML
    private TableColumn<Products, String> productsColorColumn;
    private List<String> customerPhoneList;
    private List<String> employeesPhoneList;

    //sort data , and create a filter to search for item
    static void sortData(FilteredList<Products> filteredData, JFXTextField searchField, TableView<Products> productsTable) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(product -> {
            // If filter text is empty, display all data.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            //get the value, and match all case
            String lowerCaseFilter = newValue.toLowerCase();

            try {
                if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches id or  name.
                } else if (product.getProductId().toString().equals(lowerCaseFilter)) {
                    return true;
                }
            } catch (ProductsException e) {
                e.printStackTrace();
            }
            return false; // Does not match.
        }));

        SortedList<Products> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(productsTable.comparatorProperty());

        productsTable.setItems(sortedData);
    }

    //A function to set which field of an item is altered
    static void setIndex(int columnIndex, String value, Products product, ProductsModel pm) {
        try {
            switch (columnIndex) {
                case 2:
                    product.setProductName(value);
                    break;
                case 3:
                    try {
                        product.setProductPrice(Double.parseDouble(value));
                    } catch (NumberFormatException e) {
                        throw new ProductsException("Only accept number in product price");
                    }
                    break;
                case 4:
                    product.setProductColor(value);
                    break;
                case 5:
                    try {
                        product.setProductSize(Double.parseDouble(value));
                    } catch (NumberFormatException e) {
                        throw new ProductsException("Only accept number in product size");
                    }
                    break;
                case 6:
                    product.setProductDescription(value);
                    break;
            }
            pm.updateProduct(product.getProductId(), product.getProductName(), product.getProductPrice(), product.getProductColor(), product.getProductSize(), product.getProductDescription());
        } catch (ProductsException e) {
            FunctionLibrary.showAlertError(e.getMessage());
        }
    }

    //Initialize the view and load the tables
    @FXML
    private void initialize() {
        try {
            pm = new ProductsModel();
            oim = new OrderItemsModel();
            om = new OrdersModel();
            cm = new CustomersModel();
            em = new EmployeesModel();
            oim.loadOrderItems();
            pm.loadProducts();
            cm.loadCustomers();
            em.loadEmployees();
            customerPhoneList = new ArrayList<>();
            employeesPhoneList = new ArrayList<>();
        } catch (ProductsException | OrderItemsException | OrdersException | CustomersException | EmployeesException ignored) {
        }
        for (Employees employees : EmployeesModel.sEmployeesList) {
            try {
                employeesPhoneList.add(employees.getPhoneNumber());
            } catch (EmployeesException e) {
                e.printStackTrace();
            }
        }
        TextFields.bindAutoCompletion(creatorPhone, employeesPhoneList);
        for (Customers customers : CustomersModel.sCustomersList) {
            try {
                customerPhoneList.add(customers.getPhoneNumber());
            } catch (CustomersException e) {
                e.printStackTrace();
            }
        }
        TextFields.bindAutoCompletion(customerPhone, customerPhoneList);

        //Map action to a button
        doneButton.setOnMouseClicked(event -> {
            //Get data from views and create an object
            OrdersController.isAdd = true;
            Orders temp;
            String cusPhone, emPhone;
            try {
                cusPhone = customerPhone.getText().trim();
                emPhone = creatorPhone.getText().trim();
                temp = new Orders(om.getLastedIndex() + 1, cm.getID(cusPhone), em.getID(emPhone), new java.sql.Date(System.currentTimeMillis()), orderAddress.getText());
                OrdersController.addOrder = temp;
            } catch (OrdersException | CustomersException | EmployeesException e) {
                FunctionLibrary.showAlertError(e.getMessage());
                return;
            }
            Stage stage = (Stage) doneButton.getScene().getWindow();
            stage.close();
        });
        //Mapping data to views
        productsList = FXCollections.observableList(ProductsModel.sProductsList);
        mappingData();
        setEditTable();
        productsTable.setRowFactory(tableView -> {
            TableRow<Products> row = new TableRow<>();
            // If a row of our table is clicked...
            row.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2) {
                    selectedItem = row.getItem();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(FunctionLibrary.class.getResource("/quantity_product.fxml"));
                    } catch (IOException ignored) {
                    }
                    Stage stage = new Stage();
                    stage.setTitle("Confirm buying");
                    stage.setScene(new Scene(Objects.requireNonNull(root), 450, 300));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.setFullScreen(false);
                    stage.showAndWait();
                    if (isAdd) {
                        try {
                            //Create an order item add to the order
                            OrderItems temp = new OrderItems(-1, om.getLastedIndex() + 1, selectedItem.getProductId(), selectedItem.getProductPrice(), quantity);
                            OrdersController.listItem.add(temp);
                        } catch (ProductsException | OrderItemsException e) {
                            FunctionLibrary.showAlertError(e.getMessage());
                        }
                    }
                }
            });
            return row;
        });
    }

    private void setEditTable() {
        setEditableColumn();
    }

    private void setEditableColumn() {
        //Allow table is editable
        productsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsNameColumn.setOnEditCommit(event -> setEditOnColumn(event, 2));
        productsPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsPriceColumn.setOnEditCommit(event -> setEditOnColumn(event, 4));
        productsColorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsColorColumn.setOnEditCommit(event -> setEditOnColumn(event, 3));
        productsSizeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsSizeColumn.setOnEditCommit(event -> setEditOnColumn(event, 5));

    }

    //After data change map to the table again
    private void mappingData() {
        productsNameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getProductName());
            } catch (ProductsException e) {

                return null;
            }
        });
        productsPriceColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getProductPrice().toString());
            } catch (ProductsException e) {
                e.printStackTrace();
                return null;
            }
        });
        productsColorColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getProductColor());
            } catch (ProductsException e) {
                e.printStackTrace();
                return null;
            }
        });
        productsSizeColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getProductSize().toString());
            } catch (ProductsException e) {
                e.printStackTrace();
                return null;
            }
        });

        FilteredList<Products> filteredData = new FilteredList<>(productsList, p -> true);
        sortData(filteredData, searchField, productsTable);

    }

    //Set editable columns
    private void setEditOnColumn(TableColumn.CellEditEvent<Products, String> event, int columnIndex) {
        TablePosition<Products, String> pos = event.getTablePosition();
        String value = event.getNewValue();
        int row = pos.getRow();
        Products product = event.getTableView().getItems().get(row);
            setIndex(columnIndex, value, product, pm);
    }

}
