/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

/**
 *
 * @author 842412
 */
public class AcknowledgementPrintForm extends org.apache.struts.action.ActionForm{
    private String mode;
    private String sadaremId;
    private String but;

    public String getBut() {
        return but;
    }

    public void setBut(String but) {
        this.but = but;
    }

    public String getSadaremId() {
        return sadaremId;
    }

    public void setSadaremId(String sadaremId) {
        this.sadaremId = sadaremId;
    }
    
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


}
