/*
 * MentalRetardationServiceImpl.java
 *
 * Created on September 12, 2008, 3:38 PM
 *
 * To change this template, choose Tools | Template Manager
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
import org.bf.disability.dao.MentalRetardationDAO;
import org.bf.disability.dto.MRBinetKamatDetailedTestDTO;
import org.bf.disability.dto.MentalRetardationDTO;
import org.bf.disability.dto.MentalRetardationTestsDTO;
import org.bf.disability.service.MentalRetardationService;

/**
 * This class has the implementation functionality for MentalRetardationService
 * interface.
 * @author Sunima Dilla
 * @version 1.0
 */
public class MentalRetardationServiceImpl implements MentalRetardationService {

    MentalRetardationDTO modifydto = new MentalRetardationDTO();
    MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
    MentalRetardationDAO mentaldao = new MentalRetardationDAO();

    public int insertData(DataSource ds, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int mentalinsertdetails = 0;

        try {

            mentalinsertdetails = mentaldao.insertData(ds, modifydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertData");
        }
        return mentalinsertdetails;
    }

    public int insertDataAU(DataSource ds, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int mentalinsertdetails = 0;

        try {

            mentalinsertdetails = mentaldao.insertDataAU(ds, modifydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDataAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertDataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDataAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertDataAU");
        }
        return mentalinsertdetails;
    }

    public int MRSfbinsertDataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int mentalinsertdetails = 0;
        try {

            mentalinsertdetails = mentaldao.MRSfbinsertDataAU(ds, mentalretardationtestdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MRSfbinsertDataAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "MRSfbinsertDataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MRSfbinsertDataAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "MRSfbinsertDataAU");
        }
        return mentalinsertdetails;
    }

    public int MRMalinsinsertDataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int mentalinsertdetails = 0;
        try {


            mentalinsertdetails = mentaldao.MRMalinsinsertDataAU(ds, mentalretardationtestdto, request);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MRMalinsinsertDataAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "MRMalinsinsertDataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MRMalinsinsertDataAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "MRMalinsinsertDataAU");
        }
        return mentalinsertdetails;
    }

    public int insertBinetKamaldataAU(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto) throws SADAREMDBException, SQLException {
        int binetkamaldata = 0;
        try {
            binetkamaldata = mentaldao.insertBinetKamaldataAU(ds, mentalretardationtestdto);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertBinetKamaldataAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertBinetKamaldataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertBinetKamaldataAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertBinetKamaldataAU");
        }
        return binetkamaldata;
    }

    public int insertVinelandDataAU(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int vinelanddata = 0;
        try {
            vinelanddata = mentaldao.insertVinelandDataAU(ds, personcode, year, month, vinelandresult, loginid, systemip, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVinelandDataAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertVinelandDataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVinelandDataAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertVinelandDataAU");
        }
        return vinelanddata;
    }

    public int insertDevelopmentalDataAU(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int developmentaldata = 0;
        try {
            developmentaldata = mentaldao.insertDevelopmentalDataAU(ds, personcode, year, month, developmentalresult, loginid, systemip, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDevelopmentalDataAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertDevelopmentalDataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDevelopmentalDataAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertDevelopmentalDataAU");
        }
        return developmentaldata;
    }

    public int insertMRAlexanderTestDetailsAU(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        try {

            i = mentaldao.insertMRAlexanderTestDetailsAU(ds, personcode, mentalRetardationTestsDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMRAlexanderTestDetailsAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertMRAlexanderTestDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMRAlexanderTestDetailsAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertMRAlexanderTestDetailsAU");
        }
        return i;
    }

    public int insertBhatiaTestDetailsAU(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        try {

            i = mentaldao.insertBhatiaTestDetailsAU(ds, personcode, mentalRetardationTestsDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertBhatiaTestDetailsAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertBhatiaTestDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertBhatiaTestDetailsAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertBhatiaTestDetailsAU");
        }
        return i;
    }

    public int insertMRBinetKamatDetailedTestDetailsAU(DataSource ds, MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        try {
            i = mentaldao.insertMRBinetKamatDetailedTestDetailsAU(ds, mRBinetKamatDetailedTestDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMRBinetKamatDetailedTestDetailsAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertMRBinetKamatDetailedTestDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMRBinetKamatDetailedTestDetailsAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertMRBinetKamatDetailedTestDetailsAU");
        }
        return i;
    }

    public MentalRetardationDTO getMentalDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationDTO modifydto = new MentalRetardationDTO();
        try {
            modifydto = mentaldao.getMentalDetails(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMentalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMentalDetails");
        }
        return modifydto;
    }

    public void updateDetails(DataSource ds, MentalRetardationDTO modifydto, HttpServletRequest request) throws SADAREMDBException, SQLException {


        try {
            mentaldao.updateDetails(ds, modifydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateDetails");
        }
    }

    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {

        boolean bool;
        try {
            bool = mentaldao.checkPersoncode(personcode, ds);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "checkPersoncode");
        }
        return bool;

    }

    public int MRSfbinsertData(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int mentalinsertdetails = 0;
        try {

            mentalinsertdetails = mentaldao.MRSfbinsertData(ds, mentalretardationtestdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MRSfbinsertData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "MRSfbinsertData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MRSfbinsertData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "MRSfbinsertData");
        }
        return mentalinsertdetails;
    }

    public int MRMalinsinsertData(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int mentalinsertdetails = 0;
        try {


            mentalinsertdetails = mentaldao.MRMalinsinsertData(ds, mentalretardationtestdto, request);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MRMalinsinsertData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "MRMalinsinsertData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "MRMalinsinsertData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "MRMalinsinsertData");
        }
        return mentalinsertdetails;
    }

    public MentalRetardationTestsDTO getMRseguintestDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        try {
            mentalretardationtestdto = mentaldao.getMRseguintestDetails(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMRseguintestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMRseguintestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMRseguintestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMRseguintestDetails");
        }
        return mentalretardationtestdto;
    }

    public MentalRetardationTestsDTO getMRmalinstestDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationTestsDTO mentalretardationtestdto = new MentalRetardationTestsDTO();
        try {
            mentalretardationtestdto = mentaldao.getMRmalinstestDetails(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMRmalinstestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMRmalinstestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMRmalinstestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMRmalinstestDetails");
        }
        return mentalretardationtestdto;
    }

    public void updateMRSfbDetails(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {


        try {
            mentaldao.updateMRSfbDetails(ds, mentalretardationtestdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMRSfbDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateMRSfbDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMRSfbDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateMRSfbDetails");
        }
    }

    public void updateMalinstestDetails(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {


        try {
            mentaldao.updateMalinstestDetails(ds, mentalretardationtestdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMalinstestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateMalinstestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMalinstestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateMalinstestDetails");
        }
    }

    public int insertBinetKamaldata(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int binetkamaldata = 0;
        try {
            binetkamaldata = mentaldao.insertBinetKamaldata(ds, mentalretardationtestdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertBinetKamaldata", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertBinetKamaldata");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertBinetKamaldata", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertBinetKamaldata");
        }
        return binetkamaldata;
    }

    public int insertVinelandData(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int vinelanddata = 0;
        try {
            vinelanddata = mentaldao.insertVinelandData(ds, personcode, year, month, vinelandresult, loginid, systemip, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVinelandData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertVinelandData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVinelandData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertVinelandData");
        }
        return vinelanddata;
    }

    public int insertDevelopmentalData(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int developmentaldata = 0;
        try {
            developmentaldata = mentaldao.insertDevelopmentalData(ds, personcode, year, month, developmentalresult, loginid, systemip, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDevelopmentalData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertDevelopmentalData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDevelopmentalData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertDevelopmentalData");
        }
        return developmentaldata;
    }

    public MentalRetardationTestsDTO gettBinetKamaldata(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        try {
            mentalretardationtestdto = mentaldao.gettBinetKamaldata(ds, personcode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "gettBinetKamaldata", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "gettBinetKamaldata");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "gettBinetKamaldata", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "gettBinetKamaldata");
        }
        return mentalretardationtestdto;
    }

    public MentalRetardationTestsDTO getDevelopmentalData(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        try {
            mentalretardationtestdto = mentaldao.getDevelopmentalData(ds, personcode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDevelopmentalData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getDevelopmentalData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDevelopmentalData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getDevelopmentalData");
        }
        return mentalretardationtestdto;
    }

    public MentalRetardationTestsDTO getVinelandData(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        try {
            mentalretardationtestdto = mentaldao.getVinelandData(ds, personcode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getVinelandData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getVinelandData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getVinelandData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getVinelandData");
        }
        return mentalretardationtestdto;
    }

    public int updateBinetKamaldata(DataSource ds, MentalRetardationTestsDTO mentalretardationtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int binetkamaldata = 0;
        try {
            binetkamaldata = mentaldao.updateBinetKamaldata(ds, mentalretardationtestdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateBinetKamaldata", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateBinetKamaldata");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateBinetKamaldata", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateBinetKamaldata");
        }
        return binetkamaldata;
    }

    public int updateVinelandData(DataSource ds, String personcode, String year, String month, double vinelandresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int vinelanddata = 0;
        try {
            vinelanddata = mentaldao.updateVinelandData(ds, personcode, year, month, vinelandresult, loginid, systemip, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateVinelandData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateVinelandData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateVinelandData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateVinelandData");
        }
        return vinelanddata;
    }

    public int updateDevelopmentalData(DataSource ds, String personcode, String year, String month, double developmentalresult, String loginid, String systemip, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int developmentaldata = 0;
        try {
            developmentaldata = mentaldao.updateDevelopmentalData(ds, personcode, year, month, developmentalresult, loginid, systemip, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDevelopmentalData", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateDevelopmentalData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateDevelopmentalData", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateDevelopmentalData");
        }
        return developmentaldata;
    }

    public void insertMRAlexanderTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {

            mentaldao.insertMRAlexanderTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMRAlexanderTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertMRAlexanderTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMRAlexanderTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertMRAlexanderTestDetails");
        }

    }

    public MentalRetardationTestsDTO getMRAlexanderTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        MentalRetardationTestsDTO mentalRetardationTestsDTO = null;
        try {

            mentalRetardationTestsDTO = mentaldao.getMRAlexanderTestDetails(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMRAlexanderTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMRAlexanderTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMRAlexanderTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMRAlexanderTestDetails");
        }
        return mentalRetardationTestsDTO;
    }

    public void updateMRAlexanderTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {

            mentaldao.updateMRAlexanderTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMRAlexanderTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateMRAlexanderTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMRAlexanderTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateMRAlexanderTestDetails");
        }

    }

    public boolean checkForPersonCode(DataSource ds, String personcode, String tableName) throws SADAREMDBException, SQLException {
        boolean isPersonCodeExist;
        try {

            isPersonCodeExist = mentaldao.checkForPersonCode(ds, personcode, tableName);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkForPersonCode", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "checkForPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkForPersonCode", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "checkForPersonCode");
        }
        return isPersonCodeExist;
    }

    public MentalRetardationTestsDTO getMentalRetardationIQValues(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        MentalRetardationTestsDTO mentalRetardationTestsDTO = null;
        try {

            mentalRetardationTestsDTO = mentaldao.getMentalRetardationIQValues(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMentalRetardationIQValues", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMentalRetardationIQValues");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMentalRetardationIQValues", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMentalRetardationIQValues");
        }
        return mentalRetardationTestsDTO;
    }

    public void insertBhatiaTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {

            mentaldao.insertBhatiaTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertBhatiaTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertBhatiaTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertBhatiaTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertBhatiaTestDetails");
        }

    }

    public MentalRetardationTestsDTO getBhatiaTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationTestsDTO mentalRetardationTestsDTO = null;
        try {

            mentalRetardationTestsDTO = mentaldao.getBhatiaTestDetails(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getBhatiaTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getBhatiaTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getBhatiaTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getBhatiaTestDetails");
        }
        return mentalRetardationTestsDTO;
    }

    public void updateBhatiaTestDetails(DataSource ds, String personcode, MentalRetardationTestsDTO mentalRetardationTestsDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {

        try {

            mentaldao.updateBhatiaTestDetails(ds, personcode, mentalRetardationTestsDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateBhatiaTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateBhatiaTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateBhatiaTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateBhatiaTestDetails");
        }

    }

    public MentalRetardationDTO getTestDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationDTO modifydto = new MentalRetardationDTO();
        try {
            modifydto = mentaldao.getTestDetails(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getTestDetails");
        }
        return modifydto;
    }

    public MentalRetardationDTO getTestDetailsAU(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        MentalRetardationDTO modifydto = new MentalRetardationDTO();
        try {
            modifydto = mentaldao.getTestDetailsAU(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTestDetailsAU", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getTestDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTestDetailsAU", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getTestDetailsAU");
        }
        return modifydto;
    }

    public void insertMRBinetKamatDetailedTestDetails(DataSource ds, MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {
            mentaldao.insertMRBinetKamatDetailedTestDetails(ds, mRBinetKamatDetailedTestDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMRBinetKamatDetailedTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertMRBinetKamatDetailedTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertMRBinetKamatDetailedTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "insertMRBinetKamatDetailedTestDetails");
        }

    }

    public MRBinetKamatDetailedTestDTO getMRBinetKamatDetailedTestDetails(DataSource ds, String personcode) throws SADAREMDBException, SQLException {

        MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO = null;
        try {

            mRBinetKamatDetailedTestDTO = mentaldao.getMRBinetKamatDetailedTestDetails(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMRBinetKamatDetailedTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMRBinetKamatDetailedTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMRBinetKamatDetailedTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMRBinetKamatDetailedTestDetails");
        }
        return mRBinetKamatDetailedTestDTO;
    }

    public void updateMRBinetKamatDetailedTestDetails(DataSource ds, MRBinetKamatDetailedTestDTO mRBinetKamatDetailedTestDTO, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {

            mentaldao.updateMRBinetKamatDetailedTestDetails(ds, mRBinetKamatDetailedTestDTO, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMRBinetKamatDetailedTestDetails", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateMRBinetKamatDetailedTestDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateMRBinetKamatDetailedTestDetails", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "updateMRBinetKamatDetailedTestDetails");
        }

    }

    public ArrayList getMentalItems(DataSource datasource, String personCode) throws SADAREMDBException, SQLException {
        ArrayList data = new ArrayList();
        try {

            data = mentaldao.getMentalItems(datasource, personCode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalItems", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMentalItems");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalItems", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMentalItems");
        }


        return data;
    }

    public ArrayList getMentalItemsVsms(DataSource datasource, String personCode) throws SADAREMDBException, SQLException {
        ArrayList data = new ArrayList();
        try {

            data = mentaldao.getMentalItemsVsms(datasource, personCode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalItemsVsms", "MentalRetardationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMentalItemsVsms");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalItemsVsms", "MentalRetardationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MentalRetardationServiceImpl", "getMentalItemsVsms");
        }


        return data;
    }
}
