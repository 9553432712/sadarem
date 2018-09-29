/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author 525483
 */
public class SearchForRationCardForm extends org.apache.struts.action.ActionForm {
    private String mode;
    private String rationCard;
    private String textBox;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRationCard() {
        return rationCard;
    }

    public void setRationCard(String rationCard) {
        this.rationCard = rationCard;
    }

    public String getTextBox() {
        return textBox;
    }

    public void setTextBox(String textBox) {
        this.textBox = textBox;
    }


}
