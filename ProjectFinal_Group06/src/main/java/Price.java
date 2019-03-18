/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dang Buu Hoa
 */
public class Price {

    /**
     * @param args the command line arguments
     */
    private int productID;
    private double productPrice;
    private double VAT;
    private double promotion;
    
    //Constructor
    public Price(int productID, double productPrice, double VAT, double promotion) throws PriceException {
        setProductID(productID);
        setProductPrice(productPrice);
        setVAT(VAT);
        setPromotion(promotion);
    }

    public int getProductID() throws PriceException {
        return productID;
    }

    public void setProductID (int productID) throws PriceException {
        this.productID = productID;
    }

    //get product Price
    public double getProductPrice() throws PriceException {
        return productPrice;
    }
    
    //set product Price
    public void setProductPrice(double productPrice) throws PriceException {
        this.productPrice = productPrice;
    }
    
    //get VAT
    public double getVAT() throws PriceException {
        return VAT;
    }
    
    //set VAT
    public void setVAT(double VAT) throws PriceException {
        this.VAT = VAT;
    }
    
    //get promotion
    public double getPromotion() throws PriceException {
        return promotion;
    }
    
    //set promotion
    public void setPromotion(double promotion) throws PriceException {
        this.promotion = promotion;
    }
}
