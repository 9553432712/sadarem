/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.service;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.RequestInformationDTO;
import org.bf.disability.form.RequestInformationForm;

/**
 *
 * @author 693461
 */
public interface RequestInformationService {

    //To load Master Request Details
    public ArrayList getRequestTypeDetails(DataSource ds) throws SADAREMDBException, SQLException;
    //To get PersonCode

    public ArrayList getPersonCodeDetails(DataSource ds) throws SADAREMDBException, SQLException;
    //To get Personal Information for rural Details

    public ArrayList getRequestInformationDetails(DataSource ds, String districtId, String requestType, String status) throws SADAREMDBException, SQLException;
    //To get Personal Information for Urban Details

    public ArrayList getRequestUrbanIformationDetails(DataSource ds, String districtId, String requestType, String status) throws SADAREMDBException, SQLException;
    //To get Particular Personal Information

    public ArrayList getParticularInforamtionDetails(DataSource ds, String requestId) throws SADAREMDBException, SQLException;
    //To update Particular Request Status

    public int updateRequestDetails(DataSource ds, String status, String requesttype, String requestTypeId) throws SADAREMDBException, SQLException;
    // To check weather the SADAREMID is exist or not

    public RequestInformationDTO checkRessessmentDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException;

    public RequestInformationDTO checkRenualDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException;

    public ArrayList getNewCertificateDetails(DataSource ds, String requestid, String requesttypeid) throws SADAREMDBException, SQLException;

    public int insertNewCertificate(DataSource ds, String requestId, String requestTypeId, String loginId, String systemIp, String statusId, HttpServletRequest request) throws SADAREMDBException, SQLException;

    public int rejectNewCertificate(DataSource ds, String requestId, String requestTypeId) throws SADAREMDBException, SQLException;

    public int duplicateDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException;

    public int physicalImpairment(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException;

    public ArrayList getRequestDetails(DataSource ds) throws SADAREMDBException, SQLException;

    //Start adminLevelAccessFlag
    public ArrayList getMultipleDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException;

    public ArrayList getSingleDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException;
    //end

    //Start urbanLevelAccessFlag
    public ArrayList getMultipleUrbanDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException;

    public ArrayList getSingleUrbanDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException;
    //end
}
