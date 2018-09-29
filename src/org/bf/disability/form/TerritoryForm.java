/*
 * TerritoryForm.java
 *
 * Created on June 18, 2008, 3:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.form;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionFormBean;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.dto.TerritoryDTO;

/**
 * This class contains the fields, used is terrtory selection module
 * @version 1.0
 */
public class TerritoryForm extends ActionForm {

    private String district_id;
    private String assembly_id;
    private String physiotheraphy;
    private String occupationaltherapy;
    private String occupationaltheraphy;

    private String aadharcardNumber;

    public String getAadharcardNumber() {
        return aadharcardNumber;
    }

    public void setAadharcardNumber(String aadharcardNumber) {
        this.aadharcardNumber = aadharcardNumber;
    }
    public String getOccupationaltheraphy() {
        return occupationaltheraphy;
    }

    public void setOccupationaltheraphy(String occupationaltheraphy) {
        this.occupationaltheraphy = occupationaltheraphy;
    }
    private String selectwheelchair = null;
    private String selecttricycle = null;
    private String selectwalkingstick = null;
    private String selectcrutches = null;
    private String crutchestype = null;
    private String selectwalkingframe;
    private String aeroplanesplint;
    private String figure8splint;
    private String forearmsplint;
    private String handsplint;
    private String shoulderprosthesis;
    private String aboveelbowprosthesis;
    private String elbowdisarticulationprosthesis;
    private String belowelbowprosthesis;
    private String wristdisarticulationprosthesis;
    private String handprosthesis;
    private String cosmeticfingerprosthesis;
    private String hkafo;
    private String kafo;
    private String afo;
    private String kneeorthosis;
    private String dbsplint;
    private String modifiedshoe;
    private String serialcasting;
    private String hipprothesis;
    private String rationCardNo;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }



    public String getRationCardNo() {
        return rationCardNo;
    }

    public void setRationCardNo(String rationCardNo) {
        this.rationCardNo = rationCardNo;
    }

    public String getAboveelbowprosthesis() {
        return aboveelbowprosthesis;
    }

    public void setAboveelbowprosthesis(String aboveelbowprosthesis) {
        this.aboveelbowprosthesis = aboveelbowprosthesis;
    }

    public String getAbovekneeprothesis() {
        return abovekneeprothesis;
    }

    public void setAbovekneeprothesis(String abovekneeprothesis) {
        this.abovekneeprothesis = abovekneeprothesis;
    }

    public String getAeroplanesplint() {
        return aeroplanesplint;
    }

    public void setAeroplanesplint(String aeroplanesplint) {
        this.aeroplanesplint = aeroplanesplint;
    }

    public String getAfo() {
        return afo;
    }

    public void setAfo(String afo) {
        this.afo = afo;
    }

    public String getAnyotherinlocomotor() {
        return anyotherinlocomotor;
    }

    public void setAnyotherinlocomotor(String anyotherinlocomotor) {
        this.anyotherinlocomotor = anyotherinlocomotor;
    }

    public String getBathing() {
        return bathing;
    }

    public void setBathing(String bathing) {
        this.bathing = bathing;
    }

    public String getBedtransfer() {
        return bedtransfer;
    }

    public void setBedtransfer(String bedtransfer) {
        this.bedtransfer = bedtransfer;
    }

    public String getBelowelbowprosthesis() {
        return belowelbowprosthesis;
    }

    public void setBelowelbowprosthesis(String belowelbowprosthesis) {
        this.belowelbowprosthesis = belowelbowprosthesis;
    }

    public String getBelowkneeprothesis() {
        return belowkneeprothesis;
    }

    public void setBelowkneeprothesis(String belowkneeprothesis) {
        this.belowkneeprothesis = belowkneeprothesis;
    }

    public String getBrushing() {
        return brushing;
    }

    public void setBrushing(String brushing) {
        this.brushing = brushing;
    }

    public String getCervicalcollar() {
        return cervicalcollar;
    }

    public void setCervicalcollar(String cervicalcollar) {
        this.cervicalcollar = cervicalcollar;
    }

    public String getCombing() {
        return combing;
    }

    public void setCombing(String combing) {
        this.combing = combing;
    }

    public String getCosmeticfingerprosthesis() {
        return cosmeticfingerprosthesis;
    }

    public void setCosmeticfingerprosthesis(String cosmeticfingerprosthesis) {
        this.cosmeticfingerprosthesis = cosmeticfingerprosthesis;
    }

    public String getCrutchestype() {
        return crutchestype;
    }

    public void setCrutchestype(String crutchestype) {
        this.crutchestype = crutchestype;
    }

    public String getDbsplint() {
        return dbsplint;
    }

    public void setDbsplint(String dbsplint) {
        this.dbsplint = dbsplint;
    }

    public String getDressing() {
        return dressing;
    }

    public void setDressing(String dressing) {
        this.dressing = dressing;
    }

    public String getDrivingcycling() {
        return drivingcycling;
    }

    public void setDrivingcycling(String drivingcycling) {
        this.drivingcycling = drivingcycling;
    }

    public String getElbowdisarticulationprosthesis() {
        return elbowdisarticulationprosthesis;
    }

    public void setElbowdisarticulationprosthesis(String elbowdisarticulationprosthesis) {
        this.elbowdisarticulationprosthesis = elbowdisarticulationprosthesis;
    }

    public String getFeeding() {
        return feeding;
    }

    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    public String getFigure8splint() {
        return figure8splint;
    }

    public void setFigure8splint(String figure8splint) {
        this.figure8splint = figure8splint;
    }

    public String getForearmsplint() {
        return forearmsplint;
    }

    public void setForearmsplint(String forearmsplint) {
        this.forearmsplint = forearmsplint;
    }

    public String getHandprosthesis() {
        return handprosthesis;
    }

    public void setHandprosthesis(String handprosthesis) {
        this.handprosthesis = handprosthesis;
    }

    public String getHandsplint() {
        return handsplint;
    }

    public void setHandsplint(String handsplint) {
        this.handsplint = handsplint;
    }

    public String getHipprothesis() {
        return hipprothesis;
    }

    public void setHipprothesis(String hipprothesis) {
        this.hipprothesis = hipprothesis;
    }

    public String getHkafo() {
        return hkafo;
    }

    public void setHkafo(String hkafo) {
        this.hkafo = hkafo;
    }

    public String getKafo() {
        return kafo;
    }

    public void setKafo(String kafo) {
        this.kafo = kafo;
    }

    public String getKneedisarticulation() {
        return kneedisarticulation;
    }

    public void setKneedisarticulation(String kneedisarticulation) {
        this.kneedisarticulation = kneedisarticulation;
    }

    public String getKneeorthosis() {
        return kneeorthosis;
    }

    public void setKneeorthosis(String kneeorthosis) {
        this.kneeorthosis = kneeorthosis;
    }

    public String getLsbrace() {
        return lsbrace;
    }

    public void setLsbrace(String lsbrace) {
        this.lsbrace = lsbrace;
    }

    public String getModifiedshoe() {
        return modifiedshoe;
    }

    public void setModifiedshoe(String modifiedshoe) {
        this.modifiedshoe = modifiedshoe;
    }

    public String getOccupationaltherapy() {
        return occupationaltherapy;
    }

    public void setOccupationaltherapy(String occupationaltherapy) {
        this.occupationaltherapy = occupationaltherapy;
    }

    public String getPartialfoodprothesis() {
        return partialfoodprothesis;
    }

    public void setPartialfoodprothesis(String partialfoodprothesis) {
        this.partialfoodprothesis = partialfoodprothesis;
    }

    public String getPhysiotheraphy() {
        return physiotheraphy;
    }

    public void setPhysiotheraphy(String physiotheraphy) {
        this.physiotheraphy = physiotheraphy;
    }

    public String getSelectcrutches() {
        return selectcrutches;
    }

    public void setSelectcrutches(String selectcrutches) {
        this.selectcrutches = selectcrutches;
    }

    public String getSelecttricycle() {
        return selecttricycle;
    }

    public void setSelecttricycle(String selecttricycle) {
        this.selecttricycle = selecttricycle;
    }

    public String getSelectwalkingframe() {
        return selectwalkingframe;
    }

    public void setSelectwalkingframe(String selectwalkingframe) {
        this.selectwalkingframe = selectwalkingframe;
    }

    public String getSelectwalkingstick() {
        return selectwalkingstick;
    }

    public void setSelectwalkingstick(String selectwalkingstick) {
        this.selectwalkingstick = selectwalkingstick;
    }

    public String getSelectwheelchair() {
        return selectwheelchair;
    }

    public void setSelectwheelchair(String selectwheelchair) {
        this.selectwheelchair = selectwheelchair;
    }

    public String getSerialcasting() {
        return serialcasting;
    }

    public void setSerialcasting(String serialcasting) {
        this.serialcasting = serialcasting;
    }

    public String getShoulderprosthesis() {
        return shoulderprosthesis;
    }

    public void setShoulderprosthesis(String shoulderprosthesis) {
        this.shoulderprosthesis = shoulderprosthesis;
    }

    public String getSymeprothesis() {
        return symeprothesis;
    }

    public void setSymeprothesis(String symeprothesis) {
        this.symeprothesis = symeprothesis;
    }

    public String getTlsobrace() {
        return tlsobrace;
    }

    public void setTlsobrace(String tlsobrace) {
        this.tlsobrace = tlsobrace;
    }

    public String getWristdisarticulationprosthesis() {
        return wristdisarticulationprosthesis;
    }

    public void setWristdisarticulationprosthesis(String wristdisarticulationprosthesis) {
        this.wristdisarticulationprosthesis = wristdisarticulationprosthesis;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }
    private String abovekneeprothesis;
    private String kneedisarticulation;
    private String belowkneeprothesis;
    private String symeprothesis;
    private String partialfoodprothesis;
    private String cervicalcollar;
    private String lsbrace;
    private String tlsobrace;
    private String feeding;
    private String bathing;
    private String brushing;
    private String combing;
    private String dressing;
    private String writing;
    private String drivingcycling;
    private String bedtransfer;
    private String anyotherinlocomotor = null;
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
    private ArrayList districtlist = new ArrayList();
    private ArrayList districtList = new ArrayList();
    private ArrayList assemblylist = new ArrayList();
    private ArrayList mandallist = new ArrayList();
    private ArrayList panchayatlist = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    private ArrayList habitationlist = new ArrayList();
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
    private String typeofdisability;
    // Analysis report
    private String reportcategory;
    private String reportSubcategory;
    private String disabilityreason;
    private String disabilityduration;
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
    private String totalforvisual = "0";
    private String belowfourtyformentalillness = "0";
    private String fourtytosixtyformentalillness = "0";
    private String sixtyonetoeightyformentalillness = "0";
    private String aboveeightyoneformentalillness = "0";
    private String totalformentalillness = "0";
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
    private boolean brFlag;
    private String validity;
    private String genderInitial1;
    private String genderInitial2;
    private String percentageInWord;
    private int disabilityPercentage;
    private String multipleRadio;
    private String disabilityId;
    private boolean railwaycertificate;
    private String teluguDisabilityName;
    private String comment = null;
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
    private String differenceFlag;
    private String user_name;
    private String sadaremId;
    private String parentsName;

    public ArrayList getDistrictList() {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList) {
        this.districtList = districtList;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getSadaremId() {
        return sadaremId;
    }

    public void setSadaremId(String sadaremId) {
        this.sadaremId = sadaremId;
    }

    public TerritoryForm() {
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

    public void reset(ActionMapping mapping, HttpServletRequest request) {
//        this.districtlist=null ;
//        this.mandallist=null;
//       this.panchayatlist=null;
//       this.villagelist=null;
//       this.habitationlist=null;
//       
//    this. district_id=null;
//    this. mandal_id=null;
//    this. village_id=null;
//    this. panchayat_id=null;
//    this. panchayat_name=null;
//    this. village_name=null;
//    this. district_name=null;
//    this. mandal_name=null;
//    this. habitation_id=null;
//    this. habitation_name=null;
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

    public String getRejectedpersonscount() {
        return rejectedpersonscount;
    }

    public void setRejectedpersonscount(String rejectedpersonscount) {
        this.rejectedpersonscount = rejectedpersonscount;
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

    public String getSurgeryforrejected() {
        return surgeryforrejected;
    }

    public void setSurgeryforrejected(String surgeryforrejected) {
        this.surgeryforrejected = surgeryforrejected;
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
     * @return the differenceFlag
     */
    public String getDifferenceFlag() {
        return differenceFlag;
    }

    /**
     * @param differenceFlag the differenceFlag to set
     */
    public void setDifferenceFlag(String differenceFlag) {
        this.differenceFlag = differenceFlag;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
     * @return the assembly_id
     */
    public String getAssembly_id() {
        return assembly_id;
    }

    /**
     * @param assembly_id the assembly_id to set
     */
    public void setAssembly_id(String assembly_id) {
        this.assembly_id = assembly_id;
    }

    /**
     * @return the assemblylist
     */
    public ArrayList getAssemblylist() {
        return assemblylist;
    }

    /**
     * @param assemblylist the assemblylist to set
     */
    public void setAssemblylist(ArrayList assemblylist) {
        this.assemblylist = assemblylist;
    }

    /**
     * @return the reportcategory
     */
    public String getReportcategory() {
        return reportcategory;
    }

    /**
     * @param reportcategory the reportcategory to set
     */
    public void setReportcategory(String reportcategory) {
        this.reportcategory = reportcategory;
    }

    /**
     * @return the reportSubcategory
     */
    public String getReportSubcategory() {
        return reportSubcategory;
    }

    /**
     * @param reportSubcategory the reportSubcategory to set
     */
    public void setReportSubcategory(String reportSubcategory) {
        this.reportSubcategory = reportSubcategory;
    }
}
