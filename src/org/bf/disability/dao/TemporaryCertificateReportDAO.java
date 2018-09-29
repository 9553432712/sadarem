/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.ReportDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author apoisadmin
 */
public class TemporaryCertificateReportDAO {

    public ArrayList getTemporaryCertificateReport(DataSource ds,
            String districtId, String fromDate, String toDate)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        ArrayList<Object> reportList = new ArrayList<Object>();
        Map reportData = null;
        try {
            con = DBConnection.getConnection();
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);

            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            cstmt = con.prepareCall("{Call [TEMPORARYCERTIFICATEREPORT](?,?,?)}");
            cstmt.setString(1, districtId);
            cstmt.setString(2, fromdate);
            cstmt.setString(3, todate);


            rs = cstmt.executeQuery();
            int i = 1;
            if (rs != null) {
                while (rs.next()) {
                    reportData = new HashMap();
                    reportData.put("sno", i);
                    reportData.put("pensionId", rs.getString(1));
                    reportData.put("habcode", rs.getString(2));
                    reportData.put("sadaremid", rs.getString(3));
                    reportData.put("personname", rs.getString(4));
                    reportData.put("gender", rs.getString(5));
                    reportData.put("age", rs.getString(6));
                    reportData.put("education", rs.getString(7));
                    reportData.put("caste", rs.getString(8));
                    reportData.put("relationname", rs.getString(9));
                    reportData.put("rationcardno", rs.getString(10));
                    reportData.put("typeofdisability", rs.getString(11));
                    reportData.put("percentage", rs.getString(12));
                    reportData.put("assessmentstatus", rs.getString(13));
                    reportData.put("address", rs.getString(14));

                    reportList.add(reportData);
                    i++;
                }
            }
        } catch (SQLException sqlEx) {
            ExceptionDAO.saveException(ds, sqlEx.toString(), "getTemporaryCertificateReport", "TemporaryCertificateReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TemporaryCertificateReportDAO", "getTemporaryCertificateReport");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            ExceptionDAO.saveException(ds, sqlEx.toString(), "getTemporaryCertificateReport", "TemporaryCertificateReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "TemporaryCertificateReportDAO", "getTemporaryCertificateReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return reportList;
    }
}
