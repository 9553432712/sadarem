

/*
 * HearingImpairmentActionForm.java
 *
 */

package org.bf.disability.form;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionMapping;
/**
 * This class contains the fields, required to compute Hearing Impariment
 * @author Sunima
 * @version 1.0
 */


public class HearingImpairmentActionForm extends ActionForm {
    
    /**
     * Creates a new instance of HearingImpairmentActionForm
     */
    //for getting graph when giving personcode
    private String pensioncode=null;

    public String getPensioncode() {
        return pensioncode;
    }

    public void setPensioncode(String pensioncode) {
        this.pensioncode = pensioncode;
    }
    private String hearing_dblr;
    private double totalpercent;
    //Needs
    private String speechtheraphy;
    private String surgery = null;
    private String languagedevelopment;
    private String hearingaidselect = null;
    private String hearingaidtype = null;
    private String implantablehearingaid;
    private String puretoneaudiometric;
    private String pediatricaudiometry;
    private String selectealarmingdevices;
    private String speechtrainer;
    private String anyadlequipment;
    private String anyotherhearingimpairement = null;
    private String speechtherapy;
    private String cochlearimplantation;
    private String personcode;
    private String loginid;
    private String systemip;
    // new fields for airconduction
    private String rightear250;
    private String rightear500;
    private String rightear1000;
    private String rightear2000;
    private String rightear4000;
    private String rightear8000;
    private String leftear250;
    private String leftear500;
    private String leftear1000;
    private String leftear2000;
    private String leftear4000;
    private String leftear8000;
    
    private String righteardblevel;
    private String lefteardblevel;
    
 
    //  fields for speech audiometry
    
    private String speechaudiometryrightear_pta;
 
    private String speechaudiometryleftear_pta;
 
    
    
    
    
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.setHearing_dblr("");
        
    }
    
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
    
    public String getSpeechtheraphy() {
        return speechtheraphy;
    }
    
    public void setSpeechtheraphy(String speechtheraphy) {
        this.speechtheraphy = speechtheraphy;
    }
    
    public String getSurgery() {
        if(!"".equals(surgery)) {
            return this.surgery;
        } else {
            return  null;
        }
    }
    
    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }
    
    public String getLanguagedevelopment() {
        return languagedevelopment;
    }
    
    public void setLanguagedevelopment(String languagedevelopment) {
        this.languagedevelopment = languagedevelopment;
    }
    
    public String getHearingaidselect() {
        if(!"".equals(hearingaidselect )) {
            return this.hearingaidselect;
        } else {
            return null;
        }
    }
    
    public void setHearingaidselect(String hearingaidselect) {
        this.hearingaidselect = hearingaidselect;
    }
    
    public String getHearingaidtype() {
        if(!"".equals(hearingaidtype)) {
            return this.hearingaidtype;
        } else {
            return  null;
        }
    }
    
    public void setHearingaidtype(String hearingaidtype) {
        this.hearingaidtype = hearingaidtype;
    }
    
    
    
    public String getPuretoneaudiometric() {
        return puretoneaudiometric;
    }
    
    public void setPuretoneaudiometric(String puretoneaudiometric) {
        this.puretoneaudiometric = puretoneaudiometric;
    }
    
    public String getPediatricaudiometry() {
        return pediatricaudiometry;
    }
    
    public void setPediatricaudiometry(String pediatricaudiometry) {
        this.pediatricaudiometry = pediatricaudiometry;
    }
    
    public String getSelectealarmingdevices() {
        return selectealarmingdevices;
    }
    
    public void setSelectealarmingdevices(String selectealarmingdevices) {
        this.selectealarmingdevices = selectealarmingdevices;
    }
    
    public String getSpeechtrainer() {
        return speechtrainer;
    }
    
    public void setSpeechtrainer(String speechtrainer) {
        this.speechtrainer = speechtrainer;
    }
    
    public String getAnyadlequipment() {
        return anyadlequipment;
    }
    
    public void setAnyadlequipment(String anyadlequipment) {
        this.anyadlequipment = anyadlequipment;
    }
    
    public String getAnyotherhearingimpairement() {
        if(!"".equals(anyotherhearingimpairement)) {
            return this.anyotherhearingimpairement;
        } else {
            return  null;
        }
    }
    
    public void setAnyotherhearingimpairement(String anyotherhearingimpairement) {
        this.anyotherhearingimpairement = anyotherhearingimpairement;
    }
    
    public String getSpeechtherapy() {
        return speechtherapy;
    }
    
    public void setSpeechtherapy(String speechtherapy) {
        this.speechtherapy = speechtherapy;
    }
    
    public String getImplantablehearingaid() {
        return implantablehearingaid;
    }
    
    public void setImplantablehearingaid(String implantablehearingaid) {
        this.implantablehearingaid = implantablehearingaid;
    }
    
    public String getCochlearimplantation() {
        return cochlearimplantation;
    }
    
    public void setCochlearimplantation(String cochlearimplantation) {
        this.cochlearimplantation = cochlearimplantation;
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
    
  
    
    
    
}

