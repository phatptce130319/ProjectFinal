package entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoicesModel {
    static List<Invoices> sInvoicesList;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;
    //Initialize some connection
    InvoicesModel() throws InvoicesException {
        try {
            mConnection = DatabaseConnection.getSQLServerConnection();
            mStatement = mConnection.createStatement();
        } catch (SQLException e) {
            throw new InvoicesException("Cannot connect to database");
        }
    }
    //Load data from database and add to local list
    void loadInvoices() throws InvoicesException {
        try {
            //language=TSQL
            String query = "SELECT * FROM product_manager.invoices";
            mResultSet = mStatement.executeQuery(query);
            sInvoicesList = new ArrayList<>();
            while (mResultSet.next()) {
                int number = mResultSet.getInt("invoice_number");
                int orderId = mResultSet.getInt("order_id");
                int employeeId = mResultSet.getInt("employee_id");
                int customerId = mResultSet.getInt("customer_id");
                Date invoiceDate = mResultSet.getDate("invoice_date");
                sInvoicesList.add(new Invoices(number,orderId,employeeId,customerId,invoiceDate));
            }
        } catch (SQLException | InvoicesException s) {
            throw new InvoicesException("Cannot load database");
        }
    }

    //Add a type to database
    boolean addInvoice(Integer orderId, Integer employeeId, Integer customerId, Date invoiceDate) {
        //language=TSQL
        String insert = "INSERT INTO product_manager.invoices values(?,?,?,?)";
        try {
            setValue(orderId,employeeId,customerId,invoiceDate,insert);
            mPreparedStatement.executeUpdate();
            //language=TSQL
            String query = "SELECT * FROM product_manager.customers ORDER BY customer_id DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            sInvoicesList.add(new Invoices(mResultSet.getInt("invoice_number"), mResultSet.getInt("order_id") ,mResultSet.getInt("employee_id"),mResultSet.getInt("customer_id"),mResultSet.getDate("invoice_date")));
            return true;
        } catch (SQLException | InvoicesException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Update a type with a specific ID
    boolean updateInvoice(Integer invoiceNum, Integer orderId, Integer employeeId, Integer customerId, Date invoiceDate) {
        //language=TSQL
        String update = "UPDATE product_manager.invoices SET employee_id = ?, employee_id = ?, customer_id = ?, invoice_date = ? WHERE invoice_number = ?";
        try {
            setValue(orderId,employeeId,customerId,invoiceDate,update);
            mPreparedStatement.setInt(5, invoiceNum);
            mPreparedStatement.executeUpdate();
            if (sInvoicesList.isEmpty()) throw new InvoicesException("The Type list is empty, cannot update");
            for (int i = 0; i < sInvoicesList.size(); i++) {
                if (sInvoicesList.get(i).getEmployeeId().equals(invoiceNum)) {
                    sInvoicesList.set(i,new Invoices(invoiceNum,orderId,employeeId,customerId,invoiceDate));
                }
            }
            return true;
        } catch (SQLException | InvoicesException e) {
            return false;
        }
    }

    private void setValue(Integer orderId, Integer employeeId, Integer customerId, Date invoiceDate, String update) throws SQLException {
        mPreparedStatement = mConnection.prepareStatement(update);
        mPreparedStatement.setInt(1, orderId);
        mPreparedStatement.setInt(2, employeeId);
        mPreparedStatement.setInt(3, customerId);
        mPreparedStatement.setDate(4, invoiceDate);
    }

    //Get the type by giving an ID
    Invoices getInvoice(Integer invoiceNum) throws InvoicesException {
        for (Invoices invoices : sInvoicesList) {
            if (invoices.getInvoiceNumber().equals(invoiceNum)) return invoices;
        }
        throw new InvoicesException("Cannot find the satisfied invoice");
    }

    //Get the total size of the list
    private int getSize() {
        return sInvoicesList.size();
    }

    //Use this to describe the object shortly
    @Override
    public String toString() {
        return "entity.Invoices model has " + getSize() + " records";
    } //override

    //When the object is deposed, close the connection
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mConnection.close();
    }


















}
