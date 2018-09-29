
package org.bf.disability.form;


import org.apache.struts.validator.ValidatorForm;

/**
 * This class contains the fields, required to compute Mental Illness Disability
 * @version 1.0
 */

public class MentalIllnessActionForm extends ValidatorForm 
{
   private String  selfcare;
   private String  personalactivities;
   private String  communication;
   private String  work;
   private String  duration;
   private String globalscore;
   private String personcode;
 
  //Needs
  private String behaviormodification;
  private String surgery = null;
  private String psycotherapybehaviour;
  private String managerproperties;
  private String psychiatrichospital;
  private String anyotherneed = null;

   
 
  
  
    

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

   

    public String getBehaviormodification() {
        return behaviormodification;
    }

    public void setBehaviormodification(String behaviormodification) {
        this.behaviormodification = behaviormodification;
    }

    public String getSurgery() {
        if(!"".equals(surgery)) {
            return this.surgery;
        } else {
            return null;
        }
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

    public String getSelfcare() {
        return selfcare;
    }

    public void setSelfcare(String selfcare) {
        this.selfcare = selfcare;
    }

    public String getPersonalactivities() {
        return personalactivities;
    }

    public void setPersonalactivities(String personalactivities) {
        this.personalactivities = personalactivities;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGlobalscore() {
        return globalscore;
    }

    public void setGlobalscore(String globalscore) {
        this.globalscore = globalscore;
    }

    /**
     * @return the anyotherneed
     */
    public String getAnyotherneed() {
        if(!"".equals(anyotherneed)) {
            return  this.anyotherneed;
        } else {
            return null;
        }
    }

    /**
     * @param anyotherneed the anyotherneed to set
     */
    public void setAnyotherneed(String anyotherneed) {
        this.anyotherneed = anyotherneed;
    }
  
}
