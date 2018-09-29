/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;


/**
 *
 * @author 693461
 */
public class AadharCardMappingDTO extends org.apache.struts.action.ActionForm {
     private  String mode;
    private  String sadaremId;
    private  String aadharCardId;
    private  String aadharPersonCode;
    private  String prrofId;
    private  String prrofDocType;
    private  String validMsg;
    private  int  personCodeCount;
    private  String validSADAREMIDMsg;

    public int getPersonCodeCount() {
        return personCodeCount;
    }

    public void setPersonCodeCount(int personCodeCount) {
        this.personCodeCount = personCodeCount;
    }
    

    public String getValidSADAREMIDMsg() {
        return validSADAREMIDMsg;
    }

    public void setValidSADAREMIDMsg(String validSADAREMIDMsg) {
        this.validSADAREMIDMsg = validSADAREMIDMsg;
    }
    public String getAadharCardId() {
        return aadharCardId;
    }

    public String getValidMsg() {
        return validMsg;
    }

    public void setValidMsg(String validMsg) {
        this.validMsg = validMsg;
    }

    public void setAadharCardId(String aadharCardId) {
        this.aadharCardId = aadharCardId;
    }

    public String getAadharPersonCode() {
        return aadharPersonCode;
    }

    public void setAadharPersonCode(String aadharPersonCode) {
        this.aadharPersonCode = aadharPersonCode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPrrofDocType() {
        return prrofDocType;
    }

    public void setPrrofDocType(String prrofDocType) {
        this.prrofDocType = prrofDocType;
    }

    public String getPrrofId() {
        return prrofId;
    }

    public void setPrrofId(String prrofId) {
        this.prrofId = prrofId;
    }

    public String getSadaremId() {
        return sadaremId;
    }

    public void setSadaremId(String sadaremId) {
        this.sadaremId = sadaremId;
    }
    


}
