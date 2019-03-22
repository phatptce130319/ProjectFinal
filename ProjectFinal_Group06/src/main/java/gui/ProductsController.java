package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextField;
import entity.Products;
import entity.ProductsException;
import entity.ProductsModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.textfield.TextFields;
import util.FunctionLibrary;

import java.util.ArrayList;
import java.util.List;

public class ProductsController {
    private List<String> productsName;
    static Products addProduct;
    static boolean isAdd = false;
    static boolean isDelete = false;
    private static Products selectedItem = null;
    private ObservableList<Products> productsList;
    private ProductsModel pm;
    private JFXButton addButton;
    private JFXButton deleteButton;
    @FXML
    private GridPane mainFrame;
    @FXML
    private JFXTextField searchField;
    @FXML
    private TableView<Products> productsTable;
    @FXML
    private TableColumn<Products, Integer> productsIdColumn;
    @FXML
    private TableColumn<Products, String> productsNameColumn;
    @FXML
    private TableColumn<Products, String> productsPriceColumn;
    @FXML
    private TableColumn<Products, String> productsSizeColumn;
    @FXML
    private TableColumn<Products, String> productsColorColumn;
    @FXML
    private TableColumn<Products, String> productsDescriptionColumn;

    @FXML
    private void initialize() {
        productsTable.setEditable(true);
        try {
            pm = new ProductsModel();
            pm.loadProducts();
            productsName = new ArrayList<>();
            for (Products products : ProductsModel.sProductsList){
                productsName.add(products.getProductName());
            }
            TextFields.bindAutoCompletion(searchField,productsName);
        } catch (ProductsException ignored) {
        }
        productsList = FXCollections.observableList(ProductsModel.sProductsList);
        mappingData();
        setEditTable();
        addButton = new JFXButton("", new ImageView(new Image(getClass().getResourceAsStream("/add_icon.png"))));
        addButton.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        addButton.setButtonType(JFXButton.ButtonType.RAISED);
        deleteButton = new JFXButton("", new ImageView(new Image(getClass().getResourceAsStream("/delete_icon.png"))));
        deleteButton.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        deleteButton.setButtonType(JFXButton.ButtonType.RAISED);
        deleteButton.setDisable(true);
        JFXButton menuButton = new JFXButton("", new ImageView(new Image(getClass().getResourceAsStream("/setting_icon.png"))));
        menuButton.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        JFXNodesList nodesList = new JFXNodesList();
        nodesList.setRotate(90);
        nodesList.addAnimatedNode(menuButton);
        nodesList.addAnimatedNode(addButton);
        nodesList.addAnimatedNode(deleteButton);
        mainFrame.add(nodesList, 1, 2);
        GridPane.setValignment(nodesList, VPos.CENTER);
        GridPane.setHalignment(nodesList, HPos.RIGHT);
        GridPane.setMargin(nodesList, new Insets(0, 15, 0, 0));
        setButtonClick();
        productsTable.setRowFactory(tableView -> {
            TableRow<Products> row = new TableRow<>();
            // If a row of our table is clicked...
            row.setOnMouseClicked(mouseEvent -> {
                selectedItem = row.getItem();
                deleteButton.setDisable(false);
            });
            return row;
        });
    }

    private void mappingData() {
        productsIdColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleIntegerProperty(cellData.getValue().getProductId()).asObject();
            } catch (ProductsException e) {
                e.printStackTrace();
                return new SimpleIntegerProperty(-1).asObject();
            }
        });
        productsNameColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getProductName());
            } catch (ProductsException e) {
                e.printStackTrace();
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

        productsDescriptionColumn.setCellValueFactory(cellData -> {
            try {
                return new SimpleStringProperty(cellData.getValue().getProductDescription());
            } catch (ProductsException e) {
                e.printStackTrace();
                return null;
            }
        });

        FilteredList<Products> filteredData = new FilteredList<>(productsList, p -> true);


        AddOrderController.sortData(filteredData, searchField, productsTable);

    }

    private void setEditTable() {
        setEditableColumn();
    }

    private void setEditableColumn() {
        productsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsNameColumn.setOnEditCommit(event -> setEditOnColumn(event, 2));
        productsPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsPriceColumn.setOnEditCommit(event -> setEditOnColumn(event, 3));
        productsColorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsColorColumn.setOnEditCommit(event -> setEditOnColumn(event, 4));
        productsSizeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsSizeColumn.setOnEditCommit(event -> setEditOnColumn(event, 5));
        productsDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsDescriptionColumn.setOnEditCommit(event -> setEditOnColumn(event, 6));

    }

    private void setEditOnColumn(TableColumn.CellEditEvent<Products, String> event, int columnIndex) {
        TablePosition<Products, String> pos = event.getTablePosition();
        String value = event.getNewValue();
        int row = pos.getRow();
        Products product = event.getTableView().getItems().get(row);
        AddOrderController.setIndex(columnIndex, value, product, pm);
    }

    private void setButtonClick() {
        addButton.setOnMouseClicked(event -> {
            FunctionLibrary.setUpNewWindows("/add_product_dialog.fxml", "Add Product Dialog");
            if (isAdd) {
                try {
                    pm.addProduct(addProduct.getProductName(), addProduct.getProductPrice(), addProduct.getProductColor(), addProduct.getProductSize(), addProduct.getProductDescription());
                    productsList.add(addProduct);
                    productsName.add(addProduct.getProductName());
                    TextFields.bindAutoCompletion(searchField,productsName);
                } catch (ProductsException e) {
                    e.printStackTrace();
                }
            }
            isAdd = false;
        });
        deleteButton.setOnMouseClicked(event -> {
            DeleteDialogController.type = DeleteDialogController.PRODUCTS;
            FunctionLibrary.setUpNewWindows("/delete_dialog.fxml", "Delete Product Dialog");
            if (isDelete) {
                productsList.remove(selectedItem);
                try {
                    pm.deleteProduct(selectedItem.getProductId());
                } catch (ProductsException e) {
                    e.printStackTrace();
                }
            }
            isDelete = false;
        });
    }
}
