/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.PercentageWiseReportForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 842412
 */
public class PercentageWiseReportDAO {

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
    public ArrayList getFinancialYears() {
        Calendar cal = Calendar.getInstance();
        ArrayList yearsList = new ArrayList();
        Map yrList = null;
        int finalYear = 0;

        int year = cal.get(Calendar.YEAR);
        int mnt = cal.get(Calendar.MONTH);

        if (mnt == 1 || mnt == 2 || mnt == 3) {
            finalYear = year;
        } else {
            finalYear = year + 1;
        }

        for (int i = 2010; i <= finalYear; i++) {
            yrList = new HashMap();
            yrList.put("finyear", String.valueOf(i - 1) + "-" + String.valueOf(i));
            yearsList.add(yrList);
        }
        return yearsList;
    }

    public ArrayList getPecentageWiseData(PercentageWiseReportForm percentageWiseReportForm, DataSource ds) throws SADAREMDBException, SQLException, ParseException {
        ArrayList PecentageWiseData = new ArrayList();
        String fromDate = percentageWiseReportForm.getFromdate();
        String toDate = percentageWiseReportForm.getTodate();
        String districtId = null;
        DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String query = null;
        Map map = null;
//        Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
//        String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
//        Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
//        String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

        try {
            con = DBConnection.getConnection();
//            if (percentageWiseReportForm.getDistrict_id() != null && !percentageWiseReportForm.getDistrict_id().equals("0") && percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equals("Date Wise")) {
            if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equals("Date Wise")) {
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


                if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
                    cstmt = con.prepareCall("{Call SP_Eligible_PercentageWise_HABITATION(?,?,?,?,?)}");
                    cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                    cstmt.setString(2, percentageWiseReportForm.getMandal_id());
                    cstmt.setString(3, percentageWiseReportForm.getVillage_id());
                    cstmt.setString(4, fromdate);
                    cstmt.setString(5, todate);

                } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
                    cstmt = con.prepareCall("{Call SP_Eligible_PercentageWise_village(?,?,?,?)}");
                    cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                    cstmt.setString(2, percentageWiseReportForm.getMandal_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
                    cstmt = con.prepareCall("{Call SP_Eligible_PercentageWise_Mandal(?,?,?)}");
                    cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                } else {
                    cstmt = con.prepareCall("{Call SP_Eligible_PercentageWise_state(?,?)}");
                    //cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                    cstmt.setString(1, fromdate);
                    cstmt.setString(2, todate);

                }
            } else if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equalsIgnoreCase("Financial Year Wise")) {
                String givenFromDate = percentageWiseReportForm.getFromdate();
                String givenToDate = percentageWiseReportForm.getTodate();
                if (percentageWiseReportForm.getFinancialYearWise() != null) {
                    String[] financialYear = percentageWiseReportForm.getFinancialYearWise().split("-");
                    int givenYear = Integer.parseInt(financialYear[0]);
                    givenFromDate = "01/04/" + givenYear;
                    givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);
                }
                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);
                String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
                Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
                String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
                //cstmt = con.prepareCall("{Call ShG_Eligible_PercentageWise(?,?,?)}");
//                if (percentageWiseReportForm.getDistrict_id() == null) {
//                cstmt = con.prepareCall("{Call SP_percentage_eligible_state(?,?)}");
//                //cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
//                cstmt.setString(1, fromdate);
//                cstmt.setString(2, todate);
//            }


                if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
                    cstmt = con.prepareCall("{Call SP_Eligible_PercentageWise_HABITATION(?,?,?,?,?)}");
                    cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                    cstmt.setString(2, percentageWiseReportForm.getMandal_id());
                    cstmt.setString(3, percentageWiseReportForm.getVillage_id());
                    cstmt.setString(4, fromdate);
                    cstmt.setString(5, todate);

                } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
                    cstmt = con.prepareCall("{Call SP_Eligible_PercentageWise_village(?,?,?,?)}");

                    cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                    cstmt.setString(2, percentageWiseReportForm.getMandal_id());
                    cstmt.setString(3, fromdate);
                    cstmt.setString(4, todate);
                } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
                    cstmt = con.prepareCall("{Call SP_Eligible_PercentageWise_Mandal(?,?,?)}");
                    cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                    cstmt.setString(2, fromdate);
                    cstmt.setString(3, todate);
                } else {
                    cstmt = con.prepareCall("{Call SP_Eligible_PercentageWise_state(?,?)}");
                    //cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                    cstmt.setString(1, fromdate);
                    cstmt.setString(2, todate);

                }

            }

            int totalAssessed = 0, totalDirectRejected = 0, totalAssessedRejected = 0, totalRejected = 0, totalEligible = 0, totalEligibleData = 0;
            int rejected = 0;  int totaleligible40to80=0;
            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    if (percentageWiseReportForm.getVillage_id() != null && percentageWiseReportForm.getVillage_id() != "") {
                        map.put("habitationId", rs.getString(1));
                        map.put("habitationname", rs.getString(2));
                        map.put("districtId", percentageWiseReportForm.getDistrict_id());
                        map.put("mandalId", percentageWiseReportForm.getMandal_id());
                        map.put("villageId", percentageWiseReportForm.getVillage_id());
                    } else if (percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
                        map.put("villageId", rs.getString(1));
                        map.put("villagename", rs.getString(2));
                        map.put("districtId", percentageWiseReportForm.getDistrict_id());
                        map.put("mandalId", percentageWiseReportForm.getMandal_id());
                    } else if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "") {
                        map.put("mandalId", rs.getString(1));
                        map.put("mandalname", rs.getString(2));
                        map.put("districtId", percentageWiseReportForm.getDistrict_id());
                    } else {
                        map.put("districtId", rs.getString(1));
                        map.put("districtname", rs.getString(2));
                    }
//                    if (percentageWiseReportForm.getDistrict_id() != null && percentageWiseReportForm.getDistrict_id() != "" && percentageWiseReportForm.getMandal_id() != null && percentageWiseReportForm.getMandal_id() != "") {
//                        map.put("villageId", rs.getString(1));
//                        map.put("villagename", rs.getString(2));
//                    }
                    map.put("Assessed", rs.getString(3));
                    map.put("directRejected", rs.getString(4));
                    map.put("assessedRejected", rs.getString(5));
                    map.put("eligible", rs.getString(6));
                    map.put("eligibleData", rs.getString(7));
                    int eligible40to80 = 0;
                    eligible40to80 = rs.getInt(6) + rs.getInt(7);


                  
                    map.put("eligibledata40to100", eligible40to80);
                    rejected = rs.getInt(4) + rs.getInt(5);
                    map.put("rejected", rejected);
                    map.put("finace", percentageWiseReportForm.getFinancialYear());
                    totalAssessed = totalAssessed + rs.getInt(3);
                    totalDirectRejected = totalDirectRejected + rs.getInt(4);
                    totalAssessedRejected = totalAssessedRejected + rs.getInt(5);
                    totalEligible = totalEligible + rs.getInt(6);
                    totalEligibleData = totalEligibleData + rs.getInt(7);
                    totalRejected = totalRejected + rejected;
                    totaleligible40to80=totaleligible40to80+eligible40to80;
                    PecentageWiseData.add(map);
                }
            }
            if (PecentageWiseData.size() > 0) {
                map.put("totalAssessed", totalAssessed);
                map.put("totalDirectRejected", totalDirectRejected);
                map.put("totalAssessedRejected", totalAssessedRejected);
                map.put("totalRejected", totalRejected);
                map.put("totalEligible", totalEligible);
                map.put("totalEligibleData", totalEligibleData);
                 map.put("totaleligible40to80", totaleligible40to80);
            }
        } catch (ParseException parseException) {
            try {
                int exResult = ExceptionDAO.saveException(ds, parseException.toString(), "getPecentageWiseData", "PercentageWiseReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PercentageWiseReportDAO", "getPecentageWiseData");
            } catch (Exception sqlEx) {
            }
        } catch (SQLException sqlEx) {
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPecentageWiseData", "PercentageWiseReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PercentageWiseReportDAO", "getPecentageWiseData");
            } catch (Exception Ex) {
            }
        } catch (Exception sqlEx) {
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPecentageWiseData", "PercentageWiseReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PercentageWiseReportDAO", "getPecentageWiseData");
            } catch (Exception Ex) {
            }
        }  finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return PecentageWiseData;

    }

    public ArrayList getIndividualDetails(PercentageWiseReportForm percentageWiseReportForm, DataSource ds) throws SADAREMDBException, SQLException, ParseException {
        ArrayList PecentageWiseData = new ArrayList();
        String fromDate = percentageWiseReportForm.getFromdate();
        String toDate = percentageWiseReportForm.getTodate();
        String districtId = null;
        DailyDisabilityAndPercentageDAO dailyDisabilityAndPercentageDAO = new DailyDisabilityAndPercentageDAO();

        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String query = null;
        Map map = null;

        try {
            con = DBConnection.getConnection();
            if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equals("Date Wise")) {
                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromDate);
                String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
                Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
                String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
                cstmt = con.prepareCall("{Call sp_eligible_percentage80to100_districtinvidualdetails(?,?,?)}");
                cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);

            } else if (percentageWiseReportForm.getTypeOfSearch() != null && percentageWiseReportForm.getTypeOfSearch().equalsIgnoreCase("Financial Year Wise")) {
                String givenFromDate = percentageWiseReportForm.getFromdate();
                String givenToDate = percentageWiseReportForm.getTodate();
                if (percentageWiseReportForm.getFinancialYearWise() != null) {
                    String[] financialYear = percentageWiseReportForm.getFinancialYearWise().split("-");
                    int givenYear = Integer.parseInt(financialYear[0]);
                    givenFromDate = "01/04/" + givenYear;
                    givenToDate = dailyDisabilityAndPercentageDAO.getAssignedDate(givenYear + 1, ds, givenFromDate);
                }
                Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenFromDate);
                String fromdate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
                Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(givenToDate);
                String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);
                cstmt = con.prepareCall("{Call sp_eligible_percentage80to100_districtinvidualdetails(?,?,?)}");
                cstmt.setString(1, percentageWiseReportForm.getDistrict_id());
                cstmt.setString(2, fromdate);
                cstmt.setString(3, todate);
            }


            rs = cstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();

                    map.put("personcode", rs.getString(1));
                    map.put("pensionno", rs.getString(2));
                    map.put("name", rs.getString(3));
                    map.put("gender", rs.getString(4));
                    map.put("caste", rs.getString(5));
                    map.put("age", rs.getString(6));
                    map.put("maritalstatus", rs.getString(7));
                    map.put("relationname", rs.getString(8));
                    map.put("disability", rs.getString(9));
                    map.put("totaldisability", rs.getString(10));
                    map.put("education", rs.getString(11));
                    map.put("employment", rs.getString(12));
                    map.put("rationcardno", rs.getString(13));
                    map.put("pensionphase", rs.getString(14));
                    map.put("pensionstatus", rs.getString(15));
                    map.put("mandalname", rs.getString(16));
                    map.put("villagename", rs.getString(17));
                    map.put("habitationname", rs.getString(18));
                    map.put("houseno", rs.getString(19));
                    map.put("phoneno", rs.getString(20));
                    map.put("viewmode", rs.getString(21));
                    map.put("reasonforpersonstatus", rs.getString(22));
                    map.put("assessment", rs.getString(23));
                    map.put("status", rs.getString(24));
                    map.put("loginid", rs.getString(25));
                    map.put("createddate", rs.getString(26));
                    PecentageWiseData.add(map);
                }
            }

         } catch (ParseException parseException) {
            try {
                int exResult = ExceptionDAO.saveException(ds, parseException.toString(), "getIndividualDetails", "PercentageWiseReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, parseException.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PercentageWiseReportDAO", "getIndividualDetails");
            } catch (Exception sqlEx) {
            }
        } catch (SQLException sqlEx) {
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIndividualDetails", "PercentageWiseReportDAO", "DataBase");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PercentageWiseReportDAO", "getIndividualDetails");
            } catch (Exception Ex) {
            }
        } catch (Exception sqlEx) {
            try {
                int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getIndividualDetails", "PercentageWiseReportDAO", "Code");
                throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PercentageWiseReportDAO", "getIndividualDetails");
            } catch (Exception Ex) {
            }
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }
        return PecentageWiseData;

    }
}
