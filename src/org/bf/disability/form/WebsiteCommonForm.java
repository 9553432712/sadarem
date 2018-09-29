/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author 509865
 */
public class WebsiteCommonForm extends ActionForm {

    private String name;
    private String state;
    private String district;
    private String mandal;
    private String location;
    private String phone;
    private String email;
    private String feedback;
    private String systemip;
    private String status;
    private String rowId;
    private String createdDate;
    

    private String noofpersons;
    private String noofassessed;
    private String completedpercent;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.setName(null);
        this.setState(null);
        this.setDistrict(null);
        this.setMandal(null);
        this.setLocation(null);
        this.setPhone(null);
        this.setEmail(null);
        this.setFeedback(null);
        this.setStatus(null);
        this.setRowId(null);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * @param feedback the feedback to set
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * @return the systemip
     */
    public String getSystemip() {
        return systemip;
    }

    /**
     * @param systemip the systemip to set
     */
    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the mandal
     */
    public String getMandal() {
        return mandal;
    }

    /**
     * @param mandal the mandal to set
     */
    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the rowId
     */
    public String getRowId() {
        return rowId;
    }

    /**
     * @param rowId the rowId to set
     */
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the noofpersons
     */
    public String getNoofpersons() {
        return noofpersons;
    }

    /**
     * @param noofpersons the noofpersons to set
     */
    public void setNoofpersons(String noofpersons) {
        this.noofpersons = noofpersons;
    }

    /**
     * @return the noofassessed
     */
    public String getNoofassessed() {
        return noofassessed;
    }

    /**
     * @param noofassessed the noofassessed to set
     */
    public void setNoofassessed(String noofassessed) {
        this.noofassessed = noofassessed;
    }

    /**
     * @return the completedpercent
     */
    public String getCompletedpercent() {
        return completedpercent;
    }

    /**
     * @param completedpercent the completedpercent to set
     */
    public void setCompletedpercent(String completedpercent) {
        this.completedpercent = completedpercent;
    }
}
