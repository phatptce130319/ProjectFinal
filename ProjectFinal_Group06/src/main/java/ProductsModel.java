

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsModel {


    static List<Products> sProductsList;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;
    //Initialize some connection
    ProductsModel() throws ProductsException {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
        } catch (SQLException e) {
            throw new ProductsException("Cannot connect to database");
        }
    }
    //Load data from database and add to local list
    void loadProducts() throws ProductsException {
        try {
            String query = "SELECT * FROM product_manager.products";
            mResultSet = mStatement.executeQuery(query);
            sProductsList = new ArrayList<>();
            while (mResultSet.next()) {
                int id = mResultSet.getInt("product_id");
                String productName = mResultSet.getString("product_name");
                double productPrice = mResultSet.getDouble("product_price");
                String productColor = mResultSet.getString("product_color");
                double productSize = mResultSet.getDouble("product_size");
                String productDescription = mResultSet.getString("product_description");
                sProductsList.add(new Products(id,productName,productPrice,productColor,productSize,productDescription));
            }
        } catch (SQLException s) {
            throw new ProductsException("Cannot load database");
        }
    }

    //Add a type to database
    boolean addProduct(String productName, double productPrice,String productColor, double productSize, String productDescription) {
        String insert = "INSERT INTO product_manager.products values(NULL,?,?,?,?,?)";
        try {
            mPreparedStatement = mConnection.prepareStatement(insert);
            mPreparedStatement.setString(1, productName);
            mPreparedStatement.setDouble(2, productPrice);
            mPreparedStatement.setString(3, productColor);
            mPreparedStatement.setDouble(4, productSize);
            mPreparedStatement.setString(5, productDescription);
            mPreparedStatement.executeUpdate();
            String query = "SELECT * FROM product_manager.products ORDER BY product_id DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            sProductsList.add(new Products(mResultSet.getInt("product_id"),mResultSet.getString("product_name"),mResultSet.getDouble("product_price"),mResultSet.getString("product_color"),mResultSet.getDouble("product_size"),mResultSet.getString("product_description")));
            return true;
        } catch (SQLException | ProductsException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Update a type with a specific ID
    boolean updateProduct(int productId, String productName, double productPrice, String productColor, double productSize, String productDescription) throws ProductsException {
        String update = "UPDATE product_manager.products SET product_name = ?, product_price = ?, product_color = ?, product_size = ?, product_description = ? WHERE product_id = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setString(1, productName);
            mPreparedStatement.setDouble(2, productPrice);
            mPreparedStatement.setString(3, productColor);
            mPreparedStatement.setDouble(4, productSize);
            mPreparedStatement.setString(5, productDescription);
            mPreparedStatement.setInt(6, productId);
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
        return "Type model has " + getSize() + " records";
    } //override

    //When the object is deposed, close the connection
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mConnection.close();
    }


}
