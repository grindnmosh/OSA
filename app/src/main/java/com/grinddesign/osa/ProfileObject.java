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
public class ProfileObject implements Serializable {

    private String studName;
    private String studNum;
    private String schName;
    private String dept1;
    private String dept2;
    private String dept3;
    private String dept4;
    private String dept5;
    private String num1;
    private String num2;
    private String num3;
    private String num4;
    private String num5;
    private String img;

    private static final long serialVersionUID = 491345791492131449L;

    public ProfileObject(String _studName, String _studNum, String _schName,
                         String _dept1, String _dept2, String _dept3, String _dept4, String _dept5,
                         String _num1, String _num2, String _num3, String _num4, String _num5, String _img)
    {
        studName = _studName;
        studNum = _studNum;
        schName = _schName;
        dept1 = _dept1;
        dept2 = _dept2;
        dept3 = _dept3;
        dept4 = _dept4;
        dept5 = _dept5;
        num1 = _num1;
        num2 = _num2;
        num3 = _num3;
        num4 = _num4;
        num5 = _num5;
        img = _img;
    }

    public void setStudName (String studName)
    {
        this.studName = studName;
    }

    public void setStudNum (String studNum)
    {
        this.studNum = studNum;
    }

    public void setSchName (String schName)
    {
        this.schName = schName;
    }

    public void setDept1 (String dept1)
    {
        this.dept1 = dept1;
    }

    public void setDept2 (String dept2)
    {
        this.dept2 = dept2;
    }

    public void setDept3 (String dept3)
    {
        this.dept3 = dept3;
    }

    public void setDept4 (String dept4)
    {
        this.dept4 = dept4;
    }
    public void setDept5 (String dept5)
    {
        this.dept5 = dept5;
    }

    public void setNum1 (String num1)
    {
        this.num1 = num1;
    }

    public void setNum2 (String num2)
    {
        this.num2 = num2;
    }

    public void setNum3 (String num3)
    {
        this.num3 = num3;
    }

    public void setNum4 (String num4)
    {
        this.num4 = num4;
    }

    public void setNum5 (String num5)
    {
        this.num5 = num5;
    }

    public void setImg (String img)
    {
        this.img = img;
    }

    public String getStudName()
    {
        return studName;
    }

    public String getStudNum()
    {
        return studNum;
    }

    public String getSchName()
    {
        return schName;
    }

    public String getDept1()
    {
        return dept1;
    }

    public String getDept2()
    {
        return dept2;
    }

    public String getDept3()
    {
        return dept3;
    }

    public String getDept4()
    {
        return dept4;
    }

    public String getDept5()
    {
        return dept5;
    }

    public String getNum1()
    {
        return num1;
    }

    public String getNum2()
    {
        return num2;
    }

    public String getNum3()
    {
        return num3;
    }

    public String getNum4()
    {
        return num4;
    }

    public String getNum5()
    {
        return num5;
    }

    public String getImg()
    {
        return img;
    }

}
