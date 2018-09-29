
package com.tcs.sadarem.reports.DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.sadarem.reports.DAO.DirectorReportOutsideDAO;
import com.tcs.sadarem.reports.DAO.NewDirectorReportOutsideDAO;
import com.tcs.sadarem.util.CommonUtility;
import com.tcs.sadarem.util.DataAccess;

public class NewDirectorReportOutsideDAOImpl implements NewDirectorReportOutsideDAO {
	static final private Logger log = Logger.getLogger(DirectorReportOutsideDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	
	public ArrayList  getabstractassesseddata(String fromdate,String todate,String district)
	{
		
                ArrayList resultList	= new ArrayList();
                ArrayList resultList1     = new ArrayList();
		try{
					
					ArrayList tempList	    = new ArrayList();
					
					
					lStrQuery = " {CALL TEMP_CHANGED_PRO_SADAREM_REPORT_1_2_District_WISE(?,?,?)}";
					
					
					
					tempList.add(fromdate);// CommonUtility.getDateinFormat(fromdate, "dd/MM/yyyy", "MM/dd/yyyy"));
					tempList.add(todate); //CommonUtility.getDateinFormat(todate, "dd/MM/yyyy", "MM/dd/yyyy"));
					tempList.add(district);
			     //  	tempList.add(mandal);
				//	tempList.add(village);
				//	tempList.add(disability);
					//tempList.add(areatype);
				//	tempList.add(Trigp);
					
					
					
					
				  	log.info("getabstractassesseddata lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
				  	
					resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
				
	
					if(resultList.size()>0 )
					{
						resultList1 =(ArrayList)resultList.get(0);
					}
						
					//System.out.println("resultListttttt ============ "+lStrQuery);
					//System.out.println("resultListttttt ============ "+tempList);
					//System.out.println("resultListttttt ============ "+resultList1);
					
				}    
				 catch(SQLException sqle)
				 {    
					 log.info("SQLException in DirectorReportOutsideDAOImpl @ getabstractassesseddata :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in DirectorReportOutsideDAOImpl @ getabstractassesseddata : "+lexp.getLocalizedMessage());
				 }
				
				return resultList1;	
		
	}
	public ArrayList  getabstractassessedcampdata(String fromdate,String todate,String district,String mandal,String village,String campId,String areatype)
	{
		
                ArrayList resultList	= new ArrayList();
                ArrayList resultList1     = new ArrayList();
		try{
					
					ArrayList tempList	    = new ArrayList();
					
					
					lStrQuery = " {CALL PRO_SADAREM_REPORT_1_2_Camp_WISE(?,?,?,?,?,?,?)}";
					
					
					
					tempList.add(fromdate); //CommonUtility.getDateinFormat(fromdate, "dd/MM/yyyy", "MM/dd/yyyy"));
					tempList.add(todate); //CommonUtility.getDateinFormat(todate, "dd/MM/yyyy", "MM/dd/yyyy"));
					tempList.add(district);
			       	tempList.add(mandal);
					tempList.add(village);
					tempList.add(campId);
					tempList.add(areatype);
				
			
					
					
					
					
				  	log.info("getabstractassesseddata lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
				  	
					resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
				
	
					if(resultList.size()>0 )
					{
						resultList1 =(ArrayList)resultList.get(0);
					}
						
					//System.out.println("resultListttttt  "+resultList1);
					
				}    
				 catch(SQLException sqle)
				 {    
					 log.info("SQLException in DirectorReportOutsideDAOImpl @ getabstractassesseddata :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in DirectorReportOutsideDAOImpl @ getabstractassesseddata : "+lexp.getLocalizedMessage());
				 }
				
				return resultList1;	
		
	}
	public String getDistrictWiseHeading(String fromdate,String todate,String distname,String districtid)
	{
		String msg="";
		if(fromdate.length()>0)
		{
			msg="<font color='blue'><b>From</b></font>"+"  "+fromdate+" ";
		}
		if(todate.length()>0)
		{
			msg+="<font color='blue'><b>to </b></font>"+todate+" ,";
		}
		/*if(areatype.length()>0)
		{
			if(areatype.equalsIgnoreCase("-1"))
			{
				msg+="<font color='blue'><b> Area : </b></font>All,";
			}
			else if(areatype.equalsIgnoreCase("U"))
			{
				msg+="<font color='blue'><b> Area : </b></font>Urban,";
			}
			else if(areatype.equalsIgnoreCase("R"))
			{
				msg+="<font color='blue'><b> Area : </b></font>Rural,";
			}
		}*/
		if(districtid.length()>0)
		{
			if(districtid.equals("-1"))
			{
				msg+="<font color='blue'><b> District : </b></font>All.";
			}
			else
			{
				msg+="<font color='blue'><b> District : </b></font>"+distname+". ";
			}
		}
/*		if(mandalid.length()>0)
		{
			if(mandalid.equals("-1"))
			{
				msg+="<font color='blue'><b> Mandal : </b></font>All,";
			}
			else
			{
				msg+="<font color='blue'><b> Mandal : </b></font>"+mandalname+", ";
			}
		}
		if(villageid.length()>0)
		{
			if(villageid.equals("-1"))
			{
				msg+="<font color='blue'><b> Village : </b></font>All,";
			}
			else
			{
				msg+="<font color='blue'><b> Village : </b></font>"+villagename+", ";
			}
		}
		if(TRIGPSel.length()>0)
		{
			if(TRIGPSel.equals("-1"))
			{
				msg+="<font color='blue'><b> Project : </b></font>All.";
			}
			else
			{
				msg+="<font color='blue'><b> Project : </b></font>TRIGP.";
			}
		}*/
		//System.out.println("message:"+msg);
	return msg;	
	}
	public String getCampWiseHeading(String fromdate,String todate,String distname,String mandalname,String villagename,String districtid,String mandalid,String villageid,String campid,String campname,String areatype)
	{
		String msg="";
		if(fromdate.length()>0)
		{
			msg="<font color='blue'><b>From</b></font>"+"  "+fromdate+" ";
		}
		if(todate.length()>0)
		{
			msg+="<font color='blue'><b>to </b></font>"+todate+" ,";
		}
		if(areatype.length()>0)
		{
			if(areatype.equalsIgnoreCase("-1"))
			{
				msg+="<font color='blue'><b> Area : </b></font>All,";
			}
			else if(areatype.equalsIgnoreCase("U"))
			{
				msg+="<font color='blue'><b> Area : </b></font>Urban,";
			}
			else if(areatype.equalsIgnoreCase("R"))
			{
				msg+="<font color='blue'><b> Area : </b></font>Rural,";
			}
		}
		if(districtid.length()>0)
		{
			if(districtid.equals("-1"))
			{
				msg+="<font color='blue'><b> District : </b></font>All,";
			}
			else
			{
				msg+="<font color='blue'><b> District : </b></font>"+distname+", ";
			}
		}
		if(mandalid.length()>0)
		{
			if(mandalid.equals("-1"))
			{
				msg+="<font color='blue'><b> Mandal : </b></font>All,";
			}
			else
			{
				msg+="<font color='blue'><b> Mandal : </b></font>"+mandalname+", ";
			}
		}
		if(villageid.length()>0)
		{
			if(villageid.equals("-1"))
			{
				msg+="<font color='blue'><b> Village : </b></font>All,";
			}
			else
			{
				msg+="<font color='blue'><b> Village : </b></font>"+villagename+". ";
			}
		}
		if(campid.length()>0)
		{
			if(campid.equals("-1"))
			{
				msg+="<font color='blue'><b> Camp : </b></font>All.";
			}
			else
			{
				msg+="<font color='blue'><b> Camp : </b></font>"+campname+". ";
			}
		}
	//	System.out.println("message:"+msg);
	return msg;	
	}
}
