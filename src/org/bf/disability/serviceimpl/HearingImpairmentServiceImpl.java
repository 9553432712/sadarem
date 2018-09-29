/*
 * HearingImpairmentServiceImpl.java
 *
 * Created on September 12, 2008, 5:46 PM
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
import org.bf.disability.dao.HearingImpairmentDao;
import org.bf.disability.dto.HearingImpairmentDto;
import org.bf.disability.form.HearingImpairmentActionForm;
import org.bf.disability.service.HearingImpairmentService;

/**
 * This class has the implementation functionality for HearingImpairmentService
 * interface.
 * @author Sunima Dilla
 * @version 1.0
 */
public class HearingImpairmentServiceImpl implements HearingImpairmentService {

    HearingImpairmentDao hearingdao = new HearingImpairmentDao();
    HearingImpairmentDto hearingdto = new HearingImpairmentDto();

    public int insertData(DataSource ds, HearingImpairmentDto hearingdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int hearingdetails = 0;
        try {

            hearingdetails = hearingdao.insertData(ds, hearingdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertData", "HearingImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "insertData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertData", "HearingImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "insertData");
        }
        return hearingdetails;
    }

    public HearingImpairmentDto getHearingDetails(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        HearingImpairmentDto hearingdto = new HearingImpairmentDto();
        try {
            hearingdto = hearingdao.getHearingDetails(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHearingDetails", "HearingImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "getHearingDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getHearingDetails", "HearingImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "getHearingDetails");
        }
        return hearingdto;
    }

    public int insertDataAU(DataSource ds, HearingImpairmentDto hearingdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int hearingdetails = 0;
        try {

            hearingdetails = hearingdao.insertDataAU(ds, hearingdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDataAU", "HearingImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "insertDataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertDataAU", "HearingImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "insertDataAU");
        }
        return hearingdetails;
    }

    public void updateDetails(DataSource datasource, HearingImpairmentActionForm hearingform, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {
            hearingdao.updateDetails(datasource, hearingform, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDetails", "HearingImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "updateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "updateDetails", "HearingImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "updateDetails");
        }
    }

    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        boolean bool;
        try {
            bool = hearingdao.checkPersoncode(personcode, ds);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "HearingImpairmentServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "HearingImpairmentServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "HearingImpairmentServiceImpl", "checkPersoncode");
        }
        return bool;
    }
}
