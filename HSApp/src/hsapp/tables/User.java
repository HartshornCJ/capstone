/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.tables;

import hsapp.DAO.Query;
import hsapp.utils.WriteFile;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author christina joy hartshorn
 */
public class User {
    
    private static String user;//= "test";
    private static int userId;// = 1;
    
    public static boolean verifyAccount(String username, String password) throws SQLException, IOException
    {
        user = username;
        //WriteFile.writeUserLog("Log in attempted From: "+username);
        String selectStatement = "SELECT * FROM user WHERE userName= ? AND password = ?";
        
        try{
            Query.setPrepareStatement(selectStatement);
            PreparedStatement ps = Query.getPreparedStatement();
            ps.setString(1, username);
            ps.setString(2, password);
            ps.execute();
            //System.out.println("Before result set");
            ResultSet rs = ps.getResultSet();
            //userId = rs.getInt("userId");
            //WriteFile.writeUserLog("Log in  From UserId: "+rs.getInt("userId")+ " UserName: "+rs.getString("userName"));
            if(rs.next()){
                userId = rs.getInt("userId");
               //WriteFile.writeUserLog("Log in  From UserId: "+rs.getInt("userId")+ " UserName: "+rs.getString("userName"));
                return true;
            }
            else{
                return false;
            }
            //return rs.next();
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }
    
    public static String getUser()
    {
        return user;
    }
    
    public static int getUserId()
    {
        return userId;
    }
}
