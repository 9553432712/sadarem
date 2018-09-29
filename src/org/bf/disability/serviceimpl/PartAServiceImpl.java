/*
 * PartAServiceImpl.java
 *
 * Created on September 8, 2008, 2:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.PartADAO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.service.PartAService;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.form.PartAForm;

/**
 * This class has the implementation functionality for PartAService
 * interface.
 * @author Krishnaprasad K
 * @version 1.0
 */
public class PartAServiceImpl implements PartAService {

    PartADAO partadao = new PartADAO();

    public String insertPersonalDetails(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {
        String insertpersonaldetails = null;
        try {
            insertpersonaldetails = partadao.insertPersonalDetails(partadto, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPersonalDetails", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPersonalDetails", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertPersonalDetails");
        }
        return insertpersonaldetails;
    }

    public ArrayList getAllDataBasedonRationCard(DataSource ds, String pensionId, String distid, HttpServletRequest request) throws SADAREMDBException, SQLException {
        ArrayList data = null;
        data = partadao.getAllDataBasedonRationCard(ds, pensionId, distid, request);
        return data;
    }

    public int insertDisabilityDetailsAU(String personcode, PartADTO partADTO, String personStatus, DataSource ds, HttpServletRequest request)throws SADAREMDBException, SQLException {
        int i = 0;
        try {
            i = partadao.insertDisabilityDetailsAU(personcode, partADTO, personStatus, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDisabilityDetailsAU", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertDisabilityDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDisabilityDetailsAU", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertDisabilityDetailsAU");
        }
        return i;
    }

    public PartADTO getPersonalDetails(String personcode, String personstatus, DataSource ds, int campId) throws SADAREMDBException, SQLException{
        PartADTO partadto = new PartADTO();
        try {
            partadto = partadao.getPersonalDetails(personcode, personstatus, ds, campId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalDetails", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonalDetails", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getPersonalDetails");
        }
        return partadto;
    }

    public PartADTO getDesabilityDetails(String personcode, String personstatus, DataSource ds, int campId) throws SADAREMDBException, SQLException {
        PartADTO partadto = new PartADTO();
        try {
            partadto = partadao.getDesabilityDetails(personcode, personstatus, ds, campId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDesabilityDetails", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getDesabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDesabilityDetails", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getDesabilityDetails");
        }
        return partadto;
    }

    public void updatePersonalDetails(String personcode, PartADTO partadto, DataSource ds, HttpServletRequest request)throws SADAREMDBException, SQLException {
        try {
            partadao.updatePersonalDetails(personcode, partadto, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updatePersonalDetails", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "updatePersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updatePersonalDetails", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "updatePersonalDetails");
        }
    }

    public void insertDisabilityDetails(String personcode, PartADTO partADTO, DataSource ds, HttpServletRequest request)throws SADAREMDBException, SQLException {
        try {
            partadao.insertDisabilityDetails(personcode, partADTO, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDisabilityDetails", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDisabilityDetails", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertDisabilityDetails");
        }
    }

    // added by Naidu for Geting ExistingPension detials based on PensionId,DistrictId.
    public PartADTO getExistingPensionDetails(String pensionCode, String districtId, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {
        PartADTO partadto = new PartADTO();
        try {
            partadto = partadao.getExistingPensionDetails(pensionCode, districtId, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getExistingPensionDetails", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getExistingPensionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getExistingPensionDetails", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getExistingPensionDetails");
        }
        return partadto;
    }

    public ArrayList getMandalsList(DataSource datasource, String districtid) throws SADAREMDBException, SQLException {
        ArrayList mandallist = new ArrayList();
        try {
            mandallist = partadao.getMandalsList(datasource, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandalsList", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getMandalsList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandalsList", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getMandalsList");
        }
        return mandallist;



    }

    public ArrayList getVillagesList(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException {
        ArrayList villageslist = new ArrayList();
        try {
            villageslist = partadao.getVillagesList(datasource, districtid, mandalid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillagesList", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getVillagesList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillagesList", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getVillagesList");
        }
        return villageslist;
    }

    public ArrayList getHabitationsList(DataSource datasource, String districtid, String mandalid, String villageid) throws SADAREMDBException, SQLException {
        ArrayList habitaionlist = new ArrayList();
        try {
            habitaionlist = partadao.getHabitationsList(datasource, districtid, mandalid, villageid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationsList", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getHabitationsList");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationsList", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getHabitationsList");
        }
        return habitaionlist;
    }

    public PartADTO getEmployeeDetails(String employeeId, String districtId, DataSource ds) throws SADAREMDBException, SQLException {
        PartADTO partadto = new PartADTO();
        try {
            partadto = partadao.getEmployeeDetails(employeeId, districtId, ds);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getEmployeeDetails", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getEmployeeDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getEmployeeDetails", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getEmployeeDetails");
        }
        return partadto;
    }

    public String insertPersonalDetailsForRationCard(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {
        String insertpersonaldetails = null;
        try {
            insertpersonaldetails = partadao.insertPersonalDetailsForRationCard(partadto, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPersonalDetailsForRationCard", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertPersonalDetailsForRationCard");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertPersonalDetailsForRationCard", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertPersonalDetailsForRationCard");
        }
        return insertpersonaldetails;
    }

    public int checkPensionCardNumber(DataSource ds, String pensionId, String districtId)throws SADAREMDBException, SQLException {
        int i = 0;
        try {
            i = partadao.checkPensionCardNumber(ds, pensionId, districtId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPensionCardNumber", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "checkPensionCardNumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPensionCardNumber", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "checkPensionCardNumber");
        }
        return i;
    }

    public String getPersonCode(DataSource ds, String pensionId) throws SADAREMDBException, SQLException {
        String insertpersonaldetails = null;
        try {
            insertpersonaldetails = partadao.getPersonCode(ds, pensionId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonCode", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPersonCode", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getPersonCode");
        }
        return insertpersonaldetails;
    }

    public String insertSerpEmployeesPersonalDetails(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {
        String insertpersonaldetails = null;
        try {
            insertpersonaldetails = partadao.insertSerpEmployeesPersonalDetails(partadto, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertSerpEmployeesPersonalDetails", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertSerpEmployeesPersonalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertSerpEmployeesPersonalDetails", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertSerpEmployeesPersonalDetails");
        }
        return insertpersonaldetails;
    }

    public ArrayList checkSADAREMAssessmentData(DataSource ds, String aadharCardNo) throws SADAREMDBException, Exception {
       
        ArrayList SADAREMID = new ArrayList();
        try {
            SADAREMID = partadao.checkSADAREMAssessmentData(ds, aadharCardNo);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSADAREMAssessmentData", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "checkSADAREMAssessmentData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkSADAREMAssessmentData", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "checkSADAREMAssessmentData");
        }
        return SADAREMID;
    }

    public ArrayList checkPensionData(DataSource ds, String aadharCardNo) throws SADAREMDBException, Exception {

        ArrayList pensionId = new ArrayList();
        try {
            pensionId = partadao.checkPensionData(ds, aadharCardNo);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensionData", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "checkPensionData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkPensionData", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "checkPensionData");
        }
        return pensionId;
    }

    public ArrayList getSADAREMAssessedData(DataSource ds, PartADTO partADTO) throws SQLException, SADAREMDBException {
         ArrayList sadaremList = new ArrayList();
        try {
            sadaremList = partadao.getSADAREMAssessedData(ds, partADTO);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMAssessedData", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getSADAREMAssessedData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getSADAREMAssessedData", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "getSADAREMAssessedData");
        }
        return sadaremList;

    }

        public int taggedAadharCardNo(DataSource ds, PartAForm partAForm) throws SADAREMDBException, SQLException {
            int success=0;
        try {
            success = partadao.taggedAadharCardNo(ds, partAForm);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "taggedAadharCardNo", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "taggedAadharCardNo");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "taggedAadharCardNo", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "taggedAadharCardNo");
        }
        return success;

        }


        public String insertPersonalDetailsForAadharNumber(PartADTO partadto, DataSource ds, HttpServletRequest request) throws SADAREMDBException, SQLException {
        String insertpersonaldetails = null;
        try {
            insertpersonaldetails = partadao.insertPersonalDetailsForAadharNumber(partadto, ds, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForAadharNumber", "PartAServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertPersonalDetailsForAadharNumber");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPersonalDetailsForAadharNumber", "PartAServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartAServiceImpl", "insertPersonalDetailsForAadharNumber");
        }
        return insertpersonaldetails;
    }

}
    

