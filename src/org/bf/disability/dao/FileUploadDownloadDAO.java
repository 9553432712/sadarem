/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author 693461
 */
public class FileUploadDownloadDAO {

    public String getDistrictLoginNameDetails(DataSource ds, String distid) throws SADAREMDBException, SQLException {

        String districtLoginNameDetails = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        try {
            con = DBConnection.getConnection();
            if (distid != null && !distid.equalsIgnoreCase("")) {
                query = "select District_Name from tblDistrict_Details where District_Id=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, distid);
                rs = pstmt.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        districtLoginNameDetails = rs.getString(1);
                    }
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictLoginNameDetails", "FileUploadDownloadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getDistrictLoginNameDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictLoginNameDetails", "FileUploadDownloadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getDistrictLoginNameDetails");
        } finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return districtLoginNameDetails;

    }

    public int insertFileUpdateDetails(DataSource ds, String districtId, String fileName) throws SADAREMDBException, SQLException {

        int insertFileUpdateDetails = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;

        try {
            con = DBConnection.getConnection();
            query = "insert into FileUpdatedDetails (districtid,filename,createdDate,Status)"
                    + "values(?,?,getDate(),'Active')";

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            pstmt.setString(2, fileName);

            insertFileUpdateDetails = pstmt.executeUpdate();

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertFileUpdateDetails", "FileUploadDownloadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "insertFileUpdateDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "insertFileUpdateDetails", "FileUploadDownloadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "insertFileUpdateDetails");
        } finally {
           DBConnection.closeStatement(pstmt);
           DBConnection.closeConnection(con);
        }

        return insertFileUpdateDetails;
    }

    public ArrayList getDistEmailIds(DataSource ds, String districtId) throws SADAREMDBException, SQLException {
        ArrayList emailList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "select EmailId from DistrictEmailIdsDetails where DistrictId=? and status ='Active'and role ='TO' and flag='FU'";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtId);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    emailList.add(rs.getString(1));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistEmailIds", "FileUploadDownloadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getDistEmailIds");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistEmailIds", "FileUploadDownloadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getDistEmailIds");
        } 
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return emailList;
    }

    public String basedOnSessionDetails(DataSource ds, String userid) throws SADAREMDBException, SQLException {

        String userList = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select b.district_name from login_Details a "
                    + " join tblDistrict_Details b on(a.district_id =b.district_id)"
                    + " where username =?";
            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1, userid);
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    userList = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "basedOnSessionDetails", "FileUploadDownloadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "basedOnSessionDetails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "basedOnSessionDetails", "FileUploadDownloadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "basedOnSessionDetails");
        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return userList;
    }

    public ArrayList getUserDetailsForMail(DataSource ds, String distId) throws SADAREMDBException, SQLException {

        ArrayList userList = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            query = "select UserName,Password from login_Details "
                    + " where District_ID=? and Role_id=?";

            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1, distId);
            pstmt.setString(2, CommonConstants.FILEUPLOD_MAILROLEID);
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    userList.add(rs.getString(1));
                    userList.add(rs.getString(2));
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getUserDetailsForMail", "FileUploadDownloadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getUserDetailsForMail");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getUserDetailsForMail", "FileUploadDownloadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getUserDetailsForMail");
        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }
        return userList;
    }

    public String getFileDeleteDetials(DataSource ds) throws SADAREMDBException, SQLException {
        String fileDeleteDetials = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        String query = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getConnection();
            query = "select convert(varchar,dateadd(dd,8,getDate()),103) as Date";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    fileDeleteDetials = rs.getString(1);
                }
            }

        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getFileDeleteDetials", "FileUploadDownloadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getFileDeleteDetials");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getFileDeleteDetials", "FileUploadDownloadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getFileDeleteDetials");
        } 
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return fileDeleteDetials;
    }

    public ArrayList getDistrictCCMails(DataSource ds, String district) throws SADAREMDBException, SQLException {

        ArrayList districtCCMails = new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = null;

        try {
            con = DBConnection.getConnection();
            query = "select Emailid from DistrictEmailIdsDetails where role ='CC' and status ='Active'  and flag='FU' and districtid=?  ;";

            pstmt = con.prepareStatement(query); 
            
            pstmt.setString(1, district);
            
            rs = pstmt.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    districtCCMails.add(rs.getString(1));
                }
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictCCMails", "FileUploadDownloadDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getDistrictCCMails");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "getDistrictCCMails", "FileUploadDownloadDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "FileUploadDownloadDAO", "getDistrictCCMails");
        }
        finally 
        {
	        DBConnection.closeResultSet(rs);
			DBConnection.closeStatement(pstmt);
			DBConnection.closeConnection(con); 
        }

        return districtCCMails;

    }
}
