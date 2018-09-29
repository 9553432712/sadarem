

package org.bf.disability.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * This class contains the fields, required to compute Dwarfism
 * @author Kiran
 * @version 1.0
 */
public class DwarfismActionForm extends ValidatorForm
{
    private String ageyears;
    private String agemonths;
    private String height;
     
 private String personcode;
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAgeyears() {
        return ageyears;
    }

    public void setAgeyears(String ageyears) {
        this.ageyears = ageyears;
    }

    public String getAgemonths() {
        return agemonths;
    }

    public void setAgemonths(String agemonths) {
        this.agemonths = agemonths;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }
   
   
}
