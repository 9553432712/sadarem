/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;


import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.RequestInformationDAO;
import org.bf.disability.dto.RequestInformationDTO;
import org.bf.disability.form.RequestInformationForm;
import org.bf.disability.service.RequestInformationService;

/**
 *
 * @author 693461
 */
public class RequestInformationServiceImpl implements RequestInformationService {

    public ArrayList getRequestTypeDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList requestTypeList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            requestTypeList = requestInformationDAO.getRequestTypeDetails(ds);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRequestTypeDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getRequestTypeDetails");
        }
        return requestTypeList;
    }

    public ArrayList getPersonCodeDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList personCodeDetails = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            //  personCodeDetails = requestInformationDAO.getPersonCodeDetails(ds);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonCodeDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getPersonCodeDetails");
        }

        return personCodeDetails;
    }

    public ArrayList getRequestInformationDetails(DataSource ds, String districtId, String requestType, String status) throws SADAREMDBException, SQLException {

        ArrayList requestInformationList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();


        try {

            requestInformationList = requestInformationDAO.getRequestInformationDetails(ds, districtId, requestType, status);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRequestInformationDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getRequestInformationDetails");
        }
        return requestInformationList;

    }

    public ArrayList getRequestUrbanIformationDetails(DataSource ds, String districtId, String requestType, String status) throws SADAREMDBException, SQLException {

        ArrayList requestUrbanList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            requestUrbanList = requestInformationDAO.getRequestUrbanIformationDetails(ds, districtId, requestType, status);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRequestUrbanIformationDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getRequestUrbanIformationDetails");
        }

        return requestUrbanList;
    }

    public ArrayList getParticularInforamtionDetails(DataSource ds, String requestId) throws SADAREMDBException, SQLException {

        ArrayList personalInformationList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            // personalInformationList = requestInformationDAO.getParticularInforamtionDetails(ds, requestId);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getParticularInforamtionDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getParticularInforamtionDetails");
        }

        return personalInformationList;
    }

    public int updateRequestDetails(DataSource ds, String status, String requesttype, String requestTypeId) throws SADAREMDBException, SQLException {

        int updateRequestDetails = 0;
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();
        try {
            updateRequestDetails = requestInformationDAO.updateRequestDetails(ds, status, requesttype, requestTypeId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateRequestDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "updateRequestDetails");
        }

        return updateRequestDetails;
    }

    public RequestInformationDTO checkRessessmentDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException {

        RequestInformationDTO requestInformationDTO = new RequestInformationDTO();

        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            requestInformationDTO = requestInformationDAO.checkRessessmentDetails(ds, requestTypeId, personCode, loginId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkRessessmentDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "checkRessessmentDetails");
        }

        return requestInformationDTO;

    }

    public RequestInformationDTO checkRenualDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException {

        RequestInformationDTO requestInformationDTO = new RequestInformationDTO();

        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();
        try {

            requestInformationDTO = requestInformationDAO.checkRenualDetails(ds, requestTypeId, personCode, loginId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkRenualDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "checkRenualDetails");
        }
        return requestInformationDTO;
    }

    public ArrayList getNewCertificateDetails(DataSource ds, String requestid, String requesttypeid) throws SADAREMDBException, SQLException {

        ArrayList newCertificateDetails = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            newCertificateDetails = requestInformationDAO.getNewCertificateDetails(ds, requestid, requesttypeid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getNewCertificateDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getNewCertificateDetails");
        }

        return newCertificateDetails;

    }

    public int insertNewCertificate(DataSource ds, String requestId, String requestTypeId, String loginId, String systemIp, String statusId, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int insertNewCertificate = 0;
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            insertNewCertificate = requestInformationDAO.insertNewCertificate(ds, requestId, requestTypeId, loginId, systemIp, statusId, request);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertNewCertificate", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "insertNewCertificate");
        }

        return insertNewCertificate;
    }

    public int rejectNewCertificate(DataSource ds, String requestId, String requestTypeId) throws SADAREMDBException, SQLException {

        int rejectNewCertificate = 0;
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            rejectNewCertificate = requestInformationDAO.rejectNewCertificate(ds, requestId, requestTypeId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "rejectNewCertificate", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "rejectNewCertificate");
        }

        return rejectNewCertificate;
    }

    public int duplicateDetails(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException {

        int duplicateDetails = 0;
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            duplicateDetails = requestInformationDAO.duplicateDetails(ds, requestTypeId, personCode, loginId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "duplicateDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "duplicateDetails");
        }
        return duplicateDetails;
    }

    public int physicalImpairment(DataSource ds, String requestTypeId, String personCode, String loginId) throws SADAREMDBException, SQLException {

        int physicalImpairment = 0;
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            physicalImpairment = requestInformationDAO.physicalImpairment(ds, requestTypeId, personCode, loginId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "physicalImpairment", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "physicalImpairment");
        }

        return physicalImpairment;

    }

    public ArrayList getRequestDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList requestList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            requestList = requestInformationDAO.getRequestDetails(ds);


        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRequestDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getRequestDetails");
        }


        return requestList;

    }

    public ArrayList getMultipleDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException {
        ArrayList requestList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();


        try {
            requestList = requestInformationDAO.getMultipleDetails(ds, districtId, requestInformationForm);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMultipleDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getMultipleDetails");
        }
        return requestList;
    }

    public ArrayList getSingleDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException {

        ArrayList singleList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();


        try {
            singleList = requestInformationDAO.getSingleDetails(ds, districtId, requestInformationForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getSingleDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getSingleDetails");
        }
        return singleList;
    }

    public ArrayList getMultipleUrbanDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException {

        ArrayList singleList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            singleList = requestInformationDAO.getMultipleUrbanDetails(ds, districtId, requestInformationForm);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMultipleUrbanDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getMultipleUrbanDetails");
        }
        return singleList;
    }

    public ArrayList getSingleUrbanDetails(DataSource ds, String districtId, RequestInformationForm requestInformationForm) throws SADAREMDBException, SQLException {

        ArrayList singleList = new ArrayList();
        RequestInformationDAO requestInformationDAO = new RequestInformationDAO();

        try {
            singleList = requestInformationDAO.getSingleUrbanDetails(ds, districtId, requestInformationForm);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getSingleUrbanDetails", "RequestInformationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RequestInformationServiceImpl", "getSingleUrbanDetails");
        }
        return singleList;


    }
}
