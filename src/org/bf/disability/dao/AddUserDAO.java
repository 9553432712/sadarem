/*
 * DistrictDAO.java
 *
 * Created on December 7, 2006, 11:17 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.bf.disability.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.form.Roles;

import com.tcs.sadarem.util.DataAccess;
import com.tcs.sadarem.util.PasswordEncriptDecrypt;
import com.tcs.sgv.common.util.DBConnection;

/**
 * this class is used for adding users to database
 * @author Devi Prasad .G
 * @version 1.0
 */
public class AddUserDAO {

    /**
     *
     * @param ds
     * @param adduser
     * @throws org.bf.disability.Exception.SADAREMException
     * @return
     */
    public static int addUser(DataSource ds, Roles adduser, String districtid_id, String loginid_id, int campid_id, String localaddr, String userId) throws SADAREMDBException, SQLException {
        Connection con = null;
        
        PreparedStatement pstmt = null ;
        ResultSet rs = null;
        CallableStatement cs = null;
        String lStrQuery = null;
        int i = 0;

        try
        {
        	String userpassword = adduser.getUserencrptPwd();
        	
        /*	System.out.println("UserEcryptPwd : "+userpassword);
        	System.out.println("UserEcryptPwd Decrypt: "+ PasswordEncriptDecrypt.decrypt(userpassword) );*/
        	
            con = DBConnection.getConnection();
        
            lStrQuery = "select UserName from Login_Details where UserName=? "; 
            pstmt = con.prepareStatement(lStrQuery);
            
            pstmt.setString(1,userId );
            rs = pstmt.executeQuery();
            if (rs.next()) 
            {
                return i;
            }
            else 
            {

                //  con =DBConnection.getConnection();
                con.setAutoCommit(false);
                cs = con.prepareCall("{call SP_Login_Details_INSERT(?,?,?,?,?,?,?,?,?)}");
                cs.setString(1, adduser.getUsername());
                cs.setString(2, adduser.getUserid());
                cs.setString(3, PasswordEncriptDecrypt.decrypt(userpassword));
                cs.setString(4, adduser.getRoleid());
                cs.setString(5, districtid_id);
                cs.setInt(6, campid_id);
                cs.setString(7, loginid_id);
                cs.setString(8, localaddr);
                cs.setString(9, adduser.getEncrptPwd());

                i = cs.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
            }
        } catch (SQLException sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addUser", "AddUserDAO","DataBase");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddUserDAO", "addUser");
        } catch (Exception sqlEx) {
            int exResult = ExceptionDAO.saveException(ds,sqlEx.toString(), "addUser", "AddUserDAO","Code");
            throw new SADAREMDBException(CommonConstants.DBERROR, sqlEx.getMessage(), CommonConstants.E_ERROR_MESSAGE_DATA_FAILURE, "AddUserDAO", "addUser");
        } finally {
           DBConnection.closeConnection(con);
           DBConnection.closeResultSet(rs);
           DBConnection.closeStatement(cs);
           DBConnection.closeStatement(pstmt);

        }
        return i;
    }
    
    public ArrayList getDistrictWiseLoginDetailsReport(String roleId)
	 {
		 ArrayList resultList  = new ArrayList();

	        String lStrQuery = null;
		 try
		 {
			 

			 ArrayList paramList = new ArrayList();
			 ArrayList tempList = new ArrayList();
			 
			 if("1,3,4,8,19,24,29,31".indexOf(roleId)!=-1)
			 {
				 lStrQuery = 
				 		 "SELECT ROW_NUMBER() OVER( ORDER BY c.district_name) 'S.No.',c.Camp_ID 'Camp ID',c.Name 'Camp Name',\n" +
						 " c.District_Name 'District',ISNULL(l.UserName,'-') 'User ID',\n" + 
						 " ISNULL(l.PersonName,'-') 'User Name',ISNULL(l.contactno,'-') 'Contact No.',ISNULL(l.email,'-') 'eMail Id'\n" + 
						 " FROM\n" + 
						 " (SELECT c.Camp_ID,c.Name,VenueName,d.District_Name FROM tblcamp_details c with(nolock)\n" + 
						 " LEFT JOIN TBLDISTRICT_DETAILS d with(nolock) ON(c.District_ID=d.District_ID) WHERE Status='Active' ) c\n" + 
						 " LEFT JOIN (SELECT UserName,PersonName,contactno,Camp_ID,email FROM login_details WHERE is_active='Y'\n" + 
						 " AND role_id=? and UserName<>'1211236') l ON (c.Camp_ID = l.Camp_ID )\n" + 
						 " ORDER BY c.District_Name";
			  
			 }
			 else
			 {
				 		lStrQuery =
						 		 " SELECT ROW_NUMBER() OVER( ORDER BY d.district_name) 'S.No.', \n"+
								 "CASE WHEN l.district_id<>'00' THEN d.District_Name ELSE 'State Headquarters' END 'District',ISNULL(l.UserName,'-') 'User ID', \n"+
								 " ISNULL(l.PersonName,'-') 'User Name',ISNULL(l.contactno,'-') 'Contact No.',ISNULL(l.email,'-') 'eMail Id' \n"+
								 " FROM \n"+
								 " (SELECT UserName,PersonName,contactno,Camp_ID,email,district_id FROM login_details WHERE is_active='Y' \n"+
								 " AND role_id=? and UserName<>'1211236') l  \n"+
								 " LEFT JOIN TBLDISTRICT_DETAILS d ON(d.District_ID=l.District_ID) \n"+
								 " ORDER BY District_Name"; 
			 }
			 
			 paramList = new ArrayList();
			 tempList  = new ArrayList();
		 
			 tempList.add("S");
			 tempList.add(roleId);
			 paramList.add(tempList);
			 
			// System.out.println(lStrQuery);
			 
			 resultList = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, true, false);

		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 return resultList;
	 }
    
}


