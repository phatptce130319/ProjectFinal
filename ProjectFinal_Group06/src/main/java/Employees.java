//Author : Phan Tan Phat
public class Employees{

    private Integer employeeId;
    private String employeeName;
    private String phoneNumber;
    private String emailAddress;
    private String gender;

    //Contructor to create employees
    public Employees(Integer employeeId, String employeeName, String phoneNumber, String emailAddress, String gender) throws EmployeesException {
        setEmployeeId(employeeId);
        setEmployeeName(employeeName);
        setPhoneNumber(phoneNumber);
        setEmailAddress(emailAddress);
        setGender(gender);
    }

    //Get employee id
    public Integer getEmployeeId() throws EmployeesException {
        if (employeeId == null) throw new EmployeesException("Cannot get the employee's ID");
        return employeeId;
    }

    //Set employee id
    public void setEmployeeId(Integer employeeId) throws EmployeesException {
        if (!employeeId.toString().chars().allMatch(Character::isDigit))
            throw new EmployeesException("Only accept numbers");
        this.employeeId = employeeId;
    }

    //Get employee ID
    public String getEmployeeName() throws EmployeesException {
        if (employeeName == null) throw new EmployeesException("Cannot get the employee's name");
        return employeeName;
    }

    //Set employee name
    public void setEmployeeName(String employeeName) throws EmployeesException {
        this.employeeName = employeeName;
    }

    //get phone number
    public String getPhoneNumber() throws EmployeesException {
        if (phoneNumber == null) throw new EmployeesException("Cannot get the employee's phone number");
        return phoneNumber;
    }

    //set phone number
    public void setPhoneNumber(String phoneNumber) throws EmployeesException {
        this.phoneNumber = phoneNumber;
    }

    //Get email address
    public String getEmailAddress() throws EmployeesException {
        if (emailAddress == null) throw new EmployeesException("Cannot get the employee's email address");
        return emailAddress;
    }

    //Set email address
    public void setEmailAddress(String emailAddress) throws EmployeesException {
        this.emailAddress = emailAddress;
    }

    //get gender
    public String getGender() throws EmployeesException {
        if (gender == null) throw new EmployeesException("Cannot get the employee's gender");
        return gender;
    }

    //set Gender
    public void setGender(String gender) throws EmployeesException {
        if (!gender.equals("Female") & !gender.equals("Male"))
            throw new EmployeesException("Only accept Female or Male in gender");
        this.gender = gender;
    }
    
}
