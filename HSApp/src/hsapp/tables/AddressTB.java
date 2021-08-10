/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.tables;

import hsapp.DAO.Query;
import hsapp.Model.Address;
import hsapp.Model.City;
import hsapp.Model.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author christina joy hartshorn
 */
public class AddressTB {
    CityTB cityTB = new CityTB();
    
    public Address updateAddress(Address address)
    {
        String updateStatement ="UPDATE address SET address = ?, address2 = ?, cityId = ?, postalCode = ?, phone = ?, lastUpdate = now(), lastUpdateBy = ? WHERE addressId = ?";
        try
        {
            Query.setPrepareStatement(updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
             ps.setString(1, address.getAddress());
             ps.setString(2, address.getAddress2());
             ps.setInt(3, address.getCityId());
             ps.setString(4, address.getPostalCode());
             ps.setString(5, address.getPhone());
             ps.setString(6, User.getUser());
             ps.setInt(7, address.getAddressId());
             ps.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return address;
    }
    
    public Address safeUpdateAddress(Address address)
    {
        City city = cityTB.safeUpdateCity(address);
        address.setCityId(city.getCityId());
        address.setCountryId(city.getCountryId());

        String selectStatement =  "select * from customer where addressId = ?";

        //int result = 0;
        try{

            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, address.getAddressId());
            
            ps.execute();

            ResultSet rs = ps.getResultSet();

            rs.next();
            if(!rs.next())           
            {
                return updateAddress(address);
                //return instertAddress(address);
            }
            else
            {
                return safeInstertAddress(address);
            }       
        }
        catch(SQLException e)
        {
            System.out.println(e);
            
        }
        return address;
    }
    
    public boolean deleteAddress(Address address)
    {
        String deleteStatement = "DELETE FROM address WHERE addressId = ?";
        try{
            Query.setPrepareStatement(deleteStatement);                
            PreparedStatement pss = Query.getPreparedStatement();
            pss.setInt(1, address.getAddressId());            
            pss.execute();  
            
            if(pss.getUpdateCount() > 0)
            {
                System.out.println(pss.getUpdateCount() + " row(s) affected");
                cityTB.safeDeleteCity(address);
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
    public boolean safeDeleteAddress(Address address)
    {
        String selectStatement = "SELECT * FROM customer WHERE addressId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, address.getAddressId());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return deleteAddress(address);
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
    
   public Address instertAddress(Address address)
    {
        if(address.getCityId()!=0)
        {
            City city = cityTB.safeInstertCity(address);
            address.setCityId(city.getCityId());
            address.setCountryId(city.getCountryId());
        }
        String insertStatement = "Insert INTO address(address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdateBy) VALUES(?, ?, ?, ?, ?, now(),?,?)";
        //int result = 0; 
        int key = 0;
               
        try{
        Query.setPrepareStatementKey(insertStatement);
        PreparedStatement ps = Query.getPreparedStatement();
        
        String createrUser = User.getUser();
        System.out.println(address.getPhone());
        ps.setString(1, address.getAddress());
        ps.setString(2, address.getAddress2());
        ps.setInt(3, address.getCityId());
        ps.setString(4, address.getPostalCode());
        ps.setString(5, address.getPhone());
        ps.setString(6, createrUser);
        ps.setString(7, createrUser);
        
        ps.execute();      
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            key = rs.getInt(1);
        }
 
        //System.out.println("Inserted record's ID: " + key);
        address.setAddressId(key);
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return address;
    }
    
    public Address safeInstertAddress(Address address)
    {
        City city = cityTB.safeInstertCity(address);
        address.setCityId(city.getCityId());
        address.setCountryId(city.getCountryId());
        String selectStatement =  "SELECT * FROM address WHERE address = ? AND address2 = ? AND cityId = ? AND postalCode = ? AND phone = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setString(1, address.getAddress());
            ps.setString(2, address.getAddress2());
            ps.setInt(3, address.getCityId());
            ps.setString(4, address.getPostalCode());
            ps.setString(5, address.getPhone());
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return instertAddress(address);
            }
            else
            {
                //city.setCityId(rs.getInt("cityId"));
                address.setAddressId(rs.getInt("addressId"));
                return address;
            }       
        }
        catch(SQLException e)
        {
            System.out.println(e);
            
        }
        return address;
    }
}
