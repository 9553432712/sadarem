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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.dto.MonthWiseCumulativeReportDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class MonthWiseCumulativeReportDAO {

    public ArrayList getMonthReport(DataSource ds, String fromdate, String toDate, String district, String mandal, String village) throws SADAREMDBException, SQLException, ParseException {
        ArrayList monthrep = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        Map map = null;
        Date fdate = new SimpleDateFormat("dd/mm/yyyy").parse(fromdate);
        String frodate = new SimpleDateFormat("mm/dd/yyyy").format(fdate);
        Date tdate = new SimpleDateFormat("dd/mm/yyyy").parse(toDate);
        String todate = new SimpleDateFormat("mm/dd/yyyy").format(tdate);

        CallableStatement callst = null;
        MonthWiseCumulativeReportDTO serviceDTO = null;
        try {

            con = DBConnection.getConnection();

            if (district.equals("0")) {
                callst = con.prepareCall("{call SSP_ASSESEDREPORT_DISTRICT(? , ?)}");
                callst.setString(1, fromdate);
                callst.setString(2, toDate);
            } else if (!district.equals("0") && mandal.equals("0")) {
                callst = con.prepareCall("{call SSP_ASSESEDREPORT_MANDAL(?, ? , ?)}");
                callst.setString(1, district);
                callst.setString(2, fromdate);
                callst.setString(3, toDate);
            } else if (!district.equals("0") && !mandal.equals("0") && village.equals("0")) {
                callst = con.prepareCall("{call SSP_ASSESEDREPORT_village(?, ? , ?,?)}");
                callst.setString(1, district);
                callst.setString(2, mandal);
                callst.setString(3, fromdate);
                callst.setString(4, toDate);
            } else if (!district.equals("0") && !mandal.equals("0") && !village.equals("0")) {
                callst = con.prepareCall("{call SSP_ASSESEDREPORT_Habitation(?, ? , ?, ?, ?)}");
                callst.setString(1, district);
                callst.setString(2, mandal);
                callst.setString(3, village);
                callst.setString(4, fromdate);
                callst.setString(5, toDate);
            }
            rs = callst.executeQuery();
            while (rs.next()) {
                map = new HashMap();
                map.put("sno", rs.getString(1));
                map.put("district", rs.getString(2));
                map.put("phaseI", rs.getString(3));

                map.put("phaseII", rs.getString(4));

                map.put("phaseIII", rs.getString(5));
                int old = Integer.parseInt(rs.getString(6)) - (Integer.parseInt(rs.getString(7)) + Integer.parseInt(rs.getString(8)));
                map.put("phaseIV", String.valueOf(old));

                map.put("rachabandaI", rs.getString(7));

                map.put("rachabandaII", rs.getString(8));

                map.put("attended", rs.getString(9));

                map.put("unattended", rs.getString(11));

                map.put("eligible4150", rs.getString(12));

                map.put("eligible5160", rs.getString(13));

                map.put("eligible6170", rs.getString(14));

                map.put("eligible7180", rs.getString(15));

                map.put("eligible8190", rs.getString(16));

                map.put("eligibl9100", rs.getString(17));

                map.put("neligible0010", rs.getString(18));

                map.put("eligible1120", rs.getString(19));

                map.put("eligible2130", rs.getString(20));

                map.put("eligible3140", rs.getString(21));

                map.put("total", rs.getString(22));

                monthrep.add(map);

            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthReport", "MonthWiseCumulativeReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MonthWiseCumulativeReportDAO", "getMonthReport");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMonthReport", "MonthWiseCumulativeReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MonthWiseCumulativeReportDAO", "getMonthReport");
        } finally {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(callst);

        }

        return monthrep;
    }

    public ArrayList getCumulativeReport() {
        ArrayList cumulRep = new ArrayList();
        Connection con = null;


        return cumulRep;
    }
}
