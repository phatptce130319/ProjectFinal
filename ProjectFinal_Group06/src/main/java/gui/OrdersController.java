package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.textfield.TextFields;
import util.FunctionLibrary;

import java.util.ArrayList;
import java.util.List;

public class OrdersController {
    public static List<OrderItems> listItem;
    static Orders addOrder;
    static boolean isBought;
    static boolean isAdd = false;
    public static Orders selectedItem = null;
    static boolean isDelete = false;
    private ObservableList<Orders> ordersList;
    private OrdersModel om;
    private CustomersModel cm;
    private EmployeesModel em;
    private OrderItemsModel oim;
    private JFXButton addButton;
    private JFXButton deleteButton;
    private JFXButton infoButton;
    @FXML
    private GridPane mainFrame;
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<Orders> ordersTable;
    @FXML
    private TableColumn<Orders, Integer> orderIDColumn;
    @FXML
    private TableColumn<Orders, String> customerNameColumn;
    @FXML
    private TableColumn<Orders, String> creatorNameColumn;
    @FXML
    private TableColumn<Orders, String> orderDateColumn;
    @FXML
    private TableColumn<Orders, String> orderAddressColumn;

    //Author: Phan Tan Phat
    //Initialize the views , and get the database
    @FXML
    private void initialize(){
        ordersTable.setEditable(true);
        try {
            om = new OrdersModel();
            cm = new CustomersModel();
            em = new EmployeesModel();
            oim = new OrderItemsModel();
            om.loadOrders();
            cm.loadCustomers();
            em.loadEmployees();
            oim.loadOrderItems();
            //Declare some GUI views and connection to database
            List<String> orderName = new ArrayList<>();
            listItem = new ArrayList<>();
            //Create a list of name to create a recommend list name
            for (Customers customers : CustomersModel.sCustomersList){
                orderName.add(customers.getCustomerName());
            }
            for(Employees employees : EmployeesModel.sEmployeesList){
                orderName.add(employees.getEmployeeName());
            }
            TextFields.bindAutoCompletion(searchField, orderName);
        } catch (OrdersException | CustomersException | EmployeesException | OrderItemsException ignored) {
        }
        ordersList = FXCollections.observableList(OrdersModel.sOrderList);
        //Mapping data to views and add some function buttons
        mappingData();
        addButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/add_icon.png"))));
        addButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        addButton.setButtonType(JFXButton.ButtonType.RAISED);
        deleteButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/delete_icon.png"))));
        deleteButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        deleteButton.setButtonType(JFXButton.ButtonType.RAISED);
        deleteButton.setDisable(true);
        infoButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/info_icon.png"))));
        infoButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        infoButton.setButtonType(JFXButton.ButtonType.RAISED);
        infoButton.setDisable(true);
        JFXButton menuButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/setting_icon.png"))));
        menuButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setRotate(90);
        nodesList.addAnimatedNode(menuButton);
        nodesList.addAnimatedNode(addButton);
        nodesList.addAnimatedNode(deleteButton);
        //nodesList.addAnimatedNode(editButton);
        nodesList.addAnimatedNode(infoButton);
        mainFrame.add(nodesList,1,2);
        GridPane.setValignment(nodesList, VPos.CENTER);
        GridPane.setHalignment(nodesList, HPos.RIGHT);
        GridPane.setMargin(nodesList,new Insets(0,15,0,0));
        setButtonClick();
        ordersTable.setRowFactory(tableView -> {
            TableRow<Orders> row = new TableRow<>();
            // If a row of our table is clicked...
            row.setOnMouseClicked(mouseEvent -> {
                selectedItem = row.getItem();
                deleteButton.setDisable(false);
                infoButton.setDisable(false);
            });
            return row;
        });
    }
    private void mappingData(){
        //Take date from database and load to table columns
        orderIDColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleIntegerProperty(cellData.getValue().getOrderId()).asObject();
            } catch (OrdersException e) {
                return new SimpleIntegerProperty(-1).asObject();
            }
        });
        customerNameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cm.getCustomer(cellData.getValue().getCustomerId()).getCustomerName());
            } catch (OrdersException | CustomersException e) {
                FunctionLibrary.showAlertError(e.getMessage());
                return null;
            }
        });


        creatorNameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(em.getEmployee(cellData.getValue().getEmployeeId()).getEmployeeName());
            } catch (OrdersException | EmployeesException e) {
                FunctionLibrary.showAlertError(e.getMessage());
                return null;
            }
        });

        orderDateColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getDateOrder().toString());
            } catch (OrdersException e) {
                e.printStackTrace();
                return null;
            }
        });

        orderAddressColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getAddressOrder());
            } catch (OrdersException e) {
                e.printStackTrace();
                return null;
            }
        });
        FilteredList<Orders> filteredData = new FilteredList<>(ordersList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(orders -> {
            // If filter text is empty, display all .
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            // Take the filter text
            String lowerCaseFilter = newValue.toLowerCase();
            try {
                if (orders.getOrderId().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches customer's name, employee's name or order's id.
                } else if (cm.getCustomer(orders.getCustomerId()).getCustomerName().toLowerCase().contains(lowerCaseFilter)) return true;
                else if (em.getEmployee(orders.getEmployeeId()).getEmployeeName().toLowerCase().contains(lowerCaseFilter)) return true;
            } catch (OrdersException | CustomersException  | EmployeesException e) {
                e.printStackTrace();
            }
            return false; // Does not match.
        }));
        //Sort the data
        SortedList<Orders> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ordersTable.comparatorProperty());
        ordersTable.setItems(sortedData);
    }
    private void setButtonClick(){
        //Set action to buttons , load views depends on what button is clicked
        addButton.setOnMouseClicked(event -> {
            FunctionLibrary.setUpNewWindows("/add_order_dialog.fxml", "Add Order Dialog", 800, 600);
            if (isAdd && isBought) {
                try {
                    //if customer's order something and confirm buy, create an order
                    listItem.forEach(orderItems -> {
                        try {
                            oim.addOrderItem(orderItems.getOrderId(), orderItems.getProductId(), orderItems.getProductPrice(), orderItems.getProductQuantity());
                        } catch (OrderItemsException e) {
                            e.printStackTrace();
                        }
                    });
                    om.addOrder(addOrder.getCustomerId(),addOrder.getEmployeeId(),addOrder.getDateOrder(),addOrder.getAddressOrder());
                    ordersList.add(addOrder);
                } catch (OrdersException e) {
                    e.printStackTrace();
                }
            }
            if (!isBought) FunctionLibrary.showAlertError("There is no items in the order list");
            isAdd = false;
            isBought = false;
        });
        deleteButton.setOnMouseClicked(event -> {
            DeleteDialogController.type = DeleteDialogController.ORDERS;
            FunctionLibrary.setUpNewWindows("/delete_dialog.fxml", "Delete Order Dialog", 600, 400);
            if (isDelete) {
                ordersList.remove(selectedItem);
                try {
                    om.deleteOrder(selectedItem.getOrderId());
                } catch (OrdersException e) {
                    e.printStackTrace();
                }
            }
            isDelete = false;
            deleteButton.setDisable(true);
        });
        //Click info to see detail of orders
        infoButton.setOnMouseClicked(event -> {
            FunctionLibrary.setUpNewWindows("/info_dialog.fxml", "Detail of order", 800, 600);
            infoButton.setDisable(true);
        });
    }

}
