/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Orders {

    private String orderId;
    private String customerId;
    private String employeeId;
    private String dateOrder;
    private String addressOrder;

    public Orders(String orderId, String customerId, String employeeId, String dateOrder, String addressOrder) throws OrdersException {
        setOrderId(orderId);
        setCustomerId(customerId);
        setEmployeeId(employeeId);
        setDateOrder(dateOrder);
        setAddressOrder(addressOrder);
    }
    public String getOrderId() throws OrdersException {
        return orderId;
    }

    public void setOrderId(String orderId) throws OrdersException {
        this.orderId = orderId;
    }

    public String getCustomerId() throws OrdersException {
        return customerId;
    }

    public void setCustomerId(String customerId) throws OrdersException {
        this.customerId = customerId;
    }

    public String getEmployeeId() throws OrdersException {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) throws OrdersException {
        this.employeeId = employeeId;
    }

    public String getDateOrder() throws OrdersException {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) throws OrdersException {
        this.dateOrder = dateOrder;
    }

    public String getAddressOrder()throws OrdersException {
        return addressOrder;
    }

    public void setAddressOrder(String addressOrder) throws OrdersException {
        this.addressOrder = addressOrder;
    }
}
