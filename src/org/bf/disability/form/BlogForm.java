/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 484898
 */
public class BlogForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String name;
    private String description;
    private String mobile;
    private String email;
    private List formFiles = new ArrayList();
    private FormFile postUploadPhoto;
    private String subjectId;
    private String subjectName;
    private ArrayList subjects = new ArrayList();
    private String subject;
    private String addSubject;

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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList subjects) {
        this.subjects = subjects;
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
