/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 728056
 */
public class RDCallCentreStatusForm extends org.apache.struts.action.ActionForm {

    private String fromdate = "01/01/2010";
    private String todate;//=new Date().getDate()+"/"+new Date().getMonth()+"/"+(new Date().getYear()+1900);
    private String district_id;
    private String district_name;
    private String mode;
    private ArrayList districtList = new ArrayList();
    private ArrayList categoryList = new ArrayList();
    private String requestName;
    private String requestId;
    private String level;
    private String month;
    private String year;
    private ArrayList yearList = new ArrayList();
    private ArrayList monthList = new ArrayList();
    private String urban_id;

    public String getUrban_id() {
        return urban_id;
    }

    public void setUrban_id(String urban_id) {
        this.urban_id = urban_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList getYearList() {
        return yearList;
    }

    public void setYearList(ArrayList yearList) {
        this.yearList = yearList;
    }

    public ArrayList getMonthList() {
        return monthList;
    }

    public void setMonthList(ArrayList monthList) {
        this.monthList = monthList;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public ArrayList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList categoryList) {
        this.categoryList = categoryList;
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

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }
}
