
package com.tcs.sadarem.issuetracksystem.actions;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.MultipleGrievanceDAO;
import com.tcs.sadare.issuetracksystem.DAO.MultipleGrievanceDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sgv.common.util.DBConnection;

public class MultipleGrievanceAction extends BaseDispatchAction  {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
	//	System.out.println("Hello");
		HashMap<?, ?> multipleChangeDtls = new HashMap<Object, Object>();
		HashMap<String, Serializable> multipleChangModifyDtls = new HashMap<String, Serializable>();
		HttpSession session = request.getSession();
		String toBemodifyStatus="";
		ArrayList<?> sadaremData = new ArrayList<Object>();
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj  = new CommonIssueTrackingDAOImpl();
	    multipleChangeDtls              = CommonUtility.getDiskFileItemFactory(sfu, request);
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	    MultipleGrievanceDAO obj  = new MultipleGrievanceDAOImpl();
	    Integer basicInsertStatus;
	     ArrayList data =  new ArrayList();
	     String flag="";
	    try{
	    	if(multipleChangeDtls.size()>0)
	    	{
	    		ArrayList<String> FileTypeAllowedList = new ArrayList<String>();
	    		 ArrayList proofTypeList       = new ArrayList<Object>();
			     FileTypeAllowedList.add("pdf");
			     String fileContentTypeAllowed = "application/pdf";
			     String fileName1="",filetype1="",fileContentType1="";
				 long file_size1  = 0;
				 String fileName2="",filetype2="",fileContentType2="";
				 long file_size2  = 0;
				 String fileName3="",filetype3="",fileContentType3="";
				 long file_size3  = 0;
				String tracker="";String distName="";String distId="";
				   ArrayList relationTypeList = new ArrayList();
				   ArrayList educationList = new ArrayList();
				   int roleId1         = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId"))); 
				   
				
				 flag   	    = ((FileItem) multipleChangeDtls.get("flag")).getString();
				String namecheck   	    = ((FileItem) multipleChangeDtls.get("namecheckbox1")).getString();
				String relationcheck   	    = ((FileItem) multipleChangeDtls.get("relationcheckbox1")).getString();
				String dobcheck   	    = ((FileItem) multipleChangeDtls.get("dobcheckbox1")).getString();
				String educationcheck   	    = ((FileItem) multipleChangeDtls.get("educationcheckbox1")).getString();
				String addresscheck   	    = ((FileItem) multipleChangeDtls.get("addresscheckbox1")).getString();
				
				
				if(namecheck.equals("namecheck"))
				{
					tracker="N_";
				}

				if(relationcheck.equals("relationcheck"))
				{
					tracker=tracker+"R_";
				}

				if(dobcheck.equals("dobcheck"))
				{			
				tracker=tracker+"D_";
				}
				
				if(educationcheck.equals("educationcheck"))
				{				
				tracker=tracker+"Q_";
				}
				
				if(addresscheck.equals("addresscheck"))
				{		
			    tracker=tracker+"A";
				}
				
				String qry=getIssueIdsFromtracker(tracker);
				
				String surname   	    = ((FileItem) multipleChangeDtls.get("surname")).getString();
				String fname     	    = ((FileItem) multipleChangeDtls.get("fname")).getString();
				String telname   	    = ((FileItem) multipleChangeDtls.get("hidfulnametelugu")).getString();
				String relationname     = ((FileItem) multipleChangeDtls.get("relationname")).getString();
				String relationtype     = ((FileItem) multipleChangeDtls.get("relationType")).getString();
				String Relteluguname    = ((FileItem) multipleChangeDtls.get("hidfullnametelugu")).getString();
				String dob     	        = ((FileItem) multipleChangeDtls.get("dob")).getString();
				String educationtype    = ((FileItem) multipleChangeDtls.get("educationType")).getString();
				String manName   	    = ((FileItem) multipleChangeDtls.get("mandals")).getString();
				String villName   	    = ((FileItem) multipleChangeDtls.get("villages")).getString();
				String habName          = ((FileItem) multipleChangeDtls.get("habitation")).getString();
				String houseNo          = ((FileItem) multipleChangeDtls.get("houseno")).getString();
				String pincode          = ((FileItem) multipleChangeDtls.get("pincode")).getString();
			    String sadaremId        = ((FileItem) multipleChangeDtls.get("sadaremid")).getString();
			    String selIssueType     = ((FileItem) multipleChangeDtls.get("selIssueType")).getString();
			    String remarks          = ((FileItem) multipleChangeDtls.get("decription")).getString();			    
				String FormID		    = ((FileItem) multipleChangeDtls.get("FormSessionID")).getString();
				String MobNo            = ((FileItem) multipleChangeDtls.get("contid")).getString(); 
				
				String proofType1       = ((FileItem) multipleChangeDtls.get("proofidentity_1")).getString();
				String proofId1         = ((FileItem) multipleChangeDtls.get("proofid_1")).getString();
				FileItem file1          = ((FileItem) multipleChangeDtls.get("proofDoc_1"));
				    
			    String proofType2   	= ((FileItem) multipleChangeDtls.get("proofidentity_2")).getString();
			    String proofId2    		= ((FileItem) multipleChangeDtls.get("proofid_2")).getString();
			    FileItem file2          = ((FileItem) multipleChangeDtls.get("proofDoc_2"));
			    
			    String proofType3   	= ((FileItem) multipleChangeDtls.get("proofidentity_3")).getString();
			    String proofId3    		= ((FileItem) multipleChangeDtls.get("proofid_3")).getString();
			    FileItem file3          = ((FileItem) multipleChangeDtls.get("proofDoc_3"));
				
			    
			    if(roleId1==Integer.parseInt(CommonConstants.ROLE_APPELLATE ) || roleId1==Integer.parseInt(CommonConstants.Role_AdminAssist ))
				   {
			    	//distName    =   sadaremId.substring(0,2);
			    	
			    	

			    	ArrayList  sadaremDatawithcolumnName = new ArrayList();
			    	HashMap resultList = new HashMap();
			    	//distName    =   sadaremId.substring(0,2);
			    	sadaremDatawithcolumnName = comObj.getSadaremCommonDatawithColumnName(sadaremId);
			    	//System.out.println("sadaremDatatemp--------\n"+sadaremDatawithcolumnName);
			    	resultList=CommonUtility.getHashMapfromArrayListWithcolumnName(sadaremDatawithcolumnName);
			    	distName    =   (String) resultList.get("districtid");
			    	//System.out.println("dhfsd-----"+resultList.get("districtid"));
				   }
				   else
				   {
					   distName         = (String)session.getAttribute("districtId");    
				   }
				
			    
				    file_size1 = file1.getSize();
					if(file_size1 != 0) 
					{
					      fileName1 = file1.getName();
						  filetype1 = fileName1.substring((fileName1.lastIndexOf(".")+1));
						  fileContentType1 = file1.getContentType().toLowerCase();
					}
					
					file_size2 = file2.getSize();
					if(file_size2 != 0) 
					{
					      fileName2 = file2.getName();
						  filetype2 = fileName2.substring((fileName2.lastIndexOf(".")+1));
						  fileContentType2 = file2.getContentType().toLowerCase();
					}
					
					file_size3 = file3.getSize();
					if(file_size3 != 0) 
					{
					      fileName3 = file3.getName();
						  filetype3 = fileName3.substring((fileName3.lastIndexOf(".")+1));
						  fileContentType3 = file3.getContentType().toLowerCase();
					}
				String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
		
			    if(FormID.equals(sessionFormID))
			    {  
				    int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
				    String decisionFlag = comObj.getTktDecisionFlag(roleId, "P", "",  selIssueType);
				    String reqId        = comObj.generateIssueReqId(selIssueType);
				    
					 	multipleChangModifyDtls.put("distName",distName );
					 
						multipleChangModifyDtls.put("ipaddress",ipaddress );
					 		multipleChangModifyDtls.put("surname",surname );
							multipleChangModifyDtls.put("fname",fname );
							multipleChangModifyDtls.put("telname",telname );
					 		multipleChangModifyDtls.put("relationname",relationname );
							multipleChangModifyDtls.put("relationtype",relationtype );
							multipleChangModifyDtls.put("Relteluguname",Relteluguname ); 	
					 		multipleChangModifyDtls.put("dob",dob );
					 		multipleChangModifyDtls.put("educationtype",educationtype );
					 		multipleChangModifyDtls.put("manName",manName );
							multipleChangModifyDtls.put("villName",villName );
							multipleChangModifyDtls.put("habName",habName );
							multipleChangModifyDtls.put("houseNo",houseNo );
							multipleChangModifyDtls.put("pincode",pincode );
						   
						
					 	multipleChangModifyDtls.put("namecheck",namecheck );
					 	multipleChangModifyDtls.put("relationcheck",relationcheck );					 
					 	multipleChangModifyDtls.put("dobcheck",dobcheck );
					 	multipleChangModifyDtls.put("educationcheck",educationcheck );
						multipleChangModifyDtls.put("addresscheck",addresscheck );
						multipleChangModifyDtls.put("tracker",tracker );
						
						multipleChangModifyDtls.put("reqId",reqId );
						multipleChangModifyDtls.put("sadaremId",sadaremId );
						multipleChangModifyDtls.put("selIssueType",selIssueType );
						multipleChangModifyDtls.put("reqBy",(String)session.getAttribute("loginid") ); 
						multipleChangModifyDtls.put("decisionFlag",decisionFlag );
						multipleChangModifyDtls.put("remarks",remarks );
						multipleChangModifyDtls.put("MobNo",MobNo );
						
						multipleChangModifyDtls.put("proofTypeID1",proofType1 );
						multipleChangModifyDtls.put("proofId1",proofId1);
						multipleChangModifyDtls.put("docType1",filetype1 );
						multipleChangModifyDtls.put("filePath1",reqId+"/"+proofId1); 
						multipleChangModifyDtls.put("fileDoc1",file1);
						multipleChangModifyDtls.put("fileSize1",file_size1);
						
						
						multipleChangModifyDtls.put("proofTypeID2",proofType2 );
						multipleChangModifyDtls.put("proofId2",proofId2);
						multipleChangModifyDtls.put("docType2",filetype2 );
						multipleChangModifyDtls.put("filePath2",reqId+"/"+proofId2); 
						multipleChangModifyDtls.put("fileDoc2",file2);
						multipleChangModifyDtls.put("fileSize2",file_size2);
						
						multipleChangModifyDtls.put("proofTypeID3",proofType3 );
						multipleChangModifyDtls.put("proofId3",proofId3);
						multipleChangModifyDtls.put("docType3",filetype3 );
						multipleChangModifyDtls.put("filePath3",reqId+"/"+proofId3); 
						multipleChangModifyDtls.put("fileDoc3",file3);
						multipleChangModifyDtls.put("fileSize3",file_size3);
						 
						qry=qry+"and sadarem_id='"+sadaremId+"'";
						data = obj.RestrictingToRaiseMultiIssue(qry);
					
						//System.out.println("dist"+distName+"mand"+manName+"vill"+villName+"hab"+habName+""+roleId1);
						
						String issueAlreadyRaised = comObj.getRaisedIssuesCount(sadaremId, selIssueType);
						proofTypeList = comObj.getproofdoucments();
						if(!issueAlreadyRaised.equals("N"))
						{
							if(data.size()>0)
							{
								toBemodifyStatus = "<font color='red'>Issue cannot be raised as already another grievance is raised under this sadaremID and it is in pending status.<br> Please make it Final approval or Rejection of that grievance in order to proceed here.</font>";
							}
							else if(!FileTypeAllowedList.contains(filetype1.toLowerCase()))
							{
								toBemodifyStatus = "<font color='red'>Please upload valid PDF document.</font>";
							}
							else if(file_size1>CommonConstants.PROOFDOCMAXLIMIT)
							{
								toBemodifyStatus = "<font color='red'>Please upload valid document with size less than 5MB.</font>";	
							}
							else if(fileContentTypeAllowed.indexOf(fileContentType1)==-1) 
							{
								toBemodifyStatus = "<font color='red'>Please upload valid PDF document.</font>";
							}
							else if(file_size2 != 0 && !FileTypeAllowedList.contains(filetype2.toLowerCase()))
							{
								toBemodifyStatus = "<font color='red'>Please upload valid PDF document.</font>";
							}
							else if(file_size2 != 0 && file_size2>CommonConstants.PROOFDOCMAXLIMIT)
							{
								toBemodifyStatus = "<font color='red'>Please upload valid document with size less than 5MB.</font>";	
							}
							else if(file_size2 != 0 && fileContentTypeAllowed.indexOf(fileContentType2)==-1)
							{
								toBemodifyStatus = "<font color='red'>Please upload valid PDF document.</font>";
							}
							else if(file_size3 != 0 && !FileTypeAllowedList.contains(filetype3.toLowerCase()))
							{
								toBemodifyStatus = "<font color='red'>Please upload valid PDF document.</font>";
							}
							else if(file_size3 != 0 && file_size3>CommonConstants.PROOFDOCMAXLIMIT)
							{
								toBemodifyStatus = "<font color='red'>Please upload valid document with size less than 5MB.</font>";	
							}
							else if(file_size3 != 0 && fileContentTypeAllowed.indexOf(fileContentType3)==-1)
							{
								toBemodifyStatus = "<font color='red'>Please upload valid PDF document.</font>";
							}
							else
							{
								toBemodifyStatus = insertMultipleChangeDtls(multipleChangModifyDtls);
							}
							 
						}
						else
						{
							toBemodifyStatus = "<font color='red'>Multiple Grievance Issue is already raised.</font>";
						}
						String issueName = null;
			            issueName = comObj.getIssueName(selIssueType);            
			            request.setAttribute("issueName", issueName);
					request.setAttribute("statusMsg",toBemodifyStatus );
					CommonDAOImpl comObj1 = new CommonDAOImpl();
					sadaremData = comObj.getSadaremCommonData(sadaremId);
					request.setAttribute("selIssueType",selIssueType);
				    request.setAttribute("sadaremData", sadaremData);
				    request.setAttribute("proofTypeList", proofTypeList);
				    session.setAttribute("FormSessionID",""+Math.random());
				   if(sadaremData!=null)
				   {
					   sadaremData=(ArrayList<?>)sadaremData.get(0);
				   
				   }
				   
				   if(roleId1==CommonConstants.SPMLOGIN_ROLEID)
				   {
					   distId    =  (String)sadaremData.get(1);
				   }
				   else
				   {
					   distId         = (String)session.getAttribute("districtId");    
				   }
				   
				//	String distId     = (String) session.getAttribute("districtId");
					String mandID     = (String)sadaremData.get(2);
					String villageId  = (String)sadaremData.get(3);
			    	 
			    	 ArrayList<?> mandalList      = (ArrayList<?>)comObj1.getMandalListByDistrictID(distId);
			    	 ArrayList<?> panchayatList   = (ArrayList<?>)comObj1.getpanchayatListByDistrictIDMandalIDVillageID(distId, mandID);
			    	 ArrayList<?> villageList     = (ArrayList<?>)comObj1.getVillageListByDistrictIDMandalID(distId, mandID);
			    	 ArrayList<?> habitationList  = (ArrayList<?>)comObj1.getHabitationListByDistrictIDMandalIDvillageID(distId, mandID, villageId);
			    	 educationList = comObj.getEducationList();
			    	 relationTypeList = comObj.getRelationType((String)sadaremData.get(15));
			    	 
			    	 
				     request.setAttribute("relationTypeList", relationTypeList); 	
				     request.setAttribute("educationList", educationList); 
			    	 request.setAttribute("mandalList",mandalList);
			    	 request.setAttribute("panchayatList", panchayatList);
			    	 request.setAttribute("villageList",villageList);
			    	 request.setAttribute("habitationList",habitationList);
				   
				   
					   
			    	 
					
						}
						else
						{
							request.setAttribute("MSG", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
					 		
							return mapping.findForward("exception");
						}
				
	    	
	    }	else
		{
			request.setAttribute("MSG", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
	 		
			return mapping.findForward("exception");
		}
	    	
	    }
	    catch(Exception e)
	    {
	    		    	e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");	
	    }
		 request.setAttribute("flag",flag);
	    return mapping.findForward("multiplegrievanceissue");
	}

	public String insertMultipleChangeDtls(HashMap basicDtls)
	{
		 CommonIssueTrackingDAOImpl comObj  = new CommonIssueTrackingDAOImpl();
		 MultipleGrievanceDAO comObj1  = new MultipleGrievanceDAOImpl();
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
			 long fileSize3  = (Long) basicDtls.get("fileSize3");
			 
			 try
			 {
				con = DBConnection.getConnection();
				con.setAutoCommit(false);
				basicInsertStatus = comObj.insertBasicDtls(basicDtls,con);         //insert basic details 
				if(basicInsertStatus > 0)
				{
					modifyStatus = comObj1.multipleChangeInsertDtls(basicDtls,con);         //insert to be modify details
					 if(modifyStatus > 0)
					  {
						 if(issueTrackingCommonUtil.createDirectory(CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+issueReqId)==true)
						 {
							 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc1"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath1")+".pdf")==true)
							 {
								 if(fileSize3!=0)
								 {
									 if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc3"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath3")+".pdf")==true)
									 {
										 
										 if(fileSize2!=0)
										 {
										     if(issueTrackingCommonUtil.writeFileToDirecotry((FileItem)basicDtls.get("fileDoc2"),CommonConstants.ISSUETRACKING_ATTACHMENT1_PATH+(String)basicDtls.get("filePath2")+".pdf")==true)
										     {
											 status= comObj.getFlagDesc((String)basicDtls.get("decisionFlag") )+" "+".Note the request ID : "+(String)basicDtls.get("reqId")+" for further reference." ;
										        con.commit();
										     }
										     else
										     {
											  con.rollback();
											  status="<font color='red'>06.Sorry we cannot process your request.Please try later !!</font>";
										     }
	 
										 }
										 
										
									 }
									
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
			return status;
	}
	public String getIssueIdsFromtracker(String tracker)
	{
		String[] arr;
		String issues="",query="",a="";
		arr=tracker.split("_");
		//System.out.println("-----------arr.length"+arr.length);
		String[] issueIds = new String[arr.length];
		int i=0,counter=0;
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
	
		
		for(int j=0;j<issueIds.length;j++)
		{
			
			issues+="'"+issueIds[j]+"'";
			
			if(j!=issueIds.length-1)
			{
				issues+=",";
			}
		}
		
		
         query="select * from  tkt_request_master t, tkt_status_flow_mapping k\n"
		 + "where t.tkt_type_id=k.tkt_type_id and t.status_flag=k.decision_flag and\n"
		 + "k.decision_flag not like '%R%' and k.is_final<>'Y' and t.tkt_type_id in ("+issues;
     	 if(counter==1)
		 {
     		query+=",'S002'";
		 }
		 
		 
     	query+= ")"; 
      //  System.out.println("issues"+issues);
      //   System.out.println("query"+query);
         
		 return query;
	}

	
}
