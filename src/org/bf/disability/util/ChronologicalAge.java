/*
 * ChronologicalAge.java
 *
 * Created on January 8, 2009, 2:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.util;

import java.util.Date;

/**
 *
 * @author kiran_h
 */
public class ChronologicalAge
{
    
    private String dateofbirth;
    private String todaysdate;
    private String chronologicalage;
    private Date dob;
    private Date today;
    
    
    public double computeChronologicalAge(Date todaysdate,Date dob)
    {
            long diff = todaysdate.getTime() - dob.getTime();
            long days=diff / (1000 * 60 * 60 * 24);
            
            double years=(double)days/365;
            return years;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
}
