/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dto;

import java.util.ArrayList;

/**
 *
 * @author 509865
 */
public class FunctionalNeedReportDTO {

    private String fromdate;
    private String todate;
    private String district_id;
    private String mandal_id;
    private String village_id;
    private String habitation_id;
    private int typeofdisability;
    private int reportcategory;
    private int reportSubcategory;
    private String districtName;
    private String mandalName;
    private String villageName;
    private String habitationName;
    private String village_name;
    private String camp_id;
    private String camp_name;
    private ArrayList campList;
    private String categoryID;//3.1 report -- added status(assessed/reassessed)
    private String urban_id;
    private String urban_name;
    private ArrayList urbanlist = new ArrayList();
    private boolean report = false;
    private String dataReceivedDate = null;

    public String getDataReceivedDate() {
        return dataReceivedDate;
    }

    public void setDataReceivedDate(String dataReceivedDate) {
        this.dataReceivedDate = dataReceivedDate;
    }

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

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public ArrayList getCampList() {
        return campList;
    }

    public void setCampList(ArrayList campList) {
        this.campList = campList;
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

    public String getMandal_name() {
        return mandal_name;
    }

    public void setMandal_name(String mandal_name) {
        this.mandal_name = mandal_name;
    }

    public String getVillage_name() {
        return village_name;
    }

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }
    private String mandal_name;
    // Medical Intervention OH
    private int physiotherapyBelowThreeYears;
    private int physiotherapyAboveThreeYears;
    private int occupationalTherapyBelowThreeYears;
    private int occupationalTherapyAboveThreeYears;
    private int surgeryOH;
    private int total;
    private String qualification;

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    // Assistive devices OH
    //  Prosthesis OH
    private int shoulder;
    private int aboveElbow;
    private int elbowDisarticulation;
    private int belowElbow;
    private int wristDisarticulation;
    private int hand;
    private int cosmeticFinger;
    private int hipDisarticulation;
    private int aboveKnee;
    private int kneeDisarticulation;
    private int belowKnee;
    private int symes;
    private int partialFoot;
    // Orthosis OH
    private int aeroplaneSplint;
    private int figureEightSplint;
    private int forearmSplint;
    private int handSplint;
    private int hkafoh;
    private int kafo;
    private int afo;
    private int kneeOrthosis;
    private int dbSplint;
    private int modifiedShoe;
    private int serialCastingCTEV;
    private int cervicalCollar;
    private int lsBrace;
    private int tlsoBraceForScoliosisKyphosis;
    // Mobility Aids OH
    private int wheelChairSmall;
    private int wheelChairLarge;
    private int tricycleSmall;
    private int tricycleLarge;
    private int walkingFrameWalkerSmall;
    private int walkingFrameWalkerLarge;
    // Walking Aids OH
    private int walkingStickSmall;
    private int walkingStickLarge;
    private int axillarySmall;
    private int axillaryMedium;
    private int axillaryLarge;
    private int axillaryExtraLarge;
    private int elbowSmall;
    private int elbowMedium;
    private int elbowLarge;
    private int elbowExtraLarge;
    private int gutterSmall;
    private int gutterMedium;
    private int gutterLarge;
    private int gutterExtraLarge;
    private int tripodSmall;
    private int tripodMedium;
    private int tripodLarge;
    private int tripodExtraLarge;
    // ADL Equipments OH
    private int feeding;
    private int toiletingBathing;
    private int brushing;
    private int combing;
    private int dressing;
    private int writing;
    private int drivingCycling;
    private int bedTransfer;
    // OH Otehr Needs
    private int otherNeeds;
    // Medical Intervention VI
    private int sugeryVI;
    // Assistive & Augmentative Devices VI
    private int whiteCaneBlindStick;
    private int brailleEquipments;
    private int arithmeticFramesAbacus;
    private int lowVisionAidsSpectaclesMagnifiers;
    private int speechSynthesizer;
    private int brailleShortHandMachinesWriters;
    private int talkingWatchCalculator;
    private int viADLEquipment;
    // VI Otehr Needs
    private int otherNeedsVI;
    // Medical Intervention HI
    private int speechTherapyBelowThreeYearsHI;
    private int speechTherapyAboveThreeYearsHI;
    private int languageDevelopment;
    private int surgeryHI;
    private int cochlearImplantation;
    // Assistive & Augmentative Devices HI
    private int lowIntensitySCord;
    private int lowIntensityVCord;
    private int moderateIntensitySCord;
    private int moderateIntensityVCord;
    private int highIntensitySCord;
    private int highIntensityVCord;
    private int implantableHearingAid;
    // HI Otehr Needs
    private int otherNeedsHI;
    // Medical Intervention MR
    private int speechTherapyBelowThreeYearsMR;
    private int speechTherapyAboveThreeYearsMR;
    private int behaviourModificationPsychotherapyBelow;
    private int behaviourModificationPsychotherapyAbove;
    private int sensoryIntegrationCognitiveDevelopment;
    private int cognitiveBehaviourTherapy;
    private int parentFamilyIntervention;
    private int physiotherapyMR;
    private int occupationalTherapyMR;
    // Assistive & Augmentative Devices MR
    private int learningMaterials;
    private int specialSoftware;
    private int toys;
    // MR Otehr Needs
    private int otherNeedsMR;
    // Medical Intervention MI
    private int psychotherapyBehaviourBelowThreeYearsMI;
    private int psychotherapyBehaviourAboveThreeYearsMI;
    private int surgeryMI;
    private int admissionPsychiatric;
    // MI Otehr Needs
    private int otherNeedsMI;
    // General Needs
    // Education Services
    private int earlyEducationService;
    private int homeBased;
    private int specialEducation;
    private int inclusiveEducation;
    // Vocational Training
    private int employmentPublicPvtSector;
    private int selfEmployment;
    // Counseling & Guidance
    private int individual;
    private int family;
    //  Otehr General Needs
    private int otherGeneralNeeds;
    // Otehr General Needs MR
    private int legalGurdian;
    // Otehr General Needs MI
    private int managerToTakeCareProperties;
    // Personal Details
    private String pensionid;
    private String name;
    private String firstName;
    private String lastName;
    private String middleName;
    private String ralationName;
    private String age;
    private String gender;
    private String personcode;
    private String rationcard;
    private String houseno;
    private String pincode;
    private String disabilityType;
    private String subtypeofDisability;
    private String partsInvolved;
    private String totalpercent;
    private String caste;
    private String occupation;
    private String surgeryType;
    //    Who Are Not Come to SADAREM Camp
    private String phase;
    private int notComrtoCamp;
    private int onlyPartA;
    // Education Wise Report
    private int illiterate;
    private int belowTenth;
    private int tenth;
    private int intermediate;
    private int diplomaOrITI;
    private int graduate;
    private int postGraduate;
    private int notEntered;

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
     * @return the physiotherapyBelowThreeYears
     */
    public int getPhysiotherapyBelowThreeYears() {
        return physiotherapyBelowThreeYears;
    }

    /**
     * @param physiotherapyBelowThreeYears the physiotherapyBelowThreeYears to set
     */
    public void setPhysiotherapyBelowThreeYears(int physiotherapyBelowThreeYears) {
        this.physiotherapyBelowThreeYears = physiotherapyBelowThreeYears;
    }

    /**
     * @return the physiotherapyAboveThreeYears
     */
    public int getPhysiotherapyAboveThreeYears() {
        return physiotherapyAboveThreeYears;
    }

    /**
     * @param physiotherapyAboveThreeYears the physiotherapyAboveThreeYears to set
     */
    public void setPhysiotherapyAboveThreeYears(int physiotherapyAboveThreeYears) {
        this.physiotherapyAboveThreeYears = physiotherapyAboveThreeYears;
    }

    /**
     * @return the occupationalTherapyBelowThreeYears
     */
    public int getOccupationalTherapyBelowThreeYears() {
        return occupationalTherapyBelowThreeYears;
    }

    /**
     * @param occupationalTherapyBelowThreeYears the occupationalTherapyBelowThreeYears to set
     */
    public void setOccupationalTherapyBelowThreeYears(int occupationalTherapyBelowThreeYears) {
        this.occupationalTherapyBelowThreeYears = occupationalTherapyBelowThreeYears;
    }

    /**
     * @return the occupationalTherapyAboveThreeYears
     */
    public int getOccupationalTherapyAboveThreeYears() {
        return occupationalTherapyAboveThreeYears;
    }

    /**
     * @param occupationalTherapyAboveThreeYears the occupationalTherapyAboveThreeYears to set
     */
    public void setOccupationalTherapyAboveThreeYears(int occupationalTherapyAboveThreeYears) {
        this.occupationalTherapyAboveThreeYears = occupationalTherapyAboveThreeYears;
    }

    /**
     * @return the surgeryOH
     */
    public int getSurgeryOH() {
        return surgeryOH;
    }

    /**
     * @param surgeryOH the surgeryOH to set
     */
    public void setSurgeryOH(int surgeryOH) {
        this.surgeryOH = surgeryOH;
    }

    /**
     * @return the shoulder
     */
    public int getShoulder() {
        return shoulder;
    }

    /**
     * @param shoulder the shoulder to set
     */
    public void setShoulder(int shoulder) {
        this.shoulder = shoulder;
    }

    /**
     * @return the aboveElbow
     */
    public int getAboveElbow() {
        return aboveElbow;
    }

    /**
     * @param aboveElbow the aboveElbow to set
     */
    public void setAboveElbow(int aboveElbow) {
        this.aboveElbow = aboveElbow;
    }

    /**
     * @return the elbowDisarticulation
     */
    public int getElbowDisarticulation() {
        return elbowDisarticulation;
    }

    /**
     * @param elbowDisarticulation the elbowDisarticulation to set
     */
    public void setElbowDisarticulation(int elbowDisarticulation) {
        this.elbowDisarticulation = elbowDisarticulation;
    }

    /**
     * @return the belowElbow
     */
    public int getBelowElbow() {
        return belowElbow;
    }

    /**
     * @param belowElbow the belowElbow to set
     */
    public void setBelowElbow(int belowElbow) {
        this.belowElbow = belowElbow;
    }

    /**
     * @return the wristDisarticulation
     */
    public int getWristDisarticulation() {
        return wristDisarticulation;
    }

    /**
     * @param wristDisarticulation the wristDisarticulation to set
     */
    public void setWristDisarticulation(int wristDisarticulation) {
        this.wristDisarticulation = wristDisarticulation;
    }

    /**
     * @return the hand
     */
    public int getHand() {
        return hand;
    }

    /**
     * @param hand the hand to set
     */
    public void setHand(int hand) {
        this.hand = hand;
    }

    /**
     * @return the cosmeticFinger
     */
    public int getCosmeticFinger() {
        return cosmeticFinger;
    }

    /**
     * @param cosmeticFinger the cosmeticFinger to set
     */
    public void setCosmeticFinger(int cosmeticFinger) {
        this.cosmeticFinger = cosmeticFinger;
    }

    /**
     * @return the hipDisarticulation
     */
    public int getHipDisarticulation() {
        return hipDisarticulation;
    }

    /**
     * @param hipDisarticulation the hipDisarticulation to set
     */
    public void setHipDisarticulation(int hipDisarticulation) {
        this.hipDisarticulation = hipDisarticulation;
    }

    /**
     * @return the aboveKnee
     */
    public int getAboveKnee() {
        return aboveKnee;
    }

    /**
     * @param aboveKnee the aboveKnee to set
     */
    public void setAboveKnee(int aboveKnee) {
        this.aboveKnee = aboveKnee;
    }

    /**
     * @return the kneeDisarticulation
     */
    public int getKneeDisarticulation() {
        return kneeDisarticulation;
    }

    /**
     * @param kneeDisarticulation the kneeDisarticulation to set
     */
    public void setKneeDisarticulation(int kneeDisarticulation) {
        this.kneeDisarticulation = kneeDisarticulation;
    }

    /**
     * @return the belowKnee
     */
    public int getBelowKnee() {
        return belowKnee;
    }

    /**
     * @param belowKnee the belowKnee to set
     */
    public void setBelowKnee(int belowKnee) {
        this.belowKnee = belowKnee;
    }

    /**
     * @return the symes
     */
    public int getSymes() {
        return symes;
    }

    /**
     * @param symes the symes to set
     */
    public void setSymes(int symes) {
        this.symes = symes;
    }

    /**
     * @return the partialFoot
     */
    public int getPartialFoot() {
        return partialFoot;
    }

    /**
     * @param partialFoot the partialFoot to set
     */
    public void setPartialFoot(int partialFoot) {
        this.partialFoot = partialFoot;
    }

    /**
     * @return the aeroplaneSplint
     */
    public int getAeroplaneSplint() {
        return aeroplaneSplint;
    }

    /**
     * @param aeroplaneSplint the aeroplaneSplint to set
     */
    public void setAeroplaneSplint(int aeroplaneSplint) {
        this.aeroplaneSplint = aeroplaneSplint;
    }

    /**
     * @return the figureEightSplint
     */
    public int getFigureEightSplint() {
        return figureEightSplint;
    }

    /**
     * @param figureEightSplint the figureEightSplint to set
     */
    public void setFigureEightSplint(int figureEightSplint) {
        this.figureEightSplint = figureEightSplint;
    }

    /**
     * @return the forearmSplint
     */
    public int getForearmSplint() {
        return forearmSplint;
    }

    /**
     * @param forearmSplint the forearmSplint to set
     */
    public void setForearmSplint(int forearmSplint) {
        this.forearmSplint = forearmSplint;
    }

    /**
     * @return the handSplint
     */
    public int getHandSplint() {
        return handSplint;
    }

    /**
     * @param handSplint the handSplint to set
     */
    public void setHandSplint(int handSplint) {
        this.handSplint = handSplint;
    }

    /**
     * @return the hkafoh
     */
    public int getHkafoh() {
        return hkafoh;
    }

    /**
     * @param hkafoh the hkafoh to set
     */
    public void setHkafoh(int hkafoh) {
        this.hkafoh = hkafoh;
    }

    /**
     * @return the kafo
     */
    public int getKafo() {
        return kafo;
    }

    /**
     * @param kafo the kafo to set
     */
    public void setKafo(int kafo) {
        this.kafo = kafo;
    }

    /**
     * @return the afo
     */
    public int getAfo() {
        return afo;
    }

    /**
     * @param afo the afo to set
     */
    public void setAfo(int afo) {
        this.afo = afo;
    }

    /**
     * @return the kneeOrthosis
     */
    public int getKneeOrthosis() {
        return kneeOrthosis;
    }

    /**
     * @param kneeOrthosis the kneeOrthosis to set
     */
    public void setKneeOrthosis(int kneeOrthosis) {
        this.kneeOrthosis = kneeOrthosis;
    }

    /**
     * @return the dbSplint
     */
    public int getDbSplint() {
        return dbSplint;
    }

    /**
     * @param dbSplint the dbSplint to set
     */
    public void setDbSplint(int dbSplint) {
        this.dbSplint = dbSplint;
    }

    /**
     * @return the modifiedShoe
     */
    public int getModifiedShoe() {
        return modifiedShoe;
    }

    /**
     * @param modifiedShoe the modifiedShoe to set
     */
    public void setModifiedShoe(int modifiedShoe) {
        this.modifiedShoe = modifiedShoe;
    }

    /**
     * @return the serialCastingCTEV
     */
    public int getSerialCastingCTEV() {
        return serialCastingCTEV;
    }

    /**
     * @param serialCastingCTEV the serialCastingCTEV to set
     */
    public void setSerialCastingCTEV(int serialCastingCTEV) {
        this.serialCastingCTEV = serialCastingCTEV;
    }

    /**
     * @return the cervicalCollar
     */
    public int getCervicalCollar() {
        return cervicalCollar;
    }

    /**
     * @param cervicalCollar the cervicalCollar to set
     */
    public void setCervicalCollar(int cervicalCollar) {
        this.cervicalCollar = cervicalCollar;
    }

    /**
     * @return the lsBrace
     */
    public int getLsBrace() {
        return lsBrace;
    }

    /**
     * @param lsBrace the lsBrace to set
     */
    public void setLsBrace(int lsBrace) {
        this.lsBrace = lsBrace;
    }

    /**
     * @return the tlsoBraceForScoliosisKyphosis
     */
    public int getTlsoBraceForScoliosisKyphosis() {
        return tlsoBraceForScoliosisKyphosis;
    }

    /**
     * @param tlsoBraceForScoliosisKyphosis the tlsoBraceForScoliosisKyphosis to set
     */
    public void setTlsoBraceForScoliosisKyphosis(int tlsoBraceForScoliosisKyphosis) {
        this.tlsoBraceForScoliosisKyphosis = tlsoBraceForScoliosisKyphosis;
    }

    /**
     * @return the wheelChairSmall
     */
    public int getWheelChairSmall() {
        return wheelChairSmall;
    }

    /**
     * @param wheelChairSmall the wheelChairSmall to set
     */
    public void setWheelChairSmall(int wheelChairSmall) {
        this.wheelChairSmall = wheelChairSmall;
    }

    /**
     * @return the wheelChairLarge
     */
    public int getWheelChairLarge() {
        return wheelChairLarge;
    }

    /**
     * @param wheelChairLarge the wheelChairLarge to set
     */
    public void setWheelChairLarge(int wheelChairLarge) {
        this.wheelChairLarge = wheelChairLarge;
    }

    /**
     * @return the tricycleSmall
     */
    public int getTricycleSmall() {
        return tricycleSmall;
    }

    /**
     * @param tricycleSmall the tricycleSmall to set
     */
    public void setTricycleSmall(int tricycleSmall) {
        this.tricycleSmall = tricycleSmall;
    }

    /**
     * @return the tricycleLarge
     */
    public int getTricycleLarge() {
        return tricycleLarge;
    }

    /**
     * @param tricycleLarge the tricycleLarge to set
     */
    public void setTricycleLarge(int tricycleLarge) {
        this.tricycleLarge = tricycleLarge;
    }

    /**
     * @return the walkingFrameWalkerSmall
     */
    public int getWalkingFrameWalkerSmall() {
        return walkingFrameWalkerSmall;
    }

    /**
     * @param walkingFrameWalkerSmall the walkingFrameWalkerSmall to set
     */
    public void setWalkingFrameWalkerSmall(int walkingFrameWalkerSmall) {
        this.walkingFrameWalkerSmall = walkingFrameWalkerSmall;
    }

    /**
     * @return the walkingFrameWalkerLarge
     */
    public int getWalkingFrameWalkerLarge() {
        return walkingFrameWalkerLarge;
    }

    /**
     * @param walkingFrameWalkerLarge the walkingFrameWalkerLarge to set
     */
    public void setWalkingFrameWalkerLarge(int walkingFrameWalkerLarge) {
        this.walkingFrameWalkerLarge = walkingFrameWalkerLarge;
    }

    /**
     * @return the walkingStickSmall
     */
    public int getWalkingStickSmall() {
        return walkingStickSmall;
    }

    /**
     * @param walkingStickSmall the walkingStickSmall to set
     */
    public void setWalkingStickSmall(int walkingStickSmall) {
        this.walkingStickSmall = walkingStickSmall;
    }

    /**
     * @return the walkingStickLarge
     */
    public int getWalkingStickLarge() {
        return walkingStickLarge;
    }

    /**
     * @param walkingStickLarge the walkingStickLarge to set
     */
    public void setWalkingStickLarge(int walkingStickLarge) {
        this.walkingStickLarge = walkingStickLarge;
    }

    /**
     * @return the axillarySmall
     */
    public int getAxillarySmall() {
        return axillarySmall;
    }

    /**
     * @param axillarySmall the axillarySmall to set
     */
    public void setAxillarySmall(int axillarySmall) {
        this.axillarySmall = axillarySmall;
    }

    /**
     * @return the axillaryMedium
     */
    public int getAxillaryMedium() {
        return axillaryMedium;
    }

    /**
     * @param axillaryMedium the axillaryMedium to set
     */
    public void setAxillaryMedium(int axillaryMedium) {
        this.axillaryMedium = axillaryMedium;
    }

    /**
     * @return the axillaryLarge
     */
    public int getAxillaryLarge() {
        return axillaryLarge;
    }

    /**
     * @param axillaryLarge the axillaryLarge to set
     */
    public void setAxillaryLarge(int axillaryLarge) {
        this.axillaryLarge = axillaryLarge;
    }

    /**
     * @return the axillaryExtraLarge
     */
    public int getAxillaryExtraLarge() {
        return axillaryExtraLarge;
    }

    /**
     * @param axillaryExtraLarge the axillaryExtraLarge to set
     */
    public void setAxillaryExtraLarge(int axillaryExtraLarge) {
        this.axillaryExtraLarge = axillaryExtraLarge;
    }

    /**
     * @return the elbowSmall
     */
    public int getElbowSmall() {
        return elbowSmall;
    }

    /**
     * @param elbowSmall the elbowSmall to set
     */
    public void setElbowSmall(int elbowSmall) {
        this.elbowSmall = elbowSmall;
    }

    /**
     * @return the elbowMedium
     */
    public int getElbowMedium() {
        return elbowMedium;
    }

    /**
     * @param elbowMedium the elbowMedium to set
     */
    public void setElbowMedium(int elbowMedium) {
        this.elbowMedium = elbowMedium;
    }

    /**
     * @return the elbowLarge
     */
    public int getElbowLarge() {
        return elbowLarge;
    }

    /**
     * @param elbowLarge the elbowLarge to set
     */
    public void setElbowLarge(int elbowLarge) {
        this.elbowLarge = elbowLarge;
    }

    /**
     * @return the elbowExtraLarge
     */
    public int getElbowExtraLarge() {
        return elbowExtraLarge;
    }

    /**
     * @param elbowExtraLarge the elbowExtraLarge to set
     */
    public void setElbowExtraLarge(int elbowExtraLarge) {
        this.elbowExtraLarge = elbowExtraLarge;
    }

    /**
     * @return the gutterSmall
     */
    public int getGutterSmall() {
        return gutterSmall;
    }

    /**
     * @param gutterSmall the gutterSmall to set
     */
    public void setGutterSmall(int gutterSmall) {
        this.gutterSmall = gutterSmall;
    }

    /**
     * @return the gutterMedium
     */
    public int getGutterMedium() {
        return gutterMedium;
    }

    /**
     * @param gutterMedium the gutterMedium to set
     */
    public void setGutterMedium(int gutterMedium) {
        this.gutterMedium = gutterMedium;
    }

    /**
     * @return the gutterLarge
     */
    public int getGutterLarge() {
        return gutterLarge;
    }

    /**
     * @param gutterLarge the gutterLarge to set
     */
    public void setGutterLarge(int gutterLarge) {
        this.gutterLarge = gutterLarge;
    }

    /**
     * @return the gutterExtraLarge
     */
    public int getGutterExtraLarge() {
        return gutterExtraLarge;
    }

    /**
     * @param gutterExtraLarge the gutterExtraLarge to set
     */
    public void setGutterExtraLarge(int gutterExtraLarge) {
        this.gutterExtraLarge = gutterExtraLarge;
    }

    /**
     * @return the tripodSmall
     */
    public int getTripodSmall() {
        return tripodSmall;
    }

    /**
     * @param tripodSmall the tripodSmall to set
     */
    public void setTripodSmall(int tripodSmall) {
        this.tripodSmall = tripodSmall;
    }

    /**
     * @return the tripodMedium
     */
    public int getTripodMedium() {
        return tripodMedium;
    }

    /**
     * @param tripodMedium the tripodMedium to set
     */
    public void setTripodMedium(int tripodMedium) {
        this.tripodMedium = tripodMedium;
    }

    /**
     * @return the tripodLarge
     */
    public int getTripodLarge() {
        return tripodLarge;
    }

    /**
     * @param tripodLarge the tripodLarge to set
     */
    public void setTripodLarge(int tripodLarge) {
        this.tripodLarge = tripodLarge;
    }

    /**
     * @return the tripodExtraLarge
     */
    public int getTripodExtraLarge() {
        return tripodExtraLarge;
    }

    /**
     * @param tripodExtraLarge the tripodExtraLarge to set
     */
    public void setTripodExtraLarge(int tripodExtraLarge) {
        this.tripodExtraLarge = tripodExtraLarge;
    }

    /**
     * @return the feeding
     */
    public int getFeeding() {
        return feeding;
    }

    /**
     * @param feeding the feeding to set
     */
    public void setFeeding(int feeding) {
        this.feeding = feeding;
    }

    /**
     * @return the toiletingBathing
     */
    public int getToiletingBathing() {
        return toiletingBathing;
    }

    /**
     * @param toiletingBathing the toiletingBathing to set
     */
    public void setToiletingBathing(int toiletingBathing) {
        this.toiletingBathing = toiletingBathing;
    }

    /**
     * @return the brushing
     */
    public int getBrushing() {
        return brushing;
    }

    /**
     * @param brushing the brushing to set
     */
    public void setBrushing(int brushing) {
        this.brushing = brushing;
    }

    /**
     * @return the combing
     */
    public int getCombing() {
        return combing;
    }

    /**
     * @param combing the combing to set
     */
    public void setCombing(int combing) {
        this.combing = combing;
    }

    /**
     * @return the dressing
     */
    public int getDressing() {
        return dressing;
    }

    /**
     * @param dressing the dressing to set
     */
    public void setDressing(int dressing) {
        this.dressing = dressing;
    }

    /**
     * @return the writing
     */
    public int getWriting() {
        return writing;
    }

    /**
     * @param writing the writing to set
     */
    public void setWriting(int writing) {
        this.writing = writing;
    }

    /**
     * @return the drivingCycling
     */
    public int getDrivingCycling() {
        return drivingCycling;
    }

    /**
     * @param drivingCycling the drivingCycling to set
     */
    public void setDrivingCycling(int drivingCycling) {
        this.drivingCycling = drivingCycling;
    }

    /**
     * @return the bedTransfer
     */
    public int getBedTransfer() {
        return bedTransfer;
    }

    /**
     * @param bedTransfer the bedTransfer to set
     */
    public void setBedTransfer(int bedTransfer) {
        this.bedTransfer = bedTransfer;
    }

    /**
     * @return the districtName
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param districtName the districtName to set
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * @return the mandalName
     */
    public String getMandalName() {
        return mandalName;
    }

    /**
     * @param mandalName the mandalName to set
     */
    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }

    /**
     * @return the villageName
     */
    public String getVillageName() {
        return villageName;
    }

    /**
     * @param villageName the villageName to set
     */
    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    /**
     * @return the habitationName
     */
    public String getHabitationName() {
        return habitationName;
    }

    /**
     * @param habitationName the habitationName to set
     */
    public void setHabitationName(String habitationName) {
        this.habitationName = habitationName;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the otherNeeds
     */
    public int getOtherNeeds() {
        return otherNeeds;
    }

    /**
     * @param otherNeeds the otherNeeds to set
     */
    public void setOtherNeeds(int otherNeeds) {
        this.otherNeeds = otherNeeds;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the personcode
     */
    public String getPersoncode() {
        return personcode;
    }

    /**
     * @param personcode the personcode to set
     */
    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    /**
     * @return the houseno
     */
    public String getHouseno() {
        return houseno;
    }

    /**
     * @param houseno the houseno to set
     */
    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    /**
     * @return the pincode
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * @param pincode the pincode to set
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    /**
     * @return the disabilityType
     */
    public String getDisabilityType() {
        return disabilityType;
    }

    /**
     * @param disabilityType the disabilityType to set
     */
    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    /**
     * @return the subtypeofDisability
     */
    public String getSubtypeofDisability() {
        return subtypeofDisability;
    }

    /**
     * @param subtypeofDisability the subtypeofDisability to set
     */
    public void setSubtypeofDisability(String subtypeofDisability) {
        this.subtypeofDisability = subtypeofDisability;
    }

    /**
     * @return the partsInvolved
     */
    public String getPartsInvolved() {
        return partsInvolved;
    }

    /**
     * @param partsInvolved the partsInvolved to set
     */
    public void setPartsInvolved(String partsInvolved) {
        this.partsInvolved = partsInvolved;
    }

    /**
     * @return the totalpercent
     */
    public String getTotalpercent() {
        return totalpercent;
    }

    /**
     * @param totalpercent the totalpercent to set
     */
    public void setTotalpercent(String totalpercent) {
        this.totalpercent = totalpercent;
    }

    /**
     * @return the caste
     */
    public String getCaste() {
        return caste;
    }

    /**
     * @param caste the caste to set
     */
    public void setCaste(String caste) {
        this.caste = caste;
    }

    /**
     * @return the occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * @param occupation the occupation to set
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * @return the sugeryVI
     */
    public int getSugeryVI() {
        return sugeryVI;
    }

    /**
     * @param sugeryVI the sugeryVI to set
     */
    public void setSugeryVI(int sugeryVI) {
        this.sugeryVI = sugeryVI;
    }

    /**
     * @return the whiteCaneBlindStick
     */
    public int getWhiteCaneBlindStick() {
        return whiteCaneBlindStick;
    }

    /**
     * @param whiteCaneBlindStick the whiteCaneBlindStick to set
     */
    public void setWhiteCaneBlindStick(int whiteCaneBlindStick) {
        this.whiteCaneBlindStick = whiteCaneBlindStick;
    }

    /**
     * @return the brailleEquipments
     */
    public int getBrailleEquipments() {
        return brailleEquipments;
    }

    /**
     * @param brailleEquipments the brailleEquipments to set
     */
    public void setBrailleEquipments(int brailleEquipments) {
        this.brailleEquipments = brailleEquipments;
    }

    /**
     * @return the arithmeticFramesAbacus
     */
    public int getArithmeticFramesAbacus() {
        return arithmeticFramesAbacus;
    }

    /**
     * @param arithmeticFramesAbacus the arithmeticFramesAbacus to set
     */
    public void setArithmeticFramesAbacus(int arithmeticFramesAbacus) {
        this.arithmeticFramesAbacus = arithmeticFramesAbacus;
    }

    /**
     * @return the lowVisionAidsSpectaclesMagnifiers
     */
    public int getLowVisionAidsSpectaclesMagnifiers() {
        return lowVisionAidsSpectaclesMagnifiers;
    }

    /**
     * @param lowVisionAidsSpectaclesMagnifiers the lowVisionAidsSpectaclesMagnifiers to set
     */
    public void setLowVisionAidsSpectaclesMagnifiers(int lowVisionAidsSpectaclesMagnifiers) {
        this.lowVisionAidsSpectaclesMagnifiers = lowVisionAidsSpectaclesMagnifiers;
    }

    /**
     * @return the speechSynthesizer
     */
    public int getSpeechSynthesizer() {
        return speechSynthesizer;
    }

    /**
     * @param speechSynthesizer the speechSynthesizer to set
     */
    public void setSpeechSynthesizer(int speechSynthesizer) {
        this.speechSynthesizer = speechSynthesizer;
    }

    /**
     * @return the brailleShortHandMachinesWriters
     */
    public int getBrailleShortHandMachinesWriters() {
        return brailleShortHandMachinesWriters;
    }

    /**
     * @param brailleShortHandMachinesWriters the brailleShortHandMachinesWriters to set
     */
    public void setBrailleShortHandMachinesWriters(int brailleShortHandMachinesWriters) {
        this.brailleShortHandMachinesWriters = brailleShortHandMachinesWriters;
    }

    /**
     * @return the talkingWatchCalculator
     */
    public int getTalkingWatchCalculator() {
        return talkingWatchCalculator;
    }

    /**
     * @param talkingWatchCalculator the talkingWatchCalculator to set
     */
    public void setTalkingWatchCalculator(int talkingWatchCalculator) {
        this.talkingWatchCalculator = talkingWatchCalculator;
    }

    /**
     * @return the viADLEquipment
     */
    public int getViADLEquipment() {
        return viADLEquipment;
    }

    /**
     * @param viADLEquipment the viADLEquipment to set
     */
    public void setViADLEquipment(int viADLEquipment) {
        this.viADLEquipment = viADLEquipment;
    }

    /**
     * @return the otherNeedsVI
     */
    public int getOtherNeedsVI() {
        return otherNeedsVI;
    }

    /**
     * @param otherNeedsVI the otherNeedsVI to set
     */
    public void setOtherNeedsVI(int otherNeedsVI) {
        this.otherNeedsVI = otherNeedsVI;
    }

    /**
     * @return the speechTherapyBelowThreeYearsHI
     */
    public int getSpeechTherapyBelowThreeYearsHI() {
        return speechTherapyBelowThreeYearsHI;
    }

    /**
     * @param speechTherapyBelowThreeYearsHI the speechTherapyBelowThreeYearsHI to set
     */
    public void setSpeechTherapyBelowThreeYearsHI(int speechTherapyBelowThreeYearsHI) {
        this.speechTherapyBelowThreeYearsHI = speechTherapyBelowThreeYearsHI;
    }

    /**
     * @return the speechTherapyAboveThreeYears
     */
    public int getSpeechTherapyAboveThreeYearsHI() {
        return speechTherapyAboveThreeYearsHI;
    }

    /**
     * @param speechTherapyAboveThreeYearsHI the speechTherapyAboveThreeYears to set
     */
    public void setSpeechTherapyAboveThreeYearsHI(int speechTherapyAboveThreeYearsHI) {
        this.speechTherapyAboveThreeYearsHI = speechTherapyAboveThreeYearsHI;
    }

    /**
     * @return the languageDevelopment
     */
    public int getLanguageDevelopment() {
        return languageDevelopment;
    }

    /**
     * @param languageDevelopment the languageDevelopment to set
     */
    public void setLanguageDevelopment(int languageDevelopment) {
        this.languageDevelopment = languageDevelopment;
    }

    /**
     * @return the surgeryHI
     */
    public int getSurgeryHI() {
        return surgeryHI;
    }

    /**
     * @param surgeryHI the surgeryHI to set
     */
    public void setSurgeryHI(int surgeryHI) {
        this.surgeryHI = surgeryHI;
    }

    /**
     * @return the cochlearImplantation
     */
    public int getCochlearImplantation() {
        return cochlearImplantation;
    }

    /**
     * @param cochlearImplantation the cochlearImplantation to set
     */
    public void setCochlearImplantation(int cochlearImplantation) {
        this.cochlearImplantation = cochlearImplantation;
    }

    /**
     * @return the lowIntensitySCord
     */
    public int getLowIntensitySCord() {
        return lowIntensitySCord;
    }

    /**
     * @param lowIntensitySCord the lowIntensitySCord to set
     */
    public void setLowIntensitySCord(int lowIntensitySCord) {
        this.lowIntensitySCord = lowIntensitySCord;
    }

    /**
     * @return the lowIntensityVCord
     */
    public int getLowIntensityVCord() {
        return lowIntensityVCord;
    }

    /**
     * @param lowIntensityVCord the lowIntensityVCord to set
     */
    public void setLowIntensityVCord(int lowIntensityVCord) {
        this.lowIntensityVCord = lowIntensityVCord;
    }

    /**
     * @return the moderateIntensitySCord
     */
    public int getModerateIntensitySCord() {
        return moderateIntensitySCord;
    }

    /**
     * @param moderateIntensitySCord the moderateIntensitySCord to set
     */
    public void setModerateIntensitySCord(int moderateIntensitySCord) {
        this.moderateIntensitySCord = moderateIntensitySCord;
    }

    /**
     * @return the moderateIntensityVCord
     */
    public int getModerateIntensityVCord() {
        return moderateIntensityVCord;
    }

    /**
     * @param moderateIntensityVCord the moderateIntensityVCord to set
     */
    public void setModerateIntensityVCord(int moderateIntensityVCord) {
        this.moderateIntensityVCord = moderateIntensityVCord;
    }

    /**
     * @return the highIntensitySCord
     */
    public int getHighIntensitySCord() {
        return highIntensitySCord;
    }

    /**
     * @param highIntensitySCord the highIntensitySCord to set
     */
    public void setHighIntensitySCord(int highIntensitySCord) {
        this.highIntensitySCord = highIntensitySCord;
    }

    /**
     * @return the highIntensityVCord
     */
    public int getHighIntensityVCord() {
        return highIntensityVCord;
    }

    /**
     * @param highIntensityVCord the highIntensityVCord to set
     */
    public void setHighIntensityVCord(int highIntensityVCord) {
        this.highIntensityVCord = highIntensityVCord;
    }

    /**
     * @return the implantableHearingAid
     */
    public int getImplantableHearingAid() {
        return implantableHearingAid;
    }

    /**
     * @param implantableHearingAid the implantableHearingAid to set
     */
    public void setImplantableHearingAid(int implantableHearingAid) {
        this.implantableHearingAid = implantableHearingAid;
    }

    /**
     * @return the otherNeedsHI
     */
    public int getOtherNeedsHI() {
        return otherNeedsHI;
    }

    /**
     * @param otherNeedsHI the otherNeedsHI to set
     */
    public void setOtherNeedsHI(int otherNeedsHI) {
        this.otherNeedsHI = otherNeedsHI;
    }

    /**
     * @return the speechTherapyBelowThreeYearsMR
     */
    public int getSpeechTherapyBelowThreeYearsMR() {
        return speechTherapyBelowThreeYearsMR;
    }

    /**
     * @param speechTherapyBelowThreeYearsMR the speechTherapyBelowThreeYearsMR to set
     */
    public void setSpeechTherapyBelowThreeYearsMR(int speechTherapyBelowThreeYearsMR) {
        this.speechTherapyBelowThreeYearsMR = speechTherapyBelowThreeYearsMR;
    }

    /**
     * @return the speechTherapyAboveThreeYearsMR
     */
    public int getSpeechTherapyAboveThreeYearsMR() {
        return speechTherapyAboveThreeYearsMR;
    }

    /**
     * @param speechTherapyAboveThreeYearsMR the speechTherapyAboveThreeYearsMR to set
     */
    public void setSpeechTherapyAboveThreeYearsMR(int speechTherapyAboveThreeYearsMR) {
        this.speechTherapyAboveThreeYearsMR = speechTherapyAboveThreeYearsMR;
    }

    /**
     * @return the behaviourModificationPsychotherapyBelow
     */
    public int getBehaviourModificationPsychotherapyBelow() {
        return behaviourModificationPsychotherapyBelow;
    }

    /**
     * @param behaviourModificationPsychotherapyBelow the behaviourModificationPsychotherapyBelow to set
     */
    public void setBehaviourModificationPsychotherapyBelow(int behaviourModificationPsychotherapyBelow) {
        this.behaviourModificationPsychotherapyBelow = behaviourModificationPsychotherapyBelow;
    }

    /**
     * @return the behaviourModificationPsychotherapyAbove
     */
    public int getBehaviourModificationPsychotherapyAbove() {
        return behaviourModificationPsychotherapyAbove;
    }

    /**
     * @param behaviourModificationPsychotherapyAbove the behaviourModificationPsychotherapyAbove to set
     */
    public void setBehaviourModificationPsychotherapyAbove(int behaviourModificationPsychotherapyAbove) {
        this.behaviourModificationPsychotherapyAbove = behaviourModificationPsychotherapyAbove;
    }

    /**
     * @return the sensoryIntegrationCognitiveDevelopment
     */
    public int getSensoryIntegrationCognitiveDevelopment() {
        return sensoryIntegrationCognitiveDevelopment;
    }

    /**
     * @param sensoryIntegrationCognitiveDevelopment the sensoryIntegrationCognitiveDevelopment to set
     */
    public void setSensoryIntegrationCognitiveDevelopment(int sensoryIntegrationCognitiveDevelopment) {
        this.sensoryIntegrationCognitiveDevelopment = sensoryIntegrationCognitiveDevelopment;
    }

    /**
     * @return the cognitiveBehaviourTherapy
     */
    public int getCognitiveBehaviourTherapy() {
        return cognitiveBehaviourTherapy;
    }

    /**
     * @param cognitiveBehaviourTherapy the cognitiveBehaviourTherapy to set
     */
    public void setCognitiveBehaviourTherapy(int cognitiveBehaviourTherapy) {
        this.cognitiveBehaviourTherapy = cognitiveBehaviourTherapy;
    }

    /**
     * @return the parentFamilyIntervention
     */
    public int getParentFamilyIntervention() {
        return parentFamilyIntervention;
    }

    /**
     * @param parentFamilyIntervention the parentFamilyIntervention to set
     */
    public void setParentFamilyIntervention(int parentFamilyIntervention) {
        this.parentFamilyIntervention = parentFamilyIntervention;
    }

    /**
     * @return the physiotherapyMR
     */
    public int getPhysiotherapyMR() {
        return physiotherapyMR;
    }

    /**
     * @param physiotherapyMR the physiotherapyMR to set
     */
    public void setPhysiotherapyMR(int physiotherapyMR) {
        this.physiotherapyMR = physiotherapyMR;
    }

    /**
     * @return the occupationalTherapyMR
     */
    public int getOccupationalTherapyMR() {
        return occupationalTherapyMR;
    }

    /**
     * @param occupationalTherapyMR the occupationalTherapyMR to set
     */
    public void setOccupationalTherapyMR(int occupationalTherapyMR) {
        this.occupationalTherapyMR = occupationalTherapyMR;
    }

    /**
     * @return the learningMaterials
     */
    public int getLearningMaterials() {
        return learningMaterials;
    }

    /**
     * @param learningMaterials the learningMaterials to set
     */
    public void setLearningMaterials(int learningMaterials) {
        this.learningMaterials = learningMaterials;
    }

    /**
     * @return the specialSoftware
     */
    public int getSpecialSoftware() {
        return specialSoftware;
    }

    /**
     * @param specialSoftware the specialSoftware to set
     */
    public void setSpecialSoftware(int specialSoftware) {
        this.specialSoftware = specialSoftware;
    }

    /**
     * @return the toys
     */
    public int getToys() {
        return toys;
    }

    /**
     * @param toys the toys to set
     */
    public void setToys(int toys) {
        this.toys = toys;
    }

    /**
     * @return the otherNeedsMR
     */
    public int getOtherNeedsMR() {
        return otherNeedsMR;
    }

    /**
     * @param otherNeedsMR the otherNeedsMR to set
     */
    public void setOtherNeedsMR(int otherNeedsMR) {
        this.otherNeedsMR = otherNeedsMR;
    }

    /**
     * @return the psychotherapyBehaviourBelowThreeYearsMI
     */
    public int getPsychotherapyBehaviourBelowThreeYearsMI() {
        return psychotherapyBehaviourBelowThreeYearsMI;
    }

    /**
     * @param psychotherapyBehaviourBelowThreeYearsMI the psychotherapyBehaviourBelowThreeYearsMI to set
     */
    public void setPsychotherapyBehaviourBelowThreeYearsMI(int psychotherapyBehaviourBelowThreeYearsMI) {
        this.psychotherapyBehaviourBelowThreeYearsMI = psychotherapyBehaviourBelowThreeYearsMI;
    }

    /**
     * @return the psychotherapyBehaviourAboveThreeYearsMI
     */
    public int getPsychotherapyBehaviourAboveThreeYearsMI() {
        return psychotherapyBehaviourAboveThreeYearsMI;
    }

    /**
     * @param psychotherapyBehaviourAboveThreeYearsMI the psychotherapyBehaviourAboveThreeYearsMI to set
     */
    public void setPsychotherapyBehaviourAboveThreeYearsMI(int psychotherapyBehaviourAboveThreeYearsMI) {
        this.psychotherapyBehaviourAboveThreeYearsMI = psychotherapyBehaviourAboveThreeYearsMI;
    }

    /**
     * @return the surgeryMI
     */
    public int getSurgeryMI() {
        return surgeryMI;
    }

    /**
     * @param surgeryMI the surgeryMI to set
     */
    public void setSurgeryMI(int surgeryMI) {
        this.surgeryMI = surgeryMI;
    }

    /**
     * @return the admissionPsychiatric
     */
    public int getAdmissionPsychiatric() {
        return admissionPsychiatric;
    }

    /**
     * @param admissionPsychiatric the admissionPsychiatric to set
     */
    public void setAdmissionPsychiatric(int admissionPsychiatric) {
        this.admissionPsychiatric = admissionPsychiatric;
    }

    /**
     * @return the otherNeedsMI
     */
    public int getOtherNeedsMI() {
        return otherNeedsMI;
    }

    /**
     * @param otherNeedsMI the otherNeedsMI to set
     */
    public void setOtherNeedsMI(int otherNeedsMI) {
        this.otherNeedsMI = otherNeedsMI;
    }

    /**
     * @return the earlyEducationService
     */
    public int getEarlyEducationService() {
        return earlyEducationService;
    }

    /**
     * @param earlyEducationService the earlyEducationService to set
     */
    public void setEarlyEducationService(int earlyEducationService) {
        this.earlyEducationService = earlyEducationService;
    }

    /**
     * @return the homeBased
     */
    public int getHomeBased() {
        return homeBased;
    }

    /**
     * @param homeBased the homeBased to set
     */
    public void setHomeBased(int homeBased) {
        this.homeBased = homeBased;
    }

    /**
     * @return the specialEducation
     */
    public int getSpecialEducation() {
        return specialEducation;
    }

    /**
     * @param specialEducation the specialEducation to set
     */
    public void setSpecialEducation(int specialEducation) {
        this.specialEducation = specialEducation;
    }

    /**
     * @return the inclusiveEducation
     */
    public int getInclusiveEducation() {
        return inclusiveEducation;
    }

    /**
     * @param inclusiveEducation the inclusiveEducation to set
     */
    public void setInclusiveEducation(int inclusiveEducation) {
        this.inclusiveEducation = inclusiveEducation;
    }

    /**
     * @return the employmentPublicPvtSector
     */
    public int getEmploymentPublicPvtSector() {
        return employmentPublicPvtSector;
    }

    /**
     * @param employmentPublicPvtSector the employmentPublicPvtSector to set
     */
    public void setEmploymentPublicPvtSector(int employmentPublicPvtSector) {
        this.employmentPublicPvtSector = employmentPublicPvtSector;
    }

    /**
     * @return the selfEmployment
     */
    public int getSelfEmployment() {
        return selfEmployment;
    }

    /**
     * @param selfEmployment the selfEmployment to set
     */
    public void setSelfEmployment(int selfEmployment) {
        this.selfEmployment = selfEmployment;
    }

    /**
     * @return the individual
     */
    public int getIndividual() {
        return individual;
    }

    /**
     * @param individual the individual to set
     */
    public void setIndividual(int individual) {
        this.individual = individual;
    }

    /**
     * @return the family
     */
    public int getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(int family) {
        this.family = family;
    }

    /**
     * @return the otherGeneralNeeds
     */
    public int getOtherGeneralNeeds() {
        return otherGeneralNeeds;
    }

    /**
     * @param otherGeneralNeeds the otherGeneralNeeds to set
     */
    public void setOtherGeneralNeeds(int otherGeneralNeeds) {
        this.otherGeneralNeeds = otherGeneralNeeds;
    }

    /**
     * @return the legalGurdian
     */
    public int getLegalGurdian() {
        return legalGurdian;
    }

    /**
     * @param legalGurdian the legalGurdian to set
     */
    public void setLegalGurdian(int legalGurdian) {
        this.legalGurdian = legalGurdian;
    }

    /**
     * @return the managerToTakeCareProperties
     */
    public int getManagerToTakeCareProperties() {
        return managerToTakeCareProperties;
    }

    /**
     * @param managerToTakeCareProperties the managerToTakeCareProperties to set
     */
    public void setManagerToTakeCareProperties(int managerToTakeCareProperties) {
        this.managerToTakeCareProperties = managerToTakeCareProperties;
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

    /**
     * @return the notComrtoCamp
     */
    public int getNotComrtoCamp() {
        return notComrtoCamp;
    }

    /**
     * @param notComrtoCamp the notComrtoCamp to set
     */
    public void setNotComrtoCamp(int notComrtoCamp) {
        this.notComrtoCamp = notComrtoCamp;
    }

    /**
     * @return the onlyPartA
     */
    public int getOnlyPartA() {
        return onlyPartA;
    }

    /**
     * @param onlyPartA the onlyPartA to set
     */
    public void setOnlyPartA(int onlyPartA) {
        this.onlyPartA = onlyPartA;
    }

    /**
     * @return the pensionid
     */
    public String getPensionid() {
        return pensionid;
    }

    /**
     * @param pensionid the pensionid to set
     */
    public void setPensionid(String pensionid) {
        this.pensionid = pensionid;
    }

    /**
     * @return the ralationName
     */
    public String getRalationName() {
        return ralationName;
    }

    /**
     * @param ralationName the ralationName to set
     */
    public void setRalationName(String ralationName) {
        this.ralationName = ralationName;
    }

    /**
     * @return the rationcard
     */
    public String getRationcard() {
        return rationcard;
    }

    /**
     * @param rationcard the rationcard to set
     */
    public void setRationcard(String rationcard) {
        this.rationcard = rationcard;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the surgeryType
     */
    public String getSurgeryType() {
        return surgeryType;
    }

    /**
     * @param surgeryType the surgeryType to set
     */
    public void setSurgeryType(String surgeryType) {
        this.surgeryType = surgeryType;
    }

    /**
     * @return the illiterate
     */
    public int getIlliterate() {
        return illiterate;
    }

    /**
     * @param illiterate the illiterate to set
     */
    public void setIlliterate(int illiterate) {
        this.illiterate = illiterate;
    }

    /**
     * @return the belowTenth
     */
    public int getBelowTenth() {
        return belowTenth;
    }

    /**
     * @param belowTenth the belowTenth to set
     */
    public void setBelowTenth(int belowTenth) {
        this.belowTenth = belowTenth;
    }

    /**
     * @return the tenth
     */
    public int getTenth() {
        return tenth;
    }

    /**
     * @param tenth the tenth to set
     */
    public void setTenth(int tenth) {
        this.tenth = tenth;
    }

    /**
     * @return the intermediate
     */
    public int getIntermediate() {
        return intermediate;
    }

    /**
     * @param intermediate the intermediate to set
     */
    public void setIntermediate(int intermediate) {
        this.intermediate = intermediate;
    }

    /**
     * @return the diplomaOrITI
     */
    public int getDiplomaOrITI() {
        return diplomaOrITI;
    }

    /**
     * @param diplomaOrITI the diplomaOrITI to set
     */
    public void setDiplomaOrITI(int diplomaOrITI) {
        this.diplomaOrITI = diplomaOrITI;
    }

    /**
     * @return the graduate
     */
    public int getGraduate() {
        return graduate;
    }

    /**
     * @param graduate the graduate to set
     */
    public void setGraduate(int graduate) {
        this.graduate = graduate;
    }

    /**
     * @return the postGraduate
     */
    public int getPostGraduate() {
        return postGraduate;
    }

    /**
     * @param postGraduate the postGraduate to set
     */
    public void setPostGraduate(int postGraduate) {
        this.postGraduate = postGraduate;
    }

    /**
     * @return the notEntered
     */
    public int getNotEntered() {
        return notEntered;
    }

    /**
     * @param notEntered the notEntered to set
     */
    public void setNotEntered(int notEntered) {
        this.notEntered = notEntered;
    }
}
