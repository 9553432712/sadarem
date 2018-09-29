/*
 * TransverseBean.java
 *
 * Created on January 2, 2009, 2:46 PM
 *
 */

package org.bf.disability.bean;

/**
 *
 * @author Deviprasad_t
 */
public class TransverseBean {
    
public int shoulderdisarticulationrightside;
public int shoulderdisarticulationleftside;
public int hipdisarticulationrightside;
public int hipdisarticulationleftside;
public int aboveelbowamputeerightside;
public int aboveelbowamputeeleftside;
public int kneeamputeerightside;
public int kneeamputeeleftside;
public int elbowdisarticulationrightside;
public int elbowdisarticulationleftside;
public int belowelbowamputeerightside;
public int belowelbowamputeeleftside;
public int wristdisarticulationrightside;
public int wristdisarticulationleftside;
public int carpalbonesrightside;
public int carpalbonesleftside;
public String systemip;
public double finalres;
public String personcode;
public String loginid;


//variables that control the display mechanism of form controls
private boolean shoulder;
private boolean hip;
private boolean aboveelbow;
private boolean knee;
private boolean elbow;
private boolean belowelbow;
private boolean writst;
private boolean carpalbones;

//variables for printing the calculation
private StringBuffer leftsidecalculation;
private StringBuffer rightsidecalculation;
private StringBuffer finalpercentageofdisability;

    public int getShoulderdisarticulationrightside() 
    {
        return shoulderdisarticulationrightside;
    }

    public void setShoulderdisarticulationrightside(int shoulderdisarticulationrightside) 
    {
        this.shoulderdisarticulationrightside = shoulderdisarticulationrightside;
    }

    public int getShoulderdisarticulationleftside() 
    {
        return shoulderdisarticulationleftside;
    }

    public void setShoulderdisarticulationleftside(int shoulderdisarticulationleftside) 
    {
        this.shoulderdisarticulationleftside = shoulderdisarticulationleftside;
    }

    public int getHipdisarticulationrightside() 
    {
        return hipdisarticulationrightside;
    }

    public void setHipdisarticulationrightside(int hipdisarticulationrightside) 
    {
        this.hipdisarticulationrightside = hipdisarticulationrightside;
    }

    public int getHipdisarticulationleftside() 
    {
        return hipdisarticulationleftside;
    }

    public void setHipdisarticulationleftside(int hipdisarticulationleftside) 
    {
        this.hipdisarticulationleftside = hipdisarticulationleftside;
    }

    public int getAboveelbowamputeerightside() 
    {
        return aboveelbowamputeerightside;
    }

    public void setAboveelbowamputeerightside(int aboveelbowamputeerightside) 
    {
        this.aboveelbowamputeerightside = aboveelbowamputeerightside;
    }

    public int getAboveelbowamputeeleftside() 
    {
        return aboveelbowamputeeleftside;
    }

    public void setAboveelbowamputeeleftside(int aboveelbowamputeeleftside) 
    {
        this.aboveelbowamputeeleftside = aboveelbowamputeeleftside;
    }

    public int getKneeamputeerightside() 
    {
        return kneeamputeerightside;
    }

    public void setKneeamputeerightside(int kneeamputeerightside) 
    {
        this.kneeamputeerightside = kneeamputeerightside;
    }

    public int getKneeamputeeleftside() 
    {
        return kneeamputeeleftside;
    }

    public void setKneeamputeeleftside(int kneeamputeeleftside) 
    {
        this.kneeamputeeleftside = kneeamputeeleftside;
    }

    public int getElbowdisarticulationrightside() 
    {
        return elbowdisarticulationrightside;
    }

    public void setElbowdisarticulationrightside(int elbowdisarticulationrightside) 
    {
        this.elbowdisarticulationrightside = elbowdisarticulationrightside;
    }

    public int getElbowdisarticulationleftside() 
    {
        return elbowdisarticulationleftside;
    }

    public void setElbowdisarticulationleftside(int elbowdisarticulationleftside) 
    {
        this.elbowdisarticulationleftside = elbowdisarticulationleftside;
    }

    public int getBelowelbowamputeerightside() 
    {
        return belowelbowamputeerightside;
    }

    public void setBelowelbowamputeerightside(int belowelbowamputeerightside) 
    {
        this.belowelbowamputeerightside = belowelbowamputeerightside;
    }

    public int getBelowelbowamputeeleftside() 
    {
        return belowelbowamputeeleftside;
    }

    public void setBelowelbowamputeeleftside(int belowelbowamputeeleftside) 
    {
        this.belowelbowamputeeleftside = belowelbowamputeeleftside;
    }

    public int getWristdisarticulationrightside() 
    {
        return wristdisarticulationrightside;
    }

    public void setWristdisarticulationrightside(int wristdisarticulationrightside) 
    {
        this.wristdisarticulationrightside = wristdisarticulationrightside;
    }

    public int getWristdisarticulationleftside() 
    {
        return wristdisarticulationleftside;
    }

    public void setWristdisarticulationleftside(int wristdisarticulationleftside) 
    {
        this.wristdisarticulationleftside = wristdisarticulationleftside;
    }

    public int getCarpalbonesrightside() 
    {
        return carpalbonesrightside;
    }

    public void setCarpalbonesrightside(int carpalbonesrightside) 
    {
        this.carpalbonesrightside = carpalbonesrightside;
    }

    public int getCarpalbonesleftside() 
    {
        return carpalbonesleftside;
    }

    public void setCarpalbonesleftside(int carpalbonesleftside) 
    {
        this.carpalbonesleftside = carpalbonesleftside;
    }

 public String getSystemip() 
 {
        return systemip;
    }

    public void setSystemip(String systemip) 
    {
        this.systemip = systemip;
    }

    public double getFinalres() 
    {
        return finalres;
    }

    public void setFinalres(double finalres) 
    {
        this.finalres = finalres;
    }

    public String getPersoncode() 
    {
        return personcode;
    }

    public void setPersoncode(String personcode) 
    {
        this.personcode = personcode;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public boolean getShoulder() {
        return (this.shoulderdisarticulationrightside!=0||this.shoulderdisarticulationleftside!=0);
    }

    public void setShoulder(boolean shoulder) {
        this.shoulder = shoulder;
    }

    public boolean getHip() {
        return (this.hipdisarticulationleftside!=0||this.hipdisarticulationrightside!=0);
    }

    public void setHip(boolean hip) {
        this.hip = hip;
    }

    public boolean getAboveelbow() {
        return (this.aboveelbowamputeeleftside!=0||this.aboveelbowamputeerightside!=0);
    }

    public void setAboveelbow(boolean aboveelbow) {
        this.aboveelbow = aboveelbow;
    }

    public boolean getKnee() {
        return (this.kneeamputeeleftside!=0||this.kneeamputeerightside!=0);
    }

    public void setKnee(boolean knee) {
        this.knee = knee;
    }

    public boolean getElbow() {
        return (this.elbowdisarticulationleftside!=0||this.elbowdisarticulationrightside!=0);
    }

    public void setElbow(boolean elbow) {
        this.elbow = elbow;
    }

    public boolean getBelowelbow() {
        return (this.belowelbowamputeeleftside!=0||this.belowelbowamputeerightside!=0);
    }

    public void setBelowelbow(boolean belowelbow) {
        this.belowelbow = belowelbow;
    }

    public boolean getWritst() {
        return (this.wristdisarticulationleftside!=0||this.wristdisarticulationrightside!=0);
    }

    public void setWritst(boolean writst) {
        this.writst = writst;
    }

    public boolean getCarpalbones() {
        return (this.carpalbonesleftside!=0||this.carpalbonesrightside!=0);
    }

    public void setCarpalbones(boolean carpalbones) {
        this.carpalbones = carpalbones;
    }

    public StringBuffer getLeftsidecalculation() {
        return leftsidecalculation;
    }

    public void setLeftsidecalculation(StringBuffer leftsidecalculation) {
        this.leftsidecalculation = leftsidecalculation;
    }

    public StringBuffer getRightsidecalculation() {
        return rightsidecalculation;
    }

    public void setRightsidecalculation(StringBuffer rightsidecalculation) {
        this.rightsidecalculation = rightsidecalculation;
    }

    public StringBuffer getFinalpercentageofdisability() {
        return finalpercentageofdisability;
    }

    public void setFinalpercentageofdisability(StringBuffer finalpercentageofdisability) {
        this.finalpercentageofdisability = finalpercentageofdisability;
    }
}
