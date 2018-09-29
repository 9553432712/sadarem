/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;

import java.util.ArrayList;

/**
 *
 * @author 693461
 */
public class PwdRequestDTO {

    private String personal;
    private String[] requestId;
    private String mode;
    private String name;
    private String relationNmae;
    private String dateOfBirth;
    private String district_id;
    private  String mandal_id;
    private String village_id;
    private String habitation_id;
    private String requestTypeId;
    private String requestTypeName;
    private ArrayList requestTypeList= new ArrayList();
    private String pinCode;
    private ArrayList requestTypeDetails= new ArrayList();
    
    private String personalName;
    private String personalrelationName;
    private ArrayList mandallist = new ArrayList();
    private String mandal_name;
    private ArrayList villagelist = new ArrayList();
    private String village_name;
    private String habitation_name;
    private ArrayList habitationlist = new ArrayList();
    private String contactNo;
    private String requestTyepeId;

    //To populate Recipt Details List
    private String reciptRequestId;
    private String reciptPersonCode;
    private String reciptSurName;
    private String reciptName;
    private String reciptRelationName;
    private  String reciptHouseno;
    private String reciptDistrictName;
    private String reciptMandalName;
    private String reciptVillageName;
    private String reciptHabitationName;
    private String reciptRegisteratedDate;
    private  String reciptRequestTypeName;
    private String requestHouseNo;
    private String requestPinCode;
    private String panchayat_id;
    private String panchaith;
    private ArrayList panchayatlist;
    private String renualId;
    private  String  temparyPersonCodeId;
    private  String  temparyerrormsg;
    private  String invalidErrorMsg;
    private int  validMsg;
    
    private  String sadaremIdErrorMsg;
    private int  sadaremValidMsg;

    private String error_msg;
    private String requestCountId;
    private String requestCountName;

    public String getRequestCountId() {
        return requestCountId;
    }

    public void setRequestCountId(String requestCountId) {
        this.requestCountId = requestCountId;
    }

    public String getRequestCountName() {
        return requestCountName;
    }

    public void setRequestCountName(String requestCountName) {
        this.requestCountName = requestCountName;
    }
    



    public String getSadaremIdErrorMsg() {
        return sadaremIdErrorMsg;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public void setSadaremIdErrorMsg(String sadaremIdErrorMsg) {
        this.sadaremIdErrorMsg = sadaremIdErrorMsg;
    }

    public int getSadaremValidMsg() {
        return sadaremValidMsg;
    }

    public void setSadaremValidMsg(int sadaremValidMsg) {
        this.sadaremValidMsg = sadaremValidMsg;
    }
    


    public int getValidMsg() {
        return validMsg;
    }

    public void setValidMsg(int validMsg) {
        this.validMsg = validMsg;
    }
    

    private String dupliateErrorMsg;

    public String getInvalidErrorMsg() {
        return invalidErrorMsg;
    }

    public void setInvalidErrorMsg(String invalidErrorMsg) {
        this.invalidErrorMsg = invalidErrorMsg;
    }


    
    public String getDupliateErrorMsg() {
        return dupliateErrorMsg;
    }

    public void setDupliateErrorMsg(String dupliateErrorMsg) {
        this.dupliateErrorMsg = dupliateErrorMsg;
    }


    public String getTemparyPersonCodeId() {
        return temparyPersonCodeId;
    }

    public String getTemparyerrormsg() {
        return temparyerrormsg;
    }

    public void setTemparyerrormsg(String temparyerrormsg) {
        this.temparyerrormsg = temparyerrormsg;
    }

    public void setTemparyPersonCodeId(String temparyPersonCodeId) {
        this.temparyPersonCodeId = temparyPersonCodeId;
    }


    private String requestmaxId;

    public String getRenualId() {
        return renualId;
    }

    public void setRenualId(String renualId) {
        this.renualId = renualId;
    }

    public String getPanchaith() {
        return panchaith;
    }

    public void setPanchaith(String panchaith) {
        this.panchaith = panchaith;
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

    public String getReciptSurName() {
        return reciptSurName;
    }

    public void setReciptSurName(String reciptSurName) {
        this.reciptSurName = reciptSurName;
    }

    public String getRequestmaxId() {
        return requestmaxId;
    }

    public void setRequestmaxId(String requestmaxId) {
        this.requestmaxId = requestmaxId;
    }

    public String getRequestPinCode() {
        return requestPinCode;
    }

    public void setRequestPinCode(String requestPinCode) {
        this.requestPinCode = requestPinCode;
    }

    
    public String getRequestHouseNo() {
        return requestHouseNo;
    }

    public void setRequestHouseNo(String requestHouseNo) {
        this.requestHouseNo = requestHouseNo;
    }

    

    public String getReciptDistrictName() {
        return reciptDistrictName;
    }

    public void setReciptDistrictName(String reciptDistrictName) {
        this.reciptDistrictName = reciptDistrictName;
    }

    public String getReciptHabitationName() {
        return reciptHabitationName;
    }

    public void setReciptHabitationName(String reciptHabitationName) {
        this.reciptHabitationName = reciptHabitationName;
    }

    public String getReciptHouseno() {
        return reciptHouseno;
    }

    public void setReciptHouseno(String reciptHouseno) {
        this.reciptHouseno = reciptHouseno;
    }

    public String getReciptMandalName() {
        return reciptMandalName;
    }

    public void setReciptMandalName(String reciptMandalName) {
        this.reciptMandalName = reciptMandalName;
    }

    public String getReciptName() {
        return reciptName;
    }

    public void setReciptName(String reciptName) {
        this.reciptName = reciptName;
    }

    public String getReciptPersonCode() {
        return reciptPersonCode;
    }

    public void setReciptPersonCode(String reciptPersonCode) {
        this.reciptPersonCode = reciptPersonCode;
    }

    public String getReciptRegisteratedDate() {
        return reciptRegisteratedDate;
    }

    public void setReciptRegisteratedDate(String reciptRegisteratedDate) {
        this.reciptRegisteratedDate = reciptRegisteratedDate;
    }

    public String getReciptRelationName() {
        return reciptRelationName;
    }

    public void setReciptRelationName(String reciptRelationName) {
        this.reciptRelationName = reciptRelationName;
    }

    public String getReciptRequestId() {
        return reciptRequestId;
    }

    public void setReciptRequestId(String reciptRequestId) {
        this.reciptRequestId = reciptRequestId;
    }

    public String getReciptRequestTypeName() {
        return reciptRequestTypeName;
    }

    public void setReciptRequestTypeName(String reciptRequestTypeName) {
        this.reciptRequestTypeName = reciptRequestTypeName;
    }

    public String getReciptVillageName() {
        return reciptVillageName;
    }

    public void setReciptVillageName(String reciptVillageName) {
        this.reciptVillageName = reciptVillageName;
    }

    public String getRequestTyepeId() {
        return requestTyepeId;
    }

    public void setRequestTyepeId(String requestTyepeId) {
        this.requestTyepeId = requestTyepeId;
    }



    public ArrayList getRequestTypeDetails() {
        return requestTypeDetails;
    }

    public void setRequestTypeDetails(ArrayList requestTypeDetails) {
        this.requestTypeDetails = requestTypeDetails;
    }

    
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }



    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }


    public String getHabitation_name() {
        return habitation_name;
    }

    public void setHabitation_name(String habitation_name) {
        this.habitation_name = habitation_name;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }

    public ArrayList getMandallist() {
        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getPersonalrelationName() {
        return personalrelationName;
    }

    public void setPersonalrelationName(String personalrelationName) {
        this.personalrelationName = personalrelationName;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }
   
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
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

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getRelationNmae() {
        return relationNmae;
    }

    public void setRelationNmae(String relationNmae) {
        this.relationNmae = relationNmae;
    }

    public String[] getRequestId() {
        return requestId;
    }

    public void setRequestId(String[] requestId) {
        this.requestId = requestId;
    }

    public String getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(String requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public ArrayList getRequestTypeList() {
        return requestTypeList;
    }

    public void setRequestTypeList(ArrayList requestTypeList) {
        this.requestTypeList = requestTypeList;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }
    

}
