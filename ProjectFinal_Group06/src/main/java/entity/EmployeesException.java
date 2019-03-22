package entity;

//Author : Phan Tan Phat
//The exception is generated if employees has errors
public class EmployeesException extends Exception {
    public EmployeesException(String message) {
        super (message);
        System.out.println("Employees Exception: " + message);
    }
}
