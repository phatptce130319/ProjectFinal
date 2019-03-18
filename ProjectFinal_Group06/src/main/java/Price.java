/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Price {

    /**
     * @param args the command line arguments
     */
    private double productPrice;
    private double VAT;
    private double promotion;

    public Price(double productPrice, double VAT, double promotion) throws PriceException {
        setProductPrice(productPrice);
        setVAT(VAT);
        setPromotion(promotion);
    }

    public double getProductPrice() throws PriceException {
        return productPrice;
    }

    public void setProductPrice(double productPrice) throws PriceException {
        this.productPrice = productPrice;
    }

    public double getVAT() throws PriceException {
        return VAT;
    }

    public void setVAT(double VAT) throws PriceException {
        this.VAT = VAT;
    }

    public double getPromotion() throws PriceException {
        return promotion;
    }

    public void setPromotion(double promotion) throws PriceException {
        this.promotion = promotion;
    }
}
