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
import org.bf.disability.form.ClusterReportForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 728056
 */
public class ProfileReportDAO {

    public ArrayList districtClusterReportList(DataSource ds, ClusterReportForm clusterReportForm) throws SADAREMDBException, SQLException {
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

            String breakUp = "Cluster Wise BreakUp";

            if (clusterReportForm.getCluster_name() != null) {
                breakUp = "Mandal Wise BreakUp";
            }
            if (clusterReportForm.getMandal_name() != null) {
                breakUp = "";
            }
            // this query gets district id and district name
            boolean firstFlag = true;
            boolean secondFlag = true;
            int newColumns = 11;
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

                if (clusterReportForm.getMandal_name() != null) {

                    cstmt = con.prepareCall("{Call profilemandalwiseProcedure(?,?,?,?)}");
                } else if (clusterReportForm.getCluster_name() != null && !clusterReportForm.getCluster_name().equals("0")) {

                    cstmt = con.prepareCall("{Call profileareaclusterwiseProcedure(?,?,?)}");
                } else {

                    cstmt = con.prepareCall("{Call profiledistrictwiseProcedure(?,?)}");
                }
                cstmt.setString(1, reportType);
                cstmt.setString(2, clusterReportForm.getDistrict_name());
                if (clusterReportForm.getCluster_name() != null) {
                    cstmt.setString(3, clusterReportForm.getCluster_name());
                }
                if (clusterReportForm.getMandal_name() != null) {
                    cstmt.setString(4, clusterReportForm.getMandal_name());
                }
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
                            map.put("name", breakUp);
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);

//                            if (clusterReportForm.getDistrict_name() != null && clusterReportForm.getMandal_name() == null && clusterReportForm.getCluster_name() == null) {

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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            String[] s = amount.split("\\.");

                            if (s.length > 0 && s[1].length() > 2) {

                                amount = s[0] + "." + s[1].substring(0, 2);
                            }
                            map.put("count", amount);
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#CCFFFF");

                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", breakUp);
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
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims Female");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", breakUp);
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
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims SC");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims BC");
                            map.put("count", "Data Yet to be Received");
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
                            map.put("name", breakUp);
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
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims 7-18");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims 19-35");
                            map.put("count", "Data Yet to be Received");
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
                            map.put("name", breakUp);
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
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Normal Death");
                            map.put("count", "Data Yet to be Received");
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
                            map.put("name", breakUp);
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
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Total Claims rejected at Callcenter");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Eligible Claims");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims submitted at LIC");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims pending at Callcenter");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims Settled by LIC");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims Pending by LIC");
                            map.put("count", "Data Yet to be Received");
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
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Claims to be distributed by ZS");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "2");
                            map.put("name", "Invalid Claims");
                            map.put("count", "Data Yet to be Received");
                            map.put("color", "#FFFFCC");
                            clusterReportList.add(map);
                            map = new HashMap();
                            map.put("flag", "3");
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
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
                            map.put("name", breakUp);
                            map.put("reportId", reportType);
                            map.put("color", "#CCFFFF");
                            clusterReportList.add(map);
                        }
                    }
                }
            }
        } //end of try block
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "districtClusterReportList", "ProfileReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ProfileReportDAO", "districtClusterReportList");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "districtClusterReportList", "ProfileReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ProfileReportDAO", "districtClusterReportList");

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

    public ArrayList getBreakupList(DataSource ds, String reportType, int columnsCount, ClusterReportForm clusterReportForm) throws SQLException, SADAREMDBException {
        Connection con = null;
        CallableStatement cstmt = null;
        ArrayList clusterList = new ArrayList();
        ResultSet rs = null;
        Map map = null;
        int j = 1;
        ArrayList data = null;
        try {

            con = DBConnection.getConnection();
            if (clusterReportForm.getCluster_name() != null) {

                cstmt = con.prepareCall("{Call profilemandalwisesplitProcedure(?,?,?)}");
            } else {

                cstmt = con.prepareCall("{Call profiledistrictwisesplitProcedure(?,?)}");
            }
            cstmt.setString(1, reportType);
            cstmt.setString(2, clusterReportForm.getDistrict_name());
            if (clusterReportForm.getCluster_name() != null) {
                cstmt.setString(3, clusterReportForm.getCluster_name());
            }
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
                    for (int i = 1; i <= columnsCount + 1; i++) {

                        data.add(rs.getString(i));
                    }
                }
                clusterList.add(data);
            }


        } //end of try block
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getBreakupList", "ProfileReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ProfileReportDAO", "getBreakupList");
        } catch (Exception sqlEx) {

            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getBreakupList", "ProfileReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "ProfileReportDAO", "getBreakupList");
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
}


