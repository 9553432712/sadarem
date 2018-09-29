/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author 693461
 */
public class MonthWiseCumulativeReportForm extends org.apache.struts.action.ActionForm {
   private String mode;
    private String district_id;
    private String fromDate;
    private Boolean cumulative;
    private String frmdt;
    private String districtName;
    private String villageName;

    public String getHabitationName() {
        return habitationName;
    }

    public void setHabitationName(String habitationName) {
        this.habitationName = habitationName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
    private String habitationName;
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }
    private String mandalName;

    public String getFrmdt() {
        return frmdt;
    }

    public void setFrmdt(String frmdt) {
        this.frmdt = frmdt;
    }

    public Boolean getCumulative() {
        return cumulative;
    }

    public void setCumulative(Boolean cumulative) {
        this.cumulative = cumulative;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    private String toDate;

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
    private String mandal_id;
    private String village_id;
    private String habitation_id;


    ArrayList districtList = new ArrayList();
    ArrayList mandalList = new ArrayList();
    ArrayList villageList = new ArrayList();
    ArrayList habitationList = new ArrayList();
    public MonthWiseCumulativeReportForm() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        
        return null;
    }
}
