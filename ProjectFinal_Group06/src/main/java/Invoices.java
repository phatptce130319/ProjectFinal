/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Invoices  {
    private int invoiceNumber;
    private String orderId;
    private String employeeId;
    private String customerId;
    private String invoiceDate;

    public Invoices(int invoiceNumber, String Order_id, String employeeId, String customerId, String invoiceDate) throws InvoicesException{
        setInvoiceNumber(invoiceNumber);
        setOrderId(Order_id);
        setEmployeeId(employeeId);
        setCustomerId(customerId);
        setInvoiceDate(invoiceDate);
    }
    
    public int getInvoiceNumber() throws InvoicesException {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) throws InvoicesException {
        this.invoiceNumber = invoiceNumber;
    }

    public String getOrderId() throws InvoicesException {
        return orderId;
    }

    public void setOrderId(String Order_id) throws InvoicesException {
        this.orderId = Order_id;
    }

    public String getEmployeeId() throws InvoicesException {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) throws InvoicesException {
        this.employeeId = employeeId;
    }

    public String getCustomerId() throws InvoicesException {
        return customerId;
    }

    public void setCustomerId(String customerId) throws InvoicesException {
        this.customerId = customerId;
    }

    public String getInvoiceDate() throws InvoicesException {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) throws InvoicesException {
        this.invoiceDate = invoiceDate;
    }
    
}
