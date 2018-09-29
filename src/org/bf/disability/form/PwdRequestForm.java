/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author 693461
 */
public class PwdRequestForm extends org.apache.struts.action.ActionForm {

    private String personal;
    private String[] requestId;  //requestsurName
    private String mode;
    private String personalsurName;
    private String name;
    private String relationNmae;
    private String dateOfBirth;
    private String district_id;
    private String mandal_id;
    private String village_id;
    private String habitation_id;
    private String requestTypeId;
    private String requestTypeName;
    private ArrayList requestTypeList = new ArrayList();
    private ArrayList requestTypeDetails = new ArrayList();
    private String personalName;
    private String personalrelationName;
    private ArrayList mandallist = new ArrayList();
    private String mandal_name;
    private ArrayList villagelist = new ArrayList();
    private String village_name;
    private String habitation_name;
    private ArrayList habitationlist = new ArrayList();
    private String pinCode;
    private String contactNo;
    private String radiovalue;
    private FormFile addressProof;
    private FormFile sscFile;
    private String radiovalueSingle;
    private String requestName;
    private String requestRelationName;
    private String requestMoles;
    private String requestDistrict_id;
    private String requestMandal_id;
    private String requestVillage_id;
    private String requestHabitation_id;
    private String npPresentMandalID;
    private String npPresentVillageID;
    private String npPresentHabitationID;
    private String npPresentDistrictID;
    private String sadaremID;
    private String requestMolesTwo;
    private ArrayList mandalListData;
    private ArrayList villageListData;
    private ArrayList habitationListData;
    private String mandal_nameData;
    private String village_nameData;
    private String habitation_nameData;
    private String habitation_idData;
    private String mandal_idData;
    private String village_idData;
    private String requestTyepeId;
    private String requestDetailsId;
    private String email;
    private String houseNo;
    private String sadaremIDDetails;
    private String requestsurName;
    //To populate Recipt Details List
    private String reciptRequestId;
    private String reciptPersonCode;
    private String reciptSurName;
    private String reciptName;
    private String reciptRelationName;
    private String reciptHouseno;
    private String reciptDistrictName;
    private String reciptMandalName;
    private String reciptVillageName;
    private String reciptHabitationName;
    private String reciptRegisteratedDate;
    private String reciptRequestTypeName;
    private String requestHouseNo;
    private String requestPinCode;
    private String radio3;
    private String personalDatasurName;
    private String personalDataName;
    private String personalDatarelationName;
    private String name1;
    private String surName1;
    private String houseNo1; // relationName
    private String houseNo2; //Name
    private String houseNo3;  //surname
    //Hidden Properties to carry Data For New Certidifcate
    private String hiddenpersonalName;
    private String hiddenPersonalrelationName;
    private String hiddenHouseNo;
    private String hiddenDistrictId;
    private String hiddenMandalId;
    private String hiddenVillageId;
    private String hiddenHabitationId;
    private String hiddenPinCode;
    private String hiddenContactNO;
    private String hiddenEmail;
    private String panchayat;
    //end
    // NewCertificate PartA
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
    private int gender;
    private int education;
    private int employment;
    private int marital;
    private int caste;
    private int religion;
    private String card;
    private int rtype;
    private int rationCardSlno;
    private boolean epiccard;
    private String epiccardno;
    private boolean pensioncard;
    private String pension_type;
    private String pensioncardno;
    private String mole1;
    private String mole2;
    private boolean parents_marriage;
    private String gsurname;
    private int grelation;
    private String firstnameenglish;
    private String firstnametelugu;
    private String houseno;
    private String requestDistrict_idn;
    private String requestMandal_idn;
    private String requestVillage_idn;
    private String requestHabitation_idn;
    private String phoneno;
    private String emailId;
    private String pin;
    private int type_disability;
    private int existingpercentage;
    private String personstatus;
    private String telugufathername;
    private FormFile documents;
    private FormFile addressDocuments;
    private String relationName1;
    private String districtlist;
    private String district_name;
    private String district;
    private ArrayList panchayatlist;
    private String panchaith;
    private String panchayatiid;
    private String Eligible;
    private String panchayat_id;
    private String districts_id;
    private String mandals_id;
    private String villages_id;
    private String habitations_id;
    private String requestFormId;
    private String renualId;
    private String reAssessmentId;
    private ArrayList districtList = new ArrayList();

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public String getReAssessmentId() {
        return reAssessmentId;
    }

    public void setReAssessmentId(String reAssessmentId) {
        this.reAssessmentId = reAssessmentId;
    }

    public String getRenualId() {
        return renualId;
    }

    public void setRenualId(String renualId) {
        this.renualId = renualId;
    }

    public String getRequestFormId() {
        return requestFormId;
    }

    public void setRequestFormId(String requestFormId) {
        this.requestFormId = requestFormId;
    }
    //msg Display
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getPanchayat_id() {
        return panchayat_id;
    }

    public void setPanchayat_id(String panchayat_id) {
        this.panchayat_id = panchayat_id;
    }

    public String getHabitations_id() {
        return habitations_id;
    }

    public void setHabitations_id(String habitations_id) {
        this.habitations_id = habitations_id;
    }

    public String getMandals_id() {
        return mandals_id;
    }

    public void setMandals_id(String mandals_id) {
        this.mandals_id = mandals_id;
    }

    public String getVillages_id() {
        return villages_id;
    }

    public void setVillages_id(String villages_id) {
        this.villages_id = villages_id;
    }

    public String getDistricts_id() {
        return districts_id;
    }

    public void setDistricts_id(String districts_id) {
        this.districts_id = districts_id;
    }

    public String getPanchayat() {
        return panchayat;
    }

    public void setPanchayat(String panchayat) {
        this.panchayat = panchayat;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEligible() {
        return Eligible;
    }

    public void setEligible(String Eligible) {
        this.Eligible = Eligible;
    }

    public String getPanchaith() {
        return panchaith;
    }

    public void setPanchaith(String panchaith) {
        this.panchaith = panchaith;
    }

    public String getPanchayatiid() {
        return panchayatiid;
    }

    public void setPanchayatiid(String panchayatiid) {
        this.panchayatiid = panchayatiid;
    }

    public ArrayList getPanchayatlist() {
        return panchayatlist;
    }

    public void setPanchayatlist(ArrayList panchayatlist) {
        this.panchayatlist = panchayatlist;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getDistrictlist() {
        return districtlist;
    }

    public void setDistrictlist(String districtlist) {
        this.districtlist = districtlist;
    }

    public String getHouseNo2() {
        return houseNo2;
    }

    public void setHouseNo2(String houseNo2) {
        this.houseNo2 = houseNo2;
    }

    public String getHouseNo3() {
        return houseNo3;
    }

    public void setHouseNo3(String houseNo3) {
        this.houseNo3 = houseNo3;
    }

    public String getRelationName1() {
        return relationName1;
    }

    public void setRelationName1(String relationName1) {
        this.relationName1 = relationName1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getHouseNo1() {
        return houseNo1;
    }

    public void setHouseNo1(String houseNo1) {
        this.houseNo1 = houseNo1;
    }

    public String getSurName1() {
        return surName1;
    }

    public void setSurName1(String surName1) {
        this.surName1 = surName1;
    }

    public String getPersonalDataName() {
        return personalDataName;
    }

    public void setPersonalDataName(String personalDataName) {
        this.personalDataName = personalDataName;
    }

    public String getPersonalDatarelationName() {
        return personalDatarelationName;
    }

    public void setPersonalDatarelationName(String personalDatarelationName) {
        this.personalDatarelationName = personalDatarelationName;
    }

    public String getPersonalDatasurName() {
        return personalDatasurName;
    }

    public void setPersonalDatasurName(String personalDatasurName) {
        this.personalDatasurName = personalDatasurName;
    }

    public String getReciptSurName() {
        return reciptSurName;
    }

    public void setReciptSurName(String reciptSurName) {
        this.reciptSurName = reciptSurName;
    }

    public String getPersonalsurName() {
        return personalsurName;
    }

    public void setPersonalsurName(String personalsurName) {
        this.personalsurName = personalsurName;
    }

    public String getRadio3() {
        return radio3;
    }

    public void setRadio3(String radio3) {
        this.radio3 = radio3;
    }

    public String getRequestsurName() {
        return requestsurName;
    }

    public void setRequestsurName(String requestsurName) {
        this.requestsurName = requestsurName;
    }

    public FormFile getAddressDocuments() {
        return addressDocuments;
    }

    public void setAddressDocuments(FormFile addressDocuments) {
        this.addressDocuments = addressDocuments;
    }

    public FormFile getDocuments() {
        return documents;
    }

    public void setDocuments(FormFile documents) {
        this.documents = documents;
    }

    public String getTelugufathername() {
        return telugufathername;
    }

    public void setTelugufathername(String telugufathername) {
        this.telugufathername = telugufathername;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEpiccardno() {
        return epiccardno;
    }

    public void setEpiccardno(String epiccardno) {
        this.epiccardno = epiccardno;
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

    public String getGsurname() {
        return gsurname;
    }

    public void setGsurname(String gsurname) {
        this.gsurname = gsurname;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
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

    public String getPension_type() {
        return pension_type;
    }

    public void setPension_type(String pension_type) {
        this.pension_type = pension_type;
    }

    public String getPensioncardno() {
        return pensioncardno;
    }

    public void setPensioncardno(String pensioncardno) {
        this.pensioncardno = pensioncardno;
    }

    public String getPersonstatus() {
        return personstatus;
    }

    public void setPersonstatus(String personstatus) {
        this.personstatus = personstatus;
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

    public int getRationCardSlno() {
        return rationCardSlno;
    }

    public void setRationCardSlno(int rationCardSlno) {
        this.rationCardSlno = rationCardSlno;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public String getRequestDistrict_idn() {
        return requestDistrict_idn;
    }

    public void setRequestDistrict_idn(String requestDistrict_idn) {
        this.requestDistrict_idn = requestDistrict_idn;
    }

    public String getRequestHabitation_idn() {
        return requestHabitation_idn;
    }

    public void setRequestHabitation_idn(String requestHabitation_idn) {
        this.requestHabitation_idn = requestHabitation_idn;
    }

    public String getRequestMandal_idn() {
        return requestMandal_idn;
    }

    public void setRequestMandal_idn(String requestMandal_idn) {
        this.requestMandal_idn = requestMandal_idn;
    }

    public String getRequestVillage_idn() {
        return requestVillage_idn;
    }

    public void setRequestVillage_idn(String requestVillage_idn) {
        this.requestVillage_idn = requestVillage_idn;
    }

    public int getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
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

    public int getCaste() {
        return caste;
    }

    public void setCaste(int caste) {
        this.caste = caste;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getEmployment() {
        return employment;
    }

    public void setEmployment(int employment) {
        this.employment = employment;
    }

    public boolean isEpiccard() {
        return epiccard;
    }

    public void setEpiccard(boolean epiccard) {
        this.epiccard = epiccard;
    }

    public int getExistingpercentage() {
        return existingpercentage;
    }

    public void setExistingpercentage(int existingpercentage) {
        this.existingpercentage = existingpercentage;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGrelation() {
        return grelation;
    }

    public void setGrelation(int grelation) {
        this.grelation = grelation;
    }

    public int getMarital() {
        return marital;
    }

    public void setMarital(int marital) {
        this.marital = marital;
    }

    public boolean isParents_marriage() {
        return parents_marriage;
    }

    public void setParents_marriage(boolean parents_marriage) {
        this.parents_marriage = parents_marriage;
    }

    public boolean isPensioncard() {
        return pensioncard;
    }

    public void setPensioncard(boolean pensioncard) {
        this.pensioncard = pensioncard;
    }

    public int getType_disability() {
        return type_disability;
    }

    public void setType_disability(int type_disability) {
        this.type_disability = type_disability;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    //end
    public String getRequestPinCode() {
        return requestPinCode;
    }

    public void setRequestPinCode(String requestPinCode) {
        this.requestPinCode = requestPinCode;
    }

    public String getRequestHouseNo() {
        return requestHouseNo;
    }

    public void setRequestHouseNo(String requestHouseNo) {
        this.requestHouseNo = requestHouseNo;
    }

    public String getReciptDistrictName() {
        return reciptDistrictName;
    }

    public void setReciptDistrictName(String reciptDistrictName) {
        this.reciptDistrictName = reciptDistrictName;
    }

    public String getReciptHabitationName() {
        return reciptHabitationName;
    }

    public void setReciptHabitationName(String reciptHabitationName) {
        this.reciptHabitationName = reciptHabitationName;
    }

    public String getReciptHouseno() {
        return reciptHouseno;
    }

    public void setReciptHouseno(String reciptHouseno) {
        this.reciptHouseno = reciptHouseno;
    }

    public String getReciptMandalName() {
        return reciptMandalName;
    }

    public void setReciptMandalName(String reciptMandalName) {
        this.reciptMandalName = reciptMandalName;
    }

    public String getReciptName() {
        return reciptName;
    }

    public void setReciptName(String reciptName) {
        this.reciptName = reciptName;
    }

    public String getReciptPersonCode() {
        return reciptPersonCode;
    }

    public void setReciptPersonCode(String reciptPersonCode) {
        this.reciptPersonCode = reciptPersonCode;
    }

    public String getReciptRegisteratedDate() {
        return reciptRegisteratedDate;
    }

    public void setReciptRegisteratedDate(String reciptRegisteratedDate) {
        this.reciptRegisteratedDate = reciptRegisteratedDate;
    }

    public String getReciptRelationName() {
        return reciptRelationName;
    }

    public void setReciptRelationName(String reciptRelationName) {
        this.reciptRelationName = reciptRelationName;
    }

    public String getReciptRequestId() {
        return reciptRequestId;
    }

    public void setReciptRequestId(String reciptRequestId) {
        this.reciptRequestId = reciptRequestId;
    }

    public String getReciptRequestTypeName() {
        return reciptRequestTypeName;
    }

    public void setReciptRequestTypeName(String reciptRequestTypeName) {
        this.reciptRequestTypeName = reciptRequestTypeName;
    }

    public String getReciptVillageName() {
        return reciptVillageName;
    }

    public void setReciptVillageName(String reciptVillageName) {
        this.reciptVillageName = reciptVillageName;
    }

    public String getRequestDetailsId() {
        return requestDetailsId;
    }

    public String getSadaremIDDetails() {
        return sadaremIDDetails;
    }

    public void setSadaremIDDetails(String sadaremIDDetails) {
        this.sadaremIDDetails = sadaremIDDetails;
    }

    public void setRequestDetailsId(String requestDetailsId) {
        this.requestDetailsId = requestDetailsId;
    }

    public String[] getRequestId() {
        return requestId;
    }

    public void setRequestId(String[] requestId) {
        this.requestId = requestId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequestTyepeId() {
        return requestTyepeId;
    }

    public void setRequestTyepeId(String requestTyepeId) {
        this.requestTyepeId = requestTyepeId;
    }

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

    public String getRequestMolesTwo() {
        return requestMolesTwo;
    }

    public void setRequestMolesTwo(String requestMolesTwo) {
        this.requestMolesTwo = requestMolesTwo;
    }

    public String getSadaremID() {
        return sadaremID;
    }

    public void setSadaremID(String sadaremID) {
        this.sadaremID = sadaremID;
    }

    public String getRequestHabitation_id() {
        return requestHabitation_id;
    }

    public void setRequestHabitation_id(String requestHabitation_id) {
        this.requestHabitation_id = requestHabitation_id;
    }

    public String getRequestVillage_id() {
        return requestVillage_id;
    }

    public void setRequestVillage_id(String requestVillage_id) {
        this.requestVillage_id = requestVillage_id;
    }

    public String getNpPresentDistrictID() {
        return npPresentDistrictID;
    }

    public void setNpPresentDistrictID(String npPresentDistrictID) {
        this.npPresentDistrictID = npPresentDistrictID;
    }

    public String getNpPresentHabitationID() {
        return npPresentHabitationID;
    }

    public void setNpPresentHabitationID(String npPresentHabitationID) {
        this.npPresentHabitationID = npPresentHabitationID;
    }

    public String getNpPresentVillageID() {
        return npPresentVillageID;
    }

    public void setNpPresentVillageID(String npPresentVillageID) {
        this.npPresentVillageID = npPresentVillageID;
    }

    public String getNpPresentMandalID() {
        return npPresentMandalID;
    }

    public void setNpPresentMandalID(String npPresentMandalID) {
        this.npPresentMandalID = npPresentMandalID;
    }

    public String getRequestMandal_id() {
        return requestMandal_id;
    }

    public void setRequestMandal_id(String requestMandal_id) {
        this.requestMandal_id = requestMandal_id;
    }

    public String getRequestDistrict_id() {
        return requestDistrict_id;
    }

    public void setRequestDistrict_id(String requestDistrict_id) {
        this.requestDistrict_id = requestDistrict_id;
    }

    public String getRequestMoles() {
        return requestMoles;
    }

    public void setRequestMoles(String requestMoles) {
        this.requestMoles = requestMoles;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestRelationName() {
        return requestRelationName;
    }

    public void setRequestRelationName(String requestRelationName) {
        this.requestRelationName = requestRelationName;
    }

    public String getRadiovalueSingle() {
        return radiovalueSingle;
    }

    public void setRadiovalueSingle(String radiovalueSingle) {
        this.radiovalueSingle = radiovalueSingle;
    }

    public FormFile getAddressProof() {
        return addressProof;
    }

    public void setAddressProof(FormFile addressProof) {
        this.addressProof = addressProof;
    }

    public FormFile getSscFile() {
        return sscFile;
    }

    public void setSscFile(FormFile sscFile) {
        this.sscFile = sscFile;
    }

    public String getRadiovalue() {
        return radiovalue;
    }

    public void setRadiovalue(String radiovalue) {
        this.radiovalue = radiovalue;
    }

    public ArrayList getRequestTypeDetails() {
        return requestTypeDetails;
    }

    public void setRequestTypeDetails(ArrayList requestTypeDetails) {
        this.requestTypeDetails = requestTypeDetails;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getHabitation_name() {
        return habitation_name;
    }

    public void setHabitation_name(String habitation_name) {
        this.habitation_name = habitation_name;
    }

    public ArrayList getHabitationlist() {
        return habitationlist;
    }

    public void setHabitationlist(ArrayList habitationlist) {
        this.habitationlist = habitationlist;
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

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getPersonalrelationName() {
        return personalrelationName;
    }

    public void setPersonalrelationName(String personalrelationName) {
        this.personalrelationName = personalrelationName;
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

    public String getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(String requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public ArrayList getRequestTypeList() {
        return requestTypeList;
    }

    public void setRequestTypeList(ArrayList requestTypeList) {
        this.requestTypeList = requestTypeList;
    }

    public String getRequestTypeName() {
        return requestTypeName;
    }

    public void setRequestTypeName(String requestTypeName) {
        this.requestTypeName = requestTypeName;
    }

    public String getHabitation_id() {
        return habitation_id;
    }

    public void setHabitation_id(String habitation_id) {
        this.habitation_id = habitation_id;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationNmae() {
        return relationNmae;
    }

    public void setRelationNmae(String relationNmae) {
        this.relationNmae = relationNmae;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getHiddenContactNO() {
        return hiddenContactNO;
    }

    public void setHiddenContactNO(String hiddenContactNO) {
        this.hiddenContactNO = hiddenContactNO;
    }

    public String getHiddenDistrictId() {
        return hiddenDistrictId;
    }

    public void setHiddenDistrictId(String hiddenDistrictId) {
        this.hiddenDistrictId = hiddenDistrictId;
    }

    public String getHiddenEmail() {
        return hiddenEmail;
    }

    public void setHiddenEmail(String hiddenEmail) {
        this.hiddenEmail = hiddenEmail;
    }

    public String getHiddenHabitationId() {
        return hiddenHabitationId;
    }

    public void setHiddenHabitationId(String hiddenHabitationId) {
        this.hiddenHabitationId = hiddenHabitationId;
    }

    public String getHiddenHouseNo() {
        return hiddenHouseNo;
    }

    public void setHiddenHouseNo(String hiddenHouseNo) {
        this.hiddenHouseNo = hiddenHouseNo;
    }

    public String getHiddenMandalId() {
        return hiddenMandalId;
    }

    public void setHiddenMandalId(String hiddenMandalId) {
        this.hiddenMandalId = hiddenMandalId;
    }

    public String getHiddenPersonalrelationName() {
        return hiddenPersonalrelationName;
    }

    public void setHiddenPersonalrelationName(String hiddenPersonalrelationName) {
        this.hiddenPersonalrelationName = hiddenPersonalrelationName;
    }

    public String getHiddenPinCode() {
        return hiddenPinCode;
    }

    public void setHiddenPinCode(String hiddenPinCode) {
        this.hiddenPinCode = hiddenPinCode;
    }

    public String getHiddenVillageId() {
        return hiddenVillageId;
    }

    public void setHiddenVillageId(String hiddenVillageId) {
        this.hiddenVillageId = hiddenVillageId;
    }

    public String getHiddenpersonalName() {
        return hiddenpersonalName;
    }

    public void setHiddenpersonalName(String hiddenpersonalName) {
        this.hiddenpersonalName = hiddenpersonalName;
    }
}
