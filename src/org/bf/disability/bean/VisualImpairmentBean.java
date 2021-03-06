/*
 * VisualImpairmentBean.java
 *
 * Created on January 2, 2009, 2:49 PM
 *
 */

package org.bf.disability.bean;

/**
 *
 * @author Deviprasad_t
 */
public class VisualImpairmentBean {
    
    /** Creates a new instance of VisualImpairmentBean */
    public VisualImpairmentBean() {
    }
    
    private String whitecane;
     private String brailleequipments;
     private String arithmeticframes;
     private String lowvisionaids;
     private String speechsynthesizer;
     private String brailleshorthandmachines;
     private String talkingwatchcalculator;
     private String anyadlequipmentes;
     private String anyotherspecify;
     private String surgery;
     private String visualimpairment=null;
 private boolean category;
    public String getWhitecane() {
        return whitecane;
    }

    public void setWhitecane(String whitecane) {
        this.whitecane = whitecane;
    }

    public String getBrailleequipments() {
        return brailleequipments;
    }

    public void setBrailleequipments(String brailleequipments) {
        this.brailleequipments = brailleequipments;
    }

    public String getArithmeticframes() {
        return arithmeticframes;
    }

    public void setArithmeticframes(String arithmeticframes) {
        this.arithmeticframes = arithmeticframes;
    }

    public String getLowvisionaids() {
        return lowvisionaids;
    }

    public void setLowvisionaids(String lowvisionaids) {
        this.lowvisionaids = lowvisionaids;
    }

    public String getSpeechsynthesizer() {
        return speechsynthesizer;
    }

    public void setSpeechsynthesizer(String speechsynthesizer) {
        this.speechsynthesizer = speechsynthesizer;
    }

    public String getBrailleshorthandmachines() {
        return brailleshorthandmachines;
    }

    public void setBrailleshorthandmachines(String brailleshorthandmachines) {
        this.brailleshorthandmachines = brailleshorthandmachines;
    }

    public String getTalkingwatchcalculator() {
        return talkingwatchcalculator;
    }

    public void setTalkingwatchcalculator(String talkingwatchcalculator) {
        this.talkingwatchcalculator = talkingwatchcalculator;
    }

    public String getAnyadlequipmentes() {
        return anyadlequipmentes;
    }

    public void setAnyadlequipmentes(String anyadlequipmentes) {
        this.anyadlequipmentes = anyadlequipmentes;
    }

    public String getAnyotherspecify() {
        return anyotherspecify;
    }

    public void setAnyotherspecify(String anyotherspecify) {
        this.anyotherspecify = anyotherspecify;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getVisualimpairment() {
        return visualimpairment;
    }

    public void setVisualimpairment(String visualimpairment) {
        this.visualimpairment = visualimpairment;
    }

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }
}
