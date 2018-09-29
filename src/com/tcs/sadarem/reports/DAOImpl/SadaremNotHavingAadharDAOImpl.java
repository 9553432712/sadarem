package com.tcs.sadarem.reports.DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.sadarem.reports.DAO.SadaremNotHavingAadharDAO;
import com.tcs.sadarem.util.DataAccess;

public class SadaremNotHavingAadharDAOImpl implements SadaremNotHavingAadharDAO  {
	static final private Logger log = Logger.getLogger(DirectorReportOutsideDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	
	public ArrayList  getData(String district,String mandal,String village,String areatype,String Trigp)
	{
		
                ArrayList resultList	= new ArrayList();
                ArrayList resultList1     = new ArrayList();
		try{
					
					ArrayList tempList	    = new ArrayList();
					
					//System.out.println("getData  Calling ");
					lStrQuery = " {CALL PRO_SADAREM_REPORT_1_3_AADHAAR_NOTUPDATED(?,?,?,?,?)}";
					
					
					
					
					
					tempList.add(district);
			       	tempList.add(mandal);
					tempList.add(village);				
					tempList.add(areatype);
					tempList.add(Trigp);
					
					
					
					
					
				  	log.info("getabstractassesseddata lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
				  	
					resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
					System.out.println(tempList+"\ngetData  Calling \n"+resultList);
	
					if(resultList.size()>0 )
					{
						resultList1 =(ArrayList)resultList.get(0);
					}
								
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in DirectorReportOutsideDAOImpl @ getabstractassesseddata : "+lexp.getLocalizedMessage());
					 lexp.printStackTrace();
				 }
			finally
			{
				System.out.println("It is calling getData---------------------");
			}
				
				return resultList1;	
		
	}
	public String sadaremnothavingheadingmsg(String distname,String mandalname,String villagename,String districtid,String mandalid,String villageid,String areatype,String trigpsel)
	{
		String msg="";
	
		if(areatype.length()>0)
		{
			if(areatype.equalsIgnoreCase("-1"))
			{
				msg+="<font color='blue'><b> Area : </b></font>All,";
			}
			else if(areatype.equalsIgnoreCase("U"))
			{
				msg+="<font color='blue'><b> Area : </b></font>Urban, ";
			}
			else if(areatype.equalsIgnoreCase("R"))
			{
				msg+="<font color='blue'><b> Area : </b></font>Rural, ";
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
				msg+="<font color='blue'><b> Village : </b></font>"+villagename+", ";
			}
		}
		if(trigpsel.length()>0)
		{
			if(trigpsel.equals("-1"))
			{
				msg+="<font color='blue'><b> Project : </b></font>All.";
			}
			else
			{
				msg+="<font color='blue'><b> Project : </b></font>TRIGP.";
			}
		}
		//System.out.println("message:"+msg);
	return msg;	
	}

	
	

}
