/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.Interface;

import java.sql.ResultSet;

/**
 *
 * @author christina joy hartshorn
 */
public interface ResultSetInterface {
    
    //AN interface that takes a string and returns an ResultSet in Report.java
    ResultSet getResultSet(String s);
}
