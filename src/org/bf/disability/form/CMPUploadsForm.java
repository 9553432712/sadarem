/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.form;

import org.apache.struts.upload.FormFile;


/**
 *
 * @author 484898
 */
public class CMPUploadsForm extends org.apache.struts.action.ActionForm {
  
    private String documentType;
    private String issueingDate;
    private String priority;
    private FormFile uploadFiles;
    private String mode;
    private String memoNumber;
    private String subject;
    private String reference;
    private String fileNumber;
    private String filt;

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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMemoNumber() {
        return memoNumber;
    }

    public void setMemoNumber(String memoNumber) {
        this.memoNumber = memoNumber;
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

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFilt() {
        return filt;
    }

    public void setFilt(String filt) {
        this.filt = filt;
    }

    

}
