/*
 * CommonPersonalReportServiceImpl.java
 *
 * Created on November 26, 2008, 1:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.CommonPersonalReportDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.CommonReportDTO;
import org.bf.disability.service.CommonPersonalReportService;

/**
 * This class has the implementation functionality for CommonPersonalReportService interface.
 * @author SVS Ganesh
 * @version 1.0
 */
public class CommonPersonalReportServiceImpl implements CommonPersonalReportService {

    /** Creates a new instance of CommonPersonalReportServiceImpl */
    public CommonPersonalReportServiceImpl() {
    }

    public ArrayList getPersonalReportDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String surgerytype, int start, int endrange) throws SADAREMDBException, SQLException {
        ArrayList personalReportDistrictDetails = new ArrayList();
        CommonPersonalReportDAO reportdao = new CommonPersonalReportDAO();
        try {
            personalReportDistrictDetails = reportdao.getPersonalReportDetails(datasource, surgeryreportdto, surgerytype, start, endrange);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportDetails", "CommonPersonalReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportServiceImpl", "getPersonalReportDetails");
        }
        return personalReportDistrictDetails;

    }

    public ArrayList getPersonalReportformandalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String surgerytype, int start, int endrange) throws SADAREMDBException, SQLException {
        ArrayList personalReportMandalDetails = new ArrayList();
        CommonPersonalReportDAO reportdao = new CommonPersonalReportDAO();
        try {
            personalReportMandalDetails = reportdao.getPersonalReportformandalDetails(datasource, surgeryreportdto, surgerytype, start, endrange);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportformandalDetails", "CommonPersonalReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportServiceImpl", "getPersonalReportformandalDetails");
        }
        return personalReportMandalDetails;
    }

    public ArrayList getPersonalReportforvillageDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String surgerytype, int start, int endrange) throws SADAREMDBException, SQLException {
        ArrayList personalReportVillageDetails = new ArrayList();
        CommonPersonalReportDAO reportdao = new CommonPersonalReportDAO();
        try {
            personalReportVillageDetails = reportdao.getPersonalReportforvillageDetails(datasource, surgeryreportdto, surgerytype, start, endrange);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportforvillageDetails", "CommonPersonalReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportServiceImpl", "getPersonalReportforvillageDetails");
        }
        return personalReportVillageDetails;
    }

    public ArrayList getPersonalReportforhabitationDetails(DataSource datasource, CommonReportDTO surgeryreportdto, String surgerytype, int start, int endrange) throws SADAREMDBException, SQLException {

        ArrayList personalReporthabitationDetails = new ArrayList();
        CommonPersonalReportDAO reportdao = new CommonPersonalReportDAO();
        try {
            personalReporthabitationDetails = reportdao.getPersonalReportforhabitationDetails(datasource, surgeryreportdto, surgerytype, start, endrange);
        }  catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonalReportforhabitationDetails", "CommonPersonalReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportServiceImpl", "getPersonalReportforhabitationDetails");
        }
        return personalReporthabitationDetails;
    }

    public ArrayList getNiramayaDistrictPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException, SQLException {

        ArrayList niaramayaPersonalReportDistrictDetails = new ArrayList();
        CommonPersonalReportDAO reportdao = new CommonPersonalReportDAO();
        try {
            niaramayaPersonalReportDistrictDetails = reportdao.getNiramayaDistrictPersonalDetails(datasource, surgeryreportdto, start, endrange, disabilityName);
        }  catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaDistrictPersonalDetails", "CommonPersonalReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportServiceImpl", "getNiramayaDistrictPersonalDetails");
        }
        return niaramayaPersonalReportDistrictDetails;
    }

    public ArrayList getNiramayaMandalPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException, SQLException {

        ArrayList niaramayaPersonalReportMandalDetails = new ArrayList();
        CommonPersonalReportDAO reportdao = new CommonPersonalReportDAO();
        try {
            niaramayaPersonalReportMandalDetails = reportdao.getNiramayaMandalPersonalDetails(datasource, surgeryreportdto, start, endrange, disabilityName);
        }  catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaMandalPersonalDetails", "CommonPersonalReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportServiceImpl", "getNiramayaMandalPersonalDetails");
        }
        return niaramayaPersonalReportMandalDetails;
    }

    public ArrayList getNiramayaVillagePersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException, SQLException {

        ArrayList niaramayaPersonalReportVillageDetails = new ArrayList();
        CommonPersonalReportDAO reportdao = new CommonPersonalReportDAO();
        try {
            niaramayaPersonalReportVillageDetails = reportdao.getNiramayaVillagePersonalDetails(datasource, surgeryreportdto, start, endrange, disabilityName);
        }  catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaVillagePersonalDetails", "CommonPersonalReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportServiceImpl", "getNiramayaVillagePersonalDetails");
        }
        return niaramayaPersonalReportVillageDetails;
    }

    public ArrayList getNiramayaHabitationPersonalDetails(DataSource datasource, CommonReportDTO surgeryreportdto, int start, int endrange, String disabilityName) throws SADAREMDBException, SQLException {

        ArrayList niaramayaPersonalReportHabitationDetails = new ArrayList();
        CommonPersonalReportDAO reportdao = new CommonPersonalReportDAO();
        try {
            niaramayaPersonalReportHabitationDetails = reportdao.getNiramayaHabitationPersonalDetails(datasource, surgeryreportdto, start, endrange, disabilityName);
        }  catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getNiramayaHabitationPersonalDetails", "CommonPersonalReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "CommonPersonalReportServiceImpl", "getNiramayaHabitationPersonalDetails");
        }
        return niaramayaPersonalReportHabitationDetails;
    }
}
