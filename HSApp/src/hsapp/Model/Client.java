/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.Model;

/**
 *
 * @author Elicea
 */
public class Client extends Customer{
    
    protected String type; 
    protected String contactMethod;
    
    public Client(int countryId, String countryName, int cityId, String cityName, int addressId, String address, String address2, String postalCode, String phone, int customerId, String customerName, int active, String type, String contactMethod) {
        super(countryId, countryName, cityId, cityName, addressId, address, address2, postalCode, phone, customerId, customerName, active);
        this.contactMethod = contactMethod;
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }
}
