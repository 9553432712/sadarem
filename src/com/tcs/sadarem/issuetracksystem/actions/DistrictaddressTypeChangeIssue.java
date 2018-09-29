package com.tcs.sadarem.issuetracksystem.actions;

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
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class DistrictaddressTypeChangeIssue extends BaseDispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		HashMap addressChangeDtls = new HashMap();
		HashMap addressChangModifyDtls = new HashMap();
		HttpSession session = request.getSession();
		String toBemodifyStatus="";
		ArrayList sadaremData = new ArrayList();
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj  = new CommonIssueTrackingDAOImpl();
	    addressChangeDtls              = CommonUtility.getDiskFileItemFactory(sfu, request);
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	    Integer basicInsertStatus;
	    int  roleId1 = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
	    try
	    {
	    	if(addressChangeDtls.size()>0)
	    	{
	    		ArrayList FileTypeAllowedList = new ArrayList();
	    		 ArrayList proofTypeList       = new ArrayList();
			     FileTypeAllowedList.add("pdf");
			     String fileContentTypeAllowed = "application/pdf";
			     String fileName1="",filetype1="",fileContentType1="";
				 long file_size1  = 0;
				 String fileName2="",filetype2="",fileContentType2="";String distId="";
				 long file_size2  = 0;
				 
				 String distrctName   	    = ((FileItem) addressChangeDtls.get("districts")).getString();
			    String manName   	    = ((FileItem) addressChangeDtls.get("mandal")).getString();
			    String villName   	    = ((FileItem) addressChangeDtls.get("villages")).getString();
			    String habName          = ((FileItem) addressChangeDtls.get("habitation")).getString();
			    String houseNo          = ((FileItem) addressChangeDtls.get("houseno")).getString();
			    String pincode          = ((FileItem) addressChangeDtls.get("pincode")).getString();
			    String sadaremId        = ((FileItem) addressChangeDtls.get("sadaremid")).getString();
			    String selIssueType     = ((FileItem) addressChangeDtls.get("selIssueType")).getString();
			    String remarks          = ((FileItem) addressChangeDtls.get("decription")).getString();
			    String MobNo = ((FileItem) addressChangeDtls.get("contid")).getString(); 
			    
			    FileItem file           = ((FileItem) addressChangeDtls.get("proofDoc"));
				String FormID		    = ((FileItem) addressChangeDtls.get("FormSessionID")).getString();
				
				
				
				String proofType1       = ((FileItem) addressChangeDtls.get("proofidentity_1")).getString();
				String proofId1         = ((FileItem) addressChangeDtls.get("proofid_1")).getString();
				FileItem file1          = ((FileItem) addressChangeDtls.get("proofDoc_1"));
				    
			    String proofType2   	= ((FileItem) addressChangeDtls.get("proofidentity_2")).getString();
			    String proofId2    		= ((FileItem) addressChangeDtls.get("proofid_2")).getString();
			    FileItem file2          = ((FileItem) addressChangeDtls.get("proofDoc_2"));
				  
			    String distName         = (String)session.getAttribute("districtId");
			    
			    if(roleId1==Integer.parseInt(CommonConstants.ROLE_APPELLATE ))  
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
				String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
		
			    if(FormID.equals(sessionFormID))
			    {  
					    int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
					    String decisionFlag = comObj.getTktDecisionFlag(roleId, "P", "",  selIssueType);
					    String reqId        = comObj.generateIssueReqId(selIssueType);
					 	addressChangModifyDtls.put("distName",distrctName );
						addressChangModifyDtls.put("manName",manName );
						addressChangModifyDtls.put("villName",villName );
						addressChangModifyDtls.put("habName",habName );
						addressChangModifyDtls.put("houseNo",houseNo );
						addressChangModifyDtls.put("pincode",pincode );
						addressChangModifyDtls.put("ipaddress",ipaddress ); 
						
						addressChangModifyDtls.put("reqId",reqId );
						addressChangModifyDtls.put("sadaremId",sadaremId );
						addressChangModifyDtls.put("selIssueType",selIssueType );
						addressChangModifyDtls.put("reqBy",(String)session.getAttribute("loginid") ); 
						addressChangModifyDtls.put("decisionFlag",decisionFlag );
						addressChangModifyDtls.put("remarks",remarks );
						addressChangModifyDtls.put("MobNo",MobNo ); 
						
						addressChangModifyDtls.put("proofTypeID1",proofType1 );
						addressChangModifyDtls.put("proofId1",proofId1);
						addressChangModifyDtls.put("docType1",filetype1 );
						addressChangModifyDtls.put("filePath1",reqId+"/"+proofId1); 
						addressChangModifyDtls.put("fileDoc1",file1);
						addressChangModifyDtls.put("fileSize1",file_size1);
						
						
						addressChangModifyDtls.put("proofTypeID2",proofType2 );
						addressChangModifyDtls.put("proofId2",proofId2);
						addressChangModifyDtls.put("docType2",filetype2 );
						addressChangModifyDtls.put("filePath2",reqId+"/"+proofId2); 
						addressChangModifyDtls.put("fileDoc2",file2);
						addressChangModifyDtls.put("fileSize2",file_size2);
						 
						String issueAlreadyRaised = comObj.getRaisedIssuesCount(sadaremId, selIssueType);
						proofTypeList = comObj.getproofdoucments();
						if(!issueAlreadyRaised.equals("N"))
						{
							if(!FileTypeAllowedList.contains(filetype1.toLowerCase()))
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
							else
							{
								toBemodifyStatus = comObj.insertDistrictAddressChangeDtls(addressChangModifyDtls);
							}
							 
						} 
						else
						{
							toBemodifyStatus = "<font color='red'>Address Change Issue is already raised.</font>";
						}
							request.setAttribute("statusMsg",toBemodifyStatus );
							CommonDAOImpl comObj1 = new CommonDAOImpl();
							sadaremData = comObj.getSadaremCommonData(sadaremId);
							request.setAttribute("selIssueType",selIssueType);
						    request.setAttribute("sadaremData", sadaremData);
						    request.setAttribute("proofTypeList", proofTypeList);
						    session.setAttribute("FormSessionID",""+Math.random());
						    
						   if(sadaremData!=null)
						   {
							   sadaremData=(ArrayList)sadaremData.get(0);
						   
						   }
						   if(roleId==CommonConstants.SPMLOGIN_ROLEID)
						   {
							    distId    =  (String)sadaremData.get(1);
						   }
						   else
						   {
							    distId         = (String)session.getAttribute("districtId");    
						   }
						   
							//String distId     = (String) session.getAttribute("districtId");
							String mandID     = (String)sadaremData.get(2);
							String villageId  = (String)sadaremData.get(3);
					    	 
					    	 ArrayList mandalList      = (ArrayList)comObj1.getMandalListByDistrictID(distId);
					    	 ArrayList panchayatList   = (ArrayList)comObj1.getpanchayatListByDistrictIDMandalIDVillageID(distId, mandID);
					    	 ArrayList villageList     = (ArrayList)comObj1.getVillageListByDistrictIDMandalID(distId, mandID);
					    	 ArrayList habitationList  = (ArrayList)comObj1.getHabitationListByDistrictIDMandalIDvillageID(distId, mandID, villageId);
					    	 
					    	 	String issueName = null;
					            issueName = comObj.getIssueName(selIssueType);            
					            request.setAttribute("issueName", issueName);
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
	    return mapping.findForward("addressChange");
	}
}
