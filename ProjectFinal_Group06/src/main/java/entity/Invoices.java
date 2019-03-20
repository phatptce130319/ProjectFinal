package entity;

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
public final class Invoices {

    private Integer invoiceNumber;
    private Integer orderId;
    private Integer employeeId;
    private Integer customerId;
    private Date invoiceDate;
//Constructor

    public Invoices(Integer invoiceNumber, Integer orderId, Integer employeeId, Integer customerId, Date invoiceDate) throws InvoicesException {
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

    public Integer getOrderId() throws InvoicesException {
        if (orderId == null) {
            throw new InvoicesException("Can't get the Order's Id");
        }
        return orderId;
    }
//Set order id
    

    public void setOrderId(Integer orderId) throws InvoicesException {
        if (!orderId.toString().chars().allMatch(Character::isDigit)) {
            throw new InvoicesException("Only accept numbers");
        }
        this.orderId = orderId;
    }
//Get Employee id

    public Integer getEmployeeId() throws InvoicesException {
        if (employeeId == null) {
            throw new InvoicesException("Can't get the Employee's Id");
        }
        return employeeId;
    }
//Set Employee id

    public void setEmployeeId(Integer employeeId) throws InvoicesException {
        if (!employeeId.toString().chars().allMatch(Character::isDigit)) {
            throw new InvoicesException("Only accept numbers");
        }
        this.employeeId = employeeId;
    }
//Get Customer id

    public Integer getCustomerId() throws InvoicesException {
        if (customerId == null) {
            throw new InvoicesException("Can't get the Customer's Id");
        }
        return customerId;
    }
//Set Customer id

    public void setCustomerId(Integer customerId) throws InvoicesException {
        if (!customerId.toString().chars().allMatch(Character::isDigit)) {
            throw new InvoicesException("Only accept numbers");
        }
        this.customerId = customerId;
    }
//Get Invoice date

    public Date getInvoiceDate() throws InvoicesException {
        if (invoiceDate == null) {
            throw new InvoicesException("Can't get the Invoice date");
        }
        return invoiceDate;
    }
//Set Invoice date

    public void setInvoiceDate(Date invoiceDate) throws InvoicesException {
        this.invoiceDate = invoiceDate;
    }

}
