/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.dao.GrievancesRequestDetailsDAO;
import org.bf.disability.form.GrievancesRequestDetailsForm;
import org.bf.disability.service.GrievancesRequestDetailsService;
import javax.sql.DataSource;
import org.bf.disability.dto.GrievancesRequestDetailsDTO;
import org.bf.disability.form.RDCallCentreStatusForm;

/**
 *
 * @author 728056
 */
public class GrievancesRequestDetailsServiceImpl implements GrievancesRequestDetailsService {

    public ArrayList getRequestName(DataSource ds, int currentRoleId) throws SADAREMDBException, SQLException {
        ArrayList RequestNames = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            RequestNames = dAO.getRequestName(ds, currentRoleId);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestName", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getRequestName");
        }
        return RequestNames;

    }

    public ArrayList getRequestDetails(DataSource ds, GrievancesRequestDetailsForm detailsForm, String districtid) throws SADAREMDBException, SQLException {
        ArrayList RequestDetails = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            RequestDetails = dAO.getRequestDetails(ds, detailsForm, districtid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getRequestDetails");
        }
        return RequestDetails;

    }

    //kavya
    public ArrayList getRDCallCentreStatusReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException {
        ArrayList reportDetails = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            reportDetails = dAO.getRDCallCentreStatusReport(ds, rDCallCentreStatusForm);
        } catch (Exception e) {
            int exResult = ExceptionDAO.saveException(ds, e.toString(), "getRDCallCentreStatusReport", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, e.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getRDCallCentreStatusReport");
        }
        return reportDetails;
    }

    public ArrayList getDPMApprovalList(DataSource ds, GrievancesRequestDetailsForm detailsForm, String districtid) throws SADAREMDBException, SQLException {
        ArrayList RequestDetails = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            RequestDetails = dAO.getDPMApprovalList(ds, detailsForm, districtid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getRequestDetails");
        }
        return RequestDetails;

    }

    public ArrayList getAllRequestedDetails(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, String districtid, int currentRoleId, HttpServletRequest request, String mandalId) throws SADAREMDBException, SQLException {
        ArrayList RequestDetails = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            RequestDetails = dAO.getAllRequestedDetails(ds, grievancesRequestDetailsForm, districtid, currentRoleId, request, mandalId);
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAllRequestedDetails", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getAllRequestedDetails");
        }
        return RequestDetails;
    }

    public int approveOrRejectRequests(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, int currentRoleId, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int flag = 0;
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            flag = dAO.approveOrRejectRequests(ds, grievancesRequestDetailsForm, currentRoleId, request);
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approveOrRejectRequests", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "approveOrRejectRequests");
        }
        return flag;
    }

    public int approveOrRejectDPMRequests(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int flag = 0;
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            flag = dAO.approveOrRejectDPMRequests(ds, grievancesRequestDetailsForm, request);
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "approveOrRejectRequests", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "approveOrRejectRequests");
        }
        return flag;
    }

    public ArrayList getCountOfStatusesReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException {
        ArrayList reportList = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            reportList = dAO.getCountOfStatusesReport(ds, rDCallCentreStatusForm);
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCountOfStatusesReport", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getCountOfStatusesReport");
        }
        return reportList;
    }

    public ArrayList getIndividualDetailsOfStatusesReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm, String status) throws SADAREMDBException, SQLException {
        ArrayList reportList = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            reportList = dAO.getIndividualDetailsOfStatusesReport(ds, rDCallCentreStatusForm, status);
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIndividualDetailsOfStatusesReport", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getIndividualDetailsOfStatusesReport");
        }
        return reportList;
    }

    public ArrayList getMonthWiseCallCentreReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException {
        ArrayList reportList = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            reportList = dAO.getMonthWiseCallCentreReport(ds, rDCallCentreStatusForm);
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthWiseCallCentreReport", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getMonthWiseCallCentreReport");
        }
        return reportList;
    }

    public ArrayList getRequestDetailsBasedonRequestNumber(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm) throws SADAREMDBException, SQLException {
        ArrayList RequestDetails = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            RequestDetails = dAO.getRequestDetailsBasedonRequestNumber(ds, grievancesRequestDetailsForm);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetailsBasedonRequestNumber", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getRequestDetailsBasedonRequestNumber");
        }
        return RequestDetails;
    }

    public String getDPMRemarks(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm) throws SADAREMDBException, SQLException {
        String remarks = "";
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            remarks = dAO.getDPMRemarks(ds, grievancesRequestDetailsForm);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDPMRemarks", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getDPMRemarks");
        }
        return remarks;
    }

    public ArrayList getUploadProofTypeList(DataSource ds, String requestId, String subRequestId) throws SADAREMDBException, SQLException {
        ArrayList poofList = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            poofList = dAO.getUploadProofTypeList(ds, requestId, subRequestId);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUploadProofTypeList", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getUploadProofTypeList");
        }
        return poofList;
    }

    public ArrayList getSADAREMCampReport(DataSource ds, String districtId, String mandalId, String villageId, String panchayatId, String habitationId) throws SADAREMDBException, SQLException {
        ArrayList poofList = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            poofList = dAO.getSADAREMCampReport(ds, districtId, mandalId, villageId, panchayatId, habitationId);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMCampReport", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getSADAREMCampReport");
        }
        return poofList;
    }

    public int getValidForNewCertificate(DataSource ds, String BenificiaryProblemId) throws SADAREMDBException, SQLException {
        int adhaarExist = 0;
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            adhaarExist = dAO.getValidForNewCertificate(ds, BenificiaryProblemId);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMCampReport", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getSADAREMCampReport");
        }
        return adhaarExist;
    }

    public String getValidForNewCertificateMsg(DataSource ds, String BenificiaryProblemId) throws SADAREMDBException, SQLException {
        String msg = null;
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            msg = dAO.getValidForNewCertificateMsg(ds, BenificiaryProblemId);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMCampReport", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getSADAREMCampReport");
        }
        return msg;
    }

    public int submitMesevaNewCertificateDetails(DataSource ds, GrievancesRequestDetailsDTO grievancesRequestDetailsDTO) throws SADAREMDBException, SQLException {
        int success = 0;
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            success = dAO.submitMesevaNewCertificateDetails(ds, grievancesRequestDetailsDTO);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "submitMesevaNewCertificateDetails", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "submitMesevaNewCertificateDetails");
        }
        return success;
    }

    public GrievancesRequestDetailsDTO getRequestDetails(DataSource ds, String applicationNo) throws SADAREMDBException, SQLException {
        GrievancesRequestDetailsDTO grievancesRequestDetailsDTO = null;
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            grievancesRequestDetailsDTO = dAO.getRequestDetails(ds, applicationNo);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRequestDetails", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getRequestDetails");
        }
        return grievancesRequestDetailsDTO;
    }

    public ArrayList getMeesevaRequestDetails(DataSource ds, String pensionNo, String districtId) throws SADAREMDBException, SQLException {
        ArrayList proofList = new ArrayList();
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            proofList = dAO.getMeesevaRequestDetails(ds, pensionNo, districtId);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMeesevaRequestDetails", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "getMeesevaRequestDetails");
        }
        return proofList;
    }

    public int updateMEESEVATransctionId(DataSource ds, String applicationId, String meesevaId) throws SADAREMDBException, SQLException {
        int success = 0;
        GrievancesRequestDetailsDAO dAO = new GrievancesRequestDetailsDAO();
        try {
            success = dAO.updateMEESEVATransctionId(ds, applicationId, meesevaId);
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMEESEVATransctionId", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "updateMEESEVATransctionId");
        }
        return success;
    }

    public int submitMeesevaFialTransctionDetails(DataSource ds, String benid, String uniqueid, String scaid, String loginid, String channelid, String reqid, String serviceid, String sadaremtransid, String scapwd, String meesevaflag, String fname, String distid, String mandalid, String villageid, String systemip) throws SADAREMDBException, SQLException{
        int success = 0;
        GrievancesRequestDetailsDAO griDAO = new GrievancesRequestDetailsDAO();
        try {
           success =  griDAO.submitMeesevaFialTransctionDetails(ds, benid, uniqueid, scaid, loginid, channelid,  reqid, serviceid, sadaremtransid, scapwd, meesevaflag, fname, distid, mandalid, villageid,systemip);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateMEESEVATransctionId", "GrievancesRequestDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "GrievancesRequestDetailsServiceImpl", "submittblsadaremGreivances_Details");
        }
        return success;
    }
}
