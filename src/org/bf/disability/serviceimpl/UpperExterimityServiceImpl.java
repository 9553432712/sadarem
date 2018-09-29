/*
 * UpperExterimityServiceImpl.java
 *
 * Created on September 12, 2008, 12:41 PM
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
import org.bf.disability.dao.UpperExtrimityDao;
import org.bf.disability.dto.UpperExtrimityDto;
import org.bf.disability.service.UpperExtrimityService;

/**
 * This class has the implementation functionality for UpperExtremityService
 * interface.
 * @author SVS Ganesh
 * @version 1.0
 */
public class UpperExterimityServiceImpl implements UpperExtrimityService {

    UpperExtrimityDao upperextrimitydao = new UpperExtrimityDao();
    UpperExtrimityDto upperextrimitydto = new UpperExtrimityDto();
    /*
     * This method is for checking personcode 
     */

    public boolean checkPersoncode(String personcode,
            DataSource ds) throws SADAREMDBException, SQLException {
        boolean personcodecheckflag;
        try {
            personcodecheckflag = upperextrimitydao.checkPersoncode(personcode, ds);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "UpperExterimityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "checkPersoncode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "checkPersoncode", "UpperExterimityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "checkPersoncode");
        }
        return personcodecheckflag;
    }
    /*
     *  This method is for inserting data
     */

    public int inserUpperExtremityData(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        int inserupperextremitydata = 0;
        try {
            inserupperextremitydata = upperextrimitydao.inserUpperExtremityData(ds, upperextrimitydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "inserUpperExtremityData", "UpperExterimityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "inserUpperExtremityData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "inserUpperExtremityData", "UpperExterimityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "inserUpperExtremityData");
        }
        return inserupperextremitydata;
    }
    /*
     *  This method is for updating data for particular pwd
     */

    public int updateRomData(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        int updaterom = 0;
        try {
            updaterom = upperextrimitydao.updateRomData(ds, upperextrimitydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateRomData", "UpperExterimityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "updateRomData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateRomData", "UpperExterimityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "updateRomData");
        }
        return updaterom;
    }
    /*
     *  This method is for selecting data for particular pwd
     */

    public UpperExtrimityDto selectUpperExterimityData(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        try {
            upperextrimitydto =
                    upperextrimitydao.selectUpperExterimityData(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "selectUpperExterimityData", "UpperExterimityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "selectUpperExterimityData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "selectUpperExterimityData", "UpperExterimityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "selectUpperExterimityData");
        }
        return upperextrimitydto;
    }
    /*
     * This method is for deleting records for particular
     * Pwd
     */

    public void deleteUpperExtremityUpdateRecord(DataSource ds,
            String personcode)
            throws SADAREMDBException, SQLException {
        try {
            upperextrimitydao.deleteUpperExtremityUpdateRecord(ds, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "deleteUpperExtremityUpdateRecord", "UpperExterimityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "deleteUpperExtremityUpdateRecord");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "deleteUpperExtremityUpdateRecord", "UpperExterimityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "deleteUpperExtremityUpdateRecord");
        }
    }

    /*
     *  This method is for inserting data
     */
    public int inserUpperExtremityDataAU(DataSource ds,
            UpperExtrimityDto upperextrimitydto, HttpServletRequest request)
            throws SADAREMDBException, SQLException {
        int inserupperextremitydata = 0;
        try {
            inserupperextremitydata = upperextrimitydao.inserUpperExtremityDataAU(ds, upperextrimitydto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "inserUpperExtremityDataAU", "UpperExterimityServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "inserUpperExtremityDataAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "inserUpperExtremityDataAU", "UpperExterimityServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpperExterimityServiceImpl", "inserUpperExtremityDataAU");
        }
        return inserupperextremitydata;
    }
}
