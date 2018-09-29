
package com.tcs.sadare.issuetracksystem.DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.bf.disability.Constants.CommonConstants;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.issuetracksystem.actions.issueTrackingCommonUtil;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.CommonValidators;
import com.tcs.sadarem.util.DataAccess;
import com.tcs.sgv.common.util.DBConnection;

public class CommonIssueTrackingDAOImpl implements CommonIssueTrackingDAO
{
	static final private Logger log = Logger.getLogger(CommonIssueTrackingDAOImpl.class);
	String lStrQuery;
	public ArrayList getIssueType(int roleId)
	{
		ArrayList issueTypes = new ArrayList();
		
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery =  "select mas.tkt_type_id,mas.tkt_type_name from tkt_type_master mas join tkt_role_type_mapping map on mas.tkt_type_id = map.tkt_type_id \n"+
			      "where mas.is_active='Y' and map.is_active='Y' and role_id=?";
			
			tempList = new ArrayList();
			tempList.add("I");
			tempList.add(roleId);
			paramList.add(tempList);
			
		    issueTypes = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		}
		catch(Exception e)
		{
			log.info("Exception raised in tktCommonDAOImpl @ getIssueType of "+e);
		}
		return issueTypes;
	}
	
	 
	
	
	public ArrayList getRelationType(String gendername)
	{
		ArrayList relationTypes = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		
		
		try
		{
			lStrQuery =  "select relation_id,relation_name from sadarem_gender_relation_type_mapping "+
			      "where is_active='Y' and gender_name=?";
			tempList.add("S");
			tempList.add(gendername);
			paramList.add(tempList);
			
			relationTypes = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			
		}
		catch(Exception e)
		{
			log.info("Exception raised in tktCommonDAOImpl @ getRelationType of "+e);
		}
		
		return relationTypes;
	}
	public ArrayList getEducationList()
	{
		ArrayList EducationList = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		
		
		try
		{
			lStrQuery =  "select edu_id,edu_desc from sadarem_education_master "+
			      "where is_active='Y' ";
			//tempList.add("S");
			//tempList.add(gendername); 
			//paramList.add(tempList);
			
			EducationList = (ArrayList)DataAccess.pickData(lStrQuery, false, false);
			
		}
		catch(Exception e)
		{
			log.info("Exception raised in tktCommonDAOImpl @ getEducationList of "+e);
		}
		
		return EducationList;
	} 
	
	public String checkSADAREMStatus(String sadaremid)
	{
		String status="";
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "select person_code from tblperson_personal_details  WITH(NOLOCK) where person_code=? and status='Active' and view_edit_mode IN ('View','APA')";
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkSADAREMStatus of "+e);
		}
		
		return status;	
	}
	public String getEducationTypeName(String eduid)
	{
		String status="";
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "select edu_desc from sadarem_education_master where edu_id=?";
			tempList.add("I");
			tempList.add(eduid);
			paramList.add(tempList);
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkSADAREMStatus of "+e);
		}
		
		return status;	
	}
	
	
	public String checkSADAREMLiveStatus(String sadaremid)
	{
		String status="";
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "select reasonforpersonstatus from tblperson_personal_details  WITH(NOLOCK) where person_code=?";
		 
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
			if(!status.equalsIgnoreCase("live"))
			{
				status = "Issue cannot be raised as the person status is "+status+".<br>Please raise 'Person Status' grievance in DPM login to change the Person Status ";
			}
			else
			{
				status="";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkSADAREMLiveStatus of "+e);
		}
		
		return status;	
	}
	public String checkSADAREMPersonLiveStatus(String sadaremid)
	{
		String status="";
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "select reasonforpersonstatus from tblperson_personal_details  WITH(NOLOCK) where person_code=?";
			
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkSADAREMPersonLiveStatus of "+e);
		}
		
		return status;	
	} 
	public String checkIsRaisedStatus(String sadaremid)
	{
		String status="";
		String lStrQuery="";
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "select t.status_flag,k.decision_flag,k.is_final from   tkt_status_flow_mapping k ,"
					+ " ( "
					+ "						select top 1* from tkt_request_master where  sadarem_id=? order by created_date desc )  t "
					+ "where t.tkt_type_id=k.tkt_type_id and t.status_flag=k.decision_flag and k.request_type!='R' and k.is_final<>'Y'"; 
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkIsRaisedStatus of "+e);
		}
		  
		return status;	
	} 
	
	public String checkSADAREMProofStatus(String sadaremid)
	{
		String status="";
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			tempList = MultiAdharMapping(sadaremid);

			if(!(tempList.get(2).toString().equalsIgnoreCase("true")&& CommonValidators.AadhaarNumberValidator(tempList.get(0).toString())))
			{
				status = "Issue cannot be raised as the SADAREM ID is not tagged to Aadhar ID";
			}
			else
			{
				status="";
			}
			
		}
		catch(Exception e)
		{
			log.info("Exception raised in tktCommonDAOImpl @ checkSADAREMProofStatus of "+e);
		}
		
		return status;	
	} 
	
	public ArrayList getSadaremCommonData(String sadaremid)
	{
		ArrayList sadaremData = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		String lStrQuery1="";
		
		 try
		 {
			          
			 
				lStrQuery1 =   " select sadarem_id,v.districtid,v.mandalid,v.villageid,v.habtationid,district_name,mandal_name,village_name,\n"+
					   "habitation_name,person_surname,person_name,v.personname_telugu,CONVERT(varchar(10),person_dob,103) dob,\n"+
						"ISNULL(type_of_proof,'-') proofdoc_type,ISNULL(v.proof_id,'-') proof_id,person_gender,rel_type,\n"+
						"v.relation_name,relation_telugu_name,ISNULL(person_contactno,'-')contact_no,\n"+
						"category_id,CategoryCount,v.person_status,person_live_status,dis_form_status,\n"+
						"reltype.relation_id,per.house_number,per.pin_code,v.disb_id,v.Disability_Name,\n"+
						"disp_min_per,person_disp_percent,cause_of_disp,temp_cert_expire_year, certificate_issue_date,\n"+
						"cert_expire_date,v.edu_id\n"+
                        "FROM\n"+
						"tblPerson_Personal_Details per WITH(NOLOCK) ,\n"+
						"sadarem_gender_relation_type_mapping reltype  WITH(NOLOCK),\n"+
						"sadarem_view_complete_details v WITH(NOLOCK) \n"+
						"WHERE\n"+
						"ISNULL(per.gender,0)=reltype.gender_id AND\n"+
						"ISNULL(per.Relationship,0)=reltype.relation_id AND\n"+
						"per.Person_Code=v.sadarem_id and v.sadarem_id=?  ";
				
				if(CommonUtility.checkNullObj(checkSADAREMLiveStatus(sadaremid))=="")
		          {
					lStrQuery1+=	 "and v.is_active='Y' ";
		          }
			         tempList.add("S");
			         tempList.add(sadaremid);
			         paramList.add(tempList);	
			        // System.out.println("basicdata-->"+lStrQuery1);   
					sadaremData = DataAccess.pickDataByPrepareStmt(lStrQuery1, paramList, false, false);
					 
		 }
		 catch(Exception e)
		 {
			log.info("Exception raised in tktCommonDAOImpl @ getSadaremCommonData"+e);
		 }
		
		return sadaremData;
	}
	
	public ArrayList getSadaremCommonDataForSadaremDeletion(String sadaremid)
	{
		ArrayList sadaremData = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		String lStrQuery1="";
		 try
		 {
			          
			 
				lStrQuery1 =   " select sadarem_id,v.districtid,v.mandalid,v.villageid,v.habtationid,district_name,mandal_name,village_name,\n"+
					   "habitation_name,person_surname,person_name,v.personname_telugu,CONVERT(varchar(10),person_dob,103) dob,\n"+
						"ISNULL(type_of_proof,'-') proofdoc_type,ISNULL(v.proof_id,'-') proof_id,person_gender,rel_type,\n"+
						"v.relation_name,relation_telugu_name,ISNULL(person_contactno,'-')contact_no,\n"+
						"category_id,CategoryCount,v.person_status,person_live_status,dis_form_status,\n"+
						"reltype.relation_id,per.house_number,per.pin_code,v.disb_id,v.Disability_Name,\n"+
						"disp_min_per,person_disp_percent,cause_of_disp,temp_cert_expire_year, certificate_issue_date,\n"+
						"cert_expire_date,v.edu_id\n"+
                        "FROM\n"+
						"tblPerson_Personal_Details per WITH(NOLOCK) ,\n"+
						"sadarem_gender_relation_type_mapping reltype  WITH(NOLOCK),\n"+
						"sadarem_view_complete_details v WITH(NOLOCK) \n"+
						"WHERE\n"+
						"ISNULL(per.gender,0)=reltype.gender_id AND\n"+
						"ISNULL(per.Relationship,0)=reltype.relation_id AND\n"+
						"per.Person_Code=v.sadarem_id and v.sadarem_id=?  ";
				
         tempList.add("S");
         tempList.add(sadaremid);
         paramList.add(tempList);	
        // System.out.println("basicdata-->"+lStrQuery1);   
		sadaremData = DataAccess.pickDataByPrepareStmt(lStrQuery1, paramList, false, false);
		 
		 }
		 catch(Exception e)
		 {
			log.info("Exception raised in tktCommonDAOImpl @ getSadaremCommonData"+e);
		 }
		
		return sadaremData;
	}
	
	public int insertBasicDtls(String sadaremid)
	{
		int status=0;
		String lStrQuery = "";
		
		
		lStrQuery =  "";
		
		
		return status;
	}
	
	
	public String generateIssueReqId(String issueTypeId)
	{
		String reqId = "",qry="";
		String Idformat = "";
		DateFormat dformat = new SimpleDateFormat("ddMMyy");
		Date d = new Date();
		String curDate = dformat.format(d); 
		Idformat = "G"+issueTypeId+curDate;		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
	  try
	  {
		lStrQuery = "select '"+Idformat+"'+RIGHT(REPLICATE('0', 4) + CAST(( COUNT(*)+1) AS VARCHAR(4)), 5) from tkt_request_master where substring(tkt_req_id,0,12)=?";
		 tempList.add("S");
         tempList.add(Idformat);
         paramList.add(tempList);		 
		reqId = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
		  log.info("Exception raised in tktCommonDAOImpl @  generateIssueReqId"+e);
	  }
		return reqId;
	}
	
	public   String getTktDecisionFlag(int roleID,String requestType,String requestId,String tkt_type_id)
	  {
	    //String tktDecisionFlag = "";
	    String DecisionFlag = "";
	    ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		    try
		    {
		          String lStrQuery =
		        	   " SELECT decision_flag+',' \n"+
			           " FROM  \n"+
			           " tkt_status_flow_mapping \n"+
			           "  WHERE  role_id =? AND  \n " +
			           " UPPER(request_type)=? ";
		           
		          if(!tkt_type_id.equals("-1"))
		          {
		        	   lStrQuery+="and tkt_type_id=? ";
		          }
		            if(requestId!=null && requestId.length()!=0)
		            	{
		        	   lStrQuery+= 
		        			   	" AND flow_flag =(SELECT status_flag FROM \n"+
					           " tkt_request_master WHERE \n "+
					           " tkt_req_id='"+requestId+"')";
		            	}
		            lStrQuery+= "for XML PATH('')";
		            tempList.add("I");
		            tempList.add(roleID);
		            paramList.add(tempList);	tempList = new ArrayList();	 
		            tempList.add("S");
		            tempList.add(requestType.toUpperCase());
		            paramList.add(tempList);	tempList = new ArrayList();	
		            if(!tkt_type_id.equals("-1"))
			        {
			            tempList.add("S");
			            tempList.add(tkt_type_id);
			            paramList.add(tempList);
			        }
		            DecisionFlag = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
		            if(DecisionFlag.length()>0)
		            {
		             DecisionFlag = DecisionFlag.substring(0, DecisionFlag.length()-1);
		            }
		          
			}    
		    catch(Exception lexp)
		    {
		  	 log.info("Exception in tktCommonDAOImpl @ gettktDecisionFlag : "+lexp.getLocalizedMessage());
		    }
	        return DecisionFlag;
	  }
	public  String getFlowFlag(int  roleID,String requestType,String requestId,String tkt_type_id )
	  {
	    String FlowFlag = "";
	    ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		    try
		    {
		           String lStrQuery=
		        	   " SELECT flow_flag+',' \n"+
			           " FROM  \n"+
			           " tkt_status_flow_mapping \n"+
			           "  WHERE role_id=? AND \n " +
			           " request_type=?\n" ;
		           
		           if(!tkt_type_id.equals("-1"))
			        {
		        	   lStrQuery+=" and tkt_type_id=? ";
			        }
		           
			           if(requestId!=null && requestId.length()!=0)
			           {
			        	   lStrQuery+=  " AND flow_flag =(SELECT status_flag FROM \n"+
			           " tkt_request_master WHERE \n "+
			           " tkt_req_id=?)";
			           } 
			           lStrQuery+="FOR XML PATH('')";
			           tempList.add("I");
			            tempList.add(roleID);
			            paramList.add(tempList);	
			            
			            tempList = new ArrayList();	 
			            tempList.add("S");
			            tempList.add(requestType.toUpperCase());
			            paramList.add(tempList);	
			            
			            tempList = new ArrayList();		 
			            if(!tkt_type_id.equals("-1"))
				        { 
				            tempList.add("S");
				            tempList.add(tkt_type_id);
				            paramList.add(tempList);
				        } 
			            
			            
			            if(requestId!=null && requestId.length()!=0)
		            	{       
			            tempList = new ArrayList();		
			            tempList.add("S");
			            tempList.add(requestId);
			            paramList.add(tempList);
			      
		            	}
			     
			             
			           FlowFlag = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
			           
			           
			           if(FlowFlag.length()>0) 
			           {
			             FlowFlag = FlowFlag.substring(0, FlowFlag.length()-1);
			           }
			           //System.out.println("FlowFlag-->"+FlowFlag);
			           
		     }    
		    catch(Exception lexp)
		    {
		  	  log.info("Exception in tktCommonDAOImpl @ gettktDecisionFlag : "+lexp.getLocalizedMessage());
		    }
		     return FlowFlag;
	  }
	
	public   String getIsFinalFlag(int roleID,String requestType,String requestId,String tkt_type_id)
	  {
	    //String tktDecisionFlag = "";
	    String DecisionFlag = "";
	    ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
		
		    try
		    {
		          String lStrQuery =
		        	   " SELECT is_final \n"+
			           " FROM  \n"+
			           " tkt_status_flow_mapping \n"+
			           "  WHERE  role_id =? AND  \n " +
			           " UPPER(request_type)=? and tkt_type_id=?";
		          
		            if(requestId!=null && requestId.length()!=0)
		            	{
		        	   lStrQuery+= 
		        			   	" AND flow_flag =(SELECT status_flag FROM \n"+
					           " tkt_request_master WHERE \n "+
					           " tkt_req_id=?)";
		            	}
		            
		            tempList.add("I");
		            tempList.add(roleID);
		            paramList.add(tempList);
		            
		            tempList = new ArrayList();	 
		            tempList.add("S");
		            tempList.add(requestType.toUpperCase());
		            paramList.add(tempList);	
		            
		            tempList = new ArrayList();	 
		            tempList.add("S");
		            tempList.add(tkt_type_id);
		            paramList.add(tempList);
		            
		            if(requestId!=null && requestId.length()!=0)
	            	{       
		            tempList = new ArrayList();		
		            tempList.add("S");
		            tempList.add(requestId);
		            paramList.add(tempList);
		      
	            	}
		      
		           DecisionFlag =DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
		    }    
		    catch(Exception lexp)
		    {
		  	  log.info("Exception in tktCommonDAOImpl @ getIsFinalFlag : "+lexp.getLocalizedMessage());
		    }
	        return DecisionFlag;
	  }
	
	public int insertBasicDtls(HashMap basicDtls,Connection lcon)
	{
		int result =0;
		String qry="";
		
		  PreparedStatement lPstmt = null;
		  
		  String reqId  = (String)basicDtls.get("reqId");
		  String tkt_type_id = (String)basicDtls.get("selIssueType");
		  String sadaremId = (String)basicDtls.get("sadaremId");
		  String reqBy = (String)basicDtls.get("reqBy");
		  String decisionFlag =  (String)basicDtls.get("decisionFlag");
		  String remarks = (String)basicDtls.get("remarks");
		  String MobNo = (String)basicDtls.get("MobNo");
		  String ipaddress = CommonUtility.checkNullObject((String)basicDtls.get("ipaddress"));
	try
	{ 	  
		if(reqId.length()>4)
		{
		lStrQuery =  "insert into tkt_request_master (\n"+
						"tkt_req_id,tkt_type_id,sadarem_id,aadhar_id,person_sur_name,person_name,person_telugu_name,person_dob,person_contno,relation_type,\n"+
						"relation_name,relation_teugu_name,gender,person_status,disability_type,disability_percentage,certificate_issue_date,certificate_type,\n"+
						"certificate_expired_date,cause_of_disability,dist_id,mandal_id,village_id,hab_id,house_no,pin_code,camp_id,status_flag,\n"+
						"status_remarks,is_active,req_ip_address,requested_by,requested_date,created_by,created_date,updated_by,updated_date,status_contno)\n"+

						" select ?,?,sadarem_id,ISNULL(v.proof_id,'-') proof_id,person_surname,person_name,v.personname_telugu,CONVERT(date,person_dob,103) dob,\n"+
						"ISNULL(person_contactno,'-')contact_no,reltype.relation_id,v.relation_name,relation_telugu_name,case when person_gender='Male' then 1 else 2 end,\n"+
						"v.person_live_status,v.disb_id,person_disp_percent,CONVERT(date,certificate_issue_date,103)issue_date,category_id ,case when cert_expire_date ='NA' then null else CONVERT(date,cert_expire_date,103) end,cause_of_disp,\n"+
						" v.districtid,v.mandalid,v.villageid,v.habtationid,per.house_number,per.pin_code,per.camp_id,\n"+
						" ?,?,'Y',?,?,getdate(),?,getdate(),?,getdate(),?\n"+
						"FROM\n"+
						"tblPerson_Personal_Details per WITH(NOLOCK) ,\n"+
						"sadarem_gender_relation_type_mapping reltype  WITH(NOLOCK),\n"+
						"sadarem_view_complete_details v\n"+
						"WHERE\n"+
						"ISNULL(per.gender,0)=reltype.gender_id AND\n"+
						"ISNULL(per.Relationship,0)=reltype.relation_id AND\n"+
						"per.Person_Code=v.sadarem_id and v.sadarem_id=?";
		 
	    lPstmt = lcon.prepareStatement(lStrQuery);
	    lPstmt.setString(1,reqId );
	     lPstmt.setString(2,tkt_type_id );
	     lPstmt.setString(3,decisionFlag );
	     lPstmt.setString(4,remarks );
	     lPstmt.setString(5,ipaddress );
	     lPstmt.setString(6,reqBy );
	     lPstmt.setString(7,reqBy );
	     lPstmt.setString(8,reqBy );
	     lPstmt.setString(9, MobNo);
		 lPstmt.setString(10, sadaremId );
	    result =   lPstmt.executeUpdate();
	}
	}
	    catch(Exception e)
		  {
	    	log.info("Exception raised in tktCommonDAOImpl @ insertBasicDtls"+e);
	    	 e.printStackTrace();
	      }
		return result;
	}
	
	public int insertOpenBasicDtls(HashMap basicDtls,Connection lcon)
	{
		int result =0;
		String qry="";
		 
		  PreparedStatement lPstmt = null;
		  
		  String reqId  = (String)basicDtls.get("reqId");
		  String tkt_type_id = (String)basicDtls.get("selIssueType");
		  String sadaremId = (String)basicDtls.get("sadaremId");
		  String reqBy = (String)basicDtls.get("reqBy");
		  String decisionFlag =  (String)basicDtls.get("decisionFlag");
		  String remarks = (String)basicDtls.get("remarks"); 
		  String contactno = (String)basicDtls.get("contactNo"); 
		  String altcontactno = (String)basicDtls.get("altcontactNo"); 
		  String emailid = (String)basicDtls.get("emailid"); 
		
	try{	
		if(reqId.length()>4)
		{
		  
		lStrQuery =       "insert into tkt_request_master (\n"+
						"tkt_req_id,tkt_type_id,sadarem_id,aadhar_id,person_sur_name,person_name,person_telugu_name,person_dob,person_contno,relation_type,\n"+
						"relation_name,relation_teugu_name,gender,person_status,disability_type,disability_percentage,certificate_issue_date,certificate_type,\n"+
						"certificate_expired_date,cause_of_disability,dist_id,mandal_id,village_id,hab_id,house_no,pin_code,camp_id,status_flag,\n"+
						"status_remarks,is_active,req_ip_address,requested_by,requested_date,created_by,created_date,updated_by,updated_date,open_griev,req_sms,edu_id,status_contno,status_altcontno,status_email_id)\n"+

						" select ?,?,sadarem_id,ISNULL(v.proof_id,'-') proof_id,person_surname,person_name,v.personname_telugu,CONVERT(date,person_dob,103) dob,\n"+
						"ISNULL(person_contactno,'-')contact_no,reltype.relation_id,v.relation_name,relation_telugu_name,case when person_gender='Male' then 1 else 2 end,\n"+
						"v.person_live_status,v.disb_id,person_disp_percent,CONVERT(date,certificate_issue_date,103)issue_date,category_id ,case when cert_expire_date ='NA' then null else CONVERT(date,cert_expire_date,103) end,cause_of_disp,\n"+
						" v.districtid,v.mandalid,v.villageid,v.habtationid,per.house_number,per.pin_code,per.camp_id,\n"+
						" ?,?,'Y','',?,getdate(),?,getdate(),?,getdate(),'O','Y',v.edu_id,?,?,?\n"+
						"FROM\n"+
						"tblPerson_Personal_Details per WITH(NOLOCK) ,\n"+
						"sadarem_gender_relation_type_mapping reltype  WITH(NOLOCK),\n"+
						"sadarem_view_complete_details v\n"+
						"WHERE\n"+
						"ISNULL(per.gender,0)=reltype.gender_id AND\n"+
						"ISNULL(per.Relationship,0)=reltype.relation_id AND\n"+
						"per.Person_Code=v.sadarem_id and v.sadarem_id=?";   
		 lPstmt = lcon.prepareStatement(lStrQuery);
	     lPstmt.setString(1,reqId );
	     lPstmt.setString(2,tkt_type_id );
	     lPstmt.setString(3,decisionFlag );
	     lPstmt.setString(4,remarks );
	     lPstmt.setString(5,reqBy );
	     lPstmt.setString(6,reqBy );
	     lPstmt.setString(7,reqBy );
	     lPstmt.setString(8,contactno );
	     lPstmt.setString(9,altcontactno );
	     lPstmt.setString(10,emailid );
		 lPstmt.setString(11,sadaremId );
	    result =   lPstmt.executeUpdate();
	 }
	}
	    catch(Exception e)
		  {
	    	 e.printStackTrace();
	    	 log.info("Exception raised in insertOpenBasicDtls"+e);
	      }
		
		return result;
	}
	
	
	public int nameChangeInsertDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		  String status="";
		  PreparedStatement lPstmt = null;
		  int result =0,proofInsertStatus=0;
		  HashMap proofDtls = new HashMap();
		  
		  String issueReqId ="";
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");	
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
			}
		try
		{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,person_name,person_telugu_name,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,?,'Y',?,?,getdate(),?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)nameChangModifyDtls.get("fname") );
		    lPstmt.setString(5,(String)nameChangModifyDtls.get("telname") );
		    lPstmt.setString(6,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress") )); 
		    lPstmt.setString(7,(String)nameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(8,(String)nameChangModifyDtls.get("remarks") ); 
		    
		    result = lPstmt.executeUpdate();
		    if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    }
		      }
		 }
		catch(Exception e)
		{
			log.info("Exception raised in nameChangeInsertDtls"+e);
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
	
	public int genderChangeInsertDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="" ;
		  HashMap proofDtls = new HashMap();
		  
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");	
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
			 
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,gender,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)nameChangModifyDtls.get("genderId") );
		    lPstmt.setString(5,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress") )); 
		    lPstmt.setString(6,(String)nameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(7,(String)nameChangModifyDtls.get("remarks") ); 
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					 }
			    }
		     }
		}
		catch(Exception e)
		{
			log.info("Exception raised in genderChangeInsertDtls"+e);
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
	
	public int ageChangeInsertDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="",dob="";
		  HashMap proofDtls = new HashMap();
		   
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");	
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
				dob = (String)nameChangModifyDtls.get("dob");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,dob,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,CONVERT(date,'"+dob+"',103),'Y',?,?,getdate(),?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") ); 

		    lPstmt.setString(4,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress")) ); 
		    lPstmt.setString(5,(String)nameChangModifyDtls.get("reqBy")); 
		    lPstmt.setString(6,(String)nameChangModifyDtls.get("remarks")); 
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			     }
		     }
		  
		}
		catch(Exception e)
		{
			log.info("Exception raised in ageChangeInsertDtls"+e);
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
	
	public int AppelateInsertDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="",dob="";
		  HashMap proofDtls = new HashMap();
		   
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");	
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
				dob = (String)nameChangModifyDtls.get("dob");
			}
		try{
			
		   if(updateAssessedAppellate((String)nameChangModifyDtls.get("sadaremId") ,lcon))	
		   {
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,APELATE_CATEGORY,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?)";
		 
			//System.out.println("lStrQuery"+lStrQuery);
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") ); 
		    lPstmt.setString(4,(String)nameChangModifyDtls.get("category") );
		    lPstmt.setString(5,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress")) ); 
		    lPstmt.setString(6,(String)nameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(7,(String)nameChangModifyDtls.get("remarks") ); 
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			     }
		     }
		   }
		   
		}
		catch(Exception e)
		{
			log.info("Exception raised in ageChangeInsertDtls"+e);
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
	
	
	public boolean updateAssessedAppellate(String sadaremId,Connection con)
	{
		String qry="";
		int count=0;
		boolean status=false;
		  PreparedStatement lPstmt = null;
		try
		{
			lStrQuery =  "select count(*) from tkt_request_tobe_modify_dtls where tkt_type_id='S018' and  sadarem_id='"+sadaremId+"'";
			
			count = Integer.parseInt(CommonUtility.checkNullObj(DataAccess.getReturnResult(lStrQuery)));
			if(count>0)
			{
			  lStrQuery =  "update tkt_request_tobe_modify_dtls set IS_ASSESSED='Y' where tkt_type_id='S018' and  sadarem_id=? ";
			    lPstmt =  con.prepareStatement(lStrQuery);
			    lPstmt.setString(1,sadaremId );
			   count = lPstmt.executeUpdate();
			   if(count>0)
			   {
				   status=true;
			   }
			}
			else
			{
				status=true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("");
		}
	 return status;	
	}
	
	
	
	
	public int personStatusChangeInsertDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="",docType="";
		  HashMap proofDtls = new HashMap();
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
				docType = (String)nameChangModifyDtls.get("docType");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,person_status,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)nameChangModifyDtls.get("pstatus") );
		  //  lPstmt.setString(5, issueReqId+"/"+issueReqId+"_Attachment1"+".pdf" );
		    lPstmt.setString(5,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress") )); 
		    lPstmt.setString(6,(String)nameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(7,(String)nameChangModifyDtls.get("remarks") ); 
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    }
		      }
		  
		}
		catch(Exception e){
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
	
	public int partBReEntryChangeInsertDtls(HashMap partBreEntryChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="",docType="";
		  HashMap proofDtls = new HashMap();
		  long fileSize1  = (Long) partBreEntryChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) partBreEntryChangModifyDtls.get("fileSize2");
			if(partBreEntryChangModifyDtls.size()>0)
			{
				issueReqId = (String)partBreEntryChangModifyDtls.get("reqId");
				docType = (String)partBreEntryChangModifyDtls.get("docType");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,apelate_category,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)partBreEntryChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)partBreEntryChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)partBreEntryChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)partBreEntryChangModifyDtls.get("category") );
		  //  lPstmt.setString(5, issueReqId+"/"+issueReqId+"_Attachment1"+".pdf" );
		    lPstmt.setString(5,CommonUtility.checkNullObject((String)partBreEntryChangModifyDtls.get("ipaddress") ));
		    lPstmt.setString(6,(String)partBreEntryChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(7,(String)partBreEntryChangModifyDtls.get("remarks") ); 
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",partBreEntryChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",partBreEntryChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",partBreEntryChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",partBreEntryChangModifyDtls.get("filePath1")+"."+partBreEntryChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",partBreEntryChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", partBreEntryChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",partBreEntryChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",partBreEntryChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",partBreEntryChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",partBreEntryChangModifyDtls.get("filePath2")+"."+partBreEntryChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",partBreEntryChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", partBreEntryChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    }
		      }
		  
		}
		catch(Exception e){
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
	public int fullNameChangeInsertDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0;
          HashMap proofDtls = new HashMap();
		  
		  String issueReqId ="";
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");	
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,person_sur_name,person_name,person_telugu_name,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,?,?,'Y',?,?,getdate(),?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)nameChangModifyDtls.get("surname") );
		    lPstmt.setString(5,(String)nameChangModifyDtls.get("fname") );
		    lPstmt.setString(6,(String)nameChangModifyDtls.get("telname") );
		    lPstmt.setString(7,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress") )); 
		    lPstmt.setString(8,(String)nameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(9,(String)nameChangModifyDtls.get("remarks") ); 
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    }
		      }
		}
		catch(Exception e)
		{
			log.info("Exception raised in fullNameChangeInsertDtls"+e);
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
	public int relChangeInsertDtls(HashMap nameChangModifyDtls,Connection con)
	{
		
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="" ;
		  HashMap proofDtls = new HashMap();
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");	
		 if(nameChangModifyDtls.size()>0)
		 {
			issueReqId = (String)nameChangModifyDtls.get("reqId");
	     }
			
			try
			{
				lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
		              "(tkt_req_id,tkt_type_id,sadarem_id,relation_name,relation_type,relation_teugu_name,\n"+
		              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
		              "values(?,?,?,?,?,?,'Y',?,?,getdate(),?)"; 
				 
			    lPstmt = con.prepareStatement(lStrQuery);
			    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
			    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
			    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") );
			    lPstmt.setString(4,(String)nameChangModifyDtls.get("relationname") );
			    lPstmt.setString(5,(String)nameChangModifyDtls.get("relationtype") );
			    lPstmt.setString(6,(String)nameChangModifyDtls.get("telname") ); 
			    lPstmt.setString(7,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress") )); 
			    lPstmt.setString(8,(String)nameChangModifyDtls.get("reqBy") ); 
			    lPstmt.setString(9,(String)nameChangModifyDtls.get("remarks") ); 
			    
			  result = lPstmt.executeUpdate();
			  if(result>0)
			    {
			    	if(fileSize1 != 0)
					{
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
						
						result = insertProofDtls(proofDtls,con);
					
				    	if(result>0 && fileSize2 != 0)
				    	{
				    		proofDtls = new HashMap();
				    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
				    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
				    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
				    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
				    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
				    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
				    		proofDtls.put("reqId",issueReqId );
				    		result = insertProofDtls(proofDtls,con);
						}
				    }
			    }
			  }
			catch(Exception e)
			{
				System.out.println("exception raised in relChangeInsertDtls method " );
				log.info("Exception raised in relChangeInsertDtls"+e);
				e.printStackTrace();
				result=0;
				
			}
		return result;
	}
	
	public int addressChangeInsertDtls(HashMap addressChangModifyDtls,Connection con)
	{
		String status="";
		 
		  PreparedStatement lPstmt = null;
		  int result =0;
		  HashMap proofDtls = new HashMap();
			  
		  String issueReqId ="",docType="";
		  long fileSize1  = (Long) addressChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) addressChangModifyDtls.get("fileSize2");	
			if(addressChangModifyDtls.size()>0)
			{
				issueReqId = (String)addressChangModifyDtls.get("reqId");
				docType = (String)addressChangModifyDtls.get("docType");
			}
		try
		{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,dist_id,mandal_id,village_id,hab_id,house_no,pin_code,\n" +
	              " is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,?,?,?,?,?,'Y',?,?,getdate(),?)";
			//lcon = DBConnection.getConnection();
		    lPstmt = con.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)addressChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)addressChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)addressChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)addressChangModifyDtls.get("distName") );
		    lPstmt.setString(5,(String)addressChangModifyDtls.get("manName") );
		    //lPstmt.setString(6,(String)nameChangModifyDtls.get("panName") );
		    lPstmt.setString(6,(String)addressChangModifyDtls.get("villName") );
		    lPstmt.setString(7,(String)addressChangModifyDtls.get("habName") );
		    lPstmt.setString(8,(String)addressChangModifyDtls.get("houseNo") );
		    lPstmt.setString(9,(String)addressChangModifyDtls.get("pincode") );
		    lPstmt.setString(10,CommonUtility.checkNullObject((String)addressChangModifyDtls.get("ipaddress")) );  		  
		    lPstmt.setString(11,(String)addressChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(12,(String)addressChangModifyDtls.get("remarks") );
		    
		     
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",addressChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",addressChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",addressChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",addressChangModifyDtls.get("filePath1")+"."+addressChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",addressChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", addressChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,con);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",addressChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",addressChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",addressChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",addressChangModifyDtls.get("filePath2")+"."+addressChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",addressChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", addressChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,con);
						
						
			    	}
			    	 
				}
		    	
		    }
		   
		}
		catch(Exception e){
			System.out.println("exception raised in addressChangeInsertDtls method " );
			log.info("Exception raised in addressChangeInsertDtls"+e);
			e.printStackTrace();
		}
		
		
		return result;
	}
	public int causeOfDisabilityChangeInsertDtls(HashMap causeofdisabilityChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="",docType="";
			if(causeofdisabilityChangModifyDtls.size()>0)
			{
				issueReqId = (String)causeofdisabilityChangModifyDtls.get("reqId");
				docType = (String)causeofdisabilityChangModifyDtls.get("docType");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,cause_of_disability\n"+
	              ",is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)causeofdisabilityChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)causeofdisabilityChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)causeofdisabilityChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)causeofdisabilityChangModifyDtls.get("causename") );
		    lPstmt.setString(5,CommonUtility.checkNullObject((String)causeofdisabilityChangModifyDtls.get("ipaddress")) ); 
		    lPstmt.setString(6,(String)causeofdisabilityChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(7,(String)causeofdisabilityChangModifyDtls.get("remarks") ); 
		    
		  result = lPstmt.executeUpdate();
		  
		}
		catch(Exception e)
		{
			log.info("Exception raised in causeOfDisabilityChangeInsertDtls"+e);
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
	
	public int aadharChangeInsertDtls(HashMap aadharChangModifyDtls,Connection lcon)
	{
		  String status="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="",docType="";
		  HashMap proofDtls = new HashMap();
		  long fileSize1  = (Long) aadharChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) aadharChangModifyDtls.get("fileSize2");	
			if(aadharChangModifyDtls.size()>0)
			{
				issueReqId = (String)aadharChangModifyDtls.get("reqId");
				docType = (String)aadharChangModifyDtls.get("docType");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,aadhar_id\n"+
	              ",is_active,req_ip_address,created_by,created_date,remarks,aadhar_sadarem_id)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?,?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)aadharChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)aadharChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)aadharChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)aadharChangModifyDtls.get("aadharno") );
		    lPstmt.setString(5,CommonUtility.checkNullObject((String)aadharChangModifyDtls.get("ipaddress") ));
		    lPstmt.setString(6,(String)aadharChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(7,(String)aadharChangModifyDtls.get("remarks") ); 
		    lPstmt.setString(8,(String)aadharChangModifyDtls.get("dupSadaremId") ); 
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",aadharChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",aadharChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",aadharChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",aadharChangModifyDtls.get("filePath1")+"."+aadharChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",aadharChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", aadharChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",aadharChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",aadharChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",aadharChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",aadharChangModifyDtls.get("filePath2")+"."+aadharChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",aadharChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", aadharChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    }
		    }
		}
		catch(Exception e)
		{
			log.info("Exception raised in aadharChangeInsertDtls"+e);
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
	
	public int photoChangeInsertDtls(HashMap photoChangModifyDtls,Connection lcon)
	{
		String status="",photoDocType="",sadaremId="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="",docType="",preference ="";
		  HashMap proofDtls = new HashMap();
		  long fileSize1  = (Long) photoChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) photoChangModifyDtls.get("fileSize2");	
			if(photoChangModifyDtls.size()>0)
			{
				issueReqId = (String)photoChangModifyDtls.get("reqId");
				docType = (String)photoChangModifyDtls.get("docType");
				photoDocType = (String)photoChangModifyDtls.get("photodocType");
				sadaremId  = (String)photoChangModifyDtls.get("sadaremId");
				preference = (String)photoChangModifyDtls.get("preference");
			}
		try{
			
				if("M".equals(preference))	
				{
					lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
				              "(tkt_req_id,tkt_type_id,sadarem_id,photo_preference,photo_path,\n"+
				              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
				              "values(?,?,?,?,?,'Y','',?,getdate(),?)";
					
					    lPstmt = lcon.prepareStatement(lStrQuery);
					    lPstmt.setString(1,(String)photoChangModifyDtls.get("reqId") );
					    lPstmt.setString(2,(String)photoChangModifyDtls.get("selIssueType") );
					    lPstmt.setString(3,(String)photoChangModifyDtls.get("sadaremId") );
					    lPstmt.setString(4,(String)photoChangModifyDtls.get("preference") );
					    lPstmt.setString(5,issueReqId+"/"+sadaremId+".jpg" );
					    lPstmt.setString(6,(String)photoChangModifyDtls.get("reqBy") ); 
					    lPstmt.setString(7,(String)photoChangModifyDtls.get("remarks") ); 
					    
					  result = lPstmt.executeUpdate();
				}
				else
				{
					lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
				              "(tkt_req_id,tkt_type_id,sadarem_id,photo_preference,\n"+
				              "is_active,req_ip_address,created_by,created_date,remarks,photo_path)\n"+
				              "values(?,?,?,?,'Y','',?,getdate(),?,?)";
					
					    lPstmt = lcon.prepareStatement(lStrQuery);
					    lPstmt.setString(1,(String)photoChangModifyDtls.get("reqId") );
					    lPstmt.setString(2,(String)photoChangModifyDtls.get("selIssueType") );
					    lPstmt.setString(3,(String)photoChangModifyDtls.get("sadaremId") );
					    lPstmt.setString(4,(String)photoChangModifyDtls.get("preference") );
					    lPstmt.setString(5,(String)photoChangModifyDtls.get("reqBy") ); 
					    lPstmt.setString(6,(String)photoChangModifyDtls.get("remarks") ); 
					    lPstmt.setString(7,issueReqId+"/"+sadaremId+".jpg" );
					  result = lPstmt.executeUpdate();
				}
			
				if(result>0)
			    {
			    	if(fileSize1 != 0)
					{
			    		proofDtls.put("proofTypeID",photoChangModifyDtls.get("proofTypeID1"));
			    		proofDtls.put("proofId",photoChangModifyDtls.get("proofId1"));
			    		proofDtls.put("docType",photoChangModifyDtls.get("docType1"));
			    		proofDtls.put("filePath",photoChangModifyDtls.get("filePath1")+"."+photoChangModifyDtls.get("docType1")); 
			    		proofDtls.put("fileDoc",photoChangModifyDtls.get("fileDoc1"));
			    		proofDtls.put("reqBy", photoChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
						
						result = insertProofDtls(proofDtls,lcon);
					
				    	if(result>0 && fileSize2 != 0)
				    	{
				    		proofDtls = new HashMap();
				    		proofDtls.put("proofTypeID",photoChangModifyDtls.get("proofTypeID2"));
				    		proofDtls.put("proofId",photoChangModifyDtls.get("proofId2"));
				    		proofDtls.put("docType",photoChangModifyDtls.get("docType2"));
				    		proofDtls.put("filePath",photoChangModifyDtls.get("filePath2")+"."+photoChangModifyDtls.get("docType2")); 
				    		proofDtls.put("fileDoc",photoChangModifyDtls.get("fileDoc2"));
				    		proofDtls.put("reqBy", photoChangModifyDtls.get("reqBy"));
				    		proofDtls.put("reqId",issueReqId );
				    		result = insertProofDtls(proofDtls,lcon);
						}
				    }
			    }
		 }
		catch(Exception e)
		{
			log.info("Exception raised in photoChangeInsertDtls"+e);
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
	
	
	public int manualphotoChangeInsertDtls(HashMap photoChangModifyDtls,Connection lcon)
	{
		String status="",photoDocType="",sadaremId="";
		  PreparedStatement lPstmt = null;
		  int result =0;
		  String issueReqId ="",docType="";
		  HashMap proofDtls = new HashMap();
		 
		  long fileSize1  = (Long) photoChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) photoChangModifyDtls.get("fileSize2");	
			if(photoChangModifyDtls.size()>0)
			{
				issueReqId   = (String)photoChangModifyDtls.get("reqId");
				docType      = (String)photoChangModifyDtls.get("docType");
				photoDocType = (String)photoChangModifyDtls.get("photodocType");
				sadaremId    = (String)photoChangModifyDtls.get("sadaremId");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,photo_preference,photo_path,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,?,'Y',?,?,getdate(),?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)photoChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)photoChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)photoChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)photoChangModifyDtls.get("preference") );
		    lPstmt.setString(5,issueReqId+"/"+sadaremId+".jpg");
		    lPstmt.setString(6,CommonUtility.checkNullObject((String)photoChangModifyDtls.get("ipaddress")) ); 
		    lPstmt.setString(7,(String)photoChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(8,(String)photoChangModifyDtls.get("remarks") ); 
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",photoChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",photoChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",photoChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",photoChangModifyDtls.get("filePath1")+"."+photoChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",photoChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", photoChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",photoChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",photoChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",photoChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",photoChangModifyDtls.get("filePath2")+"."+photoChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",photoChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", photoChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    }
		    }
		}
		catch(Exception e)
		{
			log.info("Exception raised in manualphotoChangeInsertDtls"+e);
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
	
	
	public String insertNameChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType1");
		}
		 long fileSize2  = (Long) basicDtls.get("fileSize2");
		 
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = nameChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {	
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 log.info("Exception raised in insertNameChangeDtls"+e);
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
	}
	
	public String insertGenderChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
		}
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		 long fileSize2  = (Long) basicDtls.get("fileSize2");
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = genderChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 log.info("Exception raised in insertGenderChangeDtls"+e);
		 
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
	}
	
	
	public String insertAppealChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
		}
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		 long fileSize2  = (Long) basicDtls.get("fileSize2");
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = AppelateInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 log.info("Exception raised in insertGenderChangeDtls"+e);
		 
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
		
}
	
	
	
	
	
	public String insertAgeChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
		}
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = ageChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 log.info("Exception raised in insertAgeChangeDtls"+e);
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
	}
	public String insertRelationNameChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="";
		 try
		 {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			
			if(basicDtls.size()>0)
			{
				issueReqId = (String)basicDtls.get("reqId");
			}
			 long fileSize2  = (Long) basicDtls.get("fileSize2");
			basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
			if(basicInsertStatus > 0)
			{
				modifyStatus = relChangeInsertDtls(basicDtls,con);         //insert to be modify details
				 if(modifyStatus > 0)
				  {
					 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
					 {
						 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
						 {
							 
							 if(fileSize2!=0)
							 {
								 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
								 {
									 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
								        con.commit();
								 }
								 else
								 {
									  con.rollback();
									  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
								 }
							 }
							 else
							 {
								status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
						        con.commit();
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
					 }
				  }
				  else
				  {
					  con.rollback();
					  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
				  }  
			 }
			 else
			  {
				  con.rollback();
				  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
			  }  
			
			
		 }
		 catch(Exception e)
		 {
			 log.info("Exception raised in insertRelationNameChangeDtls"+e);
			 try
			 {
				 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
			   con.rollback();
			 }
			 catch(Exception se)
			 {
				 se.printStackTrace();
			 }
			 
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

		 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
				return status;
	}
	public String insertFullNameChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
		}
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = fullNameChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 log.info("Exception raised in insertFullNameChangeDtls"+e);
		 e.printStackTrace();
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.  
		return status;
	}
	public String insertPersonStatusChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
		}
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = personStatusChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {	
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 System.out.println("Exception raised in insertPersonStatusChangeDtls"+e);
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
	}
	public String insertPartBReEntryStatusChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
		}
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = partBReEntryChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {	
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 System.out.println("Exception raised in insertPartBReEntryStatusChangeDtls"+e);
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
	}
	public String insertPhotoChangeDtls(HashMap basicDtls)
	{
		String status="",preference="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="",photoDocType="",aadharid="",sadaremId="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		
		if(basicDtls.size()>0)
		{
			issueReqId 		= (String)basicDtls.get("reqId");
			docType 		= (String)basicDtls.get("docType");
			preference 		= (String)basicDtls.get("preference");
			photoDocType 	= (String)basicDtls.get("photodocType");
			aadharid 		= (String)basicDtls.get("aadharid");
			sadaremId 		= (String)basicDtls.get("sadaremId");
		}
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = photoChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {
				      if(preference.equals("M"))
				      {
							if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true) 
							{ 
								
									  if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("photoDoc"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId+"/"+sadaremId+".jpg")==true)
									  {
										  if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
											 {
												 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
												 {
													 if(fileSize2!=0)
													 {
														 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
														 {
															 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
														        con.commit();
														 }
														 else
														 {
															  con.rollback();
															  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
														 }
													 }
													 else
													 {
														status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
												        con.commit();
													 }
												 }
												 else
												 {
													 con.rollback();
													 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
												 }
											 }
											 else
											 {
												 con.rollback();
												 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
											 }
									 }
									 else
									 {
										 con.rollback();
										 status="<font color='red'>07.Sorry we cannot process your request.Please try later !!</font>";
									 }
								
						  }
						 else
						 {
								  con.rollback();
								  status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
				      }
				      else
				      {
				    		 
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							  if(issueTrackingCommonUtil.saveAadharPhoto(aadharid,CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId+"/"+sadaremId+".jpg")==true)
								{
								  if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
									 {
									  if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
										 {
											 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
											 {
												 if(fileSize2!=0)
												 {
													 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
													 {
														 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
													        con.commit();
													 }
													 else
													 {
														  con.rollback();
														  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
													 }
												 }
												 else
												 {
													status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
											        con.commit();
												 }
											 }
											 else
											 {
												 con.rollback();
												 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
											 }
										 }
										 else
										 {
											 con.rollback();
											 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
										 }
								  }
								  else
								   {
											  con.rollback();
											  status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
								   }
								}
							 else
							 {
								 con.rollback();
								 status="<font color='red'>08.Sorry we cannot process your request.Please try later !!</font>";
							 }		
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
					 
				      }
								
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 log.info("Exception raised in insertPhotoChangeDtls"+e);
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
	 
		return status;
	}
	
	
	public String insertManualPhotoChangeDtls(HashMap basicDtls)
	{
		String status="",preference="",sadaremId="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="",photoDocType="";
	 try
	 {
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
			 
			photoDocType = (String)basicDtls.get("photodocType");
			sadaremId = (String)basicDtls.get("sadaremId");
		}
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		
		basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = manualphotoChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {
				     		if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true) 
								{
									  if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("photoDoc"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId+"\\"+sadaremId+".jpg")==true)
									  {
											 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
											 {
												 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
												 {
													 if(fileSize2!=0)
													 {
														 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
														 {
															 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
														        con.commit();
														 }
														 else
														 {
															  con.rollback();
															  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
														 }
													 }
													 else
													 {
														status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
												        con.commit();
													 }
												 }
												 else
												 {
													 con.rollback();
													 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
												 }
											 }
											 else
											 {
												 con.rollback();
												 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
											 }
									 }
									 else
									 {
										 con.rollback();
										 status="<font color='red'>07.Sorry we cannot process your request.Please try later !!</font>";
									 }
						  }
						 else
						 {
								  con.rollback();
								  status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
				    
				       
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 log.info("Exception raised in insertManualPhotoChangeDtls"+e);
		 e.printStackTrace();
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
	}
	
	
	
	
	
	public String relationChangeInsertDtls()
	{
		String status="";
		
		lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
              "(tkt_req_id,tkt_type_id,sadarem_id,relation_name,relation_type,relation_telugu_name\n"+
              "attach_doc_one,is_active,req_ip_address,created_by,created_date)\n";
		
		return status;
	}
	
	
	public String sadaremIdDeletiondtls(HashMap basicDtls)
	{
		{
			String status="";
			Connection con = null;
			String issueReqId ="",docType="";
			if(basicDtls.size()>0)
			{
				issueReqId = (String)basicDtls.get("reqId");
				docType = (String)basicDtls.get("docType");
			}
			int basicInsertStatus=0,modifyStatus=0;
			 long fileSize2  = (Long) basicDtls.get("fileSize2");
			 
			 try
			 {
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = addressChangeInsertDtls(basicDtls,con);         //insert to be modify details
					 if(modifyStatus > 0)
					  {
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
							 {
								 
								 if(fileSize2!=0)
								 {
									 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
									 {
										 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
									        con.commit();
									 }
									 else
									 {
										  con.rollback();
										  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
									 }
								 }
								 else
								 {
									status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
								 }
							 }
							 else
							 {
								 con.rollback();
								 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
					  }
					  else
					  {
						  con.rollback();
						  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
						  
					  }  
				 }
				 else
				  {
					  con.rollback();
					  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
				  }  
				
				
			 }
			 catch(Exception e)
			 {
				 log.info("Exception raised in insertAddressChangeDtls"+e);
				 try
				 {
					 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
				   con.rollback();
				 }
				 catch(Exception se)
				 {
					 se.printStackTrace();
				 }
				 
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
			 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
			return status;
	}
	}
	
	public String insertAddressChangeDtls(HashMap basicDtls)
	{
			String status="";
			Connection con = null;
			String issueReqId ="",docType="";
			if(basicDtls.size()>0)
			{
				issueReqId = (String)basicDtls.get("reqId");
				docType = (String)basicDtls.get("docType");
			}
			int basicInsertStatus=0,modifyStatus=0;
			 long fileSize2  = (Long) basicDtls.get("fileSize2");
			 
			 try
			 {
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = addressChangeInsertDtls(basicDtls,con);         //insert to be modify details
					 if(modifyStatus > 0)
					  {
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
							 {
								 
								 if(fileSize2!=0)
								 {
									 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
									 {
										 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
									        con.commit();
									 }
									 else
									 {
										  con.rollback();
										  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
									 }
								 }
								 else
								 {
									status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
								 }
							 }
							 else
							 {
								 con.rollback();
								 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
					  }
					  else
					  {
						  con.rollback();
						  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
						  
					  }  
				 }
				 else
				  {
					  con.rollback();
					  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
				  }  
				
				
			 }
			 catch(Exception e)
			 {
				 log.info("Exception raised in insertAddressChangeDtls"+e);
				 try
				 {
					 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
				   con.rollback();
				 }
				 catch(Exception se)
				 {
					 se.printStackTrace();
				 }
				 
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
			 

			 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
			 
			 
			return status;
	}
	
	
	
	
	public String getFlagDesc(String flagId)
	{
		String qry="",flagDesc="";
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
		qry="select tkt_flag_desc from tkt_status_flag_master where tkt_flag_id=?";
		
		 tempList = new ArrayList();		
         tempList.add("S");
         tempList.add(flagId);
         paramList.add(tempList);
   
		flagDesc =DataAccess.getReturnResultByPstmt(qry, paramList);
		
		//flagDesc = DataAccess.getReturnResult(qry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flagDesc;
	}
	 
	
	public String getRaisedIssuesCount(String sadaremId,String issueType)
	{
		String lStrQuery="",issueCount="";
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{ 
			lStrQuery= " select "
					+ " case when a.decision_flag like '%R%' then 'Y' \n"
							+ " when a.decision_flag like 'HOLD' then 'Y' \n"
							+ " else a.is_final end from\n"+
			"(\n"+
			" select distinct decision_flag,is_final from tkt_status_flow_mapping where decision_flag in\n"+
			" ( \n"+
			"select top(1) status_flag status from  tkt_request_master where sadarem_id=? and tkt_type_id=? order by created_date desc\n"+
			")  and tkt_type_id=?\n"+
			")a";
		
		tempList.add("S");
		tempList.add(sadaremId);
		paramList.add(tempList); 
		tempList = new ArrayList();
		
		tempList.add("S");
		tempList.add(issueType);
		paramList.add(tempList); 
		tempList = new ArrayList();
		
		tempList.add("S");
		tempList.add(issueType);
		paramList.add(tempList); tempList = new ArrayList();
		
		  issueCount = DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return issueCount;
	} 
	public ArrayList getPendingIssueDtlsList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus)
	{
		ArrayList pendingIssueDtlsList  = new ArrayList();
		
		CommonIssueTrackingDAOImpl comobj= new CommonIssueTrackingDAOImpl();
		
		try
		{
			ArrayList paramList	 =	new ArrayList();
			ArrayList tempList	 =  null;
			
			String statusFlag = "",lStrQuery="";
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
		
			statusFlag = statusFlag.replaceAll("'","");
			
			lStrQuery = " SELECT t.tkt_req_id,t.sadarem_id,a.district_name,b.Mandal_name \n";
			
			if("P".equals(issueReqStatus))
			{
			   lStrQuery += " ,DATEDIFF(day,d.created_date,getdate()) AS DiffDate \n"; 
			}
			else if("A".equals(issueReqStatus))	
			{
				lStrQuery += " ,isnull(convert(varchar(20),t.updated_date,103),'-') AS DiffDate,case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end \n";
			}
			else if("R".equals(issueReqStatus))
			{
				lStrQuery += " ,isnull(convert(varchar(20),t.updated_date,103),'-')  AS DiffDate,case when  t.status_remarks='null' or len(t.status_remarks)=0 then '-' else t.status_remarks   end \n";
			}
			lStrQuery += " FROM tkt_request_master(nolock) t,tbldistrict_details(nolock) a, tblmandal_details(nolock) b \n"+
						" ,tkt_request_tobe_modify_dtls(nolock) d \n"+
						" WHERE t.dist_id=a.district_id AND t.dist_id=b.district_id AND \n"+
						" t.mandal_id=b.mandal_id AND t.tkt_req_id=d.tkt_req_id AND\n"+
						" t.tkt_type_id=? AND t.dist_id=?  AND t.status_flag=? AND t.is_active='Y' order by t.tkt_req_id\n";
			
			tempList =  new ArrayList();
			tempList.add("S");
			tempList.add(tkt_type_id);
			paramList.add(tempList);
			
			tempList =  new ArrayList();
			tempList.add("S");
			tempList.add(districtId);
			paramList.add(tempList);
			
			tempList =  new ArrayList();
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
	public ArrayList getmyReqList(String districtId,String mandalId,String tkt_type_id,int roleID,String issueReqStatus,String username)
	{
		ArrayList resultList  = new ArrayList();
		
		CommonIssueTrackingDAOImpl comobj= new CommonIssueTrackingDAOImpl();
		
		try
		{
			
			
			ArrayList paramList	 =	new ArrayList();
			ArrayList tempList	 =  null;
			
			String statusFlag = "",lStrQuery="";
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
		
			statusFlag = statusFlag.replaceAll("'","");
			
			lStrQuery = " SELECT t.tkt_req_id,t.sadarem_id,b.Mandal_name,c.Village_name,e.tkt_flag_status \n";
			
			/*if("P".equals(issueReqStatus))
			   lStrQuery += " ,DATEDIFF(day,d.created_date,getdate()) AS DiffDate \n"; 
			else if("A".equals(issueReqStatus))	
				lStrQuery += " ,isnull(convert(varchar(20),t.updated_date,103),'-') AS DiffDate\n";
			else if("R".equals(issueReqStatus))
				lStrQuery += " ,isnull(convert(varchar(20),t.updated_date,103),'-')  AS DiffDate \n";*/
			if("-1".equals(tkt_type_id))
			{
				lStrQuery +=",mst.tkt_type_name \n";
			}
			
			lStrQuery += "\n"+
					"FROM tkt_request_master(nolock) t,tbldistrict_details(nolock) a, tblmandal_details(nolock) b \n"+
						" ,tblvillage_details(nolock) c,tkt_request_tobe_modify_dtls(nolock) d ,tkt_status_flag_master(nolock) e,tkt_type_master mst \n"+
						" WHERE t.dist_id=a.district_id AND t.dist_id=b.district_id AND \n"+
						" t.mandal_id=b.mandal_id AND t.tkt_req_id=d.tkt_req_id AND\n"+
						"t.dist_id=c.district_id AND t.mandal_id=c.mandal_id AND t.village_id=c.village_id AND t.tkt_type_id = mst.tkt_type_id \n";
						if(!"-1".equals(tkt_type_id))
						{	
							lStrQuery +="AND t.tkt_type_id=? \n";
							
							tempList =  new ArrayList();
							tempList.add("S");
							tempList.add(tkt_type_id);
							paramList.add(tempList);
							
						}
						
						if(roleID!=102 && roleID!=103)
						{
						lStrQuery +=" AND t.dist_id=? \n";
						
						}
					lStrQuery +=" AND  t.status_flag=e.tkt_flag_id \n"+
						" AND t.requested_by=? AND t.is_active='Y' order by t.tkt_req_id\n";
			
					if(roleID!=102 && roleID!=103)
					{
						tempList =  new ArrayList();
						tempList.add("S");
						tempList.add(districtId);
						paramList.add(tempList);
					}
						tempList =  new ArrayList();
						tempList.add("S");
						tempList.add(username);
						paramList.add(tempList);
			
						//System.out.println("jjjjjjjj"+lStrQuery+"       "+paramList);
						resultList = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
			 	
		}
		catch(SQLException sqle)
		{
			System.out.println("SQLException in CommonIssueApprovalDAOImpl @ getmyReqList :: Error Code : "+sqle.getErrorCode());
			sqle.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Exception in CommonIssueApprovalDAOImpl @  getmyReqList"+e+" \n" + e.getLocalizedMessage());
			e.printStackTrace();
			
		}
		return resultList;
	}
	public String insertCauseOfDisabilityChangeDtls(HashMap basicDtls)
	{
				String status="";
				Connection con = null;
				int basicInsertStatus=0,modifyStatus=0;
			 try
			 {
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				String issueReqId ="",docType="";
				if(basicDtls.size()>0)
				{
					issueReqId = (String)basicDtls.get("reqId");
				}
				basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = causeOfDisabilityChangeInsertDtls(basicDtls,con);            //insert to be modify details
					 if(modifyStatus > 0)
					  {
						 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					     con.commit();
					  }
					  else
					  {
						  con.rollback();
						  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
					  }  
				}
				 else
				  {
					  con.rollback();
					  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
				  }  
			}
			 catch(Exception e)
			 {
				 log.info("Exception raised in insertCauseOfDisabilityChangeDtls"+e);
				 try
				 {
					 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
				   con.rollback();
				 }
				 catch(Exception se)
				 {
					 se.printStackTrace();
				 }
				 
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
			 return status;
	  }
	
	
	public String insertaadharChangeDtls(HashMap basicDtls)
	{
				String status="";
				Connection con = null;
				int basicInsertStatus=0,modifyStatus=0;
				String issueReqId ="",docType="";
			 try
			 {
				 con = DBConnection.getConnection();
					con.setAutoCommit(false);
					if(basicDtls.size()>0)
					{
						issueReqId = (String)basicDtls.get("reqId");
						docType = (String)basicDtls.get("docType");
					}
				long fileSize2  = (Long) basicDtls.get("fileSize2");
				basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = aadharChangeInsertDtls(basicDtls,con);            //insert to be modify details
					 if(modifyStatus > 0)
					  {
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
							 {
								 
								 if(fileSize2!=0)
								 {
									 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
									 {
										 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
									        con.commit();
									 }
									 else
									 {
										  con.rollback();
										  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
									 }
								 }
								 else
								 {
									status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
								 }
							 }
							 else
							 {
								 con.rollback();
								 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
					  }
					  else
					  {
						  con.rollback();
						  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
					  }  
				}
				 else
				  {
					  con.rollback();
					  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
				  }  
				
				
			 }
			 catch(Exception e)
			 {
				 log.info("Exception raised in insertaadharChangeDtls"+e);
				 try
				 {
					 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
				   con.rollback();
				 }
				 catch(Exception se)
				 {
					 se.printStackTrace();
				 }
				 
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

			 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
			 
			 return status;
	  }





	public ArrayList searchForSadaremDetails(String sadaremID)
	{
		ArrayList resultList = new ArrayList();
		String lStrQuery 		= "";
    	try
    	{
    		
    		if((sadaremID!=null && sadaremID.length()!=0 ))
    		{
    			
    		ArrayList paramList = new ArrayList();
    		ArrayList tempList	= new ArrayList();
    		
    		lStrQuery=	"SELECT \n"+
    					"SADAREM_ID,PERSON_SURNAME,PERSON_NAME,REL_TYPE+' '+VW.RELATION_NAME RELATION_DETAILS, \n"+
    					"PERSON_DOB,PERSON_GENDER,PERSON_LIVE_STATUS,PERSON_CONTACTNO,TBL.HOUSE_NUMBER,VW.DISTRICT_NAME,VW. MANDAL_NAME,LTRIM(RTRIM(VW. VILLAGE_NAME))VILLAGE_NAME,\n" +
    					"VW.HABITATION_NAME,VW.districtid,VW.mandalid,VW.villageid,VW.habtationid,TBl.PIN_CODE,rel_type,VW.relation_name,reltype.relation_id,VW.edu_id \n"+
    					"FROM SADAREM_VIEW_COMPLETE_DETAILS VW,TBLPERSON_PERSONAL_DETAILS TBL  WITH (NOLOCK),sadarem_gender_relation_type_mapping reltype  WITH(NOLOCK) \n" +
    					"WHERE PERSON_CODE=SADAREM_ID AND ISNULL(TBL.Relationship,0)=reltype.relation_id AND ISNULL(TBL.gender,0)=reltype.gender_id \n";
    		
    		
    		if(sadaremID.length()>0)
    		{
    			lStrQuery+="AND SADAREM_ID=? ";
    		
				paramList = new ArrayList();
        		tempList	= new ArrayList();
        		tempList.add("S");
        		tempList.add(sadaremID);
        		paramList.add(tempList);
        	
    		}
    		
		  	log.info("searchForSadaremDetails : "+lStrQuery+"\n ParamList : "+paramList);
		  	//System.out.println("lStrQuery"+lStrQuery);
		   //System.out.println("searchForSadaremDetails : "+lStrQuery+"\n ParamList : "+paramList);
		  	
		  	resultList=DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
    		}
    	
    	}    
		 catch(SQLException sqle)
		 {    
			 log.info("SQLException in CommonDAOImpl  :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
			 sqle.printStackTrace();
		 }
		 catch(Exception lexp)
		 {
			 log.info("Exception in CommonDAOImpl : "+lexp.getLocalizedMessage());
			 lexp.printStackTrace();
		 }
    	return resultList;
	} 
	public ArrayList getproofdoucments()
	{
		ArrayList resultList= new ArrayList();
		String qry="";
		try
		{
			qry="select proof_type_id,proof_type_name from proof_type_master where is_active='Y'";
			
			resultList=(ArrayList)DataAccess.pickData(qry, false, false);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
		
	}
	
	
	public int insertProofDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		String status="";
		PreparedStatement lPstmt = null;
		int result =0;
		String issueReqId ="",docType="";
		if(nameChangModifyDtls.size()>0)
		{
			issueReqId = (String)nameChangModifyDtls.get("reqId");
			docType = (String)nameChangModifyDtls.get("docType");
		}
		try
		{
			lStrQuery =  "insert into  tkt_req_proof_details\n"+
	              "(tkt_req_id,proof_type_id,proof_number,\n"+
	              "proof_doc_path,proof_doc_type,created_by,created_date)\n"+
	              "values(?,?,?,?,?,?,getdate())";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("proofTypeID") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("proofId") );
		    lPstmt.setString(4,(String)nameChangModifyDtls.get("filePath") );
		    lPstmt.setString(5,(String)nameChangModifyDtls.get("docType") );
		    lPstmt.setString(6,(String)nameChangModifyDtls.get("reqBy") ); 
		    
		  	
		  result = lPstmt.executeUpdate();
		  
		}
		catch(Exception e)
		{
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
			   log.info("Error occurred in insertProofDtls"+e);
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public String insertopenAgeChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;
		String issueReqId ="",docType="";
	 try
	 {
		 CommonDAO comObj = new CommonDAOImpl();
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
		}
		basicInsertStatus = insertOpenBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = openAgeChangeInsertDtls(basicDtls,con);            //insert to be modify details
			if(modifyStatus > 0)
			  {	
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							        comObj.sendTKTRequestStatusbySMS(issueReqId, (String)basicDtls.get("reqBy"));
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>01. Error occured while raising a request.Please try later !!</font>"; 
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						  status="<font color='red'>02. Error occured while raising a request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					  status="<font color='red'>03. Error occured while raising a request.Please try later !!</font>";
				 }
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>04. Error occured while raising a request.Please try later !!</font>"; 
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>05. Error occured while raising a request.Please try later !!</font>"; 
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		log.info("Exception raised in insertopenAgeChangeDtls : "+e);
		 e.printStackTrace();
		 try
		 {
			  status="<font color='red'>06. Error occured while raising a request.Please try later !!</font>"; 
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
	}
	
	public int openAgeChangeInsertDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0,proofInsertStatus=0;
		  String issueReqId ="",docType="",dob="";
		  HashMap proofDtls = new HashMap();
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");	
		  
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
				docType = (String)nameChangModifyDtls.get("docType");
				dob = (String)nameChangModifyDtls.get("dob");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,dob,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks,contactNo,altcontactNo)\n"+
	              "values(?,?,?,CONVERT(date,'"+dob+"',103),'Y',?,?,getdate(),?,?,?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress") )); 
		    lPstmt.setString(5,(String)nameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(6,(String)nameChangModifyDtls.get("remarks") ); 
		    lPstmt.setString(7,(String)nameChangModifyDtls.get("contactNo") );
		    lPstmt.setString(8,(String)nameChangModifyDtls.get("altcontactNo") );
		    
		  result = lPstmt.executeUpdate();
		  
		  
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("sadaremId"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("sadaremId"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
						
						
			    	}
			    	else
			    	{
			    		result = proofInsertStatus;
			    	}
				}
		    	
		    }
		  
		}
		catch(Exception e)
		{
			log.info("Exception raised in openAgeChangeInsertDtls"+e);
			e.printStackTrace();
		}
		
		return result;
	}

	
	public String insertOpenNameChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0;boolean smsstatus=false;
		String issueReqId ="",docType="";
	 try
	 {
		 CommonDAO comObj = new CommonDAOImpl();
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		OpenIssueTrackingDAO openTrack= new  OpenIssueTrackingDAOImpl();
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
		}
		basicInsertStatus = insertOpenBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = openNameChangeDtls(basicDtls,con);            //insert to be modify details
			if(modifyStatus > 0)
			  {	
				
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							         smsstatus =   comObj.sendTKTRequestStatusbySMS(issueReqId, (String)basicDtls.get("reqBy"));
							        
							  }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
				
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 System.out.println("Exception raised in insertOpenNameChangeDtls"+e);
		 log.info("Exception raised in insertOpenNameChangeDtls"+e);
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
	 
		return status;
		}
	
	public int openNameChangeDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		  String status="";
		  PreparedStatement lPstmt = null;
		  int result =0,proofInsertStatus=0;
		  HashMap proofDtls = new HashMap();
		  
		  String issueReqId ="",docType="";
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");	
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
				docType = (String)nameChangModifyDtls.get("docType");
			}
			 
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,person_name,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks,contactNo,altcontactNo)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?,?,?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)nameChangModifyDtls.get("fname") ); 
		    
		    lPstmt.setString(5,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress")) ); 
		    lPstmt.setString(6,(String)nameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(7,(String)nameChangModifyDtls.get("remarks") );
		    lPstmt.setString(8,(String)nameChangModifyDtls.get("contactNo") );
		    lPstmt.setString(9,(String)nameChangModifyDtls.get("altcontactNo") );
		    
		    result = lPstmt.executeUpdate();
		    if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    	else
			    	{
			    		result = proofInsertStatus;
			    	}
				}
		    	
		    }
		  
		}
		catch(Exception e)
		{
			log.info("Exception raised in openNameChangeDtls"+e);
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String generateopenIssueReqId(String issueTypeId)
	{
		String reqId = "",qry="";
		String Idformat = "";
		DateFormat dformat = new SimpleDateFormat("ddMMyy");
		Date d = new Date();
		String curDate = dformat.format(d); 
		Idformat = "O"+issueTypeId+curDate;		
		 ArrayList paramList = new ArrayList();
		 ArrayList tempList	= new ArrayList();
	  try
	  {
		lStrQuery = "select '"+Idformat+"'+RIGHT(REPLICATE('0', 4) + CAST(( COUNT(*)+1) AS VARCHAR(4)), 5) from tkt_request_master where substring(tkt_req_id,0,12)=?";
		
		tempList.add("S");
		tempList.add(Idformat);
		paramList.add(tempList);
		
		reqId = DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
		  log.info("Exception raised in generateopenIssueReqId"+e);
	  }
		return reqId;
	}

    public String checkOTPforSadaremID(String sadaremId,String otp) 
	{
	    //String tktDecisionFlag = "";
	    String DecisionFlag = "";
	    ArrayList paramList = new ArrayList();
		ArrayList tempList	= new ArrayList();
		    try
		    {
		          String lStrQuery ="SELECT COUNT(*) FROM OPENISSUE_OTP_MASTER WHERE FK_SADAREM_ID=? AND DELIVERED_FLAG='Y' AND ACTIVE_FLAG='Y' AND OTP=?";
		        	  
	        		tempList.add("S");
	        		tempList.add(sadaremId);
	        		paramList.add(tempList);
	        		
	        		tempList	= new ArrayList();
	        		tempList.add("S");
	        		tempList.add(otp);
	        		paramList.add(tempList);
		            
		            DecisionFlag = DataAccess.getReturnResultByPstmt(lStrQuery,paramList);
		    }    
		    catch(Exception lexp)
		    {
		  	  log.info("Exception in tktCommonDAOImpl @ checkOTPforSadaremID : "+lexp.getLocalizedMessage());
		    }
	        return DecisionFlag.equals("1")?"Y":"N";
	  }
	
	public String insertOpenGenderChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		int basicInsertStatus=0,modifyStatus=0,smsstatus=0;
		String issueReqId ="",docType="";
	 try
	 {
		 CommonDAO comObj = new CommonDAOImpl();
		con = DBConnection.getConnection();
		con.setAutoCommit(false);
		long fileSize1  = (Long) basicDtls.get("fileSize1");
		OpenIssueTrackingDAO openTrack= new  OpenIssueTrackingDAOImpl();
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType1");
		}
		long fileSize2  = (Long) basicDtls.get("fileSize2");
		// System.out.println("fileSize2"+fileSize2);
		basicInsertStatus = insertOpenBasicDtls(basicDtls,con);         //insert basic details 
		if(basicInsertStatus > 0)
		{
			modifyStatus = opengenderChangeInsertDtls(basicDtls,con);            //insert to be modify details
			 if(modifyStatus > 0)
			  {
				
				 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
				 {
					 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
					 {
						 
						 if(fileSize2!=0)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
							 {
								 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
							      comObj.sendTKTRequestStatusbySMS(issueReqId, (String)basicDtls.get("reqBy"));
							 }
							 else
							 {
								  con.rollback();
								  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
					        con.commit();
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
					 }
				 }
				 else
				 {
					 con.rollback();
					 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
				 }
				
			  }
			  else
			  {
				  con.rollback();
				  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
			  }  
		}
		 else
		  {
			  con.rollback();
			  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
		  }  
		
		
	 }
	 catch(Exception e)
	 {
		 System.out.println("Exception raised in insertOpenGenderChangeDtls"+e);
		 log.info("Exception raised in insertOpenGenderChangeDtls"+e);
		 try
		 {
			 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
		   con.rollback();
		 }
		 catch(Exception se)
		 {
			 log.info("Exception raised in insertOpenGenderChangeDtls"+e);
			 se.printStackTrace();
		 }
		 
	 }
	finally{
		
		try 
		{
			con.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
		return status;
	}
	
	
	public int opengenderChangeInsertDtls(HashMap nameChangModifyDtls,Connection lcon)
	{
		String status="";
		  PreparedStatement lPstmt = null;
		  int result =0,proofInsertStatus=0;
		  String issueReqId ="",docType="";
		  HashMap proofDtls = new HashMap();
		  long fileSize1  = (Long) nameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) nameChangModifyDtls.get("fileSize2");
			if(nameChangModifyDtls.size()>0)
			{
				issueReqId = (String)nameChangModifyDtls.get("reqId");
				docType = (String)nameChangModifyDtls.get("docType");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,gender,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks,contactNo,altcontactNo)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?,?,?)";
		
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)nameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)nameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)nameChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)nameChangModifyDtls.get("genderId") );
		    lPstmt.setString(5,(String)nameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(6,CommonUtility.checkNullObject((String)nameChangModifyDtls.get("ipaddress")) ); 
		    lPstmt.setString(7,(String)nameChangModifyDtls.get("remarks") );
		    lPstmt.setString(8,(String)nameChangModifyDtls.get("contactNo") );
		    lPstmt.setString(9,(String)nameChangModifyDtls.get("altcontactNo") );
		    
		    
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",nameChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath1")+"."+nameChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", nameChangModifyDtls.get("sadaremId"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",nameChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",nameChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",nameChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",nameChangModifyDtls.get("filePath2")+"."+nameChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",nameChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", nameChangModifyDtls.get("sadaremId"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    	else
			    	{
			    		result = proofInsertStatus;
			    	}
				}
		    }
		}
		catch(Exception e)
		{
			log.info("Exception raised in opengenderChangeInsertDtls"+e);
			e.printStackTrace();
		}
		
		return result;
	}	
	
	public int openaddressChangeInsertDtls(HashMap addressChangModifyDtls,Connection lcon)
	{
		String status="";
		 
		  PreparedStatement lPstmt = null;
		  int result =0,proofInsertStatus=0;
		  String issueReqId ="",docType="";
		  HashMap proofDtls = new HashMap();
		  long fileSize1  = (Long) addressChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) addressChangModifyDtls.get("fileSize2");
			if(addressChangModifyDtls.size()>0)
			{
				issueReqId = (String)addressChangModifyDtls.get("reqId");
				docType = (String)addressChangModifyDtls.get("docType");
			}
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,dist_id,mandal_id,village_id,hab_id,house_no,pin_code,\n" +
	              " is_active,req_ip_address,created_by,created_date,remarks,contactNo,altcontactNo)\n"+
	              "values(?,?,?,?,?,?,?,?,?,'Y',?,?,getdate(),?,?,?)";
			//lcon = DBConnection.getConnection();
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)addressChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)addressChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)addressChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)addressChangModifyDtls.get("distName") );
		    lPstmt.setString(5,(String)addressChangModifyDtls.get("manName") );
		    lPstmt.setString(6,(String)addressChangModifyDtls.get("villName") );
		    lPstmt.setString(7,(String)addressChangModifyDtls.get("habName") );
		    lPstmt.setString(8,(String)addressChangModifyDtls.get("houseNo") );
		    lPstmt.setString(9,(String)addressChangModifyDtls.get("pincode") ); 
		    
		    lPstmt.setString(10,CommonUtility.checkNullObject((String)addressChangModifyDtls.get("ipaddress")) ); 
		    lPstmt.setString(11,(String)addressChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(12,(String)addressChangModifyDtls.get("remarks") );
		    lPstmt.setString(13,(String)addressChangModifyDtls.get("contactNo") );
		    lPstmt.setString(14,(String)addressChangModifyDtls.get("altcontactNo") );
		    
		     
		   result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",addressChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",addressChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",addressChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",addressChangModifyDtls.get("filePath1")+"."+addressChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",addressChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", addressChangModifyDtls.get("sadaremId"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,lcon);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",addressChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",addressChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",addressChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",addressChangModifyDtls.get("filePath2")+"."+addressChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",addressChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", addressChangModifyDtls.get("sadaremId"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,lcon);
					}
			    	else
			    	{
			    		result = proofInsertStatus;
			    	}
				}
		    	
		    }
		   
		} 
		catch(Exception e)
		{
			log.info("Exception raised in openaddressChangeInsertDtls"+e);
			System.out.println("exception raised in addressChangeInsertDtls method " );
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public String insertOpenAddressChangeDtls(HashMap basicDtls)
	{
				String status="";
				Connection con = null;
				CommonDAO comObj = new CommonDAOImpl();
				String issueReqId ="",docType="";
			try
			{					
				int basicInsertStatus=0,modifyStatus=0,smsstatus=0;
				long fileSize1  = (Long) basicDtls.get("fileSize1");
				long fileSize2  = (Long) basicDtls.get("fileSize2");
				OpenIssueTrackingDAO openTrack= new  OpenIssueTrackingDAOImpl();
				if(basicDtls.size()>0)
				{
					issueReqId = (String)basicDtls.get("reqId");
					docType = (String)basicDtls.get("docType");
				}
			 con = DBConnection.getConnection();
				con.setAutoCommit(false);
				basicInsertStatus = insertOpenBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = openaddressChangeInsertDtls(basicDtls,con);            //insert to be modify details
					 if(modifyStatus > 0)
					  {
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
							 {
								 
								 if(fileSize2!=0)
								 {
									 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
									 {
										 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
									        con.commit();
									      comObj.sendTKTRequestStatusbySMS(issueReqId, (String)basicDtls.get("reqBy"));
									 }
									 else
									 {
										  con.rollback();
										  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
									 }
								 }
								 else
								 {
									status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
								 }
							 }
							 else
							 {
								 con.rollback();
								 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
					   
					  }
					  else
					  {
						  con.rollback();
						  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
					  }  
				}
				else
				  {
					  con.rollback();
					  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
				  }  
				
				
			 }
			 catch(Exception e)
			 {
				 System.out.println("Exception raised in insertOpenAddressChangeDtls"+e);
				 log.info("Exception raised in insertOpenAddressChangeDtls"+e);
				 try
				 {
					 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
				   con.rollback();
				 }
				 catch(Exception se)
				 {
					 se.printStackTrace();
				 }
				 
			 }
			finally{
				
				try 
				{
					con.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}

			 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
				return status;
	}	
	
	public String insertOpenRelNameChangeDtls(HashMap basicDtls)
	{
				String status="";
				Connection con = null;
				int basicInsertStatus=0,modifyStatus=0,smsstatus=0;
				CommonDAO comobj = new CommonDAOImpl();
				String issueReqId ="";
			 try
			 {
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				long fileSize1  = (Long) basicDtls.get("fileSize1");
				long fileSize2  = (Long) basicDtls.get("fileSize2");
				OpenIssueTrackingDAO openTrack= new  OpenIssueTrackingDAOImpl();
				if(basicDtls.size()>0)
				{
					issueReqId = (String)basicDtls.get("reqId");
				}
				basicInsertStatus = insertOpenBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = openrelnameChangeInsertDtls(basicDtls,con);         //insert to be modify details
					 if(modifyStatus > 0)
					  {
						
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
							 {
								 
								 if(fileSize2!=0)
								 {
									 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
									 {
										 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
									        con.commit();
									        comobj.sendTKTRequestStatusbySMS(issueReqId, (String)basicDtls.get("reqBy"));
									 }
									 else
									 {
										  con.rollback();
										  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
									 }
								 }
								 else
								 {
									status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
								 }
							 }
							 else
							 {
								 con.rollback();
								 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
						 
					  }
					  else
					  {
						  con.rollback(); 
						  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
					  }  
				 }
				 else
				  {
					  con.rollback();
					  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
				  }  
				
				
			 }
			 catch(Exception e)
			 {
				 log.info("Exception raised in insertOpenRelNameChangeDtls"+e);
				 try
				 {
					 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
				   con.rollback();
				 }
				 catch(Exception se)
				 {
					 se.printStackTrace();
				 }
				 
			 }
			finally{
				
				try 
				{
					con.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}

			 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
				return status;
	}
	public String insertOpenEduNameChangeDtls(HashMap basicDtls)
	{
				String status="";
				Connection con = null;
				int basicInsertStatus=0,modifyStatus=0,smsstatus=0;
				CommonDAO comobj = new CommonDAOImpl();
				String issueReqId ="";
			 try
			 {
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				long fileSize1  = (Long) basicDtls.get("fileSize1");
				long fileSize2  = (Long) basicDtls.get("fileSize2");
				OpenIssueTrackingDAO openTrack= new  OpenIssueTrackingDAOImpl();
				if(basicDtls.size()>0)
				{
					issueReqId = (String)basicDtls.get("reqId");
				}
				basicInsertStatus = insertOpenBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = openeducationChangeInsertDtls(basicDtls,con);         //insert to be modify details
					 if(modifyStatus > 0)
					  {
						
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
							 {
								 
								 if(fileSize2!=0)
								 {
									 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
									 {
										 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
									        con.commit();
									        comobj.sendTKTRequestStatusbySMS(issueReqId, (String)basicDtls.get("reqBy"));
									 }
									 else
									 {
										  con.rollback();
										  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
									 }
								 }
								 else
								 {
									status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
								 }
							 }
							 else
							 {
								 con.rollback();
								 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
						 
					  }
					  else
					  {
						  con.rollback(); 
						  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
					  }  
				 }
				 else
				  {
					  con.rollback();
					  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
				  }  
				
				
			 }
			 catch(Exception e)
			 {
				 log.info("Exception raised in insertOpenEduNameChangeDtls"+e);
				 try
				 {
					 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
				   con.rollback();
				 }
				 catch(Exception se)
				 {
					 se.printStackTrace();
				 }
				 
			 }
			finally{
				
				try 
				{
					con.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}

			 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
			 
				return status;
	}
	
	public int openrelnameChangeInsertDtls(HashMap relnameChangModifyDtls,Connection lcon)
	{
		
		  PreparedStatement lPstmt = null;
		  int result =0,proofInsertStatus=0;
		  HashMap proofDtls = new HashMap();
		  String issueReqId ="" ;
		  long fileSize1  = (Long) relnameChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) relnameChangModifyDtls.get("fileSize2");
			if(relnameChangModifyDtls.size()>0)
			{
				issueReqId = (String)relnameChangModifyDtls.get("reqId");
		    }
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,relation_name,relation_type,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks,contactNo,altcontactNo)\n"+
	              "values(?,?,?,?,?,'Y',?,?,getdate(),?,?,?)"; 
			 
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)relnameChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)relnameChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)relnameChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)relnameChangModifyDtls.get("relationname") );
		    lPstmt.setString(5,(String)relnameChangModifyDtls.get("relationtype") ); 
		    lPstmt.setString(6,CommonUtility.checkNullObject((String)relnameChangModifyDtls.get("ipaddress") )); 
		    lPstmt.setString(7,(String)relnameChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(8,(String)relnameChangModifyDtls.get("remarks") );
		    lPstmt.setString(9,(String)relnameChangModifyDtls.get("contactNo") );
		    lPstmt.setString(10,(String)relnameChangModifyDtls.get("altcontactNo") );
		    
		    result = lPstmt.executeUpdate();
			  if(result>0)
			    {
			    	if(fileSize1 != 0)
					{
			    		proofDtls.put("proofTypeID",relnameChangModifyDtls.get("proofTypeID1"));
			    		proofDtls.put("proofId",relnameChangModifyDtls.get("proofId1"));
			    		proofDtls.put("docType",relnameChangModifyDtls.get("docType1"));
			    		proofDtls.put("filePath",relnameChangModifyDtls.get("filePath1")+"."+relnameChangModifyDtls.get("docType1")); 
			    		proofDtls.put("fileDoc",relnameChangModifyDtls.get("fileDoc1"));
			    		proofDtls.put("reqBy", relnameChangModifyDtls.get("sadaremId"));
			    		proofDtls.put("reqId",issueReqId );
						
						result = insertProofDtls(proofDtls,lcon);
					
				    	if(result>0 && fileSize2 != 0)
				    	{
				    		proofDtls = new HashMap();
				    		proofDtls.put("proofTypeID",relnameChangModifyDtls.get("proofTypeID2"));
				    		proofDtls.put("proofId",relnameChangModifyDtls.get("proofId2"));
				    		proofDtls.put("docType",relnameChangModifyDtls.get("docType2"));
				    		proofDtls.put("filePath",relnameChangModifyDtls.get("filePath2")+"."+relnameChangModifyDtls.get("docType2")); 
				    		proofDtls.put("fileDoc",relnameChangModifyDtls.get("fileDoc2"));
				    		proofDtls.put("reqBy", relnameChangModifyDtls.get("sadaremId"));
				    		proofDtls.put("reqId",issueReqId );
				    		result = insertProofDtls(proofDtls,lcon);
						}
				    	else
				    	{
				    		result = proofInsertStatus;
				    	}
					}
			    	
			    }
		}
		catch(Exception e)
		{
			log.info("exception raised in openrelnameChangeInsertDtls method "+e );
			result=0;
		}
	return result;
	}
	public int openeducationChangeInsertDtls(HashMap educationChangModifyDtls,Connection lcon)
	{
		
		  PreparedStatement lPstmt = null;
		  int result =0,proofInsertStatus=0;
		  HashMap proofDtls = new HashMap();
		  String issueReqId ="" ;
		  long fileSize1  = (Long) educationChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) educationChangModifyDtls.get("fileSize2");
			if(educationChangModifyDtls.size()>0)
			{
				issueReqId = (String)educationChangModifyDtls.get("reqId");
		    }
		try{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,edu_id,\n"+
	              "is_active,req_ip_address,created_by,created_date,remarks,contactNo,altcontactNo)\n"+
	              "values(?,?,?,?,'Y',?,?,getdate(),?,?,?)"; 
			 
		    lPstmt = lcon.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)educationChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)educationChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)educationChangModifyDtls.get("sadaremId") );

		    lPstmt.setString(4,(String)educationChangModifyDtls.get("educationType") );
		    lPstmt.setString(5,CommonUtility.checkNullObject((String)educationChangModifyDtls.get("ipaddress")) ); 
		    lPstmt.setString(6,(String)educationChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(7,(String)educationChangModifyDtls.get("remarks") );
		    lPstmt.setString(8,(String)educationChangModifyDtls.get("contactNo") );
		    lPstmt.setString(9,(String)educationChangModifyDtls.get("altcontactNo") );
		    
		    result = lPstmt.executeUpdate();
			  if(result>0)
			    {
			    	if(fileSize1 != 0)
					{
			    		proofDtls.put("proofTypeID",educationChangModifyDtls.get("proofTypeID1"));
			    		proofDtls.put("proofId",educationChangModifyDtls.get("proofId1"));
			    		proofDtls.put("docType",educationChangModifyDtls.get("docType1"));
			    		proofDtls.put("filePath",educationChangModifyDtls.get("filePath1")+"."+educationChangModifyDtls.get("docType1")); 
			    		proofDtls.put("fileDoc",educationChangModifyDtls.get("fileDoc1"));
			    		proofDtls.put("reqBy", educationChangModifyDtls.get("sadaremId"));
			    		proofDtls.put("reqId",issueReqId );
						
						result = insertProofDtls(proofDtls,lcon);
					
				    	if(result>0 && fileSize2 != 0)
				    	{
				    		proofDtls = new HashMap();
				    		proofDtls.put("proofTypeID",educationChangModifyDtls.get("proofTypeID2"));
				    		proofDtls.put("proofId",educationChangModifyDtls.get("proofId2"));
				    		proofDtls.put("docType",educationChangModifyDtls.get("docType2"));
				    		proofDtls.put("filePath",educationChangModifyDtls.get("filePath2")+"."+educationChangModifyDtls.get("docType2")); 
				    		proofDtls.put("fileDoc",educationChangModifyDtls.get("fileDoc2"));
				    		proofDtls.put("reqBy", educationChangModifyDtls.get("sadaremId"));
				    		proofDtls.put("reqId",issueReqId );
				    		result = insertProofDtls(proofDtls,lcon);
						}
				    	else
				    	{
				    		result = proofInsertStatus;
				    	}
					}
			    	
			    }
		}
		catch(Exception e)
		{
			log.info("exception raised in openeducationChangeInsertDtls method "+e );
			result=0;
		}
	return result;
	} 
	public String checkAppelleteFormStatus(String sadaremid)
	{
		String status="",lStrQuery="";
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "select ViewEditMode from AppellateAuthorityandTemporary_RegistrationDetails where status='Active' and person_code=?";
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkSADAREMPersonLiveStatus of "+e);
		}
		
		return status;	
	}
	public String checkIsAssessedStatus(String sadaremid)
	{
		String status="",lStrQuery="";
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  " select * from tkt_request_tobe_modify_dtls with(nolock) where  tkt_type_id='S018'  and is_assessed='N' and sadarem_id=? and sadarem_id in "
					+ "( select t.sadarem_id from  tkt_request_master t, tkt_status_flow_mapping k "
					+ "	where t.tkt_type_id=k.tkt_type_id and t.status_flag=k.decision_flag and  "
					+ "	t.tkt_type_id='S018'  and k.request_type='A'  and  sadarem_id=? and k.is_final='Y' )";
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkIsAssessedStatus of "+e);
		}
		
		return status;	
	} 
	public String checkWhetherRaisedInPartBReentry(String sadaremid)
	{
		String status="",lStrQuery=""; 
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "select sadarem_id from tkt_request_master where tkt_type_id='S019' and status_flag not in ('3R4','4A2','4R2','5R4') and sadarem_id=?";
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkWhetherRaisedInPartBReentry of "+e);
		}
		
		return status;	
	} 
	public String checkWhetherRaisedInMulti(String sadaremid)
	{
		String status="", lStrQuery=""; 
		
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "select sadarem_id from tkt_request_master where tkt_type_id='S020' and status_flag not in ('3R4','4R3','6A5','6R5') and sadarem_id=?";
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			status = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info("Exception raised in tktCommonDAOImpl @ checkWhetherRaisedInPartBReentry of "+e);
		}
		
		return status;	
	} 
	
	
	public int getReassessmentStatus(String sadaremId,String catId)
	{
		int status=0;
		String sql="";
		try
		{
			  ArrayList paramList = new ArrayList();
			  ArrayList tempList = new ArrayList();
			  
			  sql =  "select case when COUNT(*)>=2 then 0 else 1 end  catcount  \n"+
					"from \n"+
					"(\n"+
					"select person_code,CategoryID,CategoryCount,TotalDisability disptotal,UPdated_Date from tblPerson_Disability_TotalValue_Details(nolock)\n"+ 
					"union \n"+
					"select person_code,CategoryID,CategoryCount,0 disptotal,UPdated_Date from tblRejectPerson_Details(nolock)\n"+
					") a where a.Person_Code=? and CategoryID=?";
			  
			  tempList.add("S");
			  tempList.add(sadaremId);
			  paramList.add(tempList);tempList = new ArrayList();
			  tempList.add("S");
			  tempList.add(catId);
			  paramList.add(tempList);
			  
			status = Integer.parseInt(DataAccess.getReturnResultByPstmt(sql, paramList));
		}
		catch(Exception e)
		{
			
		}
		return status;
	}

	public ArrayList getSadaremCommonDatawithColumnName(String sadaremid)
	{

		ArrayList sadaremData = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		String lStrQuery1="";
		
		 try
		 {
			          
			 
				lStrQuery1 =   " select sadarem_id,v.districtid,v.mandalid,v.villageid,v.habtationid,district_name,mandal_name,village_name,\n"+
					   "habitation_name,person_surname,person_name,v.personname_telugu,CONVERT(varchar(10),person_dob,103) dob,\n"+
						"ISNULL(type_of_proof,'-') proofdoc_type,ISNULL(v.proof_id,'-') proof_id,person_gender,rel_type,\n"+
						"v.relation_name,relation_telugu_name,ISNULL(person_contactno,'-')contact_no,\n"+
						"category_id,CategoryCount,v.person_status,person_live_status,dis_form_status,\n"+
						"reltype.relation_id,per.house_number,per.pin_code,v.disb_id,v.Disability_Name,\n"+
						"disp_min_per,person_disp_percent,cause_of_disp,temp_cert_expire_year, certificate_issue_date,\n"+
						"cert_expire_date,v.edu_id\n"+
                        "FROM\n"+
						"tblPerson_Personal_Details per WITH(NOLOCK) ,\n"+
						"sadarem_gender_relation_type_mapping reltype  WITH(NOLOCK),\n"+
						"sadarem_view_complete_details v WITH(NOLOCK) \n"+
						"WHERE\n"+
						"ISNULL(per.gender,0)=reltype.gender_id AND\n"+
						"ISNULL(per.Relationship,0)=reltype.relation_id AND\n"+
						"per.Person_Code=v.sadarem_id and v.sadarem_id=?  ";
				
				if(CommonUtility.checkNullObj(checkSADAREMLiveStatus(sadaremid))=="")
		          {
					lStrQuery1+=	 "and v.is_active='Y' ";
		          }
			         tempList.add("S");
			         tempList.add(sadaremid);
			         paramList.add(tempList);	
			        // System.out.println("basicdata-->"+lStrQuery1);   
					sadaremData = DataAccess.pickDataByPrepareStmt(lStrQuery1, paramList, true, false);
					 
		 }
		 catch(Exception e)
		 {
			log.info("Exception raised in tktCommonDAOImpl @ getSadaremCommonData"+e);
		 }
		
		return sadaremData;
	
	}	
	public String insertSadaremIDDeletionDtls(HashMap basicDtls)
	{
			String status="";
			Connection con = null;
			String issueReqId ="",docType="";
			if(basicDtls.size()>0)
			{
				issueReqId = (String)basicDtls.get("reqId");
				docType = (String)basicDtls.get("docType");
			}
			int basicInsertStatus=0,modifyStatus=0;
			 long fileSize2  = (Long) basicDtls.get("fileSize2");
			 
			 try
			 {
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = SadaremDeletioInsertDtls(basicDtls,con);         //insert to be modify details
					 if(modifyStatus > 0)
					  {
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
							 {
								 
								 if(fileSize2!=0)
								 {
									 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
									 {
										 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
									        con.commit();
									 }
									 else
									 {
										  con.rollback();
										  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
									 }
								 }
								 else
								 {
									status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
							        con.commit();
								 }
							 }
							 else
							 {
								 con.rollback();
								 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
						 }
					  }
					  else
					  {
						  con.rollback();
						  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
						  
					  }  
				 }
				 else
				  {
					  con.rollback();
					  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
				  }  
				
				
			 }
			 catch(Exception e)
			 {
				 log.info("Exception raised in insertAddressChangeDtls"+e);
				 try
				 {
					 status="<font color='red'>Sorry we cannot process your request.Please try later !!</font>";
				   con.rollback();
				 }
				 catch(Exception se)
				 {
					 se.printStackTrace();
				 }
				 
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
			 

			 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed.
			 
			 
			return status;
	}

	public int SadaremDeletioInsertDtls(HashMap sadaremIddeleteModifyDtls,Connection con)
	{
		String status="";
		 
		  PreparedStatement lPstmt = null;
		  int result =0;
		  HashMap proofDtls = new HashMap();
			  
		  String issueReqId ="",docType="";
		  long fileSize1  = (Long) sadaremIddeleteModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) sadaremIddeleteModifyDtls.get("fileSize2");	
			if(sadaremIddeleteModifyDtls.size()>0)
			{
				issueReqId = (String)sadaremIddeleteModifyDtls.get("reqId");
				docType = (String)sadaremIddeleteModifyDtls.get("docType");
			}
		try
		{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,dist_id,mandal_id,village_id,hab_id,house_no,pin_code,\n" +
	              " is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,?,?,?,?,?,'Y',?,?,getdate(),?)";
			//lcon = DBConnection.getConnection();
		    lPstmt = con.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)sadaremIddeleteModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)sadaremIddeleteModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)sadaremIddeleteModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)sadaremIddeleteModifyDtls.get("distName") );
		    lPstmt.setString(5,(String)sadaremIddeleteModifyDtls.get("manName") );
		    //lPstmt.setString(6,(String)nameChangModifyDtls.get("panName") );
		    lPstmt.setString(6,(String)sadaremIddeleteModifyDtls.get("villName") );
		    lPstmt.setString(7,(String)sadaremIddeleteModifyDtls.get("habName") );
		    lPstmt.setString(8,(String)sadaremIddeleteModifyDtls.get("houseNo") );
		    lPstmt.setString(9,(String)sadaremIddeleteModifyDtls.get("pincode") );
		    lPstmt.setString(10,CommonUtility.checkNullObject((String)sadaremIddeleteModifyDtls.get("ipaddress")) );  		  
		    lPstmt.setString(11,(String)sadaremIddeleteModifyDtls.get("reqBy") ); 
		    lPstmt.setString(12,(String)sadaremIddeleteModifyDtls.get("remarks") );
                  //lPstmt.setString(13,(String)sadaremIddeleteModifyDtls.get("MobNo") ); 
		     
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",sadaremIddeleteModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",sadaremIddeleteModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",sadaremIddeleteModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",sadaremIddeleteModifyDtls.get("filePath1")+"."+sadaremIddeleteModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",sadaremIddeleteModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", sadaremIddeleteModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,con);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",sadaremIddeleteModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",sadaremIddeleteModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",sadaremIddeleteModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",sadaremIddeleteModifyDtls.get("filePath2")+"."+sadaremIddeleteModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",sadaremIddeleteModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", sadaremIddeleteModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,con);
						
						
			    	}
			    	 
				}
		    	
		    }
		   
		}
		catch(Exception e){
			System.out.println("exception raised in addressChangeInsertDtls method " );
			log.info("Exception raised in addressChangeInsertDtls"+e);
			e.printStackTrace();
		}
		
		
		return result;
	}
	public String getIssueName(String issuetype)
	{
		ArrayList issueTypes = new ArrayList();
		
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			lStrQuery =  "select tkt_type_name from tkt_type_master  \n"+
			      "where tkt_type_id=? and is_active='Y' ";
			
			tempList = new ArrayList();
			tempList.add("S");
			tempList.add(issuetype);
			paramList.add(tempList);
			
		    issueTypes = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		    		    
		}
		catch(Exception e)
		{
			log.info("Exception raised in tktCommonDAOImpl @ getIssueName of "+e);
		}
		return issueTypes.get(0).toString();
	}



	public String insertDistrictAddressChangeDtls(HashMap basicDtls)
	{
		String status="";
		Connection con = null;
		String issueReqId ="",docType="";
		if(basicDtls.size()>0)
		{
			issueReqId = (String)basicDtls.get("reqId");
			docType = (String)basicDtls.get("docType");
		}
		int basicInsertStatus=0,modifyStatus=0;
		 long fileSize2  = (Long) basicDtls.get("fileSize2");
		 
		 try
		 {
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			basicInsertStatus = insertBasicDtls(basicDtls,con);         //insert basic details 
			if(basicInsertStatus > 0)
			{
				modifyStatus = districtaddressChangeInsertDtls(basicDtls,con);         //insert to be modify details
				 if(modifyStatus > 0)
				  {
					 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
					 {
						 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
						 {
							 
							 if(fileSize2!=0)
							 {
								 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
								 {
									 status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
								        con.commit();
								 }
								 else
								 {
									  con.rollback();
									  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
								 }
							 }
							 else
							 {
								status= getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
						        con.commit();
							 }
						 }
						 else
						 {
							 con.rollback();
							 status="<font color='red'>05.Sorry we cannot process your request.Please try later !!</font>";
						 }
					 }
					 else
					 {
						 con.rollback();
						 status="<font color='red'>04.Sorry we cannot process your request.Please try later !!</font>";
					 }
				  }
				  else
				  {
					  con.rollback();
					  status="<font color='red'>02.Sorry we cannot process your request.Please try later !!</font>";
					  
				  }  
			 }
			 else
			  {
				  con.rollback();
				  status="<font color='red'>01.Sorry we cannot process your request.Please try later !!</font>";
			  }  
			
			
		 }
		 catch(Exception e)
		 {
			 log.info("Exception raised in insertAddressChangeDtls"+e);
			 try
			 {
				 status="<font color='red'> 001. Sorry we cannot process your request.Please try later insertDistrictAddressChangeDtls !!</font>";
			   con.rollback();
			 }
			 catch(Exception se)
			 {
				 se.printStackTrace();
			 }
			 
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
		 deleteGrievenceDirofProof(issueReqId); // Delete the Directory Created with issue id if issue failed. 
		return status;
	}
	public int districtaddressChangeInsertDtls(HashMap addressChangModifyDtls,Connection con)
	{
		String status="";
		 
		  PreparedStatement lPstmt = null;
		  int result =0;
		  HashMap proofDtls = new HashMap();
			  
		  String issueReqId ="",docType="";
		  long fileSize1  = (Long) addressChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) addressChangModifyDtls.get("fileSize2");	
			if(addressChangModifyDtls.size()>0)
			{
				issueReqId = (String)addressChangModifyDtls.get("reqId");
				docType = (String)addressChangModifyDtls.get("docType");
			}
		try
		{
			lStrQuery =  "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,dist_id,mandal_id,village_id,hab_id,house_no,pin_code,\n" +
	              " is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,?,?,?,?,?,'Y',?,?,getdate(),?)";
			//lcon = DBConnection.getConnection();
		    lPstmt = con.prepareStatement(lStrQuery);
		    lPstmt.setString(1,(String)addressChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)addressChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)addressChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)addressChangModifyDtls.get("distName") );
		    lPstmt.setString(5,(String)addressChangModifyDtls.get("manName") );
		    //lPstmt.setString(6,(String)nameChangModifyDtls.get("panName") );
		    lPstmt.setString(6,(String)addressChangModifyDtls.get("villName") );
		    lPstmt.setString(7,(String)addressChangModifyDtls.get("habName") );
		    lPstmt.setString(8,(String)addressChangModifyDtls.get("houseNo") );
		    lPstmt.setString(9,(String)addressChangModifyDtls.get("pincode") );
		    lPstmt.setString(10,CommonUtility.checkNullObject((String)addressChangModifyDtls.get("ipaddress")) );  		  
		    lPstmt.setString(11,(String)addressChangModifyDtls.get("reqBy") ); 
		    lPstmt.setString(12,(String)addressChangModifyDtls.get("remarks") );
		    
		     
		  result = lPstmt.executeUpdate();
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",addressChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",addressChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",addressChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",addressChangModifyDtls.get("filePath1")+"."+addressChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",addressChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", addressChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = insertProofDtls(proofDtls,con);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",addressChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",addressChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",addressChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",addressChangModifyDtls.get("filePath2")+"."+addressChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",addressChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", addressChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = insertProofDtls(proofDtls,con);
						
						
			    	}
			    	 
				}
		    	
		    }
		   
		}
		catch(Exception e){
			System.out.println("exception raised in addressChangeInsertDtls method " );
			log.info("Exception raised in addressChangeInsertDtls"+e);
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public ArrayList MultiAdharMapping(String sadaremid)
	{
		String status="";
		
		ArrayList resultList = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList tempList = new ArrayList();
		try
		{
			lStrQuery =  "SELECT proof_id,(SELECT COUNT(*) FROM tblperson_personal_details c WITH(NOLOCK) WHERE UPPER(c.proof_id)=a.proof_id) mycount, "
					+ "MAX(CASE WHEN proofdoc_type IN ('Aadhaar Id','Adhaar Card','Aadhaar Card','Aadhaar ID') "
					+ " AND LEN(proof_id)=12 AND UPPER(proof_id)<>'NA' "
					+ "THEN 'TRUE' ELSE 'FALSE' END )status,(Select SUBSTRING( "
					+ "( "
					+ "    SELECT ',' + person_code AS 'data()' "
					+ "        FROM tblperson_personal_details WITH(NOLOCK) WHERE (proof_id)=a.proof_id "
					+ "        FOR XML PATH('') ), 2 , 9999)) personcodes "
					+ "FROM tblperson_personal_details a WITH(NOLOCK) "
					+ " WHERE a.person_code=? GROUP BY proof_id ";
			
			tempList.add("S");
			tempList.add(sadaremid);
			paramList.add(tempList);
			resultList = DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);

			if(resultList!=null && resultList.size()==1)
			{
				resultList = (ArrayList) resultList.get(0);
			}
			
		}
		catch(Exception e)
		{
			log.info("Exception raised in tktCommonDAOImpl @ checkSADAREMProofStatus of "+e);
		}
		
		return resultList;	
	}
	
	
	public ArrayList checkProofStatusForMultiAdharMapping(String sadaremid)
	{
		String status="";
		
		ArrayList<Object> resultList = new ArrayList<Object>();
		ArrayList tempList = new ArrayList();
		try
		{
			tempList = MultiAdharMapping(sadaremid);
			
			status = "The given SADAREM ID's Adhaar ID "+tempList.get(0)+" is already tagged to the following SADAREM IDs <br>";
			resultList.add(tempList.get(1));
			
			status = status + tempList.get(3);
			
			status = status + ".<br> Please Detag other SADAREM IDs to raise the grievance";
			resultList.add(status);
			
			resultList.add(tempList.get(2));
			
			
		}
		catch(Exception e)
		{
			log.info("Exception raised in tktCommonDAOImpl @ checkSADAREMProofStatus of "+e);
		}
		
		return resultList;	
	}
	
	
	public static void deleteGrievenceDirofProof(String tkt_req_id)
	{
		try 
		{
		//	System.out.println("tkt_req_id in delete : "+tkt_req_id);
			if(!tkt_req_id.equals("") && tkt_req_id.length()>0)
			{
				int mycount = Integer.parseInt(DataAccess.getReturnResult("SELECT COUNT(*) mycount FROM tkt_request_master WHERE LTRIM(RTRIM(tkt_req_id))='"+tkt_req_id.trim().toUpperCase()+"'"));
				if(mycount==0)
				{
					issueTrackingCommonUtil.deleteDirectory(new File(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+tkt_req_id));
				}
			}
		}
		catch(Exception e)
		{
			log.error(e);
			e.printStackTrace();
		}
	}
	
}
 
