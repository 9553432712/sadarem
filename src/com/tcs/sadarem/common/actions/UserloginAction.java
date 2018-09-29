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

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.common.DAO.UserloginDAO;
import com.tcs.sadarem.common.DAO.UserloginDAOImpl;
import com.tcs.sadarem.util.ComboUtil;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.RequestUtility;

public class UserloginAction extends DispatchAction
{
  
	public ActionForward loaduserloginchange(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadUserLoginModificationPage";
       
       HttpSession session = request.getSession(true);
       
       String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	       try
	       { 
	    	   if(!sesUserId.equals(""))
	    	   {
	    		  UserloginDAO userObj = new UserloginDAOImpl(); 
	    		  request.setAttribute("statusList", userObj.getUserStatusList());
	    		  request.setAttribute("roleList", userObj.getUserRolesList());
	    		  request.setAttribute("alert_css", "alert-info");
	    		  request.setAttribute("alert_msg", "Please select the options or enter username to view/update the details");
	    		  session.setAttribute("sesFormId", "sesFormId"+Math.random());
	    	   }
	    	   else
	    	   {
		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
		    	   target="exceptionPage";
	    	   }
	    	   
	       }
	       catch(Exception e)
	       {
	    	   target="exceptionPage";
	    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
	    	   e.printStackTrace();
	       }
 
	       
       return mapping.findForward(target);
    }
	
	public ActionForward loaduserlogindetailforsearch(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadUserLoginModificationPage";
       
       HttpSession session = request.getSession(true);
       
       		String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
       		String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId"));
	       try
	       { 
	    	   if(!sesUserId.equals(""))
	    	   {
	    		   String reqFormId		= CommonUtility.checkNullObj(request.getParameter("reqFormId"));
	    		   String selStatusFlag	= CommonUtility.checkNullObj(request.getParameter("selStatusFlag"));
	    		   String selRoleId		= CommonUtility.checkNullObj(request.getParameter("selRoleId"));
	    		   String selDistrict	= CommonUtility.checkNullObj(request.getParameter("selDistrict"));
	    		   String selCampId		= CommonUtility.checkNullObj(request.getParameter("selCampId"));
	    		   String selMandal		= CommonUtility.checkNullObj(request.getParameter("selMandal"));
	    		   String selUsername	= CommonUtility.checkNullObj(request.getParameter("selUsername"));
	    		    
	    		   	  UserloginDAO userObj = new UserloginDAOImpl(); 
	    		       
	    		   	  if(reqFormId.equals(sesFormId))
	    		   	  {
			    		  request.setAttribute("userLoginDataList", userObj.getUserLoginDetailsforSearch(selStatusFlag,selRoleId, selDistrict, selCampId, selMandal, selUsername,true));
			    		  request.setAttribute("districtList", userObj.getDistrictListbyRoleId(selRoleId)); 
			    		  request.setAttribute("campList", userObj.getCampListbyRoleIdDistId(selRoleId, selDistrict)); 
			    		  request.setAttribute("mandalList", userObj.getMandalListbyRoleIdDistId(selRoleId, selDistrict)); 

			    		  request.setAttribute("selStatusFlag", selStatusFlag);
			    		  request.setAttribute("selRoleId", selRoleId);
			    		  request.setAttribute("selDistrict", selDistrict);
			    		  request.setAttribute("selCampId", selCampId);
			    		  request.setAttribute("selMandal", selMandal);
			    		  request.setAttribute("selUsername", selUsername);
	    		   	  }
	    		   	  else
	    		   	  { 
	    	    		  request.setAttribute("alert_css", "alert-danger");
	    	    		  request.setAttribute("alert_msg", "Invalid request form. Please do not refresh the page.");
	    		   	  }
		    		 
	    		   	 request.setAttribute("statusList", userObj.getUserStatusList());
	    		   	 request.setAttribute("roleList", userObj.getUserRolesList()); 
		    		 
	    	   }
	    	   else
	    	   {
		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
		    	   target="exceptionPage";
	    	   }
	    	   
	       }
	       catch(Exception e)
	       {
	    	   target="exceptionPage";
	    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
	    	   e.printStackTrace();
	       }
 
	       
       return mapping.findForward(target);
   }
	
	public ActionForward loaduserdetailsbyusername(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadUserDetailsforEditPage";
       
       HttpSession session = request.getSession(true);

  		String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
  		String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId"));
	       try
	       { 
	    	   if(!sesUserId.equals(""))
	    	   {
	    		   String reqFormId		= CommonUtility.checkNullObj(request.getParameter("reqFormId"));     
	    		   String selUsername	= CommonUtility.checkNullObj(request.getParameter("selUsername"));
	    		    
	    		   	  UserloginDAO userObj = new UserloginDAOImpl();
	    		   	  
	    		   	  session.setAttribute("ses_selUsername",null);
		    		  session.setAttribute("sesFormId", "sesFormId"+Math.random());
		    		  
	    		   	  if(reqFormId.equals(sesFormId))
	    		   	  {

	    		   		  ArrayList userLoginDetail = (ArrayList)userObj.getUserLoginDetailsforSearch(null,null, null, null, null, selUsername,false);
	    		   		 
	    		   		  if(userLoginDetail.size()==1)
	    		   		  {
		    		   		  HashMap userDetailsList = (HashMap)userLoginDetail.get(0);
		    		   		   
	    		   			  request.setAttribute("statusList", userObj.getUserStatusList());
	    		    		  request.setAttribute("roleList", userObj.getUserRolesList());
		    		   		  request.setAttribute("userLoginDataList",userDetailsList);
				    		  request.setAttribute("districtList", userObj.getDistrictListbyRoleId(userDetailsList.get("role_id").toString().trim())); 
				    		  request.setAttribute("campList", userObj.getCampListbyRoleIdDistId(userDetailsList.get("role_id").toString().trim(), userDetailsList.get("district_id").toString().trim())); 
				    		  request.setAttribute("mandalList", userObj.getMandalListbyRoleIdDistId(userDetailsList.get("role_id").toString().trim() , userDetailsList.get("district_id").toString().trim()));
				    		  session.setAttribute("ses_selUsername", selUsername);
	    		   		  }
	    		   		  else
	    		   		  { 
	    		   			  request.setAttribute("statusList", userObj.getUserStatusList());
	    		    		  request.setAttribute("roleList", userObj.getUserRolesList());
	    		    		  request.setAttribute("alert_css", "alert-danger");
	    		    		  request.setAttribute("alert_msg", "No detail available with with username.");
		    		   		  target = "loadUserLoginModificationPage";
	    		   		  }
	    		   	  }
	    		   	  else
	    		   	  {  
	    		   		  request.setAttribute("statusList", userObj.getUserStatusList());
	    		   		  request.setAttribute("roleList", userObj.getUserRolesList());
	    	    		  request.setAttribute("alert_css", "alert-danger");
	    	    		  request.setAttribute("alert_msg", "Invalid request form. Please do not refresh the page."); 
	    		   		  target = "loadUserLoginModificationPage";
	    		   	  } 
  
	    	   }
	    	   else
	    	   {
		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
		    	   target="exceptionPage";
	    	   }
	    	   
	       }
	       catch(Exception e)
	       {
	    	   target="exceptionPage";
	    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
	    	   e.printStackTrace();
	       }
 
	       
       return mapping.findForward(target);
    }
	 
	
	public ActionForward loguserloginupdates(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadUserDetailsforEditPage";
       
       HttpSession session = request.getSession(true);

  		String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
  		String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId"));
	       try
	       { 
	    	   if(!sesUserId.equals(""))
	    	   {
	    		   String reqFormId		= CommonUtility.checkNullObj(request.getParameter("reqFormId"));     
	    		   String selUsername	= CommonUtility.checkNullObj(session.getAttribute("ses_selUsername"));
	    		    
	    		   	  UserloginDAO userObj = new UserloginDAOImpl(); 

		    		  session.setAttribute("sesFormId", "sesFormId"+Math.random());
	    		   	  if(reqFormId.equals(sesFormId) && selUsername!=null && !selUsername.equals(""))
	    		   	  {
	    		   		   
	    		   		  HashMap userDataList = RequestUtility.getReqParamList(request, session, false, false, "");
	    		   		    
	    		   		  if(userDataList.size()>0)
	    		   		  {
	    		   			userObj.updateUserLoginDetails(request,selUsername,userDataList, sesUserId);
	    		   		  }

	    		   		  ArrayList userLoginDetail = (ArrayList)userObj.getUserLoginDetailsforSearch(null,null, null, null, null, selUsername,false);
	    		   		 
	    		   		  if(userLoginDetail.size()==1)
	    		   		  {
		    		   		  HashMap userDetailsList = (HashMap)userLoginDetail.get(0);
		    		   		   
	    		   			  request.setAttribute("statusList", userObj.getUserStatusList());
	    		    		  request.setAttribute("roleList", userObj.getUserRolesList());
		    		   		  request.setAttribute("userLoginDataList",userDetailsList);
				    		  request.setAttribute("districtList", userObj.getDistrictListbyRoleId(userDetailsList.get("role_id").toString().trim())); 
				    		  request.setAttribute("campList", userObj.getCampListbyRoleIdDistId(userDetailsList.get("role_id").toString().trim(), userDetailsList.get("district_id").toString().trim())); 
				    		  request.setAttribute("mandalList", userObj.getMandalListbyRoleIdDistId(userDetailsList.get("role_id").toString().trim() , userDetailsList.get("district_id").toString().trim()));
	    		   		  }
	    		   		  else
	    		   		  { 
	    		   			  request.setAttribute("statusList", userObj.getUserStatusList());
	    		    		  request.setAttribute("roleList", userObj.getUserRolesList());
	    		    		  request.setAttribute("alert_css", "alert-danger");
	    		    		  request.setAttribute("alert_msg", "No detail available with with username.");
		    		   		  target = "loadUserLoginModificationPage";
	    		   		  }
	    		   	  }
	    		   	  else
	    		   	  {  
	    		   		  request.setAttribute("statusList", userObj.getUserStatusList());
	    		   		  request.setAttribute("roleList", userObj.getUserRolesList());
	    	    		  request.setAttribute("alert_css", "alert-danger");
	    	    		  request.setAttribute("alert_msg", "Invalid request form. Please do not refresh the page."); 
	    		   		  target = "loadUserLoginModificationPage";
	    		   	  } 
  
	    	   }
	    	   else
	    	   {
		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
		    	   target="exceptionPage";
	    	   }
	    	   
	       }
	       catch(Exception e)
	       {
	    	   target="exceptionPage";
	    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
	    	   e.printStackTrace();
	       }
 
	       
       return mapping.findForward(target);
    }

	public ActionForward lognewuserlogindetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadNewUserLoginDetailsPage";
       
       HttpSession session = request.getSession(true);
       
       String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	       try
	       { 
	    	   if(!sesUserId.equals(""))
	    	   {
		    	 UserloginDAO userObj = new UserloginDAOImpl();
		    		  
	    		   HashMap formDataList = (HashMap)RequestUtility.getReqParamList(request, session, false, false, "");
	    		
	    		  // System.out.println("formDataList : "+formDataList);
	    		   
	    		   if(formDataList!=null && formDataList.size()>0 && formDataList.containsKey("reqFormId")==true)
	    		   {
	    			   String sesFormId = CommonUtility.checkNullObj(session.getAttribute("sesFormId"));
	    			   
	    			 //  System.out.println("sesFormId : "+sesFormId+"\n reqFormId : "+CommonUtility.checkNullObj(formDataList.get("reqFormId")));
	    			   
	    			   if(sesFormId.equals(CommonUtility.checkNullObj(formDataList.get("reqFormId"))))
					   { 
	    				   	userObj.lognewuserlogin(request, formDataList, sesUserId);
					   }
	    			   else
	    			   { 
			    			  request.setAttribute("alert_css", "alert-danger");
		    	    		  request.setAttribute("alert_msg", "Invalid request form. Please do not refresh the page."); 
	    			   }
	    			   
	    		   } 
	    		    
	    		  request.setAttribute("statusList", userObj.getUserStatusList());
	    		  request.setAttribute("roleList", userObj.getUserRolesList()); 
	    		  session.setAttribute("sesFormId", "sesFormId"+Math.random()); 
		    	  session.setAttribute("sesTabFormId", "sesTabFormId"+Math.random());
	    	   }
	    	   else
	    	   {
		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
		    	   target="exceptionPage";
	    	   }
	    	   
	       }
	       catch(Exception e)
	       {
	    	   target="exceptionPage";
	    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
	    	   e.printStackTrace();
	       }
 
	       
       return mapping.findForward(target);
    }

	public ActionForward viewexistingloginuserdetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadExistingUserDetailsPage";
       
       HttpSession session = request.getSession(true);

       String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
       String sesTabFormId	= CommonUtility.checkNullObj(session.getAttribute("sesTabFormId"));
	       try
	       { 
	    	   
	    	   session.setAttribute("sesTabFormId", "sesTabFormId"+Math.random());
	    	   
	    	   if(!sesUserId.equals(""))
	    	   {  
	    		  UserloginDAO userObj = new UserloginDAOImpl();

	    	       String reqTabFormId		= CommonUtility.checkNullObj(request.getParameter("reqTabFormId")); 
	    	       String role_id			= CommonUtility.checkNullObj(request.getParameter("role_id")); 
	    	       String district_id		= CommonUtility.checkNullObj(request.getParameter("district_id")); 
	    	       String camp_id			= CommonUtility.checkNullObj(request.getParameter("camp_id")); 
	    	       String mandal_id			= CommonUtility.checkNullObj(request.getParameter("mandal_id")); 
	    	       String search_user_id	= CommonUtility.checkNullObj(request.getParameter("search_user_id"));  
	    	       String search_email_id	= CommonUtility.checkNullObj(request.getParameter("search_email_id")); 
	    	       String search_contact_no	= CommonUtility.checkNullObj(request.getParameter("search_contact_no")); 
	    	       

	    	      /* System.out.println("reqTabFormId : "+reqTabFormId);
	    	       System.out.println("role_id : "+role_id);
	    	       System.out.println("district_id : "+district_id);
	    	       System.out.println("camp_id : "+camp_id);
	    	       System.out.println("mandal_id : "+mandal_id);
	    	       System.out.println("search_user_id : "+search_user_id);
	    	       System.out.println("search_email_id : "+search_email_id);
	    	       System.out.println("search_contact_no : "+search_contact_no);*/
		    	
		    		  if(reqTabFormId.equals(sesTabFormId))
		    		  {
		    			  ArrayList userLoginDetailsList =  userObj.getLoginUserDetailsByRoleDistCampId(role_id, district_id, camp_id, mandal_id, search_user_id, search_email_id, search_contact_no); 
		    			  
		    			  request.setAttribute("loginUserDetailsExist",userLoginDetailsList);
		    		  }
		    		  else
		    		  {
		    			  request.setAttribute("alert_tab_css", "alert-danger");
	    	    		  request.setAttribute("alert_tab_msg", "Invalid request form. Please do not refresh the page."); 
		    		  } 
	    	   }
	    	   else
	    	   {
		    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
		    	   target="exceptionPage";
	    	   }
 
	       }
	       catch(Exception e)
	       {
	    	   target="exceptionPage";
	    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
	    	   e.printStackTrace(); 
	    	   
	       }

    	   return mapping.findForward(target);
    }
	
	
	public void loadsalogdistrictcombobyroleid(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String result=ComboUtil.createStrComboBoxAuto("SALogDistrictId", new ArrayList(), "", "", "", true, true, "-Select-");
		try
		{
			String selroleid = CommonUtility.checkNullObj(request.getParameter("SALogRoleId"));
			
			if(!selroleid.equals("-1"))
			{
				UserloginDAO userObj = new UserloginDAOImpl();
				ArrayList dataList = (ArrayList)userObj.getDistrictListbyRoleId(selroleid);
				
				if(dataList.size()==1)
				{
					result=ComboUtil.createStrComboBoxAuto("SALogDistrictId",dataList, "", "form-control", "", false, false, "");
				}
				else
				{

					result=ComboUtil.createStrComboBoxAuto("SALogDistrictId",dataList, "", "form-control", "", true, true, "-Select-");
				}
			}
			

			response.getWriter().write(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 
	}
	
	public void loadsalogcampbyroleiddistid(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String result=ComboUtil.createStrComboBoxAuto("SALogCampId", new ArrayList(), "", "", "", true, true, "-Select-");
		try
		{
			String selroleid 	= CommonUtility.checkNullObj(request.getParameter("SALogRoleId"));
			String selDistrict 	= CommonUtility.checkNullObj(request.getParameter("SALogDistrictId"));
			
			if(!selroleid.equals("-1") && !selDistrict.equals("-1"))
			{
				UserloginDAO userObj = new UserloginDAOImpl();
				ArrayList dataList = (ArrayList)userObj.getCampListbyRoleIdDistId(selroleid, selDistrict);
				
				if(dataList.size()==1)
				{
					result=ComboUtil.createStrComboBoxAuto("SALogCampId",dataList, "", "form-control", "", false, false, "");
				}
				else
				{

					result=ComboUtil.createStrComboBoxAuto("SALogCampId",dataList, "", "form-control", "", true, true, "-Select-");
				}
			}
			
			response.getWriter().write(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 
	}
	
	public void loadsalogmandalbyroleiddistid(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String result=ComboUtil.createStrComboBoxAuto("SALogMandal", new ArrayList(), "", "", "", true, true,"-Select-");
		try
		{
			String selroleid 	= CommonUtility.checkNullObj(request.getParameter("SALogRoleId"));
			String selDistrict 	= CommonUtility.checkNullObj(request.getParameter("SALogDistrictId"));
			
			if(!selroleid.equals("-1") && !selDistrict.equals("-1"))
			{
				UserloginDAO userObj = new UserloginDAOImpl();
				ArrayList dataList = (ArrayList)userObj.getMandalListbyRoleIdDistId(selroleid, selDistrict);
				
				if(dataList.size()==1)
				{
					result=ComboUtil.createStrComboBoxAuto("SALogMandal",dataList, "", "form-control", "", false, false, "");
				}
				else
				{ 
					result=ComboUtil.createStrComboBoxAuto("SALogMandal",dataList, "", "form-control", "", true, true, "-Select-");
				}
			}

			response.getWriter().write(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void reloadsuperadmincontrol(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
		String result="";
		 
	       HttpSession session = request.getSession(true);

		try
		{
			

        	CommonDAO comObj 	 = new CommonDAOImpl();
			UserloginDAO userObj = new UserloginDAOImpl();
			
			
			String selroleid 	= CommonUtility.checkNullObj(request.getParameter("SALogRoleId"));
			String selDistrict 	= CommonUtility.checkNullObj(request.getParameter("SALogDistrictId"));
			String SALogCampId	= CommonUtility.checkNullObj(request.getParameter("SALogCampId"));
			String SALogMandal	= CommonUtility.checkNullObj(request.getParameter("SALogMandal"));
			String SALogStatus	= CommonUtility.checkNullObj(request.getParameter("SALogStatus"));
			String SALogCapText	= CommonUtility.checkNullObj(request.getParameter("SALogCapText"));
			
			
			if(SALogCampId.equalsIgnoreCase("NA"))
			{
				SALogCampId = "";
			}
			
			if(SALogMandal.equalsIgnoreCase("NA"))
			{
				SALogMandal = "";
			}
			
			String 	sesCaptcha	= CommonUtility.checkNullObj(session.getAttribute("SALogCaptchaImageCode"));
			session.removeAttribute("SALogCaptchaImageCode");
			
			//System.out.println("SALogCapText : "+SALogCapText+"\n sesCaptcha : "+sesCaptcha);
			
			if(SALogCapText.equals(sesCaptcha))
			{
				String logDistName = "";
	        	String logCampName = "";
	        	String logRoleName = "";
     
        	
	        	if(SALogStatus.equals("reset"))
	        	{
	        		selroleid 	= CommonUtility.checkNullObj(session.getAttribute("SALog_RoleId"));
	        		selDistrict = CommonUtility.checkNullObj(session.getAttribute("SALog_DistId"));
	        		SALogCampId = CommonUtility.checkNullObj(session.getAttribute("SALog_CampId"));
	        		SALogMandal = CommonUtility.checkNullObj(session.getAttribute("SALog_MandId"));
	        		
	        		logDistName = CommonUtility.checkNullObj(session.getAttribute("SALog_logDistName"));
	        		logCampName = CommonUtility.checkNullObj(session.getAttribute("SALog_logCampName"));
	        		logRoleName = CommonUtility.checkNullObj(session.getAttribute("SALog_logRoleName"));
	        		
	        		result = "<font color=\"blue\"><b>Super Admin Control Detail Reset Successfully</b></font>";
	        	}
	        	else
	        	{
	        	
		        	logDistName = comObj.getDistrictName(selDistrict);
		        	if(!SALogCampId.equals(""))
		        	{
		        		logCampName = comObj.getCampName(Integer.parseInt(SALogCampId));
		        	}
		        	
		        	if(!selroleid.equals(""))
		        	{
		        		logRoleName	= comObj.getRoleName(Integer.parseInt(selroleid));
		        	}
	        	
		        	 
		        	if(logDistName.equals(""))
		        	{
		        		logDistName="-";
		        	}
		        	
		        	if(logCampName.equals(""))
		        	{
		        		logCampName="-";
		        	}
		        	
		        	if(logRoleName.equals(""))
		        	{
		        		logRoleName="-";
		        	}
		        	
	
	        		result = "<font color=\"green\"><b>Super Admin Control Detail Updated Successfully</b></font>";
				
	        	} 
        	
	        	ArrayList loginurllist = (ArrayList)comObj.getLoginUrlList(Integer.parseInt(selroleid));
	        	
	        	loginurllist.add("userloginaction.do?action=reloadsuperadmincontrol");
        	 
	        	
				session.setAttribute("SALogRoleList", userObj.getUserRolesList()); 
	    	 	session.setAttribute("SALogDistrictList", userObj.getDistrictListbyRoleId(selroleid)); 
	    	 	session.setAttribute("SALogCampList", userObj.getCampListbyRoleIdDistId(selroleid,selDistrict)); 
	    	 	session.setAttribute("SALogMandalList", userObj.getMandalListbyRoleIdDistId(selroleid,selDistrict));
	    	 	
	    	 	
	    	    session.setAttribute("districtId", selDistrict);
	            session.setAttribute("campId", SALogCampId);
	            session.setAttribute("roleId", selroleid);
	            session.setAttribute("mandalId", SALogMandal);
	            session.setAttribute("loginurllist",loginurllist);
	            
	            
	            session.setAttribute("logDistName", logDistName);
	            session.setAttribute("logCampName", logCampName);
	            session.setAttribute("logRoleName", logRoleName);
			}
			else
			{

        		result = "<font color=\"red\"><b>Please enter valid Captcha</b></font>";
			}
			
			response.getWriter().write(result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
	 
	
}
