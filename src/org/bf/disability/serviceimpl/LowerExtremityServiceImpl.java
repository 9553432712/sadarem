/*
 * LowerExtremityServiceImpl.java
 *
 * Created on September 12, 2008, 1:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.LowerExtremityDAO;
import org.bf.disability.dto.LowerExtremityDTO;
import org.bf.disability.service.LowerExtremityService;

/**
 * This class has the implementation functionality for LowerExtremityService
 * interface.
 * @author Bapinaidu
 * @version 1.0
 */
public class LowerExtremityServiceImpl implements LowerExtremityService {

    LowerExtremityDAO lowerextremityupdatedao = new LowerExtremityDAO();

    /**
     * Creates a new instance of LowerExtremityServiceImpl
     */
    public int insertLowerExtremityDetails(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int lower;
        try {
            lower = lowerextremityupdatedao.insertLowerExtremityDetails(datasource, lowerextremitydto, request);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLowerExtremityDetails", "LowerExtremityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityServiceImpl", "insertLowerExtremityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLowerExtremityDetails", "LowerExtremityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityServiceImpl", "insertLowerExtremityDetails");
        }
        return lower;
    }

    public LowerExtremityDTO getLowerExtremityDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        LowerExtremityDTO lowerextremitydto = new LowerExtremityDTO();

        try {
            lowerextremitydto = lowerextremityupdatedao.getLowerExtremityDetails(datasource, personcode);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLowerExtremityDetails", "LowerExtremityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityServiceImpl", "getLowerExtremityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLowerExtremityDetails", "LowerExtremityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityServiceImpl", "getLowerExtremityDetails");
        }
        return lowerextremitydto;
    }

    public int updateLowerExtremityDetails(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int i;
        try {
            i = lowerextremityupdatedao.updateLowerExtremityDetails(datasource, lowerextremitydto, request);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateLowerExtremityDetails", "LowerExtremityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityServiceImpl", "updateLowerExtremityDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateLowerExtremityDetails", "LowerExtremityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityServiceImpl", "updateLowerExtremityDetails");
        }
        return i;
    }

    public int insertLowerExtremityDetailsAU(DataSource datasource, LowerExtremityDTO lowerextremitydto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int lower;
        try {
            lower = lowerextremityupdatedao.insertLowerExtremityDetailsAU(datasource, lowerextremitydto, request);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLowerExtremityDetailsAU", "LowerExtremityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityServiceImpl", "insertLowerExtremityDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLowerExtremityDetailsAU", "LowerExtremityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LowerExtremityServiceImpl", "insertLowerExtremityDetailsAU");
        }
        return lower;
    }
}
    
    
    
    
    

