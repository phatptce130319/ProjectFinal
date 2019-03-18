import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Phan Tan Phat
class DatabaseConnection {
    static Connection getMySQLConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "product_manager";
        String userName = "root";
        String password = "123456";
        //There are two types of connections.

        //This use for local database, the sql has been created.
        return getMySQLConnection(hostName, dbName, userName, password);


    }

    private static Connection getMySQLConnection(String hostName, String dbName,
                                                 String userName, String password) throws SQLException {
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433;database=" + dbName + ";user=" + userName + ";password=" + password;
        return DriverManager.getConnection(connectionURL, userName, password);

    }
}
