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
import org.bf.disability.dao.RationCardReportDAO;
import org.bf.disability.service.RationCardReportService;

/**
 *
 * @author SADAREM
 */
public class RationCardReportServiceImpl implements RationCardReportService {

    public ArrayList getMandals(DataSource datasource, String districtid) throws SADAREMDBException, SQLException {

        ArrayList mandalList = new ArrayList();

        RationCardReportDAO rationCardReportDAO = new RationCardReportDAO();

        try {
            mandalList = rationCardReportDAO.getMandals(datasource, districtid);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "RationCardReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportServiceImpl", "getMandals");
        }
        return mandalList;
    }

    public ArrayList getVillageAll(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException {

        ArrayList villageList = new ArrayList();
        RationCardReportDAO rationCardReportDAO = new RationCardReportDAO();
        try {
            villageList = rationCardReportDAO.getVillageAll(datasource, districtid, mandalid);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageAll", "RationCardReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportServiceImpl", "getVillageAll");
        }


        return villageList;

    }

    public ArrayList getHabitation(DataSource datasource, String districtid, String mandalid, String villageid) throws SADAREMDBException, SQLException {

        ArrayList villageList = new ArrayList();
        RationCardReportDAO rationCardReportDAO = new RationCardReportDAO();
        try {
            villageList = rationCardReportDAO.getHabitation(datasource, districtid, mandalid, villageid);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHabitation", "RationCardReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportServiceImpl", "getHabitation");
        }


        return villageList;

    }

    public ArrayList getRationPersonalReportDetails(DataSource ds, String district_id, String mandal_id, String village_id, String habitation_id, String personStatus) throws SADAREMDBException, SQLException {

        ArrayList rationCardPersonalDetails = new ArrayList();
        RationCardReportDAO rationCardReportDAO = new RationCardReportDAO();
        try {
            rationCardPersonalDetails = rationCardReportDAO.getRationPersonalReportDetails(ds, district_id, mandal_id, village_id, habitation_id, personStatus);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRationPersonalReportDetails", "RationCardReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportServiceImpl", "getRationPersonalReportDetails");
        }

        return rationCardPersonalDetails;
    }

    public ArrayList getRationCardMembersDetails(DataSource ds,DataSource civilDs, String rationCardNumber, HttpServletRequest request) throws SADAREMDBException, SQLException {

        ArrayList rationCardPersonalDetails = new ArrayList();
        RationCardReportDAO rationCardReportDAO = new RationCardReportDAO();
        try {
            rationCardPersonalDetails = rationCardReportDAO.getRationCardMembersDetails(ds,civilDs, rationCardNumber, request);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getRationCardMembersDetails", "RationCardReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RationCardReportServiceImpl", "getRationCardMembersDetails");
        }

        return rationCardPersonalDetails;



    }
}




