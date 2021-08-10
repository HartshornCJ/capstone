/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.Model;

/**
 *
 * @author christina joy hartshorn
 */
public class Address extends City{
    
    protected int addressId;
    protected String address;
    protected String address2;
    protected String postalCode;
    protected String phone;
    
    public Address(int countryId, String countryName, int cityId, String cityName, int addressId, String address, String address2, String postalCode, String phone) {
        super(countryId, countryName, cityId, cityName);
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    
    public int getAddressId()
    {
        return addressId;
    }
    
    public void setAddressId(int addressId)
    {
        this.addressId = addressId;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getAddress2()
    {
        return address2;
    }
    
    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }
    
    public String getPostalCode()
    {
        return postalCode;
    }
    
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
