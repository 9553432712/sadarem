/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dto;

import java.util.ArrayList;

/**
 *
 * @author 693461
 */
public class PhysicalRequirementsDetailsDTO {

    private String mode;
    private String district_id;
    private String mandal_id;
    private String village_id;
    private String mandal_name;
    private String village_name;
    private ArrayList mandallist = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    private String districtName;
    private String mandalName;
    private String villageName;
    private String personCode;
    private String name;
    private String relationName;
    private String fullAddress;
    private String personName;
    private String personRelationName;
    private String personalhouseNo;
   private String personalDistrictName;
   private String personalMandalName;
   private String personalVillageName;
   private String personalHabitationName;

    public String getPersonalDistrictName() {
        return personalDistrictName;
    }

    public void setPersonalDistrictName(String personalDistrictName) {
        this.personalDistrictName = personalDistrictName;
    }

    public String getPersonalHabitationName() {
        return personalHabitationName;
    }

    public void setPersonalHabitationName(String personalHabitationName) {
        this.personalHabitationName = personalHabitationName;
    }

    public String getPersonalMandalName() {
        return personalMandalName;
    }

    public void setPersonalMandalName(String personalMandalName) {
        this.personalMandalName = personalMandalName;
    }

    public String getPersonalVillageName() {
        return personalVillageName;
    }

    public void setPersonalVillageName(String personalVillageName) {
        this.personalVillageName = personalVillageName;
    }

    public String getPersonalhouseNo() {
        return personalhouseNo;
    }

    public void setPersonalhouseNo(String personalhouseNo) {
        this.personalhouseNo = personalhouseNo;
    }

  

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonRelationName() {
        return personRelationName;
    }

    public void setPersonRelationName(String personRelationName) {
        this.personRelationName = personRelationName;
    }
    
    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }
    
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
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

    public ArrayList getMandallist() {
        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
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

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }
}
