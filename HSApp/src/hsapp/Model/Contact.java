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
public class Contact extends Customer{
    
    protected String company; 
    protected int isPrimary;
    
    public Contact(int countryId, String countryName, int cityId, String cityName, int addressId, String address, String address2, String postalCode, String phone, int customerId, String customerName, int active, String company, int isPrimary) {
        super(countryId, countryName, cityId, cityName, addressId, address, address2, postalCode, phone, customerId, customerName, active);
        this.company = company;
        this.isPrimary = isPrimary;
    }
    
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
    public int getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(int isPrimary) {
        this.isPrimary = isPrimary;
    }
    
    
}
