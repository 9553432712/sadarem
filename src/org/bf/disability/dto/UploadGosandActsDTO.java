/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dto;

import java.util.ArrayList;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 695048
 */
public class UploadGosandActsDTO {

    private String mode;
    private String goDepartment;
    private String goSection;
    private String goType;
    private String goNumber;
    private String goCategory;
    private FormFile uploadGosActs;
    private String goSubject;
    private String rowId;
    private String uploadFile;
    private String value;
    private String dateofApplication;
    private ArrayList subSectionList = new ArrayList();
    private String sub_section;
    private String subSection;
    private String subject_id;
    private String subjectName;
    private ArrayList subjectList = new ArrayList();
    private String existing_New;
    private ArrayList departmentList = new ArrayList();
    private ArrayList departmentList1 = new ArrayList();
    private String departmentName;
    private String godescription;
    private String loginId;

    public String getGoSubject() {
        return goSubject;
    }

    public void setGoSubject(String goSubject) {
        this.goSubject = goSubject;
    }

    public ArrayList getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(ArrayList departmentList) {
        this.departmentList = departmentList;
    }

    public ArrayList getDepartmentList1() {
        return departmentList1;
    }

    public void setDepartmentList1(ArrayList departmentList1) {
        this.departmentList1 = departmentList1;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getGodescription() {
        return godescription;
    }

    public void setGodescription(String godescription) {
        this.godescription = godescription;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getGoCategory() {
        return goCategory;
    }

    public void setGoCategory(String goCategory) {
        this.goCategory = goCategory;
    }

    public String getGoDepartment() {
        return goDepartment;
    }

    public void setGoDepartment(String goDepartment) {
        this.goDepartment = goDepartment;
    }

    public String getGoNumber() {
        return goNumber;
    }

    public void setGoNumber(String goNumber) {
        this.goNumber = goNumber;
    }

    public String getGoSection() {
        return goSection;
    }

    public void setGoSection(String goSection) {
        this.goSection = goSection;
    }

    public String getGoType() {
        return goType;
    }

    public void setGoType(String goType) {
        this.goType = goType;
    }

    public FormFile getUploadGosActs() {
        return uploadGosActs;
    }

    public void setUploadGosActs(FormFile uploadGosActs) {
        this.uploadGosActs = uploadGosActs;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSubSection() {
        return subSection;
    }

    public void setSubSection(String subSection) {
        this.subSection = subSection;
    }

    public ArrayList getSubSectionList() {
        return subSectionList;
    }

    public void setSubSectionList(ArrayList subSectionList) {
        this.subSectionList = subSectionList;
    }

    public String getSub_section() {
        return sub_section;
    }

    public void setSub_section(String sub_section) {
        this.sub_section = sub_section;
    }

    public ArrayList getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(ArrayList subjectList) {
        this.subjectList = subjectList;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getDateofApplication() {
        return dateofApplication;
    }

    public void setDateofApplication(String dateofApplication) {
        this.dateofApplication = dateofApplication;
    }

    public String getExisting_New() {
        return existing_New;
    }

    public void setExisting_New(String existing_New) {
        this.existing_New = existing_New;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
}
