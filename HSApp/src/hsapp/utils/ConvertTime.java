/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.utils;

import java.time.Instant;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import javafx.util.Pair;

/**
 *
 * @author christina joy hartshorn
 */
public class ConvertTime {
    /*
    LocalDate parisDate = LocalDate.of(2020, Month.MARCH, 24);
        LocalTime timeParis = LocalTime.of(19, 00, 00);
        ZoneId parisZoneId = ZoneId.of("Greenwich");
        ZonedDateTime parisZDT = ZonedDateTime.of(parisDate, timeParis, parisZoneId);
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        
        Instant parisToGMTInstant = parisZDT.toInstant();
        ZonedDateTime paristoLocalZDT = parisZDT.withZoneSameInstant(localZoneId);
        ZonedDateTime getToLocalZDT = parisToGMTInstant.atZone(localZoneId);
        String date = String.valueOf(getToLocalZDT.toLocalDate());
        String time = String.valueOf(getToLocalZDT.toLocalTime());
    */
    public static Pair<Date, Time> covertToLocal(Date date, Time time)
    {
        LocalDate localDate = date.toLocalDate();
        LocalTime localTime= time.toLocalTime();
        
        ZoneId serverZoneId = ZoneId.of("UTC");
        ZonedDateTime serverZDT = ZonedDateTime.of(localDate, localTime, serverZoneId);
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        
        Instant serverInstant = serverZDT.toInstant();
        //ZonedDateTime servertoLocalZDT = serverZDT.withZoneSameInstant(localZoneId);
        ZonedDateTime getToLocalZDT = serverInstant.atZone(localZoneId);
        
        date = Date.valueOf(getToLocalZDT.toLocalDate());
        time = Time.valueOf(getToLocalZDT.toLocalTime());
        
        return new Pair<Date, Time>(date, time);
    }
    
    public static Pair<Date, Time> covertToSever(Date date, Time time)
    {
        LocalDate localDate = date.toLocalDate();
        LocalTime localTime= time.toLocalTime();
        
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        ZonedDateTime serverZDT = ZonedDateTime.of(localDate, localTime, localZoneId);
        ZoneId serverZoneId = ZoneId.of("UTC");
        
        Instant serverInstant = serverZDT.toInstant();
        //ZonedDateTime servertoLocalZDT = serverZDT.withZoneSameInstant(localZoneId);
        ZonedDateTime getToLocalZDT = serverInstant.atZone(serverZoneId);
        
        date = Date.valueOf(getToLocalZDT.toLocalDate());
        time = Time.valueOf(getToLocalZDT.toLocalTime());
        
        return new Pair<Date, Time>(date, time);
    }
}
