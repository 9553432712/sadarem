/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;

import java.util.ArrayList;

/**
 *
 * @author SADAREM
 */
public class ReAssessmentCampWiseReportDTO {

     private String district_Id;
    private String district_Name;
    private ArrayList districtList = new ArrayList();
      
    private String camp_Id;
    private String camp_Name;
    private ArrayList campList = new ArrayList();

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public String getCamp_Id() {
        return camp_Id;
    }

    public void setCamp_Id(String camp_Id) {
        this.camp_Id = camp_Id;
    }

    public String getCamp_Name() {
        return camp_Name;
    }

    public void setCamp_Name(String camp_Name) {
        this.camp_Name = camp_Name;
    }
    
    
    private String mode;



    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public String getDistrict_Id() {
        return district_Id;
    }

    public void setDistrict_Id(String district_Id) {
        this.district_Id = district_Id;
    }

    public String getDistrict_Name() {
        return district_Name;
    }

    public void setDistrict_Name(String district_Name) {
        this.district_Name = district_Name;
    }

}
