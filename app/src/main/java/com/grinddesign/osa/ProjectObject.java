package com.grinddesign.osa;

import java.io.Serializable;

/**
 * Author:  Robert Warren
 * <p/>
 * Project:  MDF3
 * <p/>
 * Package: com.grinddesign.osa
 * <p/>
 * Purpose:
 */
public class ProjectObject implements Serializable {

    private String name;
    private String days;
    private String hours;


    private static final long serialVersionUID = 491345792494931449L;

    public ProjectObject(String _name, String _days, String _hours)
    {
        name = _name;
        days = _days;
        hours = _hours;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void setDays (String days)
    {
        this.days = days;
    }

    public void setHours (String hours)
    {
        this.hours = hours;
    }

    public String getName()
    {
        return name;
    }

    public String getDays()
    {
        return days;
    }

    public String getHours()
    {
        return hours;
    }

}
