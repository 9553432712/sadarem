 package com.tcs.sadarem.openissuetrackingsystem.actions;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
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
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.OpenIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.OpenIssueTrackingDAOImpl;
import com.tcs.sadarem.util.CommonUtility;



public class OpenIssueApprovalAction  extends BaseDispatchAction  
{
	
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
	
		ArrayList issueTypeList = new ArrayList();
		OpenIssueTrackingDAO comObj = new OpenIssueTrackingDAOImpl();
		HttpSession session = request.getSession();
		ArrayList pendingIssueDtlsList=new ArrayList();
		CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();
		try{
			int roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			issueTypeList = comObj.getApprovalIssueType(roleId);			
			
		
			 
			 String districtId  	=  CommonUtility.checkNullObj(session.getAttribute("districtId"));		// District Id
			 String mandalId 	  	=  CommonUtility.checkNullObj(session.getAttribute("mandalId")); 		// Mandal Id
			 String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate"));
			 String todate=CommonUtility.checkNullObj(request.getParameter("todate"));
			 String reqType         =  request.getParameter("reqtype");  
			 String tkt_type_id     =  request.getParameter("issueType");  
			 
			 if(tkt_type_id==null)
			 {
				 tkt_type_id = "-1";
			 }
			 
			 if(reqType==null)
			 {
				 reqType = "P";
			 }
			 
			    if(fromdate.equals(""))
			 	{
					fromdate = CommonUtility.getDateAddOrSubDays(-7,"dd/MM/yyyy");
			 	} 
				
				if(todate.equals(""))
			 	{
					todate =   CommonUtility.getDateTime("dd/MM/yyyy"); //"04/01/2015";
					
			 	}
			 
			if("P".equals(reqType))
			{
			 pendingIssueDtlsList   = comObj1.getPendingIssueDtlsList(districtId, mandalId,tkt_type_id, roleId,reqType);
			}
		//	System.out.println(reqType+"--"+tkt_type_id+"--"+districtId+"--"+mandalId+"--"+pendingIssueDtlsList+"--"+issueTypeList);
			
			
			 request.setAttribute("issueTypeList", issueTypeList);
			 request.setAttribute("pendingIssueDtlsList", pendingIssueDtlsList);
			 request.setAttribute("selIssueType",tkt_type_id);
			 request.setAttribute("reqType",reqType);
			 request.setAttribute("fromdate",fromdate);
			  	request.setAttribute("todate",todate);
			
		 }
  		catch(Exception e)
		{
			request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
		}
		return mapping.findForward("openissueApproval");	
	}
	
	public ActionForward pendingIssueReqList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		CommonIssueApprovalDAO comObj = new CommonIssueApprovalDAOImpl();
		ArrayList pendingIssueDtlsList=new ArrayList();
		ArrayList issueTypeList = new ArrayList();
		HttpSession session = request.getSession();
		try
		{
			String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate"));
		    String todate=CommonUtility.checkNullObj(request.getParameter("todate"));
			
			 String districtId  	=  CommonUtility.checkNullObj(session.getAttribute("districtId"));		// District Id
			 String mandalId 	  	=  CommonUtility.checkNullObj(session.getAttribute("mandalId")); 		// Mandal Id
			 int  roleId            =  Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			 String tkt_type_id     =  request.getParameter("issueType");  
			 String reqType         =  request.getParameter("reqtype");  
		
			    if(fromdate.equals(""))
			 	{
					fromdate = CommonUtility.getDateAddOrSubDays(-7,"dd/MM/yyyy");
			 	} 
				
				if(todate.equals(""))
			 	{
					todate =   CommonUtility.getDateTime("dd/MM/yyyy"); //"04/01/2015";
					
			 	}
	 
			 
			 
			if("P".equalsIgnoreCase(reqType))
			{
			 pendingIssueDtlsList   = comObj.getPendingIssueDtlsList(districtId, mandalId,tkt_type_id, roleId,reqType);
			}
			else
			{
				 pendingIssueDtlsList   = comObj.getApprRejIssueDtlsList(districtId, mandalId,tkt_type_id, roleId,reqType,fromdate,todate);
			}
			 issueTypeList = comObj.getOpenIssueApprovalIssueType(roleId);
			 
			 request.setAttribute("issueTypeList", issueTypeList);
			 request.setAttribute("pendingIssueDtlsList", pendingIssueDtlsList);
			 request.setAttribute("selIssueType",tkt_type_id);
			 request.setAttribute("reqType",reqType);
			 request.setAttribute("fromdate",fromdate);
			 request.setAttribute("todate",todate);
		
			 
			 
		}
		catch(Exception e)
		{
			request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
		}
		return mapping.findForward("openissueApproval");
	}
	
	
	public ActionForward getValues(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
	
	CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	OpenIssueTrackingDAO comObj1 = new OpenIssueTrackingDAOImpl();
	ArrayList sadaremData = new ArrayList();
	ArrayList issueData1 = new ArrayList();
	ArrayList issueData = new ArrayList();
	ArrayList historyData = new ArrayList();
	ArrayList  relationTypeList= new ArrayList();
	try{
	String sadaremid	= request.getParameter("sadaremid");
	String reqid		= request.getParameter("requestid");
	String reqtype		= (String)request.getParameter("reqtype");
	String tkttype 		= (String)request.getParameter("tkttype");
	System.out.println(tkttype);
	
	sadaremData 		= comObj.getSadaremCommonData(sadaremid);
     
     request.setAttribute("sadaremData", sadaremData);
     relationTypeList = comObj.getRelationType((String)((ArrayList)sadaremData.get(0)).get(15));
   
	 request.setAttribute("relationTypeList", relationTypeList);
    	 if(tkttype.equals(CommonConstants.NAMECHANGEISSUE_ID))
 		{
            issueData=(ArrayList)comObj1.getNameIssueData(reqid);
 		}
 		else if(tkttype.equals(CommonConstants.RELATIONNAMECHANGEISSUE_ID))
 		{
 			issueData=(ArrayList)comObj1.getRelationIssueData(reqid);	
 		}
 		else if(tkttype.equals(CommonConstants.ADDRESSCHANGEISSUE_ID))
 		{
 			issueData=(ArrayList)comObj1.getAddressIssueData(reqid);	
 		}
 		else if(tkttype.equals(CommonConstants.GENDERISSUE_ID))
 		{
 			issueData=(ArrayList)comObj1.getGenderIssueData(reqid);	
 		}
 		else if(tkttype.equals(CommonConstants.AGEISSUE_ID))
 		{
 			issueData=(ArrayList)comObj1.getAgeIssueData(reqid);	
 		} 
 		else if(tkttype.equals(CommonConstants.QUALIFICATIONISSUE_ID))
 		{
 			issueData=(ArrayList)comObj1.getQualificationIssueData(reqid);	
 		}
    	 
       historyData=(ArrayList)comObj1.getIssueStatusHistory(reqid);
       request.setAttribute("historyData",historyData);
       request.setAttribute("issueData", issueData);
       request.setAttribute("sadaremid", sadaremid);
       request.setAttribute("reqId", reqid);
       request.setAttribute("reqtype", reqtype);
       

	}
	catch(Exception e)
	{
		log.info("Exception in  getValues method: "+e.getLocalizedMessage());
		request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
 		return mapping.findForward("exception");
	}
	return mapping.findForward("iframe"); 
	
		
	
	
		
	}

	public ActionForward approvedCase(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)

	{
		
		HttpSession session = request.getSession();
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    OpenIssueTrackingDAO comObj1 = new OpenIssueTrackingDAOImpl();
	    ArrayList sadaremData = new ArrayList();
		ArrayList issueData = new ArrayList();
	    ArrayList modifiedData=new ArrayList();
	    DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    HashMap IssueDtls=new HashMap();
	    HashMap IssueApprovalDtls=new HashMap();
	    ArrayList historyData = new ArrayList();
	    String statusMsg = "";
	    try
	    {
			int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			String loginId = (String)session.getAttribute("loginid");
			
			
			IssueDtls = CommonUtility.getDiskFileItemFactory(sfu, request); 
			 String ipaddress= CommonUtility.getClientIPAddress(request);
		
			 String newremarks   	= ((FileItem) IssueDtls.get("newremarks")).getString();
			 
			 
			 String docVerifyStatus = ((FileItem) IssueDtls.get("verifycheckbox")).getString();
			 String selIssueType	= ((FileItem) IssueDtls.get("selIssueType")).getString();
			 String reqId			= ((FileItem) IssueDtls.get("reqId")).getString();
			 String sadaremid		= ((FileItem) IssueDtls.get("sadaremId")).getString();
			 
			 String decisionFlag = comObj.getTktDecisionFlag(roleId, "A", reqId,  selIssueType);
			 String isFinalFlag  = comObj.getIsFinalFlag(roleId, "A", reqId,  selIssueType);
			 
			 if(selIssueType.equals("S002")||selIssueType.equals("S003"))
			 {
			   String teluname   	    = ((FileItem) IssueDtls.get("hidfulnametelugu")).getString();
			   IssueApprovalDtls.put("teluname",teluname);
			   request.setAttribute("teluname", teluname);
			 }
			 
			 IssueApprovalDtls.put("ipaddress",ipaddress);
			 IssueApprovalDtls.put("decisionFlag",decisionFlag);
			 IssueApprovalDtls.put("reqId",reqId);
			 IssueApprovalDtls.put("loginId",loginId);
			 IssueApprovalDtls.put("newremarks",newremarks);
			 IssueApprovalDtls.put("roleId",roleId);
			 IssueApprovalDtls.put("selIssueType",selIssueType);
			 IssueApprovalDtls.put("isFinalFlag",isFinalFlag);
			 IssueApprovalDtls.put("docVerifyStatus",docVerifyStatus);
			 IssueApprovalDtls.put("distName",session.getAttribute("logDistName"));
			 IssueApprovalDtls.put("sadaremId",sadaremid);
			 
			 statusMsg =  comObj1.updateApprovedStatusFlag(IssueApprovalDtls);
			
			 ArrayList issueTypeList = comObj1.getIssueType();
			 
			 request.setAttribute("issueTypeList", issueTypeList);
			 sadaremData = comObj.getSadaremCommonData(sadaremid);
			 
			 if(CommonConstants.NAMECHANGEISSUE_ID.equals(selIssueType))
			 {
				 issueData=(ArrayList)comObj1.getNameIssueData(reqId);	
			 }
			 else if(CommonConstants.RELATIONNAMECHANGEISSUE_ID.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getRelationIssueData(reqId);	
			 }
			 else if(CommonConstants.ADDRESSCHANGEISSUE_ID.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getAddressIssueData(reqId);	
			 }
			 else if(selIssueType.equals(CommonConstants.GENDERISSUE_ID))
			 {
				issueData=(ArrayList)comObj1.getGenderIssueData(reqId);	
			 }
			 else if(selIssueType.equals(CommonConstants.AGEISSUE_ID))
			 {
					issueData=(ArrayList)comObj1.getAgeIssueData(reqId);	
			 }
			 else if(selIssueType.equals(CommonConstants.QUALIFICATIONISSUE_ID))
		 		{
		 			issueData=(ArrayList)comObj1.getQualificationIssueData(reqId);	
		 		}
			  historyData=(ArrayList)comObj1.getIssueStatusHistory(reqId);
	         
			
			 request.setAttribute("historyData",historyData);
	         request.setAttribute("newremarks", newremarks);   
	         request.setAttribute("issueData", issueData);
		     request.setAttribute("sadaremData", sadaremData);
			 request.setAttribute("statusMsg", statusMsg);
			 request.setAttribute("reqtype", "A");
			 request.setAttribute("reqId", reqId);
	}
	catch(Exception e)
	{
		log.info("Exception in approvedCase : "+e.getLocalizedMessage());
		request.setAttribute("MSG", "<font color='red'size='3'><b>Error while approving the request.<br>Please Try Again..!</b></font>"); 	 		
 		return mapping.findForward("exception");
	} 
	    return mapping.findForward("iframe");
	}	
	
	
	public ActionForward rejectedCase(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		HttpSession session = request.getSession();
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    OpenIssueTrackingDAO comObj1 = new OpenIssueTrackingDAOImpl();
	    DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    ArrayList sadaremData = new ArrayList();
		ArrayList issueData = new ArrayList();
	    ArrayList modifiedData=new ArrayList();
	    ArrayList historyData = new ArrayList();
	    HashMap IssueDtls=new HashMap();
	    HashMap IssueRejDtls=new HashMap();
	    String statusMsg = "";
	    try
	    {
			int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			String loginId = (String)session.getAttribute("loginid");
			
			 
			IssueDtls = CommonUtility.getDiskFileItemFactory(sfu, request);  
			 String ipaddress= CommonUtility.getClientIPAddress(request);
			
			 String newremarks   	= ((FileItem) IssueDtls.get("newremarks")).getString();
			 String docVerifyStatus = ((FileItem) IssueDtls.get("verifycheckbox")).getString();
			 String selIssueType	= ((FileItem) IssueDtls.get("selIssueType")).getString();
			 String reqId			= ((FileItem) IssueDtls.get("reqId")).getString();
			 String sadaremid		= ((FileItem) IssueDtls.get("sadaremId")).getString();
			 String decisionFlag    = comObj.getTktDecisionFlag(roleId, "R", reqId,  selIssueType);
			
			
			 IssueRejDtls.put("ipaddress",ipaddress);
			 IssueRejDtls.put("decisionFlag",decisionFlag);
			 IssueRejDtls.put("reqId",reqId);
			 IssueRejDtls.put("loginId",loginId);
			 IssueRejDtls.put("newremarks",newremarks);
			 IssueRejDtls.put("roleId",roleId);
			 IssueRejDtls.put("selIssueType",selIssueType); 
			 IssueRejDtls.put("docVerifyStatus",docVerifyStatus);
			 IssueRejDtls.put("distName",session.getAttribute("logDistName"));
			 IssueRejDtls.put("sadaremId",sadaremid);
			 
			 statusMsg =  comObj1.updateRejectedStatusFlag(IssueRejDtls);
			 sadaremData = comObj.getSadaremCommonData(sadaremid);
			 if(CommonConstants.NAMECHANGEISSUE_ID.equals(selIssueType))
				{
				 issueData=(ArrayList)comObj1.getNameIssueData(reqId);	
				}
			    else if(CommonConstants.RELATIONNAMECHANGEISSUE_ID.equals(selIssueType))
				{
					issueData=(ArrayList)comObj1.getRelationIssueData(reqId);	
				}
			    else if(CommonConstants.ADDRESSCHANGEISSUE_ID.equals(selIssueType))
				{
					issueData=(ArrayList)comObj1.getAddressIssueData(reqId);	
				} 
			    else if(selIssueType.equals(CommonConstants.GENDERISSUE_ID))
				 {
					issueData=(ArrayList)comObj1.getGenderIssueData(reqId);	
				 }
			    else if(selIssueType.equals(CommonConstants.AGEISSUE_ID))
				 {
						issueData=(ArrayList)comObj1.getAgeIssueData(reqId);	
				 }
			    else if(selIssueType.equals(CommonConstants.QUALIFICATIONISSUE_ID))
		 		{
		 			issueData=(ArrayList)comObj1.getQualificationIssueData(reqId);	
		 		}
			    
			 historyData=(ArrayList)comObj1.getIssueStatusHistory(reqId);
	         request.setAttribute("historyData",historyData);
	         request.setAttribute("issueData", issueData);
		     request.setAttribute("sadaremData", sadaremData);
			 request.setAttribute("statusMsg", statusMsg);
			 request.setAttribute("reqtype", "R");
			 request.setAttribute("reqId", reqId);
	}
	catch(Exception e)
	{
		log.info("Exception in rejectedCase : "+e.getLocalizedMessage());
		request.setAttribute("MSG", "<font color='red'size='3'><b>Error while rejecting the request.<br>Please Try Again..!</b></font>"); 	 		
 		return mapping.findForward("exception");
	} 
		
		
		
		return mapping.findForward("iframe");
	}
	
	
}
