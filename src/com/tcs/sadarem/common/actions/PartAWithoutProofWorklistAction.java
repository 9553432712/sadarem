package com.tcs.sadarem.common.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.CommonValidators;
import com.tcs.sadarem.withoutproofparta.DAO.PartAWithoutProofWorklistDAO;
import com.tcs.sadarem.withoutproofparta.DAO.PartAWithoutProofWorklistDAOImpl;

public class PartAWithoutProofWorklistAction extends DispatchAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    { 
		return mapping.findForward("partapage");	 
   }
	public ActionForward getData(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		PartAWithoutProofWorklistDAO comObj = new PartAWithoutProofWorklistDAOImpl();
		HttpSession session = request.getSession();
		ArrayList partAIssueDtlsList=new ArrayList();
		 
		try
		{
			int roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			 
			 String districtId  	=  CommonUtility.checkNullObj(session.getAttribute("districtId"));	// Mandal Id
			 String fromdate=CommonUtility.checkNullObj(request.getParameter("fromdate"));
			 String todate=CommonUtility.checkNullObj(request.getParameter("todate"));
			 String reqType         =  request.getParameter("reqtype");
			 			 
			    if(fromdate.equals("") || !CommonValidators.dateValidater(fromdate))
			 	{
					fromdate = CommonUtility.getDateAddOrSubDays(-7,"dd/MM/yyyy");
			 	}
			   /* 
			    else
			    {
			    	fromdate = CommonUtility.getDateAddOrSubDays(-7,"dd/MM/yyyy");
			    }*/
				
				if(todate.equals("") || !CommonValidators.dateValidater(todate))
			 	{
					todate =   CommonUtility.getDateTime("dd/MM/yyyy"); //"04/01/2015";
					
			 	}
				/* else
				 {
					 todate =   CommonUtility.getDateTime("dd/MM/yyyy"); //"04/01/2015";
				 }*/
					
				partAIssueDtlsList=comObj.getPendingWithoutProofPartaList(districtId, reqType,fromdate, todate);
				

			 request.setAttribute("pendingIssueDtlsList", partAIssueDtlsList);
			 request.setAttribute("reqType",reqType);
			 request.setAttribute("fromdate",fromdate);
			 request.setAttribute("todate",todate);
		 }
  		catch(Exception e)
		{
			request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
		}
		return mapping.findForward("partapage");
	}
	

}
