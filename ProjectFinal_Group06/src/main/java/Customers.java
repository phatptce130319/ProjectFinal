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
    
    public String getCustomerId() throws CustomersException {
        return customerId;
    }

    public void setCustomerId(String customerId)throws CustomersException {
        this.customerId = customerId;
    }

    public String getCustomerName() throws CustomersException {
        return customerName;
    }

    public void setCustomerName(String customerName) throws CustomersException {
        this.customerName = customerName;
    }

    public String getGender() throws CustomersException {
        return gender;
    }

    public void setGender(String Gender) throws CustomersException {
        this.gender = Gender;
    }

    public String getEmailAddress() throws CustomersException {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) throws CustomersException {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() throws CustomersException {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws CustomersException {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressLine() throws CustomersException {
        return addressLine;
    }

    public void setAddressLine(String addressLine) throws CustomersException {
        this.addressLine = addressLine;
    }

    public String getTownCity() throws CustomersException {
        return townCity;
    }

    public void setTownCity(String townCity) throws CustomersException {
        this.townCity = townCity;
    }

    public String getStateCountyProvince() throws CustomersException {
        return stateCountyProvince;
    }

    public void setStateCountyProvince(String stateCountyProvince) throws CustomersException {
        this.stateCountyProvince = stateCountyProvince;
    }

    public String getCountry() throws CustomersException {
        return country;
    }

    public void setCountry(String country) throws CustomersException {
        this.country = country;
    }

}
