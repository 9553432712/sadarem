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
import com.tcs.sadarem.util.CommonUtility;

public class PhotoChangeIssueAction extends BaseDispatchAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		HashMap photoChangeDtls = new HashMap();
		HttpSession session = request.getSession();
		HashMap photoChangModifyDtls = new HashMap();
		ArrayList sadaremData = new ArrayList();
		int basicInsertStatus=0;
		String toBemodifyStatus="";
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    photoChangeDtls = CommonUtility.getDiskFileItemFactory(sfu, request);
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	   
	    try{
	    	
				if(photoChangeDtls.size()>0)
				{
					 ArrayList FileTypeAllowedList = new ArrayList();
				     FileTypeAllowedList.add("pdf");
				     String fileContentTypeAllowed = "application/pdf";
				     String fileName1="",filetype1="",fileContentType1="";
					 long file_size1  = 0;
					 String fileName2="",filetype2="",fileContentType2="";
					 long file_size2  = 0;
					 long photoFileSize=0;
					 
					    ArrayList photoFileTypeAllowedList = new ArrayList();
				    	photoFileTypeAllowedList.add("jpg");
				    	photoFileTypeAllowedList.add("jpeg");
				    	photoFileTypeAllowedList.add("png");
				    	String photofileContentTypeAllowed = "image/jpg,image/png,image/jpeg";
					String fileName="",filetype="",photofileName="",photofiletype="",fileContentType="",photoContentType="";
				    
					
				    String sadaremId    = ((FileItem) photoChangeDtls.get("sadaremid")).getString();
				    String selIssueType = ((FileItem) photoChangeDtls.get("selIssueType")).getString();
				    String FormID		= ((FileItem) photoChangeDtls.get("FormSessionID")).getString();
				    String remarks		= ((FileItem) photoChangeDtls.get("decription")).getString();
				    String MobNo        = ((FileItem) photoChangeDtls.get("contid")).getString();
				    String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
				    
				    String proofType1   = ((FileItem) photoChangeDtls.get("proofidentity_1")).getString();
				    String proofId1     = ((FileItem) photoChangeDtls.get("proofid_1")).getString();
				    FileItem file1      = ((FileItem) photoChangeDtls.get("proofDoc_1"));
				    
				    String proofType2   = ((FileItem) photoChangeDtls.get("proofidentity_2")).getString();
				    String proofId2     = ((FileItem) photoChangeDtls.get("proofid_2")).getString();
				    FileItem file2      = ((FileItem) photoChangeDtls.get("proofDoc_2"));
				    FileItem photoFile =  ((FileItem) photoChangeDtls.get("manualPhoto")); 
					   
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
					String reqId = comObj.generateIssueReqId(selIssueType);
					
						if(FormID.equals(sessionFormID))
						{
							photoChangModifyDtls.put("ipaddress",ipaddress );
							photoChangModifyDtls.put("sadaremId",sadaremId );
							photoChangModifyDtls.put("reqId",reqId );
							photoChangModifyDtls.put("selIssueType",selIssueType );
							photoChangModifyDtls.put("reqBy",(String)session.getAttribute("loginid") );
							photoChangModifyDtls.put("decisionFlag",decisionFlag );
							photoChangModifyDtls.put("remarks",remarks ); 
							photoChangModifyDtls.put("MobNo",MobNo ); 
							
							photoChangModifyDtls.put("proofTypeID1",proofType1 );
							photoChangModifyDtls.put("proofId1",proofId1);
							photoChangModifyDtls.put("docType1",filetype1 );
							photoChangModifyDtls.put("filePath1",reqId+"/"+proofId1); 
							photoChangModifyDtls.put("fileDoc1",file1);
							photoChangModifyDtls.put("fileSize1",file_size1);
							
							
							photoChangModifyDtls.put("proofTypeID2",proofType2 );
							photoChangModifyDtls.put("proofId2",proofId2);
							photoChangModifyDtls.put("docType2",filetype2 );
							photoChangModifyDtls.put("filePath2",reqId+"/"+proofId2); 
							photoChangModifyDtls.put("fileDoc2",file2);
							photoChangModifyDtls.put("fileSize2",file_size2);
							
							String issueAlreadyRaised = comObj.getRaisedIssuesCount(sadaremId, selIssueType);
							
							if(!issueAlreadyRaised.equals("N"))
							{
							 
									photoFileSize = photoFile.getSize();
									if(photoFileSize !=0)
									{
										  photofileName = photoFile.getName();
										  photofiletype = photofileName.substring((photofileName.lastIndexOf(".")+1));
										  photoContentType = photoFile.getContentType().toLowerCase();
										  
									}
									photoChangModifyDtls.put("photodocType",photofiletype );
									photoChangModifyDtls.put("photoDoc",photoFile );  
									
									
									if(!photoFileTypeAllowedList.contains(photofiletype.toLowerCase()))
									{
										toBemodifyStatus = "<font color='red'>Please upload valid jpg/png/jpeg photo.</font>";
									}
									else if(photoFileSize>CommonConstants.PHOTODOCMAXLIMIT)
									{
										toBemodifyStatus = "<font color='red'>Please upload valid photo with size less than 100KB.</font>";	
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
									else
									{
										toBemodifyStatus = comObj.insertManualPhotoChangeDtls(photoChangModifyDtls);
									}
								 
								  
							}
							else
							{
								toBemodifyStatus = "<font color='red'>Photo Change Issue is already raised.</font>";
							}
							request.setAttribute("statusMsg",toBemodifyStatus );
						    sadaremData = comObj.getSadaremCommonData(sadaremId);
					        request.setAttribute("sadaremData", sadaremData);
					        request.setAttribute("selIssueType",selIssueType);
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
		return mapping.findForward("manualphotoissue");
	}
	public ActionForward raisephotoIssue(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		HashMap photoChangeDtls = new HashMap();
		HttpSession session = request.getSession();
		HashMap photoChangModifyDtls = new HashMap();
		ArrayList sadaremData = new ArrayList();
		int basicInsertStatus=0;
		String toBemodifyStatus="";
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    photoChangeDtls = CommonUtility.getDiskFileItemFactory(sfu, request);
	   
	    try{
	    	
				if(photoChangeDtls.size()>0)
				{
					 ArrayList FileTypeAllowedList = new ArrayList();
					 ArrayList proofTypeList       = new ArrayList();
				     FileTypeAllowedList.add("pdf");
				     String fileContentTypeAllowed = "application/pdf";
				     String fileName1="",filetype1="",fileContentType1="";
					 long file_size1  = 0;
					 String fileName2="",filetype2="",fileContentType2="";
					 long file_size2  = 0;
					 long photoFileSize=0;
					 
					    ArrayList photoFileTypeAllowedList = new ArrayList();
				    	photoFileTypeAllowedList.add("jpg");
				    	photoFileTypeAllowedList.add("jpeg");
				    	photoFileTypeAllowedList.add("png");
				    	String photofileContentTypeAllowed = "image/jpg,image/png,image/jpeg";
					String fileName="",filetype="",photofileName="",photofiletype="";
				    
					String prefer   	= ((FileItem) photoChangeDtls.get("prefer")).getString();
				    String sadaremId    = ((FileItem) photoChangeDtls.get("sadaremid")).getString();
				    String selIssueType = ((FileItem) photoChangeDtls.get("selIssueType")).getString();
				    String FormID		= ((FileItem) photoChangeDtls.get("FormSessionID")).getString();
				    String remarks		= ((FileItem) photoChangeDtls.get("decription")).getString();
				    String MobNo        = ((FileItem) photoChangeDtls.get("contid")).getString();
				    String aadharid		= ((FileItem) photoChangeDtls.get("aadharid")).getString(); 
				    String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
				    
				    String proofType1   = ((FileItem) photoChangeDtls.get("proofidentity_1")).getString();
				    String proofId1     = ((FileItem) photoChangeDtls.get("proofid_1")).getString();
				    FileItem file1      = ((FileItem) photoChangeDtls.get("proofDoc_1"));
				    
				    String proofType2   = ((FileItem) photoChangeDtls.get("proofidentity_2")).getString();
				    String proofId2     = ((FileItem) photoChangeDtls.get("proofid_2")).getString();
				    FileItem file2      = ((FileItem) photoChangeDtls.get("proofDoc_2"));
				    
				    FileItem photoFile =  ((FileItem) photoChangeDtls.get("manualPhoto")); 
				    
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
					
				 
					proofTypeList = comObj.getproofdoucments();
					
				    int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
				    String decisionFlag = comObj.getTktDecisionFlag(roleId, "P", "",  selIssueType);
					String reqId = comObj.generateIssueReqId(selIssueType);
					
						if(FormID.equals(sessionFormID))
						{
							
						 
							photoChangModifyDtls.put("preference",prefer );
							photoChangModifyDtls.put("sadaremId",sadaremId );
							photoChangModifyDtls.put("reqId",reqId );
							photoChangModifyDtls.put("selIssueType",selIssueType );
							photoChangModifyDtls.put("reqBy",(String)session.getAttribute("loginid") );
							photoChangModifyDtls.put("decisionFlag",decisionFlag );
							photoChangModifyDtls.put("remarks",remarks );
							photoChangModifyDtls.put("MobNo",MobNo ); 

							photoChangModifyDtls.put("proofTypeID1",proofType1 );
							photoChangModifyDtls.put("proofId1",proofId1);
							photoChangModifyDtls.put("docType1",filetype1 );
							photoChangModifyDtls.put("filePath1",reqId+"/"+proofId1); 
							photoChangModifyDtls.put("fileDoc1",file1);
							photoChangModifyDtls.put("fileSize1",file_size1);
							
							
							photoChangModifyDtls.put("proofTypeID2",proofType2 );
							photoChangModifyDtls.put("proofId2",proofId2);
							photoChangModifyDtls.put("docType2",filetype2 );
							photoChangModifyDtls.put("filePath2",reqId+"/"+proofId2); 
							photoChangModifyDtls.put("fileDoc2",file2);
							photoChangModifyDtls.put("fileSize2",file_size2);
							
							photoChangModifyDtls.put("aadharid",aadharid );
							
							String issueAlreadyRaised = comObj.getRaisedIssuesCount(sadaremId, selIssueType);
							
							if(!issueAlreadyRaised.equals("N"))
							{
								
								if(prefer.equals("M"))
								{
									photoFileSize = photoFile.getSize();
									if(photoFileSize !=0)
									{
										  photofileName = photoFile.getName();
										  photofiletype = photofileName.substring((photofileName.lastIndexOf(".")+1));
									}
									photoChangModifyDtls.put("photodocType",photofiletype );
									photoChangModifyDtls.put("photoDoc",photoFile );
									
									if(!photofiletype.equalsIgnoreCase("jpg"))
									{
										toBemodifyStatus = "<font color='red'>Please upload valid jpg photo.</font>";
									}
									else if(photoFileSize>CommonConstants.PHOTODOCMAXLIMIT)
									{
										toBemodifyStatus = "<font color='red'>Please upload valid photo.</font>";	
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
									else
									{
										toBemodifyStatus = comObj.insertPhotoChangeDtls(photoChangModifyDtls);
									}
								}
								else
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
											toBemodifyStatus = comObj.insertPhotoChangeDtls(photoChangModifyDtls);
										}
								}      
							}
							else
							{
								toBemodifyStatus = "<font color='red'>Photo Change Issue is already raised.</font>";
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
		return mapping.findForward("photoChange");
	}
	
	
	 
}
