/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 484898
 */
public class TransactionDAO {

    /**
     * @param DataSourse
     * @param
     *
     */
    public int insertTransactionalDetails(DataSource ds, String transactionStatus, String person_code, HttpServletRequest request) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        String sql = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        String pensionphase;
        HttpSession session = request.getSession();
        try {
            pensionphase = getPensionPhase(ds, person_code);
            con = DBConnection.getConnection();
            



            sql = "insert into tblTransactional_Status(Person_code,Transaction_status,campid,Login_id,"
                    + "Created_Date,Updated_Date,pensionphase) values(?,?,?,?,getDate(),getDate(),?)";
            pstmt= con.prepareStatement(sql);

            pstmt.setString(1, person_code);
            pstmt.setString(2, transactionStatus);
            pstmt.setString(3, ""+session.getAttribute("campId")); 
            pstmt.setString(4, (String) session.getAttribute("loginid"));
            pstmt.setString(5, pensionphase);
            insertStatus = pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertTransactionalDetails", "TransactionDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransactionDAO", "insertTransactionalDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertTransactionalDetails", "TransactionDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransactionDAO", "insertTransactionalDetails");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return insertStatus;
    }

    public String getPensionPhase(DataSource ds, String person_code) throws SADAREMDBException, SQLException {
        String pensionphase = "";
        String sql = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            

            sql = "select pensionphase from tblperson_personal_details  with (nolock) where person_code=? ";
            pstmt= con.prepareStatement(sql);
            pstmt.setString(1, person_code);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pensionphase = rs.getString(1);
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPensionPhase", "TransactionDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransactionDAO", "getPensionPhase");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPensionPhase", "TransactionDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransactionDAO", "getPensionPhase");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);
                DBConnection.closeResultSet(rs);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pensionphase;
    }

    public synchronized  int updateTransactionDetails(DataSource ds, String transactionStatus, String personCode) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        String sql = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        CallableStatement calSt = null;
        int calResult = 0;
        int status = 1;
        int transactionUpdate = 0;
        try {
            con = DBConnection.getConnection();
            // con.setAutoCommit(false);
            

            /** Update Transaction Sataus in tblTransactional_Status Table */
            sql = "update tblTransactional_Status set Transaction_status=?,updated_date=getDate() where person_code=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, transactionStatus);
            pstmt.setString(2, personCode);
            transactionUpdate = pstmt.executeUpdate();


            /** Delete date from all DataBase Tables */
            calSt = con.prepareCall("{Call SP_DeleteDatainAllTables_for_UpdateDisability_Transaction(?)}");
            calSt.setString(1, personCode);
            calResult = calSt.executeUpdate();

//            /** Update View_Edit_Mode in tblperson_personal_details Table */
//            sql = "update tblPerson_personal_details set view_edit_mode='Edit' where person_code='" + personCode + "'";
//            insertStatus = st.executeUpdate(sql);
//            if (transactionUpdate != 0) {
//                status = 1;
//            }

            calSt.close();


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateTransactionDetails", "TransactionDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransactionDAO", "updateTransactionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateTransactionDetails", "TransactionDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransactionDAO", "updateTransactionDetails");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(calSt);
                DBConnection.closeStatement(pstmt);
                //DBConnection.closeResultSet(rs);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    public int updateTransactionStatus(DataSource ds, String transactionStatus, String personCode) throws SADAREMDBException, SQLException {
        int insertStatus = 0;
        String sql = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            

            sql = "update tblTransactional_Status set Transaction_status=?,updated_date=getDate() where person_code=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, transactionStatus);
            pstmt.setString(2, personCode);
            insertStatus = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateTransactionStatus", "TransactionDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransactionDAO", "updateTransactionStatus");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateTransactionStatus", "TransactionDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TransactionDAO", "updateTransactionStatus");
        } finally {
            try {
                DBConnection.closeConnection(con);
                DBConnection.closeStatement(pstmt);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return insertStatus;
    }
}
