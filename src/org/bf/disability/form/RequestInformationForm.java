/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;


/**
 *
 * @author 693461
 */
public class RequestInformationForm extends ActionForm {


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
    private ArrayList requestTypeList;
    private String requestTypeId;
    private String requestTypeName;
    private  String status;
    private String requestTypeDetails;
    private String[] approval;
    private String[] reject;
    private String requestIdData;
     private String requestTypeDetailsId;
    private String[] requestHiddenId;
    private String approvalStatus;
    private String rejectStatus;
    private String pendingStatus;
    private String[] recordStatus1;
    private String[] recordStatus2;
    private int hiddenlist;
    private String checkApproval;
    private String checkReject;
    private String approvalId;
    private String rejectId;
   private String requestTypeIdDataFormat;
   private String[] approvalRequestId;
   private String multiple;
   private String single;
   private  String requestTypeStatusId;



       
    private String hiddenRequestTypeId;

    //To check for sadaremID wheather Exit or Not
    private  String[] hiddenPersonCode;
    private String newCertificateReqiestId;
    private String newCertificateReqiestTypeId;

    public String getRequestTypeStatusId() {
        return requestTypeStatusId;
    }

    public void setRequestTypeStatusId(String requestTypeStatusId) {
        this.requestTypeStatusId = requestTypeStatusId;
    }


    
    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }
   

    public String getNewCertificateReqiestId() {
        return newCertificateReqiestId;
    }

    public void setNewCertificateReqiestId(String newCertificateReqiestId) {
        this.newCertificateReqiestId = newCertificateReqiestId;
    }

    public String getNewCertificateReqiestTypeId() {
        return newCertificateReqiestTypeId;
    }

    public void setNewCertificateReqiestTypeId(String newCertificateReqiestTypeId) {
        this.newCertificateReqiestTypeId = newCertificateReqiestTypeId;
    }

    public String[] getApprovalRequestId() {
        return approvalRequestId;
    }

    public void setApprovalRequestId(String[] approvalRequestId) {
        this.approvalRequestId = approvalRequestId;
    }


    public String getRequestTypeIdDataFormat() {
        return requestTypeIdDataFormat;
    }
    public void setRequestTypeIdDataFormat(String requestTypeIdDataFormat) {
        this.requestTypeIdDataFormat = requestTypeIdDataFormat;
    }
    public String getHiddenRequestTypeId() {
        return hiddenRequestTypeId;
    }

    public void setHiddenRequestTypeId(String hiddenRequestTypeId) {
        this.hiddenRequestTypeId = hiddenRequestTypeId;
    }

    public String[] getHiddenPersonCode() {
        return hiddenPersonCode;
    }

    public void setHiddenPersonCode(String[] hiddenPersonCode) {
        this.hiddenPersonCode = hiddenPersonCode;
    }

    //end
    public String getNewCertificatePersonCode() {
        return newCertificatePersonCode;
    }

    public void setNewCertificatePersonCode(String newCertificatePersonCode) {
        this.newCertificatePersonCode = newCertificatePersonCode;
    }
    

    public String getRejectId() {
        return rejectId;
    }

    public void setRejectId(String rejectId) {
        this.rejectId = rejectId;
    }
    

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }
    

    public String getCheckReject() {
        return checkReject;
    }

    public void setCheckReject(String checkReject) {
        this.checkReject = checkReject;
    }
    

    public String getCheckApproval() {
        return checkApproval;
    }

    public void setCheckApproval(String checkApproval) {
        this.checkApproval = checkApproval;
    }
    

    public int getHiddenlist() {
        return hiddenlist;
    }

    public void setHiddenlist(int hiddenlist) {
        this.hiddenlist = hiddenlist;
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

   
    public String getRequestTypeDetails() {
        return requestTypeDetails;
    }

    public void setRequestTypeDetails(String requestTypeDetails) {
        this.requestTypeDetails = requestTypeDetails;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public String getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(String requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }
    


    public ArrayList getRequestTypeList() {
        return requestTypeList;
    }

    public void setRequestTypeList(ArrayList requestTypeList) {
        this.requestTypeList = requestTypeList;
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
    



    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
