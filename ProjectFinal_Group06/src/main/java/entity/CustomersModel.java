package entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Author: Phan Tan Phat
public class CustomersModel {
        public static List<Customers> sCustomersList;
        private Connection mConnection;
        private Statement mStatement;
        private PreparedStatement mPreparedStatement;
        private ResultSet mResultSet;
        //Initialize some connection
        public CustomersModel() throws CustomersException {
            try {
                mConnection = DatabaseConnection.getSQLServerConnection();
                mStatement = mConnection.createStatement();
            } catch (SQLException e) {
                throw new CustomersException("Cannot connect to database");
            }
        }
        //Load data from database and add to local list
        public void loadCustomers() throws CustomersException {
            try {
                //language=TSQL
                String query = "SELECT * FROM product_manager.customers";
                mResultSet = mStatement.executeQuery(query);
                sCustomersList = new ArrayList<>();
                while (mResultSet.next()) {
                    int id = mResultSet.getInt("customer_id");
                    String customerName = mResultSet.getString("customer_name");
                    String customerGender = mResultSet.getString("gender");
                    String emailAddress = mResultSet.getString("email_address");
                    String phoneNumber = mResultSet.getString("phone_number");
                    String addressLine = mResultSet.getString("address_line");
                    String townCity = mResultSet.getString("town_city");
                    String stateCountyProvince = mResultSet.getString("state_county_province");
                    String country = mResultSet.getString("country");
                    sCustomersList.add(new Customers(id,customerName,customerGender,emailAddress,phoneNumber,addressLine,townCity,stateCountyProvince,country));
                }
            } catch (SQLException | CustomersException s) {
                throw new CustomersException("Cannot load database");
            }
        }

        //Add a type to database
        public boolean addCustomer(String customerName, String customerGender, String emailAddress, String phoneNumber, String addressLine, String townCity, String stateCountyProvince, String country) {
            //language=TSQL
            String insert = "INSERT INTO product_manager.customers values(?,?,?,?,?,?,?,?)";
            try {
                setValue(customerName, customerGender, emailAddress, phoneNumber, addressLine, townCity, stateCountyProvince, country, insert);
                mPreparedStatement.executeUpdate();
                //language=TSQL
                String query = "SELECT * FROM product_manager.customers ORDER BY customer_id DESC";
                mResultSet = mStatement.executeQuery(query);
                mResultSet.next();
                sCustomersList.add(new Customers(mResultSet.getInt("customer_id"),mResultSet.getString("customer_name"),mResultSet.getString("gender"),mResultSet.getString("email_address"),mResultSet.getString("phone_number"),mResultSet.getString("address_line"),mResultSet.getString("town_city"),mResultSet.getString("state_county_province"),mResultSet.getString("country")));
                return true;
            } catch (SQLException | CustomersException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

    //Get the newest index in the table to update, and optimize the database
    public int getLastedIndex() {
        //language=TSQL
        int index = -1;
        String query = "SELECT * FROM product_manager.customers ORDER BY customer_id DESC";
        try {
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            index = mResultSet.getInt("customer_id");
            mStatement.execute("DBCC CHECKIDENT ('[product_manager].[customers]' , RESEED, " + index + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return index;
    }

    //delete a customer in database
    public void deleteCustomer(int customerID) {
            String delete = "DELETE FROM product_manager.customers WHERE customer_id = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(delete);
            mPreparedStatement.setInt(1, customerID);
            mPreparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        //Update a type with a specific ID
        public boolean updateCustomer(int customerId, String customerName, String customerGender, String emailAddress, String phoneNumber, String addressLine, String townCity, String stateCountyProvince, String country) throws CustomersException {
            //language=TSQL
            String update = "UPDATE product_manager.customers SET customer_name = ?, gender = ?, email_address = ?, phone_number = ?, address_line = ?, town_city = ?, state_county_province = ?, country = ? WHERE customer_id = ?";
            try {
                setValue(customerName, customerGender, emailAddress, phoneNumber, addressLine, townCity, stateCountyProvince, country, update);
                mPreparedStatement.setInt(9, customerId);
                mPreparedStatement.executeUpdate();
                if (sCustomersList.isEmpty()) throw new ProductsException("The customers list is empty, cannot update");
                for (int i = 0; i < sCustomersList.size(); i++) {
                    if (sCustomersList.get(i).getCustomerId().equals(customerId)) {
                        sCustomersList.set(i,new Customers(customerId,customerName,customerGender,emailAddress,phoneNumber,addressLine,townCity,stateCountyProvince,country));
                    }
                }
                return true;
            } catch (SQLException | ProductsException e) {
                return false;
            }
        }

    //Set a value for a prepare statement
    private void setValue(String customerName, String customerGender, String emailAddress, String phoneNumber, String addressLine, String townCity, String stateCountyProvince, String country, String update) throws SQLException {
        mPreparedStatement = mConnection.prepareStatement(update);
        mPreparedStatement.setString(1, customerName);
        mPreparedStatement.setString(2, customerGender);
        mPreparedStatement.setString(3, emailAddress);
        mPreparedStatement.setString(4, phoneNumber);
        mPreparedStatement.setString(5, addressLine);
        mPreparedStatement.setString(6, townCity);
        mPreparedStatement.setString(7, stateCountyProvince);
        mPreparedStatement.setString(8, country);
    }

    //Get the type by giving an ID
    public  Customers getCustomer(Integer customerID) throws CustomersException {
            for (Customers customer : sCustomersList) {
                if (customer.getCustomerId().equals(customerID)) return customer;
            }
            throw new CustomersException("Cannot find the satisfied customer");
        }

        //Get the total size of the list
        private int getSize() {
            return sCustomersList.size();
        }

        //Use this to describe the object shortly
        @Override
        public String toString() {
            return "entity.Customers model has " + getSize() + " records";
        } //override

        //When the object is deposed, close the connection
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            mConnection.close();
        }


















}
