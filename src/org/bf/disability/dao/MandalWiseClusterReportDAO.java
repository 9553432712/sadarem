/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.MandalWiseClusterReportForm;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 707797
 */
public class MandalWiseClusterReportDAO {

    Connection con = null;
    Statement st = null;
    PreparedStatement pstmt = null;
    String sql = null;
    ResultSet rs = null;
    HashMap map = null;

    public ArrayList getDistrictNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList DistrictName = new ArrayList();

        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            sql = "select District_ID,District_Name from dbo.tblDistrict_Details order by District_Name";

            rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("district", rs.getString(1));
                    map.put("districtname", rs.getString(2));
                    DistrictName.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictNames", "MandalWiseClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getDistrictNames");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getDistrictNames", "MandalWiseClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getDistrictNames");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(st);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }


        return DistrictName;

    }

    public ArrayList getMandalNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList MandalName = new ArrayList();

        PreparedStatement pstmt = null;        
        try {
            con = DBConnection.getConnection();
            //st = con.createStatement();
            sql = "select Mandal_ID,Mandal_Name,District_ID from dbo.tblMandal_Details where District_ID=?";
            
            pstmt =con.prepareStatement(sql);
            pstmt.setString(1, pwdReportsForm.getDistrict());

            rs = pstmt.executeQuery(); 
            
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("mandal", rs.getString(1));
                    map.put("mandalname", rs.getString(2));
                    MandalName.add(map);
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalNames", "MandalWiseClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getMandalNames");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getMandalNames", "MandalWiseClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getMandalNames");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(st);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return MandalName;

    }

    public ArrayList getVillageNames(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList VillageName = new ArrayList();

        PreparedStatement pstmt =null ;
        
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            sql = "select Village_ID,Village_Name,Mandal_ID from dbo.tblVillage_Details "
                    + "where Mandal_ID=? and District_ID=?";

            pstmt =con.prepareStatement(sql);
            
            pstmt.setString(1, pwdReportsForm.getMandal());
            pstmt.setString(2, pwdReportsForm.getDistrict());
             
            rs = pstmt.executeQuery();
            
            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("village", rs.getString(1));
                    map.put("villagename", rs.getString(2));
                    VillageName.add(map);

                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageNames", "MandalWiseClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getVillageNames");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getVillageNames", "MandalWiseClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getVillageNames");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(st);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return VillageName;

    }

    public ArrayList getPwdData(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList pwdData = new ArrayList(); 
        
        PreparedStatement pstmt = null;
        
        String sql1 = " ";
        try {
            con = DBConnection.getConnection();
          //  st = con.createStatement();
            sql = "SELECT m.Mandal_Name, count(1) sadarem_assesed_count,cast('50' as int) nmobilised_pwd ,round((cast(50 as float)/count(1))*100,2) mobilised_percentage"
                    + " FROM  TBLPERSON_PERSONAL_DETAILS P   with (nolock) ,TBLPERSON_DISABILITY_DETAILS PD ,TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT ,"
                    + "dbo.tblDistrict_Details d,TBLMANDAL_DETAILS M WHERE  P.PERSON_CODE  = PD.PERSON_CODE AND P.PERSON_CODE  = PT.PERSON_CODE   "
                    + "AND PD.PERSON_CODE = PT.PERSON_CODE  and p.districtid = d.district_id and p.districtid = m.district_id and p.mandalid = m.mandal_id "
                    + "AND P.STATUS = 'Active' AND PD.STATUS = 'Active' and p.age_years between 0 and 55 aND PT.STATUS = 'Active' "
                    + "and pt.totaldisability > 50 and p.districtid = ?   ~replaceStr   group by m.Mandal_Name,d.District_Name";

            if (!pwdReportsForm.getMandal().equalsIgnoreCase("00")) {
                sql1 = " and p.mandalid=? ";
            }

            sql = sql.replaceAll("~replaceStr", sql1);
            
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pwdReportsForm.getDistrict());
            
            if (!pwdReportsForm.getMandal().equalsIgnoreCase("00")) {
            pstmt.setString(2, pwdReportsForm.getMandal()); }
            

            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("assesedcount", rs.getString(1));
                    map.put("mobilised", rs.getString(2));
                    map.put("percentage", rs.getString(3));
                    map.put("MandalName", rs.getString(4));
//                    map.put("DistrictName", rs.getString(5));
                    pwdData.add(map);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdData", "MandalWiseClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getPwdData");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdData", "MandalWiseClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getPwdData");
        }
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(st);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return pwdData;
    }

    public ArrayList getPwdData1(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList pwdData1 = new ArrayList(); 
        PreparedStatement pstmt =null;
        String sql1 = "";
        try {
            con = DBConnection.getConnection();
            //st = con.createStatement();
            sql = "SELECT m.Mandal_Name, count(1) sadarem_assesed_count,cast('80' as int) nmobilised_pwd ,round((cast(80 as float)/count(1))*100,2) mobilised_percentage"
                    + " FROM  TBLPERSON_PERSONAL_DETAILS P   with (nolock) ,TBLPERSON_DISABILITY_DETAILS PD ,TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT ,"
                    + "dbo.tblDistrict_Details d,TBLMANDAL_DETAILS M WHERE  P.PERSON_CODE  = PD.PERSON_CODE AND P.PERSON_CODE  = PT.PERSON_CODE   "
                    + "AND PD.PERSON_CODE = PT.PERSON_CODE  and p.districtid = d.district_id and p.districtid = m.district_id and p.mandalid = m.mandal_id "
                    + "AND P.STATUS = 'Active' AND PD.STATUS = 'Active' and p.age_years between 0 and 55 aND PT.STATUS = 'Active' "
                    + "and pt.totaldisability > 80 and p.districtid = ?   ~replaceStr  group by m.Mandal_Name,d.District_Name ";

            if (!pwdReportsForm.getMandal().equalsIgnoreCase("00")) {
                sql1 = " and p.mandalid=?";
            }

            sql = sql.replaceAll("~replaceStr", sql1);
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pwdReportsForm.getDistrict());
            
            if (!pwdReportsForm.getMandal().equalsIgnoreCase("00")) {  
            	  pstmt.setString(2, pwdReportsForm.getMandal()); }
            
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("assesedcount1", rs.getString(1));
                    map.put("mobilised1", rs.getString(2));
                    map.put("percentage1", rs.getString(3));
                    map.put("MandalName1", rs.getString(4));
//                    map.put("DistrictName1", rs.getString(5));
                    pwdData1.add(map);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdData1", "MandalWiseClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getPwdData1");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdData1", "MandalWiseClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getPwdData1");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(st);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return pwdData1;
    }

    public ArrayList getPwdData2(DataSource ds, MandalWiseClusterReportForm pwdReportsForm) throws SADAREMDBException, SQLException {
        ArrayList pwdData2 = new ArrayList();
        String sql1 = "";
        
        PreparedStatement pstmt = null;
        
        try {
            con = DBConnection.getConnection();
           // st = con.createStatement();
            sql = "SELECT m.Mandal_Name, count(1) sadarem_assesed_count,cast('60' as int) nmobilised_pwd ,round((cast(60 as float)/count(1))*100,2) mobilised_percentage "
                    + "FROM  TBLPERSON_PERSONAL_DETAILS P   with (nolock) ,TBLPERSON_DISABILITY_DETAILS PD ,TBLPERSON_DISABILITY_TOTALVALUE_DETAILS PT ,"
                    + "dbo.tblDistrict_Details d,TBLMANDAL_DETAILS M WHERE  P.PERSON_CODE  = PD.PERSON_CODE   AND"
                    + " P.PERSON_CODE  = PT.PERSON_CODE   AND PD.PERSON_CODE = PT.PERSON_CODE  and p.districtid = d.district_id and "
                    + "p.districtid = m.district_id and p.mandalid = m.mandal_id     AND P.STATUS = 'Active' AND PD.STATUS = 'Active' "
                    + "and p.age_years between 0 and 55   aND PT.STATUS = 'Active'  and pt.totaldisability > 60 "
                    + "and p.districtid = ?    ~replaceStr group by m.Mandal_Name,d.District_Name";

            if (!pwdReportsForm.getMandal().equalsIgnoreCase("00")) {
                sql1 = " and p.mandalid=?";
            }

            sql = sql.replaceAll("~replaceStr", sql1);  
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pwdReportsForm.getDistrict() );
            
            if (!pwdReportsForm.getMandal().equalsIgnoreCase("00")) { 
            pstmt.setString(1, pwdReportsForm.getMandal());  }
            
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    map = new HashMap();
                    map.put("assesedcount2", rs.getString(1));
                    map.put("mobilised2", rs.getString(2));
                    map.put("percentage2", rs.getString(3));
                    map.put("MandalName2", rs.getString(4));
//                    map.put("DistrictName2", rs.getString(5));
                    pwdData2.add(map);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdData2", "MandalWiseClusterReportDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getPwdData2");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "getPwdData2", "MandalWiseClusterReportDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "MandalWiseClusterReportDAO", "getPwdData2");
        } 
        finally 
        {
            DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(st);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return pwdData2;
    }
}
