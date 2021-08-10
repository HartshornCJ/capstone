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
public class Customer extends Address{
    protected int customerId;
    protected String customerName;
    protected int addressId;
    protected int active;

    public Customer(int countryId, String countryName, int cityId, String cityName, int addressId, String address, String address2, String postalCode, String phone, int customerId, String customerName, int active) {
        super(countryId, countryName, cityId, cityName, addressId, address, address2, postalCode, phone);
        this.customerId = customerId;
        this.customerName = customerName;
        this.active = active;
    }
    

    
    public int getCustomerId()
    {
        return customerId;
    }
    
    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }
    
    public String getCustomerName()
    {
        return customerName;
    }
    
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }
    
    /*public int getAddressId()
    {
        return addressId;
    }
    
    public void setAddressId(int addressId)
    {
        this.addressId = addressId;
    }
    */
    public int getActive()
    {
        return active;
    }
    
    public void setActive(int active)
    {
        this.active = active;
    }
}
