package com.tcs.sadarem.openissuetrackingsystem.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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

public class OpenIssueTrackingAction extends DispatchAction {

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

	public ActionForward searchsadarem(ActionMapping mapping, ActionForm form,
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
		 String randomInt = null;
         Random randomGenerator = new Random();
         String code = "";
         String codeGen = "";
         String IsRaisedInMulti="";
         String IsRaisedAlready="";
		
		try {
			message = "";
			
			CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
			CommonDAOImpl comObj1 = new CommonDAOImpl();

			// System.out.println("in searchbyids");

			String sadaremID = CommonUtility.checkNullObj(request
					.getParameter("sadaremid"));

			ArrayList resultList12 = new ArrayList();
			ArrayList temp = new ArrayList();
			ArrayList proofList=new ArrayList();
			ArrayList educationList=new ArrayList();
			IsRaisedInMulti= CommonUtility.checkNullObj(comObj.checkWhetherRaisedInMulti(sadaremID));
			IsRaisedAlready= CommonUtility.checkNullObj(comObj.checkIsRaisedStatus(sadaremID));
			
			if (sadaremID.length() == 17) 
			{
				proofStatus 	= CommonUtility.checkNullObj(comObj.checkSADAREMProofStatus(sadaremID)); //check whether sadarem ID is tagged to Aadhar
				if(proofStatus.length()>0)
				{
					message = proofStatus;
				}
				else if(IsRaisedAlready.length()>0)
				{
					 message = "Issue cannot be raised as already another grievance is raised under this sadaremID and it is in pending status.<br> Please make it Final approval or Rejection of that grievance in order to proceed here.";	
				}
				 else if( IsRaisedInMulti.length() > 0 )
		          {
					 message = "Issue Cannot be raised as already grievance raised on this SADAREM ID under 'Multiple Grievance' category in DPM Login.";
					
		          }
				else 
				{
					resultList12 = (ArrayList) comObj.searchForSadaremDetails(sadaremID);
					if(resultList12.size()>0)
					{
					temp =(ArrayList)resultList12.get(0);
					if(temp.size()>0)
					{
					    int roleId = 100;
						issueTypeList = comObj.getIssueType(roleId);
						request.setAttribute("issueTypeList", issueTypeList);
						proofList=comObj.getproofdoucments();
						educationList=comObj.getEducationList();
						request.setAttribute("proofList", proofList);
						request.setAttribute("educationList", educationList);
						
						 distId     = (String) temp.get(13);
				    	 mandID     = (String)temp.get(14);
				    	 villageId  = (String)temp.get(15);
				    	 
				    	 mandalList      = (ArrayList)comObj1.getMandalListByDistrictID(distId);
				    	 panchayatList   = (ArrayList)comObj1.getpanchayatListByDistrictIDMandalIDVillageID(distId, mandID);
				         villageList     = (ArrayList)comObj1.getVillageListByDistrictIDMandalID(distId, mandID);
				         habitationList  = (ArrayList)comObj1.getHabitationListByDistrictIDMandalIDvillageID(distId, mandID, villageId);
				         
				         relationTypeList = comObj.getRelationType((String)(temp.get(5)));
				    	
				          for (int idx = 1; idx <= 5; ++idx) 
				            {
				                randomInt = Integer.toHexString(randomGenerator.nextInt(6));
				                code = code + "" + randomInt;
				                codeGen = codeGen + " " + randomInt;

				            }
				            
				            if (code != null) 
				            {
				                request.setAttribute("captchaCode", codeGen);
				                request.setAttribute("code", code);
				            }
				         request.setAttribute("relationTypeList", relationTypeList);
				    	 request.setAttribute("mandalList",mandalList);
				    	 request.setAttribute("panchayatList", panchayatList);
				    	 request.setAttribute("villageList",villageList);
				    	 request.setAttribute("habitationList",habitationList);
				    	 request.setAttribute("distId", distId);
				    	 request.setAttribute("mandID", mandID);
				    	 request.setAttribute("villageId", villageId);
					}
	
					else if (temp.size() == 0) {
						message = "Please provide valid SADAREM ID  details found for ";
						if (sadaremID.length() == 17) {
							message += " SADAREM ID : " + sadaremID;
						}
	
					}
				}
					
				 else 
				 {
						message = "Please provide valid SADAREM ID";
				 }
			 }
			} 
			else 
			{
				message = "Please provide valid SADAREM ID";
			}
           
			
			
			session.setAttribute("FormSessionID",""+Math.random());
			request.setAttribute("resultList", resultList12);
			request.setAttribute("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(target);
	}
	
}
