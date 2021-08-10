/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsapp.Model;

/**
 *
 * @author christina joy hartshorn
 */
public class UserView {
    protected int userId;
    protected String userName;
    protected int active;
    
    public UserView(int userId, String userName,int active)
    {
        this.userId = userId;
        this.userName = userName;
        this.active = active;
    }
    
    public int getUserId()
    {
        return userId;
    }
    
    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    public String getUserName()
    {
        return userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public int getActive()
    {
        return active;
    }
    
    public void setActive(int active)
    {
        this.active = active;
    }
}
