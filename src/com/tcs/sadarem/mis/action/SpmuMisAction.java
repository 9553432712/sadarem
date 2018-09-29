package com.tcs.sadarem.mis.action;

 
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import org.bf.disability.Exception.SADAREMException;

import com.tcs.sadarem.common.DAO.CommonDAO;
import com.tcs.sadarem.common.DAO.CommonDAOImpl;
import com.tcs.sadarem.mis.DAO.MisDAO;
import com.tcs.sadarem.mis.DAO.MisDAOImpl;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sgv.common.util.DBConnection;

public class SpmuMisAction extends BaseDispatchAction  {
public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{
		
			String target="success";
			ArrayList activeactivityList = new ArrayList();
			String TextBoxDisable="";
		
				
			ArrayList actsubactList = new ArrayList();
			ArrayList DistrictList = new ArrayList();
			ArrayList  tempList = new ArrayList();
			ArrayList resultlist = new ArrayList();
			ArrayList subactivitylist = new ArrayList();
			ArrayList actsubactlist2 = new ArrayList();
			
			
		try{	
			 HttpSession session = request.getSession();
			MisDAO obj = new MisDAOImpl();
			activeactivityList = obj.getActiveActivityList();
			
			actsubactList = obj.getActSubactList();
		
			DistrictList=obj.getNewDistrictList();
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
		
			String distID = CommonUtility.checkNullObj(request.getParameter("district"));
			String ActiveactID = CommonUtility.checkNullObj(request.getParameter("Activeact"));
			String ActivesubactID = CommonUtility.checkNullObj(request.getParameter("Activesubact"));
		
			
			 actsubactlist2 = (ArrayList)obj.getActiveSubActivityList(ActiveactID);
			
			String subactname= CommonUtility.checkNullObj(obj.getSubActName(ActivesubactID));
         
		
			
			
			String mistargetid=obj.getMisTargetIDcheckMethod(finyear,distID,ActiveactID,ActivesubactID);
		
			if(!distID.equals(""))
			{		
				if(mistargetid.length()>5)
				{
					resultlist = obj.getSPMUleveltargetsaftersaving(distID,finyear,ActiveactID,ActivesubactID);	
					
		    		for(int i=0;i<resultlist.size();i++)
		    		{
		    			tempList = (ArrayList)resultlist.get(i);
		    				if(tempList.get(6).toString().trim().equals("S"))
		    				{
		    					TextBoxDisable="disabled";
		    				}
		    		}
				}
			    else
			    {
			    	  resultlist = obj.getSPMUleveltargets(distID,ActiveactID,ActivesubactID);	
			    	  
			    	
			    }
		    }
			
			
			

			
            
			request.setAttribute("Year",financialyear);			
			request.setAttribute("DistrictList",DistrictList);
			request.setAttribute("activeactivityList",activeactivityList);
			request.setAttribute("actsubactList",actsubactList);
		
			request.setAttribute("district",distID);
			request.setAttribute("resultlist",resultlist);
			request.setAttribute("subactname",subactname);
			request.setAttribute("Activesubact",ActivesubactID);
			request.setAttribute("Activeact",ActiveactID);
			
			request.setAttribute("actsubactlist2",actsubactlist2);
			request.setAttribute("TextBoxDisable",TextBoxDisable);
			

		}catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
	 		return mapping.findForward("exception");
		}
			
			return mapping.findForward(target);
			
		}
public ActionForward confirmallinserttargetsmandalwise(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)
        throws SADAREMException, SQLException, IOException 
{  String target="success";

	ArrayList DistrictList = new ArrayList();
	HashMap insertdtls = new HashMap();
	ArrayList resultlist = new ArrayList();
	ArrayList tempList = new ArrayList();
	ArrayList activeactivityList = new ArrayList();
	ArrayList actsubactlist2 = new ArrayList();

	MisDAO obj = new MisDAOImpl();
	CommonDAO obj1 = new CommonDAOImpl();

		activeactivityList = obj.getActiveActivityList();
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

	HttpSession session = request.getSession();
	String createdBy= (String)session.getAttribute("loginid");


		DistrictList=obj.getNewDistrictList();
		int listsize = Integer.parseInt(request.getParameter("listsize"));
		String distID = CommonUtility.checkNullObj(request.getParameter("district"));
		String ActiveactID = CommonUtility.checkNullObj(request.getParameter("Activeact"));
		String ActivesubactID = CommonUtility.checkNullObj(request.getParameter("Activesubact"));
		String subactname= CommonUtility.checkNullObj(obj.getSubActName(ActivesubactID));
		String mistargetid = CommonUtility.checkNullObj(request.getParameter("MisTargetId"));
		actsubactlist2 = (ArrayList)obj.getActiveSubActivityList(ActiveactID);


		ArrayList mandaltargetList= new ArrayList();
		ArrayList mandalidlist= new ArrayList();
 try
 {
	for(int i=0;i<listsize;i++)
	{
		    mandaltargetList.add((int)(Float.parseFloat(testnull(request.getParameter("Target"+i)))));
		    mandalidlist.add(request.getParameter("MandalId"+i));
	}
	
	insertdtls.put("mandaltargetList", mandaltargetList);
	insertdtls.put("mistargetid", mistargetid);
	insertdtls.put("mandalidlist", mandalidlist);
	insertdtls.put("distID", distID);
	insertdtls.put("Year", finyear);
	insertdtls.put("createdBy", createdBy);
	

	String resultMSG=obj.confirmtargetsatspmulevel(insertdtls);		
	resultlist = obj.getSPMUleveltargetsaftersaving(distID,finyear,ActiveactID,ActivesubactID);	
	
	String ConfirmButtonEnable="";

	String TextBoxDisable="";
	for(int i=0;i<resultlist.size();i++)
	{
		tempList = (ArrayList)resultlist.get(i);
			if(tempList.get(6).toString().trim().equals("S"))
			{
				TextBoxDisable="disabled";
			}
	}
	request.setAttribute("DistrictList",DistrictList);
	request.setAttribute("activeactivityList",activeactivityList);
	request.setAttribute("Year",financialyear);
	request.setAttribute("district",distID);
	request.setAttribute("resultMSG",resultMSG);
	request.setAttribute("resultlist",resultlist);
	request.setAttribute("ConfirmButtonEnable",ConfirmButtonEnable);
	request.setAttribute("TextBoxDisable",TextBoxDisable);
	request.setAttribute("Activeact",ActiveactID);
	request.setAttribute("Activesubact",ActivesubactID);
	request.setAttribute("TextBoxDisable",TextBoxDisable);
	request.setAttribute("actsubactlist2",actsubactlist2);
	request.setAttribute("subactname",subactname);
}
catch(Exception e)
{
	e.printStackTrace();
	request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
		return mapping.findForward("exception");
}

return mapping.findForward(target);
     
}

public ActionForward savetargets(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
{
	String target="success";

			ArrayList DistrictList = new ArrayList();
			HashMap insertdtls = new HashMap();
			ArrayList resultlist = new ArrayList();
			ArrayList tempList = new ArrayList();
			ArrayList activeactivityList = new ArrayList();
			ArrayList actsubactlist2 = new ArrayList();
	
		MisDAO obj = new MisDAOImpl();
		CommonDAO obj1 = new CommonDAOImpl();

		activeactivityList = obj.getActiveActivityList();
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

	
		HttpSession session = request.getSession();
		String createdBy= (String)session.getAttribute("loginid");
		

		DistrictList=obj.getNewDistrictList();
		int listsize = Integer.parseInt(request.getParameter("listsize"));
		String distID = CommonUtility.checkNullObj(request.getParameter("district"));
		String ActiveactID = CommonUtility.checkNullObj(request.getParameter("Activeact"));
		String ActivesubactID = CommonUtility.checkNullObj(request.getParameter("Activesubact"));
		String subactname= CommonUtility.checkNullObj(obj.getSubActName(ActivesubactID));
		String mistargetid = CommonUtility.checkNullObj(request.getParameter("MisTargetId"));
		actsubactlist2 = (ArrayList)obj.getActiveSubActivityList(ActiveactID);
	

	
	ArrayList mandaltargetList= new ArrayList();
	ArrayList mandalidlist= new ArrayList();
	try
	{
		for(int i=0;i<listsize;i++)
		{
		
			    mandaltargetList.add((int)(Float.parseFloat(testnull(request.getParameter("Target"+i)))));
			    mandalidlist.add(request.getParameter("MandalId"+i));
		}
		
		insertdtls.put("mandaltargetList", mandaltargetList);
		insertdtls.put("mistargetid", mistargetid);
		insertdtls.put("mandalidlist", mandalidlist);
		insertdtls.put("distID", distID);
		insertdtls.put("Year", finyear);
		insertdtls.put("createdBy", createdBy);
		
		
		
		String resultMSG=obj.inserttargetsatspmulevel(insertdtls);		
		
      
        
		resultlist = obj.getSPMUleveltargetsaftersaving(distID,finyear,ActiveactID,ActivesubactID);	
	
		String ConfirmButtonEnable="";

		String TextBoxDisable="";
		for(int i=0;i<resultlist.size();i++)
		{
			tempList = (ArrayList)resultlist.get(i);
				if(tempList.get(6).toString().trim().equals("S"))
				{
					TextBoxDisable="disabled";
				}
		}
		request.setAttribute("DistrictList",DistrictList);
		request.setAttribute("activeactivityList",activeactivityList);
		request.setAttribute("Year",financialyear);
		request.setAttribute("district",distID);
		request.setAttribute("resultMSG",resultMSG);
		request.setAttribute("resultlist",resultlist);
		request.setAttribute("ConfirmButtonEnable",ConfirmButtonEnable);
		request.setAttribute("TextBoxDisable",TextBoxDisable);
		request.setAttribute("Activeact",ActiveactID);
		request.setAttribute("Activesubact",ActivesubactID);
		request.setAttribute("TextBoxDisable",TextBoxDisable);
		request.setAttribute("actsubactlist2",actsubactlist2);
		request.setAttribute("subactname",subactname);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
 		return mapping.findForward("exception");
		}

		return mapping.findForward(target);	

	
}	
public String testnull(String val)

{
	if(val == null || val.length()==0)
	{
		return "0";
	}
	else{
		return val;
	}
}
}
