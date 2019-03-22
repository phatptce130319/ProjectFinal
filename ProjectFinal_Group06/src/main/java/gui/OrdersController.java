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
    private List<String> orderName;
    static Orders addOrder;
    static boolean isBought;
    static boolean isAdd = false;
    public static Orders selectedItem = null;
    static boolean isDelete = false;
    private ObservableList<Orders> ordersList;
    private OrdersModel om;
    private CustomersModel cm;
    private EmployeesModel em;
    private JFXButton addButton;
    private JFXButton deleteButton;
    private JFXButton editButton;
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

    @FXML
    private void initialize(){
        ordersTable.setEditable(true);
        try {
            om = new OrdersModel();
            cm = new CustomersModel();
            em = new EmployeesModel();
            om.loadOrders();
            cm.loadCustomers();
            em.loadEmployees();
            orderName = new ArrayList<>();
            for (Customers customers : CustomersModel.sCustomersList){
                orderName.add(customers.getCustomerName());
            }
            for(Employees employees : EmployeesModel.sEmployeesList){
                orderName.add(employees.getEmployeeName());
            }
            TextFields.bindAutoCompletion(searchField,orderName);
        } catch (OrdersException | CustomersException | EmployeesException ignored) {
            System.out.println("Here");
        }
        ordersList = FXCollections.observableList(OrdersModel.sOrderList);
        mappingData();
        addButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/add_icon.png"))));
        addButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        addButton.setButtonType(JFXButton.ButtonType.RAISED);
        deleteButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/delete_icon.png"))));
        deleteButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        deleteButton.setButtonType(JFXButton.ButtonType.RAISED);
        deleteButton.setDisable(true);
        editButton = new JFXButton("",new ImageView(new Image(getClass().getResourceAsStream("/edit_icon.png"))));
        editButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        editButton.setButtonType(JFXButton.ButtonType.RAISED);
        editButton.setDisable(true);
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
                editButton.setDisable(false);
                infoButton.setDisable(false);
            });
            return row;
        });
    }
    private void mappingData(){
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
                return null;
            }
        });


        creatorNameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(em.getEmployee(cellData.getValue().getEmployeeId()).getEmployeeName());
            } catch (OrdersException | EmployeesException e) {
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
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();
            try {
                if (orders.getOrderId().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cm.getCustomer(orders.getCustomerId()).getCustomerName().toLowerCase().contains(lowerCaseFilter)) return true;
                else if (em.getEmployee(orders.getEmployeeId()).getEmployeeName().toLowerCase().contains(lowerCaseFilter)) return true;
            } catch (OrdersException | CustomersException  | EmployeesException e) {
                e.printStackTrace();
            }
            return false; // Does not match.
        }));
        SortedList<Orders> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(ordersTable.comparatorProperty());
        ordersTable.setItems(sortedData);
    }
    private void setButtonClick(){
        addButton.setOnMouseClicked(event -> {
            FunctionLibrary.setUpNewWindows("/add_order_dialog.fxml","Add Order Dialog");
            if (isAdd && isBought) {
                try {
                    om.addOrder(addOrder.getCustomerId(),addOrder.getEmployeeId(),addOrder.getDateOrder(),addOrder.getAddressOrder());
                    ordersList.add(addOrder);
                } catch (OrdersException e) {
                    e.printStackTrace();
                }
            }
            isAdd = false;
            isBought = false;
        });
        deleteButton.setOnMouseClicked(event -> {
            DeleteDialogController.type = DeleteDialogController.ORDERS;
            FunctionLibrary.setUpNewWindows("/delete_dialog.fxml","Delete Order Dialog");
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
        editButton.setOnMouseClicked(event -> {
            FunctionLibrary.setUpNewWindows("/edit_order_dialog.fxml","Edit Order Dialog");
            infoButton.setDisable(true);
            editButton.setDisable(true);
        });
        infoButton.setOnMouseClicked(event -> {
            FunctionLibrary.setUpNewWindows("/info_dialog.fxml","Detail of order");
            infoButton.setDisable(true);
            editButton.setDisable(true);
        });
    }

}
