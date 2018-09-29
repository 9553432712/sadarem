/*
 * TerritoryInformationDTO.java
 *
 * Created on January 21, 2009, 4:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.dto;

import java.util.ArrayList;


public class TerritoryInformationDTO {
    
    private String modifywhom;
    private String districtid;
    private String mandalid;
    private String assemblyid;
    private String panchayatid;
    private String villageid;
    private String habitationid;
    private String modifiedname;

    public ArrayList getHabitationListData() {
        return habitationListData;
    }

    public void setHabitationListData(ArrayList habitationListData) {
        this.habitationListData = habitationListData;
    }

    public String getHabitation_idData() {
        return habitation_idData;
    }

    public void setHabitation_idData(String habitation_idData) {
        this.habitation_idData = habitation_idData;
    }

    public String getHabitation_nameData() {
        return habitation_nameData;
    }

    public void setHabitation_nameData(String habitation_nameData) {
        this.habitation_nameData = habitation_nameData;
    }

    public ArrayList getMandalListData() {
        return mandalListData;
    }

    public void setMandalListData(ArrayList mandalListData) {
        this.mandalListData = mandalListData;
    }

    public String getMandal_idData() {
        return mandal_idData;
    }

    public void setMandal_idData(String mandal_idData) {
        this.mandal_idData = mandal_idData;
    }

    public String getMandal_nameData() {
        return mandal_nameData;
    }

    public void setMandal_nameData(String mandal_nameData) {
        this.mandal_nameData = mandal_nameData;
    }

    public ArrayList getVillageListData() {
        return villageListData;
    }

    public void setVillageListData(ArrayList villageListData) {
        this.villageListData = villageListData;
    }

    public String getVillage_idData() {
        return village_idData;
    }

    public void setVillage_idData(String village_idData) {
        this.village_idData = village_idData;
    }

    public String getVillage_nameData() {
        return village_nameData;
    }

    public void setVillage_nameData(String village_nameData) {
        this.village_nameData = village_nameData;
    }
    private String addwhom;
    private String newterritory;
    private String ipaddress;
    private ArrayList mandalListData;
    private ArrayList villageListData;
    private ArrayList habitationListData;
    private String mandal_nameData;
    private String village_nameData;
    private String habitation_nameData;
     private String habitation_idData;
     private String mandal_idData;
     private String village_idData;
    public String getModifywhom() {
        return modifywhom;
    }

    public void setModifywhom(String modifywhom) {
        this.modifywhom = modifywhom;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getMandalid() {
        return mandalid;
    }

    public void setMandalid(String mandalid) {
        this.mandalid = mandalid;
    }

    public String getAssemblyid() {
        return assemblyid;
    }

    public void setAssemblyid(String assemblyid) {
        this.assemblyid = assemblyid;
    }

    public String getPanchayatid() {
        return panchayatid;
    }

    public void setPanchayatid(String panchayatid) {
        this.panchayatid = panchayatid;
    }

    public String getVillageid() {
        return villageid;
    }

    public void setVillageid(String villageid) {
        this.villageid = villageid;
    }

    public String getHabitationid() {
        return habitationid;
    }

    public void setHabitationid(String habitationid) {
        this.habitationid = habitationid;
    }

    public String getModifiedname() {
        return modifiedname;
    }

    public void setModifiedname(String modifiedname) {
        this.modifiedname = modifiedname;
    }

    public String getAddwhom() {
        return addwhom;
    }

    public void setAddwhom(String addwhom) {
        this.addwhom = addwhom;
    }

    public String getNewterritory() {
        return newterritory;
    }

    public void setNewterritory(String newterritory) {
        this.newterritory = newterritory;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
            
}
