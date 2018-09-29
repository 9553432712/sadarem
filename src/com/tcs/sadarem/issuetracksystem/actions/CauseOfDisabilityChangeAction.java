package com.tcs.sadarem.issuetracksystem.actions;

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
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class CauseOfDisabilityChangeAction extends BaseDispatchAction 
{
	public ActionForward  unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		HashMap causeofdisabilityChangeDtls = new HashMap();
		HttpSession session = request.getSession();
		HashMap causeofdisabilityChangModifyDtls = new HashMap();
		ArrayList sadaremData = new ArrayList();
		int basicInsertStatus=0;
		String toBemodifyStatus="";
		DiskFileItemFactory factory    =  new DiskFileItemFactory();
	    ServletFileUpload sfu          =  new ServletFileUpload(factory);
	    CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
	    causeofdisabilityChangeDtls = CommonUtility.getDiskFileItemFactory(sfu, request);
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	   
	    try{
	    	
				if(causeofdisabilityChangeDtls.size()>0)
				{
				
				    String causename     	= ((FileItem) causeofdisabilityChangeDtls.get("causename")).getString();
				   
				    String sadaremId    = ((FileItem) causeofdisabilityChangeDtls.get("sadaremid")).getString();
				    String selIssueType = ((FileItem) causeofdisabilityChangeDtls.get("selIssueType")).getString();
				    String FormID		= ((FileItem) causeofdisabilityChangeDtls.get("FormSessionID")).getString();
				    String remarks		= ((FileItem) causeofdisabilityChangeDtls.get("decription")).getString();
				    String MobNo        = ((FileItem) causeofdisabilityChangeDtls.get("contid")).getString();
				    String fileName="",filetype="";
				    String sessionFormID   =  CommonUtility.checkNullObj(session.getAttribute("FormSessionID"));
				 
				    int  roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
				    String decisionFlag = comObj.getTktDecisionFlag(roleId, "P", "",  selIssueType);
					String reqId = comObj.generateIssueReqId(selIssueType);
					
						if(FormID.equals(sessionFormID))
						{
							
						 
							causeofdisabilityChangModifyDtls.put("causename",causename );
							
							causeofdisabilityChangModifyDtls.put("sadaremId",sadaremId );
							causeofdisabilityChangModifyDtls.put("reqId",reqId );
							causeofdisabilityChangModifyDtls.put("selIssueType",selIssueType );
							causeofdisabilityChangModifyDtls.put("reqBy",(String)session.getAttribute("loginid") );
							causeofdisabilityChangModifyDtls.put("decisionFlag",decisionFlag );
							causeofdisabilityChangModifyDtls.put("remarks",remarks );
							causeofdisabilityChangModifyDtls.put("ipaddress",ipaddress ); 
							causeofdisabilityChangModifyDtls.put("MobNo",MobNo );
							
							String issueAlreadyRaised = comObj.getRaisedIssuesCount(sadaremId, selIssueType);
							
							if(!issueAlreadyRaised.equals("N"))
							{
								
									toBemodifyStatus = comObj.insertCauseOfDisabilityChangeDtls(causeofdisabilityChangModifyDtls);
							
							}
							else
							{
								toBemodifyStatus = "<font color='red'>Cause Of Disability Correction Issue is already raised.</font>";
							}
							String issueName = null;
				            issueName = comObj.getIssueName(selIssueType);            
				            request.setAttribute("issueName", issueName);
							request.setAttribute("statusMsg",toBemodifyStatus );
						    sadaremData = comObj.getSadaremCommonData(sadaremId);
					        request.setAttribute("sadaremData", sadaremData);
					        request.setAttribute("selIssueType",selIssueType);
					        session.setAttribute("FormSessionID",""+Math.random());
						}
						else
						{
							request.setAttribute("MSG", "<font color='red'size='3'><b>Please do not refresh the page.<br>Please Try Again..!</b></font>"); 	 		
					 		
							return mapping.findForward("exception");
						}
				}
				else
				{
					request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
			 		return mapping.findForward("exception");
				}
	        
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
	    }
		return mapping.findForward("causeOfDisabilityChange");
	}
	
	
	 
}
