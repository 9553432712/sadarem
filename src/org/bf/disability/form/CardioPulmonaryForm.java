

package org.bf.disability.form;

import org.apache.struts.action.ActionForm;

/**
 * This class contains the fields, required to compute CardioPulmonary
 * @version 1.0
 */
public class CardioPulmonaryForm extends ActionForm
{
    private String cardiopulmonary=null;
   
    private int ageyears;
    private int agemonths;
    private float height;
     

    public String getCardiopulmonary() {
        return cardiopulmonary;
    }

    public void setCardiopulmonary(String cardiopulmonary) {
        this.cardiopulmonary = cardiopulmonary;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getAgeyears() {
        return ageyears;
    }

    public void setAgeyears(int ageyears) {
        this.ageyears = ageyears;
    }

    public int getAgemonths() {
        return agemonths;
    }

    public void setAgemonths(int agemonths) {
        this.agemonths = agemonths;
    }
    
   
}
