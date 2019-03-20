
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PriceModel {

    static List<Price> sPriceList;
    private Connection mConnection;
    private Statement mStatement;
    private PreparedStatement mPreparedStatement;
    private ResultSet mResultSet;
//Initialize some connection
    PriceModel() throws PriceException {
        try {
            mConnection = DatabaseConnection.getMySQLConnection();
            mStatement = mConnection.createStatement();
        } catch (SQLException e) {
            throw new PriceException("Can't connect to database");
        }
    }
//Load data from database and add to local list
    void loadPrice() throws PriceException {
        try {
            //language=TSQL
            String query = "SELECT * FROM product_manager.price";
            mResultSet = mStatement.executeQuery(query);
            sPriceList = new ArrayList<>();
            while (mResultSet.next()) {
                int id = mResultSet.getInt("product_id");
                double vat = mResultSet.getDouble("vat");
                double promotion = mResultSet.getDouble("promotion");
                double productPrice = mResultSet.getDouble("product_price");
                sPriceList.add(new Price(id, vat, promotion, productPrice));
            }
        } catch (SQLException | PriceException s) {
            throw new PriceException("Can't load database");
        }
    }
//Add a price to database
    boolean addPrice(Double productPrice, Double vat, Double promotion) {
        //language=TSQL
        String insert = "INSERT INTO product_manager.price values(?,?,?)";
        try {
            setValue(productPrice, vat, promotion, insert);
            mPreparedStatement.executeUpdate();
            //language=TSQL
            String query = "SELECT * FROM product_manager.price ORDER BY product_price DESC";
            mResultSet = mStatement.executeQuery(query);
            mResultSet.next();
            sPriceList.add(new Price(mResultSet.getInt("product_id"), mResultSet.getDouble("product_price"), mResultSet.getDouble("vat"), mResultSet.getDouble("promotion")));
            return true;
        } catch (SQLException | PriceException s) {
            System.out.println(s.getMessage());
            return false;
        }
    }
//Update a price with a specific ID
    boolean updatePrice(Integer productId, Double productPrice, Double vat, Double promotion) throws PriceException {
        //language=TSQL
        String update = "UPDATE product_manager.price SET vat = ?, promotion = ?, product_price = ? WHERE product_id = ?";
        try {
            setValue(productPrice, vat, promotion, update);
            mPreparedStatement.setInt(4, productId);
            mPreparedStatement.executeUpdate();
            if (sPriceList.size() == 0) {
                throw new PriceException("The Price list is empty, cannot update");
            }
            for (int i = 0; i < sPriceList.size(); i++) {
                if (sPriceList.get(i).getProductId().equals(productId)) {
                    sPriceList.set(i, new Price(productId, productPrice, vat, promotion));
                }
            }
            return true;
        } catch (SQLException | PriceException s) {
            return false;
        }
    }

    private void setValue(Double productPrice, Double vat, Double promotion, String update) throws SQLException {
        mPreparedStatement = mConnection.prepareStatement(update);
        mPreparedStatement.setDouble(1, productPrice);
        mPreparedStatement.setDouble(2, vat);
        mPreparedStatement.setDouble(3, promotion);
    }
//Get the total size of the list
    private int Size() {
        return sPriceList.size();
    }
    //Get the price by giving an ID
    Price getPrice(Integer productId) throws PriceException{
        for(Price price : sPriceList){
            if(price.getProductId().equals(productId)) return price;
        }
        throw new PriceException("Cannot find the satisfied product");
    }
    
     //Use this to describe the object shortly
    @Override
    public String toString() {
        return "Price model has " + Size() + " records";
    } //override

    //When the object is deposed, close the connection
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mConnection.close();
    }
}
