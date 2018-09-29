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
import java.util.List;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;

import org.bf.disability.dto.LoginDTO;

import com.tcs.sgv.common.util.DBConnection;

/**
 *
 * @author t_bapinaidu
 */
public class UserDetailsDAO 
{
	Connection lcon;
	String lStrQuery;
	
	
    public List getUserDetails(DataSource datasource, int campId, String loginId) throws SADAREMDBException, SQLException 
    {

        ResultSet rs = null;
        lcon = null;
        PreparedStatement pstmt = null;
        LoginDTO loginDTO = null;
        List userDetailsList = null;
        
        try 
        {
        	
        	lcon = DBConnection.getConnection();
            
        	lStrQuery = 
        			"SELECT log.RowID,log.PersonName,log.UserName,\n"+
        			" CASE WHEN log.UserStatus=0 THEN 'No' WHEN log.UserStatus=1 THEN 'Yes'  END lockstatus,\n "+
        			" CASE WHEN log.Status='Inactive' THEN 'Yes' WHEN log.Status='Active' THEN 'No'  END status,\n "+
        			" ISNULL(r.role_name,'NA') rolename \n"+
        			" FROM Login_Details log WITH(NOLOCK) LEFT JOIN roles R WITH(NOLOCK) ON (log.role_id = r.role_id) \n "+
        			" WHERE log.camp_id=? and log.username != ? and log.is_active='Y' AND log.role_id IN ('1','3','19','31') ";
        	
            userDetailsList = new ArrayList();
             
            pstmt = lcon.prepareStatement(lStrQuery);
            
            pstmt.setInt(1, campId);
            pstmt.setString(2, loginId); 
            rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                loginDTO = new LoginDTO();
                loginDTO.setRowId(rs.getString("RowID"));
                loginDTO.setUserName(rs.getString("PersonName"));
                loginDTO.setLoginid(rs.getString("UserName"));
                loginDTO.setLockstatus(rs.getString("lockstatus"));
                loginDTO.setStatus(rs.getString("Status"));
                loginDTO.setRolename(rs.getString("rolename"));
                
                userDetailsList.add(loginDTO);
            }
        } 
        catch (SQLException sqlEx) 
        {
        	System.out.println(sqlEx);
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getUserDetails", "UserDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UserDetailsDAO", "getUserDetails");
        }
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(datasource, sqlEx.toString(), "getUserDetails", "UserDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UserDetailsDAO", "getUserDetails");
        }
        finally 
        {
            DBConnection.closeConnection(lcon);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return userDetailsList;
    }

    public int updateLoginDetails(DataSource ds, String loginId, String loginStatus, String selectedRowId) throws SADAREMDBException, SQLException
    {

        ResultSet rs = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        int i = 0;
        try {
            con = DBConnection.getConnection();
            
            if ("RleaseLoginIds".equals(loginStatus)) 
            {
            	pstmt = con.prepareStatement("update Login_Details set UserStatus='False' where RowID=?");
            	pstmt.setString(1, selectedRowId);
                i = pstmt.executeUpdate();
            }
            else if ("Lock".equals(loginStatus)) 
            {
            	pstmt = con.prepareStatement("update Login_Details set Status='Inactive' where RowID=?");
            	pstmt.setString(1, selectedRowId);
                i = pstmt.executeUpdate();
            } 
            else if ("UnLock".equals(loginStatus)) 
            {
            	pstmt = con.prepareStatement("update Login_Details set Status='Active' where RowID=?");
            	pstmt.setString(1, selectedRowId);
                i = pstmt.executeUpdate();
            }
        } 
        catch (SQLException sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateLoginDetails", "UserDetailsDAO", "DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UserDetailsDAO", "updateLoginDetails");
        } 
        catch (Exception sqlEx) 
        {
            int exResult = ExceptionDAO.saveException(ds, sqlEx.toString(), "updateLoginDetails", "UserDetailsDAO", "Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "UserDetailsDAO", "updateLoginDetails");
        }
        finally 
        {
            DBConnection.closeConnection(con);
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(pstmt);
        }
        return i;


    }
}
