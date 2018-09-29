/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.ValidPersonDetailsForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 728056
 */
public class ValidPersonDetailsDAO {

    public boolean checkDetails(DataSource ds, ValidPersonDetailsForm detailsForm) throws SADAREMDBException, SQLException {
        boolean person = false;
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap map = null;
        try {
            con = DBConnection.getConnection();
            
            sql = "select person_code from dbo.tblPerson_Disability_Details where person_code=? and status='Active'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, detailsForm.getPersonId());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    person = true;
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkDetails", "ValidPersonDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ValidPersonDetailsDAO", "checkDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkDetails", "ValidPersonDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ValidPersonDetailsDAO", "checkDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return person;
    }

    public int updateDetails(DataSource ds, ValidPersonDetailsForm detailsForm) throws SADAREMDBException, SQLException {
        int person = 0;
        Connection con = null;
        //Statement st = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap map = null;
        try {
            con = DBConnection.getConnection();
            
            sql = "update dbo.tblPerson_Disability_Details set Diagnosis_of_Disability=?  "
                    + "where person_code=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, detailsForm.getTextarea());
            pstmt.setString(2, detailsForm.getPersonId());
            person = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateDetails", "ValidPersonDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ValidPersonDetailsDAO", "updateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateDetails", "ValidPersonDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ValidPersonDetailsDAO", "updateDetails");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);


        }
        return person;
    }
}
