/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.IssueRaiseApprovalDAO;
import org.bf.disability.dto.IssueRaiseApprovalDTO;
import org.bf.disability.form.IssueRaiseApprovalForm;
import org.bf.disability.service.IssueRaiseApprovalService;

/**
 *
 * @author 693461
 */
public class IssueRaiseApprovalServiceImpl implements IssueRaiseApprovalService {

    public ArrayList getCampBasedOnLoginDetails(DataSource ds, String userId, String personCode) throws SADAREMDBException, SQLException {

        ArrayList campList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {

            campList = issueRaiseApprovalDAO.getCampBasedOnLoginDetails(ds, userId, personCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campList;
    }

    public ArrayList getCatgoryDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList catgoryList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            catgoryList = issueRaiseApprovalDAO.getCatgoryDetails(ds);

        } catch (Exception e) {
            e.printStackTrace();


        }
        return catgoryList;
    }

    public String getCampNameDetails(DataSource ds, String campId) throws SADAREMDBException, SQLException {

        String campList = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            campList = issueRaiseApprovalDAO.getCampNameDetails(ds, campId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campList;
    }

    public int insertIssueRasingDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String campName, String systemIp, String loginId, String districtId, String rationCardNo) throws SADAREMDBException, SQLException {

        int raisingDetails = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            raisingDetails = issueRaiseApprovalDAO.insertIssueRasingDetails(ds, issueRaiseApprovalForm, campName, systemIp, loginId, districtId, rationCardNo);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return raisingDetails;
    }

    public String getrequestMaxId(DataSource ds) throws SADAREMDBException, SQLException {

        String requestMaxId = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            requestMaxId = issueRaiseApprovalDAO.getrequestMaxId(ds);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return requestMaxId;

    }

    public ArrayList getApprovalListDetails(DataSource ds, String district_Id, String medicalBoard_Id, String category, String status, String issueId) throws SADAREMDBException, SQLException {

        ArrayList resolveList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            resolveList = issueRaiseApprovalDAO.getApprovalListDetails(ds, district_Id, category, status, issueId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resolveList;

    }

//    public ArrayList getAllResolvedDetails(DataSource ds) throws SADAREMDBException,SQLException {
//
//        ArrayList resolveList = new ArrayList();
//        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
//
//        try {
//            resolveList = issueRaiseApprovalDAO.getAllResolvedDetails(ds);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return resolveList;
//
//    }
    public ArrayList getRequestRaisedDetails(DataSource ds, String requestId, String categoryId) throws SADAREMDBException, SQLException {

        ArrayList resolveList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            resolveList = issueRaiseApprovalDAO.getRequestRaisedDetails(ds, requestId, categoryId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resolveList;
    }

    public ArrayList getPensionIdDetails(DataSource ds, String loginId, String pensionId, String category) throws SADAREMDBException, SQLException {

        ArrayList pensionDetails = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            pensionDetails = issueRaiseApprovalDAO.getPensionIdDetails(ds, loginId, pensionId, category);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pensionDetails;


    }

    public ArrayList getMailForEmailIssueRaiseBody(DataSource ds, String loginId) throws SADAREMDBException, SQLException {

        ArrayList issueMailBody = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            issueMailBody = issueRaiseApprovalDAO.getMailForEmailIssueRaiseBody(ds, loginId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return issueMailBody;

    }

    public ArrayList ToIssueRaiseList(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList raiseList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            raiseList = issueRaiseApprovalDAO.ToIssueRaiseList(ds);

        } catch (Exception e) {

            e.printStackTrace();
        }


        return raiseList;

    }

    public IssueRaiseApprovalDTO getMailForEmailIssueResloveBody(DataSource ds, String requestId, String userName) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDTO issueRaiseApprovalDTO = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            issueRaiseApprovalDTO = issueRaiseApprovalDAO.getMailForEmailIssueResloveBody(ds, requestId, userName);

        } catch (Exception e) {

            e.printStackTrace();
        }


        return issueRaiseApprovalDTO;

    }

//    public String msgRequestId(DataSource ds) throws SADAREMDBException,SQLException {
//
//        String msgRequestId = null;
//        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
//
//
//        try {
//            msgRequestId = issueRaiseApprovalDAO.msgRequestId(ds);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return msgRequestId;
//
//    }
    public String districtDetails(DataSource ds, String userId) throws SADAREMDBException, SQLException {
        String districtDetails = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            // districtDetails = issueRaiseApprovalDAO.districtDetails(ds, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return districtDetails;

    }

    public ArrayList ToIssueResloveList(DataSource ds, String districtId) throws SADAREMDBException, SQLException {

        ArrayList issueResloveList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();


        try {
            issueResloveList = issueRaiseApprovalDAO.ToIssueResloveList(ds, districtId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return issueResloveList;
    }

    public ArrayList CCIssueResloveList(DataSource ds, String districtId) throws SADAREMDBException, SQLException {

        ArrayList ccIssueResloveList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            ccIssueResloveList = issueRaiseApprovalDAO.CCIssueResloveList(ds, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ccIssueResloveList;
    }

    public ArrayList getTaskTrackerDetails(DataSource ds, String issueId, IssueRaiseApprovalForm issueRaiseApprovalForm, String loginId) throws SADAREMDBException, SQLException {

        ArrayList taskTrackerList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            taskTrackerList = issueRaiseApprovalDAO.getTaskTrackerDetails(ds, issueId, issueRaiseApprovalForm, loginId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskTrackerList;


    }

    public int updateResolveDetails(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm, String requestId, String flag, String ip) throws SADAREMDBException, SQLException {
        int resolveDetails = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            resolveDetails = issueRaiseApprovalDAO.updateResolveDetails(ds, sueRaiseApprovalForm, requestId, flag, ip);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resolveDetails;
    }

    public int checkAddressChangePhaseIII(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int checkAddressChangePhaseIII = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            checkAddressChangePhaseIII = issueRaiseApprovalDAO.checkAddressChangePhaseIII(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkAddressChangePhaseIII;


    }

    public int checkCategoryId(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int checkCategoryId = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            checkCategoryId = issueRaiseApprovalDAO.checkCategoryId(ds, sueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkCategoryId;
    }

    public int validSadaremId(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int validSadaremId = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            validSadaremId = issueRaiseApprovalDAO.validSadaremId(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return validSadaremId;
    }

    public int validRationCarNo(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int validRationCarNo = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            //  validRationCarNo = issueRaiseApprovalDAO.validRationCarNo(ds, issueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validRationCarNo;
    }

    public int checkHabCode(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int checkHabCode = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            // checkHabCode = issueRaiseApprovalDAO.checkHabCode(ds, issueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkHabCode;



    }

    public int checkTechnicalDifficultySADAREMID(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int checkTechnicalDifficultySADAREMID = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            checkTechnicalDifficultySADAREMID = issueRaiseApprovalDAO.checkTechnicalDifficultySADAREMID(ds, sueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkTechnicalDifficultySADAREMID;
    }

    public ArrayList getUserNameDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException {

        ArrayList userNameList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            userNameList = issueRaiseApprovalDAO.getUserNameDetails(ds, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userNameList;
    }

    public String getPasswordDetails(DataSource ds, String userName, String districtId) throws SADAREMDBException, SQLException {
        String passwordDetails = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            passwordDetails = issueRaiseApprovalDAO.getPasswordDetails(ds, userName, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return passwordDetails;
    }

    public ArrayList getSurNameDetails(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        ArrayList surNameDetails = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            surNameDetails = issueRaiseApprovalDAO.getSurNameDetails(ds, sueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return surNameDetails;
    }

    public String getRelationNameDetails(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        String relationNameDetails = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            relationNameDetails = issueRaiseApprovalDAO.getRelationNameDetails(ds, sueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relationNameDetails;
    }

    public ArrayList getSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm sueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {

        ArrayList sadaremList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            sadaremList = issueRaiseApprovalDAO.getSADAREMIDDetails(ds, sueRaiseApprovalForm, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sadaremList;
    }

    public ArrayList getCampAddress(DataSource ds, String districtId, String hospitalName) throws SADAREMDBException, SQLException {

        ArrayList campAddress = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            campAddress = issueRaiseApprovalDAO.getCampAddress(ds, districtId, hospitalName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return campAddress;

    }

    public int phasIIIvalidSADAREMID(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int phasIIIvalidSADAREMID = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            phasIIIvalidSADAREMID = issueRaiseApprovalDAO.phasIIIvalidSADAREMID(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return phasIIIvalidSADAREMID;
    }

    public ArrayList getValidCategoryMsg(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList validationMsg = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            validationMsg = issueRaiseApprovalDAO.getValidCategoryMsg(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return validationMsg;

    }

    public String noDataValidation(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        String noDataValidation = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            noDataValidation = issueRaiseApprovalDAO.noDataValidation(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return noDataValidation;

    }

    public ArrayList getValidMsg(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList pensionValidMsg = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            pensionValidMsg = issueRaiseApprovalDAO.getValidMsg(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pensionValidMsg;

    }

    public ArrayList validRationCardNumber(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList validRationCardNumber = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            // validRationCardNumber = issueRaiseApprovalDAO.validRationCardNumber(ds,issueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validRationCardNumber;
    }

    public ArrayList getbasedUponSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList basedUponSADAREMIDDetails = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            basedUponSADAREMIDDetails = issueRaiseApprovalDAO.getbasedUponSADAREMIDDetails(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return basedUponSADAREMIDDetails;


    }

    public String validMedicalBoardSADAREMIDDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        String validMedicalBoardSADAREMIDDetails = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            validMedicalBoardSADAREMIDDetails = issueRaiseApprovalDAO.validMedicalBoardSADAREMIDDetails(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return validMedicalBoardSADAREMIDDetails;
    }

    public String validPhaseConversion(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        String validPhaseConversion = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            validPhaseConversion = issueRaiseApprovalDAO.validPhaseConversion(ds, issueRaiseApprovalForm, districtId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validPhaseConversion;
    }

    public int issueApprovalAddressPhaseIIIDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        int issueApprovalDetails = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            issueApprovalDetails = issueRaiseApprovalDAO.issueApprovalAddressPhaseIIIDetails(ds, issueRaiseApprovalForm, districtId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issueApprovalDetails;
    }

    public int rationCardNoValid(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int rationCardNoValid = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            rationCardNoValid = issueRaiseApprovalDAO.rationCardNoValid(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rationCardNoValid;
    }

    public int issueApprovalMedicalBoardDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String requestId) throws SADAREMDBException, SQLException {
        int medicalBoardApprovalDetails = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            medicalBoardApprovalDetails = issueRaiseApprovalDAO.issueApprovalMedicalBoardDetails(ds, issueRaiseApprovalForm, requestId);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicalBoardApprovalDetails;
    }

    public int issueApprovalNameChangeDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int approvalnameDetails = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            approvalnameDetails = issueRaiseApprovalDAO.issueApprovalNameChangeDetails(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return approvalnameDetails;
    }

    public ArrayList medicalBoardUserNameDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        ArrayList medicalBoardList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            //  medicalBoardList = issueRaiseApprovalDAO.medicalBoardUserNameDetails(ds, issueRaiseApprovalForm, districtId);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return medicalBoardList;

    }

    public int approvalPhaseConversion(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        int approvalPhaseConversion = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            approvalPhaseConversion = issueRaiseApprovalDAO.approvalPhaseConversion(ds, issueRaiseApprovalForm, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvalPhaseConversion;
    }

    public ArrayList getForgetPasswordDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String distirctId) throws SADAREMDBException, SQLException {

        ArrayList forgetPasswordDEtails = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            forgetPasswordDEtails = issueRaiseApprovalDAO.getForgetPasswordDetails(ds, issueRaiseApprovalForm, distirctId);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return forgetPasswordDEtails;
    }

    public ArrayList addressChangeRationCardNumber(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList addressChangesRationCardNo = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            addressChangesRationCardNo = issueRaiseApprovalDAO.addressChangeRationCardNumber(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addressChangesRationCardNo;

    }

    public ArrayList pensionIDForMailDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList pensionIDForMailDetails = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            pensionIDForMailDetails = issueRaiseApprovalDAO.pensionIDForMailDetails(ds, issueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pensionIDForMailDetails;

    }

    public ArrayList toMailBody(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        ArrayList toMailBody = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            toMailBody = issueRaiseApprovalDAO.toMailBody(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return toMailBody;

    }

    public ArrayList categoryApprovalList(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList categoryApprovalList = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            categoryApprovalList = issueRaiseApprovalDAO.categoryApprovalList(ds);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryApprovalList;
    }

    public String approvalVenueDetails(DataSource ds, String personCode, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        String approvalCampAddressDetails = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            approvalCampAddressDetails = issueRaiseApprovalDAO.approvalVenueDetails(ds, personCode, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvalCampAddressDetails;
    }

    public IssueRaiseApprovalDTO pensionPhaseValidation(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            issueRaiseApprovalDTO = issueRaiseApprovalDAO.pensionPhaseValidation(ds, issueRaiseApprovalForm, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return issueRaiseApprovalDTO;
    }

    public int vicversaNewSADAREMID(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int vicversaNewSADAREMID = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            vicversaNewSADAREMID = issueRaiseApprovalDAO.vicversaNewSADAREMID(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vicversaNewSADAREMID;
    }

    public ArrayList validRationCardNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String districtId) throws SADAREMDBException, SQLException {
        ArrayList validRationCardNoNotOpen = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        try {
            validRationCardNoNotOpen = issueRaiseApprovalDAO.validRationCardNotOpen(ds, issueRaiseApprovalForm, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return validRationCardNoNotOpen;

    }

    public ArrayList getRationCardDetailsForNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String rationCardNo) throws SADAREMDBException, SQLException {
        ArrayList rationCardDetailsNotOpen = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            rationCardDetailsNotOpen = issueRaiseApprovalDAO.getRationCardDetailsForNotOpen(ds, issueRaiseApprovalForm, rationCardNo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rationCardDetailsNotOpen;
    }

    public int approvalRationCardNotOpen(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String rationCardNo) throws SADAREMDBException, SQLException {
        int approvalRationCardNo = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            approvalRationCardNo = issueRaiseApprovalDAO.approvalRationCardNotOpen(ds, issueRaiseApprovalForm, rationCardNo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvalRationCardNo;
    }

    public ArrayList getVicerversaPoulateDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm, String flag) throws SADAREMDBException, SQLException {

        ArrayList vicerversaPoulateDetails = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            vicerversaPoulateDetails = issueRaiseApprovalDAO.getVicerversaPoulateDetails(ds, issueRaiseApprovalForm, flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vicerversaPoulateDetails;
    }

    public int updateUserRaisedDocumentPath(DataSource ds, String requestId, String filePath) throws SADAREMDBException, SQLException {
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        int updateUserRasiedDoc = 0;
        try {
            updateUserRasiedDoc = issueRaiseApprovalDAO.updateUserRaisedDocumentPath(ds, requestId, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateUserRasiedDoc;
    }

    public ArrayList getPensionCardNotOpeningList(DataSource ds, String penionId, String districtId) throws SADAREMDBException, SQLException {
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        ArrayList pensionCardNotOpeningList = new ArrayList();
        try {
            pensionCardNotOpeningList = issueRaiseApprovalDAO.getPensionCardNotOpeningList(ds, penionId, districtId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pensionCardNotOpeningList;
    }

    public ArrayList getMedicalBoardUserList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        ArrayList medicalBoardUserList = new ArrayList();
        try {
            medicalBoardUserList = issueRaiseApprovalDAO.getMedicalBoardUserList(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return medicalBoardUserList;
    }

    public String getDistrictName(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        String districtName = null;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            districtName = issueRaiseApprovalDAO.getDistrictName(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return districtName;
    }

    public IssueRaiseApprovalDTO validblankPagesIds(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int validBankPagesIds = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
        try {
            issueRaiseApprovalDTO = issueRaiseApprovalDAO.validblankPagesIds(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return issueRaiseApprovalDTO;
    }

    public int approvalRationCardSerialNo(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int approvalRationCardSerialNo = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            approvalRationCardSerialNo = issueRaiseApprovalDAO.approvalRationCardSerialNo(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvalRationCardSerialNo;
    }

    public IssueRaiseApprovalDTO getDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
        try {
            issueRaiseApprovalDTO = issueRaiseApprovalDAO.getDateOfIssue(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return issueRaiseApprovalDTO;
    }

    public int getAppleateCountDetails(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        int appleateCountDetails = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            appleateCountDetails = issueRaiseApprovalDAO.getAppleateCountDetails(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appleateCountDetails;
    }

    public int approvalForDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        int approvalForDateOfIssue = 0;
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            approvalForDateOfIssue = issueRaiseApprovalDAO.approvalForDateOfIssue(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvalForDateOfIssue;
    }

    public IssueRaiseApprovalDTO validDateOfIssue(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        IssueRaiseApprovalDTO issueRaiseApprovalDTO = new IssueRaiseApprovalDTO();
        try {
            issueRaiseApprovalDTO = issueRaiseApprovalDAO.validDateOfIssue(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return issueRaiseApprovalDTO;
    }

    public String getRelationTypeChange(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        String getRelationTypeChange = null;
        try {
            getRelationTypeChange = issueRaiseApprovalDAO.getRelationTypeChange(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return getRelationTypeChange;


    }

    public ArrayList getRelationTypeChangeCompare(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        ArrayList medicalBoardUserList = new ArrayList();
        try {
            medicalBoardUserList = issueRaiseApprovalDAO.getRelationTypeChangeCompare(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return medicalBoardUserList;
    }

    public ArrayList getTypeofDisablity(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        ArrayList typeofdisability = new ArrayList();
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        try {
            typeofdisability = issueRaiseApprovalDAO.getTypeofDisablity(ds, issueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typeofdisability;
    }

    public ArrayList getSubDisabilityList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        ArrayList subdisabilityList = new ArrayList();
        try {
            subdisabilityList = issueRaiseApprovalDAO.getSubDisabilityList(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return subdisabilityList;
    }

    public ArrayList getSub_Sub_DisabilityList(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        ArrayList sub_sub_disability = new ArrayList();

        try {
            sub_sub_disability = issueRaiseApprovalDAO.getSub_Sub_DisabilityList(ds, issueRaiseApprovalForm);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return sub_sub_disability;
    }

    public String getcheckinTypeofDisablity(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {

        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();

        String checkinTypedisability = null;
        try {
            // checkinTypedisability=issueRaiseApprovalDAO.getcheckinTypeofDisablity(ds, issueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkinTypedisability;
    }

    public String getDiagnosisofDisability(DataSource ds, IssueRaiseApprovalForm issueRaiseApprovalForm) throws SADAREMDBException, SQLException {
        IssueRaiseApprovalDAO issueRaiseApprovalDAO = new IssueRaiseApprovalDAO();
        String diagnosisofDisability = null;
        try {
            diagnosisofDisability = issueRaiseApprovalDAO.getDiagnosisofDisability(ds, issueRaiseApprovalForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return diagnosisofDisability;
    }


    public ArrayList activeSadaremId(DataSource ds, String sadaremId) throws SADAREMDBException, SQLException {
        ArrayList act = new ArrayList();
        try {
            IssueRaiseApprovalDAO dao = new IssueRaiseApprovalDAO();
            act = dao.activeSadaremId(ds, sadaremId);
        } catch (Exception e) {
        }
        return act;
    }

    //balu Start
    public String getAddressChangeValidation(DataSource ds, String sadaremId) throws SADAREMDBException, SQLException{
        String pensioncard_no= null;
        try {
            IssueRaiseApprovalDAO dao = new IssueRaiseApprovalDAO();
            pensioncard_no  = dao.getAddressChangeValidation(ds, sadaremId);
        } catch (Exception e) {
        }
        return pensioncard_no;
    }

    public ArrayList getPhaseConversionList(DataSource ds, String pensionId, String distId) throws SADAREMDBException, SQLException{
        ArrayList personalDetails = null;
         try {
            IssueRaiseApprovalDAO dao = new IssueRaiseApprovalDAO();
            personalDetails  = dao.getPhaseConversionList(ds, pensionId, distId);
        } catch (Exception e) {
        }
        return personalDetails;
    }

    public ArrayList getPensionPersonalDetails(DataSource ds, String pensionId, String distId) throws SADAREMDBException, SQLException{
        ArrayList pensionPersonalDetails = null;
         try {
            IssueRaiseApprovalDAO dao = new IssueRaiseApprovalDAO();
            pensionPersonalDetails  = dao.getPensionPersonalDetails(ds, pensionId, distId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pensionPersonalDetails;
    }
    //balu end
}
