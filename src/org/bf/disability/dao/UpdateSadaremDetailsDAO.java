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
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.UpdateSadaremDetailsForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 728056
 */
public class UpdateSadaremDetailsDAO {

    public ArrayList getDetails(DataSource ds, HttpSession session) throws SADAREMDBException, SQLException {
        ArrayList data = new ArrayList();
        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap map = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select Pensioncardno,personcode,firstname,relationname,mandalname,"
                    + "villagename,habitationName from tbl_test where Status='pending'";


            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("Pensioncardno", rs.getString(1));
                    map.put("PersonCode", rs.getString(2));
                    map.put("firstname", rs.getString(3));
                    map.put("relationname", rs.getString(4));
                    map.put("MANDALNAME", rs.getString(5));
                    map.put("VILLAGENAME", rs.getString(6));
                    map.put("HabitationName", rs.getString(7));
                    data.add(map);
                }

            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "UpdateSadaremDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpdateSadaremDetailsDAO", "getDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDetails", "UpdateSadaremDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpdateSadaremDetailsDAO", "getDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(st);
			DBConnection.closeConnection(con);
        }
        return data;
    }

    public boolean insertData(DataSource ds, UpdateSadaremDetailsForm detailsForm, HttpSession session) throws SADAREMDBException, SQLException {
        boolean data = false;

        Connection con = null;
        Statement st = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;
        HashMap map = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            st = con.createStatement();

            if (detailsForm.getSelectBox() != null && detailsForm.getSelectBox().length > 0) {
                String[] sArray = detailsForm.getSelectBox();
                for (int i = 0; i < sArray.length; i++) {
                    sql = "update tbl_test set status='Approved',updated_date=getdate()"
                            + "where pensioncardno='" + sArray[i] + "'";
                    st.addBatch(sql);

                }
            }
            int[] i = st.executeBatch();
            con.commit();

            data = i.length > 0 ? true : false;

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertData", "UpdateSadaremDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpdateSadaremDetailsDAO", "insertData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertData", "UpdateSadaremDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UpdateSadaremDetailsDAO", "insertData");
        } finally {
            
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeStatement(st);
			DBConnection.closeConnection(con);
        }
        return data;
    }
}
