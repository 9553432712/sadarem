/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.SubTypeReportDAO;
import org.bf.disability.dao.TerritoryDAO;
import org.bf.disability.form.SubTypeReportForm;
import org.bf.disability.service.SubTypeReportService;

/**
 *
 * @author 747577
 */
public class SubTypeReportServiceImpl implements SubTypeReportService {

    public ArrayList getDistricts(DataSource ds) throws SADAREMDBException,SQLException {
        ArrayList distList = new ArrayList();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        distList = territoryDAO.getDistricts(ds);
        return distList;
    }

    public ArrayList getMandals(DataSource ds, String distId) throws SADAREMDBException,SQLException {
        ArrayList mandalList = new ArrayList();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        mandalList = territoryDAO.getMandals(ds, distId);
        return mandalList;
    }

    public ArrayList getVillages(DataSource ds, String distid, String mandalid, String panchayatid) throws SADAREMDBException,SQLException {
        ArrayList villageList = new ArrayList();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        villageList = territoryDAO.getVillages(ds, distid, mandalid, panchayatid);
        return villageList;
    }

    public ArrayList getHabitations(DataSource ds, String distid, String mandalid, String panchayatid, String villageId) throws SADAREMDBException,SQLException {
        ArrayList habitationsList = new ArrayList();
        TerritoryDAO territoryDAO = new TerritoryDAO();
        habitationsList = territoryDAO.getHabitations(ds, distid, mandalid, panchayatid, villageId);
        return habitationsList;

    }

    public ArrayList getSubTypeReportDetails( DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException,SQLException {
        ArrayList habitationsList = new ArrayList();
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        habitationsList = subTypeReportDAO.getSubTypeReportDetails( ds,subTypeReportForm);
        return habitationsList;
    }

    public ArrayList gettypeOfDisability(DataSource ds) throws SADAREMDBException,SQLException {
        ArrayList typeOfDisability = new ArrayList();
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        typeOfDisability = subTypeReportDAO.gettypeOfDisability(ds);
        return typeOfDisability;
    }

    public ArrayList getSubtypeOfDisability( DataSource ds, String disabilityId) throws SADAREMDBException,SQLException {
        ArrayList subtypeOfDisability = new ArrayList();
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        subtypeOfDisability = subTypeReportDAO.getSubtypeOfDisability( ds,disabilityId);
        return subtypeOfDisability;
    }

    public ArrayList getAddressDetails( DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException,SQLException {
        ArrayList subtypeOfDisability = new ArrayList();
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        subtypeOfDisability = subTypeReportDAO.getAddressDetails( ds,subTypeReportForm);
        return subtypeOfDisability;
    }

    public String getMandalName( DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException,SQLException {
        String mandalName = null;
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        mandalName = subTypeReportDAO.getMandalName( ds,subTypeReportForm);
        return mandalName;
    }

    public String getvillageName( DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException,SQLException {
        String villageName = null;
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        villageName = subTypeReportDAO.getvillageName(ds, subTypeReportForm);
        return villageName;
    }

    public String gethabitationName( DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException,SQLException {
        String habitationName = null;
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        habitationName = subTypeReportDAO.gethabitationName( ds,subTypeReportForm);
        return habitationName;
    }

    public String getdisabulityName(DataSource ds,  SubTypeReportForm subTypeReportForm) throws SADAREMDBException,SQLException {
        String disabulityName = null;
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        disabulityName = subTypeReportDAO.getdisabulityName( ds,subTypeReportForm);
        return disabulityName;
    }

    public String getsubdisabulityName( DataSource ds, SubTypeReportForm subTypeReportForm) throws SADAREMDBException,SQLException {
        String subdisabulityName = null;
        SubTypeReportDAO subTypeReportDAO = new SubTypeReportDAO();
        subdisabulityName = subTypeReportDAO.getsubdisabulityName( ds,subTypeReportForm);
        return subdisabulityName;
    }
}
