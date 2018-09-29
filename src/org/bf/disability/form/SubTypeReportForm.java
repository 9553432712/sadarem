/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;


import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author 747577
 */
public class SubTypeReportForm extends ActionForm {
   
    private String mode;
   
    private String mandal_id;
    private String village_id;
    private String habitation_id;
    private String typeOfDisability_id;
    private String subTypeOfDisability_id;
    private  String districts_id;
    ArrayList districtList = new ArrayList();
    ArrayList mandalList = new ArrayList();
    ArrayList villageList = new ArrayList();
    ArrayList habitationList = new ArrayList();
    ArrayList typeOfDisabilityList = new ArrayList();
    ArrayList subTypeOfDisabilityList = new ArrayList();

    public String getDistricts_id() {
        return districts_id;
    }

    public void setDistricts_id(String districts_id) {
        this.districts_id = districts_id;
    }


    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public ArrayList getHabitationList() {
        return habitationList;
    }

    public void setHabitationList(ArrayList habitationList) {
        this.habitationList = habitationList;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public ArrayList getMandalList() {
        return mandalList;
    }

    public void setMandalList(ArrayList mandalList) {
        this.mandalList = mandalList;
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

    public ArrayList getVillageList() {
        return villageList;
    }

    public void setVillageList(ArrayList villageList) {
        this.villageList = villageList;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public ArrayList getSubTypeOfDisabilityList() {
        return subTypeOfDisabilityList;
    }

    public void setSubTypeOfDisabilityList(ArrayList subTypeOfDisabilityList) {
        this.subTypeOfDisabilityList = subTypeOfDisabilityList;
    }

    public String getSubTypeOfDisability_id() {
        return subTypeOfDisability_id;
    }

    public void setSubTypeOfDisability_id(String subTypeOfDisability_id) {
        this.subTypeOfDisability_id = subTypeOfDisability_id;
    }

    public ArrayList getTypeOfDisabilityList() {
        return typeOfDisabilityList;
    }

    public void setTypeOfDisabilityList(ArrayList typeOfDisabilityList) {
        this.typeOfDisabilityList = typeOfDisabilityList;
    }

    public String getTypeOfDisability_id() {
        return typeOfDisability_id;
    }

    public void setTypeOfDisability_id(String typeOfDisability_id) {
        this.typeOfDisability_id = typeOfDisability_id;
    }



}
