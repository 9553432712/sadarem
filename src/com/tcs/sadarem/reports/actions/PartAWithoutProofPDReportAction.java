package com.tcs.sadarem.reports.actions;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;

public class PartAWithoutProofPDReportAction extends BaseDispatchAction
{

    private String target = "success";
    
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
	    	CommonReportsDAO comObj1 = new CommonReportsDAOImpl();
		    ArrayList resultList = new ArrayList();

	        HttpSession session = request.getSession();
		    String loginId = (String) session.getAttribute("loginid");
		    resultList = comObj1.PartAWithoutProofPDReportList(loginId);
		    request.setAttribute("resultList", resultList);
	    	return mapping.findForward(target);
      }
}