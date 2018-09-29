/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author 509865
 */
public class FunctionalNeedReportForm extends ActionForm {

    public FunctionalNeedReportForm() {
    }
    private String fromdate;
    private String todate;
    private String district_id = null;
    private String mandal_id = null;
    private String village_id = null;
    private String habitation_id = null;
    private int typeofdisability;
    private int reportcategory;
    private int reportSubcategory;
    private String qualification;
    private String caste;
    private String mode;
    private ArrayList districtList = new ArrayList();
    private ArrayList campList = new ArrayList();
    private ArrayList assesmentList = new ArrayList();
    private String camp_id;
    private String camp_name;
    private String pensionPhase;
    private String urban_id="";
    private String urban_name;
    private ArrayList urbanlist = new ArrayList();
    private boolean report = false;

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public String getUrban_id() {
        return urban_id;
    }

    public void setUrban_id(String urban_id) {
        this.urban_id = urban_id;
    }

    public String getUrban_name() {
        return urban_name;
    }

    public void setUrban_name(String urban_name) {
        this.urban_name = urban_name;
    }

    public ArrayList getUrbanlist() {
        return urbanlist;
    }

    public void setUrbanlist(ArrayList urbanlist) {
        this.urbanlist = urbanlist;
    }

    public String getPensionPhase() {
        return pensionPhase;
    }

    public void setPensionPhase(String pensionPhase) {
        this.pensionPhase = pensionPhase;
    }
    private String categoryID;//3.1 report -- added status(assessed/reassessed)

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCamp_id() {
        return camp_id;
    }

    public void setCamp_id(String camp_id) {
        this.camp_id = camp_id;
    }

    public String getCamp_name() {
        return camp_name;
    }

    public void setCamp_name(String camp_name) {
        this.camp_name = camp_name;
    }

    public ArrayList getAssesmentList() {
        return assesmentList;
    }

    public void setAssesmentList(ArrayList assesmentList) {
        this.assesmentList = assesmentList;
    }

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    private ArrayList districtlist = new ArrayList();
    private ArrayList mandallist = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    private ArrayList habitationlist = new ArrayList();

    public String getFromAge() {
        return fromAge;
    }

    public void setFromAge(String fromAge) {
        this.fromAge = fromAge;
    }

    public String getToAge() {
        return toAge;
    }

    public void setToAge(String toAge) {
        this.toAge = toAge;
    }
    private String fromAge;
    private String toAge;
    //    Who Are Not Come to SADAREM Camp
    private String phase;

    /**
     * @return the fromdate
     */
    public String getFromdate() {
        return fromdate;
    }

    /**
     * @param fromdate the fromdate to set
     */
    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    /**
     * @return the todate
     */
    public String getTodate() {
        return todate;
    }

    /**
     * @param todate the todate to set
     */
    public void setTodate(String todate) {
        this.todate = todate;
    }

    /**
     * @return the district_id
     */
    public String getDistrict_id() {
        if (!"".equals(district_id)) {
            return this.district_id;
        } else {
            return null;
        }
    }

    /**
     * @param district_id the district_id to set
     */
    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    /**
     * @return the mandal_id
     */
    public String getMandal_id() {
        if (!"".equals(mandal_id)) {
            return this.mandal_id;
        } else {
            return null;
        }
    }

    /**
     * @param mandal_id the mandal_id to set
     */
    public void setMandal_id(String mandal_id) {
        this.mandal_id = mandal_id;
    }

    /**
     * @return the village_id
     */
    public String getVillage_id() {
        if (!"".equals(village_id)) {
            return this.village_id;
        } else {
            return null;
        }
    }

    /**
     * @param village_id the village_id to set
     */
    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    /**
     * @return the habitation_id
     */
    public String getHabitation_id() {
        if (!"".equals(habitation_id)) {
            return this.habitation_id;
        } else {
            return null;
        }
    }

    /**
     * @param habitation_id the habitation_id to set
     */
    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    /**
     * @return the typeofdisability
     */
    public int getTypeofdisability() {
        return typeofdisability;
    }

    /**
     * @param typeofdisability the typeofdisability to set
     */
    public void setTypeofdisability(int typeofdisability) {
        this.typeofdisability = typeofdisability;
    }

    /**
     * @return the reportcategory
     */
    public int getReportcategory() {
        return reportcategory;
    }

    /**
     * @param reportcategory the reportcategory to set
     */
    public void setReportcategory(int reportcategory) {
        this.reportcategory = reportcategory;
    }

    /**
     * @return the reportSubcategory
     */
    public int getReportSubcategory() {
        return reportSubcategory;
    }

    /**
     * @param reportSubcategory the reportSubcategory to set
     */
    public void setReportSubcategory(int reportSubcategory) {
        this.reportSubcategory = reportSubcategory;
    }

    /**
     * @return the districtlist
     */
    public ArrayList getDistrictlist() {
        return districtlist;
    }

    /**
     * @param districtlist the districtlist to set
     */
    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    /**
     * @return the mandallist
     */
    public ArrayList getMandallist() {
        return mandallist;
    }

    /**
     * @param mandallist the mandallist to set
     */
    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    /**
     * @return the villagelist
     */
    public ArrayList getVillagelist() {
        return villagelist;
    }

    /**
     * @param villagelist the villagelist to set
     */
    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }

    /**
     * @return the habitationlist
     */
    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    /**
     * @param habitationlist the habitationlist to set
     */
    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

    /**
     * @return the phase
     */
    public String getPhase() {
        return phase;
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }
}
