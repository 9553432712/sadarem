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

public class OpenIssueTrackingGenderChangeAction extends DispatchAction {

	static String message = "";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HashMap genderChangeDtls = new HashMap();
		HttpSession session = request.getSession();
		HashMap genderChangModifyDtls = new HashMap();
		ArrayList sadaremData = new ArrayList();
		int basicInsertStatus=0;
		String toBemodifyStatus="";
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    genderChangeDtls = CommonUtility.getDiskFileItemFactory(sfu, request); 
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	    
	    try{
	    	if (CommonUtility.checkNullObj(request.getParameter("txtcaptch")).equals(CommonUtility.checkNullObj(request.getParameter("txtInput"))))
			 {
				
				if(genderChangeDtls.size()>0)
				{
					String otp     	= ((FileItem) genderChangeDtls.get("otpid")).getString();
					String sadaremId    = ((FileItem) genderChangeDtls.get("sadaremId")).getString();
					String generetedOtp= comObj.checkOTPforSadaremID(sadaremId,otp);
					
					if(generetedOtp.equals("Y"))
					{
					 
					 ArrayList FileTypeAllowedList = new ArrayList();
				     FileTypeAllowedList.add("pdf");
				     
				     String fileContentTypeAllowed = "application/pdf";
					 String fileName1="",filetype1="",fileContentType1="";
					 String fileName2="",filetype2="",fileContentType2="";
					 long file_size1  = 0;
					 long file_size2  = 0;
					
					
					String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
				    String gender     	= ((FileItem) genderChangeDtls.get("gender")).getString();
				    String genderId   	= ((FileItem) genderChangeDtls.get("genderId")).getString();
				    String selIssueType = ((FileItem) genderChangeDtls.get("issueType")).getString();
				    String FormID		= ((FileItem) genderChangeDtls.get("FormSessionID")).getString();
				    String remarks		= ((FileItem) genderChangeDtls.get("decription")).getString();
				    String contactNo    =((FileItem) genderChangeDtls.get("contid")).getString();
				    String altcontactNo =((FileItem) genderChangeDtls.get("altcontid")).getString();
				    String emailid      =CommonUtility.checkNullObject(((FileItem) genderChangeDtls.get("emailid")).getString());			    
					  
				    String proofType1   = ((FileItem) genderChangeDtls.get("proofidentity_1")).getString();
				    String proofId1     = ((FileItem) genderChangeDtls.get("proofid_1")).getString();
				    FileItem file1      = ((FileItem) genderChangeDtls.get("proofDoc_1"));
				    
				    String proofType2   = ((FileItem) genderChangeDtls.get("proofidentity_2")).getString();
				    String proofId2     = ((FileItem) genderChangeDtls.get("proofid_2")).getString();
				    FileItem file2      = ((FileItem) genderChangeDtls.get("proofDoc_2"));
				    
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
					 
			    	int  roleId = 100;
				    String decisionFlag = comObj.getTktDecisionFlag(roleId, "P", "",  selIssueType);
					String reqId =comObj.generateopenIssueReqId(selIssueType);
					
						if(FormID.equals(sessionFormID))
						{ 
							genderChangModifyDtls.put("ipaddress",ipaddress );
							genderChangModifyDtls.put("gender",gender );
							genderChangModifyDtls.put("genderId",genderId );
							genderChangModifyDtls.put("sadaremId",sadaremId );
							genderChangModifyDtls.put("reqId",reqId );
							genderChangModifyDtls.put("selIssueType",selIssueType );
							genderChangModifyDtls.put("reqBy",sadaremId);
							genderChangModifyDtls.put("decisionFlag",decisionFlag );
							genderChangModifyDtls.put("remarks",remarks );
							genderChangModifyDtls.put("contactNo",contactNo );
							genderChangModifyDtls.put("altcontactNo",altcontactNo ); 
							genderChangModifyDtls.put("emailid",emailid );
							
							genderChangModifyDtls.put("proofTypeID1",proofType1 );
							genderChangModifyDtls.put("proofId1",proofId1);
							genderChangModifyDtls.put("docType1",filetype1 );
							genderChangModifyDtls.put("filePath1",reqId+"/"+proofId1);
							genderChangModifyDtls.put("fileDoc1",file1);
							genderChangModifyDtls.put("fileSize1",file_size1);
							
							
							genderChangModifyDtls.put("proofTypeID2",proofType2 );
							genderChangModifyDtls.put("proofId2",proofId2);
							genderChangModifyDtls.put("docType2",filetype2 );
							genderChangModifyDtls.put("filePath2",reqId+"/"+proofId2);
							genderChangModifyDtls.put("fileDoc2",file2);
							genderChangModifyDtls.put("fileSize2",file_size2);
							
							
							
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
									toBemodifyStatus =comObj.insertOpenGenderChangeDtls(genderChangModifyDtls);
								}
							}
							else
							{
								toBemodifyStatus = "<font color='red'>Gender Change Issue is already raised.</font>";
							}
							request.setAttribute("statusMsg",toBemodifyStatus );
						    sadaremData = comObj.getSadaremCommonData(sadaremId);
					        request.setAttribute("sadaremData", sadaremData);
					        request.setAttribute("selIssueType",selIssueType);
					        session.setAttribute("FormSessionID",""+Math.random());
					        
					        String oldGender="";
					    	 int oldGenderId;
					    	 sadaremData = (ArrayList)sadaremData.get(0);
					    	 oldGender = (String)sadaremData.get(15);
					    	 if(oldGender.equalsIgnoreCase("male"))
					    	 {
					    		 oldGender = "Female";
					    		 oldGenderId = 2;
					    	 }
					    	 else
					    	 {
					    		 oldGender = "Male";
					    		 oldGenderId = 1;
					    	 }
					    	 
					    	 request.setAttribute("gender", oldGender);
					    	 request.setAttribute("genderId", oldGenderId);
					    	 
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
				}
				else
				{
					request.setAttribute("statusMsg", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
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
