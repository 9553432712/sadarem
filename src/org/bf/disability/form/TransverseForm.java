/*
 * TransverseForm.java
 *
 * Created on June 17, 2008, 9:10 AM
 */

package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


/**
 * This class contains the fields, required to compute Conginential deficiency
 * @version 1.0
 */
public class TransverseForm extends org.apache.struts.action.ActionForm 
{
 
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


/**
 *
 * If we don't select check boxes, they will return null values.
 *Inorder to avoid this situation, use reset() by specifying all the default values.
 *
 */

public void reset(ActionMapping mapping,HttpServletRequest request)
{
  
    this.shoulderdisarticulationrightside=0;
    this.shoulderdisarticulationleftside=0;
    this.hipdisarticulationrightside=0;
    this.hipdisarticulationleftside=0;
    this.aboveelbowamputeerightside=0;
    this.aboveelbowamputeeleftside=0;
    this.kneeamputeerightside=0;
    this.kneeamputeeleftside=0;
    this.elbowdisarticulationrightside=0;
    this.elbowdisarticulationleftside=0;
    this.belowelbowamputeerightside=0;
    this.belowelbowamputeeleftside=0;
    this.wristdisarticulationrightside=0;
    this.wristdisarticulationleftside=0;
    this.carpalbonesrightside=0;
    this.carpalbonesleftside=0;
    this.setShoulderdisarticulationrightside(0);
    this.setShoulderdisarticulationleftside(0);
    this.setHipdisarticulationleftside(0);
    this.setHipdisarticulationrightside(0);
    this.setAboveelbowamputeeleftside(0);
    this.setAboveelbowamputeerightside(0);
    this.setKneeamputeeleftside(0);
    this.setKneeamputeerightside(0);
    this.setElbowdisarticulationleftside(0);
    this.setElbowdisarticulationrightside(0);
    this.setBelowelbowamputeeleftside(0);
    this.setBelowelbowamputeerightside(0);
    this.setWristdisarticulationleftside(0);
    this.setWristdisarticulationrightside(0);
    this.setCarpalbonesleftside(0);
    this.setCarpalbonesrightside(0);
}

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
}
