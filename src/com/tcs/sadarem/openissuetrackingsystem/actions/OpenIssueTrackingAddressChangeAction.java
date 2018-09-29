package com.tcs.sadarem.openissuetrackingsystem.actions;

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
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class OpenIssueTrackingAddressChangeAction extends DispatchAction {

	static String message = "";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		
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
	     
	    try{
	    	if(addressChangeDtls.size()>0)
	    	{
	    		String otp     	= ((FileItem) addressChangeDtls.get("otpid")).getString();
				String sadaremId    = ((FileItem) addressChangeDtls.get("sadaremId")).getString();
				String generetedOtp= comObj.checkOTPforSadaremID(sadaremId,otp);
				 
			if (CommonUtility.checkNullObj(request.getParameter("txtcaptch")).equals(CommonUtility.checkNullObj(request.getParameter("txtInput"))))
			 {
				
				if(generetedOtp.equals("Y"))
				{
					ArrayList FileTypeAllowedList = new ArrayList();
				     FileTypeAllowedList.add("pdf");
				     String fileContentTypeAllowed = "application/pdf";
				     String fileName1="",filetype1="",fileContentType1="";
					 String fileName2="",filetype2="",fileContentType2="";
					
					  long file_size1  = 0;
				      long file_size2  = 0;
	    		
	    		
	    		
			    String distName         = ((FileItem) addressChangeDtls.get("distId")).getString();
			    String manName   	    = ((FileItem) addressChangeDtls.get("mandals")).getString();
			    String villName   	    = ((FileItem) addressChangeDtls.get("villages")).getString();
			    String habName          = ((FileItem) addressChangeDtls.get("habitation")).getString();
			    String houseNo          = ((FileItem) addressChangeDtls.get("houseno")).getString();
			    String pincode          = ((FileItem) addressChangeDtls.get("pincode")).getString();
			    String selIssueType     = ((FileItem) addressChangeDtls.get("issueType")).getString();
			    String remarks          = ((FileItem) addressChangeDtls.get("decription")).getString();
			    String FormID		    = ((FileItem) addressChangeDtls.get("FormSessionID")).getString();
			    String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
			
			
				String contactNo    =((FileItem) addressChangeDtls.get("contid")).getString();
			    String altcontactNo =((FileItem) addressChangeDtls.get("altcontid")).getString();

			    String emailid      =CommonUtility.checkNullObject(((FileItem) addressChangeDtls.get("emailid")).getString());			    
			    
			    String prooftype1=((FileItem) addressChangeDtls.get("proofidentity_1")).getString();
			    String proofid1=((FileItem) addressChangeDtls.get("proofid_1")).getString();
			    FileItem file1 =   ((FileItem) addressChangeDtls.get("proofDoc_1"));
			    
			    
			    String prooftype2=((FileItem) addressChangeDtls.get("proofidentity_2")).getString();
			    String proofid2=((FileItem) addressChangeDtls.get("proofid_2")).getString();
			    FileItem file2 =   ((FileItem) addressChangeDtls.get("proofDoc_2"));
				
				
				
			    file_size1 = file1.getSize();
		    	if(file_size1 != 0) 
				{
					fileName1 = file1.getName();
					filetype1 = fileName1.substring((fileName1.lastIndexOf(".")+1));
					fileContentType1 = file1.getContentType().toLowerCase();
				}
		    	 
		    	 file_size2 = file2.getSize();
				if(file_size2 !=0)
				{	
					fileName2 = file2.getName();
					filetype2 = fileName2.substring((fileName2.lastIndexOf(".")+1));
					fileContentType2 = file2.getContentType().toLowerCase();
				}
				 
				int roleId = 100;
			    String decisionFlag = comObj.getTktDecisionFlag(roleId, "P", "",  selIssueType);
				String reqId = comObj.generateopenIssueReqId(selIssueType);
				
		
			    if(FormID.equals(sessionFormID))
			    {  
			    	addressChangModifyDtls.put("ipaddress",ipaddress );
				    
					 	addressChangModifyDtls.put("distName",distName );
						addressChangModifyDtls.put("manName",manName );
						addressChangModifyDtls.put("villName",villName );
						addressChangModifyDtls.put("habName",habName );
						addressChangModifyDtls.put("houseNo",houseNo );
						addressChangModifyDtls.put("pincode",pincode );
						
						addressChangModifyDtls.put("reqId",reqId );
						addressChangModifyDtls.put("sadaremId",sadaremId );
						addressChangModifyDtls.put("selIssueType",selIssueType );
						addressChangModifyDtls.put("reqBy",sadaremId); 
						addressChangModifyDtls.put("decisionFlag",decisionFlag );
						addressChangModifyDtls.put("remarks",remarks );
						addressChangModifyDtls.put("contactNo",contactNo );
						addressChangModifyDtls.put("altcontactNo",altcontactNo );

						addressChangModifyDtls.put("emailid",emailid );
						
						
						addressChangModifyDtls.put("proofTypeID1", prooftype1);
						addressChangModifyDtls.put("proofId1", proofid1);
						addressChangModifyDtls.put("docType1",filetype1 );
						addressChangModifyDtls.put("filePath1",reqId+"/"+proofid1);
						addressChangModifyDtls.put("fileDoc1",file1);
						addressChangModifyDtls.put("fileSize1",file_size1);
						
						
						addressChangModifyDtls.put("proofTypeID2", prooftype2);
						addressChangModifyDtls.put("proofId2", proofid2);
						addressChangModifyDtls.put("docType2",filetype2 );
						addressChangModifyDtls.put("filePath2",reqId+"/"+proofid2);
						addressChangModifyDtls.put("fileDoc2",file2);
						addressChangModifyDtls.put("fileSize2",file_size1);
						
						 
						String issueAlreadyRaised = comObj.getRaisedIssuesCount(sadaremId, selIssueType);
						
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
								toBemodifyStatus = comObj.insertOpenAddressChangeDtls(addressChangModifyDtls);
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
				    session.setAttribute("FormSessionID",""+Math.random());
				    }
						else
						{
							request.setAttribute("statusMsg", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
					 		
							return mapping.findForward("loadpage");
					    }
					} else{
						request.setAttribute("statusMsg", "<font color='red'size='3'><b>Invalid OTP</b></font>"); 	 		
					 	return mapping.findForward("loadpage");
					}
			     } else{
					request.setAttribute("statusMsg", "<font color='red'size='3'><b>Invalid CAPTCHA</b></font>"); 	 		
				 	return mapping.findForward("loadpage");
				}
				
				}else{
				  request.setAttribute("statusMsg", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
		 		  return mapping.findForward("loadpage");
			    }
	    	
	    }
	    catch(Exception e)
	    {
	    		    	e.printStackTrace();
	    	request.setAttribute("statusMsg", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("loadpage");	
	    }
	    return mapping.findForward("loadpage");
	}
	
}
