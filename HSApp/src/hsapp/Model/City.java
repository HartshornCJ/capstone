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
public class City extends Country{
    protected int cityId;
    protected String cityName;
    
    public City(int countryId, String countryName, int cityId, String cityName)
    {
        super(countryId, countryName);
        this.cityId = cityId;
        this.cityName = cityName;
    }
    
    public int getCityId()
    {
        return cityId;
    }
    
    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }
    
    public String getCityName()
    {
        return cityName;
    }
    
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
}
