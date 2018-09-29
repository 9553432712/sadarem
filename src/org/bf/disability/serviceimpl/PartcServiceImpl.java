/*
 * PartcServiceImpl.java
 *
 * Created on September 13, 2008, 11:03 AM
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
import org.bf.disability.dto.PartCUpdateDTO;
import org.bf.disability.dao.PartCDAO;
import org.bf.disability.service.PartCService;

/**
 * This class has the implementation functionality for PartCService
 * interface.
 * @author Bapinaidu
 * @version 1.0
 */
public class PartcServiceImpl implements PartCService {

    PartCDAO partcdao = new PartCDAO();

    /** Creates a new instance of PartcServiceImpl */
    public int insertPartCService(DataSource datasource, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = partcdao.insertPartCService(datasource, partcdto, personcode, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertPartCService", "PartcServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "insertPartCService");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertPartCService", "PartcServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "insertPartCService");
        }
        return i;
    }

    public int insertPartCServiceAU(DataSource datasource, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = partcdao.insertPartCServiceAU(datasource, partcdto, personcode, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertPartCServiceAU", "PartcServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "insertPartCServiceAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertPartCServiceAU", "PartcServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "insertPartCServiceAU");
        }
        return i;
    }

    public String eligibiltyForRailwayCertificate(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        boolean exi = false;
        String result = null;
        try {
            result = partcdao.eligibiltyForRailwayCertificate(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "eligibiltyForRailwayCertificate", "PartcServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "eligibiltyForRailwayCertificate");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "eligibiltyForRailwayCertificate", "PartcServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "eligibiltyForRailwayCertificate");
        }
        return result;


    }

    public PartCUpdateDTO getLocomotorneeds(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        PartCUpdateDTO partcdto = new PartCUpdateDTO();
        try {
            partcdto = partcdao.getLocomotorneeds(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLocomotorneeds", "PartcServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "getLocomotorneeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLocomotorneeds", "PartcServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "getLocomotorneeds");
        }
        return partcdto;
    }

    public int updatePartc(DataSource datasource, PartCUpdateDTO partcdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = partcdao.updatePartc(datasource, partcdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updatePartc", "PartcServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "updatePartc");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updatePartc", "PartcServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "updatePartc");
        }
        return i;
    }

    public int insertReassesment(DataSource datasource, PartCUpdateDTO partcdto, String personcode, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = partcdao.insertReassesment(datasource, partcdto, personcode, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertReassesment", "PartcServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "insertReassesment");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertReassesment", "PartcServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PartcServiceImpl", "insertReassesment");
        }
        return i;
    }
}

