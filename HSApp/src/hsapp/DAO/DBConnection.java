/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author christina joy hartshorn
 */
public class DBConnection {
    
    //jdb url parts
    private static final String protcole = "jdbc";
    private static final String vendername = ":mysql:";
    //private static final String severName = "//3.227.166.251/";
    private static final String severName = "//wgudb.ucertify.com/";
    private static final String databaseName = "U04is8";
    
    //JDBC URL
    private static final String jdbcURL = protcole + vendername + severName + databaseName;

    //Driver and connection Refernce
    private static final String mySQLjdbcDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    //database user name and password
    private static final String userName ="U04is8";
    private static final String password ="53688253384";
    
    
    //jdbc:mysql://3.227.166.251/U04is8
    public static Connection startConnection()
    {
        try{
            Class.forName(mySQLjdbcDriver);
            conn= (Connection)DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection successful");
            
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
        return conn;

    }
    
    public static void closeConnection() throws SQLException
    {
        try
        {
            conn.close();
            System.out.println("connecton closed");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}
