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

    private int invoiceNumber;
    private String orderId;
    private String employeeId;
    private String customerId;
    private String invoiceDate;
//Constructor

    public Invoices(int invoiceNumber, String Order_id, String employeeId, String customerId, String invoiceDate) throws InvoicesException {
        setInvoiceNumber(invoiceNumber);
        setOrderId(Order_id);
        setEmployeeId(employeeId);
        setCustomerId(customerId);
        setInvoiceDate(invoiceDate);
    }
// Get Invoice  number

    public int getInvoiceNumber() throws InvoicesException {
        return invoiceNumber;
    }
// Set Invoice  number

    public void setInvoiceNumber(int invoiceNumber) throws InvoicesException {
        this.invoiceNumber = invoiceNumber;
    }
//Get order id

    public String getOrderId() throws InvoicesException {
        return orderId;
    }
//Set order id

    public void setOrderId(String Order_id) throws InvoicesException {
        this.orderId = Order_id;
    }
//Get Employee id

    public String getEmployeeId() throws InvoicesException {
        return employeeId;
    }
//Set Employee id

    public void setEmployeeId(String employeeId) throws InvoicesException {
        this.employeeId = employeeId;
    }
//Get Customer id

    public String getCustomerId() throws InvoicesException {
        return customerId;
    }
//Set Customer id

    public void setCustomerId(String customerId) throws InvoicesException {
        this.customerId = customerId;
    }
//Get Invoice date

    public String getInvoiceDate() throws InvoicesException {
        return invoiceDate;
    }
//Set Invoice date

    public void setInvoiceDate(String invoiceDate) throws InvoicesException {
        this.invoiceDate = invoiceDate;
    }

}
