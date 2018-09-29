package com.tcs.sadarem.internalscreens.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class CreateUserDAOImpl implements CreateUserDAO
{
	static final private Logger log = Logger.getLogger(CommonDAOImpl.class);
	Connection lcon;
	String lStrQuery;	
	
	
	public ArrayList rolesToCreateLogin()
	{
		ArrayList resultList = new ArrayList();
		try
		{		
			lStrQuery= 	"select * from roles(nolock) where  role_id in ('04','34','96','24','97','98','101','102','103') and is_active='Y'  order by role_id " ;
			log.info("getPendingWithoutProofPartaList lStrQuery : "+lStrQuery);
	  	
			resultList=(ArrayList)DataAccess.getaccData(lStrQuery);		
			
		}
		catch(SQLException sqle)
		 {    
			 log.info("SQLException in CreateUserDAOImpl @ rolesToCreateLogin :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CreateUserDAOImpl @ rolesToCreateLogin : "+lexp.getLocalizedMessage());
		 }
		 
		 return resultList;
	}

	public String createUser(HashMap inputparam) 
	{
		String resultMSG = "";
		PreparedStatement pstmt = null;
		
		try
		{
			lcon = DBConnection.getConnection();
			
			if(inputparam.get("roleid")!=null && CommonUtility.checkNullObj(inputparam.get("roleid")).equals("24") &&
					inputparam.get("selMandal")!=null && CommonUtility.checkNullObj(inputparam.get("selMandal")).length()>0 && !CommonUtility.checkNullObj(inputparam.get("selMandal")).equals("-1") )
			{
				lStrQuery= 	"INSERT INTO LOGIN_DETAILS (PERSONNAME,USERNAME,PASSWORD,ROLE_ID,DISTRICT_ID,Mandal_ID,CREATED_BY,CREATED_DATE,System_IP_Address,STATUS,USERSTATUS,PASSWORD_FLAG,ENCRYPTED_PASSWORD,IS_ACTIVE,CONTACTNO,CONTACT_VIEW,EMAIL,USER_ID) values "
						+ " (?,?,'Sadarem@123',?,?,?,?,getDate(),?,'Active',0,'Y','8530d2ba9b8720ee1cb8f0dd2676b5c0','Y',?,?,?,?);" ;
				
				pstmt = lcon.prepareStatement(lStrQuery);
				
				pstmt.setString(1, inputparam.get("EmpName").toString());
				pstmt.setString(2, inputparam.get("EmpID").toString());
				pstmt.setString(3, inputparam.get("roleid").toString());
				pstmt.setString(4, inputparam.get("districtid").toString());
				pstmt.setString(5, inputparam.get("selMandal").toString());
				pstmt.setString(6, inputparam.get("createdBy").toString());
				pstmt.setString(7, inputparam.get("sysIP").toString());
				pstmt.setString(8, inputparam.get("mobileno").toString());
				pstmt.setString(9, "N");
				pstmt.setString(10, inputparam.get("emailid").toString());
				pstmt.setString(11, inputparam.get("EmpID").toString());
			}
			else if(inputparam.get("roleid")!=null && CommonUtility.checkNullObj(inputparam.get("roleid")).equals("4") &&
					inputparam.get("campid")!=null && CommonUtility.checkNullObj(inputparam.get("campid")).length()>0 && !CommonUtility.checkNullObj(inputparam.get("campid")).equals("-1") )
			{
				lStrQuery= 	"INSERT INTO LOGIN_DETAILS (PERSONNAME,USERNAME,PASSWORD,ROLE_ID,DISTRICT_ID,CAMP_ID,CREATED_BY,CREATED_DATE,System_IP_Address,STATUS,USERSTATUS,PASSWORD_FLAG,ENCRYPTED_PASSWORD,IS_ACTIVE,CONTACTNO,CONTACT_VIEW,EMAIL,USER_ID) values "
						+ " (?,?,'Sadarem@123',?,?,?,?,getDate(),?,'Active',0,'Y','8530d2ba9b8720ee1cb8f0dd2676b5c0','Y',?,?,?,?);" ;
				
				pstmt = lcon.prepareStatement(lStrQuery);
				
				pstmt.setString(1, inputparam.get("EmpName").toString());
				pstmt.setString(2, inputparam.get("EmpID").toString());
				pstmt.setString(3, inputparam.get("roleid").toString());
				pstmt.setString(4, inputparam.get("districtid").toString());
				pstmt.setString(5, inputparam.get("campid").toString());
				pstmt.setString(6, inputparam.get("createdBy").toString());
				pstmt.setString(7, inputparam.get("sysIP").toString());
				pstmt.setString(8, inputparam.get("mobileno").toString());
				pstmt.setString(9, "N");
				pstmt.setString(10, inputparam.get("emailid").toString());
				pstmt.setString(11, inputparam.get("EmpID").toString());
			}
			else if(inputparam.get("roleid")!=null && (CommonUtility.checkNullObj(inputparam.get("roleid")).equals("34") || CommonUtility.checkNullObj(inputparam.get("roleid")).equals("96") || CommonUtility.checkNullObj(inputparam.get("roleid")).equals("97") || CommonUtility.checkNullObj(inputparam.get("roleid")).equals("98") || CommonUtility.checkNullObj(inputparam.get("roleid")).equals("102") || CommonUtility.checkNullObj(inputparam.get("roleid")).equals("103"))  &&
				inputparam.get("campid")!=null && CommonUtility.checkNullObj(inputparam.get("campid")).length()>0  )
		{
			lStrQuery= 	"INSERT INTO LOGIN_DETAILS (PERSONNAME,USERNAME,PASSWORD,ROLE_ID,DISTRICT_ID,CREATED_BY,CREATED_DATE,System_IP_Address,STATUS,USERSTATUS,PASSWORD_FLAG,ENCRYPTED_PASSWORD,IS_ACTIVE,CONTACTNO,CONTACT_VIEW,EMAIL,USER_ID) values "
					+ " (?,?,'Sadarem@123',?,?,?,getDate(),?,'Active',0,'Y','8530d2ba9b8720ee1cb8f0dd2676b5c0','Y',?,?,?,?);" ;
			
			pstmt = lcon.prepareStatement(lStrQuery);
			
			pstmt.setString(1, inputparam.get("EmpName").toString());
			pstmt.setString(2, inputparam.get("EmpID").toString());
			pstmt.setString(3, inputparam.get("roleid").toString());
			pstmt.setString(4, inputparam.get("districtid").toString());
			pstmt.setString(5, inputparam.get("createdBy").toString());
			pstmt.setString(6, inputparam.get("sysIP").toString());
			pstmt.setString(7, inputparam.get("mobileno").toString());
			if(inputparam.get("roleid").toString().equals("34"))
			{
				pstmt.setString(8, "Y");
			}
			else 
			{
				pstmt.setString(8, "N");
			}
			pstmt.setString(9, inputparam.get("emailid").toString());
			pstmt.setString(10, inputparam.get("EmpID").toString());
		}
			
			int result = pstmt.executeUpdate();
			if(result>0)
			{
				resultMSG = "The login Created Successfully. Please use '"+inputparam.get("EmpID").toString()+"' as your User Name";
			}
			else
			{
				resultMSG = "Error Occured!! Please try again after some time..";
			}	
			
			
			log.info("getPendingWithoutProofPartaList lStrQuery : "+lStrQuery);
			
			
	
			
		}
		catch(SQLException sqle)
		 {    
			 log.info("SQLException in CreateUserDAOImpl @ rolesToCreateLogin :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
			 resultMSG = "2) Error Occured!! Please try again after some time..";
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CreateUserDAOImpl @ rolesToCreateLogin : "+lexp.getLocalizedMessage());
			 resultMSG = "3) Error Occured!! Please try again after some time..";
		 }
		 
		 return resultMSG;
	}

	public int checkForUserExist(String username)
	{
		int resultMSG = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{

			lcon = DBConnection.getConnection();
			
			lStrQuery= 	"select count(username) from login_details where username=? " ;
			
			pstmt = lcon.prepareStatement(lStrQuery);

			log.info("checkForUserExist lStrQuery : "+lStrQuery);
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			if(rs!=null && rs.next())
			{
				resultMSG = rs.getInt(1);
			}
			
			System.out.println("resultMSG"+resultMSG);
			
	  	
				
			
		}
		catch(SQLException sqle)
		 {    
			 log.info("SQLException in CreateUserDAOImpl @ checkForUserExist :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CreateUserDAOImpl @ checkForUserExist : "+lexp.getLocalizedMessage());
		 }
		return resultMSG;
		 
	}

}
