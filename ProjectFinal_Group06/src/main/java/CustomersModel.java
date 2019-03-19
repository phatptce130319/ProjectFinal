import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersModel {
        static List<Customers> sCustomersList;
        private Connection mConnection;
        private Statement mStatement;
        private PreparedStatement mPreparedStatement;
        private ResultSet mResultSet;
        //Initialize some connection
        CustomersModel() throws CustomersException {
            try {
                mConnection = DatabaseConnection.getMySQLConnection();
                mStatement = mConnection.createStatement();
            } catch (SQLException e) {
                throw new CustomersException("Cannot connect to database");
            }
        }
        //Load data from database and add to local list
        void loadProducts() throws CustomersException {
            try {
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
        boolean addProduct(String customerName, String customerGender, String emailAddress, String phoneNumber, String addressLine, String townCity, String stateCountyProvince, String country) {
            String insert = "INSERT INTO product_manager.customers values(NULL,?,?,?,?,?,?,?,?)";
            try {
                setValue(customerName, customerGender, emailAddress, phoneNumber, addressLine, townCity, stateCountyProvince, country, insert);
                mPreparedStatement.executeUpdate();
                String query = "SELECT * FROM product_manager.customers ORDER BY customer_id DESC";
                mResultSet = mStatement.executeQuery(query);
                mResultSet.next();
                sCustomersList.add(new Customers(mResultSet.getInt("customer_id"),mResultSet.getString("customer_name"),mResultSet.getString("gender"),mResultSet.getString("email_address"),mResultSet.getString("phone_number"),mResultSet.getString("address_line"),mResultSet.getString("town_city"),mResultSet.getString("state_county_province"),mResultSet.getString("country"));
                return true;
            } catch (SQLException | CustomersException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        //Update a type with a specific ID
        boolean updateCustomer(int customerId, String customerName, String customerGender, String emailAddress, String phoneNumber, String addressLine, String townCity, String stateCountyProvince, String country) throws CustomersException {
            String update = "UPDATE product_manager.customers SET customer_name = ?, gender = ?, email_address = ?, phone_number = ?, address_line = ?, town_city = ?, state_county_province = ?, country = ? WHERE product_id = ?";
            try {
                setValue(customerName, customerGender, emailAddress, phoneNumber, addressLine, townCity, stateCountyProvince, country, update);
                mPreparedStatement.setInt(9, customerId);

                mPreparedStatement.executeUpdate();
                if (sProductsList.size() == 0) throw new ProductsException("The Type list is empty, cannot update");
                for (int i = 0; i < sProductsList.size(); i++) {
                    if (sProductsList.get(i).getProductId().equals(productId)) {
                        sProductsList.set(i,new Products(productId,productName,productPrice,productColor,productSize,productDescription));
                    }
                }
                return true;
            } catch (SQLException | ProductsException e) {
                return false;
            }
        }

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
        Products getProduct(Integer productId) throws ProductsException {
            for (Products product : sProductsList) {
                if (product.getProductId().equals(productId)) return product;
            }
            throw new ProductsException("Cannot find the satisfied product");
        }

        //Get the total size of the list
        private int getSize() {
            return sProductsList.size();
        }

        //Use this to describe the object shortly
        @Override
        public String toString() {
            return "Products model has " + getSize() + " records";
        } //override

        //When the object is deposed, close the connection
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            mConnection.close();
        }


















}
