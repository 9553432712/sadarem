/*
 * ResetPasswordServiceImpl.java
 *
 * Created on September 13, 2008, 11:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.serviceimpl;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.dao.ResetPasswordDAO;
import org.bf.disability.form.Users;
import org.bf.disability.service.ResetPasswordService;

/**
 * This class has the implementation functionality for ResetPasswordService
 * interface.
 * @author Deviprasad T
 * @version 1.0
 */
public class ResetPasswordServiceImpl implements ResetPasswordService {

    public int resetPassword(DataSource ds, Users users) throws SADAREMDBException, SQLException {

        ResetPasswordDAO resetpassworddao = new ResetPasswordDAO();

        try {
            return resetpassworddao.resetPassword(ds, users);
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "resetPassword", "ResetPasswordServiceImpl", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ResetPasswordServiceImpl", "resetPassword");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "resetPassword", "ResetPasswordServiceImpl", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ResetPasswordServiceImpl", "resetPassword");
        }
    }
}
