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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.ClusterReportForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 747577
 */
public class ClusterReportDAO {

    public ArrayList getReportTypes(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList reportList = new ArrayList();
        ResultSet rs = null;
        HashMap map = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            // this query gets district id and district name
            String query = "select reportid,reportname from dbo.tblClusterReportTypes where status='Active' order by reportname";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                map = new HashMap();
                map.put("reportid", rs.getString(1));
                map.put("reportname", rs.getString(2));
                reportList.add(map);
            }
        } //end of try block
        catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReportTypes", "ClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getReportTypes");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getReportTypes", "ClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getReportTypes");
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }//end of finally block
        return reportList;
    }

    public ArrayList getDistricts(DataSource ds) throws SADAREMDBException, SQLException {
        Connection con = null;
        Statement stmt = null;
        ArrayList districtsList = new ArrayList();
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            // this query gets district id and district name
            String query = "select distinct district from dbo.overallreportcluster_new order by district";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                map = new HashMap();
                map.put("districtName", rs.getString(1));
                districtsList.add(map);
            }
        } //end of try block
        catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistricts", "ClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getDistricts");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistricts", "ClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getDistricts");
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);

        }//end of finally block
        return districtsList;
    }

    public ArrayList getClusters(DataSource ds, String districtName) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList clusterList = new ArrayList();
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            
            // this query gets district id and district name
            String query = "select distinct clustername from dbo.overallreportcluster_new where district = ? order by clustername";
            pstmt= con.prepareStatement(query);
            pstmt.setString(1, districtName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                map = new HashMap();
                map.put("clusterName", rs.getString(1));
                clusterList.add(map);
            }
        } //end of try block
        catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getClusters", "ClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getClusters");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getClusters", "ClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getClusters");
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }//end of finally block
        return clusterList;
    }

    public ArrayList getMandals(DataSource ds, String mandalName) throws SADAREMDBException, SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ArrayList mandalList = new ArrayList();
        ResultSet rs = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
            
            // this query gets district id and district name
            String query = "select distinct mandalname from dbo.overallreportcluster_new where clustername = ? order by mandalname";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, mandalName);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                map = new HashMap();
                map.put("mandalName", rs.getString(1));
                mandalList.add(map);
            }
        } //end of try block
        catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandels", "ClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getMandels");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandels", "ClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getMandels");
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }//end of finally block
        return mandalList;
    }

    public ArrayList getStateClusterReportList(DataSource ds, ClusterReportForm clusterReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        //Statement stmt = null;
        ArrayList clusterReportList = new ArrayList();
        ResultSet rs = null;
        String query = null;
        Map map = null;
        ArrayList tlist = null;
        String reportType = null;
        try {
            con = DBConnection.getConnection();
            boolean firstFlag = true;
            boolean secondFlag = true;
            int newColumns = 11;
            // this query gets district id and district name

            for (int i = 1; i <= 24; i++) {

                reportType = Integer.toString(i);
                if (firstFlag && ((i == 14) || (i == 15))) {

                    int j = newColumns + i;
                    reportType = Integer.toString(j);
                    if (i == 15) {
                        i = 13;
                        firstFlag = false;
                    }

                } else if (!firstFlag && secondFlag && (i == 15)) {

                    i = 16;
                }

                if (secondFlag && ((i == 16) || (i == 17))) {

                    int j = newColumns + i;
                    reportType = Integer.toString(j);

                    if (i == 17) {
                        i = 14;
                        secondFlag = false;
                    }


                }

                cstmt = con.prepareCall("{Call profilestatewiseProcedure(?)}");
                cstmt.setString(1, reportType);
                rs = cstmt.executeQuery();


                if (reportType.equals("1")) {

                    if (rs != null) {

                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "1.0 SADAREM Assessments");
                            clusterReportList.add(map);

                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PWD's Assessed");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);

                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PWD's Eligible");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);

                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Assessed and Rejected");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);

                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Direct Rejected");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);

                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("2")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "PWD's Assessed (Disability Wise)");
                            clusterReportList.add(map);

                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Locomotor");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "VisualImpairment");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "HearingImpairment");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "MentalRetardation");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "MentalIllness");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "MultipleDisability");
                            map.put("count", rs.getString(6));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("4")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "PWD's Assessed (Age Wise)");
                            //rs.getString(1));
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "0-6");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "7-18");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "19-35");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "36-50");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "50-65");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", ">65");
                            map.put("count", rs.getString(6));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("3")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "PWD's Assessed (Gender Wise)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Male");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Female");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("5")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "PWD's Assessed (Caste Wise)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "ST");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "SC");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "BC");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "OC");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Minority");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("6")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "PWD's Assessed (Education Wise)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Illiterate");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Below10th");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Class10th");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Intermediate");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Diploma");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Graduate");
                            map.put("count", rs.getString(6));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Postgraduate");
                            map.put("count", rs.getString(7));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("7")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "PWD's Assessed (Marital Wise)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Married");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "UnMarried");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Divorcee");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Widow");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Widower");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);

                        }
                    }
                } else if (reportType.equals("8")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "2.0 Mobilization of PWD's into SHG's (PWD)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PWD eligible for mobilization into SHG's(PWD) (As per SADAREM)");
                            map.put("count", rs.getString(1));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PWD mobilization into SHG's(PWD) (As per MIS)");
                            map.put("count", rs.getString(2));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PWD mobilization into SHG's(PWD) (As per IB Database)");
                            map.put("count", rs.getString(3));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("9")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "3.0 M Book Keeping (Static)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of SHG's(PWD) formed (As per MIS)");
                            //map.put("count", rs.getInt(2));
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of SHG's(PWD) formed (As per M-Book Keeping DB)");
                            //map.put("count", rs.getInt(2));
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Percentage");
                            //map.put("count", rs.getInt(2));
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);

                        }
                    }
                } else if (reportType.equals("10")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "4.0 Bank Linkages(2012-13)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of SHG's(PWD) Linked to Banks");
                            map.put("count", rs.getInt(1));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Amount of bank linkage (in Lakhs)");
                            String amount = rs.getString(2);
                            String[] s=amount.split("\\.");
                            if(s.length>0 && s[1].length()>2){
                                amount=s[0]+"."+s[1].substring(0,2);
                            }
                            map.put("count",amount );
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("11")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "5.0 Social Security (Pensions)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PWDs eligible for pensions");
                            map.put("count", rs.getInt(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PWDs receiving pensions");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("12")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "6.0 JBY Insurance");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PWDs enrolled under JBY(2012-13)");
                            map.put("count", rs.getInt(1));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("13")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "7.0 SCSP Data for selecting beneficiaries (Gender)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Male and Female)");
                            map.put("count", rs.getInt(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Male only)");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Female only)");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("25")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "SCSP Data for selecting beneficiaries (Education)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Illiterate)");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Below10th)");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Class10th)");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Intermediate)");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Diploma)");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Graduate)");
                            map.put("count", rs.getString(6));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Postgraduate)");
                            map.put("count", rs.getString(7));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("26")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "SCSP Data for selecting beneficiaries (Marital Status)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Married)");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(UnMarried)");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Divorcee)");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Widow)");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Widower)");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("14")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "8.0 TSP Data for selecting beneficiaries(Gender)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Male and Female)");
                            map.put("count", rs.getInt(1));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Male only)");
                            map.put("count", rs.getString(2));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Female only)");
                            map.put("count", rs.getString(3));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("27")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "TSP Data for selecting beneficiaries(Education)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Illiterate)");
                            map.put("count", rs.getString(1));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Below10th)");
                            map.put("count", rs.getString(2));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Class10th)");
                            map.put("count", rs.getString(3));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Intermediate)");
                            map.put("count", rs.getString(4));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Diploma)");
                            map.put("count", rs.getString(5));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Graduate)");
                            map.put("count", rs.getString(6));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Postgraduate)");
                            map.put("count", rs.getString(7));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("28")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "TSP Data for selecting beneficiaries(Marital Status)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Married)");
                            map.put("count", rs.getString(1));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(UnMarried)");
                            map.put("count", rs.getString(2));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Divorcee)");
                            map.put("count", rs.getString(3));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Widow)");
                            map.put("count", rs.getString(4));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PWDs in the age group 18-50(Widower)");
                            map.put("count", rs.getString(5));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("15")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "9.0 Social Security to PwD-JBY-Claims Settlement(2013-14)(Static)");
                            clusterReportList.add(map);

                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims (OverAll)");
                            map.put("count", "1443");
                            map.put("color", "#CCFFFF");

                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("16")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "Social Security to PwD-JBY-Claims Settlement(2013-14) Gender Wise(Static)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims Male");
                            map.put("count", "968");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims Female");
                            map.put("count", "475");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("17")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();
                            map.put("flag", "1");
                            map.put("Heading", "Social Security to PwD-JBY-Claims Settlement(2013-14) Caste Wise(Static)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims ST");
                            map.put("count", "95");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims SC");
                            map.put("count", "312");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims BC");
                            map.put("count", "753");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims OC");
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims Minority");
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("18")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "Social Security to PwD-JBY-Claims Settlement(2013-14) Age Wise(Static)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims 0-6");
                            map.put("count", "95");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims 7-18");
                            map.put("count", "312");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims 19-35");
                            map.put("count", "753");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims 36-50");
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims 50-65");
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims 65>");
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("19")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "Social Security to PwD-JBY-Claims Settlement(2013-14) DeathType Wise(Static)");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Accidental Death");
                            map.put("count", "39");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Normal Death");
                            map.put("count", "1405");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Partial Disability");
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Full Disability");
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Convert to Accident");
                            map.put("count", "0");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("20")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "10.0 Social Security to PwD-JBY-Claims Settlement Status(2013-14)(Static) ");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims reported at Callcenter");
                            map.put("count", "1444");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims rejected at Callcenter");
                            map.put("count", "34");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Eligible Claims");
                            map.put("count", "1410");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims submitted at LIC");
                            map.put("count", "885");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims pending at Callcenter");
                            map.put("count", "492");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims Settled by LIC");
                            map.put("count", "576");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims Pending by LIC");
                            map.put("count", "309");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Repudiated by LIC");
                            map.put("count", "0");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims distributed by ZS");
                            map.put("count", "15");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims to be distributed by ZS");
                            map.put("count", "561");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Invalid Claims");
                            map.put("count", "3");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("21")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "11.0 Assitive Devices ");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of camps for Assitive Devices");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
//                            map = new HashMap();
//                            map.put("flag", "2");
//                            map.put("name", "No of PwDs registered for Assitive Devices");
//                            map.put("count", rs.getString(2));
//                            map.put("color", "#CCFFFF");
//                            clusterReportList.add(map);
//                            map = new HashMap();
//                            map.put("flag", "2");
//                            map.put("name", "No of Assitive Devices Advised");
//                            map.put("count", rs.getString(3));
//                            map.put("color", "#CCFFFF");
//                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of Assitive Devices issued");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("22")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "12.0 Surgical Corrections");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of camps held");
                            map.put("count", rs.getString(1));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "No of PwDs registered at camps");
                            map.put("count", rs.getString(2));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Advised surgery");
                            map.put("count", rs.getString(3));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Advised Aids and Appliances");
                            map.put("count", rs.getString(4));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Advised further evaluation");
                            map.put("count", rs.getString(5));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Advised Physiotheraphy");
                            map.put("count", rs.getString(6));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Surgery Scheduled");
                            map.put("count", rs.getString(7));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Reported at hospital");
                            map.put("count", rs.getString(8));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Surgeries completed and discharged");
                            map.put("count", rs.getString(9));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Post surgery follow up");
                            map.put("count", rs.getString(10));
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("23")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "13.0 SADAREM Grievances");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Application Registered");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Documents To Be Uploaded");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Document Upload Rejected");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Field Verification Pending");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Field Verified and Ok");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Field Verification Rejected");
                            map.put("count", rs.getString(6));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "MPDO Pending");
                            map.put("count", rs.getString(7));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "MPDO Approved");
                            map.put("count", rs.getString(8));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "MPDO Rejected");
                            map.put("count", rs.getString(9));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PD Pending");
                            map.put("count", rs.getString(10));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PD Approved");
                            map.put("count", rs.getString(11));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "PD Rejected");
                            map.put("count", rs.getString(12));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Camp slot to be issued");
                            map.put("count", rs.getString(13));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Camp slot issued");
                            map.put("count", rs.getString(14));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Camp slot Rejected");
                            map.put("count", rs.getString(15));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Certificate Issues");
                            map.put("count", rs.getString(16));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Certificate Rejected");
                            map.put("count", rs.getString(17));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Overall Pending");
                            map.put("count", rs.getString(18));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                } else if (reportType.equals("24")) {
                    if (rs != null) {
                        while (rs.next()) {
                            map = new HashMap();

                            map.put("flag", "1");
                            map.put("Heading", "Grievances Wise Status Pending Requests");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Assessment Completed but not getting certificate");
                            map.put("count", rs.getString(1));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Date Of Birth Change");
                            map.put("count", rs.getString(2));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Duplicate");
                            map.put("count", rs.getString(3));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Identification Marks Change");
                            map.put("count", rs.getString(4));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Name Change");
                            map.put("count", rs.getString(5));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "New Certificate");
                            map.put("count", rs.getString(6));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Physical Impairment");
                            map.put("count", rs.getString(7));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Reassessment");
                            map.put("count", rs.getString(8));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Relation Name Change");
                            map.put("count", rs.getString(9));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Renewal change");
                            map.put("count", rs.getString(10));
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", "District Wise BreakUp");
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                }
            }

        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStateClusterReportList", "ClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getStateClusterReportList");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStateClusterReportList", "ClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getStateClusterReportList");

        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }//end of finally block
        return clusterReportList;
    }

    public int getStateWiseDistrictColumnCount(DataSource ds, String reportType) throws SADAREMDBException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            con = DBConnection.getConnection();
            // this query gets district id and district name
            String query = "select count(column_name) from dbo.Profilecolumns where reporttype=?"  ;
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportType);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    count = Integer.parseInt(rs.getString(1));
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStateWiseDistrictColumnCount", "ClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getStateWiseDistrictColumnCount");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStateWiseDistrictColumnCount", "ClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getStateWiseDistrictColumnCount");
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }//end of finally block
        return count;
    }

    public ArrayList getStateWiseDistrictColumnNames(DataSource ds, String reportType, ClusterReportForm clusterReportForm) throws SADAREMDBException, SQLException {
        Connection con = null;
        ArrayList columnList = new ArrayList();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Map map = null;
        try {

            con = DBConnection.getConnection();

            String query = "select column_name from dbo.Profilecolumns where reporttype=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reportType);
            rs = pstmt.executeQuery();
            map = new HashMap();
            if (clusterReportForm.getCluster_name() != null && !clusterReportForm.getCluster_name().equals("")) {
                map.put("columnName", "Mandal");
            } else if (clusterReportForm.getDistrict_name() != null && !clusterReportForm.getDistrict_name().equals("")) {
                map.put("columnName", "Cluster");
            } else {
                map.put("columnName", "District");
            }
            columnList.add(map);
            while (rs.next()) {
                map = new HashMap();
                map.put("columnName", rs.getString(1));
                columnList.add(map);
            }

        } //end of try block
        catch (SQLException sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStateWiseDistrictColumnNames", "ClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getStateWiseDistrictColumnNames");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStateWiseDistrictColumnNames", "ClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getStateWiseDistrictColumnNames");
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);

        }//end of finally block
        return columnList;
    }

    public ArrayList getStateWiseDistrictBreakup(DataSource ds, String reportType, int columnCount) throws SADAREMDBException, SQLException {
        Connection con = null;
        CallableStatement cstmt = null;
        ArrayList clusterList = new ArrayList();
        ResultSet rs = null;
        Map map = null;
        int j = 1;
        ArrayList data = null;
        try {

            con = DBConnection.getConnection();


            cstmt = con.prepareCall("{Call profilestatewisesplitProcedure(?)}");
            cstmt.setString(1, reportType);
            rs = cstmt.executeQuery();
            while (rs.next()) {

                // map = new HashMap();
                data = new ArrayList();
                if (reportType != null && !reportType.equalsIgnoreCase("9")
                        && !reportType.equalsIgnoreCase("15")
                        && !reportType.equalsIgnoreCase("16")
                        && !reportType.equalsIgnoreCase("17")
                        && !reportType.equalsIgnoreCase("18") && !reportType.equalsIgnoreCase("19")
                        && !reportType.equalsIgnoreCase("20")) {
                    for (int i = 1; i <= columnCount + 1; i++) {

                        data.add(rs.getString(i));
                    }
                }
                clusterList.add(data);
            }


        } //end of try block
        catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStateWiseDistrictBreakup", "ClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getStateWiseDistrictBreakup");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getStateWiseDistrictBreakup", "ClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getStateWiseDistrictBreakup");
        }//end of catch block
        finally {
            if (con != null) {
                con.close();
            }
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(cstmt);

        }//end of finally block
        return clusterList;
    }
//    public ArrayList getClusterReport(DataSource ds, ClusterReportForm clusterReportForm, String reportType) throws SADAREMDBException, SQLException {
//        Connection con = null;
//        CallableStatement cstmt = null;
//        //Statement stmt = null;
//        ArrayList clusterReportList = new ArrayList();
//        ResultSet rs = null;
//        String query = null;
//        Map map = null;
//        ArrayList tlist = null;
//        try {
//            con = DBConnection.getConnection();
//
//
//            // this query gets district id and district name
//            if (reportType != null) {
//
//                cstmt = con.prepareCall("{Call clusterReportProcedure(?,?,?,?)}");
//                cstmt.setString(1, reportType);
//                cstmt.setString(2, clusterReportForm.getDistrict_name());
//                cstmt.setString(3, clusterReportForm.getCluster_name());
//                cstmt.setString(4, clusterReportForm.getMandal_name());
//                rs = cstmt.executeQuery();
//                int totalassessed = 0;
//                int eligible = 0;
//                int rejected = 0;
//                int notrejected = 0;
//                if (reportType.equals("1")) {
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("mandal", rs.getString(2));
//                        map.put("cluster", rs.getString(3));
//                        map.put("phase", rs.getString(4));
//                        map.put("totalassessed", rs.getString(5));
//                        totalassessed = totalassessed + Integer.parseInt(rs.getString(5));
//                        map.put("eligible", rs.getString(6));
//                        eligible = eligible + Integer.parseInt(rs.getString(6));
//                        map.put("rejected", rs.getString(7));
//                        rejected = rejected + Integer.parseInt(rs.getString(7));
//                        map.put("notrejected", rs.getString(8));
//                        notrejected = notrejected + Integer.parseInt(rs.getString(8));
//                        map.put("dcount", rs.getString(9));
//                        map.put("ccount", rs.getString(10));
//                        clusterReportList.add(map);
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("totalassessed", totalassessed);
//                    map.put("eligible", eligible);
//                    map.put("rejected", rejected);
//                    map.put("notrejected", notrejected);
//                    clusterReportList.add(map);
//                } else if (reportType.equals("2")) {
//                    int st = 0;
//                    int sc = 0;
//                    int bc = 0;
//                    int oc = 0;
//                    int minority = 0;
//                    int agebelow18 = 0;
//                    int ageabove18 = 0;
//                    int male = 0;
//                    int female = 0;
//                    int illiterate = 0;
//                    int below10th = 0;
//                    int class10th = 0;
//                    int intermediate = 0;
//                    int diploma = 0;
//                    int graduate = 0;
//                    int postgraduate = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("mandal", rs.getString(2));
//                        map.put("cluster", rs.getString(3));
//                        map.put("phase", rs.getString(4));
//                        map.put("st", rs.getString(5));
//                        map.put("sc", rs.getString(6));
//                        map.put("bc", rs.getString(7));
//                        map.put("oc", rs.getString(8));
//                        map.put("minority", rs.getString(9));
//                        map.put("agebelow18", rs.getString(10));
//                        map.put("ageabove18", rs.getString(11));
//                        map.put("male", rs.getString(12));
//                        map.put("female", rs.getString(13));
//                        map.put("illiterate", rs.getString(14));
//                        map.put("below10th", rs.getString(15));
//                        map.put("class10th", rs.getString(16));
//                        map.put("intermediate", rs.getString(17));
//                        map.put("diploma", rs.getString(18));
//                        map.put("graduate", rs.getString(19));
//                        map.put("postgraduate", rs.getString(20));
//                        map.put("dcount", rs.getString(21));
//                        map.put("ccount", rs.getString(22));
//                        clusterReportList.add(map);
//                        st = st + Integer.parseInt(rs.getString(5));
//                        sc = sc + Integer.parseInt(rs.getString(6));
//                        bc = bc + Integer.parseInt(rs.getString(7));
//                        oc = oc + Integer.parseInt(rs.getString(8));
//                        minority = minority + Integer.parseInt(rs.getString(9));
//                        agebelow18 = agebelow18 + Integer.parseInt(rs.getString(10));
//                        ageabove18 = ageabove18 + Integer.parseInt(rs.getString(11));
//                        male = male + Integer.parseInt(rs.getString(12));
//                        female = female + Integer.parseInt(rs.getString(13));
//                        illiterate = illiterate + Integer.parseInt(rs.getString(14));
//                        below10th = below10th + Integer.parseInt(rs.getString(15));
//                        class10th = class10th + Integer.parseInt(rs.getString(16));
//                        intermediate = intermediate + Integer.parseInt(rs.getString(17));
//                        diploma = diploma + Integer.parseInt(rs.getString(18));
//                        graduate = graduate + Integer.parseInt(rs.getString(19));
//                        postgraduate = postgraduate + Integer.parseInt(rs.getString(20));
//
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("st", st);
//                    map.put("sc", sc);
//                    map.put("bc", bc);
//                    map.put("oc", oc);
//                    map.put("minority", minority);
//                    map.put("agebelow18", agebelow18);
//                    map.put("ageabove18", ageabove18);
//                    map.put("male", male);
//                    map.put("female", female);
//                    map.put("illiterate", illiterate);
//                    map.put("below10th", below10th);
//                    map.put("class10th", class10th);
//                    map.put("intermediate", intermediate);
//                    map.put("diploma", diploma);
//                    map.put("graduate", graduate);
//                    map.put("postgraduate", postgraduate);
//                    clusterReportList.add(map);
//                } else if (reportType.equals("3")) {
//                    int required = 0;
//                    int evolution = 0;
//                    int Phsiotherapy = 0;
//                    int completed = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("mandal", rs.getString(2));
//                        map.put("cluster", rs.getString(3));
//                        map.put("phase", rs.getString(4));
//                        map.put("required", rs.getString(5));
//                        map.put("evolution", rs.getString(6));
//                        map.put("Phsiotherapy", rs.getString(7));
//                        map.put("completed", rs.getString(8));
//                        map.put("dcount", rs.getString(9));
//                        map.put("ccount", rs.getString(10));
//                        clusterReportList.add(map);
//                        required = required + Integer.parseInt(rs.getString(5));
//                        evolution = evolution + Integer.parseInt(rs.getString(6));
//                        Phsiotherapy = Phsiotherapy + Integer.parseInt(rs.getString(7));
//                        completed = completed + Integer.parseInt(rs.getString(8));
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("required", required);
//                    map.put("evolution", evolution);
//                    map.put("Phsiotherapy", Phsiotherapy);
//                    map.put("completed", completed);
//
//                    clusterReportList.add(map);
//                } else if (reportType.equals("4")) {
//                    int adviced = 0;
//                    int distribution = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("mandal", rs.getString(2));
//                        map.put("cluster", rs.getString(3));
//                        map.put("phase", rs.getString(4));
//                        map.put("adviced", rs.getString(5));
//                        map.put("distribution", rs.getString(6));
//                        map.put("dcount", rs.getString(7));
//                        map.put("ccount", rs.getString(8));
//                        clusterReportList.add(map);
//                        adviced = adviced + Integer.parseInt(rs.getString(5));
//                        distribution = distribution + Integer.parseInt(rs.getString(6));
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("adviced", adviced);
//                    map.put("distribution", distribution);
//
//                    clusterReportList.add(map);
//                } else if (reportType.equals("5")) {
//                    int count1 = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("cluster", rs.getString(2));
//                        map.put("mandal", rs.getString(3));
//                        map.put("count1", rs.getString(4));
//                        map.put("dcount", rs.getString(5));
//                        map.put("ccount", rs.getString(6));
//                        clusterReportList.add(map);
//                        count1 = count1 + Integer.parseInt(rs.getString(4));
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("count1", count1);
//                    clusterReportList.add(map);
//                } else if (reportType.equals("6")) {
//                    int closed = 0;
//                    int pending = 0;
//                    int clopencamp = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("cluster", rs.getString(2));
//                        map.put("mandal", rs.getString(3));
//                        map.put("closed", rs.getString(4));
//                        map.put("pending", rs.getString(5));
//                        map.put("clopencamp", rs.getString(6));
//                        map.put("dcount", rs.getString(7));
//                        map.put("ccount", rs.getString(8));
//
//                        clusterReportList.add(map);
//                        closed = closed + Integer.parseInt(rs.getString(4));
//                        pending = pending + Integer.parseInt(rs.getString(5));
//                        clopencamp = clopencamp + Integer.parseInt(rs.getString(6));
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("closed", closed);
//                    map.put("pending", pending);
//                    map.put("clopencamp", clopencamp);
//
//                    clusterReportList.add(map);
//                } else if (reportType.equals("7")) {
//                    int count = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("cluster", rs.getString(2));
//                        map.put("mandal", rs.getString(3));
//                        map.put("count", rs.getString(4));
//                        map.put("dcount", rs.getString(5));
//                        map.put("ccount", rs.getString(6));
//                        clusterReportList.add(map);
//                        count = count + Integer.parseInt(rs.getString(4));
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("count", count);
//                    clusterReportList.add(map);
//                } else if (reportType.equals("8") || reportType.equals("9") || reportType.equals("10") || reportType.equals("11")) {
//                    int Locomotor = 0;
//                    int VisualImpairment = 0;
//                    int HearingImpairment = 0;
//                    int MentalRetardation = 0;
//                    int MentalIllness = 0;
//                    int MultipleDisability = 0;
//                    int lliterate = 0;
//                    int Below10th = 0;
//                    int Class10th = 0;
//                    int intermediate = 0;
//                    int Diploma = 0;
//                    int Graduate = 0;
//                    int Postgraduate = 0;
//                    int Married = 0;
//                    int UnMarried = 0;
//                    int Divorcee = 0;
//                    int Widow = 0;
//                    int Widower = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("cluster", rs.getString(2));
//                        map.put("mandal", rs.getString(3));
//
//                        map.put("Locomotor", rs.getString(4));
//                        map.put("VisualImpairment", rs.getString(5));
//                        map.put("HearingImpairment", rs.getString(6));
//                        map.put("MentalRetardation", rs.getString(7));
//                        map.put("MentalIllness", rs.getString(8));
//                        map.put("MultipleDisability", rs.getString(9));
//                        map.put("lliterate", rs.getString(10));
//                        map.put("Below10th", rs.getString(11));
//                        map.put("Class10th", rs.getString(12));
//                        map.put("intermediate", rs.getString(13));
//                        map.put("Diploma", rs.getString(14));
//                        map.put("Graduate", rs.getString(15));
//                        map.put("Postgraduate", rs.getString(16));
//                        map.put("Married", rs.getString(17));
//                        map.put("UnMarried", rs.getString(18));
//                        map.put("Divorcee", rs.getString(19));
//                        map.put("Widow", rs.getString(20));
//                        map.put("Widower", rs.getString(21));
//
//                        map.put("dcount", rs.getString(22));
//                        map.put("ccount", rs.getString(23));
//                        clusterReportList.add(map);
//                        Locomotor = Locomotor + Integer.parseInt(rs.getString(4));
//                        VisualImpairment = VisualImpairment + Integer.parseInt(rs.getString(5));
//                        HearingImpairment = HearingImpairment + Integer.parseInt(rs.getString(6));
//                        MentalRetardation = MentalRetardation + Integer.parseInt(rs.getString(7));
//                        MentalIllness = MentalIllness + Integer.parseInt(rs.getString(8));
//                        MultipleDisability = MultipleDisability + Integer.parseInt(rs.getString(9));
//                        lliterate = lliterate + Integer.parseInt(rs.getString(10));
//                        Below10th = Below10th + Integer.parseInt(rs.getString(11));
//                        Class10th = Class10th + Integer.parseInt(rs.getString(12));
//                        intermediate = intermediate + Integer.parseInt(rs.getString(13));
//                        Diploma = Diploma + Integer.parseInt(rs.getString(14));
//                        Graduate = Graduate + Integer.parseInt(rs.getString(15));
//                        Postgraduate = Postgraduate + Integer.parseInt(rs.getString(16));
//                        Married = Married + Integer.parseInt(rs.getString(17));
//                        UnMarried = UnMarried + Integer.parseInt(rs.getString(18));
//                        Divorcee = Divorcee + Integer.parseInt(rs.getString(19));
//                        Widow = Widow + Integer.parseInt(rs.getString(20));
//                        Widower = Widower + Integer.parseInt(rs.getString(21));
//
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("Locomotor", Locomotor);
//                    map.put("VisualImpairment", VisualImpairment);
//                    map.put("HearingImpairment", HearingImpairment);
//                    map.put("MentalRetardation", MentalRetardation);
//                    map.put("MentalIllness", MentalIllness);
//                    map.put("MultipleDisability", MultipleDisability);
//                    map.put("lliterate", lliterate);
//                    map.put("Below10th", Below10th);
//                    map.put("Class10th", Class10th);
//                    map.put("intermediate", intermediate);
//                    map.put("Diploma", Diploma);
//                    map.put("Graduate", Graduate);
//                    map.put("Postgraduate", Postgraduate);
//                    map.put("Married", Married);
//                    map.put("UnMarried", UnMarried);
//                    map.put("Divorcee", Divorcee);
//                    map.put("Widow", Widow);
//                    map.put("Widower", Widower);
//                    clusterReportList.add(map);
//                } else if (reportType.equals("12")) {
//                    int Pwdshgs = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("cluster", rs.getString(2));
//                        map.put("mandal", rs.getString(3));
//                        map.put("Pwdshgs", rs.getInt(4));
//                        map.put("dcount", rs.getString(5));
//                        map.put("ccount", rs.getString(6));
//                        clusterReportList.add(map);
//
//                        Pwdshgs = Pwdshgs + Integer.parseInt(rs.getString(4));
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("Pwdshgs", Pwdshgs);
//                    clusterReportList.add(map);
//                } else if (reportType.equals("13")) {
//                    int noofloans = 0;
//                    int amount = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("cluster", rs.getString(2));
//                        map.put("mandal", rs.getString(3));
//                        map.put("noofloans", rs.getString(4));
//                        map.put("amount", rs.getInt(5));
//                        map.put("dcount", rs.getString(6));
//                        map.put("ccount", rs.getString(7));
//                        clusterReportList.add(map);
//
//                        noofloans = noofloans + Integer.parseInt(rs.getString(4));
//                        amount = amount + rs.getInt(5);
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("noofloans", noofloans);
//                    map.put("amount", amount);
//                    clusterReportList.add(map);
//                } else if (reportType.equals("14")) {
//                    int Phaseonepensiondata = 0;
//                    int phasetwopensiondata = 0;
//                    int phasefourpensiondata = 0;
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("cluster", rs.getString(2));
//                        map.put("mandal", rs.getString(3));
//                        map.put("Phaseonepensiondata", rs.getInt(4));
//                        map.put("phasetwopensiondata", rs.getString(5));
//                        map.put("phasefourpensiondata", rs.getString(6));
//                        map.put("dcount", rs.getString(7));
//                        map.put("ccount", rs.getString(8));
//                        clusterReportList.add(map);
//
//                        Phaseonepensiondata = Phaseonepensiondata + Integer.parseInt(rs.getString(4));
//                        phasetwopensiondata = phasetwopensiondata + rs.getInt(5);
//                        phasefourpensiondata = phasefourpensiondata + rs.getInt(6);
//                    }
//                    map = new HashMap();
//                    map.put("district", "Total");
//                    map.put("Phaseonepensiondata", Phaseonepensiondata);
//                    map.put("phasetwopensiondata", phasetwopensiondata);
//                    map.put("phasefourpensiondata", phasefourpensiondata);
//                    clusterReportList.add(map);
//                }
//            }
//
//        } //end of try block
//        catch (SQLException sqlEx) {
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getClusterReport", "ClusterReportDAO", "DataBase");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getClusterReport");
//        } catch (Exception sqlEx) {
//
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getClusterReport", "ClusterReportDAO", "Code");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getClusterReport");
//
//        }//end of catch block
//        finally {
//            if (con != null) {
//                con.close();
//            }
//            DBConnection.closeResultSet(rs);
//            DBConnection.closeStatement(cstmt);
//
//        }//end of finally block
//        return clusterReportList;
//    }
//
//    public ArrayList getDistrictClusterReport(DataSource ds, ClusterReportForm clusterReportForm, String reportType) throws SADAREMDBException, SQLException {
//        Connection con = null;
//        CallableStatement cstmt = null;
//        //Statement stmt = null;
//        ArrayList clusterReportList = new ArrayList();
//        ResultSet rs = null;
//        String query = null;
//        Map map = null;
//        ArrayList tlist = null;
//        try {
//            con = DBConnection.getConnection();
//
//
//            // this query gets district id and district name
//            if (reportType != null) {
//
//                cstmt = con.prepareCall("{Call clusterdistrictProcedure(?,?,?,?)}");
//                cstmt.setString(1, reportType);
//                cstmt.setString(2, clusterReportForm.getDistrict_name());
//                cstmt.setString(3, clusterReportForm.getCluster_name());
//                cstmt.setString(4, clusterReportForm.getMandal_name());
//
//                rs = cstmt.executeQuery();
//                if (reportType.equals("1")) {
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Total Assessed");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Eligible");
//                        map.put("clusterName", rs.getString(3));
//
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("2")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "ST");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "SC");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "BC");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "OC");
//                        map.put("clusterName", rs.getString(5));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Minority");
//                        map.put("clusterName", rs.getString(6));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Age below 18");
//                        map.put("clusterName", rs.getString(7));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Age above 18");
//                        map.put("clusterName", rs.getString(8));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Male");
//                        map.put("clusterName", rs.getString(9));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Female");
//                        map.put("clusterName", rs.getString(10));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Illiterate");
//                        map.put("clusterName", rs.getString(11));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Below10th");
//                        map.put("clusterName", rs.getString(12));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Class10th");
//                        map.put("clusterName", rs.getString(13));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Intermediate");
//                        map.put("clusterName", rs.getString(14));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Diploma");
//                        map.put("clusterName", rs.getString(15));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Graduate");
//                        map.put("clusterName", rs.getString(16));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Postgraduate");
//                        map.put("clusterName", rs.getString(17));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("3")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Required");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Evolution");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "physiotherapy");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Completed");
//                        map.put("clusterName", rs.getString(5));
//
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("4")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Adviced");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Distribution");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("5")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "JBY Polacies");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("6")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Closed");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Pending");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Closed/Pendingwithcampincharge");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("7")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Scholarships");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//
//                    }
//                } else if (reportType.equals("8") || reportType.equals("9") || reportType.equals("10") || reportType.equals("11")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Locomotor");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "VisualImpairment");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "HearingImpairment");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "MentalRetardation");
//                        map.put("clusterName", rs.getString(5));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "MentalIllness");
//                        map.put("clusterName", rs.getString(6));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "MultipleDisability");
//                        map.put("clusterName", rs.getString(7));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "3");
//                        map.put("cluster", "Total");
//                        map.put("clusterName", Integer.parseInt(rs.getString(3)) + Integer.parseInt(rs.getString(4)) + Integer.parseInt(rs.getString(5)) + Integer.parseInt(rs.getString(6)) + Integer.parseInt(rs.getString(7)) + Integer.parseInt(rs.getString(2)));
//                        clusterReportList.add(map);
//
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "lliterate");
//                        map.put("clusterName", rs.getString(8));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Below10th");
//                        map.put("clusterName", rs.getString(9));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Class10th");
//                        map.put("clusterName", rs.getString(10));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "intermediate");
//                        map.put("clusterName", rs.getString(11));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Diploma");
//                        map.put("clusterName", rs.getString(12));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Graduate");
//                        map.put("clusterName", rs.getString(13));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Postgraduate");
//                        map.put("clusterName", rs.getString(14));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "3");
//                        map.put("cluster", "Total");
//                        map.put("clusterName", Integer.parseInt(rs.getString(8))
//                                + Integer.parseInt(rs.getString(9))
//                                + Integer.parseInt(rs.getString(10))
//                                + Integer.parseInt(rs.getString(11))
//                                + Integer.parseInt(rs.getString(12))
//                                + Integer.parseInt(rs.getString(13))
//                                + Integer.parseInt(rs.getString(14)));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Married");
//                        map.put("clusterName", rs.getString(15));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "UnMarried");
//                        map.put("clusterName", rs.getString(16));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Divorcee");
//                        map.put("clusterName", rs.getString(17));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Widow");
//                        map.put("clusterName", rs.getString(18));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Widower");
//                        map.put("clusterName", rs.getString(19));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "3");
//                        map.put("cluster", "Total");
//                        map.put("clusterName", Integer.parseInt(rs.getString(15))
//                                + Integer.parseInt(rs.getString(16))
//                                + Integer.parseInt(rs.getString(17))
//                                + Integer.parseInt(rs.getString(18))
//                                + Integer.parseInt(rs.getString(19)));
//                        clusterReportList.add(map);
//
//
//                    }
//                } else if (reportType.equals("12")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "PWD SHG (MIS)");
//                        map.put("clusterName", rs.getInt(2));
//                        clusterReportList.add(map);
//
//                    }
//                } else if (reportType.equals("13")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "No of Loans");
//                        map.put("clusterName", rs.getInt(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Amount");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//
//                    }
//                } else if (reportType.equals("14")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//
//                        map.put("flag", "1");
//                        map.put("cluster", "District");
//                        map.put("clusterName", rs.getString(1));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "PhaseI");
//                        map.put("clusterName", rs.getInt(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "PhaseII");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "PhaseIV");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                    }
//                }
//            }
//
//        } //end of try block
//        catch (SQLException sqlEx) {
//
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getClusterReport", "ClusterReportDAO", "DataBase");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getClusterReport");
//        } catch (Exception sqlEx) {
//
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getClusterReport", "ClusterReportDAO", "Code");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getClusterReport");
//
//        }//end of catch block
//        finally {
//            if (con != null) {
//                con.close();
//            }
//            DBConnection.closeResultSet(rs);
//            DBConnection.closeStatement(cstmt);
//
//        }//end of finally block
//        return clusterReportList;
//    }
//
//    public ArrayList getClusterConfirmationNoReport(DataSource ds, ClusterReportForm clusterReportForm, String reportType) throws SADAREMDBException, SQLException {
//        Connection con = null;
//        CallableStatement cstmt = null;
//        //Statement stmt = null;
//        ArrayList clusterReportList = new ArrayList();
//        ResultSet rs = null;
//        String query = null;
//        Map map = null;
//        ArrayList tlist = null;
//        try {
//            con = DBConnection.getConnection();
//
//
//            // this query gets district id and district name
//            if (reportType != null) {
//
//                cstmt = con.prepareCall("{Call clusterReportConfirmationNoProcedure(?,?,?,?)}");
//                cstmt.setString(1, reportType);
//                cstmt.setString(2, clusterReportForm.getDistrict_name());
//                cstmt.setString(3, clusterReportForm.getCluster_name());
//                cstmt.setString(4, clusterReportForm.getMandal_name());
//
//                rs = cstmt.executeQuery();
//                if (reportType.equals("1")) {
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Total Assessed");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Eligible");
//                        map.put("clusterName", rs.getString(4));
//
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("2")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "ST");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "SC");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "BC");
//                        map.put("clusterName", rs.getString(5));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "OC");
//                        map.put("clusterName", rs.getString(6));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Minority");
//                        map.put("clusterName", rs.getString(7));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Age below 18");
//                        map.put("clusterName", rs.getString(8));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Age above 18");
//                        map.put("clusterName", rs.getString(9));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Male");
//                        map.put("clusterName", rs.getString(10));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Female");
//                        map.put("clusterName", rs.getString(11));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Illiterate");
//                        map.put("clusterName", rs.getString(12));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Below10th");
//                        map.put("clusterName", rs.getString(13));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Class10th");
//                        map.put("clusterName", rs.getString(14));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Intermediate");
//                        map.put("clusterName", rs.getString(15));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Diploma");
//                        map.put("clusterName", rs.getString(16));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Graduate");
//                        map.put("clusterName", rs.getString(17));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Postgraduate");
//                        map.put("clusterName", rs.getString(18));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("3")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Required");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Evolution");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "physiotherapy");
//                        map.put("clusterName", rs.getString(5));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Completed");
//                        map.put("clusterName", rs.getString(6));
//
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("4")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Adviced");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Distribution");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("5")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "JBY Polacies");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("6")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Closed");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Pending");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Closed/Pendingwithcampincharge");
//                        map.put("clusterName", rs.getString(5));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("7")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Scholarships");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//
//                    }
//                } else if (reportType.equals("8") || reportType.equals("9") || reportType.equals("10") || reportType.equals("11")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Locomotor");
//                        map.put("clusterName", rs.getString(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "VisualImpairment");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "HearingImpairment");
//                        map.put("clusterName", rs.getString(5));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "MentalRetardation");
//                        map.put("clusterName", rs.getString(6));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "MentalIllness");
//                        map.put("clusterName", rs.getString(7));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "MultipleDisability");
//                        map.put("clusterName", rs.getString(8));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "3");
//                        map.put("cluster", "Total");
//                        map.put("clusterName", Integer.parseInt(rs.getString(3)) + Integer.parseInt(rs.getString(4)) + Integer.parseInt(rs.getString(5)) + Integer.parseInt(rs.getString(6)) + Integer.parseInt(rs.getString(7)) + Integer.parseInt(rs.getString(2)));
//                        clusterReportList.add(map);
//
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "lliterate");
//                        map.put("clusterName", rs.getString(9));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Below10th");
//                        map.put("clusterName", rs.getString(10));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Class10th");
//                        map.put("clusterName", rs.getString(11));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "intermediate");
//                        map.put("clusterName", rs.getString(12));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Diploma");
//                        map.put("clusterName", rs.getString(13));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Graduate");
//                        map.put("clusterName", rs.getString(14));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Postgraduate");
//                        map.put("clusterName", rs.getString(15));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "3");
//                        map.put("cluster", "Total");
//                        map.put("clusterName", Integer.parseInt(rs.getString(9))
//                                + Integer.parseInt(rs.getString(10))
//                                + Integer.parseInt(rs.getString(11))
//                                + Integer.parseInt(rs.getString(12))
//                                + Integer.parseInt(rs.getString(13))
//                                + Integer.parseInt(rs.getString(14))
//                                + Integer.parseInt(rs.getString(15)));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Married");
//                        map.put("clusterName", rs.getString(16));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "UnMarried");
//                        map.put("clusterName", rs.getString(17));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Divorcee");
//                        map.put("clusterName", rs.getString(18));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Widow");
//                        map.put("clusterName", rs.getString(19));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Widower");
//                        map.put("clusterName", rs.getString(20));
//                        map = new HashMap();
//                        clusterReportList.add(map);
//                        map.put("flag", "3");
//                        map.put("cluster", "Total");
//                        map.put("clusterName", Integer.parseInt(rs.getString(16))
//                                + Integer.parseInt(rs.getString(17))
//                                + Integer.parseInt(rs.getString(18))
//                                + Integer.parseInt(rs.getString(19))
//                                + Integer.parseInt(rs.getString(20)));
//                        clusterReportList.add(map);
//                    }
//                } else if (reportType.equals("12")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "PWD SHG (MIS)");
//                        map.put("clusterName", rs.getInt(3));
//                        clusterReportList.add(map);
//
//                    }
//                } else if (reportType.equals("13")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "No of Loans");
//                        map.put("clusterName", rs.getInt(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "Amount");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//
//                    }
//                } else if (reportType.equals("14")) {
//
//                    while (rs.next()) {
//                        map = new HashMap();
//                        map.put("district", rs.getString(1));
//                        map.put("flag", "1");
//                        map.put("cluster", "Cluster");
//                        map.put("clusterName", rs.getString(2));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "PhaseI");
//                        map.put("clusterName", rs.getInt(3));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "phaseII");
//                        map.put("clusterName", rs.getString(4));
//                        clusterReportList.add(map);
//                        map = new HashMap();
//                        map.put("flag", "2");
//                        map.put("cluster", "phaseIV");
//                        map.put("clusterName", rs.getString(5));
//                        clusterReportList.add(map);
//                    }
//                }
//            }
//
//        } //end of try block
//        catch (SQLException sqlEx) {
//
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getClusterReport", "ClusterReportDAO", "DataBase");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getClusterReport");
//        } catch (Exception sqlEx) {
//
//            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getClusterReport", "ClusterReportDAO", "Code");
//            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ClusterReportDAO", "getClusterReport");
//
//        }//end of catch block
//        finally {
//            if (con != null) {
//                con.close();
//            }
//            DBConnection.closeResultSet(rs);
//            DBConnection.closeStatement(cstmt);
//
//        }//end of finally block
//        return clusterReportList;
//    }
}
