/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dang Buu Hoa
 */
public class Products {

    private String productID;
    private String productName;
    private double productPrice;
    private String productColor;
    private double productSize;
    private String productDescription;
    
    //Constructor
    public Products(String productID, String productName, double productPrice, String productColor, double productSize, String productDescription) throws ProductsException{
        setProductID(productID);
        setProductName(productName);
        setProductPrice(productPrice);
        setProductColor(productColor);
        setProductSize(productSize);
        setProductDescription(productDescription);
    }
    
    //get product ID
    public String getProductID() throws ProductsException{
        return productID;
    }
    
    //set product ID
    public void setProductID(String productID) throws ProductsException {
        this.productID = productID;
    }
    
    //get product Name
    public String getProductName()throws ProductsException {
        return productName;
    }
    
    //set product Name
    public void setProductName(String productName) throws ProductsException {
        this.productName = productName;
    }
    
    //get product Price
    public double getProductPrice() {
        return productPrice;
    }
    
    //set product Price
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    //get product Color
    public String getProductColor() {
        return productColor;
    }

    //set product Color
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    //get product Size
    public double getProductSize() throws ProductsException {
        return productSize;
    }

    //set product Size
    public void setProductSize(double productSize) throws ProductsException {
        this.productSize = productSize;
    }

    //get product Description
    public String getProductDescription() throws ProductsException {
        return productDescription;
    }

    //set product Description
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
