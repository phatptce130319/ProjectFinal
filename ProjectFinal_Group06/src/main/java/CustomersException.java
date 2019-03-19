//Author : Phan Tan Phat
//The exception is generated if Customers has errors
public class CustomersException extends Exception {
    public CustomersException(String message) {
        super (message);
        System.out.println("Customers Exception: " + message);
    }
}
