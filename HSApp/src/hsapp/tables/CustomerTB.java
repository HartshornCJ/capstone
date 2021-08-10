/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.tables;

import hsapp.DAO.Query;
import hsapp.Model.Address;
import hsapp.Model.Customer;
import hsapp.Model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author christina joy hartshorn
 */
public class CustomerTB {
    AddressTB addressTB = new AddressTB();
    
    public Customer updateCustomer(Customer customer)
    {
        Address address = addressTB.safeUpdateAddress(customer);
        customer.setAddressId(customer.getAddressId());
        customer.setCountryId(customer.getCountryId());
        customer.setCityId(customer.getCityId());
        //System.out.println(customer.getCustomerId()+ " "+ customer.getCustomerId());
        String updateStatement ="UPDATE customer SET customerName = ?, addressId = ?, lastUpdate = now(), lastUpdateBy = ? WHERE customerId = ?";
        try
        {
            Query.setPrepareStatement(updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
             ps.setString(1, customer.getCustomerName());
             ps.setInt(2, customer.getAddressId());
             ps.setString(3, User.getUser());
             ps.setInt(4, customer.getCustomerId());
             ps.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return customer;
    }
    public boolean deleteCustomer(Customer customer)
    {
        String deleteStatement = "DELETE FROM customer WHERE customerId = ?";
        try{
            Query.setPrepareStatement(deleteStatement);                
            PreparedStatement pss = Query.getPreparedStatement();
            pss.setInt(1, customer.getCustomerId());            
            pss.execute();  
            
            if(pss.getUpdateCount() > 0)
            {
                System.out.println(pss.getUpdateCount() + " row(s) affected");
                addressTB.safeDeleteAddress(customer);
                return true;
            }
            else
            {
                System.out.println("No Change");
                return false;
            } 
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
       
    }
    
    public boolean safedeleteCustomer(Customer customer)
    {
        String selectStatement = "SELECT * FROM appointment WHERE customerId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, customer.getCustomerId());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return deleteCustomer(customer);
            }
            else
            {
                return false;
            }
            
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
    
   public Customer instertCustomer(Customer customer)
    {
        if(customer.getCustomerId()!=0)
        {
            Address address = addressTB.safeInstertAddress(customer);
            customer.setAddressId(address.getAddressId());
            customer.setCountryId(customer.getCountryId());
            customer.setCityId(customer.getCityId());
        }
        String insertStatement = "Insert INTO customer(customerName, addressId, active, createDate, createdBy, lastUpdateBy) VALUES(?, ?, ?, now(),?,?)";
        //int result = 0; 
        int key = 0;
               
        try{
        Query.setPrepareStatementKey(insertStatement);
        PreparedStatement ps = Query.getPreparedStatement();
        
        String createrUser = User.getUser();
        
        ps.setString(1, customer.getCustomerName());
        ps.setInt(2, customer.getAddressId());
        ps.setInt(3, customer.getActive());
        ps.setString(4, createrUser);
        ps.setString(5, createrUser);
        
        ps.execute();      
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            key = rs.getInt(1);
        }
 
        //System.out.println("Inserted record's ID: " + key);
        customer.setCustomerId(key);
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return customer;
    }
    
    public Customer safeInstertCustomer(Customer customer)
    {
        Address address = addressTB.safeInstertAddress(customer);
        customer.setAddressId(address.getAddressId());
        customer.setCountryId(customer.getCountryId());
        customer.setCityId(customer.getCityId());
        String selectStatement =  "SELECT * FROM customer WHERE customerName = ? AND addressId = ?" ;
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setString(1, customer.getCustomerName());
            ps.setInt(2, customer.getAddressId());
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return instertCustomer(customer);
            }
            else
            {
                //customer.setCustomerId(rs.getInt("customerId"));
                customer.setCustomerId(rs.getInt("customerId"));
                return customer;
            }       
        }
        catch(SQLException e)
        {
            System.out.println(e);
            
        }
        return customer;
    }
}
 