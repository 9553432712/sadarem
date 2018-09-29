/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 310926
 */
public class NewCertificateForm extends org.apache.struts.action.ActionForm {

    private String mode;
    private String formno;
    private String fromdate;
    private String surname;
    private String firstname;
    private String surnameenglish;
    private String surnametelugu;
    private String noOfYears;
    private String day;
    private String month;
    private String year;
    private String gender;
    private String education;
    private String employment;
    private String marital;
    private String caste;
    private String religion;
    private String card;
    private String rtype;
    private String rationCardSlno;
    private boolean epiccard;
    private String epiccardno;
    private boolean pensioncard;
    private String pension_type;
    private String pensioncardno;
    private String mole1;
    private String mole2;
    private String parents_marriage;
    private String gsurname;
    private int grelation;
    private String firstnameenglish;
    private String firstnametelugu;
    private String houseno;
    private String district_id;
    private String mandal_id;
    private ArrayList mandallist = new ArrayList();
    private String village_id;
    private ArrayList villagelist = new ArrayList();
    private String habitation_id;
    private ArrayList habitationlist = new ArrayList();
    private ArrayList panchayatlist;
    private String panchayat_id;
    private String phoneno;
    private String emailId;
    private String pin;
    private String type_disability;
    private String existingpercentage;
    private String loginid;
    private int campid;
    private String habCode;
    private String dobday;
    private String systemip;
    private String telugupersonname;
    private String telugufathername;
    private String personstatus;
    private String district;
    private String aadharCardNo;

    public String getAadharCardNo() {
        return aadharCardNo;
    }

    public void setAadharCardNo(String aadharCardNo) {
        this.aadharCardNo = aadharCardNo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPersonstatus() {
        return personstatus;
    }

    public void setPersonstatus(String personstatus) {
        this.personstatus = personstatus;
    }

    public String getTelugufathername() {
        return telugufathername;
    }

    public void setTelugufathername(String telugufathername) {
        this.telugufathername = telugufathername;
    }

    public String getTelugupersonname() {
        return telugupersonname;
    }

    public void setTelugupersonname(String telugupersonname) {
        this.telugupersonname = telugupersonname;
    }

    public String getDobday() {
        return dobday;
    }

    public void setDobday(String dobday) {
        this.dobday = dobday;
    }

    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    public String getHabCode() {
        return habCode;
    }

    public void setHabCode(String habCode) {
        this.habCode = habCode;
    }

    public int getCampid() {
        return campid;
    }

    public void setCampid(int campid) {
        this.campid = campid;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public boolean isEpiccard() {
        return epiccard;
    }

    public void setEpiccard(boolean epiccard) {
        this.epiccard = epiccard;
    }

    public String getEpiccardno() {
        return epiccardno;
    }

    public void setEpiccardno(String epiccardno) {
        this.epiccardno = epiccardno;
    }

    public String getExistingpercentage() {
        return existingpercentage;
    }

    public void setExistingpercentage(String existingpercentage) {
        this.existingpercentage = existingpercentage;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstnameenglish() {
        return firstnameenglish;
    }

    public void setFirstnameenglish(String firstnameenglish) {
        this.firstnameenglish = firstnameenglish;
    }

    public String getFirstnametelugu() {
        return firstnametelugu;
    }

    public void setFirstnametelugu(String firstnametelugu) {
        this.firstnametelugu = firstnametelugu;
    }

    public String getFormno() {
        return formno;
    }

    public void setFormno(String formno) {
        this.formno = formno;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getGrelation() {
        return grelation;
    }

    public void setGrelation(int grelation) {
        this.grelation = grelation;
    }

    public String getGsurname() {
        return gsurname;
    }

    public void setGsurname(String gsurname) {
        this.gsurname = gsurname;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
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

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMole1() {
        return mole1;
    }

    public void setMole1(String mole1) {
        this.mole1 = mole1;
    }

    public String getMole2() {
        return mole2;
    }

    public void setMole2(String mole2) {
        this.mole2 = mole2;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNoOfYears() {
        return noOfYears;
    }

    public void setNoOfYears(String noOfYears) {
        this.noOfYears = noOfYears;
    }

    public String getPanchayat_id() {
        return panchayat_id;
    }

    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }

    public ArrayList getPanchayatlist() {
        return panchayatlist;
    }

    public void setPanchayatlist(ArrayList panchayatlist) {
        this.panchayatlist = panchayatlist;
    }

    public String getParents_marriage() {
        return parents_marriage;
    }

    public void setParents_marriage(String parents_marriage) {
        this.parents_marriage = parents_marriage;
    }

    public String getPension_type() {
        return pension_type;
    }

    public void setPension_type(String pension_type) {
        this.pension_type = pension_type;
    }

    public boolean isPensioncard() {
        return pensioncard;
    }

    public void setPensioncard(boolean pensioncard) {
        this.pensioncard = pensioncard;
    }

    public String getPensioncardno() {
        return pensioncardno;
    }

    public void setPensioncardno(String pensioncardno) {
        this.pensioncardno = pensioncardno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getRationCardSlno() {
        return rationCardSlno;
    }

    public void setRationCardSlno(String rationCardSlno) {
        this.rationCardSlno = rationCardSlno;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurnameenglish() {
        return surnameenglish;
    }

    public void setSurnameenglish(String surnameenglish) {
        this.surnameenglish = surnameenglish;
    }

    public String getSurnametelugu() {
        return surnametelugu;
    }

    public void setSurnametelugu(String surnametelugu) {
        this.surnametelugu = surnametelugu;
    }

    public String getType_disability() {
        return type_disability;
    }

    public void setType_disability(String type_disability) {
        this.type_disability = type_disability;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
    }

    public void reset() {
    }
}
