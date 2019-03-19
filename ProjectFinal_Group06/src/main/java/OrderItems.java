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
    private Integer orderId;
    private Integer productId;
    private Double productPrice;
    private Integer productQuantity;

    //Constructor
    public OrderItems(Integer orderItemId, Integer orderId, Integer productId, Double productPrice, Integer productQuantity) throws OrderItemsException {
       setOrderId(orderId);
       setOrderItemId(orderItemId);
       setProductId(productId);
       setProductPrice(productPrice);
       setProductQuantity(productQuantity);
    }

// Get order item id
    public Integer getOrderItemId() throws OrderItemsException {
        if (orderItemId == null) {
            throw new OrderItemsException("Can't get the order item Id");
        }
        return orderItemId;
    }
// Set order item id
    public void setOrderItemId(Integer orderItemId) throws OrderItemsException {
        if (!orderItemId.toString().chars().allMatch(Character::isDigit)) {
            throw new OrderItemsException("Only accept numbers");
        }
        this.orderItemId = orderItemId;
    }
//Get order id
    public Integer getOrderId() throws OrderItemsException {
        if (orderId == null) {
            throw new OrderItemsException("Can't get the order's id");
        }
        return orderId;
    }
//Set order id
    public void setOrderId(Integer orderId) throws OrderItemsException {
        if (!orderId.toString().chars().allMatch(Character::isDigit)) {
            throw new OrderItemsException("Only accept numbers");
        }
        this.orderId = orderId;
    }
//Get product id
    public Integer getProductId() throws OrderItemsException {
        if (productId == null) {
            throw new OrderItemsException("Can't get the product's Id");
        }
        return productId;
    }
//Set Product id
    public void setProductId(Integer productId) throws OrderItemsException {
        if (!productId.toString().chars().allMatch(Character::isDigit)) {
            throw new OrderItemsException("Only accept numbers");
        }
        this.productId = productId;
    }
//Get product price
    public Double getProductPrice() throws OrderItemsException {
        if (productPrice == null) {
            throw new OrderItemsException("Can't get the product price");
        }
        return productPrice;
    }
//Set product pice
    public void setProductPrice(Double productPrice) throws OrderItemsException {
        /*if(!productPrice.toString().chars().allMatch(Character::isDigit)){
            throw new OrderItemsException("Only accept numbrs");
        }*/
        if(productPrice <= 0){
            throw new OrderItemsException("Product quantity must be positive number");
        }
        this.productPrice = productPrice;
    }
//Get product quantity
    public Integer getProductQuantity() throws OrderItemsException {
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
