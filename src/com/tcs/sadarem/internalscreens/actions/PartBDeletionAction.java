package com.tcs.sadarem.internalscreens.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class PartBDeletionAction extends DispatchAction {


public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
{
   String target = "partbDeletion";
       try
       {
	
		       response.getWriter().write("Loading..");
		       
       }
       catch(Exception e)
       {
    	   e.printStackTrace();
       }
       return mapping.findForward(target);
}
public ActionForward updaterecord(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
{
   String target = "partbDeletion";
       try
       {
    	
	       PartBDeletionDAO comObj = new  PartBDeletionDAOImpl();
	

	       
	       String sadaremID = CommonUtility.checkNullObj(request.getParameter("sadaremid"));
	       String aadhaarflag = CommonUtility.checkNullObj(request.getParameter("aadharflag"));
	       String reasonremarks = CommonUtility.checkNullObj(request.getParameter("message"));
	
	      
	      	int result=0;
	      	
	      	if(sadaremID.length()==17)
	      	{
	      		result = comObj.updatedeleterecord(sadaremID,aadhaarflag,reasonremarks);
	      
	      	}
	    
	      	if(result>0)
	      	{
	      		request.setAttribute("finalresult", "Successfully Deleted");	
	      	}
	      	else
	      	{
	      		request.setAttribute("finalresult"," <font color='red'>Not Deleted!! Some error occured</font>");
	      	}
	      	
	      	
	    
       }
       catch(Exception e)
       {
    	   e.printStackTrace();
       }
   return mapping.findForward(target);
}





}