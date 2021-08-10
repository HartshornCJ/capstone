/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.Model;

import hsapp.tables.User;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 *
 * @author christina joy hartshorn
 */
public class Appointment {
    protected int appointmentId;
    protected int customerId;
    protected int userId;
    protected String customerName;
    //protected String userName;
    protected String title;
    protected String description;
    protected String location;
    protected String contact;
    protected String type;
    protected String url;
    protected Date startDate;
    protected Time startTime;
    protected Date endDate;
    protected Time endTime;
    protected ZoneId localZoneId;
    
    public Appointment(int appointmentId, int customerId, String customerName, String title, String description, String location, String contact, String type, String url, Date startDate, Time startTime, Date endDate, Time endTime) 
    {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.userId = User.getUserId();
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.customerName = customerName;
        this.localZoneId = ZoneId.of(TimeZone.getDefault().getID());
       }


    
    public int getAppointmentId()
    {
        return appointmentId;
    }
    
    public void setAppointmentId(int appointmentId)
    {
        this.appointmentId = appointmentId;
    }
    
    public int getCustomerId()
    {
        return customerId;
    }
    
    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }
    
    public int getUserId()
    {
        return userId;
    }
    
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public String getContact()
    {
        return contact;
    }
    
    public void setContact(String contact)
    {
        this.title = contact;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    public Date getStartDate()
    {
        return startDate;
    }
    
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }
    public Time getStartTime()
    {
        return startTime;
    }
    
    public void setStartTime(Time startTime)
    {
        this.startTime = startTime;
    }
    
    public Date getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    
    
    public Time getEndTime()
    {
        return endTime;
    }
    
    public void setEndTime(Time endTime)
    {
        this.endTime = endTime;
    }

    public String getCustomerName()
    {
        return customerName;
    }
    
    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }
}
