//Author : Phan Tan Phat
//The exception is generated if Customers has errors
class CustomersException extends Exception {
    CustomersException(String message) {
        super (message);
        System.out.println("Customers Exception: " + message);
    }
}
