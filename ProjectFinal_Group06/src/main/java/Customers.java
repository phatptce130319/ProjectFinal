//Author : Phan Tan Phat
public class Customers {
    private String customerId;
    private String customerName;
    private String gender;
    private String emailAddress;
    private String phoneNumber;
    private String addressLine;
    private String townCity;
    private String stateCountyProvince;
    private String country;

    //Constructor to create customers
    public Customers(String customerId, String customerName, String Gender, String emailAddress, String phoneNumber, String addressLine, String townCity, String stateCountyProvince, String country) throws  CustomersException{
        setCustomerId(customerId);
        setCustomerName(customerName);
        setGender(Gender);
        setEmailAddress(emailAddress);
        setPhoneNumber(phoneNumber);
        setAddressLine(addressLine);
        setTownCity(townCity);
        setStateCountyProvince(stateCountyProvince);
        setCountry(country);
    }

    //Get customer ID
    public String getCustomerId() throws CustomersException {
        return customerId;
    }

    //Set customer ID
    public void setCustomerId(String customerId)throws CustomersException {
        this.customerId = customerId;
    }

    //Get customer name
    public String getCustomerName() throws CustomersException {
        return customerName;
    }

    //Set customer name
    public void setCustomerName(String customerName) throws CustomersException {
        this.customerName = customerName;
    }

    //Get gender
    public String getGender() throws CustomersException {
        return gender;
    }

    //Set gender
    public void setGender(String Gender) throws CustomersException {
        this.gender = Gender;
    }

    //Get email address
    public String getEmailAddress() throws CustomersException {
        return emailAddress;
    }

    //Set email address
    public void setEmailAddress(String emailAddress) throws CustomersException {
        this.emailAddress = emailAddress;
    }

    //Get phone number
    public String getPhoneNumber() throws CustomersException {
        return phoneNumber;
    }

    //Set phone number
    public void setPhoneNumber(String phoneNumber) throws CustomersException {
        this.phoneNumber = phoneNumber;
    }

    //Get address line
    public String getAddressLine() throws CustomersException {
        return addressLine;
    }

    //Set address line
    public void setAddressLine(String addressLine) throws CustomersException {
        this.addressLine = addressLine;
    }

    //Get town city
    public String getTownCity() throws CustomersException {
        return townCity;
    }

    //Set town city
    public void setTownCity(String townCity) throws CustomersException {
        this.townCity = townCity;
    }

    //Get state, county and province
    public String getStateCountyProvince() throws CustomersException {
        return stateCountyProvince;
    }

    //Set state, county and province
    public void setStateCountyProvince(String stateCountyProvince) throws CustomersException {
        this.stateCountyProvince = stateCountyProvince;
    }

    //Get country
    public String getCountry() throws CustomersException {
        return country;
    }

    //Set country
    public void setCountry(String country) throws CustomersException {
        this.country = country;
    }

}
