 package com.tcs.sadarem.issuetracksystem.actions;

	import java.util.ArrayList;

	import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
 


import org.bf.disability.Exception.BaseDispatchAction;

	import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
 
import com.tcs.sadarem.util.CommonUtility;

	public class IssueRequestListAction extends BaseDispatchAction 
	{
		
		public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{ 
			ArrayList issueTypeList = new ArrayList();
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			HttpSession session = request.getSession();
			 
			try{
				int roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
				
			 
				issueTypeList = comObj.getIssueType(roleId);
				
				request.setAttribute("issueTypeList", issueTypeList);
				
			 }
	  		catch(Exception e)
			{
				request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception");
			}
				
			return mapping.findForward("requestList");
		}
		public ActionForward myReqList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			 
			ArrayList pendingIssueDtlsList=new ArrayList();
			ArrayList issueTypeList = new ArrayList();
			HttpSession session = request.getSession();
			try
			{
				
				 String districtId  	=  CommonUtility.checkNullObj(session.getAttribute("districtId"));		// District Id
				 String mandalId 	  	=  CommonUtility.checkNullObj(session.getAttribute("mandalId")); 		// Mandal Id
				 String username 	  	=  CommonUtility.checkNullObj(session.getAttribute("username"));
				 int  roleId            =  Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
				 String tkt_type_id     =  request.getParameter("issueType");  
				 String reqType         =  request.getParameter("reqtype");  
				
				 pendingIssueDtlsList   = comObj.getmyReqList(districtId, mandalId,tkt_type_id, roleId,reqType,username);
				 issueTypeList = comObj.getIssueType(roleId);
				 System.out.println("pendingIssueDtlsList"+pendingIssueDtlsList);
				 request.setAttribute("issueTypeList", issueTypeList);
				 request.setAttribute("pendingIssueDtlsList", pendingIssueDtlsList);
				 request.setAttribute("selIssueType",tkt_type_id);
				 request.setAttribute("reqType",reqType);
				 
				 
			}
			catch(Exception e)
			{
				request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception");
			}
			return mapping.findForward("requestList");
		}
		 
		public ActionForward getrequestListValues(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
		{
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();
			ArrayList sadaremData = new ArrayList();
			ArrayList issueData1 = new ArrayList();
			ArrayList issueData = new ArrayList();
			ArrayList historyData = new ArrayList();
			ArrayList  relationTypeList= new ArrayList();
			try{
			String sadaremid=request.getParameter("sadaremid");
			String reqid=request.getParameter("requestid");
			String reqtype=(String)request.getParameter("reqtype");
		
			sadaremData = comObj.getSadaremCommonData(sadaremid);
		     
		     request.setAttribute("sadaremData", sadaremData);
		    
			 
	           historyData=(ArrayList)comObj1.getIssueStatusHistory(reqid);
	           request.setAttribute("historyData",historyData);
	           request.setAttribute("issueData", issueData);
	           request.setAttribute("sadaremid", sadaremid);
	           request.setAttribute("reqId", reqid);
	           request.setAttribute("reqtype", reqtype);
	           
		
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception");
			}
			return mapping.findForward("requestListiframe"); 
		
			
		}
		
	}
