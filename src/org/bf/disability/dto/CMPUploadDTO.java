/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;

import org.apache.struts.upload.FormFile;

/**
 *
 * @author 484898
 */
public class CMPUploadDTO {

    private String documentType;
    private String issueingDate;
    private String priority;
    private FormFile uploadFiles;
    private String url;
    private String memoNumber;
    private String rowId;
    private String subject;
    private String reference;
    private String status;
    private String roleId;
    private String fileNumber;
    private String circularCount;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCircularCount() {
        return circularCount;
    }

    public void setCircularCount(String circularCount) {
        this.circularCount = circularCount;
    }

    

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getIssueingDate() {
        return issueingDate;
    }

    public void setIssueingDate(String issueingDate) {
        this.issueingDate = issueingDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public FormFile getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(FormFile uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMemoNumber() {
        return memoNumber;
    }

    public void setMemoNumber(String memoNumber) {
        this.memoNumber = memoNumber;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    

}
