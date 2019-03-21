package entity;

//Author : Phan Tan Phat
//The exception is generated if employees has errors
class EmployeesException extends Exception {
    EmployeesException(String message) {
        super (message);
        System.out.println("Employees Exception: " + message);
    }
}
