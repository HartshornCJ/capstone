/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.utils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author christina joy hartshorn
 */
public class TimeCheck {
    public static boolean timeOverLap(LocalDateTime start, LocalDateTime end, LocalDateTime check)
    {
        if(check.isAfter(start) && check.isBefore(end))
            return true;
        else if(check.equals(start) || check.equals(end))
            return true;
        else 
            return false;
    }
     public static boolean timeOverLap(Date startDate, Time startTime, Date endDate, Time endTime, Date checkDate, Time checkTime)
     {
         LocalDateTime start = LocalDateTime.of(startDate.toLocalDate(), startTime.toLocalTime());
         LocalDateTime end = LocalDateTime.of(endDate.toLocalDate(), endTime.toLocalTime());
         LocalDateTime check = LocalDateTime.of(checkDate.toLocalDate(), checkTime.toLocalTime());
         return timeOverLap(start, end, check);
     }
     
     public static boolean timeBusHours(LocalTime check)
    {
        LocalTime workStart = LocalTime.of(07, 00);
        LocalTime workend = LocalTime.of(17, 30);
        if(check.isAfter(workStart) && check.isBefore(workend))
            return true;
        else if(check.equals(workStart))
            return true;
        else 
            return false;
    }
     public static boolean timeBusHours(Time checkTime)
     {
         LocalTime check = checkTime.toLocalTime();
         return timeBusHours(check);
     }
     
     public static boolean timeAlert(LocalTime event)
     {
        LocalTime currentTime = LocalTime.now();
        
        long timeDifference = ChronoUnit.MINUTES.between(currentTime, event);
        //System.out.println(timeDifference);
        //System.out.println(event);
        //System.out.println(currentTime);
        if(timeDifference>= 0 && timeDifference <=15){
            //System.out.println("in if statement");
            return true;
        }
        else if(timeDifference <= 1)
            return false;
            //return timeDifference;
        else
            return false;
     }
     public static boolean timeAlert(Time event)
     {
         return timeAlert(event.toLocalTime());
     }

}
