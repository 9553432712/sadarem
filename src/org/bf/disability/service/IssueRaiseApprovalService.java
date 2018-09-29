/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.IssueRaiseApprovalDTO;
import org.bf.disability.form.IssueRaiseApprovalForm;

/**
 *
 * @author 693461
 */
public interface IssueRaiseApprovalService {

    public ArrayList getCampBasedOnLoginDetails(DataSource ds, String userId, String personCode) throws SADAREMDBException, SQLException;

    public ArrayList getCatgoryDetails(DataSource ds) throws SADAREMDBException, SQLException;

    public String getCampNameDetails(DataSource ds, String campId) throws SADAREMDBException, SQLException;

    public ArrayList getPensionIdDetails(DataSource ds, String loginId, String pensionId, String category) throws SADAREMDBException, SQLException;

    public String districtDetails(DataSource ds, String userId) throws SADAREMDBException, SQLException;

    public int insertIssueRasingDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String campName, String systemIp, String loginId, String districtId, String rationCardNo) throws SADAREMDBException, SQLException;

//    public String msgRequestId(DataSource ds) throws SADAREMDBException,SQLException;
    public String getrequestMaxId(DataSource ds) throws SADAREMDBException, SQLException;

    public ArrayList getApprovalListDetails(DataSource ds, String district_Id, String medicalBoard_Id, String category, String status, String issueId) throws SADAREMDBException, SQLException;

//    public ArrayList getAllResolvedDetails(DataSource ds) throws SADAREMDBException,SQLException;
    public ArrayList getRequestRaisedDetails(DataSource ds, String requestId, String categoryId) throws SADAREMDBException, SQLException;

    // public int updateResolveDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String requestId, String resolveFilePath) throws SADAREMDBException,SQLException;
    public ArrayList getTaskTrackerDetails(DataSource ds, String issueId, IssueRaiseApprovalForm issueRaiseApprovalForm, String loginId) throws SADAREMDBException, SQLException;

    //Mail Body For Email issue Details
    public ArrayList getMailForEmailIssueRaiseBody(DataSource ds, String loginId) throws SADAREMDBException, SQLException;
    //End
    // To List For issue Details

    public ArrayList ToIssueRaiseList(DataSource ds) throws SADAREMDBException, SQLException;
    //End

    //Mail Body For Email Resolve Details
    public IssueRaiseApprovalDTO getMailForEmailIssueResloveBody(DataSource ds, String requestId, String userName) throws SADAREMDBException, SQLException;
    //End

    //To List For Reslove Details
    public ArrayList ToIssueResloveList(DataSource ds, String districtId) throws SADAREMDBException, SQLException;
    //end

    //To List For Reslove Details
    public ArrayList CCIssueResloveList(DataSource ds, String districtId) throws SADAREMDBException, SQLException;

    public int updateResolveDetails(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm, String requestId, String flag, String ip) throws SADAREMDBException, SQLException;

    //end
//    //This method is used for getting Medicalboard Names Based on District
//    public ArrayList getMedicalBoardList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException,SQLException;
    //This method is used for CategoryId  to check SADAREMID
    public int checkCategoryId(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public int validRationCarNo(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    // This method is used for Valid SADARMID or not

    public int validSadaremId(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    // This method is used to check Habcode inputs SADAREMID and PENSION
    public int checkHabCode(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    //This method is used for tocheck SADARAREMID 
    public int checkTechnicalDifficultySADAREMID(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    //This method is used for CategoryId Address Change(PhaseIII) to check SADAREMID
    public int checkAddressChangePhaseIII(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //This method is used to get UserNames based upon District

    public ArrayList getUserNameDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException;

    public String getPasswordDetails(DataSource ds, String userName, String districtId) throws SADAREMDBException, SQLException;
    //To Populate SurName,Name based upon the SADAREMID

    public ArrayList getSurNameDetails(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To Populate RfelationName based upon SADAREMID

    public String getRelationNameDetails(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    // To Populate category RationCard SerialNo.

    public ArrayList getSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException;

    public ArrayList getCampAddress(DataSource ds, String districtId, String hospitalName) throws SADAREMDBException, SQLException;

    //To  Check Category  SADAREMID is Valid for PhaseIII
    public int phasIIIvalidSADAREMID(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To check wheater the SADAREMID is request is raise or not

    public ArrayList getValidCategoryMsg(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To check the Category for NoData

    public String noDataValidation(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    //To check wheater the Pensoion is request is raise or not
    public ArrayList getValidMsg(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To check Wheather the RationCardNumber is request is raise or not

    public ArrayList validRationCardNumber(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To populate List based upon the SADAREM ID

    public ArrayList getbasedUponSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To populate MedicalBoard List or Not based upon the SADAREM ID

    public String validMedicalBoardSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    // To Convert  the PhaseConversion

    public String validPhaseConversion(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException;

    //To validate RationCard No whereateher its valid or not.
    public int rationCardNoValid(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To Based Upon on Category we will Approval Details for Address Change PhaseIII

    public int issueApprovalAddressPhaseIIIDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException;

    //Approval for MedicalBoard
    public int issueApprovalMedicalBoardDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String requestId) throws SADAREMDBException, SQLException;
    //Approval For Name,SurName,Relation Name Change

    public int issueApprovalNameChangeDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To Medical UserName based upon  the District and hospitalName

    public ArrayList medicalBoardUserNameDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException;
    //To be Phase Conversion for PgaeI,PhaseII,PhaseIV

    public int approvalPhaseConversion(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException;
    //To mail Body For Password Details

    public ArrayList getForgetPasswordDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String distirctId) throws SADAREMDBException, SQLException;
    //To mail Body For RationCardAddress Change

    public ArrayList addressChangeRationCardNumber(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To mail Body For PensionId Deatails

    public ArrayList pensionIDForMailDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To Body Common for All Categories.

    public ArrayList toMailBody(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To Populate Categories in Approval And Tracker Screen

    public ArrayList categoryApprovalList(DataSource ds) throws SADAREMDBException, SQLException;

    public String approvalVenueDetails(DataSource ds, String districtId, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public IssueRaiseApprovalDTO pensionPhaseValidation(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException;
    //To valid Viceversa NEW SADAREM ID

    public int vicversaNewSADAREMID(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
    //To Valid RationCardNotOpen in CivilSupplies DB.

    public ArrayList validRationCardNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException;
    //To Populate  RationCardDetails

    public ArrayList getRationCardDetailsForNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String rationCardNo) throws SADAREMDBException, SQLException;
    //To Approval RationCardNoNotOpen Category.

    public int approvalRationCardNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String rationCardNo) throws SADAREMDBException, SQLException;

    public ArrayList getVicerversaPoulateDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String flag) throws SADAREMDBException, SQLException;

    public int updateUserRaisedDocumentPath(DataSource ds, String requestId, String ilePath) throws SADAREMDBException, SQLException;

    public ArrayList getPensionCardNotOpeningList(DataSource ds, String penionId, String districtId) throws SADAREMDBException, SQLException;

    //To populate Medical Username List
    public ArrayList getMedicalBoardUserList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;
// To Populate District Name based upon Civil Suppies.

    public String getDistrictName(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public IssueRaiseApprovalDTO validblankPagesIds(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public int approvalRationCardSerialNo(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public IssueRaiseApprovalDTO getDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    //To check for Address Change PhaseIII for this SADAREMID already
    public int getAppleateCountDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public int approvalForDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public IssueRaiseApprovalDTO validDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public String getRelationTypeChange(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public ArrayList getRelationTypeChangeCompare(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public ArrayList getTypeofDisablity(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public ArrayList getSubDisabilityList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public ArrayList getSub_Sub_DisabilityList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public String getcheckinTypeofDisablity(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public String getDiagnosisofDisability(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException;

    public ArrayList activeSadaremId(DataSource ds, String sadaremId) throws SADAREMDBException, SQLException;

    //balu Start
    public String getAddressChangeValidation(DataSource ds, String sadaremId) throws SADAREMDBException, SQLException;

    public ArrayList getPhaseConversionList(DataSource ds, String pensionId, String distId) throws SADAREMDBException, SQLException;

    public ArrayList getPensionPersonalDetails(DataSource ds, String pensionId, String distId) throws SADAREMDBException, SQLException;
    //balu end
}
