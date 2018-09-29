
package com.tcs.sadare.issuetracksystem.DAO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.SADAREMDBException;
import org.bf.disability.common.CommonDetails;
import org.bf.disability.dao.ExceptionDAO;

import Aadhar.AadhaarUtility;

import com.ecentric.servicemodels.AadhaarProfile;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sadarem.util.SMSUtility;
import com.tcs.sgv.common.util.DBConnection;
public class CommonIssueApprovalDAOImpl implements CommonIssueApprovalDAO
{
	String lStrQuery = "";
	
	public ArrayList getIssueType()
	{
		ArrayList issueTypes = new ArrayList();
		
		
		try
		{
			lStrQuery = "select mas.tkt_type_id,mas.tkt_type_name from tkt_type_master  mas WITH(NOLOCK) join tkt_role_type_mapping map WITH(NOLOCK) on mas.tkt_type_id = map.tkt_type_id \n"+
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
			lStrQuery ="select distinct a.tkt_type_id,b.tkt_type_name from tkt_status_flow_mapping a,tkt_type_master b\n"+ 
				 "where a.tkt_type_id=b.tkt_type_id and  a.role_id=?\n"+
			     "and b.is_active='Y' ";
			    
			     /*if(!(CommonConstants.SPMLOGIN_ROLEID==roleId))
			     {*/
			     
			    	 lStrQuery    += "and a.tkt_type_id not in (select tkt_type_id from tkt_role_type_mapping where role_id=?)";
			     /*}*/ 
			    	 
 
			
			
		       ArrayList resultList = new ArrayList();
	    		ArrayList paramList = new ArrayList();
	    		ArrayList tempList	= new ArrayList();

	                	   tempList.add("S");
	   			    		tempList.add(roleId);
	   			    		paramList.add(tempList);
	   			    		
	   			    		
	   			    		tempList = new ArrayList();
	   			    		tempList.add("S");
	   			    		tempList.add(roleId);
	   			    		paramList.add(tempList);
	   			    		

	    		  	
				    		issueTypes=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);    
			
			
			 if(roleId==Integer.parseInt(CommonConstants.DPMLOGINROLEID)) 
		     {
		    	 ArrayList temp = new ArrayList();
		    	 temp.add("S020");
		    	 temp.add("Multiple Grievance");
		    	 issueTypes.add(temp);
		     }
			 			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getIssueType of "+e);
		}
		
		return issueTypes;
	}
	
	
	
	public ArrayList getPendingIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus)
	{
		ArrayList pendingIssueDtlsList  = new ArrayList();
		ArrayList paramList	 =	new ArrayList();
		ArrayList tempList	 =  null;int q=0;
		//String lStrQuery ="";
		CommonIssueTrackingDAOImpl comobj= new CommonIssueTrackingDAOImpl();
		
		try
		{
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
			   lStrQuery += " ,DATEDIFF(day,t.updated_date,getdate()) AS DiffDate,t.tkt_type_id \n"; 
			else if("A".equals(issueReqStatus))	
				lStrQuery += " ,isnull(convert(varchar(20),t.updated_date,103),'-') AS DiffDate,case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end,t.tkt_type_id \n";
			else if("R".equals(issueReqStatus))
				lStrQuery += " ,isnull(convert(varchar(20),t.updated_date,103),'-')  AS DiffDate,case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end,t.tkt_type_id \n";
			
			if("-1".equals(tkt_type_id))
			{
				lStrQuery +=",mst.tkt_type_name";
			}
			lStrQuery += " ,(select count(sadarem_id) count from tkt_request_master where sadarem_id=t.sadarem_id) count FROM tkt_request_master t WITH(NOLOCK),tbldistrict_details a WITH(NOLOCK), tblmandal_details b  WITH(NOLOCK)\n"+
						" ,tkt_request_tobe_modify_dtls d WITH(NOLOCK),tkt_type_master mst \n"+
						" WHERE t.dist_id=a.district_id AND t.dist_id=b.district_id AND \n"+
						" t.mandal_id=b.mandal_id AND t.tkt_req_id=d.tkt_req_id and t.tkt_type_id = mst.tkt_type_id \n";
						
			if(!tkt_type_id.equals("-1"))
			{
				lStrQuery +=" AND t.tkt_type_id=? ";
			}
			
			if(!districtId.equals("00"))
			{
			 lStrQuery +=		"AND t.dist_id=? ";
			}	
			if(mandalId!=null && mandalId.length()>0 && mandalId !="")
			{
				 lStrQuery += "and t.mandal_id=? ";
			}
			lStrQuery +=	" AND t.status_flag In ('"+statusFlag+"') AND t.is_active='Y' order by t.tkt_req_id\n";
			
		
			if(!tkt_type_id.equals("-1"))
			{tempList = new ArrayList();
                	   tempList.add("S");
   			    		tempList.add(tkt_type_id);
   			    		paramList.add(tempList);   			    		
			}  		
			if(!districtId.equals("00"))
			{
				tempList = new ArrayList();
		    		tempList.add("S");
		    		tempList.add(districtId);
		    		paramList.add(tempList);
			}
			
			if(mandalId!=null && mandalId.length()>0 && mandalId !="")
			{
				tempList = new ArrayList();
	    		tempList.add("S");
	    		tempList.add(mandalId);
	    		paramList.add(tempList);    		
			}   
			
		/*	tempList = new ArrayList();
    		tempList.add("I");
    		tempList.add(statusFlag);
    		paramList.add(tempList); */ 

    		  	System.out.println(tkt_type_id+" - "+districtId+" - "+mandalId+" - "+statusFlag+" - "+lStrQuery);
    		
   			    		pendingIssueDtlsList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);   	
			
			
			
			//  pendingIssueDtlsList = (ArrayList)DataAccess.pickData(lStrQuery, false, false);
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
	public ArrayList getApprRejIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus,String fromdate,String todate)
	{
		ArrayList pendingIssueDtlsList  = new ArrayList();
		ArrayList paramList	 =	new ArrayList();
		ArrayList tempList	 =  null;
		CommonIssueTrackingDAOImpl comobj= new CommonIssueTrackingDAOImpl();
		
		try
		{   //System.out.println("entered");
			//System.out.println(""+mandalId+"lens"+mandalId.length());
			String statusFlag = "";
			
			lStrQuery=	" SELECT X.tkt_req_id,X.sadarem_id,X.district_name,X.Mandal_name\n"+
			            " ,DiffDate,\n"+
			            " remarks,X.tkt_type_id \n";
						 
						if("-1".equals(tkt_type_id))
						{
							lStrQuery +=",X.tkt_type_name \n";
						}	
			             
			  lStrQuery +=       "    ,(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c  WITH(NOLOCK)\n"+
					            " where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = X.tkt_req_id   FOR XML PATH('')) path ,(select count(sadarem_id) count from tkt_request_master where sadarem_id=X.sadarem_id) count  \n"+
							    " from ( \n";
				
					 
				lStrQuery += " SELECT t.tkt_req_id,t.sadarem_id,a.district_name,b.Mandal_name\n"+ 
								" ,isnull(convert(varchar(20),t.updated_date,103),'-') AS DiffDate,\n"+
								"case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end remarks,t.tkt_type_id\n";

						if("-1".equals(tkt_type_id))
						{
							lStrQuery +=",mst.tkt_type_name \n";
						}	
								
						lStrQuery +=" FROM tkt_request_master t WITH(NOLOCK),tbldistrict_details a WITH(NOLOCK), tblmandal_details b WITH(NOLOCK) \n"+
								"  ,tkt_request_tobe_modify_dtls d  WITH(NOLOCK),\n"+
								"  (SELECT distinct role_id, decision_flag FROM tkt_status_flow_mapping fm where\n";
						if(!("-1".equals(tkt_type_id)))
						{
							 lStrQuery +="tkt_type_id=? and"; 
						}
						lStrQuery +="  request_type=? and role_id=?)st,tkt_type_master mst\n"+
								" WHERE t.dist_id=a.district_id AND t.dist_id=b.district_id  and \n"+
								"  t.mandal_id=b.mandal_id AND t.tkt_req_id=d.tkt_req_id AND t.tkt_type_id = mst.tkt_type_id and st.decision_flag=t.status_flag   \n"+
								"  AND t.is_active='Y' AND mst.is_active='Y' and convert(date,t.updated_date,103)"
								+ " between convert(date,?,103) and convert(date,?,103)"; 
                                            
						if(!("-1".equals(tkt_type_id)))
						{
							 lStrQuery +="AND t.tkt_type_id=?"; 
						}

							if(!districtId.equals("00"))
							{
							 lStrQuery +=		"AND t.dist_id=? ";
							}	
							if(mandalId!=null && mandalId.length()>0 && mandalId !="")
							{
								 lStrQuery += "and t.mandal_id=? ";
							}
						lStrQuery  += "UNION  SELECT t.tkt_req_id,t.sadarem_id,a.district_name,b.Mandal_name\n"+ 
								" ,isnull(convert(varchar(20),t.updated_date,103),'-') AS DiffDate,\n"+
								"case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end remarks,t.tkt_type_id\n";
						if("-1".equals(tkt_type_id))
						{
							lStrQuery +=",mst.tkt_type_name \n";
						}	
								
						lStrQuery +=
								" FROM tkt_request_master t WITH(NOLOCK),tbldistrict_details a WITH(NOLOCK), tblmandal_details b WITH(NOLOCK) \n"+
								"  ,tkt_request_tobe_modify_dtls d  WITH(NOLOCK),tkt_request_status_history s,\n"+
								"  (SELECT distinct role_id, decision_flag FROM tkt_status_flow_mapping fm where \n";
								if(!("-1".equals(tkt_type_id)))
								{
									 lStrQuery +="tkt_type_id=? and"; 
								}
						
						 lStrQuery +="  request_type=? and role_id=?)st,tkt_type_master mst\n"+
								" WHERE t.dist_id=a.district_id AND t.dist_id=b.district_id AND t.tkt_req_id=s.tkt_req_id and \n"+
								"  t.mandal_id=b.mandal_id AND t.tkt_req_id=d.tkt_req_id AND t.tkt_type_id = mst.tkt_type_id AND  st.decision_flag=s.status_flag   \n"+
								"   AND t.is_active='Y' AND mst.is_active='Y' and convert(date,t.updated_date,103)"
								+ " between convert(date,?,103) and convert(date,?,103)";  
						 
							if(!("-1".equals(tkt_type_id)))
							{
								 lStrQuery +="AND t.tkt_type_id=?"; 
							}
							if(!districtId.equals("00"))
							{
							 lStrQuery +=		"AND t.dist_id=? ";
							}	
							if(mandalId!=null && mandalId.length()>0 && mandalId !="")
							{
								 lStrQuery += "and t.mandal_id=? ";
							}
						 lStrQuery += "	) X order by X.tkt_req_id";
			 
						 
						 if(!("-1".equals(tkt_type_id)))
							{ tempList = new ArrayList();
				                	   tempList.add("S");
				   			    		tempList.add(tkt_type_id);
				   			    		paramList.add(tempList);   			    		
							}
						    tempList = new ArrayList();
				    		tempList.add("S");
				    		tempList.add(issueReqStatus);
				    		paramList.add(tempList);  
				    		
				    		tempList = new ArrayList();
				    		tempList.add("S");
				    		tempList.add(roleID);
				    		paramList.add(tempList);  
				    		
				    		tempList = new ArrayList();
				    		tempList.add("S");
				    		tempList.add(fromdate);
				    		paramList.add(tempList);  
				    		
				    		tempList = new ArrayList();
				    		tempList.add("S");
				    		tempList.add(todate);
				    		paramList.add(tempList);  
				    		
				    		if(!("-1".equals(tkt_type_id)))
							{
				    			tempList = new ArrayList();
				    			tempList.add("S");
		   			    		tempList.add(tkt_type_id);
		   			    		paramList.add(tempList);
							}
						
						 
				    		if(!districtId.equals("00"))
							{
								tempList = new ArrayList();
						    		tempList.add("S");
						    		tempList.add(districtId);
						    		paramList.add(tempList);
							}
							
				    		if(mandalId!=null && mandalId.length()>0 && mandalId !="")
							{
								tempList = new ArrayList();
					    		tempList.add("S");
					    		tempList.add(mandalId);
					    		paramList.add(tempList);    		
							}   
				    		
				    		 if(!("-1".equals(tkt_type_id)))
								{
				    			  tempList = new ArrayList();
					                	   tempList.add("S");
					   			    		tempList.add(tkt_type_id);
					   			    		paramList.add(tempList);   			    		
								}
							    tempList = new ArrayList();
					    		tempList.add("S");
					    		tempList.add(issueReqStatus);
					    		paramList.add(tempList);  
					    		
					    		tempList = new ArrayList();
					    		tempList.add("S");
					    		tempList.add(roleID);
					    		paramList.add(tempList);  
					    		
					    		tempList = new ArrayList();
					    		tempList.add("S");
					    		tempList.add(fromdate);
					    		paramList.add(tempList);  
					    		
					    		tempList = new ArrayList();
					    		tempList.add("S");
					    		tempList.add(todate);
					    		paramList.add(tempList);  
					    		
					    		if(!("-1".equals(tkt_type_id)))
								{
					    			tempList = new ArrayList();
					    			tempList.add("S");
			   			    		tempList.add(tkt_type_id);
			   			    		paramList.add(tempList);
								}
							
							 
					    		if(!districtId.equals("00"))
								{
									tempList = new ArrayList();
							    		tempList.add("S");
							    		tempList.add(districtId);
							    		paramList.add(tempList);
								}
								
					    		if(mandalId!=null && mandalId.length()>0 && mandalId !="")
								{
									tempList = new ArrayList();
						    		tempList.add("S");
						    		tempList.add(mandalId);
						    		paramList.add(tempList);    		
								} 
				    		  
				    		
				   			    		pendingIssueDtlsList=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  			 
						 
					//	 System.out.println("000>>"+pendingIssueDtlsList);
				   			    	
		 //   System.out.println("lStrQuery22"+lStrQuery); 
			//  pendingIssueDtlsList = (ArrayList)DataAccess.pickData(lStrQuery, false, false);
			
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
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,c.relation_name,a.relation_name,a.relation_teugu_name,a.remarks,\n"+
		         "(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
				 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				 ")from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
				 "tkt_request_master b WITH(NOLOCK),sadarem_gender_relation_type_mapping c WITH(NOLOCK)\n"+
				 "where a.tkt_req_id = b.tkt_req_id and b.gender = c.gender_id and a.relation_type = c.relation_id and a.tkt_req_id=?";
			
				
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);
   			    		
   			    		
   			    		tempList = new ArrayList();
   			    		tempList.add("S");
   			    		tempList.add(reqId);   			    		
   			    		paramList.add(tempList);
   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
   			    		
		
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getRelationIssueData of "+e);
		}
		
		return issuedata;
	}
	
	
	public ArrayList getSadaremDeleteRequestDtls(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
	
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery =   "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.person_sur_name,a.person_name,\n"+
					"isnull(a.person_telugu_name,'-') person_telugu_name,a.remarks,\n"+
					"(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
					"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					")from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),tkt_request_master b  WITH(NOLOCK)\n"+
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
			System.out.println("Exception raised in getNameIssueData of "+e);
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
			
			lStrQuery =   "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.person_sur_name,a.person_name,\n"+
					"isnull(a.person_telugu_name,'-') person_telugu_name,a.remarks,\n"+
					"(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
					"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					")from tkt_request_tobe_modify_dtls a WITH(NOLOCK) ,tkt_request_master b WITH(NOLOCK)\n"+
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
			System.out.println("Exception raised in getNameIssueData of "+e);
		}
		
		return issuedata;
	}
	public ArrayList getFullNameIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		
		
		try
		{
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.person_sur_name,a.person_name,\n"+ 
				"isnull(a.person_telugu_name,'-') person_telugu_name,a.remarks,\n"+
				"(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c  WITH(NOLOCK)\n"+ 
				"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				")from tkt_request_tobe_modify_dtls a WITH(NOLOCK) ,\n"+ 
				 "tkt_request_master b WITH(NOLOCK)\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);	tempList = new ArrayList();		 
            tempList.add("S");
            tempList.add(reqId);
            paramList.add(tempList);	
            
            
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
			System.out.println("issuedata : "+issuedata);
			
		}
		catch(Exception e)
		{
			
			System.out.println("Exception raised in getNameIssueData of "+e);
		}
		
		return issuedata;
	} 
	public ArrayList getPersonStatusIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();

		 
		try
		{
		/*	lStrQuery = "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,\n"+ 
					"a.person_status,a.remarks,a.attach_doc_one,a.attach_doc_two from tkt_request_tobe_modify_dtls(nolock) a ,\n"+ 
				 "tkt_request_master(nolock) b\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id='"+reqId+"'";*/
			
			lStrQuery = "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.person_status,\n"+
				"a.remarks,\n"+
				"(select proof_doc_path+',' from tkt_request_master b  WITH(NOLOCK),tkt_req_proof_details c  WITH(NOLOCK)\n"+ 
				"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				")from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),tkt_request_master b  WITH(NOLOCK)\n"+
				"where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			
			//issuedata = DataAccess.pickData(lStrQuery, false, false);
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);
   			    		
   			    		
   			    		tempList = new ArrayList();
   			    		tempList.add("S");
   			    		tempList.add(reqId);   			    		
   			    		paramList.add(tempList);
   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  	
			
			//System.out.println("))>>"+issuedata);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getPersonStatusIssueData of "+e);
		}
		
		return issuedata;
	}
	public ArrayList getCauseOfDisabilityIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		
		try
		{
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.cause_of_disability,\n"+ 
					"a.remarks from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
				 "tkt_request_master b WITH(NOLOCK)\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			
		
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);

   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
   			 	//	System.out.println("))>>"+issuedata);
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getNameIssueData of "+e);
		}
		
		return issuedata;
	}
	
	public ArrayList getAadharIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		try
		{
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.aadhar_id,\n"+ 
				 "a.remarks,(select proof_doc_path+',' from tkt_request_master b  WITH(NOLOCK),tkt_req_proof_details c  WITH(NOLOCK)\n"+ 
				 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				 "),aadhar_sadarem_id from tkt_request_tobe_modify_dtls a WITH(NOLOCK) ,\n"+ 
				 "tkt_request_master b WITH(NOLOCK) \n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			
	
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);
   			    		
   			    		tempList	= new ArrayList();
   			    		tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);

   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
			
   			    		//System.out.println("-->>"+issuedata);
			
			
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
		
		try
		{
			lStrQuery="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,d.district_name,m.mandal_name,\n"+
					"v.village_name,h.habitation_name,a.house_no,a.pin_code,a.remarks,(select proof_doc_path+',' from tkt_request_master  b  WITH(NOLOCK),tkt_req_proof_details c  WITH(NOLOCK)\n"+ 
					"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					") from \n"+
					"tkt_request_tobe_modify_dtls a  WITH(NOLOCK),tbldistrict_details d  WITH(NOLOCK),tblmandal_details m WITH(NOLOCK),\n"+
					"tblvillage_details v WITH(NOLOCK),tblhabitation_details h WITH(NOLOCK),\n"+
					"tkt_request_master b WITH(NOLOCK)\n"+
					"where a.tkt_req_id = b.tkt_req_id and a.dist_id=d.district_id \n"+
					"and a.dist_id=m.district_id and a.mandal_id=m.mandal_id and\n"+
					" a.dist_id=v.district_id and a.mandal_id=v.mandal_id and a.village_id=v.village_id\n"+
					" and  a.dist_id=h.district_id and a.mandal_id=h.mandal_id and a.village_id=h.village_id\n"+
					" and a.hab_id=h.habitation_id and\n"+
					" a.tkt_req_id=?";
			
		//	issuedata = DataAccess.pickData(lStrQuery, false, false);
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);
   			    		
   			    		tempList	= new ArrayList();
   			    		tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);

   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
				
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getAddressIssueData of "+e);
		}
		
		return issuedata;
	} 
	
	public ArrayList getDistrictAddressIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		try
		{
			lStrQuery="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,d.district_name,m.mandal_name,\n"+
					"v.village_name,h.habitation_name,a.house_no,a.pin_code,a.remarks,(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
					"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					") from \n"+
					"tkt_request_tobe_modify_dtls a  WITH(NOLOCK),tbldistrict_details d WITH(NOLOCK),tblmandal_details m WITH(NOLOCK),\n"+
					"tblvillage_details v WITH(NOLOCK),tblhabitation_details h WITH(NOLOCK),\n"+
					"tkt_request_master b\n"+
					"where a.tkt_req_id = b.tkt_req_id and a.dist_id=d.district_id \n"+
					"and a.dist_id=m.district_id and a.mandal_id=m.mandal_id and\n"+
					" a.dist_id=v.district_id and a.mandal_id=v.mandal_id and a.village_id=v.village_id\n"+
					" and  a.dist_id=h.district_id and a.mandal_id=h.mandal_id and a.village_id=h.village_id\n"+
					" and a.hab_id=h.habitation_id and\n"+
					" a.tkt_req_id=?";
			
		//	issuedata = DataAccess.pickData(lStrQuery, false, false);
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);
   			    		
   			    		tempList	= new ArrayList();
   			    		tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);

   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
				
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getAddressIssueData of "+e);
		}
		
		return issuedata;
	} 
	
	public ArrayList getPhotoChangeIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
	
		
		try
		{
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.photo_preference,\n"+ 
					"a.remarks,a.photo_path ,(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
					"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					")  from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
				 "tkt_request_master b WITH(NOLOCK)\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			
			//issuedata = DataAccess.pickData(lStrQuery, false, false);
			
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);
   			    		
   			    		tempList	= new ArrayList();
   			    		tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);

   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
				
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getNameIssueData of "+e);
		}
		
		return issuedata;
	}
	public ArrayList getGenderIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		
		try
		{
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.gender,\n"+ 
				 "a.remarks,(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
				"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				") from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
				 "tkt_request_master b WITH(NOLOCK)\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			
			//issuedata = DataAccess.pickData(lStrQuery, false, false);
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);
   			    		
   			    		tempList	= new ArrayList();
   			    		tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);

   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
				
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getNameIssueData of "+e);
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
					"(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
					 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					 ")\n"+ 
					 " from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
					 "tkt_request_master b WITH(NOLOCK),sadarem_education_master c WITH(NOLOCK)\n"+
					 "where a.tkt_req_id = b.tkt_req_id  and a.edu_id = c.edu_id and a.tkt_req_id=?";
				 
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
			System.out.println("Exception raised in getQualificationIssueData of "+e);
		}
		
		return issuedata;
	}
	public ArrayList getAgeIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		try
		{
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,CONVERT(VARCHAR,a.dob,105),\n"+ 
				 "a.remarks,(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
				 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				 "),DATEDIFF(year,a.dob,GETDATE()) from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
				 "tkt_request_master b WITH(NOLOCK)\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
		//	System.out.println("age qry"+lStrQuery);
			
			//issuedata = DataAccess.pickData(lStrQuery, false, false);
			
			ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();

                	   tempList.add("S");
   			    		tempList.add(reqId);
   			    		paramList.add(tempList);
   			    		
   			    		tempList	= new ArrayList();
   			    		tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);

   		  	
   			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
					
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getNameIssueData of "+e);
		}
		
		return issuedata;
	}
	 
	public ArrayList getAppellateIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,CASE WHEN APELATE_CATEGORY='D' then 'Doubtful Case' WHEN APELATE_CATEGORY='F'then 'Fraud Case'WHEN APELATE_CATEGORY='T' then 'Third Time Reassessment' end,\n"+ 
				 "a.remarks,(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
				 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id =? FOR XML PATH('')\n"+
				 ") from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
				 "tkt_request_master b WITH(NOLOCK)\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
			
		//	System.out.println("appl qry"+lStrQuery);
			
		
			tempList.add("S");
			tempList.add(reqId);
			paramList.add(tempList);
			
			tempList  = new ArrayList();
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
	
	public ArrayList getPartBreEntryIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		
		
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
			lStrQuery ="select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,CASE WHEN APELATE_CATEGORY='I' then 'Invalid SADAREM ID' WHEN APELATE_CATEGORY='M'then 'Mismatch in Disability Details' WHEN APELATE_CATEGORY='O'then 'Special Case(Check thoroughly)' end,\n"+ 
				 "a.remarks,(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
				 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
				 ") from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
				 "tkt_request_master b WITH(NOLOCK)\n"+
				 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id= ? ";
	//		System.out.println("partbreentry qry \n"+lStrQuery);
			
		
			tempList.add("S");
			tempList.add(reqId);
			paramList.add(tempList);
			
			tempList  = new ArrayList();
			tempList.add("S");
			tempList.add(reqId);
			paramList.add(tempList);
			
			issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getPartBreEntryIssueData of "+e);
		}
		
		return issuedata;
	}
	
	public String getPhotoPath(String reqId)
	{
		String qry="",path="";
		ArrayList issuedata = new ArrayList();
		 try
		 {	
		lStrQuery = "select photo_path from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();

						tempList.add("S");
			    		tempList.add(reqId);
			    		paramList.add(tempList);
			    		


		  	
			    		issuedata=(ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);  
			if(issuedata!=null && issuedata.size()>0)
			{
			    		path = (String)issuedata.get(0);
			}   		
	//	System.out.println("path-->>"+path);
		
		//   path = DataAccess.getReturnResult(qry);
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception raised in getPhotoPath"+e);
		 }
		return path;
		
	}
	
	public String updateApprovedStatusFlag(HashMap IssueApprovalDtls)
	{
		Connection con = null;
	   int basicInsertStatus=0,modifyStatus=0,result =0;
	   String lStrQuery =  "",status="";
	   String statusMsg = "";
	   CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	   MultipleGrievanceDAO comObj1 = new MultipleGrievanceDAOImpl();
	   String flag, reqId, loginId, statusRemarks,issueType, isFinalFlag, docVerifyStatus,distName,sadaremid,selIssueType;
	   int roleId;
	   ArrayList smsdtls = new ArrayList();
	   CommonDAO commonObj = new CommonDAOImpl();
	   
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
	   selIssueType   = (String)IssueApprovalDtls.get("selIssueType");
	   String ipaddress   = CommonUtility.checkNullObject((String)IssueApprovalDtls.get("ipaddress"));
	   
	  try
        {
	       con = DBConnection.getConnection();
	       con.setAutoCommit(false);
		    PreparedStatement lPstmt = null;
		   
		    
		    lStrQuery =  "insert into tkt_request_status_history(tkt_req_id,status_flag,doc_verify_status,status_remarks,modified_ip_address,modified_by,modified_date)"
		    		+ "select tkt_req_id,status_flag,doc_verify_status,status_remarks,ISNULL(update_ip_address,req_ip_address),updated_by,updated_date from tkt_request_master where tkt_req_id=?";	
		    
		    			    	
		    	
		    	lPstmt = con.prepareStatement(lStrQuery);
		    
		     lPstmt.setString(1,reqId );
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
			    	  if(isFinalFlag.equals("Y"))
						 {
							if(selIssueType.equals(CommonConstants.NAMECHANGEISSUE_ID))
							{
								 result = updateFinalNameIssueDtls(reqId,flag,con);
							}
							else if(selIssueType.equals(CommonConstants.RELATIONNAMECHANGEISSUE_ID))
							{
								 result = updateFinalRelationIssueDtls(reqId,flag,con);	
							}
							else if(selIssueType.equals(CommonConstants.ADDRESSCHANGEISSUE_ID))
							{
								 result = updateFinalAddressIssueDtls(reqId,flag,con);	
							}
							else if(selIssueType.equals(CommonConstants.DistrictAddressChange))
							{
								 result = updateFinalAddressIssueDtls(reqId,flag,con);	
							}
							else if(selIssueType.equals(CommonConstants.CAUSEOFDISABILITYCHANGEISSUE_ID))
							{
								 result = updateFinalcauseOfDisabilityIssueDtls(reqId,flag,con);	
							}
							else if(selIssueType.equals("S008"))
							{
								 result = updateFinalaadharIssueDtls(reqId,flag,con);	
							}
							else if(selIssueType.equals(CommonConstants.SADAREMDELETION_ID))
							{
								 result = updateFinalSadaremDeleteDtls(reqId,flag,con);	
							}
							else if(selIssueType.equals(CommonConstants.FULLNAMECHANGEISSUE_ID))
							{
								 result = updateFinalFullNameIssueDtls(reqId,flag,con);	
							}
							else if(selIssueType.equals(CommonConstants.MANUALPHOTOISSUE_ID))
							{
								 result = uploadManualPhoto( reqId, sadaremid, distName);
							}
							else if(selIssueType.equals(CommonConstants.PERSONSTATUSISSUE_ID))  
							{
								 result = updatePersonStatusIssueDtls(reqId,flag,con);
							} 
							else if(selIssueType.equals("S005"))
							{
								 result = updatePhotoDtls( reqId, distName, sadaremid);
							}
							else if(selIssueType.equals(CommonConstants.GENDERISSUE_ID))
							{
								result = updateFinalGenderIssueDtls(reqId, distName, con);
							}
							else if(selIssueType.equals(CommonConstants.AGEISSUE_ID))
							{
								result = updateFinalAgeIssueDtls(reqId, distName, con);
							}
							else if(selIssueType.equals(CommonConstants.QUALIFICATIONISSUE_ID))
							{
								result = updateFinalQualificationIssueDtls(reqId, distName, con);
							}
							else if(selIssueType.equals(CommonConstants.APPELLATEISSUE_ID))
							{
								result = updateFinalAppelateIssueDtls(reqId,sadaremid, distName, con);
							}
							else if(selIssueType.equals(CommonConstants.PARTBREENTRYISSUE_ID))  
							{
								 result = updatePartBreEntryIssueDtls(reqId,flag,con);
							} 
							else if(selIssueType.equals(CommonConstants.MULTIPLEGRIEVANCEISSUE_ID))  
							{
								 result = comObj1.updateMultiIssueDtls(reqId,flag,con);
							} 
					    	  if(result>0)
					    	  {
					    		  	con.commit();
						    	    statusMsg = comObj.getFlagDesc(flag);
						    	    SendSMS(reqId,loginId,"approved.");			    	
					    	  }
					    	  else
						      {
						    	  statusMsg = "1. Error occured while updating request.Please try later !!";
						    	  con.rollback();
						      }
				      }
				    	  else
				    	  {
				    		  con.commit();
				    		  statusMsg = comObj.getFlagDesc(flag);
				    	  }
				    	 
						     
				      }
				      else
				      {
				    	  statusMsg = "2. Error occured while updating request.Please try later !!";
				    	  con.rollback();
				      }
			    }
			    else
			    {
			    	statusMsg = "3. Error occured while updating request.Please try later !!";
			    	con.rollback();
			    }
			     
			  }
		    catch(Exception e)
		    {
				    try 
				    {
				    	try
				    	{
				    		final Logger log = Logger.getLogger(AppelleteProofsReportDAOImpl.class);
				    		log.info("Exception raised in updateApprovedStatusFlag  @ getRecords of "+e);
							int exResult = ExceptionDAO.saveException(null, e.toString(), "updateApprovedStatusFlag", "CommonIssueApprovalDAOImpl", "DataBase");
						} 
				    	catch (SADAREMDBException e1) 
				    	{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    	statusMsg = "4. Error occured while updating request.Please try later !!";
						con.rollback();
					} catch (SQLException e1) 
					{
						 
						e1.printStackTrace();
					}
			  e.printStackTrace();
		    }
	     finally
	     {
	    	 
	    	try
	    	{
				con.close();
			} 
	    	catch (SQLException e) 
	    	{
				e.printStackTrace();
			} 
	     }
	  System.out.println("--------statusMsg-------"+statusMsg);
	return statusMsg;
     
	}
	
	

	public String updateRejectedStatusFlag(HashMap IssueApprovalDtls)
	{
		Connection con = null;
		String flag, reqId, loginId, statusRemarks, docVerifyStatus;
	   int basicInsertStatus=0,modifyStatus=0,result =0;
	   String lStrQuery =  "",status="";
	   String statusMsg = "";
	   CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	   ArrayList smsdtls = new ArrayList();
	   flag 		=   (String) IssueApprovalDtls.get("decisionFlag");
	   reqId 		=   (String) IssueApprovalDtls.get("reqId");
	   loginId 		=	(String) IssueApprovalDtls.get("loginId");
	   statusRemarks=   (String) IssueApprovalDtls.get("newremarks");
	   docVerifyStatus = (String) IssueApprovalDtls.get("docVerifyStatus");
	   String ipaddress = CommonUtility.checkNullObject((String) IssueApprovalDtls.get("ipaddress"));
	   CommonDAO commonObj = new CommonDAOImpl();
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
			    	con.commit();
		    	    statusMsg = comObj.getFlagDesc(flag); 
		    		SendSMS(reqId,loginId,"rejected.");	 
			      }
			      else
			      {
			    	  statusMsg = "Error occured while updating request.Please try later !! 1)";
			    	  con.rollback();
			      }
		    }
		    else
		    {
		    	statusMsg = "Error occured while updating request.Please try later !! 2)";
		    }
		     
		  }
		    catch(Exception e)
		    {
				    try 
				    {
				    	statusMsg = "Error occured while updating request.Please try later !! 3)";
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
				ArrayList tempList  = new ArrayList();
				
				lStrQuery = " select * from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
				
			
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
		
		public int updateFinalRelationIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "";
			ArrayList relIssueDtls = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
				
				
				   PreparedStatement lPstmt = null;
				    
				   lStrQuery = "select sadarem_id,relation_type,relation_name,relation_teugu_name from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
			
				  
					tempList.add("S");
					tempList.add(reqId);
					paramList.add(tempList);
				   
			   relIssueDtls = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			   if(relIssueDtls.size() > 0)
			   {
				   relIssueDtls = (ArrayList)relIssueDtls.get(0);
				   
				   if(relIssueDtls.size()>0)
				   {
					   
					   lStrQuery = "update tblperson_personal_details set relationship=?,relation_name=?,fathername_telugu=?,updated_date=getdate() where person_code=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					   lPstmt.setString(1,(String)relIssueDtls.get(1) );
					   lPstmt.setString(2,(String)relIssueDtls.get(2) );
					   lPstmt.setString(3,(String)relIssueDtls.get(3) );
					   lPstmt.setString(4,(String)relIssueDtls.get(0) );
					   
					     result = lPstmt.executeUpdate();
					     
					    if(result >0) 
					    {
					    	lStrQuery = "update tblperson_personal_details_new set relationship=?,relation_name=?,fathername_telugu=?,updated_date=getdate() where person_code=?";
							   lPstmt = con.prepareStatement(lStrQuery);
							   lPstmt.setString(1,(String)relIssueDtls.get(1) );
							   lPstmt.setString(2,(String)relIssueDtls.get(2) );
							   lPstmt.setString(3,(String)relIssueDtls.get(3) );
							   lPstmt.setString(4,(String)relIssueDtls.get(0) );
							   
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
		public int updateFinalQualificationIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "";
			   ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			ArrayList educationIssueDtls = new ArrayList();
			//Connection con = null;
			PreparedStatement lPstmt = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				 //  con = DBConnection.getConnection();
			     //  con.setAutoCommit(false);
				   
				    
		    	lStrQuery =  "select sadarem_id,edu_id from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
			
			//   educationIssueDtls = (ArrayList)DataAccess.pickData(lStrQuery, false, false);
				tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				educationIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			   
			   
			   if(educationIssueDtls.size() > 0)
			   {
				   educationIssueDtls = (ArrayList)educationIssueDtls.get(0);
				   
				   if(educationIssueDtls.size()>0)
				   {
					   
					   lStrQuery =  "update tblperson_personal_details set Education=?,updated_date=getdate() where person_code=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					   lPstmt.setString(1,(String)educationIssueDtls.get(1) );
					   lPstmt.setString(2,(String)educationIssueDtls.get(0) );
					   
					     result = lPstmt.executeUpdate();
					     
					    if(result >0) 
					    {
					    	 lStrQuery =  "update tblperson_personal_details_new set Education=?,updated_date=getdate() where person_code=?";
							   lPstmt = con.prepareStatement(lStrQuery);
							   lPstmt.setString(1,(String)educationIssueDtls.get(1) );
							   lPstmt.setString(2,(String)educationIssueDtls.get(0) );
							   
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
			 finally
		      {
		        
		         if(lPstmt!=null)
		         {
		             DBConnection.closeStatement(lPstmt);
		             lPstmt=null;
		         }
		         try { lPstmt.close(); } catch(Exception e) {}
		       
		       } 
			
			return result;
		}
		public int updateFinalAddressIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "";
			ArrayList addrIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				 //  con = DBConnection.getConnection();
			    //   con.setAutoCommit(false);
				   PreparedStatement lPstmt = null; 
				   CommonDAO comObj1 = new CommonDAOImpl();
				  
				    
		    	lStrQuery =  "select sadarem_id,dist_id,mandal_id,village_id,hab_id,house_no,pin_code from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
			
		  
		    	
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				addrIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    	
		    	
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
		public int updateFinalNameIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "";
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			ArrayList nameIssueDtls = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				    
		    	lStrQuery =  "select sadarem_id,person_sur_name,person_name,person_telugu_name from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
			
	
		    	
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				nameIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		 
		    	
		    	
		    	
		    	
			   if(nameIssueDtls.size() > 0)
			   {
				   nameIssueDtls = (ArrayList)nameIssueDtls.get(0);
				   
				   if(nameIssueDtls.size()>0)
				   {
					   
					   lStrQuery =  "update tblperson_personal_details set First_Name=?,Personname_Telugu=?,updated_date=getdate() where person_code=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					//   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
					   lPstmt.setString(1,(String)nameIssueDtls.get(2) );
					   lPstmt.setString(2,(String)nameIssueDtls.get(3) );
					   lPstmt.setString(3,(String)nameIssueDtls.get(0) );
					   
					     result = lPstmt.executeUpdate();
					     
					    if(result >0) 
					    {
					    	 lStrQuery =  "update tblperson_personal_details_new set First_Name=?,Personname_Telugu=? where person_code=?";
							   lPstmt = con.prepareStatement(lStrQuery);
						//	   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
							   lPstmt.setString(1,(String)nameIssueDtls.get(2) );
							   lPstmt.setString(2,(String)nameIssueDtls.get(3) );
							   lPstmt.setString(3,(String)nameIssueDtls.get(0) );
							   
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
		
		public int updateFinalGenderIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "";
			ArrayList nameIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
				
			
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				   
				   PreparedStatement lPstmt = null;
				    
		    	lStrQuery =  "select sadarem_id,gender from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
			
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				nameIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    	
		    	
		    	
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
		
		public int updateFinalAppelateIssueDtls(String reqId,String sadaremId,String flag,Connection con)
		{
			String statusMsg = "",appellateMode="";
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			ArrayList nameIssueDtls = new ArrayList();
			//Connection con = null;
			int result=0,result1=0;
			int count1=0,count2=0,count3=0,count4=0;
			int category=1,count=1;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			ArrayList details = new ArrayList();
			String HoldCase = "";
			try
			{
				  lStrQuery =  "select categoryid,categorycount,view_edit_mode from tblperson_personal_details  WITH(NOLOCK) where person_code=?";
				  
				  tempList.add("S");
					tempList.add(sadaremId);
					paramList.add(tempList);
					
					details = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			    
				
				 //  details = DataAccess.pickData(lStrQuery, false, false);
				   
				   if(details.size()>0)
				   {
					   details = (ArrayList) details.get(0);
					   if(details.size()>0)
					   {
						   category = Integer.parseInt(CommonUtility.checkNullObj(details.get(0)));
						   count    = Integer.parseInt(CommonUtility.checkNullObj(details.get(1)));
						   HoldCase = CommonUtility.checkNullObj(details.get(2));
					   }
				   }
				
				   PreparedStatement lPstmt = null;
				   if(category==CommonConstants.APPELLATE_CATEGORY_ID)
				   {
					   lStrQuery =  "update tblperson_personal_details set view_edit_mode='APA',person_status='Eligible',categoryid=4,categorycount=categorycount+1,updated_date=getdate() where person_code=? and status='Active' and view_edit_mode in ('View','APA')";
				   }
				   else
				   {
					   lStrQuery =  "update tblperson_personal_details set view_edit_mode='APA',person_status='Eligible',categoryid=4,categorycount=1,updated_date=getdate() where person_code=? and status='Active' and view_edit_mode in ('View','APA') ";
				   }   
				       lPstmt = con.prepareStatement(lStrQuery);
					    lPstmt.setString(1,sadaremId);
					   result1 = lPstmt.executeUpdate();
					     
					    if(result1 >0) 
					    {
					    	lStrQuery =  "select count(*) from tblPerson_Disability_Details where person_code='"+sadaremId+"' and status='Active'";
					    	 count1 = Integer.parseInt(CommonUtility.checkNullObj(DataAccess.getReturnResult(lStrQuery)));
					    	 
					    	 lStrQuery =  "select count(*) from tblPerson_Disability_TotalValue_Details where person_code='"+sadaremId+"' and status='Active'";
					    	 count2 = Integer.parseInt(CommonUtility.checkNullObj(DataAccess.getReturnResult(lStrQuery)));
					    	 
					    	 lStrQuery =  "select count(*) from tblRejectPerson_Details where person_code='"+sadaremId+"' and status='Active'";
					    	 count3 = Integer.parseInt(CommonUtility.checkNullObj(DataAccess.getReturnResult(lStrQuery)));
					    	 
					    	 lStrQuery =  "select count(*) from AppellateAuthorityandTemporary_RegistrationDetails where person_code='"+sadaremId+"'";
					    	 count4 = Integer.parseInt(CommonUtility.checkNullObj(DataAccess.getReturnResult(lStrQuery)));
					    	 
					    	 lStrQuery =  "select vieweditmode from AppellateAuthorityandTemporary_RegistrationDetails where person_code='"+sadaremId+"' and vieweditmode='Edit'";
					    	 appellateMode = CommonUtility.checkNullObj(DataAccess.getReturnResult(lStrQuery));
					    	
					    	if(((count1>0&&count2>0)|| count3>0) || count4>0)
					    	{
					    		if(count1>0&&count2>0)
					    		{
					    	    lStrQuery =  "update tblPerson_Disability_Details set status='Inactive',updated_date=getdate() where person_code=? and status='Active' ";
							    lPstmt = con.prepareStatement(lStrQuery);
						        lPstmt.setString(1,sadaremId);
							    result = lPstmt.executeUpdate();
								   if(result>0)
								   {
									   lStrQuery =  "update tblPerson_Disability_TotalValue_Details set status='Inactive',updated_date=getdate() where person_code=? and status='Active' ";
									   lPstmt = con.prepareStatement(lStrQuery);
								       lPstmt.setString(1,sadaremId);
									   result = lPstmt.executeUpdate(); 
								   }
					    	    }
					    		
					    		if(count3>0)
					    		{
					    			 lStrQuery =  "update tblRejectPerson_Details set status='Inactive',updated_date=getdate() where person_code=? and status='Active' ";
									   lPstmt = con.prepareStatement(lStrQuery);
								       lPstmt.setString(1,sadaremId);
									   result = lPstmt.executeUpdate(); 
					    		}
					    		if(count4>0)
						    	{ 
						    			if(appellateMode.equalsIgnoreCase("edit"))
						    			{
						    			    lStrQuery =  "update AppellateAuthorityandTemporary_RegistrationDetails set status='Inactive',vieweditmode='APA',updateddate=getdate() where person_code=? and vieweditmode='Edit' ";
						    			}
						    			else
						    			{
						    				lStrQuery =  "update AppellateAuthorityandTemporary_RegistrationDetails set status='Inactive',updateddate=getdate() where person_code=? ";
						    			}
										   lPstmt = con.prepareStatement(lStrQuery);
									       lPstmt.setString(1,sadaremId);
										   result = lPstmt.executeUpdate(); 
						    	}
					    	}
					    	else if(HoldCase!=null && HoldCase.length()>0 && HoldCase.trim().equals("APA"))
					    	{
					    		result=1;
					    	}
				    	  else
				    		{
				    			result=0;
				    		} 
					     }
					    else
			    		{
			    			result=0;
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
			String statusMsg = "";
			ArrayList nameIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				    
		    	lStrQuery =  "select sadarem_id,dob from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
		    	
		    	
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				nameIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    	
		    	
		  
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
		
		public int updatePersonStatusIssueDtls(String reqId,String flag,Connection con)
		{
			String lStrQuery="";
			String statusMsg = "";
			ArrayList nameIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			
			int result=0;  int i = 0; 
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				  
				   PreparedStatement lPstmt = null;
				 
		    	lStrQuery =  "select sadarem_id,person_status from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
		
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				nameIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    
		    	
		    
			   if(nameIssueDtls.size() > 0)
			   {
				   nameIssueDtls = (ArrayList)nameIssueDtls.get(0);
				   
				  
			        CallableStatement calstmt = null;
			        ResultSet rs = null;
			       
			        try {
			        	
			            calstmt = con.prepareCall("{Call PRO_SADAREM_DEAD_TO_LIVE_VICEVERSA(?,?)}");
			            calstmt.setString(1, (String)nameIssueDtls.get(0));
			            calstmt.setString(2, (String)nameIssueDtls.get(1));
			            i = calstmt.executeUpdate();
			            calstmt.close();
			            
			           
			            
			        } catch (SQLException sqlEx) {
			        	result=0;
			         sqlEx.printStackTrace();		       
			        
			        }  
				   
				}
			   
			   
			}
			catch(Exception e)
			{
				result=0;
				e.printStackTrace();
			}
			 
			
			
			return i;
		}
		
		public int updatePartBreEntryIssueDtls(String reqId,String flag,Connection con)
		{
			//String lStrQuery="";
			String statusMsg = "";
			ArrayList nameIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			
			int result=0;  int i = 0; 
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				  
				   PreparedStatement lPstmt = null;
				 
		    	lStrQuery =  "select sadarem_id from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
		    	
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				nameIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    
		    	
		   
			   if(nameIssueDtls.size() > 0)
			   {
				  // nameIssueDtls = (ArrayList)nameIssueDtls.get(0);
				   
				  
			        CallableStatement calstmt = null;
			        ResultSet rs = null;
			       
			        try {
			        	
			            calstmt = con.prepareCall("{Call PRO_SADAREM_PARTB_RE_ENTRY_PROVISION(?)}");
			            calstmt.setString(1, (String)nameIssueDtls.get(0));
			          
			            i = calstmt.executeUpdate();
			            calstmt.close();
			            
			           
			            
			        } catch (SQLException sqlEx) {
			        	result=0;
			         sqlEx.printStackTrace();		       
			        
			        }  
				   
				}
			   
			   
			}
			catch(Exception e)
			{
				result=0;
				e.printStackTrace();
			}
			 
			
			
			return i;
		}
		
		public int updateFinalFullNameIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "";
			ArrayList nameIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();

			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				
				   PreparedStatement lPstmt = null;
				    
		    	lStrQuery =  "select sadarem_id,person_sur_name,person_name,person_telugu_name from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
			
		    
		    	
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				nameIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    
		 	
		    	
		    	
			   if(nameIssueDtls.size() > 0)
			   {
				   nameIssueDtls = (ArrayList)nameIssueDtls.get(0);
				   
				   if(nameIssueDtls.size()>0)
				   {
					   
					   lStrQuery =  "update tblperson_personal_details set Surname=?,First_Name=?,Personname_Telugu=?,updated_date=getdate() where person_code=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
					   lPstmt.setString(2,(String)nameIssueDtls.get(2) );
					   lPstmt.setString(3,(String)nameIssueDtls.get(3) );
					   lPstmt.setString(4,(String)nameIssueDtls.get(0) );
					   
					     result = lPstmt.executeUpdate();
					     
					    if(result >0) 
					    {
					    	 lStrQuery =  "update tblperson_personal_details_new set Surname=?,First_Name=?,Personname_Telugu=? where person_code=?";
							   lPstmt = con.prepareStatement(lStrQuery);
							   lPstmt.setString(1,(String)nameIssueDtls.get(1) );
							   lPstmt.setString(2,(String)nameIssueDtls.get(2) );
							   lPstmt.setString(3,(String)nameIssueDtls.get(3) );
							   lPstmt.setString(4,(String)nameIssueDtls.get(0) );
							   
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
		public int updateFinalcauseOfDisabilityIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "";
			ArrayList causeIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			//Connection con = null;
			int result=0;
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				    
		    	lStrQuery =  "select sadarem_id,cause_of_disability from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
		    	
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				causeIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    
		 
		    	
			   if(causeIssueDtls.size() > 0)
			   {
				   causeIssueDtls = (ArrayList)causeIssueDtls.get(0);
				   
				   if(causeIssueDtls.size()>0)
				   {
					   
					   lStrQuery =  "update tblPerson_Disability_Details    set Diagnosis_of_Disability=?,updated_date=getdate() where person_code=?";
					   lPstmt = con.prepareStatement(lStrQuery);
					
					   lPstmt.setString(1,(String)causeIssueDtls.get(1) );
					   lPstmt.setString(2,(String)causeIssueDtls.get(0) );
					     result = lPstmt.executeUpdate();
					  
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
		public int updateFinalSadaremDeleteDtls(String reqId,String flag,Connection con)
		{
			int result=0;
			
			
			String statusMsg = "";
			ArrayList causeIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
				String sadarem_id =null;
				String remarks = null;
			//Connection con = null;
			
			String dupSadaremId="";
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				   
		    	lStrQuery =  "select sadarem_id,remarks from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
		    	
		    	PreparedStatement pstmt =null;
		    	ResultSet rs = null;
		    	
		    	pstmt = con.prepareStatement(lStrQuery);
		    	pstmt.setString(1, reqId);
		    	rs = pstmt.executeQuery();
		    	if(rs!=null && rs.next())
		    	{
		    		sadarem_id = rs.getString(1);
		    		remarks = rs.getString(2);
		    	}
				
				/*causeIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    	causeIssueDtls = (ArrayList) causeIssueDtls.get(0);
		    	 sadarem_id  = ""+causeIssueDtls.get(0);		    	
		    	 remarks = ""+causeIssueDtls.get(1);*/
		    														
		    	CallableStatement calstmt = null;
		    	calstmt = con.prepareCall("{Call PRO_SADAREM_DELETION_OF_CERTI(?,?,?)}");
		    	calstmt.setString(1,sadarem_id);            
		    	calstmt.setString(2,""+ "Y");
		    	calstmt.setString(3, remarks);
		    	result = calstmt.executeUpdate();		    											            
		    	calstmt.close();
		    	pstmt.close();            
		    	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}			
			
			return result;			
		}
	 	
		public int updateFinalaadharIssueDtls(String reqId,String flag,Connection con)
		{
			String statusMsg = "";
			ArrayList causeIssueDtls = new ArrayList();
			  ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
			//Connection con = null;
			int result=0;
			String dupSadaremId="";
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			try
			{
				   //con = DBConnection.getConnection();
			       //con.setAutoCommit(false);
				   PreparedStatement lPstmt = null;
				    
		    	lStrQuery =  "select sadarem_id,aadhar_id,aadhar_sadarem_id from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
		    	
		    	tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				
				causeIssueDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		 
			
			   if(causeIssueDtls.size() > 0)
			   {
				   causeIssueDtls = (ArrayList)causeIssueDtls.get(0);
				   
				   if(causeIssueDtls.size()>0)
				   {
					    dupSadaremId = CommonUtility.checkNullObj(causeIssueDtls.get(2));
					    if(dupSadaremId!=null && dupSadaremId.length()>1)
					    {
					    	lStrQuery =  "update tblPerson_personal_details set proof_id=null,proofdoc_type=null,updated_date=getdate() where person_code=?";
							   lPstmt = con.prepareStatement(lStrQuery);
							   lPstmt.setString(1,dupSadaremId);
							   result = lPstmt.executeUpdate();
							   
							   if(result >0) 
								    {
							    	 lStrQuery =  "update tblPerson_personal_details_new set proof_id=null,proofdoc_type=null,updated_date=getdate() where person_code=?";
										   lPstmt = con.prepareStatement(lStrQuery);
										
										   lPstmt.setString(1,dupSadaremId);
										    result = lPstmt.executeUpdate();
									 }
					    }
					    else
					    {
					    	result = 1;
					    }
					   
					    if(result>0)
					    {	
						   lStrQuery =  "update tblPerson_personal_details set proof_id=?,proofdoc_type='Adhaar Card',updated_date=getdate() where person_code=?";
						   lPstmt = con.prepareStatement(lStrQuery);
						
						   lPstmt.setString(1,(String)causeIssueDtls.get(1) );
						   lPstmt.setString(2,(String)causeIssueDtls.get(0) );
						     result = lPstmt.executeUpdate();
						     if(result >0) 
							    {
						    	 lStrQuery =  "update tblPerson_personal_details_new set proof_id=?,proofdoc_type='Adhaar Card',updated_date=getdate() where person_code=?";
									   lPstmt = con.prepareStatement(lStrQuery);
									
									   lPstmt.setString(1,(String)causeIssueDtls.get(1));
									   lPstmt.setString(2,(String)causeIssueDtls.get(0));
									     result = lPstmt.executeUpdate();
								 }
						     
						     	if(result >0) 
						     	{
						     			lStrQuery =  "update sadarem_without_proof_request_master set proof_id=? where sadarem_id=?";
						     			lPstmt = con.prepareStatement(lStrQuery);
									
						     			lPstmt.setString(1,(String)causeIssueDtls.get(1));
						     			lPstmt.setString(2,(String)causeIssueDtls.get(0));
									    lPstmt.executeUpdate();
								 }
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
			 String path="";
	
			try
			{
				
				ArrayList paramList = new ArrayList();
				ArrayList tempList  = new ArrayList();
				
			 lStrQuery = "select attach_doc_one from tkt_request_tobe_modify_dtls where tkt_req_id=?";
			 
			  
				//tempList  = new ArrayList();
			 
			 
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
					ArrayList tempList  = new ArrayList();
				
				try
				{
					lStrQuery = " SELECT fm.tkt_flag_desc,ISNULL(status_remarks,'-') Remarks,convert(varchar(19),modified_date) date,\n"+
					      "modified_by username,ISNULL(RN.PERSONNAME,MODIFIED_BY) personname,ISNULL(RN.ROLE_NAME,'-') role  \n"+
						"	FROM (\n"+ 
						"	 SELECT TKT_REQ_ID,STATUS_REMARKS,STATUS_FLAG,MODIFIED_BY,\n"+
						"	 MODIFIED_DATE FROM TKT_REQUEST_STATUS_HISTORY WITH(NOLOCK) \n"+
						"	 WHERE TKT_REQ_ID=?\n"+
						"	 UNION  \n"+
						"	 SELECT TKT_REQ_ID,STATUS_REMARKS,STATUS_FLAG,\n"+
						"	 UPDATED_BY MODIFIED_BY,UPDATED_DATE FROM TKT_REQUEST_MASTER WITH(NOLOCK)\n"+ 
						"	 WHERE TKT_REQ_ID= ?) hs \n"+
						"	 LEFT OUTER JOIN login_details lg ON  \n"+
						"	 hs.modified_by = lg.username COLLATE SQL_Latin1_General_CP1_CS_AS\n"+
						"	 LEFT JOIN tkt_status_flag_master fm   ON\n"+ 
						"	fm.tkt_flag_id=HS.status_flag\n"+
						"	LEFT JOIN (SELECT L.PERSONNAME,L.USERNAME,ISNULL(ROLE_NAME,'') ROLE_NAME\n"+
						"	FROM LOGIN_DETAILS L\n"+
						"	LEFT JOIN ROLES R\n"+
						"	ON L.ROLE_ID=R.ROLE_ID ) RN\n"+ 
						"	ON RN.USERNAME=HS.modified_by COLLATE SQL_Latin1_General_CP1_CS_AS ORDER BY modified_date";
		
					
					

			    	tempList.add("S");
					tempList.add(reqId);
					paramList.add(tempList);
					
					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(reqId);
					paramList.add(tempList);
					
					issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					
					
				//	issuedata = DataAccess.pickData(lStrQuery, false, false);
					
				}
				catch(Exception e)
				{
					System.out.println("Exception raised in getIssueStatusHistory of "+e);
				}
				
				return issuedata;
			}
		 
		 public ArrayList getManualPhotoChangeIssueData(String reqId)
			{
				ArrayList issuedata = new ArrayList();
				  ArrayList paramList = new ArrayList();
					ArrayList tempList  = new ArrayList();
		
				
				try
				{
					lStrQuery = "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.photo_path,\n"+ 
						 "a.remarks,(select proof_doc_path+',' from tkt_request_master b WITH(NOLOCK),tkt_req_proof_details c WITH(NOLOCK)\n"+ 
						 "where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
						 ") from tkt_request_tobe_modify_dtls a  WITH(NOLOCK),\n"+ 
						 "tkt_request_master b WITH(NOLOCK)\n"+
						 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
					
					
					//issuedata = DataAccess.pickData(lStrQuery, false, false);
					
					tempList.add("S");
					tempList.add(reqId);
					paramList.add(tempList);
					
					tempList  = new ArrayList();
					tempList.add("S");
					tempList.add(reqId);
					paramList.add(tempList);
					
					issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					
				}
				catch(Exception e)
				{
					System.out.println("Exception raised in getManualPhotoChangeIssueData of "+e);
				}
				
				return issuedata;
			}
		 
		 public int uploadManualPhoto(String reqId,String personcode,String districtName)
		 {
			 int status=0;
			  ArrayList paramList = new ArrayList();
			  ArrayList tempList  = new ArrayList();
	
			 String qry="",photoPath="";
				try
				{
					 qry="select photo_path from tkt_request_tobe_modify_dtls WITH(NOLOCK) where tkt_req_id=?";
					 

						tempList.add("S");
						tempList.add(reqId);
						paramList.add(tempList);			 
					 
						photoPath = DataAccess.getReturnResultByPstmt(qry, paramList);
					 
				//	 System.out.println("-->"+photoPath);
					 
					// photoPath = DataAccess.getReturnResult(qry);
					 status =  uploadPhoto( photoPath, personcode, districtName) ;
				}
				catch(Exception e)
				{
				    status=0;
					System.out.println("Exception raised in uploadManualPhoto "+e);	
				}
			 return status;
		 }
		 
		 public int uploadPhoto(String photoPath,String personcode,String districtName) 
		 {
			 int status=0;
			 
			 photoPath = CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+photoPath;
			 String fileName = "Profilepic" + ".jpg";
             String strDirectoytemp = CommonConstants.SADAREM_DOCUMENTS_PATH+personcode+"\\Profile\\";
             File empImage = new File(photoPath);
             try
             {
				 FileInputStream instream ;
			     File tempdir = new File(strDirectoytemp);
			     final byte buffer[] = new byte[(int) empImage.length()];
			     
			     instream = new FileInputStream(empImage);
			     instream.read(buffer);
	             if (!tempdir.exists())
	             {
	                 tempdir.mkdirs();
	             }
	            /* File tempdistdir = new File(tempdir + "/" + districtName);
	             if (!tempdistdir.exists())
	             {
	                 tempdistdir.mkdir();
	             }*/
                   
 
	             BufferedImage imag = ImageIO.read(new ByteArrayInputStream(buffer));
	             ImageIO.write(imag, "jpg", new File(tempdir, fileName));
	             status=1;
	             if(instream!=null)
            	 {
            		 instream.close();
                 }
            	 
             } 
             catch (Exception e) 
             {
				e.printStackTrace();
				status = 0;
 			 }
             
			 return status;
		 }
		 
		 public int uploadAadharPhoto(String aadharcardNo,String personcode,String districtName)
		 {
			 String base64 = "";
			 int i=0;
			 boolean success1,success2;
             AadhaarProfile aadharProfile = null;
             
             try
             {
                 //aadharProfile = AadharDetails.getAadharDetails(aadharcardNo);
             
            	 aadharProfile = AadhaarUtility.getAadhaarProfileByUID(aadharcardNo);
		             if (aadharProfile != null) 
		             {
		                 //validation Aadhar Number End
		
		                 //System.out.println("Status: " + aadharProfile.getStatus());
		                 int invalidFlag = Integer.parseInt(aadharProfile.getStatus());
		
		                 if (invalidFlag == 100 || invalidFlag == 101) {
		                     if (aadharProfile.getBase64file() != null && !aadharProfile.getBase64file().equalsIgnoreCase("101")) {
		                         base64 = aadharProfile.getBase64file();
		                     } else {
		                         base64 = "-";
		                     }
		                 }
		
		             }
 
		             if (base64 != "" && !base64.equalsIgnoreCase("-")) 
		             {
		                String fileName = "Profilepic" + ".JPG";
		
		                 String strDirectoytemp = CommonConstants.SADAREM_DOCUMENTS_PATH+personcode+"\\Profile\\";
		
		
		                 File dir = null;
		                 File subdir = null;
		                 File subdir1 = null;
		                 File distdir = null;
		                 
		                 File tempdir = new File(strDirectoytemp);
		                 if (!tempdir.exists()) {
		                     tempdir.mkdirs();
		                 }
		                 /*File tempdistdir = new File(tempdir + "/" + districtName);
		                 if (!tempdistdir.exists()) {
		                     tempdistdir.mkdir();
		                 }*/
		                 success2 = CommonDetails.uploadingAadharPhoto(base64, "" + tempdir, fileName);
		
		                 if (success2) {
		                     i = 1;
		                     
		                 }
		             } 
              }
             catch(Exception e)
             {
            	 i=0;
             }
             return i;
		 }
		 
		 public int updatePhotoDtls(String reqId,String distName,String sadaremid)
		 {
			 int status=0;
			 String qry="",photoPrefer="";
			 ArrayList reqDtls = new ArrayList();
			 ArrayList paramList = new ArrayList();
			 ArrayList tempList  = new ArrayList();
	
			 try
			 {
				 lStrQuery =  "select  b.aadhar_id,a.photo_preference,a.photo_path,a.attach_doc_two from tkt_request_tobe_modify_dtls a  WITH(NOLOCK), tkt_request_master b WITH(NOLOCK)\n"+
						 "where a.tkt_req_id = b.tkt_req_id and a.tkt_req_id=?";
				 
					
					
					tempList.add("S");
					tempList.add(reqId);
					paramList.add(tempList);
	
					
					reqDtls = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);

						//reqDtls = DataAccess.pickData(lStrQuery, false, false);
					
				 if(reqDtls.size()>0)
				 {
					 reqDtls = (ArrayList)reqDtls.get(0);
					 if(reqDtls.size()>0)
					 {
						 photoPrefer = (String)reqDtls.get(1);
						 
						  
							status = uploadPhoto( (String)reqDtls.get(2), sadaremid, distName) ;
						 
					 }
				 }
				 
			 }
			 catch(Exception e)
			 {
				 status= 0;
			 }
			 return status;
		 }
		 
		 public ArrayList getOpenIssueApprovalIssueType(int roleId)
			{
				ArrayList issueTypes = new ArrayList();
				 ArrayList paramList = new ArrayList();
				 ArrayList tempList  = new ArrayList();
				
				try
				{
					lStrQuery = 
						 "select distinct a.tkt_type_id,b.tkt_type_name from tkt_status_flow_mapping a  WITH(NOLOCK),tkt_type_master b WITH(NOLOCK)\n"+
					     "where a.tkt_type_id=b.tkt_type_id and  a.role_id=?\n"+
					     "and b.is_active='Y'\n"+ 
	                     "and len(a.decision_flag)='4'";
					
					//issueTypes = DataAccess.pickData(lStrQuery, false, false);
					
					tempList.add("S");
					tempList.add(roleId);
					paramList.add(tempList);
	
					
					issueTypes = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
					
					
				}
				catch(Exception e)
				{
					System.out.println("Exception raised in getOpenIssueApprovalIssueType of "+e);
				}
				
				return issueTypes;
			} 
		  
		 public ArrayList getBasicdetailsOpenissue(String reqId,String sadaremId) 
			{
				ArrayList openissuebasicdata = new ArrayList();
				
				
				try
				{
					ArrayList paramList = new ArrayList();
					ArrayList tempList  = new ArrayList();
					
					lStrQuery  = 
							"SELECT distinct A.tkt_req_id,B.tkt_type_name,A.sadarem_id,person_surname+' '+c.person_name 'Name',C.district_name,C.mandal_name,\n" +
									" CASE\n" + 
									"  WHEN d.request_type='R' THEN 'Rejected'\n" + 
									"  WHEN is_final='Y' THEN 'Resolved'  ELSE 'Pending' end status,CONVERT(varchar(19),A.requested_date) Requested_dat ,CONVERT(varchar(19),A.updated_date) Updated_dat\n" + 
									"  ,A.STATUS_REMARKS FROM\n" + 
									"tkt_request_master A WITH(NOLOCK),\n" + 
									"tkt_type_master B WITH(NOLOCK),\n" + 
									"sadarem_view_complete_details C WITH(NOLOCK) ,\n" + 
									"tkt_status_flow_mapping d WITH(NOLOCK)\n" + 
									"WHERE\n" + 
									"A.tkt_type_id=B.tkt_type_id AND\n" + 
									"A.sadarem_id=C.sadarem_id AND\n" + 
									"d.tkt_type_id=A.tkt_type_id AND\n" + 
									"d.decision_flag = A.status_flag";

					if(reqId.length()>=15)
					{
						lStrQuery += " AND A.tkt_req_id=?";
						
						tempList  = new ArrayList();
						tempList.add("S");
						tempList.add(reqId);
						paramList.add(tempList);
						
					}
					if(sadaremId.length()>=15)
					{
						lStrQuery += " AND A.sadarem_id=?";
						tempList  = new ArrayList();
						tempList.add("S");
						tempList.add(sadaremId);
						paramList.add(tempList);
					}
						
					openissuebasicdata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);

				}
				catch(Exception e)
				{
					System.out.println("Exception in CommonIssueApprovalDAOImpl @ getBasicdetailsOpenissue : "+e);
				}
				
				return openissuebasicdata;
			}
		 public  void SendSMS(String reqId,String loginId,String rejectapprovemsg)  
			{		
				lStrQuery = " select t.sadarem_id,t.status_contno,m.tkt_type_name from tkt_request_master t WITH(NOLOCK),tkt_type_master m where t.tkt_req_id=? and t.tkt_type_id=m.tkt_type_id";
				ArrayList tempList = new ArrayList();
				ArrayList paramList = new ArrayList();
				ArrayList issuedata = new ArrayList();
				tempList.add("S");
				tempList.add(reqId);
				paramList.add(tempList);
				try{		
				issuedata = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
				for(int i=0;i<issuedata.size();i++)
				{
					tempList = (ArrayList) issuedata.get(i);
				}
				String MobNo = (String) tempList.get(1);
				String sadaremId = (String) tempList.get(0);
				String reqTypeName = (String) tempList.get(2);
				CommonDAOImpl comObj = new CommonDAOImpl();
						
				String MsgDeliveredFlag="N";
				if (!MobNo.equals("") || !MobNo.equals("NULL") || !MobNo.equals("null") || !(MobNo.length()==0)) 
				{			
					boolean isInserted = comObj.sendTKTRequestStatusbySMS(reqId,loginId);
					if (isInserted)
					{
						String message = "(SADAREM ID)"+sadaremId+":Your  Request "+reqTypeName+" has been "+rejectapprovemsg;
						//System.out.println(message);
						if(!MobNo.equals("-") && !MobNo.equals(""))
						{					 
		 			 	   ArrayList smsResult = (ArrayList) SMSUtility.sendmysms(MobNo,message);
		 			 	// ArrayList smsResult =	new ArrayList();
		 			 	// smsResult.add("Y"); smsResult.add("Y");
							if (smsResult.size() > 0 && smsResult.get(0).toString().equals("Y"))
							{
								MsgDeliveredFlag="Y";
							}
							else
							{
								MsgDeliveredFlag="N";
							}
						}			
						
						if(MsgDeliveredFlag.equals("Y"))
						{
							UpdateisSentFlag(reqId,"Y",MobNo,loginId);					
							//System.out.println("Msg sent in issue approved or rejected case");
						}				
					}			
				}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
			} 
		 public void UpdateisSentFlag(String reqId,String flag,String MobNo,String loginId)
		 	{
				String returnMsg="N";
				String query="";
				boolean isrecordPresent=false;
				boolean commitFlag=false;
				int count=0;
				PreparedStatement pstmt=null;
				Connection con=null;
				ResultSet rs=null;
				try{
				if(!(CommonUtility.checkNullObj(reqId).equals("")))
				{
					query="SELECT COUNT(*) FROM tkt_sms_status_log WHERE tkt_req_id=?";
					con=DBConnection.getConnection();
					con.setAutoCommit(false);
					pstmt=con.prepareStatement(query);
					pstmt.setString(1, reqId);
					rs=pstmt.executeQuery();
					while(rs.next())
					{
						count=Integer.parseInt(rs.getString(1));
					}
					if(count>0)
						isrecordPresent=true;
					if(isrecordPresent && flag.equalsIgnoreCase("Y"))
					{
						query=" UPDATE tkt_sms_status_log SET is_sent= 'Y', \n"+					  
							  " updated_date=CURRENT_TIMESTAMP,updated_by=? WHERE tkt_req_id=? \n";
					}
					if(isrecordPresent)
					{
						pstmt=null;
						count=0;
						pstmt=con.prepareStatement(query);
						pstmt.setString(1,loginId);
						pstmt.setString(2,reqId);
						count=pstmt.executeUpdate();
						if(count>=1)
						{
							//System.out.println("Success isSent Flag Updated");
							con.commit();
							returnMsg="Y";
							commitFlag=true;
						 }
					}		 
				}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			
			}

	}