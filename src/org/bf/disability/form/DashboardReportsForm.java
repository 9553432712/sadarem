package org.bf.disability.form;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class DashboardReportsForm extends org.apache.struts.action.ActionForm {

    private String district_id;
    private String year;
    private String month;
    private String fromdate;
    private String todate;
    private String financialYear;
    private String mode;
    private String typeOfSearch;
    private String reportType;
    private String campId;
    ArrayList yearList = new ArrayList();
    ArrayList monthList = new ArrayList();
    ArrayList financialYearList = new ArrayList();
    ArrayList campList = new ArrayList();
     private ArrayList districtlist = new ArrayList();
    private String campName;
    private String campVenue;
    private String mandal_name;
    private String mandal_id;
    private String village_id;
    private String district_name;
    private String village_name;

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }
    
    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampVenue() {
        return campVenue;
    }

    public void setCampVenue(String campVenue) {
        this.campVenue = campVenue;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public ArrayList getFinancialYearList() {
        return financialYearList;
    }

    public void setFinancialYearList(ArrayList financialYearList) {
        this.financialYearList = financialYearList;
    }

    public ArrayList getMonthList() {
        return monthList;
    }

    public void setMonthList(ArrayList monthList) {
        this.monthList = monthList;
    }

    public ArrayList getYearList() {
        return yearList;
    }

    public void setYearList(ArrayList yearList) {
        this.yearList = yearList;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(String typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    
}
