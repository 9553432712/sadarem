/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
//import org.bf.disability.form.MandalClusterlevelPwdForm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 842412
 */
public class PwdsDisabilityPensionDAO {

    public ArrayList getDisabilityPensionsDetails(DataSource ds) {
        ArrayList pensionData = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        String query = null;
        Statement stmt = null;
        CallableStatement cstmt = null;
        Map map = null;
        try {
            con = DBConnection.getConnection();
//            query = "select disabilityname,disabilty40to79,interpercentage40to79,disabilty80,internalpercentage80,percentage40to79,percentage80above from dbo.percentagewise";
//            stmt = con.createStatement();
            cstmt = con.prepareCall("{Call  SP_CEO_PENSIONSCOVERED_DISABILITY_WISE}");

            rs = cstmt.executeQuery();
            double totalDis40to90 = 0.0, totalDis80 = 0.0, totalPer80 = 0.0, total = 0.0, allTotal = 0.0, avg80 = 0.0;
            double totalPer40to79 = 0.0;
            double avg40to79 = 0.0;
            double avg40to90 = 0.0;
            BigDecimal s = new BigDecimal("100.00");
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("disabilityId", rs.getString(1));
                    map.put("disability", rs.getString(2));

                    
                    map.put("dis40to90", NumberFormat.getIntegerInstance().format(rs.getInt(3)));
                    map.put("inter40to70", round(rs.getDouble(4),2));
                    map.put("dis80", NumberFormat.getIntegerInstance().format(round(rs.getDouble(5),2)));
                    map.put("inter80", rs.getString(6));
                    map.put("per40to79", rs.getString(7));
                    map.put("per80", rs.getString(8));

                    totalDis40to90 = totalDis40to90 + rs.getInt(3);
                    totalPer40to79 = totalPer40to79 + rs.getDouble(5);
                    totalDis80 = totalDis80 + rs.getDouble(8);
                    totalPer80 = totalPer80 + rs.getDouble(7);
                    total = rs.getInt(3) + rs.getInt(5);
                    allTotal = allTotal + total;
                    map.put("total", NumberFormat.getIntegerInstance().format(total));
                    pensionData.add(map);
                }
            }

            if (pensionData.size() > 0) {
                int pensionSize = (pensionData.size());
                avg40to90 = (totalDis40to90);
                avg40to79 = (totalPer40to79);
                totalDis80 = (totalDis80 /(pensionData.size()));
                avg80 = (totalPer80 / (pensionData.size()));
                map.put("totalDis40to90", NumberFormat.getIntegerInstance().format(avg40to90));
                map.put("avg80", round(avg80, 2));
                map.put("avg40to79",  NumberFormat.getIntegerInstance().format(avg40to79));

                map.put("totalDis80", round(totalDis80,2));
                map.put("allTotal",NumberFormat.getIntegerInstance().format(allTotal));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeStatement(cstmt);
			DBConnection.closeConnection(con); 
        }
        return pensionData;
    }

    public ArrayList getPwdsPensionDetails(DataSource ds) {
        ArrayList pwdPensionData = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        String query = null;
        Statement stmt = null;
        Map map = null;
        CallableStatement cstmt = null;
        try {
            con = DBConnection.getConnection();
//            query = "select district_id,district_name,census,Totalassessed,coverageaganistcensus,Eligible,pensionscoverage,pensioncoveredaganisteligibility from dbo.pensionpopulation";
//            stmt = con.createStatement();
            cstmt = con.prepareCall("{Call Sp_Analysis_PwD_Population}");
            rs = cstmt.executeQuery();

            double totalCensus = 0, totalAssessed = 0, totalCoverageaganistcensus = 0, totalEligible = 0, totalPensionscoverage = 0, totalPensioncoveredaganisteligibility = 0, angCoverageaganistcensus = 0, avgPensioncoveredaganisteligibility = 0;
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("district_id", rs.getString(1));
                    map.put("district_name", rs.getString(2));
                    map.put("census", NumberFormat.getIntegerInstance().format(rs.getInt(3)));
                    map.put("Totalassessed", NumberFormat.getIntegerInstance().format(rs.getInt(4)));

                    map.put("coverageaganistcensus", round(rs.getDouble(5), 2));
                    map.put("Eligible", NumberFormat.getIntegerInstance().format(rs.getInt(6)));
                    map.put("pensionscoverage",NumberFormat.getIntegerInstance().format(rs.getInt(7)));
                    map.put("pensioncoveredaganisteligibility", round(rs.getDouble(8), 2));
                    totalCensus = totalCensus + rs.getInt(3);
                    totalAssessed = totalAssessed + rs.getInt(4);
                    totalCoverageaganistcensus = totalCoverageaganistcensus + rs.getDouble(5);

                    totalEligible = totalEligible + rs.getInt(6);
                    totalPensionscoverage = totalPensionscoverage + rs.getInt(7);
                    totalPensioncoveredaganisteligibility = totalPensioncoveredaganisteligibility + rs.getInt(8);
                    pwdPensionData.add(map);
                }
            }

            if (pwdPensionData.size() > 0) {

                angCoverageaganistcensus = (totalCoverageaganistcensus / (pwdPensionData.size()));
                avgPensioncoveredaganisteligibility = (totalPensioncoveredaganisteligibility / (pwdPensionData.size()));

                map.put("totalCensus",  NumberFormat.getIntegerInstance().format(totalCensus));
                map.put("totalAssessed",NumberFormat.getIntegerInstance().format(totalAssessed));
                map.put("angCoverageaganistcensus", round(angCoverageaganistcensus, 2));
                map.put("totalEligible", NumberFormat.getIntegerInstance().format(totalEligible));
                map.put("totalPensionscoverage", NumberFormat.getIntegerInstance().format(totalPensionscoverage));
                map.put("avgPensioncoveredaganisteligibility", round(avgPensioncoveredaganisteligibility, 2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(stmt);
			DBConnection.closeStatement(cstmt);
			DBConnection.closeConnection(con); 
        }
        
         
        return pwdPensionData;
    }

    public static BigDecimal round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
}
