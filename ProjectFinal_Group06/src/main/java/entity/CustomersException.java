package entity;

//Author : Phan Tan Phat
//The exception is generated if entity.Customers has errors
public class CustomersException extends Exception {
    CustomersException(String message) {
        super (message);
        System.out.println("Customers Exception: " + message);
    }
}
