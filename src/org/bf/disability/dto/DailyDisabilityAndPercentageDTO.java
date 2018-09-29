package org.bf.disability.dto;

import java.io.*;
import java.net.*;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.Serializable;
/**
 * This class contains the fields, required to generate reports based on the no.of
 * persons per disability and percentage of persons per disability
 * @author Raghavendra
 * @version 1.0
 */
public class DailyDisabilityAndPercentageDTO 
{
      private String fromdate;
    private String todate;
    private String typeofdisability;
    private String disabilitycount;
    private String todaydate;
    
     private String no_of_persons;
    private String eligible_persons;
    private String rejected_persons;
    private String lessthan_fourty;
    private String fourty_to_sixty;
    private String sixtyone_to_eighty;
    private String above_eighty;
   
    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }
     public String getTypeofdisability() {
        return typeofdisability;
    }

    public void setTypeofdisability(String typeofdisability) {
        this.typeofdisability = typeofdisability;
    }

   
    public String getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(String todaydate) {
        this.todaydate = todaydate;
    }

    public String getDisabilitycount() {
        return disabilitycount;
    }

    public void setDisabilitycount(String disabilitycount) {
        this.disabilitycount = disabilitycount;
    }

    public String getNo_of_persons() {
        return no_of_persons;
    }

    public void setNo_of_persons(String no_of_persons) {
        this.no_of_persons = no_of_persons;
    }

    public String getEligible_persons() {
        return eligible_persons;
    }

    public void setEligible_persons(String eligible_persons) {
        this.eligible_persons = eligible_persons;
    }

    public String getRejected_persons() {
        return rejected_persons;
    }

    public void setRejected_persons(String rejected_persons) {
        this.rejected_persons = rejected_persons;
    }

    public String getLessthan_fourty() {
        return lessthan_fourty;
    }

    public void setLessthan_fourty(String lessthan_fourty) {
        this.lessthan_fourty = lessthan_fourty;
    }

    public String getFourty_to_sixty() {
        return fourty_to_sixty;
    }

    public void setFourty_to_sixty(String fourty_to_sixty) {
        this.fourty_to_sixty = fourty_to_sixty;
    }

    public String getSixtyone_to_eighty() {
        return sixtyone_to_eighty;
    }

    public void setSixtyone_to_eighty(String sixtyone_to_eighty) {
        this.sixtyone_to_eighty = sixtyone_to_eighty;
    }

    public String getAbove_eighty() {
        return above_eighty;
    }

    public void setAbove_eighty(String above_eighty) {
        this.above_eighty = above_eighty;
    }
}
