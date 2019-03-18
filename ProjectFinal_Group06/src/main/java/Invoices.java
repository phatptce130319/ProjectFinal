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
    private Integer orderId;
    private Integer employeeId;
    private Integer customerId;
    private String invoiceDate;
//Constructor

    public Invoices(Integer invoiceNumber, Integer Order_id, Integer employeeId, Integer customerId, String invoiceDate) throws InvoicesException {
        setInvoiceNumber(invoiceNumber);
        setOrderId(Order_id);
        setEmployeeId(employeeId);
        setCustomerId(customerId);
        setInvoiceDate(invoiceDate);
    }
// Get Invoice  number

    public Integer getInvoiceNumber() throws InvoicesException {
        return invoiceNumber;
    }
// Set Invoice  number

    public void setInvoiceNumber(Integer invoiceNumber) throws InvoicesException {
        this.invoiceNumber = invoiceNumber;
    }
//Get order id

    public Integer getOrderId() throws InvoicesException {
        return orderId;
    }
//Set order id

    public void setOrderId(Integer Order_id) throws InvoicesException {
        this.orderId = Order_id;
    }
//Get Employee id

    public Integer getEmployeeId() throws InvoicesException {
        return employeeId;
    }
//Set Employee id

    public void setEmployeeId(Integer employeeId) throws InvoicesException {
        this.employeeId = employeeId;
    }
//Get Customer id

    public Integer getCustomerId() throws InvoicesException {
        return customerId;
    }
//Set Customer id

    public void setCustomerId(Integer customerId) throws InvoicesException {
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
