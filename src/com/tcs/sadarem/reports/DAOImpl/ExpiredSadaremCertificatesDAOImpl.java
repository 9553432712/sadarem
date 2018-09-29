package com.tcs.sadarem.reports.DAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.tcs.sadarem.openreports.DAO.ExpiredSadaremCertificatesDAO;
import com.tcs.sadarem.reports.DAO.ExpiredSadaremCertificateDAO;
import com.tcs.sadarem.util.DataAccess;

public class ExpiredSadaremCertificatesDAOImpl implements  ExpiredSadaremCertificateDAO{
	static final private Logger log = Logger.getLogger(DirectorReportOutsideDAOImpl.class);
	Connection lcon;
	String lStrQuery;
	public ArrayList  getabstractsadaremexpireddata(String fromdate,String todate,String district,String mandal,String areatype,String Trigp)
	{
		
                ArrayList resultList	= new ArrayList();
                ArrayList resultList1     = new ArrayList();
		try{
					
					ArrayList tempList	    = new ArrayList();
					
					
					lStrQuery = " {CALL PRO_SADAREM_REPORT_5_3_EXPIRED_CERTS_DETAILS(?,?,?,?,?,?)}";
					
					
					
					tempList.add(fromdate);
					tempList.add(todate); 
					tempList.add(district);
			       	tempList.add(mandal);
			       	tempList.add(areatype);
			       	tempList.add(Trigp);
					
			
					
					
					
					
				  	log.info("getabstracteducationdata lStrQuery : "+lStrQuery+"\n ParamList : "+tempList);
				  	
					resultList=(ArrayList)DataAccess.getStoredProcedureResultSet(lStrQuery,tempList,0,false,false);
				
	
					if(resultList.size()>0 )
					{
						resultList1 =(ArrayList)resultList.get(0);
					}


				}    
				 catch(SQLException sqle)
				 {    
					 log.info("SQLException in EducationWiseDAOImpl @ getabstracteducationdata :: Error Code : "+sqle.getErrorCode()+" :: Error Message : "+sqle.getLocalizedMessage());
				 }
				 catch(Exception lexp)
				 {
					 log.info("Exception in EducationWiseDAOImpl @ getabstracteducationdata : "+lexp.getLocalizedMessage());
				 }
				
				return resultList1;	
		
	}
	public String getHeading(String fromdate, String todate, String distname,String mandalname, String district,
			String mandal, String areatype,String TRIGPSel)
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
		if(district.length()>0)
		{
			if(district.equals("-1"))
			{
				msg+="<font color='blue'><b> District : </b></font>All,";
			}
			else
			{
				msg+="<font color='blue'><b> District : </b></font>"+distname+", ";
			}
		}
		if(mandal.length()>0)
		{
			if(mandal.equals("-1"))
			{
				msg+="<font color='blue'><b> Mandal : </b></font>All,";
			}
			else
			{
				msg+="<font color='blue'><b> Mandal : </b></font>"+mandalname+", ";
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
		}
		
	
		//System.out.println("message:"+msg);
	return msg;	
		
	}
	



}
