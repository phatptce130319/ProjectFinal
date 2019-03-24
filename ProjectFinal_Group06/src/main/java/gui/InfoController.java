package gui;

import com.jfoenix.controls.JFXButton;
import entity.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class InfoController {
    //Ha Van Ngoan
    //Declare some GUI views, and connect to data models
    private ObservableList<OrderItems> orderItemsList;
    private OrderItemsModel om;
    private ProductsModel pm;
    private double total = 0;
    @FXML
    private Label totalLabel;
    @FXML
    private GridPane mainFrame;
    @FXML
    private TableView<OrderItems> orderItemTable;
    @FXML
    private TableColumn<OrderItems, Integer> productIdColumn;
    @FXML
    private TableColumn<OrderItems, String> productNameColumn;
    @FXML
    private TableColumn<OrderItems, String> productPriceColumn;
    @FXML
    private TableColumn<OrderItems, String> productQuantityColumn;
    @FXML
    private TableColumn<OrderItems, String> productTotalColumn;
    @FXML
    private JFXButton okButton;
    @FXML
    private void initialize(){
        try {
            //Load the data from database
            om = new OrderItemsModel();
            om.loadOrderItems();
            pm = new ProductsModel();
            pm.loadProducts();
        } catch (OrderItemsException | ProductsException ignored) {
        }
        try {
            orderItemsList = FXCollections.observableList(om.getAllItemInOrder(OrdersController.selectedItem.getOrderId()));
        } catch (OrdersException e) {
            e.printStackTrace();
        }
        mappingData();
        okButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        });
    }
    private void mappingData(){
        //Mapping data to views
        productIdColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleIntegerProperty(cellData.getValue().getProductId()).asObject();
            } catch (OrderItemsException e) {
                e.printStackTrace();
                return new SimpleIntegerProperty(-1).asObject();
            }
        });
        productNameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(pm.getProduct(cellData.getValue().getProductId()).getProductName());
            } catch (ProductsException | OrderItemsException e) {
                e.printStackTrace();
                return null;
            }
        });

        productPriceColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getProductPrice().toString());
            } catch (OrderItemsException e) {
                e.printStackTrace();
                return null;
            }
        });
        productQuantityColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getProductQuantity().toString());
            } catch (OrderItemsException e) {
                e.printStackTrace();
                return null;
            }
        });
        productTotalColumn.setCellValueFactory(cellData -> {
            try {
                double value = cellData.getValue().getProductQuantity() * cellData.getValue().getProductPrice();
                total += value;
                totalLabel.setText(String.format("Label: %.2f$", total));
                return new SimpleStringProperty(String.format("%.2f", value));
            } catch (OrderItemsException e) {
                e.printStackTrace();
                return null;
            }
        });
        FilteredList<OrderItems> filteredData = new FilteredList<>(orderItemsList, p -> true);
        SortedList<OrderItems> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(orderItemTable.comparatorProperty());

        orderItemTable.setItems(sortedData);
    }

}
