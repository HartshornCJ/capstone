/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author christina joy hartshorn
 */
public class Query {
    
    // Statement refecne 
    private static PreparedStatement preparedstatement;
    private static Statement statement;
    public static Connection conn;
    
    
    public static void setConnection(Connection conn_local)
    {
        conn = conn_local;
    }
    
    //create statement object
    public static void setStatement() throws SQLException
    {
        statement = conn.createStatement();
    }
    
    public static void setPrepareStatement(String sqlStatement) throws SQLException
    {
        preparedstatement = conn.prepareStatement(sqlStatement);
    }
    
    public static void setPrepareStatementKey(String sqlStatement) throws SQLException
    {
        preparedstatement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
    }
    
    //return statement object
    public static Statement getStatement()
    {
        return statement;
    }
    
    public static PreparedStatement getPreparedStatement()
    {
        return preparedstatement;
    }
    
    
}
