/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dto;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 484898
 */
public class BlogDTO {

    private String mode;
    private String name;
    private String description;
    private String mobile;
    private String email;
    private String url;
    private String systemIp;
    private List formFiles = new ArrayList();
    private FormFile file;
    private String subjectMode;
    private String subjectIdForReply;
    private String postId;
    private FormFile postUploadPhoto;
    private String subject;
    private String addSubject;

    private String subjectId;
    private String subjectName;

    public String getSubjectMode() {
        return subjectMode;
    }

    public void setSubjectMode(String subjectMode) {
        this.subjectMode = subjectMode;
    }

    public List getFormFiles() {
        return formFiles;
    }

    public void setImageList(List imageList) {
        this.formFiles = imageList;
    }

    public void setFormFiles(List formFiles) {
        this.formFiles = formFiles;
    }

    public List getUploadPhoto() {
        return this.formFiles;
    }

    public void setUploadPhoto(int iIndex, FormFile formFile) {
        if (!formFile.getFileName().equals("")) {
            if (this.formFiles.size() <= iIndex) {
                // Fill the list to the specified size
                for (int i = this.formFiles.size(); i < iIndex; i++) {
                    this.formFiles.add(null);
                }
                this.formFiles.add(formFile);

            } else {
                this.formFiles.set(iIndex, formFile);
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSystemIp() {
        return systemIp;
    }

    public void setSystemIp(String systemIp) {
        this.systemIp = systemIp;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public String getSubjectIdForReply() {
        return subjectIdForReply;
    }

    public void setSubjectIdForReply(String subjectIdForReply) {
        this.subjectIdForReply = subjectIdForReply;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public FormFile getPostUploadPhoto() {
        return postUploadPhoto;
    }

    public void setPostUploadPhoto(FormFile postUploadPhoto) {
        this.postUploadPhoto = postUploadPhoto;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAddSubject() {
        return addSubject;
    }

    public void setAddSubject(String addSubject) {
        this.addSubject = addSubject;
    }
    
}
