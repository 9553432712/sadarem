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
import org.bf.disability.dao.MandalWiseClusterReportDAO;
import org.bf.disability.form.MandalWiseClusterReportForm;
import org.bf.disability.service.MandalWiseClusterReportService;

/**
 *
 * @author 695536
 */
public class MandalWiseClusterReportServiceImpl implements MandalWiseClusterReportService {

    public ArrayList getDistrictNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList DistrictName = new ArrayList();
        MandalWiseClusterReportDAO dataDao = new MandalWiseClusterReportDAO();
        try {
            DistrictName = dataDao.getDistrictNames(ds, pwdReportsForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictNames", "MandalWiseClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportServiceImpl", "getDistrictNames");
        }
        return DistrictName;
    }

    public ArrayList getMandalNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList MandalName = new ArrayList();
        MandalWiseClusterReportDAO dataDao = new MandalWiseClusterReportDAO();
        try {
            MandalName = dataDao.getMandalNames(ds, pwdReportsForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMandalNames", "MandalWiseClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportServiceImpl", "getMandalNames");
        }
        return MandalName;
    }

    public ArrayList getVillageNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList VillageName = new ArrayList();
        MandalWiseClusterReportDAO dataDao = new MandalWiseClusterReportDAO();
        try {
            VillageName = dataDao.getVillageNames(ds, pwdReportsForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getVillageNames", "MandalWiseClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportServiceImpl", "getVillageNames");
        }
        return VillageName;
    }

    public ArrayList getPwdData(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList pwdData = new ArrayList();
        MandalWiseClusterReportDAO dataDao = new MandalWiseClusterReportDAO();
        try {
            pwdData = dataDao.getPwdData(ds, pwdReportsForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPwdData", "MandalWiseClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportServiceImpl", "getPwdData");
        }
        return pwdData;
    }

    public ArrayList getPwdData1(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList pwdData1 = new ArrayList();
        MandalWiseClusterReportDAO dataDao = new MandalWiseClusterReportDAO();
        try {
            pwdData1 = dataDao.getPwdData1(ds, pwdReportsForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPwdData1", "MandalWiseClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportServiceImpl", "getPwdData1");
        }
        return pwdData1;
    }

    public ArrayList getPwdData2(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList pwdData2 = new ArrayList();
        MandalWiseClusterReportDAO dataDao = new MandalWiseClusterReportDAO();
        try {
            pwdData2 = dataDao.getPwdData2(ds, pwdReportsForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getPwdData2", "MandalWiseClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportServiceImpl", "getPwdData2");
        }
        return pwdData2;
    }
}
