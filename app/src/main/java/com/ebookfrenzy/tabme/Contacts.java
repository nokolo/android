package com.ebookfrenzy.tabme;

/**
 * Created by Owner-PC on 9/24/2017.
 */

public class Contacts {
    private int id;
    private String name, mobile, password;


    public Contacts()
    {

    }
    public Contacts(String name, String mobile, String password)
    {
        this.name = name;
        this.mobile = mobile;
        this.password = password;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return this.id;

    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    public String getMobile()
    {
        return this.mobile;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }

}
