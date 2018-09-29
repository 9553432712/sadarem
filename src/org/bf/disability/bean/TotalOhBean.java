/*
 * TotalOhBean.java
 *
 * Created on January 15, 2009, 12:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.bean;

/**
 *
 * @author bapinaidu.t
 */
public class TotalOhBean {
    
    /** Creates a new instance of TotalOhBean */
    public TotalOhBean() {
    }
   private double upperextrimity;
  private double lowererextrimity;
  private double amputation;
  private double transverse;
  private double trunk;
  private double evaluation;
  private double cardiopulmonary;
  private double dwarfism;
  
  private StringBuffer tolalformula;
  
   private double ohfinalvalue;
  
    public double getUpperextrimity() {
        return upperextrimity;
    }

    public void setUpperextrimity(double upperextrimity) {
        this.upperextrimity = upperextrimity;
    }

    public double getLowererextrimity() {
        return lowererextrimity;
    }

    public void setLowererextrimity(double lowererextrimity) {
        this.lowererextrimity = lowererextrimity;
    }

    public double getAmputation() {
        return amputation;
    }

    public void setAmputation(double amputation) {
        this.amputation = amputation;
    }

    public double getTransverse() {
        return transverse;
    }

    public void setTransverse(double transverse) {
        this.transverse = transverse;
    }

    public double getTrunk() {
        return trunk;
    }

    public void setTrunk(double trunk) {
        this.trunk = trunk;
    }

   

    public double getCardiopulmonary() {
        return cardiopulmonary;
    }

    public void setCardiopulmonary(double cardiopulmonary) {
        this.cardiopulmonary = cardiopulmonary;
    }

    public double getDwarfism() {
        return dwarfism;
    }

    public void setDwarfism(double dwarfism) {
        this.dwarfism = dwarfism;
    }

    public StringBuffer getTolalformula() {
        return tolalformula;
    }

    public void setTolalformula(StringBuffer tolalformula) {
        this.tolalformula = tolalformula;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    public double getOhfinalvalue() {
        return ohfinalvalue;
    }

    public void setOhfinalvalue(double ohfinalvalue) {
        this.ohfinalvalue = ohfinalvalue;
    }
    
    
    
    
}
