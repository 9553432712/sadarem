
/*
 * EvaluationActionForm.java
 *
 * Created on July 16, 2008, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.form;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Sunima
 */

public class EvaluationActionForm extends ActionForm {
    
    //check box varible for Dominant upper extremity
    private String dominantupperextremity=null;
    
    //check box varible for Loss of Sensation
    private String lossofsensationupper=null;;
    private String lossofsensationlower=null;
    
    //check box varible for Altered sensorium
    private String neurologicalstatus=null;
    
    //radio button varivle for Intellectual impairment
    private String intellectualimpairment=null;
    
    //radio button varivle for speechdefect
    private String speechdefect=null;
    
    //check box for Motor cranical nerve
    private String e6a=null;
    private String e6b=null;
    private String e6c=null;
    private String e6d=null;
    private String e6e=null;
    private String e6f=null;


     //Added by two fields
    private String e6g=null;
    private String e6h=null;

    public String getE6h() {
        return e6h;
    }

    public void setE6h(String e6h) {
        this.e6h = e6h;
    }
    
    
    //check box for sensory cranical nerve
    private String sca=null;
    private String scb=null;
    private String scc=null;
    private String scd=null;
    
    //radio button for Motor system disability
    private String motorsystem;
    private String motorsystemLeftOrRight=null;
    
    //checkbox for Hands/Feetand Sensory Loss
    private String sensorysystemh=null;
    private String sensorysystemf=null;
    private String sensorysystemt=null;
    
    //radio button for  Sensory system disability
    private String sensorysystem=null;
    private String sensorysystem1=null;
    private String sensorysystem2=null;
    
    //radio button for Bladder disability
    private String bladderinvolvement=null;
    //radio button for Injury
    private String injury=null;
    //radio button for Ataxia
    private String ataxia=null;
    
    private double totalpercent=0.0;
    private String personcode=null;
    private String loginid=null;
    private String systemip=null;
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        
        this.setDominantupperextremity("0");
        this.setLossofsensationupper("0");
        this.setLossofsensationlower("0");
        this.neurologicalstatus="0";
        
        //radio button
        this.intellectualimpairment="0";
        this.speechdefect="0";
        //check box
        this.e6a="0";
        this.e6b="0";
        this.e6c="0";
        this.e6d="0";
        this.e6e="0";
        this.e6f="0";
        this.e6g="0";
        this.e6h="0";
        
        
        this.sca="0";
        this.scb="0";
        this.scc="0";
        this.scd="0";
        //radio button
        this.motorsystemLeftOrRight="0";
        this.motorsystem="0";
        this.sensorysystem="0";
        this.setSensorysystem1("0");
        this.setSensorysystem2("0");
        this.bladderinvolvement="0";
        this.ataxia="0";
        this.injury="0";
        
    }

     public String getE6g() {
        return e6g;
    }

    public void setE6g(String e6g) {
        this.e6g = e6g;
    }
    
    public String getDominantupperextremity() {
        return dominantupperextremity;
    }
    
    public void setDominantupperextremity(String dominantupperextremity) {
        this.dominantupperextremity = dominantupperextremity;
    }
    
    public String getLossofsensationupper() {
        return lossofsensationupper;
    }
    
    public void setLossofsensationupper(String lossofsensationupper) {
        this.lossofsensationupper = lossofsensationupper;
    }
    
    public String getLossofsensationlower() {
        return lossofsensationlower;
    }
    
    public void setLossofsensationlower(String lossofsensationlower) {
        this.lossofsensationlower = lossofsensationlower;
    }
    
    public String getNeurologicalstatus() {
        return neurologicalstatus;
    }
    
    public void setNeurologicalstatus(String neurologicalstatus) {
        this.neurologicalstatus = neurologicalstatus;
    }
    
    public String getIntellectualimpairment() {
        return intellectualimpairment;
    }
    
    public void setIntellectualimpairment(String intellectualimpairment) {
        this.intellectualimpairment = intellectualimpairment;
    }
    
    public String getSpeechdefect() {
        return speechdefect;
    }
    
    public void setSpeechdefect(String speechdefect) {
        this.speechdefect = speechdefect;
    }
    
    public String getE6a() {
        return e6a;
    }
    
    public void setE6a(String e6a) {
        this.e6a = e6a;
    }
    
    public String getE6b() {
        return e6b;
    }
    
    public void setE6b(String e6b) {
        this.e6b = e6b;
    }
    
    public String getE6c() {
        return e6c;
    }
    
    public void setE6c(String e6c) {
        this.e6c = e6c;
    }
    
    public String getE6d() {
        return e6d;
    }
    
    public void setE6d(String e6d) {
        this.e6d = e6d;
    }
    
    public String getE6e() {
        return e6e;
    }
    
    public void setE6e(String e6e) {
        this.e6e = e6e;
    }
    
    public String getE6f() {
        return e6f;
    }
    
    public void setE6f(String e6f) {
        this.e6f = e6f;
    }
    
    public String getSca() {
        return sca;
    }
    
    public void setSca(String sca) {
        this.sca = sca;
    }
    
    public String getScb() {
        return scb;
    }
    
    public void setScb(String scb) {
        this.scb = scb;
    }
    
    public String getScc() {
        return scc;
    }
    
    public void setScc(String scc) {
        this.scc = scc;
    }
    
    public String getScd() {
        return scd;
    }
    
    public void setScd(String scd) {
        this.scd = scd;
    }
    
    public String getMotorsystem() {
        return motorsystem;
    }
    
    public void setMotorsystem(String motorsystem) {
        this.motorsystem = motorsystem;
    }
    
    public String getSensorysystem() {
        return sensorysystem;
    }
    
    public void setSensorysystem(String sensorysystem) {
        this.sensorysystem = sensorysystem;
    }
    
    
    
    public String getAtaxia() {
        return ataxia;
    }
    
    
    
    public void setAtaxia(String ataxia) {
        this.ataxia = ataxia;
    }
    
    public String getBladderinvolvement() {
        return bladderinvolvement;
    }
    
    public void setBladderinvolvement(String bladderinvolvement) {
        this.bladderinvolvement = bladderinvolvement;
    }
    
    public String getInjury() {
        return injury;
    }
    
    public void setInjury(String injury) {
        this.injury = injury;
    }
    
    public String getSensorysystem1() {
        return sensorysystem1;
    }
    
    public void setSensorysystem1(String sensorysystem1) {
        this.sensorysystem1 = sensorysystem1;
    }
    
    public String getSensorysystem2() {
        return sensorysystem2;
    }
    
    public void setSensorysystem2(String sensorysystem2) {
        this.sensorysystem2 = sensorysystem2;
    }
    
    public String getSensorysystemh() {
        return sensorysystemh;
    }
    
    public void setSensorysystemh(String sensorysystemh) {
        this.sensorysystemh = sensorysystemh;
    }
    
    public String getSensorysystemf() {
        return sensorysystemf;
    }
    
    public void setSensorysystemf(String sensorysystemf) {
        this.sensorysystemf = sensorysystemf;
    }
    
    public String getSensorysystemt() {
        return sensorysystemt;
    }
    
    public void setSensorysystemt(String sensorysystemt) {
        this.sensorysystemt = sensorysystemt;
    }
    
    public double getTotalpercent() {
        return totalpercent;
    }
    
    public void setTotalpercent(double totalpercent) {
        this.totalpercent = totalpercent;
    }
    
    public String getPersoncode() {
        return personcode;
    }
    
    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }
    
    public String getLoginid() {
        return loginid;
    }
    
    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
    
    public String getSystemip() {
        return systemip;
    }
    
    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    /**
     * @return the motorsystemLeftOrRight
     */
    public String getMotorsystemLeftOrRight() {
        return motorsystemLeftOrRight;
    }

    /**
     * @param motorsystemLeftOrRight the motorsystemLeftOrRight to set
     */
    public void setMotorsystemLeftOrRight(String motorsystemLeftOrRight) {
        this.motorsystemLeftOrRight = motorsystemLeftOrRight;
    }
    
    
    
}
