/*
 * StatusForPWDReportServiceImpl.java
 *
 * Created on November 25, 2008, 4:07 PM
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
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.StatusForPWDReportDAO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.service.StatusForPWDReportService;

/**
 * This class has the implementation functionality for StatusForPWDReportService
 * interface.
 * @author SVS Ganesh
 * @version 1.0
 */
public class StatusForPWDReportServiceImpl implements StatusForPWDReportService {

    /** Creates a new instance of StatusForPWDReportServiceImpl */
    public StatusForPWDReportServiceImpl() {
    }

    public ArrayList getDistricts(DataSource datasource,
            TerritoryDTO territorydto) throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        StatusForPWDReportDAO statusforpwdreportdao = new StatusForPWDReportDAO();
        try {
            districtlist = statusforpwdreportdao.getDistricts(datasource, territorydto);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistricts", "StatusForPWDReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportServiceImpl", "getDistricts");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistricts", "StatusForPWDReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportServiceImpl", "getDistricts");
        }
        return districtlist;
    }

    public ArrayList getMandals(DataSource datasource,
            TerritoryDTO territorydto) throws SADAREMDBException, SQLException {
        ArrayList mandallist = new ArrayList();
        StatusForPWDReportDAO statusforpwdreportdao = new StatusForPWDReportDAO();
        try {
            mandallist = statusforpwdreportdao.getMandals(datasource, territorydto);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "StatusForPWDReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportServiceImpl", "getMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "StatusForPWDReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportServiceImpl", "getMandals");
        }
        return mandallist;
    }

    public ArrayList getVillages(DataSource datasource,
            TerritoryDTO territorydto) throws SADAREMDBException, SQLException {
        ArrayList villagelist = new ArrayList();
        StatusForPWDReportDAO statusforpwdreportdao = new StatusForPWDReportDAO();
        try {
            villagelist = statusforpwdreportdao.getVillages(datasource, territorydto);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillages", "StatusForPWDReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportServiceImpl", "getVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillages", "StatusForPWDReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportServiceImpl", "getVillages");
        }

        return villagelist;
    }

    public ArrayList getHabitations(DataSource datasource,
            TerritoryDTO territorydto) throws SADAREMDBException, SQLException {
        ArrayList habitationlist = new ArrayList();
        StatusForPWDReportDAO statusforpwdreportdao =
                new StatusForPWDReportDAO();
        try {
            habitationlist = statusforpwdreportdao.getHabitations(datasource, territorydto);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitations", "StatusForPWDReportServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportServiceImpl", "getHabitations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitations", "StatusForPWDReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "StatusForPWDReportServiceImpl", "getHabitations");
        }
        return habitationlist;
    }
}
