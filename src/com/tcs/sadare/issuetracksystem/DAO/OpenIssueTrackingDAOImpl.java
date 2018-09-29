 
package com.tcs.sadare.issuetracksystem.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.bf.disability.Constants.CommonConstants;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class OpenIssueTrackingDAOImpl implements OpenIssueTrackingDAO
{
	static final private Logger log =Logger.getLogger(OpenIssueTrackingDAOImpl.class); 
	
	String lStrQuery = "";
	
	public ArrayList getIssueType()
	{
		ArrayList issueTypes = new ArrayList();
		
		
		try
		{
			lStrQuery =  "select mas.tkt_type_id,mas.tkt_type_name from tkt_type_master(nolock) mas join tkt_role_type_mapping(nolock) map on mas.tkt_type_id = map.tkt_type_id \n"+
			      "where mas.is_active='Y' and map.is_active='Y' and role_id in ('4','34')";
			
			issueTypes = DataAccess.pickData(lStrQuery, false, false);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getIssueType of "+e);
		}
		
		return issueTypes;
	}
	
	public ArrayList getApprovalIssueType(int roleId)
	{
		ArrayList issueTypes = new ArrayList();
		
		 
		try
		{
			String qry="";
			ArrayList paramList = new ArrayList();
			 ArrayList tempList = new ArrayList();
			
			 qry = "select distinct a.tkt_type_id,b.tkt_type_name from tkt_status_flow_mapping a,tkt_type_master b\n"+ 
				 "where a.tkt_type_id=b.tkt_type_id and  a.role_id=? \n"+
			     "and b.is_active='Y' and a.tkt_type_id not in (select tkt_type_id from tkt_role_type_mapping where role_id=?)";
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(roleId);
			paramList.add(tempList);
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(roleId);
			paramList.add(tempList);
			
			//issueTypes = DataAccess.pickData(qry, false, false); 
			

			issueTypes = (ArrayList)DataAccess.pickDataByPrepareStmt(qry, paramList, false, false);
			 
			//System.out.println("issueTypes----"+issueTypes); 
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getApprovalIssueType of "+e);
		}
		
		return issueTypes;
	}
	
	
	
	public ArrayList getPendingIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus)
	{
		ArrayList pendingIssueDtlsList  = new ArrayList();


		CommonIssueTrackingDAOImpl comobj= new CommonIssueTrackingDAOImpl();
		
		try
		{
			
			ArrayList paramList	 =	new ArrayList();
			ArrayList tempList	 =  null;
			
			String statusFlag = "";
			if("P".equals(issueReqStatus))
			{
			 	statusFlag = comobj.getFlowFlag(roleID, "A", "",tkt_type_id);
			}
			else if("A".equals(issueReqStatus))
			{
				statusFlag = comobj.getTktDecisionFlag(roleID, "A", "",tkt_type_id);
			}
			else if("R".equals(issueReqStatus))
			{
				statusFlag = comobj.getTktDecisionFlag(roleID, "R", "",tkt_type_id);
			}
		    
			//statusFlag = statusFlag.replaceAll("'","");
			 statusFlag = statusFlag.replaceAll(",", "','");
			
			lStrQuery = " SELECT t.tkt_req_id,t.sadarem_id,a.district_name,b.Mandal_name \n";
			
			if("P".equals(issueReqStatus))
			{
			   lStrQuery += " ,DATEDIFF(day,d.created_date,getdate()) AS DiffDate,t.tkt_type_id \n"; 
			}
			else if("A".equals(issueReqStatus))	
			{
				lStrQuery += " ,isnull(convert(varchar(20),t.updated_date,103),'-') AS DiffDate,case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end,t.tkt_type_id \n";
			}
			else if("R".equals(issueReqStatus))
			{
				lStrQuery += " ,isnull(convert(varchar(20),t.updated_date,103),'-')  AS DiffDate,case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end,t.tkt_type_id \n";
			}
			
			lStrQuery += "FROM tkt_request_master(nolock) t,tbldistrict_details(nolock) a, tblmandal_details(nolock) b \n"+
						" ,tkt_request_tobe_modify_dtls(nolock) d \n"+
						" WHERE t.dist_id=a.district_id AND t.dist_id=b.district_id AND \n"+
						" t.mandal_id=b.mandal_id AND t.tkt_req_id=d.tkt_req_id AND\n"+
						" t.tkt_type_id=? ";
			
			
		    tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(tkt_type_id);
		    paramList.add(tempList);
			
			if(!districtId.equals("00"))
			{
				lStrQuery += " AND t.dist_id=? ";
			 
				tempList	 =  new ArrayList();
			    tempList.add("S");
			    tempList.add(districtId);
			    paramList.add(tempList);
			}
			if(mandalId!=null && mandalId.length()>0 && mandalId !="")
			{
				 lStrQuery += " AND t.mandal_id=? ";
				 
				 	tempList	 =  new ArrayList();
				    tempList.add("S");
				    tempList.add(mandalId);
				    paramList.add(tempList);
			}
			
			lStrQuery +=	" AND t.status_flag In (?) AND t.is_active='Y' order by t.tkt_req_id\n";
			
			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(statusFlag);
		    paramList.add(tempList); 
			
			
			  pendingIssueDtlsList = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(SQLException sqle)
		{
			System.out.println("SQLException in CommonIssueApprovalDAOImpl @ getPendingIssueDtlsList :: Error Code : "+sqle.getErrorCode());
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Exception in CommonIssueApprovalDAOImpl @  getPendingIssueDtlsList"+e+e.getLocalizedMessage());
			e.printStackTrace();
			
		}
		return pendingIssueDtlsList;
	}

	
	public ArrayList getApprRejIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus)
	{
		ArrayList pendingIssueDtlsList  = new ArrayList();
		
		CommonIssueTrackingDAOImpl comobj= new CommonIssueTrackingDAOImpl();
		
		try
		{
			ArrayList paramList	 =	new ArrayList();
			ArrayList tempList	 =  null;
			
			
			String statusFlag = "";
			lStrQuery  = " SELECT t.tkt_req_id,t.sadarem_id,a.district_name,b.Mandal_name\n"+ 
					" ,isnull(convert(varchar(20),t.updated_date,103),'-') AS DiffDate,\n"+
					"case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end,t.tkt_type_id\n"+ 
					" FROM tkt_request_master(nolock) t,tbldistrict_details(nolock) a, tblmandal_details(nolock) b \n"+
					"  ,tkt_request_tobe_modify_dtls(nolock) d ,\n"+
					"  (SELECT distinct role_id, decision_flag FROM tkt_status_flow_mapping fm where tkt_type_id=? and request_type=? and role_id=? )st\n"+
					" WHERE t.dist_id=a.district_id AND t.dist_id=b.district_id  and \n"+
					"  t.mandal_id=b.mandal_id AND t.tkt_req_id=d.tkt_req_id AND  st.decision_flag=t.status_flag  AND \n"+
					"  t.tkt_type_id=?  AND t.is_active='Y'  "; 

			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(tkt_type_id);
		    paramList.add(tempList);
		    
			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(issueReqStatus);
		    paramList.add(tempList);
		    
			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(roleID);
		    paramList.add(tempList);
		    
			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(tkt_type_id);
		    paramList.add(tempList);
			
			
				if(!districtId.equals("00"))
				{
				 lStrQuery += " AND t.dist_id=? ";
				 
				    tempList	 =  new ArrayList();
				    tempList.add("S");
				    tempList.add(districtId);
				    paramList.add(tempList);
				 
				}	
				if(mandalId!=null && mandalId.length()>0 && mandalId !="")
				{
					 lStrQuery += " AND t.mandal_id=? ";
					 
					    tempList	 =  new ArrayList();
					    tempList.add("S");
					    tempList.add(mandalId);
					    paramList.add(tempList);
				}
				
			lStrQuery  += "UNION  SELECT t.tkt_req_id,t.sadarem_id,a.district_name,b.Mandal_name\n"+ 
					" ,isnull(convert(varchar(20),t.updated_date,103),'-') AS DiffDate,\n"+
					"case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end,t.tkt_type_id\n"+ 
					" FROM tkt_request_master(nolock) t,tbldistrict_details(nolock) a, tblmandal_details(nolock) b \n"+
					"  ,tkt_request_tobe_modify_dtls(nolock) d ,tkt_request_status_history s,\n"+
					"  (SELECT distinct role_id, decision_flag FROM tkt_status_flow_mapping fm where tkt_type_id=? and request_type=? and role_id=?)st\n"+
					" WHERE t.dist_id=a.district_id AND t.dist_id=b.district_id AND t.tkt_req_id=s.tkt_req_id and \n"+
					"  t.mandal_id=b.mandal_id AND t.tkt_req_id=d.tkt_req_id AND  st.decision_flag=s.status_flag  AND \n"+
					"  t.tkt_type_id=?  AND t.is_active='Y'  "; 

			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(tkt_type_id);
		    paramList.add(tempList);
		    
			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(issueReqStatus);
		    paramList.add(tempList);
		    
			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(roleID);
		    paramList.add(tempList);
		    
			tempList	 =  new ArrayList();
		    tempList.add("S");
		    tempList.add(tkt_type_id);
		    paramList.add(tempList);
		    
				
			
				if(!districtId.equals("00"))
				{
				 lStrQuery +=		"AND t.dist_id=? ";
				 
				 	tempList	 =  new ArrayList();
				    tempList.add("S");
				    tempList.add(districtId);
				    paramList.add(tempList);
				 
				}	
				if(mandalId!=null && mandalId.length()>0 && mandalId !="")
				{
					 lStrQuery += "and t.mandal_id=? ";
					 
					 tempList	 =  new ArrayList();
					    tempList.add("S");
					    tempList.add(mandalId);
					    paramList.add(tempList);
				}
			 lStrQuery += "order by t.tkt_req_id";
			 
		   // System.out.println("lStrQuery"+lStrQuery);
			  pendingIssueDtlsList = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(SQLException sqle)
		{
			System.out.println("SQLException in CommonIssueApprovalDAOImpl @ getPendingIssueDtlsList :: Error Code : "+sqle.getErrorCode());
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Exception in CommonIssueApprovalDAOImpl @  getPendingIssueDtlsList"+e+e.getLocalizedMessage());
			e.printStackTrace();
			
		}
		return pendingIssueDtlsList;
	}
	
	
	public ArrayList getRelationIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList(); 
			
			
			lStrQuery = "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,c.relation_name,a.relation_name,a.relation_teugu_name,a.remarks,\n"+
				"(select proof_doc_path+',' from tkt_request_master(nolock) b,tkt_req_proof_details(nolock) c\n"+ 
				 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				 ")\n"+ 
				 " from tkt_request_tobe_modify_dtls(nolock) a ,\n"+ 
				 "tkt_request_master(nolock) b,sadarem_gender_relation_type_mapping(nolock) c\n"+
				 "where a.tkt_req_id = b.tkt_req_id and b.gender = c.gender_id and a.relation_type = c.relation_id and a.tkt_req_id=?";
			
				tempList = new ArrayList(); 
			 	tempList.add("S");
	            tempList.add(reqId);
	            paramList.add(tempList);	
	            
	            tempList = new ArrayList();		 
	            tempList.add("S");
	            tempList.add(reqId);
	            paramList.add(tempList);	
	            
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getRelationIssueData of "+e);
		}
		
		return issuedata;
	}
	public ArrayList getQualificationIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList(); 
		
		
		try
		{
			lStrQuery =  "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,c.edu_desc,a.remarks,\n"+
					"(select proof_doc_path+',' from tkt_request_master(nolock) b,tkt_req_proof_details(nolock) c\n"+ 
					 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					 ")\n"+ 
					 " from tkt_request_tobe_modify_dtls(nolock) a ,\n"+ 
					 "tkt_request_master(nolock) b,sadarem_education_master(nolock) c\n"+
					 "where a.tkt_req_id = b.tkt_req_id  and a.edu_id = c.edu_id and a.tkt_req_id=?";
			
				tempList = new ArrayList();	
			 	tempList.add("S");
	            tempList.add(reqId);
	            paramList.add(tempList);	
	            
	            tempList = new ArrayList();		 
	            tempList.add("S");
	            tempList.add(reqId);
	            paramList.add(tempList);	
	            
	           // System.out.println("queryyyyyyyyy"+lStrQuery);
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getQualificationIssueData of "+e);
		}
		
		return issuedata;
	}
	public ArrayList getNameIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
				
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery =    "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.person_sur_name,a.person_name,\n"+
					"isnull(a.person_telugu_name,'-') person_telugu_name,a.remarks,\n"+
					"(select proof_doc_path+',' from tkt_request_master(nolock) b,tkt_req_proof_details(nolock) c\n"+ 
					"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					")from tkt_request_tobe_modify_dtls(nolock) a ,tkt_request_master(nolock) b \n"+
					"where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			
			tempList = new ArrayList();
			tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);	
            
            tempList = new ArrayList();
            tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);
            
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getNameIssueData of "+e);
		}
		
		return issuedata;
	}
	
	public ArrayList getAddressIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery = "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,d.district_name,m.mandal_name,\n"+
					"v.village_name,h.habitation_name,a.house_no,a.pin_code,a.remarks,(select proof_doc_path+',' from tkt_request_master(nolock) b,tkt_req_proof_details(nolock) c\n"+ 
					"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					") from \n"+
					"tkt_request_tobe_modify_dtls(nolock) a ,tbldistrict_details(nolock) d,tblmandal_details(nolock) m,\n"+
					"tblvillage_details(nolock) v,tblhabitation_details(nolock) h,\n"+
					"tkt_request_master b\n"+
					"where a.tkt_req_id = b.tkt_req_id and a.dist_id=d.district_id \n"+
					"and a.dist_id=m.district_id and a.mandal_id=m.mandal_id and\n"+
					" a.dist_id=v.district_id and a.mandal_id=v.mandal_id and a.village_id=v.village_id\n"+
					" and  a.dist_id=h.district_id and a.mandal_id=h.mandal_id and a.village_id=h.village_id\n"+
					" and a.hab_id=h.habitation_id and\n"+
					" a.tkt_req_id=?";
			tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);	tempList = new ArrayList();		 
            tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);	
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getAddressIssueData of "+e);
		}
		
		return issuedata;
	} 
	

	public ArrayList getGenderIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		
		try
		{
			
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery = "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.gender,\n"+ 
				 "a.remarks,(select proof_doc_path+',' from tkt_request_master(nolock) b,tkt_req_proof_details(nolock) c\n"+ 
				"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				") from tkt_request_tobe_modify_dtls(nolock) a ,\n"+ 
				 "tkt_request_master(nolock) b\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			
			tempList = new ArrayList();
			tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);
            
            tempList = new ArrayList();		 
            tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);
            
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getGenderIssueData of "+e);
		}
		
		return issuedata;
	} 
	
	public ArrayList getAgeIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery = "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,CONVERT(VARCHAR,a.dob,105),\n"+ 
				 "a.remarks,(select proof_doc_path+',' from tkt_request_master(nolock) b,tkt_req_proof_details(nolock) c\n"+ 
				 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				 "),DATEDIFF(year,a.dob,GETDATE()) from tkt_request_tobe_modify_dtls(nolock) a ,\n"+ 
				 "tkt_request_master(nolock) b\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			  
			tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);	tempList = new ArrayList();		 
            tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);	
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getAgeIssueData of "+e);
		}
		
		return issuedata;
	}
	
	
	
	
	public String updateApprovedStatusFlag(HashMap IssueApprovalDtls)
	{
		Connection con = null;
	   int basicInsertStatus=0,modifyStatus=0,result =0;
	   CommonDAO commonObj = new CommonDAOImpl();
	   String lStrQuery =  "",status="";
	   String statusMsg = "";
	   CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	   String flag, reqId, loginId, statusRemarks,issueType, isFinalFlag, docVerifyStatus,distName,sadaremid;
	   int roleId;
	   ArrayList smsdtls = new ArrayList();
	   
	   flag 		=   (String) IssueApprovalDtls.get("decisionFlag");
	   reqId 		=   (String) IssueApprovalDtls.get("reqId");
	   loginId 		=	(String) IssueApprovalDtls.get("loginId");
	   statusRemarks=   (String) IssueApprovalDtls.get("newremarks");
	   roleId 		=   (Integer) IssueApprovalDtls.get("roleId");
	   issueType 	=   (String) IssueApprovalDtls.get("selIssueType");
	   isFinalFlag  =   (String) IssueApprovalDtls.get("isFinalFlag");
	   docVerifyStatus = (String) IssueApprovalDtls.get("docVerifyStatus");
	   distName        = (String) IssueApprovalDtls.get("distName");
	   sadaremid        = (String) IssueApprovalDtls.get("sadaremId");
	   String teluguname=(String) IssueApprovalDtls.get("teluname");
	   String selIssueType   = (String)IssueApprovalDtls.get("selIssueType"); 
	   String ipaddress   = CommonUtility.checkNullObject((String)IssueApprovalDtls.get("ipaddress"));
	  try
        {
	       con = DBConnection.getConnection();
	       con.setAutoCommit(false);
		    PreparedStatement lPstmt = null;
		
		    
		    lStrQuery =  "insert into tkt_request_status_history(tkt_req_id,status_flag,doc_verify_status,status_remarks,modified_ip_address,modified_by,modified_date)\n"+
		            "select tkt_req_id,status_flag,doc_verify_status,status_remarks,ISNULL(update_ip_address,req_ip_address),updated_by,updated_date from tkt_request_master where tkt_req_id=? ";
		    	
		   
		     lPstmt = con.prepareStatement(lStrQuery);
		     lPstmt.setString(1, reqId);
		     result = lPstmt.executeUpdate();
		    
		    if(result > 0)
		    {
		    	result = 0;
		    	lStrQuery =  "update tkt_request_master set status_flag=?,status_remarks=?,doc_verify_status=?,updated_date=getdate(),updated_by=?,update_ip_address=? where tkt_req_id=?";
				  
		    	 lPstmt = con.prepareStatement(lStrQuery);
		    	 lPstmt.setString(1,flag );
		    	 lPstmt.setString(2,statusRemarks );
		    	 lPstmt.setString(3,docVerifyStatus );
		    	 lPstmt.setString(4, loginId); 
		    	 lPstmt.setString(5, ipaddress);
		    	 lPstmt.setString(6, reqId);
			      result = lPstmt.executeUpdate();
			      if(result > 0)
			      {
			    	
			    	    	if(selIssueType.equals(CommonConstants.NAMECHANGEISSUE_ID))
							{
			    	    		result = updateFinalNameIssueDtls(reqId,flag,con,teluguname);
							}
							else if(selIssueType.equals(CommonConstants.RELATIONNAMECHANGEISSUE_ID))
							{
								result = updateFinalRelationIssueDtls(reqId,flag,con,teluguname);	
							}
						    if(result>0)
						    {
						    	con.commit();
					    	    statusMsg = comObj.getFlagDesc(flag); 
					    	    commonObj.sendTKTRequestStatusbySMS(reqId, loginId);
						    }
						    else
					    	{
						    	 statusMsg = "Error occured while updating request.Please try later !!";
						    	 con.rollback();
					    	}
			      }
			      else
			      {
			    	  statusMsg = "Error occured while updating request.Please try later !!";
			    	  con.rollback();
			      }
		    }
		    else
		    {
		    	statusMsg = "Error occured while updating request.Please try later !!";
		    }
		     
		  }
		    catch(Exception e)
		    {
				    try 
				    {
				    	statusMsg = "Error occured while updating request.Please try later !!";
						con.rollback();
					} catch (SQLException e1) 
					{
						 
						e1.printStackTrace();
					}
			  e.printStackTrace();
		    }
	     finally
	     {
	    	 
	    	try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			} 
	     }
	return statusMsg;
     
	}
	
	

	public String updateRejectedStatusFlag(HashMap IssueApprovalDtls)
	{
		String flag, reqId, loginId, statusRemarks, docVerifyStatus;
		Connection con = null;
	   int basicInsertStatus=0,modifyStatus=0,result =0;
	   String lStrQuery =  "",status="";
	   String statusMsg = "";
	  
     try
        {
    	 CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
  	   ArrayList smsdtls = new ArrayList();
  	   flag 		=   (String) IssueApprovalDtls.get("decisionFlag");
  	   reqId 		=   (String) IssueApprovalDtls.get("reqId");
  	   loginId 		=	(String) IssueApprovalDtls.get("loginId");
  	   statusRemarks=   (String) IssueApprovalDtls.get("newremarks");
  	   docVerifyStatus = (String) IssueApprovalDtls.get("docVerifyStatus");
  	 String ipaddress = CommonUtility.checkNullObject((String) IssueApprovalDtls.get("ipaddress"));
  	   
  	   CommonDAO commonObj = new CommonDAOImpl();
    	 
    	 
	       con = DBConnection.getConnection();
	       con.setAutoCommit(false);
		    PreparedStatement lPstmt = null;
		
		    
		    lStrQuery =  "insert into tkt_request_status_history(tkt_req_id,status_flag,doc_verify_status,status_remarks,modified_ip_address,modified_by,modified_date)\n"+
		            "select tkt_req_id,status_flag,doc_verify_status,status_remarks,ISNULL(update_ip_address,req_ip_address),updated_by,updated_date from tkt_request_master where tkt_req_id=? ";
		    	
		     lPstmt = con.prepareStatement(lStrQuery);
		     lPstmt.setString(1, reqId);
		     result = lPstmt.executeUpdate();
		    
		    if(result > 0)
		    {
		    	result = 0;
		    	lStrQuery =  "update tkt_request_master set status_flag=?,status_remarks=?,doc_verify_status=?,updated_date=getdate(),updated_by=?,update_ip_address=? where tkt_req_id=?";
				  lPstmt = con.prepareStatement(lStrQuery);
				  lPstmt.setString(1,flag );
			    	 lPstmt.setString(2,statusRemarks );
			    	 lPstmt.setString(3,docVerifyStatus );
			    	 lPstmt.setString(4, loginId); 
			    	 lPstmt.setString(5, ipaddress);
			    	 lPstmt.setString(6, reqId);
			      result = lPstmt.executeUpdate();
			      if(result > 0)
			      {
			    	  con.commit();
			    	    statusMsg = comObj.getFlagDesc(flag); 
			    	    commonObj.sendTKTRequestStatusbySMS(reqId, loginId);
				    	  
			      }
			      else
			      {
			    	  statusMsg = "Error occured while updating request.Please try later !!";
			    	  con.rollback();
			      }
		    }
		    else
		    {
		    	statusMsg = "Error occured while updating request.Please try later !!";
		    }
		     
		  }
		    catch(Exception e)
		    {
				    try 
				    {
				    	statusMsg = "Error occured while updating request.Please try later !!";
						con.rollback();
					} catch (SQLException e1) 
					{
						 
						e1.printStackTrace();
					}
			  e.printStackTrace();
		    }
	     finally
	     {
	    	 
	    	try {
				con.close();
			} catch (SQLException e) {
				 
				e.printStackTrace();
			} 
	     }
	return statusMsg;
     
	}
	
	
	
		public ArrayList getModifieddetails(String reqId)
		{
			ArrayList issuedata = new ArrayList();
			
			
			try
			{
				ArrayList paramList = new ArrayList();
				ArrayList tempList = new ArrayList();
				
				lStrQuery = " select * from dbo.tkt_request_tobe_modify_dtls(nolock) where tkt_req_id=?";
				
				tempList = new ArrayList();
				tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
				
			}
			catch(Exception e)
			{
				System.out.println("Exception raised in getModifieddetails of "+e);
			}
			
			return issuedata;
		}
		
		public int updateFinalRelationIssueDtls(String reqId,String flag,Connection con,String teluguname)
		{
			String statusMsg = "";
			ArrayList nameIssueDtls = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				    
		    		   
					   lStrQuery =  "update tkt_request_tobe_modify_dtls set relation_teugu_name=?,updated_date=getdate() where tkt_req_id=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					   lPstmt.setString(1,teluguname);
					   lPstmt.setString(2,reqId);
					 
					   
					     result = lPstmt.executeUpdate();

			}
			catch(Exception e)
			{
				result=0;
				e.printStackTrace();
			}
			 
			
			
			return result;
		}
		
		public int updateFinalAddressIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "",qry="";
			ArrayList addrIssueDtls = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				 //  con = DBConnection.getConnection();
			    //   con.setAutoCommit(false);
				   PreparedStatement lPstmt = null; 
				   CommonDAO comObj1 = new CommonDAOImpl();
				  
				 ArrayList  paramList = new ArrayList();
				 ArrayList  tempList = new ArrayList();
				 				 
				 
		    	lStrQuery = 
		    			"SELECT sadarem_id,dist_id,mandal_id,village_id,hab_id,house_no,pin_code \n "+
		    			" FROM tkt_request_tobe_modify_dtls(nolock) where tkt_req_id=?";
		    	
		    	tempList = new ArrayList();
		    	tempList.add("S");
		    	tempList.add(reqId);
		    	paramList.add(tempList);
		    	
			
		    	addrIssueDtls = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    	
			   if(addrIssueDtls.size() > 0)
			   {
				   addrIssueDtls = (ArrayList)addrIssueDtls.get(0);
				   
				   if(addrIssueDtls.size()>0)
				   {
					   String habCode = comObj1.getHabCodewithoutpanchayat((String)addrIssueDtls.get(1), (String)addrIssueDtls.get(2), (String)addrIssueDtls.get(3), (String)addrIssueDtls.get(4));
					  
					   lStrQuery =  "update tblperson_personal_details set District_ID=?,DistrictID=?,\n"+
					   		"Mandal_ID=?,MandalID=?,Village_ID=?,VillageID=?,Habitation_ID=?,HabtationID=?,\n"+
							   "House_Number=?,Pin_Code=?,updated_date=getdate(),HabCode=? where person_code=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					   lPstmt.setString(1,(String)addrIssueDtls.get(1) );
					   lPstmt.setString(2,(String)addrIssueDtls.get(1) );
					   lPstmt.setString(3,(String)addrIssueDtls.get(2) );
					   lPstmt.setString(4,(String)addrIssueDtls.get(2) );
					   lPstmt.setString(5,(String)addrIssueDtls.get(3) );
					   lPstmt.setString(6,(String)addrIssueDtls.get(3) );
					   lPstmt.setString(7,(String)addrIssueDtls.get(4) );
					   lPstmt.setString(8,(String)addrIssueDtls.get(4) );
					   lPstmt.setString(9,(String)addrIssueDtls.get(5) );
					   lPstmt.setString(10,(String)addrIssueDtls.get(6) );
					   lPstmt.setString(11,habCode);
					   lPstmt.setString(12,(String)addrIssueDtls.get(0) );
					     result = lPstmt.executeUpdate();
					     
					    if(result >0) 
					    {
					    	 lStrQuery =  "update tblperson_personal_details_new set District_ID=?,DistrictID=?,\n"+
								   		"Mandal_ID=?,MandalID=?,Village_ID=?,VillageID=?,Habitation_ID=?,HabtationID=?,\n"+
										   "House_Number=?,Pin_Code=?,updated_date=getdate(),HabCode=? where person_code=?";
								   lPstmt = con.prepareStatement(lStrQuery);
								   lPstmt.setString(1,(String)addrIssueDtls.get(1) );
								   lPstmt.setString(2,(String)addrIssueDtls.get(1) );
								   lPstmt.setString(3,(String)addrIssueDtls.get(2) );
								   lPstmt.setString(4,(String)addrIssueDtls.get(2) );
								   lPstmt.setString(5,(String)addrIssueDtls.get(3) );
								   lPstmt.setString(6,(String)addrIssueDtls.get(3) );
								   lPstmt.setString(7,(String)addrIssueDtls.get(4) );
								   lPstmt.setString(8,(String)addrIssueDtls.get(4) );
								   lPstmt.setString(9,(String)addrIssueDtls.get(5) );
								   lPstmt.setString(10,(String)addrIssueDtls.get(6) );
								   lPstmt.setString(11,habCode);
								   lPstmt.setString(12,(String)addrIssueDtls.get(0) );
							     result = lPstmt.executeUpdate();
							     
						 }
				   }
				}
			   
			   
			}
			catch(Exception e)
			{
				result=0;
				e.printStackTrace();
			}
			 
			
			return result;
		}
		public int updateFinalNameIssueDtls(String reqId,String flag,Connection con,String teluguname)
		{
			String statusMsg = "",qry="";
			ArrayList nameIssueDtls = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				    
		    		   
					   lStrQuery =  "update tkt_request_tobe_modify_dtls set person_telugu_name=?,updated_date=getdate() where tkt_req_id=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					   lPstmt.setString(1,teluguname);
					   lPstmt.setString(2,reqId);
					 
					   
					     result = lPstmt.executeUpdate();

			}
			catch(Exception e)
			{
				result=0;
				e.printStackTrace();
			}
			 
			
			
			return result;
		} 
		
		public int updateFinalGenderIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "",qry="";
			ArrayList nameIssueDtls = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				 ArrayList  paramList = new ArrayList();
				 ArrayList  tempList = new ArrayList();
				
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				    
		    	lStrQuery =  "select sadarem_id,gender from tkt_request_tobe_modify_dtls(nolock) where tkt_req_id=?";
		    	
		    	tempList = new ArrayList();
		    	tempList.add("S");
		    	tempList.add(reqId);
		    	paramList.add(tempList);
		    	
		    	nameIssueDtls = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			   if(nameIssueDtls.size() > 0)
			   {
				   nameIssueDtls = (ArrayList)nameIssueDtls.get(0);
				   
				   if(nameIssueDtls.size()>0)
				   {
					   
					   lStrQuery =  "update tblperson_personal_details set gender=?,updated_date=getdate() where person_code=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					//   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
					   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
					   lPstmt.setString(2,(String)nameIssueDtls.get(0) );
					   
					   
					     result = lPstmt.executeUpdate();
					     
					    if(result >0) 
					    {
					    	 lStrQuery =  "update tblperson_personal_details_new set gender=?,updated_date=getdate() where person_code=?";
							   lPstmt = con.prepareStatement(lStrQuery);
						//	   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
							   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
							   lPstmt.setString(2,(String)nameIssueDtls.get(0) );
							   
							     result = lPstmt.executeUpdate();
						 }
				   }
				}
			   
			   
			}
			catch(Exception e)
			{
				result=0;
				e.printStackTrace();
			}
			 
			
			
			return result;
		} 
		
		public int updateFinalAgeIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "",qry="";
			ArrayList nameIssueDtls = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				
				 ArrayList  paramList = new ArrayList();
				 ArrayList  tempList = new ArrayList();
				
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				    
		    	lStrQuery =  "select sadarem_id,dob from tkt_request_tobe_modify_dtls(nolock) where tkt_req_id=?";
		    	
		    	tempList = new ArrayList();
		    	tempList.add("S");
		    	tempList.add(reqId);
		    	paramList.add(tempList);
		    	
		    	nameIssueDtls = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			   if(nameIssueDtls.size() > 0)
			   {
				   nameIssueDtls = (ArrayList)nameIssueDtls.get(0);
				   
				   if(nameIssueDtls.size()>0)
				   {
					   
					   lStrQuery =  "update tblperson_personal_details set date_of_birth=?,updated_date=getdate() where person_code=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					//   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
					   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
					   lPstmt.setString(2,(String)nameIssueDtls.get(0) );
					   
					   
					     result = lPstmt.executeUpdate();
					     
					    if(result >0) 
					    {
					    	 lStrQuery =  "update tblperson_personal_details_new set date_of_birth=?,updated_date=getdate() where person_code=?";
							   lPstmt = con.prepareStatement(lStrQuery);
						//	   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
							   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
							   lPstmt.setString(2,(String)nameIssueDtls.get(0) );
							   
							     result = lPstmt.executeUpdate();
						 }
				   }
				}
			   
			   
			}
			catch(Exception e)
			{
				result=0;
				e.printStackTrace();
			}
			 
			
			
			return result;
		} 
		
		
		
		 public String getFilePath(String reqId)
		 {
			 String path="",qry="";
				ArrayList tempList = new ArrayList();
				ArrayList paramList = new ArrayList();
			try
			{
			 lStrQuery = "select attach_doc_one from tkt_request_tobe_modify_dtls where tkt_req_id=?";
			 
			 //path = DataAccess.getReturnResult(qry);
			 
			 tempList.add("S"); 
				tempList.add(reqId);
				paramList.add(tempList);
			 
			 path = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			} 
			 return path;
		 }
		 public ArrayList getIssueStatusHistory(String reqId)
			{
				ArrayList issuedata = new ArrayList();
				
				ArrayList paramList = new ArrayList();
				ArrayList tempList = new ArrayList();
				try
				{
					lStrQuery = " SELECT fm.tkt_flag_desc,ISNULL(status_remarks,'-') Remarks,convert(varchar(19),modified_date) date,\n"+
					      "	modified_by username,ISNULL(RN.PERSONNAME,MODIFIED_BY) personname,ISNULL(RN.ROLE_NAME,'-') role  \n"+
						"	FROM (\n"+ 
						"	 SELECT TKT_REQ_ID,STATUS_REMARKS,STATUS_FLAG,MODIFIED_BY,\n"+
						"	 MODIFIED_DATE FROM TKT_REQUEST_STATUS_HISTORY(NOLOCK) \n"+
						"	 WHERE TKT_REQ_ID=?\n"+
						"	 UNION  \n"+
						"	 SELECT TKT_REQ_ID,STATUS_REMARKS,STATUS_FLAG,\n"+
						"	 UPDATED_BY MODIFIED_BY,UPDATED_DATE FROM TKT_REQUEST_MASTER(NOLOCK)\n"+ 
						"	 WHERE TKT_REQ_ID= ?) hs \n"+
						"	 LEFT OUTER JOIN login_details lg ON  \n"+
						"	 hs.modified_by = lg.username COLLATE SQL_Latin1_General_CP1_CS_AS\n"+
						"	 LEFT JOIN tkt_status_flag_master fm   ON\n"+ 
						"	fm.tkt_flag_id=HS.status_flag\n"+
						"	LEFT JOIN (SELECT L.PERSONNAME,L.USERNAME,ISNULL(ROLE_NAME,'') ROLE_NAME\n"+
						"	FROM LOGIN_DETAILS L\n"+
						"	LEFT JOIN ROLES R\n"+
						"	ON L.ROLE_ID=R.ROLE_ID AND L.Status='Active') RN\n"+
						"	ON RN.USERNAME=HS.modified_by COLLATE SQL_Latin1_General_CP1_CS_AS ORDER BY modified_date";
					    
						tempList = new ArrayList();
						tempList.add("S");
			            tempList.add(reqId);
			            paramList.add(tempList); 
			            
			            tempList = new ArrayList();
			            tempList.add("S");
			            tempList.add(reqId);
			            paramList.add(tempList);
			            
					issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					
				}
				catch(Exception e)
				{
					System.out.println("Exception raised in getIssueData of "+e);
				}
				
				return issuedata;
			}
		 
		

		 public ArrayList getOpenIssueApprovalIssueType(int roleId)
			{
				ArrayList issueTypes = new ArrayList();
				
				
				try
				{
					ArrayList  paramList = new ArrayList();
					ArrayList  tempList = new ArrayList();
					
					lStrQuery = 
						 "select distinct a.tkt_type_id,b.tkt_type_name from tkt_status_flow_mapping a,tkt_type_master b\n"+
					     "where a.tkt_type_id=b.tkt_type_id and  a.role_id=?\n"+
					     "and b.is_active='Y'\n"+ 
	                     "and len(a.decision_flag)=?";
					
					tempList = new ArrayList();
					tempList.add("S");
					tempList.add(roleId);
					paramList.add(tempList);
					
					tempList = new ArrayList();
					tempList.add("I");
					tempList.add(4);
					paramList.add(tempList);
					
					issueTypes = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					
					
				}
				catch(Exception e)
				{
					System.out.println("Exception raised in getOpenIssueApprovalIssueType of "+e);
				}
				
				return issueTypes;
			} 
		 public String generateSMStransID()
			{
				String transId = "",qry="";
										
			  try
			  {
				lStrQuery = "SELECT 'GSMS'+ replace(CONVERT(varchar(10),getdate(),105),'-','')  + replace(CONVERT(varchar(24),getdate(),114),':','')"; 
				transId = DataAccess.getReturnResult(qry);
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
				  System.out.println(""+e);
			  }
				return transId;
			}
		 
		 public int smsInsertDtls(HashMap basicDtls,Connection lcon)
			{
				String lStrQuery =  "",status="";
				  PreparedStatement lPstmt = null;
				  int result =0;
				  CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
				  String issueReqId ="",docType="";
				  String transId= (String)generateSMStransID(); 
				  String sms_txt=(String)comObj.getFlagDesc((String)basicDtls.get("decisionFlag"));
				try
				{
				 
					if(((String)basicDtls.get("contactNo")).length()>1)
					{
						lStrQuery =  
							 "INSERT INTO tkt_sms_status_log(trans_id,tkt_req_id,Sadarem_id,status_flag,contact_no,sms_txt,is_active,is_sent,created_by,created_date)\n"+
						     "VALUES(?,?,?,?,?,?,'Y','N',?,getdate())";
					
					    lPstmt = lcon.prepareStatement(lStrQuery);
					    lPstmt.setString(1,transId);
					    lPstmt.setString(2,(String)basicDtls.get("reqId") );
					    lPstmt.setString(3,(String)basicDtls.get("sadaremId") );
					    lPstmt.setString(4,(String)basicDtls.get("decisionFlag") );
					    lPstmt.setString(5,(String)basicDtls.get("contactNo") );
					    lPstmt.setString(6,sms_txt);
					    lPstmt.setString(7,(String)basicDtls.get("reqBy") ); 
					    
					  result = lPstmt.executeUpdate();
					}
					else
					{
						result=0;
					}
				  
				}
				catch(Exception e)
				{
					log.info("Exception raised in smsInsertDtls"+e);
					e.printStackTrace();
				}
				finally
				{
					try
					{
						lPstmt.close();
					}
					catch (SQLException e) 
					{
					 
						e.printStackTrace();
					}
				}
				
				return result;
			}
		 
	}