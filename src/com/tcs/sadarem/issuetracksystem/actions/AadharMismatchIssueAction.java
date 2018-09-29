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
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class AadharMismatchIssueAction extends DispatchAction 
{
	public ActionForward raiseNameIssue(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		HashMap aadharChangeDtls = new HashMap();
		HttpSession session = request.getSession();
		HashMap aadharChangModifyDtls = new HashMap();
		ArrayList sadaremData = new ArrayList();
		int basicInsertStatus=0;
		String toBemodifyStatus="";
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    aadharChangeDtls = CommonUtility.getDiskFileItemFactory(sfu, request); 
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	   
	    try{
	    	
				if(aadharChangeDtls.size()>0)
				{
					 ArrayList FileTypeAllowedList = new ArrayList();
					 ArrayList proofTypeList       = new ArrayList();
				     FileTypeAllowedList.add("pdf");
				     String fileContentTypeAllowed = "application/pdf";
				     String fileName1="",filetype1="",fileContentType1="";
					 long file_size1  = 0;
					 String fileName2="",filetype2="",fileContentType2="";
					 long file_size2  = 0;	
				    
				    String aadharno   	= ((FileItem) aadharChangeDtls.get("aadharno")).getString();
				    String sadaremId    = ((FileItem) aadharChangeDtls.get("sadaremid")).getString();
				    String selIssueType = ((FileItem) aadharChangeDtls.get("selIssueType")).getString();
				    String FormID		= ((FileItem) aadharChangeDtls.get("FormSessionID")).getString();
				    String remarks		= ((FileItem) aadharChangeDtls.get("decription")).getString();
				    String dupSadaremId = ((FileItem) aadharChangeDtls.get("dupsadaremId")).getString();
				    String MobNo        = ((FileItem) aadharChangeDtls.get("contid")).getString(); 
				    
				    
				    String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
				    String proofType1   = ((FileItem) aadharChangeDtls.get("proofidentity_1")).getString();
				    String proofId1     = ((FileItem) aadharChangeDtls.get("proofid_1")).getString();
				    FileItem file1      = ((FileItem) aadharChangeDtls.get("proofDoc_1"));
				    
				    String proofType2   = ((FileItem) aadharChangeDtls.get("proofidentity_2")).getString();
				    String proofId2     = ((FileItem) aadharChangeDtls.get("proofid_2")).getString();
				    FileItem file2      = ((FileItem) aadharChangeDtls.get("proofDoc_2"));
		    
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
				    int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
				    
				    String decisionFlag = comObj.getTktDecisionFlag(roleId, "P", "",  selIssueType);
				    proofTypeList = comObj.getproofdoucments();
					
						if(FormID.equals(sessionFormID))
						{
							
							String reqId = comObj.generateIssueReqId(selIssueType);
							aadharChangModifyDtls.put("ipaddress",ipaddress );
							aadharChangModifyDtls.put("aadharno",aadharno );
							aadharChangModifyDtls.put("sadaremId",sadaremId );
							aadharChangModifyDtls.put("reqId",reqId );
							aadharChangModifyDtls.put("selIssueType",selIssueType );
							aadharChangModifyDtls.put("reqBy",(String)session.getAttribute("loginid") );
							aadharChangModifyDtls.put("decisionFlag",decisionFlag );
							aadharChangModifyDtls.put("remarks",remarks );
							aadharChangModifyDtls.put("dupSadaremId",CommonUtility.checkNullObj(dupSadaremId) );
							aadharChangModifyDtls.put("MobNo",MobNo );
							
							aadharChangModifyDtls.put("proofTypeID1",proofType1 );
							aadharChangModifyDtls.put("proofId1",proofId1);
							aadharChangModifyDtls.put("docType1",filetype1 );
							aadharChangModifyDtls.put("filePath1",reqId+"/"+proofId1); 
							aadharChangModifyDtls.put("fileDoc1",file1);
							aadharChangModifyDtls.put("fileSize1",file_size1);
							
							
							aadharChangModifyDtls.put("proofTypeID2",proofType2 );
							aadharChangModifyDtls.put("proofId2",proofId2);
							aadharChangModifyDtls.put("docType2",filetype2 );
							aadharChangModifyDtls.put("filePath2",reqId+"/"+proofId2); 
							aadharChangModifyDtls.put("fileDoc2",file2);
							aadharChangModifyDtls.put("fileSize2",file_size2);
							
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
									toBemodifyStatus = comObj.insertaadharChangeDtls(aadharChangModifyDtls);
								}
							}
							else
							{
								toBemodifyStatus = "<font color='red'>Aadhar Change Issue is already raised and it is pending.</font>";
							}
							String issueName = null;
				            issueName = comObj.getIssueName(selIssueType);            
				            request.setAttribute("issueName", issueName);
							request.setAttribute("statusMsg",toBemodifyStatus );
						    sadaremData = comObj.getSadaremCommonData(sadaremId);
					        request.setAttribute("sadaremData", sadaremData);
					        request.setAttribute("selIssueType",selIssueType);
					        request.setAttribute("proofTypeList", proofTypeList);
					        session.setAttribute("FormSessionID",""+Math.random());
						}
						else
						{
							request.setAttribute("MSG", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
					 		
							return mapping.findForward("exception");
						}
				}
				else
				{
					request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
			 		return mapping.findForward("exception");
				}
	        
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
	    }
		return mapping.findForward("aadharMismatchChange");
	}
	
	
	 
}
