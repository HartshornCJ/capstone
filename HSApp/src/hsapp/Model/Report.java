/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.Model;

import hsapp.DAO.Query;
import hsapp.Interface.CombineStrings;
import hsapp.Interface.ResultSetInterface;
import hsapp.tables.User;
import hsapp.utils.ConvertTime;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 *
 * @author christina joy hartshorn
 */
public class Report {
    protected static ArrayList<ReportObject> typereport;
    protected static ArrayList<ReportObject> consultant;
    protected static ArrayList<ReportObject> client;
    
    //Using the interface CombineSTring to create a lamda that will allow a Month String to Display the number and Month
    CombineStrings  combineS = s -> {
        switch (s) {
            case "1":
                return s + " - January";
            case "2":
                return s + " - February";
            case "3":
                return s+ " - March";
            case "4":
                return s+ " - March";
            case "5":
                return s+ " - May";
            case "6":
                return s+ " - June";
            case "7":
                return s+ " - July";
            case "8":
                return s+ " - August";
            case "9":
                return s+ " - September";
            case "10":
                return s+ " - October";
            case "11":
                return s+ " - November";
            case "12":
                return s+ " - December";
            default:
                break;
        }
        return s;
    };
    //return the resultSet for a given string
    ResultSetInterface getRS = (String s) -> {
        try{
            Query.setPrepareStatement(s);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            return rs;
        }
        catch(SQLException e){
            System.out.println(e);
            ResultSet rs = null;
            return rs;
        }
    };
    
    public Report() throws SQLException{
        setUpTypeReport();
        setUpConsultant();
        setUpClient();
    }
    
    public void setUpTypeReport() throws SQLException
    {
        typereport = new ArrayList<>();
        String selectType = "SELECT EXTRACT(Year FROM start) AS Y, EXTRACT(Month FROM start) AS M, type AS T, count(*) AS C from appointment GROUP BY EXTRACT(Year FROM start), EXTRACT(Month FROM start), type";
        ResultSet rs = getRS.getResultSet(selectType);
        while (rs.next()){
            //rs.getDate(YM)
            String temp = rs.getString("C");
            String temp2 = rs.getString("T");
            String temp3 = rs.getString("Y");
            String temp4 = rs.getString("M");
            ReportObject object = new ReportObject(temp, temp2, temp3, combineS.combineString(temp4));
            typereport.add(object);
        }     
    }
 
    public ObservableList<ReportObject> ObsListTypeReport() {
       return FXCollections.observableArrayList(typereport);
    }
    
    public void setUpConsultant() {
        consultant =  new ArrayList<>();
        String selectStatement = "select * from appointment, user, customer where appointment.userId = user.userId AND appointment.customerId = customer.customerId order by start";

        try{
            ResultSet rs = getRS.getResultSet(selectStatement);
            while (rs.next()){
                ReportObject object;
                Pair<Date, Time> s = ConvertTime.covertToLocal(rs.getDate("start"), rs.getTime("start"));
                Pair<Date, Time> e = ConvertTime.covertToLocal(rs.getDate("end"), rs.getTime("end"));
                object = new ReportObject(rs.getString("userName"), s.getKey().toString(), s.getValue().toString(), e.getKey().toString(), e.getValue().toString(), rs.getString("title"), rs.getString("location"), rs.getString("customerName"));
                consultant.add(object);
            }

        }
        catch(SQLException e)
        {
            System.out.println(e);
        } 
    }
    
    public ObservableList<ReportObject> ObsListConsultantReport() {
       return FXCollections.observableArrayList(consultant);
    }
    
    public void setUpClient() throws SQLException
    {
        client = new ArrayList<>();
        String selectClient = "SELECT customername AS N, EXTRACT(Year FROM start) AS Y, EXTRACT(Month FROM start) AS M, count(*) AS C FROM appointment, user, customer WHERE appointment.userId = user.userId AND customer.customerId = appointment.customerId group by customername, EXTRACT(Year FROM start), EXTRACT(Month FROM start)";
        ResultSet rs = getRS.getResultSet(selectClient);
        while (rs.next()){
            String temp = rs.getString("C");
            String temp2 = rs.getString("N");
            String temp3 = rs.getString("Y");
            String temp4 = rs.getString("M");
            ReportObject object = new ReportObject(temp, temp2, temp3, combineS.combineString(temp4));
            client.add(object);
        }
    }
    
    public ObservableList<ReportObject> ObsListClientReport() {
       return FXCollections.observableArrayList(client);
    }
}
