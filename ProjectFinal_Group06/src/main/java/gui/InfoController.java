package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextField;
import entity.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import util.FunctionLibrary;


public class InfoController {
    private ObservableList<OrderItems> orderItemsList;
    private OrderItemsModel om;
    private ProductsModel pm;
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
                return new SimpleStringProperty(Double.toString(cellData.getValue().getProductQuantity() * cellData.getValue().getProductPrice()));
            } catch (OrderItemsException e) {
                e.printStackTrace();
                return null;
            }
        });

        FilteredList<OrderItems> filteredData = new FilteredList<>(orderItemsList, p -> true);



            // Compare first name and last name of every person with filter text.


        SortedList<OrderItems> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(orderItemTable.comparatorProperty());

        orderItemTable.setItems(sortedData);

    }




}
