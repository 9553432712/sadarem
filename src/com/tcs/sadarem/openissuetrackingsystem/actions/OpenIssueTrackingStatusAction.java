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

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class OpenIssueTrackingStatusAction extends DispatchAction {

	static String message = "";

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String target = "loadpage";
		try {

			response.getWriter().write("Loading..");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(target);
	}

	public ActionForward searchissueid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String target = "loadpage";
		
		ArrayList issueTypeList = new ArrayList();
		 ArrayList relationTypeList = new ArrayList();
		 ArrayList mandalList=new ArrayList();
		 ArrayList panchayatList=new ArrayList();
		 ArrayList villageList=new ArrayList();
		 ArrayList habitationList=new ArrayList();
		 
		
		HttpSession session = request.getSession();
		 String distId    = null;
		 String mandID	  = null;
		 String villageId = null; 
		 String proofStatus = "";
		
		try {
			message = "";
			
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			//CommonDAOImpl comObj1 = new CommonDAOImpl();
			CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();

			// System.out.println("in searchbyids");

			String issueID = CommonUtility.checkNullObj(request.getParameter("issueid"));
			String sadaremId = CommonUtility.checkNullObj(request.getParameter("sadaremid1"));
		//	System.out.println("sadaremId"+sadaremId);
			
			ArrayList historyData = new ArrayList();
			ArrayList openbasicData = new ArrayList();
			
			
			
			
			
			if (issueID.length() >= 15 || sadaremId.length()>=17) 
			{       
				    if(issueID.length() >= 15)
				    {
					 historyData=(ArrayList)comObj1.getIssueStatusHistory(issueID);
				    }
				 	openbasicData=(ArrayList)comObj1.getBasicdetailsOpenissue(issueID,sadaremId);
					
					
					if(historyData==null && openbasicData==null)
					{
						message = "No Grievance is raised under this ID"; 
					}
					
			}
			else 
			{
				message = "Please provide valid Grievance/SADAREM ID";
			}
           
			session.setAttribute("FormSessionID",""+Math.random());
			request.setAttribute("historyData",historyData);
			request.setAttribute("openbasicData",openbasicData);
			request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(target);
	}
	public ActionForward searchissueidstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String target = "loadpage1";
		
		ArrayList issueTypeList = new ArrayList();
		 ArrayList relationTypeList = new ArrayList();
		 ArrayList mandalList=new ArrayList();
		 ArrayList panchayatList=new ArrayList();
		 ArrayList villageList=new ArrayList();
		 ArrayList habitationList=new ArrayList();
		 
		
		HttpSession session = request.getSession();
		 String distId    = null;
		 String mandID	  = null;
		 String villageId = null; 
		 String proofStatus = "";
		
		try {
			message = "";
			
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			//CommonDAOImpl comObj1 = new CommonDAOImpl();
			CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();

			// System.out.println("in searchbyids");

			String issueID = CommonUtility.checkNullObj(request.getParameter("issueid"));
			String sadaremId = CommonUtility.checkNullObj(request.getParameter("sadaremid1"));
			
			ArrayList historyData = new ArrayList();
			ArrayList openbasicData = new ArrayList();
			
			
			
			
			
			if (issueID.length() >= 15 || sadaremId.length()>=17) 
			{       
				    if(issueID.length() >= 15)
				    {
					 historyData=(ArrayList)comObj1.getIssueStatusHistory(issueID);
				    }
				 	openbasicData=(ArrayList)comObj1.getBasicdetailsOpenissue(issueID,sadaremId);
					
					
					if(historyData==null && openbasicData==null)
					{
						message = "No Grievance is raised under this SADAREM ID";
					}
					
			}
			else 
			{
				message = "Please provide valid Grievance/SADAREM ID";
			}
           
			session.setAttribute("FormSessionID",""+Math.random());
			request.setAttribute("historyData",historyData);
			request.setAttribute("openbasicData",openbasicData);
		//	request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(target);
	}
	public ActionForward getcountofgrievances(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String target = "loadpage2";
		
		ArrayList issueTypeList = new ArrayList();
		 ArrayList relationTypeList = new ArrayList();
		 ArrayList mandalList=new ArrayList();
		 ArrayList panchayatList=new ArrayList();
		 ArrayList villageList=new ArrayList();
		 ArrayList habitationList=new ArrayList();
		 
		
		HttpSession session = request.getSession();
		 String distId    = null;
		 String mandID	  = null;
		 String villageId = null; 
		 String proofStatus = "";
		
		try {
			message = "";
			
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			//CommonDAOImpl comObj1 = new CommonDAOImpl();
			CommonIssueApprovalDAO comObj1 = new CommonIssueApprovalDAOImpl();

			// System.out.println("in searchbyids");

			String issueID = CommonUtility.checkNullObj(request.getParameter("issueid"));
			String sadaremId = CommonUtility.checkNullObj(request.getParameter("sadaremid"));
		//	System.out.println("sadaremId"+sadaremId);
			
			ArrayList historyData = new ArrayList();
			ArrayList openbasicData = new ArrayList();
			
			
			
			
			
			if (issueID.length() >= 15 || sadaremId.length()>=17) 
			{       
				    if(issueID.length() >= 15)
				    {
					 historyData=(ArrayList)comObj1.getIssueStatusHistory(issueID);
				    }
				 	openbasicData=(ArrayList)comObj1.getBasicdetailsOpenissue(issueID,sadaremId);
					
					
					if(historyData==null && openbasicData==null)
					{
						message = "No Grievance is raised under this  SADAREM/Grievance ID"; 
					}
					
			}
			else 
			{ 
				message = "Please provide valid Grievance/SADAREM ID";
			}
           
			session.setAttribute("FormSessionID",""+Math.random());
			request.setAttribute("historyData",historyData);
			request.setAttribute("openbasicData",openbasicData);
			request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(target);
	}
	 
}
