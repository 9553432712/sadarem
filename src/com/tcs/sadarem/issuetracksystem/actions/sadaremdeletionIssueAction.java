package com.tcs.sadarem.issuetracksystem.actions;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
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
import org.apache.struts.upload.FormFile;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class sadaremdeletionIssueAction extends BaseDispatchAction 
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		HashMap SadaremchangeDtls = new HashMap();
		HashMap addressChangModifyDtls = new HashMap();
		HttpSession session = request.getSession();
		String toBemodifyStatus="";
		ArrayList sadaremData = new ArrayList();
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj  = new CommonIssueTrackingDAOImpl();
	    SadaremchangeDtls              = CommonUtility.getDiskFileItemFactory(sfu, request); 
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	    Integer basicInsertStatus;
	     
	    try{
	    	if(SadaremchangeDtls.size()>0)
	    	{
	    		ArrayList FileTypeAllowedList = new ArrayList();
	    		 ArrayList proofTypeList       = new ArrayList();
			     FileTypeAllowedList.add("pdf");
			     String fileContentTypeAllowed = "application/pdf";
			     String fileName1="",filetype1="",fileContentType1="";
				 long file_size1  = 0;
				 String fileName2="",filetype2="",fileContentType2="";
				 long file_size2  = 0;
				
			   
			    String sadaremId        = ((FileItem) SadaremchangeDtls.get("sadaremid")).getString();
			    String selIssueType     = ((FileItem) SadaremchangeDtls.get("selIssueType")).getString();
			    String remarks          = ((FileItem) SadaremchangeDtls.get("decription")).getString();
			    FileItem file           = ((FileItem) SadaremchangeDtls.get("proofDoc"));
				String FormID		    = ((FileItem) SadaremchangeDtls.get("FormSessionID")).getString();
				String MobNo 			= ((FileItem) SadaremchangeDtls.get("contid")).getString(); 
				
				String proofType1       = ((FileItem) SadaremchangeDtls.get("proofidentity_1")).getString();
				String proofId1         = ((FileItem) SadaremchangeDtls.get("proofid_1")).getString();
				FileItem file1          = ((FileItem) SadaremchangeDtls.get("proofDoc_1"));
				    
			    String proofType2   	= ((FileItem) SadaremchangeDtls.get("proofidentity_2")).getString();
			    String proofId2    		= ((FileItem) SadaremchangeDtls.get("proofid_2")).getString();
			    FileItem file2          = ((FileItem) SadaremchangeDtls.get("proofDoc_2"));
				    
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
								toBemodifyStatus = comObj.insertSadaremIDDeletionDtls(addressChangModifyDtls);
							}
							 
						}
						else
						{
							toBemodifyStatus = "<font color='red'>SADAREM ID Deletion Issue is already raised.</font>";
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
					   sadaremData=(ArrayList)sadaremData.get(0);
				   
				   }
					
						}
						else
						{
							request.setAttribute("MSG", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
					 		
							return mapping.findForward("exception");
						}
				
	    	
	    }	
	    	else{
			request.setAttribute("MSG", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
	 		
			//return mapping.findForward("exception");
		}
	    	
	    }
	    catch(Exception e)
	    {
	    		    	//e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");	
	    }
	    return mapping.findForward("sadaremdeletion");
	}
}
