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
    void loadProduct() throws ProductsException {
        try {
            String query = "SELECT * FROM type";
            mResultSet = mStatement.executeQuery(query);
            sProductsList = new ArrayList<>();
            while (mResultSet.next()) {
                int id = mResultSet.getInt("tID");
                String typeName = mResultSet.getString("tName");
                sProductsList.add(null);
            }
        } catch (SQLException s) {
            throw new ProductsException("Cannot load database");
        }
    }

    //Add a type to database
    boolean addType(String tName) {
        String insert = "INSERT INTO type values(NULL,?)";
        try {
            mPreparedStatement = mConnection.prepareStatement(insert);
            mPreparedStatement.setString(1, tName);
            mPreparedStatement.executeUpdate();
            String query = "SELECT * FROM type ORDER BY tID DESC LIMIT 1";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            sProductsList.add(null);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //Update a type with a specific ID
    boolean updateType(int tId, String newTName) throws ProductsException {
        String update = "UPDATE type SET tName = ? WHERE tID = ?";
        try {
            mPreparedStatement = mConnection.prepareStatement(update);
            mPreparedStatement.setString(1, newTName);
            mPreparedStatement.setInt(2, tId);
            mPreparedStatement.executeUpdate();
            if (sProductsList.size() == 0) throw new ProductsException("The Type list is empty, cannot update");
            for (int i = 0; i < sProductsList.size(); i++) {

            }
            return true;
        } catch (SQLException | ProductsException e) {
            return false;
        }
    }

    //Get the type by giving an ID
    Products getType(Integer id) throws ProductsException {
        for (Products type : sProductsList) {
            if (type.getProductID() == id) return type;
        }
        throw new ProductsException("Cannot find the satisfied type");
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
