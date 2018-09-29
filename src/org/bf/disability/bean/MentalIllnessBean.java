/*
 * MentalIllnessBean.java
 *
 * Created on January 2, 2009, 2:50 PM
 *
 */

package org.bf.disability.bean;

/**
 *
 * @author Deviprasad_t
 */
public class MentalIllnessBean {
    
    /** Creates a new instance of MentalIllnessBean */
    public MentalIllnessBean() {
        
    }
    private int  selfcare;
   private int  personalactivities;
   private int  communication;
   private int  work;
   private int  duration;
   private int globaldisabilityscore;
   private int finalpercentage;
   private String globalscore;
   private int mentaldisability;
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

    public int getGlobaldisabilityscore() {
        return globaldisabilityscore;
    }

    public void setGlobaldisabilityscore(int globaldisabilityscore) {
        this.globaldisabilityscore = globaldisabilityscore;
    }

    public int getFinalpercentage() {
        return finalpercentage;
    }

    public void setFinalpercentage(int finalpercentage) {
        this.finalpercentage = finalpercentage;
    }

    public String getGlobalscore() {
        return globalscore;
    }

    public void setGlobalscore(String globalscore) {
        this.globalscore = globalscore;
    }

    public int getMentaldisability() {
        return mentaldisability;
    }

    public void setMentaldisability(int mentaldisability) {
        this.mentaldisability = mentaldisability;
    }

  

   
}
