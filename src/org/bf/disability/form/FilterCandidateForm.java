/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author vijay
 */
public class FilterCandidateForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String district_id;
    private String district_name;
    private ArrayList districtList = new ArrayList();
    private String[] pensionids;
    private String village_id;
    private String village_name;
    private String[] sadaremID;
    private ArrayList villageList = new ArrayList();
    private String[] pensionCardDuplicate;

    public String[] getSadaremID() {
        return sadaremID;
    }

    public void setSadaremID(String[] sadaremID) {
        this.sadaremID = sadaremID;
    }

   

    
    public ArrayList getVillageList() {
        return villageList;
    }

    public void setVillageList(ArrayList villageList) {
        this.villageList = villageList;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String[] getPensionids() {
        return pensionids;
    }

    public void setPensionids(String[] pensionids) {
        this.pensionids = pensionids;
    }

   
    private String[] pensionCardNo;
    private String mandal_id;
    private String mandal_name;
    private ArrayList mandalList = new ArrayList();
    private String duplicatePensioncodes;

    public String getDuplicatePensioncodes() {
        return duplicatePensioncodes;
    }

    public void setDuplicatePensioncodes(String duplicatePensioncodes) {
        this.duplicatePensioncodes = duplicatePensioncodes;
    }

    public String[] getPensionCardNo() {
        return pensionCardNo;
    }

    public void setPensionCardNo(String[] pensionCardNo) {
        this.pensionCardNo = pensionCardNo;
    }

    public ArrayList getMandalList() {
        return mandalList;
    }

    public void setMandalList(ArrayList mandalList) {
        this.mandalList = mandalList;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String[] getPensionCardDuplicate() {
        return pensionCardDuplicate;
    }

    public void setPensionCardDuplicate(String[] pensionCardDuplicate) {
        this.pensionCardDuplicate = pensionCardDuplicate;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
       

    }




}
