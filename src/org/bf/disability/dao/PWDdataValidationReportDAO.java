/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author apoisadmin
 */
public class PWDdataValidationReportDAO {

    public ArrayList getpwdValidationReport(DataSource ds,
            String districtId, String mandalid, String fromDate, String toDate)
            throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        ArrayList<Object> reportList = new ArrayList<Object>();
        Map reportData = null;

        int totalassessed1 = 0;
        int validationDone1 = 0;
        int validationPending1 = 0;
        try {
            con = DBConnection.getConnection();
//            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
//            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
//
//            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
//            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);


            if (districtId != null && !districtId.equalsIgnoreCase("0") && mandalid != null && !mandalid.equalsIgnoreCase("0")) {
                cstmt = con.prepareCall("{Call [R1.5PWDDATAVALIDATIONREPORT_VILLAGE](?,?)}");
                cstmt.setString(1, districtId);
                cstmt.setString(2, mandalid);
            } else if (districtId != null && !districtId.equalsIgnoreCase("0")) {

                cstmt = con.prepareCall("{Call [R1.5PWDDATAVALIDATIONREPORT_MANDAL](?)}");
                cstmt.setString(1, districtId);

            } else {

                cstmt = con.prepareCall("{Call [R1.5PWDDATAVALIDATIONREPORT_DISTRICT]()}");
            }



            rs = cstmt.executeQuery();
            int i = 1;
            if (rs != null) {
                while (rs.next()) {
                    reportData = new HashMap();
                    reportData.put("sno", i);

                    if (districtId != null && !districtId.equalsIgnoreCase("0") && mandalid != null && !mandalid.equalsIgnoreCase("0")) {
                        reportData.put("district_id", rs.getString("district_id"));
                        reportData.put("district_name", rs.getString("district_name"));
                        reportData.put("mandal_id", rs.getString("mandal_id"));
                        reportData.put("mandal_name", rs.getString("mandal_name"));
                        reportData.put("village_id", rs.getString("village_id"));
                        reportData.put("village_name", rs.getString("village_name"));

                    } else if (districtId != null && !districtId.equalsIgnoreCase("0")) {

                        reportData.put("district_id", rs.getString("district_id"));
                        reportData.put("district_name", rs.getString("district_name"));
                        reportData.put("mandal_id", rs.getString("mandal_id"));
                        reportData.put("mandal_name", rs.getString("mandal_name"));

                    }
                    if (districtId == null) {


                        reportData.put("district_id", rs.getString("district_id"));
                        reportData.put("district_name", rs.getString("district_name"));

                    }
                    reportData.put("Assessed", rs.getString("Totalassessed"));

                    reportData.put("validationDone", rs.getString("validationdone"));
                    reportData.put("validationPending", (rs.getInt("Totalassessed") - rs.getInt("validationdone")));

                    reportData.put("districtId", districtId);
                    reportData.put("mandalid", mandalid);
                    totalassessed1 = totalassessed1 + rs.getInt("Totalassessed");

                    validationDone1 = validationDone1 + rs.getInt("validationdone");

                    validationPending1 = validationPending1 + (rs.getInt("Totalassessed") - rs.getInt("validationdone"));
                    reportData.put("totalassessed1", totalassessed1);
                    reportData.put("validationdone1", validationDone1);
                    reportData.put("validationPending1", validationPending1);


                    reportList.add(reportData);
                    i++;
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            ExceptionDAO.saveException(ds, sqlEx.toString(), "getpwdValidationReport", "PWDdataValidationReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PWDdataValidationReportDAO", "getpwdValidationReport");
        } catch (Exception sqlEx) {
            // sqlEx.printStackTrace();
            ExceptionDAO.saveException(ds, sqlEx.toString(), "getpwdValidationReport", "PWDdataValidationReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PWDdataValidationReportDAO", "getpwdValidationReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return reportList;
    }


    public ArrayList getpwdValidationReportIndividual(DataSource ds,
            String districtID, String mandalID)
            throws SADAREMDBException, SQLException {

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
       ArrayList<Object> reportList = new ArrayList<Object>();
       Map reportData = null;
        try {
            con = DBConnection.getConnection();
                cstmt = con.prepareCall("{Call [R1.5PWDDATAVALIDATIONREPORT_MANDAL_INDIVIDUAL](?,?)}");
                cstmt.setString(1, districtID);
                cstmt.setString(2, mandalID);


                rs = cstmt.executeQuery();
            int i = 1;
            if (rs != null) {
                while (rs.next()) {
                    reportData = new HashMap();
                    reportData.put("sno", i);
                    reportData.put("PENSIONCARD_NO",rs.getString("PENSIONCARD_NO"));
                    reportData.put("PERSON_CODE",rs.getString("PERSON_CODE"));
                    reportData.put("PERSONNAME",rs.getString("PERSONNAME"));
                    reportData.put("RELATION_NAME",rs.getString("RELATION_NAME"));
                    reportData.put("Gender",rs.getString("Gender"));
                    reportData.put("AGE_YEARS",rs.getString("AGE_YEARS"));
                    reportData.put("RATIONCARD_NUMBER",rs.getString("RATIONCARD_NUMBER"));
                    reportData.put("MANDAL_NAME",rs.getString("MANDAL_NAME"));
                    reportData.put("VILLAGE_NAME",rs.getString("VILLAGE_NAME"));
                    reportData.put("HABITATION_NAME",rs.getString("HABITATION_NAME"));
                    reportData.put("TYPEOF_DISABILITY",rs.getString("TYPEOF_DISABILITY"));
                    reportData.put("PENSIONPHASE",rs.getString("PENSIONPHASE"));
                    reportData.put("HOUSE_NUMBER",rs.getString("HOUSE_NUMBER"));
                    reportData.put("Catid",rs.getString("Catid"));
                    reportList.add(reportData);
                    i++;
                }

            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getpwdValidationReportIndividual", "PWDdataValidationReportDAO", "DataBase");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PWDdataValidationReportDAO", "getpwdValidationReportIndividual");
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getpwdValidationReportIndividual", "PWDdataValidationReportDAO", "Code");
           con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PWDdataValidationReportDAO", "getpwdValidationReportIndividual");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return reportList;
    }

}
