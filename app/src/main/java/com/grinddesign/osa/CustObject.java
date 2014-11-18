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
public class CustObject implements Serializable {

    private String tname;
    private long tmillis;


    private static final long serialVersionUID = 492345792494931449L;

    public CustObject(String _tname, long _tmillis)
    {
        tname = _tname;
        tmillis = _tmillis;
    }

    public void setTname (String tname)
    {
        this.tname = tname;
    }

    public void setTmillis (int tmillis)
    {
        this.tmillis = tmillis;
    }

    public String getTname()
    {
        return tname;
    }

    public long getTmillis()
    {
        return tmillis;
    }

}
