/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Employees{
    
    private String employeeId;
    private String employeeName;
    private String phoneNumber;
    private String emailAddress;
    private String gender;

    
    public Employees(String employeeId, String employeeName, String phoneNumber, String emailAddress, String gender) throws EmployeesException {
        setEmployeeId(employeeId);
        setEmployeeName(employeeName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setGender(gender);
    }
    
    public String getEmployeeId() throws EmployeesException {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) throws EmployeesException {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() throws EmployeesException {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) throws EmployeesException {
        this.employeeName = employeeName;
    }

    public String getPhoneNumber() throws EmployeesException {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws EmployeesException {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() throws EmployeesException {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) throws EmployeesException {
        this.emailAddress = emailAddress;
    }

    public String getGender() throws EmployeesException {
        return gender;
    }

    public void setGender(String gender) throws EmployeesException {
        this.gender = gender;
    }
    
}
