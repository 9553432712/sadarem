/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author 842412
 */
public class PercentageWiseReportForm extends ActionForm {

    private String mode;
    private String district_id;
    ArrayList districtlist = new ArrayList();
    ArrayList financialYearList=new ArrayList();
    private String typeOfSearch;
    private String fromdate;
    private String todate;
    private String financialYearWise;
    private String districtName;
    private String financialYear;
    private String mandal_id;
    private String habitation_id;
    private String village_id;
    private String mandalname;
    private String villagename;

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getMandalname() {
        return mandalname;
    }

    public void setMandalname(String mandalname) {
        this.mandalname = mandalname;
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
    
    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }
    

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public ArrayList getFinancialYearList() {
        return financialYearList;
    }

    public void setFinancialYearList(ArrayList financialYearList) {
        this.financialYearList = financialYearList;
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

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }
    
    public String getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(String typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }
    
    public ArrayList getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    fromdate="";
//    district_id="";
    todate="";
    financialYearWise="";
    }
}
