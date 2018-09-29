/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.UserDetailsDAO;
import org.bf.disability.service.UserDetailsService;

/**
 *
 * @author t_bapinaidu
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    public List getUserDetails(DataSource ds, int campId, String loginId) throws SADAREMDBException, SQLException {
        UserDetailsDAO userDetailsDAO = null;
        List userDetailsList = null;
        try {
            userDetailsDAO = new UserDetailsDAO();
            userDetailsList = userDetailsDAO.getUserDetails(ds, campId, loginId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getUserDetails", "UserDetailsServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UserDetailsServiceImpl", "getUserDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getUserDetails", "UserDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UserDetailsServiceImpl", "getUserDetails");
        }
        return userDetailsList;
    }

    public int updateLoginDetails(DataSource ds, String loginId, String loginStatus, String selectedRowId) throws SADAREMDBException, SQLException {
        UserDetailsDAO userDetailsDAO = null;
        int i = 0;
        try {
            userDetailsDAO = new UserDetailsDAO();
            i = userDetailsDAO.updateLoginDetails(ds, loginId, loginStatus, selectedRowId);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateLoginDetails", "UserDetailsServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UserDetailsServiceImpl", "updateLoginDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "updateLoginDetails", "UserDetailsServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UserDetailsServiceImpl", "updateLoginDetails");
        }
        return i;
    }
}
