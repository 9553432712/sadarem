package com.tcs.sadarem.common.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.CommonValidators;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class UserloginDAOImpl implements UserloginDAO
{
	static final private Logger log = Logger.getLogger(UserloginDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	
	public ArrayList getUserStatusList()
	{ 
		ArrayList resultList = new ArrayList();
		try
		{
			ArrayList tempList = new ArrayList();
			tempList.add("Y");
			tempList.add("Active");
			resultList.add(tempList);
			
			tempList = new ArrayList();
			tempList.add("N");
			tempList.add("In-Active");
			resultList.add(tempList);
					
		}  
		 catch(Exception lexp)
		 {
			 log.error(lexp);
		 }
		
		return resultList;
	}
	
	public ArrayList getUserRolesList()
	{ 
		ArrayList resultList = new ArrayList();
		try
		{
			lStrQuery = 
					"SELECT role_id,role_name\n" +
					"FROM roles WHERE\n" + 
					"role_id IN (3,4,8,24,34,96,97,98,101,102,103)\n" + 
					"AND is_active='Y'\n" + 
					"ORDER BY role_name";  
			
					resultList=(ArrayList)DataAccess.pickData(lStrQuery, false, false);
					
		}   
		 catch(SQLException sqle)
		 {    
			 log.error(sqle);
		 }
		 catch(Exception lexp)
		 {
			 log.error(lexp);
		 }
		
		return resultList;
	}
	
	public ArrayList getDistrictListbyRoleId(String roleId)
	{ 
		ArrayList resultList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{  
			ArrayList enableDistForRoles = new ArrayList();
			enableDistForRoles.add("3");
			enableDistForRoles.add("4");
			enableDistForRoles.add("8");
			enableDistForRoles.add("24");
			enableDistForRoles.add("34");
			enableDistForRoles.add("96");
			  
		  if(roleId!=null && !roleId.equals(""))
		  {
			if(enableDistForRoles.contains(roleId)==true)
			{ 
				CommonDAO comObj = new CommonDAOImpl();
				resultList = (ArrayList)comObj.getDistrictList();
			} 
			else
			{
				resultList = new ArrayList();
				tempList = new ArrayList();
				tempList.add("00");
				tempList.add("SPMU (Hyderabad)");
				resultList.add(tempList);
			}
		  }
		}    
		 catch(Exception lexp)
		 {
			 log.error(lexp);
		 }
		
		return resultList;
	}

	public ArrayList getCampListbyRoleIdDistId(String roleId,String distId)
	{
		ArrayList resultList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			ArrayList enableDistForRoles = new ArrayList();
			enableDistForRoles.add("3");
			enableDistForRoles.add("4");
			enableDistForRoles.add("8");  
			
			  if(roleId!=null && !roleId.equals("") && distId!=null && !distId.equals(""))
			  {
				if(enableDistForRoles.contains(roleId)==true)
				{
					CommonDAO comObj = new CommonDAOImpl();
					resultList = (ArrayList)comObj.getCampListByDistrictID(distId);
				}
				else
				{
					resultList = new ArrayList();
					tempList = new ArrayList();
					tempList.add("NA");
					tempList.add("NA");
					resultList.add(tempList);
				}
			  }
		}    
		 catch(Exception lexp)
		 {
			 log.error(lexp);
		 }
		
		return resultList;
	}
	
	public ArrayList getMandalListbyRoleIdDistId(String roleId,String distId)
	{
		ArrayList resultList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			 if(roleId!=null && !roleId.equals("") && distId!=null && !distId.equals(""))
			  {
					if("24".equals(roleId))
					{
						CommonDAO comObj = new CommonDAOImpl();
						resultList = (ArrayList)comObj.getMandalListByDistrictID(distId);
					}
					else
					{
						resultList = new ArrayList();
						tempList = new ArrayList();
						tempList.add("NA");
						tempList.add("NA");
						resultList.add(tempList);
					}
			  }
		}    
		 catch(Exception lexp)
		 {
			 log.error(lexp);
		 }
		
		return resultList;
	}
	
	public ArrayList getUserLoginDetailsforSearch(String statusFlag,String roleId,String distId,String campId,String mandalId,String username,boolean likeUserFlag)
	{
		ArrayList resultList = new ArrayList();
		
		try
		{
			if(!username.equals(""))
			{
				statusFlag = "";
			}
			
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			lStrQuery =
					" SELECT user_name,user_id,\n" +
					" person_name,role_name,district_name,camp_name,mandal_name,\n" + 
					" role_id,district_id,camp_id,mandal_id,email_id,contact_no,\n"+
					" is_active,user_status,created_by,\n" + 
					" CONVERT(VARCHAR(20),created_date,113) created_date,lock_status\n" + 
					" FROM SADAREM_VIEW_USER_LOGIN_DTLS\n" + 
					" WHERE user_id IS NOT NULL \n";
			
			if(statusFlag!=null && !statusFlag.equals(""))
			{
				lStrQuery +=" AND is_active=? \n"; 

				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(statusFlag);
				paramList.add(tempList);
			}
			
			if(roleId!=null && !roleId.equals("") && !roleId.equals("-1") && !roleId.equals("NA"))
			{
				lStrQuery +=" AND role_id=? \n"; 

				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(roleId);
				paramList.add(tempList);
			}
			
			if(distId!=null && !distId.equals("") && !distId.equals("-1") && !distId.equals("NA"))
			{
				lStrQuery +=" AND district_id=? \n";  
				
				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(distId);
				paramList.add(tempList);
			}	
			
			if(campId!=null && !campId.equals("") && !campId.equals("-1") && !campId.equals("NA"))
			{
				lStrQuery +=" AND camp_id=? \n";  
				
				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(campId);
				paramList.add(tempList);
			}		
			
			if(mandalId!=null && !mandalId.equals("") && !mandalId.equals("-1") && !mandalId.equals("NA"))
			{
				lStrQuery +=" AND mandal_id=? \n";  
				
				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(mandalId);
				paramList.add(tempList);
			}		
			
			if(username!=null && !username.equals(""))
			{
				if(likeUserFlag==true)
				{
					lStrQuery +=" AND ( UPPER(LTRIM(RTRIM(user_name))) LIKE ? OR  UPPER(LTRIM(RTRIM(user_id))) LIKE ?)\n";  

					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add("%"+username.trim().toUpperCase()+"%");
					paramList.add(tempList);
					
					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add("%"+username.trim().toUpperCase()+"%");
					paramList.add(tempList);
				}
				else
				{
					lStrQuery +=" AND (UPPER(LTRIM(RTRIM(user_name)))= ?  OR UPPER(LTRIM(RTRIM(user_id)))= ? )\n";  

					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(username.trim().toUpperCase());
					paramList.add(tempList);
					
					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(username.trim().toUpperCase());
					paramList.add(tempList);
				}
			}	 
			 
			//System.out.println("lStrQuery : \n"+lStrQuery+"\n ParamList : "+paramList);
				resultList = CommonUtility.getHashMapElementsFromArraylistElements((ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, true, false)); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
		
	}
	
	public void updateUserLoginDetails(HttpServletRequest request,String username,HashMap userDataList,String author)
	{ 
		lcon = null;
		try
		{
			
		   String userStatus = "Active"; 
			  
  		   String selRoleId				= CommonUtility.checkNullObj(userDataList.get("selRoleId"));
  		   String selDistrict			= CommonUtility.checkNullObj(userDataList.get("selDistrict"));
  		   String selCampId				= CommonUtility.checkNullObj(userDataList.get("selCampId"));
  		   String selMandal				= CommonUtility.checkNullObj(userDataList.get("selMandal"));
  		   String user_disp_name		= CommonUtility.checkNullObj(userDataList.get("user_disp_name"));
  		   String user_email_id			= CommonUtility.checkNullObj(userDataList.get("user_email_id"));
  		   String user_contact_no		= CommonUtility.checkNullObj(userDataList.get("user_contact_no"));
  		   String is_active_id			= CommonUtility.checkNullObj(userDataList.get("is_active_id"));
  		   String is_locked_id			= CommonUtility.checkNullObj(userDataList.get("is_locked_id"));
  		   String reset_pwd_id			= CommonUtility.checkNullObj(userDataList.get("reset_pwd_id"));
  		   String user_change_remarks	= CommonUtility.checkNullObj(userDataList.get("user_change_remarks"));
  		   
  		   String client_ip_address 	= CommonUtility.getClientIPAddress(request);
  				   
  		   
			if(is_active_id.equalsIgnoreCase("N"))
			{
				userStatus="Inactive";
			}
			
			if(CommonValidators.htmlStrValidation(user_disp_name)==false)
			{
				request.setAttribute("alert_msg","Please enter valid person name");
				request.setAttribute("alert_css", "alert-danger");
			}
			else if(CommonValidators.emailValidator(user_email_id)==false)
			{
				request.setAttribute("alert_msg","Please enter valid eMail Id");
				request.setAttribute("alert_css", "alert-danger");
			}
			else if(CommonValidators.mobileNumberValidator(user_contact_no)==false)
			{
				request.setAttribute("alert_msg","Please enter valid Contact No.");
				request.setAttribute("alert_css", "alert-danger");
			}
			else if(CommonValidators.htmlStrValidation(user_change_remarks)==false)
			{
				request.setAttribute("alert_msg","Please enter valid remarks.");
				request.setAttribute("alert_css", "alert-danger");
			}
			else
			{ 
				lcon = DBConnection.getConnection();
				 lcon.setAutoCommit(false);
				 
				if(logUserLoginDetails(lcon, username)==true)
				{
					if(logUpdatedData(lcon, username, selRoleId, selDistrict, selCampId, selMandal,user_disp_name, user_email_id, user_contact_no, is_active_id, userStatus,is_locked_id, reset_pwd_id, author,client_ip_address,user_change_remarks)==true)
					{
						lcon.commit();
						request.setAttribute("alert_msg","User Login Details Updated Successfully.");
						request.setAttribute("alert_css", "alert-success");
						
					}
					else
					{
						lcon.rollback();
						request.setAttribute("alert_msg","01) Failed to update User Login Details. Please try again!");
						request.setAttribute("alert_css", "alert-danger");	
					}
				}
				else
				{ 
					lcon.rollback();
					request.setAttribute("alert_msg","02) Failed to update User Login Details. Please try again!");
					request.setAttribute("alert_css", "alert-danger");	
				}
			}	 
				 
		}
		catch(Exception e)
		{
			try
			{
				lcon.rollback();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			log.error(e);
			request.setAttribute("alert_msg","03) Failed to update User Login Details. Please try again!");
			request.setAttribute("alert_css", "alert-danger");	
		}
		finally
		{
			DBConnection.closeConnection(lcon);
		}
	}
	
	private boolean logUserLoginDetails(Connection myCon,String username)
	{
		boolean result = false;
		PreparedStatement preStat = null;
		try
		{
			lStrQuery = 
							"INSERT INTO login_details_log\n" +
							"(PersonName,UserName,Password,role_id,District_ID,Camp_ID,Created_by,\n" + 
							"System_IP_Address,Created_Date,Updated_Date,Status,\n" + 
							"UserStatus,Mandal_ID,password_flag,Encrypted_password,\n" + 
							"is_active,updated_by,contactno,email,contact_view,User_Id,remarks)\n" + 
							"SELECT PersonName,UserName,Password,role_id,District_ID,Camp_ID,Created_by,\n" + 
							"System_IP_Address,Created_Date,Updated_Date,Status,\n" + 
							"UserStatus,Mandal_ID,password_flag,Encrypted_password,\n" + 
							"is_active,updated_by,contactno,email,contact_view,User_Id,remarks FROM login_details\n" + 
							"WHERE LTRIM(RTRIM(UPPER(username)))=?";
			
			preStat = myCon.prepareStatement(lStrQuery);
			preStat.setString(1,username.trim().toUpperCase());
			
			if(preStat.executeUpdate()>0)
			{
				result = true;
			}
			else
			{
				result = false;
			}

		}
		catch(Exception e)
		{
			log.error(e);
			e.printStackTrace();
			result = false;
		}
		finally
		{
			DBConnection.closeStatement(preStat);
		}
		return result;
	}
	
	private boolean logUpdatedData
	(
		Connection myCon,
		String loginUserID,String role_id,String district_id,String camp_id,
		String mandal_id,String user_disp_name,String email_id,String contact_no,String is_active,
		String user_status,String lock_user,String pwd_reset_flag,String author,String client_ip_address,String userRemarks
	)
	{
		boolean result = false;
		PreparedStatement preStrmt = null;
		try
		{
			 
			 lStrQuery=
					 "UPDATE login_details SET \n"+
					 "role_id=?,district_id=?,camp_id=?,mandal_id=?,personname=?,System_IP_Address=?, \n"+
					 "email=?,contactno=?,is_active=?,status=?,updated_by=?,updated_date=CURRENT_TIMESTAMP,remarks=?,userstatus=?\n";
			 
					 if(pwd_reset_flag.equalsIgnoreCase("Y"))
					 {
						 lStrQuery+=",password='Sadarem@123',Encrypted_password='8530d2ba9b8720ee1cb8f0dd2676b5c0',password_flag='Y' \n";
					 }
				 
				 lStrQuery+=" WHERE UPPER(LTRIM(RTRIM(username)))= ? ";
				 
				 preStrmt = lcon.prepareStatement(lStrQuery);
				 int param=1;
				 
				 preStrmt.setString(param++,role_id);
				 preStrmt.setString(param++,district_id);
				 
				 if(camp_id.equalsIgnoreCase("NA") || camp_id.equalsIgnoreCase("-1"))
				 {
					 preStrmt.setNull(param++,Types.NULL);
				 }
				 else
				 {
					 preStrmt.setString(param++,camp_id); 
				 }
				 

				 if(mandal_id.equalsIgnoreCase("NA") || mandal_id.equalsIgnoreCase("-1"))
				 {
					 preStrmt.setNull(param++,Types.NULL);
				 }
				 else
				 {
					 preStrmt.setString(param++,mandal_id); 
				 }
				 
				 if(lock_user.equalsIgnoreCase("N"))
				 {
					 lock_user="0";
				 }
				 else if(lock_user.equalsIgnoreCase("Y"))
				 {
					 lock_user = "1";
				 }

				 preStrmt.setString(param++,user_disp_name.trim());
				 preStrmt.setString(param++,client_ip_address.trim());
				 preStrmt.setString(param++,email_id);
				 preStrmt.setString(param++,contact_no);
				 preStrmt.setString(param++,is_active);
				 preStrmt.setString(param++,user_status);
				 preStrmt.setString(param++,author);
				 preStrmt.setString(param++,userRemarks);
				 preStrmt.setString(param++,lock_user);
				 preStrmt.setString(param++,loginUserID.trim().toUpperCase());
			
				if(preStrmt.executeUpdate()>0)
				{
					result = true;
				}
				else
				{
					result = false;
				}

		}
		catch(Exception e)
		{
			log.error(e);
			e.printStackTrace();
			result = false;
		}
		finally
		{
			DBConnection.closeStatement(preStrmt);
		}
		return result;
	}
	
	
	public ArrayList getLoginUserDetailsByRoleDistCampId(String role_id,String district_id,String camp_id,String mandal_id,String search_user_id,String search_email_id,String search_contact_no)
	{
		ArrayList resultList = new ArrayList();
		try
		{
			
			ArrayList paramList  = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
			lStrQuery =   
					" SELECT  user_status 'User Status',user_name 'User Id',user_id 'Login Id',\n" +
					" person_name 'Person Name',role_name 'Role',district_name 'District',\n" + 
					" camp_name 'Camp',mandal_name 'Mandal',\n" + 
					" contact_no 'Contact No.',email_id 'eMail Id',\n" + 
					" created_by 'Created By',CONVERT(VARCHAR(17),created_date,113) 'Created Date'\n" + 
					" FROM SADAREM_VIEW_USER_LOGIN_DTLS WITH(NOLOCK) \n" + 
					" WHERE\n" + 
					"( user_name IS NOT NULL \n";
 
						  
						if(role_id!=null && !role_id.equals("") && !role_id.equals("-1") && !role_id.equalsIgnoreCase("NA"))
						{ 
							lStrQuery+=" AND role_id=?\n" ; 
							tempList  = new ArrayList();
							tempList.add("S");
							tempList.add(role_id);
							paramList.add(tempList);
						}
						
						if(district_id!=null && !district_id.equals("") && !district_id.equals("-1") && !district_id.equalsIgnoreCase("NA"))
						{ 
							lStrQuery+=" AND district_id=?\n" ; 
							tempList  = new ArrayList();
							tempList.add("S");
							tempList.add(district_id);
							paramList.add(tempList);
						}
						
						if(camp_id!=null && !camp_id.equals("") && !camp_id.equals("-1") && !camp_id.equalsIgnoreCase("NA"))
						{ 
							lStrQuery+=" AND camp_id=?\n" ; 
							tempList  = new ArrayList();
							tempList.add("S");
							tempList.add(camp_id);
							paramList.add(tempList);
						}
						
						if(mandal_id!=null && !mandal_id.equals("") && !mandal_id.equals("-1") && !mandal_id.equalsIgnoreCase("NA"))
						{ 
							lStrQuery+=" AND mandal_id=?\n" ; 
							tempList  = new ArrayList();
							tempList.add("S");
							tempList.add(mandal_id);
							paramList.add(tempList);
						}
						
						lStrQuery+=") ";
						
						if(
							(search_user_id!=null && search_user_id.length()>=5 && CommonValidators.htmlStrValidation(search_user_id)==true) ||
							(search_email_id!=null && CommonValidators.emailValidator(search_email_id)==true) ||
							(search_contact_no!=null && search_contact_no.length()==10 && CommonValidators.mobileNumberValidator(search_contact_no))
						  )
						{
							String orCondtionStr = ""; 
							
							if(search_user_id!=null && !search_user_id.equals(""))
							{
								orCondtionStr = "( UPPER(user_name) LIKE ? OR UPPER(user_id) LIKE ? ) \n";

								tempList  = new ArrayList();
								tempList.add("S");
								tempList.add("%"+search_user_id.toUpperCase().trim()+"%");
								paramList.add(tempList);
								
								tempList  = new ArrayList();
								tempList.add("S");
								tempList.add("%"+search_user_id.toUpperCase().trim()+"%");
								paramList.add(tempList);
							}
							
							if(search_email_id.length()>0)
							{
								if(orCondtionStr.length()>0)
								{
									orCondtionStr+= " OR email_id= ?  \n";
								}
								else
								{
									orCondtionStr+= " email_id= ?  \n";
								}
								 
								tempList  = new ArrayList();
								tempList.add("S");
								tempList.add(search_email_id);
								paramList.add(tempList);
							}
							 

							if(search_contact_no.length()>0)
							{
								if(orCondtionStr.length()>0)
								{
									orCondtionStr+= " OR contact_no= ?  \n";
								}
								else
								{
									orCondtionStr+= " contact_no= ?  \n";
								}
								 
								tempList  = new ArrayList();
								tempList.add("S");
								tempList.add(search_contact_no);
								paramList.add(tempList);
							}
							
							if(orCondtionStr.length()>0)
							{
								lStrQuery+=" OR ("+orCondtionStr+") \n";
							}
						}
						
						
						lStrQuery+=" ORDER BY user_status,created_date DESC"; 
						
						// System.out.println("lStrQuery : "+lStrQuery+"\n paramList : "+paramList);
						
						resultList = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, true, false);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	public void lognewuserlogin(HttpServletRequest request,HashMap formData,String author)
	{
		
		
		lcon = null;
		PreparedStatement preStmt = null;
		
		try
		{
			String selRoleId 		= CommonUtility.checkNullObj(formData.get("selRoleId"));
			String selDistrict 		= CommonUtility.checkNullObj(formData.get("selDistrict"));
			String selCampId		= CommonUtility.checkNullObj(formData.get("selCampId"));
			String selMandal		= CommonUtility.checkNullObj(formData.get("selMandal"));
			String login_user_id	= CommonUtility.checkNullObj(formData.get("login_user_id"));
			String user_disp_name	= CommonUtility.checkNullObj(formData.get("user_disp_name"));	
			String user_email_id	= CommonUtility.checkNullObj(formData.get("user_email_id"));
			String user_contact_no	= CommonUtility.checkNullObj(formData.get("user_contact_no"));
			
			if(validateNewUserLoginForm(request, selRoleId, selDistrict, selCampId, selMandal, login_user_id, user_disp_name, user_email_id, user_contact_no)==true)
			{

				ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();

				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(login_user_id.trim().toUpperCase());
				paramList.add(tempList);
				
				tempList  = new ArrayList();
				tempList.add("S");
				tempList.add(login_user_id.trim().toUpperCase());
				paramList.add(tempList);
				
				int existCount = Integer.parseInt(DataAccess.getReturnResultByPstmt("SELECT COUNT(*) FROM login_details WITH(NOLOCK) WHERE UPPER(username)=? OR UPPER(user_id)=?", paramList));
			 	if(existCount==0)
			 	{
						lcon = DBConnection.getConnection();
						lStrQuery = "INSERT INTO login_details \n"+
									"(username,user_id,personname,\n"+
									" password,encrypted_password,password_flag,\n"+
									" role_id,district_id,camp_id, \n"+
									" mandal_id,email,contactno, \n"+
									" system_ip_address,status,userstatus,\n"+
									" is_active,contact_view,\n"+
									" created_by,created_date,updated_by,updated_date) \n"+
									"VALUES( \n "+
									" ?,?,?,\n"+
									" 'Sadarem@123','8530d2ba9b8720ee1cb8f0dd2676b5c0','Y', \n"+
									" ?,?,?,?,?,?,?,'Active',0,'Y','N',?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP)";
						
						preStmt = lcon.prepareStatement(lStrQuery);
						
						int paramId = 1;
						preStmt.setString(paramId++, login_user_id.trim().toUpperCase());
						preStmt.setString(paramId++, login_user_id.trim().toUpperCase());
						preStmt.setString(paramId++, user_disp_name.trim());
						preStmt.setString(paramId++, selRoleId.trim());
						preStmt.setString(paramId++, selDistrict.trim());
						if(selCampId.equals("") || selCampId.equals("-1") || selCampId.equalsIgnoreCase("NA"))
						{
							preStmt.setNull(paramId++,Types.NULL);
						}
						else
						{
							preStmt.setString(paramId++, selCampId.trim());
						}
						
						if(selMandal.equals("") || selMandal.equals("-1") || selMandal.equalsIgnoreCase("NA"))
						{
							preStmt.setNull(paramId++,Types.NULL);
						}
						else
						{
							preStmt.setString(paramId++, selMandal.trim());
						}
						
						preStmt.setString(paramId++, user_email_id.trim());
						preStmt.setString(paramId++, user_contact_no.trim());
						preStmt.setString(paramId++, CommonUtility.getClientIPAddress(request));
						preStmt.setString(paramId++, author.trim());
						preStmt.setString(paramId++, author.trim());
						
						if(preStmt.executeUpdate()>0)
						{ 
							request.setAttribute("alert_css","alert-success");
							request.setAttribute("alert_msg","New Login User Created Successfully with User Id : "+login_user_id.trim().toUpperCase()); 
						}
						else
						{ 
							request.setAttribute("alert_css","alert-danger");
							request.setAttribute("alert_msg","Failed to create login user. Please try again"); 
						}
						
						preStmt.close();
						lcon.close();
			 	}
			 	else
			 	{ 
					request.setAttribute("alert_css","alert-danger");
					request.setAttribute("alert_msg","Already login exist with same User Id : "+login_user_id.trim().toUpperCase()+"."); 
			 	} 
			}
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("alert_css","alert-danger");
			request.setAttribute("alert_msg","000) Sorry we are not able process your request. Please try again!"); 
		}
		finally
		{
			DBConnection.closeConnection(lcon);
			DBConnection.closeStatement(preStmt);
		}
		
	}
	
	private boolean validateNewUserLoginForm(HttpServletRequest request,String selRoleId, String selDistrict,String selCampId, String selMandal,String login_user_id, String user_disp_name, String user_email_id,String user_contact_no)
	{
		String alert_css = "";
		String alert_msg = "";
		
		boolean status = false;
		
		try
		 {
				if(selRoleId==null || selRoleId.equals("") || selRoleId.equals("-1") || CommonValidators.NumberValidator(selRoleId)==false)
				{
					alert_css = "alert-danger";
					alert_msg = " Please select valid Role"; 
					status = false;
				}
				else if(selDistrict==null || selDistrict.equals("") || selDistrict.equals("-1") || CommonValidators.NumberValidator(selDistrict)==false)
				{
					alert_css = "alert-danger";
					alert_msg = " Please select valid Role"; 
					status = false; 
				}
				else if
				(
					selCampId==null || selCampId.equals("") || 
					(!selCampId.equals("-1") && !selCampId.equalsIgnoreCase("NA") &&
					CommonValidators.NumberValidator(selCampId)==false )
				)
				{
					alert_css = "alert-danger";
					alert_msg = " Please select valid Camp"; 
					status = false; 
				}
				else if
				(
					selMandal==null || selMandal.equals("") || 
					(!selMandal.equals("-1") && !selMandal.equalsIgnoreCase("NA") &&
					CommonValidators.NumberValidator(selMandal)==false )
				)
				{
					alert_css = "alert-danger";
					alert_msg = " Please select valid Mandal"; 
					status = false; 
				}
				else if(login_user_id==null || login_user_id.length()<8 || login_user_id.length()>15 || CommonValidators.htmlStrValidation(login_user_id)==false)
				{
					alert_css = "alert-danger";
					alert_msg = " Please enter valid User Id.(Minimum length of 8 and maximum 15 characters)"; 
					status = false; 
				}
				else if(user_disp_name==null || user_disp_name.length()<5 || user_disp_name.length()>50 || CommonValidators.htmlStrValidation(user_disp_name)==false)
				{
					alert_css = "alert-danger";
					alert_msg = " Please enter valid Person Name.(Minimum length of 5 and maximum 50 characters)"; 
					status = false; 
				}
				else if(user_email_id==null || user_email_id.length()==0 || CommonValidators.emailValidator(user_email_id)==false)
				{
					alert_css = "alert-danger";
					alert_msg = " Please enter valid eMail Id"; 
					status = false; 
				}
				else if(user_contact_no==null || user_contact_no.length()<10 || CommonValidators.mobileNumberValidator(user_contact_no)==false)
				{
					alert_css = "alert-danger";
					alert_msg = " Please enter valid Contact No."; 
					status = false; 
				}
				else
				{ 
					status = true;
				}
		 }
		catch(Exception e)
		{
			e.printStackTrace();
			alert_css = "alert-danger";
			alert_msg = "001) Sorry we are not able to process your request. Please try again!"; 
			status = false;
		}

		request.setAttribute("alert_css", alert_css);
		request.setAttribute("alert_msg", alert_msg);
				
		return status;
	}
}
