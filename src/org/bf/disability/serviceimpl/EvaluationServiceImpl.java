/*
 * EvaluationServiceImpl.java
 *
 * Created on September 12, 2008, 12:50 PM
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
import org.bf.disability.dao.EvaluationDAO;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.EvaluationDTO;
import org.bf.disability.service.EvaluationService;

/**
 * This class has the implementation functionality for EvaluationService
 * interface.
 * @author Sunima Dilla
 * @version 1.0
 */
public class EvaluationServiceImpl implements EvaluationService {

    EvaluationDAO evaluationdao = new EvaluationDAO();
    EvaluationDTO evaluationdto = new EvaluationDTO();

    public int insertData(DataSource ds, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int evaluationdetails = 0;
        try {

            evaluationdetails = evaluationdao.insertData(ds, evaluationdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertData", "EvaluationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "insertData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertData", "EvaluationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "insertData");
        }
        return evaluationdetails;
    }

    public int insertDataAU(DataSource ds, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int evaluationdetails = 0;
        try {

            evaluationdetails = evaluationdao.insertDataAU(ds, evaluationdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDataAU", "EvaluationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "insertDataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDataAU", "EvaluationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "insertDataAU");
        }
        return evaluationdetails;
    }

    public EvaluationDTO getEvaluationDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        EvaluationDTO evaluationdto = new EvaluationDTO();
        try {
            evaluationdto = evaluationdao.getEvaluationDetails(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getEvaluationDetails", "EvaluationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "getEvaluationDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getEvaluationDetails", "EvaluationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "getEvaluationDetails");
        }
        return evaluationdto;
    }

    public void updateDetails(DataSource datasource, EvaluationDTO evaluationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {
            evaluationdao.updateDetails(datasource, evaluationdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDetails", "EvaluationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "updateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDetails", "EvaluationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "updateDetails");
        }
    }

    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        boolean bool;
        try {


            bool = evaluationdao.checkPersoncode(personcode, ds);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "EvaluationServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "EvaluationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "EvaluationServiceImpl", "checkPersoncode");
        }
        return bool;
    }
}
