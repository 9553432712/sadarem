/*
 * PIDuetoNeurologicalConditionsBean.java
 *
 * Created on January 2, 2009, 2:47 PM
 *
 */

package org.bf.disability.bean;

/**
 *
 * @author Syam
 */
public class PIDuetoNeurologicalConditionsBean {
    
    /**
     * Creates a new instance of PIDuetoNeurologicalConditionsBean
     */
    public PIDuetoNeurologicalConditionsBean() {
    }
    private String dominantupperextremity=null;
    
    //check box varible for Loss of Sensation
    private String lossofsensationupper=null;;
    private String lossofsensationlower=null;
    private String sumoflossofsensation=null;
    private boolean lossofsensationflag;
    //check box varible for Altered sensorium
    private String neurologicalstatus=null;
    
    //radio button varivle for Intellectual impairment
    private String intellectualimpairment=null;
    
    //radio button varivle for speechdefect
    private String speechdefect=null;
    
    //check box for Motor cranial nerve
    private String e6a=null;
    private String e6b=null;
    private String e6c=null;
    private String e6d=null;
    private String e6e=null;
    private String e6f=null;
    //Added by two fields
    private String e6g=null;
    private String e6h=null;
    public String getE6g() {
        return e6g;
    }

    public void setE6g(String e6g) {
        this.e6g = e6g;
    }

    public String getE6h() {
        return e6h;
    }

    public void setE6h(String e6h) {
        this.e6h = e6h;
    }

    private boolean motorcranialnerveflag;
     private String motorstring;
     private boolean motornervetotalflag;
    private boolean motornerveflagforvalue;
    
    //check box for sensory cranial nerve
    private String sca=null;
    private String scb=null;
    private String scc=null;
    private String scd=null;
    private boolean sensorycranialnerveflag;
    private boolean sensorynervetotalflag;
    private String sensorystring;
            
    //radio button for Motor system disability
    private String motorsystem;
   
    
    //checkbox for Hands/Feetand Sensory Loss
    private String sensorysystemh=null;
    private String sensorysystemf=null;
    private String sensorysystemt=null;
    
    //radio button for  Sensory system disability
    private String sensorysystem=null;
    private String sensorysystem1=null;
    private String sensorysystem2=null;
     private boolean sensorysystemflag;
     private boolean sensorysystemtotalflag;
     private String sensorysystemstring;
    
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
    
    //Values for formula presentation
    private StringBuffer value1;
    private boolean value1flag;
    private StringBuffer value2;
    private boolean value2flag;
    private StringBuffer value3;
    private boolean value3flag;
    private StringBuffer value4;
    private boolean value4flag;
    private StringBuffer value5;
    private boolean value5flag;
    private StringBuffer value6;
    private boolean value6flag;
     private StringBuffer value7;
    private boolean value7flag;
     private StringBuffer value8;
    private boolean value8flag;
    private StringBuffer value9;
    private boolean value9flag;
    private StringBuffer value10;
    private boolean value10flag;
    private StringBuffer totalpercentbuffer;
    private boolean totalpercentflag;
    
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

    public String getSumoflossofsensation() {
        return sumoflossofsensation;
    }

    public void setSumoflossofsensation(String sumoflossofsensation) {
        this.sumoflossofsensation = sumoflossofsensation;
    }

    public boolean isLossofsensationflag() {
        return lossofsensationflag;
    }

    public void setLossofsensationflag(boolean lossofsensationflag) {
        this.lossofsensationflag = lossofsensationflag;
    }

    public boolean isMotorcranialnerveflag() {
        return motorcranialnerveflag;
    }

    public void setMotorcranialnerveflag(boolean motorcranialnerveflag) {
        this.motorcranialnerveflag = motorcranialnerveflag;
    }

    public boolean isSensorycranialnerveflag() {
        return sensorycranialnerveflag;
    }

    public void setSensorycranialnerveflag(boolean sensorycranialnerveflag) {
        this.sensorycranialnerveflag = sensorycranialnerveflag;
    }

    public boolean isSensorysystemflag() {
        return sensorysystemflag;
    }

    public void setSensorysystemflag(boolean sensorysystemflag) {
        this.sensorysystemflag = sensorysystemflag;
    }

    public boolean getValue1flag() {
        return value1flag;
    }

    public void setValue1flag(boolean value1flag) {
        this.value1flag = value1flag;
    }

    public boolean getValue2flag() {
        return value2flag;
    }

    public void setValue2flag(boolean value2flag) {
        this.value2flag = value2flag;
    }

    public StringBuffer getValue1() {
        return value1;
    }

    public void setValue1(StringBuffer value1) {
        this.value1 = value1;
    }

    public StringBuffer getValue2() {
        return value2;
    }

    public void setValue2(StringBuffer value2) {
        this.value2 = value2;
    }

    public StringBuffer getValue3() {
        return value3;
    }

    public void setValue3(StringBuffer value3) {
        this.value3 = value3;
    }

    public boolean getValue3flag() {
        return value3flag;
    }

    public void setValue3flag(boolean value3flag) {
        this.value3flag = value3flag;
    }

    public String getMotorstring() {
        return motorstring;
    }

    public void setMotorstring(String motorstring) {
        this.motorstring = motorstring;
    }

    public boolean isMotornerveflagforvalue() {
        return motornerveflagforvalue;
    }

    public void setMotornerveflagforvalue(boolean motornerveflagforvalue) {
        this.motornerveflagforvalue = motornerveflagforvalue;
    }

    public boolean isMotornervetotalflag() {
        return motornervetotalflag;
    }

    public void setMotornervetotalflag(boolean motornervetotalflag) {
        this.motornervetotalflag = motornervetotalflag;
    }

    public StringBuffer getValue4() {
        return value4;
    }

    public void setValue4(StringBuffer value4) {
        this.value4 = value4;
    }

    public boolean isValue4flag() {
        return value4flag;
    }

    public void setValue4flag(boolean value4flag) {
        this.value4flag = value4flag;
    }

    public boolean isSensorynervetotalflag() {
        return sensorynervetotalflag;
    }

    public void setSensorynervetotalflag(boolean sensorynervetotalflag) {
        this.sensorynervetotalflag = sensorynervetotalflag;
    }

    public String getSensorystring() {
        return sensorystring;
    }

    public void setSensorystring(String sensorystring) {
        this.sensorystring = sensorystring;
    }

    public StringBuffer getValue5() {
        return value5;
    }

    public void setValue5(StringBuffer value5) {
        this.value5 = value5;
    }

    public boolean isValue5flag() {
        return value5flag;
    }

    public void setValue5flag(boolean value5flag) {
        this.value5flag = value5flag;
    }

    public StringBuffer getValue6() {
        return value6;
    }

    public void setValue6(StringBuffer value6) {
        this.value6 = value6;
    }

    public boolean isValue6flag() {
        return value6flag;
    }

    public void setValue6flag(boolean value6flag) {
        this.value6flag = value6flag;
    }

    public boolean isSensorysystemtotalflag() {
        return sensorysystemtotalflag;
    }

    public void setSensorysystemtotalflag(boolean sensorysystemtotalflag) {
        this.sensorysystemtotalflag = sensorysystemtotalflag;
    }

    public String getSensorysystemstring() {
        return sensorysystemstring;
    }

    public void setSensorysystemstring(String sensorysystemstring) {
        this.sensorysystemstring = sensorysystemstring;
    }

    public StringBuffer getValue7() {
        return value7;
    }

    public void setValue7(StringBuffer value7) {
        this.value7 = value7;
    }

    public boolean isValue7flag() {
        return value7flag;
    }

    public void setValue7flag(boolean value7flag) {
        this.value7flag = value7flag;
    }

    public boolean isValue8flag() {
        return value8flag;
    }

    public void setValue8flag(boolean value8flag) {
        this.value8flag = value8flag;
    }

    public StringBuffer getValue8() {
        return value8;
    }

    public void setValue8(StringBuffer value8) {
        this.value8 = value8;
    }

    public StringBuffer getValue9() {
        return value9;
    }

    public void setValue9(StringBuffer value9) {
        this.value9 = value9;
    }

    public boolean isValue9flag() {
        return value9flag;
    }

    public void setValue9flag(boolean value9flag) {
        this.value9flag = value9flag;
    }

    public StringBuffer getValue10() {
        return value10;
    }

    public void setValue10(StringBuffer value10) {
        this.value10 = value10;
    }

    public boolean isValue10flag() {
        return value10flag;
    }

    public void setValue10flag(boolean value10flag) {
        this.value10flag = value10flag;
    }

    public boolean isTotalpercentflag() {
        return totalpercentflag;
    }

    public void setTotalpercentflag(boolean totalpercentflag) {
        this.totalpercentflag = totalpercentflag;
    }

    public StringBuffer getTotalpercentbuffer() {
        return totalpercentbuffer;
    }

    public void setTotalpercentbuffer(StringBuffer totalpercentbuffer) {
        this.totalpercentbuffer = totalpercentbuffer;
    }

    

   
    
    
}
