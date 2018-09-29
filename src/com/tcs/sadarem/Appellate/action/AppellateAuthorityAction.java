package com.tcs.sadarem.Appellate.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.dao.DoctorsInformationDAO;
import org.bf.disability.dto.DoctorsInformationDTO;
import org.bf.disability.dto.PartADTO;
import org.bf.disability.form.DisabilityIdForm;
import org.bf.disability.form.PartAForm;
import org.bf.disability.service.PartAService;
import org.bf.disability.servicefactory.PartAServiceFactory;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.Appellate.DAO.AppellateAuthorityDAO;
import com.tcs.sadarem.Appellate.DAO.AppellateAuthorityDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.issuetracksystem.actions.IssueRequestRaiseReportDAO;
import com.tcs.sadarem.issuetracksystem.actions.IssueRequestRaiseReportImpl;
import com.tcs.sadarem.util.CommonUtility;

public class AppellateAuthorityAction extends BaseDispatchAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		ArrayList reassessList = new ArrayList();
		AppellateAuthorityDAO obj = new AppellateAuthorityDAOImpl();	 
		HttpSession session = request.getSession();
		
		String appellatestatus = CommonUtility.checkNullObj(request.getParameter("appellatestatus"));
		
		 
		
		if(appellatestatus.equals(""))
		{
			appellatestatus = "P";
		}
		
		
	    if(CommonUtility.checkNullObj(session.getAttribute("roleId")).equals(CommonConstants.ROLE_APPELLATE) && (appellatestatus.equalsIgnoreCase("P") || appellatestatus.equalsIgnoreCase("H")))
	    {	
		 reassessList = obj.getReassessmentDataList(appellatestatus);
	    }
	    else
	    {
	    	request.setAttribute("MSG", "You are not authorised to perform this operation");
			return mapping.findForward("exception");	
	    }
	    
	    request.setAttribute("reassessList", reassessList); 
	    request.setAttribute("selStatus", appellatestatus);
		 
		return mapping.findForward("success");
	}
	
	
	 
	
	public ActionForward getAppellatePartADetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		String sadaremId="",reqId="",contactNo="";int campId;
		int index,statusCount;
		ArrayList reassessList = new ArrayList();
		ArrayList innerList = new ArrayList();
		ArrayList campList = new ArrayList();
		ArrayList sadaremData = new ArrayList();
		AppellateAuthorityDAO obj = new AppellateAuthorityDAOImpl();
		CommonDAOImpl commonObj      = new CommonDAOImpl();
		CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
		HttpSession session = request.getSession();
		 Random randomGenerator = new Random();
         String code = "";
         String codeGen = "";
         String randomInt = null;
		try
		{
			if(CommonUtility.checkNullObj(session.getAttribute("roleId")).equals(CommonConstants.ROLE_APPELLATE))
		    { 
			  index = Integer.parseInt(CommonUtility.checkNullObj(request.getParameter("assessId")));
			
			  
				sadaremId = CommonUtility.checkNullObj(request.getParameter("sadaremId"+index));
				 reqId    = CommonUtility.checkNullObj(request.getParameter("reqId"+index));
				statusCount = obj.checkSADAREMIDFORAPPELLATE(sadaremId);
				if(statusCount>0)
				{
					campList = commonObj.getCampListByDistrictID(CommonConstants.APPELLATE_District_ID);
					sadaremData = comObj.getSadaremCommonData(sadaremId);
					//contactNo   = commonObj.getContactNoOfLogin(CommonUtility.checkNullObj(session.getAttribute("username")));
					
					ArrayList DisabilityTypeList = new ArrayList();
					DisabilityTypeList  = commonObj.getDisabilityList();
					
					 for(int idx = 1; idx <= 5; ++idx) 
			            {
			                randomInt = Integer.toHexString(randomGenerator.nextInt(6));
			                code = code + "" + randomInt;
			                codeGen = codeGen + " " + randomInt;
			            }
			            
			            if (code != null) 
			            {
			                request.setAttribute("captchaCode", codeGen);
			                request.setAttribute("code", code);
			            }
			            
					//request.setAttribute("contactNo",contactNo);
					request.setAttribute("sadaremData",sadaremData);
					request.setAttribute("campList",campList);
					request.setAttribute("sadaremId",sadaremId);
					request.setAttribute("reqId",reqId);
					request.setAttribute("DisabilityTypeList",DisabilityTypeList);
				}
				else
				{
					request.setAttribute("MSG", "SADAREM ID "+sadaremId+" is not eligible for Appellate Authority Reassessment");
					return mapping.findForward("exception");
				}
		    }
		    else
		    {
		    	request.setAttribute("MSG", "You are not authorised to perform this operation");
				return mapping.findForward("exception");	
		    }
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("MSG", "SADAREM ID "+sadaremId+" is not eligible for Appellate Authority Reassessment");
			return mapping.findForward("exception");
			
		}
		return mapping.findForward("openpartA");
	}
	
	public ActionForward openpartb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		String sadaremId="",dob="";int campId;
		HttpSession session = request.getSession();
		PartAForm partaform =(PartAForm)form; 
		ArrayList sadaremData = new ArrayList();
		 try 
		 {
			 if(CommonUtility.checkNullObj(session.getAttribute("roleId")).equals(CommonConstants.ROLE_APPELLATE))
			 {  
					sadaremId 			= CommonUtility.checkNullObj(request.getParameter("sadaremId"));
					campId 				= Integer.parseInt(CommonUtility.checkNullObj(request.getParameter("camp")));
				    String otp 			= 	CommonUtility.checkNullObj(request.getParameter("otpid"));
				    String reqId  		= CommonUtility.checkNullObj(request.getParameter("reqId"));
				    String contactNo  	= CommonUtility.checkNullObj(request.getParameter("contactNo"));
				    
				    AppellateAuthorityDAO obj = new AppellateAuthorityDAOImpl();
				   
					 PartADTO partADTO = new PartADTO();
					 PartAService partAService = PartAServiceFactory.getPartAServiceImpl();
					 String txtcaptcha      = CommonUtility.checkNullObj(request.getParameter("txtCaptcha"));
					 String captchainput	= CommonUtility.checkNullObj(request.getParameter("txtInput"));
					 
					 String personStatus      = CommonUtility.checkNullObj(request.getParameter("personStatusForAU"));
					 
					 					 
					 if (txtcaptcha.equals(captchainput))
					 {
						 
						 
						 
						 if(personStatus.equalsIgnoreCase("Rejected"))
						 {
							 AppellateAuthorityDAO Object = new AppellateAuthorityDAOImpl();
							 
							 String loginid = null;
							 loginid = (String) session.getAttribute("loginid");
							 /** Updating the person Status Eligible / Rejected Based on selection and update categoryid and categorycount in tblPerson_personal_details*/
							 //Object.updatePersonStatus("Rejected",sadaremId ,"4","1",campId, loginid);

			                 /** method for updating the view_edit_mode,updated_date,edit to view ,Active to Inactive in AppellateAuthorityandTemporary_RegistrationDetails*/
							// Object.checkPersonStatusForAppealAuthority(sadaremId, "Rejected");
							 
							 
							 String disableId=CommonUtility.checkNullObj(request.getParameter("disabilityId"));
							 CommonDAOImpl comObj = new CommonDAOImpl();
							 String disbiliName = comObj.getDisabilityName(disableId);
							 DoctorsInformationDAO DocDAO = new DoctorsInformationDAO();
							 DoctorsInformationDTO DoctorsInfdto = DocDAO.selectDoctorDetails(null,  disbiliName, campId);
							 String Systemip = request.getRemoteAddr();
							 
							 Object.insertDisabilityDetailsAppleteDirectReject(sadaremId,  DoctorsInfdto,  personStatus, loginid, Integer.parseInt(disableId),  Systemip,  campId);
							 request.setAttribute("personcode", sadaremId);
							 session.setAttribute("personcode",sadaremId);
							 return mapping.findForward("dirReject");
			                    
						 }
						 else
						 {	 
							partADTO = partAService.getPersonalDetails(sadaremId, "Eligible", null, campId);
						    dob = CommonUtility.checkNullObj(partADTO.getDobday());
							
					         String[] stringArray = dob.split("/");
					         if(stringArray.length>0)
					         {
						         partADTO.setDay(stringArray[0]);
						         partADTO.setMonth(stringArray[1]);
						         partADTO.setYear(stringArray[2]);
					         }
					         request.setAttribute("roleId", session.getAttribute("roleId"));
					
					         //Chrononical age  calculation for Mental Retardation tests.
					         Date d1 = new GregorianCalendar(Integer.parseInt(partADTO.getYear()), Integer.parseInt(partADTO.getMonth()), Integer.parseInt(partADTO.getDay())).getTime();
					         Date today = new Date();
					         long diff = today.getTime() - d1.getTime();
					         long days = diff / (1000 * 60 * 60 * 24);
					
					         double years = (double) days / 365;
					
					         session.setAttribute("chronologicalage", new Double(years));
					
					
					         int disablityid = partADTO.getDisabilityId();
					         request.setAttribute("olddisablityid", disablityid);
					         DisabilityIdForm disabilityIdForm = new DisabilityIdForm();
					         disabilityIdForm.setDisabilityId(String.valueOf(partADTO.getDisabilityId()));
					 
					         String teluguname = partADTO.getTelugupersonname();
					         String telugufathername = partADTO.getTelugufathername();
					         session.setAttribute("teluguname", teluguname);
					         session.setAttribute("telugufathername", telugufathername);
					         session.setAttribute("Name", partADTO.getFirstname());
					
					         request.setAttribute("surname", partADTO.getSurname());
					         request.setAttribute("name", partADTO.getFirstname());
					         request.setAttribute("relName", partADTO.getGsurname());
					      
					         partaform.setDistrict((String) request.getParameter("district_id"));
					         partaform.setMandal((String) request.getParameter("mandal_id"));
					         partaform.setTownVillage((String) request.getParameter("village_id"));
					         partaform.setHabitation((String) request.getParameter("habitation_id"));
					         partaform.setSystemip(CommonUtility.getClientIPAddress(request));
					         partaform.setCampid(campId);
					         session.setAttribute("personstatus", partADTO.getPersonstatus());
					         String vieweditmode = CommonUtility.checkNullObj(partADTO.getOperatorStatus());
					 
					
					
					         if (partADTO.getCard() != null && partADTO.getCard().trim().length() > 0)
					         {
					             request.setAttribute("RationReadOnly", "true");
					         }
					         if (partADTO.getRationCardSlno() != null && partADTO.getRationCardSlno().trim().length() > 0)
					         {
					             request.setAttribute("SlNoReadOnly", "true");
					         }
					
					         //ended
					         BeanUtils.copyProperties(partaform, partADTO);
							
							 session.setAttribute("campId",campId);
							 session.setAttribute("personcode",sadaremId);
						    }					  			
					 }
					 else
					 {
						   request.setAttribute("MSG", "Invalid CAPTCHA Please try again");
							return mapping.findForward("exception");
					 }
			 }
			else
			{
				    	request.setAttribute("MSG", "You are not authorised to perform this operation");
						return mapping.findForward("exception");	
			}
		 } 
		catch (Exception e)
		{
						e.printStackTrace();
					    request.setAttribute("MSG", "Error Occurred while opening Part B");
						return mapping.findForward("exception");
		}
		return mapping.findForward("forward");
	}
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String title = "Appellate Authority Reassessment";
		System.out.println(title);
		ArrayList hodList = new ArrayList();
		ArrayList PendList = new ArrayList();
		ArrayList HeaderList = new ArrayList();
		try
		{			
			CommonDAO cobj= new CommonDAOImpl();
			AppellateAuthorityDAO obj = new AppellateAuthorityDAOImpl();
			hodList = obj.getReassessmentDataList("H");
			PendList = obj.getReassessmentDataList("P");
			System.out.println(hodList);
			System.out.println(PendList);
			PendList.addAll(hodList);
			HeaderList.add("SADAREM ID");
			HeaderList.add("Request ID");
			HeaderList.add("Category");
			HeaderList.add("Name");
			HeaderList.add("District");
			HeaderList.add("Mandal");
			HeaderList.add("Village");
			HeaderList.add("Type of Disability");
			HeaderList.add("Date");
			HeaderList.add("No. Of (Day)s Pending");
			HeaderList.add("Hold Status");

	    	cobj.exportExcel(HeaderList, PendList , request,  response,  title);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return null;
		  
	}
  	
}
