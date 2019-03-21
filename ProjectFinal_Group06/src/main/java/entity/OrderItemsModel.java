package entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsModel {
    static List<OrderItems> sOrderItemsList;
    public static int orderIndex;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;
    //Initialize some connection
    public OrderItemsModel() throws OrderItemsException {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
            String query = "SELECT * FROM product_manager.orders ORDER BY order_id DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            orderIndex = mResultSet.getInt("order_id");
        } catch (SQLException e) {
            sOrderItemsList = new ArrayList<>();

        }
    }
    //Load data from database and add to local list
    public void loadOrderItems() throws OrderItemsException {
        try {
            //language=TSQL
            String query = "SELECT * FROM product_manager.order_items";
            mResultSet = mStatement.executeQuery(query);
            sOrderItemsList = new ArrayList<>();
            while (mResultSet.next()) {
                int orderItemId = mResultSet.getInt("order_item_id");
                int orderId = mResultSet.getInt("order_id");
                int productId = mResultSet.getInt("product_id");
                double productPrice = mResultSet.getDouble("product_price");
                int productQuantity = mResultSet.getInt("product_quantity");
                sOrderItemsList.add(new OrderItems(orderItemId,orderId,productId,productPrice,productQuantity));
            }
        } catch (SQLException | OrderItemsException s) {
            throw new OrderItemsException(s.getMessage());
        }
    }

    //Add a type to database
    public boolean addOrderItem(Integer orderId, Integer productID, Double productPrice, Integer productQuantity) {
        //language=TSQL
        String insert = "INSERT INTO product_manager.order_items values(?,?,?,?)";
        try {
            setValue(orderId,productID,productPrice,productQuantity,insert);
            mPreparedStatement.executeUpdate();

            //language=TSQL
            String query = "SELECT * FROM product_manager.orders ORDER BY order_id DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            orderIndex = mResultSet.getInt("order_id");
            query = "SELECT * FROM product_manager.order_items ORDER BY order_item_id DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            sOrderItemsList.add(new OrderItems(mResultSet.getInt("order_item_id"), OrdersModel.lastedIndex + 1, mResultSet.getInt("product_id"), mResultSet.getDouble("product_price"), mResultSet.getInt("product_quantity")));
            return true;
        } catch (SQLException | OrderItemsException e) {

            return false;
        }
    }


    //Update a type with a specific ID
    public boolean updateOrderItem(Integer orderItemId, Integer orderId, Integer productID, Double productPrice, Integer productQuantity) throws InvoicesException {
        //language=TSQL
        String update = "UPDATE product_manager.order_items SET order_id = ?, product_id = ?, product_price = ?, product_quantity = ? WHERE order_item_id = ?";
        try {
            setValue(orderId,productID,productPrice,productQuantity,update);
            mPreparedStatement.setInt(5, orderItemId);
            mPreparedStatement.executeUpdate();
            if (sOrderItemsList.isEmpty()) throw new OrderItemsException("The OrderItem list is empty, cannot update");
            for (int i = 0; i < sOrderItemsList.size(); i++) {
                if (sOrderItemsList.get(i).getOrderItemId().equals(orderItemId)) {
                    sOrderItemsList.set(i,new OrderItems(orderItemId,orderId,productID,productPrice,productQuantity));
                }
            }
            return true;
        } catch (SQLException | OrderItemsException e) {
            return false;
        }
    }
    public List<OrderItems>getAllItemInOrder(int orderId){
        List<OrderItems> result = new ArrayList<>();
        for (OrderItems item: sOrderItemsList){
            try {
                if (item.getOrderId().equals(orderId)) result.add(item);
            } catch (OrderItemsException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void setValue(Integer orderId, Integer productID, Double productPrice, Integer productQuantity, String update) throws SQLException {
        mPreparedStatement = mConnection.prepareStatement(update);
        mPreparedStatement.setInt(1, orderId);
        mPreparedStatement.setInt(2, productID);
        mPreparedStatement.setDouble(3, productPrice);
        mPreparedStatement.setInt(4, productQuantity);
    }

    //Get the type by giving an ID
    public OrderItems getInvoice(Integer orderItemId) throws OrderItemsException {
        for (OrderItems orderItem : sOrderItemsList) {
            if (orderItem.getOrderItemId().equals(orderItemId)) return orderItem;
        }
        throw new OrderItemsException("Cannot find the satisfied order item");
    }

    //Get the total size of the list
    private int getSize() {
        return sOrderItemsList.size();
    }

    //Use this to describe the object shortly
    @Override
    public String toString() {
        return "Order Items model has " + getSize() + " records";
    } //override

    //When the object is deposed, close the connection
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mConnection.close();
    }


















}
