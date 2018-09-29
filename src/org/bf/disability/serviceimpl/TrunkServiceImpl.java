/*
 * TrunkServiceImpl.java
 *
 * Created on September 12, 2008, 3:24 PM
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
import org.bf.disability.dao.TrunkDAO;
import org.bf.disability.dto.TrunkDTO;
import org.bf.disability.service.TrunkService;

/**
 * This class has the implementation functionality for TrunkService
 * interface.
 * @author Bapinaidu
 * @version 1.0
 */
public class TrunkServiceImpl implements TrunkService {

    TrunkDAO trunkdao = new TrunkDAO();

    /** Creates a new instance of TrunkServiceImpl */
    public TrunkServiceImpl() {
    }

    public int insertTrunkDetails(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int trunkinsert;

        try {
            trunkinsert = trunkdao.insertTrunkDetails(datasource, trunkdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertTrunkDetails", "TrunkServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkServiceImpl", "insertTrunkDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertTrunkDetails", "TrunkServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkServiceImpl", "insertTrunkDetails");
        }
        return trunkinsert;

    }

    public int insertTrunkDetailsAU(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int trunkinsert;

        try {
            trunkinsert = trunkdao.insertTrunkDetailsAU(datasource, trunkdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertTrunkDetailsAU", "TrunkServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkServiceImpl", "insertTrunkDetailsAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "insertTrunkDetailsAU", "TrunkServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkServiceImpl", "insertTrunkDetailsAU");
        }
        return trunkinsert;

    }

    public TrunkDTO getTrunkDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        TrunkDTO trunkdto = new TrunkDTO();
        try {
            trunkdto = trunkdao.getTrunkDetails(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTrunkDetails", "TrunkServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkServiceImpl", "getTrunkDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getTrunkDetails", "TrunkServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkServiceImpl", "getTrunkDetails");
        }
        return trunkdto;
    }

    public int updateTrunkDetails(DataSource datasource, TrunkDTO trunkdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int trunkinsert;
        try {
            trunkinsert = trunkdao.updateTrunkDetails(datasource, trunkdto, request);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateTrunkDetails", "TrunkServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkServiceImpl", "updateTrunkDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateTrunkDetails", "TrunkServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TrunkServiceImpl", "updateTrunkDetails");
        }
        return trunkinsert;
    }
}
