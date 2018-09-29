/*
 * PartAForm.java
 *
 */
package org.bf.disability.form;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * This class contains the fields, required to hold the person with disabilities
 * personal information
 * @version 1.0
 */
public class PartAForm extends ActionForm {

    //Appeal authrity parameters;
    private String rad;
    private String rationcard = "";
    private String pensioncode;
    private String rationCardSlno;
    private String mode;
    private String[] rationCardslNo;
    private String[] rationCardHidden;
    private String rationCardNo;
    private String pension;
    private String aadharCardNo;
    private String more = null;
    // Balu Start
    private String typeOfSearch = null;
    private ArrayList yearList = new ArrayList();
    private ArrayList monthList = new ArrayList();
    private String financialYear = null;
    private ArrayList financialYearList = new ArrayList();
    // Balu End
    private String partaCampId;
    private String partaCampDate;
    private String noOfdisabilities;
    private String[] maultipleDisabilitysId;
    private String noofdropdownValues;
    private String parameter;
    private int noofrowvalue;
    ArrayList campsList = new ArrayList();
    private HashMap<String, Object> hMap = new HashMap<String, Object>();
    private FormFile formFile;
    private FormFile formFile1;
    private String certificateType = null;
    private String filePath = null;
    private String personcodePopulateId;
    private float disabilityperscentage;
    private String base64Photo;
    private String remarks ;
    
    public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }

    public String getPartaCampId() {
        return partaCampId;
    }

    public void setPartaCampId(String partaCampId) {
        this.partaCampId = partaCampId;
    }

    public int getNoofrowvalue() {
        return noofrowvalue;
    }

    public void setNoofrowvalue(int noofrowvalue) {
        this.noofrowvalue = noofrowvalue;
    }

    public ArrayList getCampsList() {
        return campsList;
    }

    public void setCampsList(ArrayList campsList) {
        this.campsList = campsList;
    }

    public String getNoofdropdownValues() {
        return noofdropdownValues;
    }

    public void setNoofdropdownValues(String noofdropdownValues) {
        this.noofdropdownValues = noofdropdownValues;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public HashMap<String, Object> gethMap() {
        return hMap;
    }

    public void sethMap(HashMap<String, Object> hMap) {
        this.hMap = hMap;
    }

    public void setDynaProperty(String key, Object value) {
        this.hMap.put(key, value);
    }

    public Object getDynaProperty(String key) {
        return this.hMap.get(key);
    }

    public String getNoOfdisabilities() {
        return noOfdisabilities;
    }

    public void setNoOfdisabilities(String noOfdisabilities) {
        this.noOfdisabilities = noOfdisabilities;
    }

    public String getPartaCampDate() {
        return partaCampDate;
    }

    public void setPartaCampDate(String partaCampDate) {
        this.partaCampDate = partaCampDate;
    }

    public String[] getMaultipleDisabilitysId() {
        return maultipleDisabilitysId;
    }

    public void setMaultipleDisabilitysId(String[] maultipleDisabilitysId) {
        this.maultipleDisabilitysId = maultipleDisabilitysId;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getAadharCardNo() {
        return aadharCardNo;
    }

    public void setAadharCardNo(String aadharCardNo) {
        this.aadharCardNo = aadharCardNo;
    }

    public String getPhaseIII() {
        return phaseIII;
    }

    public void setPhaseIII(String phaseIII) {
        this.phaseIII = phaseIII;
    }

    public String getPhaseIIIAR() {
        return phaseIIIAR;
    }

    public void setPhaseIIIAR(String phaseIIIAR) {
        this.phaseIIIAR = phaseIIIAR;
    }

    public String getPhaseIIIAssessed() {
        return phaseIIIAssessed;
    }

    public void setPhaseIIIAssessed(String phaseIIIAssessed) {
        this.phaseIIIAssessed = phaseIIIAssessed;
    }

    public String getPhaseIIIDR() {
        return phaseIIIDR;
    }

    public void setPhaseIIIDR(String phaseIIIDR) {
        this.phaseIIIDR = phaseIIIDR;
    }

    public String getPhaseIIIEligible() {
        return phaseIIIEligible;
    }

    public void setPhaseIIIEligible(String phaseIIIEligible) {
        this.phaseIIIEligible = phaseIIIEligible;
    }
    private String phaseIII;
    private String phaseIIIAssessed;
    private String phaseIIIDR;
    private String phaseIIIAR;
    private String phaseIIIEligible;
//added by Rekha
    private String selectFlow;

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public String getRad() {
        return rad;
    }

    public void setRad(String rad) {
        this.rad = rad;
    }

    public String getRationCardNo() {
        return rationCardNo;
    }

    public void setRationCardNo(String rationCardNo) {
        this.rationCardNo = rationCardNo;
    }

    public String[] getRationCardHidden() {
        return rationCardHidden;
    }

    public void setRationCardHidden(String[] rationCardHidden) {
        this.rationCardHidden = rationCardHidden;
    }

    public String[] getRationCardslNo() {
        return rationCardslNo;
    }

    public void setRationCardslNo(String[] rationCardslNo) {
        this.rationCardslNo = rationCardslNo;
    }

    public String getSelectFlow() {
        return selectFlow;
    }

    public void setSelectFlow(String selectFlow) {
        this.selectFlow = selectFlow;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRationCardSlno() {
        return rationCardSlno;
    }

    public void setRationCardSlno(String rationCardSlno) {
        this.rationCardSlno = rationCardSlno;
    }

    public String getPensioncode() {
        return pensioncode;
    }

    public void setPensioncode(String pensioncode) {
        this.pensioncode = pensioncode;
    }

    public String getRationcard() {
        return rationcard;
    }

    public void setRationcard(String rationcard) {
        this.rationcard = rationcard;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    private String sid = "";
    private String viewedit;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getRelationname() {
        return relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getViewedit() {
        return viewedit;
    }

    public void setViewedit(String viewedit) {
        this.viewedit = viewedit;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
    private String disabilitytype;

    public String getDisabilitytype() {
        return disabilitytype;
    }

    public void setDisabilitytype(String disabilitytype) {
        this.disabilitytype = disabilitytype;
    }
    private String age;
    private String relationname;
    private String village;
    private String disability;
    private String percentage;
    private String categoryid;
    private String category;

    public String getSearchPage() {
        return SearchPage;
    }

    public void setSearchPage(String SearchPage) {
        this.SearchPage = SearchPage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getrMonths() {
        return rMonths;
    }

    public void setrMonths(String rMonths) {
        this.rMonths = rMonths;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
    private String status;
    private String sadaremId;
    private String name;
    private String parentsName;
    private String disabilityName;
    private int disabilityLocoSubId;
    private String disabilityLocoSubName;
    private int disabilityLocoSubSubId;
    private String disabilityLocoSubSubName;
    private int disabilityVisualSubId;
    private String disabilityVisualSubName;
    private int disabilityHearingSubId;
    private String disabilityHearingSubName;
    private int disabilityMentalRetdSubId;
    private String disabilityMentalRetdSubName;
    private int disabilityMentalIllnessSubId;
    private String disabilityMentalIllnessSubName;
    private String SearchPage;
    private int disabilityId;
    private String disabilitySubIds;
    private String disabilitySubSubIds;
    private ArrayList disabilityList = new ArrayList();
    private ArrayList disabilityLocoSubList = new ArrayList();
    private ArrayList disabilityLocoSubSubList = new ArrayList();
    private ArrayList disabilityVisualSubList = new ArrayList();
    private ArrayList disabilityHearingSubList = new ArrayList();
    private ArrayList disabilityMentalRetdSubList = new ArrayList();
    private ArrayList disabilityMentalIllnessSubList = new ArrayList();
    private int[] disabilityLocoSubIds;
    private int[] disabilityLocoSubSubIds;
    private int[] conditiondisability;
    private String conditiondisabilityIds;
    private boolean congenitalbettereye;
    private boolean congenitalworseeye;
    private boolean hereditarybettereye;
    private boolean hereditaryworseeye;
    private boolean birthinjurybettereye;
    private boolean birthinjuryworseeye;
    private boolean diseaseInfectionbettereye;
    private boolean diseaseInfectionworseeye;
    private boolean malnutritionbettereye;
    private boolean malnutritionworseeye;
    private boolean accidentbettereye;
    private boolean accidentworseeye;
    private String formno;
    private String fromdate;
    private String todate;
    private String hospitalname;
    private String hospitaladdress;
    private String firstname;
    private String surname;
    private String lastname;
    private String othertypeofdisability;
    private String conditionindisability;
    private String reassessmentyears;
    private String rMonths;
    private String months;
    private String otherconditionofdisability;
    private String noOfDisableperson;
    private int[] relation;
    private String relationStr;
    private String noOfYears;
    private String dobday;
    private String gender;
    private String day;
    private String month;
    private String year;
    private String education;
    private String edustatus;
    private String employment;
    private String anualIncome;
    private String marital;
    private String caste;
    private String religion;
    private String card = null;
    private String rtype;
    //newly added
    private String housingtype;
    private String bloodgroup;
    private String hallticket;
    private String pwdno;
    private String nregcardno;
    private String arogyasricardno;
    private String epiccardno;
    private String shgno;
    private boolean nregcard;
    private boolean arogyasricard;
    private boolean epiccard;
    private String isl;
    private String doctor1name;
    private String doctor1regnumber;
    private String doctor1designation;
    private String doctor2name;
    private String doctor2regnumber;
    private String doctor2designation;
    private String doctor3name;
    private String doctor3regnumber;
    private String doctor3designation;
    // Multiple Doctors information added by siva
    private int multipledisabilityId;
    private String doctornameOH;
    private String doctorregnumberOH;
    private String doctordesignationOH;
    private String doctornameVI;
    private String doctorregnumberVI;
    private String doctordesignationVI;
    private String doctornameHI;
    private String doctorregnumberHI;
    private String doctordesignationHI;
    private String doctornameMR;
    private String doctorregnumberMR;
    private String doctordesignationMR;
    private String doctornameMI;
    private String doctorregnumberMI;
    private String doctordesignationMI;
    private String ohdoctor;
    private String vidoctor;
    private String hidoctor;
    private String mrdoctor;
    private String midoctor;
    private String mole1;
    private String mole2;
    private String specialistprefix;
    private String gsurname;
    private String grelation;
    private String gageYears;
    private String geducation;
    private String gemployement;
    private String gincome;
    private String houseno;
    private String townVillage;
    private String mandal;
    private String district;
    private String phoneno = null;
    private String email = null;
    private String pin = null;
    private String type_disability;
    private String existingpercentage = null;
    private String systemip;
    private String loginid;
    private String personcode;
    private String panchaith;
    private String habitation;
    private boolean reassessment;
    private boolean yesanyDisableperson;
    private boolean congenital;
    private boolean hereditary;
    private boolean birthinjury;
    private boolean diseaseInfection;
    private boolean malnutrition;
    private boolean accident;
    private String diagnosisofdisability;
    private String kindofdisability;
    private String parents_marriage;
    private String leprosy_cured;
    private String personstatus;
    private String pensionPhase;
    private String telugupersonname;
    private String telugufathername;
    private String suggessionone;
    private String suggessiontwo;
    private String suggessionthree;
    private boolean pensioncard;
    private String pensioncardno;
    private String pension_type;
    private String yearsfortemporary;
    private boolean congenitalvisual;
    private boolean hereditaryvisual;
    private boolean birthinjuryvisual;
    private boolean diseaseInfectionvisual;
    private boolean malnutritionvisual;
    private boolean accidentvisual;
    //Rejected Person Details
    private String referredto;
    private String surgery;
    private String councellingandguidance;
    private String speechtherapy;
    private String hearingaid;
    private String behaviourmodification;
    private String phychotherapy;
    private String admissioninpsychiatrichospital;
    private String Physiotherapy;
    private String lowvisionaid;
    private String anyotherneed;
    // added for display existing teluguname
    private String surnametelugu;
    private String firstnametelugu;
    private String typeofdisability;
    private String f_can;
    private String pp_can;
    private String l_can;
    private String kc_can;
    private String b_can;
    private String rw_can;
    private String s_can;
    private String st_can;
    private String w_can;
    private String se_can;
    private String h_can;
    private String condition = null;
    // added for Camp venu
    private String camp_venue;
    private int campid;
    // added two cause of disanbilities.
    private boolean highfever;
    private boolean epilepsy;
    private boolean birthasphyxia;
    private String uptohabitation;
    private String uptopersoncode;
    //for update territory and generate new personecode
    private String habitation_name;
    private String village_name;
    private String mandal_name;
    private ArrayList districtlist = new ArrayList();
    private ArrayList mandallist = new ArrayList();
    private ArrayList panchayatlist = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    private ArrayList habitationlist = new ArrayList();
    private String existingpensioners;
    private String partacount;
    private String ortho;
    private String hearing;
    private String visual;
    private String mentalretardation;
    private String mentalillness;
    private String multipledisability;
    private String districtid;
    private String operatorStatus;
    // Newly Added for PersonCode Generation Based on Panchayith and assembly
    private String panchayatiid;
    private String assemblyid;
    private String district_id;
    private String mandal_id;
    private String village_id;
    private String panchayat_id;
    private String habitation_id;
    private String reasonforstatus;
    private String habCode;
    private String mdspecialistprefix;
    private String[] tagSadarem;

    public String[] getTagSadarem() {
        return tagSadarem;
    }

    public void setTagSadarem(String[] tagSadarem) {
        this.tagSadarem = tagSadarem;
    }
    private String isExist;

    public String getIsExist() {
        return isExist;
    }

    public void setIsExist(String isExist) {
        this.isExist = isExist;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {


        setNregcard(false);
        setArogyasricard(false);
        setEpiccard(false);
        setPensioncard(false);
        setNregcard(false);
        setArogyasricard(false);
        setCongenital(false);
        setHereditary(false);
        setBirthinjury(false);
        setDiseaseInfection(false);
        setMalnutrition(false);
        setAccident(false);

        this.disabilityLocoSubIds = null;
        this.disabilityLocoSubSubIds = null;

    }

    public void formReset(ActionMapping mapping, HttpServletRequest request) {


        setFormno(null);
        setFirstname(null);
        setSurname(null);
        setSurnametelugu(null);
        setGrelation("0");
        setGsurname(null);
        setFirstnametelugu(null);
        setMandal_id("0");
        setPanchayat_id("0");
        setVillage_id("0");
        setHabitation_id("0");
        setCard(null);
        setRationCardSlno(null);
        setType_disability("");
        setYear(null);
        setMarital("");
        setGender("");
        setMole1(null);
        setMole2(null);
        setAadharCardNo(null);
        setPhoneno(null);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public ArrayList getDisabilityList() {
        return disabilityList;
    }

    public void setDisabilityList(ArrayList disabilityList) {
        this.disabilityList = disabilityList;
    }

    public ArrayList getDisabilityLocoSubList() {
        return disabilityLocoSubList;
    }

    public void setDisabilityLocoSubList(ArrayList disabilityLocoSubList) {
        this.disabilityLocoSubList = disabilityLocoSubList;
    }

    public ArrayList getDisabilityLocoSubSubList() {
        return disabilityLocoSubSubList;
    }

    public void setDisabilityLocoSubSubList(ArrayList disabilityLocoSubSubList) {
        this.disabilityLocoSubSubList = disabilityLocoSubSubList;
    }

    public String getSadaremId() {
        return sadaremId;
    }

    public void setSadaremId(String sadaremId) {
        this.sadaremId = sadaremId;
    }

    public ArrayList getDisabilityVisualSubList() {
        return disabilityVisualSubList;
    }

    public void setDisabilityVisualSubList(ArrayList disabilityVisualSubList) {
        this.disabilityVisualSubList = disabilityVisualSubList;
    }

    public ArrayList getDisabilityHearingSubList() {
        return disabilityHearingSubList;
    }

    public void setDisabilityHearingSubList(ArrayList disabilityHearingSubList) {
        this.disabilityHearingSubList = disabilityHearingSubList;
    }

    public ArrayList getDisabilityMentalRetdSubList() {
        return disabilityMentalRetdSubList;
    }

    public void setDisabilityMentalRetdSubList(ArrayList disabilityMentalRetdSubList) {
        this.disabilityMentalRetdSubList = disabilityMentalRetdSubList;
    }

    public ArrayList getDisabilityMentalIllnessSubList() {
        return disabilityMentalIllnessSubList;
    }

    public void setDisabilityMentalIllnessSubList(ArrayList disabilityMentalIllnessSubList) {
        this.disabilityMentalIllnessSubList = disabilityMentalIllnessSubList;
    }

    public int getDisabilityId() {
        return disabilityId;
    }

    public void setDisabilityId(int disabilityId) {
        this.disabilityId = disabilityId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int[] getDisabilityLocoSubIds() {
        return disabilityLocoSubIds;
    }

    public void setDisabilityLocoSubIds(int[] disabilityLocoSubIds) {
        this.disabilityLocoSubIds = disabilityLocoSubIds;
    }

    public int[] getDisabilityLocoSubSubIds() {
        return disabilityLocoSubSubIds;
    }

    public void setDisabilityLocoSubSubIds(int[] disabilityLocoSubSubIds) {
        this.disabilityLocoSubSubIds = disabilityLocoSubSubIds;
    }

    public int getDisabilityVisualSubId() {
        return disabilityVisualSubId;
    }

    public void setDisabilityVisualSubId(int disabilityVisualSubId) {
        this.disabilityVisualSubId = disabilityVisualSubId;
    }

    public int getDisabilityHearingSubId() {
        return disabilityHearingSubId;
    }

    public void setDisabilityHearingSubId(int disabilityHearingSubId) {
        this.disabilityHearingSubId = disabilityHearingSubId;
    }

    public int getDisabilityMentalRetdSubId() {
        return disabilityMentalRetdSubId;
    }

    public void setDisabilityMentalRetdSubId(int disabilityMentalRetdSubId) {
        this.disabilityMentalRetdSubId = disabilityMentalRetdSubId;
    }

    public int getDisabilityMentalIllnessSubId() {
        return disabilityMentalIllnessSubId;
    }

    public void setDisabilityMentalIllnessSubId(int disabilityMentalIllnessSubId) {
        this.disabilityMentalIllnessSubId = disabilityMentalIllnessSubId;
    }

    public String getDisabilitySubIds() {
        return disabilitySubIds;
    }

    public void setDisabilitySubIds(String disabilitySubIds) {
        this.disabilitySubIds = disabilitySubIds;
    }

    public String getDisabilitySubSubIds() {
        return disabilitySubSubIds;
    }

    public void setDisabilitySubSubIds(String disabilitySubSubIds) {
        this.disabilitySubSubIds = disabilitySubSubIds;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getOthertypeofdisability() {
        return othertypeofdisability;
    }

    public void setOthertypeofdisability(String othertypeofdisability) {
        this.othertypeofdisability = othertypeofdisability;
    }

    public String getConditionindisability() {
        return conditionindisability;
    }

    public void setConditionindisability(String conditionindisability) {
        this.conditionindisability = conditionindisability;
    }

    public String getReassessmentyears() {
        return reassessmentyears;
    }

    public void setReassessmentyears(String reassessmentyears) {
        this.reassessmentyears = reassessmentyears;
    }

    public String getRMonths() {
        return rMonths;
    }

    public void setRMonths(String rMonths) {
        this.rMonths = rMonths;
    }

    public String getOtherconditionofdisability() {
        return otherconditionofdisability;
    }

    public void setOtherconditionofdisability(String otherconditionofdisability) {
        this.otherconditionofdisability = otherconditionofdisability;
    }

    public String getNoOfDisableperson() {
        return noOfDisableperson;
    }

    public void setNoOfDisableperson(String noOfDisableperson) {
        this.noOfDisableperson = noOfDisableperson;
    }

    public String getNoOfYears() {
        return noOfYears;
    }

    public void setNoOfYears(String noOfYears) {
        this.noOfYears = noOfYears;
    }

    public String getDobday() {
        return dobday;
    }

    public void setDobday(String dobday) {
        this.dobday = dobday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getAnualIncome() {
        return anualIncome;
    }

    public void setAnualIncome(String anualIncome) {
        this.anualIncome = anualIncome;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCard() {
        if (!"".equals(card)) {
            return card;
        } else {
            return null;
        }
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getGsurname() {
        return gsurname;
    }

    public void setGsurname(String gsurname) {
        this.gsurname = gsurname;
    }

    public String getGrelation() {
        return grelation;
    }

    public void setGrelation(String grelation) {
        this.grelation = grelation;
    }

    public String getGageYears() {
        return gageYears;
    }

    public void setGageYears(String gageYears) {
        this.gageYears = gageYears;
    }

    public String getGincome() {
        return gincome;
    }

    public void setGincome(String gincome) {
        this.gincome = gincome;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getTownVillage() {
        return townVillage;
    }

    public void setTownVillage(String townVillage) {
        this.townVillage = townVillage;
    }

    public String getMandal() {
        return mandal;
    }

    public void setMandal(String mandal) {
        this.mandal = mandal;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSystemip() {
        return systemip;
    }

    public void setSystemip(String systemip) {
        this.systemip = systemip;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getPanchaith() {
        return panchaith;
    }

    public void setPanchaith(String panchaith) {
        this.panchaith = panchaith;
    }

    public boolean isReassessment() {
        return reassessment;
    }

    public void setReassessment(boolean reassessment) {
        this.reassessment = reassessment;
    }

    public boolean isYesanyDisableperson() {
        return yesanyDisableperson;
    }

    public void setYesanyDisableperson(boolean yesanyDisableperson) {
        this.yesanyDisableperson = yesanyDisableperson;
    }

    public boolean isCongenital() {
        return congenital;
    }

    public void setCongenital(boolean congenital) {
        this.congenital = congenital;
    }

    public boolean isHereditary() {
        return hereditary;
    }

    public void setHereditary(boolean hereditary) {
        this.hereditary = hereditary;
    }

    public boolean isBirthinjury() {
        return birthinjury;
    }

    public void setBirthinjury(boolean birthinjury) {
        this.birthinjury = birthinjury;
    }

    public boolean isDiseaseInfection() {
        return diseaseInfection;
    }

    public void setDiseaseInfection(boolean diseaseInfection) {
        this.diseaseInfection = diseaseInfection;
    }

    public boolean isMalnutrition() {
        return malnutrition;
    }

    public void setMalnutrition(boolean malnutrition) {
        this.malnutrition = malnutrition;
    }

    public boolean isAccident() {
        return accident;
    }

    public void setAccident(boolean accident) {
        this.accident = accident;
    }

    public String getHabitation() {
        return habitation;
    }

    public void setHabitation(String habitation) {
        this.habitation = habitation;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public String getGeducation() {
        return geducation;
    }

    public void setGeducation(String geducation) {
        this.geducation = geducation;
    }

    public String getGemployement() {
        return gemployement;
    }

    public void setGemployement(String gemployement) {
        this.gemployement = gemployement;
    }

    public String getEdustatus() {
        return edustatus;
    }

    public void setEdustatus(String edustatus) {
        this.edustatus = edustatus;
    }

    public int[] getRelation() {
        return relation;
    }

    public void setRelation(int[] relation) {
        this.relation = relation;
    }

    public String getRelationStr() {
        return relationStr;
    }

    public void setRelationStr(String relationStr) {
        this.relationStr = relationStr;
    }

    public String getDisabilityName() {
        return disabilityName;
    }

    public void setDisabilityName(String disabilityName) {
        this.disabilityName = disabilityName;
    }

    public int getDisabilityLocoSubId() {
        return disabilityLocoSubId;
    }

    public void setDisabilityLocoSubId(int disabilityLocoSubId) {
        this.disabilityLocoSubId = disabilityLocoSubId;
    }

    public String getDisabilityLocoSubName() {
        return disabilityLocoSubName;
    }

    public void setDisabilityLocoSubName(String disabilityLocoSubName) {
        this.disabilityLocoSubName = disabilityLocoSubName;
    }

    public int getDisabilityLocoSubSubId() {
        return disabilityLocoSubSubId;
    }

    public void setDisabilityLocoSubSubId(int disabilityLocoSubSubId) {
        this.disabilityLocoSubSubId = disabilityLocoSubSubId;
    }

    public String getDisabilityLocoSubSubName() {
        return disabilityLocoSubSubName;
    }

    public void setDisabilityLocoSubSubName(String disabilityLocoSubSubName) {
        this.disabilityLocoSubSubName = disabilityLocoSubSubName;
    }

    public String getDisabilityVisualSubName() {
        return disabilityVisualSubName;
    }

    public void setDisabilityVisualSubName(String disabilityVisualSubName) {
        this.disabilityVisualSubName = disabilityVisualSubName;
    }

    public String getDisabilityHearingSubName() {
        return disabilityHearingSubName;
    }

    public void setDisabilityHearingSubName(String disabilityHearingSubName) {
        this.disabilityHearingSubName = disabilityHearingSubName;
    }

    public String getDisabilityMentalRetdSubName() {
        return disabilityMentalRetdSubName;
    }

    public void setDisabilityMentalRetdSubName(String disabilityMentalRetdSubName) {
        this.disabilityMentalRetdSubName = disabilityMentalRetdSubName;
    }

    public String getDisabilityMentalIllnessSubName() {
        return disabilityMentalIllnessSubName;
    }

    public void setDisabilityMentalIllnessSubName(String disabilityMentalIllnessSubName) {
        this.disabilityMentalIllnessSubName = disabilityMentalIllnessSubName;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getHospitaladdress() {
        return hospitaladdress;
    }

    public void setHospitaladdress(String hospitaladdress) {
        this.hospitaladdress = hospitaladdress;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getShgno() {
        return shgno;
    }

    public void setShgno(String shgno) {
        this.shgno = shgno;
    }

    public String getIsl() {
        return isl;
    }

    public void setIsl(String isl) {
        this.isl = isl;
    }

    public String getHousingtype() {
        return housingtype;
    }

    public void setHousingtype(String housingtype) {
        this.housingtype = housingtype;
    }

    public String getHallticket() {
        return hallticket;
    }

    public void setHallticket(String hallticket) {
        this.hallticket = hallticket;
    }

    public String getDoctor1name() {
        return doctor1name;
    }

    public void setDoctor1name(String doctor1name) {
        this.doctor1name = doctor1name;
    }

    public String getDoctor1regnumber() {
        return doctor1regnumber;
    }

    public void setDoctor1regnumber(String doctor1regnumber) {
        this.doctor1regnumber = doctor1regnumber;
    }

    public String getDoctor1designation() {
        return doctor1designation;
    }

    public void setDoctor1designation(String doctor1designation) {
        this.doctor1designation = doctor1designation;
    }

    public String getDoctor2name() {
        return doctor2name;
    }

    public void setDoctor2name(String doctor2name) {
        this.doctor2name = doctor2name;
    }

    public String getDoctor2regnumber() {
        return doctor2regnumber;
    }

    public void setDoctor2regnumber(String doctor2regnumber) {
        this.doctor2regnumber = doctor2regnumber;
    }

    public String getDoctor2designation() {
        return doctor2designation;
    }

    public void setDoctor2designation(String doctor2designation) {
        this.doctor2designation = doctor2designation;
    }

    public String getDoctor3name() {
        return doctor3name;
    }

    public void setDoctor3name(String doctor3name) {
        this.doctor3name = doctor3name;
    }

    public String getDoctor3regnumber() {
        return doctor3regnumber;
    }

    public void setDoctor3regnumber(String doctor3regnumber) {
        this.doctor3regnumber = doctor3regnumber;
    }

    public String getDoctor3designation() {
        return doctor3designation;
    }

    public void setDoctor3designation(String doctor3designation) {
        this.doctor3designation = doctor3designation;
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

    public String getTelugupersonname() {
        return telugupersonname;
    }

    public void setTelugupersonname(String telugupersonname) {
        this.telugupersonname = telugupersonname;
    }

    public String getTelugufathername() {
        return telugufathername;
    }

    public void setTelugufathername(String telugufathername) {
        this.telugufathername = telugufathername;
    }

    public String getPwdno() {
        return pwdno;
    }

    public void setPwdno(String pwdno) {
        this.pwdno = pwdno;
    }

    public String getNregcardno() {
        return nregcardno;
    }

    public void setNregcardno(String nregcardno) {
        this.nregcardno = nregcardno;
    }

    public String getArogyasricardno() {
        return arogyasricardno;
    }

    public void setArogyasricardno(String arogyasricardno) {
        this.arogyasricardno = arogyasricardno;
    }

    public String getEpiccardno() {
        if (!"".equals(epiccardno)) {
            return epiccardno;
        } else {
            return null;
        }
    }

    public void setEpiccardno(String epiccardno) {
        this.epiccardno = epiccardno;
    }

    public boolean isNregcard() {
        return nregcard;
    }

    public void setNregcard(boolean nregcard) {
        this.nregcard = nregcard;
    }

    public boolean isArogyasricard() {
        return arogyasricard;
    }

    public void setArogyasricard(boolean arogyasricard) {
        this.arogyasricard = arogyasricard;
    }

    public boolean isEpiccard() {
        return epiccard;
    }

    public void setEpiccard(boolean epiccard) {
        this.epiccard = epiccard;
    }

    public String getDiagnosisofdisability() {
        return diagnosisofdisability;
    }

    public void setDiagnosisofdisability(String diagnosisofdisability) {
        this.diagnosisofdisability = diagnosisofdisability;
    }

    public String getKindofdisability() {
        return kindofdisability;
    }

    public void setKindofdisability(String kindofdisability) {
        this.kindofdisability = kindofdisability;
    }

    public String getSuggessionone() {
        return suggessionone;
    }

    public void setSuggessionone(String suggessionone) {
        this.suggessionone = suggessionone;
    }

    public String getSuggessiontwo() {
        return suggessiontwo;
    }

    public void setSuggessiontwo(String suggessiontwo) {
        this.suggessiontwo = suggessiontwo;
    }

    public String getSuggessionthree() {
        return suggessionthree;
    }

    public void setSuggessionthree(String suggessionthree) {
        this.suggessionthree = suggessionthree;
    }

    public String getPersonstatus() {
        return personstatus;
    }

    public void setPersonstatus(String personstatus) {
        this.personstatus = personstatus;
    }

    public String getYearsfortemporary() {
        return yearsfortemporary;
    }

    public void setYearsfortemporary(String yearsfortemporary) {
        this.yearsfortemporary = yearsfortemporary;
    }

    public boolean isPensioncard() {
        return pensioncard;
    }

    public void setPensioncard(boolean pensioncard) {
        this.pensioncard = pensioncard;
    }

    public String getPensioncardno() {
        if (!"".equals(pensioncardno)) {
            return pensioncardno;
        } else {
            return null;
        }
    }

    public void setPensioncardno(String pensioncardno) {
        this.pensioncardno = pensioncardno;
    }

    public int[] getConditiondisability() {
        return conditiondisability;
    }

    public void setConditiondisability(int[] conditiondisability) {
        this.conditiondisability = conditiondisability;
    }

    public void setAccidentworseeye(String accidentworseeye) {
        this.setAccidentworseeye(accidentworseeye);
    }

    public String getConditiondisabilityIds() {
        return conditiondisabilityIds;
    }

    public void setConditiondisabilityIds(String conditiondisabilityIds) {
        this.conditiondisabilityIds = conditiondisabilityIds;
    }

    public boolean isCongenitalbettereye() {
        return congenitalbettereye;
    }

    public void setCongenitalbettereye(boolean congenitalbettereye) {
        this.congenitalbettereye = congenitalbettereye;
    }

    public boolean isCongenitalworseeye() {
        return congenitalworseeye;
    }

    public void setCongenitalworseeye(boolean congenitalworseeye) {
        this.congenitalworseeye = congenitalworseeye;
    }

    public boolean isHereditarybettereye() {
        return hereditarybettereye;
    }

    public void setHereditarybettereye(boolean hereditarybettereye) {
        this.hereditarybettereye = hereditarybettereye;
    }

    public boolean isHereditaryworseeye() {
        return hereditaryworseeye;
    }

    public void setHereditaryworseeye(boolean hereditaryworseeye) {
        this.hereditaryworseeye = hereditaryworseeye;
    }

    public boolean isBirthinjurybettereye() {
        return birthinjurybettereye;
    }

    public void setBirthinjurybettereye(boolean birthinjurybettereye) {
        this.birthinjurybettereye = birthinjurybettereye;
    }

    public boolean isBirthinjuryworseeye() {
        return birthinjuryworseeye;
    }

    public void setBirthinjuryworseeye(boolean birthinjuryworseeye) {
        this.birthinjuryworseeye = birthinjuryworseeye;
    }

    public boolean isDiseaseInfectionbettereye() {
        return diseaseInfectionbettereye;
    }

    public void setDiseaseInfectionbettereye(boolean diseaseInfectionbettereye) {
        this.diseaseInfectionbettereye = diseaseInfectionbettereye;
    }

    public boolean isDiseaseInfectionworseeye() {
        return diseaseInfectionworseeye;
    }

    public void setDiseaseInfectionworseeye(boolean diseaseInfectionworseeye) {
        this.diseaseInfectionworseeye = diseaseInfectionworseeye;
    }

    public boolean isMalnutritionbettereye() {
        return malnutritionbettereye;
    }

    public void setMalnutritionbettereye(boolean malnutritionbettereye) {
        this.malnutritionbettereye = malnutritionbettereye;
    }

    public boolean isMalnutritionworseeye() {
        return malnutritionworseeye;
    }

    public void setMalnutritionworseeye(boolean malnutritionworseeye) {
        this.malnutritionworseeye = malnutritionworseeye;
    }

    public boolean isAccidentbettereye() {
        return accidentbettereye;
    }

    public void setAccidentbettereye(boolean accidentbettereye) {
        this.accidentbettereye = accidentbettereye;
    }

    public boolean isAccidentworseeye() {
        return accidentworseeye;
    }

    public void setAccidentworseeye(boolean accidentworseeye) {
        this.accidentworseeye = accidentworseeye;
    }

    public String getReferredto() {
        return referredto;
    }

    public void setReferredto(String referredto) {
        this.referredto = referredto;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public void setCouncellingandguidance(boolean councellingandguidance) {
        this.setCouncellingandguidance(councellingandguidance);
    }

    public void setSpeechtherapy(boolean speechtherapy) {
        this.setSpeechtherapy(speechtherapy);

    }

    public void setHearingaid(boolean hearingaid) {
        this.setHearingaid(hearingaid);
    }

    public void setBehaviourmodification(boolean behaviourmodification) {
        this.setBehaviourmodification(behaviourmodification);
    }

    public void setAdmissioninpsychiatrichospital(boolean admissioninpsychiatrichospital) {
        this.setAdmissioninpsychiatrichospital(admissioninpsychiatrichospital);
    }

    public void setPhysiotherapy(boolean Physiotherapy) {
        this.setPhysiotherapy(Physiotherapy);
    }

    public void setLowvisionaid(boolean lowvisionaid) {
        this.setLowvisionaid(lowvisionaid);
    }

    public String getAnyotherneed() {
        return anyotherneed;
    }

    public void setAnyotherneed(String anyotherneed) {
        this.anyotherneed = anyotherneed;
    }

    public String getCouncellingandguidance() {
        return councellingandguidance;
    }

    public void setCouncellingandguidance(String councellingandguidance) {
        this.councellingandguidance = councellingandguidance;
    }

    public String getSpeechtherapy() {
        return speechtherapy;
    }

    public void setSpeechtherapy(String speechtherapy) {
        this.speechtherapy = speechtherapy;
    }

    public String getHearingaid() {
        return hearingaid;
    }

    public void setHearingaid(String hearingaid) {
        this.hearingaid = hearingaid;
    }

    public String getBehaviourmodification() {
        return behaviourmodification;
    }

    public void setBehaviourmodification(String behaviourmodification) {
        this.behaviourmodification = behaviourmodification;
    }

    public String getPhychotherapy() {
        return phychotherapy;
    }

    public void setPhychotherapy(String phychotherapy) {
        this.phychotherapy = phychotherapy;
    }

    public String getAdmissioninpsychiatrichospital() {
        return admissioninpsychiatrichospital;
    }

    public void setAdmissioninpsychiatrichospital(String admissioninpsychiatrichospital) {
        this.admissioninpsychiatrichospital = admissioninpsychiatrichospital;
    }

    public String getPhysiotherapy() {
        return Physiotherapy;
    }

    public void setPhysiotherapy(String Physiotherapy) {
        this.Physiotherapy = Physiotherapy;
    }

    public String getLowvisionaid() {
        return lowvisionaid;
    }

    public void setLowvisionaid(String lowvisionaid) {
        this.lowvisionaid = lowvisionaid;
    }

    public boolean isCongenitalvisual() {
        return congenitalvisual;
    }

    public void setCongenitalvisual(boolean congenitalvisual) {
        this.congenitalvisual = congenitalvisual;
    }

    public boolean isHereditaryvisual() {
        return hereditaryvisual;
    }

    public void setHereditaryvisual(boolean hereditaryvisual) {
        this.hereditaryvisual = hereditaryvisual;
    }

    public boolean isBirthinjuryvisual() {
        return birthinjuryvisual;
    }

    public void setBirthinjuryvisual(boolean birthinjuryvisual) {
        this.birthinjuryvisual = birthinjuryvisual;
    }

    public boolean isDiseaseInfectionvisual() {
        return diseaseInfectionvisual;
    }

    public void setDiseaseInfectionvisual(boolean diseaseInfectionvisual) {
        this.diseaseInfectionvisual = diseaseInfectionvisual;
    }

    public boolean isMalnutritionvisual() {
        return malnutritionvisual;
    }

    public void setMalnutritionvisual(boolean malnutritionvisual) {
        this.malnutritionvisual = malnutritionvisual;
    }

    public boolean isAccidentvisual() {
        return accidentvisual;
    }

    public void setAccidentvisual(boolean accidentvisual) {
        this.accidentvisual = accidentvisual;
    }

    public String getTypeofdisability() {
        return typeofdisability;
    }

    public void setTypeofdisability(String typeofdisability) {
        this.typeofdisability = typeofdisability;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return the pension_type
     */
    public String getPension_type() {
        if (!"".equals(pension_type)) {
            return pension_type;
        } else {
            return null;
        }
    }

    /**
     * @param pension_type the pension_type to set
     */
    public void setPension_type(String pension_type) {
        this.pension_type = pension_type;
    }

    /**
     * @return the parents_marriage
     */
    public String getParents_marriage() {
        return parents_marriage;
    }

    /**
     * @param parents_marriage the parents_marriage to set
     */
    public void setParents_marriage(String parents_marriage) {
        this.parents_marriage = parents_marriage;
    }

    /**
     * @return the leprosy_cured
     */
    public String getLeprosy_cured() {
        return leprosy_cured;
    }

    /**
     * @param leprosy_cured the leprosy_cured to set
     */
    public void setLeprosy_cured(String leprosy_cured) {
        this.leprosy_cured = leprosy_cured;
    }

    /**
     * @return the f_can
     */
    public String getF_can() {
        return f_can;
    }

    /**
     * @param f_can the f_can to set
     */
    public void setF_can(String f_can) {
        this.f_can = f_can;
    }

    /**
     * @return the pp_can
     */
    public String getPp_can() {
        return pp_can;
    }

    /**
     * @param pp_can the pp_can to set
     */
    public void setPp_can(String pp_can) {
        this.pp_can = pp_can;
    }

    /**
     * @return the l_can
     */
    public String getL_can() {
        return l_can;
    }

    /**
     * @param l_can the l_can to set
     */
    public void setL_can(String l_can) {
        this.l_can = l_can;
    }

    /**
     * @return the kc_can
     */
    public String getKc_can() {
        return kc_can;
    }

    /**
     * @param kc_can the kc_can to set
     */
    public void setKc_can(String kc_can) {
        this.kc_can = kc_can;
    }

    /**
     * @return the b_can
     */
    public String getB_can() {
        return b_can;
    }

    /**
     * @param b_can the b_can to set
     */
    public void setB_can(String b_can) {
        this.b_can = b_can;
    }

    /**
     * @return the rw_can
     */
    public String getRw_can() {
        return rw_can;
    }

    /**
     * @param rw_can the rw_can to set
     */
    public void setRw_can(String rw_can) {
        this.rw_can = rw_can;
    }

    /**
     * @return the s_can
     */
    public String getS_can() {
        return s_can;
    }

    /**
     * @param s_can the s_can to set
     */
    public void setS_can(String s_can) {
        this.s_can = s_can;
    }

    /**
     * @return the st_can
     */
    public String getSt_can() {
        return st_can;
    }

    /**
     * @param st_can the st_can to set
     */
    public void setSt_can(String st_can) {
        this.st_can = st_can;
    }

    /**
     * @return the w_can
     */
    public String getW_can() {
        return w_can;
    }

    /**
     * @param w_can the w_can to set
     */
    public void setW_can(String w_can) {
        this.w_can = w_can;
    }

    /**
     * @return the se_can
     */
    public String getSe_can() {
        return se_can;
    }

    /**
     * @param se_can the se_can to set
     */
    public void setSe_can(String se_can) {
        this.se_can = se_can;
    }

    /**
     * @return the h_can
     */
    public String getH_can() {
        return h_can;
    }

    /**
     * @param h_can the h_can to set
     */
    public void setH_can(String h_can) {
        this.h_can = h_can;
    }

    /**
     * @return the surnametelugu
     */
    public String getSurnametelugu() {
        return surnametelugu;
    }

    /**
     * @param surnametelugu the surnametelugu to set
     */
    public void setSurnametelugu(String surnametelugu) {
        this.surnametelugu = surnametelugu;
    }

    /**
     * @return the firstnametelugu
     */
    public String getFirstnametelugu() {
        return firstnametelugu;
    }

    /**
     * @param firstnametelugu the firstnametelugu to set
     */
    public void setFirstnametelugu(String firstnametelugu) {
        this.firstnametelugu = firstnametelugu;
    }

    /**
     * @return the camp_venue
     */
    public String getCamp_venue() {
        return camp_venue;
    }

    /**
     * @param camp_venue the camp_venue to set
     */
    public void setCamp_venue(String camp_venue) {
        this.camp_venue = camp_venue;
    }

    /**
     * @return the highfever
     */
    public boolean isHighfever() {
        return highfever;
    }

    /**
     * @param highfever the highfever to set
     */
    public void setHighfever(boolean highfever) {
        this.highfever = highfever;
    }

    /**
     * @return the epilepsy
     */
    public boolean isEpilepsy() {
        return epilepsy;
    }

    /**
     * @param epilepsy the epilepsy to set
     */
    public void setEpilepsy(boolean epilepsy) {
        this.epilepsy = epilepsy;
    }

    /**
     * @return the habitation_name
     */
    public String getHabitation_name() {
        return habitation_name;
    }

    /**
     * @param habitation_name the habitation_name to set
     */
    public void setHabitation_name(String habitation_name) {
        this.habitation_name = habitation_name;
    }

    /**
     * @return the village_name
     */
    public String getVillage_name() {
        return village_name;
    }

    /**
     * @param village_name the village_name to set
     */
    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    /**
     * @return the mandal_name
     */
    public String getMandal_name() {
        return mandal_name;
    }

    /**
     * @param mandal_name the mandal_name to set
     */
    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
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
     * @return the panchayatlist
     */
    public ArrayList getPanchayatlist() {
        return panchayatlist;
    }

    /**
     * @param panchayatlist the panchayatlist to set
     */
    public void setPanchayatlist(ArrayList panchayatlist) {
        this.panchayatlist = panchayatlist;
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
     * @return the birthasphyxia
     */
    public boolean isBirthasphyxia() {
        return birthasphyxia;
    }

    /**
     * @param birthasphyxia the birthasphyxia to set
     */
    public void setBirthasphyxia(boolean birthasphyxia) {
        this.birthasphyxia = birthasphyxia;
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
     * @return the uptohabitation
     */
    public String getUptohabitation() {
        return uptohabitation;
    }

    /**
     * @param uptohabitation the uptohabitation to set
     */
    public void setUptohabitation(String uptohabitation) {
        this.uptohabitation = uptohabitation;
    }

    /**
     * @return the uptopersoncode
     */
    public String getUptopersoncode() {
        return uptopersoncode;
    }

    /**
     * @param uptopersoncode the uptopersoncode to set
     */
    public void setUptopersoncode(String uptopersoncode) {
        this.uptopersoncode = uptopersoncode;
    }

    /**
     * @return the phoneno
     */
    public String getPhoneno() {
        if (!"".equals(phoneno)) {
            return this.phoneno;
        } else {
            return null;
        }
    }

    /**
     * @param phoneno the phoneno to set
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        if (!"".equals(email)) {
            return this.email;
        } else {
            return null;
        }
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        if (!"".equals(pin)) {
            return this.pin;
        } else {
            return null;
        }
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @return the type_disability
     */
    public String getType_disability() {
        return type_disability;
    }

    /**
     * @param type_disability the type_disability to set
     */
    public void setType_disability(String type_disability) {
        this.type_disability = type_disability;
    }

    /**
     * @return the existingpensioners
     */
    public String getExistingpensioners() {
        return existingpensioners;
    }

    /**
     * @param existingpensioners the existingpensioners to set
     */
    public void setExistingpensioners(String existingpensioners) {
        this.existingpensioners = existingpensioners;
    }

    /**
     * @return the partacount
     */
    public String getPartacount() {
        return partacount;
    }

    /**
     * @param partacount the partacount to set
     */
    public void setPartacount(String partacount) {
        this.partacount = partacount;
    }

    /**
     * @return the ortho
     */
    public String getOrtho() {
        return ortho;
    }

    /**
     * @param ortho the ortho to set
     */
    public void setOrtho(String ortho) {
        this.ortho = ortho;
    }

    /**
     * @return the hearing
     */
    public String getHearing() {
        return hearing;
    }

    /**
     * @param hearing the hearing to set
     */
    public void setHearing(String hearing) {
        this.hearing = hearing;
    }

    /**
     * @return the visual
     */
    public String getVisual() {
        return visual;
    }

    /**
     * @param visual the visual to set
     */
    public void setVisual(String visual) {
        this.visual = visual;
    }

    /**
     * @return the mentalretardation
     */
    public String getMentalretardation() {
        return mentalretardation;
    }

    /**
     * @param mentalretardation the mentalretardation to set
     */
    public void setMentalretardation(String mentalretardation) {
        this.mentalretardation = mentalretardation;
    }

    /**
     * @return the mentalillness
     */
    public String getMentalillness() {
        return mentalillness;
    }

    /**
     * @param mentalillness the mentalillness to set
     */
    public void setMentalillness(String mentalillness) {
        this.mentalillness = mentalillness;
    }

    /**
     * @return the multipledisability
     */
    public String getMultipledisability() {
        return multipledisability;
    }

    /**
     * @param multipledisability the multipledisability to set
     */
    public void setMultipledisability(String multipledisability) {
        this.multipledisability = multipledisability;
    }

    /**
     * @return the operatorStatus
     */
    public String getOperatorStatus() {
        return operatorStatus;
    }

    /**
     * @param operatorStatus the operatorStatus to set
     */
    public void setOperatorStatus(String operatorStatus) {
        this.operatorStatus = operatorStatus;
    }

    /**
     * @return the districtid
     */
    public String getDistrictid() {
        return districtid;
    }

    /**
     * @param districtid the districtid to set
     */
    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    /**
     * @return the existingpercentage
     */
    public String getExistingpercentage() {
        if (!"".equals(existingpercentage)) {
            return this.existingpercentage;
        } else {
            return null;
        }
    }

    /**
     * @param existingpercentage the existingpercentage to set
     */
    public void setExistingpercentage(String existingpercentage) {
        this.existingpercentage = existingpercentage;
    }

    /**
     * @return the campid
     */
    public int getCampid() {
        return campid;
    }

    /**
     * @param campid the campid to set
     */
    public void setCampid(int campid) {
        this.campid = campid;
    }

    /**
     * @return the pensionPhase
     */
    public String getPensionPhase() {
        return pensionPhase;
    }

    /**
     * @param PensionPhase the PensionPhase to set
     */
    public void setPensionPhase(String PensionPhase) {
        this.pensionPhase = PensionPhase;
    }

    /**
     * @return the doctornameOH
     */
    public String getDoctornameOH() {
        return doctornameOH;
    }

    /**
     * @param doctornameOH the doctornameOH to set
     */
    public void setDoctornameOH(String doctornameOH) {
        this.doctornameOH = doctornameOH;
    }

    /**
     * @return the doctorregnumberOH
     */
    public String getDoctorregnumberOH() {
        return doctorregnumberOH;
    }

    /**
     * @param doctorregnumberOH the doctorregnumberOH to set
     */
    public void setDoctorregnumberOH(String doctorregnumberOH) {
        this.doctorregnumberOH = doctorregnumberOH;
    }

    /**
     * @return the doctordesignationOH
     */
    public String getDoctordesignationOH() {
        return doctordesignationOH;
    }

    /**
     * @param doctordesignationOH the doctordesignationOH to set
     */
    public void setDoctordesignationOH(String doctordesignationOH) {
        this.doctordesignationOH = doctordesignationOH;
    }

    /**
     * @return the doctornameVI
     */
    public String getDoctornameVI() {
        return doctornameVI;
    }

    /**
     * @param doctornameVI the doctornameVI to set
     */
    public void setDoctornameVI(String doctornameVI) {
        this.doctornameVI = doctornameVI;
    }

    /**
     * @return the doctorregnumberVI
     */
    public String getDoctorregnumberVI() {
        return doctorregnumberVI;
    }

    /**
     * @param doctorregnumberVI the doctorregnumberVI to set
     */
    public void setDoctorregnumberVI(String doctorregnumberVI) {
        this.doctorregnumberVI = doctorregnumberVI;
    }

    /**
     * @return the doctordesignationVI
     */
    public String getDoctordesignationVI() {
        return doctordesignationVI;
    }

    /**
     * @param doctordesignationVI the doctordesignationVI to set
     */
    public void setDoctordesignationVI(String doctordesignationVI) {
        this.doctordesignationVI = doctordesignationVI;
    }

    /**
     * @return the doctornameHI
     */
    public String getDoctornameHI() {
        return doctornameHI;
    }

    /**
     * @param doctornameHI the doctornameHI to set
     */
    public void setDoctornameHI(String doctornameHI) {
        this.doctornameHI = doctornameHI;
    }

    /**
     * @return the doctorregnumberHI
     */
    public String getDoctorregnumberHI() {
        return doctorregnumberHI;
    }

    /**
     * @param doctorregnumberHI the doctorregnumberHI to set
     */
    public void setDoctorregnumberHI(String doctorregnumberHI) {
        this.doctorregnumberHI = doctorregnumberHI;
    }

    /**
     * @return the doctordesignationHI
     */
    public String getDoctordesignationHI() {
        return doctordesignationHI;
    }

    /**
     * @param doctordesignationHI the doctordesignationHI to set
     */
    public void setDoctordesignationHI(String doctordesignationHI) {
        this.doctordesignationHI = doctordesignationHI;
    }

    /**
     * @return the doctornameMR
     */
    public String getDoctornameMR() {
        return doctornameMR;
    }

    /**
     * @param doctornameMR the doctornameMR to set
     */
    public void setDoctornameMR(String doctornameMR) {
        this.doctornameMR = doctornameMR;
    }

    /**
     * @return the doctorregnumberMR
     */
    public String getDoctorregnumberMR() {
        return doctorregnumberMR;
    }

    /**
     * @param doctorregnumberMR the doctorregnumberMR to set
     */
    public void setDoctorregnumberMR(String doctorregnumberMR) {
        this.doctorregnumberMR = doctorregnumberMR;
    }

    /**
     * @return the doctordesignationMR
     */
    public String getDoctordesignationMR() {
        return doctordesignationMR;
    }

    /**
     * @param doctordesignationMR the doctordesignationMR to set
     */
    public void setDoctordesignationMR(String doctordesignationMR) {
        this.doctordesignationMR = doctordesignationMR;
    }

    /**
     * @return the doctornameMI
     */
    public String getDoctornameMI() {
        return doctornameMI;
    }

    /**
     * @param doctornameMI the doctornameMI to set
     */
    public void setDoctornameMI(String doctornameMI) {
        this.doctornameMI = doctornameMI;
    }

    /**
     * @return the doctorregnumberMI
     */
    public String getDoctorregnumberMI() {
        return doctorregnumberMI;
    }

    /**
     * @param doctorregnumberMI the doctorregnumberMI to set
     */
    public void setDoctorregnumberMI(String doctorregnumberMI) {
        this.doctorregnumberMI = doctorregnumberMI;
    }

    /**
     * @return the doctordesignationMI
     */
    public String getDoctordesignationMI() {
        return doctordesignationMI;
    }

    /**
     * @param doctordesignationMI the doctordesignationMI to set
     */
    public void setDoctordesignationMI(String doctordesignationMI) {
        this.doctordesignationMI = doctordesignationMI;
    }

    /**
     * @return the ohdoctor
     */
    public String getOhdoctor() {
        return ohdoctor;
    }

    /**
     * @param ohdoctor the ohdoctor to set
     */
    public void setOhdoctor(String ohdoctor) {
        this.ohdoctor = ohdoctor;
    }

    /**
     * @return the vidoctor
     */
    public String getVidoctor() {
        return vidoctor;
    }

    /**
     * @param vidoctor the vidoctor to set
     */
    public void setVidoctor(String vidoctor) {
        this.vidoctor = vidoctor;
    }

    /**
     * @return the hidoctor
     */
    public String getHidoctor() {
        return hidoctor;
    }

    /**
     * @param hidoctor the hidoctor to set
     */
    public void setHidoctor(String hidoctor) {
        this.hidoctor = hidoctor;
    }

    /**
     * @return the mrdoctor
     */
    public String getMrdoctor() {
        return mrdoctor;
    }

    /**
     * @param mrdoctor the mrdoctor to set
     */
    public void setMrdoctor(String mrdoctor) {
        this.mrdoctor = mrdoctor;
    }

    /**
     * @return the midoctor
     */
    public String getMidoctor() {
        return midoctor;
    }

    /**
     * @param midoctor the midoctor to set
     */
    public void setMidoctor(String midoctor) {
        this.midoctor = midoctor;
    }

    /**
     * @return the multipledisabilityId
     */
    public int getMultipledisabilityId() {
        return multipledisabilityId;
    }

    /**
     * @param multipledisabilityId the multipledisabilityId to set
     */
    public void setMultipledisabilityId(int multipledisabilityId) {
        this.multipledisabilityId = multipledisabilityId;
    }

    /**
     * @return the panchayatiid
     */
    public String getPanchayatiid() {
        return panchayatiid;
    }

    /**
     * @param panchayatiid the panchayatiid to set
     */
    public void setPanchayatiid(String panchayatiid) {
        this.panchayatiid = panchayatiid;
    }

    /**
     * @return the assemblyid
     */
    public String getAssemblyid() {
        return assemblyid;
    }

    /**
     * @param assemblyid the assemblyid to set
     */
    public void setAssemblyid(String assemblyid) {
        this.assemblyid = assemblyid;
    }

    /**
     * @return the district_id
     */
    public String getDistrict_id() {
        return district_id;
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
        return mandal_id;
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
        return village_id;
    }

    /**
     * @param village_id the village_id to set
     */
    public void setVillage_id(String village_id) {
        this.village_id = village_id;
    }

    /**
     * @return the panchayat_id
     */
    public String getPanchayat_id() {
        return panchayat_id;
    }

    /**
     * @param panchayat_id the panchayat_id to set
     */
    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }

    /**
     * @return the habitation_id
     */
    public String getHabitation_id() {
        return habitation_id;
    }

    /**
     * @param habitation_id the habitation_id to set
     */
    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
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
    ArrayList districtList = new ArrayList();

    /**
     * @return the reasonforstatus
     */
    public String getReasonforstatus() {
        return reasonforstatus;
    }

    /**
     * @param reasonforstatus the reasonforstatus to set
     */
    public void setReasonforstatus(String reasonforstatus) {
        this.reasonforstatus = reasonforstatus;
    }

    /**
     * @return the specialistprefix
     */
    public String getSpecialistprefix() {
        return specialistprefix;
    }

    /**
     * @param specialistprefix the specialistprefix to set
     */
    public void setSpecialistprefix(String specialistprefix) {
        this.specialistprefix = specialistprefix;
    }

    public String getHabCode() {
        return habCode;
    }
    private String pensionid = null;

    public String getPensionid() {
        return pensionid;
    }

    public void setPensionid(String pensionid) {
        this.pensionid = pensionid;
    }

    public void setHabCode(String habCode) {
        this.habCode = habCode;
    }

    public String getMdspecialistprefix() {
        return mdspecialistprefix;
    }

    public void setMdspecialistprefix(String mdspecialistprefix) {
        this.mdspecialistprefix = mdspecialistprefix;
    }

    //Balu Start
    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }
    public ArrayList getFinancialYearList() {
        return financialYearList;
    }

    public void setFinancialYearList(ArrayList financialYearList) {
        this.financialYearList = financialYearList;
    }

    public ArrayList getMonthList() {
        return monthList;
    }

    public void setMonthList(ArrayList monthList) {
        this.monthList = monthList;
    }

    public String getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(String typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }

    public ArrayList getYearList() {
        return yearList;
    }

    public void setYearList(ArrayList yearList) {
        this.yearList = yearList;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FormFile getFormFile() {
        return formFile;
    }

    public void setFormFile(FormFile formFile) {
        this.formFile = formFile;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public float getDisabilityperscentage() {
        return disabilityperscentage;
    }

    public void setDisabilityperscentage(float disabilityperscentage) {
        this.disabilityperscentage = disabilityperscentage;
    }

    public String getPersoncodePopulateId() {
        return personcodePopulateId;
    }

    public void setPersoncodePopulateId(String personcodePopulateId) {
        this.personcodePopulateId = personcodePopulateId;
    }

    public FormFile getFormFile1() {
        return formFile1;
    }

    public void setFormFile1(FormFile formFile1) {
        this.formFile1 = formFile1;
    }


    
    // Balu end
}
