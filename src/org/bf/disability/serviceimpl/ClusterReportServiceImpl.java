/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ClusterReportDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.ClusterReportForm;
import org.bf.disability.service.ClusterReportService;
import javax.sql.DataSource;
import org.bf.disability.dao.ProfileReportDAO;
/**
 *
 * @author 747577
 */
public class ClusterReportServiceImpl implements ClusterReportService {

    public ArrayList getReportTypes(DataSource ds) throws SADAREMDBException,SQLException {
        ArrayList districtsList = new ArrayList();
        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
        try {
            districtsList = clusterReportDAO.getReportTypes(ds);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getReportTypes", "ClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getReportTypes");
        }
        return districtsList;
    }
    public ArrayList getDistricts(DataSource ds) throws SADAREMDBException,SQLException {
        ArrayList districtsList = new ArrayList();
        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
        try {
            districtsList = clusterReportDAO.getDistricts(ds);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistricts", "ClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getDistricts");
        }
        return districtsList;
    }

    public ArrayList getClusters(DataSource ds,String districtName) throws SADAREMDBException,SQLException {
        ArrayList clusterList = new ArrayList();
        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
        try {
            clusterList = clusterReportDAO.getClusters(ds,districtName);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getClusters", "ClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getClusters");
        }
        return clusterList;
    }

    public ArrayList getMandals(DataSource ds,String mandalName) throws SADAREMDBException,SQLException {
        ArrayList mandalList = new ArrayList();
        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
        try {
            mandalList = clusterReportDAO.getMandals(ds,mandalName);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getMandels", "ClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getMandels");
        }
        return mandalList;
    }

//    public ArrayList getClusterReport(DataSource ds,ClusterReportForm clusterReportForm,String reportType) throws SADAREMDBException,SQLException {
//        ArrayList clusterReportList = new ArrayList();
//        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
//        try {
//            clusterReportList = clusterReportDAO.getClusterReport(ds,clusterReportForm, reportType);
//        } catch (Exception sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getClusterReport", "ClusterReportServiceImpl", "Code");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getClusterReport");
//        }
//        return clusterReportList;
//    }
//
//     public ArrayList getClusterConfirmationNoReport(DataSource ds,ClusterReportForm clusterReportForm,String reportType) throws SADAREMDBException,SQLException {
//        ArrayList clusterReportList = new ArrayList();
//        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
//        try {
//            clusterReportList = clusterReportDAO.getClusterConfirmationNoReport(ds,clusterReportForm, reportType);
//        } catch (Exception sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getClusterConfirmationNoReport", "ClusterReportServiceImpl", "Code");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getClusterConfirmationNoReport");
//        }
//        return clusterReportList;
//    }
//      public ArrayList getDistrictClusterReport(DataSource ds,ClusterReportForm clusterReportForm,String reportType) throws SADAREMDBException,SQLException {
//        ArrayList clusterReportList = new ArrayList();
//        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
//        try {
//            clusterReportList = clusterReportDAO.getDistrictClusterReport(ds,clusterReportForm, reportType);
//        } catch (Exception sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getClusterReport", "ClusterReportServiceImpl", "Code");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getClusterReport");
//        }
//        return clusterReportList;
//    }

    public ArrayList getStateClusterReportList(DataSource ds,ClusterReportForm clusterReportForm) throws SADAREMDBException,SQLException {
        ArrayList clusterReportList = new ArrayList();
        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
        try {
            clusterReportList = clusterReportDAO.getStateClusterReportList(ds,clusterReportForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStateClusterReportList", "ClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getStateClusterReportList");
        }
        return clusterReportList;
    }

     public int getStateWiseDistrictColumnCount(DataSource ds,String reportId) throws SADAREMDBException,SQLException {
        int count = 0;
        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
        try {
            count = clusterReportDAO.getStateWiseDistrictColumnCount(ds,reportId);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStateWiseDistrictColumnCount", "ClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getStateWiseDistrictColumnCount");
        }
        return count;
    }

     public ArrayList getStateWiseDistrictColumnNames(DataSource ds,String reportId,ClusterReportForm clusterReportForm) throws SADAREMDBException,SQLException {
        ArrayList columnNames = new ArrayList();
        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
        try {
            columnNames = clusterReportDAO.getStateWiseDistrictColumnNames(ds,reportId,clusterReportForm);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStateWiseDistrictColumnNames", "ClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getStateWiseDistrictColumnNames");
        }
        return columnNames;
    }

     public ArrayList getStateWiseDistrictBreakup(DataSource ds,String reportId,int columnCount) throws SADAREMDBException,SQLException {
        ArrayList clusterReportList = new ArrayList();
        ClusterReportDAO clusterReportDAO = new ClusterReportDAO();
        try {
            clusterReportList = clusterReportDAO.getStateWiseDistrictBreakup(ds,reportId,columnCount);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getStateClusterReportList", "ClusterReportServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getStateClusterReportList");
        }
        return clusterReportList;
    }

    public ArrayList districtClusterReportList(DataSource ds, ClusterReportForm clusterReportForm) {
        ArrayList clusterReportList = new ArrayList();
        ProfileReportDAO clusterReportDAO = new ProfileReportDAO();
        try {
            clusterReportList = clusterReportDAO.districtClusterReportList(ds,clusterReportForm);
        } catch (Exception sqlEx) {
              sqlEx.printStackTrace();
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "districtClusterReportList", "ClusterReportServiceImpl", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "districtClusterReportList");
            } catch (SADAREMDBException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return clusterReportList;
    }

    public ArrayList getBreakupList(DataSource ds, String reportId, int columnsCount, ClusterReportForm clusterReportForm) {
        ArrayList clusterReportList = new ArrayList();
      ProfileReportDAO clusterReportDAO = new ProfileReportDAO();
        try {
            clusterReportList = clusterReportDAO.getBreakupList(ds,reportId,columnsCount,clusterReportForm);
        } catch (Exception sqlEx) {
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getBreakupList", "ClusterReportServiceImpl", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportServiceImpl", "getBreakupList");
            } catch (SADAREMDBException ex) {
               ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return clusterReportList;
    }
}
