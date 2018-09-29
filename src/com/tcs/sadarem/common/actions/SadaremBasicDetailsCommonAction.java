
package com.tcs.sadarem.common.actions;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class SadaremBasicDetailsCommonAction extends DispatchAction
{
	
	static String message ="";
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadpage";
	       try
	       {
		       CommonDAO comObj = new CommonDAOImpl();		
		     // System.out.println("in unspecified");
		
		      	ArrayList districtList = (ArrayList)comObj.getDistrictList();		
		      	request.setAttribute("districtList", districtList);

			       response.getWriter().write("Loading..");
			       
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }
	       return mapping.findForward(target);
   }
	
	public ActionForward searchbyids(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadpage";
       String  PartBStatusRmks="";
	       try
	       {
	    	   message ="";
		       CommonDAO comObj = new CommonDAOImpl();
		
		      // System.out.println("in searchbyids");
		       HttpSession session = request.getSession();
		       String sadaremID = CommonUtility.checkNullObj(request.getParameter("sadaremid"));
		       String aadhaarID = CommonUtility.checkNullObj(request.getParameter("aadhaarid"));
		       String roleId    = CommonUtility.checkNullObj(session.getAttribute("roleId"));
		       String sesDistrictId = CommonUtility.checkNullObj(session.getAttribute("districtId"));
		       String sesMandalId = CommonUtility.checkNullObj(session.getAttribute("mandalId"));
		       String sadaremDistId ="",sadaremMandId="";   
		       
		      	ArrayList districtList = (ArrayList)comObj.getDistrictList();
		      	ArrayList resultList   = new ArrayList();
		      	ArrayList historyList   = new ArrayList();
		      	ArrayList originalDetailsList = new ArrayList();
		      	HashMap formStatusList = new HashMap();
		      	
		      	String statusRemarks="";
		      	
		      	sadaremDistId = CommonUtility.checkNullObj(comObj.getDistrictId(sadaremID, aadhaarID));
		      	sadaremMandId = CommonUtility.checkNullObj(comObj.getMandalId(sadaremID, aadhaarID,sadaremDistId));
		      	
		      	if(sadaremDistId.length()>0)
		      	{
		      		if((sadaremDistId.equals(sesDistrictId) && CommonConstants.DPMLOGINROLEID.equals(roleId) )   || CommonConstants.SERPLOGIN_ROLEID.equals(roleId) || CommonConstants.ROLE_APPELLATE.equals(roleId) || (sadaremMandId.equals(sesMandalId) && CommonConstants.MANDALLOGIN_ROLEID.equals(roleId))
		      			|| (sadaremDistId.equals(sesDistrictId) && CommonConstants.ROLE_CAMPINCHARGE.equals(roleId) ) 	)
		      		{
		      	 
					      	if(sadaremID.length()==17 || aadhaarID.length()==12)
					      	{
					      		resultList = (ArrayList)comObj.sadaremBasicDetails(sadaremID, aadhaarID);
					      		historyList =(ArrayList)comObj.searchForSadaremHistory(sadaremID, aadhaarID, "", "", "", "", "");
					      		PartBStatusRmks = comObj.getPartBStatusRemarks(sadaremID);
					      		
					      		
					      		if(resultList.size()==0)
					      		{
					      			statusRemarks=comObj.searchForStatusRemarks(sadaremID, aadhaarID);
					      			message = statusRemarks+"        ::   ";
					      			//message = "Please provide valid SADAREM ID or Aadhaar ID. No details found for ";
					      			if(sadaremID.length()==17)
					      			{ 
					      				message+=" SADAREM ID : "+sadaremID;
					      			}
					      			
					      			if(sadaremID.length()==17 && aadhaarID.length()==12)
					      			{
					      				message+=" OR ";
					      			}
					      			
					      			if(aadhaarID.length()==12)
					      			{
					      				message+=" Aadhaar ID : "+aadhaarID;
					      			}
					      			
						      		originalDetailsList =  (ArrayList)comObj.getSADAREMOrginalDetails(sadaremID, aadhaarID);
					      		}
					      		else
					      		{

						      		originalDetailsList = (ArrayList)comObj.getSADAREMOrginalDetails(sadaremID, aadhaarID);
						      		
						      		if(originalDetailsList.size()>0)
						      		{
						      			formStatusList = (HashMap)originalDetailsList.get(0);
						      		}
					      		}
					      	}
					      	else
					      	{
					      		message = "Please provide valid SADAREM ID or Aadhaar ID";
					      		originalDetailsList =  (ArrayList)comObj.getSADAREMOrginalDetails(sadaremID, aadhaarID);
					      		
					      	}
		      		}
		      		else
		      		{
		      			message = "Please provide SADAREM Id or Aadhar Id of respective district & Mandal.";
		      		}
		      	}
		      	else
		      	{
		      		message = "No records for found with SADAREM ID or Aadhaar ID";
		      		originalDetailsList = (ArrayList) comObj.getSADAREMOrginalDetails(sadaremID, aadhaarID);
		      	}
		      	
		      	
		      //	System.out.println("originalDetailsList : "+originalDetailsList);
		      	
		      	request.setAttribute("districtList", districtList);
		      	request.setAttribute("resultList", resultList);
		      	request.setAttribute("message", message);
		      	request.setAttribute("historyList", historyList);
		      	request.setAttribute("PartBStatusRmks", PartBStatusRmks);
		      	request.setAttribute("originalDetailsList", originalDetailsList);
		      	request.setAttribute("formStatusList", formStatusList);
		      	
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }
       return mapping.findForward(target);
   }
	
	
}
