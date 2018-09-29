package com.tcs.sadarem.util;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bf.disability.Constants.CommonConstants;
import org.bf.disability.dao.DoctorsInformationDAO;
import org.bf.disability.dto.DoctorsInformationDTO;

import com.tcs.sadarem.Appellate.DAO.AppellateAuthorityDAO;
import com.tcs.sadarem.Appellate.DAO.AppellateAuthorityDAOImpl;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.common.DAO.UserloginDAO;
import com.tcs.sadarem.common.DAO.UserloginDAOImpl;
import com.tcs.sadarem.mis.DAO.MisDAOImpl;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsDAO;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsImpl;

public class SadaremAjaxServlet  extends HttpServlet 
{
	static final private Logger log = Logger.getLogger(SadaremAjaxServlet.class);
	  public void doPost(HttpServletRequest request,HttpServletResponse response) 
      {
        processRequest(request,response);
      }
     
      public void doGet(HttpServletRequest request,HttpServletResponse response) 
      {
        processRequest(request,response);
      }
     
  	public void processRequest(HttpServletRequest request, HttpServletResponse response)
	   {
		try
		{
				//	boolean reqStatus = request.isRequestedSessionIdValid();
				
		
			      String actionVal = "";
			       if(request.getParameter("action")!=null && request.getParameter("action").length()>0)
			       {
			           actionVal=request.getParameter("action");
			           request.setAttribute("actionVal",actionVal);
			       }
						    
					    String ClientIP=request.getHeader("x-forwarded-for");      
					    if (ClientIP == null)
					    {
					    	ClientIP = request.getHeader("X_FORWARDED_FOR");  
					          if (ClientIP == null)
					          {      
					        	  ClientIP = request.getRemoteAddr();
					          }
					    }		
					       
					    try
					    {			     
					    	HttpSession session=request.getSession(true);
					    	
					    	
					    	if(actionVal.equalsIgnoreCase("loadmandalopenrep"))
					    	{
					    		response.getWriter().write(loadMandalsforOpenReport(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadmandalsrep"))
					    	{
					    		response.getWriter().write(loadmandalsrep(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadvillageopenrep"))
					    	{
					    		response.getWriter().write(loadVillageforOpenReport(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadhabitation"))
					    	{
					    		response.getWriter().write(loadHabitation(request));
					    	} 
					    	else if(actionVal.equalsIgnoreCase("getShgListForVO"))
					    	{
					    		response.getWriter().write(getShgListForVO(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadMandalsforOpenExpiredReport"))
					    	{
					    		response.getWriter().write(loadMandalsforOpenExpiredReport(request));
					    	} 
					    	else if(actionVal.equalsIgnoreCase("loadpanchayatopenrep"))
					    	{
					    		response.getWriter().write(loadpanchayatforList(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadvillagerep"))
					    	{
					    		response.getWriter().write(loadVillageforList(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadhabitationrep"))
					    	{
					    		response.getWriter().write(loadHabitationforlist(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadmandalDistrictChange"))
					    	{
					    		response.getWriter().write(loadmandalDistrictChange(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadPanchayatDistrictChange"))
					    	{
					    		response.getWriter().write(loadPanchayatDistrictChange(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadVillageforDistrictChange"))
					    	{
					    		response.getWriter().write(loadVillageforDistrictChange(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadHabitationforDistrictChange"))
					    	{
					    		response.getWriter().write(loadHabitationforDistrictChange(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("checkAadharTagging"))
					    	{
					    		response.getWriter().write(checkAadharTagging(request,response));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadcamprep"))
					    	{
					    		response.getWriter().write(loadcampsrep(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadmonths"))
					    	{
					    		response.getWriter().write(loadmonthslist(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("OpenIssuegetOTP")){
					    		
					    		generateOTP(request,response);
					    	}
					    	else if(actionVal.equalsIgnoreCase("AppellategetOTP"))
					    	{
					    		 
					    		AppellategetOTP(request,response,session);
					    	}
					    	else if(actionVal.equalsIgnoreCase("OpenIssueloadpanchayatopenrep"))
					    	{
					    		response.getWriter().write(OpenIssueloadpanchayatopenrep(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("OpenIssueloadvillagerep"))
					    	{
					    		response.getWriter().write(OpenIssueloadvillagerep(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadVilagePanchayatChangerep"))
					    	{
					    		response.getWriter().write(loadVilagePanchayatChangeresp(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("OpenIssueloadhabitationrep"))
					    	{
					    		response.getWriter().write(OpenIssueloadhabitationrep(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadsubactlist"))
					    	{
					    		response.getWriter().write(loadsubactlist(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadRelationDetails"))
					    	{
					    		response.getWriter().write(loadRelationDetails(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadDocinf"))
					    	{
					    		response.getWriter().write(loadDoctorsDetails(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("appellateassessmenthold"))
					    	{
					    		response.getWriter().write(HoldappellateAssessmentStatus(request,session));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loaddistbyroleid"))
					    	{
					    		response.getWriter().write(getDistrictComboByRoleId(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadcampbyroleiddistid"))
					    	{
					    		response.getWriter().write(getCampComboByRoleIdDistId(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadmandalbyroleiddistid"))
					    	{
					    		response.getWriter().write(getMandalComboByRoleIdDistId(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loademployeeinflocaction"))
					    	{
					    		response.getWriter().write(getEmployeeDetailsWithLocation(request));					    		
					    	}
					    		
					    }
					    catch(Exception e)
					      {
					    	log.info("Exception in SadaremAjaxServlet @ processRequest "+e.getLocalizedMessage());
					      }
		
					    
				
		}
		catch(Exception e)
		{
			log.info("Exception in SadaremAjaxServlet @ processRequest : "+e.getLocalizedMessage());
		}
	 }
  	private void generateOTP(HttpServletRequest request,HttpServletResponse response ) throws Exception 
	{
	  
		String MobNo = CommonUtility.checkNullObj(request.getParameter("contactNo"));
		String sadaremId = CommonUtility.checkNullObj(request.getParameter("sadaremId"));
		String radVal = CommonUtility.checkNullObj(request.getParameter("randomid"));
		
		CommonDAOImpl comObj = new CommonDAOImpl();
		
		int OTP = (int)(Math.random()*8998)+1001;
		String MsgDeliveredFlag="N";
		String returnMsg = "N";
		if (!MobNo.equals("")) {
			String EncryptedOTP = String.valueOf(OTP);
			String isInserted = comObj.insertOrUpdateOtp(sadaremId,EncryptedOTP,MobNo);
			if (isInserted.equals("Y")) {
				String message = "Dear User,Your One Time Password (OTP) is "
						+ OTP
						+ " .Please Enter the OTP to complete your Process.Do not share this OTP to anyone.";
				System.out.println(message);
				if(!MobNo.equals("-") && !MobNo.equals("")){
					 
 			 	   ArrayList smsResult = (ArrayList) SMSUtility.sendmysms(MobNo,message);
 			 	// ArrayList smsResult = new ArrayList();
 				 //	smsResult.add("1");
				 //	smsResult.add("Y"); 
					if (smsResult.size() > 0 && smsResult.get(0).toString() .equals("Y") && isInserted.equals("Y")) 
					{
						MsgDeliveredFlag="Y";
					}
					else
					{
						MsgDeliveredFlag="N";
					}
				}
				
				
				if(MsgDeliveredFlag.equals("Y")){
					comObj.UpdateOtpDeliveredFlag(sadaremId,"Y",MobNo);
					returnMsg = "Y";
				}
				else if(MsgDeliveredFlag.equals("N")){
					comObj.UpdateOtpDeliveredFlag(sadaremId,"N",MobNo);
					returnMsg = "N";
				}
			}else{
				returnMsg=isInserted;
			}
		}
		response.setHeader("returnMsg", returnMsg);
	} 
  	private void AppellategetOTP(HttpServletRequest request,HttpServletResponse response,HttpSession session ) throws Exception 
	{

		String sadaremId 	= CommonUtility.checkNullObj(request.getParameter("sadaremId"));
		String reqId 		= CommonUtility.checkNullObj(request.getParameter("reqId"));
		String MobNo 		= CommonUtility.checkNullObj(session.getAttribute("logincontactNo"));
		String author 		= CommonUtility.checkNullObj(session.getAttribute("username"));
		
	//	System.out.println("logincontactNo : "+MobNo);
		
		CommonDAOImpl comObj = new CommonDAOImpl();
		
		String returnMsg = "N";
		
		if (!MobNo.equals("") && MobNo.trim().length()==10) 
		{
			 if(comObj.generateOTPforappllateAuthority(sadaremId, reqId, MobNo, author, 0) == true)
			 {		
				returnMsg = "Y";
			 }
			 else
			 {
				 returnMsg = "N";
			 }
		}
		response.setHeader("returnMsg", returnMsg);
	} 
  	
		private String loadMandalsforOpenReport(HttpServletRequest request ) 
		{
			String mandalListCombo="";

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
		
			ArrayList mandalList = (ArrayList)comObj.getMandalListByDistrictID(distID);
		
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",mandalList,"","select-optionItem","",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadMandalsforOpenReport : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return mandalListCombo;
			
		} 
		
		private String loadmonthslist(HttpServletRequest request ) 
		{
			String monthListCombo="";

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
				
			Integer Year 		= Integer.parseInt(CommonUtility.checkNullObj(request.getParameter("yearId")));
		
			ArrayList monthList = (ArrayList) comObj.getMonths(Year);
			//System.out.println("year-"+Year+"monthlist"+monthList);
			
			monthListCombo =ComboUtil.createStrComboBoxAuto("month",monthList,"","select-optionItem","",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadmonthslist : "+e.getLocalizedMessage());
				monthListCombo =ComboUtil.createStrComboBoxAuto("month",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return monthListCombo;
			
		} 
		private String loadpanchayatforList(HttpServletRequest request ) 
		{
			String panchayatListCombo="",distID="";
			HttpSession session = request.getSession();
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
			

			 
			 Integer roleId   = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId"))); 
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			String sadaremId	= CommonUtility.checkNullObj(request.getParameter("sadaremId"));
			if( (  roleId==Integer.parseInt(CommonConstants.ROLE_APPELLATE ) || roleId==Integer.parseInt(CommonConstants.Role_AdminAssist ) ) && sadaremId.length()>0)
			{
				HashMap GEODtls = new HashMap();
				GEODtls=comObj.getGEODetailsbySADAREMID(sadaremId);
				distID	=	GEODtls.get("districtid").toString();
				//distID   = sadaremId.substring(0,2);
				
			}
			else
			{
				distID   = (String) session.getAttribute("districtId");
			}
			
			
			/*System.out.println("a"+distId);
			System.out.println("b"+mandID);*/
			
			ArrayList panchayatList = (ArrayList)comObj.getpanchayatListByDistrictIDMandalIDVillageID(distID, mandID);
		    
			panchayatListCombo =ComboUtil.createStrComboBoxAuto("panchayats",panchayatList,"","form-control","onchange='loadvillages()'",true,true,"");
			//System.out.println("panchayatList"+panchayatListCombo);
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				panchayatListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","select-optionItem","onchange='loadvillages()'",true,false,"");
			}
			
			return panchayatListCombo;
			
		}
		
		private String loadMandalsforOpenExpiredReport(HttpServletRequest request ) 
		{
			String mandalListCombo="";

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
		
			ArrayList mandalList = (ArrayList)comObj.getMandalListByDistrictID(distID);
		
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",mandalList,"","select-optionItem1","onchange='loadvillageCombo()'",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadMandalsforOpenReport : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return mandalListCombo;
			
		} 
		
		
		private String loadmandalsrep(HttpServletRequest request ) 
		{
			String mandalListCombo="";
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
		
			ArrayList mandalList = (ArrayList)comObj.getMandalListByDistrictID(distID);
		
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",mandalList,"","select-optionItem","onchange='loadVillageCombo()'",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadMandalsforOpenReport : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			return mandalListCombo;
			
		}
		private String loadmandalDistrictChange(HttpServletRequest request ) 
		{
			String mandalListCombo="";
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
		
			ArrayList mandalList = (ArrayList)comObj.getMandalListByDistrictID(distID);
		
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",mandalList,"","select-optionItem","onchange='loadVillageCombo()'",true,true,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadMandalsforOpenReport : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			return mandalListCombo;
			
		}
		private String loadPanchayatDistrictChange(HttpServletRequest request ) 
		{
			String panchayatListCombo="",distID="";
			HttpSession session = request.getSession();
			try
			{
				CommonDAOImpl comObj = new CommonDAOImpl();
				distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
				String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));				
				ArrayList panchayatList = (ArrayList)comObj.getpanchayatListByDistrictIDMandalIDVillageID(distID, mandID);			    
				panchayatListCombo =ComboUtil.createStrComboBoxAuto("panchayats",panchayatList,"","form-control","onchange='loadvillages()'",true,true,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				panchayatListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","select-optionItem","onchange='loadvillages()'",true,false,"");
			}
			
			return panchayatListCombo;
			
		}
		private String loadVilagePanchayatChangeresp(HttpServletRequest request ) 
		{
			String villageListCombo="";
			HttpSession session = request.getSession();
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			String distID   = CommonUtility.checkNullObj(request.getParameter("districtId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			String panchId  = CommonUtility.checkNullObj(request.getParameter("panchId"));
			
			
			ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalIDPanchayatId(distID, mandID, panchId);
		
			villageListCombo =ComboUtil.createStrComboBoxAuto("villages",villageList,"","form-control","onchange='loadhabitation()'",true,true,"");
			
			
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVilagePanchayatChangeresp : "+e.getLocalizedMessage());
				villageListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","form-control","",true,false,"");
			}			
			return villageListCombo;			
		}
		private String loadVillageforDistrictChange(HttpServletRequest request ) 
		{
			String villageListCombo="",distID="";
			HttpSession session = request.getSession();
			try
			{
				CommonDAOImpl comObj = new CommonDAOImpl();
				distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
				String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
				
			
				
				ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(distID, mandID);
			
				villageListCombo =ComboUtil.createStrComboBoxAuto("villages",villageList,"","form-control","onchange='loadhabitation()'",true,true,"");
				//System.out.println("villageListCombo"+villageListCombo); 
				
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				villageListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","form-control","",true,false,"");
			}
			
			return villageListCombo;
			
		}
		private String loadHabitationforDistrictChange(HttpServletRequest request ) 
		{
			String habitationListCombo="";String distID="";
			HttpSession session = request.getSession();

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			distID 		= CommonUtility.checkNullObj(request.getParameter("distId")); 
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			String villageId = CommonUtility.checkNullObj(request.getParameter("villageId"));		
			
			ArrayList habitationList = (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(distID, mandID, villageId);
			
			//System.out.println("habitationList"+habitationList);
			
			habitationListCombo =ComboUtil.createStrComboBoxAuto("habitation",habitationList,"","form-control","",true,true,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadHabitation : "+e.getLocalizedMessage());
				habitationListCombo =ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,false,"");
			}
			
			return habitationListCombo;
			
		}
		private String loadcampsrep(HttpServletRequest request ) 
		{
			String campListCombo="";

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
		
			ArrayList campList = (ArrayList)comObj.getCampListByDistrictID(distID);
		
			//System.out.println("campListCombo"+campList);
			campListCombo =ComboUtil.createStrComboBoxAuto("campId",campList,"","select-optionItem","",true,false,"");
			//System.out.println("campListCombo"+campListCombo);
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadMandalsforOpenReport : "+e.getLocalizedMessage());
				campListCombo =ComboUtil.createStrComboBoxAuto("campId",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return campListCombo;
			
		} 
		private String loadVillageforOpenReport(HttpServletRequest request ) 
		{
			String mandalListCombo="";

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			String distID	= CommonUtility.checkNullObj(request.getParameter("distId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("mandalId"));
			
			ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(distID, mandID);
		
			 mandalListCombo =ComboUtil.createStrComboBoxAuto("village",villageList,"","select-optionItem","onchange='loadHabitationCombo()'",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return mandalListCombo;
			
		}
		private String loadVillageforList(HttpServletRequest request ) 
		{
			String villageListCombo="",distID="";
			HttpSession session = request.getSession();
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			
			Integer roleId   = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			String sadaremID	= CommonUtility.checkNullObj(request.getParameter("sadaremId"));
			 
			if((  roleId==Integer.parseInt(CommonConstants.ROLE_APPELLATE ) || roleId==Integer.parseInt(CommonConstants.Role_AdminAssist ) ) && sadaremID.length()>0)
			{
				HashMap GEODtls = new HashMap();
				GEODtls=comObj.getGEODetailsbySADAREMID(sadaremID);
				distID	=	GEODtls.get("districtid").toString();
				// distID   = sadaremID.substring(0,2);
			}
			else
			{
				 distID   = (String) session.getAttribute("districtId");
			}
			
			ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(distID, mandID);
		
			villageListCombo =ComboUtil.createStrComboBoxAuto("villages",villageList,"","form-control","onchange='loadhabitation()'",true,true,"");
			//System.out.println("villageListCombo"+villageListCombo); 
			
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				villageListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","form-control","",true,false,"");
			}
			
			return villageListCombo;
			
		}
		private String loadHabitation(HttpServletRequest request ) 
		{
			String mandalListCombo="";

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			String distID	= CommonUtility.checkNullObj(request.getParameter("distId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("mandalId"));
			String villageId = CommonUtility.checkNullObj(request.getParameter("villageId"));
			ArrayList habitationList = (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(distID, mandID, villageId);
			 mandalListCombo =ComboUtil.createStrComboBoxAuto("habitation",habitationList,"","select-optionItem","",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadHabitation : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return mandalListCombo;
			
		}
		private String loadHabitationforlist(HttpServletRequest request ) 
		{
			String habitationListCombo="";String distID="";
			HttpSession session = request.getSession();

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			 
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			String villageId = CommonUtility.checkNullObj(request.getParameter("villageId"));
			String sadaremID = CommonUtility.checkNullObj(request.getParameter("sadaremId"));
			
			Integer roleId = Integer.parseInt(CommonUtility.checkNullObj(session.getAttribute("roleId")));
			 
			if( (  roleId==Integer.parseInt(CommonConstants.ROLE_APPELLATE ) || roleId==Integer.parseInt(CommonConstants.Role_AdminAssist ) ) && sadaremID.length()>0)
			{
				HashMap GEODtls = new HashMap();
				GEODtls=comObj.getGEODetailsbySADAREMID(sadaremID);
				distID	=	GEODtls.get("districtid").toString();
				 //distID   = sadaremID.substring(0,2);
			}
			else
			{
				 distID   = (String) session.getAttribute("districtId");
			}
		
			
			ArrayList habitationList = (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(distID, mandID, villageId);
			
			//System.out.println("habitationList"+habitationList);
			
			habitationListCombo =ComboUtil.createStrComboBoxAuto("habitation",habitationList,"","form-control","",true,true,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadHabitation : "+e.getLocalizedMessage());
				habitationListCombo =ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,false,"");
			}
			
			return habitationListCombo;
			
		}

		
		private String getShgListForVO(HttpServletRequest request)
		{
			String shgCombo = "";
			ArrayList shgList = new ArrayList();
			String distID	= CommonUtility.checkNullObj(request.getParameter("distId"));
			String voID	= CommonUtility.checkNullObj(request.getParameter("voId"));
			UpdateCaptureDeadDetailsDAO obj = new UpdateCaptureDeadDetailsImpl();
			try {
				shgList = obj.getShgList(distID, voID);
				shgCombo = ComboUtil.createStrComboBoxAuto("shglist",shgList,"","select-optionItem","",true,true,"");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return shgCombo;
		}
		
		private String checkAadharTagging(HttpServletRequest request,HttpServletResponse response)
		{
			String aadharTagSadaremId="",aadharNo="",sadaremId="",status=" ";
			CommonDAOImpl comObj = new CommonDAOImpl();
			try
			{
				aadharNo = CommonUtility.checkNullObject(request.getParameter("aadharId"));
				sadaremId = CommonUtility.checkNullObject(request.getParameter("sadaremId"));
				
				aadharTagSadaremId = CommonUtility.checkNullObj(comObj.checkAadharTagging(aadharNo, sadaremId));
				if(aadharTagSadaremId.length()>0)
				{
					status = "<font color='red'><b>Aadhaar ID <a style=\"font-family:Verdana, Geneva, sans-serif;\" href=\"http://www.serp.telangana.gov.in/searchaadhar/do?requestType=commonrh&strUID="+aadharNo+"&actionVal=checkmyaadhar&randomid=%22+Math.random()\" target=\"_blank\">"+aadharNo+"</a> is already tagged to SADAREM ID "+aadharTagSadaremId+"."+
				             "On approving the request Aadhar Id will be detagged from "+aadharTagSadaremId+".</b></font>";
				
					response.setHeader("sadaremId", aadharTagSadaremId);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return status;
			
		}
		
		private String OpenIssueloadpanchayatopenrep(HttpServletRequest request ) 
		{
			String panchayatListCombo="";
			HttpSession session = request.getSession();
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
			

			String distId   =CommonUtility.checkNullObj(request.getParameter("districtId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			ArrayList panchayatList = (ArrayList)comObj.getpanchayatListByDistrictIDMandalIDVillageID(distId, mandID);
		    
			panchayatListCombo =ComboUtil.createStrComboBoxAuto("panchayats",panchayatList,"","form-control","onchange='loadvillages()'",true,true,"");
			//System.out.println("panchayatList"+panchayatListCombo);
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				panchayatListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","select-optionItem","onchange='loadvillages()'",true,false,"");
			}
			
			return panchayatListCombo;
			
		}
		private String OpenIssueloadvillagerep(HttpServletRequest request ) 
		{
			String villageListCombo="";
			HttpSession session = request.getSession();
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			String distID   = CommonUtility.checkNullObj(request.getParameter("districtId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			
			ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(distID, mandID);
		
			villageListCombo =ComboUtil.createStrComboBoxAuto("villages",villageList,"","form-control","onchange='loadhabitation()'",true,true,"");
			//System.out.println("villageListCombo"+villageListCombo); 
			
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				villageListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","form-control","",true,false,"");
			}
			
			return villageListCombo;
			
		}
		private String OpenIssueloadhabitationrep(HttpServletRequest request ) 
		{
			String habitationListCombo="";
			HttpSession session = request.getSession();

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			String distID   = CommonUtility.checkNullObj(request.getParameter("districtId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			String villageId = CommonUtility.checkNullObj(request.getParameter("villageId"));
			
			
			ArrayList habitationList = (ArrayList)comObj.getHabitationListByDistrictIDMandalIDvillageID(distID, mandID, villageId);
			
			//System.out.println("habitationList"+habitationList);
			
			habitationListCombo =ComboUtil.createStrComboBoxAuto("habitation",habitationList,"","form-control","",true,true,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadHabitation : "+e.getLocalizedMessage());
				habitationListCombo =ComboUtil.createStrComboBoxAuto("habitation",new ArrayList(),"","form-control","",true,false,"");
			}
			
			return habitationListCombo;
			
		}
		private String loadsubactlist(HttpServletRequest request ) 
		{
			String subactListCombo="";

			try
			{
			MisDAOImpl comObj = new MisDAOImpl(); 
				
			String Activeact 		= CommonUtility.checkNullObj(request.getParameter("Activeact"));	
			String Activesubact 		= CommonUtility.checkNullObj(request.getParameter("Activesubact"));	
			
			ArrayList actsubactlist = (ArrayList)comObj.getActiveSubActivityList(Activeact);
		
			
			subactListCombo =ComboUtil.createStrComboBoxAuto("Activesubact",actsubactlist,Activesubact,"form-control mycomboStyle","",true,true,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadsubactlist : "+e.getLocalizedMessage());
				subactListCombo =ComboUtil.createStrComboBoxAuto("Activesubact",new ArrayList(),"","form-control mycomboStyle","",true,true,"");
			}
			
			return subactListCombo;
			
		}
		private String loadRelationDetails(HttpServletRequest request ) 
		{
			String RelationDtlsListCombo="";

			try
			{
				CommonDAOImpl comObj = new CommonDAOImpl();
				/*String Activeact 		= CommonUtility.checkNullObj(request.getParameter("Activeact"));	
				String selRelation 		= CommonUtility.checkNullObj(request.getParameter("selRelation"));*/
				String genderId=CommonUtility.checkNullObj(request.getParameter("gender"));
				ArrayList RelationDtlsList = (ArrayList)comObj.getRelationDetails(genderId);
			
				RelationDtlsListCombo=ComboUtil.createStrComboBoxAuto("grelation",RelationDtlsList,"","select-optionItem","",true,true,"");
				
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadRelationDetails : "+e.getLocalizedMessage());
			}			
			return RelationDtlsListCombo;
			
		}
		private String loadDoctorsDetails(HttpServletRequest request) 
		{
			String DoctInf="";

			try
			{
				CommonDAOImpl comObj = new CommonDAOImpl();
				/*String Activeact 		= CommonUtility.checkNullObj(request.getParameter("Activeact"));	
				String selRelation 		= CommonUtility.checkNullObj(request.getParameter("selRelation"));*/
				String campId=CommonUtility.checkNullObj(request.getParameter("campId"));
				String disableId=CommonUtility.checkNullObj(request.getParameter("disabId"));
				
				String disbiliName = comObj.getDisabilityName(disableId);
				
				System.out.println("campId"+campId+"disabId"+disableId);
			
				String doctinf = "";
				DoctorsInformationDAO obj = new DoctorsInformationDAO();
				DoctorsInformationDTO dto = new DoctorsInformationDTO();
				if(campId.trim().length()>0 && !campId.trim().equals("-1")  && disableId.trim().length()>0)
				{

					dto=obj.selectDoctorDetails(null,  disbiliName, Integer.parseInt(campId));
					
					DoctInf ="";
					
					ArrayList HeadersList= new ArrayList();
					HeadersList.add("S.No.");
					HeadersList.add("Doctor Name");
					HeadersList.add("Designation");
					HeadersList.add("Regd No.");
					
					ArrayList bodylist= new ArrayList();
					ArrayList bodylist1= new ArrayList();
					bodylist.add("1");
					bodylist.add(CommonUtility.checkNull(dto.getDoctorname1()));
					bodylist.add(CommonUtility.checkNull(dto.getDesignation1()));
					bodylist.add(CommonUtility.checkNull(dto.getRegisterno1()));
					
									
					bodylist1.add(bodylist);
					
					bodylist= new ArrayList();
					bodylist.add("2");
					bodylist.add(CommonUtility.checkNull(dto.getDoctorname2()));
					bodylist.add(CommonUtility.checkNull(dto.getDesignation2()));
					bodylist.add(CommonUtility.checkNull(dto.getRegisterno2()));
					bodylist1.add(bodylist);
					
					bodylist= new ArrayList();
					bodylist.add("3");
					bodylist.add(CommonUtility.checkNull(dto.getDoctorname3()));
					bodylist.add(CommonUtility.checkNull(dto.getDesignation3()));
					bodylist.add(CommonUtility.checkNull(dto.getRegisterno3()));
					bodylist1.add(bodylist);
					
					DoctInf = CommonUtility.generateHtmlTable( bodylist1, HeadersList,3,4,"disabTable");
					
					
					
					
				}
				
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadRelationDetails : "+e.getLocalizedMessage());
			}			
			return DoctInf;
			
		}
		
		
		private String HoldappellateAssessmentStatus(HttpServletRequest request,HttpSession session) 
		{
			String result="";
			
			try
			{
				String holdmyrequestid 	 = CommonUtility.checkNullObj(request.getParameter("holdmyrequestid"));
				String holdappremarks	 = CommonUtility.checkNullObj(request.getParameter("holdappremarks"));
				String author 			= CommonUtility.checkNullObj(session.getAttribute("username"));
				
				
				if(holdmyrequestid!=null && holdappremarks!= null)
				{
					AppellateAuthorityDAO obj = new AppellateAuthorityDAOImpl();
					
				int status=	obj.updateHoldStatusofAppleteAuthority(holdmyrequestid, holdappremarks, author, CommonUtility.getClientIPAddress(request));
				
					if(status==1)
					{
						result ="<font color='green'><b>"+holdmyrequestid+"</b> request id is successfully holded.</font>";
					}
					else if(status==-1)
					{
						result ="<font color='red'>We are not able to process your request.<br/> Due some system error. Please try again.!</font>";
					}
					else
					{
						result ="<font color='red'>We are not able to process your request.Please try again.!</font>";
					} 
				}
				else
				{
					result ="<font color='red'>We are not able to process your request.Please try again.!</font>";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return result ;
		}
		
		private String getDistrictComboByRoleId(HttpServletRequest request)
		{
			String result=ComboUtil.createStrComboBoxAuto("selDistrict", new ArrayList(), "", "", "", true, true, "All");
			try
			{
				String selroleid = CommonUtility.checkNullObj(request.getParameter("selroleid"));
				
				if(!selroleid.equals("-1"))
				{
					UserloginDAO userObj = new UserloginDAOImpl();
					ArrayList dataList = (ArrayList)userObj.getDistrictListbyRoleId(selroleid);
					
					if(dataList.size()==1)
					{
						result=ComboUtil.createStrComboBoxAuto("selDistrict",dataList, "", "", "", false, false, "");
					}
					else
					{
	
						result=ComboUtil.createStrComboBoxAuto("selDistrict",dataList, "", "", "", true, true, "All");
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return result;
		}


		private String getCampComboByRoleIdDistId(HttpServletRequest request)
		{
			String result=ComboUtil.createStrComboBoxAuto("selCampId", new ArrayList(), "", "", "", true, true, "All");
			try
			{
				String selroleid 	= CommonUtility.checkNullObj(request.getParameter("selroleid"));
				String selDistrict 	= CommonUtility.checkNullObj(request.getParameter("selDistrict"));
				
				if(!selroleid.equals("-1") && !selDistrict.equals("-1"))
				{
					UserloginDAO userObj = new UserloginDAOImpl();
					ArrayList dataList = (ArrayList)userObj.getCampListbyRoleIdDistId(selroleid, selDistrict);
					
					if(dataList.size()==1)
					{
						result=ComboUtil.createStrComboBoxAuto("selCampId",dataList, "", "", "", false, false, "");
					}
					else
					{
	
						result=ComboUtil.createStrComboBoxAuto("selCampId",dataList, "", "", "", true, true, "All");
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return result;
		}

		private String getMandalComboByRoleIdDistId(HttpServletRequest request)
		{
			String result=ComboUtil.createStrComboBoxAuto("selMandal", new ArrayList(), "", "", "", true, true, "All");
			try
			{
				String selroleid 	= CommonUtility.checkNullObj(request.getParameter("selroleid"));
				String selDistrict 	= CommonUtility.checkNullObj(request.getParameter("selDistrict"));
				
				if(!selroleid.equals("-1") && !selDistrict.equals("-1"))
				{
					UserloginDAO userObj = new UserloginDAOImpl();
					ArrayList dataList = (ArrayList)userObj.getMandalListbyRoleIdDistId(selroleid, selDistrict);
					
					if(dataList.size()==1)
					{
						result=ComboUtil.createStrComboBoxAuto("selMandal",dataList, "", "", "", false, false, "");
					}
					else
					{
	
						result=ComboUtil.createStrComboBoxAuto("selMandal",dataList, "", "", "", true, true, "All");
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return result;
		}
		private String getEmployeeDetailsWithLocation(HttpServletRequest request)
		{
			String EmpInf="";

			try
			{

				String selroleid 	= CommonUtility.checkNullObj(request.getParameter("selroleid"));
				String selDistrict 	= CommonUtility.checkNullObj(request.getParameter("selDistrict"));
				String selcampid 	= CommonUtility.checkNullObj(request.getParameter("selCampId"));
				String selmandalid 	= CommonUtility.checkNullObj(request.getParameter("selMandal"));
				
				if(selroleid.trim().length()>0  && !(selroleid.equals("24")  || selroleid.equals("4")  || selroleid.equals("34") || selroleid.equals("96")))
				{
					selDistrict = "00";
				}
				
				
				if(selDistrict.trim().length()>0 && !selDistrict.trim().equals("-1")  && selroleid.trim().length()>0  && selroleid.trim().length()>0)
				{
					
					EmpInf ="";
					
					
					
					ArrayList bodylist= new ArrayList();
					ArrayList bodylist1= new ArrayList();
					
					ArrayList EmpList= new ArrayList();
					HashMap TempList= new HashMap();

					ArrayList HeadersList= new ArrayList();
					
					UserloginDAO userObj = new UserloginDAOImpl();
					
					//String statusFlag,String roleId,String distId,String campId,String mandalId,String username

					EmpList = userObj.getUserLoginDetailsforSearch("Y",selroleid, selDistrict, selcampid, selmandalid,"", false);
					
					if(EmpList!=null && EmpList.size()>0)
					{
						HeadersList.add("S.No.");
						HeadersList.add("EMP Name");
						HeadersList.add("USER ID");
						HeadersList.add("Role");					
						HeadersList.add("District");
						HeadersList.add("Mandal");
						HeadersList.add("Camp");
						HeadersList.add("Is Active");
						HeadersList.add("User Status");
						HeadersList.add("Created By");
						HeadersList.add("Created Date");
						for(int i=0;i<EmpList.size();i++)
						{							
							//  , camp_id=164, district_name=Adilabad, user_id=CA/1308614, role_name=Camp Admin, mandal_name=NA, mandal_id=NA}
							TempList = (HashMap) EmpList.get(i);
							bodylist= new ArrayList();
							bodylist.add(i+1+"");
							bodylist.add(TempList.get("person_name"));	
							bodylist.add(TempList.get("user_name"));
							bodylist.add(TempList.get("role_name"));
							bodylist.add(TempList.get("district_name"));
							bodylist.add(TempList.get("mandal_name"));
							bodylist.add(TempList.get("camp_name"));
							bodylist.add(TempList.get("is_active"));
							bodylist.add(TempList.get("user_status"));
							bodylist.add(TempList.get("created_by"));
							bodylist.add(TempList.get("created_date"));
							bodylist1.add(bodylist);
						}
					}		
					
					
					EmpInf = CommonUtility.generateHtmlTable( bodylist1, HeadersList,EmpList.size(),HeadersList.size(),"disabTable");
					
					
					
					
				}
				
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadRelationDetails : "+e.getLocalizedMessage());
			}			
			return EmpInf;
			
		}
}
