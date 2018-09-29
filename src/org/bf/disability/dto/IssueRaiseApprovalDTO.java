/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dto;

import java.util.ArrayList;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 693461
 */
public class IssueRaiseApprovalDTO {

    private String medicalBoardId;
    private String sadaremId;
    private String pensionId;
    private String rationCardNo;
    private String decription;
    private FormFile fileUpload;
    private String campId;
    private String campName;
    private ArrayList campList = new ArrayList();
    private String categoryId;
    private String categoryname;
    private ArrayList categoryList = new ArrayList();
    private int issueRaisedUpdate;
    private int issueRaisedUpload;
    private int uploadDoc;
    private int uploadCount;
    private String resloveComments;
    private String resolveuserName;
    private String resolverequestId;
    private String medicalBoardName;
    private  String resolveDescription;
    private  String pensionPhaseErrorMsg;
    private  int pensionPhase;
    private int flag;
    private  String blankPagesSadaremId;
    private  String blankPagesPensionId;
    private  int blankCount;
    private  String personStatus;
    private  String fromDate;
    private  String toDate;

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
   

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }


    public int getBlankCount() {
        return blankCount;
    }

    public void setBlankCount(int blankCount) {
        this.blankCount = blankCount;
    }

    public String getBlankPagesPensionId() {
        return blankPagesPensionId;
    }

    public void setBlankPagesPensionId(String blankPagesPensionId) {
        this.blankPagesPensionId = blankPagesPensionId;
    }

    public String getBlankPagesSadaremId() {
        return blankPagesSadaremId;
    }

    public void setBlankPagesSadaremId(String blankPagesSadaremId) {
        this.blankPagesSadaremId = blankPagesSadaremId;
    }


    public int getPensionPhase() {
        return pensionPhase;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setPensionPhase(int pensionPhase) {
        this.pensionPhase = pensionPhase;
    }

    public String getPensionPhaseErrorMsg() {
        return pensionPhaseErrorMsg;
    }

    public void setPensionPhaseErrorMsg(String pensionPhaseErrorMsg) {
        this.pensionPhaseErrorMsg = pensionPhaseErrorMsg;
    }
    public String getResolveDescription() {
        return resolveDescription;
    }

    public void setResolveDescription(String resolveDescription) {
        this.resolveDescription = resolveDescription;
    }


    public String getResolverequestId() {
        return resolverequestId;
    }

    public void setResolverequestId(String resolverequestId) {
        this.resolverequestId = resolverequestId;
    }

    public String getResolveuserName() {
        return resolveuserName;
    }

    public void setResolveuserName(String resolveuserName) {
        this.resolveuserName = resolveuserName;
    }

    public String getResloveComments() {
        return resloveComments;
    }

    public void setResloveComments(String resloveComments) {
        this.resloveComments = resloveComments;
    }

    public int getUploadCount() {
        return uploadCount;
    }

    public void setUploadCount(int uploadCount) {
        this.uploadCount = uploadCount;
    }

    public int getUploadDoc() {
        return uploadDoc;
    }

    public void setUploadDoc(int uploadDoc) {
        this.uploadDoc = uploadDoc;
    }

    public int getIssueRaisedUpdate() {
        return issueRaisedUpdate;
    }

    public void setIssueRaisedUpdate(int issueRaisedUpdate) {
        this.issueRaisedUpdate = issueRaisedUpdate;
    }

    public int getIssueRaisedUpload() {
        return issueRaisedUpload;
    }

    public void setIssueRaisedUpload(int issueRaisedUpload) {
        this.issueRaisedUpload = issueRaisedUpload;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public ArrayList getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList categoryList) {
        this.categoryList = categoryList;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getSadaremId() {
        return sadaremId;
    }

    public void setSadaremId(String sadaremId) {
        this.sadaremId = sadaremId;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public FormFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getMedicalBoardId() {
        return medicalBoardId;
    }

    public void setMedicalBoardId(String medicalBoardId) {
        this.medicalBoardId = medicalBoardId;
    }

    public String getPensionId() {
        return pensionId;
    }

    public void setPensionId(String pensionId) {
        this.pensionId = pensionId;
    }

    public String getRationCardNo() {
        return rationCardNo;
    }

    public void setRationCardNo(String rationCardNo) {
        this.rationCardNo = rationCardNo;
    }

    public String getMedicalBoardName() {
        return medicalBoardName;
    }

    public void setMedicalBoardName(String medicalBoardName) {
        this.medicalBoardName = medicalBoardName;
    }
}
