
package com.tcs.sadarem.mis.action;

import java.io.IOException;
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

	public class DpmMisAction extends BaseDispatchAction  {

public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{
			String target="success";			
			ArrayList activeactivityList = new ArrayList();
			ArrayList actsubactList = new ArrayList();			
			ArrayList resultlist = new ArrayList();
			ArrayList mandallistcombo = new ArrayList();
			ArrayList monthyearlistcombo = new ArrayList();
			ArrayList tempList = new ArrayList();
			
			
			Calendar cal = Calendar.getInstance();
			String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
			String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
		     int currentmonth = cal.get(Calendar.MONTH) + 1;
			String finyear=null,yr=null;
			String financialyear=null;
			
		     if(currentmonth<4)
		     {
		    	 finyear=(Integer.parseInt(Year)-1)+"";
		     }
		     else
		     {
		    	 finyear=Year;
		     }
			
		     yr=(Integer.parseInt(finyear)+1)+"";

		     financialyear=finyear+"-"+yr;
		     
		     
			
			
		try{	
			 HttpSession session = request.getSession();
			MisDAO obj = new MisDAOImpl();
			activeactivityList = obj.getActiveActivityList();
			String Mandalid= (String)session.getAttribute("mandalId");
			String distID= (String)session.getAttribute("districtId");
			String monthyear= CommonUtility.checkNullObj((String)request.getParameter("monthyear"));
			String mandal= CommonUtility.checkNullObj((String)request.getParameter("mandal"));
			
			monthyearlistcombo=obj.getMonths(Integer.parseInt(finyear));
			
			mandallistcombo = obj.getNewMandalList(distID);
 			
			actsubactList = obj.getActSubactList();
			CommonDAO obj1 = new CommonDAOImpl(); 
			
		    String distid = obj.getNewdistrictID(distID,mandal);
		
			String districtname= obj1.getDistrictName(distID);
			String mandalname=obj1.getMandalName(distID, mandal);
			

			if(!mandal.equals("")){
			String month = ""+Integer.parseInt((String)monthyear);
				
			resultlist    =  obj.getDPMlevelMandalTarAchBalValues(distid,mandal,finyear,month); 
			}
			String TextBoxDisable="";
			int sum=0;
			for(int i=0;i<resultlist.size();i++)
			{
				tempList = (ArrayList)resultlist.get(i);
					if(tempList.get(13).toString().trim().equals("Y"))
					{
						TextBoxDisable="disabled";
					}
					sum=sum+(int)Float.parseFloat((String)tempList.get(4));
			}

			request.setAttribute("totaltargetsum",testnull(""+sum));
			request.setAttribute("districtname",districtname);
			request.setAttribute("MandalName",mandalname);
			request.setAttribute("monthName",monthName);
			request.setAttribute("Year",financialyear);
		
			request.setAttribute("activeactivityList",activeactivityList);
			request.setAttribute("actsubactList",actsubactList);
		
			request.setAttribute("mandallistcombo",mandallistcombo);
			request.setAttribute("monthyearlistcombo",monthyearlistcombo);
			request.setAttribute("monthyear",monthyear);
			request.setAttribute("mandal",mandal);
			request.setAttribute("TextBoxDisable",TextBoxDisable);
			
			request.setAttribute("resultlist",resultlist);
		}catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
			return mapping.findForward("exception");
		}
			
			return mapping.findForward(target);
			
		}
public ActionForward saveAchvsAtDPMLevel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{ 
	String target="success";			
	ArrayList tempList = new ArrayList();
	ArrayList actsubactList = new ArrayList();			
	ArrayList resultlist = new ArrayList();
	ArrayList mandallistcombo = new ArrayList();
	ArrayList monthyearlistcombo = new ArrayList();
	HashMap insertdtls = new HashMap();
	ArrayList templist = new ArrayList();
	
	Calendar cal = Calendar.getInstance();
	String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
	String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
     int currentmonth = cal.get(Calendar.MONTH) + 1;
	String finyear=null,yr=null;
	String financialyear=null;
	
     if(currentmonth<4)
     {
    	 finyear=(Integer.parseInt(Year)-1)+"";
     }
     else
     {
    	 finyear=Year;
     }
	
     yr=(Integer.parseInt(finyear)+1)+"";

     financialyear=finyear+"-"+yr;
 	
try{	
	 HttpSession session = request.getSession();
	MisDAO obj = new MisDAOImpl();

	
	String distID= (String)session.getAttribute("districtId");
	String monthyear= CommonUtility.checkNullObj((String)request.getParameter("monthyear"));
	String mandal= CommonUtility.checkNullObj((String)request.getParameter("mandal"));
	String createdBy= (String)session.getAttribute("loginid");
	monthyearlistcombo=obj.getMonths(Integer.parseInt(finyear));
	
	mandallistcombo = obj.getNewMandalList(distID);
		
	actsubactList = obj.getActSubactList();
	CommonDAO obj1 = new CommonDAOImpl(); 
	
    String distid = obj.getNewdistrictID(distID,mandal);

	String districtname= obj1.getDistrictName(distID);
	String mandalname=obj1.getMandalName(distID, mandal);
	


	
		        int listsize = Integer.parseInt(request.getParameter("listsize"));
		    	ArrayList achiveList = new ArrayList();		
				ArrayList targetList = new ArrayList();		
				ArrayList misTarIdList = new ArrayList();
				ArrayList misBalList = new ArrayList();
				HashMap inputList = new HashMap();
				String tar="",achieve="",misTarId="";float bal=0;
				for(int val=0;val<listsize;val++)
				{
					 tar=  CommonUtility.checkNullObj((request.getParameter("target_"+val)));
					 achieve= CommonUtility.checkNullObj((request.getParameter("achieve_"+val)));
					 misTarId= CommonUtility.checkNullObj(request.getParameter("misTarId_"+val));
				

					 targetList.add(Float.parseFloat(testnull(tar)));
					 achiveList.add(Float.parseFloat(testnull(achieve)));
					 bal=Float.parseFloat(testnull(tar))-Float.parseFloat(testnull(achieve));
					 misBalList.add(Float.parseFloat(testnull(tar))-Float.parseFloat(testnull(achieve)));
					 misTarIdList.add(misTarId);
								 
				}						
					
					String loginid= (String)session.getAttribute("loginid");
					
					
					
					inputList.put("targetList", targetList);
					inputList.put("achiveList", achiveList);
					inputList.put("misTarIdList", misTarIdList);
					inputList.put("misBalList", misBalList);
					inputList.put("Mandalid", mandal);
					inputList.put("distId", distid);
					inputList.put("loginid", loginid);
					inputList.put("Year", finyear);
					inputList.put("monthyear", monthyear);
					
					
							String resultMSG="";
							
							if(achiveList.size()>0)
							{
								resultMSG=obj.saveupdateAchvssatspmulevel(inputList);							
							}
							
							if(!mandal.equals("")){
								String month = ""+Integer.parseInt((String)monthyear);
									
								resultlist    =  obj.getDPMlevelMandalTarAchBalValues(distid,mandal,finyear,month); 
								}
					
			
				
				String ConfirmButtonEnable="";
				String TextBoxDisable="";
				for(int i=0;i<resultlist.size();i++)
				{
					    tempList = (ArrayList)resultlist.get(i);
						if(tempList.get(13).toString().trim().equals("Y"))
						{
							TextBoxDisable="disabled";
						}
				}

				
				int sum=0;
				request.setAttribute("Year",financialyear);
				request.setAttribute("district",distID);
				request.setAttribute("resultMSG",resultMSG);
				request.setAttribute("resultlist",resultlist);
				request.setAttribute("ConfirmButtonEnable",ConfirmButtonEnable);
				request.setAttribute("TextBoxDisable",TextBoxDisable);
				request.setAttribute("districtname",districtname);
				request.setAttribute("MandalName",mandalname);
				request.setAttribute("monthName",monthName);
				request.setAttribute("Year",financialyear);
			
			
				request.setAttribute("mandallistcombo",mandallistcombo);
				request.setAttribute("monthyearlistcombo",monthyearlistcombo);
				request.setAttribute("monthyear",monthyear);
				request.setAttribute("mandal",mandal);
				request.setAttribute("totaltargetsum",testnull(""+sum));
				
				request.setAttribute("resultlist",resultlist);
			

			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception");
				}

				return mapping.findForward(target);	

			
		}	
public ActionForward confirmAchvsAtDPMLevel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
{ 
String target="success";			
ArrayList tempList = new ArrayList();
ArrayList actsubactList = new ArrayList();			
ArrayList resultlist = new ArrayList();
ArrayList mandallistcombo = new ArrayList();
ArrayList monthyearlistcombo = new ArrayList();
HashMap insertdtls = new HashMap();
ArrayList templist = new ArrayList();

Calendar cal = Calendar.getInstance();
String monthName = new SimpleDateFormat("MMM").format(cal.getTime());
String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
int currentmonth = cal.get(Calendar.MONTH) + 1;
String finyear=null,yr=null;
String financialyear=null;

if(currentmonth<4)
{
 finyear=(Integer.parseInt(Year)-1)+"";
}
else
{
 finyear=Year;
}

yr=(Integer.parseInt(finyear)+1)+"";

financialyear=finyear+"-"+yr;

try{	
HttpSession session = request.getSession();
MisDAO obj = new MisDAOImpl();


String distID= (String)session.getAttribute("districtId");
String monthyear= CommonUtility.checkNullObj((String)request.getParameter("monthyear"));
String mandal= CommonUtility.checkNullObj((String)request.getParameter("mandal"));
String createdBy= (String)session.getAttribute("loginid");
monthyearlistcombo=obj.getMonths(Integer.parseInt(finyear));

mandallistcombo = obj.getNewMandalList(distID);

actsubactList = obj.getActSubactList();
CommonDAO obj1 = new CommonDAOImpl(); 

String distid = obj.getNewdistrictID(distID,mandal);

String districtname= obj1.getDistrictName(distID);
String mandalname=obj1.getMandalName(distID, mandal);




        int listsize = Integer.parseInt(request.getParameter("listsize"));
    	ArrayList achiveList = new ArrayList();		
		ArrayList targetList = new ArrayList();		
		ArrayList misTarIdList = new ArrayList();
		ArrayList misBalList = new ArrayList();
		HashMap inputList = new HashMap();
		String tar="",achieve="",misTarId="";float bal=0;
		for(int val=0;val<listsize;val++)
		{
			 tar=  CommonUtility.checkNullObj((request.getParameter("target_"+val)));
			 achieve= CommonUtility.checkNullObj((request.getParameter("achieve_"+val)));
			 misTarId= CommonUtility.checkNullObj(request.getParameter("misTarId_"+val));
		

			 targetList.add(Float.parseFloat(testnull(tar)));
			 achiveList.add(Float.parseFloat(testnull(achieve)));
			 bal=Float.parseFloat(testnull(tar))-Float.parseFloat(testnull(achieve));
			 misBalList.add(Float.parseFloat(testnull(tar))-Float.parseFloat(testnull(achieve)));
			 misTarIdList.add(misTarId);
						 
		}						
			
			String loginid= (String)session.getAttribute("loginid");
			
			
			
			inputList.put("targetList", targetList);
			inputList.put("achiveList", achiveList);
			inputList.put("misTarIdList", misTarIdList);
			inputList.put("misBalList", misBalList);
			inputList.put("Mandalid", mandal);
			inputList.put("distId", distid);
			inputList.put("loginid", loginid);
			inputList.put("Year", finyear);
			inputList.put("monthyear", monthyear);
			
			
			
			
					String resultMSG="";
					
					if(achiveList.size()>0)
					{
						resultMSG=obj.confirmupdateAchvssatspmulevel(inputList);							
					}
					
					if(!mandal.equals("")){
						String month = ""+Integer.parseInt((String)monthyear);
							
						resultlist    =  obj.getDPMlevelMandalTarAchBalValues(distid,mandal,finyear,month); 
						}
			
	     
		
		String ConfirmButtonEnable="";

		String TextBoxDisable="";
		for(int i=0;i<resultlist.size();i++)
		{
			tempList = (ArrayList)resultlist.get(i);
				if(tempList.get(13).toString().trim().equals("Y"))
				{
					TextBoxDisable="disabled";
				}
		}
int sum=0;
		request.setAttribute("Year",financialyear);
		request.setAttribute("district",distID);
		request.setAttribute("resultMSG",resultMSG);
		request.setAttribute("resultlist",resultlist);
		request.setAttribute("ConfirmButtonEnable",ConfirmButtonEnable);
		request.setAttribute("TextBoxDisable",TextBoxDisable);
		request.setAttribute("districtname",districtname);
		request.setAttribute("MandalName",mandalname);
		request.setAttribute("monthName",monthName);
		request.setAttribute("Year",financialyear);
	
		request.setAttribute("totaltargetsum",testnull(""+sum));
		request.setAttribute("mandallistcombo",mandallistcombo);
		request.setAttribute("monthyearlistcombo",monthyearlistcombo);
		request.setAttribute("monthyear",monthyear);
		request.setAttribute("mandal",mandal);
		
		request.setAttribute("resultlist",resultlist);
	

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
