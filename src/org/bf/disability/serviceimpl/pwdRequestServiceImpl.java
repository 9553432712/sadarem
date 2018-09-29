/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.PwdRequestDAO;
import org.bf.disability.dto.PwdRequestDTO;
import org.bf.disability.form.PwdRequestForm;
import org.bf.disability.service.pwdRequestService;

/**
 *
 * @author 693461
 */
public class pwdRequestServiceImpl implements pwdRequestService {

    public ArrayList getPwdRequestList(DataSource ds, String requestTypeId) throws SADAREMDBException, SQLException {
        ArrayList requestList = new ArrayList();
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();

        try {
            requestList = pwdRequestDAO.getPwdRequestList(ds, requestTypeId);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPwdRequestList", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getPwdRequestList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPwdRequestList", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getPwdRequestList");
        }


        return requestList;
    }

    public ArrayList getPanchayatDetails(DataSource ds, String districtId, String mandalId) throws SADAREMDBException, SQLException {

        ArrayList panchayatList = new ArrayList();
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();

        try {
            panchayatList = pwdRequestDAO.getPanchayatDetails(ds, districtId, mandalId);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPanchayatDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getPanchayatDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPanchayatDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getPanchayatDetails");
        }


        return panchayatList;

    }

    public PwdRequestDTO checkSadarermIDNo(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();
        int checkSadaremID = 0;

        try {
            pwdRequestDTO = pwdRequestDAO.checkSadarermIDNo(ds, personCode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkSadarermIDNo", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "checkSadarermIDNo");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkSadarermIDNo", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "checkSadarermIDNo");
        }
        return pwdRequestDTO;
    }

    public PwdRequestDTO tempararyDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {
        PwdRequestDTO tempararyDetails = new PwdRequestDTO();
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();

        try {
            tempararyDetails = pwdRequestDAO.tempararyDetails(ds, personCode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "tempararyDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "tempararyDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "tempararyDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "tempararyDetails");
        }

        return tempararyDetails;

    }

    public PwdRequestDTO duplicateCertificateDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        String duplicateCertificate = null;
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();

        try {
            pwdRequestDTO = pwdRequestDAO.duplicateCertificateDetails(ds, personCode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "duplicateCertificateDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "duplicateCertificateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "duplicateCertificateDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "duplicateCertificateDetails");
        }

        return pwdRequestDTO;
    }

    public boolean physicalImpairment(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        boolean physicalImpairment = false;
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();

        try {
            physicalImpairment = pwdRequestDAO.physicalImpairment(ds, personCode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "physicalImpairment", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "physicalImpairment");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "physicalImpairment", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "physicalImpairment");
        }

        return physicalImpairment;
    }

    public int insertRequestInformationDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException {

        int requestInformationDetails = 0;
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();

        try {
            requestInformationDetails = pwdRequestDAO.insertRequestInformationDetails(ds, pwdRequestForm, systemIp);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertRequestInformationDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "insertRequestInformationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertRequestInformationDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "insertRequestInformationDetails");
        }

        return requestInformationDetails;
    }

    public int insertRessessmentRequestInformationDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException {

        int ressessmentDetails = 0;
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        try {
            ressessmentDetails = pwdRequestDAO.insertRessessmentRequestInformationDetails(ds, pwdRequestForm, systemIp);


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertRessessmentRequestInformationDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "insertRessessmentRequestInformationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertRessessmentRequestInformationDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "insertRessessmentRequestInformationDetails");
        }

        return ressessmentDetails;
    }

    public int insertPwdDetails(DataSource ds, PwdRequestForm pwdRequestForm, String reqId) throws SADAREMDBException, SQLException {
        int insertPwdDetails = 0;
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();

        try {
            // insertPwdDetails = pwdRequestDAO.insertPwdDetails(ds, pwdRequestForm,reqId);
        }  catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPwdDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "insertPwdDetails");
        }
        return insertPwdDetails;

    }

    public PwdRequestDTO checkSadarermIDNoPersoncode(DataSource ds, String personCode) throws SADAREMDBException, SQLException {
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        int checkSadaremID = 0;
        PwdRequestDTO pwdRequestDTO = null;


        try {
            pwdRequestDTO = pwdRequestDAO.checkSadarermIDNoPersoncode(ds, personCode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkSadarermIDNoPersoncode", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "checkSadarermIDNoPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkSadarermIDNoPersoncode", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "checkSadarermIDNoPersoncode");
        }
        return pwdRequestDTO;
    }

    public String getrequestMaxId(DataSource ds) throws SADAREMDBException, SQLException {
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        String checkSadaremID = null;

        try {
            checkSadaremID = pwdRequestDAO.getrequestMaxId(ds);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getrequestMaxId", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getrequestMaxId");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getrequestMaxId", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getrequestMaxId");
        }
        return checkSadaremID;
    }

    public PwdRequestDTO getReciptDetils(DataSource ds, String requestId) throws SADAREMDBException, SQLException {

        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();


        try {
            pwdRequestDTO = pwdRequestDAO.getReciptDetils(ds, requestId);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getReciptDetils", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getReciptDetils");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getReciptDetils", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getReciptDetils");
        }
        return pwdRequestDTO;
    }

    public synchronized int partAInsertDetails(DataSource ds, PwdRequestForm pwdRequestForm, String documentPath, String addressPath, String loginId, String systemIp) throws SADAREMDBException, SQLException {

        int partAInsertDetails = 0;

        try {
            PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
            partAInsertDetails = pwdRequestDAO.partAInsertDetails(ds, pwdRequestForm, documentPath, addressPath, loginId, systemIp);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "partAInsertDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "partAInsertDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "partAInsertDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "partAInsertDetails");
        }
        return partAInsertDetails;

    }

    public int newCertificateDetails(DataSource ds, PwdRequestForm pwdRequestForm, String systemIp) throws SADAREMDBException, SQLException {

        int newCertificateDetails = 0;
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();

        try {
            newCertificateDetails = pwdRequestDAO.newCertificateDetails(ds, pwdRequestForm, systemIp);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "newCertificateDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "newCertificateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "newCertificateDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "newCertificateDetails");
        }
        return newCertificateDetails;
    }

    public ArrayList getTORequestNameDetails(DataSource ds) throws SADAREMDBException, SQLException {

        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        ArrayList nameRelationNameList = new ArrayList();

        try {
            nameRelationNameList = pwdRequestDAO.getTORequestNameDetails(ds);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getTORequestNameDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getTORequestNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getTORequestNameDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getTORequestNameDetails");
        }

        return nameRelationNameList;

    }

    public ArrayList getCCRequestNameDetails(DataSource ds, String districtId) throws SADAREMDBException, SQLException {

        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        ArrayList nameRelationNameList = new ArrayList();

        try {
            nameRelationNameList = pwdRequestDAO.getCCRequestNameDetails(ds, districtId);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCCRequestNameDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getCCRequestNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getCCRequestNameDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getCCRequestNameDetails");
        }
        return nameRelationNameList;
    }

    public ArrayList getPwdBodyDetails(DataSource ds, String personCode) throws SADAREMDBException, SQLException {

        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        ArrayList pwdBodyDetails = new ArrayList();

        try {
            pwdBodyDetails = pwdRequestDAO.getPwdBodyDetails(ds, personCode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPwdBodyDetails", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getPwdBodyDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPwdBodyDetails", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "getPwdBodyDetails");
        }

        return pwdBodyDetails;
    }

    public PwdRequestDTO validRequestSADAREMID(DataSource ds, String personCode, String requesttypeId) throws SADAREMDBException, SQLException {

        PwdRequestDTO pwdRequestDTO = new PwdRequestDTO();
        PwdRequestDAO pwdRequestDAO = new PwdRequestDAO();
        try {
            pwdRequestDTO = pwdRequestDAO.validRequestSADAREMID(ds, personCode, requesttypeId);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "validRequestSADAREMID", "pwdRequestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "validRequestSADAREMID");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "validRequestSADAREMID", "pwdRequestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "pwdRequestServiceImpl", "validRequestSADAREMID");
        }

        return pwdRequestDTO;



    }
}
