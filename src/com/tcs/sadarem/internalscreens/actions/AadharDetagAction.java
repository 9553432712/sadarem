package com.tcs.sadarem.internalscreens.actions;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class AadharDetagAction extends BaseDispatchAction
{
	
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		 String target="AadharDetag";/*
		 HttpSession session = request.getSession();
	     String sadaremID = CommonUtility.checkNullObj(request.getParameter("sadaremID"));*/
	     return mapping.findForward(target);
	}
	
	public ActionForward Remarks(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String result="AadharDetag",sadaremid="";
		int roleId;
				
		String createdBy="",aadhar="",victimname="";		
		String remark="";
		 AadharDetagDAO obj = new AadharDetagImpl();
		 ArrayList basicDetails= new ArrayList();	
		try
		{		 
					
			remark = request.getParameter("rmrk");
			
			HttpSession session = request.getSession();
			roleId=Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));			
			createdBy = (String) session.getAttribute("loginid");
			sadaremid = CommonUtility.checkNullObj(request.getParameter("sadaremID"));
			
		       basicDetails= obj.getSadaremCommonData(sadaremid);
	       
		       if(basicDetails.size()>0)
		       {
		    	   basicDetails=(ArrayList)basicDetails.get(0);
		       }		

		       aadhar=(String)basicDetails.get(14);
		       victimname=(String) basicDetails.get(9)+basicDetails.get(10);
		       int temp=0;       
	        if(remark.trim().length()>0)
	        {
	        	temp = obj.RemarksLogInsert(sadaremid,aadhar,remark,victimname,createdBy);
	        }
			
	        
		
			if(temp==1)
			{
				int tmp=0;
				tmp = obj.AadharDetagqry(sadaremid);
				 
				 request.setAttribute("DetagQueryResult", tmp);
				
				 request.setAttribute("sadaremData", basicDetails);
				 request.setAttribute("sadID", sadaremid);
			}
			else
			{				
				request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mapping.findForward(result);
	}
	
	
	public ActionForward AadharDetagMethod(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "AadharDetag",sadID="";
       ArrayList basicDetails= new ArrayList();
       ArrayList basicDetailstemp= new ArrayList();
	       try
	       { 	      
		       HttpSession session = request.getSession();
		       sadID = CommonUtility.checkNullObj(request.getParameter("sadaremID"));		     
		       AadharDetagDAO obj = new AadharDetagImpl();
		       
		       String distIdFromSadaremId =null;
		       CommonDAOImpl comObj = new CommonDAOImpl();
		       HashMap GEODtls = new HashMap();
			   GEODtls=comObj.getGEODetailsbySADAREMID(sadID);
			   if(GEODtls.size()>0)
			   {
				   distIdFromSadaremId	=	GEODtls.get("districtid").toString();
			   }
			   String sessDistId = (String)session.getAttribute("districtId"); 
			   int roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			   
			   if(	(distIdFromSadaremId!=null && distIdFromSadaremId.length()>0 && distIdFromSadaremId.equals(sessDistId) ) 
					   || (roleId==CommonConstants.SPMLOGIN_ROLEID) )
			   {
				   basicDetails= obj.getSadaremCommonData(sadID);
			   }
			   else
			   {
				   request.setAttribute("errorMsg", "SADAREM ID doesn't belong to your district.");
				   return mapping.findForward(target);
			   }
		       
		       if(basicDetails.size()>0)
		       {
		    	   basicDetails=(ArrayList)basicDetails.get(0);
		       }		       		   

		       request.setAttribute("sadaremData", basicDetails);		
		       request.setAttribute("sadID", sadID);
	       }
	       catch(Exception e)
	       {
	    	
	    	   e.printStackTrace();
	       }
       return mapping.findForward(target);
   }
	

}
