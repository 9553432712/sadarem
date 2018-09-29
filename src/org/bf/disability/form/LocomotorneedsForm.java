/*
 * LocomotorneedsForm.java
 
 */

package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * This class contains the fields, required to Locomotor disability needs
 * @version 1.0
 */
public class LocomotorneedsForm extends ActionForm{
    
    /** Creates a new instance of LocomotorneedsForm */
    public LocomotorneedsForm() {
    }
    private String interventionservices;
    private String physiotheraphy;
   
    private String occupationaltherapy;
    private String surgery = null;
    private String physiotherapy;
    private String occupationaltheraphy;
    private String selectwheelchair = null;
    private String selecttricycle = null;
    private String selectwalkingstick = null;
    private String selectcrutches = null;
    private String crutchestype = null;
    private String selectwalkingframe;
    private String aeroplanesplint;
    private String figure8splint;
    private String forearmsplint;
    private String handsplint;
    private String shoulderprosthesis;
    private String aboveelbowprosthesis;
    private String elbowdisarticulationprosthesis;
    private String belowelbowprosthesis;
    private String wristdisarticulationprosthesis;
    private String handprosthesis;
    private String cosmeticfingerprosthesis;
    private String hkafo;
    private String kafo;
    private String afo;
    private String kneeorthosis;
    private String dbsplint;
    private String modifiedshoe;
    private String serialcasting;
    private String hipprothesis;
    private String abovekneeprothesis;
    private String kneedisarticulation;
    private String belowkneeprothesis;
    private String symeprothesis;
    private String partialfoodprothesis;
    private String cervicalcollar;
    private String lsbrace;
    private String tlsobrace;
    private String loginid;
    private String systemip;
    private String personcode;
    
     private String feeding;
     private String bathing;
     private String brushing;
     private String combing;
     private String dressing;
     private String writing;
     private String drivingcycling;
     private String bedtransfer;
     private String anyotherinlocomotor=null;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
         this.physiotherapy=null;
         this.physiotheraphy=null;
         this. interventionservices=null;
    this. physiotheraphy=null;
    this. occupationaltherapy=null;
    this. surgery=null;
    this. physiotherapy=null;
    this. occupationaltheraphy=null;
    this. selectwheelchair=null;
    this. selecttricycle=null;
    this. selectwalkingstick=null;
    this. selectcrutches=null;
    this. crutchestype=null;
    this. selectwalkingframe=null;
    this. aeroplanesplint=null;
    this. figure8splint=null;
    this. forearmsplint=null;
    this. handsplint=null;
    this. shoulderprosthesis=null;
    this. aboveelbowprosthesis=null;
    this. elbowdisarticulationprosthesis=null;
    this. belowelbowprosthesis=null;
    this. wristdisarticulationprosthesis=null;
    this. handprosthesis=null;
    this. cosmeticfingerprosthesis=null;
    this. hkafo=null;
    this. kafo=null;
    this. afo=null;
    this. kneeorthosis=null;
    this. dbsplint=null;
    this. modifiedshoe=null;
    this. serialcasting=null;
    this. hipprothesis=null;
    this. abovekneeprothesis=null;
    this. kneedisarticulation=null;
    this. belowkneeprothesis=null;
    this. symeprothesis=null;
    this. partialfoodprothesis=null;
    this. cervicalcollar=null;
    this. lsbrace=null;
    this. tlsobrace=null;
    this. loginid=null;
    this. systemip=null;
    this. personcode=null;
    
     this. feeding=null;
     this. bathing=null;
     this. brushing=null;
     this. combing=null;
     this. dressing=null;
     this. writing=null;
     this. drivingcycling=null;
     this. bedtransfer=null;
     this. anyotherinlocomotor=null; 
         
    }
    
    


    public String getInterventionservices() {
        return interventionservices;
    }

    public void setInterventionservices(String interventionservices) {
        this.interventionservices = interventionservices;
    }

    public String getPhysiotheraphy() {
        return physiotheraphy;
    }

    public void setPhysiotheraphy(String physiotheraphy) {
        this.physiotheraphy = physiotheraphy;
    }

    public String getOccupationaltherapy() {
        return occupationaltherapy;
    }

    public void setOccupationaltherapy(String occupationaltherapy) {
        this.occupationaltherapy = occupationaltherapy;
    }

    public String getPhysiotherapy() {
        return physiotherapy;
    }

    public void setPhysiotherapy(String physiotherapy) {
        this.physiotherapy = physiotherapy;
    }

    public String getOccupationaltheraphy() {
        return occupationaltheraphy;
    }

    public void setOccupationaltheraphy(String occupationaltheraphy) {
        this.occupationaltheraphy = occupationaltheraphy;
    }

    public String getSelectwheelchair() {
        if(!"".equals(selectwheelchair)){
            return this.selectwheelchair;
        }else{
            return null;
        }
    }

    public void setSelectwheelchair(String selectwheelchair) {
        this.selectwheelchair = selectwheelchair;
    }

    public String getSelecttricycle() {
        if(!"".equals(selecttricycle)){
            return this.selecttricycle;
        }else{
            return null;
        }
    }

    public void setSelecttricycle(String selecttricycle) {
        this.selecttricycle = selecttricycle;
    }

    public String getSelectwalkingstick() {
        if(!"".equals(selectwalkingstick)){
            return this.selectwalkingstick;
        }else{
            return null;
        }
    }

    public void setSelectwalkingstick(String selectwalkingstick) {
        this.selectwalkingstick = selectwalkingstick;
    }

    public String getSelectcrutches() {
        if(!"".equals(selectcrutches)){
            return this.selectcrutches;
        }else{
            return null;
        }
    }

    public void setSelectcrutches(String selectcrutches) {
        this.selectcrutches = selectcrutches;
    }

    public String getCrutchestype() {
        if(!"".equals(crutchestype)){
            return this.crutchestype;
        }else{
            return null;
        }
    }

    public void setCrutchestype(String crutchestype) {
        this.crutchestype = crutchestype;
    }

    public String getSelectwalkingframe() {
        if(!"".equals(selectwalkingframe)){
            return this.selectwalkingframe;
        }else{
            return null;
        }
    }

    public void setSelectwalkingframe(String selectwalkingframe) {
        this.selectwalkingframe = selectwalkingframe;
    }

    public String getAeroplanesplint() {
        return aeroplanesplint;
    }

    public void setAeroplanesplint(String aeroplanesplint) {
        this.aeroplanesplint = aeroplanesplint;
    }

    public String getFigure8splint() {
        return figure8splint;
    }

    public void setFigure8splint(String figure8splint) {
        this.figure8splint = figure8splint;
    }

    public String getForearmsplint() {
        return forearmsplint;
    }

    public void setForearmsplint(String forearmsplint) {
        this.forearmsplint = forearmsplint;
    }

    public String getHandsplint() {
        return handsplint;
    }

    public void setHandsplint(String handsplint) {
        this.handsplint = handsplint;
    }

    public String getShoulderprosthesis() {
        return shoulderprosthesis;
    }

    public void setShoulderprosthesis(String shoulderprosthesis) {
        this.shoulderprosthesis = shoulderprosthesis;
    }

    public String getAboveelbowprosthesis() {
        return aboveelbowprosthesis;
    }

    public void setAboveelbowprosthesis(String aboveelbowprosthesis) {
        this.aboveelbowprosthesis = aboveelbowprosthesis;
    }

    public String getElbowdisarticulationprosthesis() {
        return elbowdisarticulationprosthesis;
    }

    public void setElbowdisarticulationprosthesis(String elbowdisarticulationprosthesis) {
        this.elbowdisarticulationprosthesis = elbowdisarticulationprosthesis;
    }

    public String getBelowelbowprosthesis() {
        return belowelbowprosthesis;
    }

    public void setBelowelbowprosthesis(String belowelbowprosthesis) {
        this.belowelbowprosthesis = belowelbowprosthesis;
    }

    public String getWristdisarticulationprosthesis() {
        return wristdisarticulationprosthesis;
    }

    public void setWristdisarticulationprosthesis(String wristdisarticulationprosthesis) {
        this.wristdisarticulationprosthesis = wristdisarticulationprosthesis;
    }

    public String getHandprosthesis() {
        return handprosthesis;
    }

    public void setHandprosthesis(String handprosthesis) {
        this.handprosthesis = handprosthesis;
    }

    public String getCosmeticfingerprosthesis() {
        return cosmeticfingerprosthesis;
    }

    public void setCosmeticfingerprosthesis(String cosmeticfingerprosthesis) {
        this.cosmeticfingerprosthesis = cosmeticfingerprosthesis;
    }

    public String getHkafo() {
        return hkafo;
    }

    public void setHkafo(String hkafo) {
        this.hkafo = hkafo;
    }

    public String getKafo() {
        return kafo;
    }

    public void setKafo(String kafo) {
        this.kafo = kafo;
    }

    public String getAfo() {
        return afo;
    }

    public void setAfo(String afo) {
        this.afo = afo;
    }

    public String getKneeorthosis() {
        return kneeorthosis;
    }

    public void setKneeorthosis(String kneeorthosis) {
        this.kneeorthosis = kneeorthosis;
    }

    public String getDbsplint() {
        return dbsplint;
    }

    public void setDbsplint(String dbsplint) {
        this.dbsplint = dbsplint;
    }

    public String getModifiedshoe() {
        return modifiedshoe;
    }

    public void setModifiedshoe(String modifiedshoe) {
        this.modifiedshoe = modifiedshoe;
    }

    public String getSerialcasting() {
        return serialcasting;
    }

    public void setSerialcasting(String serialcasting) {
        this.serialcasting = serialcasting;
    }

    public String getHipprothesis() {
        return hipprothesis;
    }

    public void setHipprothesis(String hipprothesis) {
        this.hipprothesis = hipprothesis;
    }

    public String getAbovekneeprothesis() {
        return abovekneeprothesis;
    }

    public void setAbovekneeprothesis(String abovekneeprothesis) {
        this.abovekneeprothesis = abovekneeprothesis;
    }

    public String getKneedisarticulation() {
        return kneedisarticulation;
    }

    public void setKneedisarticulation(String kneedisarticulation) {
        this.kneedisarticulation = kneedisarticulation;
    }

    public String getBelowkneeprothesis() {
        return belowkneeprothesis;
    }

    public void setBelowkneeprothesis(String belowkneeprothesis) {
        this.belowkneeprothesis = belowkneeprothesis;
    }

    public String getSymeprothesis() {
        return symeprothesis;
    }

    public void setSymeprothesis(String symeprothesis) {
        this.symeprothesis = symeprothesis;
    }

    public String getPartialfoodprothesis() {
        return partialfoodprothesis;
    }

    public void setPartialfoodprothesis(String partialfoodprothesis) {
        this.partialfoodprothesis = partialfoodprothesis;
    }

    public String getCervicalcollar() {
        return cervicalcollar;
    }

    public void setCervicalcollar(String cervicalcollar) {
        this.cervicalcollar = cervicalcollar;
    }

    public String getLsbrace() {
        return lsbrace;
    }

    public void setLsbrace(String lsbrace) {
        this.lsbrace = lsbrace;
    }

    public String getTlsobrace() {
        return tlsobrace;
    }

    public void setTlsobrace(String tlsobrace) {
        this.tlsobrace = tlsobrace;
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

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getSurgery() {
        if(!"".equals(surgery)){
            return this.surgery;
        }else{
            return null;
        }
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getFeeding() {
        return feeding;
    }

    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    public String getBathing() {
        return bathing;
    }

    public void setBathing(String bathing) {
        this.bathing = bathing;
    }

    public String getBrushing() {
        return brushing;
    }

    public void setBrushing(String brushing) {
        this.brushing = brushing;
    }

    public String getCombing() {
        return combing;
    }

    public void setCombing(String combing) {
        this.combing = combing;
    }

    public String getDressing() {
        return dressing;
    }

    public void setDressing(String dressing) {
        this.dressing = dressing;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getDrivingcycling() {
        return drivingcycling;
    }

    public void setDrivingcycling(String drivingcycling) {
        this.drivingcycling = drivingcycling;
    }

    public String getBedtransfer() {
        return bedtransfer;
    }

    public void setBedtransfer(String bedtransfer) {
        this.bedtransfer = bedtransfer;
    }

    public String getAnyotherinlocomotor() {
       if(!"".equals(anyotherinlocomotor)) {
           return this.anyotherinlocomotor;
       } else {
           return null;
       }
    }

    public void setAnyotherinlocomotor(String anyotherinlocomotor) {
        this.anyotherinlocomotor = anyotherinlocomotor;
    }
    
    
    
    
    
    
    
}
