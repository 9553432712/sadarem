/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.RejectedAadharMappingForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 842412
 */
public class RejectedAadharMappingDAO {

//    public ArrayList getFinancialYears() {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        ArrayList yearsList = new ArrayList();
//        Map yrList = null;
//        for (int i = 2010; i <= year; i++) {
//            String finyear = String.valueOf(i - 1) + "-" + String.valueOf(i);
//            yrList = new HashMap();
//            yrList.put("finyear", finyear);
//            yearsList.add(yrList);
//        }
//        return yearsList;
//    }
    public static BigDecimal round(float d, int decimalPlace) {

        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public ArrayList getAadharMappingDetails(RejectedAadharMappingForm rejectedAadharMappingForm, DataSource ds) throws SADAREMDBException, SQLException, ParseException {
        ArrayList mappingDetails = new ArrayList();
        String fromDate = rejectedAadharMappingForm.getFromdate();
        String toDate = rejectedAadharMappingForm.getTodate();
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String query = null;
        Map map = null;
        BigDecimal TotalmappedPercentage = new BigDecimal("0.00");
//        Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
//        String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
//        Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
//        String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

        try {
            con = DBConnection.getConnection();
            Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
            String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
            Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
            String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

            //cstmt = con.prepareCall("{Call ShG_Eligible_PercentageWise(?,?,?)}");
//            if (percentageWiseReportForm.getDistrict_id() == null) {
//
//                cstmt = con.prepareCall("{Call shg_percentage_eligible_state(?,?)}");
//                //cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
//                cstmt.setString(1, fromdate);
//                cstmt.setString(2, todate);
//            }

            //if (rejectedAadharMappingForm.getDistrict_id() != null && rejectedAadharMappingForm.getDistrict_id() != "" && rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")) {
            cstmt = con.prepareCall("{Call Sp_SADAREM_REJECTED_AADHAR_MAPPING(?,?,?)}");
            cstmt.setString(1, rejectedAadharMappingForm.getDistrict_id());
            cstmt.setString(2, fromdate);
            cstmt.setString(3, todate);

            int target = 0, mappedcount = 0, totalTarget = 0, totalTargetCount = 0, totalEligibleData = 0;
            int rejected = 0;
            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    if (rejectedAadharMappingForm.getDistrict_id() != null && rejectedAadharMappingForm.getDistrict_id() != "" && rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")) {
                        map.put("districtid", rs.getString(1));
                        map.put("districtname", rs.getString(2));

                    }
                    if (rejectedAadharMappingForm.getDistrict_id() != null && rejectedAadharMappingForm.getDistrict_id() != "" && !rejectedAadharMappingForm.getDistrict_id().equalsIgnoreCase("All")) {
                        map.put("mandalid", rs.getString(1));
                        map.put("mandalname", rs.getString(2));
                    }

                    map.put("target", rs.getString(3));
                    map.put("mappedcount", rs.getString(4));
                    map.put("fromdate", rejectedAadharMappingForm.getFromdate());
                    map.put("todate", rejectedAadharMappingForm.getTodate());
                    map.put("districtid", rejectedAadharMappingForm.getDistrict_id());

                    target = target + rs.getInt(3);
                    mappedcount = mappedcount + rs.getInt(4);
                    mappingDetails.add(map);
                    totalTarget = rs.getInt(3) + rs.getInt(4);
                    map.put("totalTarget1", totalTarget);
                    totalTargetCount = totalTargetCount + totalTarget;
//                    System.out.println("ee---" + round(((Float.parseFloat(Integer.toString(i)) / Float.parseFloat(Integer.toString(totalTarget))) * 100), 2));
                    BigDecimal mappedPercentage = round(((Float.parseFloat(rs.getString(4)) / Float.parseFloat(Integer.toString(totalTarget))) * 100), 2);
                    map.put("mappedPercentage", mappedPercentage);
                    TotalmappedPercentage = TotalmappedPercentage.add(mappedPercentage);

                }
                BigDecimal big = new BigDecimal(Integer.toString(mappingDetails.size()));
                TotalmappedPercentage = TotalmappedPercentage.divide(big, 2);
                map.put("TotalmappedPercentage", TotalmappedPercentage);
            }
            if (mappingDetails.size() > 0) {
                map.put("totalTargetCount", totalTargetCount);
                map.put("totaltarget", target);
                map.put("totalmappedcount", mappedcount);
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAadharMappingDetails", "RejectedAadharMappingDAO", "DataBase");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RejectedAadharMappingDAO", "getAadharMappingDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getAadharMappingDetails", "RejectedAadharMappingDAO", "Code");
            con.rollback();
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "RejectedAadharMappingDAO", "getAadharMappingDetails");
        } finally {
        	DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(cstmt);
			DBConnection.closeConnection(con);

        }
        return mappingDetails;

    }
}
