/*
 * LoginServiceImpl.java
 *
 * Created on September 12, 2008, 6:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.LoginDAO;
import org.bf.disability.dto.LoginDTO;
import org.bf.disability.form.ShgloginForm;
import org.bf.disability.service.LoginService;

/**
 * This class has the implementation functionality for DoctorsInformationService
 * interface.
 * @author Deviprasad
 * @version 1.0
 */
public class LoginServiceImpl implements LoginService {

    public LoginDTO getValidUser(DataSource ds, String userid, String password) throws SADAREMException, SQLException {
        try 
        {
            LoginDAO loginDAO = new LoginDAO();
            return loginDAO.getValidUser(ds, userid, password);
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidUser", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "getValidUser");
        }
    }

    public LoginDTO getUserDetails(DataSource ds, String userid) throws SADAREMException, SQLException {
        try {
            LoginDAO loginDAO = new LoginDAO();
            return loginDAO.getUserDetails(ds, userid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getUserDetails", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "getUserDetails");
        }
    }

    public String getRoleId(DataSource ds, String userid) throws SADAREMException, SQLException {
        try {
            LoginDAO loginDAO = new LoginDAO();
            return loginDAO.getRoleId(ds, userid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRoleId", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "getRoleId");
        }
    }

    public Vector getServicesSQL(DataSource ds, String roleid) throws SADAREMException, SQLException {
        try {
            LoginDAO loginDAO = new LoginDAO();
            return loginDAO.getServicesSQL(ds, roleid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getServicesSQL", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "getServicesSQL");
        }
    }

    public int unAuthorisedAccessStatus(DataSource ds, String userid) throws SADAREMException, SQLException {
        try {
            LoginDAO loginDAO = new LoginDAO();
            return loginDAO.getunAuthorisedAccessStatus(ds, userid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "unAuthorisedAccessStatus", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "unAuthorisedAccessStatus");
        }
    }

    public int changePassword(DataSource ds, ShgloginForm ShgloginForm, String username) throws SADAREMException, SQLException {
        try {
            LoginDAO loginDAO = new LoginDAO();
            return loginDAO.changePassword(ds, ShgloginForm, username);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "changePassword", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "changePassword");
        }
    }

    public String getCampNames(DataSource ds, String disId) throws SADAREMException, SQLException {
        String campNames = "";
        LoginDAO dataDao = new LoginDAO();

        try {
            campNames = dataDao.getCampNames(ds, disId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campNames;
    }

    public ArrayList getCountOfApprovalPendingofRole(DataSource ds, String districtId, String roleid, String mandalId) throws SADAREMException, SQLException {
        try {

            LoginDAO loginDAO = new LoginDAO();
            ArrayList list = loginDAO.getCountOfApprovalPendingofRole(ds, districtId, roleid, mandalId);
            return list;
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCountOfApprovalPendingofRole", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "getCountOfApprovalPendingofRole");
        }
    }

    public LoginDTO getRDCallCenterValidUser(DataSource ds, String userid) throws SADAREMException, SQLException {

        try {
            LoginDAO loginDAO = new LoginDAO();
            return loginDAO.getRDCallCenterValidUser(ds, userid);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getValidUser", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "getValidUser");
        }
    }

    public int insertLoginStatus(DataSource ds, String userid, String systemIp, String status) throws SADAREMException, SQLException {
        int i = 0;
        try {
            LoginDAO loginDAO = new LoginDAO();
            i = loginDAO.insertLoginStatus(ds, userid, systemIp, status);
            return i;
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertLoginStatus", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "insertLoginStatus");
        }
    }

    public int loginStatusCnt(DataSource ds, String userid) throws SADAREMException, SQLException {
        int i = 0;
        try {
            LoginDAO loginDAO = new LoginDAO();
            i = loginDAO.loginStatusCnt(ds, userid);
            return i;
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "loginStatusCnt", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "loginStatusCnt");
        }
    }

    public int updatePasswordFlag(DataSource ds, String username, String password,String encrptPassword, String districtId, String roleId) throws SADAREMException, SQLException {
        try {
            LoginDAO loginDAO = new LoginDAO();
            return loginDAO.updatePasswordFlag(ds, username, password,encrptPassword, districtId, roleId);
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updatePasswordFlag", "LoginServiceImpl", "Code");
            throw new SADAREMException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "LoginServiceImpl", "updatePasswordFlag");
        }
    }
}
