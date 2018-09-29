package com.tcs.sadare.issuetracksystem.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;

public class MultipleGrievanceDAOImpl implements MultipleGrievanceDAO {
	static final private Logger log = Logger.getLogger(MultipleGrievanceDAOImpl.class);
	String lStrQuery;
	public int multipleChangeInsertDtls(HashMap multipleChangModifyDtls,Connection con)
	{
		
		String qry = "",status="",dob="";

		 CommonIssueTrackingDAOImpl comObj  = new CommonIssueTrackingDAOImpl();
		  PreparedStatement lPstmt = null;
		  int result =0;
		  HashMap proofDtls = new HashMap();
			  
		  String issueReqId ="",docType="";
		  long fileSize1  = (Long) multipleChangModifyDtls.get("fileSize1");
		  long fileSize2  = (Long) multipleChangModifyDtls.get("fileSize2");	
		  long fileSize3  = (Long) multipleChangModifyDtls.get("fileSize3");
		  

		  
			if(multipleChangModifyDtls.size()>0)
			{
				issueReqId = (String)multipleChangModifyDtls.get("reqId");
				dob = (String)multipleChangModifyDtls.get("dob");
			}
		try
		{
			qry = "insert into  tkt_request_tobe_modify_dtls\n"+
	              "(tkt_req_id,tkt_type_id,sadarem_id,person_sur_name,person_name,person_telugu_name,\n"+
	               "relation_name,relation_type,relation_teugu_name,dob,edu_id,\n"+
	               "dist_id,mandal_id,village_id,hab_id,house_no,pin_code,tracker,\n"+
	              " is_active,req_ip_address,created_by,created_date,remarks)\n"+
	              "values(?,?,?,?,?,?,?,?,?,CONVERT(date,'"+dob+"',103),?,?,?,?,?,?,?,?,'Y',?,?,getdate(),?)";
			//lcon = DBConnection.getConnection();
		    lPstmt = con.prepareStatement(qry);
		    lPstmt.setString(1,(String)multipleChangModifyDtls.get("reqId") );
		    lPstmt.setString(2,(String)multipleChangModifyDtls.get("selIssueType") );
		    lPstmt.setString(3,(String)multipleChangModifyDtls.get("sadaremId") );
		    lPstmt.setString(4,(String)multipleChangModifyDtls.get("surname") );
		    lPstmt.setString(5,(String)multipleChangModifyDtls.get("fname") );
		    lPstmt.setString(6,(String)multipleChangModifyDtls.get("telname") ); 
		    lPstmt.setString(7,(String)multipleChangModifyDtls.get("relationname") );
		    lPstmt.setString(8,(String)multipleChangModifyDtls.get("relationtype") );
		    lPstmt.setString(9,(String)multipleChangModifyDtls.get("Relteluguname") );
		  //  lPstmt.setString(10,(String)multipleChangModifyDtls.get("dob") );
		    lPstmt.setString(10,(String)multipleChangModifyDtls.get("educationtype") );
		    lPstmt.setString(11,(String)multipleChangModifyDtls.get("distName") );
		    lPstmt.setString(12,(String)multipleChangModifyDtls.get("manName") );
		    lPstmt.setString(13,(String)multipleChangModifyDtls.get("villName") );
		    lPstmt.setString(14,(String)multipleChangModifyDtls.get("habName") );
		    lPstmt.setString(15,(String)multipleChangModifyDtls.get("houseNo") );
		    lPstmt.setString(16,(String)multipleChangModifyDtls.get("pincode") );
		    lPstmt.setString(17,(String)multipleChangModifyDtls.get("tracker") ); 
		    lPstmt.setString(18,CommonUtility.checkNullObject((String)multipleChangModifyDtls.get("ipaddress") ));
		    lPstmt.setString(19,(String)multipleChangModifyDtls.get("reqBy") );
		    lPstmt.setString(20,(String)multipleChangModifyDtls.get("remarks") );
		    
		     
		  result = lPstmt.executeUpdate(); 
		//  System.out.println("111"+qry);
		  if(result>0)
		    {
		    	if(fileSize1 != 0)
				{
		    		proofDtls.put("proofTypeID",multipleChangModifyDtls.get("proofTypeID1"));
		    		proofDtls.put("proofId",multipleChangModifyDtls.get("proofId1"));
		    		proofDtls.put("docType",multipleChangModifyDtls.get("docType1"));
		    		proofDtls.put("filePath",multipleChangModifyDtls.get("filePath1")+"."+multipleChangModifyDtls.get("docType1")); 
		    		proofDtls.put("fileDoc",multipleChangModifyDtls.get("fileDoc1"));
		    		proofDtls.put("reqBy", multipleChangModifyDtls.get("reqBy"));
		    		proofDtls.put("reqId",issueReqId );
					
					result = comObj.insertProofDtls(proofDtls,con);
				
			    	if(result>0 && fileSize2 != 0)
			    	{
			    		proofDtls = new HashMap();
			    		proofDtls.put("proofTypeID",multipleChangModifyDtls.get("proofTypeID2"));
			    		proofDtls.put("proofId",multipleChangModifyDtls.get("proofId2"));
			    		proofDtls.put("docType",multipleChangModifyDtls.get("docType2"));
			    		proofDtls.put("filePath",multipleChangModifyDtls.get("filePath2")+"."+multipleChangModifyDtls.get("docType2")); 
			    		proofDtls.put("fileDoc",multipleChangModifyDtls.get("fileDoc2"));
			    		proofDtls.put("reqBy", multipleChangModifyDtls.get("reqBy"));
			    		proofDtls.put("reqId",issueReqId );
			    		result = comObj.insertProofDtls(proofDtls,con);
		    		
			    		if(result>0 && fileSize3 != 0)
				    	{
				    		proofDtls = new HashMap();
				    		proofDtls.put("proofTypeID",multipleChangModifyDtls.get("proofTypeID3"));
				    		proofDtls.put("proofId",multipleChangModifyDtls.get("proofId3"));
				    		proofDtls.put("docType",multipleChangModifyDtls.get("docType3"));
				    		proofDtls.put("filePath",multipleChangModifyDtls.get("filePath3")+"."+multipleChangModifyDtls.get("docType2")); 
				    		proofDtls.put("fileDoc",multipleChangModifyDtls.get("fileDoc3"));
				    		proofDtls.put("reqBy", multipleChangModifyDtls.get("reqBy"));
				    		proofDtls.put("reqId",issueReqId );
				    		result = comObj.insertProofDtls(proofDtls,con);
							
							
				    	}
						
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
	public ArrayList RestrictingToRaiseMultiIssue(String query)
	{
		ArrayList data = new ArrayList();
	
		try
		{
			data = DataAccess.pickData(query, false, false);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in RestrictingToRaiseMultiIssue of "+e);
		}
		
		return data;
	}
	
	public ArrayList getMultiIssueData(String reqId)
	{
		ArrayList issuedata = new ArrayList();
		try
		{
			ArrayList paramList = new ArrayList();
			ArrayList tempList  = new ArrayList();
			
			lStrQuery  = "select a.tkt_req_id,a.tkt_type_id,a.sadarem_id,a.person_sur_name,a.person_name,\n"+
					"isnull(a.person_telugu_name,'-') person_telugu_name,c.relation_name,a.relation_name,a.relation_teugu_name,\n"+
					"e.edu_desc,CONVERT(VARCHAR,a.dob,105) DOB,DATEDIFF(year,a.dob,GETDATE()) Age,d.district_name,m.mandal_name,\n"+
					"v.village_name,h.habitation_name,a.house_no,a.pin_code,a.remarks,(select proof_doc_path+',' from tkt_request_master(nolock) b,tkt_req_proof_details(nolock) c\n"+ 
					"where b.tkt_req_id = c.tkt_req_id and b.tkt_req_id = ? FOR XML PATH('')\n"+
					") path from \n"+
					"tkt_request_tobe_modify_dtls(nolock) a ,tbldistrict_details(nolock) d,tblmandal_details(nolock) m,\n"+
					"tblvillage_details(nolock) v,tblhabitation_details(nolock) h,\n"+
					"tkt_request_master b,sadarem_gender_relation_type_mapping(nolock) c,sadarem_education_master(nolock) e\n"+
					"where a.tkt_req_id = b.tkt_req_id and b.gender = c.gender_id and a.relation_type = c.relation_id and a.edu_id = e.edu_id and a.dist_id=d.district_id \n"+
					"and a.dist_id=m.district_id and a.mandal_id=m.mandal_id and\n"+
					" a.dist_id=v.district_id and a.mandal_id=v.mandal_id and a.village_id=v.village_id\n"+
					" and  a.dist_id=h.district_id and a.mandal_id=h.mandal_id and a.village_id=h.village_id\n"+
					" and a.hab_id=h.habitation_id and\n"+
					" a.tkt_req_id=?";
			
			tempList  = new ArrayList();
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
			System.out.println("Exception raised in getMultiIssueData of "+e);
		}
		
		return issuedata;
	} 
	public String getMultiIssuesTracker(String reqId)
	{
		ArrayList tempList = new ArrayList();
		ArrayList paramList = new ArrayList();
		String result="";
		
		try
		{
			lStrQuery = "select tracker from tkt_request_tobe_modify_dtls where tkt_req_id=?";
			 
			tempList.add("S");
			tempList.add(reqId);
			paramList.add(tempList);
			
			result = DataAccess.getReturnResultByPstmt(lStrQuery, paramList);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception raised in getIssuesTracker of "+e);
		}
		
		return result;
	}
	public ArrayList getSelectedIssueIdsFromtracker(String tracker)
	{
		
		String[] arr;
		String issues="",a="";
		arr=tracker.split("_");
		System.out.println("length"+arr.length);
		String[] issueIds = new String[arr.length];
		int i=0,counter=0;
		ArrayList<String> issuesids = new ArrayList<String>(); 
		try
		{
		for(i=0;i<arr.length;i++)
		{
			if(arr[i].equals("N"))
			{
				issueIds[i]="S001";
				counter=1;
				
			}
			if(arr[i].equals("R"))
			{
				issueIds[i]="S003";
				
			}
			if(arr[i].equals("D"))
			{
				issueIds[i]="S016";
				
			}
			if(arr[i].equals("Q"))
			{
				issueIds[i]="S017";
				
			}
			if(arr[i].equals("A"))
			{
				issueIds[i]="S004";
				
			}
			
		}
		
	 //System.out.println("issueIds"+issueIds);
		issuesids.addAll(Arrays.asList(issueIds));
		if(counter==1)
		{
			issuesids.add("S002");
			
		}
		

        // System.out.println("issuesids"+issuesids);
		 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		return issuesids;
	}
	public int updateMultiIssueDtls(String reqId,String flag,Connection con)
	{
		String statusMsg = "";
		ArrayList addrIssueDtls = new ArrayList();
		//Connection con = null;
		int result=0;
		CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
		try
		{
			
			ArrayList paramList = new ArrayList();
			ArrayList tempList	= new ArrayList();
			
			 //  con = DBConnection.getConnection();
		    //   con.setAutoCommit(false);
			   PreparedStatement lPstmt = null; 
			   CommonDAO comObj1 = new CommonDAOImpl();
			  
			    
			   lStrQuery = "select sadarem_id,person_sur_name,person_name,person_telugu_name,\n"
	    			+ "relation_name,relation_type,relation_teugu_name,\n"
	    			+ "dob,edu_id,dist_id,mandal_id,village_id,hab_id,house_no,pin_code from tkt_request_tobe_modify_dtls(nolock) where tkt_req_id=?";
		
	    	tempList	= new ArrayList();
	    	tempList.add("S");
	    	tempList.add(reqId);
	    	paramList.add(tempList);
	    	
	    	
	    	addrIssueDtls = (ArrayList)DataAccess.pickDataByPrepareStmt(lStrQuery, paramList, false, false);
		   if(addrIssueDtls.size() > 0)
		   {
			   addrIssueDtls = (ArrayList)addrIssueDtls.get(0);
			   
			   if(addrIssueDtls.size()>0)
			   {
				   String habCode = comObj1.getHabCodewithoutpanchayat((String)addrIssueDtls.get(9), (String)addrIssueDtls.get(10), (String)addrIssueDtls.get(11), (String)addrIssueDtls.get(12));
				  
				   lStrQuery = "update tblperson_personal_details set Surname=?,First_Name=?,Personname_Telugu=?,Relation_Name=?,\n"
				   		+ "Relationship=?,Fathername_Telugu=?,Date_of_Birth=?,Education=?,District_ID=?,DistrictID=?,\n"+
				   		"Mandal_ID=?,MandalID=?,Village_ID=?,VillageID=?,Habitation_ID=?,HabtationID=?,\n"+
						   "House_Number=?,Pin_Code=?,updated_date=getdate(),HabCode=? where person_code=?";
				   lPstmt = con.prepareStatement(lStrQuery);
				   lPstmt.setString(1,(String)addrIssueDtls.get(1) );
				   lPstmt.setString(2,(String)addrIssueDtls.get(2) );
				   lPstmt.setString(3,(String)addrIssueDtls.get(3) );
				   lPstmt.setString(4,(String)addrIssueDtls.get(4) );
				   lPstmt.setString(5,(String)addrIssueDtls.get(5) );
				   lPstmt.setString(6,(String)addrIssueDtls.get(6) );
				   lPstmt.setString(7,(String)addrIssueDtls.get(7) );
				   lPstmt.setString(8,(String)addrIssueDtls.get(8) );
				   lPstmt.setString(9,(String)addrIssueDtls.get(9) );
				   lPstmt.setString(10,(String)addrIssueDtls.get(9) );
				   lPstmt.setString(11,(String)addrIssueDtls.get(10) );
				   lPstmt.setString(12,(String)addrIssueDtls.get(10) );
				   lPstmt.setString(13,(String)addrIssueDtls.get(11) );
				   lPstmt.setString(14,(String)addrIssueDtls.get(11) );
				   lPstmt.setString(15,(String)addrIssueDtls.get(12) );
				   lPstmt.setString(16,(String)addrIssueDtls.get(12) );
				   lPstmt.setString(17,(String)addrIssueDtls.get(13) );
				   lPstmt.setString(18,(String)addrIssueDtls.get(14) );
				   lPstmt.setString(19,habCode);
				   lPstmt.setString(20,(String)addrIssueDtls.get(0) );
				     result = lPstmt.executeUpdate();
				     
				    if(result >0) 
				    {
				    	lStrQuery = "update tblperson_personal_details_new set Surname=?,First_Name=?,Personname_Telugu=?,Relation_Name=?,\n"
							   		+ "Relationship=?,Fathername_Telugu=?,Date_of_Birth=?,Education=?,District_ID=?,DistrictID=?,\n"+
							   		"Mandal_ID=?,MandalID=?,Village_ID=?,VillageID=?,Habitation_ID=?,HabtationID=?,\n"+
									   "House_Number=?,Pin_Code=?,updated_date=getdate(),HabCode=? where person_code=?";
							   lPstmt = con.prepareStatement(lStrQuery);
							   lPstmt.setString(1,(String)addrIssueDtls.get(1) );
							   lPstmt.setString(2,(String)addrIssueDtls.get(2) );
							   lPstmt.setString(3,(String)addrIssueDtls.get(3) );
							   lPstmt.setString(4,(String)addrIssueDtls.get(4) );
							   lPstmt.setString(5,(String)addrIssueDtls.get(5) );
							   lPstmt.setString(6,(String)addrIssueDtls.get(6) );
							   lPstmt.setString(7,(String)addrIssueDtls.get(7) );
							   lPstmt.setString(8,(String)addrIssueDtls.get(8) );
							   lPstmt.setString(9,(String)addrIssueDtls.get(9) );
							   lPstmt.setString(10,(String)addrIssueDtls.get(9) );
							   lPstmt.setString(11,(String)addrIssueDtls.get(10) );
							   lPstmt.setString(12,(String)addrIssueDtls.get(10) );
							   lPstmt.setString(13,(String)addrIssueDtls.get(11) );
							   lPstmt.setString(14,(String)addrIssueDtls.get(11) );
							   lPstmt.setString(15,(String)addrIssueDtls.get(12) );
							   lPstmt.setString(16,(String)addrIssueDtls.get(12) );
							   lPstmt.setString(17,(String)addrIssueDtls.get(13) );
							   lPstmt.setString(18,(String)addrIssueDtls.get(14) );
							   lPstmt.setString(19,habCode);
							   lPstmt.setString(20,(String)addrIssueDtls.get(0) );
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
}
