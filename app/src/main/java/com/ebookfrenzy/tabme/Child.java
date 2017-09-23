package com.ebookfrenzy.tabme;

/**
 * Created by Owner-PC on 9/19/2017.
 */

public class Child {
    private int age;
    private String name;
    private int mobile;

    public Child()
    {

    }

    public Child( String name, int age, int mobile)
    {
        this.name = name;
        this.age = age;
        this.mobile = mobile;
    }
    public Child(String name, int mobile)
    {
        this.name = name;
        this.mobile = mobile;

    }
    public void setMobile(int mobile)
    {
        this.mobile = mobile;
    }
    public int getMobile()
    {
        return this.mobile;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public int getAge()
    {
        return this.age;
    }
}
