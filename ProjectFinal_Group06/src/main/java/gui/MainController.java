package gui;

import com.jfoenix.controls.JFXTabPane;
import entity.Customers;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;

public class MainController {
    @FXML
    private JFXTabPane tabContainer;

    @FXML
    private Tab customersTab;

    @FXML
    private AnchorPane customersContainer;

    @FXML
    private Tab productsTab;

    @FXML
    private AnchorPane productsContainer;

    @FXML
    private Tab employeesTab;

    @FXML
    private AnchorPane employeesContainer;

    @FXML
    private Tab ordersTab;

    @FXML
    private AnchorPane ordersContainer;


    private double tabWidth = 90.0;
    public static int lastSelectedTabIndex = 0;

    /// Life cycle

    @FXML
    public void initialize() {
        configureView();
    }

    /// Private

    private void configureView() {
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);

        EventHandler<Event> replaceBackgroundColorHandler = event -> {
            lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();

            Tab currentTab = (Tab) event.getTarget();
            if (currentTab.isSelected()) {
                currentTab.setStyle("-fx-background-color: -fx-focus-color;");
            } else {
                currentTab.setStyle("-fx-background-color: -fx-accent;");
            }
        };

        configureTab(customersTab, "Customers", "/customers.png", customersContainer, getClass().getResource("/customers_view.fxml"), replaceBackgroundColorHandler);
        configureTab(productsTab, "Products", "/products.png", productsContainer, getClass().getResource("/products_view.fxml"), replaceBackgroundColorHandler);
        configureTab(employeesTab, "Employees", "/employees.png", employeesContainer, getClass().getResource("/employees_view.fxml"), replaceBackgroundColorHandler);
        configureTab(ordersTab,"Orders","/orders.png",ordersContainer,getClass().getResource("/orders_view.fxml"),replaceBackgroundColorHandler);
        customersTab.setStyle("-fx-background-color: -fx-focus-color;");
    }

    private void configureTab(Tab tab, String title, String iconPath, AnchorPane containerPane, URL resourceURL, EventHandler<Event> onSelectionChangedEvent) {
        double imageWidth = 40.0;

        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);

        Label label = new Label(title);
        label.setMaxWidth(tabWidth - 20);
        label.setPadding(new Insets(5, 0, 0, 0));
        label.setStyle("-fx-text-fill: black; -fx-font-size: 8pt; -fx-font-weight: normal;");
        label.setTextAlignment(TextAlignment.CENTER);

        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);

        tab.setText("");
        tab.setGraphic(tabPane);

        tab.setOnSelectionChanged(onSelectionChangedEvent);

        if (containerPane != null && resourceURL != null) {
            try {
                Parent contentView = FXMLLoader.load(resourceURL);
                containerPane.getChildren().add(contentView);
                AnchorPane.setTopAnchor(contentView, 0.0);
                AnchorPane.setBottomAnchor(contentView, 0.0);
                AnchorPane.setRightAnchor(contentView, 0.0);
                AnchorPane.setLeftAnchor(contentView, 0.0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }

