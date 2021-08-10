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
public class ReportObject {
    protected String one;
    protected String two;
    protected String three;
    protected String four;
    protected String five;
    protected String six;
    protected String seven;
    protected String eight;
    
    public ReportObject(String one, String two, String three, String four, String five, String six, String seven, String eight) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
    }
    public ReportObject(String one, String two, String three, String four) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = "";
        this.six = "";
        this.seven = "";
        this.eight = "";
    }
    
    
    public String getOne()
    {
        return one;
    }
    
    public void setOne(String one)
    {
        this.one = one;
    }
    public String getTwo()
    {
        return two;
    }
    
    public void setTwo(String two)
    {
        this.two = two;
    }
    public String getThree()
    {
        return three;
    }
    
    public void setThree(String three)
    {
        this.three = three;
    }
    public String getFour()
    {
        return four;
    }
    
    public void setFour(String four)
    {
        this.four = four;
    }
    public String getFive()
    {
        return five;
    }
    
    public void setFive(String five)
    {
        this.five = five;
    }
    public String getSix()
    {
        return six;
    }
    
    public void setSix(String six)
    {
        this.six = six;
    }
    public String getSeven()
    {
        return seven;
    }
    
    public void setSeven(String seven)
    {
        this.seven = seven;
    }
    public String getEight()
    {
        return eight;
    }
    
    public void setEight(String eight)
    {
        this.eight = eight;
    }
    
    
}
