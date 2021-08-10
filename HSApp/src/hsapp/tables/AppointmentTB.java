/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.tables;

import hsapp.DAO.Query;
import hsapp.Model.Customer;
import hsapp.Model.Appointment;
import hsapp.utils.ConvertTime;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javafx.util.Pair;

/**
 *
 * @author christina joy hartshorn
 */
public class AppointmentTB {
    CustomerTB customerTB = new CustomerTB();
    
    public Appointment updateAppointment(Appointment appointment)
    {
        String updateStatement ="UPDATE appointment SET customerId = ?, userId = ?, title = ?, description = ?, location = ?, contact = ?, type = ?, url = ?, start = ?, end = ?, lastUpdate = now(), lastUpdateBy = ? WHERE appointmentId = ?";
        try
        {
            Query.setPrepareStatement(updateStatement);
            PreparedStatement ps = Query.getPreparedStatement();
             ps.setInt(1, appointment.getCustomerId());
             ps.setInt(2, User.getUserId());
             //ps.setString(3, appointment.getCustomerName());
             ps.setString(3, appointment.getTitle());
             ps.setString(4, appointment.getDescription());
             ps.setString(5, appointment.getLocation());
             ps.setString(6, appointment.getContact());
             ps.setString(7, appointment.getType());
             ps.setString(8, appointment.getUrl());
             
             Pair<Date, Time> s = ConvertTime.covertToSever(appointment.getStartDate(), appointment.getStartTime());
             Pair<Date, Time> e = ConvertTime.covertToSever(appointment.getEndDate(), appointment.getEndTime());
             ps.setString(9, s.getKey()+ " "+s.getValue());
             ps.setString(10, e.getKey()+ " "+e.getValue());
             
             
             ps.setString(11, User.getUser());
             ps.setInt(12, appointment.getAppointmentId());
             ps.execute();
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return appointment;
    }
    public boolean deleteAppointment(Appointment appointment)
    {
        String deleteStatement = "DELETE FROM appointment WHERE appointmentId = ?";
        try{
            Query.setPrepareStatement(deleteStatement);                
            PreparedStatement pss = Query.getPreparedStatement();
            pss.setInt(1, appointment.getAppointmentId());            
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
    
    
   public Appointment instertAppointment(Appointment appointment)
    {
        String insertStatement = "Insert INTO appointment(customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdateBy) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  now(),?,?)";
        //int result = 0; 
        int key = 0;
               
        try{
        Query.setPrepareStatementKey(insertStatement);
        PreparedStatement ps = Query.getPreparedStatement();
        
        String createrUser = User.getUser();
        
             ps.setInt(1, appointment.getCustomerId());
             ps.setInt(2, User.getUserId());
             //ps.setString(3, appointment.getCustomerName());
             ps.setString(3, appointment.getTitle());
             ps.setString(4, appointment.getDescription());
             ps.setString(5, appointment.getLocation());
             ps.setString(6, appointment.getContact());
             ps.setString(7, appointment.getType());
             ps.setString(8, appointment.getUrl());
             
             Pair<Date, Time> s = ConvertTime.covertToSever(appointment.getStartDate(), appointment.getStartTime());
             Pair<Date, Time> e = ConvertTime.covertToSever(appointment.getEndDate(), appointment.getEndTime());
             ps.setString(9, s.getKey()+ " "+s.getValue());
             ps.setString(10, e.getKey()+ " "+e.getValue());

             ps.setString(11, User.getUser());
             ps.setString(12, User.getUser());
        
        ps.execute();      
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            key = rs.getInt(1);
        }
 
        //System.out.println("Inserted record's ID: " + key);
        appointment.setAppointmentId(key);
        
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return appointment;
    }
    
}
