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
public class GradObject implements Serializable{

    private long gmillis;


    private static final long serialVersionUID = 492345792494931549L;

    public GradObject(long _gmillis)
    {
        gmillis = _gmillis;
    }
    public void setGmillis (long gmillis)
    {
        this.gmillis = gmillis;
    }

    public long getGmillis()
    {
        return gmillis;
    }

}
