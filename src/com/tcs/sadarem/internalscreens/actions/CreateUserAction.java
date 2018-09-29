package com.tcs.sadarem.internalscreens.actions;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class CreateUserAction extends BaseDispatchAction
{	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		 String target="useradd";
		 HttpSession session = request.getSession();
		 String sesUsername = CommonUtility.checkNullObj(session.getAttribute("username"));
		 ArrayList rolesToCreateLoginList = new ArrayList();
		 ArrayList districtList = new ArrayList();
		 try
		 {
			 if(sesUsername!=null && sesUsername.length()>0)
			 {
				 CreateUserDAO obj = new CreateUserDAOImpl();
				 rolesToCreateLoginList = obj.rolesToCreateLogin();
				 
				 CommonDAO comobj = new CommonDAOImpl(); 
				 districtList = comobj.getDistrictList();
				 
				 request.setAttribute("rolesToCreateLoginList", rolesToCreateLoginList);
				 request.setAttribute("districtList", districtList);
				 
			 }
			 else
			 {
				 request.setAttribute("MSG", "Error Occured!!. Please try agaun...");
				 target="failure";
			 }
		 }
		 catch(Exception e)
		 {
			 request.setAttribute("MSG", "Error Occured!!. Please try agaun...");
			 target="exception";
			 e.printStackTrace();
		 }
	     return mapping.findForward(target);
	}
	public ActionForward userAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{

		 String target="useradd";
		 HttpSession session = request.getSession();
		 String sesUsername = CommonUtility.checkNullObj(session.getAttribute("username"));
		 ArrayList rolesToCreateLoginList = new ArrayList();
		 ArrayList districtList = new ArrayList();
		 String resultMSG = "";
		 try
		 {
			 if(sesUsername!=null && sesUsername.length()>0)
			 {
				String roleid = request.getParameter("roleid");
				String districtid = request.getParameter("selDistrict");
				String selMandal  = request.getParameter("selMandal");
				String campid = request.getParameter("selCampId");
				String EmpID = request.getParameter("EmpID");
				String EmpName = request.getParameter("EmpName");
				String gender = request.getParameter("gender");
				String mobileno = request.getParameter("mobileno");
				String emailid = request.getParameter("emailid");	
				String sysIP = request.getRemoteAddr();
				
				HashMap inpParam = new HashMap();
				
				inpParam.put("roleid", roleid);
				inpParam.put("districtid", districtid);
				inpParam.put("selMandal", selMandal);
				inpParam.put("campid", campid);
				inpParam.put("EmpID", EmpID);
				inpParam.put("EmpName", EmpName);
				inpParam.put("gender", gender);
				inpParam.put("mobileno", mobileno);
				inpParam.put("emailid", emailid);
				inpParam.put("createdBy", sesUsername);
				inpParam.put("sysIP", sysIP);
				
				
				
				CreateUserDAO obj = new CreateUserDAOImpl();
				String alertMsgCSS = "";
				
				if(obj.checkForUserExist(EmpID)>=1)
				{
					resultMSG = "The User Name "+EmpID+" already existed.";
					alertMsgCSS = "alert-danger";
				}
				else
				{
					 resultMSG = obj.createUser(inpParam);
					 alertMsgCSS = "alert-success";
				}
				
				rolesToCreateLoginList = obj.rolesToCreateLogin();
				 
				 CommonDAO comobj = new CommonDAOImpl(); 
				 districtList = comobj.getDistrictList();
				 
				 request.setAttribute("rolesToCreateLoginList", rolesToCreateLoginList);
				 request.setAttribute("districtList", districtList);
				 
				 request.setAttribute("resultMSG", resultMSG);
				 request.setAttribute("alertMsgCSS", alertMsgCSS);
			
			 }
			 else
			 {
				 request.setAttribute("MSG", "Error Occured!!. Please try agaun...");
				 target="failure";
			 }
		 }
		 catch(Exception e)
		 {
			 request.setAttribute("MSG", "Error Occured!!. Please try agaun...");
			 target="exception";
			 e.printStackTrace();
		 }
	     return mapping.findForward(target);
	
	}

}
