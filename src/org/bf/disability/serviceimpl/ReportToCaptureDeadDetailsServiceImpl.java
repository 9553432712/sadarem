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
import org.bf.disability.dao.ReportToCaptureDeadDetailsDAO;
import org.bf.disability.service.ReportToCaptureDeadDetailsService;
import org.bf.disability.form.ReportToCaptureDeadDetailsForm;

/**
 *
 * @author 310926
 */
public class ReportToCaptureDeadDetailsServiceImpl implements ReportToCaptureDeadDetailsService {

    public ArrayList validateSADAREMID(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        ArrayList SADAREMIDValidDetails = new ArrayList();
        ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();

        try {
            SADAREMIDValidDetails = reportDAO.validateSADAREMID(ds,reportForm);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "validateSADAREMID", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "validateSADAREMID");
        }
        return SADAREMIDValidDetails;
    }
    public int alreadyExist(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        int status = 0;
        ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();

        try {
            status = reportDAO.alreadyExist(ds,reportForm);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "alreadyExist", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "alreadyExist");
        }
        return status;
    }

    public int insertDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        int status = 0;
        ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();

        try {
            status = reportDAO.insertDetails(ds,reportForm);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDetails", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "insertDetails");
        }
        return status;
    }

    public ArrayList getVoList(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        ArrayList voList = new ArrayList();
         ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();
        try {
            voList =reportDAO.getVoList(ds, reportForm);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertDetails", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "insertDetails");

        }
        return  voList;
    }

    public ArrayList voNameshgMappingDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {

        ArrayList voName = new ArrayList();
        ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();
        try {
            voName =reportDAO.voNameshgMappingDetails(ds, reportForm);
            

        } catch (Exception sqlEx) {
        int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "voNameshgMappingDetails", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "voNameshgMappingDetails");
        }
        return voName;
    }

    public ArrayList shgMappingName(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        ArrayList shgName = new ArrayList();
        ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();
        try {
        shgName= reportDAO.shgMappingName(ds, reportForm);

        } catch (Exception sqlEx) {
        int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "shgMappingName", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "shgMappingName");
        }
        return shgName;
    }

    public int getMappingCount(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        int mappingCount = 0;
        ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();

        try {
            mappingCount = reportDAO.getMappingCount(ds, reportForm);

        } catch (Exception sqlEx) {
         int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "shgMappingName", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "shgMappingName");
        }
        return  mappingCount;
    }

    public ArrayList getShgDropdownDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException 
    {
        ArrayList ShgDropdownDetails  = new ArrayList();
         ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();
        try
        {
            ShgDropdownDetails=reportDAO.getShgDropdownDetails(ds, reportForm);

        }
        catch (Exception sqlEx) 
        {
         int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgDropdownDetails", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            //throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "shgMappingName");
        }
        return  ShgDropdownDetails;
    }

    public String getShgIdDetails(DataSource ds, ReportToCaptureDeadDetailsForm reportForm) throws SADAREMDBException, SQLException {
        String shgIdDetails = null;
        ReportToCaptureDeadDetailsDAO reportDAO = new ReportToCaptureDeadDetailsDAO();
        
        try {
        shgIdDetails=reportDAO.getShgIdDetails(ds, reportForm);

        } catch (Exception sqlEx) {
         int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getShgDropdownDetails", "ReportToCaptureDeadDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ReportToCaptureDeadDetailsServiceImpl", "shgMappingName");
        }
       return shgIdDetails;
    }
}
