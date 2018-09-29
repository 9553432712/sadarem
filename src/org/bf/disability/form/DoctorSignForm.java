/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

/**
 * @author 484898
 * 
 */
public class DoctorSignForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String[] doSign;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String[] getDoSign() {
        return doSign;
    }

    public void setDoSign(String[] doSign) {
        this.doSign = doSign;
    }
    
}
