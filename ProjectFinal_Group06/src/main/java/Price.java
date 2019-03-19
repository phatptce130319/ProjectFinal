/**
 *
 * @author Dang Buu Hoa
 */
public class Price {

    /**
     * @param args the command line arguments
     */
    private Double productPrice;
    private Double VAT;
    private Double promotion;
    
    //Constructor
    public Price(Double productPrice, Double VAT, Double promotion) throws PriceException {
        setProductPrice(productPrice);
        setVAT(VAT);
        setPromotion(promotion);
    }
    
    //get product Price
    public Double getProductPrice() throws PriceException {
        if (productPrice == null) throw new PriceException("Cannot get the product price");
        return productPrice;
    }
    
    //set product Price
    public void setProductPrice(Double productPrice) throws PriceException {
        if (!productPrice.toString().chars().allMatch(Character::isDigit))
            throw new PriceException("Only accept numbers");
        this.productPrice = productPrice;
    }
    
    //get VAT
    public Double getVAT() throws PriceException {
        if (VAT == null) throw new PriceException("Cannot get the VAT");
        return VAT;
    }
    
    //set VAT
    public void setVAT(Double VAT) throws PriceException {
        if (!VAT.toString().chars().allMatch(Character::isDigit))
            throw new PriceException("Only accept numbers");
        this.VAT = VAT;
    }
    
    //get promotion
    public Double getPromotion() throws PriceException {
        if (promotion == null) throw new PriceException("Cannot get the product promotion");
        return promotion;
    }
    
    //set promotion
    public void setPromotion(Double promotion) throws PriceException {
        if (!promotion.toString().chars().allMatch(Character::isDigit))
            throw new PriceException("Only accept numbers");
        this.promotion = promotion;
    }
}