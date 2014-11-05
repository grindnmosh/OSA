package com.grinddesign.osa;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Author:  Robert Warren
 * <p/>
 * Project:  MDF3
 * <p/>
 * Package: com.grinddesign.osa
 * <p/>
 * Purpose:
 */
public class AssignObject implements Serializable {
    private String ass;
    private String cl;
    private String due;
    private String det;
    private String link;
    private String ins;
    private String em;
    private String chSer;
    private String chHan;

    private static final long serialVersionUID = 491345791492131449L;

    public AssignObject(String _ass, String _cl, String _due, String _det, String _link, String _ins, String _em, String _chSer, String _chHan)
    {
        ass = _ass;
        cl = _cl;
        due = _due;
        det = _det;
        link = _link;
        ins = _ins;
        em = _em;
        chSer = _chSer;
        chHan = _chHan;
    }

    public void setAss (String ass)
    {
        this.ass = ass;
    }

    public void setCl (String cl)
    {
        this.cl = cl;
    }

    public void setDue (String due)
    {
        this.due = due;
    }

    public void setDet (String det)
    {
        this.det = det;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public void setIns (String ins)
    {
        this.ins = ins;
    }

    public void setEm (String em)
    {
        this.em = em;
    }

    public void setChSer (String chSer)
    {
        this.chSer = chSer;
    }

    public void setChHan (String chHan)
    {
        this.chHan = chHan;
    }

    public String getAss()
    {
        return ass;
    }

    public String getCl()
    {
        return cl;
    }

    public String getDue()
    {
        return due;
    }

    public String getDet()
    {
        return det;
    }

    public String getLink()
    {
        return link;
    }

    public String getIns()
    {
        return ins;
    }

    public String getEm()
    {
        return em;
    }

    public String getChSer()
    {
        return chSer;
    }

    public String getChHan()
    {
        return chHan;
    }
}
