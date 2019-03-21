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
import util.FunctionLibrary;

import java.io.IOException;
import java.util.Objects;


public class AddOrderController {
    public static int quantity;
    static boolean isAdd = false;
    private static Products selectedItem = null;
    private ObservableList<Products> productsList;
    private ProductsModel pm;
    private OrderItemsModel oim;
    private OrdersModel om;
    @FXML
    private GridPane mainFrame;
    @FXML
    private JFXTextField customerID;
    @FXML
    private JFXTextField orderAddress;
    @FXML
    private JFXTextField creatorID;
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
    @FXML
    private void initialize() {
        try {
            pm = new ProductsModel();
            oim = new OrderItemsModel();
            om = new OrdersModel();
            oim.loadOrderItems();
            pm.loadProducts();

        } catch (ProductsException | OrderItemsException | OrdersException ignored) {

        }
        doneButton.setOnMouseClicked(event -> {
            OrdersController.isAdd = true;
            try {
                OrdersController.addOrder = new Orders(OrdersModel.lastedIndex,Integer.parseInt(customerID.getText()),Integer.parseInt(creatorID.getText()),new java.sql.Date(System.currentTimeMillis()),orderAddress.getText());
            } catch (OrdersException | NumberFormatException e) {
                FunctionLibrary.showAlertError(e.getMessage());
            }
            Stage stage = (Stage) doneButton.getScene().getWindow();
            stage.close();
        });
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
                            oim.addOrderItem(om.getLastedIndex() + 1, selectedItem.getProductId(), selectedItem.getProductPrice(), quantity);
                        } catch (ProductsException ignored) {
                        }
                    }
                }

            });
            return row;
        });
    }

    static void sortData(FilteredList<Products> filteredData, JFXTextField searchField, TableView<Products> productsTable) {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(product -> {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            try {
                if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
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

    static void setIndex(int columnIndex, String value, Products product, ProductsModel pm) throws ProductsException {
        switch (columnIndex) {
            case 2:
                product.setProductName(value);
                break;
            case 3:
                product.setProductPrice(Double.parseDouble(value));
                break;
            case 4:
                product.setProductColor(value);
                break;
            case 5:
                product.setProductSize(Double.parseDouble(value));
                break;
            case 6:
                product.setProductDescription(value);
                break;
        }
        pm.updateProduct(product.getProductId(), product.getProductName(), product.getProductPrice(), product.getProductColor(), product.getProductSize(), product.getProductDescription());
    }

    private void setEditTable() {
        setEditableColumn();
    }
    private void setEditableColumn() {
        productsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsNameColumn.setOnEditCommit(event -> setEditOnColumn(event, 2));
        productsPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsPriceColumn.setOnEditCommit(event -> setEditOnColumn(event, 4));
        productsColorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsColorColumn.setOnEditCommit(event -> setEditOnColumn(event, 3));
        productsSizeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsSizeColumn.setOnEditCommit(event -> setEditOnColumn(event, 5));

    }

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

    private void setEditOnColumn(TableColumn.CellEditEvent<Products, String> event, int columnIndex) {
        TablePosition<Products, String> pos = event.getTablePosition();
        String value = event.getNewValue();
        int row = pos.getRow();
        Products product = event.getTableView().getItems().get(row);
        try {
            setIndex(columnIndex, value, product, pm);
        } catch (ProductsException ignored) {

        }
    }

}
