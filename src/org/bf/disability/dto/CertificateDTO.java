/*
 * CertificateDTO.java
 *
 * Created on September 18, 2008, 10:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dto;

/**
 * This class contains the fields, used in the generation of a certificate
 * @version 1.0
 */
public class CertificateDTO {

    /** Creates a new instance of CertificateDTO */
    public CertificateDTO() {
    }
    private String relationship;
    private String relationname;
    private String housenumber;
    private String districtname;
    private String habitationname;
    private String villagename;
    private String mandalname;
    private String suggestionone;
    private String suggestiontwo;
    private String suggestionthree;
    private String name;
    private String personcode;
    private String doctorname;
    private String doctornumber;
    private String doctordesignation;
    private String issuedate;
    private String surgery;
    private String referred;
    private String physiotherapy;
    private String councelling;
    private String anyother;
    private String speechtherapy;
    private String hearingaid;
    private String behaviour;
    private String psychotherapy;
    private String admission;
    private String disabilityidforrejecte;
    private String lowvision;
    private String partInvolved1;
    private String partInvolved2;
    private String partInvolved3;
    private String partInvolved4;
    private String partInvolved5;
    private String partInvolved6;
    private String partInvolved7;
    private String partInvolved8;
    private String partInvolved9;
    private String partInvolved10;
    private String partInvolved11;
    private String partInvolved12;
    private String partInvolved13;
    private String partInvolved14;
    private String partInvolved15;
    private String partInvolved16;
    private String partInvolved17;
    private String ohsubtypes1;
    private String ohsubtypes2;
    private String ohsubtypes3;
    private String ohsubtypes4;
    private String ohsubtypes5;
    private String ohsubtypes6;
    private String ohsubtypes7;
    private String ohsubtypes8;
    private String ohsubtypes9;
    private String ohsubtypes10;
    private String ohsubtypes11;
    private String ohsubtypes12;
    private String ohsubtypes13;
    private String ohsubtypes14;
    private String ohsubtypes15;
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
    // New field added by siva
    private String coordinate_drinking;
    public String hospitalname;
    public String hospitaladdress;
    // added for displaying certificate
    private String maritialstatus;
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRelationname() {
        return relationname;
    }

    public void setRelationname(String relationname) {
        this.relationname = relationname;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    public String getMandalname() {
        return mandalname;
    }

    public void setMandalname(String mandalname) {
        this.mandalname = mandalname;
    }

    public String getSuggestionone() {
        return suggestionone;
    }

    public void setSuggestionone(String suggestionone) {
        this.suggestionone = suggestionone;
    }

    public String getSuggestiontwo() {
        return suggestiontwo;
    }

    public void setSuggestiontwo(String suggestiontwo) {
        this.suggestiontwo = suggestiontwo;
    }

    public String getSuggestionthree() {
        return suggestionthree;
    }

    public void setSuggestionthree(String suggestionthree) {
        this.suggestionthree = suggestionthree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersoncode() {
        return personcode;
    }

    public void setPersoncode(String personcode) {
        this.personcode = personcode;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDoctornumber() {
        return doctornumber;
    }

    public void setDoctornumber(String doctornumber) {
        this.doctornumber = doctornumber;
    }

    public String getDoctordesignation() {
        return doctordesignation;
    }

    public void setDoctordesignation(String doctordesignation) {
        this.doctordesignation = doctordesignation;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getReferred() {
        return referred;
    }

    public void setReferred(String referred) {
        this.referred = referred;
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

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getPsychotherapy() {
        return psychotherapy;
    }

    public void setPsychotherapy(String psychotherapy) {
        this.psychotherapy = psychotherapy;
    }

    public String getAdmission() {
        return admission;
    }

    public void setAdmission(String admission) {
        this.admission = admission;
    }

    public String getPhysiotherapy() {
        return physiotherapy;
    }

    public void setPhysiotherapy(String physiotherapy) {
        this.physiotherapy = physiotherapy;
    }

    public String getLowvision() {
        return lowvision;
    }

    public void setLowvision(String lowvision) {
        this.lowvision = lowvision;
    }

    public String getDisabilityidforrejecte() {
        return disabilityidforrejecte;
    }

    public void setDisabilityidforrejecte(String disabilityidforrejecte) {
        this.disabilityidforrejecte = disabilityidforrejecte;
    }

    public String getPartInvolved1() {
        return partInvolved1;
    }

    public void setPartInvolved1(String partInvolved1) {
        this.partInvolved1 = partInvolved1;
    }

    public String getPartInvolved2() {
        return partInvolved2;
    }

    public void setPartInvolved2(String partInvolved2) {
        this.partInvolved2 = partInvolved2;
    }

    public String getPartInvolved3() {
        return partInvolved3;
    }

    public void setPartInvolved3(String partInvolved3) {
        this.partInvolved3 = partInvolved3;
    }

    public String getPartInvolved4() {
        return partInvolved4;
    }

    public void setPartInvolved4(String partInvolved4) {
        this.partInvolved4 = partInvolved4;
    }

    public String getPartInvolved5() {
        return partInvolved5;
    }

    public void setPartInvolved5(String partInvolved5) {
        this.partInvolved5 = partInvolved5;
    }

    public String getPartInvolved6() {
        return partInvolved6;
    }

    public void setPartInvolved6(String partInvolved6) {
        this.partInvolved6 = partInvolved6;
    }

    public String getPartInvolved7() {
        return partInvolved7;
    }

    public void setPartInvolved7(String partInvolved7) {
        this.partInvolved7 = partInvolved7;
    }

    public String getPartInvolved8() {
        return partInvolved8;
    }

    public void setPartInvolved8(String partInvolved8) {
        this.partInvolved8 = partInvolved8;
    }

    public String getPartInvolved9() {
        return partInvolved9;
    }

    public void setPartInvolved9(String partInvolved9) {
        this.partInvolved9 = partInvolved9;
    }

    public String getPartInvolved10() {
        return partInvolved10;
    }

    public void setPartInvolved10(String partInvolved10) {
        this.partInvolved10 = partInvolved10;
    }

    public String getPartInvolved11() {
        return partInvolved11;
    }

    public void setPartInvolved11(String partInvolved11) {
        this.partInvolved11 = partInvolved11;
    }

    public String getPartInvolved12() {
        return partInvolved12;
    }

    public void setPartInvolved12(String partInvolved12) {
        this.partInvolved12 = partInvolved12;
    }

    public String getPartInvolved13() {
        return partInvolved13;
    }

    public void setPartInvolved13(String partInvolved13) {
        this.partInvolved13 = partInvolved13;
    }

    public String getPartInvolved14() {
        return partInvolved14;
    }

    public void setPartInvolved14(String partInvolved14) {
        this.partInvolved14 = partInvolved14;
    }

    public String getPartInvolved15() {
        return partInvolved15;
    }

    public void setPartInvolved15(String partInvolved15) {
        this.partInvolved15 = partInvolved15;
    }

    public String getPartInvolved16() {
        return partInvolved16;
    }

    public void setPartInvolved16(String partInvolved16) {
        this.partInvolved16 = partInvolved16;
    }

    public String getPartInvolved17() {
        return partInvolved17;
    }

    public void setPartInvolved17(String partInvolved17) {
        this.partInvolved17 = partInvolved17;
    }

    public String getOhsubtypes1() {
        return ohsubtypes1;
    }

    public void setOhsubtypes1(String ohsubtypes1) {
        this.ohsubtypes1 = ohsubtypes1;
    }

    public String getOhsubtypes2() {
        return ohsubtypes2;
    }

    public void setOhsubtypes2(String ohsubtypes2) {
        this.ohsubtypes2 = ohsubtypes2;
    }

    public String getOhsubtypes3() {
        return ohsubtypes3;
    }

    public void setOhsubtypes3(String ohsubtypes3) {
        this.ohsubtypes3 = ohsubtypes3;
    }

    public String getOhsubtypes4() {
        return ohsubtypes4;
    }

    public void setOhsubtypes4(String ohsubtypes4) {
        this.ohsubtypes4 = ohsubtypes4;
    }

    public String getOhsubtypes5() {
        return ohsubtypes5;
    }

    public void setOhsubtypes5(String ohsubtypes5) {
        this.ohsubtypes5 = ohsubtypes5;
    }

    public String getOhsubtypes6() {
        return ohsubtypes6;
    }

    public void setOhsubtypes6(String ohsubtypes6) {
        this.ohsubtypes6 = ohsubtypes6;
    }

    public String getOhsubtypes7() {
        return ohsubtypes7;
    }

    public void setOhsubtypes7(String ohsubtypes7) {
        this.ohsubtypes7 = ohsubtypes7;
    }

    public String getOhsubtypes8() {
        return ohsubtypes8;
    }

    public void setOhsubtypes8(String ohsubtypes8) {
        this.ohsubtypes8 = ohsubtypes8;
    }

    public String getOhsubtypes9() {
        return ohsubtypes9;
    }

    public void setOhsubtypes9(String ohsubtypes9) {
        this.ohsubtypes9 = ohsubtypes9;
    }

    public String getOhsubtypes10() {
        return ohsubtypes10;
    }

    public void setOhsubtypes10(String ohsubtypes10) {
        this.ohsubtypes10 = ohsubtypes10;
    }

    public String getOhsubtypes11() {
        return ohsubtypes11;
    }

    public void setOhsubtypes11(String ohsubtypes11) {
        this.ohsubtypes11 = ohsubtypes11;
    }

    public String getOhsubtypes12() {
        return ohsubtypes12;
    }

    public void setOhsubtypes12(String ohsubtypes12) {
        this.ohsubtypes12 = ohsubtypes12;
    }

    public String getOhsubtypes13() {
        return ohsubtypes13;
    }

    public void setOhsubtypes13(String ohsubtypes13) {
        this.ohsubtypes13 = ohsubtypes13;
    }

    public String getOhsubtypes14() {
        return ohsubtypes14;
    }

    public void setOhsubtypes14(String ohsubtypes14) {
        this.ohsubtypes14 = ohsubtypes14;
    }

    public String getOhsubtypes15() {
        return ohsubtypes15;
    }

    public void setOhsubtypes15(String ohsubtypes15) {
        this.ohsubtypes15 = ohsubtypes15;
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

    /**
     * @return the hospitalname
     */
    public String getHospitalname() {
        return hospitalname;
    }

    /**
     * @param hospitalname the hospitalname to set
     */
    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    /**
     * @return the hospitaladdress
     */
    public String getHospitaladdress() {
        return hospitaladdress;
    }

    /**
     * @param hospitaladdress the hospitaladdress to set
     */
    public void setHospitaladdress(String hospitaladdress) {
        this.hospitaladdress = hospitaladdress;
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
     * @return the habitationname
     */
    public String getHabitationname() {
        return habitationname;
    }

    /**
     * @param habitationname the habitationname to set
     */
    public void setHabitationname(String habitationname) {
        this.habitationname = habitationname;
    }
}
