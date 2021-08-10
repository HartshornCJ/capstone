/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.tables;

import hsapp.DAO.Query;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hsapp.Model.Contact;
import hsapp.Model.Customer;

/**
 *
 * @author Elicea
 */
public class ContactTB{
    CustomerTB customerTB = new CustomerTB();
    
    public Contact updateContact(Contact contact)
    {
        Customer customer = customerTB.updateCustomer(contact);
        contact.setCompany(contact.getCompany());
        contact.setIsPrimary(contact.getIsPrimary());
        
        String updateStatement ="UPDATE contact SET company = ?, isPrimary = ? WHERE customerId = ?";
        try
        {
            Query.setPrepareStatement(updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
             ps.setString(1, contact.getCompany());
             ps.setInt(2, contact.getIsPrimary());
             ps.setInt(3, contact.getCustomerId());
             ps.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
        return contact;
    }
    
    public boolean deleteContact(Contact contact)
    {
        String deleteStatement = "DELETE FROM contact WHERE customerId = ?";
        try{
            Query.setPrepareStatement(deleteStatement);                
            PreparedStatement pss = Query.getPreparedStatement();
            pss.setInt(1, contact.getCustomerId());            
            pss.execute();  
            
            if(pss.getUpdateCount() > 0)
            {
                System.out.println(pss.getUpdateCount() + " row(s) affected");
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
    
    
    public boolean deleteContact(int customerId)
    {
        String deleteStatement = "DELETE FROM contact WHERE customerId = ?";
        try{
            Query.setPrepareStatement(deleteStatement);                
            PreparedStatement pss = Query.getPreparedStatement();
            pss.setInt(1, customerId);            
            pss.execute();  
            
            if(pss.getUpdateCount() > 0)
            {
                System.out.println(pss.getUpdateCount() + " row(s) affected");
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
    
    public boolean safedeleteCustomer(Contact contact)
    {
        String selectStatement = "SELECT * FROM customer WHERE customerId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, contact.getCustomerId());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return deleteContact(contact);
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
    
    
    public Contact instertContact(Contact contact)
    {
        System.out.println("instertCustomer  1");
        /*if(contact.getCustomerId()!=0)
        {
            Customer customer = customerTB.safeInstertCustomer(contact);
            contact.setCustomerId(customer.getCustomerId());
            contact.setType(contact.getType());
            contact.setContactMethod(contact.getContactMethod());
        }*/
        String insertStatement = "Insert INTO contact(customerID, company, isPrimary) VALUES(?, ?, ?)";
        //int result = 0; 
        int key = 0;
               
        try{
        Query.setPrepareStatementKey(insertStatement);
        PreparedStatement ps = Query.getPreparedStatement();
        
        String createrUser = User.getUser();
        
        ps.setInt(1, contact.getCustomerId());
        ps.setString(2, contact.getCompany());
        ps.setInt(3, contact.getIsPrimary());
        
        ps.execute();      
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            key = rs.getInt(1);
        }
 
        //System.out.println("Inserted record's ID: " + key);
        //contact.setCustomerId(key);
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return contact;
    }
    
    public Contact safeInstertContact(Contact contact)
    {
        Customer customer = customerTB.safeInstertCustomer(contact);
        contact.setCustomerId(customer.getCustomerId());
        contact.setCompany(contact.getCompany());
        contact.setIsPrimary(contact.getIsPrimary());
        String selectStatement =  "SELECT * FROM contact WHERE customerID = ?" ;
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, customer.getCustomerId());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return instertContact(contact);
            }
            else
            {
                //customer.setCustomerId(rs.getInt("customerId"));
                customer.setCustomerId(rs.getInt("customerId"));
                return contact;
            }       
        }
        catch(SQLException e)
        {
            System.out.println(e);
            
        }
        return contact;
    }
       
}
