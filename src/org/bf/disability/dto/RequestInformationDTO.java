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
public class RequestInformationDTO {
 private String mode;
    private String requestId;
    private String personCode;
    //while selecting new certificate scenario
      private String newCertificatePersonCode;
    //end
    private  String name;
    private String relationName;
    private  String houseNO;
    private String mandalName;
    private String villageName;
    private String districtName;
    private String habitationName;
    private ArrayList requestTypeList = new ArrayList();
    private String requestTypeId;
    private String requestTypeName;
    private String requestTypeDetails;
    private String requestTypeDetailsId;
    private String[] requestHiddenId;
    private String[] approval;
    private String[] reject;
    private String approvalStatus;
    private String rejectStatus;
     private String[] recordStatus1;
    private String[] recordStatus2;
    private String requestTypeIdDataFormat;
    private String hiddenRequestTypeId;
    private  String regDate;
    private String multipleReqName;




    //For Edit Scenario Msg
      private String editErrormsg;
    //end
       //For View Scenario Msg
      private String viewErrormsg;
      //end

    //For MailStatus Scenario Msg
      private String sendmailStatus;
      private  String[] hiddenPersonCode;
      private int insertDetails;

      //For Renual Scenario Msg
      private String renualstatus;
      private int renualInsertDetails;
      private String  msgSadaremId;
      private String photomsg;
      private String photoRequestId;
       private String photoRequestViewId;
       private String fileData;

    public String getMultipleReqName() {
        return multipleReqName;
    }

    public void setMultipleReqName(String multipleReqName) {
        this.multipleReqName = multipleReqName;
    }

       


    public String getFileData() {
        return fileData;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }


    public String getPhotoRequestViewId() {
        return photoRequestViewId;
    }

    public void setPhotoRequestViewId(String photoRequestViewId) {
        this.photoRequestViewId = photoRequestViewId;
    }
       


    public String getPhotomsg() {
        return photomsg;
    }

    public String getPhotoRequestId() {
        return photoRequestId;
    }

    public void setPhotoRequestId(String photoRequestId) {
        this.photoRequestId = photoRequestId;
    }

    public void setPhotomsg(String photomsg) {
        this.photomsg = photomsg;
    }


    public String getMsgSadaremId() {
        return msgSadaremId;
    }

    public void setMsgSadaremId(String msgSadaremId) {
        this.msgSadaremId = msgSadaremId;
    }

    public String getHiddenRequestTypeId() {
        return hiddenRequestTypeId;
    }

    public void setHiddenRequestTypeId(String hiddenRequestTypeId) {
        this.hiddenRequestTypeId = hiddenRequestTypeId;
    }

    public String getRequestTypeIdDataFormat() {
        return requestTypeIdDataFormat;
    }

    public void setRequestTypeIdDataFormat(String requestTypeIdDataFormat) {
        this.requestTypeIdDataFormat = requestTypeIdDataFormat;
    }
      
    public int getRenualInsertDetails() {
        return renualInsertDetails;
    }

    public void setRenualInsertDetails(int renualInsertDetails) {
        this.renualInsertDetails = renualInsertDetails;
    }

    public String getRenualstatus() {
        return renualstatus;
    }

    public void setRenualstatus(String renualstatus) {
        this.renualstatus = renualstatus;
    }
      

    public int getInsertDetails() {
        return insertDetails;
    }

    public void setInsertDetails(int insertDetails) {
        this.insertDetails = insertDetails;
    }

    public String[] getHiddenPersonCode() {
        return hiddenPersonCode;
    }

    public void setHiddenPersonCode(String[] hiddenPersonCode) {
        this.hiddenPersonCode = hiddenPersonCode;
    }
    public String getSendmailStatus() {
        return sendmailStatus;
    }

    public void setSendmailStatus(String sendmailStatus) {
        this.sendmailStatus = sendmailStatus;
    }
      //end

    public String getNewCertificatePersonCode() {
        return newCertificatePersonCode;
    }

    public String getEditErrormsg() {
        return editErrormsg;
    }

    public void setEditErrormsg(String editErrormsg) {
        this.editErrormsg = editErrormsg;
    }

    public String getViewErrormsg() {
        return viewErrormsg;
    }

    public void setViewErrormsg(String viewErrormsg) {
        this.viewErrormsg = viewErrormsg;
    }

    public void setNewCertificatePersonCode(String newCertificatePersonCode) {
        this.newCertificatePersonCode = newCertificatePersonCode;
    }

    

    public String[] getRecordStatus1() {
        return recordStatus1;
    }

    public void setRecordStatus1(String[] recordStatus1) {
        this.recordStatus1 = recordStatus1;
    }

    public String[] getRecordStatus2() {
        return recordStatus2;
    }

    public void setRecordStatus2(String[] recordStatus2) {
        this.recordStatus2 = recordStatus2;
    }
    



    // To populate Details Based Upon RequestID
    private String personalName;
    private String personalPersonCode;
    private String personalRelationName;
    private String personalHouseNo;
    private String personalHabitationName;
    private String personalVillageName;
    private String personalMandalName;
    private String personalDistrictName;
    
    private String requestIdData;
    private String pendingStatus;

    public String getPendingStatus() {
        return pendingStatus;
    }

    public void setPendingStatus(String pendingStatus) {
        this.pendingStatus = pendingStatus;
    }

    

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(String rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

   

    public String[] getRequestHiddenId() {
        return requestHiddenId;
    }

    public void setRequestHiddenId(String[] requestHiddenId) {
        this.requestHiddenId = requestHiddenId;
    }

    public String getRequestTypeDetailsId() {
        return requestTypeDetailsId;
    }

    public void setRequestTypeDetailsId(String requestTypeDetailsId) {
        this.requestTypeDetailsId = requestTypeDetailsId;
    }
    

    public String getRequestIdData() {
        return requestIdData;
    }

    public void setRequestIdData(String requestIdData) {
        this.requestIdData = requestIdData;
    }

    public String[] getApproval() {
        return approval;
    }

    public void setApproval(String[] approval) {
        this.approval = approval;
    }

    public String[] getReject() {
        return reject;
    }

    public void setReject(String[] reject) {
        this.reject = reject;
    }

    public String getPersonalDistrictName() {
        return personalDistrictName;
    }

    public void setPersonalDistrictName(String personalDistrictName) {
        this.personalDistrictName = personalDistrictName;
    }

    public String getPersonalHabitationName() {
        return personalHabitationName;
    }

    public void setPersonalHabitationName(String personalHabitationName) {
        this.personalHabitationName = personalHabitationName;
    }

    public String getPersonalHouseNo() {
        return personalHouseNo;
    }

    public void setPersonalHouseNo(String personalHouseNo) {
        this.personalHouseNo = personalHouseNo;
    }

    public String getPersonalMandalName() {
        return personalMandalName;
    }

    public void setPersonalMandalName(String personalMandalName) {
        this.personalMandalName = personalMandalName;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getPersonalPersonCode() {
        return personalPersonCode;
    }

    public void setPersonalPersonCode(String personalPersonCode) {
        this.personalPersonCode = personalPersonCode;
    }

    public String getPersonalRelationName() {
        return personalRelationName;
    }

    public void setPersonalRelationName(String personalRelationName) {
        this.personalRelationName = personalRelationName;
    }

    public String getPersonalVillageName() {
        return personalVillageName;
    }

    public void setPersonalVillageName(String personalVillageName) {
        this.personalVillageName = personalVillageName;
    }

    public String getRequestTypeDetails() {
        return requestTypeDetails;
    }

    public void setRequestTypeDetails(String requestTypeDetails) {
        this.requestTypeDetails = requestTypeDetails;
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


    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getHabitationName() {
        return habitationName;
    }

    public void setHabitationName(String habitationName) {
        this.habitationName = habitationName;
    }

    public String getHouseNO() {
        return houseNO;
    }

    public void setHouseNO(String houseNO) {
        this.houseNO = houseNO;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
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

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    
}
