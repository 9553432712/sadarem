package com.tcs.sadarem.mis.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.tcs.sadarem.mis.DAO.MisDAO;
import com.tcs.sadarem.mis.DAO.MisDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

public class PopulationupdateAction extends BaseDispatchAction  {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target="success";
		//ArrayList insertheadingList = new ArrayList();
	
			
		//ArrayList actsubactList = new ArrayList();
		ArrayList DistrictList = new ArrayList();
		
		ArrayList resultlist = new ArrayList();
		
		
		
	try{	
		 HttpSession session = request.getSession();
		MisDAO obj = new MisDAOImpl();
		
		CommonDAO obj1 = new CommonDAOImpl(); 
		DistrictList=obj1.getDistrictList();
		
		String distID= (String)session.getAttribute("districtId");
		
		//String distID = CommonUtility.checkNullObj(request.getParameter("district"));
			
		
		
		resultlist = obj.getDistrictWisePopulation();

		
		Calendar cal = Calendar.getInstance();
		String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
		String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
		
		request.setAttribute("monthName",monthName);
		request.setAttribute("Year",Year);			
		request.setAttribute("DistrictList",DistrictList);
		request.setAttribute("district",distID);
		request.setAttribute("resultlist",resultlist);
	}catch(Exception e)
	{
		e.printStackTrace();
		request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
 		return mapping.findForward("exception");
	}		
		return mapping.findForward(target);		
	}
	public ActionForward districtWisePopulation(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		String target="iframe";
		ArrayList DistrictList = new ArrayList();	
		ArrayList mandalList = new ArrayList();
		ArrayList resultlist = new ArrayList();
		
	try{	
		 HttpSession session = request.getSession();
		MisDAO obj = new MisDAOImpl();
		
			
		
		CommonDAO obj1 = new CommonDAOImpl(); 
		DistrictList=obj1.getDistrictList();
		
		String distName=CommonUtility.checkNullObj(request.getParameter("distName"));
		String distID = CommonUtility.checkNullObj(request.getParameter("district1"));
		
		if(distID.equals("-1"))
		{
			resultlist = obj.getDistrictWisePopulation();			
		}
		else
		{
			mandalList=obj.getMandalListByDistrictIDPopulation(distID);
		}
		
		
		Calendar cal = Calendar.getInstance();
		String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
		String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
		
		String ConfirmButtonEnable="";
		String TextBoxDisable="";
		ArrayList tempList = new ArrayList();
		for(int i=0;i<mandalList.size();i++)
		{
			 tempList = (ArrayList)mandalList.get(i);
				if(tempList.get(2).toString().trim().equals("0"))
				{
					ConfirmButtonEnable="disabled";
				}
		}
		if(tempList.get(3).toString().trim().equals("Y"))
		{
			TextBoxDisable="disabled";
		}
		request.setAttribute("DistrictList",DistrictList);
		request.setAttribute("monthName",monthName);
		request.setAttribute("Year",Year);
		request.setAttribute("district",distID);
		request.setAttribute("ConfirmButtonEnable",ConfirmButtonEnable);
		request.setAttribute("TextBoxDisable",TextBoxDisable);
		request.setAttribute("mandalList",mandalList);
		request.setAttribute("resultlist",resultlist);
		request.setAttribute("distName",distName);
		request.setAttribute("districtid",distID);
	    }
	catch(Exception e)
	  {
		e.printStackTrace();
		request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
 		return mapping.findForward("exception");
	  }
		
		return mapping.findForward(target);

	        
	}
	public ActionForward confirmMethod(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
			String target="iframe";
		
		ArrayList DistrictList = new ArrayList();
		HashMap insertdtls = new HashMap();
		ArrayList mandalList = new ArrayList();
		ArrayList mandalIdList= new ArrayList();
		ArrayList populationinputList= new ArrayList();
		
		MisDAO obj = new MisDAOImpl();
		CommonDAO obj1 = new CommonDAOImpl();
		
		DistrictList=obj1.getDistrictList();
		
		Calendar cal = Calendar.getInstance();
		String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
		String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
		
		String distName=CommonUtility.checkNullObj(request.getParameter("distName"));
		String distID = CommonUtility.checkNullObj(request.getParameter("districtid"));
		HttpSession session = request.getSession();
		String createdBy= (String)session.getAttribute("persondispname");
		
		mandalList=obj.getMandalListByDistrictIDPopulation(distID);
		
		try
		{
			for(int i=0;i<mandalList.size();i++)
			{
				String stsmsg="";
				
				{
					mandalIdList.add(request.getParameter("manId_"+i));
					populationinputList.add(request.getParameter("val_"+i));					
				}							
			}
			mandalList=obj.getMandalListByDistrictIDPopulation(distID);
			insertdtls.put("mandalIdList", mandalIdList);
			insertdtls.put("populationinputList", populationinputList);
			insertdtls.put("distID", distID);
			insertdtls.put("Year", Year);
			insertdtls.put("createdBy", createdBy);
			
			String resultMSG =obj.confirmDtls(insertdtls,"Y");
			
			mandalList=obj.getMandalListByDistrictIDPopulation(distID);
			String TextBoxDisable="";
			String ConfirmButtonEnable="";
			ArrayList tempList=new ArrayList();
			for(int i=0;i<mandalList.size();i++)
			{
				tempList = (ArrayList)mandalList.get(i);
					if(tempList.get(2).toString().trim().equals("0"))
					{
						ConfirmButtonEnable="disabled";
					}
			}
			
			if(tempList.get(3).toString().trim().equals("Y"))
			{
				TextBoxDisable="disabled";
			}
			request.setAttribute("DistrictList",DistrictList);
			request.setAttribute("monthName",monthName);
			request.setAttribute("Year",Year);
			request.setAttribute("distName",distName);
			request.setAttribute("districtid",distID);			
			request.setAttribute("mandalList",mandalList);
			request.setAttribute("ConfirmButtonEnable",ConfirmButtonEnable);
			request.setAttribute("TextBoxDisable",TextBoxDisable);
			request.setAttribute("resultMSG",resultMSG);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
		}
		
		return mapping.findForward(target);
	}
	
	
	public ActionForward inserPopulation(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		String target="iframe";
		
		ArrayList DistrictList = new ArrayList();
		HashMap insertdtls = new HashMap();
		ArrayList mandalList = new ArrayList();
		ArrayList tempList = new ArrayList();
		
		MisDAO obj = new MisDAOImpl();
		CommonDAO obj1 = new CommonDAOImpl();
		
		DistrictList=obj1.getDistrictList();
		
		Calendar cal = Calendar.getInstance();
		String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
		String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
		
		//String distID= (String)session.getAttribute("districtId");
		String distID = CommonUtility.checkNullObj(request.getParameter("districtid"));
		String distName=CommonUtility.checkNullObj(request.getParameter("distName"));
		
		
		mandalList=obj.getMandalListByDistrictIDPopulation(distID);
		
		ArrayList mandalIdList= new ArrayList();
		ArrayList populationinputList= new ArrayList();
		try
		{
			for(int i=0;i<mandalList.size();i++)
			{
					mandalIdList.add(request.getParameter("manId_"+i));
					populationinputList.add(request.getParameter("val_"+i));
					
			}
			
			insertdtls.put("mandalIdList", mandalIdList);
			insertdtls.put("populationinputList", populationinputList);
			insertdtls.put("distID", distID);
			insertdtls.put("Year", Year);
			
			String resultMSG=obj.instpopulatio(insertdtls);			
			mandalList=obj.getMandalListByDistrictIDPopulation(distID);
			
			
			String ConfirmButtonEnable="";
			for(int i=0;i<mandalList.size();i++)
			{
				tempList = (ArrayList)mandalList.get(i);
					if(tempList.get(2).toString().trim().equals("0") )
					{
						ConfirmButtonEnable="disabled";
					}
			}
			String TextBoxDisable="";
			for(int i=0;i<mandalList.size();i++)
			{
				tempList = (ArrayList)mandalList.get(i);
					if(tempList.get(3).toString().trim().equals("Y"))
					{
						TextBoxDisable="disabled";
					}
			}
			request.setAttribute("DistrictList",DistrictList);
			request.setAttribute("monthName",monthName);
			request.setAttribute("Year",Year);
			request.setAttribute("district",distID);
			request.setAttribute("resultMSG",resultMSG);
			request.setAttribute("mandalList",mandalList);
			request.setAttribute("ConfirmButtonEnable",ConfirmButtonEnable);
			request.setAttribute("TextBoxDisable",TextBoxDisable);
			request.setAttribute("distName",distName);
			request.setAttribute("districtid",distID);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
		}
		
		return mapping.findForward(target);
	}

}