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

    private String orderItemId;
    private String orderId;
    private String productId;
    private String productPrice;
    private Integer productQuantity;
    
    //Constructor
    public OrderItems(String orderItemId, String orderId, String productId, String productPrice, Integer productQuantity) throws OrderItemsException {
       setOrderId(orderId);
       setOrderItemId(orderItemId);
       setProductId(productId);
       setProductPrice(productPrice);
       setProductQuantity(productQuantity);
    }
    
// Get order item id
    public String getOrderItemId() throws OrderItemsException {
        if (orderItemId == null) {
            throw new OrderItemsException("Can't get the order item Id");
        }
        return orderItemId;
    }
// Set order item id
    public void setOrderItemId(String orderItemId) throws OrderItemsException {
        if (!orderItemId.chars().allMatch(Character::isDigit)) {
            throw new OrderItemsException("Only accept numbers");
        }
        if (orderItemId.equals("")) {
            throw new OrderItemsException("The field can't empty");
        }
        this.orderItemId = orderItemId;
    }
//Get order id
    public String getOrderId() throws OrderItemsException {
        if (orderId == null) {
            throw new OrderItemsException("Can't get the order's id");
        }
        return orderId;
    }
//Set order id
    public void setOrderId(String orderId) throws OrderItemsException {
        if (!orderId.chars().allMatch(Character::isDigit)) {
            throw new OrderItemsException("Only accept numbers");
        }
        if (orderItemId.equals("")) {
            throw new OrderItemsException("The field can't empty");
        }
        this.orderId = orderId;
    }
//Get product id
    public String getProductId() throws OrderItemsException {
        if (productId == null) {
            throw new OrderItemsException("Can't get the product's Id");
        }
        return productId;
    }
//Set Product id
    public void setProductId(String productId) throws OrderItemsException {
        if (!productId.chars().allMatch(Character::isDigit)) {
            throw new OrderItemsException("Only accept numbers");
        }
        if (orderItemId.equals("")) {
            throw new OrderItemsException("The field can't empty");
        }
        this.productId = productId;
    }
//Get product price
    public String getProductPrice() throws OrderItemsException {
        if (productPrice == null) {
            throw new OrderItemsException("Can't get the product price");
        }
        return productPrice;
    }
//Set product pice
    public void setProductPrice(String productPrice) throws OrderItemsException {
        if(!productPrice.chars().allMatch(Character::isDidit)){
            throw new OrderItemsException("Only accept numbrs");
        }
        if(Integer.parseInt(productPrice) <= 0){
            throw new OrderItemsException("Product quantity must be positive number");
        }
        this.productPrice = productPrice;
    }
//Get product quantity
    public int getProductQuantity() throws OrderItemsException {
        if (productQuantity == null) {
            throw new OrderItemsException("Can't get the product quantity");
        }
        return productQuantity;
    }
//Set product quantity
    public void setProductQuantity(Integer productQuantity) throws OrderItemsException {
        if(!productQuantity.toString().chars().allMatch(Character::isDigit)){
            throw new OrderItemsException("Only accept numbers");
        }
        if(productQuantity <= 0){
            throw new OrderItemsException("Product quantity must be positive number");
        }
        this.productQuantity = productQuantity;
    }

}


