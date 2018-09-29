/*
 * TotalDisabilityBean.java
 *
 * Created on January 20, 2009, 10:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.bean;

/**
 *
 * @author Bapinaidu.t
 */
public class TotalDisabilityBean {
    
    /** Creates a new instance of TotalDisabilityBean */
    public TotalDisabilityBean() {
    }
    
    public double totalphysical;
    public double hearingimpairment;
    public double visualimpairment;
    public double mentalretardation;
    public double mentalillness;
    public StringBuffer tolalformula;
    public double ohfinalvalue;

    public StringBuffer getTolalformula() {
        return tolalformula;
    }

    public void setTolalformula(StringBuffer tolalformula) {
        this.tolalformula = tolalformula;
    }

    public double getOhfinalvalue() {
        return ohfinalvalue;
    }

    public void setOhfinalvalue(double ohfinalvalue) {
        this.ohfinalvalue = ohfinalvalue;
    }

    public double getTotalphysical() {
        return totalphysical;
    }

    public void setTotalphysical(double totalphysical) {
        this.totalphysical = totalphysical;
    }

    public double getHearingimpairment() {
        return hearingimpairment;
    }

    public void setHearingimpairment(double hearingimpairment) {
        this.hearingimpairment = hearingimpairment;
    }

    public double getVisualimpairment() {
        return visualimpairment;
    }

    public void setVisualimpairment(double visualimpairment) {
        this.visualimpairment = visualimpairment;
    }

    public double getMentalretardation() {
        return mentalretardation;
    }

    public void setMentalretardation(double mentalretardation) {
        this.mentalretardation = mentalretardation;
    }

    public double getMentalillness() {
        return mentalillness;
    }

    public void setMentalillness(double mentalillness) {
        this.mentalillness = mentalillness;
    }
    
}
