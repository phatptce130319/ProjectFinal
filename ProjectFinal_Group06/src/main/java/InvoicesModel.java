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
            mConnection = DatabaseConnection.getMySQLConnection();
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
        String insert = "INSERT INTO product_manager.invoices values(NULL,?,?,?,?)";
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
    boolean updateInvoice(int employeeId, String employeeName, String employeeGender, String emailAddress, String phoneNumber) throws InvoicesException {
        //language=TSQL
        String update = "UPDATE product_manager.employees SET employee_name = ?, gender = ?, email_address = ?, phone_number = ? WHERE employee_id = ?";
        try {
            setValue(employeeName, employeeGender, emailAddress, phoneNumber,update);
            mPreparedStatement.setInt(5, employeeId);
            mPreparedStatement.executeUpdate();
            if (sEmployeesList.size() == 0) throw new ProductsException("The Type list is empty, cannot update");
            for (int i = 0; i < sEmployeesList.size(); i++) {
                if (sEmployeesList.get(i).getEmployeeId().equals(employeeId)) {
                    sEmployeesList.set(i,new Employees(employeeId,employeeName,phoneNumber,emailAddress,employeeGender));
                }
            }
            return true;
        } catch (SQLException | InvoicesException e) {
            return false;
        }
    }

    private void setValue(String employeesName, String employeesGender, String emailAddress, String phoneNumber, String update) throws SQLException {
        mPreparedStatement = mConnection.prepareStatement(update);
        mPreparedStatement.setString(1, employeesName);
        mPreparedStatement.setString(2, phoneNumber);
        mPreparedStatement.setString(3, emailAddress);
        mPreparedStatement.setString(4, employeesGender);
    }

    //Get the type by giving an ID
    Invoices getInvoice(Integer invoiceNum) throws InvoicesException {
        for (Invoices invoices : sInvoicesList) {
            if (invoices.getInvoiceNumber().equals(invoiceNum)) return invoices;
        }
        throw new InvoicesException("Cannot find the satisfied product");
    }

    //Get the total size of the list
    private int getSize() {
        return sInvoicesList.size();
    }

    //Use this to describe the object shortly
    @Override
    public String toString() {
        return "Customers model has " + getSize() + " records";
    } //override

    //When the object is deposed, close the connection
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mConnection.close();
    }


















}
