/*
 * AmputationServiceImpl.java
 *
 * Created on September 12, 2008, 2:25 PM
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
import org.bf.disability.dao.AmputationDao;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.AmputationDto;
import org.bf.disability.service.AmputationService;

/**
 * This class has the implementation functionality for AmputationService interface.
 * @author Deviprasad
 * @version 1.0
 */
public class AmputationServiceImpl implements AmputationService {

    AmputationDao ampdao = new AmputationDao();
    AmputationDto ampdto = new AmputationDto();

    public void insertAmputationDetails(DataSource ds, AmputationDto ampdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {
            ampdao.insertAmputationDetails(ds, ampdto, request);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertAmputationDetails", "AmputationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationServiceImpl", "insertAmputationDetails");
        }

    }

    public int insertAmputationDetailsAU(DataSource ds, AmputationDto ampdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int i = 0;
        try {
            i = ampdao.insertAmputationDetailsAU(ds, ampdto, request);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertAmputationDetailsAU", "AmputationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationServiceImpl", "insertAmputationDetailsAU");
        }
        return i;
    }

    public AmputationDto getAmputationDetails(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        try {
            ampdto = ampdao.getAmputationDetails(personcode, ds);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getAmputationDetails", "AmputationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationServiceImpl", "getAmputationDetails");
        }
        return ampdto;
    }

    public void updateAmputationDetails(DataSource ds, AmputationDto amputationdto, HttpServletRequest request) throws SADAREMDBException, SQLException {
        try {
            ampdao.updateAmputationDetails(ds, amputationdto, request);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateAmputationDetails", "AmputationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationServiceImpl", "updateAmputationDetails");
        }
    }

    public boolean checkPersoncode(String personcode, DataSource ds) throws SADAREMDBException, SQLException {
        boolean personcodecheckflag;
        try {
            personcodecheckflag = ampdao.checkPersoncode(personcode, ds);

        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "AmputationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationServiceImpl", "checkPersoncode");
        }
        return personcodecheckflag;
    }

    public void deleteAmputaionUpdateRecord(DataSource ds, String personcode) throws SADAREMDBException, SQLException {
        try {
            ampdao.deleteAmputaionUpdateRecord(ds, personcode);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "deleteAmputaionUpdateRecord", "AmputationServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AmputationServiceImpl", "deleteAmputaionUpdateRecord");
        }

    }
}
