/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class OrderItems  {

    private String orderItemId;
    private String orderID;
    private String productID;
    private String productPrice;
    private int productQuantity;

    public String getOrderItemId() throws OrderItemsException {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) throws OrderItemsException {
        this.orderItemId = orderItemId;
    }

    public String getOrderID() throws OrderItemsException {
        return orderID;
    }

    public void setOrderID(String orderID) throws OrderItemsException {
        this.orderID = orderID;
    }

    public String getProductID() throws OrderItemsException {
        return productID;
    }

    public void setProductID(String productID) throws OrderItemsException {
        this.productID = productID;
    }

    public String getProductPrice() throws OrderItemsException {
        return productPrice;
    }

    public void setProductPrice(String productPrice) throws OrderItemsException {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() throws OrderItemsException {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) throws OrderItemsException {
        this.productQuantity = productQuantity;
    }

    public OrderItems(String orderItemId, String orderID, String productID, String productPrice, int productQuantity) throws OrderItemsException {
       setOrderID(orderID);
       setOrderItemId(orderItemId);
       setProductID(productID);
       setProductPrice(productPrice);
       setProductQuantity(productQuantity);
    }
}


