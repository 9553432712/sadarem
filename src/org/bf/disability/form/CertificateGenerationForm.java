/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 728056
 */
public class CertificateGenerationForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String mandal;
    private String village;
    private String habitation;
    private ArrayList mandalList=new ArrayList();
    private ArrayList villageList=new ArrayList();
    private ArrayList habitationList=new ArrayList();
    private String mandalname;
    private String villagename;
    private String habitationname;
    private String[] checkbox;
    private String sub1;
    private String[] remarks;
    private  String[] fromdate;
     private String districtId;
      private String loginId;

       private HashMap<String, Object> hMap = new HashMap<String, Object>();
    public CertificateGenerationForm() {
    }
    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getHabitation() {
        return habitation;
    }

    public void setHabitation(String habitation) {
        this.habitation = habitation;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public ArrayList getHabitationList() {
        return habitationList;
    }

    public void setHabitationList(ArrayList habitationList) {
        this.habitationList = habitationList;
    }

    public String getHabitationname() {
        return habitationname;
    }

    public void setHabitationname(String habitationname) {
        this.habitationname = habitationname;
    }

    public ArrayList getMandalList() {
        return mandalList;
    }

    public void setMandalList(ArrayList mandalList) {
        this.mandalList = mandalList;
    }

    public String getMandalname() {
        return mandalname;
    }

    public void setMandalname(String mandalname) {
        this.mandalname = mandalname;
    }

    public ArrayList getVillageList() {
        return villageList;
    }

    public void setVillageList(ArrayList villageList) {
        this.villageList = villageList;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String[] getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String[] checkbox) {
        this.checkbox = checkbox;
    }

    public String getSub1() {
        return sub1;
    }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }

    public String[] getFromdate() {
        return fromdate;
    }

    public void setFromdate(String[] fromdate) {
        this.fromdate = fromdate;
    }

    public String[] getRemarks() {
        return remarks;
    }

    public void setRemarks(String[] remarks) {
        this.remarks = remarks;
    }

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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
   public void reset(int noOfRows, int columns) {
        for (int i = 0; i < noOfRows; i++) {
            for (int k = 1; k <= columns; k++) {
                setDynaProperty(i + "#" + (k), "");
            }
        }
    }
}
