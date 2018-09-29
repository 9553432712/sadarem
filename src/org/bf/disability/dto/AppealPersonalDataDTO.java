/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;

/**
 *
 * @author 509862
 */
public class AppealPersonalDataDTO {

    public AppealPersonalDataDTO(){

    } private String viewedit;
    private String categoryid;
    private String rationcard="";

    public String getRationcard() {
        return rationcard;
    }

    public void setRationcard(String rationcard) {
        this.rationcard = rationcard;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    private String sid="";

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
    private String reasonforstatus;

    public String getReasonforstatus() {
        return reasonforstatus;
    }

    private String error_msg=null; //display error msg for appleat authority.

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public void setReasonforstatus(String reasonforstatus) {
        this.reasonforstatus = reasonforstatus;
    }
    private String personstatus;

    public String getPersonstatus() {
        return personstatus;
    }

    public void setPersonstatus(String personstatus) {
        this.personstatus = personstatus;
    }

    public String getViewedit() {
        return viewedit;
    }

    public void setViewedit(String viewedit) {
        this.viewedit = viewedit;
    }
private String name;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
private String gender;
private String age;
private String relationname;

    public String getRelationname() {
        return relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname;
    }
private String mandal;
private String village;
private String disability;
private String percentage;
private String personcode;
private String district;
private String status;

private String disabilitytype;

    public String getDisabilitytype() {
        return disabilitytype;
    }

    public void setDisabilitytype(String disabilitytype) {
        this.disabilitytype = disabilitytype;
    }

}
