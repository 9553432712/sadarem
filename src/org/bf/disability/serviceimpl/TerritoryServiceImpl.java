/*
 * TerritoryServiceImpl.java
 *
 * Created on September 12, 2008, 5:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dto.TerritoryDTO;
import org.bf.disability.service.TerritoryService;

/**
 * This class has the implementation functionality for TerritoryService
 * interface.
 * @author SVS Ganesh
 * @version 1.0
 */
public class TerritoryServiceImpl implements TerritoryService {

    TerritoryDAO territorydao = new TerritoryDAO();
    TerritoryDTO territory = new TerritoryDTO();

    public String getDistrictsName(DataSource datasource, String districtid) throws SADAREMDBException, SQLException {

        String districtName = null;
        try {
            districtName = territorydao.getDistrictsName(datasource, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistrictsName", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getDistrictsName");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistrictsName", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getDistrictsName");
        }
        return districtName;
    }

    public ArrayList getDistricts(DataSource datasource) throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        try {
            districtlist = territorydao.getDistricts(datasource);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistricts", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getDistricts");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getDistricts", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getDistricts");
        }
        return districtlist;
    }

    public ArrayList getMandals(DataSource datasource, String districtid) throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        try {
            districtlist = territorydao.getMandals(datasource, districtid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getMandals");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getMandals");
        }
        return districtlist;
    }

    public ArrayList getPanchayats(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        try {
            districtlist = territorydao.getPanchayats(datasource, districtid, mandalid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPanchayats", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getPanchayats");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPanchayats", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getPanchayats");
        }
        return districtlist;
    }

    public ArrayList getVillages(DataSource datasource, String districtid, String mandalid, String panchayatid) throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        try {
            districtlist = territorydao.getVillages(datasource, districtid, mandalid, panchayatid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillages", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getVillages");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillages", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getVillages");
        }
        return districtlist;
    }

    public ArrayList getHabitations(DataSource datasource, String districtid, String mandalid, String panchayatid, String villageid) throws SADAREMDBException, SQLException {
        ArrayList districtlist = new ArrayList();
        try {
            districtlist = territorydao.getHabitations(datasource, districtid, mandalid, panchayatid, villageid);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitations", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getHabitations");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitations", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getHabitations");
        }
        return districtlist;
    }

    public String getHabitationCode(String districtid, String mandalid, String panchayatid, String assemblyid, String villageid, String habitationid, DataSource datasource) throws SADAREMDBException, SQLException {
        String habitationUpdatedCode = null;
        try {
            habitationUpdatedCode = territorydao.getHabitationCode(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, datasource);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationCode", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getHabitationCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationCode", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getHabitationCode");
        }
        return habitationUpdatedCode;
    }

    public ArrayList getPersonCode(String districtid, String mandalid, String panchayatid, String villageid, String habitationid, String personstatus, DataSource datasource) throws SADAREMDBException, SQLException {

        ArrayList personcode = new ArrayList();
        try {
            personcode = territorydao.getPersonCode(districtid, mandalid, panchayatid, villageid, habitationid, personstatus, datasource);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonCode", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonCode", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getPersonCode");
        }
        return personcode;
    }

    public TerritoryDTO getTotalValues(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        try {
            territory = territorydao.getTotalValues(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTotalValues", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getTotalValues");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTotalValues", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getTotalValues");
        }
        return territory;
    }

    public int totalValueInsert(DataSource datasource, TerritoryDTO territoryDTO, LinkedList list, LinkedList list1, double totaldisability, String personcode, String loginid, String systemip, int maxMultipleId, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int totalvalueinsert = 0;
        try {
            totalvalueinsert = territorydao.totalValueInsert(datasource, territoryDTO, list, list1, totaldisability, personcode, loginid, systemip, maxMultipleId, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "totalValueInsert", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "totalValueInsert");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "totalValueInsert", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "totalValueInsert");
        }
        return totalvalueinsert;
    }

    public int totalValueUpdate(DataSource datasource, TerritoryDTO territoryDTO, LinkedList list, LinkedList list1, double totaldisability, String personcode, String loginid, String systemip, int maxMultipleId, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int totalvalueinsert = 0;
        try {
            totalvalueinsert = territorydao.totalValueUpdate(datasource, territoryDTO, list, list1, totaldisability, personcode, loginid, systemip, maxMultipleId, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "totalValueUpdate", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "totalValueUpdate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "totalValueUpdate", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "totalValueUpdate");
        }
        return totalvalueinsert;
    }

    public ArrayList getPersonStatus(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        ArrayList personStatusList = new ArrayList();
        try {
            personStatusList = territorydao.getPersonStatus(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonStatus", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getPersonStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPersonStatus", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getPersonStatus");
        }
        return personStatusList;
    }

    public ArrayList getVillageAll(DataSource datasource, String districeId, String mandalId) throws SADAREMDBException, SQLException {
        ArrayList personStatusList = new ArrayList();
        try {
            personStatusList = territorydao.getVillageAll(datasource, districeId, mandalId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageAll", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getVillageAll");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageAll", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getVillageAll");
        }
        return personStatusList;
    }

    public String getHabitationCodeRationCard(String districtid, String mandalid, String panchayatid, String assemblyid, String villageid, String habitationid, DataSource datasource) throws SADAREMDBException, SQLException {
        String habitationUpdatedCode = null;
        try {
            habitationUpdatedCode = territorydao.getHabitationCode(districtid, mandalid, panchayatid, assemblyid, villageid, habitationid, datasource);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationCodeRationCard", "TerritoryServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getHabitationCodeRationCard");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitationCodeRationCard", "TerritoryServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TerritoryServiceImpl", "getHabitationCodeRationCard");
        }
        return habitationUpdatedCode;
    }
}
