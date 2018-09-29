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

public class OpenIssueTrackingNameChangeAction extends DispatchAction {

	static String message = "";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HashMap OpennameChangeDtls = new HashMap();
		HttpSession session = request.getSession();
		HashMap OpennameChangModifyDtls = new HashMap();
		HashMap OpennameChangeProofDtls = new HashMap();
		ArrayList sadaremData = new ArrayList();
		int basicInsertStatus=0; 
		String toBemodifyStatus="";
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    OpennameChangeDtls = CommonUtility.getDiskFileItemFactory(sfu, request); 
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	    
	    
	    try{
	    	
				if(OpennameChangeDtls.size()>0)
				{
					String otp     	= ((FileItem) OpennameChangeDtls.get("otpid")).getString();
					String sadaremId    = ((FileItem) OpennameChangeDtls.get("sadaremId")).getString();
					String generetedOtp= comObj.checkOTPforSadaremID(sadaremId,otp);
					
					 String txtcaptcha = ((FileItem) OpennameChangeDtls.get("txtCaptcha")).getString();
					 String captchainput	= ((FileItem) OpennameChangeDtls.get("txtInput")).getString();
				
					if (txtcaptcha.equals(captchainput))
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
					    
					   
						
						String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
					    String fname     	= ((FileItem) OpennameChangeDtls.get("fname")).getString();
					    
					    String selIssueType = ((FileItem) OpennameChangeDtls.get("issueType")).getString();
					    String FormID		= ((FileItem) OpennameChangeDtls.get("FormSessionID")).getString();
					    String remarks		= ((FileItem) OpennameChangeDtls.get("decription")).getString();
					    
					    String contactNo    =((FileItem) OpennameChangeDtls.get("contid")).getString();
					    String altcontactNo =((FileItem) OpennameChangeDtls.get("altcontid")).getString();
					    String emailid      =CommonUtility.checkNullObject(((FileItem) OpennameChangeDtls.get("emailid")).getString());			    
						     
					    String prooftype1=((FileItem) OpennameChangeDtls.get("proofidentity_1")).getString();
					    String proofid1=((FileItem) OpennameChangeDtls.get("proofid_1")).getString();
					    FileItem file1 =   ((FileItem) OpennameChangeDtls.get("proofDoc_1"));
					     
					    
					    String prooftype2=((FileItem) OpennameChangeDtls.get("proofidentity_2")).getString();
					    String proofid2=((FileItem) OpennameChangeDtls.get("proofid_2")).getString();
					    FileItem file2 =   ((FileItem) OpennameChangeDtls.get("proofDoc_2"));
					    	
						
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
							  OpennameChangModifyDtls.put("ipaddress",ipaddress );
								OpennameChangModifyDtls.put("fname",fname );
								OpennameChangModifyDtls.put("sadaremId",sadaremId );
								OpennameChangModifyDtls.put("reqId",reqId );
								OpennameChangModifyDtls.put("selIssueType",selIssueType );
								OpennameChangModifyDtls.put("reqBy",sadaremId);
								OpennameChangModifyDtls.put("decisionFlag",decisionFlag );
								OpennameChangModifyDtls.put("remarks",remarks );
								OpennameChangModifyDtls.put("contactNo",contactNo );
								OpennameChangModifyDtls.put("altcontactNo",altcontactNo );
								
								OpennameChangModifyDtls.put("emailid",emailid );
								OpennameChangModifyDtls.put("proofTypeID1", prooftype1);
								OpennameChangModifyDtls.put("proofId1", proofid1);
								OpennameChangModifyDtls.put("docType1",filetype1 );
								OpennameChangModifyDtls.put("filePath1",reqId+"/"+proofid1);
								OpennameChangModifyDtls.put("fileDoc1",file1);
								OpennameChangModifyDtls.put("fileSize1",file_size1);
								
								
								OpennameChangModifyDtls.put("proofTypeID2", prooftype2);
								OpennameChangModifyDtls.put("proofId2", proofid2);
								OpennameChangModifyDtls.put("docType2",filetype2 );
								OpennameChangModifyDtls.put("filePath2",reqId+"/"+proofid2);
								OpennameChangModifyDtls.put("fileDoc2",file2);
								OpennameChangModifyDtls.put("fileSize2",file_size1);
								
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
											toBemodifyStatus =comObj.insertOpenNameChangeDtls(OpennameChangModifyDtls);
										}
									}
								else
								{
									toBemodifyStatus = "<font color='red'>Name Change Issue is already raised.</font>";
								}
								request.setAttribute("statusMsg",toBemodifyStatus );
							    sadaremData = comObj.getSadaremCommonData(sadaremId);
						        request.setAttribute("sadaremData", sadaremData);
						        request.setAttribute("selIssueType",selIssueType);
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
				  } 
					else
					{
						request.setAttribute("statusMsg", "<font color='red'size='3'><b>Invalid CAPTCHA</b></font>"); 	 		
					 	return mapping.findForward("loadpage");
					}
					
				}
				else
				{
					request.setAttribute("statusMsg", "<font color='red'size='3'><b>Sorry we cannot process your request.<br>Please Try Again..!</b></font>"); 	 		
			 		return mapping.findForward("loadpage");
				}
	        
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	request.setAttribute("statusMsg", "<font color='red'size='3'><b>Sorry we cannot process your request.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("loadpage");
	    }
		return mapping.findForward("loadpage");
	}

}
