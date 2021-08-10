/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.tables;

import com.mysql.jdbc.Statement;
import hsapp.DAO.Query;
import hsapp.Model.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author christina joy hartshorn
 */
public class CountryTB {
    public Country updateCountry(Country country)
    {
        String updateStatement ="UPDATE country SET country = ?, lastUpdate = now(), lastUpdateBy = ? WHERE countryId = ?";
        try
        {
            Query.setPrepareStatement(updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
             ps.setString(1, country.getCountryName());
             ps.setString(2, User.getUser());
             ps.setInt(3, country.getCountryId());
             ps.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return country;
    }
    public Country safeUpdateCountry(Country country)
    {
        String selectStatement = "SELECT * FROM city WHERE countryId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, country.getCountryId());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            rs.next();
            if(!rs.next())
            {
                return updateCountry(country);
            }
            else
            {
                return safeInstertCountry(country);
            }
            
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return country;
        }
    }
    public boolean deleteCountry(Country country)
    {
        String deleteStatement = "DELETE FROM country WHERE countryId  = ?";
        try{
            Query.setPrepareStatement(deleteStatement);                
            PreparedStatement pss = Query.getPreparedStatement();
            pss.setInt(1, country.getCountryId());            
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
    public boolean safeDeleteCountry(Country country)
    {
        String selectStatement = "SELECT * FROM city WHERE countryId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, country.getCountryId());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return deleteCountry(country);
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
    
    public Country instertCountry(Country country)
    {
         String insertStatement = "Insert INTO country(country, createDate, createdBy, lastUpdateBy) VALUES(?, now(),?,?)";
        //int result = 0; 
        int key = 0;
               
        try{
        Query.setPrepareStatementKey(insertStatement);
        PreparedStatement ps = Query.getPreparedStatement();
        
        String createrUser = User.getUser();
        
        ps.setString(1, country.getCountryName());
        ps.setString(2, createrUser);
        ps.setString(3, createrUser);
        
        ps.execute();      
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            key = rs.getInt(1);
        }
 
        //System.out.println("Inserted record's ID: " + key);
        country.setCountryId(key);
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return country;
    }
    
     public Country safeInstertCountry(Country country)
    {
        String selectStatement = "SELECT * FROM country WHERE country = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setString(1, country.getCountryName());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return instertCountry(country);
            }
            else
            {
                return new Country(rs.getInt("countryId"), rs.getString("country"));
            }       
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return country;
        }
    }
}
