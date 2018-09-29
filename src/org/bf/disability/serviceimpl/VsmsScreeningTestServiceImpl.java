/*
 * VsmsScreeningTestServiceImpl.java
 *
 * Created on October 16, 2008, 2:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dto.VsmsScreeningTestDTO;
import org.bf.disability.service.VsmsScreeningTestService;
import org.bf.disability.dao.VsmsScreeningTestDAO;

/**
 * This class has the implementation functionality for VsmsScreeningTestService
 * interface.
 * @author Sunima Dilla
 * @version 1.0
 */
public class VsmsScreeningTestServiceImpl implements VsmsScreeningTestService {

    VsmsScreeningTestDAO vsmstestdao = new VsmsScreeningTestDAO();

    public int insertVsmsScreeningTest(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int vsmstestdetails = 0;
        try {


            vsmstestdetails = vsmstestdao.insertVsmsScreeningTest(ds, vsmsscreeningtestdto, request);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVsmsScreeningTest", "VsmsScreeningTestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "insertVsmsScreeningTest");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVsmsScreeningTest", "VsmsScreeningTestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "insertVsmsScreeningTest");
        }
        return vsmstestdetails;
    }

    public int insertVsmsScreeningTestAU(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {

        int vsmstestdetails = 0;
        try {


            vsmstestdetails = vsmstestdao.insertVsmsScreeningTestAU(ds, vsmsscreeningtestdto, request);

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVsmsScreeningTestAU", "VsmsScreeningTestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "insertVsmsScreeningTestAU");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertVsmsScreeningTestAU", "VsmsScreeningTestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "insertVsmsScreeningTestAU");
        }
        return vsmstestdetails;
    }

    public VsmsScreeningTestDTO getVsmsScreeningTest(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        VsmsScreeningTestDTO vsmsscreeningtestdto = new VsmsScreeningTestDTO();
        try {
            vsmsscreeningtestdto = vsmstestdao.getVsmsScreeningTest(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVsmsScreeningTest", "VsmsScreeningTestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "getVsmsScreeningTest");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getVsmsScreeningTest", "VsmsScreeningTestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "getVsmsScreeningTest");
        }
        return vsmsscreeningtestdto;

    }

    public void updateVsmsScreeningTest(DataSource ds, VsmsScreeningTestDTO vsmsscreeningtestdto, HttpServletRequest request) throws SADAREMDBException, SQLException {


        try {
            vsmstestdao.updateVsmsScreeningTest(ds, vsmsscreeningtestdto, request);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateVsmsScreeningTest", "VsmsScreeningTestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "updateVsmsScreeningTest");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateVsmsScreeningTest", "VsmsScreeningTestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "updateVsmsScreeningTest");
        }
    }

    public boolean checkPersonCode(DataSource datasource, String personcode) throws SADAREMDBException, SQLException {
        boolean isPersonCodeExist;
        try {

            isPersonCodeExist = vsmstestdao.checkPersonCode(datasource, personcode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "checkPersonCode", "VsmsScreeningTestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "checkPersonCode");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "checkPersonCode", "VsmsScreeningTestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "checkPersonCode");
        }
        return isPersonCodeExist;
    }

    public ArrayList getMentalItems(DataSource datasource, String personCode) throws SADAREMDBException, SQLException {
        ArrayList data = new ArrayList();
        try {

            data = vsmstestdao.getMentalItems(datasource, personCode);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalItems", "VsmsScreeningTestServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "getMentalItems");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(datasource,sqlEx.toString(), "getMentalItems", "VsmsScreeningTestServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "VsmsScreeningTestServiceImpl", "getMentalItems");
        }


        return data;
    }
}


