/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;


/**
 *
 * @author 728056
 */
public class AssessedPWDDetailsForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String district_id;
    private String mandal_id;  
    private String village_id;

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }

    public String getPanchayat_name() {
        return panchayat_name;
    }

    public void setPanchayat_name(String panchayat_name) {
        this.panchayat_name = panchayat_name;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public ArrayList getPanchayatList() {
        return panchayatList;
    }

    public void setPanchayatList(ArrayList panchayatList) {
        this.panchayatList = panchayatList;
    }

    public String getPanchayat_id() {
        return panchayat_id;
    }

    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }
    private String panchayat_id;
    private ArrayList panchayatList = new ArrayList();
    private String fromdate="01/01/2010";
    private String todate;
    private String financialYearWise;
    private String pensionPhase;
    private String typeOfSearch="Datewise";
    private String district_name;
    private String mandal_name;
    private String panchayat_name;
    private String village_name;
    private ArrayList districtlist = new ArrayList();
    private ArrayList mandallist = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    
    public String getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(String typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }
    
    
    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public ArrayList getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    public String getFinancialYearWise() {
        return financialYearWise;
    }

    public void setFinancialYearWise(String financialYearWise) {
        this.financialYearWise = financialYearWise;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public ArrayList getMandallist() {
        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPensionPhase() {
        return pensionPhase;
    }

    public void setPensionPhase(String pensionPhase) {
        this.pensionPhase = pensionPhase;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }

}
