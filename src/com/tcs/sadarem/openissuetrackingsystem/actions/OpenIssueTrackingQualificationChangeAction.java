
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

public class OpenIssueTrackingQualificationChangeAction extends DispatchAction {

	static String message = "";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) { 
		
		HashMap educationChangeDtls = new HashMap();
		HashMap educationChangModifyDtls = new HashMap();
		HttpSession session = request.getSession();
		String toBemodifyStatus=""; 
		ArrayList sadaremData = new ArrayList();
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    educationChangeDtls = CommonUtility.getDiskFileItemFactory(sfu, request); 
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	    Integer basicInsertStatus;
	    ArrayList educationList = new ArrayList();
	    
	    try{
	    	if(educationChangeDtls.size()>0)
	    	{
	    		String otp     	= ((FileItem) educationChangeDtls.get("otpid")).getString();
				String sadaremId    = ((FileItem) educationChangeDtls.get("sadaremId")).getString();
				String generetedOtp=comObj.checkOTPforSadaremID(sadaremId,otp);
				
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
	    	
			    String distName       = (String)session.getAttribute("logDistName");
			   // String relationname   = ((FileItem) educationChangeDtls.get("relationname")).getString();
			    String educationType   = ((FileItem) educationChangeDtls.get("educationType")).getString();
			    String selIssueType   = ((FileItem) educationChangeDtls.get("issueType")).getString();
			    String remarks		  =	((FileItem) educationChangeDtls.get("decription")).getString();
			    String issueAlreadyRaised="";
				
		        String FormID		=	((FileItem) educationChangeDtls.get("FormSessionID")).getString();
			    String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
			    
			    String contactNo    =((FileItem) educationChangeDtls.get("contid")).getString();
			    String altcontactNo =((FileItem) educationChangeDtls.get("altcontid")).getString();
			    String emailid      =CommonUtility.checkNullObject(((FileItem) educationChangeDtls.get("emailid")).getString());			    
				  
			    String prooftype1=((FileItem) educationChangeDtls.get("proofidentity_1")).getString();
			    String proofid1=((FileItem) educationChangeDtls.get("proofid_1")).getString();
			    FileItem file1 =   ((FileItem) educationChangeDtls.get("proofDoc_1"));
			    
			    
			    String prooftype2=((FileItem) educationChangeDtls.get("proofidentity_2")).getString();
			    String proofid2=((FileItem) educationChangeDtls.get("proofid_2")).getString();
			    FileItem file2 =   ((FileItem) educationChangeDtls.get("proofDoc_2"));
			    	
				
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
		    	
			 	//educationChangModifyDtls.put("relationname",relationname );
		    	educationChangModifyDtls.put("ipaddress",ipaddress );
				educationChangModifyDtls.put("educationType",educationType );
				educationChangModifyDtls.put("sadaremId",sadaremId );
				educationChangModifyDtls.put("reqId",reqId );
				educationChangModifyDtls.put("selIssueType",selIssueType );
				educationChangModifyDtls.put("reqBy",sadaremId); 
				educationChangModifyDtls.put("decisionFlag",decisionFlag );
				educationChangModifyDtls.put("remarks",remarks );
				educationChangModifyDtls.put("contactNo",contactNo );
				educationChangModifyDtls.put("altcontactNo",altcontactNo );
				educationChangModifyDtls.put("emailid",emailid );
				
				educationChangModifyDtls.put("proofTypeID1", prooftype1);
				educationChangModifyDtls.put("proofId1", proofid1);
				educationChangModifyDtls.put("docType1",filetype1 );
				educationChangModifyDtls.put("filePath1",reqId+"/"+proofid1);
				educationChangModifyDtls.put("fileDoc1",file1);
				educationChangModifyDtls.put("fileSize1",file_size1);
				
				
				educationChangModifyDtls.put("proofTypeID2", prooftype2);
				educationChangModifyDtls.put("proofId2", proofid2);
				educationChangModifyDtls.put("docType2",filetype2 );
				educationChangModifyDtls.put("filePath2",reqId+"/"+proofid2);
				educationChangModifyDtls.put("fileDoc2",file2);
				educationChangModifyDtls.put("fileSize2",file_size1);
				
				issueAlreadyRaised = comObj.getRaisedIssuesCount(sadaremId, selIssueType);
				 
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
						toBemodifyStatus = comObj.insertOpenEduNameChangeDtls(educationChangModifyDtls);
					}
				}
				else
				{
					toBemodifyStatus = "<font color='red'>Qualification Change Issue is already raised.</font>";
				}
					request.setAttribute("statusMsg",toBemodifyStatus );
					sadaremData = comObj.getSadaremCommonData(sadaremId);
					educationList = comObj.getEducationList();
			    	request.setAttribute("educationList", educationList); 
			
			          request.setAttribute("selIssueType",selIssueType);
		              request.setAttribute("sadaremData", sadaremData);
		              session.setAttribute("FormSessionID",""+Math.random());
					}
					else
					{
						request.setAttribute("statusMsg", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
				 		
						return mapping.findForward("loadpage");
					}
				} 
				else
				{
					request.setAttribute("statusMsg", "<font color='red'size='3'><b>Invalid OTP</b></font>"); 	 		
			 		
					return mapping.findForward("loadpage");
				}
			  } 
			    else
			    {
					request.setAttribute("statusMsg", "<font color='red'size='3'><b>Invalid CAPTCHA</b></font>"); 	 		
				 	return mapping.findForward("loadpage");
				}
	    	}
			else
			{
				request.setAttribute("statusMsg", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("loadpage");
			}
		    }catch(Exception e)
		    {
		    	
		    	e.printStackTrace();
		    	request.setAttribute("statusMsg", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("loadpage");
		    }
		return mapping.findForward("loadpage");
	}

}

