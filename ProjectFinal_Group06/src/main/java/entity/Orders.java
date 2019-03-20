package entity;

import java.util.Date;

/**
 *
 * @author Dang Buu Hoa
 */
public final class Orders {

    private Integer orderId;
    private Integer customerId;
    private Integer employeeId;
    private Date dateOrder;
    private String addressOrder;
    
    //Constructor
    public Orders(Integer orderId, Integer customerId, Integer employeeId, Date dateOrder, String addressOrder) throws OrdersException {
        setOrderId(orderId);
        setCustomerId(customerId);
        setEmployeeId(employeeId);
        setDateOrder(dateOrder);
        setAddressOrder(addressOrder);
    }
    
    //get order ID
    public Integer getOrderId() throws OrdersException {
        if (orderId == null) throw new OrdersException("Cannot get the order's ID");
        return orderId;
    }
    
    //set order ID
    public void setOrderId(Integer orderId) throws OrdersException {
        if (!orderId.toString().chars().allMatch(Character::isDigit))
            throw new OrdersException("Only accept numbers");
        this.orderId = orderId;
    }
    
    //get customer ID
    public Integer getCustomerId() throws OrdersException {
        if (customerId == null) throw new OrdersException("Cannot get the customer's ID");
        return customerId;
    }
    
    //set customer ID
    public void setCustomerId(Integer customerId) throws OrdersException {
        if (!customerId.toString().chars().allMatch(Character::isDigit))
            throw new OrdersException("Only accept numbers");
        this.customerId = customerId;
    }
    
    //get employee ID
    public Integer getEmployeeId() throws OrdersException {
        if (employeeId == null) throw new OrdersException("Cannot get the employee's ID");
        return employeeId;
    }
    
    //set employee ID
    public void setEmployeeId(Integer employeeId) throws OrdersException {
        if (!employeeId.toString().chars().allMatch(Character::isDigit))
            throw new OrdersException("Only accept numbers");
        this.employeeId = employeeId;
    }
    
    //get date Order
    public Date getDateOrder() throws OrdersException {
        return dateOrder;
    }
    
    //set date Order
    public void setDateOrder(Date dateOrder) throws OrdersException {
        this.dateOrder = dateOrder;
    }
    
    //get address Order
    public String getAddressOrder()throws OrdersException {
        if (addressOrder == null) throw new OrdersException("Cannot get the order's address");
        return addressOrder;
    }
    
    //set address Order
    public void setAddressOrder(String addressOrder) throws OrdersException {
        if (addressOrder.equals("")) throw new OrdersException("The field cannot be empty");
        this.addressOrder = addressOrder;
    }
}