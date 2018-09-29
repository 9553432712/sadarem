/*
 * PWDPhysicalImpairmentForm.java
 *
 * Created on July 29, 2008, 5:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.form;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author demo
 */
public class PWDPhysicalImpairmentForm  extends ActionForm
{
    
    private String name;
    private String age;
    private String gender;
    private String village;
    private String mandal;
    private String houseno;
    private String district;
    private String habitation;
    private String personcode;
    
    private String subtype;
    private String cause;
    private String annualincome;
    private String partinvolved;
    private String totalpersent;
    
  private boolean  congential;
  private boolean  hereditary;
  private boolean  birthinjury;
  private boolean  disease;
  private boolean  malnutrition;
  private boolean  accident;
  private boolean  othercause;
    
    
    //From part C 15.9 & 15.10
    
  //  Prescribed prosthesis
  private String prosthesis;
  private boolean  upperextremityprosthesis;
    private boolean shoulderprosthesis;
    private boolean aboveelbowprosthesis;
    private boolean elbowdisarticulationprosthesis;
    private boolean belowelbowprosthesis;
    private boolean wristdisarticulationprosthesis;
    private boolean handprosthesis;
    private boolean cosmeticfingerprosthesis;
   private boolean lowerextremityprosthesis;
   private boolean hipdisarticulationprosthesis;
   private boolean abovekneeprosthesis;
   private boolean kneedisarticulationprosthesis;
   private boolean belowkneeprosthesis;
   private boolean simesprosthesis;
  private boolean  partialfootprosthesis;
    
  //Prescribed orthosis  15.5 & 15.7 & 15.8
 private String orthosis;
 private boolean orthosisupperextremity;
 private boolean aeroplanesplint;
 private boolean figure8splint;
 private boolean forearmsplint;
 private boolean handsplint;
  
 private boolean orthosislowerextremity;
 private boolean hkafo;
 private boolean kafo;
 private boolean afo;
 private boolean kneeorthosis;
 private boolean dbsplint;
 private boolean modifiedshoe;
 private boolean serialcasting;
  
 private boolean spinalorthosis;
 private boolean cervicalcollar;
 private boolean lsbrace;
 private boolean tlsobrace;
    

 //15.1 to 15.5
 private String selectwheelchair;   
 private String selecttricycle;
 private String selectwalkingstick;
 private String selectcrutches;
 private String selectwalkingframe;
    
  // 15.11
 private String adlequipment;
private boolean feeding;
private boolean toiletingandbathing;
private boolean brushing;
private boolean combing;
private boolean dressing;
private boolean writing;
private boolean drivingcycling;
private boolean bedtransfer;
private String anyotherlocomotor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

  

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getPartinvolved() {
        return partinvolved;
    }

    public void setPartinvolved(String partinvolved) {
        this.partinvolved = partinvolved;
    }

    public String getTotalpersent() {
        return totalpersent;
    }

    public void setTotalpersent(String totalpersent) {
        this.totalpersent = totalpersent;
    }

    public boolean isUpperextremityprosthesis() {
        return upperextremityprosthesis;
    }

    public void setUpperextremityprosthesis(boolean upperextremityprosthesis) {
        this.upperextremityprosthesis = upperextremityprosthesis;
    }

    public boolean isShoulderprosthesis() {
        return shoulderprosthesis;
    }

    public void setShoulderprosthesis(boolean shoulderprosthesis) {
        this.shoulderprosthesis = shoulderprosthesis;
    }

    public boolean isAboveelbowprosthesis() {
        return aboveelbowprosthesis;
    }

    public void setAboveelbowprosthesis(boolean aboveelbowprosthesis) {
        this.aboveelbowprosthesis = aboveelbowprosthesis;
    }

    public boolean isElbowdisarticulationprosthesis() {
        return elbowdisarticulationprosthesis;
    }

    public void setElbowdisarticulationprosthesis(boolean elbowdisarticulationprosthesis) {
        this.elbowdisarticulationprosthesis = elbowdisarticulationprosthesis;
    }

    public boolean isBelowelbowprosthesis() {
        return belowelbowprosthesis;
    }

    public void setBelowelbowprosthesis(boolean belowelbowprosthesis) {
        this.belowelbowprosthesis = belowelbowprosthesis;
    }

    public boolean isWristdisarticulationprosthesis() {
        return wristdisarticulationprosthesis;
    }

    public void setWristdisarticulationprosthesis(boolean wristdisarticulationprosthesis) {
        this.wristdisarticulationprosthesis = wristdisarticulationprosthesis;
    }

    public boolean isHandprosthesis() {
        return handprosthesis;
    }

    public void setHandprosthesis(boolean handprosthesis) {
        this.handprosthesis = handprosthesis;
    }

    public boolean isCosmeticfingerprosthesis() {
        return cosmeticfingerprosthesis;
    }

    public void setCosmeticfingerprosthesis(boolean cosmeticfingerprosthesis) {
        this.cosmeticfingerprosthesis = cosmeticfingerprosthesis;
    }

    public boolean isLowerextremityprosthesis() {
        return lowerextremityprosthesis;
    }

    public void setLowerextremityprosthesis(boolean lowerextremityprosthesis) {
        this.lowerextremityprosthesis = lowerextremityprosthesis;
    }

    public boolean isHipdisarticulationprosthesis() {
        return hipdisarticulationprosthesis;
    }

    public void setHipdisarticulationprosthesis(boolean hipdisarticulationprosthesis) {
        this.hipdisarticulationprosthesis = hipdisarticulationprosthesis;
    }

    public boolean isAbovekneeprosthesis() {
        return abovekneeprosthesis;
    }

    public void setAbovekneeprosthesis(boolean abovekneeprosthesis) {
        this.abovekneeprosthesis = abovekneeprosthesis;
    }

    public boolean isKneedisarticulationprosthesis() {
        return kneedisarticulationprosthesis;
    }

    public void setKneedisarticulationprosthesis(boolean kneedisarticulationprosthesis) {
        this.kneedisarticulationprosthesis = kneedisarticulationprosthesis;
    }

    public boolean isBelowkneeprosthesis() {
        return belowkneeprosthesis;
    }

    public void setBelowkneeprosthesis(boolean belowkneeprosthesis) {
        this.belowkneeprosthesis = belowkneeprosthesis;
    }

    public boolean isSimesprosthesis() {
        return simesprosthesis;
    }

    public void setSimesprosthesis(boolean simesprosthesis) {
        this.simesprosthesis = simesprosthesis;
    }

    public boolean isPartialfootprosthesis() {
        return partialfootprosthesis;
    }

    public void setPartialfootprosthesis(boolean partialfootprosthesis) {
        this.partialfootprosthesis = partialfootprosthesis;
    }

    public boolean isOrthosisupperextremity() {
        return orthosisupperextremity;
    }

    public void setOrthosisupperextremity(boolean orthosisupperextremity) {
        this.orthosisupperextremity = orthosisupperextremity;
    }

    public boolean isAeroplanesplint() {
        return aeroplanesplint;
    }

    public void setAeroplanesplint(boolean aeroplanesplint) {
        this.aeroplanesplint = aeroplanesplint;
    }

    public boolean isFigure8splint() {
        return figure8splint;
    }

    public void setFigure8splint(boolean figure8splint) {
        this.figure8splint = figure8splint;
    }

    public boolean isForearmsplint() {
        return forearmsplint;
    }

    public void setForearmsplint(boolean forearmsplint) {
        this.forearmsplint = forearmsplint;
    }

    public boolean isHandsplint() {
        return handsplint;
    }

    public void setHandsplint(boolean handsplint) {
        this.handsplint = handsplint;
    }

    public boolean isOrthosislowerextremity() {
        return orthosislowerextremity;
    }

    public void setOrthosislowerextremity(boolean orthosislowerextremity) {
        this.orthosislowerextremity = orthosislowerextremity;
    }

    public boolean isHkafo() {
        return hkafo;
    }

    public void setHkafo(boolean hkafo) {
        this.hkafo = hkafo;
    }

    public boolean isKafo() {
        return kafo;
    }

    public void setKafo(boolean kafo) {
        this.kafo = kafo;
    }

    public boolean isAfo() {
        return afo;
    }

    public void setAfo(boolean afo) {
        this.afo = afo;
    }

    public boolean isKneeorthosis() {
        return kneeorthosis;
    }

    public void setKneeorthosis(boolean kneeorthosis) {
        this.kneeorthosis = kneeorthosis;
    }

    public boolean isDbsplint() {
        return dbsplint;
    }

    public void setDbsplint(boolean dbsplint) {
        this.dbsplint = dbsplint;
    }

    public boolean isModifiedshoe() {
        return modifiedshoe;
    }

    public void setModifiedshoe(boolean modifiedshoe) {
        this.modifiedshoe = modifiedshoe;
    }

    public boolean isSerialcasting() {
        return serialcasting;
    }

    public void setSerialcasting(boolean serialcasting) {
        this.serialcasting = serialcasting;
    }

    public boolean isSpinalorthosis() {
        return spinalorthosis;
    }

    public void setSpinalorthosis(boolean spinalorthosis) {
        this.spinalorthosis = spinalorthosis;
    }

    public boolean isCervicalcollar() {
        return cervicalcollar;
    }

    public void setCervicalcollar(boolean cervicalcollar) {
        this.cervicalcollar = cervicalcollar;
    }

    public boolean isLsbrace() {
        return lsbrace;
    }

    public void setLsbrace(boolean lsbrace) {
        this.lsbrace = lsbrace;
    }

    public boolean isTlsobrace() {
        return tlsobrace;
    }

    public void setTlsobrace(boolean tlsobrace) {
        this.tlsobrace = tlsobrace;
    }

    public String getSelectwheelchair() {
        return selectwheelchair;
    }

    public void setSelectwheelchair(String selectwheelchair) {
        this.selectwheelchair = selectwheelchair;
    }

    public String getSelecttricycle() {
        return selecttricycle;
    }

    public void setSelecttricycle(String selecttricycle) {
        this.selecttricycle = selecttricycle;
    }

    public String getSelectwalkingstick() {
        return selectwalkingstick;
    }

    public void setSelectwalkingstick(String selectwalkingstick) {
        this.selectwalkingstick = selectwalkingstick;
    }

    public String getSelectcrutches() {
        return selectcrutches;
    }

    public void setSelectcrutches(String selectcrutches) {
        this.selectcrutches = selectcrutches;
    }

    public String getSelectwalkingframe() {
        return selectwalkingframe;
    }

    public void setSelectwalkingframe(String selectwalkingframe) {
        this.selectwalkingframe = selectwalkingframe;
    }

    public boolean isFeeding() {
        return feeding;
    }

    public void setFeeding(boolean feeding) {
        this.feeding = feeding;
    }

    public boolean isToiletingandbathing() {
        return toiletingandbathing;
    }

    public void setToiletingandbathing(boolean toiletingandbathing) {
        this.toiletingandbathing = toiletingandbathing;
    }

    public boolean isBrushing() {
        return brushing;
    }

    public void setBrushing(boolean brushing) {
        this.brushing = brushing;
    }

    public boolean isCombing() {
        return combing;
    }

    public void setCombing(boolean combing) {
        this.combing = combing;
    }

    public boolean isDressing() {
        return dressing;
    }

    public void setDressing(boolean dressing) {
        this.dressing = dressing;
    }

    public boolean isWriting() {
        return writing;
    }

    public void setWriting(boolean writing) {
        this.writing = writing;
    }

    public boolean isDrivingcycling() {
        return drivingcycling;
    }

    public void setDrivingcycling(boolean drivingcycling) {
        this.drivingcycling = drivingcycling;
    }

    public boolean isBedtransfer() {
        return bedtransfer;
    }

    public void setBedtransfer(boolean bedtransfer) {
        this.bedtransfer = bedtransfer;
    }

    public String getAnyotherlocomotor() {
        return anyotherlocomotor;
    }

    public void setAnyotherlocomotor(String anyotherlocomotor) {
        this.anyotherlocomotor = anyotherlocomotor;
    }

    public String getAnnualincome() {
        return annualincome;
    }

    public void setAnnualincome(String annualincome) {
        this.annualincome = annualincome;
    }

    public boolean isCongential() {
        return congential;
    }

    public void setCongential(boolean congential) {
        this.congential = congential;
    }

    public boolean isHereditary() {
        return hereditary;
    }

    public void setHereditary(boolean hereditary) {
        this.hereditary = hereditary;
    }

    public boolean isBirthinjury() {
        return birthinjury;
    }

    public void setBirthinjury(boolean birthinjury) {
        this.birthinjury = birthinjury;
    }

    public boolean isDisease() {
        return disease;
    }

    public void setDisease(boolean disease) {
        this.disease = disease;
    }

    public boolean isMalnutrition() {
        return malnutrition;
    }

    public void setMalnutrition(boolean malnutrition) {
        this.malnutrition = malnutrition;
    }

    public boolean isAccident() {
        return accident;
    }

    public void setAccident(boolean accident) {
        this.accident = accident;
    }

    public boolean isOthercause() {
        return othercause;
    }

    public void setOthercause(boolean othercause) {
        this.othercause = othercause;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

   

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHabitation() {
        return habitation;
    }

    public void setHabitation(String habitation) {
        this.habitation = habitation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProsthesis() {
        return prosthesis;
    }

    public void setProsthesis(String prosthesis) {
        this.prosthesis = prosthesis;
    }

    public String getOrthosis() {
        return orthosis;
    }

    public void setOrthosis(String orthosis) {
        this.orthosis = orthosis;
    }

    public String getAdlequipment() {
        return adlequipment;
    }

    public void setAdlequipment(String adlequipment) {
        this.adlequipment = adlequipment;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

}       
    
