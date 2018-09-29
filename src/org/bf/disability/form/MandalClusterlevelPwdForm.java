/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author 842412
 */
public class MandalClusterlevelPwdForm extends ActionForm {
   private String mode;
   private String fromdate;
   private String todate;
   private String typeOfSearch;
   private String district_id;
   private String mandal_id;
   private String village_id;
   private String districtName;
   private String mandalName;
   private String villageName;
   private String habitation_id;
   private String disabilityWise;
   ArrayList disabilityList=new ArrayList();
   ArrayList districtlist=new ArrayList();
   ArrayList campList=new ArrayList();
   private String disabiltyId;
   private String habitationName;
   private String district;
   private String campId;
   private String district_name;
   private String disabilityName;
   private String districtID;
   private String campDate;
   private String districtid;

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getCampDate() {
        return campDate;
    }

    public void setCampDate(String campDate) {
        this.campDate = campDate;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }
   
    public String getDisabilityName() {
        return disabilityName;
    }

    public void setDisabilityName(String disabilityName) {
        this.disabilityName = disabilityName;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public ArrayList getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    public String getHabitationName() {
        return habitationName;
    }

    public void setHabitationName(String habitationName) {
        this.habitationName = habitationName;
    }

    public String getDisabiltyId() {
        return disabiltyId;
    }

    public void setDisabiltyId(String disabiltyId) {
        this.disabiltyId = disabiltyId;
    }

    public ArrayList getDisabilityList() {
        return disabilityList;
    }

    public void setDisabilityList(ArrayList disabilityList) {
        this.disabilityList = disabilityList;
    }

    public String getDisabilityWise() {
        return disabilityWise;
    }

    public void setDisabilityWise(String disabilityWise) {
        this.disabilityWise = disabilityWise;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

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

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
   
    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(String typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
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
   
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
   
}
