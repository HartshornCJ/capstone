/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.tables;

import hsapp.DAO.Query;
import hsapp.Model.City;
import hsapp.Model.Country;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author christina joy hartshorn
 */



public class CityTB {
    CountryTB countryTB = new CountryTB();
    //protected Country country;
    
    public City updateCity(City city)
    {
        String updateStatement ="UPDATE city SET city = ?, countryId = ?, lastUpdate = now(), lastUpdateBy = ? WHERE cityId = ?";
        try
        {
            Query.setPrepareStatement(updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
             ps.setString(1, city.getCityName());
             ps.setInt(2, city.getCountryId());
             ps.setString(3, User.getUser());
             ps.setInt(4, city.getCityId());
             ps.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return city;
    }
    public City safeUpdateCity(City city)
    {
        Country country = countryTB.safeInstertCountry(city);
        city.setCountryId(country.getCountryId());
        String selectStatement = "SELECT * FROM address WHERE cityId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, city.getCityId());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            rs.next();
            if(!rs.next())
            {
                return updateCity(city);
            }
            else
            {
                return safeInstertCity(city);
            }
            
            
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return city;
        }
    }
    public boolean deleteCity(City city)
    {
        String deleteStatement = "DELETE FROM city WHERE cityId  = ?";
        try{
            Query.setPrepareStatement(deleteStatement);                
            PreparedStatement pss = Query.getPreparedStatement();
            pss.setInt(1, city.getCityId());            
            pss.execute();  
            
            if(pss.getUpdateCount() > 0)
            {
                System.out.println(pss.getUpdateCount() + " row(s) affected");
                countryTB.safeDeleteCountry(city);
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
    public boolean safeDeleteCity(City city)
    {
        String selectStatement = "SELECT * FROM address WHERE cityId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, city.getCityId());           
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return deleteCity(city);
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
    
   public City instertCity(City city)
    {
        if(city.getCountryId()!=0)
        {
            Country country = countryTB.safeInstertCountry(city);
            city.setCountryId(country.getCountryId());
        }
        String insertStatement = "Insert INTO city(city, countryId, createDate, createdBy, lastUpdateBy) VALUES(?, ?, now(),?,?)";
        //int result = 0; 
        int key = 0;
               
        try{
        Query.setPrepareStatementKey(insertStatement);
        PreparedStatement ps = Query.getPreparedStatement();
        
        String createrUser = User.getUser();
        
        ps.setString(1, city.getCityName());
        ps.setInt(2, city.getCountryId());
        ps.setString(3, createrUser);
        ps.setString(4, createrUser);
        
        ps.execute();      
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            key = rs.getInt(1);
        }
 
        //System.out.println("Inserted record's ID: " + key);
        city.setCityId(key);
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return city;
    }
    
    public City safeInstertCity(City city)
    {
        Country country = countryTB.safeInstertCountry(city);
        city.setCountryId(country.getCountryId());
        String selectStatement = "SELECT * FROM city WHERE city = ? AND countryId = ?";
        //int result = 0;
        try{
            Query.setPrepareStatement(selectStatement);     
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setString(1, city.getCityName());
            ps.setInt(2, country.getCountryId());
            ps.execute();
            
            ResultSet rs = ps.getResultSet();
            if(!rs.next())
            {
                return instertCity(city);
            }
            else
            {
                city.setCityId(rs.getInt("cityId"));
                return city;
            }       
        }
        catch(SQLException e)
        {
            System.out.println(e);
            
        }
        return city;
    }
}
