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
import hsapp.Model.Client;
import hsapp.Model.Customer;

/**
 *
 * @author Elicea
 */
public class ClientTB{
    CustomerTB customerTB = new CustomerTB();
    
    public Client updateClient(Client client)
    {
        Customer customer = customerTB.updateCustomer(client);
        client.setType(client.getType());
        client.setContactMethod(client.getContactMethod());
        
        String updateStatement ="UPDATE client SET clientType = ?, contactMethod = ? WHERE customerId = ?";
        try
        {
            Query.setPrepareStatement(updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
             ps.setString(1, client.getType());
             ps.setString(2, client.getContactMethod());
             ps.setInt(3, client.getCustomerId());
             ps.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
        return client;
    }
    
    public boolean deleteClient(Client client)
    {
        String deleteStatement = "DELETE FROM client WHERE customerId = ?";
        try{
            Query.setPrepareStatement(deleteStatement);                
            PreparedStatement pss = Query.getPreparedStatement();
            pss.setInt(1, client.getCustomerId());            
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
    
    
    public boolean deleteClient(int customerId)
    {
        String deleteStatement = "DELETE FROM client WHERE customerId = ?";
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
    
    public boolean safedeleteCustomer(Client client)
    {
        String selectStatement = "SELECT * FROM customer WHERE customerId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, client.getCustomerId());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return deleteClient(client);
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
    
    
    public Client instertClient(Client client)
    {
        System.out.println("instertCustomer  1");
        /*if(client.getCustomerId()!=0)
        {
            Customer customer = customerTB.safeInstertCustomer(client);
            client.setCustomerId(customer.getCustomerId());
            client.setType(client.getType());
            client.setContactMethod(client.getContactMethod());
        }*/
        String insertStatement = "Insert INTO client(customerID, clientType, contactMethod) VALUES(?, ?, ?)";
        //int result = 0; 
        int key = 0;
               
        try{
        Query.setPrepareStatementKey(insertStatement);
        PreparedStatement ps = Query.getPreparedStatement();
        
        String createrUser = User.getUser();
        
        ps.setInt(1, client.getCustomerId());
        ps.setString(2, client.getType());
        ps.setString(3, client.getContactMethod());
        
        ps.execute();      
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            key = rs.getInt(1);
        }
 
        //System.out.println("Inserted record's ID: " + key);
        //client.setCustomerId(key);
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return client;
    }
    
    public Client safeInstertClient(Client client)
    {
        System.out.println("safeInstertClient  b  1");
        Customer customer = customerTB.safeInstertCustomer(client);
        client.setCustomerId(customer.getCustomerId());
        client.setType(client.getType());
        client.setContactMethod(client.getContactMethod());
        String selectStatement =  "SELECT * FROM client WHERE customerID = ?" ;
        //int result = 0;
        try{
            System.out.println("safeInstertClient  2");
            Query.setPrepareStatement(selectStatement);     
            System.out.println("safeInstertClient  3");
            PreparedStatement ps = Query.getPreparedStatement();
            System.out.println("safeInstertClient  4");
            ps.setInt(1, customer.getCustomerId());
            System.out.println("safeInstertClient  5");
            System.out.println("safeInstertClient  6");
            ps.execute();
            System.out.println("safeInstertClient  7");
            ResultSet rs = ps.getResultSet();
            System.out.println("safeInstertClient  8");
            if(!rs.next())
            {
                System.out.println("safeInstertClient  9");
                return instertClient(client);
            }
            else
            {
                System.out.println("safeInstertClient  10");
                //customer.setCustomerId(rs.getInt("customerId"));
                customer.setCustomerId(rs.getInt("customerId"));
                return client;
            }       
        }
        catch(SQLException e)
        {
            System.out.println(e);
            
        }
        return client;
    }
       
}
