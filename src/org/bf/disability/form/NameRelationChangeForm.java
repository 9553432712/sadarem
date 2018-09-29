/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;



/**
 *
 * @author 693461
 */
public class NameRelationChangeForm extends org.apache.struts.action.ActionForm {

    private  String mode;
    private String requesttypeId;
    private String requestIdData;
    private String populateOldName;
    private String populateOldRelationName;
    private String newName;
    private String newRelationName;
    private String populatenewName;
    private String populatenewRelationName;
    private String requestPersonCodeData;
    private  String requestIndividualRequestTypeId;
    private String oldName;
    private String requestChangeId;
    private String particularRequestIdDetails;
    private String populateOldSurName;
    private String populateNewSurName;
    private String RequestIndividualTypeId;

    private String surnameenglish;
    private String firstnametelugu;
    private String telugufathername;
    private String telugupersonname;
    private  String surnametelugu;
    private String populateOldNewName;
    private String relationShip;
    private String firstnameenglish;
    private  String firstFathernametelugu;
    private String oldmolesOne;
    private String oldmolesTwo;
    private String newmoleOne;
    private String newmolesTwo;
     private String populateoldMolesOne;
    private String populateoldMolesTwo;
    private String populatenewMolesOne;
    private String populatenewMolesTwo;
    private String oldDOB;
    private String newDOB;
    private String populateOldDOB;
    private String populateNewDOB;
    private String grelation;
    private String newmoleTwo;
    private String emailRequestId;
    private String emailPersonCode;
    private String loginId;
 private String oldTeluguName;
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getOldTeluguName() {
        return oldTeluguName;
    }

    public void setOldTeluguName(String oldTeluguName) {
        this.oldTeluguName = oldTeluguName;
    }
     private String oldEmailName;
     private String newEmailName;
     private String oldEmailRelationName;
     private String newEmailRelationName;
     private String emailRequestTypeId;
     private String emailPensionCardNo;
     private String emailPersonCodeId;
     private String emailHouseNo;

     private String emailDistrictName;
     private String emailMandalName;
     private String emailVillagename;
     private String emailHabitationName;
     private String emailAddress;
     private String emailPersonalName;
     private String emailPersonalRelationName;
     private String emailCampName;
     private String emailRequestTypeName;
    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }
     private String emailRegDate;
     private String[] biometricId;
     private String[] hiddenId;  
     private String requestFormId;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     private String personCodeFormId;
    private String status;
    private String district_id;
    private ArrayList districtList = new ArrayList();
    private String districtName;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPersonCodeFormId() {
        return personCodeFormId;
    }

    public void setPersonCodeFormId(String personCodeFormId) {
        this.personCodeFormId = personCodeFormId;
    }

    public String getRequestFormId() {
        return requestFormId;
    }

    public void setRequestFormId(String requestFormId) {
        this.requestFormId = requestFormId;
    }

    public String[] getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(String[] hiddenId) {
        this.hiddenId = hiddenId;
    }
     

    public String getEmailRegDate() {
        return emailRegDate;
    }

    public String[] getBiometricId() {
        return biometricId;
    }

    public void setBiometricId(String[] biometricId) {
        this.biometricId = biometricId;
    }
   
    public void setEmailRegDate(String emailRegDate) {
        this.emailRegDate = emailRegDate;
    }

    public String getEmailRequestTypeName() {
        return emailRequestTypeName;
    }

    public void setEmailRequestTypeName(String emailRequestTypeName) {
        this.emailRequestTypeName = emailRequestTypeName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailCampName() {
        return emailCampName;
    }

    public void setEmailCampName(String emailCampName) {
        this.emailCampName = emailCampName;
    }

    public String getEmailDistrictName() {
        return emailDistrictName;
    }

    public void setEmailDistrictName(String emailDistrictName) {
        this.emailDistrictName = emailDistrictName;
    }

    public String getEmailHabitationName() {
        return emailHabitationName;
    }

    public void setEmailHabitationName(String emailHabitationName) {
        this.emailHabitationName = emailHabitationName;
    }

    public String getEmailHouseNo() {
        return emailHouseNo;
    }

    public void setEmailHouseNo(String emailHouseNo) {
        this.emailHouseNo = emailHouseNo;
    }

    public String getEmailMandalName() {
        return emailMandalName;
    }

    public void setEmailMandalName(String emailMandalName) {
        this.emailMandalName = emailMandalName;
    }

    public String getEmailPensionCardNo() {
        return emailPensionCardNo;
    }

    public void setEmailPensionCardNo(String emailPensionCardNo) {
        this.emailPensionCardNo = emailPensionCardNo;
    }

    public String getEmailPersonCodeId() {
        return emailPersonCodeId;
    }

    public void setEmailPersonCodeId(String emailPersonCodeId) {
        this.emailPersonCodeId = emailPersonCodeId;
    }

    public String getEmailPersonalName() {
        return emailPersonalName;
    }

    public void setEmailPersonalName(String emailPersonalName) {
        this.emailPersonalName = emailPersonalName;
    }

    public String getEmailPersonalRelationName() {
        return emailPersonalRelationName;
    }

    public void setEmailPersonalRelationName(String emailPersonalRelationName) {
        this.emailPersonalRelationName = emailPersonalRelationName;
    }

    public String getEmailRequestTypeId() {
        return emailRequestTypeId;
    }

    public void setEmailRequestTypeId(String emailRequestTypeId) {
        this.emailRequestTypeId = emailRequestTypeId;
    }

    public String getEmailVillagename() {
        return emailVillagename;
    }

    public void setEmailVillagename(String emailVillagename) {
        this.emailVillagename = emailVillagename;
    }

    public String getNewEmailName() {
        return newEmailName;
    }

    public void setNewEmailName(String newEmailName) {
        this.newEmailName = newEmailName;
    }

    public String getNewEmailRelationName() {
        return newEmailRelationName;
    }

    public void setNewEmailRelationName(String newEmailRelationName) {
        this.newEmailRelationName = newEmailRelationName;
    }

    public String getOldEmailName() {
        return oldEmailName;
    }

    public void setOldEmailName(String oldEmailName) {
        this.oldEmailName = oldEmailName;
    }

    public String getOldEmailRelationName() {
        return oldEmailRelationName;
    }

    public void setOldEmailRelationName(String oldEmailRelationName) {
        this.oldEmailRelationName = oldEmailRelationName;
    }

    //end


     
    public String getEmailPersonCode() {
        return emailPersonCode;
    }

    public void setEmailPersonCode(String emailPersonCode) {
        this.emailPersonCode = emailPersonCode;
    }

    public String getEmailRequestId() {
        return emailRequestId;
    }

    public void setEmailRequestId(String emailRequestId) {
        this.emailRequestId = emailRequestId;
    }
    

    public String getNewmoleTwo() {
        return newmoleTwo;
    }

    public void setNewmoleTwo(String newmoleTwo) {
        this.newmoleTwo = newmoleTwo;
    }
    

    public String getGrelation() {
        return grelation;
    }

    public void setGrelation(String grelation) {
        this.grelation = grelation;
    }
    

    public String getNewDOB() {
        return newDOB;
    }

    public void setNewDOB(String newDOB) {
        this.newDOB = newDOB;
    }

    public String getOldDOB() {
        return oldDOB;
    }

    public void setOldDOB(String oldDOB) {
        this.oldDOB = oldDOB;
    }

    public String getPopulateNewDOB() {
        return populateNewDOB;
    }

    public void setPopulateNewDOB(String populateNewDOB) {
        this.populateNewDOB = populateNewDOB;
    }

    public String getPopulateOldDOB() {
        return populateOldDOB;
    }

    public void setPopulateOldDOB(String populateOldDOB) {
        this.populateOldDOB = populateOldDOB;
    }


    private String requesttypeidDataForm;

    public String getRequesttypeidDataForm() {
        return requesttypeidDataForm;
    }

    public void setRequesttypeidDataForm(String requesttypeidDataForm) {
        this.requesttypeidDataForm = requesttypeidDataForm;
    }
    

    public String getPopulatenewMolesOne() {
        return populatenewMolesOne;
    }

    public void setPopulatenewMolesOne(String populatenewMolesOne) {
        this.populatenewMolesOne = populatenewMolesOne;
    }

    public String getPopulatenewMolesTwo() {
        return populatenewMolesTwo;
    }

    public void setPopulatenewMolesTwo(String populatenewMolesTwo) {
        this.populatenewMolesTwo = populatenewMolesTwo;
    }

    public String getPopulateoldMolesOne() {
        return populateoldMolesOne;
    }

    public void setPopulateoldMolesOne(String populateoldMolesOne) {
        this.populateoldMolesOne = populateoldMolesOne;
    }

    public String getPopulateoldMolesTwo() {
        return populateoldMolesTwo;
    }

    public void setPopulateoldMolesTwo(String populateoldMolesTwo) {
        this.populateoldMolesTwo = populateoldMolesTwo;
    }

    

    public String getNewmolesTwo() {
        return newmolesTwo;
    }

    public void setNewmolesTwo(String newmolesTwo) {
        this.newmolesTwo = newmolesTwo;
    }

    public String getOldmolesOne() {
        return oldmolesOne;
    }

    public void setOldmolesOne(String oldmolesOne) {
        this.oldmolesOne = oldmolesOne;
    }

    public String getOldmolesTwo() {
        return oldmolesTwo;
    }

    public void setOldmolesTwo(String oldmolesTwo) {
        this.oldmolesTwo = oldmolesTwo;
    }

    

    public String getNewmoleOne() {
        return newmoleOne;
    }

    public void setNewmoleOne(String newmoleOne) {
        this.newmoleOne = newmoleOne;
    }
  

    public String getFirstFathernametelugu() {
        return firstFathernametelugu;
    }

    public void setFirstFathernametelugu(String firstFathernametelugu) {
        this.firstFathernametelugu = firstFathernametelugu;
    }


    public String getFirstnameenglish() {
        return firstnameenglish;
    }

    public void setFirstnameenglish(String firstnameenglish) {
        this.firstnameenglish = firstnameenglish;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }

    public String getPopulateOldNewName() {
        return populateOldNewName;
    }

    public void setPopulateOldNewName(String populateOldNewName) {
        this.populateOldNewName = populateOldNewName;
    }

    public String getSurnametelugu() {
        return surnametelugu;
    }

    public void setSurnametelugu(String surnametelugu) {
        this.surnametelugu = surnametelugu;
    }

    public String getTelugupersonname() {
        return telugupersonname;
    }

    public void setTelugupersonname(String telugupersonname) {
        this.telugupersonname = telugupersonname;
    }

    public String getTelugufathername() {
        return telugufathername;
    }

    public void setTelugufathername(String telugufathername) {
        this.telugufathername = telugufathername;
    }


    public String getFirstnametelugu() {
        return firstnametelugu;
    }

    public void setFirstnametelugu(String firstnametelugu) {
        this.firstnametelugu = firstnametelugu;
    }


    public String getSurnameenglish() {
        return surnameenglish;
    }

    public void setSurnameenglish(String surnameenglish) {
        this.surnameenglish = surnameenglish;
    }
    

    public String getPopulateNewSurName() {
        return populateNewSurName;
    }

    public void setPopulateNewSurName(String populateNewSurName) {
        this.populateNewSurName = populateNewSurName;
    }

    public String getPopulateOldSurName() {
        return populateOldSurName;
    }

    public void setPopulateOldSurName(String populateOldSurName) {
        this.populateOldSurName = populateOldSurName;
    }

    

    public String getRequestIndividualTypeId() {
        return RequestIndividualTypeId;
    }

    public void setRequestIndividualTypeId(String RequestIndividualTypeId) {
        this.RequestIndividualTypeId = RequestIndividualTypeId;
    }

    public String getParticularRequestIdDetails() {
        return particularRequestIdDetails;
    }

    public void setParticularRequestIdDetails(String particularRequestIdDetails) {
        this.particularRequestIdDetails = particularRequestIdDetails;
    }

    public String getRequestChangeId() {
        return requestChangeId;
    }

    public void setRequestChangeId(String requestChangeId) {
        this.requestChangeId = requestChangeId;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }


    public String getRequestIndividualRequestTypeId() {
        return requestIndividualRequestTypeId;
    }

    public void setRequestIndividualRequestTypeId(String requestIndividualRequestTypeId) {
        this.requestIndividualRequestTypeId = requestIndividualRequestTypeId;
    }
    

    public String getRequestPersonCodeData() {
        return requestPersonCodeData;
    }

    public void setRequestPersonCodeData(String requestPersonCodeData) {
        this.requestPersonCodeData = requestPersonCodeData;
    }
    

    public String getPopulatenewName() {
        return populatenewName;
    }

    public void setPopulatenewName(String populatenewName) {
        this.populatenewName = populatenewName;
    }

    public String getPopulatenewRelationName() {
        return populatenewRelationName;
    }

    public void setPopulatenewRelationName(String populatenewRelationName) {
        this.populatenewRelationName = populatenewRelationName;
    }

    public String getPopulateOldRelationName() {
        return populateOldRelationName;
    }

    public void setPopulateOldRelationName(String populateOldRelationName) {
        this.populateOldRelationName = populateOldRelationName;
    }
    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewRelationName() {
        return newRelationName;
    }

    public void setNewRelationName(String newRelationName) {
        this.newRelationName = newRelationName;
    }

    public String getPopulateOldName() {
        return populateOldName;
    }

    public void setPopulateOldName(String populateOldName) {
        this.populateOldName = populateOldName;
    }

    public String getRequestIdData() {
        return requestIdData;
    }

    public void setRequestIdData(String requestIdData) {
        this.requestIdData = requestIdData;
    }

    public String getRequesttypeId() {
        return requesttypeId;
    }

    public void setRequesttypeId(String requesttypeId) {
        this.requesttypeId = requesttypeId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}