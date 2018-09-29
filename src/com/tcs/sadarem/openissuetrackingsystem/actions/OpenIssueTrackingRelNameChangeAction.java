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

public class OpenIssueTrackingRelNameChangeAction extends DispatchAction {

	static String message = "";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) { 
		
		HashMap relnameChangeDtls = new HashMap();
		HashMap relnameChangModifyDtls = new HashMap();
		HttpSession session = request.getSession();
		String toBemodifyStatus=""; 
		ArrayList sadaremData = new ArrayList();
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    relnameChangeDtls = CommonUtility.getDiskFileItemFactory(sfu, request);  
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	    Integer basicInsertStatus;
	    ArrayList relationTypeList = new ArrayList();
	    
	    try{
	    	if(relnameChangeDtls.size()>0)
	    	{
	    		String otp     	= ((FileItem) relnameChangeDtls.get("otpid")).getString();
				String sadaremId    = ((FileItem) relnameChangeDtls.get("sadaremId")).getString();
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
			    String relationname   = ((FileItem) relnameChangeDtls.get("relationname")).getString();
			    String relationtype   = ((FileItem) relnameChangeDtls.get("relationType")).getString();
			    String selIssueType   = ((FileItem) relnameChangeDtls.get("issueType")).getString();
			    String remarks		  =	((FileItem) relnameChangeDtls.get("decription")).getString();
			    String issueAlreadyRaised="";
				
		        String FormID		=	((FileItem) relnameChangeDtls.get("FormSessionID")).getString();
			    String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
			    
			    String contactNo    =((FileItem) relnameChangeDtls.get("contid")).getString();
			    String altcontactNo =((FileItem) relnameChangeDtls.get("altcontid")).getString();
			    String emailid      =CommonUtility.checkNullObject(((FileItem) relnameChangeDtls.get("emailid")).getString());			    
				    
			    
			    String prooftype1=((FileItem) relnameChangeDtls.get("proofidentity_1")).getString();
			    String proofid1=((FileItem) relnameChangeDtls.get("proofid_1")).getString();
			    FileItem file1 =   ((FileItem) relnameChangeDtls.get("proofDoc_1"));
			    
			    
			    String prooftype2=((FileItem) relnameChangeDtls.get("proofidentity_2")).getString();
			    String proofid2=((FileItem) relnameChangeDtls.get("proofid_2")).getString();
			    FileItem file2 =   ((FileItem) relnameChangeDtls.get("proofDoc_2"));
			    	
				
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
		    	
		    	relnameChangModifyDtls.put("ipaddress",ipaddress);
			 	relnameChangModifyDtls.put("relationname",relationname );
				relnameChangModifyDtls.put("relationtype",relationtype );
				relnameChangModifyDtls.put("sadaremId",sadaremId );
				relnameChangModifyDtls.put("reqId",reqId );
				relnameChangModifyDtls.put("selIssueType",selIssueType );
				relnameChangModifyDtls.put("reqBy",sadaremId); 
				relnameChangModifyDtls.put("decisionFlag",decisionFlag );
				relnameChangModifyDtls.put("remarks",remarks );
				relnameChangModifyDtls.put("contactNo",contactNo );
				relnameChangModifyDtls.put("altcontactNo",altcontactNo );
				relnameChangModifyDtls.put("emailid",emailid );
				
				relnameChangModifyDtls.put("proofTypeID1", prooftype1);
				relnameChangModifyDtls.put("proofId1", proofid1);
				relnameChangModifyDtls.put("docType1",filetype1 );
				relnameChangModifyDtls.put("filePath1",reqId+"/"+proofid1);
				relnameChangModifyDtls.put("fileDoc1",file1);
				relnameChangModifyDtls.put("fileSize1",file_size1);
				
				
				relnameChangModifyDtls.put("proofTypeID2", prooftype2);
				relnameChangModifyDtls.put("proofId2", proofid2);
				relnameChangModifyDtls.put("docType2",filetype2 );
				relnameChangModifyDtls.put("filePath2",reqId+"/"+proofid2);
				relnameChangModifyDtls.put("fileDoc2",file2);
				relnameChangModifyDtls.put("fileSize2",file_size1);
				
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
						toBemodifyStatus = comObj.insertOpenRelNameChangeDtls(relnameChangModifyDtls);
					}
				}
				else
				{
					toBemodifyStatus = "<font color='red'>Relation Change Issue is already raised.</font>";
				}
					request.setAttribute("statusMsg",toBemodifyStatus );
					sadaremData = comObj.getSadaremCommonData(sadaremId);
					relationTypeList = comObj.getRelationType((String)((ArrayList)sadaremData.get(0)).get(15));
			    	request.setAttribute("relationTypeList", relationTypeList); 
			
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
