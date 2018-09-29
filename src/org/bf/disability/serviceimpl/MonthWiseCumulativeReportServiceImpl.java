/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.dao.MonthWiseCumulativeReportDAO;
import org.bf.disability.form.MonthWiseCumulativeReportForm;
import org.bf.disability.service.MonthWiseCumulativeReportService;
import org.bf.disability.service.SubTypeReportService;

/**
 *
 * @author 747577
 */
public class MonthWiseCumulativeReportServiceImpl implements MonthWiseCumulativeReportService {

    public ArrayList getDistricts(DataSource ds) throws SADAREMDBException, SQLException {
        ArrayList distList = new ArrayList();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        distList = territoryDAO.getDistricts(ds);
        return distList;
    }

    public ArrayList getMandals(DataSource ds, String distId) throws SADAREMDBException, SQLException {
        ArrayList mandalList = new ArrayList();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        mandalList = territoryDAO.getMandals(ds, distId);
        return mandalList;
    }

    public ArrayList getVillages(DataSource ds, String distid, String mandalid, String panchayatid) throws SADAREMDBException, SQLException {
        ArrayList villageList = new ArrayList();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        villageList = territoryDAO.getVillages(ds, distid, mandalid, panchayatid);
        return villageList;
    }

    public ArrayList getHabitations(DataSource ds, String distid, String mandalid, String panchayatid, String villageId) throws SADAREMDBException, SQLException {
        ArrayList habitationsList = new ArrayList();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        habitationsList = territoryDAO.getHabitations(ds, distid, mandalid, panchayatid, villageId);
        return habitationsList;

    }

    public ArrayList getReportMonthWise(DataSource datasource, String fromDate, String toDate, String district, String mandal, String village) throws SADAREMDBException, SQLException, ParseException {
        ArrayList reportList = new ArrayList();
        MonthWiseCumulativeReportDAO monthDao = new MonthWiseCumulativeReportDAO();

        reportList = monthDao.getMonthReport(datasource, fromDate, toDate, district, mandal, village);
        return reportList;
    }
}
