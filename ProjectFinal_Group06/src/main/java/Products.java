/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Products {

    private String productID;
    private String productName;
    private double productPrice;
    private String productColor;
    private double productSize;
    private String productDescription;

    public Products(String productID, String productName, double productPrice, String productColor, double productSize, String productDescription) throws ProductsException{
        setProductID(productID);
        setProductName(productName);
        setProductPrice(productPrice);
        setProductColor(productColor);
        setProductSize(productSize);
        setProductDescription(productDescription);
    }

    public String getProductID() throws ProductsException{
        return productID;
    }

    public void setProductID(String productID) throws ProductsException {
        this.productID = productID;
    }

    public String getProductName()throws ProductsException {
        return productName;
    }

    public void setProductName(String productName) throws ProductsException {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public double getProductSize() throws ProductsException {
        return productSize;
    }

    public void setProductSize(double productSize) throws ProductsException {
        this.productSize = productSize;
    }

    public String getProductDescription() throws ProductsException {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
