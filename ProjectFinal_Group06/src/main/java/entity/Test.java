package entity;
import java.sql.SQLException;
public class Test {
    public static void main(String [] args){
        try {
            CustomersModel cm = new CustomersModel();
            cm.loadCustomers();
            CustomersModel.sCustomersList.forEach(System.out::println);
        } catch (CustomersException e) {
            e.printStackTrace();
        }
    }
}
