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
public class CampDetailsForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String campId;
    private String campName;
    private ArrayList campList = new ArrayList();
    private ArrayList yearsList = new ArrayList();
    private String campDate;
    private String district_id;
    private String district_name;
    private String month;
    private int year;
    private ArrayList monthList = new ArrayList();
    private ArrayList districtList = new ArrayList();
    private String status;
    private String disabilityCount;
    private String columns;
    private String columnNamesPending;
    private String columnsPending;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList getYearsList() {
        return yearsList;
    }

    public void setYearsList(ArrayList yearsList) {
        this.yearsList = yearsList;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public ArrayList getMonthList() {
        return monthList;
    }

    public void setMonthList(ArrayList monthList) {
        this.monthList = monthList;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCampDate() {
        return campDate;
    }

    public void setCampDate(String campDate) {
        this.campDate = campDate;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDisabilityCount() {
        return disabilityCount;
    }

    public void setDisabilityCount(String disabilityCount) {
        this.disabilityCount = disabilityCount;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getColumnNamesPending() {
        return columnNamesPending;
    }

    public void setColumnNamesPending(String columnNamesPending) {
        this.columnNamesPending = columnNamesPending;
    }

    public String getColumnsPending() {
        return columnsPending;
    }

    public void setColumnsPending(String columnsPending) {
        this.columnsPending = columnsPending;
    }
    
}
