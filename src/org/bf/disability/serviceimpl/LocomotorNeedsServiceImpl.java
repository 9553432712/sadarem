/*
 * LocomotorNeedsServiceImpl.java
 *
 * Created on September 12, 2008, 4:50 PM
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
import org.bf.disability.dao.LocomotorneedsDAO;
import org.bf.disability.dto.LocomotorneedsDTO;
import org.bf.disability.service.LocomotorneedsService;

/**
 * This class has the implementation functionality for LocomotorneedsService
 * interface.
 * @author Bapinaidu
 * @version 1.0
 */
public class LocomotorNeedsServiceImpl implements LocomotorneedsService {

    LocomotorneedsDAO locomotorneedsdao = new LocomotorneedsDAO();

    /** Creates a new instance of LocomotorNeedsServiceImpl */
    public int insertLocomotorneeds(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = locomotorneedsdao.insertLocomotorneeds(datasource, locomotorneedsdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLocomotorneeds", "LocomotorNeedsServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorNeedsServiceImpl", "insertLocomotorneeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLocomotorneeds", "LocomotorNeedsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorNeedsServiceImpl", "insertLocomotorneeds");
        }
        return i;
    }

    public LocomotorneedsDTO getLocomotorneeds(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        LocomotorneedsDTO locomotorneedsdto = new LocomotorneedsDTO();
        try {
            locomotorneedsdto = locomotorneedsdao.getLocomotorneeds(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLocomotorneeds", "LocomotorNeedsServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorNeedsServiceImpl", "getLocomotorneeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getLocomotorneeds", "LocomotorNeedsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorNeedsServiceImpl", "getLocomotorneeds");
        }
        return locomotorneedsdto;
    }

    public int updateLocomotorneeds(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = locomotorneedsdao.updateLocomotorneeds(datasource, locomotorneedsdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateLocomotorneeds", "LocomotorNeedsServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorNeedsServiceImpl", "updateLocomotorneeds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateLocomotorneeds", "LocomotorNeedsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorNeedsServiceImpl", "updateLocomotorneeds");
        }
        return i;
    }

    public int insertLocomotorneedsAU(DataSource datasource, LocomotorneedsDTO locomotorneedsdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i;
        try {
            i = locomotorneedsdao.insertLocomotorneedsAU(datasource, locomotorneedsdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLocomotorneedsAU", "LocomotorNeedsServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorNeedsServiceImpl", "insertLocomotorneedsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertLocomotorneedsAU", "LocomotorNeedsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LocomotorNeedsServiceImpl", "insertLocomotorneedsAU");
        }
        return i;
    }
}   
   
   

