/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bf.disability.dto;

/**
 *
 * @author 484898
 */
public class PensionForSearchReportDTO {

    private String personcode;
    private String sadaremId;
    private String district_id;
    private String mandal_id;
    private String mandal_name;
    private String mandallist;
    private String village_id;
    private String village_name;
    private String villagelist;

    public String getDistrict_id() {
        return district_id;
    }

    public String getMandallist() {
        return mandallist;
    }

    public void setMandallist(String mandallist) {
        this.mandallist = mandallist;
    }

    public String getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(String villagelist) {
        this.villagelist = villagelist;
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

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getSadaremId() {
        return sadaremId;
    }

    public void setSadaremId(String sadaremId) {
        this.sadaremId = sadaremId;
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

 
}
