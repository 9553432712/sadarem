/*
 * HearingImpairmentBean.java
 *
 * Created on January 2, 2009, 2:49 PM
 *
 */

package org.bf.disability.bean;

/**
 *
 * @author Deviprasad_t
 */
public class HearingImpairmentBean {
    
    /** Creates a new instance of HearingImpairmentBean */
    public HearingImpairmentBean() {
    }
    public String hearing_dblr;
    public double totalpercent;
    public String personcode;
    // new fields for airconduction
    public String rightear250;
    public String rightear500;
    public String rightear1000;
    public String rightear2000;
    public String rightear4000;
    public String rightear8000;
    public String leftear250;
    public String leftear500;
    public String leftear1000;
    public String leftear2000;
    public String leftear4000;
    public String leftear8000;
    public String righteardblevel;
    public String lefteardblevel;
    
    
    //new variables for calculation in H.I
    
    public StringBuffer monauralvaluebetterear;
     public StringBuffer monauralvaluepoorerear;
     public StringBuffer binauralvaluepoorerear;
    
    
    
    
    
    
    
    //  fields for speech audiometry
    
    public String speechaudiometryrightear_pta;
    
    public String speechaudiometryleftear_pta;
    
    public String getHearing_dblr() {
        return hearing_dblr;
    }
    
    public void setHearing_dblr(String hearing_dblr) {
        this.hearing_dblr = hearing_dblr;
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
    
    public String getRightear250() {
        return rightear250;
    }
    
    public void setRightear250(String rightear250) {
        this.rightear250 = rightear250;
    }
    
    public String getRightear500() {
        return rightear500;
    }
    
    public void setRightear500(String rightear500) {
        this.rightear500 = rightear500;
    }
    
    public String getRightear1000() {
        return rightear1000;
    }
    
    public void setRightear1000(String rightear1000) {
        this.rightear1000 = rightear1000;
    }
    
    public String getRightear2000() {
        return rightear2000;
    }
    
    public void setRightear2000(String rightear2000) {
        this.rightear2000 = rightear2000;
    }
    
    public String getRightear4000() {
        return rightear4000;
    }
    
    public void setRightear4000(String rightear4000) {
        this.rightear4000 = rightear4000;
    }
    
    public String getRightear8000() {
        return rightear8000;
    }
    
    public void setRightear8000(String rightear8000) {
        this.rightear8000 = rightear8000;
    }
    
    public String getLeftear250() {
        return leftear250;
    }
    
    public void setLeftear250(String leftear250) {
        this.leftear250 = leftear250;
    }
    
    public String getLeftear500() {
        return leftear500;
    }
    
    public void setLeftear500(String leftear500) {
        this.leftear500 = leftear500;
    }
    
    public String getLeftear1000() {
        return leftear1000;
    }
    
    public void setLeftear1000(String leftear1000) {
        this.leftear1000 = leftear1000;
    }
    
    public String getLeftear2000() {
        return leftear2000;
    }
    
    public void setLeftear2000(String leftear2000) {
        this.leftear2000 = leftear2000;
    }
    
    public String getLeftear4000() {
        return leftear4000;
    }
    
    public void setLeftear4000(String leftear4000) {
        this.leftear4000 = leftear4000;
    }
    
    public String getLeftear8000() {
        return leftear8000;
    }
    
    public void setLeftear8000(String leftear8000) {
        this.leftear8000 = leftear8000;
    }
    
    public String getRighteardblevel() {
        return righteardblevel;
    }
    
    public void setRighteardblevel(String righteardblevel) {
        this.righteardblevel = righteardblevel;
    }
    
    public String getLefteardblevel() {
        return lefteardblevel;
    }
    
    public void setLefteardblevel(String lefteardblevel) {
        this.lefteardblevel = lefteardblevel;
    }
    
    public String getSpeechaudiometryrightear_pta() {
        return speechaudiometryrightear_pta;
    }
    
    public void setSpeechaudiometryrightear_pta(String speechaudiometryrightear_pta) {
        this.speechaudiometryrightear_pta = speechaudiometryrightear_pta;
    }
    
    public String getSpeechaudiometryleftear_pta() {
        return speechaudiometryleftear_pta;
    }
    
    public void setSpeechaudiometryleftear_pta(String speechaudiometryleftear_pta) {
        this.speechaudiometryleftear_pta = speechaudiometryleftear_pta;
    }

    public StringBuffer getMonauralvaluebetterear() {
        return monauralvaluebetterear;
    }

    public void setMonauralvaluebetterear(StringBuffer monauralvaluebetterear) {
        this.monauralvaluebetterear = monauralvaluebetterear;
    }

    public StringBuffer getMonauralvaluepoorerear() {
        return monauralvaluepoorerear;
    }

    public void setMonauralvaluepoorerear(StringBuffer monauralvaluepoorerear) {
        this.monauralvaluepoorerear = monauralvaluepoorerear;
    }

    public StringBuffer getBinauralvaluepoorerear() {
        return binauralvaluepoorerear;
    }

    public void setBinauralvaluepoorerear(StringBuffer binauralvaluepoorerear) {
        this.binauralvaluepoorerear = binauralvaluepoorerear;
    }

   
    
    
}
