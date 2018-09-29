

package org.bf.disability.form;

import org.apache.struts.validator.ValidatorForm;


/**
 * This class contains the fields, required to compute CardioPulmonary
 * @version 1.0
 */
public class CardioPulmonaryActionForm extends ValidatorForm
{
    
  private String cardiopulmonary=null;
  private String visualimpairment=null;
  private String personcode;
  private String whitecane;
  private String brailleequipments;
  private String arithmeticframes;
  private String lowvisionaids;
  private String speechsynthesizer;
  private String brailleshorthandmachines;
  private String talkingwatchcalculator;
  private String anyadlequipmentes;
  private String anyotherspecify = null;
  private String surgery = null;
 
 
 
 
 
 
    public String getCardiopulmonary() {
        return cardiopulmonary;
    }

    public void setCardiopulmonary(String cardiopulmonary) {
        this.cardiopulmonary = cardiopulmonary;
    }

    public String getVisualimpairment() {
        return visualimpairment;
    }

    public void setVisualimpairment(String visualimpairment) {
        this.visualimpairment = visualimpairment;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

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
        if(!"".equals(anyotherspecify)) {
            return this.anyotherspecify;
        } else {
            return null;
        }
    }

    public void setAnyotherspecify(String anyotherspecify) {
        this.anyotherspecify = anyotherspecify;
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

  
  
}
