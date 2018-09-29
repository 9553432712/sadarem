/*
 * ReportForm.java
 *
 */

package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

/**
 * This class contains the fields, required to populate the information on
 * certificate
 * @author svsganesh
 * @version 1.0
 */
public class ReportForm extends ActionForm {
    
    /** Creates a new instance of ReportForm */
    public ReportForm() {
    }
    private String mode;
     private String district_id;
    private String mandal_id;
    private String village_id;
    private String panchayat_id;
    private String panchayat_name;
    private String village_name;
    private String district_name;
    private String mandal_name;
    private String habitation_id;
    private String habitation_name;
    private String personcode;
    private ArrayList personcodelist = new ArrayList();
    private ArrayList districtlist=new ArrayList();
    private ArrayList mandallist=new ArrayList();
    private ArrayList panchayatlist=new ArrayList();
    private ArrayList villagelist=new ArrayList();
    private ArrayList habitationlist=new ArrayList();
    private String personid;
    private String surname;
    private String lastname;
    private String firstname;
    private String gender;
    private String relationname;
    private String name;
    private String age;
   private String conditionofdisability;
    private String conditionimprove;
    private String reassessmentrecommended;
    private int reassessmentyears;
    private int reassessmentmonth;
    private  String congenital;
    private String hereditary;

    private  String fromDate;
    private String toDate;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }


    public ArrayList getPanchayatList() {
        return panchayatList;
    }

    public void setPanchayatList(ArrayList panchayatList) {
        this.panchayatList = panchayatList;
    }
    private String birthinjury;
    private String duseaseandinfection;
    private String malnutrition;
    private String accident;
    private String othercause;
    private ArrayList districtList = new ArrayList();
    private ArrayList panchayatList = new ArrayList();

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

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelationname() {
        return relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getConditionofdisability() {
        return conditionofdisability;
    }

    public void setConditionofdisability(String conditionofdisability) {
        this.conditionofdisability = conditionofdisability;
    }

    public String getConditionimprove() {
        return conditionimprove;
    }

    public void setConditionimprove(String conditionimprove) {
        this.conditionimprove = conditionimprove;
    }

    public String getReassessmentrecommended() {
        return reassessmentrecommended;
    }

    public void setReassessmentrecommended(String reassessmentrecommended) {
        this.reassessmentrecommended = reassessmentrecommended;
    }

    public int getReassessmentyears() {
        return reassessmentyears;
    }

    public void setReassessmentyears(int reassessmentyears) {
        this.reassessmentyears = reassessmentyears;
    }

    public int getReassessmentmonth() {
        return reassessmentmonth;
    }

    public void setReassessmentmonth(int reassessmentmonth) {
        this.reassessmentmonth = reassessmentmonth;
    }

      public String getOthercause() {
        return othercause;
    }

    public void setOthercause(String othercause) {
        this.othercause = othercause;
    }

    public String getCongenital() {
        return congenital;
    }

    public void setCongenital(String congenital) {
        this.congenital = congenital;
    }

    public String getHereditary() {
        return hereditary;
    }

    public void setHereditary(String hereditary) {
        this.hereditary = hereditary;
    }

    public String getBirthinjury() {
        return birthinjury;
    }

    public void setBirthinjury(String birthinjury) {
        this.birthinjury = birthinjury;
    }

    public String getDuseaseandinfection() {
        return duseaseandinfection;
    }

    public void setDuseaseandinfection(String duseaseandinfection) {
        this.duseaseandinfection = duseaseandinfection;
    }

    public String getMalnutrition() {
        return malnutrition;
    }

    public void setMalnutrition(String malnutrition) {
        this.malnutrition = malnutrition;
    }

    public String getAccident() {
        return accident;
    }

    public void setAccident(String accident) {
        this.accident = accident;
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

    public String getVillage_id() {
        return village_id;
    }

    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    public String getPanchayat_id() {
        return panchayat_id;
    }

    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }

    public String getPanchayat_name() {
        return panchayat_name;
    }

    public void setPanchayat_name(String panchayat_name) {
        this.panchayat_name = panchayat_name;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public String getHabitation_name() {
        return habitation_name;
    }

    public void setHabitation_name(String habitation_name) {
        this.habitation_name = habitation_name;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public ArrayList getPersoncodelist() {
        return personcodelist;
    }

    public void setPersoncodelist(ArrayList personcodelist) {
        this.personcodelist = personcodelist;
    }

    public ArrayList getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(ArrayList districtlist) {
        this.districtlist = districtlist;
    }

    public ArrayList getMandallist() {
        return mandallist;
    }

    public void setMandallist(ArrayList mandallist) {
        this.mandallist = mandallist;
    }

    public ArrayList getPanchayatlist() {
        return panchayatlist;
    }

    public void setPanchayatlist(ArrayList panchayatlist) {
        this.panchayatlist = panchayatlist;
    }

    public ArrayList getVillagelist() {
        return villagelist;
    }

    public void setVillagelist(ArrayList villagelist) {
        this.villagelist = villagelist;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

   
   

   
  
}
