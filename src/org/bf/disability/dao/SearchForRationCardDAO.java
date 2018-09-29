/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.SearchForRationCardForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 525483
 */
public class SearchForRationCardDAO {

    public ArrayList getRationCardDetails(SearchForRationCardForm searchForRationCardForm, DataSource ds) throws SADAREMDBException, SQLException {

        ArrayList getRationCardNo = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;
        try {
            con = DBConnection.getConnection();

            if (searchForRationCardForm.getRationCard().equals("1")) {
                query = "select Person_id,person_code,(surname+''+first_name) as Name,pensioncard_no,"
                        + "pensionphase,case  when view_edit_mode='view' then 'Assesment Completed' when "
                        + "view_edit_mode='Edit' then 'PartA Entered'end as view_edit_mode,"
                        + "ReasonforPersonStatus "
                        + "from tblPerson_Personal_Details  with (nolock) where Status='Active' and  "
                        + "RationCard_Number = ?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, searchForRationCardForm.getTextBox());
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        HashMap map = new HashMap();
                        map.put("Person_id", rs.getString(1));
                        map.put("person_code", rs.getString(2));
                        map.put("Name", rs.getString(3));
                        map.put("pensioncard_no", rs.getString(4));
                        map.put("pensionphase", rs.getString(5));
                        map.put("view_edit_mode", rs.getString(6));
                        map.put("ReasonforPersonStatus", rs.getString(7));
                        getRationCardNo.add(map);

                    }
                }
            } else if (searchForRationCardForm.getRationCard().equals("2")) {
                query = " select Person_id,RationCard_Number,(surname+''+first_name) as Name,"
                        + "pensioncard_no,pensionphase, "
                        + "case  when view_edit_mode='view' then 'Assesment Completed'"
                        + "when view_edit_mode='Edit' then 'PartA Entered'"
                        + "end as view_edit_mode,ReasonforPersonStatus"
                        + " from dbo.tblPerson_Personal_Details  with (nolock) "
                        + " where Status='Active' and person_code=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, searchForRationCardForm.getTextBox());
                rs = pstmt.executeQuery();

                if (rs != null) {
                    getRationCardNo = new ArrayList();
                    while (rs.next()) {
                        HashMap map = new HashMap();
                        map.put("Person_id", rs.getString(1));
                        map.put("RationCard_Number", rs.getString(2));
                        map.put("Name", rs.getString(3));
                        map.put("pensioncard_no", rs.getString(4));
                        map.put("pensionphase", rs.getString(5));
                        map.put("view_edit_mode", rs.getString(6));
                        map.put("ReasonforPersonStatus", rs.getString(7));
                        getRationCardNo.add(map);

                    }
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardDetails", "SearchForRationCardDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SearchForRationCardDAO", "getRationCardDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getRationCardDetails", "SearchForRationCardDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "SearchForRationCardDAO", "getRationCardDetails");
        } finally {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con);
        }

        return getRationCardNo;
    }
}
