
 package com.tcs.sadarem.issuetracksystem.actions;
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
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.MultipleGrievanceDAO;
import com.tcs.sadare.issuetracksystem.DAO.MultipleGrievanceDAOImpl;
import com.tcs.sadarem.util.CommonUtility;



public class IssueApprovalAction  extends BaseDispatchAction  
{
	
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
	
		ArrayList issueTypeList = new ArrayList();
		CommonIssueApprovalDAO comObj = new CommonIssueApprovalDAOImpl();
		HttpSession session = request.getSession();
		ArrayList pendingIssueDtlsList=new ArrayList();
		 
		try{
			int roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			issueTypeList = comObj.getApprovalIssueType(roleId);			
			
			//System.out.println("xxxxxx"+issueTypeList);
			 
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
			 pendingIssueDtlsList   = comObj.getPendingIssueDtlsList(districtId, mandalId,tkt_type_id, roleId,reqType);
			}
			//System.out.println(reqType+"--"+tkt_type_id+"--"+districtId+"--"+mandalId+"--"+pendingIssueDtlsList);
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
		return mapping.findForward("issueApproval");	
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
			 
			 
			if("P".equals(reqType))
			{
			 pendingIssueDtlsList   = comObj.getPendingIssueDtlsList(districtId, mandalId,tkt_type_id, roleId,reqType);
			}
			else
			{
				pendingIssueDtlsList   = comObj.getApprRejIssueDtlsList(districtId, mandalId,tkt_type_id, roleId,reqType,fromdate,todate);	
			}
			 issueTypeList = comObj.getApprovalIssueType(roleId);
			// System.out.println("*****>>>>"+pendingIssueDtlsList);
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
		return mapping.findForward("issueApproval");
	}
	
	
	public ActionForward getValues(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
		CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();
		MultipleGrievanceDAO comObj2  = new MultipleGrievanceDAOImpl();
		ArrayList sadaremData = new ArrayList();
		ArrayList issueData1 = new ArrayList();
		ArrayList issueData = new ArrayList();
		ArrayList historyData = new ArrayList();
		ArrayList  relationTypeList= new ArrayList();
		ArrayList aadharReqDtls = new ArrayList();
		ArrayList MultiIssueIds = new ArrayList();
		String status="", MultiIssueTracker="";
		String edid;
		try
		{
		String sadaremid	= request.getParameter("sadaremid");
		String reqid		= request.getParameter("requestid");
		String reqtype		= (String)request.getParameter("reqtype");
		String tkttype 		= (String)request.getParameter("tkttype"); 
		
		if(CommonConstants.SADAREMDELETION_ID.equals(tkttype))
		{
		sadaremData 		= comObj.getSadaremCommonDataForSadaremDeletion(sadaremid);
		}
		else
		{
			sadaremData 		= comObj.getSadaremCommonData(sadaremid);
		}
		
		
	     request.setAttribute("sadaremData", sadaremData);
	     relationTypeList = comObj.getRelationType((String)((ArrayList)sadaremData.get(0)).get(15));
	     
	     edid= (String)((ArrayList)sadaremData.get(0)).get(36) ; 
	     
	    // System.out.println("edu"+(String)((ArrayList)sadaremData.get(0)).get(15));
	     
	    String  edu_name = comObj.getEducationTypeName((String)((ArrayList)sadaremData.get(0)).get(36));
	    request.setAttribute("edu_name", edu_name);
	    
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
	 		else if(tkttype.equals(CommonConstants.DistrictAddressChange))
	 		{
	 			issueData=(ArrayList)comObj1.getDistrictAddressIssueData(reqid);	
	 		}
	 		else if(tkttype.equals(CommonConstants.CAUSEOFDISABILITYCHANGEISSUE_ID))
	 		{
	 			issueData=(ArrayList)comObj1.getCauseOfDisabilityIssueData(reqid);	
	 		}
	 		else if(tkttype.equals(CommonConstants.AADHARISSUE_ID))
	 		{
	 			issueData=(ArrayList)comObj1.getAadharIssueData(reqid);	
	 			aadharReqDtls=issueData;
	 			if(aadharReqDtls.size()>0 && "P".equals(reqtype))
	 			{
	 				aadharReqDtls = (ArrayList)aadharReqDtls.get(0);
	 				if(aadharReqDtls.size()>0)
	 				{
	 					String dupSadaremId = CommonUtility.checkNullObj(aadharReqDtls.get(6));
	 					if(dupSadaremId.length()>10)
	 					{
	 					 status="<font color='red' size=2>Aadhaar ID <a style=\"font-family:Verdana, Geneva, sans-serif;\" href=\"http://www.serp.telangana.gov.in/searchaadhar/do?requestType=commonrh&strUID="+aadharReqDtls.get(3)+"&actionVal=checkmyaadhar&randomid=%22+Math.random()\" target=\"_blank\">"+aadharReqDtls.get(3)+"</a> is already tagged to SADAREM ID "+aadharReqDtls.get(6)+"."+
					             "On approving the request Aadhar Id will be detagged from "+aadharReqDtls.get(6)+".</font>";
	 					 request.setAttribute("statusMsg", status);
	 					}
	 				}
	 			}
	 		}
	 		else if(tkttype.equals(CommonConstants.FULLNAMECHANGEISSUE_ID))
	 		{
	 			issueData=(ArrayList)comObj1.getFullNameIssueData(reqid);	
	 		}
	 		else if(tkttype.equals("S005"))
	 		{
	 			issueData=(ArrayList)comObj1.getPhotoChangeIssueData(reqid);	
	 		}
	    	 if(CommonConstants.SADAREMDELETION_ID.equals(tkttype))
			 {
				 issueData=(ArrayList)comObj1.getSadaremDeleteRequestDtls(reqid);	
			 }
	 		else if(tkttype.equals(CommonConstants.MANUALPHOTOISSUE_ID))
	 		{
	 			issueData=(ArrayList)comObj1.getManualPhotoChangeIssueData(reqid);	
	 		} 
	 		else if(tkttype.equals(CommonConstants.PERSONSTATUSISSUE_ID))
	 		{
	 			issueData=(ArrayList)comObj1.getPersonStatusIssueData(reqid);	
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
	 		else if(tkttype.equals(CommonConstants.APPELLATEISSUE_ID))
	 		{
	 			issueData=(ArrayList)comObj1.getAppellateIssueData(reqid);	
	 		}  
	 		else if(tkttype.equals(CommonConstants.PARTBREENTRYISSUE_ID))
	 		{
	 			issueData=(ArrayList)comObj1.getPartBreEntryIssueData(reqid);	
	 		}  
	 		else if(tkttype.equals(CommonConstants.MULTIPLEGRIEVANCEISSUE_ID))
	 		{
	 			issueData=(ArrayList)comObj2.getMultiIssueData(reqid);	
	 		     MultiIssueTracker= comObj2.getMultiIssuesTracker(reqid);
	            MultiIssueIds=(ArrayList)comObj2.getSelectedIssueIdsFromtracker(MultiIssueTracker);
	 		} 
           historyData=(ArrayList)comObj1.getIssueStatusHistory(reqid);     
           
           
           
           request.setAttribute("MultiIssueIds",MultiIssueIds);
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
	    CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();
	    MultipleGrievanceDAO comObj2  = new MultipleGrievanceDAOImpl();
	    ArrayList sadaremData = new ArrayList();
	    ArrayList MultiIssueIds = new ArrayList();
		ArrayList issueData = new ArrayList();
	    ArrayList modifiedData=new ArrayList();
	    DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    HashMap IssueDtls=new HashMap();
	    HashMap IssueApprovalDtls=new HashMap();
	    ArrayList historyData = new ArrayList();
	    String statusMsg = "", MultiIssueTracker="";
	    try
	    {
			int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			String loginId = (String)session.getAttribute("loginid");
			
			
			IssueDtls = CommonUtility.getDiskFileItemFactory(sfu, request); 
			String ipaddress = CommonUtility.getClientIPAddress(request);
		
			 String newremarks   	= ((FileItem) IssueDtls.get("newremarks")).getString();
			 String docVerifyStatus = ((FileItem) IssueDtls.get("verifycheckbox")).getString();
			 String selIssueType	= ((FileItem) IssueDtls.get("selIssueType")).getString();
			 String reqId			= ((FileItem) IssueDtls.get("reqId")).getString();
			 String sadaremid		= ((FileItem) IssueDtls.get("sadaremId")).getString();
			 
			 String decisionFlag = comObj.getTktDecisionFlag(roleId, "A", reqId,  selIssueType);
			 
			 String isFinalFlag  = comObj.getIsFinalFlag(roleId, "A", reqId,  selIssueType);
		 
			 
			 
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
			 IssueApprovalDtls.put("ipaddress",ipaddress);
			 
			 statusMsg =  comObj1.updateApprovedStatusFlag(IssueApprovalDtls);
			
			 ArrayList issueTypeList = comObj1.getIssueType();
			 
			 request.setAttribute("issueTypeList", issueTypeList);
			 
			 if(CommonConstants.SADAREMDELETION_ID.equals(selIssueType))
			 {
				 sadaremData = comObj.getSadaremCommonDataForSadaremDeletion(sadaremid);
			 }
			 else
			 {
				 sadaremData = comObj.getSadaremCommonData(sadaremid);
			 }
			 
			 if(CommonConstants.NAMECHANGEISSUE_ID.equals(selIssueType))
			 {
				 issueData=(ArrayList)comObj1.getNameIssueData(reqId);	
			 }
			 if(CommonConstants.SADAREMDELETION_ID.equals(selIssueType))
			 {
				 issueData=(ArrayList)comObj1.getSadaremDeleteRequestDtls(reqId);
			 }
			 
			 else if(CommonConstants.RELATIONNAMECHANGEISSUE_ID.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getRelationIssueData(reqId);	
			 }
			 else if(CommonConstants.ADDRESSCHANGEISSUE_ID.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getAddressIssueData(reqId);	
			 }
			 else if(CommonConstants.DistrictAddressChange.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getDistrictAddressIssueData(reqId);	
			 }
			 else if(CommonConstants.CAUSEOFDISABILITYCHANGEISSUE_ID.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getCauseOfDisabilityIssueData(reqId);	
			 }
			 else if(CommonConstants.AADHARISSUE_ID.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getAadharIssueData(reqId);	
			 }
			 else if(CommonConstants.FULLNAMECHANGEISSUE_ID.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getFullNameIssueData(reqId);	
			 }
			 else if(CommonConstants.MANUALPHOTOISSUE_ID.equals(selIssueType))
			 {
					issueData=(ArrayList)comObj1.getManualPhotoChangeIssueData(reqId);	
			 }
			 else if(CommonConstants.PERSONSTATUSISSUE_ID.equals(selIssueType))
			 {
				    issueData=(ArrayList)comObj1.getPersonStatusIssueData(reqId);	
			 }
			 else if(selIssueType.equals(CommonConstants.GENDERISSUE_ID))
			 {
				issueData=(ArrayList)comObj1.getGenderIssueData(reqId);	
			 }
			 else if(selIssueType.equals(CommonConstants.AGEISSUE_ID))
			 {
					issueData=(ArrayList)comObj1.getAgeIssueData(reqId);	
			 }
			 else if(selIssueType.equals(CommonConstants.PHOTOISSUE_ID))
			 {
				issueData=(ArrayList)comObj1.getPhotoChangeIssueData(reqId);	
			 }
			 else if(selIssueType.equals(CommonConstants.QUALIFICATIONISSUE_ID))
		 	{
		 			issueData=(ArrayList)comObj1.getQualificationIssueData(reqId);	
		 	}
			 else if(selIssueType.equals(CommonConstants.APPELLATEISSUE_ID))
	 		 {
	 			issueData=(ArrayList)comObj1.getAppellateIssueData(reqId);	
	 		 } 
			 else if(selIssueType.equals(CommonConstants.PARTBREENTRYISSUE_ID))
		 		{
		 			issueData=(ArrayList)comObj1.getPartBreEntryIssueData(reqId);	
		 		} 
			 else if(selIssueType.equals(CommonConstants.MULTIPLEGRIEVANCEISSUE_ID))
		 		{
		 			issueData=(ArrayList)comObj2.getMultiIssueData(reqId);	
		 			 MultiIssueTracker= comObj2.getMultiIssuesTracker(reqId);
			           MultiIssueIds=(ArrayList)comObj2.getSelectedIssueIdsFromtracker(MultiIssueTracker);
		 		} 
			  historyData=(ArrayList)comObj1.getIssueStatusHistory(reqId);
	          
			  
	           request.setAttribute("MultiIssueIds",MultiIssueIds);	           
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
	    CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();
	    MultipleGrievanceDAO comObj2  = new MultipleGrievanceDAOImpl();
	    DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    ArrayList sadaremData = new ArrayList();
		ArrayList MultiIssueIds = new ArrayList();
	    ArrayList issueData = new ArrayList();
	    ArrayList modifiedData=new ArrayList();
	    ArrayList historyData = new ArrayList();
	    HashMap IssueDtls=new HashMap();
	    HashMap IssueRejDtls = new HashMap();
	    String statusMsg = "", MultiIssueTracker="";
	    try
	    {
			int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			String loginId = (String)session.getAttribute("loginid");
			
			 
			IssueDtls = CommonUtility.getDiskFileItemFactory(sfu, request); 
			String ipaddress = CommonUtility.getClientIPAddress(request);
			
			 String newremarks   	= ((FileItem) IssueDtls.get("newremarks")).getString();
			 String docVerifyStatus = ((FileItem) IssueDtls.get("verifycheckbox")).getString();
			 String selIssueType	= ((FileItem) IssueDtls.get("selIssueType")).getString();
			 String reqId			= ((FileItem) IssueDtls.get("reqId")).getString();
			 String sadaremid		= ((FileItem) IssueDtls.get("sadaremId")).getString();
			 String decisionFlag    = comObj.getTktDecisionFlag(roleId, "R", reqId,  selIssueType);
			
			 IssueRejDtls.put("decisionFlag",decisionFlag);
			 IssueRejDtls.put("reqId",reqId);
			 IssueRejDtls.put("loginId",loginId);
			 IssueRejDtls.put("newremarks",newremarks);
			 IssueRejDtls.put("roleId",roleId);
			 IssueRejDtls.put("selIssueType",selIssueType); 
			 IssueRejDtls.put("docVerifyStatus",docVerifyStatus);
			 IssueRejDtls.put("distName",session.getAttribute("logDistName"));
			 IssueRejDtls.put("sadaremId",sadaremid);
			 IssueRejDtls.put("ipaddress",ipaddress);
			 
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
			    else if(CommonConstants.DistrictAddressChange.equals(selIssueType))
				{
					issueData=(ArrayList)comObj1.getDistrictAddressIssueData(reqId);	
				} 
			    else if(CommonConstants.CAUSEOFDISABILITYCHANGEISSUE_ID.equals(selIssueType))
			    {
					issueData=(ArrayList)comObj1.getCauseOfDisabilityIssueData(reqId);	
			    }
			    else if(CommonConstants.FULLNAMECHANGEISSUE_ID.equals(selIssueType))
				 {
						issueData=(ArrayList)comObj1.getFullNameIssueData(reqId);	
				 } 
			    else if(CommonConstants.MANUALPHOTOISSUE_ID.equals(selIssueType))
				 {
						issueData=(ArrayList)comObj1.getManualPhotoChangeIssueData(reqId);	
				 }
			    else if(CommonConstants.SADAREMDELETION_ID.equals(selIssueType))
			    {
				 issueData=(ArrayList)comObj1.getSadaremDeleteRequestDtls(reqId);	
			    }
			    else if(selIssueType.equals(CommonConstants.GENDERISSUE_ID))
				 {
					issueData=(ArrayList)comObj1.getGenderIssueData(reqId);	
				 }
			    else if(selIssueType.equals(CommonConstants.AGEISSUE_ID))
				 {
						issueData=(ArrayList)comObj1.getAgeIssueData(reqId);	
				 }
			    else if(selIssueType.equals(CommonConstants.PHOTOISSUE_ID))
				 {
					issueData=(ArrayList)comObj1.getPhotoChangeIssueData(reqId);	
				 }
			    else if(selIssueType.equals(CommonConstants.AADHARISSUE_ID))
		 		{
		 			issueData=(ArrayList)comObj1.getAadharIssueData(reqId);	
		 		}
			    else if(selIssueType.equals(CommonConstants.QUALIFICATIONISSUE_ID))
		 		{
		 			issueData=(ArrayList)comObj1.getQualificationIssueData(reqId);	
		 		}
			    else if(selIssueType.equals(CommonConstants.APPELLATEISSUE_ID))
		 		{
		 		    issueData=(ArrayList)comObj1.getAppellateIssueData(reqId);	
		 		} 
			    else if(selIssueType.equals(CommonConstants.PERSONSTATUSISSUE_ID))
		 		{
			    	issueData=(ArrayList)comObj1.getPersonStatusIssueData(reqId);	
		 		}
				 else if(selIssueType.equals(CommonConstants.PARTBREENTRYISSUE_ID))
			 		{
			 			issueData=(ArrayList)comObj1.getPartBreEntryIssueData(reqId);	
			 		} 
				 else if(selIssueType.equals(CommonConstants.MULTIPLEGRIEVANCEISSUE_ID))
			 		{
			 			issueData=(ArrayList)comObj2.getMultiIssueData(reqId);	
			 			  MultiIssueTracker= comObj2.getMultiIssuesTracker(reqId);
				           MultiIssueIds=(ArrayList)comObj2.getSelectedIssueIdsFromtracker(MultiIssueTracker);
			 		} 
			 historyData=(ArrayList)comObj1.getIssueStatusHistory(reqId);
			 
			
	           request.setAttribute("MultiIssueIds",MultiIssueIds);
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
	
	
//	
//	 public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//	          
//	   {
//		    CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();
//		    String reqId = CommonUtility.checkNullObj(request.getParameter("reqId"));
//		    String path = comObj1.getFilePath(reqId);
//	        viewFile(path,reqId,".pdf", request, response);
//
//	        return mapping.findForward("iframe");
//	    }

	 public ActionForward downloadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	 {
            BufferedInputStream in = null;
	        ServletOutputStream out = null;
	        try {
	            getServlet().getServletContext().getRealPath("");


	            String downloadpath =  "D:\\SADAREMTG\\ISSUETRACKING\\Attachment1";


	            //File fileDetailsData = new File(downloadpath + "\\" + request.getParameter("id") + "\\" + request.getParameter("id") + ".pdf");
	            File fileDetailsData = new File(downloadpath + "\\" + request.getParameter("reqId")+".pdf");
	          
	            //System.out.println(""+downloadpath + "\\" + request.getParameter("reqId")+".pdf");
	            File[] listOfFiles = fileDetailsData.listFiles();
	            fileDetailsData =listOfFiles[0];
	            FileInputStream fin = new FileInputStream(fileDetailsData);

	            in = new BufferedInputStream(fin);

	            out = response.getOutputStream();
	            response.setContentType("application/force-download");
	            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileDetailsData.getName() + "\"");
	            byte[] buffer = new byte[4 * 1024];

	            int data = 0;
	            while ((data = in.read(buffer)) != -1) {
	                out.write(buffer, 0, data);
	            }
	            out.close();
	            in.close();

	            //out.flush();


	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("nodata", "G.O you are looking for is not found");
	        } finally {
	            if (out != null) {
	                try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            if (in != null) {
	                try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }

	            return mapping.findForward("iframe");
	        }

	    


	    }
	
	
}
