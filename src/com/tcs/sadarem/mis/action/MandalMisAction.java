package com.tcs.sadarem.mis.action;import java.sql.SQLException;
import java.text.DateFormatSymbols;
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


	public class MandalMisAction extends BaseDispatchAction 
	{
		public ActionForward unspecified(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		{
			String target="success";
				
			ArrayList monthlist = new ArrayList();
			Calendar cal = Calendar.getInstance();
			
			try
			{	
					HttpSession session = request.getSession();
					MisDAO obj = new MisDAOImpl();
					CommonDAO obj1 = new CommonDAOImpl();
					
					String Mandalid= (String)session.getAttribute("mandalId");
					String distID= (String)session.getAttribute("districtId");
					String month = (CommonUtility.checkNullObject(request.getParameter("month")));
					String mandalname=obj1.getMandalName(distID, Mandalid);				
					distID=obj.getNewdistrictID(distID, Mandalid);
					
					
					int curMonth=Integer.parseInt(new SimpleDateFormat("MM").format(cal.getTime()));
					String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
					int finYear=0;
					
					if(curMonth<4)
					{
						finYear=Integer.parseInt(Year)-1;
					}
					else
					{
						finYear=Integer.parseInt(Year);
					}					
					if(month.trim().length()==0)
					{
						
						month=""+curMonth;
					}
					
					String monthName = obj.getMonthForInt(Integer.parseInt(month));					
					monthlist=obj.getMonths(finYear);					
					
				
				int sum=0;
					request.setAttribute("MandalName",mandalname);
					request.setAttribute("monthName",monthName);
					request.setAttribute("Year",Year);
					request.setAttribute("monthlist",monthlist);
					request.setAttribute("month",month);
					request.setAttribute("monthDisable",testnull(""+1));
				
			}
			catch(Exception e)
			{
				request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception"); 
			}
				
				return mapping.findForward(target);
				
		}

		public ActionForward getDetails(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)
		{
			String target="success";
			String monthDisable="";
			String confirmButtonEnable="";
			String updatButtonDisable="";			
			int actEnableCount=0;
			
			ArrayList templist = new ArrayList();			
			ArrayList resultlist = new ArrayList();			
			ArrayList monthlist = new ArrayList();
			Calendar cal = Calendar.getInstance();			
			try
			{	
					HttpSession session = request.getSession();
					MisDAO obj = new MisDAOImpl();
					CommonDAO obj1 = new CommonDAOImpl();
					
					String Mandalid= (String)session.getAttribute("mandalId");
					String distID= (String)session.getAttribute("districtId");
					String month = (CommonUtility.checkNullObject(request.getParameter("month")));
					String mandalname=obj1.getMandalName(distID, Mandalid);					
					distID=obj.getNewdistrictID(distID, Mandalid);
					
					
					int curMonth=Integer.parseInt(new SimpleDateFormat("MM").format(cal.getTime()));
					String Year = new SimpleDateFormat("yyyy").format(cal.getTime());
					int finYear=0;
					
					if(curMonth<4)
					{
						finYear=Integer.parseInt(Year)-1;
					}
					else
					{
						finYear=Integer.parseInt(Year);
					}					
					if(month.trim().length()==0)
					{
						
						month=""+curMonth;
					}
					
					String monthName = obj.getMonthForInt(Integer.parseInt(month));	
					
					
					
					
					resultlist    =  obj.getMandalTargetAchieveBal( ""+finYear, distID, Mandalid,Year+month);
				//	System.out.println("resultlist"+resultlist);
					monthlist=obj.getMonths(finYear);					
					
					int sum=0;
					//Buttons Enable Disable Logic
					//System.out.println("resultlist---------=======>"+resultlist);
					for(int i=0;i<resultlist.size();i++)
					{
						
						templist = (ArrayList) resultlist.get(i);
						//System.out.println("templist----"+templist.get(13));
						
						if(templist.get(13).toString().trim().equals("") || templist.get(13).toString().trim().equals("-") || templist.get(12).toString().trim().equals("Y"))
						{
							confirmButtonEnable="disabled"; 
						}
						if( templist.get(12).toString().trim().equals("Y") )
						{
							updatButtonDisable="disabled";
						}
						if(templist.get(12).toString().trim().equalsIgnoreCase("-"))
						{							
							actEnableCount++;
						}
						sum=sum + (int)Float.parseFloat((String)templist.get(4));
						
					}
					//System.out.println("bye");
					if(actEnableCount==resultlist.size())
					{
						monthDisable="disabled";
					}
					//System.out.println(actEnableCount);	
				
					request.setAttribute("MandalName",mandalname);
					request.setAttribute("monthName",monthName);
					request.setAttribute("Year",Year);
					request.setAttribute("monthlist",monthlist);
					request.setAttribute("resultlist",resultlist);
					request.setAttribute("month",month);
					
					request.setAttribute("monthDisable",testnull(""+sum));					
					request.setAttribute("confirmButtonEnable",confirmButtonEnable);
					request.setAttribute("updatButtonDisable",updatButtonDisable);
					//System.out.println("uu");
			}
			catch(Exception e)
			{
				request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
		 		return mapping.findForward("exception"); 
			}
				
				return mapping.findForward(target);
		}
		public ActionForward updateMandalTargetValues(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)
		{			
	    	String returnstmt="success";
	    	String confirmButtonEnable="";
			String updatButtonDisable="";
			int actEnableCount=0;
			String monthDisable="";
			ArrayList templist = new ArrayList();
			ArrayList resultlist = new ArrayList();
			ArrayList monthlist = new ArrayList();			
			HttpSession session = request.getSession();
			MisDAO obj = new MisDAOImpl();
			CommonDAO obj1 = new CommonDAOImpl();
	    	
			
			 try
			 {
						String target;
						String achieve;
						float bal;
						String misTarId="";
						
						Calendar cal = Calendar.getInstance();						
						String CurYear = new SimpleDateFormat("yyyy").format(cal.getTime());
						
						String Mandalid= (String)session.getAttribute("mandalId");
						String distID= (String)session.getAttribute("districtId");
						String loginid= (String)session.getAttribute("loginid");
						String Selmonth = request.getParameter("month");
						String mandalname=obj1.getMandalName(distID, Mandalid);					
						distID=obj.getNewdistrictID(distID, Mandalid);
						String yearMonth=CurYear+Selmonth;
						
						int curMonth=Integer.parseInt(new SimpleDateFormat("MM").format(cal.getTime()));
					
						int finYear=0;						
						if(curMonth<4)
						{
							finYear=Integer.parseInt(CurYear)-1;
						}
						else
						{
							finYear=Integer.parseInt(CurYear);
						}
						
						ArrayList achiveList = new ArrayList();		
						ArrayList targetList = new ArrayList();		
						ArrayList misTarIdList = new ArrayList();
						ArrayList misBalList = new ArrayList();
						HashMap inputList = new HashMap();
						
						for(int val=0;val<17;val++)
						{
							// System.out.println("\t"+request.getParameter("tar"+val));
							 target=  CommonUtility.checkNullObj((request.getParameter("tar"+val)));
							 achieve= CommonUtility.checkNullObj((request.getParameter("ach_"+val)));
							 misTarId= CommonUtility.checkNullObj(request.getParameter("misTarId"+val));
							 
							 if(target.trim().length()==0 || achieve.trim().length()==0 )
							 {
								 
							 }
							 else if((target.trim().length()>0)&&(achieve.trim().length()>0))//&&(Float.parseFloat(target)-Float.parseFloat(achieve)>0))
							 {
								 targetList.add(Float.parseFloat(target));
								 achiveList.add(Float.parseFloat(achieve));
								 bal=Float.parseFloat(target)-Float.parseFloat(achieve);								 
								 misBalList.add(Float.parseFloat(target)-Float.parseFloat(achieve));
								 misTarIdList.add(misTarId);
							 }							 
						}						
							
							
							
							inputList.put("targetList", targetList);
							inputList.put("achiveList", achiveList);
							inputList.put("misTarIdList", misTarIdList);
							inputList.put("misBalList", misBalList);
							inputList.put("Mandalid", Mandalid);
							inputList.put("distId", distID);
							inputList.put("loginid", loginid);
							inputList.put("finYear", finYear);
							inputList.put("yearMonth", yearMonth);
							
							
							monthlist=obj.getMonths(Integer.parseInt(CurYear));
									String resultMSG="";
									
									if(achiveList.size()>0)
									{
										resultMSG=obj.updateMandalTarAchieve( inputList);										
									}
									else
									{										
										resultMSG="<font color='red'><b> Please Fill Atleat One Value.!</b></font>";
									}
							
							String monthName = obj.getMonthForInt(Integer.parseInt(Selmonth));
							resultlist    =  obj.getMandalTargetAchieveBal(CurYear, distID, Mandalid,yearMonth);
							int sum=0;
						//	System.out.println("resultlist---------++++++>"+resultlist);
							//Buttons Enable Disable Logic
							for(int i=0;i<resultlist.size();i++)
							{
								templist = (ArrayList) resultlist.get(i);
								if( templist.get(13).toString().trim().equals("-") || templist.get(13).toString().trim().equals("") || templist.get(12).toString().trim().equals("Y"))
								{
									confirmButtonEnable="disabled";
								}
								if(((String) templist.get(12)).trim().equals("Y"))
								{
									updatButtonDisable="disabled";
								}
								if(templist.get(12).toString().trim().equalsIgnoreCase("-"))
								{									
									actEnableCount++;
								}
								sum=sum + (int)Float.parseFloat((String)templist.get(4));
							}
							if(actEnableCount==17)
							{
								monthDisable="disabled";
							}
							
							request.setAttribute("MandalName",mandalname);
							request.setAttribute("monthName",monthName);
							request.setAttribute("Year",CurYear);						
							request.setAttribute("monthlist",monthlist);
							request.setAttribute("resultlist",resultlist);
							request.setAttribute("month",Selmonth);
							request.setAttribute("resultMSG",resultMSG);
							
							request.setAttribute("confirmButtonEnable",confirmButtonEnable);
							request.setAttribute("updatButtonDisable",updatButtonDisable);							
							request.setAttribute("monthDisable",testnull(""+sum));						
							
				}
				catch(Exception e)
				{
					request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
			 		return mapping.findForward("exception"); 
				}				
			 return mapping.findForward(returnstmt);				     
	    }
	
		public ActionForward confirmMethod(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)
		{		
	    	//System.out.println("hhhhhhconfirmMethodhhhh");
			String returnstmt="success";
	    	String confirmButtonEnable="";
			String updatButtonDisable="";
			int actEnableCount=0;
			String monthDisable="";
			ArrayList templist = new ArrayList();
			ArrayList resultlist = new ArrayList();
			ArrayList monthlist = new ArrayList();
			
			
			HttpSession session = request.getSession();
			MisDAO obj = new MisDAOImpl();
			CommonDAO obj1 = new CommonDAOImpl();
	    	
			
			 try
			 {
						String target;
						String achieve;
						float bal;
						String misTarId="";
						
						Calendar cal = Calendar.getInstance();
						
						String CurYear = new SimpleDateFormat("yyyy").format(cal.getTime());
						
						String Mandalid= (String)session.getAttribute("mandalId");
						String distID= (String)session.getAttribute("districtId");
						String month = request.getParameter("month");
						String mandalname=obj1.getMandalName(distID, Mandalid);
						distID=obj.getNewdistrictID(distID, Mandalid);
						String monthyear=CurYear+month;
						
						ArrayList achiveList = new ArrayList();	
						ArrayList targetList = new ArrayList();
						ArrayList misTarIdList = new ArrayList();
						ArrayList misBalList = new ArrayList();
						HashMap inputList = new HashMap();
						
						int curMonth=Integer.parseInt(new SimpleDateFormat("MM").format(cal.getTime()));
						
						int finYear=0;						
						if(curMonth<4)
						{
							finYear=Integer.parseInt(CurYear)-1;
						}
						else
						{
							finYear=Integer.parseInt(CurYear);
						}
						
						for(int val=0;val<17;val++)
						{
							// System.out.println("\t"+request.getParameter("tar"+val));
							 target=  CommonUtility.checkNullObj((request.getParameter("tar"+val)));
							 achieve= CommonUtility.checkNullObj((request.getParameter("ach_"+val)));
							 misTarId= CommonUtility.checkNullObj(request.getParameter("misTarId"+val));
							 
							 if(target.trim().length()==0 || achieve.trim().length()==0 )
							 {
								 
							 }
							 else if((target.trim().length()>0)&&(achieve.trim().length()>0))//&&(Float.parseFloat(target)-Float.parseFloat(achieve)>0))
							 {
								 targetList.add(Float.parseFloat(target));
								 achiveList.add(Float.parseFloat(achieve));
								 bal=Float.parseFloat(target)-Float.parseFloat(achieve);
								 
								 misBalList.add(Float.parseFloat(target)-Float.parseFloat(achieve));
								 misTarIdList.add(misTarId);
							 }							 
						}						
							
							String loginid= (String)session.getAttribute("loginid");
							
							inputList.put("targetList", targetList);
							inputList.put("achiveList", achiveList);
							inputList.put("misTarIdList", misTarIdList);
							inputList.put("misBalList", misBalList);
							inputList.put("Mandalid", Mandalid);
							inputList.put("distId", distID);
							inputList.put("loginid", loginid);
							inputList.put("finYear", finYear);
							inputList.put("monthyear", monthyear);
							
							//System.out.println(inputList);
							monthlist=obj.getMonths(finYear);
									
									String resultMSG="";
									
									if(achiveList.size()>0)
									{
										resultMSG=obj.confirmMandalTarAchieve( inputList);
										//System.out.println("resultMSG in update"+resultMSG);
									}
									else
									{
										
										resultMSG="<font color='red'><b> Please Fill Atleat One Value.!</b></font>";
									}
							
							String monthName = obj.getMonthForInt(Integer.parseInt(month));
							resultlist    =  obj.getMandalTargetAchieveBal( ""+finYear, distID, Mandalid,monthyear);
							int sum=0;
							
							//Buttons Enable Disable Logic
							for(int i=0;i<resultlist.size();i++)
							{
								templist = (ArrayList) resultlist.get(i);
								if( templist.get(13).toString().trim().equals("-") || templist.get(13).toString().trim().equals("") || templist.get(12).toString().trim().equals("Y"))
								{
									confirmButtonEnable="disabled";
								}
								if(((String) templist.get(12)).trim().equals("Y"))
								{
									updatButtonDisable="disabled";
								}
								if(templist.get(12).toString().trim().equalsIgnoreCase("-"))
								{									
									actEnableCount++;
								}
								sum=sum + (int)Float.parseFloat((String)templist.get(4));
							}
							if(actEnableCount==17)
							{
								monthDisable="disabled";
							}
							
							request.setAttribute("MandalName",mandalname);
							request.setAttribute("monthName",monthName);
							request.setAttribute("Year",CurYear);						
							request.setAttribute("monthlist",monthlist);
							request.setAttribute("resultlist",resultlist);
							request.setAttribute("month",month);
							request.setAttribute("resultMSG",resultMSG);
							
							request.setAttribute("confirmButtonEnable",confirmButtonEnable);
							request.setAttribute("updatButtonDisable",updatButtonDisable);
							request.setAttribute("monthDisable",testnull(""+sum));
							
							
				}
				catch(Exception e)
				{
					request.setAttribute("MSG", "<font color='red'size='3'><b>Error occurred.<br>Please Try Again..!</b></font>"); 	 		
			 		return mapping.findForward("exception"); 
				}				
			 return mapping.findForward(returnstmt);
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
