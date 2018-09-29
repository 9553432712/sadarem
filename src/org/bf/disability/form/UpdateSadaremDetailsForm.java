/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;



/**
 *
 * @author 728056
 */
public class UpdateSadaremDetailsForm extends org.apache.struts.action.ActionForm {
    
    private String mode;
    private String[] selectBox;
    private String sub;
    private String hello;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String[] getSelectBox() {
        return selectBox;
    }

    public void setSelectBox(String[] selectBox) {
        this.selectBox = selectBox;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    

    
}
