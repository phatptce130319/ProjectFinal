/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ha Van Ngoan
 */
public class OrderItems  {

    private Integer orderItemId;
    private String orderID;
    private String productID;
    private String productPrice;
    private int productQuantity;

    //Constructor
    public OrderItems(Integer orderItemId, String orderID, String productID, String productPrice, int productQuantity) throws OrderItemsException {
        setOrderID(orderID);
        setOrderItemId(orderItemId);
        setProductID(productID);
        setProductPrice(productPrice);
        setProductQuantity(productQuantity);
    }

// Get order item id
public Integer getOrderItemId() throws OrderItemsException {
        return orderItemId;
    }
//Get order id
    public String getOrderID() throws OrderItemsException {
        return orderID;
    }
//Set order id
    public void setOrderID(String orderID) throws OrderItemsException {
        this.orderID = orderID;
    }
//Get product id
    public String getProductID() throws OrderItemsException {
        return productID;
    }
//Set Product id
    public void setProductID(String productID) throws OrderItemsException {
        this.productID = productID;
    }
//Get product price
    public String getProductPrice() throws OrderItemsException {
        return productPrice;
    }
//Set product pice
    public void setProductPrice(String productPrice) throws OrderItemsException {
        this.productPrice = productPrice;
    }
//Get product quantity
    public int getProductQuantity() throws OrderItemsException {
        return productQuantity;
    }
//Set product quantity
    public void setProductQuantity(int productQuantity) throws OrderItemsException {
        this.productQuantity = productQuantity;
    }

    // Set order item id
    public void setOrderItemId(Integer orderItemId) throws OrderItemsException {
        this.orderItemId = orderItemId;
    }
}


