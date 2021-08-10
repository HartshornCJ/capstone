/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.Model;

import hsapp.DAO.Query;
import hsapp.tables.ClientTB;
import hsapp.tables.ContactTB;
import hsapp.tables.CustomerTB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author christina joy hartshorn
 */
public class Contacts {
    protected ObservableList<Customer> data;
    //protected ArrayList<Part> allParts;
    ResultSet rs;
    CustomerTB customerTB = new CustomerTB();
    ClientTB clientTB = new ClientTB();
    ContactTB contactTB = new ContactTB();
    
    /*public Contacts()
    {
        String selectStatement = "select customer.customerId, customer.customerName, customer.active, " +
            "address.addressId, address.address, address.address2, address.postalCode, address.phone, " +
            "city.cityId, city.city, country.countryId, country.country " +
            "From customer, address, city, country " +
            "Where customer.addressId = address.addressId AND address.cityId = city.cityId " +
            "AND city.countryId = country.countryId;";

        try{
            Query.setPrepareStatement(selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            data = FXCollections.observableArrayList();
            while (rs.next()){
                //public Customer(int countryId, String countryName, int cityId, String cityName, int addressId, String address, String address2, String postalCode, String phone, int customerId, String customerName, int active)
            data.add(new Customer(rs.getInt("countryId"), rs.getString("country"), rs.getInt("cityId"), rs.getString("city"), rs.getInt("addressId"), rs.getString("address"),rs.getString("address2"), rs.getString("postalCode"), rs.getString("phone"), rs.getInt("customerId"), rs.getString("customerName"), rs.getInt("active")));
        }

        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Contacts");
        } 


    }*/
    
    public Contacts()
    {
        String selectStatement = "select customer.customerId, customer.customerName, customer.active, " +
            "address.addressId, address.address, address.address2, address.postalCode, address.phone, " +
            "city.cityId, city.city, country.countryId, country.country, client.clientType, client.contactMethod " +
            "From customer, address, city, country, client " +
            "Where customer.addressId = address.addressId AND address.cityId = city.cityId " +
            "AND city.countryId = country.countryId "+
            "AND customer.customerId = client.customerID;";
        data = FXCollections.observableArrayList();
        
        try{
            Query.setPrepareStatement(selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()){
                //public Customer(int countryId, String countryName, int cityId, String cityName, int addressId, String address, String address2, String postalCode, String phone, int customerId, String customerName, int active)
            data.add(new Client(rs.getInt("countryId"), rs.getString("country"), rs.getInt("cityId"), rs.getString("city"), rs.getInt("addressId"), 
                    rs.getString("address"),rs.getString("address2"), rs.getString("postalCode"), rs.getString("phone"), rs.getInt("customerId"), rs.getString("customerName"), 
                    rs.getInt("active"), rs.getString("clientType"), rs.getString("contactMethod")));
        }
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Contacts");
        }
        
        selectStatement = "select customer.customerId, customer.customerName, customer.active, " +
            "address.addressId, address.address, address.address2, address.postalCode, address.phone, " +
            "city.cityId, city.city, country.countryId, country.country, contact.company, contact.isPrimary"
                + " " +
            "From customer, address, city, country, contact " +
            "Where customer.addressId = address.addressId AND address.cityId = city.cityId " +
            "AND city.countryId = country.countryId "+
            "AND customer.customerId = contact.customerID;";

        try{
            Query.setPrepareStatement(selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()){
                //public Customer(int countryId, String countryName, int cityId, String cityName, int addressId, String address, String address2, String postalCode, String phone, int customerId, String customerName, int active)
            data.add(new Contact(rs.getInt("countryId"), rs.getString("country"), rs.getInt("cityId"), rs.getString("city"), rs.getInt("addressId"), 
                    rs.getString("address"),rs.getString("address2"), rs.getString("postalCode"), rs.getString("phone"), rs.getInt("customerId"), rs.getString("customerName"), 
                    rs.getInt("active"), rs.getString("company"), rs.getInt("isPrimary")));
        }
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Contacts");
        } 
        
        selectStatement = "select distinct customer.customerId, customer.customerName, customer.active, " +
            "address.addressId, address.address, address.address2, address.postalCode, address.phone, " +
            "city.cityId, city.city, country.countryId, country.country"
                + " " +
            "From customer, address, city, country, contact, client " +
            "Where customer.addressId = address.addressId AND address.cityId = city.cityId " +
            "AND city.countryId = country.countryId "+
            "AND customer.customerId NOT IN (select customerID from contact) AND customer.customerId NOT IN (select customerID from client);";
        
        try{
            Query.setPrepareStatement(selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()){
                //public Customer(int countryId, String countryName, int cityId, String cityName, int addressId, String address, String address2, String postalCode, String phone, int customerId, String customerName, int active)
            data.add(new Customer(rs.getInt("countryId"), rs.getString("country"), rs.getInt("cityId"), rs.getString("city"), rs.getInt("addressId"), 
                    rs.getString("address"),rs.getString("address2"), rs.getString("postalCode"), rs.getString("phone"), rs.getInt("customerId"), rs.getString("customerName"), 
                    rs.getInt("active")));
        }
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Contacts");
        }
    }
    
    
    public void addCustomer(Customer customer) {
        customer = customerTB.safeInstertCustomer(customer);
        data.add(customer);
    }
    
    public void addClient(Client client) {
        
        
        Customer customer = clientTB.safeInstertClient(client);
        System.out.println("addClient 2");
        data.add(customer);
        System.out.println("addClient 3");
    }
    
    public void addContact(Contact contact) {
        Customer customer = contactTB.safeInstertContact(contact);
        data.add(customer);
    }
    
    public boolean removeCustomer(Customer customer) {
        //return true;
        contactTB.deleteContact(customer.getCustomerId());
        clientTB.deleteClient(customer.getCustomerId());
        boolean temp = customerTB.safedeleteCustomer(customer);
        if(temp){
            return data.removeIf(n -> (n.getCustomerId() == customer.getCustomerId()));
        }
        return temp;
    }
    
    public Customer lookupCustomer(int customerId) {
        //return products.get(productID);
        
        for(Customer customer : data) {
            if(customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }
    
    public void updateCustomer(int customerId, Customer updatedCustomer) {
        
        //System.out.println(updatedCustomer.getAddressId() + ", "+ updatedCustomer.getCityId() + ", "+ updatedCustomer.getCountryId());
        updatedCustomer = customerTB.updateCustomer(updatedCustomer);
        //updatedCustomer.setCustomerId(customerId);
        //updatedProduct.correctCount();
        //addCustomer(updatedCustomer);
        data.removeIf(n -> (n.getCustomerId() == customerId));
        data.add(updatedCustomer);
        
    }
    
    public Customer optionSwithc(int customerId, Customer updatedCustomer) {
        if(updatedCustomer instanceof Client){
            contactTB.deleteContact(customerId);
            clientTB.instertClient((Client) updatedCustomer);
        }
        else if(updatedCustomer instanceof Contact){
            clientTB.deleteClient(customerId);
            contactTB.instertContact((Contact) updatedCustomer);
        }
        return updatedCustomer;
    }
    
    public ObservableList<Customer> ObsListCustomer() {
        return data;
    }
    
    
    public Client lookupClient(int customerId)
    {
        
        String selectStatement = "select customer.customerId, customer.customerName, customer.active, " +
            "address.addressId, address.address, address.address2, address.postalCode, address.phone, " +
            "city.cityId, city.city, country.countryId, country.country, client.clientType, client.contactMethod " +
            "From customer, address, city, country, client " +
            "Where customer.addressId = address.addressId AND address.cityId = city.cityId " +
            "AND city.countryId = country.countryId "+
            "AND customer.customerId = client.customerID AND client.customerID = ?;";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, customerId);           
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if(rs.next()){
                return new Client(rs.getInt("countryId"), rs.getString("country"), rs.getInt("cityId"), rs.getString("city"), rs.getInt("addressId"),
                                rs.getString("address"),rs.getString("address2"), rs.getString("postalCode"), rs.getString("phone"), rs.getInt("customerId"), 
                                rs.getString("customerName"), rs.getInt("active"), rs.getString("clientType"), rs.getString("contactMethod"));
            }
            /*return new Client(rs.getInt("countryId"), rs.getString("country"), rs.getInt("cityId"), rs.getString("city"), rs.getInt("addressId"),
                                rs.getString("address"),rs.getString("address2"), rs.getString("postalCode"), rs.getString("phone"), rs.getInt("customerId"), 
                                rs.getString("customerName"), rs.getInt("active"), rs.getString("type"), rs.getString("contactMethod"));*/
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Contacts");
        } 
        
        return null;
    }
    
    
    public Contact lookupContact(int customerId)
    {
        
        String selectStatement = "select customer.customerId, customer.customerName, customer.active, " +
            "address.addressId, address.address, address.address2, address.postalCode, address.phone, " +
            "city.cityId, city.city, country.countryId, country.country, contact.company, contact.isPrimary"
                + " " +
            "From customer, address, city, country, contact " +
            "Where customer.addressId = address.addressId AND address.cityId = city.cityId " +
            "AND city.countryId = country.countryId "+
            "AND customer.customerId = contact.customerID AND contact.customerID = ?;";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, customerId);           
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if(rs.next()){
                return new Contact(rs.getInt("countryId"), rs.getString("country"), rs.getInt("cityId"), rs.getString("city"), rs.getInt("addressId"),
                                    rs.getString("address"),rs.getString("address2"), rs.getString("postalCode"), rs.getString("phone"), rs.getInt("customerId"), 
                                    rs.getString("customerName"), rs.getInt("active"), rs.getString("company"), rs.getInt("isPrimary"));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Contacts");
        } 
        
        return null;
    }
    
}
