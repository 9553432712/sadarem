package com.tcs.sadarem.issuetracksystem.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadare.issuetracksystem.DAO.AppelleteProofsReportDAO;
import com.tcs.sadare.issuetracksystem.DAO.AppelleteProofsReportDAOImpl;

public class AppelleteProofsReportAction extends BaseDispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ArrayList records=new ArrayList();
		try{
	    	
	    	
	    	AppelleteProofsReportDAO obj=new AppelleteProofsReportDAOImpl();
	    	
	    	records = obj.getRecords();
	
		 }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Error Occured.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
	    }
		System.out.println("records-->"+records);
		request.setAttribute("records", records);
		
		return mapping.findForward("appelletereport");
	}
}
