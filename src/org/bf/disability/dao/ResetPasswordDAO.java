/*
 * DistrictDAO.java
 *
 * Created on December 7, 2006, 11:17 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dao.ExceptionDAO;
import org.bf.disability.form.Users;

import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is for changing password to database
 * @author Devi Prasad .T
 * @version 1.0
 */
public class ResetPasswordDAO {

    /**
     * 
     * @param ds 
     * @param users 
     * @throws org.bf.disability.Exception.SADAREMException 
     * @return 
     */
    public int resetPassword(DataSource ds, Users users) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        int i = 0;
        try {
            con = DBConnection.getConnection();
            
            pstmt = con.prepareStatement("update users set password=? where userid=?");
            pstmt.setString(1, users.getPassword());
            pstmt.setString(2, users.getUserid());
            i = pstmt.executeUpdate();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "resetPassword", "ResetPasswordDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ResetPasswordDAO", "resetPassword");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "resetPassword", "ResetPasswordDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ResetPasswordDAO", "resetPassword");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeStatement(pstmt);

        }
        return i;
    }
}


