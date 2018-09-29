/*
 * ReassessmentFiltringForm.java
 *
 * Created on June 18, 2008, 3:33 PM
 */
package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

/**
 * This class contains all the setter and getter methods.
 * These methods are used either to hold the form values or to populate
 * the data on to the form.
 * All the data members and the member functions in this class are
 * self-explanatory
 * @author 484898
 * @version 1.0
 */
public class ReassessmentFiltringForm extends ActionForm {

    private String mode;
    private String mandal_id;
    private String village_id;
    private String habitation_id;
    private ArrayList mandal_list = new ArrayList();
    private ArrayList village_list = new ArrayList();
    private ArrayList habitation_list = new ArrayList();
    private String[] checkedCandidates;

    public ArrayList getMandal_list() {
        return mandal_list;
    }

    public void setMandal_list(ArrayList mandal_list) {
        this.mandal_list = mandal_list;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public ArrayList getHabitation_list() {
        return habitation_list;
    }

    public void setHabitation_list(ArrayList habitation_list) {
        this.habitation_list = habitation_list;
    }

    public ArrayList getVillage_list() {
        return village_list;
    }

    public void setVillage_list(ArrayList village_list) {
        this.village_list = village_list;
    }

    public String[] getCheckedCandidates() {
        return checkedCandidates;
    }

    public void setCheckedCandidates(String[] checkedCandidates) {
        this.checkedCandidates = checkedCandidates;
    }

    
}
