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
import org.bf.disability.dao.PensionForSearchReportDAO;
import org.bf.disability.service.PensionForSearchReportService;

/**
 *
 * @author 484898
 */
public class PensionForSearchReportServiceImpl implements PensionForSearchReportService {

    public ArrayList getMandals(DataSource datasource, String districtid) throws SADAREMDBException, SQLException {

        ArrayList mandalList = new ArrayList();
        PensionForSearchReportDAO pensionForSearchReportDAO = new PensionForSearchReportDAO();

        try {

            mandalList = pensionForSearchReportDAO.getMandals(datasource, districtid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMandals", "PensionForSearchReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportServiceImpl", "getMandals");
        }

        return mandalList;
    }

    public ArrayList getVillageAll(DataSource datasource, String districtid, String mandalid) throws SADAREMDBException, SQLException {

        ArrayList villagelist = new ArrayList();
        PensionForSearchReportDAO pensionForSearchReportDAO = new PensionForSearchReportDAO();

        try {
            villagelist = pensionForSearchReportDAO.getVillageAll(datasource, districtid, mandalid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVillageAll", "PensionForSearchReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportServiceImpl", "getVillageAll");
        }

        return villagelist;
    }

    public ArrayList getPhaseSearchDetails(DataSource datasource, String pensionCode, String sadaremId, String districtId, String mandalId, String villageId) throws SADAREMDBException, SQLException {

        ArrayList<Object> searchList = new ArrayList<Object>();
        PensionForSearchReportDAO pensionForSearchReportDAO = new PensionForSearchReportDAO();


        try {
            searchList = pensionForSearchReportDAO.getPhaseSearchDetails(datasource, pensionCode, sadaremId, districtId, mandalId, villageId);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getPhaseSearchDetails", "PensionForSearchReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PensionForSearchReportServiceImpl", "getPhaseSearchDetails");
        }

        return searchList;
    }
}
