package org.bf.disability.form;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * This form contains the fields, used in computing Amputation moudule
 * @version 1.0
 */
public class RationCardServiceForm extends ActionForm {

    private String mandal_id;
    private String mandal_name;
    private String village_id;
    private String village_name;
    private String habitation_id;
    private String habitation_name;
    private ArrayList mandal_list = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    private ArrayList habitationlist = new ArrayList();
    private String mode;
    private String[] rationCardDetails;
    private String[] rationCardSerialNumbers;
    private String[] rationCardTypes;
    private String[] personCode;
    private String personStatus;
    private String rationCardNo;

    public String getRationCardNo() {
        return rationCardNo;
    }

    public void setRationCardNo(String rationCardNo) {
        this.rationCardNo = rationCardNo;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public String getHabitation_name() {
        return habitation_name;
    }

    public void setHabitation_name(String habitation_name) {
        this.habitation_name = habitation_name;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public ArrayList getMandal_list() {
        return mandal_list;
    }

    public void setMandal_list(ArrayList mandal_list) {
        this.mandal_list = mandal_list;
    }

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
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

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }

    public String[] getRationCardDetails() {
        return rationCardDetails;
    }

    public void setRationCardDetails(String[] rationCardDetails) {
        this.rationCardDetails = rationCardDetails;
    }

    public String[] getRationCardSerialNumbers() {
        return rationCardSerialNumbers;
    }

    public void setRationCardSerialNumbers(String[] rationCardSerialNumbers) {
        this.rationCardSerialNumbers = rationCardSerialNumbers;
    }

    public String[] getRationCardTypes() {
        return rationCardTypes;
    }

    public void setRationCardTypes(String[] rationCardTypes) {
        this.rationCardTypes = rationCardTypes;
    }

    public String[] getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String[] personCode) {
        this.personCode = personCode;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.rationCardDetails = null;
        this.rationCardSerialNumbers = null;
        this.rationCardTypes = null;
    }
}
