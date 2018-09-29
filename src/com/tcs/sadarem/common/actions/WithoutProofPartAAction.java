package com.tcs.sadarem.common.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import Aadhar.AadhaarUtility;

import com.ecentric.servicemodels.AadhaarProfile;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.CommonValidators;
import com.tcs.sadarem.util.PasswordEncriptDecrypt;
import com.tcs.sadarem.util.RequestUtility;
import com.tcs.sadarem.withoutproofparta.DAO.WithoutProofPartADAO;
import com.tcs.sadarem.withoutproofparta.DAO.WithoutProofPartADAOImpl;

public class WithoutProofPartAAction extends DispatchAction
{

	
	static String message ="";
	
	
	public ActionForward loadpartaentrywithaadhaar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "searchpartaentrybyaadharPage";
	     
	    	   HttpSession session = request.getSession(true);
	           
	           String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	    	       try
	    	       { 
	    	    	   if(!sesUserId.equals(""))
	    	    	   {  
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
	
 
	public ActionForward loadpartawithaadhaardetails(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "searchpartaentrybyaadharPage";
	     
	    	   HttpSession session = request.getSession(true);
	           
	           String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	    	       try
	    	       { 
	    	    	   if(!sesUserId.equals(""))
	    	    	   {  

	    		           String reqFormId		= CommonUtility.checkNullObj(request.getParameter("reqFormId"));
	    		           String searchaadhaar = CommonUtility.checkNullObj(request.getParameter("searchaadhaar"));
	    		           
	    		           String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 

	    		           session.setAttribute("sesFormId", "sesFormId"+Math.random());
	    		           
	    		           session.removeAttribute("AadhaarProfileData"); 
	    		    		  
	    	    		    if(reqFormId.equals(sesFormId) && CommonValidators.AadhaarNumberValidator(searchaadhaar)==true)
	    	    		    {  
	    	    		    	 WithoutProofPartADAO withOutObj  = new WithoutProofPartADAOImpl();
	    	    		    	 AadhaarProfile	selAadhaarProfile = null;
	    	    		    	 HashMap requestDetailsList = withOutObj.getRequestDetailsByAadhaarIdorRequestId(searchaadhaar, searchaadhaar);
	    	    		    	 
	    	    		    	 if(requestDetailsList!=null && requestDetailsList.size()>0)
	    	    		    	 {
	    	    		    		  request.setAttribute("requestDetailsList", requestDetailsList);
	    	    		    		 
			    	    		      request.setAttribute("alert_msg", "Details Already Exist with Aaadhaar ID : "+searchaadhaar+".Please find the details below Registered Details");
			    	    		      request.setAttribute("alert_css", "alert-danger");
	    	    		    	 }
	    	    		    	 else
	    	    		    	 { 

			    	    		      CommonDAO comObj = new CommonDAOImpl(); 
			    	    		    	 
		    	    		      selAadhaarProfile = (AadhaarProfile)AadhaarUtility.getAadhaarProfileByUID(searchaadhaar.trim()); 
// selAadhaarProfile = (AadhaarProfile)comObj.get101AadhaarProfile(searchaadhaar);  // 101 Case by default.(No service required)
			    	    		      
			    	    		       request.setAttribute("aadhaarTaggedSadaremIDList", comObj.getSADAREMIDByAadhaarID(searchaadhaar));		    	    		       
			    	    		       request.setAttribute("alert_msg", CommonUtility.checkNullObj(selAadhaarProfile.getError_msg()).trim());
			    	    		       request.setAttribute("alert_css", "alert-danger");
			    	    		       request.setAttribute("searchaadhaar", searchaadhaar);
			    	    		       
			    	    		       session.setAttribute("AadhaarProfileData",selAadhaarProfile);
	    	    		    	 }

	    	    		    }
	    	    		    else
	    	    		    {
	    	    		    	request.setAttribute("alert_msg", "Please enter valid Aadhaar ID.");
	    	    		    	request.setAttribute("alert_css", "alert-danger");
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
	
	

	
	public ActionForward loadpartawithaadhaarentry(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "searchpartaentrybyaadharPage";
	     
	    	   HttpSession session = request.getSession(true);

	           String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	           String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
	           
	    	       try
	    	       { 
	    	    	   if(!sesUserId.equals(""))
	    	    	   {  

	    		           String reqFormId			= CommonUtility.checkNullObj(request.getParameter("reqFormId"));
	    		           String sesFormId			= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 
	    		           String selAadhaarId 		= CommonUtility.checkNullObj(request.getParameter("selAadhaarId"));
	    		           String AaadhaarStatus	= CommonUtility.checkNullObj(request.getParameter("AaadhaarStatus"));

	    		            session.setAttribute("sesFormId", "sesFormId"+Math.random());

    			            session.setAttribute("ses_AadhaarId", "");
    			            session.removeAttribute("ses_AadhaarId");
    			            
	    	    		    if(reqFormId.equals(sesFormId) && CommonValidators.AadhaarNumberValidator(selAadhaarId)==true && AaadhaarStatus.equals("100"))
	    	    		    {  
	    	    		    	 CommonDAO comObj			   = new CommonDAOImpl(); 
	    	    		    	 CommonIssueTrackingDAO tktObj = new CommonIssueTrackingDAOImpl(); 
	    	    		         CommonReportsDAO mstObj 	   = new CommonReportsDAOImpl();
	    	    		    	 
	    	    		    	 if(((ArrayList)comObj.getSADAREMIDByAadhaarID(selAadhaarId)).size()==0)
	    	    		    	 {
			   	    		         	request.setAttribute("campList", comObj.getCampListByDistrictID(sesDistId));
	    	    		    			request.setAttribute("genderList",comObj.getGenderList());
	    	    			            request.setAttribute("EducationList", mstObj.getEducationList());
	    	    			            request.setAttribute("EmploymentList", mstObj.getEmploymentList());
	    	    			            request.setAttribute("CasteList", mstObj.getCasteList());
	    	    			            request.setAttribute("MaritalStatusList", comObj.getMaritalList());
	    	    			            request.setAttribute("religionList", comObj.getReligionList());
	    	    			            request.setAttribute("proofTypeList", tktObj.getproofdoucments());
	    	    			            request.setAttribute("rationCardTypeList", comObj.getRationCardTypeList());
	    	    			            request.setAttribute("pensionTypeList", comObj.getPentionTypeList());   
	    	    			          
	    	    			            request.setAttribute("mandalList", comObj.getMandalListByDistrictID(sesDistId));
	    	    			            session.setAttribute("ses_AadhaarId", selAadhaarId);
	    	    			            target="loadpartaformentryopenPage";
	    	    		    	 }
	    	    		    	 else
	    	    		    	 {
	    	    		    		request.setAttribute("alert_msg", "Aadhaar ID already tagged to SADAREM ID.");
	 	    	    		    	request.setAttribute("alert_css", "alert-danger");
	    	    		    	 } 	    		         
	    	    		    }
	    	    		    else
	    	    		    {
	    	    		    	request.setAttribute("alert_msg", "Please enter valid Aadhaar ID.");
	    	    		    	request.setAttribute("alert_css", "alert-danger");
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
	

	
	public ActionForward logpartadetailsbyaadhaar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadpartaformentryopenPage";
	     
	    	   HttpSession session = request.getSession(true);

	           String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	           String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
	           String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId"));
	           String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 
	           String selAadhaarId	= CommonUtility.checkNullObj(session.getAttribute("ses_AadhaarId"));
	           
	    	       try
	    	       { 
	    	    	   if(!sesUserId.equals(""))
	    	    	   {  
	    	    		   HashMap formDataList = RequestUtility.getReqParamList(request, session, false, false, ""); 
	    	    		   
	    		           String reqFormId			= CommonUtility.getStringFromFileItem((FileItem)formDataList.get("reqFormId")); 
	    		           session.setAttribute("sesFormId", "sesFormId"+Math.random());
	    		           
	    	    		    if(reqFormId.equals(sesFormId) && CommonValidators.AadhaarNumberValidator(selAadhaarId)==true)
	    	    		    {  
	    	    		    	 CommonDAO comObj	 			 = new CommonDAOImpl();
	    	    		    	 WithoutProofPartADAO withoutObj = new WithoutProofPartADAOImpl();
	    	    		    	 
	    	    		    	 if(((ArrayList)comObj.getSADAREMIDByAadhaarID(selAadhaarId)).size()==0)
	    	    		    	 {
	    	    		    		 if(withoutObj.logPartADtlsWithAadhaar(request, formDataList,sesDistId,selAadhaarId,sesUserId))
	    	    		    		 {
	    	    		    			 target="searchpartaentrybyaadharPage";
	    	    		    		 }
	    	    		    		 else
	    	    		    		 {  
	    	    	    		    	 CommonIssueTrackingDAO tktObj = new CommonIssueTrackingDAOImpl(); 
	    	    	    		         CommonReportsDAO mstObj 	   = new CommonReportsDAOImpl();
	    	    		    			 
	    	    		                request.setAttribute("PartAformEnteredDataList", formDataList);
			   	    		         	request.setAttribute("campList", comObj.getCampListByDistrictID(sesDistId));
	    	    		            	request.setAttribute("genderList",comObj.getGenderList());
	    	    			            request.setAttribute("EducationList", mstObj.getEducationList());
	    	    			            request.setAttribute("EmploymentList", mstObj.getEmploymentList());
	    	    			            request.setAttribute("CasteList", mstObj.getCasteList());
	    	    			            request.setAttribute("MaritalStatusList", comObj.getMaritalList());
	    	    			            request.setAttribute("religionList", comObj.getReligionList());
	    	    			            request.setAttribute("relationTypeList", comObj.getRelationDetails(CommonUtility.getStringFromFileItem((FileItem)formDataList.get("gender"))));
	    	    			            request.setAttribute("proofTypeList", tktObj.getproofdoucments());
	    	    			            request.setAttribute("rationCardTypeList", comObj.getRationCardTypeList());
	    	    			            request.setAttribute("pensionTypeList", comObj.getPentionTypeList());
	    	    			            

	    	    			            request.setAttribute("mandalList", comObj.getMandalListByDistrictID(sesDistId));
	    	    			            request.setAttribute("panchayatList", comObj.getpanchayatListByDistrictIDMandalIDVillageID(sesDistId, CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mandals"))));
	    	    			            request.setAttribute("villageList", comObj.getVillageListByDistrictIDMandalIDPanchayatId(sesDistId, CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mandals")), CommonUtility.getStringFromFileItem((FileItem)formDataList.get("panchayats"))));
	    	    			            request.setAttribute("habitationList", comObj.getHabitationListByDistrictIDMandalIDvillageID(sesDistId,CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mandals")),  CommonUtility.getStringFromFileItem((FileItem)formDataList.get("villages"))));
	    	    		   				
	    	    			            session.setAttribute("ses_AadhaarId", selAadhaarId);
	    	    			            target="loadpartaformentryopenPage";
	    	    		    		 }
	    	    		    	 }
	    	    		    	 else
	    	    		    	 {
	    	    		    		session.setAttribute("ses_AadhaarId", "");
	    	    			        session.removeAttribute("ses_AadhaarId");
	    		    		        session.removeAttribute("AadhaarProfileData"); 
	    	    		    		request.setAttribute("alert_msg", "Aadhaar ID already tagged to SADAREM ID.");
	 	    	    		    	request.setAttribute("alert_css", "alert-danger");
	 	    	    		    	target="searchpartaentrybyaadharPage";
	    	    		    	 } 	    		         
	    	    		    }
	    	    		    else
	    	    		    {
	    	    		    	session.setAttribute("ses_AadhaarId", "");
	      			            session.removeAttribute("ses_AadhaarId");
    		    		        session.removeAttribute("AadhaarProfileData"); 
	      			            
	    	    		    	request.setAttribute("alert_msg", "Please enter valid Aadhaar ID.Do not refresh the page.");
	    	    		    	request.setAttribute("alert_css", "alert-danger");
 	    	    		    	target="searchpartaentrybyaadharPage";
	    	    		    }
	    	    	   } 
			    	   else
			    	   {
   	    		    	session.setAttribute("ses_AadhaarId", "");
  			            session.removeAttribute("ses_AadhaarId");
	    		        session.removeAttribute("AadhaarProfileData");
	    		        
				    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request as session expired.<br>Please login again."); 
				    	   target="exceptionPage";
			    	   } 
			       }
			       catch(Exception e)
			       {
   	    		    	session.setAttribute("ses_AadhaarId", "");
  			            session.removeAttribute("ses_AadhaarId");
	    		        session.removeAttribute("AadhaarProfileData");
	    		        
			    	   target="exceptionPage";
			    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
			    	   e.printStackTrace();
			       }
			       
	       

	       return mapping.findForward(target);
   }
	
	public ActionForward loadpartawithoutproofentry(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadpartawithoutproofentryPage";
	     
	    	   HttpSession session = request.getSession(true);
	    	   
	    	   String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	           String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
	           String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId"));
	           String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 
	           String selAadhaarId	= CommonUtility.checkNullObj(session.getAttribute("ses_AadhaarId"));
	           
	    	       try
	    	       { 
	    	    	   if(!sesUserId.equals(""))
	    	    	   {  
	    	    		   
	    	    		 CommonDAO comObj			   = new CommonDAOImpl(); 
  	    		    	 CommonIssueTrackingDAO tktObj = new CommonIssueTrackingDAOImpl(); 
  	    		         CommonReportsDAO mstObj 	   = new CommonReportsDAOImpl();
  	    		         
  	    		         	request.setAttribute("campList", comObj.getCampListByDistrictID(sesDistId));
		    	    		request.setAttribute("genderList",comObj.getGenderList());
	   			            request.setAttribute("EducationList", mstObj.getEducationList());
	   			            request.setAttribute("EmploymentList", mstObj.getEmploymentList());
	   			            request.setAttribute("CasteList", mstObj.getCasteList());
	   			            request.setAttribute("MaritalStatusList", comObj.getMaritalList());
	   			            request.setAttribute("religionList", comObj.getReligionList());
	   			            request.setAttribute("proofTypeList", tktObj.getproofdoucments());
	   			            request.setAttribute("rationCardTypeList", comObj.getRationCardTypeList());
	   			            request.setAttribute("pensionTypeList", comObj.getPentionTypeList());   
	   			          
	   			            request.setAttribute("mandalList", comObj.getMandalListByDistrictID(sesDistId));
	    	    		   
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
	
	

	public ActionForward logpartawithoutproofreqform(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "loadpartawithoutproofentryPage";
	     
	    	   HttpSession session = request.getSession(true);

	           String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	           String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
	           String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId"));
	           String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId"));  
	           
	    	       try
	    	       { 
	    	    	   if(!sesUserId.equals(""))
	    	    	   {  
	    	    		   HashMap formDataList = RequestUtility.getReqParamList(request, session, false, false, ""); 
	    	    		   
	    		           String reqFormId			= CommonUtility.getStringFromFileItem((FileItem)formDataList.get("reqFormId")); 
	    		           session.setAttribute("sesFormId", "sesFormId"+Math.random());
	    		           String aadhaar_proof_id = "";
	    		           
	    		           if(formDataList.containsKey("aadhaar_proof_id"))
	    		           {
	    		        	   aadhaar_proof_id = CommonUtility.getStringFromFileItem((FileItem)formDataList.get("aadhaar_proof_id")); 
	    		           }
	    		           
	    	    		    if(reqFormId.equals(sesFormId))
	    	    		    {  

	    	    		    	 CommonDAO comObj	 			 = new CommonDAOImpl();
	    	    		    	 WithoutProofPartADAO withoutObj = new WithoutProofPartADAOImpl();
	    	    		    	 
	    	    		    	if(!aadhaar_proof_id.equals("") && aadhaar_proof_id.length()==12 && CommonValidators.AadhaarNumberValidator(aadhaar_proof_id)==false)
	    	    		    	{ 
	    	    		    		request.setAttribute("alert_msg", "Please provide valid Aadhaar Id.");
	 	    	    		    	request.setAttribute("alert_css", "alert-danger");
	 	    	    		    	return loadpartawithoutproofentry(mapping, form, request, response);
	    	    		    	}
	    	    		    	else if(!aadhaar_proof_id.equals("") && aadhaar_proof_id.length()==12 && ((ArrayList)comObj.getSADAREMIDByAadhaarID(aadhaar_proof_id)).size()>0)
	    	    		    	{ 
	    	    		    		request.setAttribute("alert_msg", "Aadhaar ID already tagged to SADAREM ID.");
	 	    	    		    	request.setAttribute("alert_css", "alert-danger");
	 	    	    		    	return loadpartawithoutproofentry(mapping, form, request, response);	
	    	    		    	}
	    	    		    	else if(withoutObj.logPartARequestFormWithOutProof(request, formDataList,sesDistId,aadhaar_proof_id,sesUserId))
	    	    		    	{   
    	    		    			 target="ViewRequestDetailsPrintPage";
	    	    		    	}
		    		    		else
		    		    		{  
		    	    		    	 CommonIssueTrackingDAO tktObj = new CommonIssueTrackingDAOImpl(); 
		    	    		         CommonReportsDAO mstObj 	   = new CommonReportsDAOImpl();

		   	    		         	request.setAttribute("campList", comObj.getCampListByDistrictID(sesDistId));
		    		                request.setAttribute("PartAformEnteredDataList", formDataList);
		    		            	request.setAttribute("genderList",comObj.getGenderList());
		    			            request.setAttribute("EducationList", mstObj.getEducationList());
		    			            request.setAttribute("EmploymentList", mstObj.getEmploymentList());
		    			            request.setAttribute("CasteList", mstObj.getCasteList());
		    			            request.setAttribute("MaritalStatusList", comObj.getMaritalList());
		    			            request.setAttribute("religionList", comObj.getReligionList());
		    			            request.setAttribute("relationTypeList", comObj.getRelationDetails(CommonUtility.getStringFromFileItem((FileItem)formDataList.get("gender"))));
		    			            request.setAttribute("proofTypeList", tktObj.getproofdoucments());
		    			            request.setAttribute("rationCardTypeList", comObj.getRationCardTypeList());
		    			            request.setAttribute("pensionTypeList", comObj.getPentionTypeList());
		    			            
	
		    			            request.setAttribute("mandalList", comObj.getMandalListByDistrictID(sesDistId));
		    			            request.setAttribute("panchayatList", comObj.getpanchayatListByDistrictIDMandalIDVillageID(sesDistId, CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mandals"))));
		    			            request.setAttribute("villageList", comObj.getVillageListByDistrictIDMandalIDPanchayatId(sesDistId, CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mandals")), CommonUtility.getStringFromFileItem((FileItem)formDataList.get("panchayats"))));
		    			            request.setAttribute("habitationList", comObj.getHabitationListByDistrictIDMandalIDvillageID(sesDistId,CommonUtility.getStringFromFileItem((FileItem)formDataList.get("mandals")),  CommonUtility.getStringFromFileItem((FileItem)formDataList.get("villages"))));
		    		   				  
		    		    		 }
	    	    		    	  		         
	    	    		    }
	    	    		    else
	    	    		    { 
	    	    		    	request.setAttribute("alert_msg", "Invalid form request. Please do not refresh the page.");
	    	    		    	request.setAttribute("alert_css", "alert-danger"); 
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
			    	   request.setAttribute("errorMessage", "Sorry we are not able to process your request. Please try again!.");
			    	   target="exceptionPage";
			    	   e.printStackTrace();
			       }
			         
	       return mapping.findForward(target);
   }
	
	
	public ActionForward loadpendingrequestforpartadtls(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    { 
		  String target = "loadpendingrequestPage";
		  
	  	   HttpSession session = request.getSession(true);
	
	       String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid")); 
	       String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId"));
	       String sesroleId		= CommonUtility.checkNullObj(session.getAttribute("roleId"));
       
  	       try
  	       { 
  	    	   if(!sesUserId.equals(""))
		    	   {   
  	    		   		if(sesroleId.equals("4"))
  	    		   		{
	  	    		   		String request_type	= CommonUtility.checkNullObj(request.getParameter("request_type"));
		  	    		  	String start_date 	= CommonUtility.checkNullObj(request.getParameter("start_date"));
		  	    		  	String end_date 	= CommonUtility.checkNullObj(request.getParameter("end_date"));

	  	    		   		String ses_request_type	= CommonUtility.checkNullObj(session.getAttribute("ses_request_type"));
		  	    		  	String ses_start_date 	= CommonUtility.checkNullObj(session.getAttribute("ses_start_date"));
		  	    		  	String ses_end_date 	= CommonUtility.checkNullObj(session.getAttribute("ses_end_date"));

		  	    		  	 session.setAttribute("ses_request_type","");
			  	    		 session.setAttribute("ses_start_date","");
			  	    		 session.setAttribute("ses_end_date","");
		  	    		  	
		  	    		  	if(request_type.equals("") && start_date.equals("") &&  end_date.equals(""))
		  	    		  	{
			  	    		  	if(!ses_request_type.equals(""))
			  	    		  	{
			  	    		  		request_type = ses_request_type;
			  	    		  	}
	
			  	    		  	if(start_date.equals("") && !ses_start_date.equals(""))
			  	    		  	{
			  	    		  		start_date = ses_start_date;
			  	    		  	}
	
			  	    		  	if(end_date.equals("") && !ses_end_date.equals(""))
			  	    		  	{
			  	    		  		end_date = ses_end_date;
			  	    		  	}
		  	    		  	}
		  	    		  	
  	    		   			
						       WithoutProofPartADAO obj = new WithoutProofPartADAOImpl();
						       request.setAttribute("pendingRequestList", obj.getPartARequestDetailsListByCampId(sesCampId,request_type,start_date,end_date));
					    	   session.setAttribute("sesFormId", "sesFormId"+Math.random());

					    	   request.setAttribute("request_type", request_type);
					    	   request.setAttribute("start_date", start_date);
					    	   request.setAttribute("end_date", end_date);
					    	   
					    	   
					    	 session.setAttribute("ses_request_type",request_type);
			  	    		 session.setAttribute("ses_start_date",start_date);
			  	    		 session.setAttribute("ses_end_date",end_date);
			  	    		  	
  	    		   		}
  	    		   		else
  	    		   		{
  				    	   request.setAttribute("errorMessage", "Sorry your are not authorized to access this service."); 
  				    	   target="exceptionPage";
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
	
	public ActionForward loadrequestdtlsforaction(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    { 
		  String target = "loadpendingrequestPage";
		  
	  	   HttpSession session = request.getSession(true);


           String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
           String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
           String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId")); 
	       String sesRoleId		= CommonUtility.checkNullObj(session.getAttribute("roleId"));
           String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 
       
  	       try
  	       { 
  	    	   if(!sesUserId.equals(""))
		    	   {   
  	    		   
  	    		   		CommonDAO comObj			   = new CommonDAOImpl(); 
  	    		   		CommonIssueTrackingDAO tktObj  = new CommonIssueTrackingDAOImpl(); 
  	    		   		WithoutProofPartADAO obj 	   = new WithoutProofPartADAOImpl(); 
	    		         CommonReportsDAO mstObj 	   = new CommonReportsDAOImpl();
  	    		   		
  	    		   		if(sesRoleId.equals("4"))
  	    		   		{
  	    		   			String selRequestId = CommonUtility.checkNullObj(request.getParameter("request_id"));
  	    		   			String reqFormId	= CommonUtility.checkNullObj(request.getParameter("reqFormId"));

					    	 session.setAttribute("sesFormId", "sesFormId"+Math.random());
					    	 session.removeAttribute("ses_request_id");
					    	  
  	    		   			if(reqFormId.equals(sesFormId))
  	    		   			{ 
  	    		   				HashMap memberDetailsList = (HashMap)obj.getPartAFormDetailsByRequestId(selRequestId);
  	    		   				  
	  	    		   			request.setAttribute("genderList",comObj.getGenderList());
	    			            request.setAttribute("EducationList", mstObj.getEducationList());
	    			            request.setAttribute("EmploymentList", mstObj.getEmploymentList());
	    			            request.setAttribute("CasteList", mstObj.getCasteList());
	    			            request.setAttribute("MaritalStatusList", comObj.getMaritalList());
	    			            request.setAttribute("religionList", comObj.getReligionList());
	    			            request.setAttribute("relationTypeList", comObj.getRelationDetails(CommonUtility.checkNullObj(memberDetailsList.get("gender_id"))));
	    			            request.setAttribute("proofTypeList", tktObj.getproofdoucments());
	    			            request.setAttribute("rationCardTypeList", comObj.getRationCardTypeList());
	    			            request.setAttribute("pensionTypeList", comObj.getPentionTypeList());
	    			            

	    			            request.setAttribute("mandalList", comObj.getMandalListByDistrictID(sesDistId));
	    			            request.setAttribute("panchayatList", comObj.getpanchayatListByDistrictIDMandalIDVillageID(sesDistId, CommonUtility.checkNullObj(memberDetailsList.get("mandal_id"))));
	    			            request.setAttribute("villageList", comObj.getVillageListByDistrictIDMandalIDPanchayatId(sesDistId,  CommonUtility.checkNullObj(memberDetailsList.get("mandal_id")),  CommonUtility.checkNullObj(memberDetailsList.get("panchayath_id"))));
	    			            request.setAttribute("habitationList", comObj.getHabitationListByDistrictIDMandalIDvillageID(sesDistId, CommonUtility.checkNullObj(memberDetailsList.get("mandal_id")),  CommonUtility.checkNullObj(memberDetailsList.get("village_id"))));
	    		   				
	    			            request.setAttribute("partAProofList", obj.getPartAProofDetailsList(selRequestId));
	    			            request.setAttribute("PartARequestFullDltsList",memberDetailsList);
	    		   				session.setAttribute("ses_request_id",selRequestId);
  	    		   				
   	  				    	    target = "PartARequestFormDecisionPage";
  	    		   			}
  	    		   			else
  	    		   			{
 						       request.setAttribute("pendingRequestList", obj.getPartARequestDetailsListByCampId(sesCampId,"","",""));
  	  				    	   request.setAttribute("alert_msg", "Please do not refresh the page."); 
  	  				    	   request.setAttribute("alert_css", "alert-danger");
  	  				    	   target = "loadpendingrequestPage";
  	    		   			}
  	    		   		}
  	    		   		else
  	    		   		{
  				    	   request.setAttribute("errorMessage", "Sorry your are not authorized to access this service."); 
  				    	   target="exceptionPage";
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
	
	
	public ActionForward logupdaterequestdtls(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target = "loadpendingrequestPage";
		  
	  	   HttpSession session = request.getSession(true);


        String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
        String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
        String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId")); 
	    String sesRoleId		= CommonUtility.checkNullObj(session.getAttribute("roleId"));
        String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 
        String ses_request_id	= CommonUtility.checkNullObj(session.getAttribute("ses_request_id"));
	       try
	       { 
	    	   if(!sesUserId.equals(""))
		    	   {      
	    		   		if(sesRoleId.equals("4"))
	    		   		{
	    		   			String reqFormId		= CommonUtility.checkNullObj(request.getParameter("reqFormId")); 
	    		   			

					    	 session.setAttribute("sesFormId", "sesFormId"+Math.random());
					    	 session.removeAttribute("ses_request_id");

	    		   				CommonDAO comObj			   = new CommonDAOImpl(); 
	      	    		   		CommonIssueTrackingDAO tktObj  = new CommonIssueTrackingDAOImpl(); 
	      	    		   		WithoutProofPartADAO obj 	   = new WithoutProofPartADAOImpl(); 
	    	    		        CommonReportsDAO mstObj 	   = new CommonReportsDAOImpl();
	    	    		        
	    		   			if(reqFormId.equals(sesFormId))
	    		   			{   
	    		   				
	    		   				
  	    		   				HashMap memberDetailsList = (HashMap)obj.getPartAFormDetailsByRequestId(ses_request_id);
  	    		   				
	  	    		   			if(!CommonUtility.checkNullObj(memberDetailsList.get("camp_id")).equals(sesCampId))
	  	    		   			{  
	  	    		   			   request.setAttribute("alert_msg", "Your are not authorized perform this action. As this request belongs to \""+CommonUtility.checkNullObj(memberDetailsList.get("camp_name"))+"\" camp"); 
		  				    	   request.setAttribute("alert_css", "alert-danger");

	  						       request.setAttribute("pendingRequestList", obj.getPartARequestDetailsListByCampId(sesCampId,"","",""));
	  	  				    	   target = "loadpendingrequestPage";
	  	    		   			}
	  	    		   			else
	  	    		   			{   
		    		   				obj.logPartARequestUpdate(request, ses_request_id, sesDistId, sesCampId, sesUserId);		// Update details
		    		   				
		    		   				memberDetailsList = (HashMap)obj.getPartAFormDetailsByRequestId(ses_request_id);
	  	    		   				  
		  	    		   			request.setAttribute("genderList",comObj.getGenderList());
		    			            request.setAttribute("EducationList", mstObj.getEducationList());
		    			            request.setAttribute("EmploymentList", mstObj.getEmploymentList());
		    			            request.setAttribute("CasteList", mstObj.getCasteList());
		    			            request.setAttribute("MaritalStatusList", comObj.getMaritalList());
		    			            request.setAttribute("religionList", comObj.getReligionList());
		    			            request.setAttribute("relationTypeList", comObj.getRelationDetails(CommonUtility.checkNullObj(memberDetailsList.get("gender_id"))));
		    			            request.setAttribute("proofTypeList", tktObj.getproofdoucments());
		    			            request.setAttribute("rationCardTypeList", comObj.getRationCardTypeList());
		    			            request.setAttribute("pensionTypeList", comObj.getPentionTypeList());

		    			            request.setAttribute("mandalList", comObj.getMandalListByDistrictID(sesDistId));
		    			            request.setAttribute("panchayatList", comObj.getpanchayatListByDistrictIDMandalIDVillageID(sesDistId, CommonUtility.checkNullObj(memberDetailsList.get("mandal_id"))));
		    			            request.setAttribute("villageList", comObj.getVillageListByDistrictIDMandalIDPanchayatId(sesDistId,  CommonUtility.checkNullObj(memberDetailsList.get("mandal_id")),  CommonUtility.checkNullObj(memberDetailsList.get("panchayath_id"))));
		    			            request.setAttribute("habitationList", comObj.getHabitationListByDistrictIDMandalIDvillageID(sesDistId, CommonUtility.checkNullObj(memberDetailsList.get("mandal_id")),  CommonUtility.checkNullObj(memberDetailsList.get("village_id"))));
		    		   				
		    			            request.setAttribute("PartARequestFullDltsList",memberDetailsList);
		    		   				session.setAttribute("ses_request_id",ses_request_id);
	  	    		   				
	   	  				    	    target = "PartARequestFormDecisionPage";
		    		   				
	  	    		   			}
	    		   			}
	    		   			else
	    		   			{ 
	  				    	   request.setAttribute("alert_msg", "Please do not refresh the page."); 
	  				    	   request.setAttribute("alert_css", "alert-danger");

  						       request.setAttribute("pendingRequestList", obj.getPartARequestDetailsListByCampId(sesCampId,"","",""));
  	  				    	   target = "loadpendingrequestPage";
	    		   			}
 
	    		   		}
	    		   		else
	    		   		{
				    	   request.setAttribute("errorMessage", "Sorry your are not authorized to access this service."); 
				    	   target="exceptionPage";
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
	

	public ActionForward logrequestrejection(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target = "loadpendingrequestPage";
		  
	  	   HttpSession session = request.getSession(true);


        String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
        String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
        String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId")); 
	    String sesRoleId		= CommonUtility.checkNullObj(session.getAttribute("roleId"));
        String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 
        String ses_request_id	= CommonUtility.checkNullObj(session.getAttribute("ses_request_id"));
	       try
	       { 
	    	   if(!sesUserId.equals(""))
		    	   {    
	    		   		WithoutProofPartADAO obj 	   = new WithoutProofPartADAOImpl();  
	    		   		
	    		   		if(sesRoleId.equals("4"))
	    		   		{
	    		   			String reqFormId		= CommonUtility.checkNullObj(request.getParameter("reqFormId"));
	    		   			String status_remarks 	= CommonUtility.checkNullObj(request.getParameter("status_remarks"));
	    		   			String term_status		= CommonUtility.checkNullObj(request.getParameter("term_status"));
	    		   			

					    	 session.setAttribute("sesFormId", "sesFormId"+Math.random());
					    	 session.removeAttribute("ses_request_id");
					    	  
	    		   			if(reqFormId.equals(sesFormId))
	    		   			{   
  	    		   				HashMap memberDetailsList = (HashMap)obj.getPartAFormDetailsByRequestId(ses_request_id);
  	    		   				
	  	    		   			if(!CommonUtility.checkNullObj(memberDetailsList.get("camp_id")).equals(sesCampId))
	  	    		   			{ 
	  	    		   			   request.setAttribute("alert_msg", "Your are not authorized perform this action. As this request belongs to \""+CommonUtility.checkNullObj(memberDetailsList.get("camp_name"))+"\" camp"); 
		  				    	   request.setAttribute("alert_css", "alert-danger");
	  	    		   			}
	  	    		   			else
	  	    		   			{
		    		   				obj.logPartARejectRequest(request, ses_request_id, status_remarks, term_status, sesUserId);
	  	    		   			}
	    		   			}
	    		   			else
	    		   			{ 
	  				    	   request.setAttribute("alert_msg", "Please do not refresh the page."); 
	  				    	   request.setAttribute("alert_css", "alert-danger");
	    		   			}
 
					       request.setAttribute("pendingRequestList", obj.getPartARequestDetailsListByCampId(sesCampId,"","",""));
  				    	   target = "loadpendingrequestPage";
	    		   		}
	    		   		else
	    		   		{
				    	   request.setAttribute("errorMessage", "Sorry your are not authorized to access this service."); 
				    	   target="exceptionPage";
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
	
	
	public ActionForward logrequestapproval(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target = "loadpendingrequestPage";
		  
	  	   HttpSession session = request.getSession(true);


        String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
        String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
        String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId")); 
	    String sesRoleId		= CommonUtility.checkNullObj(session.getAttribute("roleId"));
        String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 
        String ses_request_id	= CommonUtility.checkNullObj(session.getAttribute("ses_request_id"));
	       try
	       { 
	    	   if(!sesUserId.equals(""))
		    	   {    
	    		   		WithoutProofPartADAO obj 	   = new WithoutProofPartADAOImpl();  
	    		   		
	    		   		if(sesRoleId.equals("4"))
	    		   		{
	    		   			String reqFormId		= CommonUtility.checkNullObj(request.getParameter("reqFormId"));
	    		   			String status_remarks 	= CommonUtility.checkNullObj(request.getParameter("status_remarks"));
	    		   			String term_status		= CommonUtility.checkNullObj(request.getParameter("term_status"));
	    		   			

					    	 session.setAttribute("sesFormId", "sesFormId"+Math.random());
					    	 session.removeAttribute("ses_request_id");
					    	  
	    		   			if(reqFormId.equals(sesFormId))
	    		   			{   
  	    		   				HashMap memberDetailsList = (HashMap)obj.getPartAFormDetailsByRequestId(ses_request_id);
  	    		   				
	  	    		   			if(!CommonUtility.checkNullObj(memberDetailsList.get("camp_id")).equals(sesCampId))
	  	    		   			{ 
	  	    		   			   request.setAttribute("alert_msg", "Your are not authorized perform this action. As this request belongs to \""+CommonUtility.checkNullObj(memberDetailsList.get("camp_name"))+"\" camp"); 
		  				    	   request.setAttribute("alert_css", "alert-danger");
	  	    		   			}
	  	    		   			else
	  	    		   			{
		    		   				if(obj.logPartARequestApproval(request, ses_request_id, sesDistId, CommonUtility.checkNullObj(memberDetailsList.get("mandal_id")), CommonUtility.checkNullObj(memberDetailsList.get("village_id")), status_remarks, term_status, CommonUtility.checkNullObj(memberDetailsList.get("profile_pic_path")), sesUserId)==true)
		    		   				{
		    		   					request.setAttribute("PartARequestFullDltsList",(HashMap)obj.getPartAFormDetailsByRequestId(ses_request_id));
		    		   					target = "ViewRequestDetailsPrintPage";
		    		   				}
		    		   				else
		    		   				{ 
	    						       request.setAttribute("pendingRequestList", obj.getPartARequestDetailsListByCampId(sesCampId,"","",""));
	    	  				    	   target = "loadpendingrequestPage";
		    		   				}
	  	    		   			}
	    		   			}
	    		   			else
	    		   			{  
	 					       request.setAttribute("pendingRequestList", obj.getPartARequestDetailsListByCampId(sesCampId,"","",""));
	   				    	   target = "loadpendingrequestPage";
	    		   				
	  				    	   request.setAttribute("alert_msg", "Please do not refresh the page."); 
	  				    	   request.setAttribute("alert_css", "alert-danger");
	    		   			}
 
	    		   		}
	    		   		else
	    		   		{ 
				    	   request.setAttribute("errorMessage", "Sorry your are not authorized to access this service."); 
				    	   target="exceptionPage";
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
	

	public ActionForward loadmyreqsatusdtls(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target = "PartARequestStatusByUserPage";
		 
	  	   HttpSession session = request.getSession(true);


	     String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	     String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
	     String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId")); 
		 String sesRoleId		= CommonUtility.checkNullObj(session.getAttribute("roleId"));
	     String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId"));  
			
			try
			{

			     String reqFormId	= CommonUtility.checkNullObj(request.getParameter("reqFormId"));
			     String start_date	= CommonUtility.checkNullObj(request.getParameter("start_date"));
			     String end_date	= CommonUtility.checkNullObj(request.getParameter("end_date"));
			     String request_id	= CommonUtility.checkNullObj(request.getParameter("request_id"));
			     String proof_id	= CommonUtility.checkNullObj(request.getParameter("proof_id")); 
				 
				session.setAttribute("sesFormId", "sesFormId"+Math.random());
				
				if(sesRoleId.equals("3") || sesRoleId.equals("96"))
  		   		{
					if(!reqFormId.equals("") && reqFormId.equals(sesFormId))
					{
						if(!start_date.equals("") && !end_date.equals("") && CommonValidators.validateBetweenTwoDateTimes(start_date, "dd/MM/yyyy", end_date, "dd/MM/yyyy", 0, 0)==false)
						{
							request.setAttribute("alert_msg", "Please select valid from and to dates.");
		    		    	request.setAttribute("alert_css", "alert-danger");
						}
						else if(!request_id.equals("") && ( CommonValidators.NumberValidator(request_id)==false || request_id.length()<10))
						{
							request.setAttribute("alert_msg", "Please enter valid Request Id.");
		    		    	request.setAttribute("alert_css", "alert-danger");
						}
						else if(!proof_id.equals("") && CommonValidators.AadhaarNumberValidator(proof_id)==false)
						{
							request.setAttribute("alert_msg", "Please enter valid Aadhaar Id.");
		    		    	request.setAttribute("alert_css", "alert-danger");
						}
						else if((!start_date.equals("") && !end_date.equals("")) || !request_id.equals("") || !proof_id.equals(""))
						{
							WithoutProofPartADAO obj = new WithoutProofPartADAOImpl();
							
							String selectionTitle = "";

							if(!start_date.equals("") && !end_date.equals(""))
							{
								selectionTitle ="<b>From Date :</b> "+start_date+" <b>To Date :</b> "+end_date;
							}

							if(!request_id.equals(""))
							{
								selectionTitle ="<b>Request ID :</b> "+request_id;
							}
							
							if(!proof_id.equals(""))
							{
								selectionTitle ="<b>Aadhaar ID :</b> "+proof_id;
							}
							
							
							
							ArrayList requestStatusList = (ArrayList)obj.getPartARequesStatustDetailsListByLoginId(sesUserId, request_id, proof_id, start_date, end_date);
							
							request.setAttribute("selectionTitle", selectionTitle);
							request.setAttribute("requestStatusList", requestStatusList);
						
						}
						else
						{
							request.setAttribute("alert_msg", "Please select atleast one option and click on search button.");
		    		    	request.setAttribute("alert_css", "alert-danger");
						}
							
					}
					else if(!reqFormId.equals(""))
					{
						request.setAttribute("alert_msg", "Please do not refresh the page.");
	    		    	request.setAttribute("alert_css", "alert-danger"); 
					}
  		   		}
				else
				{
					 request.setAttribute("errorMessage", "Sorry your are not authorized to access this service."); 
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
	
	
	public ActionForward partarequestviewprint(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target = "PartARequestViewPrintPage";
		  
	  	   HttpSession session = request.getSession(true);


        String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
        String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
        String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId")); 
	    String sesRoleId		= CommonUtility.checkNullObj(session.getAttribute("roleId"));
        String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId")); 
        
	       try
	       { 
	    	   if(!sesUserId.equals(""))
		    	   {    
	    		   		WithoutProofPartADAO obj 	   = new WithoutProofPartADAOImpl();  
	    		   		

    		   			String reqFormId		= CommonUtility.checkNullObj(request.getParameter("reqFormId"));  
    		   			String request_id		= CommonUtility.checkNullObj(request.getParameter("request_id"));   
	    		   			  
    		   		//	System.out.println("reqFormId : "+reqFormId+"\nrequest_id : "+request_id.replace("@^", "+"));
    		   			
					    	   
	    		   			if(reqFormId.equals(sesFormId))
	    		   			{
	    		   				String decry_req_id = PasswordEncriptDecrypt.decrypt(request_id.replace("@^", "+")); 
	    		   				
	    		   				//System.out.println("decry_req_id : "+decry_req_id);
	    		   				
  	    		   				HashMap memberDetailsList = (HashMap)obj.getPartAFormDetailsByRequestId(decry_req_id);
  	    		   				
	  	    		   			if(!CommonUtility.checkNullObj(memberDetailsList.get("camp_id")).equals(sesCampId) && !CommonUtility.checkNullObj(memberDetailsList.get("login_id")).equals(sesUserId))
	  	    		   			{ 
	  	    		   			   request.setAttribute("alert_msg", "Your are not authorized perform this action. As this request belongs to \""+CommonUtility.checkNullObj(memberDetailsList.get("camp_name"))+"\" camp"); 
		  				    	   request.setAttribute("alert_css", "alert-danger");
	  	    		   			}
	  	    		   			else
	  	    		   			{ 
	    		   					request.setAttribute("PartARequestFullDltsList",(HashMap)obj.getPartAFormDetailsByRequestId(decry_req_id));
	  	    		   			}
	    		   			}
	    		   			else
	    		   			{ 
	  				    	   request.setAttribute("alert_msg", "Please do not refresh the page."); 
	  				    	   request.setAttribute("alert_css", "alert-danger");
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
		 
	
	public ActionForward loadpartarequestglancerpt(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
		String target = "PartARequestGlanceRptPage";
		 
	  	   HttpSession session = request.getSession(true);


	     String sesUserId		= CommonUtility.checkNullObj(session.getAttribute("loginid"));
	     String sesDistId		= CommonUtility.checkNullObj(session.getAttribute("districtId"));
	     String sesCampId		= CommonUtility.checkNullObj(session.getAttribute("campId")); 
		 String sesRoleId		= CommonUtility.checkNullObj(session.getAttribute("roleId"));
	     String sesFormId		= CommonUtility.checkNullObj(session.getAttribute("sesFormId"));  
			
			try
			{

			     String reqFormId	= CommonUtility.checkNullObj(request.getParameter("reqFormId"));
			     String start_date	= CommonUtility.checkNullObj(request.getParameter("start_date"));
			     String end_date	= CommonUtility.checkNullObj(request.getParameter("end_date")); 
				 
			     session.setAttribute("sesFormId", "sesFormId"+Math.random());
			 
					if(!reqFormId.equals("") && reqFormId.equals(sesFormId))
					{

						if(start_date.equals("") || end_date.equals("") )
						{
							request.setAttribute("alert_msg", "Please select valid From and To dates.");
		    		    	request.setAttribute("alert_css", "alert-danger");
						}
						else if(!start_date.equals("") && !end_date.equals("") && CommonValidators.validateBetweenTwoDateTimes(start_date, "dd/MM/yyyy", end_date, "dd/MM/yyyy", 0, 0)==false)
						{
							request.setAttribute("alert_msg", "Please select valid From and To dates.");
		    		    	request.setAttribute("alert_css", "alert-danger");
						} 
						else if((!start_date.equals("") && !end_date.equals("")))
						{
							WithoutProofPartADAO obj = new WithoutProofPartADAOImpl();
							
							String selectionTitle = "";

							if(!start_date.equals("") && !end_date.equals(""))
							{
								selectionTitle ="<b>From Date :</b> "+start_date+" <b>To Date :</b> "+end_date;
							}
 
							
							ArrayList requestStatusList = (ArrayList)obj.getPartARequestStatusAtGlance(start_date, end_date);
							
							request.setAttribute("selectionTitle", selectionTitle);
							request.setAttribute("requestStatusList", requestStatusList);
							request.setAttribute("start_date", start_date);
							request.setAttribute("end_date", end_date);
						
						}
						else
						{
							request.setAttribute("alert_msg", "Please select From  and To dates to view details.");
		    		    	request.setAttribute("alert_css", "alert-danger");
						}
							
					}
					else if(!reqFormId.equals(""))
					{
						request.setAttribute("alert_msg", "Please do not refresh the page.");
	    		    	request.setAttribute("alert_css", "alert-danger"); 
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ActionForward getPartADetailsByuid(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
    {
       String target = "partapage";
       
	       WithoutProofPartADAO obj2 = new WithoutProofPartADAOImpl();
		   HttpSession session = null;
		        String districtid   = null;
		        String mandalid     = null;
		        String villageId    = null;
		        String districtName = null;
		        String todate = null;
		        
		        ArrayList mandalList 	= new ArrayList(); 
		        ArrayList panchayatList = new ArrayList();
		        ArrayList villageList 	= new ArrayList();
		        ArrayList habitationList= new ArrayList();
		        
	        session = request.getSession(true);
	        CommonDAO obj= new CommonDAOImpl();
	        CommonDAOImpl comObj = new CommonDAOImpl();
	        try
	        {
	        	    ArrayList resultList = new ArrayList();
	        	    String reqId = (String)CommonUtility.checkNullObj(request.getParameter("reqId"));
		            String aadharId = (String)CommonUtility.checkNullObj(request.getParameter("aadharId"));
	        
		            resultList = obj2.getWithoutProofPartaList(reqId,aadharId);

	            if (session.getAttribute("districtId") != null)
	            {
	                   districtid = (String)((ArrayList)resultList.get(0)).get(4);
	                   mandalid = (String)((ArrayList)resultList.get(0)).get(5);
	                   villageId = (String)((ArrayList)resultList.get(0)).get(7);
	                   mandalList = obj.getMandalListByDistrictID(districtid);
	                   panchayatList = (ArrayList)comObj.getpanchayatListByDistrictIDMandalIDVillageID(districtid, mandalid);
	                   villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(districtid, mandalid);
	                   habitationList = (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(districtid, mandalid, villageId);

	   	           
	                
	                districtName=obj.getDistrictName(districtid);
	         
	                             
	            }
	            
	            ArrayList proofTypeList = new ArrayList();
	            CommonReportsDAO masters = new CommonReportsDAOImpl();
	            
	            ArrayList CasteList = new ArrayList();
	            ArrayList EducationList = new ArrayList();	           
	            ArrayList EmploymentList = new ArrayList();
	            
	            CasteList=masters.getCasteList();
	            EducationList=masters.getEducationList();
	            EmploymentList= masters.getEmploymentList();
	            
	            
	            request.setAttribute("MaritalStatusList", obj.getMaritalList());
	            request.setAttribute("EducationList", EducationList);
	            request.setAttribute("EmploymentList", EmploymentList);
	            request.setAttribute("CasteList", CasteList);
	            request.setAttribute("proofTypeList", proofTypeList);
	              
	            request.setAttribute("districtName", districtName);
	            request.setAttribute("districtid", districtid);
	            request.setAttribute("mandalList", mandalList);
	            request.setAttribute("panchayatList", panchayatList);
	            request.setAttribute("villageList", villageList);
	            request.setAttribute("habitationList", habitationList);
	            request.setAttribute("resultList", resultList);
	            

	            
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	            request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception");
	        }

		return mapping.findForward(target);
       
         
   }
	
	public ActionForward approvePartAform(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) // Old Approval Screen
	{
	String target="partapage";
		
	    String ResultMSG=null;
	    HttpSession session = null;
	    session = request.getSession(true);

	   
	    try{
	    	
	        
	      //Personal Details
	        String aadharCardNo="";
	  	        
	         String loginid = null;	
	    	 String request_id	= CommonUtility.checkNullObj((String)request.getParameter("request_id"));
	         aadharCardNo       = convertToNullWhenNoInput((String)request.getParameter("aadharCardNo"));	
	         loginid = (String) session.getAttribute("loginid");
	        
		        //update part-A details
		        WithoutProofPartADAO obj2 = new WithoutProofPartADAOImpl();   
    
		        
				
		        String districtid 		= null;
		        String mandalid 		= null;
		        String villageId 		= null;
		        String districtName 	= null;
		       
		        
		        ArrayList mandalList 	= new ArrayList(); 
		        ArrayList panchayatList = new ArrayList();
		        ArrayList villageList 	= new ArrayList();
		        ArrayList habitationList= new ArrayList();
		        
	        session = request.getSession(true);
	        CommonDAO obj= new CommonDAOImpl();
	        CommonDAOImpl comObj = new CommonDAOImpl();
	       
	        	 ArrayList resultList = new ArrayList();
	        
		            resultList = obj2.getWithoutProofPartaList(request_id,aadharCardNo);

	            if (session.getAttribute("districtId") != null)
	            {
	                   districtid = (String)((ArrayList)resultList.get(0)).get(4);
	                   mandalid = (String)((ArrayList)resultList.get(0)).get(5);
	                   villageId = (String)((ArrayList)resultList.get(0)).get(7);   
	                   mandalList = obj.getMandalListByDistrictID(districtid);         
	                   panchayatList = (ArrayList)comObj.getpanchayatListByDistrictIDMandalIDVillageID(districtid, mandalid);
	                   villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(districtid, mandalid);
	                   habitationList = (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(districtid, mandalid, villageId);
	                
	                
	                districtName=obj.getDistrictName(districtid);
	         
	                             
	            }
	            

	            CommonReportsDAO masters = new CommonReportsDAOImpl();
	            
	            ArrayList CasteList = new ArrayList();
	            ArrayList EducationList = new ArrayList();	           
	            ArrayList EmploymentList = new ArrayList();
	            
	            CasteList=masters.getCasteList();
	            EducationList=masters.getEducationList();
	            EmploymentList= masters.getEmploymentList();

	            request.setAttribute("EducationList", EducationList);
	            request.setAttribute("EmploymentList", EmploymentList);
	            request.setAttribute("CasteList", CasteList);

	              
	            request.setAttribute("districtName", districtName);
	            request.setAttribute("districtid", districtid);
	            request.setAttribute("mandalList", mandalList);
	            request.setAttribute("panchayatList", panchayatList);
	            request.setAttribute("villageList", villageList);
	            request.setAttribute("habitationList", habitationList);
	            
		        
        
		        
		        
		        //generate new sadaremid
		        
		        CommonDAOImpl obj5 = new CommonDAOImpl();
		        
		     String new_sadarem_id   = obj5.getNewSADAREMID(districtid, mandalid, villageId);
		      
		     int result=0;
		     
		    if(new_sadarem_id.length()>0 && new_sadarem_id!=null)
		    {
		    	 result = obj2.aproofPartAWithoutProofCA( new_sadarem_id, request_id, aadharCardNo, loginid ,districtName);
		    }
        	if(result>0 && result!=0)
									{
 		        		request.setAttribute("ResultMSG", "<font style='color:green;font-weight:bold'>Approved successfully !! Generated SADAREM ID :"+new_sadarem_id+" . Please proceed for part-B!!</font>");
									}
							else
									{
						request.setAttribute("ResultMSG", "<font style='color:red;font-weight:bold'>Something went wrong / Error occured when copying photo!! Please try again!</font>");
									} 
								
		   
        	 resultList = obj2.getWithoutProofPartaList(request_id,aadharCardNo);	
		     request.setAttribute("resultList", resultList);	
				
							         
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Error occured!! Please try again!!!</b></font>"); 	 		
	 		return mapping.findForward("exception");
	    	
	    }
	    return mapping.findForward(target);
		
	
	}	  
	
	
	public ActionForward updatePartAform(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target="partapage";
		
	    String ResultMSG=null;
	    ArrayList basidtls= new ArrayList();
	    
	    //System.out.println("called");
        
	    HttpSession session = null;
	    session = request.getSession(true);
		HashMap partAInsertInputs = new HashMap();

		
		
	    String ipaddress= CommonUtility.getClientIPAddress(request);
	   
	    try{
	    	
	        
	      //Personal Details
	        String aadharCardNo="";
	        String surName="";
	        String firstName="";
	        String surnameenglish="";
	        String telugupersonname="";
	        String gender="";
	        String education="";
	        String employment="";
	        String marital="";
	        String caste="";
	        String religion="";
	        String relationName="";
	        String Rcard="";
	        String rtype="";
	        String rationCardSlno="";
	        String epiccardno=null;
	        String pensioncardno="";
	        String mole1="";
	        String mole2="";
	        String parents_marriage=null;
	        String telugufathername="";        
	        
	        //String partACheckForDuplicatePersonCode = null;
	        String personcodemax = null;
	        String relationType = null;
	        //String status = null;
	        String personcode = null;
	        String loginid = null;	
	        String pension_type=null;
	        
	        String houseNumber="";
	        int pensionNumberCheck = 0;		        
	        String dob = "";
            String Systemip = request.getRemoteAddr();//CommonUtility.getClientIPAddress(request);
            HashMap myDetails = (HashMap)RequestUtility.getReqParamList(request, session, false, false, "");
            
           // System.out.println("Form Data Size: " +myDetails.size()+"=====>formDate : "+myDetails.get("dateOfAss"));
        
                
            String formDate			=CommonUtility.checkNullObj((String)request.getParameter("dateOfAss"));                   
            formDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new SimpleDateFormat("dd/MM/yyyy").parse(formDate));
            
	    	String formno			=CommonUtility.checkNullObj((String)request.getParameter("formno"));
	    	String request_id			=CommonUtility.checkNullObj((String)request.getParameter("request_id"));
		 	String district_id 		=CommonUtility.checkNullObj((String)request.getParameter("district_id"));
	        String mandal_id 		=CommonUtility.checkNullObj((String)request.getParameter("mandals"));
	        String village_id 		=CommonUtility.checkNullObj((String)request.getParameter("villages"));
	        String habitation_id 	=CommonUtility.checkNullObj((String)request.getParameter("habitation"));
	        String panchayat_id 	=CommonUtility.checkNullObj((String)request.getParameter("panchayats"));
	        String habcode 	=CommonUtility.checkNullObj((String)request.getParameter("habcode"));
	        
	        
	        String PinCode 			=convertToNullWhenNoInput(request.getParameter("pin"));
	        String contid 			=convertToNullWhenNoInput(request.getParameter("contid"));
	        String email 			=convertToNullWhenNoInput(request.getParameter("email"));	        
	        houseNumber				=convertToNullWhenNoInput(request.getParameter("houseno"));
	        
			 surName				=convertToNullWhenNoInput((String)request.getParameter("surname"));
	         firstName				=convertToNullWhenNoInput((String)request.getParameter("firstname"));
	       //  surnameenglish			=request.getParameter("surnameenglish");
	         
	         telugupersonname		=convertToNullWhenNoInput((String)request.getParameter("telugupersonname"));
	         dob  					=convertToNullWhenNoInput((String)request.getParameter("dob"));
	         dob=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new SimpleDateFormat("dd/MM/yyyy").parse(dob));
	         
	         gender					=convertToNullWhenNoInput((String)request.getParameter("gender"));
	         education				=convertToNullWhenNoInput(request.getParameter("education"));
	         employment				=convertToNullWhenNoInput(request.getParameter("employment"));
	         marital				=convertToNullWhenNoInput(request.getParameter("marital"));
	         caste					=convertToNullWhenNoInput(request.getParameter("caste"));
	         religion				=convertToNullWhenNoInput(request.getParameter("religion"));
	         Rcard					=convertToNullWhenNoInput(request.getParameter("card"));
	         
	         if(request.getParameter("rtype")!=null)
	         {
	        	 rtype=convertToNullWhenNoInput(request.getParameter("rtype"));
	         }
	         else
	         {
	        	 rtype="";
	         }
	         
	         rationCardSlno=convertToNullWhenNoInput(request.getParameter("rationCardSlno"));
	         
	         String epicradio=CommonUtility.checkNullObj((String)request.getParameter("epicradio"));
	         epiccardno=CommonUtility.checkNullObj((String)request.getParameter("epiccardno"));
	         
	         String pensionradio=convertToNullWhenNoInput(request.getParameter("pensionradio"));  
	         pensioncardno=convertToNullWhenNoInput(request.getParameter("pensioncardno"));  
	         pension_type=convertToNullWhenNoInput(request.getParameter("pension_type"));
	         mole1=convertToNullWhenNoInput((String)request.getParameter("mole1"));
	         mole2=convertToNullWhenNoInput((String)request.getParameter("mole2"));
	         
	         if(request.getParameter("parents_marriage")!=null)
	         {
	        	 parents_marriage=(CommonUtility.checkNullObj((String)request.getParameter("parents_marriage")));
	         }
	         else
	         {
	        	 parents_marriage="";
	         }
	         
	         		         
	         relationName=convertToNullWhenNoInput((String)request.getParameter("GName"));
	         relationType = convertToNullWhenNoInput((String)request.getParameter("grelation"));		         
	         telugufathername = convertToNullWhenNoInput((String)request.getParameter("telugufathername"));		         
	         aadharCardNo =convertToNullWhenNoInput((String)request.getParameter("aadharCardNo"));
	         String proofdoctype =convertToNullWhenNoInput((String)request.getParameter("proofdoctype"));
	         loginid = (String) session.getAttribute("loginid");
             int campId = 0 ;
             if(session.getAttribute("campId")!=null)
             {
            	  campId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("campId")));
             }
             else
             {
            	 	request.setAttribute("ResultMSG", "<font style='color:red;font-weight:bold'>Error in Form Filling. Mandatory Details are missing</font>");
		        	return mapping.findForward("partapage");
             }
	    	
	         //Contact Details 
             
             
	         	partAInsertInputs.put("formDate", formDate);
		        partAInsertInputs.put("formno", formno);
		        partAInsertInputs.put("request_id", request_id);
		        partAInsertInputs.put("district_id", district_id);
		        partAInsertInputs.put("mandal_id", mandal_id);
		        partAInsertInputs.put("village_id", village_id);
		        partAInsertInputs.put("panchayat_id", panchayat_id);   
		        partAInsertInputs.put("habitation_id", habitation_id);
		        partAInsertInputs.put("habcode", habcode);
		        partAInsertInputs.put("PinCode", PinCode);
		        partAInsertInputs.put("contid", contid);
		        partAInsertInputs.put("email", email);
		        partAInsertInputs.put("houseNumber", houseNumber);
	    	//Personal Details
		        partAInsertInputs.put("surName", surName);
		        partAInsertInputs.put("firstName", firstName);
		     //   partAInsertInputs.put("surnameenglish", surnameenglish);
		        partAInsertInputs.put("telugupersonname", telugupersonname);
		        partAInsertInputs.put("dob", dob);
		        partAInsertInputs.put("gender", gender);
		        partAInsertInputs.put("education", education);
		        partAInsertInputs.put("employment", employment);
		        partAInsertInputs.put("marital", marital);
		        partAInsertInputs.put("caste", caste);
		        partAInsertInputs.put("religion", religion);
		        partAInsertInputs.put("Rcard", Rcard);
		        partAInsertInputs.put("rtype", rtype);
		        partAInsertInputs.put("rationCardSlno", rationCardSlno);
		        
		        partAInsertInputs.put("epicradio", epicradio);
		        partAInsertInputs.put("epiccardno", epiccardno);
		        
		        partAInsertInputs.put("pensionradio", pensionradio);
		        partAInsertInputs.put("pensioncardno", pensioncardno);
		        partAInsertInputs.put("pension_type", pension_type);
		        
		        partAInsertInputs.put("mole1", mole1);
		        partAInsertInputs.put("mole2", mole2);
		        partAInsertInputs.put("parents_marriage", parents_marriage);
		        partAInsertInputs.put("relationName", relationName);
		        partAInsertInputs.put("relationType", relationType);
		        partAInsertInputs.put("telugufathername", telugufathername); 
		        
		        partAInsertInputs.put("aadharCardNo", aadharCardNo);	
		        partAInsertInputs.put("proofdoctype", proofdoctype);
		        partAInsertInputs.put("loginid", loginid);		        
		        partAInsertInputs.put("Systemip", Systemip);
		        partAInsertInputs.put("campId", campId);
		        
		        //System.out.println("full details-->"+partAInsertInputs.size()+"--"+partAInsertInputs);  
		     
		        
		        //update part-A details
		        WithoutProofPartADAO obj2 = new WithoutProofPartADAOImpl();   

				int basidtl = obj2.updatePartADtls(partAInsertInputs);	        
		        
				
		        String districtid = null;
		        String mandalid = null;
		        String villageId = null;
		        String districtName = null;
		        String todate = null;
		        ArrayList mandalList = new ArrayList(); 
		        ArrayList panchayatList = new ArrayList();
		        ArrayList villageList = new ArrayList();
		        ArrayList habitationList = new ArrayList();
		        
	        session = request.getSession(true);
	        CommonDAO obj= new CommonDAOImpl();
	        CommonDAOImpl comObj = new CommonDAOImpl();
	       
	        	 ArrayList resultList = new ArrayList();
	        
		            resultList = obj2.getWithoutProofPartaList(request_id,aadharCardNo);

	            if (session.getAttribute("districtId") != null)
	            {
	                districtid = (String)((ArrayList)resultList.get(0)).get(4);
	                mandalid = (String)((ArrayList)resultList.get(0)).get(5);
	                villageId = (String)((ArrayList)resultList.get(0)).get(7);
	                
	                mandalList = obj.getMandalListByDistrictID(districtid);
	                
	                 panchayatList = (ArrayList)comObj.getpanchayatListByDistrictIDMandalIDVillageID(districtid, mandalid);
	                  villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(districtid, mandalid);
	                   habitationList = (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(districtid, mandalid, villageId);
	                
	                
	                districtName=obj.getDistrictName(districtid);
	         
	                             
	            }
	            

	            CommonReportsDAO masters = new CommonReportsDAOImpl();
	            
	            ArrayList CasteList = new ArrayList();
	            ArrayList EducationList = new ArrayList();	           
	            ArrayList EmploymentList = new ArrayList();
	            
	            CasteList=masters.getCasteList();
	            EducationList=masters.getEducationList();
	            EmploymentList= masters.getEmploymentList();

	            request.setAttribute("EducationList", EducationList);
	            request.setAttribute("EmploymentList", EmploymentList);
	            request.setAttribute("CasteList", CasteList);

	              
	            request.setAttribute("districtName", districtName);
	            request.setAttribute("districtid", districtid);
	            request.setAttribute("mandalList", mandalList);
	            request.setAttribute("panchayatList", panchayatList);
	            request.setAttribute("villageList", villageList);
	            request.setAttribute("habitationList", habitationList);
	            request.setAttribute("resultList", resultList);	
		        
		      //Contact Details check
		        if(district_id.trim().equals("")  ||district_id.trim().equals("-1")  || mandal_id.trim().equals("") ||  mandal_id.trim().equals("-1") || panchayat_id.trim().equals("") || panchayat_id.trim().equals("-1") ||village_id.trim().equals("") ||village_id.trim().equals("-1") ||habitation_id.trim().equals("")||habitation_id.trim().equals("-1"))
		        {
		        	request.setAttribute("ResultMSG", "<font color='red'size='3'><b>Mandatory Contact Details should be filled.Error occurred.Please Try Again..!</b></font>"); 	 		
			 		return mapping.findForward("partapage"); 
		     
		        }
		        //Personal Details check
		        if(surName.trim().equals("")  || firstName.trim().equals("")  ||telugupersonname.trim().equals("") 
		        		||dob.trim().equals("")  || gender.trim().equals("") ||marital.trim().equals("") ||mole1.trim().equals("") ||mole2.trim().equals("")
		        			||relationName.trim().equals("")  || relationType.trim().equals("") ||relationType.trim().equals("-1")||telugufathername.trim().equals("") )
		        {
		        	request.setAttribute("ResultMSG", "<font color='red'size='3'><b>Mandatory Details should be filled properly.Error occurred.Please Try Again..!</b></font>"); 	 		
			 		return mapping.findForward("partapage");
		        }  
		        

									
		        	if(basidtl>0 && basidtl!=0)
									{
		        		request.setAttribute("ResultMSG", "<font style='color:green;font-weight:bold'>Details Updated succesfully!! Please Go for Approval/Rejection of request Id in order to proceed for part-B!!</font>");
									}
							else
									{
						request.setAttribute("ResultMSG", "<font style='color:green;font-weight:bold'>Details Not Updated!! Please try again!</font>");
									}
								
							
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Something went wrong!! Please try again!!</b></font>"); 	 		
	 		return mapping.findForward("exception");
	    	
	    }
	    return mapping.findForward(target);
		
	
	}
	
	public ActionForward rejectPartAform(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
	String target="partapage";
		
	    String ResultMSG=null;
	    HttpSession session = null;
	    session = request.getSession(true);
	    try{

         
	    	String request_id		= 			CommonUtility.checkNullObj((String)request.getParameter("request_id"));
		   // String requestflag 		=        	CommonUtility.checkNullObj((String)request.getParameter("requestflag"));
	        String rejectedremarks	=			CommonUtility.checkNullObj((String)request.getParameter("remarks"));
	        String aadharCardNo 	=			convertToNullWhenNoInput((String)request.getParameter("aadharCardNo"));
	        String  login_id 		= 			(String) session.getAttribute("loginid");
         	   
	       System.out.println("request_id : "+request_id);
		      
		        //update part-A details
		        WithoutProofPartADAO obj2 = new WithoutProofPartADAOImpl();   

        
		        
				
		        String districtid = null;
		        String mandalid = null;
		        String villageId = null; 
		        String districtName = null;
		        String todate = null;
		        ArrayList mandalList = new ArrayList(); 
		        ArrayList panchayatList = new ArrayList();
		        ArrayList villageList = new ArrayList();
		        ArrayList habitationList = new ArrayList();
		        
	        session = request.getSession(true);
	        CommonDAO obj= new CommonDAOImpl();
	        CommonDAOImpl comObj = new CommonDAOImpl();
	       
	        	 ArrayList resultList = new ArrayList();
	        
		            resultList = obj2.getWithoutProofPartaList(request_id,aadharCardNo);
		            
		            
		            System.out.println("resultList : "+resultList);

	            if (session.getAttribute("districtId") != null)
	            {
	                districtid = (String)((ArrayList)resultList.get(0)).get(4);
	                mandalid = (String)((ArrayList)resultList.get(0)).get(5);
	                villageId = (String)((ArrayList)resultList.get(0)).get(7);
	                
	                mandalList = obj.getMandalListByDistrictID(districtid);
	                
	                 panchayatList = (ArrayList)comObj.getpanchayatListByDistrictIDMandalIDVillageID(districtid, mandalid);
	                 villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(districtid, mandalid);
	                 habitationList = (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(districtid, mandalid, villageId);
	                
	                
	                districtName=obj.getDistrictName(districtid);
	         
	                             
	            }
	            

	            CommonReportsDAO masters = new CommonReportsDAOImpl();
	            
	            ArrayList CasteList = new ArrayList();
	            ArrayList EducationList = new ArrayList();	           
	            ArrayList EmploymentList = new ArrayList();
	            
	            CasteList=masters.getCasteList();
	            EducationList=masters.getEducationList();
	            EmploymentList= masters.getEmploymentList();

	            request.setAttribute("EducationList", EducationList);
	            request.setAttribute("EmploymentList", EmploymentList);
	            request.setAttribute("CasteList", CasteList);

	              
	            request.setAttribute("districtName", districtName);
	            request.setAttribute("districtid", districtid);
	            request.setAttribute("mandalList", mandalList);
	            request.setAttribute("panchayatList", panchayatList);
	            request.setAttribute("villageList", villageList);
	            request.setAttribute("habitationList", habitationList);
	           
		        
		     
		    int result =  obj2.partAWithoutProofCAReject(aadharCardNo,request_id,"R",rejectedremarks,login_id);
	

		        if(result>0 && result!=0)
				{
		        		request.setAttribute("ResultMSG", "<font style='color:green;font-weight:bold'>Request ID : "+request_id+". Rejected succesfully!!</font>");
				}
		        	else
				{
						request.setAttribute("ResultMSG", "<font style='color:red;font-weight:bold'>Not Rejected!! something went wrong! Please try again!!</font>");
				}	
		        resultList = obj2.getWithoutProofPartaList(request_id,aadharCardNo);	
		        request.setAttribute("resultList", resultList);	
		        
		}
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	request.setAttribute("MSG", "<font color='red'size='3'><b>Sucessfully Approved!</b></font>"); 	 		
	 		return mapping.findForward("exception");
	    	
	    }
	    return mapping.findForward(target);
		
	
	}	  
	public String convertToNullWhenNoInput(String s)
	{
		if(s==null ||s.trim().equals("") || s.trim().equals("-1"))
		{
			return "";
		}
		return s;
	}
	
}
