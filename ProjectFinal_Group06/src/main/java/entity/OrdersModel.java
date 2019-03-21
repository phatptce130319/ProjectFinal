package entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersModel {
    public static List<Orders> sOrderList;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;
    public static int lastedIndex = 1;
    //Initialize some connection
    public OrdersModel() throws OrdersException {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
            //language=TSQL
            String query = "SELECT * FROM product_manager.orders ORDER BY order_id DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            lastedIndex = mResultSet.getInt("order_id");
        } catch (SQLException e) {
            sOrderList = new ArrayList<>();
        }
    }
    //Load data from database and add to local list
    public void loadOrders() throws OrdersException {
        sOrderList = new ArrayList<>();
        try {
            //language=TSQL
            String query = "SELECT * FROM product_manager.orders";
            mResultSet = mStatement.executeQuery(query);
            while (mResultSet.next()) {
                int orderId = mResultSet.getInt("order_id");
                int customerId = mResultSet.getInt("customer_id");
                int employeeId = mResultSet.getInt("employee_id");
                Date orderDate = mResultSet.getDate("order_date");
                String orderAddress = mResultSet.getString("order_address");
                sOrderList.add(new Orders(orderId,customerId,employeeId,orderDate,orderAddress));
            }
        } catch (SQLException | OrdersException s) {
            throw new OrdersException("Cannot load database");
        }
    }

    //Add a type to database
    public boolean addOrder(Integer customerId, Integer employeeId, Date orderDate, String orderAddress) {
        //language=TSQL
        String insert = "INSERT INTO product_manager.orders values(?,?,?,?)";
        try {
            setValue(customerId,employeeId,orderDate,orderAddress,insert);
            mPreparedStatement.executeUpdate();
            //language=TSQL
            String query = "SELECT * FROM product_manager.orders ORDER BY order_id DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            lastedIndex = mResultSet.getInt("order_id");
            sOrderList.add(new Orders(mResultSet.getInt("order_id"), mResultSet.getInt("customer_id") ,mResultSet.getInt("employee_id"),mResultSet.getDate("order_date"),mResultSet.getString("order_address")));
            return true;
        } catch (SQLException | OrdersException e) {
            return false;
        }
    }

    public int getLastedIndex() {
        //language=TSQL
        String query = "SELECT * FROM product_manager.orders ORDER BY order_id DESC";
        try {
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            return mResultSet.getInt("order_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //Update a type with a specific ID
    public boolean updateOrder(Integer orderId, Integer customerId, Integer employeeId, Date orderDate, String orderAddress) throws OrdersException {
        //language=TSQL
        String update = "UPDATE product_manager.orders SET customer_id = ?, employee_id = ?, order_date = ?, order_address = ? WHERE order_id = ?";
        try {
            setValue(customerId,employeeId,orderDate,orderAddress,update);
            mPreparedStatement.setInt(5, orderId);
            mPreparedStatement.executeUpdate();
            if (sOrderList.isEmpty()) throw new OrdersException("The Order list is empty, cannot update");
            for (int i = 0; i < sOrderList.size(); i++) {
                if (sOrderList.get(i).getOrderId().equals(orderId)) {
                    sOrderList.set(i,new Orders(orderId,customerId,employeeId,orderDate,orderAddress));
                }
            }
            return true;
        } catch (SQLException | OrdersException e) {
            return false;
        }
    }
    public void deleteOrder(int orderID) {
        String delete = "DELETE FROM product_manager.orders WHERE order_id = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(delete);
            mPreparedStatement.setInt(1, orderID);
            mPreparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setValue(Integer customerId, Integer employeeId, Date orderDate, String orderAddress, String update) throws SQLException {
        mPreparedStatement = mConnection.prepareStatement(update);
        mPreparedStatement.setInt(1, customerId);
        mPreparedStatement.setInt(2, employeeId);
        mPreparedStatement.setDate(3, orderDate);
        mPreparedStatement.setString(4, orderAddress);
    }

    //Get the type by giving an ID
    Orders getOrder(Integer orderId) throws OrdersException {
        for (Orders order : sOrderList) {
            if (order.getOrderId().equals(orderId)) return order;
        }
        throw new OrdersException("Cannot find the satisfied order item");
    }

    //Get the total size of the list
    private int getSize() {
        return sOrderList.size();
    }

    //Use this to describe the object shortly
    @Override
    public String toString() {
        return "Order model has " + getSize() + " records";
    } //override

    //When the object is deposed, close the connection
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mConnection.close();
    }


}
