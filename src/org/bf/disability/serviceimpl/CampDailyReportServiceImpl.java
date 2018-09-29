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
import org.bf.disability.dao.CampDailyReportDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.CampDetailsDTO;
import org.bf.disability.form.CampDailyReportForm;
import org.bf.disability.form.CampDetailsForm;
import org.bf.disability.service.CampDailyReportService;

/**
 *
 * @author SADAREM
 */
public class CampDailyReportServiceImpl implements CampDailyReportService {

    public ArrayList getCampBasedOnLoginDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {


        ArrayList campList = new ArrayList();

        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();
        try {

            campList = campDailyReportDAO.getCampBasedOnLoginDetails(ds, district_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetails", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampBasedOnLoginDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampBasedOnLoginDetails", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampBasedOnLoginDetails");
        }
        return campList;

    }

    public ArrayList getCampDailyReportDetails(DataSource ds, String district_id) throws SADAREMDBException, SQLException {


        ArrayList campList = new ArrayList();

        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();
        try {

            campList = campDailyReportDAO.getCampDailyReportDetails(ds, district_id);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDailyReportDetails", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampDailyReportDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDailyReportDetails", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampDailyReportDetails");
        }
        return campList;

    }

    public ArrayList getDisabilityDetails(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList DisabilityList = new ArrayList();
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();

        try {
            DisabilityList = campDailyReportDAO.getDisabilityDetails(ds);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityDetails", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getDisabilityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDisabilityDetails", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getDisabilityDetails");
        }
        return DisabilityList;
    }

    public int insertPwdAssessementDetails(DataSource ds, CampDailyReportForm campDailyReportForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException {

        int i = 0;
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();

        try {

            i = campDailyReportDAO.insertPwdAssessementDetails(ds, campDailyReportForm, districtid, loginID, systemIP);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPwdAssessementDetails", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "insertPwdAssessementDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertPwdAssessementDetails", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "insertPwdAssessementDetails");
        }
        return i;
    }

    public int getCampDetailsDateWise(DataSource ds, CampDetailsForm sueRaiseApprovalForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException {
        int i = 0;
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();

        try {

            i = campDailyReportDAO.getCampDetailsDateWise(ds, sueRaiseApprovalForm, districtid, loginID, systemIP);

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampDetailsDateWise", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "insertCampDetailsDateWise");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampDetailsDateWise", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "insertCampDetailsDateWise");
        }
        return i;
    }

    public int insertCampDetailsDateWise(DataSource ds, CampDetailsForm sueRaiseApprovalForm, String districtid, String loginID, String systemIP) throws SADAREMDBException, SQLException {
        int i = 0;
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();

        try {

            i = campDailyReportDAO.insertCampDetailsDateWise(ds, sueRaiseApprovalForm, districtid, loginID, systemIP);

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampDetailsDateWise", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "insertCampDetailsDateWise");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCampDetailsDateWise", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "insertCampDetailsDateWise");
        }
        return i;
    }

    public ArrayList getCampReportBasedOnMonthYearDistrict(DataSource ds, CampDetailsDTO campDetailsDTO, String flag) throws SADAREMDBException, SQLException {
        ArrayList DisabilityList = new ArrayList();
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();

        try {
            DisabilityList = campDailyReportDAO.getCampReportBasedOnMonthYearDistrict(ds, campDetailsDTO, flag);

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampReportBasedOnMonthYearDistrict", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampReportBasedOnMonthYearDistrict");
        } catch (Exception sqlEx) 
        {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampReportBasedOnMonthYearDistrict", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampReportBasedOnMonthYearDistrict");
        }
        return DisabilityList;
    }

    public ArrayList getCampDisabilityWiseReport(DataSource ds, CampDetailsDTO campDetailsDTO) throws SADAREMDBException, SQLException {
        ArrayList campReport = new ArrayList();
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();

        try {
            campReport = campDailyReportDAO.getCampDisabilityWiseReport(ds, campDetailsDTO);

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDisabilityWiseReport", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampDisabilityWiseReport");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDisabilityWiseReport", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampDisabilityWiseReport");
        }
        return campReport;
    }

    public String getCampName(DataSource ds, String campId) throws SADAREMDBException, SQLException {
        String campName = "";
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();
        try {
            campName = campDailyReportDAO.getCampName(ds, campId);

        } catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampName", "CampDailyReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampName");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampName", "CampDailyReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CampDailyReportServiceImpl", "getCampName");
        }
        return campName;
    }

    public ArrayList getDisabilityList(DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList disabilityList = new ArrayList();
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();
        try {
            disabilityList = campDailyReportDAO.getDisabilityList(ds);
            //System.out.println("disabilityList (Subbu) : "+disabilityList);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return disabilityList;
    }

    /**
     * This method is for gettign the precious camp assessment details
     * @param ds
     * @param campDetailsForm
     * @return
     * @throws SADAREMDBException
     * @throws SQLException
     */
    public String checkCampAssessmentStatus(DataSource ds, CampDetailsForm campDetailsForm) throws SADAREMDBException, SQLException {
        String status = null;
        CampDailyReportDAO campDailyReportDAO = new CampDailyReportDAO();
        try {
            status = campDailyReportDAO.checkCampAssessmentStatus(ds, campDetailsForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
