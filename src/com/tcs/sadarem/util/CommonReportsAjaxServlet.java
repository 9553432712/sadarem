package com.tcs.sadarem.util;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bf.disability.common.CommonDAO;

import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsDAO;
import com.tcs.sadarem.openreports.DAO.UpdateCaptureDeadDetailsImpl;
import com.tcs.sadarem.reports.DAO.CommonReportsDAO;
import com.tcs.sadarem.reports.DAOImpl.CommonReportsDAOImpl;

public class CommonReportsAjaxServlet  extends HttpServlet 
{
	static final private Logger log = Logger.getLogger(CommonReportsAjaxServlet.class);
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
					    	
					    	
					    	if(actionVal.equalsIgnoreCase("loadmandals")) 
					    	{
					    		response.getWriter().write(loadMandals(request));
					    	}
					    	/*else if(actionVal.equalsIgnoreCase("loadmandalsrep"))
					    	{
					    		response.getWriter().write(loadmandalsrep(request));
					    	}*/
					    	else if(actionVal.equalsIgnoreCase("loadVillages"))
					    	{
					    		response.getWriter().write(loadVillages(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadMandalsbyareawise"))
					    	{
					    		response.getWriter().write(loadMandalsbyareawise(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadMandalsbyurbanruralareawise"))
					    	{
					    		response.getWriter().write(loadMandalsbyurbanruralareawise(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadVillagesurbanruralareawise"))
					    	{
					    		response.getWriter().write(loadVillagesurbanruralareawise(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loadVillagesareawise"))
					    	{
					    		response.getWriter().write(loadVillagesareawise(request));
					    	}
					    	/*else if(actionVal.equalsIgnoreCase("loadhabitation"))
					    	{
					    		response.getWriter().write(loadHabitation(request));loadVillagesurbanruralareawise
					    	} */
					    /*	else if(actionVal.equalsIgnoreCase("getShgListForVO"))
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
					    	else if(actionVal.equalsIgnoreCase("checkAadharTagging"))
					    	{
					    		response.getWriter().write(checkAadharTagging(request));
					    	}*/
					    	else if(actionVal.equalsIgnoreCase("loadcamps"))
					    	{
					    		response.getWriter().write(loadcamps(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loaddistricts"))
					    	{
					    		response.getWriter().write(loaddistrictsurbanruralwise(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loaddistrictss"))
					    	{
					    		response.getWriter().write(loaddistrictsurbanruralareawise(request));
					    	}
					    	else if(actionVal.equalsIgnoreCase("loaddistrictsnew"))
					    	{
					    		response.getWriter().write(loaddistrictsurbanruralwisenew(request));
					    	}
					    	/*else if(actionVal.equalsIgnoreCase("loadmonths"))
					    	{
					    		response.getWriter().write(loadmonthslist(request));
					    	}*/
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
  	  	
		private String loadMandals(HttpServletRequest request ) 
		{
			String mandalListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
			String mandalidname 		= CommonUtility.checkNullObj(request.getParameter("mandalidname"));
			String areatype 		= CommonUtility.checkNullObj(request.getParameter("areatype"));
			ArrayList mandalList = (ArrayList)comObj.getMandalListbyDistrictId(distID);
			
		if(CommonUtility.checkNullObj(mandalidname)=="")
			mandalidname="mandal";
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto(mandalidname,mandalList,"","select-optionItem","",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in CommonReportsAjaxServlet @ loadMandals : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return mandalListCombo;
			
		} 
		private String loadMandalsbyareawise(HttpServletRequest request ) 
		{
			String mandalListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
			String mandalidname 		= CommonUtility.checkNullObj(request.getParameter("mandalidname"));
			String areatype 		= CommonUtility.checkNullObj(request.getParameter("areatype"));
			ArrayList mandalList = (ArrayList)comObj.getMandalListbyDistrictIdAreatype(distID,areatype);
			
		if(CommonUtility.checkNullObj(mandalidname)=="")
			mandalidname="mandal";
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto(mandalidname,mandalList,"","select-optionItem","",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in CommonReportsAjaxServlet @ loadMandals : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return mandalListCombo;
			
		} 
		private String loadMandalsbyurbanruralareawise(HttpServletRequest request ) 
		{
			String mandalListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
			String mandalidname 		= CommonUtility.checkNullObj(request.getParameter("mandalidname"));
			String areatype 		= CommonUtility.checkNullObj(request.getParameter("areatype"));
			ArrayList mandalList = (ArrayList)comObj.getMandalListbyDistrictIdAreatype(distID,areatype);
			
		if(CommonUtility.checkNullObj(mandalidname)=="")
			mandalidname="mandal";
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto(mandalidname,mandalList,"","select-optionItem","",true,true,"");
			}
			catch(Exception e)
			{
				log.info("Exception in CommonReportsAjaxServlet @ loadMandals : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("mandal",new ArrayList(),"","select-optionItem","",true,true,"");
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
			String panchayatListCombo="";
			HttpSession session = request.getSession();
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();
			

			String distId   = (String) session.getAttribute("districtId");
			String mandID	= CommonUtility.checkNullObj(request.getParameter("manId"));
			
			/*System.out.println("a"+distId);
			System.out.println("b"+mandID);*/
			
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
		private String loadcamps(HttpServletRequest request ) 
		{
			String campListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();
				
			String distID 		= CommonUtility.checkNullObj(request.getParameter("distId"));
		
			ArrayList campList = (ArrayList)comObj.getCampListByDistrictID(distID);
		
			//System.out.println("campListCombo"+campList);
			campListCombo =ComboUtil.createStrComboBoxAuto("campId",campList,"","select-optionItem","",true,false,"");
			//System.out.println("campListCombo"+campListCombo);
			}
			catch(Exception e)
			{
				log.info("Exception in CommonReportsAjaxServlet @ loadcamps : "+e.getLocalizedMessage());
				campListCombo =ComboUtil.createStrComboBoxAuto("campId",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return campListCombo;
			
		} 
		private String loaddistrictsurbanruralwise(HttpServletRequest request ) 
		{
			String DistrictListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();
				
			String areatype 		= CommonUtility.checkNullObj(request.getParameter("areatype"));
		
			ArrayList districtList = (ArrayList)comObj.getDistrictListbyAreatype(areatype);
		
			//System.out.println("campListCombo"+districtList);
			DistrictListCombo =ComboUtil.createStrComboBoxAuto("district1",districtList,"","select-optionItem","",true,false,"");
			//System.out.println("campListCombo"+DistrictListCombo);
			}
			catch(Exception e)
			{
				log.info("Exception in CommonReportsAjaxServlet @ loaddistrictsurbanruralwise : "+e.getLocalizedMessage());
				DistrictListCombo =ComboUtil.createStrComboBoxAuto("campId",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return DistrictListCombo;
			
		} 
		private String loaddistrictsurbanruralareawise(HttpServletRequest request ) 
		{
			String DistrictListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();
				
			String areatype 		= CommonUtility.checkNullObj(request.getParameter("areatype"));
		
			ArrayList districtList = (ArrayList)comObj.getDistrictListbyAreatype(areatype);
		
			//System.out.println("campListCombo"+districtList);
			DistrictListCombo =ComboUtil.createStrComboBoxAuto("district1",districtList,"","select-optionItem","",true,true,"");
			//System.out.println("campListCombo"+DistrictListCombo);
			}
			catch(Exception e)
			{
				log.info("Exception in CommonReportsAjaxServlet @ loaddistrictsurbanruralwise : "+e.getLocalizedMessage());
				DistrictListCombo =ComboUtil.createStrComboBoxAuto("campId",new ArrayList(),"","select-optionItem","",true,true,"");
			}
			
			return DistrictListCombo;
			
		} 
		private String loaddistrictsurbanruralwisenew(HttpServletRequest request ) 
		{
			String DistrictListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();
				
			String areatype 		= CommonUtility.checkNullObj(request.getParameter("areatype"));
		
			ArrayList districtList = (ArrayList)comObj.getDistrictListbyAreatype(areatype);
		
			//System.out.println("campListCombo"+districtList);
			DistrictListCombo =ComboUtil.createStrComboBoxAuto("district2",districtList,"","select-optionItem","",true,false,"");
			//System.out.println("campListCombo"+DistrictListCombo);
			}
			catch(Exception e)
			{
				log.info("Exception in CommonReportsAjaxServlet @ loaddistrictsurbanruralwise : "+e.getLocalizedMessage());
				DistrictListCombo =ComboUtil.createStrComboBoxAuto("campId",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return DistrictListCombo;
			
		} 
		private String loadVillages(HttpServletRequest request) 
		{
			String mandalListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();

			String distID	= CommonUtility.checkNullObj(request.getParameter("distId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("mandalId"));
			String villageidname	= CommonUtility.checkNullObj(request.getParameter("villageidname"));
			
			
			ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalID(distID, mandID);
		
			if(CommonUtility.checkNullObj(villageidname)=="")
				villageidname="village";
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto(villageidname,villageList,"","select-optionItem","onchange='loadHabitationCombo()'",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return mandalListCombo;
			
		}
		private String loadVillagesareawise(HttpServletRequest request) 
		{
			String mandalListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();

			String distID	= CommonUtility.checkNullObj(request.getParameter("distId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("mandalId"));
			String areatype	= CommonUtility.checkNullObj(request.getParameter("areatype"));
			String villageidname	= CommonUtility.checkNullObj(request.getParameter("villageidname"));
			
			
			ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalIDAreaWise(distID, mandID,areatype);
		
			if(CommonUtility.checkNullObj(villageidname)=="")
				villageidname="village";
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto(villageidname,villageList,"","select-optionItem","onchange='loadHabitationCombo()'",true,false,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","select-optionItem","",true,false,"");
			}
			
			return mandalListCombo;
			
		}
		private String loadVillagesurbanruralareawise(HttpServletRequest request) 
		{
			String mandalListCombo="";

			try
			{
				CommonReportsDAO comObj = new CommonReportsDAOImpl();

			String distID	= CommonUtility.checkNullObj(request.getParameter("distId"));
			String mandID	= CommonUtility.checkNullObj(request.getParameter("mandalId"));
			String areatype	= CommonUtility.checkNullObj(request.getParameter("areatype"));
			String villageidname	= CommonUtility.checkNullObj(request.getParameter("villageidname"));
			
			
			ArrayList villageList = (ArrayList)comObj.getVillageListByDistrictIDMandalIDAreaWise(distID, mandID,areatype);
		
			if(CommonUtility.checkNullObj(villageidname)=="")
				villageidname="village";
			
			 mandalListCombo =ComboUtil.createStrComboBoxAuto(villageidname,villageList,"","select-optionItem","onchange='loadHabitationCombo()'",true,true,"");
			}
			catch(Exception e)
			{
				log.info("Exception in SadaremAjax @ loadVillageforOpenReport : "+e.getLocalizedMessage());
				mandalListCombo =ComboUtil.createStrComboBoxAuto("village",new ArrayList(),"","select-optionItem","",true,true,"");
			}
			
			return mandalListCombo;
			
		}
		private String loadVillageforList(HttpServletRequest request ) 
		{
			String villageListCombo="";
			HttpSession session = request.getSession();
			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			String distID   = (String) session.getAttribute("districtId");
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
			String habitationListCombo="";
			HttpSession session = request.getSession();

			try
			{
			CommonDAOImpl comObj = new CommonDAOImpl();

			String distID   = (String) session.getAttribute("districtId");
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
		
		private String checkAadharTagging(HttpServletRequest request)
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
					status = "<font color='red'>Aadhaar ID "+aadharNo+" is already tagged to SADAREM ID "+aadharTagSadaremId+".Please check before raising the request.</font>";
					System.out.println("status"+status);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return status;
			
		}
		

}
