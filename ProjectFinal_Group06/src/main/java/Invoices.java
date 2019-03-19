
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ha Van Ngoan
 */
public class Invoices {

    private Integer invoiceNumber;
    private String orderId;
    private String employeeId;
    private String customerId;
    private String invoiceDate;
//Constructor

    public Invoices(Integer invoiceNumber, String orderId, String employeeId, String customerId, String invoiceDate) throws InvoicesException {
        setInvoiceNumber(invoiceNumber);
        setOrderId(orderId);
        setEmployeeId(employeeId);
        setCustomerId(customerId);
        setInvoiceDate(invoiceDate);
    }
// Get Invoice  number

    public Integer getInvoiceNumber() throws InvoicesException {
        if (invoiceNumber == null) {
            throw new InvoicesException("Can't get the Invoice number");
        }
        return invoiceNumber;
    }
// Set Invoice  number

    public void setInvoiceNumber(Integer invoiceNumber) throws InvoicesException {
        if (!invoiceNumber.toString().chars().allMatch(Character::isDigit)) {
            throw new InvoicesException("Only accept numbers");
        }
        this.invoiceNumber = invoiceNumber;
    }
//Get order id

    public String getOrderId() throws InvoicesException {
        if (orderId == null) {
            throw new InvoicesException("Can't get the Order's Id");
        }
        return orderId;
    }
//Set order id
    c

    public void setOrderId(String orderId) throws InvoicesException {
        if (!orderId.chars().allMatch(Character::isDigit)) {
            throw new InvoicesException("Only accept numbers");
        }
        if (orderId.equals("")) {
            throw new InvoicesException("The field can't empty");
        }
        this.orderId = orderId;
    }
//Get Employee id

    public String getEmployeeId() throws InvoicesException {
        if (employeeId == null) {
            throw new InvoicesException("Can't get the Employee's Id");
        }
        return employeeId;
    }
//Set Employee id

    public void setEmployeeId(String employeeId) throws InvoicesException {
        if (!employeeId.chars().allMatch(Character::isDigit)) {
            throw new InvoicesException("Only accept numbers");
        }
        if (employeeId.equals("")) {
            throw new InvoicesException("The field can't empty");
        }
        this.employeeId = employeeId;
    }
//Get Customer id

    public String getCustomerId() throws InvoicesException {
        if (customerId == null) {
            throw new InvoicesException("Can't get the Customer's Id");
        }
        return customerId;
    }
//Set Customer id

    public void setCustomerId(String customerId) throws InvoicesException {
        if (!customerId.chars().allMatch(Character::isDigit)) {
            throw new InvoicesException("Only accept numbers");
        }
        if (customerId.equals("")) {
            throw new InvoicesException("The field can't empty");
        }
        this.customerId = customerId;
    }
//Get Invoice date

    public String getInvoiceDate() throws InvoicesException {
        if (invoiceDate == null) {
            throw new InvoicesException("Can't get the Invoice date");
        }
        return invoiceDate;
    }
//Set Invoice date

    public void setInvoiceDate(String invoiceDate) throws InvoicesException {
        if(ValidDate(invoiceDate) == false){
            throw new InvoicesException("Invoice date invalid");
        }
        this.invoiceDate = invoiceDate;
    }
    
    public static boolean ValidDate(String date) {
        SimpleDateFormat CheckDate = new SimpleDateFormat("dd/MM/yyyy");
        Date testDate = null;
        try {
            testDate = CheckDate.parse(date);
        } catch (ParseException e) {
            return false;
        }
        if (!CheckDate.format(testDate).equals(date)) {
            return false;
        }
        return true;
    }

}
