/*
 * ReportDTO.java
 *
 * Created on July 15, 2008, 3:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.bf.disability.dto;
/**
 * This class contains the fields, required to populate the information on
 * certificate
 * @author svsganesh
 * @version 1.0
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author svsganesh
 */
public class ReportDTO implements Serializable  {

    /** Creates a new instance of ReportDTO */
    public ReportDTO() {
    }
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
    private  String congenital;
    private String hereditary;
    private String birthinjury;
    private String duseaseandinfection;
    private String malnutrition;
    private String accident;
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
    private ArrayList districtlist = new ArrayList();
    private ArrayList mandallist = new ArrayList();
    private ArrayList panchayatlist = new ArrayList();
    private ArrayList villagelist = new ArrayList();
    private ArrayList habitationlist = new ArrayList();
    private String campName;
    private String campVenue;
    private String mandalName;
    private String flag;
    private String reporttypeId;
    private String habcode;
    //Start
    private String grandtotalOhAssessed;
    private String grandtotalOhRejected;
    private String grandtotalOhEligible;

    private String grandtotalViAssessed;
    private String grandtotalViRejected;
    private String grandtotalViEligible;
    private String grandtotalHiAssessed;
    private String grandtotalHiRejected;
    private String grandtotalHiEligible;

    private String grandtotalMrAssessed;
    private String grandtotalMrRejected;
    private String grandtotalMrEligible;

    private String grandtotalMdAssessed;
    private String grandtotalMdRejected;
    private String grandtotalMdEligible;
//RejectedCM
    private String grandtotalMiAssessed;
    private String grandtotalMiRejected;
    private String grandtotalMiEligible;

    private String totalAssessedDis;
    private String totalEligible;
    private String totalRejected;
    
    private int totalAssessmentDisability;
    private int totalEligibleDisability;
    private int totalRejectedDisability;

    public int getTotalAssessmentDisability() {
        return totalAssessmentDisability;
    }

    public void setTotalAssessmentDisability(int totalAssessmentDisability) {
        this.totalAssessmentDisability = totalAssessmentDisability;
    }

    public int getTotalEligibleDisability() {
        return totalEligibleDisability;
    }

    public void setTotalEligibleDisability(int totalEligibleDisability) {
        this.totalEligibleDisability = totalEligibleDisability;
    }

    public int getTotalRejectedDisability() {
        return totalRejectedDisability;
    }

    public void setTotalRejectedDisability(int totalRejectedDisability) {
        this.totalRejectedDisability = totalRejectedDisability;
    }

   
    public String getTotalAssessedDis() {
        return totalAssessedDis;
    }

    public void setTotalAssessedDis(String totalAssessedDis) {
        this.totalAssessedDis = totalAssessedDis;
    }

    public String getTotalEligible() {
        return totalEligible;
    }

    public void setTotalEligible(String totalEligible) {
        this.totalEligible = totalEligible;
    }

    public String getTotalRejected() {
        return totalRejected;
    }

    public void setTotalRejected(String totalRejected) {
        this.totalRejected = totalRejected;
    }

    private String grandTotalShowDifference;

    private BigDecimal grandTotalCertificate;

    public BigDecimal getGrandTotalCertificate() {
        return grandTotalCertificate;
    }

    public void setGrandTotalCertificate(BigDecimal grandTotalCertificate) {
        this.grandTotalCertificate = grandTotalCertificate;
    }

    public String getGrandTotalShowDifference() {
        return grandTotalShowDifference;
    }

    public void setGrandTotalShowDifference(String grandTotalShowDifference) {
        this.grandTotalShowDifference = grandTotalShowDifference;
    }



    public String getGrandtotalHiAssessed() {
        return grandtotalHiAssessed;
    }

    public void setGrandtotalHiAssessed(String grandtotalHiAssessed) {
        this.grandtotalHiAssessed = grandtotalHiAssessed;
    }

    public String getGrandtotalHiEligible() {
        return grandtotalHiEligible;
    }

    public void setGrandtotalHiEligible(String grandtotalHiEligible) {
        this.grandtotalHiEligible = grandtotalHiEligible;
    }

    public String getGrandtotalHiRejected() {
        return grandtotalHiRejected;
    }

    public void setGrandtotalHiRejected(String grandtotalHiRejected) {
        this.grandtotalHiRejected = grandtotalHiRejected;
    }

    public String getGrandtotalMdAssessed() {
        return grandtotalMdAssessed;
    }

    public void setGrandtotalMdAssessed(String grandtotalMdAssessed) {
        this.grandtotalMdAssessed = grandtotalMdAssessed;
    }

    public String getGrandtotalMdEligible() {
        return grandtotalMdEligible;
    }

    public void setGrandtotalMdEligible(String grandtotalMdEligible) {
        this.grandtotalMdEligible = grandtotalMdEligible;
    }

    public String getGrandtotalMdRejected() {
        return grandtotalMdRejected;
    }

    public void setGrandtotalMdRejected(String grandtotalMdRejected) {
        this.grandtotalMdRejected = grandtotalMdRejected;
    }

    public String getGrandtotalMiAssessed() {
        return grandtotalMiAssessed;
    }

    public void setGrandtotalMiAssessed(String grandtotalMiAssessed) {
        this.grandtotalMiAssessed = grandtotalMiAssessed;
    }

    public String getGrandtotalMiEligible() {
        return grandtotalMiEligible;
    }

    public void setGrandtotalMiEligible(String grandtotalMiEligible) {
        this.grandtotalMiEligible = grandtotalMiEligible;
    }

    public String getGrandtotalMiRejected() {
        return grandtotalMiRejected;
    }

    public void setGrandtotalMiRejected(String grandtotalMiRejected) {
        this.grandtotalMiRejected = grandtotalMiRejected;
    }

    public String getGrandtotalMrAssessed() {
        return grandtotalMrAssessed;
    }

    public void setGrandtotalMrAssessed(String grandtotalMrAssessed) {
        this.grandtotalMrAssessed = grandtotalMrAssessed;
    }

    public String getGrandtotalMrEligible() {
        return grandtotalMrEligible;
    }

    public void setGrandtotalMrEligible(String grandtotalMrEligible) {
        this.grandtotalMrEligible = grandtotalMrEligible;
    }

    public String getGrandtotalMrRejected() {
        return grandtotalMrRejected;
    }

    public void setGrandtotalMrRejected(String grandtotalMrRejected) {
        this.grandtotalMrRejected = grandtotalMrRejected;
    }

    public String getGrandtotalOhAssessed() {
        return grandtotalOhAssessed;
    }

    public void setGrandtotalOhAssessed(String grandtotalOhAssessed) {
        this.grandtotalOhAssessed = grandtotalOhAssessed;
    }

    public String getGrandtotalOhEligible() {
        return grandtotalOhEligible;
    }

    public void setGrandtotalOhEligible(String grandtotalOhEligible) {
        this.grandtotalOhEligible = grandtotalOhEligible;
    }

    public String getGrandtotalOhRejected() {
        return grandtotalOhRejected;
    }

    public void setGrandtotalOhRejected(String grandtotalOhRejected) {
        this.grandtotalOhRejected = grandtotalOhRejected;
    }

    public String getGrandtotalViAssessed() {
        return grandtotalViAssessed;
    }

    public void setGrandtotalViAssessed(String grandtotalViAssessed) {
        this.grandtotalViAssessed = grandtotalViAssessed;
    }

    public String getGrandtotalViEligible() {
        return grandtotalViEligible;
    }

    public void setGrandtotalViEligible(String grandtotalViEligible) {
        this.grandtotalViEligible = grandtotalViEligible;
    }

    public String getGrandtotalViRejected() {
        return grandtotalViRejected;
    }

    public void setGrandtotalViRejected(String grandtotalViRejected) {
        this.grandtotalViRejected = grandtotalViRejected;
    }


    public String getHabcode() {
        return habcode;
    }

    public void setHabcode(String habcode) {
        this.habcode = habcode;
    }

    public String getReporttypeId() {
        return reporttypeId;
    }

    public void setReporttypeId(String reporttypeId) {
        this.reporttypeId = reporttypeId;
    }
    


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampVenue() {
        return campVenue;
    }

    public void setCampVenue(String campVenue) {
        this.campVenue = campVenue;
    }

    public String getMandalName() {
        return mandalName;
    }

    public void setMandalName(String mandalName) {
        this.mandalName = mandalName;
    }


     private String year;
    private String month;
    private String fromdate;
    private String todate;
    private String financialYear;
    private String mode;
    private String typeOfSearch;
    private String reportType;
    private String campId;
    private int totalApplied;
    private int totalAssessed;
    private int rejected;
    private int eligible;
    private int oh;
    private int vi;
    private int hi;
    private int mr;
    private int mi;
    private int md;
    private int total;
//    private String flag;
//    private String typeOfSearch;
//    private String reportType;
//    private String campName;
//    private String campVenue;
//    private String mandalName;
//    private String year;
//    private String month;
//    private String fromdate;
//    private String todate;
//    private String financialYear;
//    private String mode;
//    private String campId;
    private String totalAppliedCM;
    private String totalAssessedCM;
    private String rejectedCM;
    private String eligibleCM;
    private String eligiblebelow80;
    private String eligibleabove80;
    private String ohCM;
    private String viCM;
    private String hiCM;
    private String mrCM;
    private String miCM;
    private String mdCM;
    private String totalCM;
    private String grandtotalAppliedCM;
    private String grandtotalAssessedCM;
    private String totalrejectedCM;
    private String totaleligibleCM;
    private String totaleligiblebelow80;
    private String totaleligibleabove80;
    private String totalohCM;
    private String totalviCM;
    private String totalhiCM;
    private String totalmrCM;
    private String totalmiCM;
    private String totalmdCM;
    private String totaltotalCM;
    private BigDecimal certificatePercentage;
    private float totalCertificatePercentage;
    private String showDifference;
    private String ohassessed;
    private String viassessed;
    private String hiassessed;
    private String mrassessed;
    private String miassessed;
    private String mdassessed;
    private String ohrejected;
    private String virejected;
    private String hirejected;
    private String mrrejected;
    private String mirejected;
    private String mdrejected;
    private String oheligible;
    private String vieligible;
    private String hieligible;
    private String mreligible;
    private String mieligible;
    private String mdeligible;
    private int totalOHassessed;
    private int totalVIassessed;
    private int totalHIassessed;
    private int totalMRassessed;
    private int totalMIassessed;
    private int totalMDassessed;
    private int totalOHeligible;
    private int totalVIeligible;
    private int totalHIeligible;
    private int totalMReligible;
    private int totalMIeligible;
    private int totalMDeligible;
    private int totalOHrejected;
    private int totalVIrejected;
    private int totalHIrejected;
    private int totalMRrejected;
    private int totalMIrejected;
    private int totalMDrejected;
    private String address;
    private String disabilityId;
    private BigDecimal assessedPercentage;
    private BigDecimal rejectedPercentage;
    private BigDecimal eligiblePercentage;
    private BigDecimal eligiblebelow80Percentage;
    private BigDecimal eligibleabove80Percentage;
    private BigDecimal pensionerPercentage;
    private BigDecimal totalassessedPercentage;
    private BigDecimal totalrejectedPercentage;
    private BigDecimal totaleligiblePercentage;
    private BigDecimal totaleligiblebelow80Percentage;
    private BigDecimal totaleligibleabove80Percentage;
    private BigDecimal totalpensionerPercentage;
   

    public BigDecimal getCertificatePercentage() {
        return certificatePercentage;
    }

    public void setCertificatePercentage(BigDecimal certificatePercentage) {
        this.certificatePercentage = certificatePercentage;
    }

  
    public float getTotalCertificatePercentage() {
        return totalCertificatePercentage;
    }

    public void setTotalCertificatePercentage(float totalCertificatePercentage) {
        this.totalCertificatePercentage = totalCertificatePercentage;
    }

    


    public BigDecimal getAssessedPercentage() {
        return assessedPercentage;
    }

    public void setAssessedPercentage(BigDecimal assessedPercentage) {
        this.assessedPercentage = assessedPercentage;
    }

    public BigDecimal getEligiblePercentage() {
        return eligiblePercentage;
    }

    public void setEligiblePercentage(BigDecimal eligiblePercentage) {
        this.eligiblePercentage = eligiblePercentage;
    }

    public BigDecimal getEligibleabove80Percentage() {
        return eligibleabove80Percentage;
    }

    public void setEligibleabove80Percentage(BigDecimal eligibleabove80Percentage) {
        this.eligibleabove80Percentage = eligibleabove80Percentage;
    }

    public BigDecimal getEligiblebelow80Percentage() {
        return eligiblebelow80Percentage;
    }

    public void setEligiblebelow80Percentage(BigDecimal eligiblebelow80Percentage) {
        this.eligiblebelow80Percentage = eligiblebelow80Percentage;
    }

    public BigDecimal getPensionerPercentage() {
        return pensionerPercentage;
    }

    public void setPensionerPercentage(BigDecimal pensionerPercentage) {
        this.pensionerPercentage = pensionerPercentage;
    }

    public BigDecimal getRejectedPercentage() {
        return rejectedPercentage;
    }

    public void setRejectedPercentage(BigDecimal rejectedPercentage) {
        this.rejectedPercentage = rejectedPercentage;
    }

    public BigDecimal getTotalassessedPercentage() {
        return totalassessedPercentage;
    }

    public void setTotalassessedPercentage(BigDecimal totalassessedPercentage) {
        this.totalassessedPercentage = totalassessedPercentage;
    }

    public BigDecimal getTotaleligiblePercentage() {
        return totaleligiblePercentage;
    }

    public void setTotaleligiblePercentage(BigDecimal totaleligiblePercentage) {
        this.totaleligiblePercentage = totaleligiblePercentage;
    }

    public BigDecimal getTotaleligibleabove80Percentage() {
        return totaleligibleabove80Percentage;
    }

    public void setTotaleligibleabove80Percentage(BigDecimal totaleligibleabove80Percentage) {
        this.totaleligibleabove80Percentage = totaleligibleabove80Percentage;
    }

    public BigDecimal getTotaleligiblebelow80Percentage() {
        return totaleligiblebelow80Percentage;
    }

    public void setTotaleligiblebelow80Percentage(BigDecimal totaleligiblebelow80Percentage) {
        this.totaleligiblebelow80Percentage = totaleligiblebelow80Percentage;
    }

    public BigDecimal getTotalpensionerPercentage() {
        return totalpensionerPercentage;
    }

    public void setTotalpensionerPercentage(BigDecimal totalpensionerPercentage) {
        this.totalpensionerPercentage = totalpensionerPercentage;
    }

    public BigDecimal getTotalrejectedPercentage() {
        return totalrejectedPercentage;
    }

    public void setTotalrejectedPercentage(BigDecimal totalrejectedPercentage) {
        this.totalrejectedPercentage = totalrejectedPercentage;
    }

    public String getEligibleabove80() {
        return eligibleabove80;
    }

    public void setEligibleabove80(String eligibleabove80) {
        this.eligibleabove80 = eligibleabove80;
    }

    public String getEligiblebelow80() {
        return eligiblebelow80;
    }

    public void setEligiblebelow80(String eligiblebelow80) {
        this.eligiblebelow80 = eligiblebelow80;
    }

    public String getTotaleligibleabove80() {
        return totaleligibleabove80;
    }

    public void setTotaleligibleabove80(String totaleligibleabove80) {
        this.totaleligibleabove80 = totaleligibleabove80;
    }

    public String getTotaleligiblebelow80() {
        return totaleligiblebelow80;
    }

    public void setTotaleligiblebelow80(String totaleligiblebelow80) {
        this.totaleligiblebelow80 = totaleligiblebelow80;
    }

    public String getDisabilityId() {
        return disabilityId;
    }

    public void setDisabilityId(String disabilityId) {
        this.disabilityId = disabilityId;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
      
    public int getTotalHIassessed() {
        return totalHIassessed;
    }

    public void setTotalHIassessed(int totalHIassessed) {
        this.totalHIassessed = totalHIassessed;
    }

    public int getTotalHIeligible() {
        return totalHIeligible;
    }

    public void setTotalHIeligible(int totalHIeligible) {
        this.totalHIeligible = totalHIeligible;
    }

    public int getTotalHIrejected() {
        return totalHIrejected;
    }

    public void setTotalHIrejected(int totalHIrejected) {
        this.totalHIrejected = totalHIrejected;
    }

    public int getTotalMDassessed() {
        return totalMDassessed;
    }

    public void setTotalMDassessed(int totalMDassessed) {
        this.totalMDassessed = totalMDassessed;
    }

    public int getTotalMDeligible() {
        return totalMDeligible;
    }

    public void setTotalMDeligible(int totalMDeligible) {
        this.totalMDeligible = totalMDeligible;
    }

    public int getTotalMDrejected() {
        return totalMDrejected;
    }

    public void setTotalMDrejected(int totalMDrejected) {
        this.totalMDrejected = totalMDrejected;
    }

    public int getTotalMIassessed() {
        return totalMIassessed;
    }

    public void setTotalMIassessed(int totalMIassessed) {
        this.totalMIassessed = totalMIassessed;
    }

    public int getTotalMIeligible() {
        return totalMIeligible;
    }

    public void setTotalMIeligible(int totalMIeligible) {
        this.totalMIeligible = totalMIeligible;
    }

    public int getTotalMIrejected() {
        return totalMIrejected;
    }

    public void setTotalMIrejected(int totalMIrejected) {
        this.totalMIrejected = totalMIrejected;
    }

    public int getTotalMRassessed() {
        return totalMRassessed;
    }

    public void setTotalMRassessed(int totalMRassessed) {
        this.totalMRassessed = totalMRassessed;
    }

    public int getTotalMReligible() {
        return totalMReligible;
    }

    public void setTotalMReligible(int totalMReligible) {
        this.totalMReligible = totalMReligible;
    }

    public int getTotalMRrejected() {
        return totalMRrejected;
    }

    public void setTotalMRrejected(int totalMRrejected) {
        this.totalMRrejected = totalMRrejected;
    }

    public int getTotalOHassessed() {
        return totalOHassessed;
    }

    public void setTotalOHassessed(int totalOHassessed) {
        this.totalOHassessed = totalOHassessed;
    }

    public int getTotalOHeligible() {
        return totalOHeligible;
    }

    public void setTotalOHeligible(int totalOHeligible) {
        this.totalOHeligible = totalOHeligible;
    }

    public int getTotalOHrejected() {
        return totalOHrejected;
    }

    public void setTotalOHrejected(int totalOHrejected) {
        this.totalOHrejected = totalOHrejected;
    }

    public int getTotalVIassessed() {
        return totalVIassessed;
    }

    public void setTotalVIassessed(int totalVIassessed) {
        this.totalVIassessed = totalVIassessed;
    }

    public int getTotalVIeligible() {
        return totalVIeligible;
    }

    public void setTotalVIeligible(int totalVIeligible) {
        this.totalVIeligible = totalVIeligible;
    }

    public int getTotalVIrejected() {
        return totalVIrejected;
    }

    public void setTotalVIrejected(int totalVIrejected) {
        this.totalVIrejected = totalVIrejected;
    }

 

    public String getHiassessed() {
        return hiassessed;
    }

    public void setHiassessed(String hiassessed) {
        this.hiassessed = hiassessed;
    }

    public String getHieligible() {
        return hieligible;
    }

    public void setHieligible(String hieligible) {
        this.hieligible = hieligible;
    }

    public String getHirejected() {
        return hirejected;
    }

    public void setHirejected(String hirejected) {
        this.hirejected = hirejected;
    }

    public String getMdassessed() {
        return mdassessed;
    }

    public void setMdassessed(String mdassessed) {
        this.mdassessed = mdassessed;
    }

    public String getMdeligible() {
        return mdeligible;
    }

    public void setMdeligible(String mdeligible) {
        this.mdeligible = mdeligible;
    }

    public String getMdrejected() {
        return mdrejected;
    }

    public void setMdrejected(String mdrejected) {
        this.mdrejected = mdrejected;
    }

    public String getMiassessed() {
        return miassessed;
    }

    public void setMiassessed(String miassessed) {
        this.miassessed = miassessed;
    }

    public String getMieligible() {
        return mieligible;
    }

    public void setMieligible(String mieligible) {
        this.mieligible = mieligible;
    }

    public String getMirejected() {
        return mirejected;
    }

    public void setMirejected(String mirejected) {
        this.mirejected = mirejected;
    }

    public String getMrassessed() {
        return mrassessed;
    }

    public void setMrassessed(String mrassessed) {
        this.mrassessed = mrassessed;
    }

    public String getMreligible() {
        return mreligible;
    }

    public void setMreligible(String mreligible) {
        this.mreligible = mreligible;
    }

    public String getMrrejected() {
        return mrrejected;
    }

    public void setMrrejected(String mrrejected) {
        this.mrrejected = mrrejected;
    }

    public String getOhassessed() {
        return ohassessed;
    }

    public void setOhassessed(String ohassessed) {
        this.ohassessed = ohassessed;
    }

    public String getOheligible() {
        return oheligible;
    }

    public void setOheligible(String oheligible) {
        this.oheligible = oheligible;
    }

    public String getOhrejected() {
        return ohrejected;
    }

    public void setOhrejected(String ohrejected) {
        this.ohrejected = ohrejected;
    }

    public String getViassessed() {
        return viassessed;
    }

    public void setViassessed(String viassessed) {
        this.viassessed = viassessed;
    }

    public String getVieligible() {
        return vieligible;
    }

    public void setVieligible(String vieligible) {
        this.vieligible = vieligible;
    }

    public String getVirejected() {
        return virejected;
    }

    public void setVirejected(String virejected) {
        this.virejected = virejected;
    }

    public String getShowDifference() {
        return showDifference;
    }

    public void setShowDifference(String showDifference) {
        this.showDifference = showDifference;
    }

   

    public void setTotalCertificatePercentage(int totalCertificatePercentage) {
        this.totalCertificatePercentage = totalCertificatePercentage;
    }

    public String getGrandtotalAppliedCM() {
        return grandtotalAppliedCM;
    }

    public void setGrandtotalAppliedCM(String grandtotalAppliedCM) {
        this.grandtotalAppliedCM = grandtotalAppliedCM;
    }

    public String getGrandtotalAssessedCM() {
        return grandtotalAssessedCM;
    }

    public void setGrandtotalAssessedCM(String grandtotalAssessedCM) {
        this.grandtotalAssessedCM = grandtotalAssessedCM;
    }

    public String getTotaleligibleCM() {
        return totaleligibleCM;
    }

    public void setTotaleligibleCM(String totaleligibleCM) {
        this.totaleligibleCM = totaleligibleCM;
    }

    public String getTotalhiCM() {
        return totalhiCM;
    }

    public void setTotalhiCM(String totalhiCM) {
        this.totalhiCM = totalhiCM;
    }

    public String getTotalmdCM() {
        return totalmdCM;
    }

    public void setTotalmdCM(String totalmdCM) {
        this.totalmdCM = totalmdCM;
    }

    public String getTotalmiCM() {
        return totalmiCM;
    }

    public void setTotalmiCM(String totalmiCM) {
        this.totalmiCM = totalmiCM;
    }

    public String getTotalmrCM() {
        return totalmrCM;
    }

    public void setTotalmrCM(String totalmrCM) {
        this.totalmrCM = totalmrCM;
    }

    public String getTotalohCM() {
        return totalohCM;
    }

    public void setTotalohCM(String totalohCM) {
        this.totalohCM = totalohCM;
    }

    public String getTotalrejectedCM() {
        return totalrejectedCM;
    }

    public void setTotalrejectedCM(String totalrejectedCM) {
        this.totalrejectedCM = totalrejectedCM;
    }

    public String getTotaltotalCM() {
        return totaltotalCM;
    }

    public void setTotaltotalCM(String totaltotalCM) {
        this.totaltotalCM = totaltotalCM;
    }

    public String getTotalviCM() {
        return totalviCM;
    }

    public void setTotalviCM(String totalviCM) {
        this.totalviCM = totalviCM;
    }

    public String getEligibleCM() {
        return eligibleCM;
    }

    public void setEligibleCM(String eligibleCM) {
        this.eligibleCM = eligibleCM;
    }

    public String getHiCM() {
        return hiCM;
    }

    public void setHiCM(String hiCM) {
        this.hiCM = hiCM;
    }

    public String getMdCM() {
        return mdCM;
    }

    public void setMdCM(String mdCM) {
        this.mdCM = mdCM;
    }

    public String getMiCM() {
        return miCM;
    }

    public void setMiCM(String miCM) {
        this.miCM = miCM;
    }

    public String getMrCM() {
        return mrCM;
    }

    public void setMrCM(String mrCM) {
        this.mrCM = mrCM;
    }

    public String getOhCM() {
        return ohCM;
    }

    public void setOhCM(String ohCM) {
        this.ohCM = ohCM;
    }

    public String getRejectedCM() {
        return rejectedCM;
    }

    public void setRejectedCM(String rejectedCM) {
        this.rejectedCM = rejectedCM;
    }

    public String getTotalAppliedCM() {
        return totalAppliedCM;
    }

    public void setTotalAppliedCM(String totalAppliedCM) {
        this.totalAppliedCM = totalAppliedCM;
    }

    public String getTotalAssessedCM() {
        return totalAssessedCM;
    }

    public void setTotalAssessedCM(String totalAssessedCM) {
        this.totalAssessedCM = totalAssessedCM;
    }

    public String getTotalCM() {
        return totalCM;
    }

    public void setTotalCM(String totalCM) {
        this.totalCM = totalCM;
    }

    public String getViCM() {
        return viCM;
    }

    public void setViCM(String viCM) {
        this.viCM = viCM;
    }

//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }

    public int getEligible() {
        return eligible;
    }

    public void setEligible(int eligible) {
        this.eligible = eligible;
    }

    public int getHi() {
        return hi;
    }

    public void setHi(int hi) {
        this.hi = hi;
    }

    public int getMd() {
        return md;
    }

    public void setMd(int md) {
        this.md = md;
    }

    public int getMi() {
        return mi;
    }

    public void setMi(int mi) {
        this.mi = mi;
    }

    public int getMr() {
        return mr;
    }

    public void setMr(int mr) {
        this.mr = mr;
    }

    public int getOh() {
        return oh;
    }

    public void setOh(int oh) {
        this.oh = oh;
    }

    public int getRejected() {
        return rejected;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalApplied() {
        return totalApplied;
    }

    public void setTotalApplied(int totalApplied) {
        this.totalApplied = totalApplied;
    }

    public int getTotalAssessed() {
        return totalAssessed;
    }

    public void setTotalAssessed(int totalAssessed) {
        this.totalAssessed = totalAssessed;
    }

    public int getVi() {
        return vi;
    }

    public void setVi(int vi) {
        this.vi = vi;
    }


    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getTypeOfSearch() {
        return typeOfSearch;
    }

    public void setTypeOfSearch(String typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
