package entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesModel {
    public static List<Employees> sEmployeesList;
    public static int latestID;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;
    //Initialize some connection
    public EmployeesModel() throws EmployeesException {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
        } catch (SQLException e) {
            throw new EmployeesException("Cannot connect to database");
        }
    }
    //Load data from database and add to local list
    public void loadEmployees() throws EmployeesException {
        try {
            //language=TSQL
            String query = "SELECT * FROM product_manager.employees";
            mResultSet = mStatement.executeQuery(query);
            sEmployeesList = new ArrayList<>();
            while (mResultSet.next()) {
                int id = mResultSet.getInt("employee_id");
                String employeeName = mResultSet.getString("employee_name");
                String employeeGender = mResultSet.getString("gender");
                String emailAddress = mResultSet.getString("email_address");
                String phoneNumber = mResultSet.getString("phone_number");
                sEmployeesList.add(new Employees(id,employeeName,phoneNumber,emailAddress,employeeGender));
            }
        } catch (SQLException | EmployeesException s) {
            throw new EmployeesException("Cannot load database");
        }
    }
    public void deleteEmployee(int employeeID) {
        String delete = "DELETE FROM product_manager.employees WHERE employee_id = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(delete);
            mPreparedStatement.setInt(1, employeeID);
            mPreparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Add a type to database
    public boolean addEmployee(String employeesName, String employeesGender, String emailAddress, String phoneNumber) {
        //language=TSQL
        String insert = "INSERT INTO product_manager.employees values(?,?,?,?)";
        try {
            setValue(employeesName, employeesGender, emailAddress, phoneNumber,insert);
            mPreparedStatement.executeUpdate();
            //language=TSQL
            String query = "SELECT * FROM product_manager.customers ORDER BY customer_id DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            latestID = mResultSet.getInt("employee_id");
            sEmployeesList.add(new Employees(mResultSet.getInt("employee_id"),mResultSet.getString("employee_name"),mResultSet.getString("phone_number"),mResultSet.getString("email_address"),mResultSet.getString("gender")));
            return true;
        } catch (SQLException | EmployeesException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Update a type with a specific ID
    public boolean updateEmployee(int employeeId, String employeeName, String employeeGender, String emailAddress, String phoneNumber) throws EmployeesException {
        //language=TSQL
        String update = "UPDATE product_manager.employees SET employee_name = ?, gender = ?, email_address = ?, phone_number = ? WHERE employee_id = ?";
        try {
            setValue(employeeName, employeeGender, emailAddress, phoneNumber,update);
            mPreparedStatement.setInt(5, employeeId);
            mPreparedStatement.executeUpdate();
            if (sEmployeesList.isEmpty()) throw new ProductsException("The employees list is empty, cannot update");
            for (int i = 0; i < sEmployeesList.size(); i++) {
                if (sEmployeesList.get(i).getEmployeeId().equals(employeeId)) {
                    sEmployeesList.set(i,new Employees(employeeId,employeeName,phoneNumber,emailAddress,employeeGender));
                }
            }
            return true;
        } catch (SQLException | ProductsException e) {
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
    public Employees getEmployee(Integer employeeID) throws EmployeesException {
        for (Employees employee : sEmployeesList) {
            if (employee.getEmployeeId().equals(employeeID)) return employee;
        }
        throw new EmployeesException("Cannot find the satisfied employee");
    }

    //Get the total size of the list
    private int getSize() {
        return sEmployeesList.size();
    }

    //Use this to describe the object shortly
    @Override
    public String toString() {
        return "Employes model has " + getSize() + " records";
    } //override

    //When the object is deposed, close the connection
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mConnection.close();
    }


















}
