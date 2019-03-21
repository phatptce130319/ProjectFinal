package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextField;
import entity.CustomersModel;
import entity.Products;
import entity.ProductsException;
import entity.ProductsModel;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProductsController {
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
        productsDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productsDescriptionColumn.setOnEditCommit(event -> setEditOnColumn(event, 6));

    }

    private void setEditOnColumn(TableColumn.CellEditEvent<Products, String> event, int columnIndex) {
        TablePosition<Products, String> pos = event.getTablePosition();
        String value = event.getNewValue();
        int row = pos.getRow();
        Products product = event.getTableView().getItems().get(row);
        try {
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
        } catch (ProductsException e) {
            e.printStackTrace();
        }
    }

    private void setButtonClick() {
        addButton.setOnMouseClicked(event -> {
            setUpNewWindows("/add_product_dialog.fxml", "Add Product Dialog");
            if (isAdd) {
                try {
                    pm.addProduct(addProduct.getProductName(), addProduct.getProductPrice(), addProduct.getProductColor(), addProduct.getProductSize(), addProduct.getProductDescription());
                    addProduct.setProductId(CustomersModel.latestID);
                    productsList.add(addProduct);
                } catch (ProductsException e) {
                    e.printStackTrace();
                }
            }
            isAdd = false;
        });
        deleteButton.setOnMouseClicked(event -> {
            DeleteDialogController.type = DeleteDialogController.PRODUCTS;
            setUpNewWindows("/delete_dialog.fxml", "Delete Product Dialog");
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

    private void setUpNewWindows(String resource, String windowName) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(windowName);
        stage.setScene(new Scene(Objects.requireNonNull(root), 450, 450));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.showAndWait();
    }

}
