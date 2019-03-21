package entity;

import util.FunctionLibrary;

//Author : Phan Tan Phat
public final class Customers {
    private Integer customerId;
    private String customerName;
    private String gender;
    private String emailAddress;
    private String phoneNumber;
    private String addressLine;
    private String townCity;
    private String stateCountyProvince;
    private String country;

    //Constructor to create customers
    public Customers(Integer customerId, String customerName, String Gender, String emailAddress, String phoneNumber, String addressLine, String townCity, String stateCountyProvince, String country) throws CustomersException {
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
    public Integer getCustomerId() throws CustomersException {
        if (customerId == null) throw new CustomersException("Cannot get the customer's ID");
        return customerId;
    }

    //Set customer ID
    public void setCustomerId(Integer customerId) throws CustomersException {
        if (!customerId.toString().chars().allMatch(Character::isDigit))
            throw new CustomersException("Only accept numbers");
        this.customerId = customerId;
    }

    //Get customer name
    public String getCustomerName() throws CustomersException {
        if (customerName == null) throw new CustomersException("Cannot get the customer's name");
        return customerName;
    }

    //Set customer name
    public void setCustomerName(String customerName) throws CustomersException {
        if (customerName.chars().noneMatch(Character::isLetter))
            throw new CustomersException("Only alphabet letters are accepted");
        if (customerName.equals("")) throw new CustomersException("The field cannot be empty");
        this.customerName = FunctionLibrary.convertToTitleCase(customerName);
    }

    //Get gender
    public String getGender() throws CustomersException {
        if (gender == null) throw new CustomersException("Cannot get the customer's gender");
        return gender;
    }

    //Set gender
    public void setGender(String Gender) throws CustomersException {
        if (Gender.equals("")) throw new CustomersException("The field cannot be empty");
        if (!Gender.equals("Female") & !Gender.equals("Male"))
            throw new CustomersException("Only Female or Male is accepted");
        this.gender = FunctionLibrary.convertToTitleCase(Gender);
    }

    //Get email address
    public String getEmailAddress() throws CustomersException {
        if (emailAddress == null) throw new CustomersException("Cannot get the customer's email address");
        return emailAddress;
    }

    //Set email address
    public void setEmailAddress(String emailAddress) throws CustomersException {
        if (emailAddress.equals("")) throw new CustomersException("The field cannot be empty");
        if (!emailAddress.matches("^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"))
            throw new CustomersException("Invalid email address");
        this.emailAddress = FunctionLibrary.convertToTitleCase(emailAddress);
    }

    //Get phone number
    public String getPhoneNumber() throws CustomersException {
        if (phoneNumber == null) throw new CustomersException("Cannot get the customer's phone number");
        return phoneNumber;
    }

    //Set phone number
    public void setPhoneNumber(String phoneNumber) throws CustomersException {
        if (phoneNumber.equals("")) throw new CustomersException("The field cannot be empty");
        if (phoneNumber.length() != 10 | phoneNumber.chars().anyMatch(Character::isLetter))
            throw new CustomersException("Phone number must be a 10 digits string");
        this.phoneNumber = phoneNumber;
    }

    //Get address line
    public String getAddressLine() throws CustomersException {
        if (addressLine == null) throw new CustomersException("Cannot get the customer's address line");
        return addressLine;
    }

    //Set address line
     public void setAddressLine(String addressLine) throws CustomersException {
        if (addressLine.equals("")) throw new CustomersException("The field cannot be empty");
        this.addressLine = addressLine;
    }

    //Get town city
    public String getTownCity() throws CustomersException {
        if (townCity == null) throw new CustomersException("Cannot get the customer's town city");
        return townCity;
    }

    //Set town city
    public void setTownCity(String townCity) throws CustomersException {
        if (townCity.equals("")) throw new CustomersException("The field cannot be empty");
        this.townCity = FunctionLibrary.convertToTitleCase(townCity);
    }

    //Get state, county and province
    public String getStateCountyProvince() throws CustomersException {
        if (stateCountyProvince == null)
            throw new CustomersException("Cannot get the customer's state, county or province");
        return stateCountyProvince;
    }

    //Set state, county and province
    public void setStateCountyProvince(String stateCountyProvince) throws CustomersException {
        if (stateCountyProvince.equals("")) throw new CustomersException("The field cannot be empty");
        this.stateCountyProvince = FunctionLibrary.convertToTitleCase(stateCountyProvince);
    }

    //Get country
    public String getCountry() throws CustomersException {
        if (country == null) throw new CustomersException("Cannot get the customer's country");
        return country;
    }

    //Set country
    public void setCountry(String country) throws CustomersException {
        if (country.equals("")) throw new CustomersException("The field cannot be empty");
        this.country = FunctionLibrary.convertToTitleCase(country);
    }
    @Override
    public String toString(){
        try {
            return getCustomerName();
        } catch (CustomersException e) {
            e.printStackTrace();
            return "not found";
        }
    }

}
