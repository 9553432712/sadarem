/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.GrievancesRequestDetailsForm;
import javax.sql.DataSource;
import org.bf.disability.form.RDCallCentreStatusForm;
import javax.servlet.http.HttpServletRequest;
import org.bf.disability.dto.GrievancesRequestDetailsDTO;

/**
 *
 * @author 728056
 */
public interface GrievancesRequestDetailsService {

    public ArrayList getRequestName(DataSource ds, int currentRoleId) throws SADAREMDBException, SQLException;

    public ArrayList getRequestDetails(DataSource ds, GrievancesRequestDetailsForm detailsForm, String districtid) throws SADAREMDBException, SQLException;

    //kavya
    public ArrayList getRDCallCentreStatusReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException;

    public ArrayList getDPMApprovalList(DataSource ds, GrievancesRequestDetailsForm detailsForm, String districtid) throws SADAREMDBException, SQLException;
//kavya

    public ArrayList getAllRequestedDetails(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, String districtid, int currentRoleId, HttpServletRequest request, String mandalId) throws SADAREMDBException, SQLException;

    public int approveOrRejectRequests(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, int currentRoleId, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int approveOrRejectDPMRequests(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public ArrayList getCountOfStatusesReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException;

    public ArrayList getIndividualDetailsOfStatusesReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm, String status) throws SADAREMDBException, SQLException;

    public ArrayList getMonthWiseCallCentreReport(DataSource ds, RDCallCentreStatusForm rDCallCentreStatusForm) throws SADAREMDBException, SQLException;

    public ArrayList getRequestDetailsBasedonRequestNumber(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm) throws SADAREMDBException, SQLException;

    public String getDPMRemarks(DataSource ds, GrievancesRequestDetailsForm grievancesRequestDetailsForm) throws SADAREMDBException, SQLException;

    public int getValidForNewCertificate(DataSource ds, String BenificiaryProblemId) throws SADAREMDBException, SQLException;

    public String getValidForNewCertificateMsg(DataSource ds, String BenificiaryProblemId) throws SADAREMDBException, SQLException;

    public ArrayList getUploadProofTypeList(DataSource ds, String requestId, String subRequestId) throws SADAREMDBException, SQLException;

    public ArrayList getSADAREMCampReport(DataSource ds, String districtId, String mandalId, String villageId, String panchayatId, String habitationId) throws SADAREMDBException, SQLException;

    public int submitMesevaNewCertificateDetails(DataSource ds, GrievancesRequestDetailsDTO grievancesRequestDetailsDTO) throws SADAREMDBException, SQLException;

    public GrievancesRequestDetailsDTO getRequestDetails(DataSource ds, String applicationNo) throws SADAREMDBException, SQLException;

    public ArrayList getMeesevaRequestDetails(DataSource ds, String pensionNo, String districtId) throws SADAREMDBException, SQLException;

    public int updateMEESEVATransctionId(DataSource ds, String applicationId, String meesevaId) throws SADAREMDBException, SQLException;

    public int submitMeesevaFialTransctionDetails(DataSource ds, String benid, String uniqueid, String scaid, String loginid, String channelid,
                                        String reqid, String serviceid, String sadaremtransid, String scapwd,
                                        String meesevaflag, String fname, String distid, String mandalid, String villageid, String systemip)throws SADAREMDBException, SQLException;
}
