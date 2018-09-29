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
 * @author SADAREM
 */
public class CampDailyReportForm extends org.apache.struts.action.ActionForm {

    private String campId;
    private String name;
    private  String address;
    private String venueAddress;
    private String mode;
    private String fromDate;
    private  String campdate;
    private String pwdAttended;
    private String pwdAssessed;
    private String doctorsCount;
    private String disabiltyId;
    private String disabilityName;
    private ArrayList disabilityList = new ArrayList();
    private String camp_id;

    public String getCamp_id() {
        return camp_id;
    }

    public void setCamp_id(String camp_id) {
        this.camp_id = camp_id;
    }


    public String getCampdate() {
        return campdate;
    }

    public void setCampdate(String campdate) {
        this.campdate = campdate;
    }

    public String getDoctorsCount() {
        return doctorsCount;
    }

    public void setDoctorsCount(String doctorsCount) {
        this.doctorsCount = doctorsCount;
    }

    public String getPwdAssessed() {
        return pwdAssessed;
    }

    public void setPwdAssessed(String pwdAssessed) {
        this.pwdAssessed = pwdAssessed;
    }

    public String getPwdAttended() {
        return pwdAttended;
    }

    public void setPwdAttended(String pwdAttended) {
        this.pwdAttended = pwdAttended;
    }


    
   

    
    public ArrayList getDisabilityList() {
        return disabilityList;
    }

    public void setDisabilityList(ArrayList disabilityList) {
        this.disabilityList = disabilityList;
    }
    private String campAssessedId;
    private String campData;

   
    public String getDisabilityName() {
        return disabilityName;
    }

    public String getCampData() {
        return campData;
    }

    public void setCampData(String campData) {
        this.campData = campData;
    }

   

    

    public void setDisabilityName(String disabilityName) {
        this.disabilityName = disabilityName;
    }



    
    public String getCampAssessedId() {
        return campAssessedId;
    }

    public void setCampAssessedId(String campAssessedId) {
        this.campAssessedId = campAssessedId;
    }
    

    public String getDisabiltyId() {
        return disabiltyId;
    }

    public void setDisabiltyId(String disabiltyId) {
        this.disabiltyId = disabiltyId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    
       private ArrayList campDataList = new ArrayList();

    public ArrayList getCampDataList() {
        return campDataList;
    }

    public void setCampDataList(ArrayList campDataList) {
        this.campDataList = campDataList;
    }
    private ArrayList campList = new ArrayList();

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }
    
   
}
