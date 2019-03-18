/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dang Buu Hoa
 */
public class Orders {

    private String orderId;
    private String customerId;
    private String employeeId;
    private String dateOrder;
    private String addressOrder;
    
    //Constructor
    public Orders(String orderId, String customerId, String employeeId, String dateOrder, String addressOrder) throws OrdersException {
        setOrderId(orderId);
        setCustomerId(customerId);
        setEmployeeId(employeeId);
        setDateOrder(dateOrder);
        setAddressOrder(addressOrder);
    }
    
    //get order ID
    public String getOrderId() throws OrdersException {
        return orderId;
    }
    
    //set order ID
    public void setOrderId(String orderId) throws OrdersException {
        this.orderId = orderId;
    }
    
    //get customer ID
    public String getCustomerId() throws OrdersException {
        return customerId;
    }
    
    //set customer ID
    public void setCustomerId(String customerId) throws OrdersException {
        this.customerId = customerId;
    }
    
    //get employee ID
    public String getEmployeeId() throws OrdersException {
        return employeeId;
    }
    
    //set employee ID
    public void setEmployeeId(String employeeId) throws OrdersException {
        this.employeeId = employeeId;
    }
    
    //get date Order
    public String getDateOrder() throws OrdersException {
        return dateOrder;
    }
    
    //set date Order
    public void setDateOrder(String dateOrder) throws OrdersException {
        this.dateOrder = dateOrder;
    }
    
    //get address Order
    public String getAddressOrder()throws OrdersException {
        return addressOrder;
    }
    
    //set address Order
    public void setAddressOrder(String addressOrder) throws OrdersException {
        this.addressOrder = addressOrder;
    }
}
