
import java.sql.SQLException;

public class TestClass {
    public static void main(String[] args){
        try {
            DatabaseConnection.getMySQLConnection();
            System.out.println("Success");
        } catch (SQLException sql){
            System.out.println("Fail");
        }
    }
}
