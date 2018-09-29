/*
 * MentalIllnessDTO.java
 *
 * Created on June 21, 2008, 4:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.dto;

/**
 * This class contains the fields, required to compute Mental Illness Disability
 * @version 1.0
 */
public class MentalIllnessDTO
{
   private int  selfcare;
   private int  personalactivities;
   private int  communication;
   private int  work;
   private int  duration;
 private String personcode;
   private int  globalscore;
   private int  mentaldisability;
   private String systemip;
   private String loginid; 
   //Needs
   private String behaviormodification;
  private String surgery;
  private String psycotherapybehaviour;
  private String managerproperties;
  private String psychiatrichospital;
  private String anyotherneed;

    public String getAnyotherneed() {
        return anyotherneed;
    }

    public void setAnyotherneed(String anyotherneed) {
        this.anyotherneed = anyotherneed;
    }

   
   
   

    public int getSelfcare() {
        return selfcare;
    }

    public void setSelfcare(int selfcare) {
        this.selfcare = selfcare;
    }

    public int getPersonalactivities() {
        return personalactivities;
    }

    public void setPersonalactivities(int personalactivities) {
        this.personalactivities = personalactivities;
    }

    public int getCommunication() {
        return communication;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public int getWork() {
        return work;
    }

    public void setWork(int work) {
        this.work = work;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

  
    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    public int getMentaldisability() {
        return mentaldisability;
    }

    public void setMentaldisability(int mentaldisability) {
        this.mentaldisability = mentaldisability;
    }

    public int getGlobalscore() {
        return globalscore;
    }

    public void setGlobalscore(int globalscore) {
        this.globalscore = globalscore;
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

    public String getBehaviormodification() {
        return behaviormodification;
    }

    public void setBehaviormodification(String behaviormodification) {
        this.behaviormodification = behaviormodification;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getPsycotherapybehaviour() {
        return psycotherapybehaviour;
    }

    public void setPsycotherapybehaviour(String psycotherapybehaviour) {
        this.psycotherapybehaviour = psycotherapybehaviour;
    }

    public String getManagerproperties() {
        return managerproperties;
    }

    public void setManagerproperties(String managerproperties) {
        this.managerproperties = managerproperties;
    }

    public String getPsychiatrichospital() {
        return psychiatrichospital;
    }

    public void setPsychiatrichospital(String psychiatrichospital) {
        this.psychiatrichospital = psychiatrichospital;
    }

   
    
}
