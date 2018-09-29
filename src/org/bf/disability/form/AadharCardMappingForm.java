/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 693461
 */
public class AadharCardMappingForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String sadaremId;
    private String aadharCardId=null;
    private String[] aadharCardValue = null;
    private String aadharPersonCode;
    private String aadharCardPersonName;
    private String district_id;
    private String mandal_id;
    private String village_id;
    ArrayList mandallist = new ArrayList();
    ArrayList villagelist = new ArrayList();
    private String radioValue;
    private String typeOfInput;
    private String loginId;
    private String habitation_id;
    ArrayList habitationlist = new ArrayList();
    private String rationcardNo;

    public String getRationcardNo() {
        return rationcardNo;
    }

    public void setRationcardNo(String rationcardNo) {
        this.rationcardNo = rationcardNo;
    }

    
    private HashMap<String, Object> hMap = new HashMap<String, Object>();

    public void setDynaProperty(String key, Object value) {
        this.hMap.put(key, value);
    }

    public Object getDynaProperty(String key) {
        return this.hMap.get(key);
    }

    public HashMap<String, Object> gethMap() {
        return hMap;
    }

    public void sethMap(HashMap<String, Object> hMap) {
        this.hMap = hMap;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

    public String getTypeOfInput() {
        return typeOfInput;
    }

    public void setTypeOfInput(String typeOfInput) {
        this.typeOfInput = typeOfInput;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getMandal_id() {
        return mandal_id;
    }

    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    public ArrayList getMandallist() {
        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }

    public String getRadioValue() {
        return radioValue;
    }

    public void setRadioValue(String radioValue) {
        this.radioValue = radioValue;
    }

    public String[] getAadharCardValue() {
        return aadharCardValue;
    }

    public void setAadharCardValue(String[] aadharCardValue) {
        this.aadharCardValue = aadharCardValue;
    }

    public String getAadharPersonCode() {
        return aadharPersonCode;
    }

    public void setAadharPersonCode(String aadharPersonCode) {
        this.aadharPersonCode = aadharPersonCode;
    }

    public String getAadharCardPersonName() {
        return aadharCardPersonName;
    }

    public void setAadharCardPersonName(String aadharCardPersonName) {
        this.aadharCardPersonName = aadharCardPersonName;
    }

    public String getAadharCardId() {
        return aadharCardId;
    }

    public void setAadharCardId(String aadharCardId) {
        this.aadharCardId = aadharCardId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSadaremId() {
        return sadaremId;
    }

    public void setSadaremId(String sadaremId) {
        this.sadaremId = sadaremId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
