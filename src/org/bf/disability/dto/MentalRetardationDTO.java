
/*
 * MentalRetardationDTO.java
 *
 * Created on July 16, 2008, 12:27 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dto;

/**
 * This class contains the fields, required to compute Mental Retardation
 * @author Sunima
 * @version 1.0
 */

public class MentalRetardationDTO {
    
    private String mentalage;
    private String chronologicalage;
    private String systemip;
    private double totalpercent;
    private  String personcode;
    private String loginid;
    
    private String speechtheraphy;
    private String behaviormodification;
    private String speechtherapy;
    private String languagedevelopment;
    private String psycotherapybehaviour;
    private String legalguardian;
    private String mentalimpairment;
    private String learningmaterials;
    private String specialsoftware;
    private String toys;
    private String anyothermentalretardation;
    private String surgery;
    private String iq;
   /* New Fields are added in Mentalretardation  */
    private String sensoryintegrationcognitive;
    private String physiotherapy;
    private String occupationaltherapy;
    private String cognitivebehaviourtherapy;
    private String parientfamilyintervention;

    
    public String getMentalage() {
        return mentalage;
    }
    
    public void setMentalage(String mentalage) {
        this.mentalage = mentalage;
    }
    
    public String getChronologicalage() {
        return chronologicalage;
    }
    
    public void setChronologicalage(String chronologicalage) {
        this.chronologicalage = chronologicalage;
    }
    
    public String getSystemip() {
        return systemip;
    }
    
    public void setSystemip(String systemip) {
        this.systemip = systemip;
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
    
    public String getSpeechtheraphy() {
        return speechtheraphy;
    }
    
    public void setSpeechtheraphy(String speechtheraphy) {
        this.speechtheraphy = speechtheraphy;
    }
    
    public String getBehaviormodification() {
        return behaviormodification;
    }
    
    public void setBehaviormodification(String behaviormodification) {
        this.behaviormodification = behaviormodification;
    }
    
    public String getSpeechtherapy() {
        return speechtherapy;
    }
    
    public void setSpeechtherapy(String speechtherapy) {
        this.speechtherapy = speechtherapy;
    }
    
    public String getLanguagedevelopment() {
        return languagedevelopment;
    }
    
    public void setLanguagedevelopment(String languagedevelopment) {
        this.languagedevelopment = languagedevelopment;
    }
    
    public String getPsycotherapybehaviour() {
        return psycotherapybehaviour;
    }
    
    public void setPsycotherapybehaviour(String psycotherapybehaviour) {
        this.psycotherapybehaviour = psycotherapybehaviour;
    }
    
    public String getLegalguardian() {
        return legalguardian;
    }
    
    public void setLegalguardian(String legalguardian) {
        this.legalguardian = legalguardian;
    }
    
    public String getMentalimpairment() {
        return mentalimpairment;
    }
    
    public void setMentalimpairment(String mentalimpairment) {
        this.mentalimpairment = mentalimpairment;
    }
    
    public String getLearningmaterials() {
        return learningmaterials;
    }
    
    public void setLearningmaterials(String learningmaterials) {
        this.learningmaterials = learningmaterials;
    }
    
    public String getSpecialsoftware() {
        return specialsoftware;
    }
    
    public void setSpecialsoftware(String specialsoftware) {
        this.specialsoftware = specialsoftware;
    }
    
    
    
    public String getAnyothermentalretardation() {
        return anyothermentalretardation;
    }
    
    public void setAnyothermentalretardation(String anyothermentalretardation) {
        this.anyothermentalretardation = anyothermentalretardation;
    }
    
    public String getSurgery() {
        return surgery;
    }
    
    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }
    
    public String getToys() {
        return toys;
    }
    
    public void setToys(String toys) {
        this.toys = toys;
    }

    public String getIq() {
        return iq;
    }

    public void setIq(String iq) {
        this.iq = iq;
    }

    /**
     * @return the sensoryintegrationcognitive
     */
    public String getSensoryintegrationcognitive() {
        return sensoryintegrationcognitive;
    }

    /**
     * @param sensoryintegrationcognitive the sensoryintegrationcognitive to set
     */
    public void setSensoryintegrationcognitive(String sensoryintegrationcognitive) {
        this.sensoryintegrationcognitive = sensoryintegrationcognitive;
    }

    /**
     * @return the physiotherapy
     */
    public String getPhysiotherapy() {
        return physiotherapy;
    }

    /**
     * @param physiotherapy the physiotherapy to set
     */
    public void setPhysiotherapy(String physiotherapy) {
        this.physiotherapy = physiotherapy;
    }

    /**
     * @return the occupationaltherapy
     */
    public String getOccupationaltherapy() {
        return occupationaltherapy;
    }

    /**
     * @param occupationaltherapy the occupationaltherapy to set
     */
    public void setOccupationaltherapy(String occupationaltherapy) {
        this.occupationaltherapy = occupationaltherapy;
    }

    /**
     * @return the cognitivebehaviourtherapy
     */
    public String getCognitivebehaviourtherapy() {
        return cognitivebehaviourtherapy;
    }

    /**
     * @param cognitivebehaviourtherapy the cognitivebehaviourtherapy to set
     */
    public void setCognitivebehaviourtherapy(String cognitivebehaviourtherapy) {
        this.cognitivebehaviourtherapy = cognitivebehaviourtherapy;
    }

    /**
     * @return the parientfamilyintervention
     */
    public String getParientfamilyintervention() {
        return parientfamilyintervention;
    }

    /**
     * @param parientfamilyintervention the parientfamilyintervention to set
     */
    public void setParientfamilyintervention(String parientfamilyintervention) {
        this.parientfamilyintervention = parientfamilyintervention;
    }
}
