package com.tcs.sadarem.mis.action;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bf.disability.Exception.BaseDispatchAction;
import org.bf.disability.Exception.SADAREMException;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.mis.DAO.MisDAO;
import com.tcs.sadarem.mis.DAO.MisDAOImpl;
import com.tcs.sadarem.util.CommonUtility;

	public class SpmuMisDistAction extends BaseDispatchAction  {

		public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{
		
			String target="success";
			ArrayList activeactivityList = new ArrayList();
			
		
				
			ArrayList actsubactList = new ArrayList();
			ArrayList DistrictList = new ArrayList();
			
			ArrayList resultlist = new ArrayList();
			
			
			
		try{	
			 HttpSession session = request.getSession();
			MisDAO obj = new MisDAOImpl();
			
		
			activeactivityList = obj.getActiveActivityList();
			
 			
			actsubactList = obj.getActSubactList();
			CommonDAO obj1 = new CommonDAOImpl(); 
			DistrictList=obj.getNewDistrictList();
			
			String Mandalid= (String)session.getAttribute("mandalId");

			
			String distID = CommonUtility.checkNullObj(request.getParameter("district"));
			
				
			String mandalname=obj1.getMandalName(distID, Mandalid);
			Calendar cal = Calendar.getInstance();
			
			String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
			String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
		     int month = cal.get(Calendar.MONTH) + 1;
			String finyear=null,yr=null;
			String financialyear=null;
			
		     if(month<4)
		     {
		    	 finyear=(Integer.parseInt(Year)-1)+"";
		     }
		     else
		     {
		    	 finyear=Year;
		     }
			
		     yr=(Integer.parseInt(finyear)+1)+"";

		     financialyear=finyear+"-"+yr;
		     
		     resultlist = obj.getSPMUleveldistricttargets(finyear);			
		
		
			request.setAttribute("MandalName",mandalname);
			request.setAttribute("monthName",monthName);
			request.setAttribute("Year",financialyear);			
			request.setAttribute("DistrictList",DistrictList);
			request.setAttribute("activeactivityList",activeactivityList);
			request.setAttribute("actsubactList",actsubactList);
		
			request.setAttribute("district",distID);
			request.setAttribute("resultlist",resultlist);
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
			return mapping.findForward(target);
			
		}
	}