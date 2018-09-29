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

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 567999
 */
public class Report16DAO {

    public synchronized ArrayList getReport16(DataSource ds, String fromDate, String toDate) throws Exception
    {
        ArrayList list = new ArrayList();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        Map tlist = null;
        long t = 0;
        long t1 = 0;
        long t2 = 0;
        long t3 = 0;
        long t4 = 0;
        long t5 = 0;
        long t6 = 0;
        long t7 = 0;
        long t8 = 0;
        long t9 = 0;

        try {
            con = DBConnection.getConnection();
            Date FD = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
            String fDate = new SimpleDateFormat("MM/dd/yyyy").format(FD);
            Date TD = new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
            String tDate = new SimpleDateFormat("MM/dd/yyyy").format(TD);
            cs = con.prepareCall("{Call PWDVALIDATIONABSTRACTREPORT(?,?)}");
            cs.setString(1, fDate);
            cs.setString(2, tDate);
            rs = cs.executeQuery();
            int sno = 1;
            if (rs != null)
            {
                while (rs.next()) 
                {
                    tlist = new HashMap();
                    tlist.put("sno", sno);
                    tlist.put("disname", rs.getString(1));
                    tlist.put("total", rs.getString(2));
                    tlist.put("taged", rs.getString(3));
                    tlist.put("alive", rs.getString(4));
                    tlist.put("dead", rs.getString(5));
                    tlist.put("tagshg", rs.getString(6));
                    tlist.put("noaccount", rs.getString(7));
                    tlist.put("haveaccount", rs.getString(8));
                    tlist.put("notcover", rs.getString(9));
                    tlist.put("conver", rs.getString(10));
                    tlist.put("totalassessed", rs.getString(11));
                    t = t + Long.parseLong(rs.getString(2));
                    t1 = t1 + Long.parseLong(rs.getString(3));
                    t2 = t2 + Long.parseLong(rs.getString(4));
                    t3 = t3 + Long.parseLong(rs.getString(5));
                    t4 = t4 + Long.parseLong(rs.getString(6));
                    t5 = t5 + Long.parseLong(rs.getString(7));
                    t6 = t6 + Long.parseLong(rs.getString(8));
                    t7 = t7 + Long.parseLong(rs.getString(9));
                    t8 = t8 + Long.parseLong(rs.getString(10));
                    t9 = t9 + Long.parseLong(rs.getString(11));
                    list.add(tlist);
                    sno++;
                }
                tlist.put("t", "<b>Total</b>");
                tlist.put("t2", "<b>" + t + "</b>");
                tlist.put("t3", "<b>" + t1 + "</b>");
                tlist.put("t4", "<b>" + t2 + "</b>");
                tlist.put("t5", "<b>" + t3 + "</b>");
                tlist.put("t6", "<b>" + t4 + "</b>");
                tlist.put("t7", "<b>" + t5 + "</b>");
                tlist.put("t8", "<b>" + t6 + "</b>");
                tlist.put("t9", "<b>" + t7 + "</b>");
                tlist.put("t10", "<b>" + t8 + "</b>");
                tlist.put("t11", "<b>" + t9 + "</b>");
            }

        }
        catch (SQLException sqlEx) 
        {
            sqlEx.printStackTrace();
            ExceptionDAO.saveException(ds, sqlEx.toString(), "getReport16", "Report16DAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "Report16DAO", "getReport16");
        } 
        catch (Exception sqlEx) 
        {
            sqlEx.printStackTrace();
            ExceptionDAO.saveException(ds, sqlEx.toString(), "getReport16", "Report16DAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "Report16DAO", "getReport16");
        } 
        finally 
        {
            try 
            {
                DBConnection.closeConnection(con);
                DBConnection.closeResultSet(rs);
                DBConnection.closeStatement(cs);
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return list;
    }
}
