/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.Model;

import hsapp.DAO.Query;
import hsapp.tables.AppointmentTB;
import hsapp.tables.User;
import hsapp.utils.ConvertTime;
import hsapp.utils.TimeCheck;
import java.sql.Date;
//import hsapp.tables.AppointmentTB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 *
 * @author christina joy hartshorn
 */
public class Calander {
    protected ObservableList<Appointment> data;
    protected ObservableList<Appointment> month;
    protected ObservableList<Appointment> week;
    //protected ArrayList<Part> allParts;
    ResultSet rs;
    AppointmentTB appointmentTB = new AppointmentTB();
    ArrayList<Appointment> allAppointments;
    
    
    public Calander()
    {
        allAppointments =  new ArrayList<>();
        String selectStatement = "select * from appointment, customer where customer.customerId = appointment.customerId AND userId = ?;";

        try{
            Query.setPrepareStatement(selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, User.getUserId());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            //data = FXCollections.observableArrayList();
            while (rs.next()){
                Appointment appointment;
                Pair<Date, Time> s = ConvertTime.covertToLocal(rs.getDate("start"), rs.getTime("start"));
                Pair<Date, Time> e = ConvertTime.covertToLocal(rs.getDate("end"), rs.getTime("end"));
                //Appointment(int appointmentId, int appointmentId, String appointmentName, int userId, String userName, String title, String description, String location, String contact, String type, String url, String start, String end) 
                appointment = new Appointment(rs.getInt("appointmentId"), rs.getInt("customerId"), rs.getString("customerName"), rs.getString("title"), rs.getString("description"), rs.getString("location"), rs.getString("contact"), rs.getString("type"), rs.getString("url"), s.getKey(), s.getValue(), e.getKey(), e.getValue());
            allAppointments.add(appointment);
        }

        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Calander");
        } 
        
    }
    
   public String checkForAppintmentShortly(){
       for(Appointment appointment : allAppointments) {
           //System.out.println(Date.valueOf(LocalDate.now())+" : "+appointment.getStartDate());
           //System.out.println(Date.valueOf(LocalDate.now()).equals(appointment.getStartDate()));
            if(TimeCheck.timeAlert(appointment.getStartTime()) && Date.valueOf(LocalDate.now()).equals(appointment.getStartDate())) {
                //System.out.println("in statmeing in check");
                return "You have an appointment at "+appointment.getStartTime()+ " with "+appointment.getCustomerName();
            }
        }
        return "No Appointments coming up";
    }
   
   public boolean checkForOverLap(Appointment appointment) {
       if ((appointment.getStartTime().equals(appointment.getEndTime())) && appointment.getStartDate().equals(appointment.getEndDate()))
       {
           return true;
       }
       for(Appointment appoint : allAppointments) {
           if(appointment.getAppointmentId() != appoint.getAppointmentId())
           {
                if (TimeCheck.timeOverLap(appoint.getStartDate(), appoint.getStartTime(), appoint.getEndDate(), appoint.getEndTime(), appointment.getStartDate(), appointment.getStartTime()))
                    return true;
                else if (TimeCheck.timeOverLap(appoint.getStartDate(), appoint.getStartTime(), appoint.getEndDate(), appoint.getEndTime(), appointment.getEndDate(), appointment.getEndTime()))
                    return true;
           }
        }
       return false;
   }
   

    
    public void addAppointment(Appointment appointment) {

        appointment = appointmentTB.instertAppointment(appointment);
        allAppointments.add(appointment);
    }
    
    public boolean removeAppointment(Appointment appointment) {
        //return true;
        appointmentTB.deleteAppointment(appointment);
        return allAppointments.removeIf(n -> (n.getAppointmentId() == appointment.getAppointmentId()));
    }
    
    public Appointment lookupAppointment(int appointmentId) {
        //return products.get(productID);
        
        for(Appointment appointment : allAppointments) {
            if(appointment.getAppointmentId() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }
    
    public void updateAppointment(int appointmentId, Appointment updatedAppointment) {
        

        updatedAppointment = appointmentTB.updateAppointment(updatedAppointment);

        allAppointments.removeIf(n -> (n.getAppointmentId() == appointmentId));
        allAppointments.add(updatedAppointment);
        
    }
    
    public ObservableList<Appointment> ObsListAppointment() {
       return FXCollections.observableArrayList(allAppointments);
    }
    
    public ObservableList<Appointment> ObsListAppointmentMonth() {
        String selectStatement = "select * from appointment, customer where customer.customerId = appointment.customerId AND start >= curdate() And start < date_add(curdate(), INTERVAL 31 DAY) AND userId = ?;";

        try{
            Query.setPrepareStatement(selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, User.getUserId());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            month = FXCollections.observableArrayList();
            while (rs.next()){
                Appointment appointment;
                Pair<Date, Time> s = ConvertTime.covertToLocal(rs.getDate("start"), rs.getTime("start"));
                Pair<Date, Time> e = ConvertTime.covertToLocal(rs.getDate("end"), rs.getTime("end"));
                //Appointment(int appointmentId, int appointmentId, String appointmentName, int userId, String userName, String title, String description, String location, String contact, String type, String url, String start, String end) 
                appointment = new Appointment(rs.getInt("appointmentId"), rs.getInt("customerId"), rs.getString("customerName"), rs.getString("title"), rs.getString("description"), rs.getString("location"), rs.getString("contact"), rs.getString("type"), rs.getString("url"), s.getKey(), s.getValue(), e.getKey(), e.getValue());
            month.add(appointment);
        }

        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Calander");
        } 
        return month;
    }
    
    public ObservableList<Appointment> ObsListAppointmentWeek() {
        String selectStatement = "select * from appointment, customer where customer.customerId = appointment.customerId AND start >= curdate() And start < date_add(curdate(), INTERVAL 7 DAY) AND userId = ?;";

        try{
            Query.setPrepareStatement(selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setInt(1, User.getUserId());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            week = FXCollections.observableArrayList();
            while (rs.next()){
                Appointment appointment;
                Pair<Date, Time> s = ConvertTime.covertToLocal(rs.getDate("start"), rs.getTime("start"));
                Pair<Date, Time> e = ConvertTime.covertToLocal(rs.getDate("end"), rs.getTime("end"));
                //Appointment(int appointmentId, int appointmentId, String appointmentName, int userId, String userName, String title, String description, String location, String contact, String type, String url, String start, String end) 
                appointment = new Appointment(rs.getInt("appointmentId"), rs.getInt("customerId"), rs.getString("customerName"), rs.getString("title"), rs.getString("description"), rs.getString("location"), rs.getString("contact"), rs.getString("type"), rs.getString("url"), s.getKey(), s.getValue(), e.getKey(), e.getValue());
            week.add(appointment);
        }

        }
        catch(SQLException e)
        {
            System.out.println(e);
            System.out.println("contracter Calander");
        } 
        return week;
    }
}
