//Author : Phan Tan Phat
public class Employees{
    
    private String employeeId;
    private String employeeName;
    private String phoneNumber;
    private String emailAddress;
    private String gender;

    //Contructor to create employees
    public Employees(String employeeId, String employeeName, String phoneNumber, String emailAddress, String gender) throws EmployeesException {
        setEmployeeId(employeeId);
        setEmployeeName(employeeName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setGender(gender);
    }

    //Get employee id
    public String getEmployeeId() throws EmployeesException {
        return employeeId;
    }

    //Set employee id
    public void setEmployeeId(String employeeId) throws EmployeesException {
        this.employeeId = employeeId;
    }

    //Get employee ID
    public String getEmployeeName() throws EmployeesException {
        return employeeName;
    }

    //Set employee name
    public void setEmployeeName(String employeeName) throws EmployeesException {
        this.employeeName = employeeName;
    }

    //get phone number
    public String getPhoneNumber() throws EmployeesException {
        return phoneNumber;
    }

    //set phone number
    public void setPhoneNumber(String phoneNumber) throws EmployeesException {
        this.phoneNumber = phoneNumber;
    }

    //Get email address
    public String getEmailAddress() throws EmployeesException {
        return emailAddress;
    }

    //Set email address
    public void setEmailAddress(String emailAddress) throws EmployeesException {
        this.emailAddress = emailAddress;
    }

    //get gender
    public String getGender() throws EmployeesException {
        return gender;
    }

    //set Gender
    public void setGender(String gender) throws EmployeesException {
        this.gender = gender;
    }
    
}
