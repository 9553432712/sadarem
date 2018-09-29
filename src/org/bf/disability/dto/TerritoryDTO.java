package org.bf.disability.dto;

import java.util.ArrayList;

/**
 * This class contains the fields, used is terrtory selection module
 * @version 1.0
 */
public class TerritoryDTO {

    /** Creates a new instance of TerritoryDTO */
    public TerritoryDTO() {
    }
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
    private String Assembly_id;
    private String personcode;
    private String UpperExtremity_Total;
    private String LowerExtremity_Total;
    private String Amputation_Total;
    private String Transverse_Total;
    private String Trunk_Total;
    private String Physical_Impairments_Total;
    private String Pulmonary_Condition;
    private String Dwarfism_Total;
    private String Mental_Disability_Total;
    private String Mental_Retardation_Total;
    private String Visual_Impairment;
    private String Hearing_Percentage;
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
    private String othercause;
    private String congenital;
    private String hereditary;
    private String birthinjury;
    private String duseaseandinfection;
    private String malnutrition;
    private String accident;
    private String disabilitytype;
    private double disabilityvalue;
    private String disabilityId;
    private boolean  railwaycertificate;
    private double locomotortotal;
    private String housenumber;
    private String hospitalname;
    private String hospitaladdress;
    private String dateofisse;
    private String dateofbirth;
    private String firstdoctorname;
    private String firstdoctornumber;
    private String firstdesgination;
    private String seconddoctorname;
    private String seconddoctornumber;
    private String seconddesgination;
    private String moleone;
    private String moletwo;
    private String thirddoctorname;
    private String thirddoctornumber;
    private String thirddesgination;
    private String telugupersonname;
    private String telugufathername;
    private String relationship;
    private String disabilityintellec;
    private String typeofdisability;
    private String disabilityreason;
    private String disabilityduration;
    private String disabilitySubIds;
    private String disabilitySubIds1;
    private String disabilitySubIds2;
    private String disabilitySubIds3;
    private String disabilitySubIds4;
    private String disabilitySubIds5;
    private String disabilitySubIds6;
    private String disabilitySubIds7;
    private String disabilitySubIds8;
    private String disabilitySubIds9;
    private String disabilitySubIds10;
    private String disabilitySubIds11;
    private String disabilitySubIds12;
    private String disabilitySubIds13;
    private String disabilitySubIds14;
    private String disabilitySubIds15;
    private String disabilitySubIds16;
    private String disabilitySubIds17;
    private String disabilitySubIds18;
    private String disabilitySubIds19;
    private String disabilitySubIds20;
    private String disabilitySubIds21;
    private String disabilitySubIds22;
    private String disabilitySubIds23;
    private String disabilitySubIds24;
    private String disabilitySubIds25;
    private String disabilitySubIds26;
    private String disabilitySubIds27;
    private String disabilitySubIds28;
    private String disabilitySubIds29;
    private String disabilitySubIds30;
    private String disabilitySubIds31;
    private String disabilitySubSubIds;
    private String disabilitySubSubIds1;
    private String disabilitySubSubIds2;
    private String disabilitySubSubIds3;
    private String disabilitySubSubIds4;
    private String disabilitySubSubIds5;
    private String disabilitySubSubIds6;
    private String disabilitySubSubIds7;
    private String disabilitySubSubIds8;
    private String disabilitySubSubIds9;
    private String disabilitySubSubIds10;
    private String coordinate_lifting;
    private String coordinate_touching;
    private String coordinate_eating;
    private String coordinate_combing;
    private String coordinate_putting;
    private String coordinate_ablution;
    private String coordinate_buttoning;
    private String coordinate_tie;
    private String coordinate_writing;
    private String flag;
    private String proof_id;
    private String proofdoc_type;
    
    //Added by sowjanya
    private String category;

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProof_id() {
        return proof_id;
    }

    public void setProof_id(String proof_id) {
        this.proof_id = proof_id;
    }

    public String getProofdoc_type() {
        return proofdoc_type;
    }

    public void setProofdoc_type(String proofdoc_type) {
        this.proofdoc_type = proofdoc_type;
    }

    

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    // New field added by siva
        private String coordinate_drinking;

        private String reasonforstatus;

        public String getCoordinate_drinking() {
        return coordinate_drinking;
        }

         public void setCoordinate_drinking(String coordinate_drinking) {
        this.coordinate_drinking = coordinate_drinking;
        }
    private String Hand_stgrip_right;
    private String Hand_stgrip_left;
    private String Hand_stpinch_right;
    private String Hand_stpinch_left;
    private String coordinate;
    private String strength;
    private double UpperExterimity_total;
    //Added BY Raghavendra for DailyReports date wise
    private String belowfourtyforphysical = "0";
    private String fourtytosixtyforphysical = "0";
    private String sixtytoeightyforphysical = "0";
    private String aboveeightyoneforphysical = "0";
    private String totalforphysical = "0";
    private String belowfourtyforvisual = "0";
    private String fourtytosixtyforvisual = "0";
    private String sixtyonetoeightyforvisual = "0";
    private String aboveeightyoneforvisual = "0";
    private String belowfourtyforhearing = "0";
    private String fourtytosixtyforhearing = "0";
    private String sixtyonetoeightyforhearing = "0";
    private String aboveeightyoneforhearing = "0";
    private String totalforhearing = "0";
    private String belowfourtyformentalretardation = "0";
    private String fourtytosixtyformentalretardation = "0";
    private String sixtyonetoeightyformentalretardation = "0";
    private String aboveeightyoneformentalretardation = "0";
    private String totalformentalretardation = "0";
    private String belowfourtyformentalillness = "0";
    private String fourtytosixtyformentalillness = "0";
    private String sixtyonetoeightyformentalillness = "0";
    private String aboveeightyoneformentalillness = "0";
    private String totalformentalillness = "0";
    private String totalforvisual = "0";
    private String belowfourtyformultipledisability = "0";
    private String fourtytosixtyformultipledisability = "0";
    private String sixtyonetoeightyformultipledisability = "0";
    private String aboveeightyoneformultipledisability = "0";
    private String totalformultipledisability = "0";
    private String totaldisability = "0";
    private String fromdate;
    private String todate;
    private String belowfourtypercent = "0";
    private String fourtypercent = "0";
    private String fourtytosixtypercent = "0";
    private String sixtyonetoeightypercent = "0";
    private String aboveeightyonepercent = "0";
    private String disabledpensioners = "0";
    private String noofpersonsassessed = "0";
    private String noofpersonsrejected = "0";
    private String personsassesedbelowfourty = "0";
    private String personsassesedfourtytosixty = "0";
    private String personsassesedsixtytoeighty = "0";
    private String personsassesedaboveeighty = "0";
    private String rejectedpersonscount = "0";
    private String assessedandrejectedtotal = "0";
    //END BY Raghavendra for DailyReports date wise
    private String personstatus;
    // Death case
    private String status;
    private String period;
    private String kindofdisability;
    private String surgery;
    private String physiotherapy;
    private String councelling;
    private String anyother;
    private String rejected;
    private String referredto;
    private String surgeryforrejected;
    private String councellingandguidance;
    private String speechtherapy;
    private String hearingaid;
    private String behaviourmodification;
    private String phychotherapy;
    private String admissioninpsychiatrichospital;
    private String Physiotherapyforreject;
    private String lowvisionaid;
    private String anyotherneed;
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
    private boolean brFlag;
    private String validity;
    private String genderInitial1;
    private String genderInitial2;
    private String percentageInWord;
    private int disabilityPercentage;
    private String teluguDisabilityName;
    private String multipleRadio;
    private String loco;
    private String ohcondition1;
    private String ohcondition2;
    private String ohcondition3;
    private String ohcondition4;
    private String ohcondition5;
    private String ohcondition6;
    private String ohcondition7;
    private String ohcondition8;
    private String ohcondition9;
    private String ohcondition10;
    private String ohcondition11;
    private String comment = null;
    private String global_Disablity;
    private String global_Disablity_InWords;
    private String hearing_DB;
    private String hearing_DB_In_Words;
    private String mentalRetardation_IQ;
    private String mentalRetardation_IQ_Inwords;

    // Added for Visual Impairment
    private String congenital_BetterEye;
    private String congenital_WorseEye;
    private String hereditary_BetterEye;
    private String hereditary_WorseEye;
    private String birthinjury_BetterEye;
    private String birthinjury_WorseEye;
    private String diseaseandinfection_BetterEye;
    private String diseaseandinfection_WorseEye;
    private String malnutrition_BetterEye;
    private String malnutrition_WorseEye;
    private String accident_BetterEye;
    private String accident_WorseEye;

     // added two cause of disanbilities.
   private String highfever;
   private String epilepsy;
   private String birthasphyxia;

   // added for displaying certificate

   private String maritialstatus;


    private String component_walkingonplane;
    private String component_walkingonslope;
    private String component_climbingstairs;
    private String component_standingonbothlegs;
    private String component_standingoneffectedleg;
    private String component_squattingonfloor;
    private String component_sittingcrossleg;
    private String component_kneeling;
    private String component_takingturns;

    private String totalsubdisabilities;
    private String totalsubsubdisabilities;
    private String totalcauseofdisabilities;
    private String totallocomotorarmlegeffectedlist;
    private String physicalrequirementlist;
    private String disabilitycondition;

    // added for multiple disability certificate.
    private String physicalImpairmentName;
    private String hearingImpairmentName;
    private String visualImpairmentName;
    private String mentalRetardationName;
    private String mentalIllnessName;

    private String hearingImpairmentCondition;
    private String mentalRetardationCondition;
    private String mentalIllnessCondition;

    private int physicalImpairmentPercentage;
    private int hearingImpairmentPercentage;
    private int visualImpairmentPercentage;
    private int mentalRetardationPercentage;
    private int mentalIllnessPercentage;

    private String physicalImpairmentPhysicalRequirementList;
    private String hearingImpairmentPhysicalRequirementList;
    private String visualImpairmentPhysicalRequirementList;
    private String mentalRetardationPhysicalRequirementList;
    private String multipleDisabilityPhysicalRequirementList;

    private String physicalImpairmentTotalsubdisabilities;
    private String visualImpairmentTotalsubdisabilities;

    private String genderInTelugu;
    private String disabilityTypeInIdCard;
    private String disabilityTypeInTelugu;

    private ArrayList districtList = new ArrayList();
 
    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
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

    public String getAssembly_id() {
        return Assembly_id;
    }

    public void setAssembly_id(String Assembly_id) {
        this.Assembly_id = Assembly_id;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getUpperExtremity_Total() {
        return UpperExtremity_Total;
    }

    public void setUpperExtremity_Total(String UpperExtremity_Total) {
        this.UpperExtremity_Total = UpperExtremity_Total;
    }

    public String getLowerExtremity_Total() {
        return LowerExtremity_Total;
    }

    public void setLowerExtremity_Total(String LowerExtremity_Total) {
        this.LowerExtremity_Total = LowerExtremity_Total;
    }

    public String getAmputation_Total() {
        return Amputation_Total;
    }

    public void setAmputation_Total(String Amputation_Total) {
        this.Amputation_Total = Amputation_Total;
    }

    public String getTransverse_Total() {
        return Transverse_Total;
    }

    public void setTransverse_Total(String Transverse_Total) {
        this.Transverse_Total = Transverse_Total;
    }

    public String getTrunk_Total() {
        return Trunk_Total;
    }

    public void setTrunk_Total(String Trunk_Total) {
        this.Trunk_Total = Trunk_Total;
    }

    public String getPhysical_Impairments_Total() {
        return Physical_Impairments_Total;
    }

    public void setPhysical_Impairments_Total(String Physical_Impairments_Total) {
        this.Physical_Impairments_Total = Physical_Impairments_Total;
    }

    public String getPulmonary_Condition() {
        return Pulmonary_Condition;
    }

    public void setPulmonary_Condition(String Pulmonary_Condition) {
        this.Pulmonary_Condition = Pulmonary_Condition;
    }

    public String getDwarfism_Total() {
        return Dwarfism_Total;
    }

    public void setDwarfism_Total(String Dwarfism_Total) {
        this.Dwarfism_Total = Dwarfism_Total;
    }

    public String getMental_Disability_Total() {
        return Mental_Disability_Total;
    }

    public void setMental_Disability_Total(String Mental_Disability_Total) {
        this.Mental_Disability_Total = Mental_Disability_Total;
    }

    public String getMental_Retardation_Total() {
        return Mental_Retardation_Total;
    }

    public void setMental_Retardation_Total(String Mental_Retardation_Total) {
        this.Mental_Retardation_Total = Mental_Retardation_Total;
    }

    public String getVisual_Impairment() {
        return Visual_Impairment;
    }

    public void setVisual_Impairment(String Visual_Impairment) {
        this.Visual_Impairment = Visual_Impairment;
    }

    public String getHearing_Percentage() {
        return Hearing_Percentage;
    }

    public void setHearing_Percentage(String Hearing_Percentage) {
        this.Hearing_Percentage = Hearing_Percentage;
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

    public String getDisabilitytype() {
        return disabilitytype;
    }

    public void setDisabilitytype(String disabilitytype) {
        this.disabilitytype = disabilitytype;
    }

    public double getDisabilityvalue() {
        return disabilityvalue;
    }

    public void setDisabilityvalue(double disabilityvalue) {
        this.disabilityvalue = disabilityvalue;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
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

    public double getLocomotortotal() {
        return locomotortotal;
    }

    public void setLocomotortotal(double locomotortotal) {
        this.locomotortotal = locomotortotal;
    }

    public String getDateofisse() {
        return dateofisse;
    }

    public void setDateofisse(String dateofisse) {
        this.dateofisse = dateofisse;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getFirstdoctorname() {
        return firstdoctorname;
    }

    public void setFirstdoctorname(String firstdoctorname) {
        this.firstdoctorname = firstdoctorname;
    }

    public String getFirstdoctornumber() {
        return firstdoctornumber;
    }

    public void setFirstdoctornumber(String firstdoctornumber) {
        this.firstdoctornumber = firstdoctornumber;
    }

    public String getFirstdesgination() {
        return firstdesgination;
    }

    public void setFirstdesgination(String firstdesgination) {
        this.firstdesgination = firstdesgination;
    }

    public String getSeconddoctorname() {
        return seconddoctorname;
    }

    public void setSeconddoctorname(String seconddoctorname) {
        this.seconddoctorname = seconddoctorname;
    }

    public String getSeconddoctornumber() {
        return seconddoctornumber;
    }

    public void setSeconddoctornumber(String seconddoctornumber) {
        this.seconddoctornumber = seconddoctornumber;
    }

    public String getSeconddesgination() {
        return seconddesgination;
    }

    public void setSeconddesgination(String seconddesgination) {
        this.seconddesgination = seconddesgination;
    }

    public String getMoleone() {
        return moleone;
    }

    public void setMoleone(String moleone) {
        this.moleone = moleone;
    }

    public String getMoletwo() {
        return moletwo;
    }

    public void setMoletwo(String moletwo) {
        this.moletwo = moletwo;
    }

    public String getThirddoctorname() {
        return thirddoctorname;
    }

    public void setThirddoctorname(String thirddoctorname) {
        this.thirddoctorname = thirddoctorname;
    }

    public String getThirddoctornumber() {
        return thirddoctornumber;
    }

    public void setThirddoctornumber(String thirddoctornumber) {
        this.thirddoctornumber = thirddoctornumber;
    }

    public String getThirddesgination() {
        return thirddesgination;
    }

    public void setThirddesgination(String thirddesgination) {
        this.thirddesgination = thirddesgination;
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getDisabilityintellec() {
        return disabilityintellec;
    }

    public void setDisabilityintellec(String disabilityintellec) {
        this.disabilityintellec = disabilityintellec;
    }

    public String getTypeofdisability() {
        return typeofdisability;
    }

    public void setTypeofdisability(String typeofdisability) {
        this.typeofdisability = typeofdisability;
    }

    public String getDisabilityreason() {
        return disabilityreason;
    }

    public void setDisabilityreason(String disabilityreason) {
        this.disabilityreason = disabilityreason;
    }

    public String getDisabilityduration() {
        return disabilityduration;
    }

    public void setDisabilityduration(String disabilityduration) {
        this.disabilityduration = disabilityduration;
    }

    public String getBelowfourtyforphysical() {
        return belowfourtyforphysical;
    }

    public void setBelowfourtyforphysical(String belowfourtyforphysical) {
        this.belowfourtyforphysical = belowfourtyforphysical;
    }

    public String getFourtytosixtyforphysical() {
        return fourtytosixtyforphysical;
    }

    public void setFourtytosixtyforphysical(String fourtytosixtyforphysical) {
        this.fourtytosixtyforphysical = fourtytosixtyforphysical;
    }

    public String getSixtytoeightyforphysical() {
        return sixtytoeightyforphysical;
    }

    public void setSixtytoeightyforphysical(String sixtytoeightyforphysical) {
        this.sixtytoeightyforphysical = sixtytoeightyforphysical;
    }

    public String getAboveeightyoneforphysical() {
        return aboveeightyoneforphysical;
    }

    public void setAboveeightyoneforphysical(String aboveeightyoneforphysical) {
        this.aboveeightyoneforphysical = aboveeightyoneforphysical;
    }

    public String getTotalforphysical() {
        return totalforphysical;
    }

    public void setTotalforphysical(String totalforphysical) {
        this.totalforphysical = totalforphysical;
    }

    public String getBelowfourtyforvisual() {
        return belowfourtyforvisual;
    }

    public void setBelowfourtyforvisual(String belowfourtyforvisual) {
        this.belowfourtyforvisual = belowfourtyforvisual;
    }

    public String getFourtytosixtyforvisual() {
        return fourtytosixtyforvisual;
    }

    public void setFourtytosixtyforvisual(String fourtytosixtyforvisual) {
        this.fourtytosixtyforvisual = fourtytosixtyforvisual;
    }

    public String getSixtyonetoeightyforvisual() {
        return sixtyonetoeightyforvisual;
    }

    public void setSixtyonetoeightyforvisual(String sixtyonetoeightyforvisual) {
        this.sixtyonetoeightyforvisual = sixtyonetoeightyforvisual;
    }

    public String getAboveeightyoneforvisual() {
        return aboveeightyoneforvisual;
    }

    public void setAboveeightyoneforvisual(String aboveeightyoneforvisual) {
        this.aboveeightyoneforvisual = aboveeightyoneforvisual;
    }

    public String getBelowfourtyforhearing() {
        return belowfourtyforhearing;
    }

    public void setBelowfourtyforhearing(String belowfourtyforhearing) {
        this.belowfourtyforhearing = belowfourtyforhearing;
    }

    public String getFourtytosixtyforhearing() {
        return fourtytosixtyforhearing;
    }

    public void setFourtytosixtyforhearing(String fourtytosixtyforhearing) {
        this.fourtytosixtyforhearing = fourtytosixtyforhearing;
    }

    public String getSixtyonetoeightyforhearing() {
        return sixtyonetoeightyforhearing;
    }

    public void setSixtyonetoeightyforhearing(String sixtyonetoeightyforhearing) {
        this.sixtyonetoeightyforhearing = sixtyonetoeightyforhearing;
    }

    public String getAboveeightyoneforhearing() {
        return aboveeightyoneforhearing;
    }

    public void setAboveeightyoneforhearing(String aboveeightyoneforhearing) {
        this.aboveeightyoneforhearing = aboveeightyoneforhearing;
    }

    public String getTotalforhearing() {
        return totalforhearing;
    }

    public void setTotalforhearing(String totalforhearing) {
        this.totalforhearing = totalforhearing;
    }

    public String getBelowfourtyformentalretardation() {
        return belowfourtyformentalretardation;
    }

    public void setBelowfourtyformentalretardation(String belowfourtyformentalretardation) {
        this.belowfourtyformentalretardation = belowfourtyformentalretardation;
    }

    public String getFourtytosixtyformentalretardation() {
        return fourtytosixtyformentalretardation;
    }

    public void setFourtytosixtyformentalretardation(String fourtytosixtyformentalretardation) {
        this.fourtytosixtyformentalretardation = fourtytosixtyformentalretardation;
    }

    public String getSixtyonetoeightyformentalretardation() {
        return sixtyonetoeightyformentalretardation;
    }

    public void setSixtyonetoeightyformentalretardation(String sixtyonetoeightyformentalretardation) {
        this.sixtyonetoeightyformentalretardation = sixtyonetoeightyformentalretardation;
    }

    public String getAboveeightyoneformentalretardation() {
        return aboveeightyoneformentalretardation;
    }

    public void setAboveeightyoneformentalretardation(String aboveeightyoneformentalretardation) {
        this.aboveeightyoneformentalretardation = aboveeightyoneformentalretardation;
    }

    public String getTotalformentalretardation() {
        return totalformentalretardation;
    }

    public void setTotalformentalretardation(String totalformentalretardation) {
        this.totalformentalretardation = totalformentalretardation;
    }

    public String getBelowfourtyformentalillness() {
        return belowfourtyformentalillness;
    }

    public void setBelowfourtyformentalillness(String belowfourtyformentalillness) {
        this.belowfourtyformentalillness = belowfourtyformentalillness;
    }

    public String getFourtytosixtyformentalillness() {
        return fourtytosixtyformentalillness;
    }

    public void setFourtytosixtyformentalillness(String fourtytosixtyformentalillness) {
        this.fourtytosixtyformentalillness = fourtytosixtyformentalillness;
    }

    public String getSixtyonetoeightyformentalillness() {
        return sixtyonetoeightyformentalillness;
    }

    public void setSixtyonetoeightyformentalillness(String sixtyonetoeightyformentalillness) {
        this.sixtyonetoeightyformentalillness = sixtyonetoeightyformentalillness;
    }

    public String getAboveeightyoneformentalillness() {
        return aboveeightyoneformentalillness;
    }

    public void setAboveeightyoneformentalillness(String aboveeightyoneformentalillness) {
        this.aboveeightyoneformentalillness = aboveeightyoneformentalillness;
    }

    public String getTotalformentalillness() {
        return totalformentalillness;
    }

    public void setTotalformentalillness(String totalformentalillness) {
        this.totalformentalillness = totalformentalillness;
    }

    public String getBelowfourtyformultipledisability() {
        return belowfourtyformultipledisability;
    }

    public void setBelowfourtyformultipledisability(String belowfourtyformultipledisability) {
        this.belowfourtyformultipledisability = belowfourtyformultipledisability;
    }

    public String getFourtytosixtyformultipledisability() {
        return fourtytosixtyformultipledisability;
    }

    public void setFourtytosixtyformultipledisability(String fourtytosixtyformultipledisability) {
        this.fourtytosixtyformultipledisability = fourtytosixtyformultipledisability;
    }

    public String getSixtyonetoeightyformultipledisability() {
        return sixtyonetoeightyformultipledisability;
    }

    public void setSixtyonetoeightyformultipledisability(String sixtyonetoeightyformultipledisability) {
        this.sixtyonetoeightyformultipledisability = sixtyonetoeightyformultipledisability;
    }

    public String getAboveeightyoneformultipledisability() {
        return aboveeightyoneformultipledisability;
    }

    public void setAboveeightyoneformultipledisability(String aboveeightyoneformultipledisability) {
        this.aboveeightyoneformultipledisability = aboveeightyoneformultipledisability;
    }

    public String getTotalformultipledisability() {
        return totalformultipledisability;
    }

    public void setTotalformultipledisability(String totalformultipledisability) {
        this.totalformultipledisability = totalformultipledisability;
    }

    public String getTotaldisability() {
        return totaldisability;
    }

    public void setTotaldisability(String totaldisability) {
        this.totaldisability = totaldisability;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getBelowfourtypercent() {
        return belowfourtypercent;
    }

    public void setBelowfourtypercent(String belowfourtypercent) {
        this.belowfourtypercent = belowfourtypercent;
    }

    public String getFourtypercent() {
        return fourtypercent;
    }

    public void setFourtypercent(String fourtypercent) {
        this.fourtypercent = fourtypercent;
    }

    public String getFourtytosixtypercent() {
        return fourtytosixtypercent;
    }

    public void setFourtytosixtypercent(String fourtytosixtypercent) {
        this.fourtytosixtypercent = fourtytosixtypercent;
    }

    public String getSixtyonetoeightypercent() {
        return sixtyonetoeightypercent;
    }

    public void setSixtyonetoeightypercent(String sixtyonetoeightypercent) {
        this.sixtyonetoeightypercent = sixtyonetoeightypercent;
    }

    public String getAboveeightyonepercent() {
        return aboveeightyonepercent;
    }

    public void setAboveeightyonepercent(String aboveeightyonepercent) {
        this.aboveeightyonepercent = aboveeightyonepercent;
    }

    public String getTotalforvisual() {
        return totalforvisual;
    }

    public void setTotalforvisual(String totalforvisual) {
        this.totalforvisual = totalforvisual;
    }

    public String getDisabledpensioners() {
        return disabledpensioners;
    }

    public void setDisabledpensioners(String disabledpensioners) {
        this.disabledpensioners = disabledpensioners;
    }

    public String getNoofpersonsassessed() {
        return noofpersonsassessed;
    }

    public void setNoofpersonsassessed(String noofpersonsassessed) {
        this.noofpersonsassessed = noofpersonsassessed;
    }

    public String getNoofpersonsrejected() {
        return noofpersonsrejected;
    }

    public void setNoofpersonsrejected(String noofpersonsrejected) {
        this.noofpersonsrejected = noofpersonsrejected;
    }

    public String getPersonsassesedbelowfourty() {
        return personsassesedbelowfourty;
    }

    public void setPersonsassesedbelowfourty(String personsassesedbelowfourty) {
        this.personsassesedbelowfourty = personsassesedbelowfourty;
    }

    public String getPersonsassesedfourtytosixty() {
        return personsassesedfourtytosixty;
    }

    public void setPersonsassesedfourtytosixty(String personsassesedfourtytosixty) {
        this.personsassesedfourtytosixty = personsassesedfourtytosixty;
    }

    public String getPersonsassesedsixtytoeighty() {
        return personsassesedsixtytoeighty;
    }

    public void setPersonsassesedsixtytoeighty(String personsassesedsixtytoeighty) {
        this.personsassesedsixtytoeighty = personsassesedsixtytoeighty;
    }

    public String getPersonsassesedaboveeighty() {
        return personsassesedaboveeighty;
    }

    public void setPersonsassesedaboveeighty(String personsassesedaboveeighty) {
        this.personsassesedaboveeighty = personsassesedaboveeighty;
    }

    public String getPersonstatus() {
        return personstatus;
    }

    public void setPersonstatus(String personstatus) {
        this.personstatus = personstatus;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getKindofdisability() {
        return kindofdisability;
    }

    public void setKindofdisability(String kindofdisability) {
        this.kindofdisability = kindofdisability;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getPhysiotherapy() {
        return physiotherapy;
    }

    public void setPhysiotherapy(String physiotherapy) {
        this.physiotherapy = physiotherapy;
    }

    public String getCouncelling() {
        return councelling;
    }

    public void setCouncelling(String councelling) {
        this.councelling = councelling;
    }

    public String getAnyother() {
        return anyother;
    }

    public void setAnyother(String anyother) {
        this.anyother = anyother;
    }

    public String getReferredto() {
        return referredto;
    }

    public void setReferredto(String referredto) {
        this.referredto = referredto;
    }

    public String getSurgeryforrejected() {
        return surgeryforrejected;
    }

    public void setSurgeryforrejected(String surgeryforrejected) {
        this.surgeryforrejected = surgeryforrejected;
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

    public String getLowvisionaid() {
        return lowvisionaid;
    }

    public void setLowvisionaid(String lowvisionaid) {
        this.lowvisionaid = lowvisionaid;
    }

    public String getAnyotherneed() {
        return anyotherneed;
    }

    public void setAnyotherneed(String anyotherneed) {
        this.anyotherneed = anyotherneed;
    }

    public String getPhysiotherapyforreject() {
        return Physiotherapyforreject;
    }

    public void setPhysiotherapyforreject(String Physiotherapyforreject) {
        this.Physiotherapyforreject = Physiotherapyforreject;
    }

    public boolean isBrFlag() {
        return brFlag;
    }

    public void setBrFlag(boolean brFlag) {
        this.brFlag = brFlag;
    }

    public String getRejectedpersonscount() {
        return rejectedpersonscount;
    }

    public void setRejectedpersonscount(String rejectedpersonscount) {
        this.rejectedpersonscount = rejectedpersonscount;
    }

    public String getAssessedandrejectedtotal() {
        return assessedandrejectedtotal;
    }

    public void setAssessedandrejectedtotal(String assessedandrejectedtotal) {
        this.assessedandrejectedtotal = assessedandrejectedtotal;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getGenderInitial1() {
        return genderInitial1;
    }

    public void setGenderInitial1(String genderInitial1) {
        this.genderInitial1 = genderInitial1;
    }

    public String getGenderInitial2() {
        return genderInitial2;
    }

    public void setGenderInitial2(String genderInitial2) {
        this.genderInitial2 = genderInitial2;
    }

    public String getPercentageInWord() {
        return percentageInWord;
    }

    public void setPercentageInWord(String percentageInWord) {
        this.percentageInWord = percentageInWord;
    }

    public int getDisabilityPercentage() {
        return disabilityPercentage;
    }

    public void setDisabilityPercentage(int disabilityPercentage) {
        this.disabilityPercentage = disabilityPercentage;
    }

    public String getMultipleRadio() {
        return multipleRadio;
    }

    public void setMultipleRadio(String multipleRadio) {
        this.multipleRadio = multipleRadio;
    }

    public String getTeluguDisabilityName() {
        return teluguDisabilityName;
    }

    public void setTeluguDisabilityName(String teluguDisabilityName) {
        this.teluguDisabilityName = teluguDisabilityName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLoco() {
        return loco;
    }

    public void setLoco(String loco) {
        this.loco = loco;
    }

    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
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
     * @return the ohcondition1
     */
    public String getOhcondition1() {
        return ohcondition1;
    }

    /**
     * @param ohcondition1 the ohcondition1 to set
     */
    public void setOhcondition1(String ohcondition1) {
        this.ohcondition1 = ohcondition1;
    }

    /**
     * @return the ohcondition2
     */
    public String getOhcondition2() {
        return ohcondition2;
    }

    /**
     * @param ohcondition2 the ohcondition2 to set
     */
    public void setOhcondition2(String ohcondition2) {
        this.ohcondition2 = ohcondition2;
    }

    /**
     * @return the ohcondition3
     */
    public String getOhcondition3() {
        return ohcondition3;
    }

    /**
     * @param ohcondition3 the ohcondition3 to set
     */
    public void setOhcondition3(String ohcondition3) {
        this.ohcondition3 = ohcondition3;
    }

    /**
     * @return the ohcondition4
     */
    public String getOhcondition4() {
        return ohcondition4;
    }

    /**
     * @param ohcondition4 the ohcondition4 to set
     */
    public void setOhcondition4(String ohcondition4) {
        this.ohcondition4 = ohcondition4;
    }

    /**
     * @return the ohcondition5
     */
    public String getOhcondition5() {
        return ohcondition5;
    }

    /**
     * @param ohcondition5 the ohcondition5 to set
     */
    public void setOhcondition5(String ohcondition5) {
        this.ohcondition5 = ohcondition5;
    }

    /**
     * @return the ohcondition6
     */
    public String getOhcondition6() {
        return ohcondition6;
    }

    /**
     * @param ohcondition6 the ohcondition6 to set
     */
    public void setOhcondition6(String ohcondition6) {
        this.ohcondition6 = ohcondition6;
    }

    /**
     * @return the ohcondition7
     */
    public String getOhcondition7() {
        return ohcondition7;
    }

    /**
     * @param ohcondition7 the ohcondition7 to set
     */
    public void setOhcondition7(String ohcondition7) {
        this.ohcondition7 = ohcondition7;
    }

    /**
     * @return the ohcondition8
     */
    public String getOhcondition8() {
        return ohcondition8;
    }

    /**
     * @param ohcondition8 the ohcondition8 to set
     */
    public void setOhcondition8(String ohcondition8) {
        this.ohcondition8 = ohcondition8;
    }

    /**
     * @return the ohcondition9
     */
    public String getOhcondition9() {
        return ohcondition9;
    }

    /**
     * @param ohcondition9 the ohcondition9 to set
     */
    public void setOhcondition9(String ohcondition9) {
        this.ohcondition9 = ohcondition9;
    }

    /**
     * @return the ohcondition10
     */
    public String getOhcondition10() {
        return ohcondition10;
    }

    /**
     * @param ohcondition10 the ohcondition10 to set
     */
    public void setOhcondition10(String ohcondition10) {
        this.ohcondition10 = ohcondition10;
    }

    /**
     * @return the ohcondition11
     */
    public String getOhcondition11() {
        return ohcondition11;
    }

    /**
     * @param ohcondition11 the ohcondition11 to set
     */
    public void setOhcondition11(String ohcondition11) {
        this.ohcondition11 = ohcondition11;
    }

    /**
     * @return the disabilitySubIds
     */
    public String getDisabilitySubIds() {
        return disabilitySubIds;
    }

    /**
     * @param disabilitySubIds the disabilitySubIds to set
     */
    public void setDisabilitySubIds(String disabilitySubIds) {
        this.disabilitySubIds = disabilitySubIds;
    }

    /**
     * @return the disabilitySubSubIds
     */
    public String getDisabilitySubSubIds() {
        return disabilitySubSubIds;
    }

    /**
     * @param disabilitySubSubIds the disabilitySubSubIds to set
     */
    public void setDisabilitySubSubIds(String disabilitySubSubIds) {
        this.disabilitySubSubIds = disabilitySubSubIds;
    }

    /**
     * @return the disabilitySubIds1
     */
    public String getDisabilitySubIds1() {
        return disabilitySubIds1;
    }

    /**
     * @param disabilitySubIds1 the disabilitySubIds1 to set
     */
    public void setDisabilitySubIds1(String disabilitySubIds1) {
        this.disabilitySubIds1 = disabilitySubIds1;
    }

    /**
     * @return the disabilitySubIds2
     */
    public String getDisabilitySubIds2() {
        return disabilitySubIds2;
    }

    /**
     * @param disabilitySubIds2 the disabilitySubIds2 to set
     */
    public void setDisabilitySubIds2(String disabilitySubIds2) {
        this.disabilitySubIds2 = disabilitySubIds2;
    }

    /**
     * @return the disabilitySubIds3
     */
    public String getDisabilitySubIds3() {
        return disabilitySubIds3;
    }

    /**
     * @param disabilitySubIds3 the disabilitySubIds3 to set
     */
    public void setDisabilitySubIds3(String disabilitySubIds3) {
        this.disabilitySubIds3 = disabilitySubIds3;
    }

    /**
     * @return the disabilitySubIds4
     */
    public String getDisabilitySubIds4() {
        return disabilitySubIds4;
    }

    /**
     * @param disabilitySubIds4 the disabilitySubIds4 to set
     */
    public void setDisabilitySubIds4(String disabilitySubIds4) {
        this.disabilitySubIds4 = disabilitySubIds4;
    }

    /**
     * @return the disabilitySubIds5
     */
    public String getDisabilitySubIds5() {
        return disabilitySubIds5;
    }

    /**
     * @param disabilitySubIds5 the disabilitySubIds5 to set
     */
    public void setDisabilitySubIds5(String disabilitySubIds5) {
        this.disabilitySubIds5 = disabilitySubIds5;
    }

    /**
     * @return the disabilitySubIds6
     */
    public String getDisabilitySubIds6() {
        return disabilitySubIds6;
    }

    /**
     * @param disabilitySubIds6 the disabilitySubIds6 to set
     */
    public void setDisabilitySubIds6(String disabilitySubIds6) {
        this.disabilitySubIds6 = disabilitySubIds6;
    }

    /**
     * @return the disabilitySubIds7
     */
    public String getDisabilitySubIds7() {
        return disabilitySubIds7;
    }

    /**
     * @param disabilitySubIds7 the disabilitySubIds7 to set
     */
    public void setDisabilitySubIds7(String disabilitySubIds7) {
        this.disabilitySubIds7 = disabilitySubIds7;
    }

    /**
     * @return the disabilitySubIds8
     */
    public String getDisabilitySubIds8() {
        return disabilitySubIds8;
    }

    /**
     * @param disabilitySubIds8 the disabilitySubIds8 to set
     */
    public void setDisabilitySubIds8(String disabilitySubIds8) {
        this.disabilitySubIds8 = disabilitySubIds8;
    }

    /**
     * @return the disabilitySubIds9
     */
    public String getDisabilitySubIds9() {
        return disabilitySubIds9;
    }

    /**
     * @param disabilitySubIds9 the disabilitySubIds9 to set
     */
    public void setDisabilitySubIds9(String disabilitySubIds9) {
        this.disabilitySubIds9 = disabilitySubIds9;
    }

    /**
     * @return the disabilitySubIds10
     */
    public String getDisabilitySubIds10() {
        return disabilitySubIds10;
    }

    /**
     * @param disabilitySubIds10 the disabilitySubIds10 to set
     */
    public void setDisabilitySubIds10(String disabilitySubIds10) {
        this.disabilitySubIds10 = disabilitySubIds10;
    }

    /**
     * @return the disabilitySubIds11
     */
    public String getDisabilitySubIds11() {
        return disabilitySubIds11;
    }

    /**
     * @param disabilitySubIds11 the disabilitySubIds11 to set
     */
    public void setDisabilitySubIds11(String disabilitySubIds11) {
        this.disabilitySubIds11 = disabilitySubIds11;
    }

    /**
     * @return the disabilitySubIds12
     */
    public String getDisabilitySubIds12() {
        return disabilitySubIds12;
    }

    /**
     * @param disabilitySubIds12 the disabilitySubIds12 to set
     */
    public void setDisabilitySubIds12(String disabilitySubIds12) {
        this.disabilitySubIds12 = disabilitySubIds12;
    }

    /**
     * @return the disabilitySubIds13
     */
    public String getDisabilitySubIds13() {
        return disabilitySubIds13;
    }

    /**
     * @param disabilitySubIds13 the disabilitySubIds13 to set
     */
    public void setDisabilitySubIds13(String disabilitySubIds13) {
        this.disabilitySubIds13 = disabilitySubIds13;
    }

    /**
     * @return the disabilitySubIds14
     */
    public String getDisabilitySubIds14() {
        return disabilitySubIds14;
    }

    /**
     * @param disabilitySubIds14 the disabilitySubIds14 to set
     */
    public void setDisabilitySubIds14(String disabilitySubIds14) {
        this.disabilitySubIds14 = disabilitySubIds14;
    }

    /**
     * @return the disabilitySubIds15
     */
    public String getDisabilitySubIds15() {
        return disabilitySubIds15;
    }

    /**
     * @param disabilitySubIds15 the disabilitySubIds15 to set
     */
    public void setDisabilitySubIds15(String disabilitySubIds15) {
        this.disabilitySubIds15 = disabilitySubIds15;
    }

    /**
     * @return the disabilitySubIds16
     */
    public String getDisabilitySubIds16() {
        return disabilitySubIds16;
    }

    /**
     * @param disabilitySubIds16 the disabilitySubIds16 to set
     */
    public void setDisabilitySubIds16(String disabilitySubIds16) {
        this.disabilitySubIds16 = disabilitySubIds16;
    }

    /**
     * @return the disabilitySubIds17
     */
    public String getDisabilitySubIds17() {
        return disabilitySubIds17;
    }

    /**
     * @param disabilitySubIds17 the disabilitySubIds17 to set
     */
    public void setDisabilitySubIds17(String disabilitySubIds17) {
        this.disabilitySubIds17 = disabilitySubIds17;
    }

    /**
     * @return the disabilitySubIds18
     */
    public String getDisabilitySubIds18() {
        return disabilitySubIds18;
    }

    /**
     * @param disabilitySubIds18 the disabilitySubIds18 to set
     */
    public void setDisabilitySubIds18(String disabilitySubIds18) {
        this.disabilitySubIds18 = disabilitySubIds18;
    }

    /**
     * @return the disabilitySubIds19
     */
    public String getDisabilitySubIds19() {
        return disabilitySubIds19;
    }

    /**
     * @param disabilitySubIds19 the disabilitySubIds19 to set
     */
    public void setDisabilitySubIds19(String disabilitySubIds19) {
        this.disabilitySubIds19 = disabilitySubIds19;
    }

    /**
     * @return the disabilitySubIds20
     */
    public String getDisabilitySubIds20() {
        return disabilitySubIds20;
    }

    /**
     * @param disabilitySubIds20 the disabilitySubIds20 to set
     */
    public void setDisabilitySubIds20(String disabilitySubIds20) {
        this.disabilitySubIds20 = disabilitySubIds20;
    }

    /**
     * @return the disabilitySubIds21
     */
    public String getDisabilitySubIds21() {
        return disabilitySubIds21;
    }

    /**
     * @param disabilitySubIds21 the disabilitySubIds21 to set
     */
    public void setDisabilitySubIds21(String disabilitySubIds21) {
        this.disabilitySubIds21 = disabilitySubIds21;
    }

    /**
     * @return the disabilitySubIds22
     */
    public String getDisabilitySubIds22() {
        return disabilitySubIds22;
    }

    /**
     * @param disabilitySubIds22 the disabilitySubIds22 to set
     */
    public void setDisabilitySubIds22(String disabilitySubIds22) {
        this.disabilitySubIds22 = disabilitySubIds22;
    }

    /**
     * @return the disabilitySubIds23
     */
    public String getDisabilitySubIds23() {
        return disabilitySubIds23;
    }

    /**
     * @param disabilitySubIds23 the disabilitySubIds23 to set
     */
    public void setDisabilitySubIds23(String disabilitySubIds23) {
        this.disabilitySubIds23 = disabilitySubIds23;
    }

    /**
     * @return the disabilitySubIds24
     */
    public String getDisabilitySubIds24() {
        return disabilitySubIds24;
    }

    /**
     * @param disabilitySubIds24 the disabilitySubIds24 to set
     */
    public void setDisabilitySubIds24(String disabilitySubIds24) {
        this.disabilitySubIds24 = disabilitySubIds24;
    }

    /**
     * @return the disabilitySubIds25
     */
    public String getDisabilitySubIds25() {
        return disabilitySubIds25;
    }

    /**
     * @param disabilitySubIds25 the disabilitySubIds25 to set
     */
    public void setDisabilitySubIds25(String disabilitySubIds25) {
        this.disabilitySubIds25 = disabilitySubIds25;
    }

    /**
     * @return the disabilitySubIds26
     */
    public String getDisabilitySubIds26() {
        return disabilitySubIds26;
    }

    /**
     * @param disabilitySubIds26 the disabilitySubIds26 to set
     */
    public void setDisabilitySubIds26(String disabilitySubIds26) {
        this.disabilitySubIds26 = disabilitySubIds26;
    }

    /**
     * @return the disabilitySubIds27
     */
    public String getDisabilitySubIds27() {
        return disabilitySubIds27;
    }

    /**
     * @param disabilitySubIds27 the disabilitySubIds27 to set
     */
    public void setDisabilitySubIds27(String disabilitySubIds27) {
        this.disabilitySubIds27 = disabilitySubIds27;
    }

    /**
     * @return the disabilitySubIds28
     */
    public String getDisabilitySubIds28() {
        return disabilitySubIds28;
    }

    /**
     * @param disabilitySubIds28 the disabilitySubIds28 to set
     */
    public void setDisabilitySubIds28(String disabilitySubIds28) {
        this.disabilitySubIds28 = disabilitySubIds28;
    }

    /**
     * @return the disabilitySubIds29
     */
    public String getDisabilitySubIds29() {
        return disabilitySubIds29;
    }

    /**
     * @param disabilitySubIds29 the disabilitySubIds29 to set
     */
    public void setDisabilitySubIds29(String disabilitySubIds29) {
        this.disabilitySubIds29 = disabilitySubIds29;
    }

    /**
     * @return the disabilitySubIds30
     */
    public String getDisabilitySubIds30() {
        return disabilitySubIds30;
    }

    /**
     * @param disabilitySubIds30 the disabilitySubIds30 to set
     */
    public void setDisabilitySubIds30(String disabilitySubIds30) {
        this.disabilitySubIds30 = disabilitySubIds30;
    }

    /**
     * @return the disabilitySubIds31
     */
    public String getDisabilitySubIds31() {
        return disabilitySubIds31;
    }

    /**
     * @param disabilitySubIds31 the disabilitySubIds31 to set
     */
    public void setDisabilitySubIds31(String disabilitySubIds31) {
        this.disabilitySubIds31 = disabilitySubIds31;
    }

    /**
     * @return the disabilitySubSubIds1
     */
    public String getDisabilitySubSubIds1() {
        return disabilitySubSubIds1;
    }

    /**
     * @param disabilitySubSubIds1 the disabilitySubSubIds1 to set
     */
    public void setDisabilitySubSubIds1(String disabilitySubSubIds1) {
        this.disabilitySubSubIds1 = disabilitySubSubIds1;
    }

    /**
     * @return the disabilitySubSubIds2
     */
    public String getDisabilitySubSubIds2() {
        return disabilitySubSubIds2;
    }

    /**
     * @param disabilitySubSubIds2 the disabilitySubSubIds2 to set
     */
    public void setDisabilitySubSubIds2(String disabilitySubSubIds2) {
        this.disabilitySubSubIds2 = disabilitySubSubIds2;
    }

    /**
     * @return the disabilitySubSubIds3
     */
    public String getDisabilitySubSubIds3() {
        return disabilitySubSubIds3;
    }

    /**
     * @param disabilitySubSubIds3 the disabilitySubSubIds3 to set
     */
    public void setDisabilitySubSubIds3(String disabilitySubSubIds3) {
        this.disabilitySubSubIds3 = disabilitySubSubIds3;
    }

    /**
     * @return the disabilitySubSubIds4
     */
    public String getDisabilitySubSubIds4() {
        return disabilitySubSubIds4;
    }

    /**
     * @param disabilitySubSubIds4 the disabilitySubSubIds4 to set
     */
    public void setDisabilitySubSubIds4(String disabilitySubSubIds4) {
        this.disabilitySubSubIds4 = disabilitySubSubIds4;
    }

    /**
     * @return the disabilitySubSubIds5
     */
    public String getDisabilitySubSubIds5() {
        return disabilitySubSubIds5;
    }

    /**
     * @param disabilitySubSubIds5 the disabilitySubSubIds5 to set
     */
    public void setDisabilitySubSubIds5(String disabilitySubSubIds5) {
        this.disabilitySubSubIds5 = disabilitySubSubIds5;
    }

    /**
     * @return the disabilitySubSubIds6
     */
    public String getDisabilitySubSubIds6() {
        return disabilitySubSubIds6;
    }

    /**
     * @param disabilitySubSubIds6 the disabilitySubSubIds6 to set
     */
    public void setDisabilitySubSubIds6(String disabilitySubSubIds6) {
        this.disabilitySubSubIds6 = disabilitySubSubIds6;
    }

    /**
     * @return the disabilitySubSubIds7
     */
    public String getDisabilitySubSubIds7() {
        return disabilitySubSubIds7;
    }

    /**
     * @param disabilitySubSubIds7 the disabilitySubSubIds7 to set
     */
    public void setDisabilitySubSubIds7(String disabilitySubSubIds7) {
        this.disabilitySubSubIds7 = disabilitySubSubIds7;
    }

    /**
     * @return the disabilitySubSubIds8
     */
    public String getDisabilitySubSubIds8() {
        return disabilitySubSubIds8;
    }

    /**
     * @param disabilitySubSubIds8 the disabilitySubSubIds8 to set
     */
    public void setDisabilitySubSubIds8(String disabilitySubSubIds8) {
        this.disabilitySubSubIds8 = disabilitySubSubIds8;
    }

    /**
     * @return the disabilitySubSubIds9
     */
    public String getDisabilitySubSubIds9() {
        return disabilitySubSubIds9;
    }

    /**
     * @param disabilitySubSubIds9 the disabilitySubSubIds9 to set
     */
    public void setDisabilitySubSubIds9(String disabilitySubSubIds9) {
        this.disabilitySubSubIds9 = disabilitySubSubIds9;
    }

    /**
     * @return the disabilitySubSubIds10
     */
    public String getDisabilitySubSubIds10() {
        return disabilitySubSubIds10;
    }

    /**
     * @param disabilitySubSubIds10 the disabilitySubSubIds10 to set
     */
    public void setDisabilitySubSubIds10(String disabilitySubSubIds10) {
        this.disabilitySubSubIds10 = disabilitySubSubIds10;
    }

    /**
     * @return the coordinate_lifting
     */
    public String getCoordinate_lifting() {
        return coordinate_lifting;
    }

    /**
     * @param coordinate_lifting the coordinate_lifting to set
     */
    public void setCoordinate_lifting(String coordinate_lifting) {
        this.coordinate_lifting = coordinate_lifting;
    }

    /**
     * @return the coordinate_touching
     */
    public String getCoordinate_touching() {
        return coordinate_touching;
    }

    /**
     * @param coordinate_touching the coordinate_touching to set
     */
    public void setCoordinate_touching(String coordinate_touching) {
        this.coordinate_touching = coordinate_touching;
    }

    /**
     * @return the coordinate_eating
     */
    public String getCoordinate_eating() {
        return coordinate_eating;
    }

    /**
     * @param coordinate_eating the coordinate_eating to set
     */
    public void setCoordinate_eating(String coordinate_eating) {
        this.coordinate_eating = coordinate_eating;
    }

    /**
     * @return the coordinate_combing
     */
    public String getCoordinate_combing() {
        return coordinate_combing;
    }

    /**
     * @param coordinate_combing the coordinate_combing to set
     */
    public void setCoordinate_combing(String coordinate_combing) {
        this.coordinate_combing = coordinate_combing;
    }

    /**
     * @return the coordinate_putting
     */
    public String getCoordinate_putting() {
        return coordinate_putting;
    }

    /**
     * @param coordinate_putting the coordinate_putting to set
     */
    public void setCoordinate_putting(String coordinate_putting) {
        this.coordinate_putting = coordinate_putting;
    }

    /**
     * @return the coordinate_ablution
     */
    public String getCoordinate_ablution() {
        return coordinate_ablution;
    }

    /**
     * @param coordinate_ablution the coordinate_ablution to set
     */
    public void setCoordinate_ablution(String coordinate_ablution) {
        this.coordinate_ablution = coordinate_ablution;
    }

    /**
     * @return the coordinate_buttoning
     */
    public String getCoordinate_buttoning() {
        return coordinate_buttoning;
    }

    /**
     * @param coordinate_buttoning the coordinate_buttoning to set
     */
    public void setCoordinate_buttoning(String coordinate_buttoning) {
        this.coordinate_buttoning = coordinate_buttoning;
    }

    /**
     * @return the coordinate_tie
     */
    public String getCoordinate_tie() {
        return coordinate_tie;
    }

    /**
     * @param coordinate_tie the coordinate_tie to set
     */
    public void setCoordinate_tie(String coordinate_tie) {
        this.coordinate_tie = coordinate_tie;
    }

    /**
     * @return the coordinate_writing
     */
    public String getCoordinate_writing() {
        return coordinate_writing;
    }

    /**
     * @param coordinate_writing the coordinate_writing to set
     */
    public void setCoordinate_writing(String coordinate_writing) {
        this.coordinate_writing = coordinate_writing;
    }

    /**
     * @return the Hand_stgrip_right
     */
    public String getHand_stgrip_right() {
        return Hand_stgrip_right;
    }

    /**
     * @param Hand_stgrip_right the Hand_stgrip_right to set
     */
    public void setHand_stgrip_right(String Hand_stgrip_right) {
        this.Hand_stgrip_right = Hand_stgrip_right;
    }

    /**
     * @return the Hand_stgrip_left
     */
    public String getHand_stgrip_left() {
        return Hand_stgrip_left;
    }

    /**
     * @param Hand_stgrip_left the Hand_stgrip_left to set
     */
    public void setHand_stgrip_left(String Hand_stgrip_left) {
        this.Hand_stgrip_left = Hand_stgrip_left;
    }

    /**
     * @return the Hand_stpinch_right
     */
    public String getHand_stpinch_right() {
        return Hand_stpinch_right;
    }

    /**
     * @param Hand_stpinch_right the Hand_stpinch_right to set
     */
    public void setHand_stpinch_right(String Hand_stpinch_right) {
        this.Hand_stpinch_right = Hand_stpinch_right;
    }

    /**
     * @return the Hand_stpinch_left
     */
    public String getHand_stpinch_left() {
        return Hand_stpinch_left;
    }

    /**
     * @param Hand_stpinch_left the Hand_stpinch_left to set
     */
    public void setHand_stpinch_left(String Hand_stpinch_left) {
        this.Hand_stpinch_left = Hand_stpinch_left;
    }

    /**
     * @return the coordinate
     */
    public String getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return the strength
     */
    public String getStrength() {
        return strength;
    }

    /**
     * @param strength the strength to set
     */
    public void setStrength(String strength) {
        this.strength = strength;
    }

    /**
     * @return the UpperExterimity_total
     */
    public double getUpperExterimity_total() {
        return UpperExterimity_total;
    }

    /**
     * @param UpperExterimity_total the UpperExterimity_total to set
     */
    public void setUpperExterimity_total(double UpperExterimity_total) {
        this.UpperExterimity_total = UpperExterimity_total;
    }

    public String getGlobal_Disablity() {
        return global_Disablity;
    }

    public void setGlobal_Disablity(String global_Disablity) {
        this.global_Disablity = global_Disablity;
    }

    public String getHearing_DB() {
        return hearing_DB;
    }

    public void setHearing_DB(String hearing_DB) {
        this.hearing_DB = hearing_DB;
    }

    public String getHearing_DB_In_Words() {
        return hearing_DB_In_Words;
    }

    public void setHearing_DB_In_Words(String hearing_DB_In_Words) {
        this.hearing_DB_In_Words = hearing_DB_In_Words;
    }

    public String getMentalRetardation_IQ() {
        return mentalRetardation_IQ;
    }

    public void setMentalRetardation_IQ(String mentalRetardation_IQ) {
        this.mentalRetardation_IQ = mentalRetardation_IQ;
    }

    public String getMentalRetardation_IQ_Inwords() {
        return mentalRetardation_IQ_Inwords;
    }

    public void setMentalRetardation_IQ_Inwords(String mentalRetardation_IQ_Inwords) {
        this.mentalRetardation_IQ_Inwords = mentalRetardation_IQ_Inwords;
    }

    /**
     * @return the global_Disablity_InWords
     */
    public String getGlobal_Disablity_InWords() {
        return global_Disablity_InWords;
    }

    /**
     * @param global_Disablity_InWords the global_Disablity_InWords to set
     */
    public void setGlobal_Disablity_InWords(String global_Disablity_InWords) {
        this.global_Disablity_InWords = global_Disablity_InWords;
    }

    /**
     * @return the congenital_BetterEye
     */
    public String getCongenital_BetterEye() {
        return congenital_BetterEye;
    }

    /**
     * @param congenital_BetterEye the congenital_BetterEye to set
     */
    public void setCongenital_BetterEye(String congenital_BetterEye) {
        this.congenital_BetterEye = congenital_BetterEye;
    }

    /**
     * @return the congenital_WorseEye
     */
    public String getCongenital_WorseEye() {
        return congenital_WorseEye;
    }

    /**
     * @param congenital_WorseEye the congenital_WorseEye to set
     */
    public void setCongenital_WorseEye(String congenital_WorseEye) {
        this.congenital_WorseEye = congenital_WorseEye;
    }

    /**
     * @return the hereditary_BetterEye
     */
    public String getHereditary_BetterEye() {
        return hereditary_BetterEye;
    }

    /**
     * @param hereditary_BetterEye the hereditary_BetterEye to set
     */
    public void setHereditary_BetterEye(String hereditary_BetterEye) {
        this.hereditary_BetterEye = hereditary_BetterEye;
    }

    /**
     * @return the hereditary_WorseEye
     */
    public String getHereditary_WorseEye() {
        return hereditary_WorseEye;
    }

    /**
     * @param hereditary_WorseEye the hereditary_WorseEye to set
     */
    public void setHereditary_WorseEye(String hereditary_WorseEye) {
        this.hereditary_WorseEye = hereditary_WorseEye;
    }

    /**
     * @return the birthinjury_BetterEye
     */
    public String getBirthinjury_BetterEye() {
        return birthinjury_BetterEye;
    }

    /**
     * @param birthinjury_BetterEye the birthinjury_BetterEye to set
     */
    public void setBirthinjury_BetterEye(String birthinjury_BetterEye) {
        this.birthinjury_BetterEye = birthinjury_BetterEye;
    }

    /**
     * @return the birthinjury_WorseEye
     */
    public String getBirthinjury_WorseEye() {
        return birthinjury_WorseEye;
    }

    /**
     * @param birthinjury_WorseEye the birthinjury_WorseEye to set
     */
    public void setBirthinjury_WorseEye(String birthinjury_WorseEye) {
        this.birthinjury_WorseEye = birthinjury_WorseEye;
    }

  
    /**
     * @return the malnutrition_BetterEye
     */
    public String getMalnutrition_BetterEye() {
        return malnutrition_BetterEye;
    }

    /**
     * @param malnutrition_BetterEye the malnutrition_BetterEye to set
     */
    public void setMalnutrition_BetterEye(String malnutrition_BetterEye) {
        this.malnutrition_BetterEye = malnutrition_BetterEye;
    }

    /**
     * @return the malnutrition_WorseEye
     */
    public String getMalnutrition_WorseEye() {
        return malnutrition_WorseEye;
    }

    /**
     * @param malnutrition_WorseEye the malnutrition_WorseEye to set
     */
    public void setMalnutrition_WorseEye(String malnutrition_WorseEye) {
        this.malnutrition_WorseEye = malnutrition_WorseEye;
    }

    /**
     * @return the accident_BetterEye
     */
    public String getAccident_BetterEye() {
        return accident_BetterEye;
    }

    /**
     * @param accident_BetterEye the accident_BetterEye to set
     */
    public void setAccident_BetterEye(String accident_BetterEye) {
        this.accident_BetterEye = accident_BetterEye;
    }

    /**
     * @return the accident_WorseEye
     */
    public String getAccident_WorseEye() {
        return accident_WorseEye;
    }

    /**
     * @param accident_WorseEye the accident_WorseEye to set
     */
    public void setAccident_WorseEye(String accident_WorseEye) {
        this.accident_WorseEye = accident_WorseEye;
    }

    /**
     * @return the diseaseandinfection_BetterEye
     */
    public String getDiseaseandinfection_BetterEye() {
        return diseaseandinfection_BetterEye;
    }

    /**
     * @param diseaseandinfection_BetterEye the diseaseandinfection_BetterEye to set
     */
    public void setDiseaseandinfection_BetterEye(String diseaseandinfection_BetterEye) {
        this.diseaseandinfection_BetterEye = diseaseandinfection_BetterEye;
    }

    /**
     * @return the diseaseandinfection_WorseEye
     */
    public String getDiseaseandinfection_WorseEye() {
        return diseaseandinfection_WorseEye;
    }

    /**
     * @param diseaseandinfection_WorseEye the diseaseandinfection_WorseEye to set
     */
    public void setDiseaseandinfection_WorseEye(String diseaseandinfection_WorseEye) {
        this.diseaseandinfection_WorseEye = diseaseandinfection_WorseEye;
    }

    /**
     * @return the highfever
     */
    public String getHighfever() {
        return highfever;
    }

    /**
     * @param highfever the highfever to set
     */
    public void setHighfever(String highfever) {
        this.highfever = highfever;
    }

    /**
     * @return the epilepsy
     */
    public String getEpilepsy() {
        return epilepsy;
    }

    /**
     * @param epilepsy the epilepsy to set
     */
    public void setEpilepsy(String epilepsy) {
        this.epilepsy = epilepsy;
    }

    /**
     * @return the birthasphyxia
     */
    public String getBirthasphyxia() {
        return birthasphyxia;
    }

    /**
     * @param birthasphyxia the birthasphyxia to set
     */
    public void setBirthasphyxia(String birthasphyxia) {
        this.birthasphyxia = birthasphyxia;
    }

    /**
     * @return the maritialstatus
     */
    public String getMaritialstatus() {
        return maritialstatus;
    }

    /**
     * @param maritialstatus the maritialstatus to set
     */
    public void setMaritialstatus(String maritialstatus) {
        this.maritialstatus = maritialstatus;
    }

    /**
     * @return the component_walkingonplane
     */
    public String getComponent_walkingonplane() {
        return component_walkingonplane;
    }

    /**
     * @param component_walkingonplane the component_walkingonplane to set
     */
    public void setComponent_walkingonplane(String component_walkingonplane) {
        this.component_walkingonplane = component_walkingonplane;
    }

    /**
     * @return the component_walkingonslope
     */
    public String getComponent_walkingonslope() {
        return component_walkingonslope;
    }

    /**
     * @param component_walkingonslope the component_walkingonslope to set
     */
    public void setComponent_walkingonslope(String component_walkingonslope) {
        this.component_walkingonslope = component_walkingonslope;
    }

    /**
     * @return the component_climbingstairs
     */
    public String getComponent_climbingstairs() {
        return component_climbingstairs;
    }

    /**
     * @param component_climbingstairs the component_climbingstairs to set
     */
    public void setComponent_climbingstairs(String component_climbingstairs) {
        this.component_climbingstairs = component_climbingstairs;
    }

    /**
     * @return the component_standingonbothlegs
     */
    public String getComponent_standingonbothlegs() {
        return component_standingonbothlegs;
    }

    /**
     * @param component_standingonbothlegs the component_standingonbothlegs to set
     */
    public void setComponent_standingonbothlegs(String component_standingonbothlegs) {
        this.component_standingonbothlegs = component_standingonbothlegs;
    }

    /**
     * @return the component_standingoneffectedleg
     */
    public String getComponent_standingoneffectedleg() {
        return component_standingoneffectedleg;
    }

    /**
     * @param component_standingoneffectedleg the component_standingoneffectedleg to set
     */
    public void setComponent_standingoneffectedleg(String component_standingoneffectedleg) {
        this.component_standingoneffectedleg = component_standingoneffectedleg;
    }

    /**
     * @return the component_squattingonfloor
     */
    public String getComponent_squattingonfloor() {
        return component_squattingonfloor;
    }

    /**
     * @param component_squattingonfloor the component_squattingonfloor to set
     */
    public void setComponent_squattingonfloor(String component_squattingonfloor) {
        this.component_squattingonfloor = component_squattingonfloor;
    }

    /**
     * @return the component_sittingcrossleg
     */
    public String getComponent_sittingcrossleg() {
        return component_sittingcrossleg;
    }

    /**
     * @param component_sittingcrossleg the component_sittingcrossleg to set
     */
    public void setComponent_sittingcrossleg(String component_sittingcrossleg) {
        this.component_sittingcrossleg = component_sittingcrossleg;
    }

    /**
     * @return the component_kneeling
     */
    public String getComponent_kneeling() {
        return component_kneeling;
    }

    /**
     * @param component_kneeling the component_kneeling to set
     */
    public void setComponent_kneeling(String component_kneeling) {
        this.component_kneeling = component_kneeling;
    }

    /**
     * @return the component_takingturns
     */
    public String getComponent_takingturns() {
        return component_takingturns;
    }

    /**
     * @param component_takingturns the component_takingturns to set
     */
    public void setComponent_takingturns(String component_takingturns) {
        this.component_takingturns = component_takingturns;
    }

    /**
     * @return the totalsubdisabilities
     */
    public String getTotalsubdisabilities() {
        return totalsubdisabilities;
    }

    /**
     * @param totalsubdisabilities the totalsubdisabilities to set
     */
    public void setTotalsubdisabilities(String totalsubdisabilities) {
        this.totalsubdisabilities = totalsubdisabilities;
    }

    /**
     * @return the totalsubsubdisabilities
     */
    public String getTotalsubsubdisabilities() {
        return totalsubsubdisabilities;
    }

    /**
     * @param totalsubsubdisabilities the totalsubsubdisabilities to set
     */
    public void setTotalsubsubdisabilities(String totalsubsubdisabilities) {
        this.totalsubsubdisabilities = totalsubsubdisabilities;
    }

    /**
     * @return the totalcauseofdisabilities
     */
    public String getTotalcauseofdisabilities() {
        return totalcauseofdisabilities;
    }

    /**
     * @param totalcauseofdisabilities the totalcauseofdisabilities to set
     */
    public void setTotalcauseofdisabilities(String totalcauseofdisabilities) {
        this.totalcauseofdisabilities = totalcauseofdisabilities;
    }

    /**
     * @return the totallocomotorarmlegeffectedlist
     */
    public String getTotallocomotorarmlegeffectedlist() {
        return totallocomotorarmlegeffectedlist;
    }

    /**
     * @param totallocomotorarmlegeffectedlist the totallocomotorarmlegeffectedlist to set
     */
    public void setTotallocomotorarmlegeffectedlist(String totallocomotorarmlegeffectedlist) {
        this.totallocomotorarmlegeffectedlist = totallocomotorarmlegeffectedlist;
    }

    /**
     * @return the physicalrequirementlist
     */
    public String getPhysicalrequirementlist() {
        return physicalrequirementlist;
    }

    /**
     * @param physicalrequirementlist the physicalrequirementlist to set
     */
    public void setPhysicalrequirementlist(String physicalrequirementlist) {
        this.physicalrequirementlist = physicalrequirementlist;
    }

    /**
     * @return the disabilitycondition
     */
    public String getDisabilitycondition() {
        return disabilitycondition;
    }

    /**
     * @param disabilitycondition the disabilitycondition to set
     */
    public void setDisabilitycondition(String disabilitycondition) {
        this.disabilitycondition = disabilitycondition;
    }

    /**
     * @return the physicalImpairmentName
     */
    public String getPhysicalImpairmentName() {
        return physicalImpairmentName;
    }

    /**
     * @param physicalImpairmentName the physicalImpairmentName to set
     */
    public void setPhysicalImpairmentName(String physicalImpairmentName) {
        this.physicalImpairmentName = physicalImpairmentName;
    }



    /**
     * @return the visualImpairmentName
     */
    public String getVisualImpairmentName() {
        return visualImpairmentName;
    }

    /**
     * @param visualImpairmentName the visualImpairmentName to set
     */
    public void setVisualImpairmentName(String visualImpairmentName) {
        this.visualImpairmentName = visualImpairmentName;
    }

    /**
     * @return the mentalRetardationName
     */
    public String getMentalRetardationName() {
        return mentalRetardationName;
    }

    /**
     * @param mentalRetardationName the mentalRetardationName to set
     */
    public void setMentalRetardationName(String mentalRetardationName) {
        this.mentalRetardationName = mentalRetardationName;
    }

    /**
     * @return the mentalIllnessName
     */
    public String getMentalIllnessName() {
        return mentalIllnessName;
    }

    /**
     * @param mentalIllnessName the mentalIllnessName to set
     */
    public void setMentalIllnessName(String mentalIllnessName) {
        this.mentalIllnessName = mentalIllnessName;
    }

    

    /**
     * @return the mentalRetardationCondition
     */
    public String getMentalRetardationCondition() {
        return mentalRetardationCondition;
    }

    /**
     * @param mentalRetardationCondition the mentalRetardationCondition to set
     */
    public void setMentalRetardationCondition(String mentalRetardationCondition) {
        this.mentalRetardationCondition = mentalRetardationCondition;
    }

    /**
     * @return the mentalIllnessCondition
     */
    public String getMentalIllnessCondition() {
        return mentalIllnessCondition;
    }

    /**
     * @param mentalIllnessCondition the mentalIllnessCondition to set
     */
    public void setMentalIllnessCondition(String mentalIllnessCondition) {
        this.mentalIllnessCondition = mentalIllnessCondition;
    }

    /**
     * @return the physicalImpairmentPercentage
     */
    public int getPhysicalImpairmentPercentage() {
        return physicalImpairmentPercentage;
    }

    /**
     * @param physicalImpairmentPercentage the physicalImpairmentPercentage to set
     */
    public void setPhysicalImpairmentPercentage(int physicalImpairmentPercentage) {
        this.physicalImpairmentPercentage = physicalImpairmentPercentage;
    }

   

    /**
     * @return the visualImpairmentPercentage
     */
    public int getVisualImpairmentPercentage() {
        return visualImpairmentPercentage;
    }

    /**
     * @param visualImpairmentPercentage the visualImpairmentPercentage to set
     */
    public void setVisualImpairmentPercentage(int visualImpairmentPercentage) {
        this.visualImpairmentPercentage = visualImpairmentPercentage;
    }

    /**
     * @return the mentalRetardationPercentage
     */
    public int getMentalRetardationPercentage() {
        return mentalRetardationPercentage;
    }

    /**
     * @param mentalRetardationPercentage the mentalRetardationPercentage to set
     */
    public void setMentalRetardationPercentage(int mentalRetardationPercentage) {
        this.mentalRetardationPercentage = mentalRetardationPercentage;
    }

    /**
     * @return the mentalIllnessPercentage
     */
    public int getMentalIllnessPercentage() {
        return mentalIllnessPercentage;
    }

    /**
     * @param mentalIllnessPercentage the mentalIllnessPercentage to set
     */
    public void setMentalIllnessPercentage(int mentalIllnessPercentage) {
        this.mentalIllnessPercentage = mentalIllnessPercentage;
    }

    /**
     * @return the physicalImpairmentPhysicalRequirementList
     */
    public String getPhysicalImpairmentPhysicalRequirementList() {
        return physicalImpairmentPhysicalRequirementList;
    }

    /**
     * @param physicalImpairmentPhysicalRequirementList the physicalImpairmentPhysicalRequirementList to set
     */
    public void setPhysicalImpairmentPhysicalRequirementList(String physicalImpairmentPhysicalRequirementList) {
        this.physicalImpairmentPhysicalRequirementList = physicalImpairmentPhysicalRequirementList;
    }

   
    /**
     * @return the visualImpairmentPhysicalRequirementList
     */
    public String getVisualImpairmentPhysicalRequirementList() {
        return visualImpairmentPhysicalRequirementList;
    }

    /**
     * @param visualImpairmentPhysicalRequirementList the visualImpairmentPhysicalRequirementList to set
     */
    public void setVisualImpairmentPhysicalRequirementList(String visualImpairmentPhysicalRequirementList) {
        this.visualImpairmentPhysicalRequirementList = visualImpairmentPhysicalRequirementList;
    }

    /**
     * @return the mentalRetardationPhysicalRequirementList
     */
    public String getMentalRetardationPhysicalRequirementList() {
        return mentalRetardationPhysicalRequirementList;
    }

    /**
     * @param mentalRetardationPhysicalRequirementList the mentalRetardationPhysicalRequirementList to set
     */
    public void setMentalRetardationPhysicalRequirementList(String mentalRetardationPhysicalRequirementList) {
        this.mentalRetardationPhysicalRequirementList = mentalRetardationPhysicalRequirementList;
    }

    /**
     * @return the physicalImpairmentTotalsubdisabilities
     */
    public String getPhysicalImpairmentTotalsubdisabilities() {
        return physicalImpairmentTotalsubdisabilities;
    }

    /**
     * @param physicalImpairmentTotalsubdisabilities the physicalImpairmentTotalsubdisabilities to set
     */
    public void setPhysicalImpairmentTotalsubdisabilities(String physicalImpairmentTotalsubdisabilities) {
        this.physicalImpairmentTotalsubdisabilities = physicalImpairmentTotalsubdisabilities;
    }

    /**
     * @return the visualImpairmentTotalsubdisabilities
     */
    public String getVisualImpairmentTotalsubdisabilities() {
        return visualImpairmentTotalsubdisabilities;
    }

    /**
     * @param visualImpairmentTotalsubdisabilities the visualImpairmentTotalsubdisabilities to set
     */
    public void setVisualImpairmentTotalsubdisabilities(String visualImpairmentTotalsubdisabilities) {
        this.visualImpairmentTotalsubdisabilities = visualImpairmentTotalsubdisabilities;
    }

    /**
     * @return the hearingImpairmentPhysicalRequirementList
     */
    public String getHearingImpairmentPhysicalRequirementList() {
        return hearingImpairmentPhysicalRequirementList;
    }

    /**
     * @param hearingImpairmentPhysicalRequirementList the hearingImpairmentPhysicalRequirementList to set
     */
    public void setHearingImpairmentPhysicalRequirementList(String hearingImpairmentPhysicalRequirementList) {
        this.hearingImpairmentPhysicalRequirementList = hearingImpairmentPhysicalRequirementList;
    }

    /**
     * @return the hearingImpairmentName
     */
    public String getHearingImpairmentName() {
        return hearingImpairmentName;
    }

    /**
     * @param hearingImpairmentName the hearingImpairmentName to set
     */
    public void setHearingImpairmentName(String hearingImpairmentName) {
        this.hearingImpairmentName = hearingImpairmentName;
    }

    /**
     * @return the hearingImpairmentCondition
     */
    public String getHearingImpairmentCondition() {
        return hearingImpairmentCondition;
    }

    /**
     * @param hearingImpairmentCondition the hearingImpairmentCondition to set
     */
    public void setHearingImpairmentCondition(String hearingImpairmentCondition) {
        this.hearingImpairmentCondition = hearingImpairmentCondition;
    }

    /**
     * @return the hearingImpairmentPercentage
     */
    public int getHearingImpairmentPercentage() {
        return hearingImpairmentPercentage;
    }

    /**
     * @param hearingImpairmentPercentage the hearingImpairmentPercentage to set
     */
    public void setHearingImpairmentPercentage(int hearingImpairmentPercentage) {
        this.hearingImpairmentPercentage = hearingImpairmentPercentage;
    }

    /**
     * @return the multipleDisabilityPhysicalRequirementList
     */
    public String getMultipleDisabilityPhysicalRequirementList() {
        return multipleDisabilityPhysicalRequirementList;
    }

    /**
     * @param multipleDisabilityPhysicalRequirementList the multipleDisabilityPhysicalRequirementList to set
     */
    public void setMultipleDisabilityPhysicalRequirementList(String multipleDisabilityPhysicalRequirementList) {
        this.multipleDisabilityPhysicalRequirementList = multipleDisabilityPhysicalRequirementList;
    }

    /**
     * @return the genderInTelugu
     */
    public String getGenderInTelugu() {
        return genderInTelugu;
    }

    /**
     * @param genderInTelugu the genderInTelugu to set
     */
    public void setGenderInTelugu(String genderInTelugu) {
        this.genderInTelugu = genderInTelugu;
    }

    /**
     * @return the disabilityTypeInIdCard
     */
    public String getDisabilityTypeInIdCard() {
        return disabilityTypeInIdCard;
    }

    /**
     * @param disabilityTypeInIdCard the disabilityTypeInIdCard to set
     */
    public void setDisabilityTypeInIdCard(String disabilityTypeInIdCard) {
        this.disabilityTypeInIdCard = disabilityTypeInIdCard;
    }

    /**
     * @return the disabilityTypeInTelugu
     */
    public String getDisabilityTypeInTelugu() {
        return disabilityTypeInTelugu;
    }

    /**
     * @param disabilityTypeInTelugu the disabilityTypeInTelugu to set
     */
    public void setDisabilityTypeInTelugu(String disabilityTypeInTelugu) {
        this.disabilityTypeInTelugu = disabilityTypeInTelugu;
    }

    /**
     * @return the disabilityId
     */
    public String getDisabilityId() {
        return disabilityId;
    }

    /**
     * @param disabilityId the disabilityId to set
     */
    public void setDisabilityId(String disabilityId) {
        this.disabilityId = disabilityId;
    }

   

    /**
     * @return the railwaycertificate
     */
    public boolean isRailwaycertificate() {
        return railwaycertificate;
    }

    /**
     * @param railwaycertificate the railwaycertificate to set
     */
    public void setRailwaycertificate(boolean railwaycertificate) {
        this.railwaycertificate = railwaycertificate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

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

   
    
}




