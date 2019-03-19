/**
 *
 * @author Dang Buu Hoa
 */
public final class Products {

    private Integer productId;
    private String productName;
    private Double productPrice;
    private String productColor;
    private Double productSize;
    private String productDescription;
    
    //Constructor
    public Products(Integer productId, String productName, Double productPrice, String productColor, Double productSize, String productDescription) throws ProductsException {
        setProductId(productId);
        setProductName(productName);
        setProductPrice(productPrice);
        setProductColor(productColor);
        setProductSize(productSize);
        setProductDescription(productDescription);
    }
    //get product ID
    public Integer getProductId() throws ProductsException {
        if (productId == null) throw new ProductsException("Cannot get the product's ID");
        return productId;
    }
    
    //set product ID
    public void setProductId(Integer productId) throws ProductsException {
        if (!productId.toString().chars().allMatch(Character::isDigit))
            throw new ProductsException("Only accept numbers");
        this.productId = productId;
    }
    
    //get product Name
    public String getProductName()throws ProductsException {
        if (productName == null) throw new ProductsException("Cannot get the product's name");
        return productName;
    }
    
    //set product Name
    public void setProductName(String productName) throws ProductsException {
        if (productName.chars().noneMatch(Character::isLetter))
            throw new ProductsException("Only alphabet letters are accepted");
        if (productName.equals("")) throw new ProductsException("The field cannot be empty");
        this.productName = productName;
    }
    
    //get product Price
    public Double getProductPrice() throws ProductsException {
        if (productPrice == null) throw new ProductsException("Cannot get the product price");
        return productPrice;
    }
    
    //set product Price
    public void setProductPrice(Double productPrice) throws ProductsException {
        if (!productPrice.toString().chars().allMatch(Character::isDigit))
            throw new ProductsException("Only accept numbers");
        this.productPrice = productPrice;
    }

    //get product Color
    public String getProductColor() throws ProductsException {
        if (productColor == null) throw new ProductsException("Cannot get the product's color");
        return productColor;
    }

    //set product Color
    public void setProductColor(String productColor) throws ProductsException {
        if (productColor.chars().noneMatch(Character::isLetter))
            throw new ProductsException("Only alphabet letters are accepted");
        if (productColor.equals("")) throw new ProductsException("The field cannot be empty");
        this.productColor = productColor;
    }

    //get product Size
    public Double getProductSize() throws ProductsException {
        if (productSize == null) throw new ProductsException("Cannot get the product size");
        return productSize;
    }

    //set product Size
    public void setProductSize(Double productSize) throws ProductsException {
        if (!productSize.toString().chars().allMatch(Character::isDigit))
            throw new ProductsException("Only accept numbers");
        this.productSize = productSize;
    }

    //get product Description
    public String getProductDescription() throws ProductsException {
        if (productDescription == null) throw new ProductsException("Cannot get the product's description");
        return productDescription;
    }

    //set product Description
    public void setProductDescription(String productDescription) throws ProductsException {
        if (productDescription.chars().noneMatch(Character::isLetter))
            throw new ProductsException("Only alphabet letters are accepted");
        if (productDescription.equals("")) throw new ProductsException("The field cannot be empty");
        this.productDescription = productDescription;
    }
}