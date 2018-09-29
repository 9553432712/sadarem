/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dto;

import java.util.ArrayList;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 310926
 */
public class GrievancesRequestDetailsDTO {

    private String mode;
    private String requestId;
    private String subRequestId;
    private String status;
    private ArrayList requestList = new ArrayList();
    private String sub;
    private String requestNumber;
    private String requestIn;
    private String natureOfBeneficiary;
    private String schDate;
    private String proofId;
    private String proofType;
    private ArrayList proofList = new ArrayList();
    private FormFile uploadDocument;
    private String systemIp;
    //MESEVA properties Start
    private String surname;
    private String firstname;
    private String relationName;
    private String relationType;
    private String age;
    private String gender;
    private String phoneno;
    private String email;
    private String houseno;
    private String pensioncardno;
    private String pensionNo;
    private String district_id;
    private String mandal_id;
    private String village_id;
    private String panchayat_id;
    private String habitation_id;
    private String districtName;
    private String mandalName;
    private String villageName;
    private String panchayatName;
    private String habitationName;
    private ArrayList districtlist = new ArrayList();
    private ArrayList mandallist = new ArrayList();
    private ArrayList panchayatlist = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    private ArrayList habitationlist = new ArrayList();
    private String SCAUserId;
    private String uniqueNo;
    private String loginId;
    private String channelId;
    private String serviceid;
    private String scaPassword;
    private String applicationNo;
    private String meesevaFlag;
    private String created_date;
    private String meesevaId;
    private String adhaarSurname;
    private String adhaarName;
     private String adhaarDistrictName;
    private String adhaaarMandalName;
    private String adhaarGender;
    private String adhaarRelationName;
    private String adhaarDistrictId;
    private String adhaarMandalId;
    private String adhaarHouseNo;
    private String uploadFlag;

    public String getUploadFlag() {
        return uploadFlag;
    }

    public void setUploadFlag(String uploadFlag) {
        this.uploadFlag = uploadFlag;
    }


    public String getAdhaarHouseNo() {
        return adhaarHouseNo;
    }

    public void setAdhaarHouseNo(String adhaarHouseNo) {
        this.adhaarHouseNo = adhaarHouseNo;
    }
    

    public String getAdhaarDistrictId() {
        return adhaarDistrictId;
    }

    public void setAdhaarDistrictId(String adhaarDistrictId) {
        this.adhaarDistrictId = adhaarDistrictId;
    }

    public String getAdhaarMandalId() {
        return adhaarMandalId;
    }

    public void setAdhaarMandalId(String adhaarMandalId) {
        this.adhaarMandalId = adhaarMandalId;
    }
    

    public String getAdhaarRelationName() {
        return adhaarRelationName;
    }

    public void setAdhaarRelationName(String adhaarRelationName) {
        this.adhaarRelationName = adhaarRelationName;
    }

    public String getAdhaarGender() {
        return adhaarGender;
    }

    public void setAdhaarGender(String adhaarGender) {
        this.adhaarGender = adhaarGender;
    }
    

    
    public String getAdhaaarMandalName() {
        return adhaaarMandalName;
    }

    public void setAdhaaarMandalName(String adhaaarMandalName) {
        this.adhaaarMandalName = adhaaarMandalName;
    }

    public String getAdhaarDistrictName() {
        return adhaarDistrictName;
    }

    public void setAdhaarDistrictName(String adhaarDistrictName) {
        this.adhaarDistrictName = adhaarDistrictName;
    }
    

    public String getAdhaarName() {
        return adhaarName;
    }

    public void setAdhaarName(String adhaarName) {
        this.adhaarName = adhaarName;
    }

    public String getAdhaarSurname() {
        return adhaarSurname;
    }

    public void setAdhaarSurname(String adhaarSurname) {
        this.adhaarSurname = adhaarSurname;
    }
    

    public String getMeesevaFlag() {
        return meesevaFlag;
    }

    public void setMeesevaFlag(String meesevaFlag) {
        this.meesevaFlag = meesevaFlag;
    }

    public String getMeesevaId() {
        return meesevaId;
    }

    public void setMeesevaId(String meesevaId) {
        this.meesevaId = meesevaId;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }


    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getScaPassword() {
        return scaPassword;
    }

    public void setScaPassword(String scaPassword) {
        this.scaPassword = scaPassword;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getSCAUserId() {
        return SCAUserId;
    }

    public void setSCAUserId(String SCAUserId) {
        this.SCAUserId = SCAUserId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHabitationName() {
        return habitationName;
    }

    public void setHabitationName(String habitationName) {
        this.habitationName = habitationName;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
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

    public String getNatureOfBeneficiary() {
        return natureOfBeneficiary;
    }

    public void setNatureOfBeneficiary(String natureOfBeneficiary) {
        this.natureOfBeneficiary = natureOfBeneficiary;
    }

    public String getPanchayatName() {
        return panchayatName;
    }

    public void setPanchayatName(String panchayatName) {
        this.panchayatName = panchayatName;
    }

    public String getPanchayat_id() {
        return panchayat_id;
    }

    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }

    public ArrayList getPanchayatlist() {
        return panchayatlist;
    }

    public void setPanchayatlist(ArrayList panchayatlist) {
        this.panchayatlist = panchayatlist;
    }

    public String getPensionNo() {
        return pensionNo;
    }

    public void setPensionNo(String pensionNo) {
        this.pensionNo = pensionNo;
    }

    public String getPensioncardno() {
        return pensioncardno;
    }

    public void setPensioncardno(String pensioncardno) {
        this.pensioncardno = pensioncardno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getProofId() {
        return proofId;
    }

    public void setProofId(String proofId) {
        this.proofId = proofId;
    }

    public ArrayList getProofList() {
        return proofList;
    }

    public void setProofList(ArrayList proofList) {
        this.proofList = proofList;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestIn() {
        return requestIn;
    }

    public void setRequestIn(String requestIn) {
        this.requestIn = requestIn;
    }

    public ArrayList getRequestList() {
        return requestList;
    }

    public void setRequestList(ArrayList requestList) {
        this.requestList = requestList;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getSchDate() {
        return schDate;
    }

    public void setSchDate(String schDate) {
        this.schDate = schDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getSubRequestId() {
        return subRequestId;
    }

    public void setSubRequestId(String subRequestId) {
        this.subRequestId = subRequestId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSystemIp() {
        return systemIp;
    }

    public void setSystemIp(String systemIp) {
        this.systemIp = systemIp;
    }

    public String getUniqueNo() {
        return uniqueNo;
    }

    public void setUniqueNo(String uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    public FormFile getUploadDocument() {
        return uploadDocument;
    }

    public void setUploadDocument(FormFile uploadDocument) {
        this.uploadDocument = uploadDocument;
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

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }
}
