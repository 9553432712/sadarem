/*
 * SFBBean.java
 *
 * Created on January 5, 2009, 11:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.bean;

/**
 *
 * @author kiran_h
 */
public class SFBBean {
    
    /** Creates a new instance of SFBBean */
    public SFBBean() {
    }
    private String sfbyear;
    private String sfbmonth;
    private double ca;
    private String sfbtrialone;
    private String sfbtrialtwo;
    private String sfbtrialthree;
    private double sfbiq;
    private double ageyears;
    private double agemonths;
    private StringBuffer iqbuffer;  
    private StringBuffer mentalagebuffer;
    private String flag;
    
    
    private String dateofbirth;
    private String todaysdate;
    private String chronologicalage;
    private boolean chronologicalageflag;
    
   
    
    public String getSfbyear() {
        return sfbyear;
    }

    public void setSfbyear(String sfbyear) {
        this.sfbyear = sfbyear;
    }

    public String getSfbmonth() {
        return sfbmonth;
    }

    public void setSfbmonth(String sfbmonth) {
        this.sfbmonth = sfbmonth;
    }

    public String getSfbtrialone() {
        return sfbtrialone;
    }

    public void setSfbtrialone(String sfbtrialone) {
        this.sfbtrialone = sfbtrialone;
    }

    public String getSfbtrialtwo() {
        return sfbtrialtwo;
    }

    public void setSfbtrialtwo(String sfbtrialtwo) {
        this.sfbtrialtwo = sfbtrialtwo;
    }

    public String getSfbtrialthree() {
        return sfbtrialthree;
    }

    public void setSfbtrialthree(String sfbtrialthree) {
        this.sfbtrialthree = sfbtrialthree;
    }

    public double getSfbiq() {
        return sfbiq;
    }

    public void setSfbiq(double sfbiq) {
        this.sfbiq = sfbiq;
    }

    public double getAgeyears() {
        return ageyears;
    }

    public void setAgeyears(double ageyears) {
        this.ageyears = ageyears;
    }

    public double getAgemonths() {
        return agemonths;
    }

    public void setAgemonths(double agemonths) {
        this.agemonths = agemonths;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public StringBuffer getIqbuffer() {
        return iqbuffer;
    }

    public void setIqbuffer(StringBuffer iqbuffer) {
        this.iqbuffer = iqbuffer;
    }

    public StringBuffer getMentalagebuffer() {
        return mentalagebuffer;
    }

    public void setMentalagebuffer(StringBuffer mentalagebuffer) {
        this.mentalagebuffer = mentalagebuffer;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getTodaysdate() {
        return todaysdate;
    }

    public void setTodaysdate(String todaysdate) {
        this.todaysdate = todaysdate;
    }

    public String getChronologicalage() {
        return chronologicalage;
    }

    public void setChronologicalage(String chronologicalage) {
        this.chronologicalage = chronologicalage;
    }

    public boolean isChronologicalageflag() {
        return chronologicalageflag;
    }

    public void setChronologicalageflag(boolean chronologicalageflag) {
        this.chronologicalageflag = chronologicalageflag;
    }

   

       

    
}
