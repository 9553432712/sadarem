package com.tcs.sadarem.issuetracksystem.actions;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.Exception.BaseDispatchAction;

import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueApprovalDAOImpl;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAO;
import com.tcs.sadare.issuetracksystem.DAO.CommonIssueTrackingDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.CommonValidators;

public class CommonIssueTrackingAction extends BaseDispatchAction 
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList issueTypeList = new ArrayList();
		CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl(); 
		HttpSession session = request.getSession();
		 
		try{
			int roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			issueTypeList = comObj.getIssueType(roleId);
			request.setAttribute("issueTypeList", issueTypeList);
			
		 }
		catch(Exception e)
		{
			System.out.println("Exception raised in CommonIssueTrackingAction"+e);
		}
		return mapping.findForward("raiseIssue");
	}
	 
	
	public ActionForward raiseIssue(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		 ArrayList issueTypeList 	= new ArrayList();
		 ArrayList relationTypeList = new ArrayList();
		 ArrayList mandalList		= new ArrayList();
		 ArrayList panchayatList	= new ArrayList();
		 ArrayList villageList		= new ArrayList();
		 ArrayList habitationList	= new ArrayList();
		 ArrayList proofTypeList 	= new ArrayList();
		 ArrayList sadaremData  	= new ArrayList();		 
		 ArrayList tempList 		= new ArrayList();
		 ArrayList innerList        = new ArrayList();
		 ArrayList educationList	= new ArrayList();
		 ArrayList DistrictList	= new ArrayList();
		 
		 CommonIssueTrackingDAO comObj = new CommonIssueTrackingDAOImpl();
		 CommonDAOImpl comObj1 = new CommonDAOImpl();
		 
		 HttpSession session = request.getSession();
		 String issueTypeId="",sadaremId="";
		 String aliveStatus="",proofStatus="",sadaremIdStatus="",AliveOrDead="",AppelleteFormStatus="",
				 IsAssessedStatus="",IsRaisedInPartBReentry="",IsRaisedInMulti="",isRaisedanyOtherGrievance=""; 
		 String distId    = null;
		 String mandID	  = null;
		 String villageId = null; 
	     String catId="";
	     int assessStatus;
		 String mandId = (String)session.getAttribute("mandalId");
		
		try{
			int roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			String sessDistId = (String)session.getAttribute("districtId"); 
			issueTypeList = comObj.getIssueType(roleId);
			
			 
			issueTypeId = CommonUtility.checkNullObj(request.getParameter("issueType"));
			sadaremId   = CommonUtility.checkNullObj(request.getParameter("sadaremid"));
			
			aliveStatus 	= CommonUtility.checkNullObj(comObj.checkSADAREMLiveStatus(sadaremId));
			proofStatus 	= CommonUtility.checkNullObj(comObj.checkSADAREMProofStatus(sadaremId));
			sadaremIdStatus = CommonUtility.checkNullObj(comObj.checkSADAREMStatus(sadaremId));
		//AliveOrDead = CommonUtility.checkNullObj(comObj.checkSADAREMPersonLiveStatus(sadaremId));
			isRaisedanyOtherGrievance=CommonUtility.checkNullObj(comObj.checkIsRaisedStatus(sadaremId));
			
			AppelleteFormStatus = CommonUtility.checkNullObj(comObj.checkAppelleteFormStatus(sadaremId));
			IsAssessedStatus = CommonUtility.checkNullObj(comObj.checkIsAssessedStatus(sadaremId));
			IsRaisedInPartBReentry= CommonUtility.checkNullObj(comObj.checkWhetherRaisedInPartBReentry(sadaremId));
			IsRaisedInMulti= CommonUtility.checkNullObj(comObj.checkWhetherRaisedInMulti(sadaremId));
			
			HashMap GeoDtls = comObj1.getGEODetailsbySADAREMID(sadaremId);
		     String distIdFromSadaremId = GeoDtls.get("districtid").toString();
		     
			
		     request.setAttribute("relationTypeList", relationTypeList);
			 request.setAttribute("selIssueType",issueTypeId);
			 request.setAttribute("sadaremId",sadaremId);
			 request.setAttribute("issueTypeList", issueTypeList);
            session.setAttribute("FormSessionID",""+Math.random());
            String issueName = null;
            issueName = comObj.getIssueName(issueTypeId);            
            request.setAttribute("issueName", issueName);
            if(issueTypeId.equals(CommonConstants.SADAREMDELETION_ID))
            {
            	sadaremData = comObj.getSadaremCommonDataForSadaremDeletion(sadaremId);
            }
            else
            {
            	sadaremData = comObj.getSadaremCommonData(sadaremId);
            }
            
            ArrayList AdharTaggedMultiRecordsCheck= new ArrayList();
            AdharTaggedMultiRecordsCheck = comObj.checkProofStatusForMultiAdharMapping( sadaremId);
            Integer noOfAdhars = Integer.parseInt((String) AdharTaggedMultiRecordsCheck.get(0)); 
		     request.setAttribute("sadaremData", sadaremData); 
		     
		     
		     if(
		    		 (distIdFromSadaremId.length()>0 && !distIdFromSadaremId.equals(sessDistId) ) && 
		    		 !(roleId==Integer.parseInt(CommonConstants.ROLE_APPELLATE)) && !(roleId==Integer.parseInt(CommonConstants.Role_AdminAssist))
		      )
		     {
		    	 request.setAttribute("proofStatus", "SADAREM ID doesn't belong to this district OR No data found for this SADAREM-ID.");
		     }
		     else if
		     (
		    		 isRaisedanyOtherGrievance.length() > 0 &&
		    		 !(issueTypeId.equals(CommonConstants.APPELLATEISSUE_ID) && isRaisedanyOtherGrievance.equalsIgnoreCase("HOLD"))  // Allow to raise Appellate issue when it is in HOLD Status
		     )
	         {
	        	  request.setAttribute("proofStatus", "Issue cannot be raised as already another grievance is raised under this sadaremID and it is in pending status.<br> Please make it Final approval or Rejection of that grievance in order to proceed here.");	
	         }		          
	         else if(issueTypeId.equals(CommonConstants.SADAREMDELETION_ID))
		     {
	        	  proofTypeList = comObj.getproofdoucments();
				  request.setAttribute("proofTypeList", proofTypeList);
		    	  return mapping.findForward("sadaremdeletionissue");
			    	
		     }
	         else if(AdharTaggedMultiRecordsCheck!=null && AdharTaggedMultiRecordsCheck.size()>0 && noOfAdhars>1 && !CommonValidators.AadhaarNumberValidator(AdharTaggedMultiRecordsCheck.get(0).toString()))
	         {
	        	 request.setAttribute("proofStatus",  AdharTaggedMultiRecordsCheck.get(1));	
	         }
	         else if(sadaremIdStatus.length() > 0 || issueTypeId.equals(CommonConstants.PERSONSTATUSISSUE_ID))
	         {       	 
		        	  
		        	 
		        	  	 if(aliveStatus.length() >0 && !(issueTypeId.equals(CommonConstants.PERSONSTATUSISSUE_ID) ))
							 {
							   request.setAttribute("aliveStatus", aliveStatus);
							 } 
							 else if(!(proofStatus.length() > 0) && (issueTypeId.equals(CommonConstants.MANUALPHOTOISSUE_ID) ))
							 {
								 request.setAttribute("proofStatus", "Photo upload issue cannot be raised as SADAREM ID is tagged to Aadhar ID.");	
							 }
							 else if(proofStatus.length() > 0 && (!(issueTypeId.equals(CommonConstants.MANUALPHOTOISSUE_ID)) &&  !(issueTypeId.equals(CommonConstants.PERSONSTATUSISSUE_ID)) ) && !(issueTypeId.equals(CommonConstants.APPELLATEISSUE_ID)) ) 
					          {
							     request.setAttribute("proofStatus", proofStatus);	 
					          }
							 else if(proofStatus.length() > 0 &&   ((issueTypeId.equals(CommonConstants.PERSONSTATUSISSUE_ID)) && !(aliveStatus.length() >0)) && !(issueTypeId.equals(CommonConstants.APPELLATEISSUE_ID)) ) 
					          {
							     request.setAttribute("proofStatus", proofStatus);	
					          }
							
							 else if((issueTypeId.equals(CommonConstants.PARTBREENTRYISSUE_ID) ) && AppelleteFormStatus.equalsIgnoreCase("Edit"))
					          {
							     request.setAttribute("proofStatus", "Issue Cannot be raised as Part B is already open to fill. ");	
					          }
							 else if((issueTypeId.equals(CommonConstants.PARTBREENTRYISSUE_ID) ) && IsAssessedStatus.length() > 0 )
					          {
							     request.setAttribute("proofStatus", "Issue Cannot be raised as already grievance raised on this SADAREM ID under 'Appellete Authority at SPMU' category for Part-B Processing.");	
					          } 
							 
							 else if((issueTypeId.equals(CommonConstants.APPELLATEISSUE_ID) ) && IsRaisedInPartBReentry.length() > 0 )
					          {
							     request.setAttribute("proofStatus", "Issue Cannot be raised as already grievance raised on this SADAREM ID under 'Part-B Re-entry' category for Part-B Processing.");	
					          } 
							 else if(roleId==CommonConstants.MANDALLOGINROLEID && (!mandId.equals((((ArrayList) sadaremData.get(0)).get(2)))))
						      {
							     request.setAttribute("mandalvalidation", "SADAREM ID doesn't belong to this Mandal.");
						      }
							 else if((  issueTypeId.equals(CommonConstants.FULLNAMECHANGEISSUE_ID) || issueTypeId.equals(CommonConstants.NAMECHANGEISSUE_ID) || issueTypeId.equals(CommonConstants.RELATIONNAMECHANGEISSUE_ID) || issueTypeId.equals(CommonConstants.AGEISSUE_ID) || issueTypeId.equals(CommonConstants.ADDRESSCHANGEISSUE_ID) || issueTypeId.equals(CommonConstants.QUALIFICATIONISSUE_ID)   ) && IsRaisedInMulti.length() > 0 )
					          {
							     request.setAttribute("proofStatus", "Issue Cannot be raised as already grievance raised on this SADAREM ID under 'Multiple Grievance' category.");	
					          } 
						    else
							 {
									   
									     
								 if(sadaremData.size()>0)
								 {
									 proofTypeList = comObj.getproofdoucments();
									 request.setAttribute("proofTypeList", proofTypeList);
									// System.out.println("proofTypeList"+proofTypeList);
									 
									     if(issueTypeId.equals(CommonConstants.NAMECHANGEISSUE_ID))
									     {
									    	 
									       return mapping.findForward("nameChange");
									     }
									     else if(issueTypeId.equals(CommonConstants.RELATIONNAMECHANGEISSUE_ID))
									     {
									    	 relationTypeList = comObj.getRelationType((String)((ArrayList)sadaremData.get(0)).get(15));
									    	 request.setAttribute("relationTypeList", relationTypeList);
									    	 return mapping.findForward("relationnametypeChange");
									     }
									     
									     else  if(issueTypeId.equals(CommonConstants.ADDRESSCHANGEISSUE_ID))
									     {
									    	 
									    	 sadaremData = (ArrayList)sadaremData.get(0);  
									    	 if(roleId==Integer.parseInt(CommonConstants.ROLE_APPELLATE ) || roleId==Integer.parseInt(CommonConstants.Role_AdminAssist ))
										    	{
										    		distId     = (String)sadaremData.get(1);
										    	}
										    	else
										    	{
										    		distId     = (String) session.getAttribute("districtId");	
										    	}
									    	 
									    	
									    	 mandID     = (String)sadaremData.get(2);
									    	 villageId  = (String)sadaremData.get(3);
									    	 
									    	 mandalList      = (ArrayList)comObj1.getMandalListByDistrictID(distId);
									    	 panchayatList   = (ArrayList)comObj1.getpanchayatListByDistrictIDMandalIDVillageID(distId, mandID);
									         villageList     = (ArrayList)comObj1.getVillageListByDistrictIDMandalID(distId, mandID);
									         habitationList  = (ArrayList)comObj1.getHabitationListByDistrictIDMandalIDvillageID(distId, mandID, villageId);
									    	 
									    	
									    	 request.setAttribute("mandalList",mandalList);
									    	 request.setAttribute("panchayatList", panchayatList);
									    	 request.setAttribute("villageList",villageList);
									    	 request.setAttribute("habitationList",habitationList);
									    	 
									    	 return mapping.findForward("addressChange");
									     }
									     else if(issueTypeId.equals(CommonConstants.CAUSEOFDISABILITYCHANGEISSUE_ID))
									     {
									         return mapping.findForward("causeOfDisabilityChange");
									     }
									     else if(issueTypeId.equals(CommonConstants.AADHARISSUE_ID))
									     {
									         return mapping.findForward("aadharMismatchChange");
									     }
									     else if(issueTypeId.equals(CommonConstants.PHOTOISSUE_ID))
									     { 
									         return mapping.findForward("photoChange");
									     }
									     else if(issueTypeId.equals(CommonConstants.FULLNAMECHANGEISSUE_ID))
									     {
									         return mapping.findForward("fullNameChange");
									     }
									     else if(issueTypeId.equals(CommonConstants.MANUALPHOTOISSUE_ID))
									     {
									         return mapping.findForward("manualphotoissue");
									     }
									     else if(issueTypeId.equals(CommonConstants.PERSONSTATUSISSUE_ID))
									     {
									         return mapping.findForward("personstatusissue");
									     }
									     else if(issueTypeId.equals(CommonConstants.GENDERISSUE_ID))
									     {
									    	 String gender="";
									    	 int genderId;
									    	 sadaremData = (ArrayList)sadaremData.get(0);
									    	 gender = (String)sadaremData.get(15);
									    	 if(gender.equalsIgnoreCase("male"))
									    	 {
									    		 gender = "Female";
									    		 genderId = 2;
									    	 }
									    	 else
									    	 {
									    		 gender = "Male";
									    		 genderId = 1;
									    	 }
									    	 
									    	 request.setAttribute("gender", gender);
									    	 request.setAttribute("genderId", genderId);
									    	 
									         return mapping.findForward("gendercorrection");
									     }
									     else if(issueTypeId.equals(CommonConstants.AGEISSUE_ID))
									     {
									         return mapping.findForward("agecorrectionissue");
									     } 
									     else if(issueTypeId.equals(CommonConstants.APPELLATEISSUE_ID))
									     {
									    	 innerList.add("D");innerList.add("Doubtful Case");
									    	 tempList.add(innerList);innerList = new ArrayList();
									    	 innerList.add("F");innerList.add("Fraud Case");
									    	 tempList.add(innerList);innerList = new ArrayList();
									    	 innerList.add("T"); innerList.add("Third time Reassessment");
									    	 tempList.add(innerList);
									    	 request.setAttribute("AppelateCategory", tempList);
									    	 
									    	 return mapping.findForward("apelateissue");
									     }
									     else if(issueTypeId.equals(CommonConstants.PARTBREENTRYISSUE_ID))
									     {
									    	 innerList.add("I");innerList.add("Invalid SADAREM ID");
									    	 tempList.add(innerList);innerList = new ArrayList();
									    	 innerList.add("M");innerList.add("Mismatch in Disability Details");
									    	 tempList.add(innerList);innerList = new ArrayList(); 
									    	 innerList.add("O");innerList.add("Others(Mention reason in remarks)");
									    	 tempList.add(innerList);
									    	 sadaremData = (ArrayList)sadaremData.get(0);
									    	 if(sadaremData.size()>0)
									    	  catId = CommonUtility.checkNullObj(sadaremData.get(20));
									    	 assessStatus = comObj.getReassessmentStatus(sadaremId, catId);
									    	 
									    	 /*if(assessStatus == 0)
									    	 {
									    		 request.setAttribute("proofStatus", "Issue cannot be raised as assessment is done multiple times.Please opt for Appelllate Authority at SPMU Grievance. ");	
									    	 }
									    	 else*/
									    	 {
									    	   request.setAttribute("PartBReasonCategory", tempList);
									           return mapping.findForward("partbrentryissue");
									    	 }
									     } 
									     
									     else  if(issueTypeId.equals(CommonConstants.DistrictAddressChange))
									     {
									    	 
									    	 sadaremData = (ArrayList)sadaremData.get(0);  
									    	 if(roleId==Integer.parseInt(CommonConstants.ROLE_APPELLATE ) || roleId==Integer.parseInt(CommonConstants.Role_AdminAssist))
										    	{
										    		distId     = (String)sadaremData.get(1);
										    	}
										    	else
										    	{
										    		distId     = (String) session.getAttribute("districtId");	
										    	}
									    	 
									    	
									    	 mandID     = (String)sadaremData.get(2);
									    	 villageId  = (String)sadaremData.get(3);
									    	 
									    	 CommonReportsDAO comrep= new CommonReportsDAOImpl();
									    	 
									    	 DistrictList    = comrep.getDistrictList();
									    	 mandalList      = (ArrayList)comObj1.getMandalListByDistrictID(distId);
									    	 panchayatList   = (ArrayList)comObj1.getpanchayatListByDistrictIDMandalIDVillageID(distId, mandID);
									         villageList     = (ArrayList)comObj1.getVillageListByDistrictIDMandalID(distId, mandID);
									         habitationList  = (ArrayList)comObj1.getHabitationListByDistrictIDMandalIDvillageID(distId, mandID, villageId);
									    	 
									         request.setAttribute("DistrictList",DistrictList);
									    	 request.setAttribute("mandalList",mandalList);
									    	 request.setAttribute("panchayatList", panchayatList);
									    	 request.setAttribute("villageList",villageList);
									    	 request.setAttribute("habitationList",habitationList);
									    	 
									    	 return mapping.findForward("districtaddressChange");
									     }
									     
									    
									     
									     
									     
									     else if(issueTypeId.equals(CommonConstants.MULTIPLEGRIEVANCEISSUE_ID))
									     {
									    	 sadaremData = (ArrayList)sadaremData.get(0); 
									    	if(roleId==Integer.parseInt(CommonConstants.ROLE_APPELLATE ) || roleId==Integer.parseInt(CommonConstants.Role_AdminAssist ))
									    	{
									    		distId     = (String)sadaremData.get(1);
									    	}
									    	else
									    	{
									    		distId     = (String) session.getAttribute("districtId");	
									    	}
									    	 mandID     = (String)sadaremData.get(2);
									    	 villageId  = (String)sadaremData.get(3);
									    	 
									    	 educationList=     comObj.getEducationList();
									    	 relationTypeList = comObj.getRelationType((String)sadaremData.get(15));	
									    	 mandalList      = (ArrayList)comObj1.getMandalListByDistrictID(distId);
									    	 panchayatList   = (ArrayList)comObj1.getpanchayatListByDistrictIDMandalIDVillageID(distId, mandID);
									         villageList     = (ArrayList)comObj1.getVillageListByDistrictIDMandalID(distId, mandID);
									         habitationList  = (ArrayList)comObj1.getHabitationListByDistrictIDMandalIDvillageID(distId, mandID, villageId);
									    	 
									         request.setAttribute("educationList", educationList);
									         request.setAttribute("relationTypeList", relationTypeList);
									    	 request.setAttribute("mandalList",mandalList);
									    	 request.setAttribute("panchayatList", panchayatList);
									    	 request.setAttribute("villageList",villageList);
									    	 request.setAttribute("habitationList",habitationList);
									    	 
									    	 return mapping.findForward("multiplegrievanceissue");
									     }
			
									     
						 }
						 else
						 {
							    request.setAttribute("MSG", "<font color='red'size='3'><b>Error while raising the request.<br>Please Try Again..!</b></font>"); 	 		
						 		return mapping.findForward("exception");
						 }
					 }
			 }
			 else
			 {
				 request.setAttribute("proofStatus", "Issue cannot be raised as SADAREM ID is Invalid.");	 
			 }
			/* else if((issueTypeId.equals(CommonConstants.PERSONSTATUSISSUE_ID)) && AliveOrDead.equalsIgnoreCase("Dead"))
				 {
					  return mapping.findForward("personstatusissue"); 
				 }
				 else {
				 request.setAttribute("aliveStatus", "SADAREM ID "+sadaremId+" is invalid");
				 }*/
		}
					
		 
		catch(Exception e)
		{
			System.out.println("Exception raised in CommonIssueTrackingAction"+e);
		}
		return mapping.findForward("raiseIssue");
	}

	
}
