/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author 509862
 */
public class SearchCriteriaForm extends ActionForm {

    HttpServletRequest request = null;

    public String[] getType_disability() {
        return type_disability;
    }

    public void setType_disability(String[] type_disability) {
        this.type_disability = type_disability;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String[] getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String[] habitation_id) {
        this.habitation_id = habitation_id;
    }

   

    public String[] getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String[] mandal_id) {
        this.mandal_id = mandal_id;
    }

   

    public String getPanchayat_id() {
        return panchayat_id;
    }

    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }
    private String mode;
    private String from;
    private String to;
    private String[] type_disability;
    private String checkMandal;
    private String checkVillage;

    public String getCheckVillage() {
        return checkVillage;
    }

    public void setCheckVillage(String checkVillage) {
        this.checkVillage = checkVillage;
    }
    
    public String getCheckMandal() {
        return checkMandal;
    }

    public void setCheckMandal(String checkMandal) {
        this.checkMandal = checkMandal;
    }

    public String[] getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String[] village_id) {
        this.village_id = village_id;
    }
    

   

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }
    private String[] qualification;
    private String district_id;
    private String[] mandal_id;
    private String panchayat_id;
    private String[] village_id;
    private String[] habitation_id;
    private String assembly_id;
    private ArrayList districtlist = new ArrayList();
    private ArrayList mandallist = new ArrayList();
    private ArrayList panchayatlist = new ArrayList();
    private ArrayList villagelist = new ArrayList();

    public String[] getSelectedMandalsHidden() {
        return selectedMandalsHidden;
    }

    public void setSelectedMandalsHidden(String[] selectedMandalsHidden) {
        this.selectedMandalsHidden = selectedMandalsHidden;
    }
    private ArrayList habitationlist = new ArrayList();
    private String status;
    private String[] selectedMandalsHidden;

    public String getAssembly_id() {
        return assembly_id;
    }

    public void setAssembly_id(String assembly_id) {
        this.assembly_id = assembly_id;
    }

    public ArrayList getAssemblylist() {
        return assemblylist;
    }

    public void setAssemblylist(ArrayList assemblylist) {
        this.assemblylist = assemblylist;
    }
    private ArrayList assemblylist = new ArrayList();

    public ArrayList getDistrictlist() {

        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

    public ArrayList getMandallist() {


        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    public ArrayList getPanchayatlist() {
        return panchayatlist;
    }

    public void setPanchayatlist(ArrayList panchayatlist) {
        this.panchayatlist = panchayatlist;
    }

    public String[] getQualification() {
        return qualification;
    }

    public void setQualification(String[] qualification) {
        this.qualification = qualification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    private String gender;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
