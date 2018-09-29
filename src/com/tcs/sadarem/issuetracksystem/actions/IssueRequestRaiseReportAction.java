package com.tcs.sadarem.issuetracksystem.actions;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;

public class IssueRequestRaiseReportAction extends DispatchAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
	   String target = "IssueRequestRaiseReport";	   
	       try
	       {	    	   
	    	   IssueRequestRaiseReportDAO obj=new IssueRequestRaiseReportImpl();			
	    	   ArrayList roleNames = new ArrayList(); 	
	    	   roleNames	=	obj.ExtractRoleNmaes(); 					
	    	   ArrayList outputTable = new ArrayList();
	    	   outputTable = obj.ExtractRolesServicesMap(); 	
	    	   
	    	   request.setAttribute("roleNames",roleNames );
			    request.setAttribute("outputTable", outputTable);
	       }
	       catch(Exception e)
	       {
	    	   e.printStackTrace();
	       }
	       return mapping.findForward(target);
	}
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String title = "Issue Raising Provision";
		try
		{
			
			CommonDAO cobj= new CommonDAOImpl();
			IssueRequestRaiseReportDAO obj=new IssueRequestRaiseReportImpl();			
	    	ArrayList roleNames = new ArrayList(); 	
	    	roleNames	=	obj.ExtractRoleNmaes();
	    	ArrayList outputTable = new ArrayList();
	    	outputTable = obj.ExtractRolesServicesMap();
	    	ArrayList resultList = new ArrayList();
	    	ArrayList tempList = new ArrayList();
	    	ArrayList outList = new ArrayList();
	    	for(int i=0;i<outputTable.size();i++)
	    	{
	    		tempList = (ArrayList) outputTable.get(i);
	    		outList = new ArrayList();
	    		for(int j=0;j<tempList.size();j++)
	    		{
	    			if(tempList.get(j).equals("False"))
	    			{
	    				outList.add("N");
	    			}
	    			else if(tempList.get(j).equals("True"))
	    			{
	    				outList.add("Y");
	    			}
	    			else
	    			{
	    				outList.add(tempList.get(j));
	    			}
	    		}
	    		resultList.add(outList);
	    	}
	    	cobj.exportExcel(roleNames, resultList , request,  response,  title);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return null;
		  
	}
}
