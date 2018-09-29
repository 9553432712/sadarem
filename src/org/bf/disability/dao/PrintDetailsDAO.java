/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.PrintDetailsResultForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 747577
 */
public class PrintDetailsDAO {

    public ArrayList getCampDetails(DataSource ds,String district_id) throws SADAREMDBException, SQLException {
        ArrayList campDetailsList = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map campDetails = null;
        int i = 1;
        try {
            con = DBConnection.getConnection();
            
            sql = "select camp_id,name,address,venueName from dbo.tblCamp_Details where district_id=? and status='Active' order by name";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, district_id);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    campDetails = new HashMap();
                    campDetails.put("sno", i);
                    campDetails.put("camp_id", rs.getString(1));
                    campDetails.put("camp_name", rs.getString(2) + ", " + rs.getString(4));
                    campDetails.put("venue_name", rs.getString(3));
                    campDetailsList.add(campDetails);
                    i++;
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "PrintDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "getCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampDetails", "PrintDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "getCampDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return campDetailsList;
    }

    public ArrayList getFullCampDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm)throws SADAREMDBException, SQLException {
        ArrayList campDetailsList = new ArrayList();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map campDetails = null;
        try {
            con = DBConnection.getConnection();
            
            if (printDetailsResultForm.getCamp_id().equals("0"))
            {
                sql = "select camp_id,name,address,venueName,count(sadaremid) Total from"
                        + " tblCamp_Details cd left outer join certificate_printdetails cp on (cd.camp_id=cp.campid)"
                        + " where cd.district_id=? and cd.status='Active' "
                        + " group by  cp.campid,cd.camp_id,name,address,venueName order by name";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, printDetailsResultForm.getDistrict_id());
            }
            else
            {
                sql = "select camp_id,name,address,venueName,count(sadaremid) Total from"
                        + " tblCamp_Details cd left outer join certificate_printdetails cp on (cd.camp_id=cp.campid)"
                        + " where cd.district_id=? and cp.campid=? and cd.status='Active'  "
                        + " group by  cp.campid,cd.camp_id,name,address,venueName order by name";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, printDetailsResultForm.getDistrict_id());
                pstmt.setString(2, printDetailsResultForm.getCamp_id());
            }
            
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    campDetails = new HashMap();
                    campDetails.put("camp_id", rs.getString(1));
                    campDetails.put("camp_name", rs.getString(2) + ", " + rs.getString(4));
                    campDetails.put("count", rs.getString(5));
                    campDetailsList.add(campDetails);
                }
            }


        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getFullCampDetails", "PrintDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "getFullCampDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getFullCampDetails", "PrintDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "getFullCampDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }


        return campDetailsList;
    }

    public void insertCertificatePrintDetails(DataSource ds,String sId, String cId, String cType, String lId, String campID, String distId) throws SADAREMDBException, SQLException,Exception {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        InetAddress ownIP = InetAddress.getLocalHost();
        String systemIp = ownIP.getHostAddress();
        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement("insert into Certificate_PrintDetails(sadaremid,category,certificatetype,systemip,loginid,createddate,campid,districtid) values(?,?,?,?,?,getDate(),?,?)");
            ps.setString(1, sId);
            ps.setString(2, cId);
            ps.setString(3, cType);
            ps.setString(4, systemIp);
            ps.setString(5, lId);
            ps.setString(6, campID);
            ps.setString(7, distId);
            ps.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCertificatePrintDetails", "PrintDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "insertCertificatePrintDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCertificatePrintDetails", "PrintDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "insertCertificatePrintDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);
        }

    }
 public void insertCertificatePrintDetails1(DataSource ds,String sId, String cId, String cType, String lId, String campID, String distId) throws SADAREMDBException, SQLException,Exception {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        InetAddress ownIP = InetAddress.getLocalHost();
        String systemIp = ownIP.getHostAddress();
        try {
            con = DBConnection.getConnection();
            //con = DBConnection.getConnection();
            ps = con.prepareStatement("insert into Certificate_PrintDetails(sadaremid,category,certificatetype,systemip,loginid,createddate,campid,districtid) values(?,?,?,?,?,getDate(),?,?)");
            ps.setString(1, sId);
            ps.setString(2, cId);
            ps.setString(3, cType);
            ps.setString(4, systemIp);
            ps.setString(5, lId);
            ps.setString(6, campID);
            ps.setString(7, distId);
            ps.executeUpdate();
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCertificatePrintDetails1", "PrintDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "insertCertificatePrintDetails1");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "insertCertificatePrintDetails1", "PrintDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "insertCertificatePrintDetails1");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(ps);
            DBConnection.closeConnection(con);
        }

    }
    public ArrayList getPrintCertificateDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm) throws SADAREMDBException, SQLException {
        ArrayList printDetailsList = new ArrayList();
        Map m = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection con = null;
        String query = null;
        try {
            con = DBConnection.getConnection();
            
            query = "select sadaremid,isnull(sum(case when certificateType='CT' then 1 else 0 end),0) CT,"
                    + "isnull(sum(case when certificateType='ID' then 1 else 0 end),0) ID,isnull(sum(case when certificateType='RC' then 1 else 0 end),0) RC"
                    + " from certificate_printdetails where campid=? and districtid=? and sadaremid=? group by sadaremid,certificatetype";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, printDetailsResultForm.getCamp_id());
            pstmt.setString(2, printDetailsResultForm.getDistrict_id());
            pstmt.setString(3, printDetailsResultForm.getS_id());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                m = new HashMap();
                m.put("sid", rs.getString(1));
                m.put("ct", rs.getString(2));
                m.put("id", rs.getString(3));
                m.put("rc", rs.getString(4));
                printDetailsList.add(m);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPrintCertificateDetails", "PrintDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "getPrintCertificateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPrintCertificateDetails", "PrintDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "getPrintCertificateDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return printDetailsList;
    }

    public ArrayList getCampCountDetails(DataSource ds,PrintDetailsResultForm printDetailsResultForm) throws SADAREMDBException, SQLException {
        ArrayList<Object> campDetailsList = new ArrayList<Object>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        Map campDetails = null;

        try {
            con = DBConnection.getConnection();
            
            sql = " select sadaremid,count(sadaremid) total from certificate_printdetails  where campid=? and districtid=? group by sadaremid";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,  printDetailsResultForm.getCamp_id());
            pstmt.setString(2,  printDetailsResultForm.getDistrict_id());
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    campDetails = new HashMap();
                    campDetails.put("sid", rs.getString(1));
                    campDetails.put("count", rs.getString(2));
                    campDetailsList.add(campDetails);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampCountDetails", "PrintDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "getCampCountDetails");
        } catch (Exception sqlEx) {System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getCampCountDetails", "PrintDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "getCampCountDetails");
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }
        return campDetailsList;
    }
    public boolean checkForAppellateHasRaised(String personCode, DataSource ds) throws SADAREMDBException, SQLException
    {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sql = null;
        boolean result = false; 
        try
        {
            con = DBConnection.getConnection();
            
            sql = " select sadarem_id  from tkt_request_master  where 	tkt_type_id='S018' and status_flag='6A5' and sadarem_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,  personCode);
            rs = pstmt.executeQuery();
            if (rs != null)
            {
                while (rs.next())
                { 
                	if(rs.getString(1).length()>0)
                	{
                		result  = true;
                	}
                }
            }
        }
        catch (SQLException sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkForAppellateHasRaised", "PrintDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "checkForAppellateHasRaised");
        } 
        catch (Exception sqlEx)
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "checkForAppellateHasRaised", "PrintDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "PrintDetailsDAO", "checkForAppellateHasRaised");
        }
        finally
        {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
            DBConnection.closeConnection(con);
        }    
    	return result;
    }
}
