/*
 * TransverseServiceImpl.java
 *
 * Created on September 12, 2008, 12:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.TransverseDAO;
import org.bf.disability.dto.TransverseDTO;
import org.bf.disability.service.TransverseService;

/**
 * This class deals with the methods to invoke the methods 
 * that are availble in TransverseDAO class.
 */
public class TransverseServiceImpl implements TransverseService {

    /**
     * Service method that invokes the insertTransverseDetails() by passing
     * passing DataSource Object and TransverseDTO object
     */
    public int insertTransverseDetails(DataSource datasource, TransverseDTO transversedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        TransverseDAO transversedao = new TransverseDAO();
        int val = 0;
        try {
            val = transversedao.insertTransverseDetails(datasource, transversedto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertTransverseDetails", "TransverseServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseServiceImpl", "insertTransverseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertTransverseDetails", "TransverseServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseServiceImpl", "insertTransverseDetails");
        }
        return val;
    }

    /**
     * Service method that invokes the insertTransverseDetails() by passing
     * passing DataSource Object and TransverseDTO object
     */
    public int insertTransverseDetailsAU(DataSource datasource, TransverseDTO transversedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        TransverseDAO transversedao = new TransverseDAO();
        int val = 0;
        try {
            val = transversedao.insertTransverseDetailsAU(datasource, transversedto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertTransverseDetailsAU", "TransverseServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseServiceImpl", "insertTransverseDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertTransverseDetailsAU", "TransverseServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseServiceImpl", "insertTransverseDetailsAU");
        }
        return val;
    }

    /**
     * Service method that invokes the insertTransverseDetails() by passing
     * passing DataSource Object and Person_Code
     */
    public TransverseDTO getTransverseDetails(DataSource datasource, String personcode)
            throws SADAREMDBException, SQLException {
        TransverseDAO transversedao = new TransverseDAO();
        TransverseDTO transverseupdatedto = new TransverseDTO();
        transverseupdatedto = transversedao.getTransverseDetails(datasource, personcode);
        return transverseupdatedto;
    }

    /**
     * Service method that invokes the insertTransverseDetails() by passing
     * passing DataSource Object and TransverseDTO object
     */
    public void updateTransverseDetails(DataSource datasource, TransverseDTO transverseupdatedto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        TransverseDAO transversedao = new TransverseDAO();
        try {
            transversedao.updateTransverseDetails(datasource, transverseupdatedto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateTransverseDetails", "TransverseServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseServiceImpl", "updateTransverseDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateTransverseDetails", "TransverseServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransverseServiceImpl", "updateTransverseDetails");
        }
    }
}

